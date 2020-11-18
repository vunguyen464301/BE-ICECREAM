package com.icecream.IceCream.mappingData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.icecream.IceCream.dto.AccountDTO;
import com.icecream.IceCream.model.Account;
import com.icecream.IceCream.model.Role;

public class MappingAccountDTO {
	
	
	public MappingAccountDTO() {
		super();
		// TODO Auto-generated constructor stub
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
