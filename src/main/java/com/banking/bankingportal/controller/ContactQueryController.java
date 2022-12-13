package com.banking.bankingportal.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bankingportal.model.Contact_Query;
import com.banking.bankingportal.repo.ContactQueryRepo;


@RestController
public class ContactQueryController {
	
	@Autowired
	ContactQueryRepo contactQueryRepo;
	
	// GET - CONTACT PAGE
	@RequestMapping(value="/contact",method=RequestMethod.GET)
	public String showContactPage() {
		return "Contact Us";
	}
	

	// POST - CONTACT QUERY
	
	@RequestMapping(value = "/contactquery", method = RequestMethod.POST)
	public ResponseEntity<Object> addContactQuery(@RequestBody Contact_Query contactquery) {
		contactquery.setQuery_dt(new Date(System.currentTimeMillis()));
		contactQueryRepo.save(contactquery);
		return ResponseEntity.created(null).build();
	}
	
	// GET - CONTACT (Admin)
	
	@RequestMapping(value = "/admin/contactquery", method = RequestMethod.GET)
	public List<Contact_Query> showContactQuery(){
		List<Contact_Query> query = contactQueryRepo.findAll();
		return query;
	}
	
	
}
