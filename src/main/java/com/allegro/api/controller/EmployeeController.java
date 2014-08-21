package com.allegro.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allegro.api.model.Employee;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class EmployeeController extends com.allegro.api.controller.Controller {

	@Autowired
	private MongoTemplate mongoTemplate;

	Map<String, Employee> empData = new HashMap<String, Employee>();

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Employee getEmployee(@PathVariable("id") int empId) {
		logger.info("Test Logger");
		
		Employee e = new Employee();
		e.setName("Edward Fernando");
		e.setCreatedDate(new Date());
		e.setId("1234");
		
		logger.debug("Employee : {}", e);
		
		return e;
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public @ResponseBody
	List<Employee> getAllEmployees() {
		return null;
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public @ResponseBody
	Employee createEmployee(@RequestBody Employee emp) {
		return null;
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	Employee deleteEmployee(@PathVariable("id") int empId) {
		return null;
	}

}
