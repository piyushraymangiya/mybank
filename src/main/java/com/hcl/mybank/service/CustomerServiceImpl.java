package com.hcl.mybank.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.mybank.dto.AccountSummaryDto;
import com.hcl.mybank.dto.CustomerDto;
import com.hcl.mybank.dto.ResponseDto;
import com.hcl.mybank.entity.AccountDetail;
import com.hcl.mybank.entity.Customer;
import com.hcl.mybank.exception.ResourceNotFoundException;
import com.hcl.mybank.repository.AccountDetailRepository;
import com.hcl.mybank.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AccountDetailRepository accountRepository;

	@Override
	public ResponseDto getCustomerAccountSummery(CustomerDto customerDto) {

		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDto, customer);
		Customer customer1 = customerRepository.findById(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("customer not found"));
		if(customer.getPassword().equals(customer1.getPassword())) {
			return new ResponseDto("Customer login successfully", HttpStatus.ACCEPTED,null);	
		}
		throw new  ResourceNotFoundException("enter valid password");
	}

	@Override
	public ResponseDto getAccountSummary(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("customer not found"));
		AccountDetail accountDetail = accountRepository.findByCustomerId(customer);

		AccountSummaryDto accountSummaryDto = new AccountSummaryDto();
		accountSummaryDto.setCustomerName(customer.getCustomerName());
		accountSummaryDto.setAccountNumber(accountDetail.getAccountNumber());
		accountSummaryDto.setAccountType(accountDetail.getAccountType());
		accountSummaryDto.setAvailableBalance(accountDetail.getAccountBalance());

		return new ResponseDto("Account Summary", HttpStatus.ACCEPTED, accountSummaryDto);
	}

}