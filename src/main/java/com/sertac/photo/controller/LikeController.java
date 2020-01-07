package com.sertac.photo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sertac.photo.security.CurrentUser;
import com.sertac.photo.security.UserPrincipal;
import com.sertac.photo.service.LikeService;

@RestController
@RequestMapping("/api/like")
public class LikeController {

	@Autowired
	LikeService likeService;

	@GetMapping("/like/{id}")
	public void like(@CurrentUser UserPrincipal currentUser, @PathVariable Long id) {
		likeService.like(currentUser, id);
	}

	@GetMapping("/unLike/{id}")
	public void unLike(@CurrentUser UserPrincipal currentUser, @PathVariable Long id) {
		likeService.unLike(currentUser.getId(), id);
	}
}
