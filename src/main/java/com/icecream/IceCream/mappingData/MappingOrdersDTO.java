package com.icecream.IceCream.mappingData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.icecream.IceCream.dto.OrdersDTO;
import com.icecream.IceCream.dto.OrdersDetailDTO;
import com.icecream.IceCream.model.Orders;
import com.icecream.IceCream.model.OrdersDetail;
import com.icecream.IceCream.repository.OrdersDetailPageRepository;
import com.icecream.IceCream.repository.OrdersDetailRepository;
import com.icecream.IceCream.repository.ProductRepository;

public class MappingOrdersDTO {
	@Autowired
	ProductRepository productRepository;

	@Autowired
	OrdersDetailRepository ordersDetailRepository;

	@Autowired
	OrdersDetailPageRepository ordersDetailPageRepository;

	public MappingOrdersDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Page<OrdersDTO> convertToPageOrdersDTO(Page<Orders> pageOrders) {
		List<Orders> listOrders;
		List<OrdersDTO> listOrdersDTO = new ArrayList<OrdersDTO>();
		listOrders = pageOrders.stream().map(e -> e).collect(Collectors.toList());
		for (int i = 0; i < listOrders.size(); i++) {
			OrdersDTO ordersDTO = new OrdersDTO();
			ordersDTO.setId(listOrders.get(i).getId());
			ordersDTO.setAccount_id(listOrders.get(i).getAccount().getId());
			ordersDTO.setUsername(listOrders.get(i).getAccount().getUsername());
			List<OrdersDetailDTO> listOrdersDetailDTO = new ArrayList<OrdersDetailDTO>();
			for (int j = 0; j < listOrders.get(i).getOrdersDetail().size(); j++) {
				OrdersDetailDTO ordersDetailDTO = new OrdersDetailDTO();
				ordersDetailDTO.setId(listOrders.get(i).getOrdersDetail().get(j).getId());
				ordersDetailDTO.setName(listOrders.get(i).getOrdersDetail().get(j).getProduct().getName());
				ordersDetailDTO.setNote(listOrders.get(i).getOrdersDetail().get(j).getNote());
				ordersDetailDTO.setPrice(listOrders.get(i).getOrdersDetail().get(j).getProduct().getPrice());
				ordersDetailDTO.setProductId(listOrders.get(i).getOrdersDetail().get(j).getProduct().getId());
				ordersDetailDTO.setStatusId(listOrders.get(i).getOrdersDetail().get(j).getStatus().getId());
				ordersDetailDTO.setCreated_date(listOrders.get(i).getOrdersDetail().get(j).getCreated_date());
				listOrdersDetailDTO.add(ordersDetailDTO);
				ordersDTO.setOrdersDetail(listOrdersDetailDTO);
			}
			listOrdersDTO.add(ordersDTO);
		}
		Page<OrdersDTO> pageOrdersDTO = new PageImpl<OrdersDTO>(listOrdersDTO, pageOrders.getPageable(),
				listOrdersDTO.size());
		return pageOrdersDTO;
	}

	public OrdersDTO convertToOrdersDTO(Orders orders) {
		OrdersDTO ordersDTO;
		ordersDTO = new OrdersDTO();
		ordersDTO.setId(orders.getId());
		ordersDTO.setAccount_id(orders.getAccount().getId());
		ordersDTO.setUsername(orders.getAccount().getUsername());

		List<OrdersDetail> listOrdersDetail = new ArrayList<OrdersDetail>();
		List<OrdersDetailDTO> listOrdersDetailDTO = new ArrayList<OrdersDetailDTO>();
//		listOrdersDetail = ordersDetailRepository.findOrdersDetailByOrdersId((long)1);
		listOrdersDetail=orders.getOrdersDetail();
		listOrdersDetail.forEach(e -> {
			OrdersDetailDTO ordersDetailDTO = new OrdersDetailDTO();
			ordersDetailDTO.setId(e.getId());
			ordersDetailDTO.setName(e.getProduct().getName());
			ordersDetailDTO.setNote(e.getNote());
			ordersDetailDTO.setPrice(e.getProduct().getPrice());
			ordersDetailDTO.setProductId(e.getProduct().getId());
			ordersDetailDTO.setStatusId(e.getStatus().getId());
			ordersDetailDTO.setCreated_date(e.getCreated_date());
			listOrdersDetailDTO.add(ordersDetailDTO);
		});

		ordersDTO.setOrdersDetail(listOrdersDetailDTO);
		return ordersDTO;
	}
}
