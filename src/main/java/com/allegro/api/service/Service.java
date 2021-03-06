package com.allegro.api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.allegro.api.annotation.LogProcess;
import com.allegro.api.dao.AbstractDAO;
import com.allegro.api.enumclass.ActionType;
import com.allegro.api.model.Model;

public abstract class Service<T extends Model> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected abstract AbstractDAO<T> dao();

	@LogProcess(className = Model.class, actionType = ActionType.SAVE)
	public ResponseEntity<T> save(T object) {
		dao().save(object);
		return new ResponseEntity<T>(HttpStatus.OK);
	}

	@LogProcess(className = Model.class, actionType = ActionType.UPDATE)
	public ResponseEntity<T> update(T object) {
		dao().update(object);
		return new ResponseEntity<T>(HttpStatus.OK);
	}

	@LogProcess(className = Model.class, actionType = ActionType.DELETE)
	public ResponseEntity<T> delete(T object) {
		dao().delete(object);
		return new ResponseEntity<T>(HttpStatus.OK);
	}

	@LogProcess(className = Model.class, actionType = ActionType.READ)
	public ResponseEntity<List<T>> getAll() {
		return new ResponseEntity<List<T>>(dao().getAll(), HttpStatus.OK);
	}

	@LogProcess(className = Model.class, actionType = ActionType.READ)
	public ResponseEntity<T> get(String id) {
		return new ResponseEntity<T>(dao().get(id), HttpStatus.OK);
	}
}
