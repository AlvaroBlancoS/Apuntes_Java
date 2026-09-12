package com.paymentchain.customer.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
/**
 *
 * @author sotobotero 
 * @author Alvaro Blanco Sangines
 */
@Data
@Entity
public class Customer {
   
   @GeneratedValue(strategy = GenerationType.AUTO)  
   @Id
   private long id;
   private String name;
   private String phone; 
   private Set<String> emails;
}
