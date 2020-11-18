package com.icecream.IceCream.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icecream.IceCream.dto.CatalogueDTO;
import com.icecream.IceCream.mappingData.MappingCatalogueDTO;
import com.icecream.IceCream.repository.CatalogueRepository;
import com.icecream.IceCream.service.CatalogueService;

@Service
public class CatalogueServiceImpl implements CatalogueService {

	@Autowired
	private CatalogueRepository catalogueRepository;

	private static MappingCatalogueDTO mappingCatalogueDTO = new MappingCatalogueDTO();
	
	@Override
	public List<CatalogueDTO> findAll() {
		// TODO Auto-generated method stub
		return mappingCatalogueDTO.convertToListCatalogueDTO(catalogueRepository.findAll());
	}

	

}
