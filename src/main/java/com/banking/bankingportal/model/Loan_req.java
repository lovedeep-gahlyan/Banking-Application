package com.banking.bankingportal.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "loan", initialValue = 20000, allocationSize = 0)
public class Loan_req {

	@Id
	@GeneratedValue
	private int req_id;

	private String loan_type;
	private int loan_amt;
	private int loan_term;
	private boolean approve;
	private Date req_dt;
	private String loan_desc;
	private int loan_rate;

	public int getLoan_rate() {
		return loan_rate;
	}

	public void setLoan_rate(int loan_rate) {
		this.loan_rate = loan_rate;
	}

	public String getLoan_desc() {
		return loan_desc;
	}

	public void setLoan_desc(String loan_desc) {
		this.loan_desc = loan_desc;
	}

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference
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

	public int getLoan_amt() {
		return loan_amt;
	}

	public void setLoan_amt(int loan_amt) {
		this.loan_amt = loan_amt;
	}

	public int getLoan_term() {
		return loan_term;
	}

	public void setLoan_term(int loan_term) {
		this.loan_term = loan_term;
	}

	public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
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

	@Override
	public String toString() {
		return "Loan_req [req_id=" + req_id + ", loan_type=" + loan_type + ", loan_amt=" + loan_amt + ", loan_term="
				+ loan_term + ", approve=" + approve + ", req_dt=" + req_dt + ", customer_id="
				+ customer.getCustomer_id() + "]";
	}

}
