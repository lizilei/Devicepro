package cn.com.sgcc.dev.utils;//package cn.com.sgcc.dev.utils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Enumeration;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//
///**
// * <p>@description:Zip压缩解压缩</p>
// *
// * @author lizilei
// * @version 1.0
// * @Email lizilei_warms@163.com
// * @since 2018/1/24
// */
//
//public class ZipUtils {
//
//    public final static String encoding = "GBK";
//
//    /**
//     * 解压zip文件
//     *
//     * @param zipfile zip文件绝对路径
//     * @param dispath 解压到的目录
//     * @throws Exception
//     */
//    public static void unZipFile(File zipfile, String dispath, boolean mkZipName) throws Exception {
//        // 定义输入输出流对象
//        InputStream in = null;
//        OutputStream out = null;
//        try {
//            org.apache.tools.zip.ZipFile zipFile = new org.apache.tools.zip.ZipFile(zipfile, "gbk");
//            String name = zipfile.getName().substring(0, zipfile.getName().lastIndexOf("."));
//            File unzipFile = new File(dispath);
//            if (mkZipName) {
//                unzipFile = new File(unzipFile, name);
//                if (unzipFile.exists())
//                    unzipFile.delete();
//                unzipFile.mkdir();
//            }
//            Enumeration zipEnum = zipFile.getEntries();
//            org.apache.tools.zip.ZipEntry entry = null;
//            String entryName = null, path = null;
//            String names[] = null;
//            int length;
//            while (zipEnum.hasMoreElements()) {
//                entry = (org.apache.tools.zip.ZipEntry) zipEnum.nextElement();
//                entryName = new String(entry.getName());
//                names = entryName.split("\\/");
//                length = names.length;
//                path = unzipFile.getAbsolutePath();
//                for (int v = 0; v < length; v++) {
//                    if (v < length - 1)
//                        new File(path, names[v]).mkdir();
//                    else {
//                        if (entryName.endsWith("/"))
//                            new File(unzipFile.getAbsolutePath(), entryName).mkdir();
//                        else {
//                            in = zipFile.getInputStream(entry);
//                            out = new FileOutputStream(new File(unzipFile.getAbsolutePath() + "/" + entryName));
//                            byte[] buffer = new byte[1024 * 8];
//                            int readLen = 0;
//                            while ((readLen = in.read(buffer, 0, 1024 * 8)) != -1)
//                                out.write(buffer, 0, readLen);
//                            in.close();
//                            out.flush();
//                            out.close();
//                        }
//                    }
//                }
//            }
//            zipFile.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//        }
//    }
//
//    /**
//     * 压缩文件,文件夹
//     *
//     * @param zipFilePath 指定压缩的目的和名字
//     * @param srcFilePath 要压缩的文件/文件夹名字
//     */
//    public static void zipFolder(String zipFilePath, String... srcFilePath) {
//        //创建Zip包
//        ZipOutputStream outZip;
//        try {
//            outZip = new ZipOutputStream(new FileOutputStream(zipFilePath));
//
//            for (String s : srcFilePath) {
//                //打开要输出的文件
//                File file = new File(s);
//                //压缩
//                zipFiles(file.getParent() + File.separator, file.getName(), outZip);
//            }
//            //完成,关闭
//            outZip.finish();
//            outZip.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 压缩文件
//     *
//     * @param folderPath
//     * @param filePath
//     * @param zipOut
//     * @throws Exception
//     */
//    private static void zipFiles(String folderPath, String filePath, ZipOutputStream zipOut) {
//        if (zipOut == null) {
//            return;
//        }
//        File file = new File(folderPath + filePath);
//        try {
//            //判断是不是文件
//            if (file.isFile()) {
//                ZipEntry zipEntry = new ZipEntry(filePath);
//                FileInputStream inputStream = new FileInputStream(file);
//                zipOut.putNextEntry(zipEntry);
//
//                int len;
//                byte[] buffer = new byte[4096];
//
//                while ((len = inputStream.read(buffer)) != -1) {
//                    zipOut.write(buffer, 0, len);
//                }
//
//                zipOut.closeEntry();
//            } else {
//                //文件夹的方式,获取文件夹下的子文件
//                String fileList[] = file.list();
//
//                //如果没有子文件, 则添加进去即可
//                if (fileList.length <= 0) {
//                    ZipEntry zipEntry = new ZipEntry(filePath + File.separator);
//                    zipOut.putNextEntry(zipEntry);
//                    zipOut.closeEntry();
//                }
//
//                //如果有子文件, 遍历子文件
//                for (int i = 0; i < fileList.length; i++) {
//                    zipFiles(folderPath, filePath + File.separator + fileList[i], zipOut);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
