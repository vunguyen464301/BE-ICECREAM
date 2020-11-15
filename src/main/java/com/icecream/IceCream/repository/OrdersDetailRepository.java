package com.icecream.IceCream.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.icecream.IceCream.model.OrdersDetail;

public interface OrdersDetailRepository extends CrudRepository<OrdersDetail, Long> {
	public OrdersDetail findOrdersDetailById(Long id);

	public List<OrdersDetail> findOrdersDetailByOrdersId(Long id);

	@Query(value = " select * from orders_detail a , orders b where a.orders_id=b.id", nativeQuery = true)
	Page<OrdersDetail> findOrdersDetailAll(Pageable pageable);

	public List<OrdersDetail> findAll();
}
