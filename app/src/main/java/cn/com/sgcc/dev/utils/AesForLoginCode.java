/**
 * Copyright (c) 2009, Chinsoft All Rights Reserved.
 */
package cn.com.sgcc.dev.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author 南思请求解码
 * @version 1.0
 * @Date 2019-06-21
 */
public class AesForLoginCode {

	public static final String AES_PASSWORD = "PASSWORD-B695622";
	public static final String AES_OFFSET = "OFFSET-153AA2215";
	public static final String AES_CHARSET = "utf-8";
	private static final String KEY_ALGORITHM = "AES";
	private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

	public AesForLoginCode() {
	}

	private static String formatByteArray2HexStr(byte[] b) {
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < b.length; ++i) {
			String s = Integer.toHexString(b[i] & 255);
			if (s.length() == 1) {
				sb.append("0");
			}

			sb.append(s.toLowerCase());
		}

		return sb.toString();
	}

	private static byte[] formatStr2ByteArray(String s) {
		int byteArrayLength = s.length() / 2;
		byte[] b = new byte[byteArrayLength];

		for(int i = 0; i < byteArrayLength; ++i) {
			//byte b0 = (byte)Integer.valueOf(s.substring(i * 2, i * 2 + 2), 16);
			byte b0 = (byte)Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
			b[i] = b0;
		}

		return b;
	}

	public static String aesEncrypt(String content) {
		try {
			IvParameterSpec zeroIv = new IvParameterSpec("OFFSET-153AA2215".getBytes());
			SecretKeySpec key = new SecretKeySpec("PASSWORD-B695622".getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(1, key, zeroIv);
			byte[] encryptedData = cipher.doFinal(content.getBytes("utf-8"));
			return formatByteArray2HexStr(encryptedData);
		} catch (NoSuchAlgorithmException var5) {
			var5.printStackTrace();
		} catch (NoSuchPaddingException var6) {
			var6.printStackTrace();
		} catch (UnsupportedEncodingException var7) {
			var7.printStackTrace();
		} catch (InvalidKeyException var8) {
			var8.printStackTrace();
		} catch (IllegalBlockSizeException var9) {
			var9.printStackTrace();
		} catch (BadPaddingException var10) {
			var10.printStackTrace();
		} catch (InvalidAlgorithmParameterException var11) {
			var11.printStackTrace();
		}

		return null;
	}

	public static String aesDecrypt(String content) {
		try {
			byte[] byteMi = formatStr2ByteArray(content);
			IvParameterSpec zeroIv = new IvParameterSpec("OFFSET-153AA2215".getBytes());
			SecretKeySpec key = new SecretKeySpec("PASSWORD-B695622".getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(2, key, zeroIv);
			byte[] decryptedData = cipher.doFinal(byteMi);
			return new String(decryptedData, "utf-8");
		} catch (NoSuchAlgorithmException var6) {
			var6.printStackTrace();
		} catch (NoSuchPaddingException var7) {
			var7.printStackTrace();
		} catch (InvalidKeyException var8) {
			var8.printStackTrace();
		} catch (IllegalBlockSizeException var9) {
			var9.printStackTrace();
		} catch (BadPaddingException var10) {
			var10.printStackTrace();
		} catch (UnsupportedEncodingException var11) {
			var11.printStackTrace();
		} catch (InvalidAlgorithmParameterException var12) {
			var12.printStackTrace();
		}

		return null;
	}

}
