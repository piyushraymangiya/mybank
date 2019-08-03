package com.hcl.mybank.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.mybank.dto.AccountSummaryDto;
import com.hcl.mybank.dto.CustomerDto;
import com.hcl.mybank.dto.ResponseDto;
import com.hcl.mybank.entity.AccountDetail;
import com.hcl.mybank.entity.Beneficiary;
import com.hcl.mybank.entity.Customer;
import com.hcl.mybank.exception.ResourceNotFoundException;
import com.hcl.mybank.repository.AccountDetailRepository;
import com.hcl.mybank.repository.BeneficiaryRepository;
import com.hcl.mybank.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountDetailRepository accountRepository;

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;

	@Override
	public ResponseDto getCustomerAccountSummery(CustomerDto customerDto) {

		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDto, customer);
		Customer customer1 = customerRepository.findById(customer.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException("customer not found"));
		if (customer.getPassword().equals(customer1.getPassword())) {
			return new ResponseDto("Customer login successfully", HttpStatus.ACCEPTED, customer1.getCustomerId());
		}
		throw new ResourceNotFoundException("enter valid password");
	}

	@Override
	public ResponseDto getAccountSummary(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("customer not found"));
		AccountDetail accountDetail = accountRepository.findByCustomerId(customer);
		if (null != accountDetail) {
			AccountSummaryDto accountSummaryDto = new AccountSummaryDto();
			accountSummaryDto.setCustomerName(customer.getCustomerName());
			accountSummaryDto.setAccountNumber(accountDetail.getAccountNumber());
			accountSummaryDto.setAccountType(accountDetail.getAccountType());
			accountSummaryDto.setAvailableBalance(accountDetail.getAccountBalance());
			return new ResponseDto("Account Summary", HttpStatus.ACCEPTED, accountSummaryDto);
		}
		return new ResponseDto("No Account customer summery to show", HttpStatus.ACCEPTED, null);

	}

	@Override
	public ResponseDto getBeneficiaries(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("customer not found"));
		List<Beneficiary> beneficiariesList = beneficiaryRepository.findBeneficiary(customer.getCustomerId());
		if (beneficiariesList.isEmpty()) {
			return new ResponseDto("Customer Beneficiaries", HttpStatus.OK, beneficiariesList);
		}
		return new ResponseDto("Sorry No Beneficiary added in your account", HttpStatus.OK, beneficiariesList);
	}

}