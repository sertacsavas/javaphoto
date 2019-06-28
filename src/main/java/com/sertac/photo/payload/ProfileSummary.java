package com.sertac.photo.payload;

public class ProfileSummary {
	private Long id;
	private String username;
	private String name;
	private Long followerCount;
	private Long followedCount;
	private boolean following;

	public ProfileSummary() {
		super();
	}

	public ProfileSummary(Long id, String username, String name, Long followerCount, Long followedCount,
			boolean following) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.followerCount = followerCount;
		this.followedCount = followedCount;
		this.following = following;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public Long getFollowerCount() {
		return followerCount;
	}

	public Long getFollowedCount() {
		return followedCount;
	}

	public boolean isFollowing() {
		return following;
	}

	public void setFollowing(boolean following) {
		this.following = following;
	}

}
