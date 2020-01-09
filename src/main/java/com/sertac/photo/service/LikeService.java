package com.sertac.photo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sertac.photo.model.Like;
import com.sertac.photo.repository.LikeRepository;
import com.sertac.photo.security.UserPrincipal;

@Service
public class LikeService {
	@Autowired
	LikeRepository likeRepository;

	public Long getLikeCount(Long postId) {
		return likeRepository.findCountByPostId(postId);
	}

	private Like findLike(Long userId, Long postId) {
		List<Like> likes = likeRepository.findByUserIdAndPostId(userId, postId);

		return likes != null && !likes.isEmpty() ? likes.get(0) : null;
	}

	public boolean viewerHasLiked(UserPrincipal currentUser, Long id) {
		Like currentLike = findLike(currentUser.getId(), id);

		return currentLike != null ? currentLike.isActive() : false;
	}

	private void handleLike(Long userId, Long postId, boolean isActive) {
		Like like = new Like();
		like.setUserId(userId);
		like.setActive(isActive);
		like.setPostId(new Long(postId));

		Like currentLike = findLike(userId, postId);
		like.setId(currentLike != null ? currentLike.getId() : null);

		like.setDate(new Date());
		likeRepository.save(like);
	}

	public void like(UserPrincipal currentUser, Long id) {
		handleLike(currentUser.getId(), id, true);
	}

	public void unLike(Long userId, Long id) {
		handleLike(userId, id, false);
	}
}
