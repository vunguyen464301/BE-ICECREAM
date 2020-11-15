package com.icecream.IceCream.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.icecream.IceCream.model.Orders;

public interface OrdersPageRepository extends PagingAndSortingRepository<Orders, Long> {
	@Query(value = " select * from orders where account_id = ?1 ", nativeQuery = true)
	Page<Orders> findByAccountId(Long id, Pageable pageable);

	@Query(value = " select * from orders ", nativeQuery = true)
	Page<Orders> findAll(Pageable pageable);
}
