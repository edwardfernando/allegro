package com.allegro.api.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@Document
public class Employee {
	private static final long serialVersionUID = -7788619177798333712L;

	@Id
	private String id;
	private String name;
	private Date createdDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
    public String toString() {
	    return "Employee [id=" + id + ", name=" + name + ", createdDate=" + createdDate + "]";
    }
	
	
}
