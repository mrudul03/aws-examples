package com.microservices.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoAccountRepository extends JpaRepository<DemoAccountEntity, Long>{
	
}
