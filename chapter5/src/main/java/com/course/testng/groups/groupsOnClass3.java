package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups="teacher")
public class groupsOnClass3 {
	public void teacher1() {
		System.out.println("groupsOnClass 3--  teacher 1 ");
	}
	public void teacher2() {
		System.out.println("groupsOnClass 3--  teacher 2 ");
	}
}