package com.icecream.IceCream.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.icecream.IceCream.model.ProductFeedback;

public interface ProductFeedbackPageRepository extends PagingAndSortingRepository<ProductFeedback, Long> {
	// right
	@Query(value = "select * " + "from productfeedback a, product b "
			+ "where a.product_id = b.id and a.product_id = ?1 ", nativeQuery = true)
	Page<ProductFeedback> findByProductId(Long id, Pageable pageable);

	// wrong
	@Query(value = " select * from productfeedback where account_id = ?1 ", nativeQuery = true)
	Page<ProductFeedback> findByAccountId(Long id, Pageable pageable);
}
