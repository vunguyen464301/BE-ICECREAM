package com.icecream.IceCream.Interface;

import java.util.List;

import org.springframework.data.domain.Page;

import com.icecream.IceCream.dto.FormCreateProductFeedbackDTO;
import com.icecream.IceCream.dto.ProductFeedbackDTO;

public interface ProductFeedbackInterface {
	public List<ProductFeedbackDTO> getAll();
	
	public List<ProductFeedbackDTO> getProductFeedbackByProductId(Long id);
	
	public ProductFeedbackDTO createProductFeedback(FormCreateProductFeedbackDTO formCreateProductFeedbackDTO);
	
	public Boolean deleteProductFeedbackById(Long id);
	
	public Page<ProductFeedbackDTO> getProductFeedbackByProductId(Long id, int page);
	public Page<ProductFeedbackDTO> getProductFeedbackByAccountId(Long id, int page);
	

}
