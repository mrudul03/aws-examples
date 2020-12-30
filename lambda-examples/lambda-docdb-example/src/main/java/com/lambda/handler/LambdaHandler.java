package com.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lambda.config.MongoDbConfig;
import com.lambda.config.SslConfig;
import com.lambda.dao.ExampleDao;
import com.lambda.model.DataRequest;
import com.lambda.model.DataResponse;
import com.mongodb.MongoClient;

public class LambdaHandler implements RequestHandler<DataRequest, DataResponse> {

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	// Load SSL certificate
	SslConfig sslConfig = new SslConfig.Builder().loadTls();
	MongoDbConfig mongoDbConfig = new MongoDbConfig.Builder().initialize();

	@Override
	public DataResponse handleRequest(DataRequest request, Context context) {
		
		LambdaLogger logger = context.getLogger();
	    // process event
	    logger.log("EVENT: " + gson.toJson(request));
	    logger.log("EVENT TYPE: " + request.getClass().toString());
		
		logger.log("Getting MongoClient: ");
		MongoClient mongoClient =mongoDbConfig.getMongoClient();
		
		ExampleDao dao = new ExampleDao();
		dao.updateDocument(mongoClient);
		
		DataResponse response = new DataResponse();
		response.setMessage("Successfully executed function");
		return response;
	}

}
