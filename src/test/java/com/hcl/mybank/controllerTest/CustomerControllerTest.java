package com.hcl.mybank.controllerTest;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.hcl.mybank.controller.CustomerController;
import com.hcl.mybank.dto.CustomerDto;
import com.hcl.mybank.dto.ResponseDto;
import com.hcl.mybank.entity.Customer;
import com.hcl.mybank.exception.InvalidInputException;
import com.hcl.mybank.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	@InjectMocks
	CustomerController customerController;

	@Mock
	CustomerService customerService;
	Customer customer;
	CustomerDto customerDto;
	ResponseDto responseDto;

	@Before
	public void setup() {
		customerDto = new CustomerDto();
		customerDto.setCustomerId(1l);
		customerDto.setPassword("qazwsx1!");

	}

	@Test(expected = InvalidInputException.class)
	public void customerLoginTestcustomerId() throws InvalidInputException {
		customerDto.setCustomerId(0l);
		customerController.customerLogin(customerDto);
	}
	
	@Test(expected = InvalidInputException.class)
	public void customerLoginTestPassword() throws InvalidInputException {
		customerDto.setPassword(null);
		customerController.customerLogin(customerDto);
	}
	
	@Test(expected = InvalidInputException.class)
	public void customerLoginTestpass() throws InvalidInputException {
		customerDto.setCustomerId(1l);
		customerDto.setPassword("qazrfv1!");
		customerController.customerLogin(customerDto);
	}


	@Test
	public void customerLoginTestPass() throws InvalidInputException {
		responseDto = new ResponseDto();
		responseDto.setData(customerDto);
		responseDto.setHttpStatus(HttpStatus.ACCEPTED);
		responseDto.setMessage("Successfull login");
		Mockito.when(customerService.getCustomerAccountSummery(customerDto)).thenReturn(responseDto);
		assertNotNull(customerController.customerLogin(customerDto));
	}	

	
	@Test
	public void getAccountSummarytest() {
		customer= new Customer();
		customer.setCustomerId(1L);
		responseDto = new ResponseDto();
		responseDto.setData(customerDto);
		responseDto.setHttpStatus(HttpStatus.ACCEPTED);
		responseDto.setMessage("Successfull login");
		Mockito.when(customerService.getAccountSummary(customer.getCustomerId())).thenReturn(responseDto);
		assertNotNull(customerController.getAccountSummary(customer.getCustomerId()));
	
	}
	

}
