package com.allegro.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.allegro.api.model.Comment;
import com.allegro.api.service.CommentService;

@Controller
public class CommentController extends com.allegro.api.controller.Controller {

	@Autowired
	private CommentService service;

	@RequestMapping(value = "/comments", method = RequestMethod.GET)
	public ResponseEntity<List<Comment>> getAll() {
		return service.getAll();
	}

	@RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
	public ResponseEntity<Comment> get(@PathVariable("id") String id) {
		return service.get(id);
	}

	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public ResponseEntity<Comment> create(@RequestBody @Valid Comment Comment) {
		return service.save(Comment);
	}

	@RequestMapping(value = "/comment", method = RequestMethod.PUT)
	public ResponseEntity<Comment> update(@RequestBody Comment Comment) {
		return service.update(Comment);
	}

	@RequestMapping(value = "/comment", method = RequestMethod.DELETE)
	public ResponseEntity<Comment> delete(@RequestBody Comment Comment) {
		return service.delete(Comment);
	}

}
