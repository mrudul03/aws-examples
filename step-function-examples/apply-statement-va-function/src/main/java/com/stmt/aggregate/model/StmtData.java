package com.stmt.aggregate.model;

import lombok.Data;

@Data
public class StmtData {
	
	private String statementId;
	private String paymentDate;
	private double amount;

}
