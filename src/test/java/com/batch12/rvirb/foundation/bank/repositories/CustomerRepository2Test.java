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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.batch12.rvirb.foundation.bank.entities.Account;
import com.batch12.rvirb.foundation.bank.entities.Customer;

// This Test will use actual DataBase with @SpringBootTest

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class CustomerRepository2Test {

	@Autowired
	CustomerRepository customerRepository;

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

		List<Customer> customerList1 = new ArrayList<Customer>();
		List<Customer> customerList2 = new ArrayList<Customer>();
		List<Customer> customerList3 = new ArrayList<Customer>();
		List<Customer> customerList4 = new ArrayList<Customer>();
		
		List<Account> accountList1 = new ArrayList<Account>();
		List<Account> accountList2 = new ArrayList<Account>();		
		List<Account> accountList3 = new ArrayList<Account>();
		
		customerList1.add(customer1);
		customerList2.add(customer2);
		customerList3.add(customer3);
		customerList3.add(customer1);
		customerList4.add(customer4);
		
		accountList1.add(account1);
		accountList2.add(account2);
		accountList3.add(account3);
		accountList3.add(account2);
		
		customer1.setAccounts(accountList3);
		customer2.setAccounts(accountList2);
		customer3.setAccounts(accountList3);
		customer4.setAccounts(accountList1);
		
		account1.setCustomers(customerList4);
		account2.setCustomers(customerList2);
		account3.setCustomers(customerList3);
		
		customerRepository.save(customer1);
		customerRepository.save(customer2);
		customerRepository.save(customer3);
		customerRepository.save(customer4);
	}

	@Test
	@Order(2)
	@Transactional
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