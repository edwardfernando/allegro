package com.allegro.api.dao;

import org.springframework.stereotype.Component;

import com.allegro.api.model.AccessToken;

@Component
public class AccessTokenDAO extends AbstractDAO<AccessToken> {

	@Override
	public Class<AccessToken> getDomain() {
		return AccessToken.class;
	}

}
