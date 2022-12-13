package com.banking.bankingportal.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Customer {
	
	@Id
	private int customer_id;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private int age;
	
	private String gender;
	
	private String address;
	
	private String martial_status;
	
	private String username;
	
	private String password;
	
	private String unique_identity;
	
	private long unique_identity_number;
	
	private String role;
	
	private String employement_status;

	
	@OneToMany(mappedBy="customer",fetch=FetchType.EAGER)
	private Set<Payee> payee;
	
	@JsonManagedReference
	@OneToMany(mappedBy="customer",fetch=FetchType.EAGER)
	private Set<Query> query;
	
	@OneToMany(mappedBy="customer",fetch=FetchType.EAGER)
	private Set<Transactions> transactions;
	
	@OneToMany(mappedBy="customer",fetch=FetchType.EAGER)
	private Set<Loan_req> loan_req;
	
	@OneToMany(mappedBy="customer",fetch=FetchType.EAGER)
	private Set<Credit_req> credit_req;
	
	@OneToOne(mappedBy="customer",fetch=FetchType.EAGER)
	private Account_details account_details;
	
	@OneToOne(mappedBy="customer",fetch=FetchType.EAGER)
	private Checkbook_req checkbook_req;
	
	@OneToMany(mappedBy="customer",fetch=FetchType.EAGER)
	private Set<User_offers> user_offers;
	
	
	
	public int getCustomer_id() {
		return customer_id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getAddress() {
		return address;
	}

	public String getMartial_status() {
		return martial_status;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUnique_identity() {
		return unique_identity;
	}

	public long getUnique_identity_number() {
		return unique_identity_number;
	}

	public String getRole() {
		return role;
	}

	public String getEmployement_status() {
		return employement_status;
	}

	

	public Set<Payee> getPayee() {
		return payee;
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", age=" + age + ", gender=" + gender + ", address=" + address + ", martial_status=" + martial_status
				+ ", username=" + username + ", password=" + password + ", unique_identity=" + unique_identity
				+ ", unique_identity_number=" + unique_identity_number + ", role=" + role + ", employement_status="
				+ employement_status + ", payee=" + payee + ", query=" + query + ", contact_query=" 
				+ ", transactions=" + transactions + ", loan_req=" + loan_req + ", credit_req=" + credit_req
				+ ", account_details=" + account_details + ", checkbook_req=" + checkbook_req + ", user_offers="
				+ user_offers + ", authority=" + "]";
	}

	public void setPayee(Set<Payee> payee) {
		this.payee = payee;
	}

	public Set<Query> getQuery() {
		return query;
	}

	public void setQuery(Set<Query> query) {
		this.query = query;
	}

	
	public Set<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transactions> transactions) {
		this.transactions = transactions;
	}

	public Set<Loan_req> getLoan_req() {
		return loan_req;
	}

	public void setLoan_req(Set<Loan_req> loan_req) {
		this.loan_req = loan_req;
	}

	public Set<Credit_req> getCredit_req() {
		return credit_req;
	}

	public void setCredit_req(Set<Credit_req> credit_req) {
		this.credit_req = credit_req;
	}

	public Account_details getAccount_details() {
		return account_details;
	}

	public void setAccount_details(Account_details account_details) {
		this.account_details = account_details;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setMartial_status(String martial_status) {
		this.martial_status = martial_status;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUnique_identity(String unique_identity) {
		this.unique_identity = unique_identity;
	}

	public void setUnique_identity_number(long unique_identity_number) {
		this.unique_identity_number = unique_identity_number;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setEmployement_status(String employement_status) {
		this.employement_status = employement_status;
	}


}
