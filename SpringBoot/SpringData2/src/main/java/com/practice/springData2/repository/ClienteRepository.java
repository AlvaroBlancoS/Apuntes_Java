package com.practice.springData2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.springData2.domain.client.Client;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Long> {

}
