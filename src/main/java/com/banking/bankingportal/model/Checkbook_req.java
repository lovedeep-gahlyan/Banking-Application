package com.banking.bankingportal.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Checkbook_req {
	
	@Id
	@GeneratedValue
	private int req_id ;
	private int account_no;
	private int no_of_leaf;
	
	private boolean approve;
	private Date req_dt;
	
	@OneToOne
	@JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;

	public int getReq_id() {
		return req_id;
	}

	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}

	public int getAccount_no() {
		return account_no;
	}

	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}

	public int getNo_of_leaf() {
		return no_of_leaf;
	}

	public void setNo_of_leaf(int no_of_leaf) {
		this.no_of_leaf = no_of_leaf;
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
		return "Checkbook_req [req_id=" + req_id + ", account_no=" + account_no + ", no_of_leaf=" + no_of_leaf
				+ ", approve=" + approve + ", req_dt=" + req_dt + ", customer=" + customer + "]";
	}
	
	
	
}
