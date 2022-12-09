package com.banking.bankingportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Account_details;

public interface AccountDetailsRepo extends JpaRepository<Account_details,Long>{

}