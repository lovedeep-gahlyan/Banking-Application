package com.banking.bankingportal.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Credit_req {
	
	@Id
	@GeneratedValue
	private int req_id;
	
	@ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
	
	@Override
	public String toString() {
		return "Credit_req [req_id=" + req_id + ", customer=" + customer + ", pan_num=" + pan_num + ", annual_salary="
				+ annual_salary + ", type_of_card=" + type_of_card + ", approve=" + approve + ", req_dt=" + req_dt
				+ "]";
	}

	private String pan_num;
	
	private int annual_salary;
	
	private String type_of_card;
	
	private boolean approve;
	
	private Date req_dt;

	public int getReq_id() {
		return req_id;
	}

	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPan_num() {
		return pan_num;
	}

	public void setPan_num(String pan_num) {
		this.pan_num = pan_num;
	}

	public int getAnnual_salary() {
		return annual_salary;
	}

	public void setAnnual_salary(int annual_salary) {
		this.annual_salary = annual_salary;
	}

	public String getType_of_card() {
		return type_of_card;
	}

	public void setType_of_card(String type_of_card) {
		this.type_of_card = type_of_card;
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
	
	
	
	

}
