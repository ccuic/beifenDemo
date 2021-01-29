package com.course.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class dataproviderTest {
	@Test(dataProvider = "data")
	public void dataTest1(String name,int age) {
		System.out.println("name= "+name+"; age= "+age);
	}

	@DataProvider(name="data")
	public Object[][] providerData() {
		Object[][] o=new Object[][]{
				{"zhangsan2",30},
				{"jack",32},
				{"lisi",34}
		};
		return o;
	}
	@Test(dataProvider = "data2")
	public void Test1(String name,int age) {
		System.out.println("test1  name= "+name+"; age= "+age);
	}
	@Test(dataProvider = "data2")
	public void Test2(String name,int age) {
		System.out.println("test2  name= "+name+"; age= "+age);
	}
	@DataProvider(name="data2")
	public Object[][] providerData2(Method method) {
		Object[][] o=null;
		if (method.getName().equals("Test1")) {
			o=new Object[][]{
					{"Bob li 1",30},
					{"Loe aa 1",32}, 
					{"kaka 1",34}
			};
		} else {
			o=new Object[][]{
					{"Tom 2",60},
					{"PK 2",72},
					{"ZZZG 2",84}
			};
		}
		return o;
	}
}