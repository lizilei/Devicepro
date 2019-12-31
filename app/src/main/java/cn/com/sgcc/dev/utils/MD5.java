/**
 * Copyright (c) 2009, Chinsoft All Rights Reserved.
 */
package cn.com.sgcc.dev.utils;

/**
 * MD5类
 * 
 * @author 杨贵云
 * @version 1.0
 * @Date 2009-10-18
 */
public class MD5 {

	private static final byte PADDING[] = { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private static final int S11 = 3;

	private static final int S12 = 7;

	private static final int S13 = 11;

	private static final int S14 = 19;

	private static final int S21 = 3;

	private static final int S22 = 5;

	private static final int S23 = 9;

	private static final int S24 = 13;

	private static final int S31 = 3;

	private static final int S32 = 9;

	private static final int S33 = 11;

	private static final int S34 = 15;

	private int state[];

	private int count[];

	private byte buffer[];

	/**
	 * Creates a new instance of MD5.
	 * 
	 */
	public MD5() {
		state = new int[4];
		count = new int[2];
		buffer = new byte[64];
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		count[0] = count[1] = 0;
		state[0] = 0x67452301;
		state[1] = 0xefcdab89;
		state[2] = 0x98badcfe;
		state[3] = 0x10325476;
	}

	public void update(byte input[], int off, int len) {
		int index = count[0] >> 3 & 0x3f;
		int bitlen = len << 3;
		if ((count[0] += bitlen) < bitlen) {
			count[1]++;
		}
		count[1] += len >> 29;
		int partlen = 64 - index;
		int i;
		if (len >= partlen) {
			System.arraycopy(input, off, buffer, index, partlen);
			transform(buffer, 0);
			for (i = partlen; i + 63 < len; i += 64) {
				transform(input, off + i);
			}

			index = 0;
		} else {
			i = 0;
		}
		System.arraycopy(input, off + i, buffer, index, len - i);
	}

	public byte[] finish() {
		byte digest[] = new byte[16];
		byte bits[] = new byte[8];
		encode(count, bits, 0, bits.length);
		int index = count[0] >> 3 & 0x3f;
		int padlen = index >= 56 ? 120 - index : 56 - index;
		update(PADDING, 0, padlen);
		update(bits, 0, bits.length);
		encode(state, digest, 0, digest.length);
		return digest;
	}

	private void transform(byte block[], int offset) {
		int a = state[0];
		int b = state[1];
		int c = state[2];
		int d = state[3];
		int x[] = new int[16];
		decode(block, offset, 64, x);
		a = FF(a, b, c, d, x[0], 3);
		d = FF(d, a, b, c, x[1], 7);
		c = FF(c, d, a, b, x[2], 11);
		b = FF(b, c, d, a, x[3], 19);
		a = FF(a, b, c, d, x[4], 3);
		d = FF(d, a, b, c, x[5], 7);
		c = FF(c, d, a, b, x[6], 11);
		b = FF(b, c, d, a, x[7], 19);
		a = FF(a, b, c, d, x[8], 3);
		d = FF(d, a, b, c, x[9], 7);
		c = FF(c, d, a, b, x[10], 11);
		b = FF(b, c, d, a, x[11], 19);
		a = FF(a, b, c, d, x[12], 3);
		d = FF(d, a, b, c, x[13], 7);
		c = FF(c, d, a, b, x[14], 11);
		b = FF(b, c, d, a, x[15], 19);
		a = GG(a, b, c, d, x[0], 3);
		d = GG(d, a, b, c, x[4], 5);
		c = GG(c, d, a, b, x[8], 9);
		b = GG(b, c, d, a, x[12], 13);
		a = GG(a, b, c, d, x[1], 3);
		d = GG(d, a, b, c, x[5], 5);
		c = GG(c, d, a, b, x[9], 9);
		b = GG(b, c, d, a, x[13], 13);
		a = GG(a, b, c, d, x[2], 3);
		d = GG(d, a, b, c, x[6], 5);
		c = GG(c, d, a, b, x[10], 9);
		b = GG(b, c, d, a, x[14], 13);
		a = GG(a, b, c, d, x[3], 3);
		d = GG(d, a, b, c, x[7], 5);
		c = GG(c, d, a, b, x[11], 9);
		b = GG(b, c, d, a, x[15], 13);
		a = HH(a, b, c, d, x[0], 3);
		d = HH(d, a, b, c, x[8], 9);
		c = HH(c, d, a, b, x[4], 11);
		b = HH(b, c, d, a, x[12], 15);
		a = HH(a, b, c, d, x[2], 3);
		d = HH(d, a, b, c, x[10], 9);
		c = HH(c, d, a, b, x[6], 11);
		b = HH(b, c, d, a, x[14], 15);
		a = HH(a, b, c, d, x[1], 3);
		d = HH(d, a, b, c, x[9], 9);
		c = HH(c, d, a, b, x[5], 11);
		b = HH(b, c, d, a, x[13], 15);
		a = HH(a, b, c, d, x[3], 3);
		d = HH(d, a, b, c, x[11], 9);
		c = HH(c, d, a, b, x[7], 11);
		b = HH(b, c, d, a, x[15], 15);
		state[0] += a;
		state[1] += b;
		state[2] += c;
		state[3] += d;
	}

	/**
	 * 编码
	 * 
	 * @param input
	 *            输入字节数组
	 * @param output
	 *            输出字节数组
	 * @param off
	 *            结束位置
	 * @param len
	 *            长度
	 */
	private static void encode(int input[], byte output[], int off, int len) {
		int i = 0;
		for (int j = off; j < off + len; j += 4) {
			output[j] = (byte) (input[i] & 0xff);
			output[j + 1] = (byte) (input[i] >> 8 & 0xff);
			output[j + 2] = (byte) (input[i] >> 16 & 0xff);
			output[j + 3] = (byte) (input[i] >> 24 & 0xff);
			i++;
		}

	}

	/**
	 * 解码
	 * 
	 * @param input
	 *            输入字节数组
	 * @param off
	 *            结束位置
	 * @param len
	 *            长度
	 * @param output
	 *            输出字节数组
	 * @see
	 */
	private static void decode(byte input[], int off, int len, int output[]) {
		int i = 0;
		for (int j = off; j < off + len; j += 4) {
			int ch1 = input[j] & 0xff;
			int ch2 = input[j + 1] & 0xff;
			int ch3 = input[j + 2] & 0xff;
			int ch4 = input[j + 3] & 0xff;
			output[i] = ch1 | ch2 << 8 | ch3 << 16 | ch4 << 24;
			i++;
		}

	}

	private static int FF(int a, int b, int c, int d, int x, int s) {
		a += (b & c | ~b & d) + x;
		return a << s | a >>> 32 - s;
	}

	private static int GG(int a, int b, int c, int d, int x, int s) {
		a += (b & c | b & d | c & d) + x + 0x5a827999;
		return a << s | a >>> 32 - s;
	}

	private static int HH(int a, int b, int c, int d, int x, int s) {
		a += (b ^ c ^ d) + x + 0x6ed9eba1;
		return a << s | a >>> 32 - s;
	}

	/**
	 * 对字节数组进行MD5加密
	 * 
	 * @param input
	 *            字节数组
	 * @return 新的字节数组
	 */
	public static byte[] digest(byte input[]) {
		MD5 MD5 = new MD5();
		MD5.update(input, 0, input.length);
		return MD5.finish();
	}

}
