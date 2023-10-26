package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.UserComment;

public interface UserCommentRepository extends JpaRepository<UserComment, Integer> {
	
	UserComment searchById(int userCommentId);

}
