package com.icecream.IceCream.dto;

public class FormUpdateOrdersDTO {
	private Long id;
	private Long accountId;
	private Long statusId;
	public FormUpdateOrdersDTO() {
		super();
	}
	public FormUpdateOrdersDTO(Long id, Long accountId, Long statusId) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.statusId = statusId;
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
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the statusId
	 */
	public Long getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	
	
}
