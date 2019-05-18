package com.sertac.photo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sertac.photo.model.Post;
import com.sertac.photo.model.User;
import com.sertac.photo.payload.PostResponse;
import com.sertac.photo.payload.UserPosts;
import com.sertac.photo.repository.PostRepository;
import com.sertac.photo.repository.UserRepository;
import com.sertac.photo.security.CurrentUser;
import com.sertac.photo.security.UserPrincipal;
import com.sertac.photo.util.AppConstants;
import com.sertac.photo.util.ModelMapper;

@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/getPosts/{username}")
	public UserPosts getPosts(@CurrentUser UserPrincipal currentUser, @PathVariable String username,
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
		UserPosts userPosts = new UserPosts();
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
			Page<Post> postList = postRepository.findByUserId(user.get().getId(), pageable);

			List<PostResponse> postResponse = postList.map(poll -> {
				return ModelMapper.mapPostToPollResponse(poll, poll.getUser());
			}).getContent();

			userPosts.setPostList(postResponse);
		}

		return userPosts;
	}
}
