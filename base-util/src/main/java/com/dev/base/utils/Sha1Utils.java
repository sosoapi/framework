package com.dev.base.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.StringUtils;

/**
 * 
		* <p>Title: Sha1加密算法</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:48:58</p>
 */
public class Sha1Utils {
	/**
	 * 
			*@name 加密
			*@Description 相关说明 
			*@Time 创建时间:2015年7月31日上午11:28:19
			*@param plainText
	 */
	public static String sha1Crypt(String plainText ){
		if (StringUtils.isEmpty(plainText)) {
			return "";
		}
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		md.update(plainText.getBytes(Charset.forName("UTF-8")));
		byte[] digest = md.digest();

		StringBuffer hexstr = new StringBuffer();
		String shaHex = "";
		for (int i = 0; i < digest.length; i++) {
			shaHex = Integer.toHexString(digest[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexstr.append(0);
			}
			hexstr.append(shaHex);
		}
		return hexstr.toString();
	}
}
