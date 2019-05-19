package com.sertac.photo.util;

import com.sertac.photo.model.Post;
import com.sertac.photo.model.User;
import com.sertac.photo.payload.PostResponse;
import com.sertac.photo.payload.UserSummary;

public class ModelMapper {

	public static PostResponse mapPostToPollResponse(Post post) {
		PostResponse postResponse = new PostResponse();
		postResponse.setId(post.getId());
		postResponse.setUrl(post.getUrl());

		return postResponse;
	}

	public static UserSummary mapUserToUserSummary(User user) {
		UserSummary userSummary = new UserSummary(user.getId(), user.getUsername(), user.getName());
		return userSummary;
	}

}
