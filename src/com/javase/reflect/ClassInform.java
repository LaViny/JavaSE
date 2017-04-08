package com.javase.reflect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import org.junit.Test;

public class ClassInform {
	/**
	 * 1.关于Class类，是对象照镜子后得到的信息：
	 * 	某个类的数据成员，方法构造器，实现了哪些接口，继承了哪个父类。
	 * Class对象又系统创建，
	 * 一个类在jvm中只有一个class实例。
	 * @throws ClassNotFoundException 
	 */	
	@Test
	public void testClass() throws ClassNotFoundException{
		Class clazz = null;
//		得到Class对象的三种方式。
//		1.通过 类名.class方式得到
		clazz = Person.class;
		Field [] fields = clazz.getDeclaredFields();
		System.out.println();
//		2.通过对象调用getClass()方法来获取。
		Object obj = new Person();
		clazz = obj.getClass();
//		3.通过全类名方式来获取，用的也做多。
		String className = "com.javase.reflect.Person";
		clazz = Class.forName(className);
		System.out.println(clazz);
	}
	
	@Test
	public void testClassNewInstence() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		String className = "com.javase.reflect.Person";
		Class clazz = Class.forName(className);
		//利用Class对象的newInstance方法来创建类的一个对象。
		//实际调用的是无参构造器
		//所以声明有参同时，别忘了声明无参
		Object object = clazz.newInstance();
		System.out.println(object);
	}
	
	@Test
	public void testClassLoader() throws ClassNotFoundException, IOException{
		//系统类加载器，可以加载类路径下
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		System.out.println("系统类加载器"+classLoader);
		//
		classLoader = classLoader.getParent();
		System.out.println("扩张类加载器"+classLoader);
		//
		classLoader = classLoader.getParent();
		System.out.println(classLoader);
		//
		classLoader = Class.forName("com.javase.reflect.Person").getClassLoader();
		System.out.println(classLoader);
		//
		classLoader = Class.forName("java.lang.Integer").getClassLoader();
		System.out.println(classLoader);
		//用流的方式读取项目中的文件，此时用FileInputStream是不行的
		//，因为这个流是直接跟内存挂钩的。只能通过类加载器来加载。
		InputStream fileInputStream = 
//				new FileInputStream("test.properties");
				//通过getResourceAsStream()方法来获取类路径下的输入流
				this.getClass().getClassLoader().getResourceAsStream("test.properties");
		System.out.println(fileInputStream);
		int i = fileInputStream.read();
		System.out.println(i);
	}
}
