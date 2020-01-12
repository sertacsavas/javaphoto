package com.sertac.photo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sertac.photo.payload.CommentRequest;
import com.sertac.photo.security.CurrentUser;
import com.sertac.photo.security.UserPrincipal;
import com.sertac.photo.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping("/comment")
	public void comment(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody CommentRequest commentRequest) {
		commentService.comment(currentUser.getId(), commentRequest.getPostId(), commentRequest.getComment());
	}
	/*
	 * @GetMapping("/getComments/{postId}") public PostComments
	 * getComments(@PathVariable Long postId) { return
	 * commentService.getComments(postId); }
	 */
}
