package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.PartyGoer;
import com.skilldistillery.watchparty.entities.PartyGoerId;

public interface PartyGoerRepository extends JpaRepository<PartyGoer, PartyGoerId> {

}
