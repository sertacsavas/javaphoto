package com.sertac.photo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sertac.photo.security.CurrentUser;
import com.sertac.photo.security.UserPrincipal;
import com.sertac.photo.service.UploadService;

@RestController
@RequestMapping("/api/upload")
public class UploadContreller {

	@Autowired
	UploadService uploadService;

	@PostMapping(path = "/media/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void upload(@CurrentUser UserPrincipal currentUser, @RequestParam("media") MultipartFile media) {
		uploadService.upload(currentUser.getId(), media);

	}
}
