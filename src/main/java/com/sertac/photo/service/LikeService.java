package com.sertac.photo.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.sertac.photo.model.Like;
import com.sertac.photo.model.LikeCount;
import com.sertac.photo.model.ViewerLike;
import com.sertac.photo.repository.LikeRepository;
import com.sertac.photo.security.UserPrincipal;

@Service
public class LikeService {
	@Autowired
	LikeRepository likeRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	public List<LikeCount> getLikeCount(List<Long> postIdList) {

		Aggregation agg = newAggregation(match(Criteria.where("isActive").in(true)),
				match(Criteria.where("postId").in(postIdList)), group("postId").count().as("total"),
				project("total").and("postId").previousOperation(), sort(Sort.Direction.DESC, "total")

		);

		// Convert the aggregation result into a List
		AggregationResults<LikeCount> groupResults = mongoTemplate.aggregate(agg, Like.class, LikeCount.class);
		List<LikeCount> result = groupResults.getMappedResults();

		return result;
	}

	public List<ViewerLike> viewerHasLikedList(List<Long> postIdList, Long userId) {

		Aggregation agg = newAggregation(match(Criteria.where("isActive").in(true)),
				match(Criteria.where("postId").in(postIdList)), match(Criteria.where("userId").in(userId)));

		AggregationResults<ViewerLike> groupResults = mongoTemplate.aggregate(agg, Like.class, ViewerLike.class);
		List<ViewerLike> result = groupResults.getMappedResults();

		return result;
	}

	private Like findLike(Long userId, Long postId) {
		List<Like> likes = likeRepository.findByUserIdAndPostId(userId, postId);

		return likes != null && !likes.isEmpty() ? likes.get(0) : null;
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
