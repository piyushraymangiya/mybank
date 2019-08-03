package com.hcl.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mybank.dto.AccountDetailsDto;
import com.hcl.mybank.dto.FundTransferDto;
import com.hcl.mybank.dto.ResponseDto;
import com.hcl.mybank.exception.InvalidInputException;
import com.hcl.mybank.service.AccountService;
import com.hcl.mybank.service.FundTransferService;

@CrossOrigin("*")
@RestController
@RequestMapping("/accounts")
public class AccountController {

	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private FundTransferService fundTransferService;
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<ResponseDto> getTrends(@PathVariable("customerId") Long customerId,Pageable page){
		AccountDetailsDto accontDto = accountService.getAccountDetailsWithTrnxs(customerId,page);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setHttpStatus(HttpStatus.OK);
		responseDto.setData(accontDto);
		return 	new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	
	@PostMapping("/customers/transfer")
	public ResponseDto fundTransfer(@RequestBody FundTransferDto fundTransferDto) throws InvalidInputException{
		ResponseDto response = new ResponseDto();
		response.setData(fundTransferService.fundTransfer(fundTransferDto));
		response.setHttpStatus(HttpStatus.OK);
		response.setMessage("Transfer Sucess");	
		return response; 
	}

	
	
	
	
}
