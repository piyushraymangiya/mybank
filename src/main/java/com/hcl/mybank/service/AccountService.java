package com.hcl.mybank.service;

import com.hcl.mybank.dto.AccountDetailsDto;

public interface AccountService {
	
	public AccountDetailsDto getAccountDetailsWithTrnxs(Long custId) ;

}
