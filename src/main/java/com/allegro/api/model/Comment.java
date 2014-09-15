package com.allegro.api.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.allegro.api.util.JodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Document
public class Comment extends Model {

	@Range(min = 0, max = 5)
	private int ratingGiven;

	@NotBlank
	private String comments;
	private DateTime createdDate = DateTime.now();

	@DBRef
	private User user;

	@DBRef
	private Page page;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@JsonSerialize(using = JodaDateTimeSerializer.class)
	public DateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}

	public int getRatingGiven() {
		return ratingGiven;
	}

	public void setRatingGiven(int ratingGiven) {
		this.ratingGiven = ratingGiven;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
