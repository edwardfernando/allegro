package com.allegro.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return new ResponseEntity<List<Page>>(service.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
	public ResponseEntity<Page> get(@PathVariable("id") String id) {
		return new ResponseEntity<Page>(service.get(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public ResponseEntity<Page> create(@RequestBody Page page) {
		service.save(page);
		return new ResponseEntity<Page>(HttpStatus.OK);
	}

	@RequestMapping(value = "/page", method = RequestMethod.PUT)
	public ResponseEntity<Page> update(@RequestBody Page page) {
		service.update(page);
		return new ResponseEntity<Page>(HttpStatus.OK);
	}

	@RequestMapping(value = "/page", method = RequestMethod.DELETE)
	public ResponseEntity<Page> delete(@RequestBody Page page) {
		service.delete(page);
		return new ResponseEntity<Page>(HttpStatus.OK);
	}

}
