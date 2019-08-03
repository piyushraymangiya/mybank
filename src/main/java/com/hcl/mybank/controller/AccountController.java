package com.hcl.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mybank.dto.ResponseDto;
import com.hcl.mybank.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/accounts")
public class AccountController {

	
	@Autowired
	AccountService accountService;
	
	@GetMapping
	@RequestMapping("/customers/{customerId}")
	public ResponseEntity<ResponseDto> getTrends(@PathVariable("customerId") String customerId){

		return null;
	}
	
	
	
}
