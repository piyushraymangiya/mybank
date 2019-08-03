package com.hcl.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mybank.dto.CustomerDto;
import com.hcl.mybank.exception.InvalidInputException;
import com.hcl.mybank.service.CustomerService;

@CrossOrigin("*")
@RestController("/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/login")
	public ResponseEntity<Object> customerLogin(@RequestBody CustomerDto customerDto) throws InvalidInputException {
		if (customerDto.getCustomerId().equals(null) || customerDto.equals(null)) {
			throw new InvalidInputException("Provide valid Input");
		}
		return new ResponseEntity<>(customerService.getCustomerAccountSummery(customerDto), HttpStatus.OK);

	}

	@GetMapping("/account/summary/{customerId}")
	public ResponseEntity<Object> getAccountSummary(@PathVariable("customerId") Long customerId) {

		return null;
	}

}
