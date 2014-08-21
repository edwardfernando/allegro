package com.allegro.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.allegro.api.model.Model;

public abstract class AbstractDAO<T extends Model> {

	@Autowired
	private MongoTemplate template;

	public abstract Class<T> getDomain();

	public T save (T object) {
		if (tableExists()) {

		}
		return object;
	}

	public boolean tableExists () {
		return template.collectionExists(getDomain());
	}
}
