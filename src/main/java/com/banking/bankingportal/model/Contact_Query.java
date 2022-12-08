package com.banking.bankingportal.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contact_Query {
	@Id
	private int query_id;
	
	private String query_desc;
	
	private String name;
	
	private String phone;
	
	private Date query_dt;
	
	private String email;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
}
