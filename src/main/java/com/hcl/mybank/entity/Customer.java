package com.hcl.mybank.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Data
@Table(name = "customer")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "customerId")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	private String customerName;

	private String gender;

	private String mobile;

	private String email;

	private String password;

	@OneToMany
	@JoinTable(name = "beneficiary", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "beneficiary_id"))
	List<Customer> beneficiaries;

}
