package com.icecream.IceCream.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.icecream.IceCream.dto.FormCreateProductDTO;
import com.icecream.IceCream.dto.FormUpdateProductDTO;
import com.icecream.IceCream.dto.ProductDTO;
import com.icecream.IceCream.model.Product;

public interface ProductService {
	Page<ProductDTO> getPageProductByNameAndCatalogueId(String findName, Long catalogueId, int page);
	List<ProductDTO> getListProductByNameAndCatalogueId(String findName, Long catalogueId);
	List<Product> findAll();
	ProductDTO findbyId(Long id);
	ProductDTO createProduct(FormCreateProductDTO formProductDTO);
	ProductDTO updateProduct(FormUpdateProductDTO formProductDTO);
	Boolean deleteProduct(Long id);
}
