package com.pentair.utils;

import java.util.Random;

/**
 * 字符串处理工具
 */
public class StringUtil {

	private static final char[] zeroArray = "0000000000000000000000000000000000000000000000000000000000000000"
			.toCharArray();
	private static final Random random = new Random();
	
	
	public static final String zeroPadString(String string, int length) {
		if (string == null || string.length() > length) {
			return string;
		} else {
			StringBuffer buf = new StringBuffer(length);
			if (string.startsWith("-")) {
				buf.append('-').append(zeroArray, 0, length - string.length())
						.append(string.substring(1));
			} else {
				buf.append(zeroArray, 0, length - string.length()).append(
						string);
			}
			return buf.toString();
		}
	}

	/**
	 * 获取指定长度的随机字符串
	 * @param num
	 * @return
	 */
	public static String getRandomString(int num) {
		int rand = random.nextInt(999999);
		return zeroPadString(""+rand, num);
	}
	
	/**
	 * 将字符串空值转换为空字符串
	 * @param str
	 * @return
	 */
	public static String nullToBlank(String str) {
		if (str == null || "null".equals(str))
			return "";
		return str.trim();
	}
	
	/**
	 * 将字符串空值转换为空字符串
	 * @param str
	 * @return
	 */
	public static String nullToBlank(Object str) {
		if (str == null || "null".equals(str))
			return "";
		return str.toString();
	}

	/**
	 * 截取指定长度的字符串，并加后缀，常用语页面列表链接的显示
	 * @param str
	 * @param cutLength
	 * @param append
	 * @return
	 */
	public static String cutString(String str, int cutLength, String append) {
		if (str == null || str.length() == 0) {
			return "";
		}
		int i4 = str.getBytes().length;
		if (i4 > cutLength) {
			i4 = 0;
			int cut = 0;
			while (i4 < cutLength) {
				if (cut == str.length())
					break;
				if (str.charAt(cut) > 126)
					i4 += 2;
				else
					i4++;
				cut++;
			}
			if (i4 == (cutLength + 1)) {
				cut -= 1;
			}
			if (cut == (cutLength / 2))
				cutLength--;
			return str.substring(0, cut) + append;
		} else
			return str;
	}

	/*
	 * TODO: test
	 *
	 * public static String cut(String str, int bytesCount){
	 *
	 * byte[] bytes = str.getBytes(); char[] chars = new String(bytes, 0,
	 * bytesCount).toCharArray(); char[] charsPlus = new String(bytes, 0,
	 * bytesCount + 1).toCharArray();
	 *
	 * if (chars.length == charsPlus.length) return new String(bytes, 0,
	 * bytesCount - 1);
	 *
	 * return new String(bytes, 0, bytesCount);
	 *  } public static void main(String[] args){
	 * System.out.println(cut("我ABC汉字d", 8)); System.out.println(cut("我ABC汉字d",
	 * 7)); System.out.println(cut("我ABC汉字d", 6));
	 * System.out.println(cut("我ABC汉字d", 5)); System.out.println(cut("我ABC汉字d",
	 * 4)); System.out.println(cut("我ABC汉字d", 3));
	 * System.out.println(cut("我ABC汉字d", 2)); System.out.println(cut("我ABC汉字d",
	 * 1)); }
	 */

	/**
	 * 组合连接字符串，用英文逗号分隔
	 */
	public static final String concat(final String[] list) {
		return concat(list, ',');
	}

	/**
	 * 组合连接字符串
	 * @param list
	 * @param separator
	 * @return
	 */
	public static final String concat(final String[] list, char separator) {
		if (list == null || "".equals(list))
			return "";
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < list.length; i++) {
			if (list[i] == null || "".equals(list[i]))
				continue;
			buffer.append(list[i]);
			buffer.append(separator);
		}

		return buffer.substring(0, buffer.length() - 1).toString();
	}
	
	/**
	 * 组合连接字符串
	 * @param list
	 * @param separator
	 * @return
	 */
	public static final String concat(final String[] list, String separator) {
		if (list == null || "".equals(list))
			return "";
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < list.length; i++) {
			if (list[i] == null || "".equals(list[i]))
				continue;
			buffer.append(list[i]);
			buffer.append(separator);
		}

		return buffer.substring(0, buffer.length() - separator.length())
				.toString();
	}

}
