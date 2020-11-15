package com.icecream.IceCream.dto;

import java.util.List;

public class OrdersDTO {
	private Long id;
	private Long account_id;
	private String username;
	private List<OrdersDetailDTO> ordersDetail;
	public OrdersDTO() {
		super();
	}
	public OrdersDTO(Long id, Long account_id, String username, List<OrdersDetailDTO> ordersDetail) {
		super();
		this.id = id;
		this.account_id = account_id;
		this.username = username;
		this.ordersDetail = ordersDetail;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<OrdersDetailDTO> getOrdersDetail() {
		return ordersDetail;
	}
	public void setOrdersDetail(List<OrdersDetailDTO> ordersDetail) {
		this.ordersDetail = ordersDetail;
	}
	
	
}
