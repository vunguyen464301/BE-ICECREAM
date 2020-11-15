package com.icecream.IceCream.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.icecream.IceCream.model.Account;
import com.icecream.IceCream.model.Role;

public interface AccountPageRepository extends PagingAndSortingRepository<Account, Long> {
	Page<Account> findAll(Pageable pageable);

	@Query(value = "select * " + " from account a , account_role b , role c "
			+ "where a.id = b.account_id and c.id = b.role_id and a.username like ?1% and c.id = ?2 ", nativeQuery = true)
	Page<Account> findByUsernameAndRole(String username, long roleId, Pageable pageable);

	Page<Account> findByRole(Role role, Pageable pageable);

}
