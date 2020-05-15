package com.batch12.rvirb.foundation.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch12.rvirb.foundation.bank.dao.AccountDao;
import com.batch12.rvirb.foundation.bank.entities.Account;

@Service
public class AccountService {
	
	@Autowired
	AccountDao accountDao;
	
	public Account getAccount(int id) {
		return accountDao.getAccount(id);
		
	}
	
	public List<Account> getAccounts() {
		return accountDao.getAccounts();
		
	}
	
	public Account createAccount(Account account) {
		return accountDao.createAccount(account);
		
	}

	public Account updateAccount(int id, Account customer) {
		return accountDao.updateAccount(id, customer);
		
	}

	public Account deleteAccount(int id) {
		return accountDao.deleteAccount(id);
		
	}
	
}
