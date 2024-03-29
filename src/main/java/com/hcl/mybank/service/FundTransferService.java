package com.hcl.mybank.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.mybank.dto.FundTransferDto;
import com.hcl.mybank.dto.TransactionDetailDto;
import com.hcl.mybank.entity.AccountDetail;
import com.hcl.mybank.entity.TransactionDetail;
import com.hcl.mybank.exception.InvalidInputException;
import com.hcl.mybank.exception.ResourceNotFoundException;
import com.hcl.mybank.repository.AccountDetailRepository;
import com.hcl.mybank.repository.TransactionDetailsRepository;

@Service
public class FundTransferService {

	@Autowired
	TransactionDetailsRepository transactionDetailRepository;

	@Autowired
	AccountDetailRepository accountDetailRepository;

	@Transactional
	public TransactionDetailDto fundTransfer(FundTransferDto fundTransferDto) throws InvalidInputException {
		TransactionDetailDto transactionDetailDto = new TransactionDetailDto();

		AccountDetail fromAccount = accountDetailRepository.findByAccountNumber(fundTransferDto.getOriginAccount());
		AccountDetail toAccount = accountDetailRepository.findByAccountNumber(fundTransferDto.getDestinationAccount());

		if (!(fromAccount != null && toAccount != null)) {
			throw new ResourceNotFoundException("Transfer Accounts Not Found");
		}

		Boolean isAllowed = validateBalance(fromAccount, fundTransferDto.getAmount());
		Boolean isInLimit = validateDailyTransactionLimits(fromAccount, fundTransferDto.getAmount());

		if (isInLimit && isAllowed) {
			TransactionDetail creditTransaction = new TransactionDetail();
			TransactionDetail debitTransaction = new TransactionDetail();

			creditTransaction.setFromAccountNumber(fundTransferDto.getOriginAccount());
			creditTransaction.setToAccountNumber(fundTransferDto.getDestinationAccount());
			creditTransaction.setTransactionAmount(fundTransferDto.getAmount());
			creditTransaction.setTransactionDate(LocalDate.now());
			creditTransaction.setTransactionType("CR");
			creditTransaction.setComment(fundTransferDto.getComments());

			debitTransaction.setFromAccountNumber(fundTransferDto.getOriginAccount());
			debitTransaction.setToAccountNumber(fundTransferDto.getDestinationAccount());
			debitTransaction.setTransactionAmount(fundTransferDto.getAmount());
			debitTransaction.setTransactionDate(LocalDate.now());
			debitTransaction.setTransactionType("DR");
			debitTransaction.setComment(fundTransferDto.getComments());

			List<TransactionDetail> transactionList = new ArrayList<>();
			transactionList.add(creditTransaction);
			transactionList.add(debitTransaction);
			transactionDetailRepository.saveAll(transactionList);

			fromAccount.setAccountBalance(fromAccount.getAccountBalance() - fundTransferDto.getAmount());
			toAccount.setAccountBalance(toAccount.getAccountBalance() + fundTransferDto.getAmount());

			BeanUtils.copyProperties(debitTransaction, transactionDetailDto);
		} else {
			String message;
			if (!isAllowed) {
				message = "Insufficient Minimum Balance - " + fromAccount.getMinimumAccountBalance();
				throw new InvalidInputException(message);
			} else if (!isInLimit) {
				message = "Exceded Daily Transaction Limit - " + fromAccount.getDailyTransactionLimit();
				throw new InvalidInputException(message);
			}

		}

		return transactionDetailDto;
	}

	public Boolean validateBalance(AccountDetail account, Double transactionAmount) {
		Double minimumBalance = account.getAccountBalance() - transactionAmount;
		if (minimumBalance > account.getMinimumAccountBalance()) {
			return true;
		}
		return false;
	}

	public Boolean validateDailyTransactionLimits(AccountDetail account, Double transactionAmount) {

		List<TransactionDetail> transactions = transactionDetailRepository
				.findByFromAccountNumberEquals(account.getAccountNumber());
		Double totalTransactionAmount = transactions.stream().mapToDouble(e -> e.getTransactionAmount()).sum();

		totalTransactionAmount += transactionAmount;

		if (totalTransactionAmount < account.getDailyTransactionLimit()) {
			return true;
		}

		return false;
	}
}
