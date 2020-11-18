package com.icecream.IceCream.mappingData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.icecream.IceCream.dto.ProductFeedbackDTO;
import com.icecream.IceCream.model.ProductFeedback;

public class MappingProductFeedbackDTO {
	
	public MappingProductFeedbackDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Page<ProductFeedbackDTO> convertToProductFeedbackPageDTO(Page<ProductFeedback> productFeedbackPage) {
		List<ProductFeedback> listProductFeedback = new ArrayList<ProductFeedback>();
		listProductFeedback = productFeedbackPage.stream().map(e -> e).collect(Collectors.toList());
		List<ProductFeedbackDTO> listProductFeedbackDTO = new ArrayList<ProductFeedbackDTO>();
		for (int i = 0; i < listProductFeedback.size(); i++) {
			ProductFeedbackDTO productFeedbackDTO = new ProductFeedbackDTO();
			productFeedbackDTO.setId(listProductFeedback.get(i).getId());
			productFeedbackDTO.setProductId(listProductFeedback.get(i).getProduct().getId());
			productFeedbackDTO.setProductName(listProductFeedback.get(i).getProduct().getName());
			productFeedbackDTO.setAccountId(listProductFeedback.get(i).getAccount().getId());
			productFeedbackDTO.setAccountName(listProductFeedback.get(i).getAccount().getUsername());
			productFeedbackDTO.setContent(listProductFeedback.get(i).getContent());
			productFeedbackDTO.setCreated_date(listProductFeedback.get(i).getCreated_date());
			listProductFeedbackDTO.add(productFeedbackDTO);
		}
		Page<ProductFeedbackDTO> pageProductFeedbackDTO = new PageImpl<ProductFeedbackDTO>(listProductFeedbackDTO,
				productFeedbackPage.getPageable(), listProductFeedbackDTO.size());
		return pageProductFeedbackDTO;
	}

	public ProductFeedbackDTO convertToProductFeedbackDTO(ProductFeedback productFeedback) {
		ProductFeedbackDTO productFeedbackDTO = new ProductFeedbackDTO();
		productFeedbackDTO.setId(productFeedback.getId());
		productFeedbackDTO.setProductId(productFeedback.getProduct().getId());
		productFeedbackDTO.setProductName(productFeedback.getProduct().getName());
		productFeedbackDTO.setAccountId(productFeedback.getAccount().getId());
		productFeedbackDTO.setAccountName(productFeedback.getAccount().getUsername());
		productFeedbackDTO.setContent(productFeedback.getContent());
		productFeedbackDTO.setCreated_date(productFeedback.getCreated_date());
		return productFeedbackDTO;
	}
}
