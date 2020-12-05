package com.stepfunction.invoker;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.amazonaws.services.stepfunctions.model.StartExecutionResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stepfunction.invoker.gateway.BankData;
import com.stepfunction.invoker.gateway.BankDataRequest;
import com.stepfunction.invoker.gateway.ParsedData;
import com.stepfunction.invoker.gateway.StmtData;
import com.stepfunction.invoker.model.StepFunctionRequest;
import com.stepfunction.invoker.model.StepFunctionResponse;

public class StepFunctionHandler implements RequestHandler<StepFunctionRequest, StepFunctionResponse>{

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Override
	public StepFunctionResponse handleRequest(StepFunctionRequest request, Context context) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String requestId = context.getAwsRequestId();

		LambdaLogger logger = context.getLogger();
	    // process event
	    logger.log("EVENT: " + gson.toJson(request));
	    logger.log("EVENT TYPE: " + request.getClass().toString());
	    
	    logger.log("Invoking Step function");
	    this.invokeStrpFunction(logger, requestId);
		
		return null;
	}
	
	private void invokeStrpFunction(LambdaLogger logger, String requestId) {
		
		List<BankData> banksData = this.createBankData();
		List<StmtData> stmtsData = this.createStmtData();
		BankDataRequest bankDataRequest = new BankDataRequest();
		ParsedData parsedData = new ParsedData();
		parsedData.setBanksData(banksData);
		parsedData.setStmtsData(stmtsData);
		bankDataRequest.setParsedData(parsedData);
		
		String jsonData = gson.toJson(bankDataRequest);
		logger.log("Created parsed data:"+jsonData);
		
		// list of SMS Data
		StartExecutionRequest executionRequest = new StartExecutionRequest();
		executionRequest.setInput(jsonData);
		executionRequest.setName("ASF"+requestId);
		// statemachine ARN
		executionRequest.setStateMachineArn("arn:aws:states:ap-south-1:376368920129:stateMachine:VariableAggregateMachine");
		
		AWSStepFunctions client = AWSStepFunctionsClientBuilder.defaultClient();
		StartExecutionResult result = client.startExecution(executionRequest);
		logger.log("Got Step Function Result: " + result.toString());
		logger.log("Got Step Function Result:Arn: " + result.getExecutionArn());
	}
	
	private List<BankData> createBankData(){
		List<BankData> bankData = new ArrayList<>();
		BankData data1 = new BankData("SBI", "Credit", 123.00);
		BankData data2 = new BankData("ICICI", "Debit", 123.00);
		bankData.add(data1);
		bankData.add(data2);
		return bankData;
	}
	
	private List<StmtData> createStmtData(){
		List<StmtData> stmtsData = new ArrayList<>();
		
		StmtData data1 = new StmtData("Stmt1", "01012020", 1212.00);
		StmtData data2 = new StmtData("Stmt2", "01022020", 200.00);
		stmtsData.add(data1);
		stmtsData.add(data2);
		
		return stmtsData;
	}

}
