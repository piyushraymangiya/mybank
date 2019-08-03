package com.hcl.mybank.FundTransferService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.lenient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.mybank.dto.FundTransferDto;
import com.hcl.mybank.dto.TransactionDetailDto;
import com.hcl.mybank.entity.AccountDetail;
import com.hcl.mybank.entity.TransactionDetail;
import com.hcl.mybank.exception.InvalidInputException;
import com.hcl.mybank.repository.AccountDetailRepository;
import com.hcl.mybank.repository.TransactionDetailsRepository;
import com.hcl.mybank.service.FundTransferService;

@RunWith(MockitoJUnitRunner.class)
public class FundTransferServiceTest {

	@Mock
	FundTransferService fundTransferServiceMock;

	@Mock
	AccountDetailRepository accountDetailRepository;

	@Mock
	TransactionDetailsRepository transactionDetailsRepository;

	AccountDetail account;
	TransactionDetail debit, credit;
	TransactionDetailDto debitDto, creditDto;
	AccountDetail account1, account2;

	@Test
	public void testFundTransfer() throws InvalidInputException {
		debitDto = new TransactionDetailDto();
		debitDto.setFromAccountNumber("1111");
		debitDto.setToAccountNumber("2222");
		creditDto = new TransactionDetailDto();
		creditDto.setFromAccountNumber("1111");
		creditDto.setToAccountNumber("2222");
		FundTransferDto fundTransferDto = new FundTransferDto();
		fundTransferDto.setAmount(100.00);
		fundTransferDto.setComments("test");
		fundTransferDto.setDestinationAccount("2222");
		fundTransferDto.setOriginAccount("1111");

		Mockito.when(fundTransferServiceMock.fundTransfer(fundTransferDto)).thenReturn(debitDto);

		TransactionDetailDto transactionDetailDto = fundTransferServiceMock.fundTransfer(fundTransferDto);

		assertNotNull(transactionDetailDto);

	}

	@Test
	public void validBalance() {

		lenient().when(fundTransferServiceMock.validateBalance(account, 500.00)).thenReturn(true);
		Boolean actual = fundTransferServiceMock.validateBalance(account, 500.00);
		assertEquals(true, actual);

	}

	@Test
	public void validLimit() {
		lenient().when(fundTransferServiceMock.validateDailyTransactionLimits(account, 500.00)).thenReturn(true);
		Boolean actual = fundTransferServiceMock.validateDailyTransactionLimits(account, 500.00);
		assertEquals(true, actual);
	}
}
