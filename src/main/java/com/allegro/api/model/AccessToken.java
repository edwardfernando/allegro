package com.allegro.api.model;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AccessToken extends Model {

	private String token;
	private boolean isActive;
	private DateTime createdDate = DateTime.now();
	private DateTime validUntil;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public DateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}

	public DateTime getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(DateTime validUntil) {
		this.validUntil = validUntil;
	}

	public boolean isExpired() {
		return validUntil.isBeforeNow();
	}
}
