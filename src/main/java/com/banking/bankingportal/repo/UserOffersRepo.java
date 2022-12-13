package com.banking.bankingportal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.model.User_offers;

public interface UserOffersRepo extends JpaRepository<User_offers,Integer>{
	List<User_offers> findByCustomer(Customer customer);
}
