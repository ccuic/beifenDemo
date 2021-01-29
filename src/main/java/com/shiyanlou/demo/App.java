package com.shiyanlou.demo;

import com.shiyanlou.demo.helloworld.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context=new ClassPathXmlApplicationContext("SpringBeans.xml");
		HelloWorld obj=(HelloWorld)context.getBean("helloBean");
		obj.printHello();
	}
}