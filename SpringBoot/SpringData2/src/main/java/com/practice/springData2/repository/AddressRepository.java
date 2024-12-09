package com.practice.springData2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.springData2.domain.address.Address;

@Repository
public interface AddressRepository extends JpaRepository <Address, Long>{

}
