package com.sertac.photo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sertac.photo.security.CurrentUser;
import com.sertac.photo.security.UserPrincipal;
import com.sertac.photo.service.FollowService;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

	@Autowired
	FollowService followService;

	@GetMapping("/follow/{userId}")
	public void follow(@CurrentUser UserPrincipal currentUser, @PathVariable Long userId) {
		followService.follow(currentUser.getId(), userId);
	}

	@GetMapping("/unFollow/{userId}")
	public void unFollow(@CurrentUser UserPrincipal currentUser, @PathVariable Long userId) {
		followService.unFollow(currentUser.getId(), userId);
	}

}
