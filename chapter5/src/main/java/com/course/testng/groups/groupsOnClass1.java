package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups="stu")
public class groupsOnClass1 {
	public void stu1() {
		System.out.println("groupsOnClass1  stu 1 ");
	}
	public void stu2() {
		System.out.println("groupsOnClass1  stu 2 ");
	}
}