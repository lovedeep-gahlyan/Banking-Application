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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bankingportal.model.Account_details;
import com.banking.bankingportal.model.Customer;
import com.banking.bankingportal.model.Transactions;
import com.banking.bankingportal.repo.AccountDetailsRepo;
import com.banking.bankingportal.repo.CustomerRepo;
import com.banking.bankingportal.repo.TransactionRepo;

@RestController
@RequestMapping("/trxn")
public class TransactionsController {

	@Autowired
	CustomerRepo CustomerRepo;

	@Autowired
	TransactionRepo TransactionRepo;

	@Autowired
	AccountDetailsRepo AccountRepo;

	private Logger log = LoggerFactory.getLogger(TransactionsController.class);

	@PostMapping(path = "/maketrxn/{customerId}")
	public ResponseEntity<?> saveTransaction(@PathVariable("customerId") int customerId,
			@RequestBody Transactions trxn) {

		String body = null;

		log.info("Entered into method 'saveTransaction' with Transaction object to save");

		ResponseEntity<String> resp = null;

		try {

			Customer customer = CustomerRepo.findById(customerId).get();
			
			// Get account details of sender and receiver
			Account_details accrec = AccountRepo.findById(trxn.getAccount_num_reciever());
			Account_details accsend = AccountRepo.findById(trxn.getAccount_num_sender());

			log.debug("Created sender's and receiver's data objects");

			if (accsend == null) {

				log.error("ERROR! Customer's account details returned 'null' object");
				return new ResponseEntity<String>("An error occured! Could not find user account", HttpStatus.OK);
			}

			// Logic to update customer balance
			int updatedBal = accsend.getAccount_balance() - trxn.getTransaction_amt();
			if (updatedBal >= 0) {
				trxn.setClosing_bal_sender(updatedBal);
				accsend.setAccount_balance(updatedBal);
				if (accrec != null) {

					int updatedbal_rec = accrec.getAccount_balance() + trxn.getTransaction_amt();
					trxn.setClosing_bal_reciever(updatedbal_rec);
					accrec.setAccount_balance(updatedbal_rec);

				} else {
					trxn.setClosing_bal_reciever(-1);
				}

				// foreign key value update
				trxn.setCustomer(customer);
				
				// Set real time date for transaction
				trxn.setTransaction_dt(new Date(System.currentTimeMillis()));
				
				// SET update of current transaction in customer
				customer.addTransaction(trxn);

				// Saving details in database
				AccountRepo.save(accsend);
				AccountRepo.save(accrec);
				Transactions id = TransactionRepo.save(trxn);
				
				log.info("Data saved in database");
				log.debug("Saved transaction with id " + id.getTransaction_id());

				body = "Transactions details with id '" + id.getTransaction_id() + "' is saved ";

			} else {
				body = "Amount insufficient for making any transaction possible";
			}

			// Creating a Response Entity object
			resp = new ResponseEntity<String>(body, HttpStatus.CREATED); // 201
			log.info("Success response constructed");

		} catch (Exception e) {
			
			log.error("Unable to save transaction details! Problem is :" + e.getMessage());
			resp = new ResponseEntity<String>("Unable to save transactions details", HttpStatus.INTERNAL_SERVER_ERROR); // 500
			e.printStackTrace();
		}

		log.info("Exiting save method 'saveTransaction' ");
		return resp;
	}

	@GetMapping(path = "/gettrxn/all")
	public ResponseEntity<?> getAllTransactions() {
		
		log.info("Entered into method 'getAllTransactions' to fetch all transactions details");
		
		ResponseEntity<?> resp = null;
		
		try {
			
			log.info("Creating a list of transactions for all transactions details");

			List<Transactions> list = TransactionRepo.findAll();
			
			if (list != null && !list.isEmpty()) {
				
				log.info("Data found with size => " + list.size());
				resp = new ResponseEntity<List<Transactions>>(list, HttpStatus.OK);
				
			} else {
				
				log.info("No transactions  exist! Size : " + list.size());
				resp = new ResponseEntity<String>("No Transaction history found!", HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Unable to fetch transaction history! Problem is :" + e.getMessage());

			resp = new ResponseEntity<String>("Unable to Fetch Transaction details", HttpStatus.INTERNAL_SERVER_ERROR); // 500
			e.printStackTrace();
		}
		log.info("Exiting fetch all method 'getAllTransactions'");
		return resp;
	}

	@GetMapping(path = "/getbyid/{customerid}")
	public ResponseEntity<?> getTransactionsById(@PathVariable("customerid") int customerId) {
		
		log.info("Entered into method 'getTransactionsById' to fetch transactions details by Id");
		
		ResponseEntity<?> resp = null;
		
		try {
			
			log.info("Creating a list of transaction for the customer by customer Id");

			Customer customer = CustomerRepo.findById(customerId).get();
			List<Transactions> list = TransactionRepo.findByCustomer(customer);
			
			if (list != null && !list.isEmpty()) {
				
				log.info("Data found with size => " + list.size());
				resp = new ResponseEntity<List<Transactions>>(list, HttpStatus.OK);
				
			} else {
				
				log.info("No transaction history exist! Size : " + list.size());
				resp = new ResponseEntity<String>("No Transaction History Found", HttpStatus.OK);
				
			}
		} catch (Exception e) {
			
			log.error("Unable to fetch transaction history! Problem is :" + e.getMessage());

			resp = new ResponseEntity<String>("Unable to Fetch Transaction details", HttpStatus.INTERNAL_SERVER_ERROR); // 500
			e.printStackTrace();
		}
		log.info("Exiting fetch method 'getTransactionsById'");
		return resp;
	}

}
