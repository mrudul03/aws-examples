package com.bakdata.aggregate.model;

import lombok.Data;

@Data
public class BankData {
	
	private String bankName;
	private String transactionType;
	private double amount;

}
