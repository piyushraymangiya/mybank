package com.hcl.mybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mybank.entity.AccountDetail;
import com.hcl.mybank.entity.Customer;

@Repository
public interface AccountDetailRepository extends JpaRepository<AccountDetail, Long> {

	AccountDetail findByAccountNumber(String originalAccount);

	AccountDetail findByCustomerId(Customer customer);

}
