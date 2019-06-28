package com.sertac.photo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sertac.photo.model.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
	Optional<Follow> findById(Long id);

	@Query("SELECT COUNT(f.id) from Follow f where f.followedUser.id = :userId and f.unfollowDate is null")
	Long followerCountByUserId(@Param("userId") Long userId);

	@Query("SELECT COUNT(f.id) from Follow f where f.followerUser.id = :userId and f.unfollowDate is null")
	Long followedCountByUserId(@Param("userId") Long userId);

	@Query("SELECT f from Follow f WHERE f.followerUser.id = :followerUserId AND f.followedUser.id = :followedUserId AND f.unfollowDate is null")
	Optional<Follow> getValidFollowRecord(@Param("followerUserId") Long followerUserId,
			@Param("followedUserId") Long followedUserId);

	@Query("SELECT f from Follow f WHERE f.followerUser.id = :followerUserId AND f.unfollowDate is null")
	List<Follow> getFollowedUsers(@Param("followerUserId") Long followerUserId);

}
