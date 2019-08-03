package com.hcl.mybank.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class TransactionDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	@OneToOne
	@JoinColumn(name = "accountId")
	private AccountDetail fromAccount;

	@OneToOne
	@JoinColumn(name = "accountId")
	private AccountDetail toAccount;

	private Double transactionAmount;

	private String transactionType;

	private LocalDate transactionDate;

	private String comment;

}
