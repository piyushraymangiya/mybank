package com.hcl.mybank.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CustomerDto {
	
	@NotNull
	private Long customerId;
	@NotNull
	private String password;
}
