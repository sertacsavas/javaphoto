package com.sertac.photo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sertac.photo.model.Post;
import com.sertac.photo.repository.PostRepository;
import com.sertac.photo.security.CurrentUser;
import com.sertac.photo.security.UserPrincipal;

@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	PostRepository postRepository;

	@GetMapping("/getPosts")
	public List<Post> getPosts(@CurrentUser UserPrincipal currentUser) throws JsonProcessingException {
		List<Post> postList = postRepository.findByUserId(currentUser.getId());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

		objectMapper.writeValueAsString(postList);
		return postList;
	}
}
