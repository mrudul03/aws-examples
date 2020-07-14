package com.microservices.customer.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CUSTOMER")
@Data
public class CustomerEntity {
	
	@Id
	//@Column(name="customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
//    @Column(name = "birthdate")
//    private Date birthDate;
//
//    @Column(nullable = false, updatable = false, name="date_created")
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreatedDate
//    private Date createdAt;
//
//    @Column(nullable = false, name="date_updated")
//    @Temporal(TemporalType.TIMESTAMP)
//    @LastModifiedDate
//    private Date updatedAt;
    
//    @ElementCollection
//    @CollectionTable(name="CONTACT", joinColumns=@JoinColumn(name="customer_id"))
//    private List<ContactEntity> contacts;
//    
//    @ElementCollection
//    @CollectionTable(name="ADDRESS", joinColumns=@JoinColumn(name="customer_id"))
//    private List<AddressEntity> address;

}
