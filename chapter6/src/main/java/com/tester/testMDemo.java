package com.tester;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class testMDemo {
	@Test
	public void test1() {
		Assert.assertEquals(1,2);
		System.out.println("test1 run");
	}
	@Test
	public void test2() {
		Assert.assertEquals(1,1);
		System.out.println("test2 run");
	}
	@Test
	public void Logtest3() {
		Reporter.log("This my logs.");
		System.out.println("Logtest3 run");
		throw new RuntimeException("This is RuntimeException.");
	}
	@Test
	public void test4() {
		Assert.assertEquals("aaa","aaa");
		System.out.println("test4 run");
	}
}