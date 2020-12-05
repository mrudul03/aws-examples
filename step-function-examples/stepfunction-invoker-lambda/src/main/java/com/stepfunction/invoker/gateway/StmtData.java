package com.stepfunction.invoker.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StmtData {
	
	private String statementId;
	private String paymentDate;
	private double amount;

}
