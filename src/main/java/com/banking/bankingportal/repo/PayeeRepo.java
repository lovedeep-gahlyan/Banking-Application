package com.banking.bankingportal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.model.Payee;

public interface PayeeRepo extends JpaRepository<Payee,Integer>{
	List<Payee> findByCustomer(Customer customer);

}
