package com.icecream.IceCream.controller;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icecream.IceCream.dto.FormCreateProductFeedbackDTO;
import com.icecream.IceCream.dto.ProductFeedbackDTO;
import com.icecream.IceCream.service.ProductFeedbackService;

@Configuration
@EnableWebSecurity
@CrossOrigin
@RestController
@RequestMapping("/productFeedback")
public class ProductFeedbackController {	
	private final ProductFeedbackService productFeedbackService;
	public ProductFeedbackController(ProductFeedbackService productFeedbackService) {
		this.productFeedbackService = productFeedbackService;
	}
	
	@RequestMapping("/getAll")
	public List<ProductFeedbackDTO> getAllProduct() {
		return productFeedbackService.getAll();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ProductFeedbackDTO createProductFeedback(@RequestBody FormCreateProductFeedbackDTO formProductFeedbackDTO) {
		return productFeedbackService.createProductFeedback(formProductFeedbackDTO);
	}
	
	@RequestMapping(value = "/productId/")
	public Page<ProductFeedbackDTO> getFindPageByProductId(@RequestParam Long id,@RequestParam int page) {
		return productFeedbackService.getProductFeedbackByProductId(id,page);
	}
	
	@RequestMapping(value = "/accountId/")
	public Page<ProductFeedbackDTO> getFindPageByAccountId(@RequestParam Long id,@RequestParam int page) {
		return productFeedbackService.getProductFeedbackByAccountId(id,page);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public Boolean deleteProductFeedbackById(@PathVariable (value="id") Long id) {
		return productFeedbackService.deleteProductFeedbackById(id);
	}
	
}
