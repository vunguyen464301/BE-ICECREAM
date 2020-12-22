package com.icecream.IceCream.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icecream.IceCream.dto.SigninDTO;
import com.icecream.IceCream.dto.UpdateAccountDTO;
import com.icecream.IceCream.dto.UpdatePasswordDTO;
import com.icecream.IceCream.repository.AccountRepository;
import com.icecream.IceCream.service.impl.AccountServiceImpl;

@Configuration
@EnableWebSecurity
@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
	private final AccountServiceImpl accountService;

	public AccountController(AccountServiceImpl accountService) {
		this.accountService = accountService;
	}

	// find a user
	// =>Role: Admin or User
	@RequestMapping("/{username}")
	public ResponseEntity<?> findByUsername(@PathVariable(value = "username") String username) {
		return accountService.findByUsername(username);
	}

	@RequestMapping("/find/")
	public ResponseEntity<?> getPageAccountByUsername(@RequestParam String username, @RequestParam long roleId,
			@RequestParam int page) {
		return accountService.getPageAccountByUsernameAndRoleId(username, roleId, page);
	}

	// SignUp
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<?> signUp(@RequestBody SigninDTO signinDTO) {
		return accountService.signUp(signinDTO);
	}

	// Sign In
	// =>Role: ...
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> signIn(@RequestBody SigninDTO signinDTO) {
		return accountService.signIn(signinDTO);
	}

	// Update Account
	// =>Role: Admin, User, Customer
	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAccountPassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
		return accountService.updateAccountPassword(updatePasswordDTO);
	}

	// Update Account
	// =>Role: Admin, User, Customer
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAccount(@RequestBody UpdateAccountDTO updateAccountDTO) {
		return accountService.updateAccount(updateAccountDTO);
	}

	// Delete Account
	// =>Role: Admin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAccount(@PathVariable long id) {
		return accountService.deleteAccount(id);
	}

}