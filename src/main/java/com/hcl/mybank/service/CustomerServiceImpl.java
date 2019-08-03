package com.hcl.mybank.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.mybank.dto.CustomerDto;
import com.hcl.mybank.dto.ResponseDto;
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

	public ResponseDto getCustomerAccountSummery(CustomerDto customerDto) {

		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDto, customer);

		return new ResponseDto("Customer login successfully", HttpStatus.ACCEPTED,
				customerRepository.findById(customer.getCustomerId())
						.orElseThrow(() -> new ResourceNotFoundException("customer not found")));

	}

	@Override
	public ResponseDto getAccountSummary(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("customer not found"));
		return new ResponseDto("Account Summary", HttpStatus.ACCEPTED, customer);
	}

}