package com.batch12.rvirb.foundation.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch12.rvirb.foundation.bank.entities.Account;
import com.batch12.rvirb.foundation.bank.repositories.AccountRepository;

@Service
public class AccountServiceImpl2 implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	public Account getAccount(int id) {
		Optional<Account> accountOpt = accountRepository.findById(id);
		Account account = accountOpt.get();
		return account;
		
	}
	
	public List<Account> getAccounts() {
		List<Account> accounts = (List<Account>) accountRepository.findAll();
		return accounts;
	}
	
	public Account createAccount(Account account) {
		Account accountSaved = accountRepository.save(account);
		return accountSaved;
		
	}

	public Account updateAccount(int id, Account account) {
		Optional<Account> accountOpt = accountRepository.findById(id);
		Account accountUpdated = accountOpt.get();
		
		accountUpdated.setAccountBalance(account.getAccountBalance());
		accountUpdated.setAccountType(account.getAccountType());
		
		return accountUpdated;
				
	}

	public Account deleteAccount(int id) {
		Optional<Account> accountOpt = accountRepository.findById(id);
		Account accountDeleted = accountOpt.get();
		
		accountRepository.delete(accountDeleted);
		
		return accountDeleted;
		
	}
	
}