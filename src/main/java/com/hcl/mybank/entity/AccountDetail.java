package com.hcl.mybank.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.ISBN;

public class AccountDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	
	@OneToOne
	@JoinColumn(name = "customerId")
	private Customer customerId;
	
	private String accountType;
	
	private Double accountBalance;
	

}
