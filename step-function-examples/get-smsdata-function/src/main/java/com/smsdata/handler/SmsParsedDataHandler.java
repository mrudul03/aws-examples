package com.smsdata.handler;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smsdata.handler.model.BankData;
import com.smsdata.handler.model.SmsParsedData;
import com.smsdata.handler.model.SmsParsedDataRequest;
import com.smsdata.handler.model.SmsParsedDataResponse;
import com.smsdata.handler.model.StmtData;

public class SmsParsedDataHandler implements RequestHandler<SmsParsedDataRequest, SmsParsedDataResponse> {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public SmsParsedDataResponse handleRequest(SmsParsedDataRequest data, Context context) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		LambdaLogger logger = context.getLogger();
	    // process event
	    logger.log("EVENT: " + gson.toJson(data));
	    logger.log("EVENT TYPE: " + data.getClass().toString());
	    
	    SmsParsedDataResponse response = new SmsParsedDataResponse();
	    
	    SmsParsedData parsedData = new SmsParsedData();
	    
	    parsedData.setStmtsData(this.getStmtData());
	    parsedData.setBanksData(this.getBankData());
	    response.setParsedData(parsedData);
	    return response;

	}
	
	private List<StmtData> getStmtData(){
		List<StmtData> stmtsData = new ArrayList<>();
		
		StmtData data1 = new StmtData("123123", "Credit", 123);
		StmtData data2 = new StmtData("123123", "Debit", 123);
		stmtsData.add(data1);
		stmtsData.add(data2);
		
		return stmtsData;
	}
	
	private List<BankData> getBankData(){
		List<BankData> smsParsedData = new ArrayList<>();
		
		BankData data1 = new BankData("SBI123", "Credit", 100);
		BankData data2 = new BankData("ICICI123", "Debit", 200);
		smsParsedData.add(data1);
		smsParsedData.add(data2);
		
		return smsParsedData;
	}

}
