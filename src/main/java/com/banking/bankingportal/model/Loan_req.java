package com.banking.bankingportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Loan_req {

	@Id
	private int req_id;

	private int customer_id;
	private String loan_type;
	private int time;
	private boolean approve;
	private String req_dt;

//	@ManyToOne
//	@JoinColumn(name = "customer_id")
//	private Customer customer;

	public int getReq_id() {
		return req_id;
	}

	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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

	public String getReq_dt() {
		return req_dt;
	}

	public void setReq_dt(String req_dt) {
		this.req_dt = req_dt;
	}

}
