package com.course.testng.mulThread;

import org.testng.annotations.Test;

public class MuThreadOnAnnotion {
	@Test(invocationCount =10,threadPoolSize = 3)
	public void test() {
		System.out.println(1);
		System.out.printf("Thread id is : %s%n",Thread.currentThread().getId());
	}
}