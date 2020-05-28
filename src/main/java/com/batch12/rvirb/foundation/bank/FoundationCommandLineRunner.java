package com.batch12.rvirb.foundation.bank;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.batch12.rvirb.foundation.bank.entities.Account;
import com.batch12.rvirb.foundation.bank.entities.Customer;
import com.batch12.rvirb.foundation.bank.service.CustomerService;

@Component
public class FoundationCommandLineRunner implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(" -- Begin - Command Line Runner -- ");
		
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(2, Account.AccountType.Current, 200D, null));
		Customer customer = (new Customer(2, "Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", accountList));
		
		customerService.createCustomer(customer);
		
		System.out.println(" -- End - Command Line Runner -- ");
		
	}

}
