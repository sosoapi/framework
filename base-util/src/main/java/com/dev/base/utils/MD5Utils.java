package com.dev.base.utils;

import java.nio.charset.Charset;

import org.springframework.util.StringUtils;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

/**
 * 
		* <p>Title: MD5工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:50:12</p>
 */
public class MD5Utils {
	public static String Md5(String plainText){
		StringBuffer buf = new StringBuffer("");
		if (StringUtils.isEmpty(plainText)) {
			return buf.toString();
		}
		
		Hasher hasher = Hashing.md5().newHasher();
		hasher.putString(plainText, Charset.forName("UTF-8"));
		byte[] md5 = hasher.hash().asBytes();
		int i;
		for (byte element : md5) {
			i = element;
			if (i < 0) {
				i += 256;
			}
			if (i < 16) {
				buf.append("0");
			}
			buf.append(Integer.toHexString(i));
		}
		
		return buf.toString();
	}
}
