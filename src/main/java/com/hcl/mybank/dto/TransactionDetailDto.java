package com.hcl.mybank.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class TransactionDetailDto {

	private Long transactionId;
	
	private String fromAccountNumber;

	private String toAccountNumber;

	private Double transactionAmount;

	private String transactionType;

	private LocalDate transactionDate;

	private String comment;
}
