package com.banking.bankingportal.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	private int customer_id;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String age;
	
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
	
	@OneToMany(mappedBy="customer",fetch=FetchType.EAGER)
	private Set<Query> query;
	
	@OneToMany(mappedBy="customer",fetch=FetchType.EAGER)
	private Set<Contact_Query> contact_query;
	
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

	public String getAge() {
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

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", age=" + age + ", gender=" + gender + ", address=" + address + ", martial_status=" + martial_status
				+ ", username=" + username + ", password=" + password + ", unique_identity=" + unique_identity
				+ ", unique_identity_number=" + unique_identity_number + ", role=" + role + ", employement_status="
				+ employement_status + "]";
	}
	
	

}
