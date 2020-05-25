package com.batch12.rvirb.foundation.bank.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.batch12.rvirb.foundation.bank.entities.Account;
import com.batch12.rvirb.foundation.bank.exceptions.AccountNotFound;
import com.batch12.rvirb.foundation.bank.repositories.AccountRepository;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class AccountServiceTest {
	
	@InjectMocks 
	AccountServiceImpl2 accountService;
	
	@Mock
	private AccountRepository accountRepository;

	@Test
	@Order(1)
	public void test_getAccount() {

		Optional<Account> mockAccount = Optional.of(new Account(1, Account.AccountType.Current, 200D, null));
		
		Mockito.when(accountRepository.findById(Mockito.anyInt())).thenReturn(mockAccount);
			
		Account accountFetched = accountService.getAccount(1);		
		System.out.println(accountFetched);

		assertEquals(mockAccount.get().getAccountBalance(), accountFetched.getAccountBalance());
		verify(accountRepository,atLeast(1)).findById(Mockito.anyInt());
	}
	
	@Test
	@Order(2)
	public void test_getAccount_negative() {

		String exceptionMessage = "Account Not Found!!";
		Mockito.when(accountRepository.findById(Mockito.anyInt())).thenThrow(new AccountNotFound(exceptionMessage));
	
		Throwable exception = assertThrows(AccountNotFound.class, () -> accountService.getAccount(1));
		
		assertEquals(exception.getMessage(), exceptionMessage);
		verify(accountRepository,atLeast(1)).findById(Mockito.anyInt());
	}

	@Test
	@Order(3)
	public void test_getAccounts() {

		List<Account> accountList = new ArrayList<Account>();
		
		accountList.add(new Account(1, Account.AccountType.Current, 200D, null));
		accountList.add(new Account(2, Account.AccountType.Joint, 300D, null));
		
		Mockito.when(accountRepository.findAll()).thenReturn(accountList);
	
		List<Account> accountListFetched = accountService.getAccounts();
		Account accountFetched = accountListFetched.get(1);
		Account accountSaved = accountList.get(1);
		
		System.out.println(accountFetched);

		assertEquals(accountSaved.getAccountBalance(), accountFetched.getAccountBalance());
		verify(accountRepository,atLeast(1)).findAll();	
		
	}
	
	@Test
	@Order(4)
	public void test_getAccounts_negative() {

		List<Account> accountList = new ArrayList<Account>();
				
		Mockito.when(accountRepository.findAll()).thenReturn(accountList);
	
		List<Account> accountListFetched = accountService.getAccounts();
		
		System.out.println(accountListFetched);

		assertEquals(accountList, accountListFetched);
		verify(accountRepository,atLeast(1)).findAll();	
		
	}
	
	@Test
	@Order(5)
	public void test_createAccount() {
		
		Account account = new Account(1, Account.AccountType.Current, 200D, null);
		Account mockAccount = new Account(1, Account.AccountType.Current, 500D, null);
		
		Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(mockAccount);
	
		Account accountCreated = accountService.createAccount(account);
		
		System.out.println(accountCreated);

		assertEquals(mockAccount.getAccountBalance(), accountCreated.getAccountBalance());
		verify(accountRepository,atLeast(1)).save(Mockito.any(Account.class));	
		
	}
	
	@Test
	@Order(6)
	public void test_updateAccount() {

		Account account = new Account(1, Account.AccountType.Current, 200D, null);
		Optional<Account> mockAccount = Optional.of(new Account(1,Account.AccountType.Current, 500D, null));
		
		Mockito.when(accountRepository.findById(Mockito.anyInt())).thenReturn(mockAccount);
		Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(mockAccount.get());
	
		Account accountUpdated = accountService.updateAccount(1, account);
		
		System.out.println(accountUpdated);

		assertEquals(mockAccount.get().getAccountBalance(), accountUpdated.getAccountBalance());
		verify(accountRepository,atLeast(1)).findById(Mockito.anyInt());
		verify(accountRepository,atLeast(1)).save(Mockito.any(Account.class));	
		
	}
	
	@Test
	@Order(7)
	public void test_updateAccount_negative() {

		Account account = new Account(1, Account.AccountType.Current, 200D, null);
		
		String exceptionMessage = "Account Not Found!!";
		Mockito.when(accountRepository.findById(Mockito.anyInt())).thenThrow(new AccountNotFound(exceptionMessage));
	
		Throwable exception = assertThrows(AccountNotFound.class, () -> accountService.updateAccount(1, account));
		
		assertEquals(exceptionMessage, exception.getMessage());
		verify(accountRepository,atLeast(1)).findById(Mockito.anyInt());
		
	}
	
	@Test
	@Order(8)
	public void test_deleteAccount() {

		Optional<Account> mockAccount = Optional.of(new Account(1, Account.AccountType.Current, 200D, null));
		
		Mockito.when(accountRepository.findById(Mockito.anyInt())).thenReturn(mockAccount);
	
		Account accountDeleted = accountService.deleteAccount(1);
		
		System.out.println(accountDeleted);

		assertEquals(mockAccount.get().getAccountBalance(), accountDeleted.getAccountBalance());
		verify(accountRepository,atLeast(1)).findById(Mockito.anyInt());
		
	}
	
	@Test
	@Order(9)
	public void test_deleteAccount_negative() {
		
		String exceptionMessage = "Account Not Found!!";
		Mockito.when(accountRepository.findById(Mockito.anyInt())).thenThrow(new AccountNotFound(exceptionMessage));
	
		Throwable exception = assertThrows(AccountNotFound.class, () -> accountService.deleteAccount(1));
		
		assertEquals(exceptionMessage, exception.getMessage());
		verify(accountRepository,atLeast(1)).findById(Mockito.anyInt());
		
	}
	
}
