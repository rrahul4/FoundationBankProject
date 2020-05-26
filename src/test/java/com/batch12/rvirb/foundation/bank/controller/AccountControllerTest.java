package com.batch12.rvirb.foundation.bank.controller;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.batch12.rvirb.foundation.bank.entities.Account;
import com.batch12.rvirb.foundation.bank.entities.TransferFund;
import com.batch12.rvirb.foundation.bank.exceptions.AccountNotFound;
import com.batch12.rvirb.foundation.bank.service.AccountService;
import com.google.gson.Gson;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
@TestMethodOrder(OrderAnnotation.class)
class AccountControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AccountService accountService;
	
	@BeforeEach
    public void init(TestInfo testInfo) {
        System.out.println(" -- BEGIN " + testInfo.getDisplayName() + " -- ");
    
    }
    
    @AfterEach
    public void end() {
        System.out.println(" -- END -- ");
    
    }
	
	@Test
	@Order(1)
	public void test_getAccounts() throws Exception {
				
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, Account.AccountType.Current, 700D, null));
		
		System.out.println(accountList);
				
		Mockito.when(accountService.getAccounts()).thenReturn(accountList);
		
		RequestBuilder request=MockMvcRequestBuilders
				.get("/accounts")
				.accept(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String expectedResult = "[{\"accountId\":1,\"accountType\":\"Current\",\"accountBalance\":700.0}]";
		
		assertEquals(expectedResult, response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	@Order(2)
	public void test_getAccount() throws Exception {

		Account account = new Account(1, Account.AccountType.Current, 700D, null);		
		
		System.out.println(account);
				
		Mockito.when(accountService.getAccount(Mockito.anyInt())).thenReturn(account);
		
		RequestBuilder request=MockMvcRequestBuilders
				.get("/accounts/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String expectedResult = "{\"accountId\":1,\"accountType\":\"Current\",\"accountBalance\":700.0}";
		
		assertEquals(expectedResult, response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@Test
	@Order(3)
	public void test_getAccount_negative() throws Exception {
	
		String exceptionMessage = "Account Not Found!!";
		Mockito.when(accountService.getAccount(Mockito.anyInt())).thenThrow(new AccountNotFound(exceptionMessage));
		
		RequestBuilder request=MockMvcRequestBuilders
				.get("/accounts/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		
	}

	@Test
	@Order(4)
	public void test_transferFunds() throws Exception {
		
		Account account1 = new Account(1, Account.AccountType.Current, 300D, null);
		Account account2 = new Account(2, Account.AccountType.Current, 500D, null);
		
		System.out.println(account1);
		System.out.println(account2);
		
		TransferFund transferFund = new TransferFund(1, 2 ,200D);
		
		Gson gson = new Gson();		
		String transferFundJson = gson.toJson(transferFund);
		System.out.println(transferFundJson);
		
		Mockito.when(accountService.getAccount(Mockito.anyInt())).thenReturn(account1).thenReturn(account2);
		
		RequestBuilder request=MockMvcRequestBuilders
				.put("/accounts/transferFunds")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(transferFundJson);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String expectedResult = "Transfer Successful !!!";
		
		assertEquals(expectedResult, response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	@Order(5)
	public void test_transferFunds_negative() throws Exception {
								
		Account account1 = new Account(1, Account.AccountType.Current, 150D, null);
		Account account2 = new Account(2, Account.AccountType.Current, 500D, null);
		
		System.out.println(account1);
		System.out.println(account2);
		
		TransferFund transferFund = new TransferFund(1,2,200D);
		
		Gson gson = new Gson();		
		String transferFundJson = gson.toJson(transferFund);
		System.out.println(transferFundJson);
		
		Mockito.when(accountService.getAccount(Mockito.anyInt())).thenReturn(account1).thenReturn(account2);
		
		RequestBuilder request=MockMvcRequestBuilders
				.put("/accounts/transferFunds")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(transferFundJson);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String expectedResult = "Transfer Unsuccessful! From Account is not having enough Balance: " + account1.getAccountBalance();
		
		assertEquals(expectedResult, response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());		
		
	}
	
}
