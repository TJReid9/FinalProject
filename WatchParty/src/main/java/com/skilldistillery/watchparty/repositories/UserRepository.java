package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);

}
