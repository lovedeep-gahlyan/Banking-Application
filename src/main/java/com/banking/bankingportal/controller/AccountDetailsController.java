package com.banking.bankingportal.controller;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bankingportal.model.Account_details;
import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.repo.AccountDetailsRepo;
import com.banking.bankingportal.repo.CustomerRepo;



@RestController
public class AccountDetailsController {
	private Logger log=LoggerFactory.getLogger(AccountDetailsController.class);
	
	@Autowired
	private AccountDetailsRepo accountDetailsRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	//save method
	@PostMapping("/customer/{customerId}/account-details")
	public ResponseEntity<String> saveapp(@PathVariable int customerId,@RequestBody Account_details account_details){
		log.info("Entered into method with data to save");
		ResponseEntity<String> resp =null;
		try {
			Customer customer = customerRepo.findById(customerId).get();
			customer.setAccount_details(account_details);
			account_details.setCustomer(customer);
			account_details.setCreate_dt(new Date(System.currentTimeMillis()));
			accountDetailsRepo.save(account_details);
			log.info("saved");
			String body="Row inserted";
			resp=new ResponseEntity<String>(body,HttpStatus.CREATED);
			log.info("Response created successfully");
			
		}
		catch (Exception e) {
			log.error("Unable to save :"+e.getMessage());
			resp =  new ResponseEntity<String>(
					"Unable to Save", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}

		log.info("About to Exit save method with Response");
		return resp;
	}
	
	//get all method
	@GetMapping("/admin/customers/account-details/")
	public ResponseEntity<?> getall(){
		log.info("Entered into method to fetch data");
		ResponseEntity<?> resp = null ;
		try {
			log.info("About to call fetch service");
			List<Account_details> list =accountDetailsRepo.findAll();
			if(list!=null && !list.isEmpty()) {
				log.info("Data is not empty =>" +list.size());
				resp=new ResponseEntity<List<Account_details>>(list,HttpStatus.OK);
			}
				
			else {
				log.info("No record exist: size "+list.size());
				resp = new ResponseEntity<String>(
						"No record exists",
						HttpStatus.OK);
			}
		}
		catch (Exception e) {
			log.error("Unable to fetch data : problem is :"+e.getMessage());

			resp =  new ResponseEntity<String>(
					"Unable to Fetch Appointments", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}
		log.info("About to Exist fetch all method with Response");
		return resp;
	}
	
	//get by id method
	@GetMapping("/customer/{customerId}/account-details")
	public ResponseEntity<?> getbyid(@PathVariable int customerId){
		log.info("Entered into method to fetch data");
		ResponseEntity<?> resp = null ;
		try {
			log.info("About to call fetch service");
			Customer customer = customerRepo.findById(customerId).get();
			Account_details accountDetails=accountDetailsRepo.findByCustomer(customer);
			resp=new ResponseEntity<Account_details>(accountDetails,HttpStatus.OK);
		}
		catch (Exception e) {
			log.error("Unable to fetch data : problem is :"+e.getMessage());

			resp =  new ResponseEntity<String>(
					"Unable to Fetch", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}
		log.info("About to Exit fetch one method with Response");
		return resp;
	}
	
	
	
	
	
	

	
	

}
