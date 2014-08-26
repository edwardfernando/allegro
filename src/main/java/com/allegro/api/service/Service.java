package com.allegro.api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.allegro.api.dao.AbstractDAO;
import com.allegro.api.model.Model;

public abstract class Service<T extends Model> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected abstract AbstractDAO<T> dao();

	public void save(T object) {
		dao().save(object);
	}

	public void update(T object) {
		dao().update(object);
	}

	public void delete(T object) {
		dao().delete(object);
	}

	public List<T> getAll() {
		return dao().getAll();
	}

	public T get(String id) {
		return dao().get(id);
	}
}
