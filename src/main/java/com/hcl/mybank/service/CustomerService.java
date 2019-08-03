package com.hcl.mybank.service;

import com.hcl.mybank.dto.CustomerDto;
import com.hcl.mybank.dto.ResponseDto;

public interface CustomerService {

	public ResponseDto getCustomerAccountSummery(CustomerDto customerDto);

	public ResponseDto getAccountSummary(Long customerId);
	
	public ResponseDto getBeneficiaries(Long customerId);

}
