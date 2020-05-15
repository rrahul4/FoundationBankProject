package com.batch12.rvirb.foundation.bank.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.batch12.rvirb.foundation.bank.entities.Customer;


@Component
public class CustomerDaoImpl implements CustomerDao {
	
	public static List<Customer> customers = new ArrayList<Customer>();
	static int counter=0;
	
	static {
		customers.add(new Customer(++counter, "Rahulkumar", "Rakhonde", "abc.xyz@gmail.com"));
		customers.add(new Customer(++counter, "Rupeshkumar", "Rakhonde", "abc.xyz@gmail.com"));
		customers.add(new Customer(++counter, "Aarohi", "Rakhonde", "abc.xyz@gmail.com"));
	}

	@Override
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub

		Customer tempCustomer = new Customer();
		
		for (Customer customer:customers) {
			if (customer.getCustomerId() == id) {
				tempCustomer = customer;
				break;
			}
		}
		return tempCustomer;
	}

	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return customers;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		Customer customerSave = customer;
		customerSave.setCustomerId(++counter);
		
		customers.add(customerSave);
		
		return customerSave;
	}

	@Override
	public Customer updateCustomer(int id, Customer customerUpdate) {
		// TODO Auto-generated method stub
		
		Customer customerUpdated = new Customer();
		
		for (Customer customer:customers) {
			if (customer.getCustomerId() == id) {
				customer.setCustomerFirstName(customerUpdate.getCustomerFirstName());
				customer.setCustomerLastName(customerUpdate.getCustomerLastName());
				customer.setCustomerEmail(customerUpdate.getCustomerEmail());
				customerUpdated = customer;
				break;
			}
		}
		return customerUpdated;
	}

	@Override
	public Customer deleteCustomer(int id) {
		// TODO Auto-generated method stub
		Customer tempCustomer = new Customer();
		
		Iterator<Customer> itr = customers.iterator();
		
		while(itr.hasNext()) {
			Customer customer = itr.next();
			if (customer.getCustomerId() == id) {
				tempCustomer = customer;
				itr.remove();
				break;
			}
		}
		return tempCustomer;
	}

}
