package com.smsdata.handler.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsParsedData {
	
	private List<BankData> banksData;
	private List<StmtData> stmtsData;

}
