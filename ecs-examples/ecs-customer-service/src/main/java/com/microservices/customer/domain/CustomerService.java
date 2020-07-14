package com.microservices.customer.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.customer.contract.CustomerResource;
import com.microservices.customer.repository.CustomerEntity;
import com.microservices.customer.repository.CustomersRepository;
import com.microservices.customer.transform.ResourceToEntityTransformer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomersRepository customersRepository;
	
	@Autowired
	private ResourceToEntityTransformer entityTransformer;
	
	public CustomerEntity createCustomer(CustomerResource customerResource){
		System.out.println("In createCustomer Service");
		CustomerEntity customerEntity = entityTransformer.transformCustomer(customerResource);
		CustomerEntity savedCustomer = customersRepository.save(customerEntity);
		System.out.println("In service now - Publishing message ####################:");
		return savedCustomer;
	}
	
	public CustomerEntity getCustomer(Long customerId){
		return customersRepository.findById(customerId).get();
	}
	
	public void deleteCustomer(Long customerId){
		CustomerEntity customerEntity = customersRepository.findById(customerId).get();
		customersRepository.delete(customerEntity);
	}
	
	public List<CustomerEntity> getAllCustomers(){
		return customersRepository.findAll();
	}
	
	
}
