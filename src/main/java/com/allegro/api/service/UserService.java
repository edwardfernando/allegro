package com.allegro.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allegro.api.dao.AbstractDAO;
import com.allegro.api.dao.UserDAO;
import com.allegro.api.model.User;

@Component
public class UserService extends Service<User> {

	@Autowired
	private UserDAO userDAO;

	@Override
	protected AbstractDAO<User> dao() {
		return userDAO;
	}

}
