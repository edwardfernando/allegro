package com.allegro.api.dao;

import org.springframework.stereotype.Component;

import com.allegro.api.model.Comment;

@Component
public class CommentDAO extends AbstractDAO<Comment> {

	@Override
	public Class<Comment> getDomain() {
		return Comment.class;
	}

}
