package com.icecream.IceCream.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "status")
public class Status{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Account> account;
	
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Product> product;
	
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<OrdersDetail> ordersDetail;
	
	public Status() {
	}
	
	public Status(Long id) {
		super();
		this.id = id;
	}

	public Status(Long id, String name, List<Account> account, List<Product> product, List<OrdersDetail> ordersDetail) {
		super();
		this.id = id;
		this.name = name;
		this.account = account;
		this.product = product;
		this.ordersDetail = ordersDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public List<OrdersDetail> getOrdersDetail() {
		return ordersDetail;
	}

	public void setOrdersDetail(List<OrdersDetail> ordersDetail) {
		this.ordersDetail = ordersDetail;
	}

	
}
