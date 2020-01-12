package com.sertac.photo.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.sertac.photo.model.Comment;
import com.sertac.photo.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	public void comment(Long userId, Long postId, String comment) {
		Comment commentModel = new Comment();
		commentModel.setComment(comment);
		commentModel.setDate(new Date());
		commentModel.setPostId(postId);
		commentModel.setUserId(userId);
		commentRepository.save(commentModel);
	}

	/*
	 * public PostComments getComments(Long postId) { PostComments postComments =
	 * new PostComments();
	 * postComments.setCommentList(commentRepository.findByPostId(postId)); return
	 * postComments; }
	 */
	public List<Comment> getCommentsByPostIdList(List<Long> postIdList) {
		Aggregation agg = newAggregation(match(Criteria.where("postId").in(postIdList)));

		AggregationResults<Comment> groupResults = mongoTemplate.aggregate(agg, Comment.class, Comment.class);

		return groupResults.getMappedResults();

	}
}
