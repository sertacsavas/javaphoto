package com.sertac.photo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sertac.photo.model.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
	Optional<Follow> findById(Long id);

	@Query("SELECT COUNT(f.id) from Follow f where f.followedUser.id = :userId")
	Long followerCountByUserId(@Param("userId") Long userId);

	@Query("SELECT COUNT(f.id) from Follow f where f.followerUser.id = :userId")
	Long followedCountByUserId(@Param("userId") Long userId);

}
