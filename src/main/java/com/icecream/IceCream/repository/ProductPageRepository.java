package com.icecream.IceCream.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.icecream.IceCream.model.Product;

public interface ProductPageRepository extends PagingAndSortingRepository<Product, Long> {
	@Query(value = "select * " + "from product b , catalogue a , status c "
			+ "where b.catalogue_id = a.id  and c.id = b.status_id and a.name like ?1% and a.id = ?2 ", nativeQuery = true)
	Page<Product> findByNameAndCatalogueId(String username, long catalogueId, Pageable pageable);
}
