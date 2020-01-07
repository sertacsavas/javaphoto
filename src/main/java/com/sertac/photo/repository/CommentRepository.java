package com.sertac.photo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sertac.photo.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

	List<Comment> findByPostId(Long postId);

}
