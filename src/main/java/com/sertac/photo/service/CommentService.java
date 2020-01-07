package com.sertac.photo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sertac.photo.model.Comment;
import com.sertac.photo.payload.PostComments;
import com.sertac.photo.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;

	public void comment(Long userId, Long postId, String comment) {
		Comment commentModel = new Comment();
		commentModel.setComment(comment);
		commentModel.setDate(new Date());
		commentModel.setPostId(postId);
		commentModel.setUserId(userId);
		commentRepository.save(commentModel);
	}

	public PostComments getComments(Long postId) {
		PostComments postComments = new PostComments();
		postComments.setCommentList(commentRepository.findByPostId(postId));
		return postComments;
	}
}
