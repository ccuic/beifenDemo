package com.course.testng;

import org.testng.annotations.Test;

public class TimeOutTest {
	@Test(timeOut = 10)//依赖的测试如果失败，这次测试不执行。
	public void test2() throws InterruptedException {
		System.out.println("TimeOutTest run");
		Thread.sleep(4);
	}
}