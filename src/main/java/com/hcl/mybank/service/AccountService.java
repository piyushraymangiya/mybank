package com.hcl.mybank.service;

import org.springframework.data.domain.Pageable;

import com.hcl.mybank.dto.AccountDetailsDto;

public interface AccountService {
	
	public AccountDetailsDto getAccountDetailsWithTrnxs(Long custId,Pageable page);

}
