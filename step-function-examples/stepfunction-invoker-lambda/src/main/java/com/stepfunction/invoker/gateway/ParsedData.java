package com.stepfunction.invoker.gateway;

import java.util.List;

import lombok.Data;

@Data
public class ParsedData {
	
	private List<StmtData> stmtsData;
	private List<BankData> banksData;

}
