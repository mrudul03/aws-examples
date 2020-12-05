package com.stmt.aggregate.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stmt.aggregate.model.StmtDataRequest;
import com.stmt.aggregate.model.StmtDataResponse;

public class StmtAggregateHandler implements RequestHandler<StmtDataRequest, StmtDataResponse> {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public StmtDataResponse handleRequest(StmtDataRequest data, Context context) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		LambdaLogger logger = context.getLogger();
	    // process event
	    logger.log("EVENT: " + gson.toJson(data));
	    logger.log("EVENT TYPE: " + data.getClass().toString());
	    StmtDataResponse response = new StmtDataResponse();
	    response.setAggrgatedPayments("AggregatedPayments");
	    response.setAggrgatedPaymentAmount(123);
	    return response;

	}

}
