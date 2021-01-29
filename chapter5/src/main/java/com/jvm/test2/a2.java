package com.jvm.test2;

public class a2 {
	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader classLoader=a2.class.getClassLoader();
		//1、使用ClassLoader.loadClass()来加载类，不会执行初始化块
		//classLoader.loadClass("com.jvm.test2.Demo");

		//2、使用Class.forName()来加载类，默认会执行初始化块
		//Class.forName("com.jvm.test2.Demo");

		//3、使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
		Class.forName("com.jvm.test2.Demo",false,classLoader);
	}
}