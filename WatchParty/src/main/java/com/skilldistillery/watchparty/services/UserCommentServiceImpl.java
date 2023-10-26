package com.skilldistillery.watchparty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.UserComment;
import com.skilldistillery.watchparty.repositories.UserCommentRepository;

@Service
public class UserCommentServiceImpl implements UserCommentService {
	
	@Autowired
	private UserCommentRepository userCommentRepo;
	
	@Override
	public List<UserComment> getAllUserComments() {
		return userCommentRepo.findAll();
	}

	@Override
	public UserComment getUserComment(int userCommentId) {
		return userCommentRepo.searchById(userCommentId);
	}

	@Override
	public UserComment createUserComment(UserComment userComment) {
//		locationRepo.saveAndFlush(userComment.getLocation());
		return userCommentRepo.saveAndFlush(userComment);
	}

	@Override
	public UserComment updateUserComment(int userCommentId, UserComment userComment) {
		UserComment dbUserComment = userCommentRepo.searchById(userCommentId);
		System.out.println("***************" + dbUserComment);
		if(dbUserComment != null) {
			dbUserComment.setComment(userComment.getComment());
			dbUserComment.setPhotoUrl(userComment.getPhotoUrl());
			dbUserComment.setEnabled(userComment.isEnabled());
			userCommentRepo.saveAndFlush(dbUserComment);
		}
		return dbUserComment;
	}

	@Override
	public boolean deleteUserComment(int userCommentId) {
		boolean deleted = false;
		UserComment userCommentToDelete = userCommentRepo.searchById(userCommentId);
		if(userCommentToDelete != null) {
			userCommentRepo.deleteById(userCommentToDelete.getId());
			deleted = true;
		}
		return deleted;
	}

}
