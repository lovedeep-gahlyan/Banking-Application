package com.banking.bankingportal.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transactions {

	@Id
	private int transaction_id;

	private long account_num_sender;

	private long account_num_reciever;
	private Date transaction_dt;
	private int transaction_amt;
	private int closing_balance;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public long getAccount_num_sender() {
		return account_num_sender;
	}

	public void setAccount_num_sender(long account_num_sender) {
		this.account_num_sender = account_num_sender;
	}


	public long getAccount_num_reciever() {
		return account_num_reciever;
	}

	public void setAccount_num_reciever(long account_num_reciever) {
		this.account_num_reciever = account_num_reciever;
	}

	public Date getTransaction_dt() {
		return transaction_dt;
	}

	public void setTransaction_dt(Date transaction_dt) {
		this.transaction_dt = transaction_dt;
	}

	public int getTransaction_amt() {
		return transaction_amt;
	}


	@Override
	public String toString() {
		return "Transactions [transaction_id=" + transaction_id + ", account_num_sender=" + account_num_sender
				+ ", account_num_reciever=" + account_num_reciever + ", transaction_dt=" + transaction_dt
				+ ", transaction_amt=" + transaction_amt + ", closing_balance=" + closing_balance + ", customer="
				+ customer + "]";
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

}