package com.hcl.mybank.service;

import com.hcl.mybank.entity.AccountDetail;

public interface AccountService {
	
	public AccountDetail getAccountDetailsWithTrnxs(Long custId) ;

}
