package com.hcl.mybank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mybank.dto.CustomerDto;

@CrossOrigin("*")
@RestController("/customers")
public class CustomerController {
	
	@PostMapping("/login")
	public ResponseEntity<Object> getAccountSummery(@RequestBody CustomerDto customerdto){
		
		
		return null;
		
	}
	
	

}
