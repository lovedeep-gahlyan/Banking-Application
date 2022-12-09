package com.banking.bankingportal.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.banking.bankingportal.model.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {
	List<Customer> findByUsername(String username);
}
