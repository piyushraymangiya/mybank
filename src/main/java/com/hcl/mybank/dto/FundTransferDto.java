package com.hcl.mybank.dto;

import lombok.Data;

@Data
public class FundTransferDto {

	String originAccount;
	String destinationAccount;
	Double amount;
	String comments;
}