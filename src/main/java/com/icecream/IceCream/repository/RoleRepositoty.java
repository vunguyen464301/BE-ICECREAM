package com.icecream.IceCream.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icecream.IceCream.model.Role;

@Repository
public interface RoleRepositoty extends CrudRepository<Role, Long> {
	Role findRoleById(Long id);
}
