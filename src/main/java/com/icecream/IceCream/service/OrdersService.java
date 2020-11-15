package com.icecream.IceCream.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.icecream.IceCream.Interface.OrdersInterface;
import com.icecream.IceCream.dto.FormCreateOrdersDTO;
import com.icecream.IceCream.dto.FormUpdateOrdersDTO;
import com.icecream.IceCream.dto.OrdersDTO;
import com.icecream.IceCream.dto.OrdersDetailDTO;
import com.icecream.IceCream.exception.ApiRequestException;
//import com.icecream.IceCream.model.Account;
import com.icecream.IceCream.model.Orders;
import com.icecream.IceCream.model.OrdersDetail;
import com.icecream.IceCream.model.Product;
import com.icecream.IceCream.repository.AccountRepository;
import com.icecream.IceCream.repository.CatalogueRepository;
import com.icecream.IceCream.repository.OrdersDetailPageRepository;
import com.icecream.IceCream.repository.OrdersDetailRepository;
import com.icecream.IceCream.repository.OrdersPageRepository;
import com.icecream.IceCream.repository.OrdersRepository;
import com.icecream.IceCream.repository.ProductPageRepository;
import com.icecream.IceCream.repository.ProductRepository;
import com.icecream.IceCream.repository.StatusRepository;

@Service
public class OrdersService implements OrdersInterface {
	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductPageRepository productPageRepository;

	@Autowired
	CatalogueRepository catalogueRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	OrdersRepository ordersRepository;

	@Autowired
	OrdersDetailRepository ordersDetailRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	OrdersPageRepository ordersPageRepository;

	@Autowired
	OrdersDetailPageRepository ordersDetailPageRepository;

	@Override
	public OrdersDTO createOrders(FormCreateOrdersDTO formOrdersDTO) {
		// TODO Auto-generated method stub
		try {
			Orders orders;
			Product product;
			orders = ordersRepository.findOrdersByAccountId(formOrdersDTO.getAccountId());
			product = productRepository.findProductById(formOrdersDTO.getProductId());
			if (orders == null && product != null) {
				orders = new Orders();
				orders.setAccount(accountRepository.findAccountById(formOrdersDTO.getAccountId()));
				Orders ordersSave = ordersRepository.save(orders);
				saveOrdersDetail(formOrdersDTO, orders);
				return convertToOrdersDTO(ordersSave);
			} else if (product != null) {
				saveOrdersDetail(formOrdersDTO, orders);
				return convertToOrdersDTO(orders);
			}
			return null;

		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public OrdersDTO updateOrdersDetail(FormUpdateOrdersDTO formUpdateDTO) {
		// TODO Auto-generated method stub
		try {
			Orders orders;
			OrdersDetail odersDetail;
			orders = ordersRepository.findOrdersByAccountId(formUpdateDTO.getAccountId());
			if (orders != null) {
				odersDetail = ordersDetailRepository.findOrdersDetailById(formUpdateDTO.getId());
				odersDetail.setStatus(statusRepository.findStatusById(formUpdateDTO.getStatusId()));
				ordersDetailRepository.save(odersDetail);
				return convertToOrdersDTO(ordersRepository.findOrdersByAccountId(formUpdateDTO.getAccountId()));
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public Boolean deleteOrdersDetail(Long id) {
		// TODO Auto-generated method stub
		try {
			OrdersDetail ordersDetail;
			ordersDetail = ordersDetailRepository.findOrdersDetailById(id);
			if (ordersDetail != null) {
				ordersDetailRepository.deleteById(id);
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public Boolean deleteOrders(Long id) {
		// TODO Auto-generated method stub
		try {
			Orders orders;
			orders = ordersRepository.findOrdersById(id);
			if (orders != null) {
				ordersRepository.deleteById(id);
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public Page<OrdersDTO> getPageOrdersByAccountId(Long accountId, int page) {
		// TODO Auto-generated method stub
		try {
//check account
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			Account account;
//			account = accountRepository.findAccountById(accountId);
//			if(auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
//					&& account.getUsername().equals(auth.getName())){				
//				return convertToPageOrdersDTO(ordersPageRepository.findByAccountId(accountId, PageRequest.of(page, 5)));				
//			}
			return convertToPageOrdersDTO(ordersPageRepository.findByAccountId(accountId, PageRequest.of(page, 5)));		
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public Page<OrdersDTO> getPageOrdersAll(int page) {
		// TODO Auto-generated method stub
		try {
			return convertToPageOrdersDTO(ordersPageRepository.findAll(PageRequest.of(page, 5)));
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}
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

		List<OrdersDetail> listOrdersDetail;
		List<OrdersDetailDTO> listOrdersDetailDTO = new ArrayList<OrdersDetailDTO>();
		listOrdersDetail = ordersDetailRepository.findOrdersDetailByOrdersId(orders.getId());
		listOrdersDetail.forEach(e -> {
			OrdersDetailDTO ordersDetailDTO = new OrdersDetailDTO();
			ordersDetailDTO.setId(e.getId());
			ordersDetailDTO.setName(productRepository.findProductById(e.getProduct().getId()).getName());
			ordersDetailDTO.setNote(e.getNote());
			ordersDetailDTO.setPrice(productRepository.findProductById(e.getProduct().getId()).getPrice());
			ordersDetailDTO.setProductId(e.getProduct().getId());
			ordersDetailDTO.setStatusId(e.getStatus().getId());
			ordersDetailDTO.setCreated_date(e.getCreated_date());
			listOrdersDetailDTO.add(ordersDetailDTO);
		});
		ordersDTO.setOrdersDetail(listOrdersDetailDTO);
		return ordersDTO;
	}

	public void saveOrdersDetail(FormCreateOrdersDTO formOrdersDTO, Orders orders) {
		OrdersDetail ordersDetail;
		ordersDetail = new OrdersDetail();
		ordersDetail.setProduct(productRepository.findProductById(formOrdersDTO.getProductId()));
		ordersDetail.setStatus(statusRepository.findStatusById((long) 1));
		ordersDetail.setNote(formOrdersDTO.getNote());
		ordersDetail.setCreated_date(getDateCurrent());
		ordersDetail.setOrders(orders);
		ordersDetailRepository.save(ordersDetail);
	}

	public Date getDateCurrent() {
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		return date;
	}

	@Override
	public List<OrdersDTO> getAll() {
		// TODO Auto-generated method stub
		List<Orders> listOrders = ordersRepository.findAll();
		List<OrdersDTO> listOrdersDTO = new ArrayList<OrdersDTO>();
		for (int i = 0; i < listOrders.size(); i++) {
			listOrdersDTO.add(convertToOrdersDTO(listOrders.get(i)));
		}
		return listOrdersDTO;
	}

	@Override
	public OrdersDTO getOrdersByAccountId(Long id) {
		// TODO Auto-generated method stub
		try {
			return convertToOrdersDTO(ordersRepository.findOrdersByAccountId(id));
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}
	}

}
