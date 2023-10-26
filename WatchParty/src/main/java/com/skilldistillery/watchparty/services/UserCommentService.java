package com.skilldistillery.watchparty.services;

import java.util.List;

import com.skilldistillery.watchparty.entities.UserComment;

public interface UserCommentService {
	
	List<UserComment> getAllUserComments();
	UserComment getUserComment(int userCommentId);
	UserComment createUserComment(UserComment userComment);
	UserComment updateUserComment(int userCommentId, UserComment userComment);
	boolean deleteUserComment(int userCommentId);

}
