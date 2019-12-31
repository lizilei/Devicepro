package cn.com.sgcc.dev.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>@description:使用AES对文件进行加密和解密</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/2/5
 */

public class CipherUtil {

    /**
     * 压缩多个文件可包含文件夹
     *
     * @param zipPath     文件路径
     * @param type        压缩方式
     * @param level       压缩级别
     * @param method      加密方式
     * @param pwd         密码
     * @param srcFilePath 文件或文件夹路径
     * @throws ZipException
     */
    public static void dozip(String zipPath, int type, int level, int method, String pwd, String... srcFilePath) throws ZipException {
        ZipFile zip = new ZipFile(zipPath);
        //要紧跟设置编码
        zip.setFileNameCharset("GBK");
        ArrayList<File> fileList = new ArrayList<>();
        ArrayList<File> folderList = new ArrayList<>();
        if (srcFilePath != null && srcFilePath.length > 0) {
            for (String s : srcFilePath) {
                File file = new File(s);
                if (file.isFile()) {
                    fileList.add(file);
                } else if (file.isDirectory()) {
                    folderList.add(file);
                }
            }
        }

        ZipParameters para = new ZipParameters();
        para.setCompressionMethod(type);
        para.setCompressionLevel(level);
        //设置密码：
        para.setEncryptFiles(true);
        //设置AES加密方式
        para.setEncryptionMethod(method);
        //必须设置长度
        para.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        para.setPassword(pwd);
        if (fileList.size() > 0) {
            zip.addFiles(fileList, para);
        }
        if (folderList.size() > 0) {
            for (File file : folderList) {
                zip.addFolder(file, para);
            }
        }
        System.out.println("压缩成功！");
    }

    /**
     * 解压zip文件
     *
     * @param zipFile  需要解压的zip的路径
     * @param destPath 解压后的路径
     * @param pwd      解压需要输入的密码
     * @throws ZipException
     */
    public static String unzip4j(String zipFile, String destPath, String pwd) throws ZipException {

        ZipFile zipFile2 = new ZipFile(zipFile);
        //设置编码格式
        zipFile2.setFileNameCharset("GBK");
        if (!zipFile2.isValidZipFile()) {
            return "文件不合法或不存在";
        }
        //检查是否需要密码
        checkEncrypted(zipFile2, pwd);


        zipFile2.extractAll(destPath);
        return "解压成功";
    }

    /**
     * 解压zip文件
     *
     * @param zipFile  需要解压的zip文件
     * @param destPath 解压后的路径
     * @param pwd      解压需要输入的密码
     * @param handler  解压进度回调
     * @throws ZipException
     */
    public static String unzip4j(final File zipFile, String destPath, String pwd, final Handler handler) throws ZipException {
        final ZipFile zipFile2 = new ZipFile(zipFile);
        //设置编码格式
        zipFile2.setFileNameCharset("GBK");
        if (!zipFile2.isValidZipFile()) {
            Bundle bundle = new Bundle();
            bundle.putInt("error", -1);
            bundle.putString("errorMsg", "文件不合法或不存在");
            Message msg = new Message();
            msg.setData(bundle);
            msg.what = 2;
            handler.sendMessage(msg);
            return "文件不合法或不存在";
        }

        //检查是否需要密码
        checkEncrypted(zipFile2, pwd);
        final ProgressMonitor progressMonitor = zipFile2.getProgressMonitor();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = null;
                Message message = null;
                try {
                    int precentDone = 0;
                    if (handler == null) {
                        return;
                    }
                    while (true) {
                        //每隔1s发送一个解压进度出去
                        Thread.sleep(1000);

                        precentDone = progressMonitor.getPercentDone();
                        bundle = new Bundle();
                        bundle.putInt("precent", precentDone);
                        message = new Message();
                        message.what = 1;
                        message.setData(bundle);
                        handler.sendMessage(message);

                        if (precentDone >= 100) {
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    bundle = new Bundle();
                    bundle.putInt("error", -1);
                    bundle.putString("errorMsg", "文件已损坏");
                    message = new Message();
                    message.what = 2;
                    message.setData(bundle);
                    handler.sendMessage(message);
                } finally {
                    zipFile.deleteOnExit();
                }
            }
        });

        thread.start();
//        zipFile2.setRunInThread(true);
        zipFile2.extractAll(destPath);

        return "解压成功";
    }

    //检测密码
    private static void checkEncrypted(ZipFile zip, String pwd) throws ZipException {
        Scanner in = new Scanner(System.in);
        if (zip.isEncrypted()) {
            System.out.println("文件" + zip.getFile().getName() + "有密码！");
            System.out.println("请输入密码：");
            zip.setPassword(pwd);
        }
        in.close();
    }
}
