package com.course.testng.mulThread;

import org.testng.annotations.Test;

public class MTOnXML {
	@Test
	public void test1() {
		System.out.printf("Thread id is : %s%n",Thread.currentThread().getId());
	}
	@Test
	public void test2() {
		System.out.printf("Thread id is : %s%n",Thread.currentThread().getId());
	}
	@Test
	public void test3() {
		System.out.printf("Thread id is : %s%n",Thread.currentThread().getId());
	}
	@Test
	public void test4() {
		System.out.printf("Thread id is : %s%n",Thread.currentThread().getId());
	}
}