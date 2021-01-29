package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class groupOnMethod {
	@Test(groups = "server")
	public void b() {
		System.out.println("a1  success.");
	}
	@Test(groups = "server")
	public void b2() {
		System.out.println("a2  success.");
	}
	@Test(groups = "client")
	public void c() {
		System.out.println("c1  success.");
	}
	@Test(groups = "client")
	public void c2() {
		System.out.println("c2  success.");
	}

	@BeforeGroups("server")
	public void beforeGroupOnServer() {
		System.out.println("before  GroupOnServer");
	}
	@AfterGroups("server")
	public void afterGroupOnServer() {
		System.out.println("after   GroupOnServer");
	}

	@BeforeGroups("client")
	public void beforeGroupOnclient() {
		System.out.println("before  ----");
	}
	@AfterGroups("client")
	public void afterGroupOnclient() {
		System.out.println("after   =====");
	}
}