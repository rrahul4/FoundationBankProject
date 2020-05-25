package com.batch12.rvirb.foundation.bank.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.batch12.rvirb.foundation.bank.entities.Customer;
import com.batch12.rvirb.foundation.bank.exceptions.CustomerNotFound;
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

		Optional<Customer> mockCustomer = Optional.of(new Customer(1,"Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", null));
		
		Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(mockCustomer);
	
		Customer customerFetched = customerService.getCustomer(1);		
		System.out.println(customerFetched);

		assertEquals(mockCustomer.get().getCustomerFirstName(), customerFetched.getCustomerFirstName());
		verify(customerRepository,atLeast(1)).findById(Mockito.anyInt());
		
	}
	
	@Test
	@Order(2)
	public void test_getCustomer_negative() {

		String exceptionMessage = "Customer Not Found!!";
		
		Mockito.when(customerRepository.findById(Mockito.anyInt())).thenThrow(new CustomerNotFound(exceptionMessage));
	
		Throwable exception = assertThrows(CustomerNotFound.class, () -> customerService.getCustomer(1));
		
		assertEquals(exceptionMessage, exception.getMessage());
		verify(customerRepository,atLeast(1)).findById(Mockito.anyInt());
		
	}

	@Test
	@Order(3)
	public void test_getCustomers() {

		List<Customer> customerList = new ArrayList<Customer>();
		
		customerList.add(new Customer(1,"Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", null));
		customerList.add(new Customer(2,"Aarohi", "Rakhonde", "abc.xyz@gmail.com", null));
		
		Mockito.when(customerRepository.findAll()).thenReturn(customerList);
	
		List<Customer> customerListFetched = customerService.getCustomers();
		Customer customerFetched = customerListFetched.get(1);
		Customer customerSaved = customerList.get(1);
		
		System.out.println(customerFetched);

		assertEquals(customerSaved.getCustomerFirstName(), customerFetched.getCustomerFirstName());
		verify(customerRepository,atLeast(1)).findAll();	
		
	}
	
	@Test
	@Order(4)
	public void test_getCustomers_negative() {

		List<Customer> customerList = new ArrayList<Customer>();
		
		Mockito.when(customerRepository.findAll()).thenReturn(customerList);
	
		List<Customer> customerListFetched = customerService.getCustomers();
		
		System.out.println(customerListFetched);

		assertEquals(customerList, customerListFetched);
		verify(customerRepository,atLeast(1)).findAll();	
		
	}
	
	@Test
	@Order(5)
	public void test_createCustomer() {
		
		Customer customer = new Customer(1,"Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", null);
		Customer mockCustomer = new Customer(1,"Aarohi", "Rakhonde", "abc.xyz@gmail.com", null);
		
		Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(mockCustomer);
	
		Customer customerCreated = customerService.createCustomer(customer);
		
		System.out.println(customerCreated);

		assertEquals(mockCustomer.getCustomerFirstName(), customerCreated.getCustomerFirstName());
		verify(customerRepository,atLeast(1)).save(Mockito.any(Customer.class));	
		
	}
	
	@Test
	@Order(6)
	public void test_updateCustomer() {

		Customer customer = new Customer(1,"Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", null);
		Optional<Customer> mockCustomer = Optional.of(new Customer(1,"Aarohi", "Rakhonde", "abc.xyz@gmail.com", null));
		
		Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(mockCustomer);
		Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(mockCustomer.get());
	
		Customer customerUpdated = customerService.updateCustomer(1, customer);
		
		System.out.println(customerUpdated);

		assertEquals(mockCustomer.get().getCustomerFirstName(), customerUpdated.getCustomerFirstName());
		verify(customerRepository,atLeast(1)).save(Mockito.any(Customer.class));
		
	}
	
	@Test
	@Order(7)
	public void test_updateCustomer_negative() {

		Customer customer = new Customer(1,"Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", null);
		
		String exceptionMessage = "Customer Not Found!!";
		Mockito.when(customerRepository.findById(Mockito.anyInt())).thenThrow(new CustomerNotFound(exceptionMessage));
	
		Throwable exception = assertThrows(CustomerNotFound.class, () -> customerService.updateCustomer(1, customer));
		
		assertEquals(exceptionMessage, exception.getMessage());
		verify(customerRepository,atLeast(1)).findById(Mockito.anyInt());
		
	}
	
	@Test
	@Order(8)
	public void test_deleteCustomer() {

		Optional<Customer> mockCustomer = Optional.of(new Customer(1,"Aarohi", "Rakhonde", "abc.xyz@gmail.com", null));
		
		Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(mockCustomer);
	
		Customer customerDeleted = customerService.deleteCustomer(1);
		
		System.out.println(customerDeleted);

		assertEquals(mockCustomer.get().getCustomerFirstName(), customerDeleted.getCustomerFirstName());
		verify(customerRepository,atLeast(1)).findById(Mockito.anyInt());
		
	}
	
	@Test
	@Order(9)
	public void test_deleteCustomer_negative() {
		
		String exceptionMessage = "Customer Not Found!!";
		Mockito.when(customerRepository.findById(Mockito.anyInt())).thenThrow(new CustomerNotFound(exceptionMessage));
	
		Throwable exception = assertThrows(CustomerNotFound.class, () -> customerService.deleteCustomer(1));
		
		assertEquals(exceptionMessage, exception.getMessage());
		verify(customerRepository,atLeast(1)).findById(Mockito.anyInt());
		
	}
	
}
