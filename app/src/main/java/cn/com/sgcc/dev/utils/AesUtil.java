/**
 * Copyright (c) 2009, Chinsoft All Rights Reserved.
 */
package cn.com.sgcc.dev.utils;

/**
 *
 * @author 南思请求解码
 * @version 1.0
 * @Date 2019-06-21
 */
public class AesUtil {

	public AesUtil() {
	}

	public static String encryptPassword(String password) {
		String ret = null;

		try {
			ret = AesForPassword.encryptForJS(password, "c32ad1415f6c89fee76d8457c31efb4b");
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return ret;
	}

	public static String encryptLonginCode(String loginCode) {
		return AesForLoginCode.aesEncrypt(loginCode);
	}

}
