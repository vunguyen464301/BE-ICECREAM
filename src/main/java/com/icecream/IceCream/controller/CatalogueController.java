package com.icecream.IceCream.controller;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icecream.IceCream.dto.CatalogueDTO;
import com.icecream.IceCream.service.CatalogueService;

@Configuration
@EnableWebSecurity
@CrossOrigin
@RestController
@RequestMapping("/catalogue")
public class CatalogueController {
	final private CatalogueService catalogueService;

	public CatalogueController(CatalogueService catalogueService) {
		this.catalogueService = catalogueService;
	}

	@RequestMapping("/")
	public List<CatalogueDTO> getAllCatalogue() {
		return catalogueService.findAll();
	}
}
