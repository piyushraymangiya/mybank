package com.hcl.mybank.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.mybank.dto.FundTransferDto;
import com.hcl.mybank.dto.TransactionDetailDto;
import com.hcl.mybank.exception.InvalidInputException;
import com.hcl.mybank.service.FundTransferService;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
	@Mock
	FundTransferService fundTransferService;

	@Test
	public void fundTransferTest() throws InvalidInputException {
		TransactionDetailDto debitDto = new TransactionDetailDto();
		debitDto = new TransactionDetailDto();
		debitDto.setFromAccountNumber("1111");
		debitDto.setToAccountNumber("2222");
		TransactionDetailDto creditDto = new TransactionDetailDto();
		creditDto.setFromAccountNumber("1111");
		creditDto.setToAccountNumber("2222");
		FundTransferDto fundTransferDto = new FundTransferDto();
		fundTransferDto.setAmount(100.00);
		fundTransferDto.setComments("test");
		fundTransferDto.setDestinationAccount("2222");
		fundTransferDto.setOriginAccount("1111");

		Mockito.when(fundTransferService.fundTransfer(fundTransferDto)).thenReturn(debitDto);
		TransactionDetailDto expected = fundTransferService.fundTransfer(fundTransferDto);
		assertNotNull(expected);
	}

}
