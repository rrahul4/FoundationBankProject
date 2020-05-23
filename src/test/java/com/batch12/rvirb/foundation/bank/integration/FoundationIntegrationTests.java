package com.batch12.rvirb.foundation.bank.integration;

import static org.junit.jupiter.api.Assertions.*;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.batch12.rvirb.foundation.bank.entities.Customer;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class FoundationIntegrationTests {
	
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	@Order(1)
	void test_create_customer() {
		
		Customer customer1 = new Customer(1,"Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", null);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		
		HttpEntity<Customer> request = new HttpEntity<>(customer1, headers);
		
		ResponseEntity<String> result = this.restTemplate.postForEntity("/customers", request, String.class);
		
		assertEquals(201,result.getStatusCodeValue());
	}

	@Test
	@Order(2)
	void test_create_customer2() {
		
		Customer customer2 = new Customer(2,"Aarohi", "Rakhonde", "abc.xyz@gmail.com", null);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		
		HttpEntity<Customer> request = new HttpEntity<>(customer2, headers);
		
		ResponseEntity<String> result = this.restTemplate.postForEntity("/customers", request, String.class);
		
		assertEquals(201,result.getStatusCodeValue());
	}

	@Test
	@Order(3)
	void test_get_customer() {
				
		Customer[] customers = this.restTemplate.getForObject("/customers", Customer[].class);
		
		for(int i=0; i<customers.length;i++) {
			System.out.println(customers[i]);
		}
	
	}
}
