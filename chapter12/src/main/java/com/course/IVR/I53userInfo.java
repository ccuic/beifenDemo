package com.course.IVR;

import com.course.config.TestConfig;
import com.course.model.IVR_API;
import com.course.utils.ConfigFile;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

//5.3 用户信息查询，调用地址： /info/userInfo 请求账务/v1/user/baseInfo
public class I53userInfo {
    private String url;
    @BeforeTest(groups = "loginTrue",description = "测试准备工作,获取Authorization对象")
    public void beforeTest(){
        TestConfig.strIVR_URL = ConfigFile.getProp(IVR_API.IVR_URL);
        String jiekou_url="/info/userInfo";
        url= TestConfig.strIVR_URL+jiekou_url;
    }
    @Test(groups = "I53userInfo")//合法数据，验证可用额度
    public void s1() throws IOException {
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"17710325011\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";
        String result = IVRUtils.gongyong(url,number);
        if(result.contains("\"msg\":\"execute successful\",\"code\":1000,\"data\":{\"shortIdcardCode\":\"3290\",\"shortPhone\":\"5011\"}"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    @Test(groups = "I53userInfo")//sign非法  正确为 1d1cfe35bb3df977223bf934e5a1ef08
    public void f1() throws IOException {
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"14587385111\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"6af9ced89dde03633d2d20d79c734a05\"}\n";
        String result = IVRUtils.gongyong(url,number);
        if(result.contains("\"msg\":\"签名校验失败\",\"code\":1003"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    @Test(groups = "I53userInfo")//sign为空串
    public void f2() throws IOException {
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"14587385111\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"\"}\n";
        String result = IVRUtils.gongyong(url,number);
        if(result.contains("\"msg\":\"签名校验失败\",\"code\":1003"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    @Test(groups = "I53userInfo")//没有sign
    public void f3() throws IOException {
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"14587385111\"},\"appid\":\"kg1u9xn5gdrtolfq\"}\n";
        String result = IVRUtils.gongyong(url,number);
        if(result.contains("\"msg\":\"签名校验失败\",\"code\":1003"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    @Test(groups = "I53userInfo")//appid非法 正确值为 kg1u9xn5gdrtolfq
    public void f4() throws IOException {
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"14587385111\"},\"appid\":\"kg1u9xn5gdrtolf1\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";
        String result = IVRUtils.gongyong(url,number);
        if(result.contains("\"msg\":\"签名校验失败\",\"code\":1003"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    @Test(groups = "I53userInfo")//appid为空串
    public void f5() throws IOException {
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"14587385111\"},\"appid\":\"\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";
        String result = IVRUtils.gongyong(url,number);
        if(result.contains("\"msg\":\"签名校验失败\",\"code\":1003"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    @Test(groups = "I53userInfo")//没有appid参数
    public void f6() throws IOException {
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"14587385111\"},\"sign\":\"\"}\n";
        String result = IVRUtils.gongyong(url,number);
        if(result.contains("\"msg\":\"签名校验失败\",\"code\":1003"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    @Test(groups = "I53userInfo")//businessLine 为空串
    public void f7() throws IOException {
        String number = "{\"param\":{\"businessLine\":\"\",\"mobile\":\"14587385111\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";
        String result = IVRUtils.gongyong(url,number);
        if(result.contains("success"))//不校验businessLine合法性，返回success
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    @Test(groups = "I53userInfo")//无businessLine 参数
    public void f8() throws IOException {
        String number = "{\"param\":{\"mobile\":\"14587385111\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";
        String result = IVRUtils.gongyong(url,number);
        if(result.contains("\"msg\":\"参数异常\",\"code\":9000"))//
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    @Test(groups = "I53userInfo")//businessLine非法  正确为haohuan
    public void f9() throws IOException {
        String number = "{\"param\":{\"businessLine\":\"haohuan2\",\"mobile\":\"14587385111\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";
        String result = IVRUtils.gongyong(url,number);
        if(result.contains("success"))//不校验businessLine合法性，返回success
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    @Test(groups = "I53userInfo")// mobile非法
    public void f10() throws IOException {
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"14587385111aa\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";
        String result = IVRUtils.gongyong(url,number);
        if(result.contains("\"msg\":\"手机号非法\",\"code\":1004"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    @Test(groups = "I53userInfo")// mobile为空串
    public void f11() throws IOException {
        String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";
        String result = IVRUtils.gongyong(url,number);
        if(result.contains("\"msg\":\"手机号非法\",\"code\":1004"))
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
    @Test(groups = "I53userInfo")// 没有mobile参数
    public void f12() throws IOException {
        String number = "{\"param\":{\"businessLine\":\"haohuan\"},\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";
        String result = IVRUtils.gongyong(url,number);
        if(result.contains("\"msg\":\"手机号非法\",\"code\":1004"))//
        {Assert.assertEquals(1,1);}
        else {Assert.assertEquals(0,1);}
    }
}
