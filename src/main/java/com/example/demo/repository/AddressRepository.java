package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.entities.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

	
}
