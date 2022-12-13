package com.banking.bankingportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.model.User_offers;
import com.banking.bankingportal.repo.CustomerRepo;
import com.banking.bankingportal.repo.UserOffersRepo;



@RestController
public class UserOfferController {
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	UserOffersRepo userOffersRepo;

	@PostMapping("/admin/customer/{customerId}/offers")
	public ResponseEntity<Object> addUserOffer(@PathVariable int customerId,@RequestBody User_offers user_offers) {
		Customer customer = customerRepo.findById(customerId).get();
		user_offers.setCustomer(customer);
		userOffersRepo.save(user_offers);
		return ResponseEntity.created(null).build();
	}
	
	@GetMapping("admin/customer/{customerId}/offers")
	public List<User_offers> getUserOffers(@PathVariable int customerId) {
		Customer customer = customerRepo.findById(customerId).get();
		List<User_offers> user_offers = userOffersRepo.findByCustomer(customer);
		return user_offers;
	}
	
	
}
