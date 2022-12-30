package com.banking.bankingportal.controller;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.model.Query;
import com.banking.bankingportal.repo.CustomerRepo;
import com.banking.bankingportal.repo.QueryRepo;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class QueryController {
	
	@Autowired
	QueryRepo queryRepo;
	
	@Autowired
	CustomerRepo customerRepo;
	
	
	// Get - All Query (Admin Route)
	
	@RequestMapping(value="admin/queries",method=RequestMethod.GET)
	public List<Query> getQuery() {
		List<Query> query = queryRepo.findAll();
		return query;
	}
	
	//Post - Query By Customer ID
	
	@RequestMapping(value="/customer/{customerId}/query",method=RequestMethod.POST)
	public ResponseEntity<Object> postQueryById(@PathVariable int customerId,@RequestBody Query query) {
		Customer customer = customerRepo.findById(customerId).get();
		query.setCustomer(customer);
		query.setQuery_dt(new Date(System.currentTimeMillis()));
		query.setUserName(customer.getUsername());
		queryRepo.save(query);
		return ResponseEntity.created(null).build();

	}
	
	// Get - Query By Id (User Route)
	
	@RequestMapping(value="/customer/{customerId}/query",method=RequestMethod.GET)
	public List<Query> getQuery(@PathVariable int customerId) {
		Customer customer = customerRepo.findById(customerId).get();
		List<Query> query =  queryRepo.findByCustomer(customer);
		return query;
	}
	
	
}
