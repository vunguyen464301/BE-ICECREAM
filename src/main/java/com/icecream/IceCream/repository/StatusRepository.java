package com.icecream.IceCream.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icecream.IceCream.model.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
	Status findStatusById(Long id);
}
