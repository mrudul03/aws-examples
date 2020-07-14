package com.microservices.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.account.contract.DemoAccountResource;
import com.microservices.account.domain.DemoAccountService;
import com.microservices.account.repository.DemoAccountEntity;
import com.microservices.account.transform.EntityToResourceTransformer;

@RestController
@RequestMapping(value="/api/v1/accountservice")
public class DemoAccountController {
	
	@Autowired
	private DemoAccountService demoAccountService;
	
	@Autowired
	private EntityToResourceTransformer entityToResourceTransformer;
	
	@RequestMapping(value="/accounts/{accountId}", 
			method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<DemoAccountResource> getAccount(@PathVariable Long accountId){
		
		System.out.println("Got a request for payment account.....................");
		return ResponseEntity.ok(entityToResourceTransformer.transformAccount(
				demoAccountService.getPaymentAccount(accountId)));
	}

	@RequestMapping(value="/accounts", 
			method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<DemoAccountResource>> getAllAccounts(){
		
		System.out.println("Got a request for all payment accounts.....................");
		return ResponseEntity.ok(entityToResourceTransformer.transformAccountList(
				demoAccountService.getAllAccounts()));
	}
	
	@RequestMapping(value="/test", 
			method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> test(){
		
		System.out.println("Got test request.....................");
		return ResponseEntity.ok("Ok...");
	}
	
	@RequestMapping(value="/accounts", 
			method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<DemoAccountResource> createAccount(
			@RequestBody DemoAccountResource accountResource){
		
		System.out.println("In createAccount Controller ##############");
		
		DemoAccountEntity accountEntity = demoAccountService.createPaymentAccount(
				accountResource);
		return ResponseEntity.ok(entityToResourceTransformer.transformAccount(accountEntity));
	}

}
