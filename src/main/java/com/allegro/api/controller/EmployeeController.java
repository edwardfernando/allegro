package com.allegro.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allegro.api.model.Employee;

@Controller
public class EmployeeController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	Map<String, Employee> empData = new HashMap<String, Employee>();

	@RequestMapping(value = "/employee/dummy", method = RequestMethod.GET)
	public @ResponseBody
	Employee getDummyEmployee() {
		logger.info("Start getDummyEmployee");
		Employee emp = new Employee();
		emp.setId("9999");
		emp.setName("Dummy");
		emp.setCreatedDate(new Date());
		empData.put("9999", emp);

		Employee emp1 = new Employee();
		emp1.setId("9998");
		emp1.setName("Dummy 1");
		emp1.setCreatedDate(new Date());
		empData.put("9998", emp1);

		return emp1;
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Employee getEmployee(@PathVariable("id") int empId) {
		logger.info("Start getEmployee. ID=" + empId);
		System.out.println("Start getEmployee. ID=" + empId);

		return empData.get(empId);
	}

	@Autowired
	private MongoTemplate mongoTemplate;

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public @ResponseBody
	List<Employee> getAllEmployees() {
		logger.info("Start getAllEmployees.");
		List<Employee> emps = new ArrayList<Employee>();
		Set<String> empIdKeys = empData.keySet();
		for (String i : empIdKeys) {
			emps.add(empData.get(i));
		}
		return emps;
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public @ResponseBody
	Employee createEmployee(@RequestBody Employee emp) {
		logger.info("Start createEmployee.");
		emp.setCreatedDate(new Date());
		empData.put(emp.getId(), emp);

		if (!mongoTemplate.collectionExists(Employee.class)) {
			mongoTemplate.createCollection(Employee.class);
		}
		emp.setId(UUID.randomUUID().toString());

		System.out.println(emp);

		mongoTemplate.insert(emp, Employee.class.getName());

		return emp;
	}

	@RequestMapping(value = "/employee", method = RequestMethod.PUT)
	public @ResponseBody
	Employee deleteEmployee(@PathVariable("id") int empId) {
		logger.info("Start deleteEmployee.");
		Employee emp = empData.get(empId);
		empData.remove(empId);
		return emp;
	}
}
