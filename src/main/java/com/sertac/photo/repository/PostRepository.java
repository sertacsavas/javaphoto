package com.sertac.photo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sertac.photo.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findById(Long pollId);

	// Page<Post> findByCreatedBy(Long userId, Pageable pageable);

	// long countByCreatedBy(Long userId);

	List<Post> findByUserId(Long userId);

	List<Post> findByIdIn(List<Long> postIds);

	List<Post> findByIdIn(List<Long> postIds, Sort sort);
}
