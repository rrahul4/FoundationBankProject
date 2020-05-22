package com.batch12.rvirb.foundation.bank.repositories;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.batch12.rvirb.foundation.bank.entities.Account;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class AccountRepositoryTest {

	@Autowired
	AccountRepository accountRepository;

	@Test
	@Order(1)
	public void createAccount() {
		Account account1 = new Account();
		account1.setAccountType(Account.AccountType.Current);
		account1.setAccountBalance(100L);

		accountRepository.save(account1);

		Account account2 = new Account();
		account2.setAccountType(Account.AccountType.Savings);
		account2.setAccountBalance(200L);

		accountRepository.save(account2);
		
		Account account3 = new Account();
		account3.setAccountType(Account.AccountType.Joint);
		account3.setAccountBalance(300L);

		accountRepository.save(account3);
	}

	@Test
	@Order(2)
	public void getAccounts() {
		Iterable<Account> accounts = accountRepository.findAll();

		for (Account account : accounts) {
			System.out.println(account);

		}
	}
	
	@Test
	@Order(3)
	public void getAccount() {
		Optional<Account> accountOpt = accountRepository.findById(1);
		Account account = accountOpt.get();
		System.out.println(account);
		
	}

}