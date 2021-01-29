package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/", description = "This my Post menthods.")
@RequestMapping("/v1")
public class MyPostMethod {
	private static Cookie cookie;

	//用户获取cookies，在访问接口
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "By this POST Cookies.", httpMethod = "POST")
	public String login(HttpServletResponse response,
						@RequestParam(value = "userName", required = true) String username,
						@RequestParam(value = "passWord", required = true) String password) {
		if (username.equals("jack") && password.equals("123")) {
			cookie = new Cookie("login", "true");
			response.addCookie(cookie);
			return "SUCCESS";
		}
		return "Failed";
	}

	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	@ApiOperation(value = "获取用户列表post", httpMethod = "POST")
	public String getUserList(HttpServletRequest request,
							  @RequestBody User user) {
		User user1;
		Cookie[] cookies=request.getCookies();
		//request.get
				//HttpServletResponse  response;response.set
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("login")
					&& cookie.getValue().equals("true")
					&& user.getUserName().equals("jack")
					&& user.getPassword().equals("123")) {
				user = new User();
				user.setName("Tom ");
				user.setAge("19");
				user.setSex("man");
				return user.toString();
			}
		}
		return "参数不合法";
	}
}