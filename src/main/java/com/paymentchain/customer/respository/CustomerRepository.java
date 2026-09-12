package com.paymentchain.customer.respository;

import com.paymentchain.customer.entities.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sotobotero
 * @author Alvaro Blanco Sangines
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    List<Customer> findByName(String name);
    
}
