package com.banking.bankingportal.model;

import java.sql.Date;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class User_offers {
	
	@Id
	@GeneratedValue
	private int offer_id;
	
	private String offer_desc;
	
	private Date start_date;
	private Date expiry_date;
	
	@Override
	public String toString() {
		return "User_offers [offer_id=" + offer_id + ", offer_desc=" + offer_desc + ", start_date=" + start_date
				+ ", expiry_date=" + expiry_date + ", customer=" + customer + "]";
	}

	@ManyToOne
	@JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;

	public int getOffer_id() {
		return offer_id;
	}

	public void setOffer_id(int offer_id) {
		this.offer_id = offer_id;
	}

	public String getOffer_desc() {
		return offer_desc;
	}

	public void setOffer_desc(String offer_desc) {
		this.offer_desc = offer_desc;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
