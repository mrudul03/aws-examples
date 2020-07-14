package com.microservices.account.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoAccountResource {
	
	//private Long customerId;
	private String accountName;
	private String accountNumber;
	private double balance;

}
