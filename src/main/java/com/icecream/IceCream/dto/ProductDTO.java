package com.icecream.IceCream.dto;

import java.sql.Date;

public class ProductDTO {
	
	private Long id;
	private String name;
	private String image;
	private String content;
	private Long price;
	private Long catalogueId;
	private Long statusId;
	private Date created_date;
	
	public ProductDTO() {
		
	}
	
	public ProductDTO(Long id, String name, String image, String content, Long price, Long catalogueId, Long statusId,
			Date created_date) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.content = content;
		this.price = price;
		this.catalogueId = catalogueId;
		this.statusId = statusId;
		this.created_date = created_date;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}
	/**
	 * @return the catalogueId
	 */
	public Long getCatalogueId() {
		return catalogueId;
	}
	/**
	 * @param catalogueId the catalogueId to set
	 */
	public void setCatalogueId(Long catalogueId) {
		this.catalogueId = catalogueId;
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
	
}
