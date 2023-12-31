package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	Address searchById(int id);
	Address findByStreet(String street);
	
}
