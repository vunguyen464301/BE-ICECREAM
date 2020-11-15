package com.icecream.IceCream.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.icecream.IceCream.model.AccountDetail;

@Repository
public interface AccountDetailRepository extends CrudRepository<AccountDetail, Long> {
}
