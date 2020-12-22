package com.icecream.IceCream.service;

import java.util.Optional;

import javax.xml.ws.Response;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.icecream.IceCream.dto.AccountDTO;
import com.icecream.IceCream.dto.SigninDTO;
import com.icecream.IceCream.dto.UpdateAccountDTO;
import com.icecream.IceCream.dto.UpdatePasswordDTO;
import com.icecream.IceCream.model.Account;

public interface AccountService extends UserDetailsService {
	ResponseEntity<?> getPageAccountByUsernameAndRoleId(String username, long roleId, int page);

	ResponseEntity<?> findById(Long id);

	// sigin or signup

	ResponseEntity<?> signIn(SigninDTO signinDTO);

	ResponseEntity<?> signUp(SigninDTO signinDTO);

	ResponseEntity<?> updateAccountPassword(UpdatePasswordDTO updatePasswordDTO);

	ResponseEntity<?> updateAccount(UpdateAccountDTO updateAccountDTO);

	ResponseEntity<?> deleteAccount(long id);
	
	ResponseEntity<?> findByUsername(String username);
	
}
