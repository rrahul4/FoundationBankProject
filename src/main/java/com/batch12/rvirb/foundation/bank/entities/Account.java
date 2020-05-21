package com.batch12.rvirb.foundation.bank.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
	
	public static enum AccountType {
		Savings, Current
	}
	
	@Id
	@GeneratedValue
	private Integer accountId;
	private AccountType accountType;
	private Long accountBalance;
		
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(Integer accountId, AccountType accountType, Long accountBalance) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Long accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountType=" + accountType + ", accountBalance=" + accountBalance
				+ "]";
	}

}
