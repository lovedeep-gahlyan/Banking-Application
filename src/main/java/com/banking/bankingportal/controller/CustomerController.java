package com.banking.bankingportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bankingportal.model.Account_details;
import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.repo.CustomerRepo;




@RestController
public class CustomerController {
	
		@Autowired
		private CustomerRepo customerRepo;
		
		@Autowired
		private PasswordEncoder passwordEncoder;		
		@Autowired
		private JdbcTemplate springJdbcTemplate;
	
		// Registering Customer
		
		@PostMapping("/register")
		public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
	        Customer savedCustomer = null;
	        ResponseEntity<String> response = null;
	        try {
	            String hashPwd = passwordEncoder.encode(customer.getPassword());
	            customer.setPassword(hashPwd);
	            savedCustomer = customerRepo.save(customer);
	            if (savedCustomer.getCustomer_id() > 0) {
	                response = ResponseEntity
	                        .status(HttpStatus.CREATED)
	                        .body("Given user details are successfully registered");
	            }
	        } catch (Exception ex) {
	            response = ResponseEntity
	                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("An exception occured due to " + ex.getMessage());
	        }
	        return response;
	    }
		
		// Get Customer after Auth
		
		 @RequestMapping("/user")
		    public Customer getUserDetailsAfterLogin(Authentication authentication) {
		        List<Customer> customers = customerRepo.findByUsername(authentication.getName());
		        if (customers.size() > 0) {
		            return customers.get(0);
		        } else {
		            return null;
		        }

		    }
		 
		 // Updating Customer Details
		 
		 @RequestMapping(value = "/customer/{customerId}/details/update", method=RequestMethod.PATCH)
		 	public void updateCustomerDetails(@PathVariable int customerId, @RequestBody Customer customer) {
			 String address = customer.getAddress();
			 String phone = customer.getPhone();
			 String email = customer.getEmail();
			 String Update_Query = "update customer set address=?, phone=?, email=? where customer_id = ?";
			 springJdbcTemplate.update(Update_Query,address,phone,email,customerId);
		 }
		 
		 
		 // Show All Customer ( Admin )
		 @GetMapping("/admin/customers")
		 public ResponseEntity<?> getAllCustomers(){
			 ResponseEntity<?> resp = null ;
			 List<Customer> customers = (List<Customer>) customerRepo.findAll();
			 resp=new ResponseEntity<List<Customer>>(customers,HttpStatus.OK); 
			 return resp;
		 }
		 
}
