package com.hcl.mybank.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TransactionDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	private String fromAccountNumber;

	private String toAccountNumber;

	private Double transactionAmount;

	private String transactionType;

	private LocalDate transactionDate;

	private String comment;

}
