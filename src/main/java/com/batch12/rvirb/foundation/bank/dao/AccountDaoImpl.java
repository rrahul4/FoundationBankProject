package com.batch12.rvirb.foundation.bank.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.batch12.rvirb.foundation.bank.entities.Account;


@Component
public class AccountDaoImpl implements AccountDao {
	
	public static List<Account> accounts = new ArrayList<Account>();
	static int counter=0;
		
	static {
		accounts.add(new Account(++counter, Account.AccountType.Savings, 10D, null));
		accounts.add(new Account(++counter, Account.AccountType.Savings, 20D, null));
		accounts.add(new Account(++counter, Account.AccountType.Current, 10D, null));

	}

	@Override
	public Account getAccount(int id) {
		// TODO Auto-generated method stub

		Account tempAccount = new Account();
		
		for (Account account:accounts) {
			if (account.getAccountId() == id) {
				tempAccount = account;
				break;
			}
		}
		return tempAccount;
	}

	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return accounts;
	}

	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		
		Account accountSave = account;
		accountSave.setAccountId(++counter);
		
		accounts.add(accountSave);
		
		return accountSave;
	}

	@Override
	public Account updateAccount(int id, Account accountUpdate) {
		// TODO Auto-generated method stub
		
		Account accountUpdated = new Account();
		
		for (Account account:accounts) {
			if (account.getAccountId() == id) {
				account.setAccountType(accountUpdate.getAccountType());
				account.setAccountBalance(accountUpdate.getAccountBalance());
				accountUpdated = account;
				break;
			}
		}
		return accountUpdated;
	}

	@Override
	public Account deleteAccount(int id) {
		// TODO Auto-generated method stub
		Account tempAccount = new Account();
		
		Iterator<Account> itr = accounts.iterator();
		
		while(itr.hasNext()) {
			Account account = itr.next();
			if (account.getAccountId() == id) {
				tempAccount = account;
				itr.remove();
				break;
			}
		}
		return tempAccount;
	}

}
