package com.sertac.photo.util;

import com.sertac.photo.model.Post;
import com.sertac.photo.model.User;
import com.sertac.photo.payload.PostResponse;
import com.sertac.photo.payload.ProfileSummary;
import com.sertac.photo.payload.UserSummary;

public class ModelMapper {

	public static PostResponse mapPostToPollResponse(Post post, User user) {
		PostResponse postResponse = new PostResponse();
		postResponse.setId(post.getId());
		postResponse.setUrl(post.getUrl());
		postResponse.setUserSummary(mapUserToUserSummary(user));

		return postResponse;
	}

	public static UserSummary mapUserToUserSummary(User user) {
		UserSummary userSummary = new UserSummary(user.getId(), user.getUsername(), user.getName());
		return userSummary;
	}

	public static ProfileSummary mapUserToProfileSummary(User user, long followerCount, long followedCount,
			boolean following) {
		ProfileSummary profileSummary = new ProfileSummary(user.getId(), user.getUsername(), user.getName(),
				followerCount, followedCount, following);
		return profileSummary;
	}

}
