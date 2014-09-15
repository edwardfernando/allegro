package com.allegro.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allegro.api.dao.AbstractDAO;
import com.allegro.api.dao.CommentDAO;
import com.allegro.api.model.Comment;

@Component
public class CommentService extends Service<Comment> {

	@Autowired
	private CommentDAO dao;

	@Override
	protected AbstractDAO<Comment> dao() {
		return dao;
	}

}
