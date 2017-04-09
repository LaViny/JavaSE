package com.javase.reflect;

public class Person {
	private String name;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.println(this.name);
	}
	public int getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
		System.out.println(this.age);
	}
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		System.out.println("有参构造器");
	}
	
	public Person() {
		System.out.println("无参构造");
	}
	
	
}
