package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups="stu")
public class groupsOnClass2 {
	public void stu1() {
		System.out.println("groupsOnClass 2=  stu 1 ");
	}
	public void stu2() {
		System.out.println("groupsOnClass 2=  stu 2 ");
	}
}