package com.batch12.rvirb.foundation.bank.service;

import java.util.List;

import com.batch12.rvirb.foundation.bank.entities.Account;

public interface AccountService {
	
	public Account getAccount(int id);
	public List<Account> getAccounts();
	public Account createAccount(Account account);
	public Account updateAccount(int id, Account account);
	public Account deleteAccount(int id);

}
