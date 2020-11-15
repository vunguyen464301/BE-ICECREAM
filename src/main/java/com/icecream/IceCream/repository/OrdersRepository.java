package com.icecream.IceCream.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.icecream.IceCream.model.Orders;

public interface OrdersRepository extends CrudRepository<Orders, Long> {
	public Orders findOrdersById(Long id);

	public Orders findOrdersByAccountId(Long id);

	public List<Orders> findAll();
}
