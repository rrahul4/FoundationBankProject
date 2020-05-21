package com.batch12.rvirb.foundation.bank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.batch12.rvirb.foundation.bank.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	public List<Customer> findByCustomerFirstNameAndCustomerLastName(String firstName, String lastName);
	public List<Customer> findByCustomerFirstNameOrCustomerLastName(String firstName, String lastName);
	
	@Query("from Customer where customerFirstName=:firstName and customerLastName=:lastName")
	public List<Customer> getCustomerByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
	
	@Query(value="select * from customer", nativeQuery=true)
	public List<Customer> findAllCustomers();
	
}
