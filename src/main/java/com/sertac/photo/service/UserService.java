package com.sertac.photo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sertac.photo.model.User;
import com.sertac.photo.payload.ProfileSummary;
import com.sertac.photo.repository.FollowRepository;
import com.sertac.photo.repository.UserRepository;
import com.sertac.photo.security.UserPrincipal;
import com.sertac.photo.util.ModelMapper;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FollowRepository followRepository;

	public ProfileSummary getProfileByUserName(UserPrincipal currentUser, String username) {
		Optional<User> user = userRepository.findByUsername(username);

		return user.isPresent() ? ModelMapper.mapUserToProfileSummary(user.get(),
				followRepository.followerCountByUserId(user.get().getId()),
				followRepository.followedCountByUserId(user.get().getId()),
				currentUser != null
						? followRepository.getValidFollowRecord(currentUser.getId(), user.get().getId()).isPresent()
						: false)
				: new ProfileSummary();
	}
}
//commit
