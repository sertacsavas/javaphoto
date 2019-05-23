package com.sertac.photo.payload;

public class UserSummary {
	private Long id;
	private String username;
	private String name;
	private Long followerCount;
	private Long followedCount;

	public UserSummary(Long id, String username, String name, Long followerCount, Long followedCount) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.followerCount = followerCount;
		this.followedCount = followedCount;
	}

	public UserSummary() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(Long followerCount) {
		this.followerCount = followerCount;
	}

	public Long getFollowedCount() {
		return followedCount;
	}

	public void setFollowedCount(Long followedCount) {
		this.followedCount = followedCount;
	}

}
