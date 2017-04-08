package com.javase.reflect;

public class Person {
	private String name;
	private int age;
	private Integer age;
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
