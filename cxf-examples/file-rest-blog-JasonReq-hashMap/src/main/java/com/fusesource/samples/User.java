package com.fusesource.samples;

import java.util.Map;

public class User {
	
	private String name;
	private int age;
	private Map msgs;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Map getMsgs() {
		return msgs;
	}
	public void setMsgs(Map msgs) {
		this.msgs = msgs;
	}

}
