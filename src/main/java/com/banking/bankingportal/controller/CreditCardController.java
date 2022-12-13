package com.banking.bankingportal.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bankingportal.model.Credit_req;
import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.repo.CreditreqRepo;
import com.banking.bankingportal.repo.CustomerRepo;


@RestController
//@RequestMapping("/rest/Credit_req")
//@CrossOrigin("")
public class CreditCardController {
	
	private Logger log = LoggerFactory.getLogger(CreditCardController.class);
	

//	----------------- controller code for the Credit_req class ---------------------
	
	@Autowired
	private	CreditreqRepo Credit_repo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	
//	@RequestMapping("/")
//	@ResponseBody
//	public String showme(){
//		return "tghis is worikign";
//	}
	
	
	
	@PostMapping("/save/creditcard/{customerId}")
	public ResponseEntity<?> saveCreditCard(
			@PathVariable int customerId,
			@RequestBody Credit_req cred)
	{
	
		log.info("Entered into method with Credit card data to save");

		ResponseEntity<String> resp = null;
		try {
			  
			 Customer customer = customerRepo.findById(customerId).get();
			 
			 // set update of the credit cards 
			 
	         customer.appendCredit_reqList(cred);
	         
	         // foreign key value update 
	         cred.setCustomer(customer);     
//	         log.info(customer.getCredit_req().toString()+"-------------------");
	      
	         
	         cred.setReq_dt( new Date(System.currentTimeMillis()));
	         Credit_req id =  Credit_repo.save(cred);
		

			log.info("About to call save Operation");
			
			log.debug("saved crdit card with id "+id.getReq_id());

			String body = "credit '"+id.getReq_id()+"' is created ";

			resp =  new ResponseEntity<String>(body,	HttpStatus.CREATED); //201

			log.info("Sucess response constructed");
		} catch (Exception e) {
			log.error("Unable to save credit card : problem is :"+e.getMessage());
			resp =  new ResponseEntity<String>(
					"Unable to Create credit card", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}
         
		log.info("About to Exit save method with Response");
		return resp;
	}
	
	
	@GetMapping(path="/all/credit_cards")
	public ResponseEntity<?> getAllCreditCards() {
		log.info("Entered into method to fetch Credit card data");
		ResponseEntity<?> resp = null ;
		try {

			log.info("About to call fetch credit card service");
			List<Credit_req> list = Credit_repo.findAll();
			if(list!=null && !list.isEmpty()) {
				log.info("Data is not empty=>"+list.size());
//				list.sort((s1,s2)->s1.getReq_dt().compareTo(s2.getReq_dt()));
				/* JDK 1.8
				list = list.stream()
						.sorted((s1,s2)->s1.getName().compareTo(s2.getName()))
						.collect(Collectors.toList());
				 */
				resp = new ResponseEntity<List<Credit_req>>(list, HttpStatus.OK);
			}
			else {
				log.info("No Credit cards  exist: size "+list.size());

				resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				resp = new ResponseEntity<String>(
						"No Credit_Card found Found",
						HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Unable to fetch credit card : problem is :"+e.getMessage());

			resp =  new ResponseEntity<String>(
					"Unable to Fetch Credit Card details", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}
		log.info("About to Exist fetch all method with Response");
		return resp;
	}

	@GetMapping("/one/credit_card/{id}")
	public ResponseEntity<?> getOneCreditCard(
			@PathVariable Integer id
			) 
	{
		log.info("Entered into Get one Credit card method");
		ResponseEntity<?> resp = null;
		try {
			log.info("About to make service call to fetch one record");
			Optional<Credit_req> opt =  Credit_repo.findById(id);
			if(opt.isPresent()) {
				log.info("credit card exist=>"+id);
				resp = new ResponseEntity<Credit_req>(opt.get(), HttpStatus.OK);
				//resp = ResponseEntity.ok(opt.get());
			} else {
				log.warn("Given Credit_cared id not exist=>"+id);
				resp = new ResponseEntity<String>(
						"Credit card '"+id+"' not exist", 
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Unable to process request fetch " + e.getMessage());
			resp = new ResponseEntity<String>(
					"Unable to process credit_Cad fetch", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}

	
	@DeleteMapping("/remove/credit_card/{id}")
	public ResponseEntity<String> removeCredit_req(
			@PathVariable Integer id
			)
	{
		log.info("Entered into delete method");

		
		ResponseEntity<String> resp = null;

		try {

			log.info("About to make service call for data check");
			boolean exist = Credit_repo.existsById(id);
			if(exist) {
				Credit_repo.deleteById(id);
				log.info("Credit_req exist with given id and deleted=>"+id);
				resp = new ResponseEntity<String>(
						"Credit_req '"+id+"' deleted",
						HttpStatus.OK);
			} else {
				log.warn("Given Credit_req id not exist =>"+id);
				resp = new ResponseEntity<String>(
						"Credit_req '"+id+"' not exist",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Unable to perform Delete Operation =>" + e.getMessage());
			resp = new ResponseEntity<String>(
					"Unable to delete", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}
	
	



	
//	----------------------------- end of controller function for the credit_req class --------
	
// -------------------------------
	
	
	

}
