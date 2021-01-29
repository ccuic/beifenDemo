package com.daihou.pingfenka;

import com.daihou.model.DHInterfaceName;
import com.daihou.utils.DHHttpUtil;
import com.daihou.model.DaiHou_API;
import com.daihou.utils.DHConfigFile;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class pingfen {
	private String url;
	Map<String, String> headers;
	@BeforeTest(groups = "loginTrue",description = "测试准备工作,获取Authorization对象")
	public void beforeTest(){
		url=DHConfigFile.getUrl(DHInterfaceName.PINGFEN);
		headers=new HashMap<>();
		//设置请求头中的Authorization
		headers.put("Authorization", DHConfigFile.getProp(DaiHou_API.AUTH));
	}
	@Test(groups = "I53userInfo")//合法数据，验证可用额度
	public void s1() throws IOException {
		String strBody = "{\"pageNum\":null,\"pageSize\":null,\"order\":null}";
		String result = DHHttpUtil.post_with_String(url,strBody,headers).toJSONString();
		System.out.println("================ "+result);
		if(result.contains("\"message\":\"操作成功\",\"status\":\"0\""))
			{
				Assert.assertEquals(1,1);
			}
		else
			{
				Assert.assertEquals(0,1);
			}
	}
}