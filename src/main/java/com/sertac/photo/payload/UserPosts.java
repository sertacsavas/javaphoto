package com.sertac.photo.payload;

import java.io.Serializable;
import java.util.List;

public class UserPosts implements Serializable {

	private List<PostResponse> postList;
	private long totalElements;
	private int totalPages;
	private int numberOfElements;
	private int number;

	public List<PostResponse> getPostList() {
		return postList;
	}

	public void setPostList(List<PostResponse> postList) {
		this.postList = postList;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7654542049931727547L;

}
