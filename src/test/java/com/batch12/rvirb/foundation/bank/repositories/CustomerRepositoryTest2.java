package com.batch12.rvirb.foundation.bank.repositories;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.batch12.rvirb.foundation.bank.entities.Customer;

// This Test will use actual DataBase with @SpringBootTest

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class CustomerRepositoryTest2 {

	@Autowired
	CustomerRepository customerRepository;

	@Test
	@Order(1)
	public void createCustomer() {
		
		Customer customer1 = new Customer();
		customer1.setCustomerFirstName("Rahulkumar");
		customer1.setCustomerLastName("Rakhonde");
		customer1.setCustomerEmail("abc.xyz@gmail.com");
		customerRepository.save(customer1);

		Customer customer2 = new Customer();
		customer2.setCustomerFirstName("Rupeshkumar");
		customer2.setCustomerLastName("Rakhonde");
		customer2.setCustomerEmail("abc.xyz@gmail.com");		
		customerRepository.save(customer2);
		
		Customer customer3 = new Customer();
		customer3.setCustomerFirstName("Aarohi");
		customer3.setCustomerLastName("Rakhonde");
		customer3.setCustomerEmail("abc.xyz@gmail.com");
		customerRepository.save(customer3);
		
		Customer customer4 = new Customer();
		customer4.setCustomerFirstName("Anupama");
		customer4.setCustomerLastName("Rakhonde");
		customer4.setCustomerEmail("abc.xyz@gmail.com");
		customerRepository.save(customer4);
	}

	@Test
	@Order(2)
	public void getCustomers() {
		
		Iterable<Customer> customers = customerRepository.findAll();

		for (Customer customer : customers) {
			System.out.println(customer);

		}
	}
	
	@Test
	@Order(3)
	public void getCustomer() {
		
		Optional<Customer> customerOpt = customerRepository.findById(1);
		Customer customer = customerOpt.get();
		System.out.println(customer);
	
	}

	
	@Test
	@Order(4)
	public void getCustomerByFirstNameAndLastName() {
		
		Iterable<Customer> customers = customerRepository.findByCustomerFirstNameAndCustomerLastName("Rahulkumar", "Rakhonde");
		
		for (Customer customer : customers) {
			System.out.println(customer);		
		}		
	}
	
	@Test
	@Order(5)
	public void getCustomerByFirstNameOrLastName() {
		
		Iterable<Customer> customers = customerRepository.findByCustomerFirstNameOrCustomerLastName("Rahulkumar", "Rakhonde");
		
		for (Customer customer : customers) {
			System.out.println(customer);
		}
	}
	
	@Test
	@Order(6)
	public void getCustomerByFirstNameLastName() {
		
		Iterable<Customer> customers = customerRepository.getCustomerByFirstNameAndLastName("Rahulkumar", "Rakhonde");
		
		for (Customer customer : customers) {
			System.out.println(customer);
		}
	}
	
	@Test
	@Order(7)
	public void getAllCustomers() {
		
		Iterable<Customer> customers = customerRepository.findAllCustomers();
		
		for (Customer customer : customers) {
			System.out.println(customer);	
		}
	}
	
}