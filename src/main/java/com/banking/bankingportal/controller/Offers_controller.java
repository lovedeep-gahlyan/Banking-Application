package com.banking.bankingportal.controller;

import java.sql.Date;
import java.util.List;

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

import com.banking.bankingportal.model.Offers;
import com.banking.bankingportal.model.Offers;
import com.banking.bankingportal.repo.OffersRepo;

@RestController	
public class Offers_controller {
	
	private Logger log = LoggerFactory.getLogger(CreditCardController.class);
	
	@Autowired
	private OffersRepo repo;

	
	@PostMapping("/save/offers")
	public ResponseEntity<String> saveOffer(@RequestBody Offers offer)
	{
		log.info("Entered into method with Offers card data to save");

		ResponseEntity<String> resp = null;
		try {
			  
			 offer.setStart_date(new Date(System.currentTimeMillis()));
	         Offers id =  repo.save(offer);
		

			log.info("About to call save Operation");
			
			log.debug("saved offer with id "+id.getOffer_id());

			String body = "offer '"+id.getOffer_id()+"' is created ";

			resp =  new ResponseEntity<String>(body,	HttpStatus.CREATED); //201

			log.info("Sucess response constructed");
		} catch (Exception e) {
			log.error("Unable to save offer : problem is :"+e.getMessage());
			resp =  new ResponseEntity<String>(
					"Unable to Create offer", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}
         
		log.info("About to Exit save method with Response");
		return resp;
	}
	
	@GetMapping(path="/all/offers")
	public ResponseEntity<?> getAllOffers() {
		log.info("Entered into method to fetch offers data");
		ResponseEntity<?> resp = null ;
		try {

			log.info("About to call fetch offer service");
			List<Offers> list = repo.findAll();
			if(list!=null && !list.isEmpty()) {
				log.info("Data is not empty=>"+list.size());
//				list.sort((s1,s2)->s1.getReq_dt().compareTo(s2.getReq_dt()));
				/* JDK 1.8
				list = list.stream()
						.sorted((s1,s2)->s1.getName().compareTo(s2.getName()))
						.collect(Collectors.toList());
				 */
				resp = new ResponseEntity<List<Offers>>(list, HttpStatus.OK);
			}
			else {
				log.info("No Offers cards  exist: size "+list.size());

				resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				resp = new ResponseEntity<String>(
						"No Offers_Card found Found",
						HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Unable to fetch offers : problem is :"+e.getMessage());

			resp =  new ResponseEntity<String>(
					"Unable to Fetch offers details", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}
		log.info("About to Exist fetch all method with Response");
		return resp;
	}
	
	
	@DeleteMapping("/remove/offers/{id}")
	public ResponseEntity<String> removeOffer(
			@PathVariable Integer id
			)
	{
		log.info("Entered into delete method");

		
		ResponseEntity<String> resp = null;

		try {

			log.info("About to make service call for data check");
			boolean exist =repo.existsById(id);
			if(exist) {
				repo.deleteById(id);
				log.info("offers exist with given id and deleted=>"+id);
				resp = new ResponseEntity<String>(
						"offerr '"+id+"' deleted",
						HttpStatus.OK);
			} else {
				log.warn("Given Offer id not exist =>"+id);
				resp = new ResponseEntity<String>(
						"offer '"+id+"' not exist",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Unable to perform offer Delete Operation =>" + e.getMessage());
			resp = new ResponseEntity<String>(
					"Unable to delete", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}
	
	
	

}
