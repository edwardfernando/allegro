package com.allegro.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allegro.api.model.Page;
import com.typesafe.config.ConfigFactory;

@Controller
public class PageController extends com.allegro.api.controller.Controller {

	@RequestMapping("/pages")
	public @ResponseBody
	List<Page> all() {
		return null;
	}

	@RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
	public ResponseEntity<Page> get(@PathVariable("id") String id) {

		Page page = new Page();
		page.setTitle("titleeee");
		page.setThreadId("AAAAAA");
		page.setUrl("http://kuaskus.com");

		return new ResponseEntity<Page>(page, HttpStatus.OK);
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public ResponseEntity<Page> create(@RequestBody Page page, HttpServletRequest request) {
		String action = request.getParameter("action");

		if (StringUtils.equals(action, "new_thread")) {

		}
		
		logger.debug("Coba typeSafe : {}", ConfigFactory.load().getString("simple-lib.foo"));

		return new ResponseEntity<Page>(page, HttpStatus.OK);
	}

	@RequestMapping(value = "/page", method = RequestMethod.PUT)
	public @ResponseBody
	Page update(@RequestBody Page page) {
		return null;
	}

	@RequestMapping(value = "/page", method = RequestMethod.DELETE)
	public @ResponseBody
	Page destroy(@PathVariable("id") String id) {
		return null;
	}

}
