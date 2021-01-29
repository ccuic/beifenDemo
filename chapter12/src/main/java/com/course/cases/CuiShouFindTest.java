package com.course.cases;

import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.AuthName;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import com.course.utils.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class CuiShouFindTest {

    @BeforeTest(groups = "loginTrue",description = "测试准备工作,获取Authorization对象")
    public void beforeTest(){
        TestConfig.Auth = ConfigFile.getProp(AuthName.AUTH);
    }

    @Test(groups = "CuiShouFind",description = "催收系统查询接口")
    public void loginTrue2() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //下边的代码为写完接口的测试代码
        String result = getResult_Utils();
        //处理结果，就是判断返回结果是否符合预期
        if(result.contains("操作成功"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    private String getResult(LoginCase loginCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost("http://172.16.1.83:30002/api/clock/remind/list");
        //String jsonStr = "{\"pageNum\":1,\"pageSize\":20,\"order\":null,\"remindType\":\"\",\"acctNo\":\"3_18071017A3D01B97475041E58B6D0BE6F13F20A5\",\"startTime\":\"\",\"endTime\":\"\"}";
        JSONObject param = new JSONObject();
        param.put("pageNum",1);
        param.put("pageSize",20);
        param.put("order","");
        param.put("remindType","");
        param.put("acctNo","3_18071017A3D01B97475041E58B6D0BE6F13F20A5");
        param.put("startTime","");
        param.put("endTime","");
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        post.setHeader("Authorization","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjdXJyZW50VGltZU1pbGxpcyI6IjE2MDY4NzgzNzQ1OTEiLCJleHAiOjE2MDY5MjE1NzQsImFjY291bnQiOiJhZG1pbiJ9.e-o-GYs6dVsFkT8_6sNzqZiLlZj1hJwpMKLizIFAOjA");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return result;
    }

    private String getResult_Utils() throws IOException {
        //下边的代码为写完接口的测试代码
        String url="http://172.16.1.83:30002/api/clock/remind/list";
        JSONObject params = new JSONObject();
        params.put("pageNum",1);
        params.put("pageSize",20);
        params.put("order","");
        params.put("remindType","");
        params.put("acctNo","3_18071017A3D01B97475041E58B6D0BE6F13F20A5");
        params.put("startTime","");
        params.put("endTime","");
        String authName="Authorization";
        String authValue=TestConfig.Auth;
        JSONObject jsonObject= HttpUtil.post_with_Auth(url,params,authName,authValue);
        System.out.println(jsonObject);
        //TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return jsonObject.toJSONString();
    }
}
