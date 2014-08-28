package com.allegro.api.dao;

import org.springframework.stereotype.Component;

import com.allegro.api.model.User;

@Component
public class UserDAO extends AbstractDAO<User>{

	@Override
	public Class<User> getDomain() {
		return User.class;
	}

	public User findByKaskusId(User user) {
		User userFound = this.execUnique("kaskusId", user.getKaskusId());
		return userFound;
	}

}
