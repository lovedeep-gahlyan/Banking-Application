package com.banking.bankingportal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Account_details;
import com.banking.bankingportal.model.Customer;

public interface AccountDetailsRepo extends JpaRepository<Account_details, Long> {

	Account_details findByCustomer(Customer customer);
	Account_details findById(long account_no);
}