package com.batch12.rvirb.foundation.bank.dao;

import java.util.List;

import com.batch12.rvirb.foundation.bank.entities.Customer;

public interface CustomerDao {

	public Customer getCustomer(int id);
	public List<Customer> getCustomers();
	public Customer createCustomer(Customer customer);
	public Customer updateCustomer(int id, Customer customer);
	public Customer deleteCustomer(int id);
	
}
