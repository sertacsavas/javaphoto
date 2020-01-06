package com.sertac.photo.payload;

import java.io.Serializable;

public class PostResponse implements Serializable {

	private Long id;
	private UserSummary userSummary;
	private String url;
	private boolean viewerHasLiked;
	private Long likeCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserSummary getUserSummary() {
		return userSummary;
	}

	public void setUserSummary(UserSummary userSummary) {
		this.userSummary = userSummary;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isViewerHasLiked() {
		return viewerHasLiked;
	}

	public void setViewerHasLiked(boolean viewerHasLiked) {
		this.viewerHasLiked = viewerHasLiked;
	}

	public Long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5400108628669591542L;

}
