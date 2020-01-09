package com.sertac.photo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sertac.photo.model.Like;

@Repository
public interface LikeRepository extends MongoRepository<Like, String> {
	@Query(value = "{'postId': ?0, 'isActive': true}", count = true)
	Long findCountByPostId(Long postId);

	List<Like> findByUserIdAndPostId(Long userId, Long postId);
}
