package com.banking.bankingportal.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transactions {

	@Id
	private int transaction_id;
	
	
	private String account_num_sender;

	
	private int customer_id;
	private int account_num_reciever;
	private Date transaction_dt;
	private int transaction_amt;
	private int closing_balance;
	
//	@ManyToOne
//	@JoinColumn(name = "customer_id")
//	private Customer customer;
	
	
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getAccount_num_sender() {
		return account_num_sender;
	}
	public void setAccount_num_sender(String account_num_sender) {
		this.account_num_sender = account_num_sender;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getAccount_num_reciever() {
		return account_num_reciever;
	}
	public void setAccount_num_reciever(int account_num_reciever) {
		this.account_num_reciever = account_num_reciever;
	}
	public int getTransaction_amt() {
		return transaction_amt;
	}
	public void setTransaction_amt(int transaction_amt) {
		this.transaction_amt = transaction_amt;
	}
	public int getClosing_balance() {
		return closing_balance;
	}
	public void setClosing_balance(int closing_balance) {
		this.closing_balance = closing_balance;
	}
	public Date getTransaction_dt() {
		return transaction_dt;
	}
	public void setTransaction_dt(Date transaction_dt) {
		this.transaction_dt = transaction_dt;
	}
	
	
}
