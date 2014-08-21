package com.allegro.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allegro.api.model.Page;

@Controller
public class PageController extends com.allegro.api.controller.Controller {

	@RequestMapping("/pages")
	public @ResponseBody
	List<Page> all() {
		return null;
	}

	@RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Page get(@PathVariable("id") String id) {

		if (!template.collectionExists(Page.class.getName())) {
			template.createCollection(Page.class.getName());
		}
		
		Page page = new Page();
		page.setTitle("titleeee");
		page.setThreadId("AAAAAA");
		page.setUrl("http://kuaskus.com");
		
		template.save(page);

		return page;
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public @ResponseBody
	Page create(@RequestBody Page page) {
		return null;
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

	@Autowired
	private MongoTemplate template;

}
