package com.icecream.IceCream.controller;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icecream.IceCream.dto.FormCreateOrdersDTO;
import com.icecream.IceCream.dto.FormUpdateOrdersDTO;
import com.icecream.IceCream.dto.OrdersDTO;
import com.icecream.IceCream.service.OrdersService;

@Configuration
@EnableWebSecurity
@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrdersController {
	
	private final OrdersService ordersService;
	
	public OrdersController(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	@RequestMapping("/getAll/")
	public List<OrdersDTO> getAll() {
		return ordersService.getAll();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public OrdersDTO createOrders(@RequestBody FormCreateOrdersDTO formOrdersDTO) {
		return ordersService.createOrders(formOrdersDTO);
	}
	
	@RequestMapping(value = "/details/", method = RequestMethod.PUT)
	public ResponseEntity<OrdersDTO> updateOrdersDetail(@RequestBody FormUpdateOrdersDTO formOrdersDTO) {
		return ResponseEntity.ok().body(ordersService.updateOrdersDetail(formOrdersDTO));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean deleteOrders(@PathVariable (value="id") Long id) {
		return ordersService.deleteOrders(id);
	}
	@RequestMapping(value = "/details/{id}", method = RequestMethod.DELETE)
	public Boolean deleteOrdersDetail(@PathVariable (value="id") Long id) {
		return ordersService.deleteOrdersDetail(id);
	}
	
	@RequestMapping("/find/{id}")
	public OrdersDTO getPageOrdersByAccountId(@PathVariable(value="id") Long  id) {
		return ordersService.getOrdersByAccountId(id);
	}
	
	@RequestMapping("/findAll/")
	public Page<OrdersDTO> getPageOrdersAll(@RequestParam int page) {
		return ordersService.getPageOrdersAll(page);
	}
	
	
}
