package com.sertac.photo.payload;

import java.io.Serializable;
import java.util.List;

import com.sertac.photo.model.Comment;

public class PostResponse implements Serializable {

	private Long id;
	private UserSummary userSummary;
	private String url;
	private boolean viewerHasLiked;
	private Long likeCount;
	private List<Comment> commentList;

	public PostResponse(Long id) {
		super();
		this.id = id;
	}

	public PostResponse() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostResponse other = (PostResponse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

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

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5400108628669591542L;

}
