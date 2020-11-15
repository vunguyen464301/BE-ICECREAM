package com.icecream.IceCream.dto;

import java.sql.Date;

public class OrdersDetailDTO {
	private Long id;
	private String name;
	private Long price;
	private Long productId;
	private Long statusId;
	private String note;
	private Date created_date;
	public OrdersDetailDTO() {
		super();
	}
	public OrdersDetailDTO(Long id, String name, Long price, Long productId, Long statusId, String note,
			Date created_date) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.productId = productId;
		this.statusId = statusId;
		this.note = note;
		this.created_date = created_date;
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
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}


	
}
