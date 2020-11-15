package com.icecream.IceCream.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icecream.IceCream.dto.AccountDTO;
import com.icecream.IceCream.dto.SigninDTO;
import com.icecream.IceCream.dto.UpdateAccountDTO;
import com.icecream.IceCream.dto.UpdatePasswordDTO;
import com.icecream.IceCream.model.Account;
import com.icecream.IceCream.repository.AccountRepository;
import com.icecream.IceCream.service.AccountService;

@Configuration
@EnableWebSecurity
@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
	 private final AccountService accountService;
	 private final AccountRepository accountRepository;

	public AccountController(AccountService accountService, AccountRepository accountRepository) {
		this.accountService = accountService;
		this.accountRepository = accountRepository;
	}

	// find a user
	// =>Role: Admin or User
	@RequestMapping("/{username}")
	public ResponseEntity<AccountDTO> findByUsername(@PathVariable(value = "username") String username) {
		return ResponseEntity.ok().body(accountService.findByUsername(username));
	}
	

	@RequestMapping("/find/")
	public Page<AccountDTO> getPageAccountByUsername(@RequestParam String username, @RequestParam long roleId,
			@RequestParam int page) {
		return accountService.getPageAccountByUsernameAndRoleId(username, roleId, page);
	}

	// SignUp
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public AccountDTO signUp(@RequestBody SigninDTO signinDTO) {
		Account account;
		account = accountRepository.findByUsername(signinDTO.getUsername());
		if (account == null) {
			return accountService.signUp(signinDTO);
		}
		return null;

	}

	// Sign In
	// =>Role: ...
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public AccountDTO signIn(@RequestBody SigninDTO signinDTO) {
		return accountService.signIn(signinDTO);
	}

	// Update Account
	// =>Role: Admin, User, Customer
	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	public ResponseEntity<AccountDTO> updateAccountPassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
		return ResponseEntity.ok().body(accountService.updateAccountPassword(updatePasswordDTO));
	}

	// Update Account
	// =>Role: Admin, User, Customer
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<AccountDTO> updateAccount(@RequestBody UpdateAccountDTO updateAccountDTO) {
		return  ResponseEntity.ok().body(accountService.updateAccount(updateAccountDTO));
	}

	// Delete Account
	// =>Role: Admin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean deleteAccount(@PathVariable long id) {
		return accountService.deleteAccount(id);
	}

}