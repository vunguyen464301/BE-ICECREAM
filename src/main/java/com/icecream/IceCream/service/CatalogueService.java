package com.icecream.IceCream.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icecream.IceCream.Interface.CatalogueInterface;
import com.icecream.IceCream.dto.CatalogueDTO;
import com.icecream.IceCream.dto.ProductDTO;
import com.icecream.IceCream.model.Catalogue;
import com.icecream.IceCream.repository.CatalogueRepository;

@Service
public class CatalogueService implements CatalogueInterface {

	@Autowired
	private CatalogueRepository catalogueRepository;

	@Override
	public List<CatalogueDTO> findAll() {
		// TODO Auto-generated method stub
		return convertToListCatalogueDTO(catalogueRepository.findAll());
	}

	public List<CatalogueDTO> convertToListCatalogueDTO(List<Catalogue> listCatalogue) {
		List<CatalogueDTO> listCatalogueDTO = new ArrayList<CatalogueDTO>();
		for (int i = 0; i < listCatalogue.size(); i++) {
			CatalogueDTO catalogueDTO = new CatalogueDTO();
			List<ProductDTO> listProductDTO = new ArrayList<ProductDTO>();
			catalogueDTO.setId(listCatalogue.get(i).getId());
			catalogueDTO.setName(listCatalogue.get(i).getName());
			for (int j = 0; j < listCatalogue.get(i).getProduct().size(); j++) {
				ProductDTO productDTO = new ProductDTO();
				productDTO.setId(listCatalogue.get(i).getProduct().get(j).getId());
				productDTO.setName(listCatalogue.get(i).getProduct().get(j).getName());
				productDTO.setImage(listCatalogue.get(i).getProduct().get(j).getImage());
				productDTO.setContent(listCatalogue.get(i).getProduct().get(j).getContent());
				productDTO.setPrice(listCatalogue.get(i).getProduct().get(j).getPrice());
				productDTO.setCatalogueId(listCatalogue.get(i).getProduct().get(j).getCatalogue().getId());
				productDTO.setStatusId(listCatalogue.get(i).getProduct().get(j).getStatus().getId());
				productDTO.setCreated_date(listCatalogue.get(i).getProduct().get(j).getCreated_date());
				listProductDTO.add(productDTO);
			}
			catalogueDTO.setListProduct(listProductDTO);
			listCatalogueDTO.add(catalogueDTO);
		}
		return listCatalogueDTO;
	}

}
