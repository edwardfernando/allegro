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

import com.allegro.api.model.Page;
import com.allegro.api.service.PageService;

@Controller
public class PageController extends com.allegro.api.controller.Controller {

	@Autowired
	private PageService service;

	@RequestMapping(value = "/pages", method = RequestMethod.GET)
	public ResponseEntity<List<Page>> getAll() {
		return service.getAll();
	}

	@RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
	public ResponseEntity<Page> get(@PathVariable("id") String id) {
		return service.get(id);
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public ResponseEntity<Page> create(@RequestBody @Valid Page page) {
		return service.save(page);
	}

	@RequestMapping(value = "/page", method = RequestMethod.PUT)
	public ResponseEntity<Page> update(@RequestBody Page page) {
		return service.update(page);
	}

	@RequestMapping(value = "/page", method = RequestMethod.DELETE)
	public ResponseEntity<Page> delete(@RequestBody Page page) {
		return service.delete(page);	
	}

}
