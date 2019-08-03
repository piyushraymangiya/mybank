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
public class AccountDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	
	@OneToOne
	@JoinColumn(name = "customerId")
	private Customer customerId;
	
	private String accountType;
	
	private Double accountBalance;
	
	private String accountNumber;
	
	private Double dailyTransactionLimit;
	
	private Double minimumAccountBalance;
	
	private LocalDate accountCreationDate;
	

}
