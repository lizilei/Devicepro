package cn.com.sgcc.dev.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.util.Xml;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.PictureData;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import cn.com.sgcc.dev.constants.Constants;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/18
 */

public class FileUtils {

    private static volatile FileUtils instance;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    private File myFile;
    private OutputStream output;
    private String nameStr;
    public String htmlPath;
    private int presentPicture;
    private HWPFDocument hwpf;
    private Range range;
    private TableIterator tableIterator;
    private List<Picture> pictures;
    private String picturePath;

    private FileUtils() {

    }

    public static FileUtils getInstance() {
        if (instance == null) {
            synchronized (FileUtils.class) {
                if (instance == null) {
                    instance = new FileUtils();
                    return instance;
                }
            }
        }
        return instance;
    }


    /**
     * 拷贝单个文件到指定目录下
     *
     * @param oldPath
     * @param newPath
     */
    public static void copyFile(String oldPath, String newPath) {
        if (oldPath == null || oldPath.equals(""))
            return;

        File[] files = new File(newPath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String extName = FileUtils.getExtensionName(pathname.getName());

                return extName.equalsIgnoreCase("key");
            }
        });

        //写入成功后删除原来的key文件
        if (files != null && files.length > 0) {
            for (File file : files) {
                FileUtils.deleteFileSafely(file);
            }
        }

        try {
            File oldFile = new File(oldPath);
            if (oldFile.exists()) {
                FileInputStream fis = new FileInputStream(oldPath);
                File newFile = new File(newPath + File.separator + oldPath.substring(oldPath.lastIndexOf("/") + 1));
                if (!newFile.exists())
                    newFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(newFile);
                byte[] buffer = new byte[1444];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                fos.flush();
                fos.close();
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件操作 获取文件扩展名
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 获取指定目录下的所有文件名
     *
     * @param path
     */
    public static List<String> getFileNamesByPath(String path) {
        File[] files = new File(path).listFiles();
        List<String> list = new ArrayList<>();

        if (files != null && files.length > 0) {
            for (File file : files) {
                list.add(file.getName());
            }
        }
        return list;
    }


    /**
     * 删除文件安全方式
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败：" + fileName + "不存在！");
            return false;
        }
        if (file.isFile()) {
            return deleteFileSafely(file);
        }
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                return deleteFileSafely(file);
            }

            for (File childFile : childFiles) {
                deleteFile(childFile.getAbsolutePath());
            }
            return deleteFileSafely(file);

        }
        return false;
    }

    /**
     * 安全删除文件
     *
     * @param file
     * @return
     */
    public static boolean deleteFileSafely(File file) {
        if (file != null) {
            String tmpPath = file.getParent() + File.separator + System.currentTimeMillis();
            File tmp = new File(tmpPath);
            file.renameTo(tmp);
            return tmp.delete();
        }
        return false;
    }

    /**
     * 判断本地sg目录下是存在zip文件包
     *
     * @return
     */
    public static boolean hasZip() {
        boolean hasZip = false;
        File inputFile = new File(Constants.INPUT_PATH);
        File[] files = inputFile.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (FileUtils.getExtensionName(file.getName()).equalsIgnoreCase("zip")) {
                    hasZip = true;
                    break;
                }
            }
        }
        return hasZip;
    }

    /**
     * 解压zip文件
     */
    public static String unZip() {
        File acceFile = null;
        File inputFile = new File(Constants.INPUT_PATH);
        File[] files = inputFile.listFiles();

        for (File file : files) {
            if (FileUtils.getExtensionName(file.getName()).equalsIgnoreCase("zip")) {
                acceFile = file;
                break;
            }
        }

        File imgFile = new File(Constants.APP_IMG);
        File[] filed = imgFile.listFiles();
        if (filed != null & filed.length > 0) {
            for (File file : filed) {
                file.delete();
            }
        }

        String show = null;

        if (acceFile != null && acceFile.isFile()) {
            //先删除已存在的文件，如果存在的话
            for (File file : files) {
                if (!file.getName().equals(acceFile.getName())) {
                    file.delete();
                }
            }
            try {
                //解压的方法
                show = CipherUtil.unzip4j(acceFile.getAbsolutePath(), Constants.INPUT_PATH, "jdbh.2019@app_rlst");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                acceFile.delete();
            }
        }
        return show;
    }

    /**
     * 解压zip文件
     */
    public static String unZip(Handler handler) {
        File acceFile = null;
        File inputFile = new File(Constants.INPUT_PATH);
        File[] files = inputFile.listFiles();

        for (File file : files) {
            if (FileUtils.getExtensionName(file.getName()).equalsIgnoreCase("zip")) {
                acceFile = file;
                break;
            }
        }

        File imgFile = new File(Constants.APP_IMG);
        File[] filed = imgFile.listFiles();
        if (filed != null & filed.length > 0) {
            for (File file : filed) {
                file.delete();
            }
        }

        String show = null;

        if (acceFile != null && acceFile.isFile()) {
            //先删除已存在的文件，如果存在的话
            for (File file : files) {
                if (!file.getName().equals(acceFile.getName())) {
                    file.delete();
                }
            }
            try {
                //解压的方法
                show = CipherUtil.unzip4j(acceFile, Constants.INPUT_PATH, "jdbh.2019@app_rlst", handler);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                acceFile.delete();
            }
        }
        return show;
    }

    /**
     * 解压zip文件
     *
     * @param zipPath
     */
    public static String unZip(String zipPath) {
        boolean isD = FileUtils.deleteFile(Constants.INPUT_PATH);
        boolean is = FileUtils.deleteFile(Constants.APP_ACCESSORY);

        if (isD) {
            System.out.println("sg文件删除成功");
        }
        if (is) {
            System.out.println("accessory文件删除成功");
        }

        String show = "";

        //解压的方法
        try {
            show = CipherUtil.unzip4j(zipPath, Constants.ROOT_PATH, "jdbh.2019@app_rlst");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return show;
    }

    /**
     * 读取文本文件
     *
     * @param file
     */
    public static String readFileContent(File file) {
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sbf.toString();
    }

    /**
     * 写入字符串
     *
     * @param json
     * @param file
     */
    public static void writeString(String json, File file) {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            fos.write(json.getBytes());
            fos.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 写入序列化对象
     *
     * @param obj
     */
    public static void writeObject(Object obj, File file) {
        if (file.exists())
            file.delete();

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(obj);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readObject(File file) {
        if (!file.exists()) {
            return null;
        }
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置生成的html文件保存地址
     *
     * @param filename
     * @return
     */
    public boolean makeFile(String filename, String nameStr) {
        this.nameStr = nameStr;
        File myFile = new File(Constants.APP_OFFICE_HTML + File.separator + filename + ".html");// 获取my.html的地址

        if (!myFile.exists()) {// 如果不存在
            try {
                myFile.createNewFile();// 创建文件
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            htmlPath = myFile.getAbsolutePath();
            return false;
        }
        htmlPath = myFile.getAbsolutePath();
        return true;
    }

    public boolean getRange(String type, String nameStr) {
        File file = new File(nameStr);
        if (!file.exists()) {
            return false;
        }
        FileInputStream in = null;
        POIFSFileSystem pfs = null;
        try {
            this.nameStr = nameStr;
            in = new FileInputStream(file);

            pfs = new POIFSFileSystem(in);

            if (type.equals("doc")) {
                hwpf = new HWPFDocument(pfs);
            } else if (type.equals("docx")) {
            } else if (type.equals("xls")) {
            } else if (type.equals("xlsx")) {
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        range = hwpf.getRange();

        pictures = hwpf.getPicturesTable().getAllPictures();

        tableIterator = new TableIterator(range);

        return true;
    }

    public void readDOC2() {
        try {
            myFile = new File(htmlPath);
            output = new FileOutputStream(myFile);
            presentPicture = 0;
            String head = "<html><meta charset=\"utf-8\"><body>";
            String tagBegin = "<p>";
            String tagEnd = "</p>";
            output.write(head.getBytes());
            int numParagraphs = range.numParagraphs();// 得到页面所有的段落数
            for (int i = 0; i < numParagraphs; i++) { // 遍历段落数
                Paragraph p = range.getParagraph(i); // 得到文档中的每一个段落
                if (p.isInTable()) {
                    int temp = i;
                    if (tableIterator.hasNext()) {
                        String tableBegin = "<table style=\"border-collapse:collapse\" border=1 bordercolor=\"black\">";
                        String tableEnd = "</table>";
                        String rowBegin = "<tr>";
                        String rowEnd = "</tr>";
                        String colBegin = "<td>";
                        String colEnd = "</td>";
                        Table table = tableIterator.next();
                        output.write(tableBegin.getBytes());
                        int rows = table.numRows();
                        for (int r = 0; r < rows; r++) {
                            output.write(rowBegin.getBytes());
                            TableRow row = table.getRow(r);
                            int cols = row.numCells();
                            int rowNumParagraphs = row.numParagraphs();
                            int colsNumParagraphs = 0;
                            for (int c = 0; c < cols; c++) {
                                output.write(colBegin.getBytes());
                                TableCell cell = row.getCell(c);
                                int max = temp + cell.numParagraphs();
                                colsNumParagraphs = colsNumParagraphs
                                        + cell.numParagraphs();
                                for (int cp = temp; cp < max; cp++) {
                                    Paragraph p1 = range.getParagraph(cp);
                                    output.write(tagBegin.getBytes());
                                    writeParagraphContent(p1);
                                    output.write(tagEnd.getBytes());
                                    temp++;
                                }
                                output.write(colEnd.getBytes());
                            }
                            int max1 = temp + rowNumParagraphs;
                            for (int m = temp + colsNumParagraphs; m < max1; m++) {
                                temp++;
                            }
                            output.write(rowEnd.getBytes());
                        }
                        output.write(tableEnd.getBytes());
                    }
                    i = temp;
                } else {
                    output.write(tagBegin.getBytes());
                    writeParagraphContent(p);
                    output.write(tagEnd.getBytes());
                }
            }
            String end = "</body></html>";
            output.write(end.getBytes());
            output.close();
        } catch (Exception e) {
            System.out.println("readAndWrite Exception:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void readDOCX2() {
        String river = "";
        try {
            this.myFile = new File(htmlPath);// new一个File,路径为html文件
            this.output = new FileOutputStream(this.myFile);// new一个流,目标为html文件
            presentPicture = 0;
            String head = "<!DOCTYPE><html><meta charset=\"utf-8\"><body>";// 定义头文件,我在这里加了utf-8,不然会出现乱码
            String end = "</body></html>";
            String tagBegin = "<p>";// 段落开始,标记开始?
            String tagEnd = "</p>";// 段落结束
            String tableBegin = "<table style=\"border-collapse:collapse\" border=1 bordercolor=\"black\">";
            String tableEnd = "</table>";
            String rowBegin = "<tr>";
            String rowEnd = "</tr>";
            String colBegin = "<td>";
            String colEnd = "</td>";
            String style = "style=\"";
            this.output.write(head.getBytes());// 写如头部
            ZipFile xlsxFile = new ZipFile(new File(nameStr));
            ZipEntry sharedStringXML = xlsxFile.getEntry("word/document.xml");
            InputStream inputStream = xlsxFile.getInputStream(sharedStringXML);
            XmlPullParser xmlParser = Xml.newPullParser();
            xmlParser.setInput(inputStream, "utf-8");
            int evtType = xmlParser.getEventType();
            boolean isTable = false; // 是表格 用来统计 列 行 数
            boolean isSize = false; // 大小状态
            boolean isColor = false; // 颜色状态
            boolean isCenter = false; // 居中状态
            boolean isRight = false; // 居右状态
            boolean isItalic = false; // 是斜体
            boolean isUnderline = false; // 是下划线
            boolean isBold = false; // 加粗
            boolean isR = false; // 在那个r中
            boolean isStyle = false;
            int pictureIndex = 1; // docx 压缩包中的图片名 iamge1 开始 所以索引从1开始
            while (evtType != XmlPullParser.END_DOCUMENT) {
                switch (evtType) {
                    // 开始标签
                    case XmlPullParser.START_TAG:
                        String tag = xmlParser.getName();

                        if (tag.equalsIgnoreCase("r")) {
                            isR = true;
                        }
                        if (tag.equalsIgnoreCase("u")) { // 判断下划线
                            isUnderline = true;
                        }
                        if (tag.equalsIgnoreCase("jc")) { // 判断对齐方式
                            String align = xmlParser.getAttributeValue(0);
                            if (align.equals("center")) {
                                this.output.write("<center>".getBytes());
                                isCenter = true;
                            }
                            if (align.equals("right")) {
                                this.output.write("<div align=\"right\">"
                                        .getBytes());
                                isRight = true;
                            }
                        }

                        if (tag.equalsIgnoreCase("color")) { // 判断颜色

                            String color = xmlParser.getAttributeValue(0);

                            this.output
                                    .write(("<span style=\"color:" + color + ";\">")
                                            .getBytes());
                            isColor = true;
                        }
                        if (tag.equalsIgnoreCase("sz")) { // 判断大小
                            if (isR == true) {
                                int size = decideSize(Integer.valueOf(xmlParser
                                        .getAttributeValue(0)));
                                this.output.write(("<font size=" + size + ">")
                                        .getBytes());
                                isSize = true;
                            }
                        }
                        // 下面是表格处理
                        if (tag.equalsIgnoreCase("tbl")) { // 检测到tbl 表格开始
                            this.output.write(tableBegin.getBytes());
                            isTable = true;
                        }
                        if (tag.equalsIgnoreCase("tr")) { // 行
                            this.output.write(rowBegin.getBytes());
                        }
                        if (tag.equalsIgnoreCase("tc")) { // 列
                            this.output.write(colBegin.getBytes());
                        }

                        if (tag.equalsIgnoreCase("pic")) { // 检测到标签 pic 图片
                            String entryName_jpeg = "word/media/image"
                                    + pictureIndex + ".jpeg";
                            String entryName_png = "word/media/image"
                                    + pictureIndex + ".png";
                            String entryName_gif = "word/media/image"
                                    + pictureIndex + ".gif";
                            String entryName_wmf = "word/media/image"
                                    + pictureIndex + ".wmf";
                            ZipEntry sharePicture = null;
                            InputStream pictIS = null;
                            sharePicture = xlsxFile.getEntry(entryName_jpeg);
                            // 一下为读取docx的图片 转化为流数组
                            if (sharePicture == null) {
                                sharePicture = xlsxFile.getEntry(entryName_png);
                            }
                            if (sharePicture == null) {
                                sharePicture = xlsxFile.getEntry(entryName_gif);
                            }
                            if (sharePicture == null) {
                                sharePicture = xlsxFile.getEntry(entryName_wmf);
                            }

                            if (sharePicture != null) {
                                pictIS = xlsxFile.getInputStream(sharePicture);
                                ByteArrayOutputStream pOut = new ByteArrayOutputStream();
                                byte[] bt = null;
                                byte[] b = new byte[1000];
                                int len = 0;
                                while ((len = pictIS.read(b)) != -1) {
                                    pOut.write(b, 0, len);
                                }
                                pictIS.close();
                                pOut.close();
                                bt = pOut.toByteArray();
                                Log.i("byteArray", "" + bt);
                                if (pictIS != null) {
                                    pictIS.close();
                                }
                                if (pOut != null) {
                                    pOut.close();
                                }
                                writeDOCXPicture(bt);
                            }

                            pictureIndex++; // 转换一张后 索引+1
                        }

                        if (tag.equalsIgnoreCase("b")) { // 检测到加粗标签
                            isBold = true;
                        }
                        if (tag.equalsIgnoreCase("p")) {// 检测到 p 标签
                            if (isTable == false) { // 如果在表格中 就无视
                                this.output.write(tagBegin.getBytes());
                            }
                        }
                        if (tag.equalsIgnoreCase("i")) { // 斜体
                            isItalic = true;
                        }
                        // 检测到值 标签
                        if (tag.equalsIgnoreCase("t")) {
                            if (isBold == true) { // 加粗
                                this.output.write("<b>".getBytes());
                            }
                            if (isUnderline == true) { // 检测到下划线标签,输入<u>
                                this.output.write("<u>".getBytes());
                            }
                            if (isItalic == true) { // 检测到斜体标签,输入<i>
                                output.write("<i>".getBytes());
                            }
                            river = xmlParser.nextText();
                            this.output.write(river.getBytes()); // 写入数值
                            if (isItalic == true) { // 检测到斜体标签,在输入值之后,输入</i>,并且斜体状态=false
                                this.output.write("</i>".getBytes());
                                isItalic = false;
                            }
                            if (isUnderline == true) {// 检测到下划线标签,在输入值之后,输入</u>,并且下划线状态=false
                                this.output.write("</u>".getBytes());
                                isUnderline = false;
                            }
                            if (isBold == true) { // 加粗
                                this.output.write("</b>".getBytes());
                                isBold = false;
                            }
                            if (isSize == true) { // 检测到大小设置,输入结束标签
                                this.output.write("</font>".getBytes());
                                isSize = false;
                            }
                            if (isColor == true) { // 检测到颜色设置存在,输入结束标签
                                this.output.write("</span>".getBytes());
                                isColor = false;
                            }
                            if (isCenter == true) { // 检测到居中,输入结束标签
                                this.output.write("</center>".getBytes());
                                isCenter = false;
                            }
                            if (isRight == true) { // 居右不能使用<right></right>,使用div可能会有状况,先用着
                                this.output.write("</div>".getBytes());
                                isRight = false;
                            }
                        }
                        break;
                    // 结束标签
                    case XmlPullParser.END_TAG:
                        String tag2 = xmlParser.getName();
                        if (tag2.equalsIgnoreCase("tbl")) { // 检测到表格结束,更改表格状态
                            this.output.write(tableEnd.getBytes());
                            isTable = false;
                        }
                        if (tag2.equalsIgnoreCase("tr")) { // 行结束
                            this.output.write(rowEnd.getBytes());
                        }
                        if (tag2.equalsIgnoreCase("tc")) { // 列结束
                            this.output.write(colEnd.getBytes());
                        }
                        if (tag2.equalsIgnoreCase("p")) { // p结束,如果在表格中就无视
                            if (isTable == false) {
                                this.output.write(tagEnd.getBytes());
                            }
                        }
                        if (tag2.equalsIgnoreCase("r")) {
                            isR = false;
                        }
                        break;
                    default:
                        break;
                }
                evtType = xmlParser.next();
            }
            this.output.write(end.getBytes());
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        if (river == null) {
            river = "解析文件出现问题";
        }
    }

    public StringBuffer readXLS2() throws Exception {
        StringBuffer lsb = new StringBuffer();

        myFile = new File(htmlPath);
        output = new FileOutputStream(myFile);
        lsb.append("<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>");
        lsb.append("<head><meta http-equiv=Content-Type content='text/html; charset=utf-8'><meta name=ProgId content=Excel.Sheet>");
        HSSFSheet sheet;

        String excelFileName = nameStr;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
                    excelFileName)); // 获整个Excel

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                if (workbook.getSheetAt(sheetIndex) != null) {
                    sheet = workbook.getSheetAt(sheetIndex);// 获得不为空的这个sheet
                    String sheetName = workbook.getSheetName(sheetIndex); // sheetName
                    if (sheet != null) {
                        int firstRowNum = sheet.getFirstRowNum(); // 第一行
                        int lastRowNum = sheet.getLastRowNum(); // 最后一行

                        lsb.append("<table width=\"100%\" style=\"border:1px solid #000;border-width:1px 0 0 1px;margin:2px 0 2px 0;border-collapse:collapse;\">");

                        lsb.append("<h1>" + sheetName + "</h1>");

                        for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
                            if (sheet.getRow(rowNum) != null) {// 如果行不为空，
                                HSSFRow row = sheet.getRow(rowNum);
                                short firstCellNum = row.getFirstCellNum(); // 该行的第一个单元格
                                short lastCellNum = row.getLastCellNum(); // 该行的最后一个单元格
                                int height = (int) (row.getHeight() / 15.625); // 行的高度
                                lsb.append("<tr height=\""
                                        + height
                                        + "\" style=\"border:1px solid #000;border-width:0 1px 1px 0;margin:2px 0 2px 0;\">");
                                for (short cellNum = firstCellNum; cellNum <= lastCellNum; cellNum++) { // 循环该行的每一个单元格
                                    HSSFCell cell = row.getCell(cellNum);
                                    if (cell != null) {
                                        if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                            continue;
                                        } else {
                                            StringBuffer tdStyle = new StringBuffer(
                                                    "<td style=\"border:1px solid #000; border-width:0 1px 1px 0;margin:2px 0 2px 0; ");
                                            HSSFCellStyle cellStyle = cell
                                                    .getCellStyle();
                                            HSSFPalette palette = workbook
                                                    .getCustomPalette(); // 类HSSFPalette用于求颜色的国际标准形式
                                            HSSFColor hColor = palette
                                                    .getColor(cellStyle
                                                            .getFillForegroundColor());
                                            HSSFColor hColor2 = palette
                                                    .getColor(cellStyle
                                                            .getFont(workbook)
                                                            .getColor());

                                            String bgColor = convertToStardColor(hColor);// 背景颜色
                                            short boldWeight = cellStyle
                                                    .getFont(workbook)
                                                    .getBoldweight(); // 字体粗细
                                            short fontHeight = (short) (cellStyle
                                                    .getFont(workbook)
                                                    .getFontHeight() / 2); // 字体大小
                                            String fontColor = convertToStardColor(hColor2); // 字体颜色
                                            if (bgColor != null
                                                    && !"".equals(bgColor
                                                    .trim())) {
                                                tdStyle.append(" background-color:"
                                                        + bgColor + "; ");
                                            }
                                            if (fontColor != null
                                                    && !"".equals(fontColor
                                                    .trim())) {
                                                tdStyle.append(" color:"
                                                        + fontColor + "; ");
                                            }
                                            tdStyle.append(" font-weight:"
                                                    + boldWeight + "; ");
                                            tdStyle.append(" font-size: "
                                                    + fontHeight + "%;");
                                            lsb.append(tdStyle + "\"");

                                            int width = (int) (sheet.getColumnWidth(cellNum) / 35.7);

                                            int cellReginCol = getMergerCellRegionCol(
                                                    sheet, rowNum, cellNum); // 合并的列（solspan）
                                            int cellReginRow = getMergerCellRegionRow(
                                                    sheet, rowNum, cellNum);// 合并的行（rowspan）
                                            String align = convertVerticalAlignToHtml(cellStyle
                                                    .getAlignment()); //
                                            String vAlign = convertVerticalAlignToHtml(cellStyle
                                                    .getVerticalAlignment());

                                            lsb.append(" align=\"" + align
                                                    + "\" valign=\"" + vAlign
                                                    + "\" width=\"" + width
                                                    + "\" ");

                                            lsb.append(" colspan=\""
                                                    + cellReginCol
                                                    + "\" rowspan=\""
                                                    + cellReginRow + "\"");
                                            lsb.append(">" + getCellValue(cell)
                                                    + "</td>");
                                        }
                                    }
                                }
                                lsb.append("</tr>");
                            }
                        }
                    }

                }

            }
            output.write(lsb.toString().getBytes());
        } catch (FileNotFoundException e) {
            throw new Exception("文件" + excelFileName + " 没有找到!");
        } catch (IOException e) {
            throw new Exception("文件" + excelFileName + " 处理错误("
                    + e.getMessage() + ")!");
        }
        return lsb;
    }


    public StringBuffer readXLSX2() throws Exception {
        StringBuffer lsb = new StringBuffer();

        myFile = new File(htmlPath);
        output = new FileOutputStream(myFile);
        lsb.append("<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>");
        lsb.append("<head><meta http-equiv=Content-Type content='text/html; charset=utf-8'><meta name=ProgId content=Excel.Sheet>");
        XSSFSheet sheet;

        String excelFileName = nameStr;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(
                    excelFileName)); // 获整个Excel

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                if (workbook.getSheetAt(sheetIndex) != null) {
                    sheet = workbook.getSheetAt(sheetIndex);// 获得不为空的这个sheet
                    String sheetName = workbook.getSheetName(sheetIndex); // sheetName
                    if (sheet != null) {
                        int firstRowNum = sheet.getFirstRowNum(); // 第一行
                        int lastRowNum = sheet.getLastRowNum(); // 最后一行

                        lsb.append("<table width=\"100%\" style=\"border:1px solid #000;border-width:1px 0 0 1px;margin:2px 0 2px 0;border-collapse:collapse;\">");

                        lsb.append("<h1>" + sheetName + "</h1>");

                        for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
                            if (sheet.getRow(rowNum) != null) {// 如果行不为空，
                                XSSFRow row = sheet.getRow(rowNum);
                                short firstCellNum = row.getFirstCellNum(); // 该行的第一个单元格
                                short lastCellNum = row.getLastCellNum(); // 该行的最后一个单元格
                                int height = (int) (row.getHeight() / 15.625); // 行的高度
                                lsb.append("<tr height=\""
                                        + height
                                        + "\" style=\"border:1px solid #000;border-width:0 1px 1px 0;margin:2px 0 2px 0;\">");
                                for (short cellNum = firstCellNum; cellNum <= lastCellNum; cellNum++) { // 循环该行的每一个单元格
                                    XSSFCell cell = row.getCell(cellNum);
                                    if (cell != null) {
                                        StringBuffer tdStyle = new StringBuffer(
                                                "<td style=\"border:1px solid #000; border-width:0 1px 1px 0;margin:2px 0 2px 0; ");
                                        XSSFCellStyle cellStyle = cell
                                                .getCellStyle();
//                                            HSSFPalette palette = workbook
//                                                    .getCustomPalette(); // 类HSSFPalette用于求颜色的国际标准形式
//                                            HSSFColor hColor = palette
//                                                    .getColor(cellStyle
//                                                            .getFillForegroundColor());
//                                            HSSFColor hColor2 = palette
//                                                    .getColor(cellStyle
//                                                            .getFont(workbook)
//                                                            .getColor());

//                                            String bgColor = convertToStardColor(hColor);// 背景颜色
//                                            short boldWeight = cellStyle
//                                                    .getFont(workbook)
//                                                    .getBoldweight(); // 字体粗细
//                                            short fontHeight = (short) (cellStyle
//                                                    .getFont(workbook)
//                                                    .getFontHeight() / 2); // 字体大小
//                                            String fontColor = convertToStardColor(hColor2); // 字体颜色
//                                            if (bgColor != null
//                                                    && !"".equals(bgColor
//                                                    .trim())) {
//                                                tdStyle.append(" background-color:"
//                                                        + bgColor + "; ");
//                                            }
//                                            if (fontColor != null
//                                                    && !"".equals(fontColor
//                                                    .trim())) {
//                                                tdStyle.append(" color:"
//                                                        + fontColor + "; ");
//                                            }
//                                            tdStyle.append(" font-weight:"
//                                                    + boldWeight + "; ");
//                                            tdStyle.append(" font-size: "
//                                                    + fontHeight + "%;");
                                        lsb.append(tdStyle + "\"");

                                        int width = (int) (sheet.getColumnWidth(cellNum) / 35.7);

                                        int cellReginCol = getMergerCellRegionCol(
                                                sheet, rowNum, cellNum); // 合并的列（solspan）
                                        int cellReginRow = getMergerCellRegionRow(
                                                sheet, rowNum, cellNum);// 合并的行（rowspan）
                                        String align = convertVerticalAlignToHtml(cellStyle
                                                .getAlignment()); //
                                        String vAlign = convertVerticalAlignToHtml(cellStyle
                                                .getVerticalAlignment());

                                        lsb.append(" align=\"" + align
                                                + "\" valign=\"" + vAlign
                                                + "\" width=\"" + width
                                                + "\" ");

                                        lsb.append(" colspan=\""
                                                + cellReginCol
                                                + "\" rowspan=\""
                                                + cellReginRow + "\"");
                                        if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
                                            lsb.append(">" + ""
                                                    + "</td>");
                                        } else {
                                            lsb.append(">" + getCellValue(cell)
                                                    + "</td>");
                                        }
                                    }
                                }
                                lsb.append("</tr>");
                            }
                        }
                    }

                }

            }
            output.write(lsb.toString().getBytes());
        } catch (FileNotFoundException e) {
            throw new Exception("文件" + excelFileName + " 没有找到!");
        } catch (IOException e) {
            throw new Exception("文件" + excelFileName + " 处理错误("
                    + e.getMessage() + ")!");
        }
        return lsb;
    }


    /**
     * 取得单元格的值
     *
     * @param cell
     * @return
     * @throws IOException
     */
    private static Object getCellValue(Cell cell) {
        Object value = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    value = sdf.format(date);
                } else {
                    double value_temp = cell.getNumericCellValue();
                    DecimalFormat format = new DecimalFormat("#0.###");
                    value = format.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                value = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                value = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                value = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                value = "非法字符";
                break;
            default:
                value = "未知类型";
                break;

        }
        return value;
    }

    /**
     * 取得单元格的值
     *
     * @param cell
     * @return
     * @throws IOException
     */
    private static Object getCellValue(HSSFCell cell) {
        Object value = "";
        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            value = cell.getRichStringCellValue().toString();
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                value = sdf.format(date);
            } else {
                double value_temp = cell.getNumericCellValue();
                BigDecimal bd = new BigDecimal(value_temp);
                BigDecimal bd1 = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
                value = bd1.doubleValue();

                DecimalFormat format = new DecimalFormat("#0.###");
                value = format.format(cell.getNumericCellValue());

            }
        }
        if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
            value = "";
        }
        return value;
    }

    /**
     * 判断单元格在不在合并单元格范围内，如果是，获取其合并的列数。
     *
     * @param sheet   工作表
     * @param cellRow 被判断的单元格的行号
     * @param cellCol 被判断的单元格的列号
     * @return
     * @throws IOException
     */
    private static int getMergerCellRegionCol(Sheet sheet, int cellRow,
                                              int cellCol) {
        int retVal = 0;
        int sheetMergerCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            CellRangeAddress cra = sheet.getMergedRegion(i);
            int firstRow = cra.getFirstRow(); // 合并单元格CELL起始行
            int firstCol = cra.getFirstColumn(); // 合并单元格CELL起始列
            int lastRow = cra.getLastRow(); // 合并单元格CELL结束行
            int lastCol = cra.getLastColumn(); // 合并单元格CELL结束列
            if (cellRow >= firstRow && cellRow <= lastRow) { // 判断该单元格是否是在合并单元格中
                if (cellCol >= firstCol && cellCol <= lastCol) {
                    retVal = lastCol - firstCol + 1; // 得到合并的列数
                    break;
                }
            }
        }
        return retVal;
    }

    /**
     * 判断单元格是否是合并的单格，如果是，获取其合并的行数。
     *
     * @param sheet   表单
     * @param cellRow 被判断的单元格的行号
     * @param cellCol 被判断的单元格的列号
     * @return
     * @throws IOException
     */
    private static int getMergerCellRegionRow(Sheet sheet, int cellRow,
                                              int cellCol) {
        int retVal = 0;
        int sheetMergerCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            CellRangeAddress cra = sheet.getMergedRegion(i);
            int firstRow = cra.getFirstRow(); // 合并单元格CELL起始行
            int firstCol = cra.getFirstColumn(); // 合并单元格CELL起始列
            int lastRow = cra.getLastRow(); // 合并单元格CELL结束行
            int lastCol = cra.getLastColumn(); // 合并单元格CELL结束列
            if (cellRow >= firstRow && cellRow <= lastRow) { // 判断该单元格是否是在合并单元格中
                if (cellCol >= firstCol && cellCol <= lastCol) {
                    retVal = lastRow - firstRow + 1; // 得到合并的行数
                    break;
                }
            }
        }
        return 0;
    }

    /**
     * 单元格背景色转换
     *
     * @param hc
     * @return
     */
    private String convertToStardColor(HSSFColor hc) {
        StringBuffer sb = new StringBuffer("");
        if (hc != null) {
            int a = HSSFColor.AUTOMATIC.index;
            int b = hc.getIndex();
            if (a == b) {
                return null;
            }
            sb.append("#");
            for (int i = 0; i < hc.getTriplet().length; i++) {
                String str;
                String str_tmp = Integer.toHexString(hc.getTriplet()[i]);
                if (str_tmp != null && str_tmp.length() < 2) {
                    str = "0" + str_tmp;
                } else {
                    str = str_tmp;
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    /**
     * 单元格小平对齐
     *
     * @param alignment
     * @return
     */
    private String convertAlignToHtml(short alignment) {
        String align = "left";
        switch (alignment) {
            case HSSFCellStyle.ALIGN_LEFT:
                align = "left";
                break;
            case HSSFCellStyle.ALIGN_CENTER:
                align = "center";
                break;
            case HSSFCellStyle.ALIGN_RIGHT:
                align = "right";
                break;
            default:
                break;
        }
        return align;
    }

    /**
     * 单元格垂直对齐
     *
     * @param verticalAlignment
     * @return
     */
    private String convertVerticalAlignToHtml(short verticalAlignment) {
        String valign = "middle";
        switch (verticalAlignment) {
            case HSSFCellStyle.VERTICAL_BOTTOM:
                valign = "bottom";
                break;
            case HSSFCellStyle.VERTICAL_CENTER:
                valign = "center";
                break;
            case HSSFCellStyle.VERTICAL_TOP:
                valign = "top";
                break;
            default:
                break;
        }
        return valign;
    }


    /* 用来在sdcard上创建图片 */
    public void makePictureFile() {
        try {
            File pictureFile = new File(Constants.APP_IMG
                    + getFileName(nameStr) + presentPicture + ".jpg");// 创建jpg文件,方法与html相同
            if (!pictureFile.exists()) {
                pictureFile.createNewFile();
            }
            this.picturePath = pictureFile.getAbsolutePath();// 获取jpg文件绝对路径
        } catch (Exception e) {
            System.out.println("PictureFile Catch Exception");
        }

    }

    public String getFileName(String pathandname) {
        int start = pathandname.lastIndexOf("/");
        int end = pathandname.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return pathandname.substring(start + 1, end);
        } else {
            return null;
        }
    }


    public void writePicture() {
        Picture picture = pictures.get(presentPicture);
        byte[] pictureBytes = picture.getContent();
        Bitmap bitmap = BitmapFactory.decodeByteArray(pictureBytes, 0,
                pictureBytes.length);


        makePictureFile();
        presentPicture++;

        File myPicture = new File(picturePath);

        try {
            FileOutputStream outputPicture = new FileOutputStream(myPicture);
            outputPicture.write(pictureBytes);
            outputPicture.close();
        } catch (Exception e) {
            System.out.println("outputPicture Exception");
        }
        String imageString = "<img src=\"" + picturePath + "\"";
        imageString = imageString + ">";
        try {
            output.write(imageString.getBytes());
        } catch (Exception e) {
            System.out.println("output Exception");
        }
    }

    public void writeDOCXPicture(byte[] pictureBytes) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(pictureBytes, 0,
                pictureBytes.length);
        makePictureFile();
        this.presentPicture++;
        File myPicture = new File(this.picturePath);
        try {
            FileOutputStream outputPicture = new FileOutputStream(myPicture);
            outputPicture.write(pictureBytes);
            outputPicture.close();
        } catch (Exception e) {
            System.out.println("outputPicture Exception");
        }
        String imageString = "<img src=\"" + this.picturePath + "\"";

        imageString = imageString + ">";
        try {
            this.output.write(imageString.getBytes());
        } catch (Exception e) {
            System.out.println("output Exception");
        }
    }

    public void writeParagraphContent(Paragraph paragraph) {
        Paragraph p = paragraph;
        int pnumCharacterRuns = p.numCharacterRuns();

        for (int j = 0; j < pnumCharacterRuns; j++) {

            CharacterRun run = p.getCharacterRun(j);

            if (run.getPicOffset() == 0 || run.getPicOffset() >= 1000) {
                if (presentPicture < pictures.size()) {
                    writePicture();
                }
            } else {
                try {
                    String text = run.text();
                    if (text.length() >= 2 && pnumCharacterRuns < 2) {
                        output.write(text.getBytes());
                    } else {
                        int size = run.getFontSize();
                        int color = run.getColor();
                        String fontSizeBegin = "<font size=\""
                                + decideSize(size) + "\">";
                        String fontColorBegin = "<font color=\""
                                + decideColor(color) + "\">";
                        String fontEnd = "</font>";
                        String boldBegin = "<b>";
                        String boldEnd = "</b>";
                        String islaBegin = "<i>";
                        String islaEnd = "</i>";

                        output.write(fontSizeBegin.getBytes());
                        output.write(fontColorBegin.getBytes());

                        if (run.isBold()) {
                            output.write(boldBegin.getBytes());
                        }
                        if (run.isItalic()) {
                            output.write(islaBegin.getBytes());
                        }

                        output.write(text.getBytes());

                        if (run.isBold()) {
                            output.write(boldEnd.getBytes());
                        }
                        if (run.isItalic()) {
                            output.write(islaEnd.getBytes());
                        }
                        output.write(fontEnd.getBytes());
                        output.write(fontEnd.getBytes());
                    }
                } catch (Exception e) {
                    System.out.println("Write File Exception");
                }
            }
        }
    }

    public int decideSize(int size) {
        if (size >= 1 && size <= 8) {
            return 1;
        }
        if (size >= 9 && size <= 11) {
            return 2;
        }
        if (size >= 12 && size <= 14) {
            return 3;
        }
        if (size >= 15 && size <= 19) {
            return 4;
        }
        if (size >= 20 && size <= 29) {
            return 5;
        }
        if (size >= 30 && size <= 39) {
            return 6;
        }
        if (size >= 40) {
            return 7;
        }
        return 3;
    }

    private String decideColor(int a) {
        int color = a;
        switch (color) {
            case 1:
                return "#000000";
            case 2:
                return "#0000FF";
            case 3:
            case 4:
                return "#00FF00";
            case 5:
            case 6:
                return "#FF0000";
            case 7:
                return "#FFFF00";
            case 8:
                return "#FFFFFF";
            case 9:
                return "#CCCCCC";
            case 10:
            case 11:
                return "#00FF00";
            case 12:
                return "#080808";
            case 13:
            case 14:
                return "#FFFF00";
            case 15:
                return "#CCCCCC";
            case 16:
                return "#080808";
            default:
                return "#000000";
        }
    }

    /**
     * 读取txt文件
     *
     * @param path
     * @return
     */
    public static String readTXT(String path) {
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(new File(path)),
                    "UTF-8");
            if (inputStreamReader != null) {
                reader = new BufferedReader(inputStreamReader);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            if (reader != null) {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 读取txt文件
     *
     * @param path
     * @return
     */
    public static String readAppKey(String path) {
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(new File(path)),
                    "UTF-8");
            if (inputStreamReader != null) {
                reader = new BufferedReader(inputStreamReader);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            if (reader != null) {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 读取ppt文件
     *
     * @return
     */
    public String readPPTX(String path) {
        List<String> ls = new ArrayList<>();
        String river = "";
        ZipFile xlsxFile = null;
        try {
            xlsxFile = new ZipFile(new File(path));// pptx按照读取zip格式读取
        } catch (ZipException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            ZipEntry sharedStringXML = xlsxFile.getEntry("[Content_Types].xml");// 找到里面存放内容的文件
            InputStream inputStream = xlsxFile.getInputStream(sharedStringXML);// 将得到文件流
            XmlPullParser xmlParser = Xml.newPullParser();// 实例化pull
            xmlParser.setInput(inputStream, "utf-8");// 将流放进pull中
            int evtType = xmlParser.getEventType();// 得到标签类型的状态
            while (evtType != XmlPullParser.END_DOCUMENT) {// 循环读取流
                switch (evtType) {
                    case XmlPullParser.START_TAG: // 判断标签开始读取
                        String tag = xmlParser.getName();// 得到标签
                        if (tag.equalsIgnoreCase("Override")) {
                            String s = xmlParser
                                    .getAttributeValue(null, "PartName");
                            if (s.lastIndexOf("/ppt/slides/slide") == 0) {
                                ls.add(s);
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:// 标签读取结束
                        break;
                    default:
                        break;
                }
                evtType = xmlParser.next();// 读取下一个标签
            }
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < (ls.size() + 1); i++) {// 假设有6张幻灯片
            river += "第" + i + "张················" + "\n";
            try {
                ZipEntry sharedStringXML = xlsxFile.getEntry("ppt/slides/slide"
                        + i + ".xml");// 找到里面存放内容的文件
                InputStream inputStream = xlsxFile
                        .getInputStream(sharedStringXML);// 将得到文件流
                XmlPullParser xmlParser = Xml.newPullParser();// 实例化pull
                xmlParser.setInput(inputStream, "utf-8");// 将流放进pull中
                int evtType = xmlParser.getEventType();// 得到标签类型的状态
                while (evtType != XmlPullParser.END_DOCUMENT) {// 循环读取流
                    switch (evtType) {
                        case XmlPullParser.START_TAG: // 判断标签开始读取
                            String tag = xmlParser.getName();// 得到标签
                            if (tag.equalsIgnoreCase("t")) {
                                river += xmlParser.nextText() + "\n";
                            }
                            break;
                        case XmlPullParser.END_TAG:// 标签读取结束
                            break;
                        default:
                            break;
                    }
                    evtType = xmlParser.next();// 读取下一个标签
                }
            } catch (ZipException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
        if (river == null) {
            river = "解析文件出现问题";
        }
        return river;
    }

    /**
     * 读取ppt
     *
     * @param path
     * @return
     */
    public ArrayList<String> readPPT(String path) {
        ArrayList<String> contentArray = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            HSLFSlideShow hslf = new HSLFSlideShow(fis);
            PictureData[] pictures = hslf.getPictures();

            SlideShow ss = new SlideShow(new HSLFSlideShow(fis));
            Slide[] slides = ss.getSlides();
            for (Slide slide : slides) {
                //读取一张幻灯片的标题
                String title = slide.getTitle();
                System.out.println("标题:" + title);
                //读取一张幻灯片的内容(包括标题)
                TextRun[] runs = slide.getTextRuns();
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < runs.length; j++) {
                    sb.append(runs[j].getText());
                }
                contentArray.add(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentArray;
    }

    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     */
    public static long getFileSize(File file) {
        long size = 0;
        if (file.exists()) {
            try {
                size = new FileInputStream(file).available();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
                Log.e("获取文件大小", "文件不存在！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String finalSizeString = "";
        String worngSize = "0B";
        if (fileS == 0) {
            return worngSize;
        }
        if (fileS < 1024) {
            finalSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            finalSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            finalSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            finalSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return finalSizeString;
    }
}
