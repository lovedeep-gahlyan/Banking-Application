package com.banking.bankingportal.controller;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.model.Loan_req;

import com.banking.bankingportal.repo.CustomerRepo;
import com.banking.bankingportal.repo.LoanReqRepo;

@RestController
public class LoanReqController {

	@Autowired
	private CustomerRepo CustRepo;

	@Autowired
	private LoanReqRepo LoanRepo;

	Logger log = LoggerFactory.getLogger(LoanReqController.class);

	@PostMapping("customer/{customerid}/loan/request")
	public ResponseEntity<?> setLoanReq(@PathVariable("customerid") int customerid, @RequestBody Loan_req loanReq) {

		log.info("Entered into method 'setLoanReq' to add Loan Requests ");

		
		ResponseEntity<String> resp = null;
		try {
			
			log.info("Creating and adding loan request data to the database");
			
			Customer customer = CustRepo.findById(customerid).get();
			loanReq.setCustomer(customer);
			loanReq.setApprove(false);
			Date now = new Date(System.currentTimeMillis());
			loanReq.setReq_dt(now);
			
			//customer.addLoan_req(loanReq);
			
			Loan_req reqId = LoanRepo.save(loanReq);

			log.info("Loan Request Added with request ID : " + reqId.getReq_id());

			String body = "Loan Request Added with request ID : " + reqId.getReq_id();

			resp = new ResponseEntity<String>(body, HttpStatus.CREATED); // HttpStatusCode = 201

		} catch (Exception e) {
			log.error("Unable to send loan request! Problem is :" + e.getMessage());
			resp = new ResponseEntity<String>("Unable to send Loan Request!", HttpStatus.INTERNAL_SERVER_ERROR); // 500
			e.printStackTrace();
		}

		log.info("About to exit method 'setLoanReq' ");
		return resp;
	}

	@GetMapping("admin/loan/requests")
	public ResponseEntity<?> getAllRequests() {

		log.info("Entered into method 'getAllRequests' to fetch all loan requests");
		
		ResponseEntity<?> resp = null;
		try {
			log.info("Creating a list of loan_req for all loan requests in database");

			List<Loan_req> list = LoanRepo.findAll();
			
			if (list != null && !list.isEmpty()) {
				
				log.info("Data found with size => " + list.size());
				resp = new ResponseEntity<List<Loan_req>>(list, HttpStatus.OK);
				
			} else {
				log.info("No loan reqest record exists! Size : " + list.size());
				resp = new ResponseEntity<String>("No loan requests found", HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Unable to fetch loan requests! Problem is :" + e.getMessage());

			resp = new ResponseEntity<String>("Unable to Fetch Loan requests", HttpStatus.INTERNAL_SERVER_ERROR); // 500
			e.printStackTrace();
		}
		log.info("About to exit method 'getAllRequests'");
		return resp;
	}
	
	@GetMapping(path = "customer/{customerid}/loan/request")
	public ResponseEntity<?> getLoanReqById(@PathVariable("customerid") int customerId) {
		
		log.info("Entered into method 'getLoanReqById' to fetch loan requests by customer Id");
		
		ResponseEntity<?> resp = null;
		try {
			log.info("Creating list of loan requests fetched by customer Id ");

			Customer customer = CustRepo.findById(customerId).get();
			List<Loan_req> list = LoanRepo.findByCustomer(customer);
			
			if (list != null && !list.isEmpty()) {
				
				log.info("Data found with size => " + list.size());
				resp = new ResponseEntity<List<Loan_req>>(list, HttpStatus.OK);
				
			} else {
				log.info("No loan requests exists! Size : " + list.size());
				resp = new ResponseEntity<String>("No loan requests found! ", HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Unable to fetch loan requests! Problem is :" + e.getMessage());

			resp = new ResponseEntity<String>("Unable to Fetch Loan requests! ", HttpStatus.INTERNAL_SERVER_ERROR); // 500
			e.printStackTrace();
		}
		log.info("About to exit method 'getLoanReqById' ");
		return resp;
	}

}
