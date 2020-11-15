package com.icecream.IceCream.dto;

import java.util.List;

public class FormCreateOrdersDTO {
	private Long accountId;
	private Long productId;
	private String note;
	public FormCreateOrdersDTO() {
		super();
	}
	public FormCreateOrdersDTO(Long accountId, List<Long> productId) {
	}
	public FormCreateOrdersDTO(Long accountId, Long productId, String note) {
		super();
		this.accountId = accountId;
		this.productId = productId;
		this.note = note;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
