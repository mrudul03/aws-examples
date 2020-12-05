package com.stepfunction.invoker.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankData {
	
	private String bankName;
	private String transactionType;
	private double amount;

}
