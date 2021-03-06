package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "v1",description = "这是我第一个版本的Demo。")
@RequestMapping("v1")
public class DemoCotroller {
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCount", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户数")
    public int getUserCount() {
        return template.selectOne("getUserCount");
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public int addUser(@RequestBody User user) {
        return template.insert("addUser", user);
    }
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public int updateUser(@RequestBody User user) {
        return template.update("updateUser", user);
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public int deleteUser(@RequestParam int id) {
        return template.update("deleteUser", id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "By this POST Cookies.", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName", required = true) String username,
                        @RequestParam(value = "passWord", required = true) String password) {
        if (username.equals("jack") && password.equals("123")) {
            return "SUCCESS";
        }
        return "Failed";
    }
}
