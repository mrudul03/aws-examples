package com.bakdata.aggregate.handler;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.bakdata.aggregate.model.BankDataRequest;
import com.bakdata.aggregate.model.BankDataResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BankDataAggregationHandler implements RequestHandler<BankDataRequest, BankDataResponse> {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public BankDataResponse handleRequest(BankDataRequest data, Context context) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		LambdaLogger logger = context.getLogger();
	    // process event
	    logger.log("EVENT: " + gson.toJson(data));
	    logger.log("EVENT TYPE: " + data.getClass().toString());
	    BankDataResponse response = new BankDataResponse();
	    response.putData("Credit", Double.valueOf(123.00));
	    response.putData("Debit", Double.valueOf(100));
	    return response;

	}

}
