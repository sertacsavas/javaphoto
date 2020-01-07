package com.sertac.photo.payload;

import java.util.List;

import com.sertac.photo.model.Comment;

public class PostComments {
	private List<Comment> commentList;

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
}
