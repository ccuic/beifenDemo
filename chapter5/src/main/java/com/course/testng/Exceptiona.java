package com.course.testng;

import org.testng.annotations.ExpectedExceptions;
import org.testng.annotations.Test;

public class Exceptiona {
	@Test(expectedExceptions = RuntimeException.class)
	public void runTimeException() {
		System.out.println("this is a failed run.");
	}
	@Test(expectedExceptions = RuntimeException.class)
	public void runTime2() {
		System.out.println("this is Exception");
		throw new RuntimeException();
	}
}