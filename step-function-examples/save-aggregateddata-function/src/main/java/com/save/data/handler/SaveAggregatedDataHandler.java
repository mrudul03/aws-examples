package com.save.data.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.save.data.model.AggregatedDataRequest;
import com.save.data.model.AggregatedDataResponse;

public class SaveAggregatedDataHandler implements RequestHandler<AggregatedDataRequest, AggregatedDataResponse> {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Override
	public AggregatedDataResponse handleRequest(AggregatedDataRequest request, Context context) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		LambdaLogger logger = context.getLogger();
	    // process event
	    logger.log("EVENT: " + gson.toJson(request));
	    logger.log("EVENT TYPE: " + request.getClass().toString());
	    
	    AggregatedDataResponse response = new AggregatedDataResponse();
	    response.setMessage("Data Saved");
	    
		return response;
	}
	

}
