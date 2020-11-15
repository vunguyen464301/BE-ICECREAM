package com.icecream.IceCream.Interface;

import java.util.List;

import org.springframework.data.domain.Page;

import com.icecream.IceCream.dto.FormCreateOrdersDTO;
import com.icecream.IceCream.dto.FormUpdateOrdersDTO;
import com.icecream.IceCream.dto.OrdersDTO;

public interface OrdersInterface {
	Page<OrdersDTO> getPageOrdersByAccountId(Long accountId, int page);

	Page<OrdersDTO> getPageOrdersAll(int page);

	OrdersDTO createOrders(FormCreateOrdersDTO formOrdersDTO);

	OrdersDTO updateOrdersDetail(FormUpdateOrdersDTO formUpdateDTO);

	Boolean deleteOrdersDetail(Long id);

	Boolean deleteOrders(Long id);

	List<OrdersDTO> getAll();

	OrdersDTO getOrdersByAccountId(Long id);
}
