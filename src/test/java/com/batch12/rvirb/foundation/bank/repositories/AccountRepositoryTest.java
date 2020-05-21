package com.batch12.rvirb.foundation.bank.repositories;

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
		account1.setAccountId(1001);
		account1.setAccountType(Account.AccountType.Current);
		account1.setAccountBalance(100L);

		accountRepository.save(account1);

		Account account2 = new Account();
		account2.setAccountId(1002);
		account2.setAccountType(Account.AccountType.Savings);
		account2.setAccountBalance(100L);

		accountRepository.save(account2);

	}

	@Test
	@Order(2)
	public void getAccounts() {
		Iterable<Account> accounts = accountRepository.findAll();

		for (Account account : accounts) {
			System.out.println(
					account.getAccountId() + "\t" + account.getAccountType() + "\t" + account.getAccountBalance());

		}
	}

}