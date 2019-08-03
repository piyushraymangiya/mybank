package com.hcl.mybank.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mybank.entity.TransactionDetail;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetail, Long>{

	List<TransactionDetail> findByFromAccountNumberEquals(String accountNumber);
	List<TransactionDetail> findByFromAccountNumberOrderByTransactionDateDesc(String accountNumber,Pageable page);
	
}
