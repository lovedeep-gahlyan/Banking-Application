package com.banking.bankingportal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Account_details;
import com.banking.bankingportal.model.Customer;

<<<<<<< HEAD
public interface AccountDetailsRepo extends JpaRepository<Account_details, Long> {

	public Account_details findByCustomer(Customer customer);
=======
public interface AccountDetailsRepo extends JpaRepository<Account_details,Long>{
	Account_details findByCustomer(Customer customer);
>>>>>>> 492a17beefa5eba56e6c204d40d14fac83c124c8

}