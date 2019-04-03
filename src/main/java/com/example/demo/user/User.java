package com.example.demo.user;

import java.util.Date;

import javax.validation.constraints.Size;

public class User {

	
	@Size(min=2, message = "Name should have atleast 2 characters")
	private String name;
	private int id;
	private Date timeStamp;

	public User() {
		
	}
	
	public User(int id, String name, Date timeStamp) {
		super();
		this.id = id;
		this.name = name;
		this.timeStamp = timeStamp;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", timeStamp=" + timeStamp + "]";
	}

}
