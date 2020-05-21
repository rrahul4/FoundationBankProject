package com.batch12.rvirb.foundation.bank.repositories;

import org.springframework.data.repository.CrudRepository;

import com.batch12.rvirb.foundation.bank.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
