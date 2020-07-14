package com.microservices.account.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "ACCOUNT")
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
//        allowGetters = true)
@Data
public class DemoAccountEntity {
	
	@Id
	@Column(name="account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    @Column(name = "account_name", nullable = true)
    private String accountName;
    
    @Column(name = "account_number", nullable = false)
    private String accountNumber;
    
    @Column(name = "balance", nullable = true)
    private Double balance;
    
//    @Column(nullable = true, name="date_created")
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreatedDate
//    private Date createdAt;
//
//    @Column(nullable = true, name="date_updated")
//    @Temporal(TemporalType.TIMESTAMP)
//    @LastModifiedDate
//    private Date updatedAt;

}
