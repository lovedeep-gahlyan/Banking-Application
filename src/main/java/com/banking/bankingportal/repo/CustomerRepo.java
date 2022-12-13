package com.banking.bankingportal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	List<Customer> findByUsername(String username);
}
