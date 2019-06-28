package com.sertac.photo.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sertac.photo.exception.AppException;
import com.sertac.photo.model.Follow;
import com.sertac.photo.model.User;
import com.sertac.photo.repository.FollowRepository;

@Service
public class FollowService {

	@Autowired
	FollowRepository followRepository;

	public void follow(Long followerUserId, Long followedUserId) {
		checkSelf(followerUserId, followedUserId);
		Optional<Follow> existingFollow = followRepository.getValidFollowRecord(followerUserId, followedUserId);

		if (existingFollow.isPresent()) {
			throw new AppException("User is already being followed");

		} else {

			Follow follow = new Follow();
			User followerUser = new User();
			followerUser.setId(followerUserId);
			User followedUser = new User();
			followedUser.setId(followedUserId);
			follow.setFollowerUser(followerUser);
			follow.setFollowedUser(followedUser);
			follow.setFollowDate(new Date());
			followRepository.save(follow);

		}
	}

	public void unFollow(Long followerUserId, Long followedUserId) {
		checkSelf(followerUserId, followedUserId);
		Optional<Follow> existingFollow = followRepository.getValidFollowRecord(followerUserId, followedUserId);
		if (existingFollow.isPresent()) {
			Follow follow = existingFollow.get();
			follow.setUnfollowDate(new Date());
			followRepository.save(follow);

		} else {
			throw new AppException("User is not being followed");
		}
	}

	private void checkSelf(Long followerUserId, Long followedUserId) {
		if (followerUserId.equals(followedUserId)) {
			throw new AppException("You can not follow yourself");
		}
	}
}
