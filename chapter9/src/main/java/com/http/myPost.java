package com.http;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class myPost {
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
	public void testPost() throws IOException {
		String uri = bundle.getString("test.post.with.cookies");
		String testurl=this.url+uri;
		//声明一个client对象，用来进行方法的执行
		DefaultHttpClient client = new DefaultHttpClient();
		//声明一个方法，post方法
		HttpPost httpPost = new HttpPost(testurl);
		//添加参数
		JSONObject param = new JSONObject();
		param.put("name", "jack");
		param.put("age", "18");
		//设置请求头信息
		httpPost.setHeader("content-type","application/json");
		//httpPost.setHeader("content-type","application/json");
		StringEntity entity = new StringEntity(param.toString(), "utf-8");
		httpPost.setEntity(entity);
		String result;
		client.setCookieStore(this.cookieStore2);
		HttpResponse response=client.execute(httpPost);
		result = EntityUtils.toString(response.getEntity(), "utf-8");
		System.out.println(result);
		JSONObject resultjson = new JSONObject(result);
		String success1 = (String) resultjson.get("jack");
		String status1=(String)resultjson.get("status");
		Assert.assertEquals("success", success1);
		Assert.assertEquals("1001", status1);
	}
}