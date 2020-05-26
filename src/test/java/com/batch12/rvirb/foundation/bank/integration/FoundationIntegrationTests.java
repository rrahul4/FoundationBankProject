package com.batch12.rvirb.foundation.bank.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.batch12.rvirb.foundation.bank.FoundationBankApplication;
import com.batch12.rvirb.foundation.bank.entities.Account;
import com.batch12.rvirb.foundation.bank.entities.Customer;
import com.batch12.rvirb.foundation.bank.entities.TransferFund;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = FoundationBankApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT)
class FoundationIntegrationTests {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	TestRestTemplate restTemplate;

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
    @BeforeEach
    public void init(TestInfo testInfo) {
        System.out.println(" -- BEGIN " + testInfo.getDisplayName() + " -- ");
    
    }
    
    @AfterEach
    public void end() {
        System.out.println(" -- END -- ");
    
    }
        
	@Test
	@Order(1)
	public void test_createCustomer() {

		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, Account.AccountType.Savings, 700D, null));
		accountList.add(new Account(2, Account.AccountType.Savings, 500D, null));
		
		Customer customer1 = new Customer(1, "Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", accountList);
		Customer customer2 = new Customer(2, "Aarohi", "Rakhonde", "abc.xyz@gmail.com", null);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		headers.set("Content-Type", "application/json");

		HttpEntity<Customer> request1 = new HttpEntity<>(customer1, headers);
		ResponseEntity<String> response1 = this.restTemplate.exchange(
									createURLWithPort("/customers"),
									HttpMethod.POST, request1, String.class);
		
		HttpEntity<Customer> request2 = new HttpEntity<>(customer2, headers);
		ResponseEntity<String> response2 = this.restTemplate.exchange(
									createURLWithPort("/customers"),
									HttpMethod.POST, request2, String.class);
		
		assertEquals(HttpStatus.CREATED.value(), response1.getStatusCodeValue());
		assertEquals(HttpStatus.CREATED.value(), response2.getStatusCodeValue());

	}
	
	@Test
	@Order(2)
	public void test_updateCustomer() {
		
		Customer customer = new Customer(1, "Rupeshkumar", "Rakhonde", "abc.xyz@gmail.com", null);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		headers.set("Content-Type", "application/json");

		HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
		ResponseEntity<String> response = this.restTemplate.exchange(
									createURLWithPort("/customers/1"),
									HttpMethod.PUT, request, String.class);
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
	}

	@Test
	@Order(3)
	public void test_getCustomer() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
				
		HttpEntity<Customer> request = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = this.restTemplate.exchange(
									createURLWithPort("/customers/1"),
									HttpMethod.GET, request, String.class);
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}
	
	@Test
	@Order(4)
	public void test_getCustomers() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
				
		HttpEntity<Customer> request = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = this.restTemplate.exchange(
									createURLWithPort("/customers"),
									HttpMethod.GET, request, String.class);
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
	}
	
	@Test
	@Order(5)
	public void test_createAccountUnderCustomer() {

		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(3, Account.AccountType.Savings, 100D, null));
		accountList.add(new Account(4, Account.AccountType.Current, 200D, null));
				
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		headers.set("Content-Type", "application/json");

		HttpEntity<List<Account>> request = new HttpEntity<>(accountList, headers);
		ResponseEntity<String> response = this.restTemplate.exchange(
									createURLWithPort("/customers/1/accounts"),
									HttpMethod.POST, request, String.class);
			
		assertEquals(HttpStatus.CREATED.value(),response.getStatusCodeValue());
		
	}
	
	@Test
	@Order(6)
	public void test_getCustomerAccount() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
				
		HttpEntity<Customer> request = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = this.restTemplate.exchange(
									createURLWithPort("/customers/1/accounts"),
									HttpMethod.GET, request, String.class);
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}
	
	@Test
	@Order(7)
	public void test_getAccount() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
				
		HttpEntity<Customer> request = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = this.restTemplate.exchange(
									createURLWithPort("/accounts/1"),
									HttpMethod.GET, request, String.class);
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}
	
	@Test
	@Order(8)
	public void test_getAccounts() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
				
		HttpEntity<Customer> request = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = this.restTemplate.exchange(
									createURLWithPort("/accounts"),
									HttpMethod.GET, request, String.class);
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}

	
	@Test
	@Order(9)
	public void test_transferFunds() {

		TransferFund transferFund = new TransferFund(1, 2, 200D);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		headers.set("Content-Type", "application/json");

		HttpEntity<TransferFund> request = new HttpEntity<>(transferFund, headers);
		ResponseEntity<String> response = this.restTemplate.exchange(
									createURLWithPort("/accounts/transferFunds"),
									HttpMethod.PUT, request, String.class);
		
		String expectedResult = "Transfer Successful !!!";
		
		assertEquals(expectedResult, response.getBody());
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
	}
		
	@Test
	@Order(10)
	public void test_deleteCustomer() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
				
		HttpEntity<Customer> request = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = this.restTemplate.exchange(
									createURLWithPort("/customers/2"),
									HttpMethod.DELETE, request, String.class);
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
	}
	
}
