package com.sertac.photo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sertac.photo.model.Comment;
import com.sertac.photo.model.Follow;
import com.sertac.photo.model.LikeCount;
import com.sertac.photo.model.Post;
import com.sertac.photo.model.User;
import com.sertac.photo.model.ViewerLike;
import com.sertac.photo.payload.PostResponse;
import com.sertac.photo.payload.UserPosts;
import com.sertac.photo.repository.FollowRepository;
import com.sertac.photo.repository.PostRepository;
import com.sertac.photo.security.UserPrincipal;
import com.sertac.photo.util.ModelMapper;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;

	@Autowired
	FollowRepository followRepository;

	@Autowired
	LikeService likeService;

	@Autowired
	CommentService commentService;

	public UserPosts getPosts(UserPrincipal currentUser, Long userId, int page, int size) {

		UserPosts userPosts = new UserPosts();

		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
		Page<Post> postList = postRepository.findByUserId(userId, pageable);

		userPosts.setTotalElements(postList.getTotalElements());
		userPosts.setTotalPages(postList.getTotalPages());
		userPosts.setNumberOfElements(postList.getNumberOfElements());
		userPosts.setNumber(postList.getNumber());

		List<PostResponse> postResponse = postList.map(post -> {
			return ModelMapper.mapPostToPollResponse(post, post.getUser());
		}).getContent();

		// check in..

		userPosts.setPostList(postResponse);

		return userPosts;
	}

	public UserPosts getFeed(UserPrincipal currentUser, int page, int size) {
		UserPosts userPosts = new UserPosts();

		List<Follow> followedList = followRepository.getFollowedUsers(currentUser.getId());
		List<Long> followedListLong = new ArrayList<Long>();

		for (Follow followed : followedList) {
			followedListLong.add(followed.getFollowedUser().getId());
		}
		followedListLong.add(currentUser.getId());

		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
		Page<Post> postList = postRepository.findByUserIdIn(followedListLong, pageable);

		userPosts.setTotalElements(postList.getTotalElements());
		userPosts.setTotalPages(postList.getTotalPages());
		userPosts.setNumberOfElements(postList.getNumberOfElements());
		userPosts.setNumber(postList.getNumber());

		List<PostResponse> postResponseList = postList.map(post -> {
			return ModelMapper.mapPostToPollResponse(post, post.getUser());
		}).getContent();

		List<Long> postIdList = new ArrayList<Long>();
		for (PostResponse postResponse : postResponseList) {
			postIdList.add(postResponse.getId());
		}

		List<LikeCount> likeCountList = likeService.getLikeCount(postIdList);
		List<ViewerLike> viewerHasLikedList = likeService.viewerHasLikedList(postIdList, currentUser.getId());
		List<Comment> coomentList = commentService.getCommentsByPostIdList(postIdList);

		for (PostResponse postResponse : postResponseList) {
			int indexOfCount = likeCountList.indexOf(new LikeCount(postResponse.getId()));
			if (indexOfCount >= 0) {
				postResponse.setLikeCount(likeCountList.get(indexOfCount).getTotal());
			} else {
				postResponse.setLikeCount(new Long(0));
			}

			int indexOfviewerLike = viewerHasLikedList.indexOf(new ViewerLike(postResponse.getId()));
			if (indexOfviewerLike >= 0) {
				postResponse.setViewerHasLiked(viewerHasLikedList.get(indexOfviewerLike).isActive());
			}

			postResponse.setCommentList(new ArrayList<Comment>());
		}

		for (Comment comment : coomentList) {
			int indexOfPostResponse = postResponseList.indexOf(new PostResponse(comment.getPostId()));
			if (indexOfPostResponse >= 0) {
				postResponseList.get(indexOfPostResponse).getCommentList().add(comment);
			}
		}

		userPosts.setPostList(postResponseList);

		return userPosts;
	}

	public void savePost(Long id, String mediaUrl) {
		Post post = new Post();
		User user = new User();
		user.setId(id);
		post.setUrl(mediaUrl);
		post.setUser(user);
		postRepository.save(post);

	}
}
