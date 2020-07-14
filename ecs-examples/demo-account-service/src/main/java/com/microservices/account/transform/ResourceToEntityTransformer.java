package com.microservices.account.transform;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.microservices.account.contract.DemoAccountResource;
import com.microservices.account.repository.DemoAccountEntity;

@Component
public class ResourceToEntityTransformer {
	
	private Function<DemoAccountResource, DemoAccountEntity> transformAccountToEntity = new 
			Function<DemoAccountResource, DemoAccountEntity>() {
		
	    public DemoAccountEntity apply(DemoAccountResource accountResource) {
	    	DemoAccountEntity accountEntity = new DemoAccountEntity();
	    	accountEntity.setAccountName(accountResource.getAccountName());
	    	accountEntity.setAccountNumber(accountResource.getAccountNumber());
	    	accountEntity.setBalance(accountResource.getBalance());
	    	//accountEntity.setCreatedAt(new Date());
	    	//accountEntity.setUpdatedAt(new Date());
	        return accountEntity;
	    }
	};
	
	public DemoAccountEntity transformAccount(DemoAccountResource accountResource){
		DemoAccountEntity accountEntity = transformAccountToEntity.apply(accountResource);
		return accountEntity;
	}


}
