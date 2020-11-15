package com.icecream.IceCream.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icecream.IceCream.model.Account;

@Repository
public interface AccountAuthRepository extends JpaRepository<Account, Integer> {
	Optional<Account> findByUsername(String userName);
}
