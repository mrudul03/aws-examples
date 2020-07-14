package com.microservices.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoAccount {
	
	private String accountName;
	private String accountNumber;
	private double balance;

}
