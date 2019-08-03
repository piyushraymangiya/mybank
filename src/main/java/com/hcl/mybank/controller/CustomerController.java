package com.hcl.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mybank.dto.CustomerDto;
import com.hcl.mybank.dto.ResponseDto;
import com.hcl.mybank.exception.InvalidInputException;
import com.hcl.mybank.service.CustomerService;

@CrossOrigin("*")
@RequestMapping("/customers")
@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/login")
	public ResponseEntity<Object> customerLogin(@RequestBody CustomerDto customerDto) throws InvalidInputException {
		if (0l == customerDto.getCustomerId() || StringUtils.isEmpty(customerDto.getPassword())) {
			throw new InvalidInputException("Provide valid Input");
		}
		return new ResponseEntity<>(customerService.getCustomerAccountSummery(customerDto), HttpStatus.OK);
	}

	@GetMapping("/account/summary/{customerId}")
	public ResponseEntity<Object> getAccountSummary(@PathVariable("customerId") Long customerId) {
		ResponseDto responseDto = customerService.getAccountSummary(customerId);
		return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
	}
	
	@GetMapping("/beneficiary/{customerId}")
	public ResponseEntity<Object> getBeneficiaries(@PathVariable("customerId") Long customerId){
		ResponseDto responseDto  = customerService.getBeneficiaries(customerId);
		return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
	}

}
