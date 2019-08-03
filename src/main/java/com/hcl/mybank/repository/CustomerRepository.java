package com.hcl.mybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mybank.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

//	Customer findByCustomerIdAndPassword(Customer customer);
}
