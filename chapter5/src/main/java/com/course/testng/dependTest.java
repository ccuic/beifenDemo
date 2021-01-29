package com.course.testng;

import org.testng.annotations.Test;

public class dependTest {
	@Test
	public void test1() {
		System.out.println("test1 run");
		throw new RuntimeException();
	}
	@Test(dependsOnMethods = {"test1"})//依赖的测试如果失败，这次测试不执行。
	public void test2() {
		System.out.println("test2 run");
	}
}