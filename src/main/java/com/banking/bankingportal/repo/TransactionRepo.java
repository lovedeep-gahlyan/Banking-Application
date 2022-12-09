package com.banking.bankingportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Transactions;

public interface TransactionRepo extends JpaRepository<Transactions, Integer> {

}
