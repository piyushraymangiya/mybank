package com.hcl.mybank.service;

import org.springframework.stereotype.Component;

import com.hcl.mybank.dto.CustomerDto;

@Component
public interface CustomerService {
	
	public void getCustomerAccountSummery(CustomerDto customerDto);

}
