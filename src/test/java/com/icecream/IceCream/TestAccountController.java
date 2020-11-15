package com.icecream.IceCream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;

import com.icecream.IceCream.controller.AccountController;
import com.icecream.IceCream.dto.AccountDTO;

import com.icecream.IceCream.dto.SigninDTO;
import com.icecream.IceCream.exception.ApiRequestException;
import com.icecream.IceCream.model.Account;
import com.icecream.IceCream.repository.AccountRepository;
import com.icecream.IceCream.service.AccountService;

@RunWith(MockitoJUnitRunner.class)
public class TestAccountController {

	@Mock
	AccountService accountService;

	@Mock
	AccountRepository accountRepository;

	@Mock
	Account account;

	private AccountController accountController;

	@Before
	public void setUp() {
		accountController = new AccountController(accountService,accountRepository);
	}

	@After
	public void end() {
		System.out.println("Testing phrase ends");
	}


	@Test
	public void testSignIn() {
		SigninDTO signinDTO = new SigninDTO("anhvu1", "123456");
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(1);
		accountDTO.setUsername("anhvu1");
//		accountDTO.setFullname("");
//		accountDTO.setGender("");
//		accountDTO.setEmail("");
//		accountDTO.setRoleId(listRole);
//		accountDTO.setStatusId(1);

		Mockito.when(accountService.signIn(signinDTO)).thenReturn(accountDTO);

		AccountDTO signInResult = accountController.signIn(signinDTO);

		Assert.assertEquals(signInResult.getId(), 1);
		Assert.assertEquals(signInResult.getUsername(), "anhvu1");
//		
//		Assert.assertEquals(signInResult.getFullname(),"");
//		Assert.assertEquals(signInResult.getGender(),"");
//		Assert.assertEquals(signInResult.getEmail(),"");
////		Assert.assertEquals(signInResult.getRoleId(),);
//		Assert.assertEquals(signInResult.getStatusId(),1);
	}

//	@Test
	public void testSignUp() {
		SigninDTO signinDTO = new SigninDTO("anhvu1", "123456");
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setUsername("anhvu1");
		accountDTO.setPassword("123456");
		accountService = mock(AccountService.class);
		when(accountService.signUp(signinDTO)).thenReturn(accountDTO);
		AccountDTO signupResult = accountController.signUp(signinDTO);
		Assert.assertEquals(signupResult.getUsername(), "anhvu1");
	}

//	@Test
	public void mockExceptionThrowing() {
		SigninDTO signinDTO = new SigninDTO();
		signinDTO.setUsername("anhvu1");
		signinDTO.setPassword("123456");
		Mockito.when(accountService.signIn(signinDTO)).thenThrow(ApiRequestException.class);
		AccountDTO login = null;
		try {
			login = accountController.signIn(signinDTO);
		} catch (Exception e) {
			// TODO: handle exception
			Assert.assertEquals(new ApiRequestException("Fail : " + e), login);
		}
		Mockito.verify(accountService).signIn(signinDTO);
	}

}
