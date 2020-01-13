package com.sertac.photo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "follow", indexes = { @Index(name = "x_follow_1", columnList = "follower_user_id"),
		@Index(name = "x_follow_2", columnList = "followed_user_id") })

public class Follow implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "follower_user_id", nullable = false)
	private User followerUser;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "followed_user_id", nullable = false)
	private User followedUser;

	private Date followDate;

	private Date unfollowDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getFollowerUser() {
		return followerUser;
	}

	public void setFollowerUser(User followerUser) {
		this.followerUser = followerUser;
	}

	public User getFollowedUser() {
		return followedUser;
	}

	public void setFollowedUser(User followedUser) {
		this.followedUser = followedUser;
	}

	public Date getFollowDate() {
		return followDate;
	}

	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}

	public Date getUnfollowDate() {
		return unfollowDate;
	}

	public void setUnfollowDate(Date unfollowDate) {
		this.unfollowDate = unfollowDate;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4139103460990036911L;

}
