package com.icecream.IceCream.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.icecream.IceCream.dto.FormCreateProductDTO;
import com.icecream.IceCream.dto.FormUpdateProductDTO;
import com.icecream.IceCream.dto.ProductDTO;
import com.icecream.IceCream.exception.ApiRequestException;
import com.icecream.IceCream.mappingData.MappingProductDTO;
import com.icecream.IceCream.model.Product;
import com.icecream.IceCream.repository.CatalogueRepository;
import com.icecream.IceCream.repository.ProductPageRepository;
import com.icecream.IceCream.repository.ProductRepository;
import com.icecream.IceCream.repository.StatusRepository;
import com.icecream.IceCream.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductPageRepository productPageRepository;

	@Autowired
	CatalogueRepository catalogueRepository;

	@Autowired
	StatusRepository statusRepository;

	private static MappingProductDTO mappingProductDTO = new MappingProductDTO();
	
	@Override
	public Page<ProductDTO> getPageProductByNameAndCatalogueId(String findName, Long catalogueId, int page) {
		// TODO Auto-generated method stub
		Page<Product> pageProduct = productPageRepository.findByNameAndCatalogueId(findName, catalogueId,
				PageRequest.of(page, 5));
		return mappingProductDTO.convertToPageProductDTO(pageProduct);
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public ProductDTO findbyId(Long id) {
		// TODO Auto-generated method stub
		try {
			Product product;
			product = productRepository.findProductById(id);
			if (product != null) {
				return mappingProductDTO.convertToProductDTO(product);
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public ProductDTO createProduct(FormCreateProductDTO formProductDTO) {
		// TODO Auto-generated method stub
		try {
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			Product product = new Product();
			product.setName(formProductDTO.getName());
			product.setImage(formProductDTO.getImage());
			product.setContent(formProductDTO.getContent());
			product.setPrice(formProductDTO.getPrice());
			product.setCatalogue(catalogueRepository.findCatalogueById(formProductDTO.getCatalogueId()));
			product.setStatus(statusRepository.findStatusById((long) 1));
			product.setCreated_date(date);

			return mappingProductDTO.convertToProductDTO(productRepository.save(product));
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}
	}

	@Override
	public ProductDTO updateProduct(FormUpdateProductDTO formProductDTO) {
		// TODO Auto-generated method stub
		try {
			Product product;
			product = productRepository.findProductById(formProductDTO.getId());
			if (product != null) {
				product.setName(formProductDTO.getName());
				product.setImage(formProductDTO.getImage());
				product.setContent(formProductDTO.getContent());
				product.setPrice(formProductDTO.getPrice());
				product.setCatalogue(catalogueRepository.findCatalogueById(formProductDTO.getCatalogueId()));
				product.setStatus(statusRepository.findStatusById(formProductDTO.getStatusId()));
				return mappingProductDTO.convertToProductDTO(productRepository.save(product));
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiRequestException("Fail : " + e);
		}

	}

	@Override
	public Boolean deleteProduct(Long id) {
		// TODO Auto-generated method stub
		try {
			Product product;
			product = productRepository.findProductById(id);
			if (product != null) {
				productRepository.deleteById(product.getId());
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new ApiRequestException("Fail : " + e);
		}
	}

	

	@Override
	public List<ProductDTO> getListProductByNameAndCatalogueId(String findName, Long catalogueId) {
		// TODO Auto-generated method stub
		List<ProductDTO> listProductDTO = new ArrayList<ProductDTO>();
		List<Product> listProduct = productRepository.findByNameAndCatalogueId(findName, catalogueId);
		for (int i = 0; i < listProduct.size(); i++) {
			listProductDTO.add(mappingProductDTO.convertToProductDTO(listProduct.get(i)));

		}
		return listProductDTO;
	}

}
