package com.banking.bankingportal.controller;



import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.model.Payee;
import com.banking.bankingportal.repo.CustomerRepo;
import com.banking.bankingportal.repo.PayeeRepo;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class PayeeController {
	private Logger log=LoggerFactory.getLogger(PayeeController.class);
	
	@Autowired
	PayeeRepo payeeRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	JdbcTemplate jt;
	
	//save method
		@PostMapping("/customer/{customerId}/payee")
		public ResponseEntity<String> saveapp(@PathVariable int customerId,@RequestBody Payee payee){
			log.info("Entered into method with data to save");
			ResponseEntity<String> resp =null;
			try {
				Customer customer = customerRepo.findById(customerId).get();
				customer.assignPayee(payee);
				payee.setCustomer(customer);
				payeeRepo.save(payee);
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
//		@GetMapping("/getall/payee")
//		public ResponseEntity<?> getall(){
//			log.info("Entered into method to fetch data");
//			ResponseEntity<?> resp = null ;
//			try {
//				log.info("About to call fetch service");
//				List<Payee> list =payeeRepo.findAll();
//				if(list!=null && !list.isEmpty()) {
//					log.info("Data is not empty =>" +list.size());
//					resp=new ResponseEntity<List<Payee>>(list,HttpStatus.OK);
//				}
//					
//				else {
//					log.info("No record exist: size "+list.size());
//					resp = new ResponseEntity<String>(
//							"No record exists",
//							HttpStatus.OK);
//				}
//			}
//			catch (Exception e) {
//				log.error("Unable to fetch data : problem is :"+e.getMessage());
//
//				resp =  new ResponseEntity<String>(
//						"Unable to Fetch Appointments", 
//						HttpStatus.INTERNAL_SERVER_ERROR); //500
//				e.printStackTrace();
//			}
//			log.info("About to Exist fetch all method with Response");
//			return resp;
//		}
		
		
		
		//get by customerId method
		@GetMapping("/customer/{customerId}/payee")
		public ResponseEntity<?> getbyCusid(@PathVariable int customerId){
			log.info("Entered into method to fetch data");
			ResponseEntity<?> resp = null ;
			try {
				log.info("About to call fetch service");
				Customer customer = customerRepo.findById(customerId).get();
				 List<Payee> payee=payeeRepo.findByCustomer(customer);
				resp=new ResponseEntity<List<Payee>>(payee,HttpStatus.OK);
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
		
		
		
		
		//get by payeeId method
		@GetMapping("/customer/payee/{payeeId}")
		public ResponseEntity<?> getbyPayeeId(@PathVariable int payeeId){
			log.info("Entered into method to fetch data");
			ResponseEntity<?> resp = null ;
			try {
				log.info("About to call fetch service");
				 Optional<Payee> payee=payeeRepo.findById(payeeId);
				resp=new ResponseEntity<Optional<Payee>>(payee,HttpStatus.OK);
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
		
		
		
		//delete Payee
		@DeleteMapping("/customer/payee/{payeeId}/delete")
		public ResponseEntity<String> RemovePayee(@PathVariable int payeeId)
		{
			log.info("Entered into delete method");
			ResponseEntity<String> resp = null;

			try {

				log.info("About to make service call for data check");
				boolean exist = payeeRepo.existsById(payeeId);
				if(exist) {
					payeeRepo.deleteById(payeeId);
					log.info("Payee exist with given id and deleted=>"+payeeId);
					resp = new ResponseEntity<String>(
							"payee '"+payeeId+"' deleted",
							HttpStatus.OK);
				} else {
					log.warn("Given payee id not exist =>"+payeeId);
					resp = new ResponseEntity<String>(
							"payee '"+payeeId+"' not exist",
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
		
		
		//update payee
		@PatchMapping("customer/payee/{payeeId}/update")
		public ResponseEntity<String> UpdatePayee(@PathVariable int payeeId,@RequestBody Payee payee){
			log.info("Entered into update method");
			ResponseEntity<String> resp = null;
			try {

				log.info("About to make service call for data check");
				boolean exist = payeeRepo.existsById(payeeId);
				
				if(exist) {
					
					String name=payee.getName();
					String email=payee.getEmail();
					long payee_account_no=payee.getPayee_account_no();
					String bank_name=payee.getBank_name();
					String phone=payee.getPhone();
					String Update_Query = "update payee set name=?, phone=?, email=?, payee_account_no=?,bank_name=? where payee_id = ?";
					jt.update(Update_Query,name,phone,email,payee_account_no,bank_name,payeeId);
					
					log.info("Payee exist with given id and updated=>"+payeeId);
					resp = new ResponseEntity<String>(
							"payee '"+payeeId+"' updated",
							HttpStatus.OK);
				} else {
					log.warn("Given payee id not exist =>"+payeeId);
					resp = new ResponseEntity<String>(
							"payee '"+payeeId+"' not exist",
							HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {
				log.error("Unable to perform update Operation =>" + e.getMessage());
				resp = new ResponseEntity<String>(
						"Unable to update", 
						HttpStatus.INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
			
			return resp;
		}
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		

}
