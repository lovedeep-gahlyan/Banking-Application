package com.banking.bankingportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Credit_req;
import com.banking.bankingportal.model.Customer;

public interface CreditreqRepo extends JpaRepository<Credit_req,Integer>{
	Credit_req findByCustomer(Customer customer);
}
