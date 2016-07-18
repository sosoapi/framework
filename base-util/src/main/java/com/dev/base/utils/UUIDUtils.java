package com.dev.base.utils;

import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

/**
 * 
		* <p>Title: UUID工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:47:48</p>
 */
public class UUIDUtils {

	private static final char[] charMap;
	
	private static final int lenght=8;

	static {
		charMap = new char[64];
		for (int i = 0; i < 10; i++) {
			charMap[i] = (char) ('0' + i);
		}
		for (int i = 10; i < 36; i++) {
			charMap[i] = (char) ('a' + i - 10);
		}
		for (int i = 36; i < 62; i++) {
			charMap[i] = (char) ('A' + i - 36);
		}
		charMap[62] = '_';
		charMap[63] = '-';
	}

	public static String getUUID2() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return hexTo64(uuid);
	}

	private static String hexTo64(String hex) {
		StringBuffer r = new StringBuffer();
		int index = 0;
		int[] buff = new int[3];
		int l = hex.length();
		for (int i = 0; i < l; i++) {
			index = i % 3;
			buff[index] = Integer.parseInt("" + hex.charAt(i), 16);
			if (index == 2) {
				r.append(charMap[buff[0] << 2 | buff[1] >>> 2]);
				r.append(charMap[(buff[1] & 3) << 4 | buff[2]]);
			}
		}
		return r.toString();
	}

	public static String getUUID() {
		byte[] byUuid = new byte[16];

		UUID uuid = UUID.randomUUID();
		long least = uuid.getLeastSignificantBits();
		long most = uuid.getMostSignificantBits();
		long2bytes(most, byUuid, 0);
		long2bytes(least, byUuid, 8);
		String compressUUID = Base64.encodeBase64URLSafeString(byUuid);

		return compressUUID;
	}

	private static void long2bytes(long value, byte[] bytes, int offset) {
		for (int i = 7; i > -1; i--) {
			bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF);
		}
	}

	private static String[] randomValues = new String[] { "0", "1", "2", "3",
			"4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
			"h", "i", "j", "k", "l", "m", "n", "u", "t", "s", "o", "x", "v",
			"p", "q", "r", "w", "y", "z" };

	public static String getTopicCode() {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < lenght; i++) {
			Double number = Math.random() * (randomValues.length - 1);
			str.append(randomValues[number.intValue()]);
		}
		return str.toString();
	}
}