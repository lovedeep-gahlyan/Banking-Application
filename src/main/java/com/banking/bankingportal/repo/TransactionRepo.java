package com.banking.bankingportal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.model.Transactions;

public interface TransactionRepo extends JpaRepository<Transactions, Integer> {
	
	public List<Transactions> findByCustomer(Customer customer);
}
