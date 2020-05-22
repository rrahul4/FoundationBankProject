package com.batch12.rvirb.foundation.bank.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.batch12.rvirb.foundation.bank.entities.Customer;
import com.batch12.rvirb.foundation.bank.repositories.CustomerRepository;


@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class CustomerServiceTest {
	
	@InjectMocks 
	CustomerServiceImpl2 customerService;
	
	@Mock
	private CustomerRepository customerRepository;

	@Test
	@Order(1)
	public void test_getCustomer() {

		Optional<Customer> customer = Optional.of(new Customer(1,"Rahulkumar", "Rakhonde", "abc.xyz@gmail.com"));
		
		when(customerRepository.findById(1)).thenReturn(customer);
	
		Customer customerFetched = customerService.getCustomer(1);
		Customer customerSaved = customer.get();
		
		System.out.println(customerFetched);

		assertThat(customerSaved.getCustomerEmail() == customerFetched.getCustomerEmail()).isTrue();
		verify(customerRepository,atLeast(1)).findById(1);
	}

	@Test
	@Order(2)
	public void test_getCustomers() {

		List<Customer> customerList = new ArrayList<Customer>();
		
		customerList.add(new Customer(1,"Rahulkumar", "Rakhonde", "abc.xyz@gmail.com"));
		customerList.add(new Customer(2,"Aarohi", "Rakhonde", "abc.xyz@gmail.com"));
		
		when(customerRepository.findAll()).thenReturn(customerList);
	
		List<Customer> customerListFetched = customerService.getCustomers();
		Customer customerFetched = customerListFetched.get(1);
		Customer customerSaved = customerList.get(1);
		
		System.out.println(customerFetched);

		assertThat(customerSaved.getCustomerFirstName() == customerFetched.getCustomerFirstName()).isTrue();
		verify(customerRepository,atLeast(1)).findAll();	
	}
	
	
	@Test
	@Order(3)
	public void test_createCustomer() {

		Customer customer = new Customer(1,"Rahulkumar", "Rakhonde", "abc.xyz@gmail.com");
		Customer mockCustomer = new Customer(1,"Aarohi", "Rakhonde", "abc.xyz@gmail.com");
		
		when(customerRepository.save(customer)).thenReturn(mockCustomer);
	
		Customer customerCreated = customerService.createCustomer(customer);
		
		System.out.println(customerCreated);

		assertThat(mockCustomer.getCustomerFirstName() == customerCreated.getCustomerFirstName()).isTrue();
		verify(customerRepository,atLeast(1)).save(customer);	
	}
}
