package com.sertac.photo.payload;

import java.io.Serializable;
import java.util.List;

public class UserPosts implements Serializable {

	List<PostResponse> postList;

	public List<PostResponse> getPostList() {
		return postList;
	}

	public void setPostList(List<PostResponse> postList) {
		this.postList = postList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7654542049931727547L;

}
