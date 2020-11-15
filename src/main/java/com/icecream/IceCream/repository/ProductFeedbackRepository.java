package com.icecream.IceCream.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.icecream.IceCream.model.ProductFeedback;

public interface ProductFeedbackRepository extends CrudRepository<ProductFeedback, Long> {
	public List<ProductFeedback> findAll();

	public List<ProductFeedback> findProductFeedbackByProductId(Long id);

	public List<ProductFeedback> findProductFeedbackByAccountId(Long id);

	public ProductFeedback findProductFeedbackById(Long id);
}
