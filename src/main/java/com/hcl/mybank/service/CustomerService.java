package com.hcl.mybank.service;

import org.springframework.stereotype.Component;

import com.hcl.mybank.dto.CustomerDto;
import com.hcl.mybank.dto.ResponseDto;

@Component
public interface CustomerService {
	
	public ResponseDto getCustomerAccountSummery(CustomerDto customerDto);

}
