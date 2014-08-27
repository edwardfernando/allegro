package com.allegro.api.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.allegro.api.dao.UserDAO;
import com.allegro.api.model.User;
import com.allegro.api.service.UserService;

@Controller
public class UserController extends com.allegro.api.controller.Controller {

	@Autowired
	UserDAO userDAO;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAll() {
		//		User user = new User();
		//		user.setKaskusId("0980");
		//		user.setToken("lkjljl");
		//		user.setVerified(false);
		//		user.setVerifiedDate(DateTime.now());
		//
		//		userDAO.save(user);
		return userService.getAll();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> get(@PathVariable("id") String id) {
		return userService.get(id);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user) {
		logger.debug(">>> USer : {}",user);
		return userService.save(user);
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<User> update(@RequestBody User user) {
		return userService.update(user);
	}

	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public ResponseEntity<User> delete(@RequestBody User user) {
		return userService.delete(user);
	}

	@RequestMapping(value = "/user/verify", method = RequestMethod.POST)
	public ResponseEntity<User> testVerify(@RequestBody User user) {
		try {
			userService.verifyUser(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<User>(HttpStatus.OK);
	}
}
