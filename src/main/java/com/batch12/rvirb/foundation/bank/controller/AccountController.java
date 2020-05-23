package com.batch12.rvirb.foundation.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.batch12.rvirb.foundation.bank.entities.Account;
import com.batch12.rvirb.foundation.bank.entities.TransferFund;
import com.batch12.rvirb.foundation.bank.exceptions.AccountNotFound;
import com.batch12.rvirb.foundation.bank.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	AccountService accountService;

	@GetMapping("/accounts")
	public List<Account> getAccounts() {
		return accountService.getAccounts();
		
	}

	@GetMapping("/accounts/{id}")
	public Account getAccount(@PathVariable int id) {
		Account account = accountService.getAccount(id);
		
		if (account.getAccountId()==null) {
			throw new AccountNotFound("Id --" +id);
		}
		
		return account;	
	}
		
	@PutMapping("/accounts/transferFunds")
	public String transferFunds(@RequestBody TransferFund transferFund) {
		
		Double amount = transferFund.getAmount();
		Account fromAccount = accountService.getAccount(transferFund.getFromAccount());
		Account toAccount = accountService.getAccount(transferFund.getToAccount());
		
		if ((fromAccount.getAccountBalance() - amount) < 0) 
			return "Transfer Unsuccessful! From Account is not having enough Balance: " + fromAccount.getAccountBalance();
		
		fromAccount.setAccountBalance(fromAccount.getAccountBalance() - amount);
		toAccount.setAccountBalance(toAccount.getAccountBalance() + amount);

		accountService.updateAccount(fromAccount.getAccountId(), fromAccount);
		accountService.updateAccount(toAccount.getAccountId(), toAccount);
		
		return "Transfer Successful !!!";
	}
}
