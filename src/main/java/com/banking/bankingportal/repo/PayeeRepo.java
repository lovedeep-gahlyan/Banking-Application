package com.banking.bankingportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Payee;

public interface PayeeRepo extends JpaRepository<Payee,Integer>{

}
