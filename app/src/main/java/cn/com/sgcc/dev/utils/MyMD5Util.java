package cn.com.sgcc.dev.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/11
 */

public class MyMD5Util {
    private static final char hexTable[] = "0123456789abcdef".toCharArray();
//    private static final char hexTable[] = "0123456789ABCDEF".toCharArray();
    private static final Integer SALT_LENGTH = 12;

//    /**
//     * 将16进制字符串转换成字节数组
//     *
//     * @param hex
//     * @return
//     */
//    public static byte[] hexStringToByte(String hex) {
//        int len = hex.length() / 2;
//        byte[] result = new byte[len];
//        char[] hexxChars = hex.toCharArray();
//        for (int i = 0; i < len; i++) {
//            int pos = i * 2;
//            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexxChars[pos]) << 4 | HEX_NUMS_STR.indexOf(hexxChars[pos + 1]));
//        }
//        return result;
//    }

    public static String digest(String algorithm, String data, String enc) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        /*
         * if (algorithm.equalsIgnoreCase("ntlm")) { return digest("md5",
		 * data.getBytes("UnicodeLittleUnmarked")); } else { return
		 * digest(algorithm, data.getBytes(enc)); }
		 */
        if (algorithm.equalsIgnoreCase("ntlm")) {
            return digest("md5", data.getBytes("UnicodeLittleUnmarked"));
        } else {
            return digest(algorithm, data.getBytes(enc));
        }
    }

    public static String digest(String str) {
        StringBuffer str2 = new StringBuffer("{");
        try {
            str2.append("ntlm");
            str2.append("}");
            str2.append(digest("ntlm", str, "UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str2.toString();
    }

    /**
     * 将指定byte数组转换成16进制字符串
     *
     * @param algorithm
     * @param data
     * @return
     */
    public static String digest(String algorithm, byte data[]) throws NoSuchAlgorithmException {
        byte digest[];
        if (algorithm.equalsIgnoreCase("md4")) {
            digest = MD5.digest(data);
        } else {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            digest = md.digest(data);
        }
        StringBuffer res = new StringBuffer(digest.length * 2);
        for (int i = 0; i < digest.length; i++) {
            byte b = digest[i];
            res.append(hexTable[b >> 4 & 0xf]);
            res.append(hexTable[b & 0xf]);
        }
        return res.toString();
    }

    public static String tomd5_16(String str) {
        StringBuffer hexString = new StringBuffer();
        if (str != null && str.trim().length() != 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("md5");
                md.update(str.getBytes());
                byte[] hash = md.digest();
                for (int i = 0; i < hash.length; i++) {
                    if ((0xff & hash[i]) < 0x10) {
                        hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                    } else {
                        hexString.append(Integer.toHexString(0xFF & hash[i]));
                    }
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
//        return hexString.toString();//32位
        return hexString.toString().substring(8,24);//16位
    }

    public static String tomd5_32(String str) {
        StringBuffer hexString = new StringBuffer();
        if (str != null && str.trim().length() != 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("md5");
                md.update(str.getBytes());
                byte[] hash = md.digest();
                for (int i = 0; i < hash.length; i++) {
                    if ((0xff & hash[i]) < 0x10) {
                        hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                    } else {
                        hexString.append(Integer.toHexString(0xFF & hash[i]));
                    }
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return hexString.toString();//32位
    }

//    /**
//     * 验证口令是否合法
//     *
//     * @param password
//     * @param passwordInDb
//     * @return
//     * @throws NoSuchAlgorithmException
//     * @throws UnsupportedEncodingException
//     */
//    public static boolean validPassword(String password, String passwordInDb) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        //将16进制字符串格式口令转换成字节数组
//        byte[] pwdInDb = hexStringToByte(passwordInDb);
//        //声明盐变量
//        byte[] salt = new byte[SALT_LENGTH];
//        //将盐从数据库中保存的口令字节数组中提取出来
//        System.arraycopy(pwdInDb, 0, salt, 0, SALT_LENGTH);
//        //创建消息摘要对象
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        //将盐数据传入消息摘要对象
//        md.update(salt);
//        //将口令的数据传给消息摘要对象
//        md.update(password.getBytes("UTF-8"));
//        //生成输入口令的消息摘要
//        byte[] digest = md.digest();
//        //声明一个保存数据库中口令消息摘要变量
//        byte[] digestInDb = new byte[pwdInDb.length - SALT_LENGTH];
//        //取得数据库中口令消息的消息 摘要
//        System.arraycopy(pwdInDb, SALT_LENGTH, digestInDb, 0, digestInDb.length);
//        //比较根据输入口令生成的消息摘要和数据库中消息摘要是否相同
//        if (Arrays.equals(digest, digestInDb)) {
//            //口令正确返回口令匹配消息
//            return true;
//        } else {
//            //口令不正确返回口令匹配消息
//            return false;
//        }
//    }
//
//    public static String getEncryptedPwd(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        byte[] pwd = null;
//        //随机数生成
//        SecureRandom random = new SecureRandom();
//        //声明盐变量
//        byte[] salt = new byte[SALT_LENGTH];
//        //将随机数放入盐变量中
//        random.nextBytes(salt);
//        //声明消息摘要对象
//        MessageDigest md = null;
//        //创建消息摘要
//        md = MessageDigest.getInstance("MD5");
//        //将盐数据传入消息摘要对象
//        md.update(salt);
//        //将口令的数据传给消息摘要对象
//        md.update(password.getBytes("UTF-8"));
//        //获得消息摘要的字节数组
//        byte[] digest = md.digest();
//        //因为要在口令的字节数组中存放盐，所以加上盐的字节长度
//        pwd = new byte[digest.length + SALT_LENGTH];
//        //将盐的字节拷贝到生成的加密口令字节数组的前12个字节，以便在验证口令是取出盐
//        System.arraycopy(salt, 0, pwd, 0, SALT_LENGTH);
//        //将消息摘要拷贝到加密口令字节数组从第13个字节开始的字节
//        System.arraycopy(digest, 0, pwd, SALT_LENGTH, digest.length);
//        //将字节数组格式加密后的口令转化为16进制字符串格式的口令
//        return byteToHexString(pwd);
//    }
}
