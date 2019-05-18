package com.sertac.photo.controller;

import java.io.IOException;
import java.util.List;

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
import com.sertac.photo.payload.PostResponse;
import com.sertac.photo.payload.UserPosts;
import com.sertac.photo.repository.PostRepository;
import com.sertac.photo.security.CurrentUser;
import com.sertac.photo.security.UserPrincipal;
import com.sertac.photo.util.AppConstants;
import com.sertac.photo.util.ModelMapper;

@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	PostRepository postRepository;

	@GetMapping("/getPosts/{userId}")
	public UserPosts getPosts(@CurrentUser UserPrincipal currentUser, @PathVariable Long userId,
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) throws IOException {
		UserPosts userPosts = new UserPosts();
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
		Page<Post> postList = postRepository.findByUserId(userId, pageable);

		List<PostResponse> postResponse = postList.map(poll -> {
			return ModelMapper.mapPostToPollResponse(poll, poll.getUser());
		}).getContent();

		userPosts.setPostList(postResponse);

		return userPosts;
	}
}
