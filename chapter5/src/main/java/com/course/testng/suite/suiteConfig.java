package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class suiteConfig {
	@BeforeSuite
	public void a() {
		System.out.println("BeforeSuite --");
	}
	@AfterSuite
	public void b() {
		System.out.println("AfterSuite ==");
	}
	@BeforeTest
	public void a1() {
		System.out.println("BeforeTest  1 --");
	}
	@AfterTest
	public void b1() {
		System.out.println("AfterTest 1  ==");
	}
}