package com.sertac.photo.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sertac.photo.model.BucketName;

@Service
public class UploadService {
	private final AmazonS3 s3;

	@Autowired
	PostService postService;

	@Autowired
	public UploadService(AmazonS3 s3) {
		this.s3 = s3;
	}

	private Map<String, String> extractMetadata(MultipartFile file) {
		Map<String, String> metadata = new HashMap<>();
		metadata.put("Content-Type", file.getContentType());
		metadata.put("Content-Length", String.valueOf(file.getSize()));
		return metadata;
	}

	public void upload(Long id, MultipartFile media) {

		String path = String.format("%s/%s", BucketName.PHOTO_APP_MEDIA.getBucketName(), id);
		String filename = String.format("%s.jpg", UUID.randomUUID());

		Map<String, String> metadata = extractMetadata(media);
		ObjectMetadata objectMetadata = new ObjectMetadata();

		Optional.of(metadata).ifPresent(map -> {
			if (!map.isEmpty()) {
				map.forEach(objectMetadata::addUserMetadata);
			}
		});

		String mediaUrl = null;
		try {

			s3.putObject(new PutObjectRequest(path, filename, media.getInputStream(), objectMetadata)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			mediaUrl = s3.getUrl(BucketName.PHOTO_APP_MEDIA.getBucketName(), id + "/" + filename).toExternalForm();

		} catch (AmazonServiceException e) {
			throw new IllegalStateException("Failed to store file to s3", e);
		} catch (SdkClientException e) {
			throw new IllegalStateException("Failed to store file to s3", e);
		} catch (IOException e) {
			throw new IllegalStateException("Failed to store file to s3", e);
		}

		postService.savePost(id, mediaUrl);

	}

}
