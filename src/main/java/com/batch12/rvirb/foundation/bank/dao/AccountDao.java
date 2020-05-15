package com.batch12.rvirb.foundation.bank.dao;

import java.util.List;

import com.batch12.rvirb.foundation.bank.entities.Account;

public interface AccountDao {

	public Account getAccount(int id);
	public List<Account> getAccounts();
	public Account createAccount(Account account);
	public Account updateAccount(int id, Account account);
	public Account deleteAccount(int id);
	
}
