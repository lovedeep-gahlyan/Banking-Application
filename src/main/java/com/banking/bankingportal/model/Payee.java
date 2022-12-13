package com.banking.bankingportal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;


import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Payee {
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Id
	private int payee_id;
	
	private String name;
	private long payee_account_no;
	private String email;
	private String phone;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getPayee_id() {
		return payee_id;
	}
	public void setPayee_id(int payee_id) {
		this.payee_id = payee_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPayee_account_no() {
		return payee_account_no;
	}
	public void setPayee_account_no(long payee_account_no) {
		this.payee_account_no = payee_account_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Payee [payee_id=" + payee_id + ", name=" + name + ", payee_account_no=" + payee_account_no + ", email="
				+ email + ", phone=" + phone + "]";
	}

	

}
