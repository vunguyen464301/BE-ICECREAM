package com.icecream.IceCream.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.icecream.IceCream.dto.FormCreateProductFeedbackDTO;
import com.icecream.IceCream.dto.ProductFeedbackDTO;
import com.icecream.IceCream.exception.ApiRequestException;
import com.icecream.IceCream.mappingData.MappingProductFeedbackDTO;
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
import com.icecream.IceCream.service.ProductFeedbackService;

@Service
public class ProductFeedbackServiceImpl implements ProductFeedbackService {
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

	private static MappingProductFeedbackDTO mappingProductFeedbackDTO = new MappingProductFeedbackDTO();
	
	@Override
	public List<ProductFeedbackDTO> getAll() {
		// TODO Auto-generated method stub
		List<ProductFeedbackDTO> listProductFeedbackDTO = new ArrayList<ProductFeedbackDTO>();
		List<ProductFeedback> listProductFeedback;
		listProductFeedback = productFeedbackRepository.findAll();
		for (int i = 0; i < listProductFeedback.size(); i++) {
			listProductFeedbackDTO.add(mappingProductFeedbackDTO.convertToProductFeedbackDTO(listProductFeedback.get(i)));
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
				return mappingProductFeedbackDTO.convertToProductFeedbackPageDTO(
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
				return mappingProductFeedbackDTO.convertToProductFeedbackPageDTO(
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
				return mappingProductFeedbackDTO.convertToProductFeedbackDTO(productFeedbackRepository.save(productFeedback));
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

	

	public Date getDateCurrent() {
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		return date;
	}

}
