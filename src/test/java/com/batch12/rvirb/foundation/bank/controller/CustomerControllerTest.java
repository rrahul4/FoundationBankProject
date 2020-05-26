package com.batch12.rvirb.foundation.bank.controller;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.batch12.rvirb.foundation.bank.entities.Account;
import com.batch12.rvirb.foundation.bank.entities.Customer;
import com.batch12.rvirb.foundation.bank.exceptions.CustomerNotFound;
import com.batch12.rvirb.foundation.bank.service.CustomerService;
import com.google.gson.Gson;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
@TestMethodOrder(OrderAnnotation.class)
class CustomerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService customerService;
	
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
	public void test_getCustomers() throws Exception {
		
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, Account.AccountType.Current, 700D, null));
		
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(1, "Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", accountList));
		
		System.out.println(customerList);
		
		Mockito.when(customerService.getCustomers()).thenReturn(customerList);
		
		RequestBuilder request=MockMvcRequestBuilders
				.get("/customers")
				.accept(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String expectedResult = "[{\"customerId\":1,\"customerFirstName\":\"Rahulkumar\",\"customerLastName\":\"Rakhonde\",\"customerEmail\":\"abc.xyz@gmail.com\",\"accounts\":[{\"accountId\":1,\"accountType\":\"Current\",\"accountBalance\":700.0}]}]";
		
		assertEquals(expectedResult, response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}

	@Test
	@Order(2)
	public void test_getCustomer() throws Exception {
		
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, Account.AccountType.Current, 700D, null));
		
		Customer customer = new Customer(1, "Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", accountList);		
		
		System.out.println(customer);
				
		Mockito.when(customerService.getCustomer(Mockito.anyInt())).thenReturn(customer);
		
		RequestBuilder request=MockMvcRequestBuilders
				.get("/customers/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String expectedResult = "{\"customerId\":1,\"customerFirstName\":\"Rahulkumar\",\"customerLastName\":\"Rakhonde\",\"customerEmail\":\"abc.xyz@gmail.com\",\"accounts\":[{\"accountId\":1,\"accountType\":\"Current\",\"accountBalance\":700.0}]}";
		
		assertEquals(expectedResult, response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@Test
	@Order(3)
	public void test_getCustomer_negative() throws Exception {
		
		String exceptionMessage = "Customer Not Found!!";
		
		Mockito.when(customerService.getCustomer(Mockito.anyInt())).thenThrow(new CustomerNotFound(exceptionMessage));
		
		RequestBuilder request=MockMvcRequestBuilders
				.get("/customers/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		
	}
	
	@Test
	@Order(4)
	public void test_createCustomer() throws Exception {
				
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, Account.AccountType.Current, 700D, null));
		
		Customer customer = new Customer(1,"Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", accountList);
		System.out.println(customer);
		
		Gson gson = new Gson();		
		String customerJson = gson.toJson(customer);
		System.out.println(customerJson);
		
		Mockito.when(customerService.createCustomer(Mockito.any(Customer.class))).thenReturn(customer);
		
		RequestBuilder request=MockMvcRequestBuilders
				.post("/customers")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerJson);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());		
		
	}

	@Test
	@Order(5)
	public void test_updateCustomer() throws Exception {
				
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1,Account.AccountType.Current, 700D, null));
		
		Customer customer = new Customer(1, "Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", accountList);
		System.out.println(customer);
		
		Gson gson = new Gson();		
		String customerJson = gson.toJson(customer);
		System.out.println(customerJson);
		
		Mockito.when(customerService.updateCustomer(Mockito.anyInt(),Mockito.any(Customer.class))).thenReturn(customer);
		
		RequestBuilder request=MockMvcRequestBuilders
				.put("/customers/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerJson);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());		
	
	}
	
	@Test
	@Order(6)
	public void test_updateCustomer_negative() throws Exception {
		
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, Account.AccountType.Current, 700D, null));
		
		Customer customer = new Customer(1, "Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", accountList);
		System.out.println(customer);
		
		Gson gson = new Gson();		
		String customerJson = gson.toJson(customer);
		System.out.println(customerJson);
		
		String exceptionMessage = "Customer Not Found!!";
		
		Mockito.when(customerService.updateCustomer(Mockito.anyInt(),Mockito.any(Customer.class))).thenThrow(new CustomerNotFound(exceptionMessage));
		
		RequestBuilder request=MockMvcRequestBuilders
				.put("/customers/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerJson);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		
	}
	
	@Test
	@Order(7)
	public void test_deleteCustomer() throws Exception {
				
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1,Account.AccountType.Current, 700D, null));
		
		Customer customer = new Customer(1, "Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", accountList);
		System.out.println(customer);
		
		Mockito.when(customerService.deleteCustomer(Mockito.anyInt())).thenReturn(customer);
				
		RequestBuilder request=MockMvcRequestBuilders
				.delete("/customers/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());		
				
	}
	
	@Test
	@Order(8)
	public void test_deleteCustomer_negative() throws Exception {
		
		String exceptionMessage = "Customer Not Found!!";
		
		Mockito.when(customerService.deleteCustomer(Mockito.anyInt())).thenThrow(new CustomerNotFound(exceptionMessage));
		
		RequestBuilder request=MockMvcRequestBuilders
				.delete("/customers/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		
	}
	
	@Test
	@Order(9)
	public void test_createAccountUnderCustomer() throws Exception {
				
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, Account.AccountType.Current, 700D, null));
		
		Customer customer = new Customer(1, "Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", accountList);
		System.out.println(customer);
		
		Gson gson = new Gson();		
		String accountListJson = gson.toJson(accountList);
		System.out.println(accountListJson);
		
		Mockito.when(customerService.getCustomer(Mockito.anyInt())).thenReturn(customer);
		Mockito.when(customerService.updateCustomer(Mockito.anyInt(),Mockito.any(Customer.class))).thenReturn(customer);
		
		RequestBuilder request=MockMvcRequestBuilders
				.post("/customers/1/accounts")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(accountListJson);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());		
	
	}
	
	@Test
	@Order(10)
	public void test_createAccountUnderCustomer_negative() throws Exception {
				
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, Account.AccountType.Current, 700D, null));
		
		Customer customer = new Customer(1, "Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", accountList);
		System.out.println(customer);
		
		Gson gson = new Gson();		
		String accountListJson = gson.toJson(accountList);
		System.out.println(accountListJson);
		
		String exceptionMessage = "Customer Not Found!!";
		Mockito.when(customerService.getCustomer(Mockito.anyInt())).thenThrow(new CustomerNotFound(exceptionMessage));
		Mockito.when(customerService.updateCustomer(Mockito.anyInt(),Mockito.any(Customer.class))).thenThrow(new CustomerNotFound(exceptionMessage));
		
		RequestBuilder request=MockMvcRequestBuilders
				.post("/customers/1/accounts")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(accountListJson);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());		
	
	}
	
	@Test
	@Order(11)
	public void test_getCustomerAccount() throws Exception {
		
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(new Account(1, Account.AccountType.Current, 700D, null));
		
		Customer customer = new Customer(1, "Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", accountList);		
		
		System.out.println(customer);
				
		Mockito.when(customerService.getCustomer(Mockito.anyInt())).thenReturn(customer);
		
		RequestBuilder request=MockMvcRequestBuilders
				.get("/customers/1/accounts")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String expectedResult = "[{\"accountId\":1,\"accountType\":\"Current\",\"accountBalance\":700.0}]";
		
		assertEquals(expectedResult, response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	
	@Test
	@Order(12)
	public void test_getCustomerAccount_negative() throws Exception {
		
		String exceptionMessage = "Customer Not Found!!";
		Mockito.when(customerService.getCustomer(Mockito.anyInt())).thenThrow(new CustomerNotFound(exceptionMessage));
		
		RequestBuilder request=MockMvcRequestBuilders
				.get("/customers/1/accounts")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	}
	
}
