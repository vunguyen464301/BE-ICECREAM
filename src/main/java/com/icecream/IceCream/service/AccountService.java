package com.icecream.IceCream.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.icecream.IceCream.Interface.AccountInterface;
import com.icecream.IceCream.dto.AccountDTO;
import com.icecream.IceCream.dto.SigninDTO;
import com.icecream.IceCream.dto.UpdateAccountDTO;
import com.icecream.IceCream.dto.UpdatePasswordDTO;
import com.icecream.IceCream.exception.ApiRequestException;
import com.icecream.IceCream.model.Account;
import com.icecream.IceCream.model.AccountDetail;
import com.icecream.IceCream.model.Role;
import com.icecream.IceCream.model.Status;
import com.icecream.IceCream.repository.AccountPageRepository;
import com.icecream.IceCream.repository.AccountRepository;
import com.icecream.IceCream.repository.RoleRepositoty;
import com.icecream.IceCream.repository.StatusRepository;
import com.icecream.IceCream.repository.AccountAuthRepository;
import com.icecream.IceCream.security.AccountAuth;

@Service
public class AccountService implements AccountInterface, UserDetailsService {
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	AccountAuthRepository userRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	RoleRepositoty roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	AccountPageRepository accountPageRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Account> user = userRepository.findByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found:" + username));
		return user.map(AccountAuth::new).get();
	}

	@Override
	public Page<AccountDTO> getPageAccountByUsernameAndRoleId(String username, long roleId, int page) {
		Page<Account> pageAccount = accountPageRepository.findByUsernameAndRole(username, roleId,
				PageRequest.of(page, 5));
		return convertToPageAccountDTO(pageAccount);

	}

	@Override
	public Optional<Account> findById(Long id) {
		try {
			return accountRepository.findById(id);
		} catch (Exception e) {
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public AccountDTO findByUsername(String username) {
		try {
			Account account = accountRepository.findByUsername(username);
			if (account != null) {
				return convertToAccountDTO(account);
			}
			return null;
		} catch (Exception e) {
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public AccountDTO signIn(SigninDTO signinDTO) {
		try {
			Account account = accountRepository.findByUsername(signinDTO.getUsername());
			if (passwordEncoder.matches(signinDTO.getPassword(), account.getPassword())) {
				return convertToAccountDTO(account);
			}
			return null;
		} catch (Exception e) {
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public AccountDTO signUp(SigninDTO signinDTO) {
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);

		Account account = new Account();
		account.setUsername(signinDTO.getUsername());
		account.setStatus(statusRepository.findStatusById((long) 1));
		account.setPassword(passwordEncoder.encode(signinDTO.getPassword()));
		account.setRole(Collections.singleton(roleRepository.findRoleById((long) 3)));
		account.setCreated_date(date);
		return convertToAccountDTO(accountRepository.save(account));

	}

	@Override
	public AccountDTO updateAccountPassword(UpdatePasswordDTO updatePasswordDTO) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Account account = accountRepository.findByUsername(updatePasswordDTO.getUsername());
			if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
					&& !account.getUsername().equals(auth.getName())) {
				account.setPassword(passwordEncoder.encode(updatePasswordDTO.getPasswordNew()));
				return convertToAccountDTO(accountRepository.save(account));
			} else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
					&& account.getUsername().equals(auth.getName())
					&& passwordEncoder.matches(updatePasswordDTO.getPasswordOld(), account.getPassword())) {
				account.setPassword(passwordEncoder.encode(updatePasswordDTO.getPasswordNew()));
				return convertToAccountDTO(accountRepository.save(account));

			} else if (account.getUsername().equals(auth.getName())
					&& passwordEncoder.matches(updatePasswordDTO.getPasswordOld(), account.getPassword())) {
				account.setPassword(passwordEncoder.encode(updatePasswordDTO.getPasswordNew()));
				return convertToAccountDTO(accountRepository.save(account));
			}
			return null;
		} catch (
		Exception e) {
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public AccountDTO updateAccount(UpdateAccountDTO updateAccountDTO) throws ApiRequestException {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Account account = accountRepository.findByUsername(updateAccountDTO.getUsername());
			AccountDetail accDetail;
			Role role;
			Status status;
			if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
					|| account.getUsername().equals(auth.getName())) {
				if (account.getDetails() == null) {
					accDetail = new AccountDetail();
					accDetail.setDetails(account);
					account.setDetails(accDetail);
				}
				Set<Role> setRoleNew = new HashSet<Role>();
				role = roleRepository.findById(updateAccountDTO.getRoleId().get(0)).orElseThrow(
						() -> new ApiRequestException("Not found role at id: " + updateAccountDTO.getRoleId().get(0)));
				setRoleNew.add(role);

				status = statusRepository.findById(updateAccountDTO.getStatusId()).orElseThrow(
						() -> new ApiRequestException("Not found status at id: " + updateAccountDTO.getStatusId()));

				account.getDetails()
						.setFullname(updateAccountDTO.getFullname() != null ? updateAccountDTO.getFullname() : "");
				account.getDetails().setEmail(updateAccountDTO.getEmail() != null ? updateAccountDTO.getEmail() : "");
				account.getDetails()
						.setGender(updateAccountDTO.getGender() != null ? updateAccountDTO.getGender() : "");
				account.setRole(setRoleNew);
				account.setStatus(status);

				return convertToAccountDTO(accountRepository.save(account));
			}
			return null;
		} catch (Exception e) {
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public Boolean deleteAccount(long id) {
		try {
			accountRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			throw new ApiRequestException("Fail : " + e);
		}
	}

	public Page<AccountDTO> convertToPageAccountDTO(Page<Account> pageAccount) {
		List<Account> listAccount;
		List<AccountDTO> listAccountDTO = new ArrayList<AccountDTO>();
		listAccount = pageAccount.stream().map(e -> e).collect(Collectors.toList());
		for (int i = 0; i < listAccount.size(); i++) {
			AccountDTO accountDTO = new AccountDTO();
			List<Long> listRoleId;
			accountDTO.setId(listAccount.get(i).getId());
			accountDTO.setUsername(listAccount.get(i).getUsername());
			accountDTO.setFullname(
					listAccount.get(i).getDetails() != null ? listAccount.get(i).getDetails().getFullname() : "");
			accountDTO.setEmail(
					listAccount.get(i).getDetails() != null ? listAccount.get(i).getDetails().getEmail() : "");
			accountDTO.setGender(
					listAccount.get(i).getDetails() != null ? listAccount.get(i).getDetails().getGender() : "");
			listRoleId = listAccount.get(i).getRole().stream().map(e -> e.getId()).collect(Collectors.toList());
			accountDTO.setRoleId(listRoleId);
			accountDTO.setStatusId(listAccount.get(i).getStatus().getId());
			accountDTO.setCreated_date(listAccount.get(i).getCreated_date());
			listAccountDTO.add(accountDTO);
		}
		Page<AccountDTO> pageAccountDTO = new PageImpl<AccountDTO>(listAccountDTO, pageAccount.getPageable(),
				listAccountDTO.size());
		return pageAccountDTO;
	}

	public AccountDTO convertToAccountDTO(Account account) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(account.getId());
		accountDTO.setUsername(account.getUsername());
		accountDTO.setFullname(account.getDetails() != null ? account.getDetails().getFullname() : "");
		accountDTO.setEmail(account.getDetails() != null ? account.getDetails().getEmail() : "");
		accountDTO.setGender(account.getDetails() != null ? account.getDetails().getGender() : "");

		Set<Role> setRoleAccount = account.getRole();
		List<Long> listRole = setRoleAccount.stream().map(e -> e.getId()).collect(Collectors.toList());

		accountDTO.setRoleId(listRole);
		accountDTO.setStatusId(account.getStatus().getId());
		accountDTO.setCreated_date(account.getCreated_date());

		return accountDTO;
	}

}
