package com.allegro.api.dao;

import org.springframework.stereotype.Component;

import com.allegro.api.model.Page;

@Component
public class PageDAO extends AbstractDAO<Page> {

	@Override
	public Class<Page> getDomain() {
		return Page.class;
	}

}
