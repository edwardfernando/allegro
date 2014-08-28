package com.allegro.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.allegro.api.model.User;
import com.allegro.api.service.UserService;

@Controller
public class UserController extends com.allegro.api.controller.Controller {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAll() {
		return userService.getAll();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> get(@PathVariable("id") String id) {
		return userService.get(id);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user, HttpServletRequest request) {

		String action = request.getParameter("action");

		if (StringUtils.equals(action, "verify")) {
			return userService.verifyUser(user);
		}

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
}
