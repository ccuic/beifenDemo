package com.course.IVR;

import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.AuthName;
import com.course.model.IVR_API;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import com.course.utils.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class IVRTest {

    @BeforeTest(groups = "loginTrue",description = "测试准备工作,获取Authorization对象")
    public void beforeTest(){
        TestConfig.strIVR_URL = ConfigFile.getProp(IVR_API.IVR_URL);
    }
//5.1 放款银行卡及金额查询接口
    @Test(groups = "getLoanInfo",description = "5.1 放款银行卡及金额查询接口")
    public void getLoanInfo() throws IOException {
        String jiekou_url="/info/ivr/getLoanInfo";
        String url= TestConfig.strIVR_URL+jiekou_url;
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"13810381202\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";
        String result = gongyong(url,number);
        System.out.println("================ "+result);
        //处理结果，就是判断返回结果是否符合预期
        if(result.contains("success"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }

    //5.2 用户额度查询接口
    @Test(groups = "getActualAmount",description = "5.2 用户额度查询接口")
    public void getActualAmount() throws IOException {
        String jiekou_url="/info/ivr/getActualAmount";
        String url= TestConfig.strIVR_URL+jiekou_url;
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"13810381202\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";
        String result = gongyong(url,number);
        System.out.println("================ "+result);
        //处理结果，就是判断返回结果是否符合预期
        if(result.contains("success"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    //5.3 用户信息查询
    @Test(groups = "userInfo")
    public void userInfo() throws IOException {
        String jiekou_url="/info/userInfo";
        String url= TestConfig.strIVR_URL+jiekou_url;
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"13810381202\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";

        String result = gongyong(url,number);
        System.out.println("================ "+result);
        //处理结果，就是判断返回结果是否符合预期
        if(result.contains("success"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    //5.4 智能 ivr 发送短信-1  email--邮件短信
    @Test(groups = "userInfo")
    public void sendSms_email() throws IOException {
        String jiekou_url="/ivr/sendSms";
        String url= TestConfig.strIVR_URL+jiekou_url;
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"13810381202\",\"type\":\"email\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";

        String result = gongyong(url,number);
        System.out.println("================ "+result);
        //处理结果，就是判断返回结果是否符合预期
        if(result.contains("success"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    //5.4 智能 ivr 发送短信-2 bankCard-绑卡短信
    @Test(groups = "userInfo")
    public void sendSms_bankCard() throws IOException {
        String jiekou_url="/ivr/sendSms";
        String url= TestConfig.strIVR_URL+jiekou_url;
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"13810381202\",\"type\":\"bankCard\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";

        String result = gongyong(url,number);
        System.out.println("================ "+result);
        //处理结果，就是判断返回结果是否符合预期
        if(result.contains("success"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    //5.4 智能 ivr 发送短信-3  repay--还款短信
    @Test(groups = "userInfo")
    public void sendSms_repay() throws IOException {
        String jiekou_url="/ivr/sendSms";
        String url= TestConfig.strIVR_URL+jiekou_url;
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"13810381202\",\"type\":\"repay\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";

        String result = gongyong(url,number);
        System.out.println("================ "+result);
        //处理结果，就是判断返回结果是否符合预期
        if(result.contains("success"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    //5.4 智能 ivr 发送短信-4  cancel--注销短信
    @Test(groups = "userInfo")
    public void sendSms_cancel() throws IOException {
        String jiekou_url="/ivr/sendSms";
        String url= TestConfig.strIVR_URL+jiekou_url;
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"13810381202\",\"type\":\"cancel\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";

        String result = gongyong(url,number);
        System.out.println("================ "+result);
        //处理结果，就是判断返回结果是否符合预期
        if(result.contains("success"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    public String gongyong(String url,String number) {
        String key = "1de11b884b0139815aa13104342c1c63";
        String encodedNumber = EncryptTest.encrypt(number, key, 128);
        JSONObject jsonObject= HttpUtil.post_with_String(url,encodedNumber);
        //下边的代码为写完接口的测试代码
        String result = jsonObject.toJSONString();
        return result;
    }
}
