package com.batch12.rvirb.foundation.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.batch12.rvirb.foundation.bank.entities.Customer;
import com.batch12.rvirb.foundation.bank.exceptions.CustomerNotFound;
import com.batch12.rvirb.foundation.bank.repositories.CustomerRepository;

//This Implementation will use CRUD Repository Layer, Actual Database

@Service
@Primary
public class CustomerServiceImpl2 implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	public Customer getCustomer(int id) {
		Optional<Customer> customerOpt = customerRepository.findById(id);
		if (!customerOpt.isPresent()) {
			throw new CustomerNotFound("Id --" +id);
		}
		
		Customer customer = customerOpt.get();
		return customer;
	}
	
	public List<Customer> getCustomers() {
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		return customers;
	}
	
	public Customer createCustomer(Customer customer) {
		Customer customerSaved = customerRepository.save(customer);
		return customerSaved;
		
	}

	public Customer updateCustomer(int id, Customer customer) {
		Optional<Customer> customerOpt = customerRepository.findById(id);
		if (!customerOpt.isPresent()) {
			throw new CustomerNotFound("Id --" +id);
		}
	
		Customer customerUpdated = customerOpt.get();
		
		customerUpdated.setCustomerFirstName(customer.getCustomerFirstName());
		customerUpdated.setCustomerLastName(customer.getCustomerLastName());
		customerUpdated.setCustomerEmail(customer.getCustomerEmail());
		if (customer.getAccounts() !=null) {
			customerUpdated.setAccounts(customer.getAccounts());	
		}
		
		customerRepository.save(customerUpdated);
		
		return customerUpdated;	
	}

	public Customer deleteCustomer(int id) {
		Optional<Customer> customerOpt = customerRepository.findById(id);
		if (!customerOpt.isPresent()) {
			throw new CustomerNotFound("Id --" +id);
		}
		
		Customer customerDeleted = customerOpt.get();
		
		customerRepository.delete(customerDeleted);
		
		return customerDeleted;
		
	}
	
}
