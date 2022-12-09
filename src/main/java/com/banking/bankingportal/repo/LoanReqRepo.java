package com.banking.bankingportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Loan_req;

public interface LoanReqRepo extends JpaRepository<Loan_req, Integer>{

}
