package com.batch12.rvirb.foundation.bank.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.batch12.rvirb.foundation.bank.service.CustomerServiceImpl2;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
@TestMethodOrder(OrderAnnotation.class)
class CustomerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerServiceImpl2 customerService;

	@Test
	void test() {
		System.out.println("Not yet implemented");
	}

}
