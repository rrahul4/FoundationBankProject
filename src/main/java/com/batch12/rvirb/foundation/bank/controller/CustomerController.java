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
import com.batch12.rvirb.foundation.bank.entities.Customer;
import com.batch12.rvirb.foundation.bank.exceptions.CustomerNotFound;
import com.batch12.rvirb.foundation.bank.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
		
	}

	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable int id) {
		Customer customer = customerService.getCustomer(id);
		if (customer.getCustomerId()==null) {
			throw new CustomerNotFound("Id --" +id);
		}
		
		return customer;	
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer savedCustomer = customerService.createCustomer(customer);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedCustomer.getCustomerId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/customers/{id}")
	public void updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
		
		Customer updatedCustomer = customerService.updateCustomer(id, customer);
		
		if (updatedCustomer.getCustomerId()==null) {
			throw new CustomerNotFound("Id --" +id);
		}
	}
	
	@DeleteMapping("/customers/{id}")
	public void deleteCustomer(@PathVariable int id) {
		
		Customer deletedCustomer = customerService.deleteCustomer(id);
		if (deletedCustomer.getCustomerId()==null) {
			throw new CustomerNotFound("Id --" +id);
		}	
	}
	
	
	@PostMapping("/customers/{id}/accounts")
	public ResponseEntity<Account> createAccountUnderCustomer(@PathVariable int id, @RequestBody List<Account> accounts) {
		
		Customer customer = customerService.getCustomer(id);		
		if (customer.getCustomerId()==null) {
			throw new CustomerNotFound("Id --" +id);
		}		
		
		customer.setAccounts(accounts);
		Customer updatedCustomer = customerService.updateCustomer(id, customer);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(updatedCustomer.getCustomerId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/customers/{id}/accounts")
	public List<Account> getCustomerAccount(@PathVariable int id) {
		Customer customer = customerService.getCustomer(id);
		if (customer.getCustomerId()==null) {
			throw new CustomerNotFound("Id --" +id);
		}
		
		return customer.getAccounts();	
	}
	
	@GetMapping("/customers/accounts")
	public List<Customer> getCustomerAccount() {
		
		return customerService.getCustomers();
		
	}
}
