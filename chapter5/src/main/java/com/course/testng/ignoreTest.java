package com.course.testng;

import org.testng.annotations.Test;

public class ignoreTest {
	@Test
	public void b() {
		System.out.println("ignoreTest  success.");
	}
	@Test(enabled = false)
	public void b2() {
		System.out.println("ignoreTest2  success.");
	}
}