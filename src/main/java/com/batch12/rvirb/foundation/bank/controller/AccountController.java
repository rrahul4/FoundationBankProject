package com.batch12.rvirb.foundation.bank.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.batch12.rvirb.foundation.bank.entities.Account;
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
	
	@PostMapping("/accounts")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		Account savedAccount = accountService.createAccount(account);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedAccount.getAccountId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/accounts/{id}")
	public void updateAccount(@PathVariable int id, @RequestBody Account account) {
		
		Account updatedAccount = accountService.updateAccount(id, account);
		
		if (updatedAccount.getAccountId()==null) {
			throw new AccountNotFound("Id --" +id);
		}
	}
	
	@DeleteMapping("/accounts/{id}")
	public void deleteCustomer(@PathVariable int id) {
		
		Account deletedAccount = accountService.deleteAccount(id);
		
		if (deletedAccount.getAccountId()==null) {
			throw new AccountNotFound("Id --" +id);
		}	
	}
}
