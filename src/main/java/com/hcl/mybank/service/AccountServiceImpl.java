package com.hcl.mybank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.mybank.dto.AccountDetailsDto;
import com.hcl.mybank.entity.AccountDetail;
import com.hcl.mybank.entity.Customer;
import com.hcl.mybank.entity.TransactionDetail;
import com.hcl.mybank.exception.ResourceNotFoundException;
import com.hcl.mybank.repository.CustomerRepository;
import com.hcl.mybank.repository.TransactionDetailsRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	TransactionDetailsRepository trnxRepository;
	
	
	
	@Override
	public AccountDetailsDto getAccountDetailsWithTrnxs(Long custId) {
		Optional<Customer> custOptional = customerRepository.findById(custId);
		AccountDetailsDto accountDto = null;
		if(custOptional.isPresent()){
			AccountDetail accountEntity = custOptional.get().getAccount();
			accountDto = new AccountDetailsDto();
			accountDto.setAccountNumber(accountEntity.getAccountNumber());
			accountDto.setAvailableBalance(accountEntity.getAccountBalance());
			accountDto.setAccountCreationDate(accountEntity.getAccountCreationDate());
			List<TransactionDetail> trnxList = trnxRepository.findByFromAccountNumberEquals(accountEntity.getAccountNumber());
			accountDto.setTransactionDetails(trnxList);
		}else {
			throw new ResourceNotFoundException("Customer id is not found");
		}
		
		return null;
	}

}
