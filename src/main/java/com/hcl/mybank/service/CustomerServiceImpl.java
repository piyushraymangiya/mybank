package com.hcl.mybank.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.mybank.dto.CustomerDto;
import com.hcl.mybank.entity.Customer;
import com.hcl.mybank.exception.ResourceNotFoundException;
import com.hcl.mybank.repository.AccountRepository;
import com.hcl.mybank.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public void getCustomerAccountSummery(CustomerDto customerDto) {
		
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDto, customer);
		
		Customer customer1 = customerRepository.findById(customer.getCustomerId()).orElseThrow(()-> 
		new ResourceNotFoundException("customer not found"));
		
	}

}