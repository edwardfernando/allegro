package com.allegro.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.allegro.api.dao.PageDAO;
import com.allegro.api.model.Page;

@Controller
public class PageController extends com.allegro.api.controller.Controller {

	@Autowired
	private PageDAO dao;

	@RequestMapping(value = "/pages", method = RequestMethod.GET)
	public ResponseEntity<List<Page>> getAll() {
		return new ResponseEntity<List<Page>>(dao.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
	public ResponseEntity<Page> get(@PathVariable("id") String id) {
		return new ResponseEntity<Page>(dao.get(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public ResponseEntity<Page> create(@RequestBody Page page, HttpServletRequest request) {
		String action = request.getParameter("action");

		if (StringUtils.equals(action, "new_thread")) {

		}

		dao.save(page);
		return new ResponseEntity<Page>(HttpStatus.OK);
	}

	@RequestMapping(value = "/page", method = RequestMethod.PUT)
	public ResponseEntity<Page> update(@RequestBody Page page) {
		dao.update(page);
		return new ResponseEntity<Page>(HttpStatus.OK);
	}

	@RequestMapping(value = "/page", method = RequestMethod.DELETE)
	public ResponseEntity<Page> delete(@RequestBody Page page) {
		dao.delete(page);
		return new ResponseEntity<Page>(HttpStatus.OK);
	}

}
