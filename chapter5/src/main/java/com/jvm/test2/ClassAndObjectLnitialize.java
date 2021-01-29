package com.jvm.test2;

public class ClassAndObjectLnitialize {
	public static void main(String[] args) {
		ClassLoader t=Test.class.getClassLoader();
		System.out.println(t);
		System.out.println(t.getParent());
		System.out.println(t.getParent().getParent());
	}

	public ClassAndObjectLnitialize(){

		System.out.println("构造方法");
		System.out.println("我是熊孩子我的智商=" + ZhiShang +",情商=" + QingShang);
	}

	{
		System.out.println("普通代码块");
	}

	int ZhiShang = 250;
	static int QingShang = 666;

	static
	{
		System.out.println("静态代码块");
	}
}