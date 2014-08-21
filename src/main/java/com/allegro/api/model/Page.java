package com.allegro.api.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@Document
public class Page extends Model {

	private String url;
	private String title;
	private String content;
	private String threadId;
	private Date threadCreateAt;

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

	@JsonSerialize(using = DateSerializer.class)
	public Date getThreadCreateAt() {
		return threadCreateAt;
	}

	public void setThreadCreateAt(Date threadCreateAt) {
		this.threadCreateAt = threadCreateAt;
	}

}
