package com.icecream.IceCream.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date created_date;

	@OneToOne(mappedBy = "details",cascade = CascadeType.ALL)
	private AccountDetail details;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "account_role", joinColumns = { @JoinColumn(name = "account_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> role;
	

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Orders> orders;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ProductFeedback> productFeedback;
	
	public Account() {
	}

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Account(Long id, String username, String password, Date created_date, AccountDetail details, Status status,
			Set<Role> role, List<Orders> orders, List<ProductFeedback> productFeedback) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.created_date = created_date;
		this.details = details;
		this.status = status;
		this.role = role;
		this.orders = orders;
		this.productFeedback = productFeedback;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the created_date
	 */
	public Date getCreated_date() {
		return created_date;
	}

	/**
	 * @param created_date the created_date to set
	 */
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	/**
	 * @return the details
	 */
	public AccountDetail getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(AccountDetail details) {
		this.details = details;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the role
	 */
	public Set<Role> getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Set<Role> role) {
		this.role = role;
	}

	/**
	 * @return the orders
	 */
	public List<Orders> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	/**
	 * @return the productFeedback
	 */
	public List<ProductFeedback> getProductFeedback() {
		return productFeedback;
	}

	/**
	 * @param productFeedback the productFeedback to set
	 */
	public void setProductFeedback(List<ProductFeedback> productFeedback) {
		this.productFeedback = productFeedback;
	}
	
}