package com.icecream.IceCream.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.icecream.IceCream.Interface.ProductFeedbackInterface;
import com.icecream.IceCream.dto.FormCreateProductFeedbackDTO;
import com.icecream.IceCream.dto.ProductFeedbackDTO;
import com.icecream.IceCream.exception.ApiRequestException;
import com.icecream.IceCream.model.Account;
import com.icecream.IceCream.model.Product;
import com.icecream.IceCream.model.ProductFeedback;
import com.icecream.IceCream.repository.AccountRepository;
import com.icecream.IceCream.repository.CatalogueRepository;
import com.icecream.IceCream.repository.OrdersDetailPageRepository;
import com.icecream.IceCream.repository.OrdersDetailRepository;
import com.icecream.IceCream.repository.OrdersPageRepository;
import com.icecream.IceCream.repository.OrdersRepository;
import com.icecream.IceCream.repository.ProductFeedbackPageRepository;
import com.icecream.IceCream.repository.ProductFeedbackRepository;
import com.icecream.IceCream.repository.ProductPageRepository;
import com.icecream.IceCream.repository.ProductRepository;
import com.icecream.IceCream.repository.StatusRepository;

@Service
public class ProductFeedbackService implements ProductFeedbackInterface {
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

	@Autowired
	ProductFeedbackRepository productFeedbackRepository;

	@Autowired
	ProductFeedbackPageRepository productFeedbackPageRepository;

	@Override
	public List<ProductFeedbackDTO> getAll() {
		// TODO Auto-generated method stub
		List<ProductFeedbackDTO> listProductFeedbackDTO = new ArrayList<ProductFeedbackDTO>();
		List<ProductFeedback> listProductFeedback;
		listProductFeedback = productFeedbackRepository.findAll();
		for (int i = 0; i < listProductFeedback.size(); i++) {
			listProductFeedbackDTO.add(convertToProductFeedbackDTO(listProductFeedback.get(i)));
		}
		return listProductFeedbackDTO;
	}

	@Override
	public List<ProductFeedbackDTO> getProductFeedbackByProductId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductFeedbackDTO> getProductFeedbackByProductId(Long id, int page) {
		// TODO Auto-generated method stub
		try {
			Product product;
			product = productRepository.findProductById(id);
			if (product != null) {
				return convertToProductFeedbackPageDTO(
						productFeedbackPageRepository.findByProductId(id, PageRequest.of(page, 5)));
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public Page<ProductFeedbackDTO> getProductFeedbackByAccountId(Long id, int page) {
		// TODO Auto-generated method stub
		try {
			Account account;
			account = accountRepository.findAccountById(id);
			if (account != null) {
				return convertToProductFeedbackPageDTO(
						productFeedbackPageRepository.findByAccountId(id, PageRequest.of(page, 5)));
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public ProductFeedbackDTO createProductFeedback(FormCreateProductFeedbackDTO formCreateProductFeedbackDTO) {
		// TODO Auto-generated method stub
		try {
			Product product;
			Account account;
			ProductFeedback productFeedback;
			product = productRepository.findProductById(formCreateProductFeedbackDTO.getProductId());
			account = accountRepository.findAccountById(formCreateProductFeedbackDTO.getAccountId());
			if (product != null && account != null) {
				productFeedback = new ProductFeedback();
				productFeedback.setProduct(product);
				productFeedback.setAccount(account);
				productFeedback.setContent(formCreateProductFeedbackDTO.getContent());
				productFeedback.setCreated_date(getDateCurrent());
				return convertToProductFeedbackDTO(productFeedbackRepository.save(productFeedback));
			}
			return null;

		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public Boolean deleteProductFeedbackById(Long id) {
		// TODO Auto-generated method stub
		try {
			ProductFeedback productFeedback;
			productFeedback = productFeedbackRepository.findProductFeedbackById(id);
			if (productFeedback != null) {
				productFeedbackRepository.deleteById(id);
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}
	}

	public Page<ProductFeedbackDTO> convertToProductFeedbackPageDTO(Page<ProductFeedback> productFeedbackPage) {
		List<ProductFeedback> listProductFeedback = new ArrayList<ProductFeedback>();
		listProductFeedback = productFeedbackPage.stream().map(e -> e).collect(Collectors.toList());
		List<ProductFeedbackDTO> listProductFeedbackDTO = new ArrayList<ProductFeedbackDTO>();
		for (int i = 0; i < listProductFeedback.size(); i++) {
			ProductFeedbackDTO productFeedbackDTO = new ProductFeedbackDTO();
			productFeedbackDTO.setId(listProductFeedback.get(i).getId());
			productFeedbackDTO.setProductId(listProductFeedback.get(i).getProduct().getId());
			productFeedbackDTO.setProductName(listProductFeedback.get(i).getProduct().getName());
			productFeedbackDTO.setAccountId(listProductFeedback.get(i).getAccount().getId());
			productFeedbackDTO.setAccountName(listProductFeedback.get(i).getAccount().getUsername());
			productFeedbackDTO.setContent(listProductFeedback.get(i).getContent());
			productFeedbackDTO.setCreated_date(listProductFeedback.get(i).getCreated_date());
			listProductFeedbackDTO.add(productFeedbackDTO);
		}
		Page<ProductFeedbackDTO> pageProductFeedbackDTO = new PageImpl<ProductFeedbackDTO>(listProductFeedbackDTO,
				productFeedbackPage.getPageable(), listProductFeedbackDTO.size());
		return pageProductFeedbackDTO;
	}

	public ProductFeedbackDTO convertToProductFeedbackDTO(ProductFeedback productFeedback) {
		ProductFeedbackDTO productFeedbackDTO = new ProductFeedbackDTO();
		productFeedbackDTO.setId(productFeedback.getId());
		productFeedbackDTO.setProductId(productFeedback.getProduct().getId());
		productFeedbackDTO.setProductName(productFeedback.getProduct().getName());
		productFeedbackDTO.setAccountId(productFeedback.getAccount().getId());
		productFeedbackDTO.setAccountName(productFeedback.getAccount().getUsername());
		productFeedbackDTO.setContent(productFeedback.getContent());
		productFeedbackDTO.setCreated_date(productFeedback.getCreated_date());
		return productFeedbackDTO;
	}

	public Date getDateCurrent() {
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		return date;
	}

}
