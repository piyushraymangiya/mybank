package com.hcl.mybank.dto;

import java.time.LocalDate;
import java.util.List;

import com.hcl.mybank.entity.TransactionDetail;

import lombok.Data;

@Data
public class AccountDetailsDto {

	private String accountNumber;

	private Double availableBalance;

	private LocalDate accountCreationDate;

	private List<TransactionDetail> transactionDetails;

}
