package com.batch12.rvirb.foundation.bank.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.batch12.rvirb.foundation.bank.entities.Account;
import com.batch12.rvirb.foundation.bank.entities.Customer;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class FoundationIntegrationTests {
	
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	@Order(1)
	void test_createCustomer() {
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1,Account.AccountType.Joint, 700D, null));
		
		Customer customer1 = new Customer(1, "Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", null);
		Customer customer2 = new Customer(2, "Aarohi", "Rakhonde", "abc.xyz@gmail.com", null);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");

		HttpEntity<Customer> request1 = new HttpEntity<>(customer1, headers);
		ResponseEntity<String> result1 = this.restTemplate.postForEntity("/customers", request1, String.class);
		assertEquals(HttpStatus.CREATED.value(),result1.getStatusCodeValue());

		HttpEntity<Customer> request2 = new HttpEntity<>(customer2, headers);
		ResponseEntity<String> result2 = this.restTemplate.postForEntity("/customers", request2, String.class);
		assertEquals(HttpStatus.CREATED.value(),result2.getStatusCodeValue());
	}

	@Test
	@Order(2)
	void test_getCustomer() {
				
		Customer customer = this.restTemplate.getForObject("/customers/1", Customer.class);	
		System.out.println(customer);
	}
	
	@Test
	@Order(3)
	void test_getCustomers() {
				
		Customer[] customers = this.restTemplate.getForObject("/customers", Customer[].class);
		
		for(int i=0; i<customers.length;i++) {
			System.out.println(customers[i]);
		}
	}		
	
	@Test
	@Order(4)
	void test_updateCustomer() {
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, Account.AccountType.Joint, 700D, null));
		
		Customer customer1 = new Customer(1, "Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", null);
		Customer customer2 = new Customer(2, "Aarohi", "Rakhonde", "abc.xyz@gmail.com", null);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");

		HttpEntity<Customer> request1 = new HttpEntity<>(customer1, headers);
		ResponseEntity<String> result1 = this.restTemplate.postForEntity("/customers", request1, String.class);
		assertEquals(HttpStatus.CREATED.value(),result1.getStatusCodeValue());

		HttpEntity<Customer> request2 = new HttpEntity<>(customer2, headers);
		ResponseEntity<String> result2 = this.restTemplate.postForEntity("/customers", request2, String.class);
		assertEquals(HttpStatus.CREATED.value(),result2.getStatusCodeValue());
	
	}
}
