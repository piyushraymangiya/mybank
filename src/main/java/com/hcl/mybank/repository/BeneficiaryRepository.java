package com.hcl.mybank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.mybank.entity.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
	
	@Query(value = "from Beneficiary where customerId.customerId=:customerId")
	public List<Beneficiary> findBeneficiary(@Param("customerId") Long customerId);
	
	

}
