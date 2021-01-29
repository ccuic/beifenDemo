package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {
	@Test
	public void testCase1() {
		System.out.println("this is testcase 1.");
	}
	@Test
	public void testCase2() {
		System.out.println("this is testcase 2.");
	}
	@BeforeMethod
	public void a() {
		System.out.println("before method is before run.");
	}

	@AfterMethod
	public void b() {
		System.out.println("after method is after run.");
	}

	@BeforeClass
	public void ca() {
		System.out.println("before class is ");
	}
	@AfterClass
	public void cb() {
		System.out.println("After Class is ");
	}

	@BeforeSuite//把类包含起来了。可以包含多个类。
	public void sa() {
		System.out.println("BeforeSuite---");
	}

	@AfterSuite
	public void sb() {
		System.out.println("AfterSuite==");
	}
}