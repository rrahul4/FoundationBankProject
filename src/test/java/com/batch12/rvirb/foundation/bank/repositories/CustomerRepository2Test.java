package com.batch12.rvirb.foundation.bank.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.batch12.rvirb.foundation.bank.entities.Account;
import com.batch12.rvirb.foundation.bank.entities.Customer;
import com.batch12.rvirb.foundation.bank.service.CustomerService;

//This Test will use Embedded DataBase with @DataJpaTest

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
class CustomerRepository2Test {

	@Autowired
	CustomerRepository customerRepository;
	
// This Bean is created to support CommandLineRunner from Main Spring Boot Application by Autowiring CustomerService.
// @WebMvcTest will not create all Beans from application unlike @SpringBootTest does it.
	@MockBean
	private CustomerService customerService;

	@Test
	@Order(1)
	public void createCustomer() {
		
		Customer customer1 = new Customer();
		customer1.setCustomerFirstName("Rahulkumar");
		customer1.setCustomerLastName("Rakhonde");
		customer1.setCustomerEmail("abc.xyz@gmail.com");

		Customer customer2 = new Customer();
		customer2.setCustomerFirstName("Rupeshkumar");
		customer2.setCustomerLastName("Rakhonde");
		customer2.setCustomerEmail("abc.xyz@gmail.com");		
		
		Customer customer3 = new Customer();
		customer3.setCustomerFirstName("Aarohi");
		customer3.setCustomerLastName("Rakhonde");
		customer3.setCustomerEmail("abc.xyz@gmail.com");
		
		Customer customer4 = new Customer();
		customer4.setCustomerFirstName("Anupama");
		customer4.setCustomerLastName("Rakhonde");
		customer4.setCustomerEmail("abc.xyz@gmail.com");

		Account account1 = new Account();
		account1.setAccountType(Account.AccountType.Current);
		account1.setAccountBalance(100D);

		Account account2 = new Account();
		account2.setAccountType(Account.AccountType.Savings);
		account2.setAccountBalance(200D);

		Account account3 = new Account();
		account3.setAccountType(Account.AccountType.Joint);
		account3.setAccountBalance(400D);

		List<Customer> customerList = new ArrayList<Customer>();		
		List<Account> accountList = new ArrayList<Account>();
		
		customerList.add(customer1);
		customerList.add(customer2);
		customerList.add(customer3);
		
		accountList.add(account1);
		accountList.add(account2);
		accountList.add(account3);
				
		customer1.setAccounts(accountList);
		customer2.setAccounts(accountList);
		customer3.setAccounts(accountList);
		customer4.setAccounts(null);
		
/*		account1.setCustomers(customerList);
		account2.setCustomers(customerList);
		account3.setCustomers(customerList);*/

		customerRepository.save(customer1);
		customerRepository.save(customer2);
		customerRepository.save(customer3);
		customerRepository.save(customer4);
		
	}

	@Test
	@Order(2)
	@Transactional
	public void getCustomers() {
		createCustomer();
		
		Iterable<Customer> customers = customerRepository.findAll();

		for (Customer customer : customers) {
			System.out.println(customer);
			
		}
	}
	
	@Test
	@Order(3)
	@Transactional
	public void getCustomer() {
		createCustomer();
		
		Optional<Customer> customerOpt = customerRepository.findById(9);
		Customer customer = customerOpt.get();
		System.out.println(customer);
		
	}

	@Test
	@Order(4)
	@Transactional
	public void getCustomerByFirstNameAndLastName() {
		createCustomer();
		
		Iterable<Customer> customers = customerRepository.findByCustomerFirstNameAndCustomerLastName("Rahulkumar", "Rakhonde");
		
		for (Customer customer : customers) {
			System.out.println(customer);		

		}		
	}
	
	@Test
	@Order(5)
	@Transactional
	public void getCustomerByFirstNameOrLastName() {		
		createCustomer();
		
		Iterable<Customer> customers = customerRepository.findByCustomerFirstNameOrCustomerLastName("Rahulkumar", "Rakhonde");
		
		for (Customer customer : customers) {
			System.out.println(customer);

		}
	}
	
	@Test
	@Order(6)
	@Transactional
	public void getCustomerByFirstNameLastName() {
		createCustomer();
		
		Iterable<Customer> customers = customerRepository.getCustomerByFirstNameAndLastName("Rahulkumar", "Rakhonde");
		
		for (Customer customer : customers) {
			System.out.println(customer);
		
		}
	}
	
	@Test
	@Order(7)
	@Transactional
	public void getAllCustomers() {
		createCustomer();
		
		Iterable<Customer> customers = customerRepository.findAllCustomers();
		
		for (Customer customer : customers) {
			System.out.println(customer);	

		}
	}
	
}