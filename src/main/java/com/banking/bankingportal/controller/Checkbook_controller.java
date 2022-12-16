package com.banking.bankingportal.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

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

import com.banking.bankingportal.model.Checkbook_req;
import com.banking.bankingportal.model.Credit_req;
import com.banking.bankingportal.model.Checkbook_req;
import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.repo.CheckbookreqRepo;
import com.banking.bankingportal.repo.CustomerRepo;

@RestController
public class Checkbook_controller {
	
	private Logger log = LoggerFactory.getLogger(CreditCardController.class);
	
	@Autowired
	CheckbookreqRepo checkbookRepo;
	
	@Autowired
	CustomerRepo custRepo;
	
	
	@PostMapping("/customer/{customerId}/checkbook")
	public ResponseEntity<?> saveCheckbook(
			@PathVariable int customerId,
			@RequestBody Checkbook_req checkBook)
	{
	
		log.info("Entered into method with checkbook data to save");

		ResponseEntity<String> resp = null;
		try {
			  
			 Customer customer = custRepo.findById(customerId).get();
			 
			 // set update of thecheckbooks 
			 
//	         customer.appendCheckbook_reqList(cred);
	         
	         // foreign key value update 
	         checkBook.setCustomer(customer);     
//	         log.info(customer.getCheckbook_req().toString()+"-------------------");
	      
	         
	         checkBook.setReq_dt(new Date(System.currentTimeMillis()));
	         Checkbook_req id = checkbookRepo.save(checkBook);

			log.info("About to call save Operation");
			
			log.debug("saved check book with id "+id.getReq_id());
			
			String body = "checkbook '"+id.getReq_id()+"' is created ";
			
			resp =  new ResponseEntity<String>(body,HttpStatus.CREATED); //201
			
			log.info("Sucess response constructed");
			
			
		} catch (Exception e) {
			log.error("Unable to savecheckbook : problem is :"+e.getMessage());
			resp =  new ResponseEntity<String>(
					"Unable to Createcheckbook", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}
         
		log.info("About to Exit save method with Response");
		return resp;
	}
	
	
	
	@GetMapping(path="/admin/check_books/requests")
	public ResponseEntity<?> getAllCheckBook() {
		log.info("Entered into method to fetch Check_Book data");
		ResponseEntity<?> resp = null ;
		try {

			log.info("About to call fetch Check_Book service");
			List<Checkbook_req> list = checkbookRepo.findAll();
			if(list!=null && !list.isEmpty()) {
				log.info("Data is not empty=>"+list.size());
//				list.sort((s1,s2)->s1.getReq_dt().compareTo(s2.getReq_dt()));
				/* JDK 1.8
				list = list.stream()
						.sorted((s1,s2)->s1.getName().compareTo(s2.getName()))
						.collect(Collectors.toList());
				 */
				resp = new ResponseEntity<List<Checkbook_req>>(list, HttpStatus.OK);
			}
			else {
				log.info("No Check_Books  exist: size "+list.size());

				resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				resp = new ResponseEntity<String>(
						"No Check book found Found",
						HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Unable to fetch Check_Book : problem is :"+e.getMessage());

			resp =  new ResponseEntity<String>(
					"Unable to Fetch Check_Book details", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}
		log.info("About to Exist fetch all method with Response");
		return resp;
	}
	

	@GetMapping("/customer/{customerId}/checkbook")
	public Checkbook_req getCardByCustomerId(@PathVariable int customerId) {
		Customer customer = custRepo.findById(customerId).get();
		Checkbook_req request = checkbookRepo.findByCustomer(customer);
		return request;
	}
	
//	@GetMapping("/one/checkbook/{id}")
//	public ResponseEntity<?> getOneCheckbook(
//			@PathVariable Integer id
//			) 
//	{
//		log.info("Entered into Get one Check book method");
//		ResponseEntity<?> resp = null;
//		try {
//			log.info("About to make service call to fetch one record");
//			Optional<Checkbook_req> opt =  repo.findById(id);
//			if(opt.isPresent()) {
//				log.info("Check book exist=>"+id);
//				resp = new ResponseEntity<Checkbook_req>(opt.get(), HttpStatus.OK);
//				//resp = ResponseEntity.ok(opt.get());
//			} else {
//				log.warn("Given checkbook id not exist=>"+id);
//				resp = new ResponseEntity<String>(
//						"Check book '"+id+"' not exist", 
//						HttpStatus.BAD_REQUEST);
//			}
//		} catch (Exception e) {
//			log.error("Unable to process request fetch " + e.getMessage());
//			resp = new ResponseEntity<String>(
//					"Unable to process checkbook fetch", 
//					HttpStatus.INTERNAL_SERVER_ERROR);
//			e.printStackTrace();
//		}
//
//		return resp;
//	}
	
	
	
    
}
