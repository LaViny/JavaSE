package com.javase.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;

public class IOBase {
	/**
	 *概述：
	 *输入：读取外部数据（磁盘，光盘等存储设备的数据）到程序（内存）中。
	 *输出：将程序（内存）中的数据输出到磁盘，光盘等存储设备中。
	 * java的IO流主要包括输入，输出两种IO流，每种输入，输出流又可分为
	 * 字节流和字符流。
	 * 字节流：以字节为单位来处理输入，输出操作。
	 * 字符流：以字符为单位来处理输入，输出操作。
	 * @throws IOException 
	 */
	
	/**
	 * File类代表与平台无关的文件和目录。
	 * file能创建，删除，重命名文件和目录，但file不能访问文件的内容本身。
	 * 需要通过使用输入／输出流来访问文件内容本身。
	 * @throws IOException
	 */
	@Test
	public void testFileClass() throws IOException{
		File dir = new File("/Users/LaVine/Documents/test1");
		if (dir.exists()) {
			dir.delete();
		}
		File dir1 = new File("/Users/LaVine/Documents/testFile");
		dir1.mkdir();

		File file = new File("/Users/LaVine/Documents/test2");
		if (file.exists()) {
			file.delete();
		}
		File file1 = new File("/Users/LaVine/Documents/testFile/test.doc");
		file1.createNewFile();
		System.out.println(file1.getName());//test1.doc
		file.getPath();//file path
		System.out.println(file1.getAbsoluteFile());
		System.out.println(file1.getAbsolutePath());
		System.out.println(file1.getParent());
	}
	
	/**
	 * IO流的分类：
	 * 1.按方向分：1⃣.输入流
	 * 			2⃣.输出流
	 * 2.按处理的单位：1⃣。字节流（8位的字节）。以inputStream,outputStream结尾的。
	 * 				2⃣。字符流（16位的字节）。以reader，和writer结尾的。
	 * 3.按流的角色：
	 * 			1⃣。节点流。可以从一个特定的IO设备读／写数据的流。
	 * 			2⃣。处理流。对一个已经存在的流进行连接和封装，通过封装后的流来实现数据读／写操作。
	 * @throws IOException 
	 * 
	 */
	@Test
	public void testInputStreamAndReader() throws IOException{
		
		File file = new File("/Users/LaVine/Documents/testFile/test.doc");
		InputStream is = new FileInputStream(file);
		byte[] bs = {12,34,56};
		int i = is.read(bs,0,1);
		System.out.println(i);
		
		Reader rd = new FileReader(file);
		char [] cs = {'a','b','c','d','e'};
		int j = rd.read(cs,0,3);
		System.out.println(j);
		/*
		 * 注意：
		 * 程序中打开的文件IO资源不属于内存里的资源，垃圾回收机制无法
		 * 回收该资源，所以应该显式的关闭文件IO资源。
		 */
		is.close();
		rd.close();
	}
	
	@Test
	public void testOuputStreamAndWriter() throws IOException{
		OutputStream os =
				new FileOutputStream("/Users/LaVine/Documents/testFile/test.doc");
		os.write(1);//输出一个字节到指定文件
		File file = new File("/Users/LaVine/Documents/testFile/test1.doc");
		//file.createNewFile();
		Writer writer = new FileWriter(file);
		writer.write("nihao");
	}
	
	/**
	 * randomAccessFile类既可以读取文件内容，也可以向文件中输出数据。
	 * RandomAccessFile类支持“随机访问”。程序可以直接跳到文件的任意地方来读写文件。
	 * 1.支持只访问文件的部分内容。
	 * 2.可以向已存在的文件后追加内容。
	 * randomAccessFile对象包含一个记录指针。用以标示当前读写处的位置。可以自由移动指针。
	 * @throws IOException 
	 */
	@Test
	public void testRandomAccessFile() throws IOException{
		File file = new File("/Users/LaVine/Documents/testFile/test1.doc");
		RandomAccessFile ras = new RandomAccessFile(file,"rw");	//第二个参数"r"表示只读，"rw"表示可读写.
		ras.writeChars("nihao");
		long l = ras.getFilePointer();//获取指针当前位置
		System.out.println(l);
		ras.seek(4);//将指针移到某个位置
		String string = ras.readLine();
		System.out.println(string);
	}
}
