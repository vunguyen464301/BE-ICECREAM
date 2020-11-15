package com.icecream.IceCream.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icecream.IceCream.model.Catalogue;

@Repository
public interface CatalogueRepository extends CrudRepository<Catalogue, Long> {
	Catalogue findCatalogueById(Long id);

	List<Catalogue> findAll();
}
