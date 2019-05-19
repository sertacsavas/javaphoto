package com.sertac.photo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sertac.photo.model.User;
import com.sertac.photo.payload.UserSummary;
import com.sertac.photo.repository.UserRepository;
import com.sertac.photo.util.ModelMapper;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public UserSummary getUserByUserName(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return user.isPresent() ? ModelMapper.mapUserToUserSummary(user.get()) : new UserSummary();
	}
}
