package com.banking.bankingportal.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.TableGenerator;

@Entity
@SequenceGenerator(name = "trxn", initialValue = 10000, allocationSize = 0)
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trxn")
	private int transaction_id;
	private long account_num_sender;
	private long account_num_reciever;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "customer_id")
	private Customer customer;

	private Date transaction_dt;
	private int transaction_amt;
	private int closing_bal_sender;
	private int closing_bal_reciever;
	private String trxnDescription;

	public String getTrxnDescription() {
		return trxnDescription;
	}

	public void setTrxnDescription(String trxnDescription) {
		this.trxnDescription = trxnDescription;
	}

	public int getClosing_bal_sender() {
		return closing_bal_sender;
	}

	public void setClosing_bal_sender(int closing_bal_sender) {
		this.closing_bal_sender = closing_bal_sender;
	}

	public int getClosing_bal_reciever() {
		return closing_bal_reciever;
	}

	public void setClosing_bal_reciever(int closing_bal_reciever) {
		this.closing_bal_reciever = closing_bal_reciever;
	}

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

	public void setTransaction_amt(int transaction_amt) {
		this.transaction_amt = transaction_amt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Transactions [transaction_id=" + transaction_id + ", account_num_sender=" + account_num_sender
				+ ", account_num_reciever=" + account_num_reciever + ", customer_id=" + customer.getCustomer_id()
				+ ", transaction_dt=" + transaction_dt + ", transaction_amt=" + transaction_amt
				+ ", closing_bal_sender=" + closing_bal_sender + ", closing_bal_reciever=" + closing_bal_reciever
				+ ", trxnDescription=" + trxnDescription + "]";
	}

}