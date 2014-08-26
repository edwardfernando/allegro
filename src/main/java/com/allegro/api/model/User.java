package com.allegro.api.model;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import com.allegro.api.util.JodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Document
public class User extends Model {
	private String kaskusId;
	private String token;
	private boolean verified;
	private DateTime verifiedDate;

	public String getKaskusId() {
		return kaskusId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public DateTime getVerifiedDate() {
		return verifiedDate;
	}

	@JsonSerialize(using = JodaDateTimeSerializer.class)
	public void setVerifiedDate(DateTime verifiedDate) {
		this.verifiedDate = verifiedDate;
	}

	public void setKaskusId(String kaskusId) {
		this.kaskusId = kaskusId;
	}
}
