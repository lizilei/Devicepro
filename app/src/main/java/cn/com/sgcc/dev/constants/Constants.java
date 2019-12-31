package cn.com.sgcc.dev.constants;

import android.Manifest;
import android.os.Environment;

/**
 * <p>@description:常量类</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/18
 */

public class Constants {
    //南思端口写入  NSData/NSClient/ServerAddress.json
    public static final String NSData_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/NSData";
    //南思端口写入
    public static final String NSClient_PATH = NSData_PATH + "/NSClient/";
    //跟目录
    public static final String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DevicePro";
    //导入数据存储目录（db文件）
    public static final String INPUT_PATH = ROOT_PATH + "/sg/";
    //导入附件存储目录
    public static final String INPUT_TMPWD = ROOT_PATH + "/sg/tmpwd";
    //导出数据存储目录导入数据存储目录（db文件）
    public static final String OUTPUT_PATH = ROOT_PATH + "/source/";
    //App更新文件存储路径
    public static final String APP_UPDATE = ROOT_PATH + "/update";
    //App附件存储路径
    public static final String APP_ACCESSORY = ROOT_PATH + "/accessory";
    //Office转换html存储路径
    public static final String APP_OFFICE_HTML = ROOT_PATH + "/office_html";
    //App附件图片存储路径
    public static final String APP_IMG = APP_ACCESSORY + "/img/";
    //App附件office路径
    public static final String APP_OFFICE = APP_ACCESSORY + "/office";
    //App附件txt路径
    public static final String APP_TXT = APP_ACCESSORY + "/txt";
    //App附件pdf路径
    public static final String APP_PDF = APP_ACCESSORY + "/pdf";
    //App配置文件隐藏路径
    public static final String APP_DEFAULT = ROOT_PATH + "/.def";
    //App配置文件隐藏路径
    public static final String APP_DO = ROOT_PATH + "/execute";

    //校验原始数据存储路径
    public static final String APP_JY = APP_DEFAULT + "/JiaoYanData.txt";
    /**
     * 异常日志 存储位置为根目录下的 Crash文件夹
     */
    public static final String CRASH_PATH = ROOT_PATH + "/crash/";
    /**
     * 默认数据备份目录
     */
    public static final String APP_DATA_STORE = ROOT_PATH + "/store";
    /**
     * 备份配置文件
     */
    public static final String APP_STORE = APP_DEFAULT + "/store.txt";
    /**
     * 测试读取key文件
     */
    public static final String APP_AUTHORIZE = ROOT_PATH + "/authorize/";

    /**
     * 是否包含南思功能
     */
    public static final boolean ISNS = true;

    public static final String DBBB = "";
    public static String DB_NAME = "";
    public static String OUT_DB_NAME = "";
    public static String OUT_DB_PATH = "";

    public static final String[] PERMISSIONS = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
            , Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE};

    public static final String[] pxOne = {"电压等级", "保护类别"};
    public static final String[] pxTwo = {"正序", "倒序", "重置"};
    //    public static final String[] clzt = {"未处理", "已处理", "已删除", "已新增", "已导出", "重置"};
    public static final String[] clzt = {"未处理", "已处理", "已删除", "新增", "已导出", "重置"};
    public static final String[] fjfl = {"txt文档", "office文档", "pdf文档"};
    //    public static final String[] xqfl = {"装置基本信息", "安装及运维信息", "运行基本信息","板卡信息","电子标签信息","CT信息","资产信息","附件信息"};
    public static final String[] xqfl = {"装置基本信息", "安装及运维信息", "运行基本信息", "板卡信息", "资产信息", "附件信息"};
    public static final String[] Bhfl = {"装置基本信息", "安装及运维信息", "运行基本信息", "板卡信息", "CT信息", "资产信息", "附件信息"};
    //    public static final String[] Bhfl = {"装置基本信息", "安装及运维信息", "运行基本信息","通道信息","安控系统信息","板卡信息","ICD文件信息","连接器信息","电子标签信息","CT信息","资产信息"};
    public static final String[] zzfl = {"国产", "进口"};
    public static final String[] zzlx = {"微机型", "电磁型", "集成电路", "晶体管"};
    public static String[] zzlxNoRjbb = {"操作箱", "电压切换箱", "操作箱/电压切换箱", "光纤通信接口装置"};

    //辅助必填项
    public static final String[] BTX_FZ = {"六统一标准版本", "制造厂家", "装置类别", "装置型号", "装置分类", "装置类型", "模块名称", "一次设备类型", "一次设备名称", "电压等级", "单位名称", "运行状态", "定期检验周期", "ICD文件名"};

    public static final int rjbb_cout = 5;

    public static int sy_cout = 5;
    //五分钟
    public static int ch_times = 300001;
    //2分钟
    public static int ch_timess = 5000;

    //定时备份时间，默认60分钟
    public static int store_time = 3600000;
//    public static int store_time = 120000;

    public static final int ver_info_limt = 5;
    public static boolean ischeck = false;

    public static boolean loginout = false;
    public static final String admin = "admin";
    public static final String admin_pw = "admindky";

    //登录ISC返回的cookue，每次请求添加到请求头里
    public static String Cookie = "";
    //是否在线登录
    public static boolean isLoginOnLine;
    //公共接口
//    public static final String ROOT_URL = "http://192.168.2.230:8091/RLST/";
    public static String ROOT_URL = "http://192.168.2.146:8080";
//    public static String ROOT_URL = "http://192.168.2.103:8080";
//    public static final String ROOT_URL = "http://10.0.2.2:8080/haircutht/";
//    public static final String ROOT_URL = "http://192.168.8.104:8080/haircutht/";

    /**
     * 登录接口
     */
//    public static String GET_USER_LOGIN = "/RLST/appLogin.action";
    public static String GET_USER_LOGIN = "/sgsms/iscintegrate/rest/common/loginuser/validate";

    /**
     * 获取当前登录信息
     */
    public static String GET_USERBEAN = "/sgsms/tongfen/rest/getCurrentUserInTongFen";
    /**
     * 获取厂站
     */
    public static String GET_CZXX = "/sgsms/tongfen/rest/getStationListByUserAccount";

    /**
     * 下载数据文件
     */
    public static String APP_DOWN_DBFILE = "/sgsms/tongfen/rest/getDeviceDataFile";

    /**
     * 上传数据文件
     */
    public static String APP_UP_DBFILE = "/sgsms/tongfen/rest/postDeviceDataFile";
}
