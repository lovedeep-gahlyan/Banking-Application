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

//	@RequestMapping(path="/maketrxn")
//	public String makeTrxn() {
//		return "hello";
//	}

	@PostMapping(path = "/maketrxn/{customerId}")
	public ResponseEntity<?> savetransaction(@PathVariable("customerId") int customerId,
			@RequestBody Transactions trxn) {

		log.info("Entered into method with transaction data to save");

		ResponseEntity<String> resp = null;
		try {

			Customer customer = CustomerRepo.findById(customerId).get();
			
			Account_details accrec = AccountRepo.findByCustomer(customer);
			Account_details accsend = AccountRepo.findByCustomer(customer);
			
			int currentbal = accdet.getAccount_balance();
			trxn.setClosing_balance_sender(currentbal);
			accdet.setAccount_balance(currentbal-trxn.getTransaction_amt());
			
			AccountRepo.save(accdet);
			
			// set update of the credit cards
			customer.addTransaction(trxn);
			System.out.println(customer);
			// foreign key value update
			trxn.setCustomer(customer);

			trxn.setTransaction_dt(new Date(System.currentTimeMillis()));
			Transactions id = TransactionRepo.save(trxn);

			log.info("About to call save Operation");

			log.debug("saved transaction with id " + id.getTransaction_id());

			String body = "Transactions details with id '" + id.getTransaction_id() + "' is saved ";

			resp = new ResponseEntity<String>(body, HttpStatus.CREATED); // 201

			log.info("Success response constructed");
		} catch (Exception e) {
			log.error("Unable to save transaction details : problem is :" + e.getMessage());
			resp = new ResponseEntity<String>("Unable to save transactions details", HttpStatus.INTERNAL_SERVER_ERROR); // 500
			e.printStackTrace();
		}

		log.info("About to Exit save method with Response");
		return resp;
	}

	@GetMapping(path = "/gettrxn/all/")
	public ResponseEntity<?> getAlltransactions() {
		log.info("Entered into method to fetch transactions details");
		ResponseEntity<?> resp = null;
		try {
			log.info("About to call fetch transaction details service");

			List<Transactions> list = TransactionRepo.findAll();
			if (list != null && !list.isEmpty()) {
				log.info("Data is not empty=>" + list.size());
//                list.sort((s1,s2)->s1.getReq_dt().compareTo(s2.getReq_dt()));
				/*
				 * JDK 1.8 list = list.stream()
				 * .sorted((s1,s2)->s1.getName().compareTo(s2.getName()))
				 * .collect(Collectors.toList());
				 */
				resp = new ResponseEntity<List<Transactions>>(list, HttpStatus.OK);
			} else {
				log.info("No transactions  exist: size " + list.size());

				resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				resp = new ResponseEntity<String>("No Transaction History Found", HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Unable to fetch transaction history : problem is :" + e.getMessage());

			resp = new ResponseEntity<String>("Unable to Fetch Transaction details", HttpStatus.INTERNAL_SERVER_ERROR); // 500
			e.printStackTrace();
		}
		log.info("About to Exist fetch all method with Response");
		return resp;
	}

	@GetMapping(path = "/gettrxn/getbyid/{customerid}")
	public ResponseEntity<?> getTransactionsById(@PathVariable("customerid") int customerId) {
		log.info("Entered into method to fetch transactions details by Id");
		ResponseEntity<?> resp = null;
		try {
			log.info("About to call fetch transaction details service");

			Customer customer = CustomerRepo.findById(customerId).get();
			List<Transactions> list = TransactionRepo.findByCustomer(customer);
			if (list != null && !list.isEmpty()) {
				log.info("Data is not empty=> " + list.size());
//                list.sort((s1,s2)->s1.getReq_dt().compareTo(s2.getReq_dt()));
				/*
				 * JDK 1.8 list = list.stream()
				 * .sorted((s1,s2)->s1.getName().compareTo(s2.getName()))
				 * .collect(Collectors.toList());
				 */
				resp = new ResponseEntity<List<Transactions>>(list, HttpStatus.OK);
			} else {
				log.info("No transaction history exist! : size " + list.size());

				resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				resp = new ResponseEntity<String>("No Transaction History Found", HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Unable to fetch transaction history : problem is :" + e.getMessage());

			resp = new ResponseEntity<String>("Unable to Fetch Transaction details", HttpStatus.INTERNAL_SERVER_ERROR); // 500
			e.printStackTrace();
		}
		log.info("About to Exist fetch method with Response");
		return resp;
	}

}
