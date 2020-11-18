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

import com.icecream.IceCream.dto.FormCreateProductDTO;
import com.icecream.IceCream.dto.FormUpdateProductDTO;
import com.icecream.IceCream.dto.ProductDTO;
import com.icecream.IceCream.model.Product;
import com.icecream.IceCream.service.impl.ProductServiceImpl;

@Configuration
@EnableWebSecurity
@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {
	private final ProductServiceImpl productService;
	public ProductController(ProductServiceImpl productService) {
		this.productService = productService;
	}
	
	@RequestMapping("/")
	public List<Product> getAllProduct() {
		return productService.findAll();  
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ProductDTO createProduct(@RequestBody FormCreateProductDTO formProductDTO) {
		return productService.createProduct(formProductDTO);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody FormUpdateProductDTO formProductDTO) {
		return ResponseEntity.ok().body(productService.updateProduct(formProductDTO));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ProductDTO getProductById(@PathVariable( value="id") Long id) {
		return productService.findbyId(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean deleteProduct(@PathVariable( value="id") Long id) {
		return productService.deleteProduct(id);
	}
	
	@RequestMapping("/findPageProduct/")
	public Page<ProductDTO> getPageAccountByUsername(@RequestParam String findName, @RequestParam Long catalogueId,@RequestParam int page) {
		return productService.getPageProductByNameAndCatalogueId(findName, catalogueId, page);
	}
	
	@RequestMapping("/findProduct/")
	public List<ProductDTO> getPageAccountByName(@RequestParam String findName, @RequestParam Long catalogueId) {
		return productService.getListProductByNameAndCatalogueId(findName, catalogueId);
	}
	
}
