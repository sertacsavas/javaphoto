package com.sertac.photo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sertac.photo.model.Post;
import com.sertac.photo.payload.PostResponse;
import com.sertac.photo.payload.UserPosts;
import com.sertac.photo.repository.PostRepository;
import com.sertac.photo.security.UserPrincipal;
import com.sertac.photo.util.ModelMapper;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;

	public UserPosts getPosts(UserPrincipal currentUser, Long userId, int page, int size) {
		UserPosts userPosts = new UserPosts();

		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
		Page<Post> postList = postRepository.findByUserId(userId, pageable);

		userPosts.setTotalElements(postList.getTotalElements());
		userPosts.setTotalPages(postList.getTotalPages());
		userPosts.setNumberOfElements(postList.getNumberOfElements());
		userPosts.setNumber(postList.getNumber());

		List<PostResponse> postResponse = postList.map(post -> {
			return ModelMapper.mapPostToPollResponse(post);
		}).getContent();

		userPosts.setPostList(postResponse);

		return userPosts;
	}
}
