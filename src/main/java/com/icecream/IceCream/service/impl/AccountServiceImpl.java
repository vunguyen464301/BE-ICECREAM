package com.icecream.IceCream.service.impl;

import java.sql.Date;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.icecream.IceCream.dto.AccountDTO;
import com.icecream.IceCream.dto.SigninDTO;
import com.icecream.IceCream.dto.UpdateAccountDTO;
import com.icecream.IceCream.dto.UpdatePasswordDTO;
import com.icecream.IceCream.exception.ApiRequestException;
import com.icecream.IceCream.mappingData.MappingAccountDTO;
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
import com.icecream.IceCream.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {
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
	
	private static MappingAccountDTO mappingAccountDTO = new MappingAccountDTO();

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
		return mappingAccountDTO.convertToPageAccountDTO(pageAccount);

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
				return mappingAccountDTO.convertToAccountDTO(account);
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
				return mappingAccountDTO.convertToAccountDTO(account);
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
		return mappingAccountDTO.convertToAccountDTO(accountRepository.save(account));

	}

	@Override
	public AccountDTO updateAccountPassword(UpdatePasswordDTO updatePasswordDTO) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Account account = accountRepository.findByUsername(updatePasswordDTO.getUsername());
			if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
					&& !account.getUsername().equals(auth.getName())) {
				account.setPassword(passwordEncoder.encode(updatePasswordDTO.getPasswordNew()));
				return mappingAccountDTO.convertToAccountDTO(accountRepository.save(account));
			} else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
					&& account.getUsername().equals(auth.getName())
					&& passwordEncoder.matches(updatePasswordDTO.getPasswordOld(), account.getPassword())) {
				account.setPassword(passwordEncoder.encode(updatePasswordDTO.getPasswordNew()));
				return mappingAccountDTO.convertToAccountDTO(accountRepository.save(account));

			} else if (account.getUsername().equals(auth.getName())
					&& passwordEncoder.matches(updatePasswordDTO.getPasswordOld(), account.getPassword())) {
				account.setPassword(passwordEncoder.encode(updatePasswordDTO.getPasswordNew()));
				return mappingAccountDTO.convertToAccountDTO(accountRepository.save(account));
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

				return mappingAccountDTO.convertToAccountDTO(accountRepository.save(account));
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

}
