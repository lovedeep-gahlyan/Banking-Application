package com.banking.bankingportal.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Query {
		
	@Id
	private int query_id;
	
	private String query_desc;
	
	private int customer_id;
	
	private Date query_dt;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	public int getQuery_id() {
		return query_id;
	}

	public void setQuery_id(int query_id) {
		this.query_id = query_id;
	}

	public String getQuery_desc() {
		return query_desc;
	}

	public void setQuery_desc(String query_desc) {
		this.query_desc = query_desc;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public Date getQuery_dt() {
		return query_dt;
	}

	public void setQuery_dt(Date query_dt) {
		this.query_dt = query_dt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
