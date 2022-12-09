package com.banking.bankingportal.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Loan_req {

	@Id
	private int req_id;
	private String loan_type;
	private int loan_amt;
	private int time;
	private boolean approve;
	private Date req_dt;



	@Override
	public String toString() {
		return "Loan_req [req_id=" + req_id + ", loan_type=" + loan_type + ", loan_amt=" + loan_amt + ", time=" + time
				+ ", approve=" + approve + ", req_dt=" + req_dt + ", customer=" + customer + "]";
	}

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public int getReq_id() {
		return req_id;
	}

	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}

	public String getLoan_type() {
		return loan_type;
	}

	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
	}

	public int getLoan_amt() {
		return loan_amt;
	}

	public void setLoan_amt(int loan_amt) {
		this.loan_amt = loan_amt;
	}

	public Date getReq_dt() {
		return req_dt;
	}

	public void setReq_dt(Date req_dt) {
		this.req_dt = req_dt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	

}
