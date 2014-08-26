package com.allegro.api.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.allegro.api.model.Model;

public abstract class AbstractDAO<T extends Model> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MongoTemplate template;

	public boolean tableExists() {
		return template.collectionExists(getDomain());
	}

	public void save(T object) {
		template.insert(object);
	}

	public void update(T object) {
		template.save(object);
	}

	public void delete(T object) {
		template.remove(object);
	}

	public List<T> getAll() {
		return template.findAll(getDomain());
	}

	public T get(String id) {
		return template.findById(id, getDomain());
	}

	public abstract Class<T> getDomain();
}
