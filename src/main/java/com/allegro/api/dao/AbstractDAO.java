package com.allegro.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.allegro.api.model.Model;

public abstract class AbstractDAO<T extends Model> {

	@Autowired
	private MongoTemplate template;

	public abstract Class<T> getDomain();

	public boolean tableExists () {
		return template.collectionExists(getDomain());
	}

	public T save (T object) {

		if (!tableExists()) {
			template.createCollection(getDomain());
		}

		template.save(object);

		return object;
	}

	public T update (T object) {
		if (!tableExists()) {
			template.createCollection(getDomain());
		}
		System.out.println(">>> OBJECT : {}"+object);
		System.out.println(">>> OBJECT ID : {}"+object.getId());

		Query searchQuery = new Query(Criteria.where("id").is(object.getId()));
		//template.findAndModify(searchQuery, Update.fromDBObject(object), object);
		//template.updateFirst(query, update, entityClass)
		return object;
	}

	public T delete (T object) {
		if (!tableExists()) {
			template.createCollection(getDomain());
		}

		Query searchQuery = new Query(Criteria.where("id").is(object.getId()));

		template.remove(object);
		template.remove(searchQuery, object.getClass());
		return object;
	}


	public void get (String id) {
		if (tableExists()) {

		}
	}


}
