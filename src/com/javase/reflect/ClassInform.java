package com.javase.reflect;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class ClassInform {
	/**
	 * 1.关于Class类，是对象照镜子后得到的信息： 某个类的数据成员，方法构造器，实现了哪些接口，继承了哪个父类。
	 * Class对象由系统创建，
	 * 一个类在jvm中只有一个class实例。
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testClass() throws ClassNotFoundException {
		Class clazz = null;
		// 得到Class对象的三种方式。
		// 1.通过 类名.class方式得到
		clazz = Person.class;
		Field[] fields = clazz.getDeclaredFields();
		System.out.println();
		// 2.通过对象调用getClass()方法来获取。
		Object obj = new Person();
		clazz = obj.getClass();
		// 3.通过全类名方式来获取，用的也做多。
		String className = "com.javase.reflect.Person";
		clazz = Class.forName(className);
		System.out.println(clazz);
	}

	@Test
	public void testClassNewInstence() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String className = "com.javase.reflect.Person";
		Class clazz = Class.forName(className);
		// 利用Class对象的newInstance方法来创建类的一个对象。
		// 实际调用的是无参构造器
		// 所以声明有参同时，别忘了声明无参
		Object object = clazz.newInstance();
		System.out.println(object);
	}

	@Test
	public void testClassLoader() throws ClassNotFoundException, IOException {
		// 系统类加载器，可以加载类路径下
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		System.out.println("系统类加载器" + classLoader);
		//
		classLoader = classLoader.getParent();
		System.out.println("扩张类加载器" + classLoader);
		//
		classLoader = classLoader.getParent();
		System.out.println(classLoader);
		//
		classLoader = Class.forName("com.javase.reflect.Person").getClassLoader();
		System.out.println(classLoader);
		//
		classLoader = Class.forName("java.lang.Integer").getClassLoader();
		System.out.println(classLoader);
		// 用流的方式读取项目中的文件，此时用FileInputStream是不行的
		// ，因为这个流是直接跟内存挂钩的。只能通过类加载器来加载。
		InputStream fileInputStream =
				// new FileInputStream("test.properties");
				// 通过getResourceAsStream()方法来获取类路径下的输入流
				this.getClass().getClassLoader().getResourceAsStream("test.properties");
		System.out.println(fileInputStream);
		int i = fileInputStream.read();
		System.out.println(i);
	}

	/**
	 * Class是对一个类的描述。 类的属性：Field 类的方法：method 类的构造器：constructor
	 * 
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testMethod() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class class1 = Class.forName("com.javase.reflect.Person");
		// getDeclaredMethods()方法获取本类中包括private的方法。且只获取当前的类声明的方法。
		// getMethods()不能获取private的方法。
		// 同理 constructor,filed.
		Method[] methods = class1.getMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getName());
			/*
			 * getName setName setAge getAge wait wait wait equals toString
			 * hashCode getClass notify notifyAll
			 */
		}
		Method[] methods1 = class1.getDeclaredMethods();
		for (int i = 0; i < methods1.length; i++) {
			System.out.println(methods1[i].getName());
			/*
			 * getName setName getAge setAge
			 */
		}
		// 获取某个指定方法
		Method method = class1.getDeclaredMethod("setName", String.class);
		System.out.println(method);
		// public void com.javase.reflect.Person.setName(java.lang.String)
		Object object = class1.newInstance();
		// 执行方法
		method.invoke(object, "xiaonan");
	}

	/**
	 * 
	 * @param className
	 *            某个类的全类名
	 * @param methodName
	 *            类的一个方法名
	 * @param args
	 *            调用该方法要传入的参数
	 * @return 调用invoke方法的返回值
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public Object invoke(String className, String methodName, Object... args)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 根据全类名
		Class clazz = Class.forName(className);
		Object object = clazz.newInstance();
		// Class [] parameterTypes = new Class[args.length];
		// for(int i = 0; i < parameterTypes.length; i++){
		// parameterTypes[i] = args[i].getClass();
		// }
		// Method method = object.getClass().getDeclaredMethod(methodName,
		// parameterTypes);
		// return method.invoke(object, args);
		return invoke(object, methodName, args);
	}

	/**
	 * @param obj
	 *            方法执行的那个对象
	 * @param methodName
	 *            类的一个方法名
	 * @param args
	 *            调用该方法需要传入的参数
	 * @return 调用invoke的返回值
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public Object invoke(Object obj, String methodName, Object... args) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class[] parameterTypes = new Class[args.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			parameterTypes[i] = args[i].getClass();
		}
		Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
		return method.invoke(obj, args);
	}

	@Test
	public void testInvoke() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object obj = new Person();
		invoke(obj, "setName", "laven");
		String className = "com.javase.reflect.Person";
		invoke(className, "setAge", 25);
	}
}
