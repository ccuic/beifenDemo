package com.course.IVR;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @author zhangzhaoqiang
 * @version 1.0
 * @date 2020/12/09 12:58
 */
public class EncryptTest {
	/**
	 * AES解密 CBC模式
	 *
	 * @param data
	 * @param key
	 * @param ivParameter
	 * @return
	 */
	public static String decrypt(byte[] data, String key, String ivParameter) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] raw = key.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(data);
			return new String(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加密 AES 默认加密模式
	 *
	 * @param content    需要加密的内容
	 * @param key        加密密码
	 * @param bucketSize 加密密码长度 128 192
	 * @return
	 */
	public static String encrypt(String content, String key, Integer bucketSize) {
		try {
			int keySize = 128;
			if (bucketSize != null) {
				if (bucketSize == 128 || bucketSize == 192 || bucketSize == 256) {
					keySize = bucketSize;
				}
			}
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(key.getBytes());
			kgen.init(keySize, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return Base64.encodeBase64String(result); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密 AES 默认解密模式
	 *
	 * @param content    待解密内容
	 * @param key        解密密钥
	 * @param bucketSize 解密密钥长度
	 * @return
	 */
	public static String decrypt(byte[] content, String key, Integer bucketSize) {
		try {
			int keySize = 128;
			if (bucketSize != null) {
				if (bucketSize == 128 || bucketSize == 192 || bucketSize == 256) {
					keySize = bucketSize;
				}
			}
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(key.getBytes());
			kgen.init(keySize, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);// 初始化
			byte[] result = cipher.doFinal(content);
			return new String(result, "utf-8"); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String key = "1de11b884b0139815aa13104342c1c63";
		String number = "{\"param\":{\"businessLine\":\"haohuan\",\"mobile\":\"13621094965\",\"type\":\"email\"},,\"appid\":\"kg1u9xn5gdrtolfq\",\"sign\":\"1d1cfe35bb3df977223bf934e5a1ef08\"}\n";
		/**
		 * AES 默认加密模式
		 */
		String encodedNumber = encrypt(number, key, 128);
		System.out.println("默认模式加密后： number = " + encodedNumber);
		System.out.println("默认模式解密后： number = " + decrypt(Base64.decodeBase64(encodedNumber), key, 128));

	}
}