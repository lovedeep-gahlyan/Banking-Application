package com.banking.bankingportal.model.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banking.bankingportal.model.Customer;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer>  {
	
}
