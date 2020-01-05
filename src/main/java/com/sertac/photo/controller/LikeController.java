package com.sertac.photo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sertac.photo.security.CurrentUser;
import com.sertac.photo.security.UserPrincipal;

@RestController
@RequestMapping("/api/like")
public class LikeController {

	@GetMapping("/like/{id}")
	public boolean like(@CurrentUser UserPrincipal currentUser, @PathVariable Long id) {
		return true;
	}

	@GetMapping("/unLike/{id}")
	public boolean unLike(@CurrentUser UserPrincipal currentUser, @PathVariable Long id) {
		return true;
	}
}
