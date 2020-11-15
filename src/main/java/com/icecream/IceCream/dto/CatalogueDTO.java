package com.icecream.IceCream.dto;

import java.util.List;

public class CatalogueDTO {
	private Long id;
	private String name;
	private List<ProductDTO> listProduct;
	public CatalogueDTO() {
		
	}
	public CatalogueDTO(Long id, String name, List<ProductDTO> listProduct) {
		super();
		this.id = id;
		this.name = name;
		this.listProduct = listProduct;
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
	 * @return the listProduct
	 */
	public List<ProductDTO> getListProduct() {
		return listProduct;
	}
	/**
	 * @param listProduct the listProduct to set
	 */
	public void setListProduct(List<ProductDTO> listProduct) {
		this.listProduct = listProduct;
	}
	
}
