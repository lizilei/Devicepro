/**
 * Copyright (c) 2009, Chinsoft All Rights Reserved.
 */
package cn.com.sgcc.dev.utils;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author 南思请求解码
 * @version 1.0
 * @Date 2019-06-21
 */
public class AesForPassword {

	public AesForPassword() {
	}

	public static String encryptForJS(String content, String keyseed) throws Exception {
		SecretKeySpec key = getKeySpecFromBytes(keyseed.toUpperCase());
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(1, key);
		byte[] byteEnc = cipher.doFinal(content.getBytes("UTF-8"));
		return byte2hex(byteEnc);
	}

	public static String decryptForJS(String content, String keyseed) throws Exception {
		SecretKeySpec key = getKeySpecFromBytes(keyseed.toUpperCase());
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(2, key);
		String result = new String(cipher.doFinal(hex2byte(content.getBytes("UTF-8"))));
		return result.trim();
	}

	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";

		for(int n = 0; n < b.length; ++n) {
			stmp = Integer.toHexString(b[n] & 255);
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}

		return hs.toUpperCase();
	}

	private static byte[] hex2byte(byte[] b) {
		if (b.length % 2 != 0) {
			throw new IllegalArgumentException("长度不是偶数!");
		} else {
			byte[] b2 = new byte[b.length / 2];

			for(int n = 0; n < b.length; n += 2) {
				String item = new String(b, n, 2);
				b2[n / 2] = (byte)Integer.parseInt(item, 16);
			}

			return b2;
		}
	}

	private static SecretKeySpec getKeySpecFromBytes(String strBytes) throws NoSuchAlgorithmException {
		SecretKeySpec spec = new SecretKeySpec(hex2byte(strBytes.getBytes()), "AES");
		return spec;
	}

}
