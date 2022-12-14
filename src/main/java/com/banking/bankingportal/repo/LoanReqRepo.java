package com.banking.bankingportal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.model.Loan_req;

public interface LoanReqRepo extends JpaRepository<Loan_req, Integer>{

	List<Loan_req> findByCustomer(Customer customer);

}
