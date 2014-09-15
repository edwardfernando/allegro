package com.allegro.api.model;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.allegro.api.util.JodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Document
public class Page extends Model {

	private String url;
	private String title;
	private String content;

	@NotBlank
	private String threadId;
	private DateTime threadCreateAt;

	@DBRef
	private User user;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	@JsonSerialize(using = JodaDateTimeSerializer.class)
	public DateTime getThreadCreateAt() {
		return threadCreateAt;
	}

	public void setThreadCreateAt(DateTime threadCreateAt) {
		this.threadCreateAt = threadCreateAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
