package com.microservices.account.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.account.contract.DemoAccountResource;
import com.microservices.account.repository.DemoAccountEntity;
import com.microservices.account.repository.DemoAccountRepository;
import com.microservices.account.transform.ResourceToEntityTransformer;

@Service
@Transactional
public class DemoAccountService {

	@Autowired
	private ResourceToEntityTransformer resourceToEntityTransformer;
	
	@Autowired
	private DemoAccountRepository accountRepository;
	
	public DemoAccountEntity getPaymentAccount(Long id){
//		DemoAccountEntity demoAccount = new DemoAccountEntity();
//		demoAccount.setAccountName("Demo Account");
//		demoAccount.setAccountNumber("DEMO-AC-1");
//		demoAccount.setBalance(123.00);
//		demoAccount.setId(id);
//		return demoAccount;
		
		return accountRepository.getOne(id);
	}
	
	public List<DemoAccountEntity> getAllAccounts(){
//		DemoAccountEntity demoAccount = new DemoAccountEntity();
//		demoAccount.setAccountName("Demo Account");
//		demoAccount.setAccountNumber("DEMO-AC-1");
//		demoAccount.setBalance(123.00);
//		demoAccount.setId(1L);
//		List<DemoAccountEntity> accounts = new ArrayList<DemoAccountEntity>();
//		accounts.add(demoAccount);
//		return accounts;
		return accountRepository.findAll();
	}
	
//	public List<DemoAccountEntity> getPaymentAccountsByCustomer(Long customerId){
//		DemoAccountEntity demoAccount = new DemoAccountEntity();
//		demoAccount.setAccountName("Demo Account");
//		demoAccount.setAccountNumber("DEMO-AC-1");
//		demoAccount.setBalance(123.00);
//		demoAccount.setId(1L);
//		List<DemoAccountEntity> accounts = new ArrayList<DemoAccountEntity>();
//		accounts.add(demoAccount);
//		return accounts;
//	}
	
	public DemoAccountEntity createPaymentAccount(DemoAccountResource accountResource){
		System.out.println("Creating customer on controller req #######################");
		DemoAccountEntity accountEntity = resourceToEntityTransformer.transformAccount(accountResource);
		accountRepository.save(accountEntity);
		System.out.println("Saved account to DB #######################");
		return accountEntity;
	}
	
	

}
