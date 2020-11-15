package com.icecream.IceCream.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.icecream.IceCream.model.OrdersDetail;

public interface OrdersDetailPageRepository extends PagingAndSortingRepository<OrdersDetail, Long> {
	@Query(value = "select * " + "from product a , status b , orders c, orders_detail d, account e "
			+ "where a.id = d.product_id and c.id = d.orders_id and b.id = d.status_id and e.id = c.account_id and d.orders_id = ?1 ", nativeQuery = true)
	Page<OrdersDetail> findByOrderId(Long id, Pageable pageable);
}
