package com.sertac.photo.model;

public class ViewerLike {
	private Long postId;
	private boolean isActive;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((postId == null) ? 0 : postId.hashCode());
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
		ViewerLike other = (ViewerLike) obj;
		if (postId == null) {
			if (other.postId != null)
				return false;
		} else if (!postId.equals(other.postId))
			return false;
		return true;
	}

	public ViewerLike(Long postId) {
		super();
		this.postId = postId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
