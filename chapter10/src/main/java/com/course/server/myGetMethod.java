package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value="/",description = "This is my all methods.")
public class myGetMethod {
	@RequestMapping(value="/get/cookies",method = RequestMethod.GET)
	@ApiOperation(value="By this can get Cookies.",httpMethod ="GET" )
	public String getCookies(HttpServletResponse response) {
		Cookie cookie = new Cookie("login", "true");
		response.addCookie(cookie);
		return "This is a need cookies GET12.";
	}
	@RequestMapping(value="/get/with/cookies2",method = RequestMethod.GET)
	@ApiOperation(value="By this 222 Cookies.",httpMethod ="GET" )
	public String getWithCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(Objects.isNull(cookies)){
			return "你必须带cookies来。";
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("login")&&
			cookie.getValue().equals("true")) {
				return "This is a need cookies GET 02.";
			}
		}
		return "aa";
	}

	//获取商品列表
	@RequestMapping(value="/get/with/param",method = RequestMethod.GET)
	@ApiOperation(value="By this param 33 Cookies.",httpMethod ="GET" )
	public Map<String, Integer> getList(@RequestParam Integer start, @RequestParam Integer end) {
		Map<String, Integer> myList = new HashMap<>();
		myList.put("book", 110);
		myList.put("bed", 3500);
		myList.put("clothes", 230);
		return myList;
	}

	/***
	 *路径形式
	 */
	@RequestMapping(value="/get/with/myGetList/{start}/{end}")
	@ApiOperation(value="By this param 44 Cookies.",httpMethod ="GET" )
	public Map myGetList(@PathVariable Integer start, @PathVariable Integer end) {
		Map<String, Integer> myList = new HashMap<>();
		myList.put("book", 110);
		myList.put("bed", 3500);
		myList.put("clothes", 230);
		return myList;
	}
}