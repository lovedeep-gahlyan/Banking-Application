package com.banking.bankingportal.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Offers {
	
	@Id
	@GeneratedValue
	private int offer_id;
	private String offer_desc;
	
	private Date start_date;
	private Date expiry_date;
	
	
	
	
	public  int getOffer_id() {
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
	@Override
	public String toString() {
		return "Offers [offer_id=" + offer_id + ", offer_desc=" + offer_desc + ", start_date=" + start_date
				+ ", expiry_date=" + expiry_date + "]";
	}
	
	
	

}
