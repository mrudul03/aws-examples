package com.microservices.account.transform;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.microservices.account.contract.DemoAccountResource;
import com.microservices.account.repository.DemoAccountEntity;

@Component
public class EntityToResourceTransformer {
	private Function<DemoAccountEntity, DemoAccountResource> transformAccountToResource = new 
			Function<DemoAccountEntity, DemoAccountResource>() {
		
	    public DemoAccountResource apply(DemoAccountEntity accountEntity) {
	    	DemoAccountResource accountResource = new DemoAccountResource();
	    	accountResource.setAccountName(accountEntity.getAccountName());
	    	accountResource.setAccountNumber(accountEntity.getAccountNumber());
	    	accountResource.setBalance(accountEntity.getBalance());
	        return accountResource;
	    }
	};
	
	public DemoAccountResource transformAccount(DemoAccountEntity accountEntity){
		DemoAccountResource accountResource = transformAccountToResource.apply(accountEntity);
		return accountResource;
	}
	
	public List<DemoAccountResource> transformAccountList(List<DemoAccountEntity> accounts){
		List<DemoAccountResource> accountResources = accounts.stream().
				map(entity -> transformAccount(entity)).
				collect(Collectors.toList());
		
		return accountResources;
	}

}
