package com.icecream.IceCream.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account_detail")
public class AccountDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "gender")
	private String gender;

	@Column(name = "email")
	private String email;

	public AccountDetail() {
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id",referencedColumnName = "id",unique=true)
	@JsonIgnore
	private Account details;

	public AccountDetail(Long id, String fullname, String gender, String email, Account details) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.gender = gender;
		this.email = email;
		this.details = details;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Account getDetails() {
		return details;
	}
	public void setDetails(Account details) {
		this.details = details;
	}

}