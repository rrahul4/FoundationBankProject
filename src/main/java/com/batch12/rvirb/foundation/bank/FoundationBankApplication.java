package com.batch12.rvirb.foundation.bank;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.batch12.rvirb.foundation.bank.entities.Account;
import com.batch12.rvirb.foundation.bank.entities.Customer;
import com.batch12.rvirb.foundation.bank.service.CustomerService;

@SpringBootApplication
public class FoundationBankApplication  implements CommandLineRunner {
	
	@Autowired
	private CustomerService customerService;
	
	public static void main(String[] args) {
		SpringApplication.run(FoundationBankApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(" -- 1. Begin - Command Line Runner -- ");
		
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, Account.AccountType.Current, 100D, null));
		Customer customer = (new Customer(1, "Rupeshkumar", "Rakhonde", "abc.xyz@gmail.com", accountList));
		
		customerService.createCustomer(customer);
		
		System.out.println(" -- 1. End - Command Line Runner -- ");
	}


}
