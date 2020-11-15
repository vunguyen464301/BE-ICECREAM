package com.icecream.IceCream.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icecream.IceCream.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	Product findProductById(Long id);

	@Query(value = "select * " + "from product b , catalogue a ,  status c "
			+ "where b.catalogue_id = a.id and c.id = b.status_id and b.name like ?1% and a.id = ?2 ", nativeQuery = true)
	List<Product> findByNameAndCatalogueId(String username, long catalogueId);
}
