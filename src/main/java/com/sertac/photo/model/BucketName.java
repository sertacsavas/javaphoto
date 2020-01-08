package com.sertac.photo.model;

public enum BucketName {

	PHOTO_APP_MEDIA("photo-app-media");

	private final String bucketName;

	BucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getBucketName() {
		return bucketName;
	}

}
