package com.batch12.rvirb.foundation.bank.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.batch12.rvirb.foundation.bank.entities.Customer;
import com.batch12.rvirb.foundation.bank.service.CustomerService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
@TestMethodOrder(OrderAnnotation.class)
class CustomerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService customerService;

	@Test
	@Order(1)
	public void test_getCustomers() throws Exception {
		
		RequestBuilder request;
		
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(1,"Rahulkumar", "Rakhonde", "abc.xyz@gmail.com", null));
		customerList.add(new Customer(2,"Aarohi", "Rakhonde", "abc.xyz@gmail.com", null));
		
		System.out.println("customerList: " + customerList.toString());
		
		when(customerService.getCustomers()).thenReturn(customerList);
		
		request=MockMvcRequestBuilders
				.get("/customers")
				.accept(MediaType.APPLICATION_JSON);
		String expectedResults="[{customerId=1, customerFirstName=Rahulkumar, customerLastName=Rakhonde, customerEmail=abc.xyz@gmail.com}, {customerId=2, customerFirstName=Aarohi, customerLastName=Rakhonde, customerEmail=abc.xyz@gmail.com}]";
	
		mockMvc.perform(request)
				.andExpect(status()
				.isOk())
				.andExpect(content().json(expectedResults))
				.andReturn();
		
	}

}
