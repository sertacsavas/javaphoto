package com.sertac.photo.payload;

import java.io.Serializable;

public class PostResponse implements Serializable {

	private Long id;
	private UserSummary user;
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserSummary getUser() {
		return user;
	}

	public void setUser(UserSummary user) {
		this.user = user;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5400108628669591542L;

}
