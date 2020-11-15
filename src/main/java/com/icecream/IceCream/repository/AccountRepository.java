package com.icecream.IceCream.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.icecream.IceCream.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
	public Account findByUsername(String username);

	public Account findAccountById(Long id);
}