package com.icecream.IceCream.mappingData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.icecream.IceCream.dto.ProductDTO;
import com.icecream.IceCream.model.Product;

public class MappingProductDTO {
	
	public MappingProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDTO convertToProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setImage(product.getImage());
		productDTO.setContent(product.getContent());
		productDTO.setPrice(product.getPrice());
		productDTO.setCatalogueId(product.getCatalogue().getId());
		productDTO.setStatusId(product.getStatus().getId());
		productDTO.setCreated_date(product.getCreated_date());
		return productDTO;
	}

	public Page<ProductDTO> convertToPageProductDTO(Page<Product> pageProduct) {
		List<Product> listProduct;
		List<ProductDTO> listProductDTO = new ArrayList<ProductDTO>();
		listProduct = pageProduct.stream().map(e -> e).collect(Collectors.toList());
		for (int i = 0; i < listProduct.size(); i++) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(listProduct.get(i).getId());
			productDTO.setName(listProduct.get(i).getName());
			productDTO.setImage(listProduct.get(i).getImage());
			productDTO.setContent(listProduct.get(i).getContent());
			productDTO.setPrice(listProduct.get(i).getPrice());
			productDTO.setCatalogueId(listProduct.get(i).getCatalogue().getId());
			productDTO.setStatusId(listProduct.get(i).getStatus().getId());
			productDTO.setCreated_date(listProduct.get(i).getCreated_date());
			listProductDTO.add(productDTO);
		}
		Page<ProductDTO> pageProductDTO = new PageImpl<ProductDTO>(listProductDTO, pageProduct.getPageable(),
				listProductDTO.size());
		return pageProductDTO;
	}
	
}
