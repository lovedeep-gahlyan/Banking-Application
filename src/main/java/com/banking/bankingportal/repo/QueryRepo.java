package com.banking.bankingportal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.model.Query;

public interface QueryRepo extends JpaRepository<Query,Integer> {
		
	List<Query> findByCustomer(Customer customer);
	
}
