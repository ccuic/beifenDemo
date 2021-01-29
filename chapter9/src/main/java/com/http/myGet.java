package com.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class myGet {
	private String url;
	private ResourceBundle bundle;
	private CookieStore cookieStore2;
	@BeforeTest
	public void beforeTest() {
		bundle = ResourceBundle.getBundle("application", Locale.CHINA);
		url = bundle.getString("test.url");
	}
	@Test
	public void testCookies() throws IOException {
		String result;
	String uri = bundle.getString("getCookies.uri");
	HttpGet get = new HttpGet(url+uri);
	DefaultHttpClient client = new DefaultHttpClient();
	HttpResponse response = client.execute(get);
	result= EntityUtils.toString(response.getEntity(), "utf-8");
	System.out.println(result);

	cookieStore2=client.getCookieStore();
	List<Cookie> cookieList=cookieStore2.getCookies();
	for (Cookie cookie : cookieList) {
		System.out.println("cookie name= "+cookie.getName()+" , value="+cookie.getValue());
	}
	}

	@Test(dependsOnMethods = {"testCookies"})
	public void testGetWithCookies() throws IOException {
		String result;
		String uri=bundle.getString("test.get.with.cookies");
		HttpGet get = new HttpGet(url+uri);
		DefaultHttpClient client=new DefaultHttpClient();
		client.setCookieStore(cookieStore2);
		HttpResponse response=client.execute(get);
		int statusCode=response.getStatusLine().getStatusCode();
		System.out.println("statusCode= " + statusCode);
		if (statusCode == 200) {
			result= EntityUtils.toString(response.getEntity(), "utf-8");
			System.out.println(result);
		}

	}
}