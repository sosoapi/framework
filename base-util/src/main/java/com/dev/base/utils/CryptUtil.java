package com.dev.base.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

/**
 * 
		* <p>Title: AES加密算法</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日下午1:47:04</p>
 */
public class CryptUtil {
	// 默认编码
	public static String DEFAULT_CHARSET = "UTF-8";

	// 默认密钥
	public static byte[] DEFAULT_SECRET_KEY = { 67, 34, -58, -74, -8, -102,
												-71, 92, 104, 84, 60, 98, -126, 18, -1, -68 };

	/**
	 * DES的不安全性以及DESede算法的低效，催生了这个AES算法（advanced Encryption Standard）。
	 * 这个算法比DES要快，安全性高。密钥建立时间段、灵敏性好、内存需求低。在各个领域应用广泛。
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Key initKeyForAES(byte[] key) throws Exception {
		// if (StringUtils.isEmpty(key)) {
		// throw new NullPointerException("key  is null");
		// }

		// 不采用指定随机数生成器生成SecureRandom对象来生成密钥
		// 否则不同平台下的密钥可能不一样（windows和aix就不一样）
		// 从而造成移植过程中数据库数据出问题
		// KeyGenerator kgen = KeyGenerator.getInstance("AES");
		// // 初始化密钥生成器，AES要求密钥长度为128位、192位、256位
		// kgen.init(128, new SecureRandom(key.getBytes(DEFAULT_CHARSET)));
		// SecretKey secretKey = kgen.generateKey();
		//
		// return new SecretKeySpec(secretKey.getEncoded(), "AES");

		if ((key == null) || (key.length != 16)) {
			throw new Exception("AES密钥生成参数必须为byte数组且长度为16");
		}

		return new SecretKeySpec(key, "AES");
	}

	/**
	 * 使用AES算法进行加密 密文长度为64字节
	 * 
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptAES(String content) {
		return encryptAES(content, DEFAULT_SECRET_KEY);
	}

	/**
	 * 使用AES算法进行加密 密文长度为64字节
	 * 
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptAES(String content, byte[] key) {
		if (StringUtils.isEmpty(content)) {
			return "";
		}

		byte[] result = null;
		try {
			byte[] byteContent = content.getBytes(DEFAULT_CHARSET);
			SecretKeySpec secretKey = (SecretKeySpec) initKeyForAES(key);
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);// 初始化
			result = cipher.doFinal(byteContent);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return asHex(result); // 加密
	}

	/**
	 * 使用AES算法进行解密
	 * 
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptAES(String content){
		return decryptAES(content, DEFAULT_SECRET_KEY);
	}
	
	/**
	 * 使用AES算法进行解密
	 * 
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptAES(String content, byte[] key){
		if (StringUtils.isEmpty(content)) {
			return "";
		}

		String result = "";
		try {
			SecretKeySpec secretKey = (SecretKeySpec) initKeyForAES(key);
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, secretKey);// 初始化
			byte[] byteArray = cipher.doFinal(asBytes(content));
			result = new String(byteArray, DEFAULT_CHARSET);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 二进制转换为十六进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		for (int i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10) {
				strbuf.append("0");
			}

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	/**
	 * 十六进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] asBytes(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}

		byte[] result = new byte[hexStr.length() / 2];
		int high, low;
		for (int i = 0; i < hexStr.length() / 2; i++) {
			high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		String content = "dev";
		String dcontent = CryptUtil.encryptAES(content,
				CryptUtil.DEFAULT_SECRET_KEY);
		System.out.println(dcontent);
		System.out.println(CryptUtil.decryptAES(dcontent,
				CryptUtil.DEFAULT_SECRET_KEY));
	}
}
