package com.sertac.photo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sertac.photo.payload.UserPosts;
import com.sertac.photo.security.CurrentUser;
import com.sertac.photo.security.UserPrincipal;
import com.sertac.photo.service.PostService;
import com.sertac.photo.util.AppConstants;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	PostService postService;

	@GetMapping("/getPosts/{userId}")
	public UserPosts getPosts(@CurrentUser UserPrincipal currentUser, @PathVariable Long userId,
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {

		return postService.getPosts(currentUser, userId, page, size);
	}
}
