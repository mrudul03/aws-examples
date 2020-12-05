package com.bakdata.aggregate.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class BankDataResponse {
	
	private Map<String, Double> aggregatedData = new HashMap<>();
	
	public void putData(String paymentType, Double paymentAmount) {
		aggregatedData.put(paymentType, paymentAmount);
	}

}
