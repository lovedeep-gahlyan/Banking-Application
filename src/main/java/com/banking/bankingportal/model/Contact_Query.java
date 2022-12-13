package com.banking.bankingportal.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contact_Query {
	@Id
	@GeneratedValue()
	private int query_id;
	
	private String query_desc;
	
	private String name;
	
	private String phone;
	
	private Date query_dt;
	
	private String email;
	
	@Override
	public String toString() {
		return "Contact_Query [query_id=" + query_id + ", query_desc=" + query_desc + ", name=" + name + ", phone="
				+ phone + ", query_dt=" + query_dt + ", email=" + email + " ]";
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getQuery_dt() {
		return query_dt;
	}

	public void setQuery_dt(Date query_dt) {
		this.query_dt = query_dt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	

}
