package com.javase.internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

public class SoketTest {
	
	@Test
	public void testInetAddress() throws UnknownHostException{
		InetAddress inetAddress = InetAddress.getLocalHost();
		System.out.println(inetAddress);//获取本季IP地址
	}
	/**
	 * 通过IP地址和端口号来定义一个套接字。socket。
	 */
}
