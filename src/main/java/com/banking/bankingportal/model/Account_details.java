package com.banking.bankingportal.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Account_details {
	@OneToOne(cascade = {CascadeType.ALL})
	@JsonBackReference
	@JoinColumn(name="customer_id")
	private Customer customer;
	@Id
	private long account_no;
	
	private String account_type;
	private String branch_address;
	private Date create_dt;
	private int account_balance;
	@Override
	public String toString() {
		return "Account_details [customer=" + customer + ", account_no=" + account_no + ", account_type=" + account_type
				+ ", branch_address=" + branch_address + ", create_dt=" + create_dt + ", account_balance="
				+ account_balance + "]";
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public long getAccount_no() {
		return account_no;
	}
	public void setAccount_no(long account_no) {
		this.account_no = account_no;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getBranch_address() {
		return branch_address;
	}
	public void setBranch_address(String branch_address) {
		this.branch_address = branch_address;
	}
	public Date getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}
	public int getAccount_balance() {
		return account_balance;
	}
	public void setAccount_balance(int account_balance) {
		this.account_balance = account_balance;
	}
	



	

}