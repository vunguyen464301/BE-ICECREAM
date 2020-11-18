package com.icecream.IceCream.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.icecream.IceCream.dto.AccountDTO;
import com.icecream.IceCream.dto.SigninDTO;
import com.icecream.IceCream.dto.UpdateAccountDTO;
import com.icecream.IceCream.dto.UpdatePasswordDTO;
import com.icecream.IceCream.model.Account;

public interface AccountService extends UserDetailsService {
	Page<AccountDTO> getPageAccountByUsernameAndRoleId(String username, long roleId, int page);

	Optional<Account> findById(Long id);

	// sigin or signup
	AccountDTO findByUsername(String username);

	AccountDTO signIn(SigninDTO signinDTO);

	AccountDTO signUp(SigninDTO signinDTO);

	AccountDTO updateAccountPassword(UpdatePasswordDTO updatePasswordDTO);

	AccountDTO updateAccount(UpdateAccountDTO updateAccountDTO);

	Boolean deleteAccount(long id);
}
