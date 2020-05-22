package com.batch12.rvirb.foundation.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch12.rvirb.foundation.bank.dao.CustomerDao;
import com.batch12.rvirb.foundation.bank.entities.Customer;

//This Implementation will use DAO Layer, Static Array List and No Database

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDao customerDao;
	
	public Customer getCustomer(int id) {
		return customerDao.getCustomer(id);
		
	}
	
	public List<Customer> getCustomers() {
		return customerDao.getCustomers();
		
	}
	
	public Customer createCustomer(Customer customer) {
		return customerDao.createCustomer(customer);
		
	}

	public Customer updateCustomer(int id, Customer customer) {
		return customerDao.updateCustomer(id, customer);
		
	}

	public Customer deleteCustomer(int id) {
		return customerDao.deleteCustomer(id);
		
	}
	
}
