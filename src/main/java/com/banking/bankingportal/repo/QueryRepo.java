package com.banking.bankingportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Query;

public interface QueryRepo extends JpaRepository<Query,Integer> {

}
