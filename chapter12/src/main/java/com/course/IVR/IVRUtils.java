package com.course.IVR;

import com.alibaba.fastjson.JSONObject;
import com.course.utils.HttpUtil;

public class IVRUtils {
	public static String gongyong(String url,String number) {
		String key = "1de11b884b0139815aa13104342c1c63";
		String encodedNumber = EncryptTest.encrypt(number, key, 128);
		JSONObject jsonObject= HttpUtil.post_with_String(url,encodedNumber);
		//下边的代码为写完接口的测试代码
		String result = jsonObject.toJSONString();
		System.out.println("================ "+result);
		return result;
	}
	public static boolean strContain(String str,String a,String b) {
		if(str.contains(a) && str.contains(b))
		{
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean strContain(String str,String a) {
		if(str.contains(a))
		{
			return true;
		}
		else {
			return false;
		}
	}
}