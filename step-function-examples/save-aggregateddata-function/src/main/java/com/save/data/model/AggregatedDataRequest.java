package com.save.data.model;

import lombok.Data;

@Data
public class AggregatedDataRequest {
	
	//private Map<String, String> aggregatedData;
	private BankAggregate bankAggregate;
	private StmtAggregate stmtAggregate;

}
