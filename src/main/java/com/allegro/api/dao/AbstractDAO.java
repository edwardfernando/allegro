package com.allegro.api.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.allegro.api.model.Model;

public abstract class AbstractDAO<T extends Model> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected MongoTemplate template;

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

	public T execUnique(String criteria, Object expectedValue) {
		return template.findOne(new Query(Criteria.where(criteria).is(expectedValue)), getDomain());
	}

	public T execUnique(String[] criterias, Object[] expectedValues) {
		Criteria criteria = Criteria.where(criterias[0]).is(expectedValues[0]);

		for (int i = 1; i < criterias.length; i++) {
			criteria.and(criterias[i]).is(expectedValues[i]);
		}

		return template.findOne(new Query(criteria), getDomain());
	}

	public List<T> execList(String criteria, String expectedValue) {
		return template.find(new Query(Criteria.where(criteria).is(expectedValue)), getDomain());
	}

	public abstract Class<T> getDomain();
}
