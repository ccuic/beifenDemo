package com.course.testng.mulThread;

import org.testng.annotations.Test;

public class MTOnXML2 {
	@Test
	public void test11() {
		System.out.printf("Thread id is : %s%n",Thread.currentThread().getId());
	}
	@Test
	public void test12() {
		System.out.printf("Thread id is : %s%n",Thread.currentThread().getId());
	}
	@Test
	public void test13() {
		System.out.printf("Thread id is : %s%n",Thread.currentThread().getId());
	}
	@Test
	public void test14() {
		System.out.printf("Thread id is : %s%n",Thread.currentThread().getId());
	}
}