package com.banking.bankingportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Checkbook_req;
import com.banking.bankingportal.model.Credit_req;
import com.banking.bankingportal.model.Customer;

public interface CheckbookreqRepo extends JpaRepository<Checkbook_req,Integer>{
	Checkbook_req findByCustomer(Customer customer);
	
}
