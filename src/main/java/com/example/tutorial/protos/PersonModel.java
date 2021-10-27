package com.example.tutorial.protos;

public class PersonModel {
	
	public int id;
	public String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "PersonModel [id=" + id + ", name=" + name + "]";
	}


	
	

}
