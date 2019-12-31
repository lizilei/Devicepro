package cn.com.sgcc.dev.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.datapick.widget.DatePickDialog;
import com.datapick.widget.OnSureLisener;
import com.datapick.widget.bean.DateType;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.constants.JiaoYanDataConstant;
import cn.com.sgcc.dev.customeView.MyDatePickerDialog;
import cn.com.sgcc.dev.model2.vo.JiaoYanData;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.view.activity.JiaoYanActivity;
import cn.com.sgcc.dev.view.activity.MainActivitys;
import cn.com.sgcc.dev.view.viewinterface.DateChooseListener;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/6
 */

public class AppUtils {

    /**
     * 判断网路是否可用
     *
     * @param context
     * @return
     */
    public static boolean checkNetwork(Context context) {
        if (isNetworkConnected(context)) {
            return isWifiConnected(context) || isMobileConnected(context);
        } else {
            return false;
        }
    }

    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 判断wifi 是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断手机网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 通用获取IP的方法
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            return ex.getMessage();
        }
        return null;
    }

    /**
     * 判断是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNUmeric(String str) {
        String reg = "[0-9]*";
        Pattern pattern = Pattern.compile(reg);
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static void showDialog(final Context context, String msg, View.OnClickListener lcancel, View.OnClickListener lensure) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 0.5f;
        ((Activity) context).getWindow().setAttributes(lp);
        final PopupWindow pop = new PopupWindow(context);
        View view = LayoutInflater.from(context).inflate(R.layout.common_dialog_view, null);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_ensure = (TextView) view.findViewById(R.id.tv_ensure);
        tv_message.setText(msg);

        tv_cancel.setOnClickListener(lcancel);
        tv_cancel.setTag(pop);
        tv_ensure.setOnClickListener(lensure);
        tv_ensure.setTag(pop);

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
                lp.alpha = 1.0f;
                ((Activity) context).getWindow().setAttributes(lp);
            }
        });

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(false);
        pop.setContentView(view);
        pop.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public static void showDialogs(final Context context, View.OnClickListener lcancel, View.OnClickListener lensure) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 0.1f;
        ((Activity) context).getWindow().setAttributes(lp);
        final PopupWindow pop = new PopupWindow(context);
        View view = LayoutInflater.from(context).inflate(R.layout.common_dialog_views, null);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_ensure = (TextView) view.findViewById(R.id.tv_ensure);
        tv_cancel.setOnClickListener(lcancel);
        tv_cancel.setTag(pop);
        tv_ensure.setOnClickListener(lensure);
        tv_ensure.setTag(pop);

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
                lp.alpha = 1.0f;
                ((Activity) context).getWindow().setAttributes(lp);
            }
        });

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(false);
        pop.setContentView(view);
        pop.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    /**
     * 时间选择控件
     *
     * @param context
     * @param listener
     */
    public static void showDateDialog(Context context, final DateChooseListener listener) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        new MyDatePickerDialog(context, new MyDatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String time = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                listener.onDateChooseListener(TimeUtil.formatString2(time));
            }
        }, mYear, mMonth, mDay).myShow();
    }


    /**
     * 时间选择控件，带时分秒
     */
    public static void showDatePickDialog(Context context, final DateType type, final DateChooseListener listener) {
        DatePickDialog dialog = new DatePickDialog(context);
        //设置上下年分限制
        dialog.setYearLimt(40);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(type);
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd HH:mm:ss");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                if (date == null) {
                    listener.onDateChooseListener("");
                } else {
                    if (type.equals(DateType.TYPE_YMDHM)) {
                        listener.onDateChooseListener(TimeUtil.dateToStr2(date));
                    } else {
                        listener.onDateChooseListener(TimeUtil.dateToStr(date));
                    }
                }
//                Toast.makeText(MainActivity.this,date+"",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    public static Map setTxt(Map keymap, String value, boolean save) {
        Map<String, String> txtMap = new HashMap<>();
        String result = "";
        //保存默认配置，隐藏
        File defaultTxt = new File(Constants.APP_DEFAULT + "/cssz.txt");
        if (!defaultTxt.exists()) {
            //先得到文件的上级目录，并创建上级目录，在创建文件
            defaultTxt.getParentFile().mkdir();
            try {
                //创建文件
                defaultTxt.createNewFile();
                save = true;
                keymap.put("syms", "否");
                keymap.put("sycs", "0");
                keymap.put("symax", "5");
                keymap.put("csmax", "1800000");
                keymap.put("auto_store", "60");
                keymap.put("store_path", Constants.APP_DATA_STORE);
                keymap.put("bdms", "否");
                keymap.put("jgms", "否");
                keymap.put("tbms", "否");
                keymap.put("sbxx", "");
                keymap.put("base_url", Constants.ROOT_URL);
                txtMap = keymap;
//                Log.e("隐藏读取成功","初始化成功");
            } catch (IOException e) {
                e.printStackTrace();
//                Log.e("隐藏读取成功","初始化失败");
            }
        }

        if (save) {
            //判断SD卡是否可读写
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(defaultTxt);
                    String syms = keymap.get("syms") + "";
                    String sycs = keymap.get("sycs") + "";
                    String symax = keymap.get("symax") + "";
                    String csmax = keymap.get("csmax") + "";
                    String bdms = keymap.get("bdms") + "";
                    String jgms = keymap.get("jgms") + "";
                    String tbms = keymap.get("tbms") + "";
                    String sbxx = keymap.get("sbxx") + "";
                    String base_url = keymap.get("base_url") + "";
                    String auto_store = keymap.get("auto_store") + "";
                    String store_path = keymap.get("store_path") + "";
//                    StringBuilder sb = new StringBuilder();
//                    for (Object o : keymap.entrySet()) {
//                        sb.append("&");
//                        sb.append(o.toString());
//                    }

                    String listStr = "1&" + syms + "&" + sycs + "&" + symax + "&" + csmax + "&" + bdms + "&" + jgms + ""
                            + "&" + sbxx + "" + "&" + tbms + "" + "&" + base_url + "&" + auto_store + "&" + store_path;

                    fileOutputStream.write(listStr.getBytes());
                    //存入赋值
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
//                    Log.e("隐藏读取成功","写入成功"+listStr);
                } catch (Exception e) {
                    e.printStackTrace();
//                    Log.e("隐藏读取成功","写入失败"+keymap.toString());
                }

            }
        } else {

//       读取文件内容保存到resultStr
            String resultStr = null;
            File file = new File(Constants.APP_DEFAULT + "/cssz.txt");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] b = new byte[fileInputStream.available()];
                fileInputStream.read(b);
                resultStr = new String(b);
                //读取赋值
                result = resultStr;
                String[] ss = result.split("&");
                if (ss.length > 5) {
                    txtMap.put("syms", ss[1]);
                    txtMap.put("csmax", ss[4]);
                    txtMap.put("sycs", ss[2]);
                    txtMap.put("symax", ss[3]);
                    txtMap.put("bdms", ss[5]);
                    if (ss.length > 6) {
                        txtMap.put("jgms", ss[6] + "");
                    } else {
                        txtMap.put("jgms", "");
                    }
                    if (ss.length > 7) {
                        txtMap.put("sbxx", ss[7] + "");
                    } else {
                        txtMap.put("sbxx", "");
                    }
                    if (ss.length > 8) {
                        txtMap.put("tbms", ss[8] + "");
                    } else {
                        txtMap.put("tbms", "");
                    }
                    if (ss.length > 9) {
                        txtMap.put("base_url", ss[9] + "");
                    } else {
                        txtMap.put("base_url", Constants.ROOT_URL);
                    }
                    if (ss.length > 10) {
                        txtMap.put("auto_store", ss[10] + "");
                    } else {
                        txtMap.put("auto_store", "60");
                    }
                    if (ss.length > 11) {
                        txtMap.put("store_path", ss[11] + "");
                    } else {
                        txtMap.put("store_path", Constants.APP_DATA_STORE);
                    }
                }
//                Log.e("隐藏读取成功",resultStr+"");
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
//                Log.e("隐藏读取成功","获取失败"+resultStr);
            }
        }
        return txtMap;
    }

    public static Map setTxt(Map keymap, boolean save) {
        Map<String, String> txtMap = new HashMap<>();
        //保存默认配置，隐藏
        File defaultTxt = new File(Constants.APP_DEFAULT + "/cssz.txt");
        if (!defaultTxt.exists()) {
            //先得到文件的上级目录，并创建上级目录，在创建文件
            defaultTxt.getParentFile().mkdir();
            try {
                //创建文件
                defaultTxt.createNewFile();
                save = true;
                keymap.put("syms", "否");
                keymap.put("sycs", "0");
                keymap.put("symax", "5");
                keymap.put("csmax", "1800000");
                keymap.put("auto_store", "1800000");
                keymap.put("store_path", Constants.APP_DATA_STORE);
                keymap.put("bdms", "否");
                keymap.put("jgms", "否");
                keymap.put("sbxx", "");
                keymap.put("base_url", Constants.ROOT_URL);
                txtMap = keymap;
//                Log.e("隐藏读取成功","初始化成功");
            } catch (IOException e) {
                e.printStackTrace();
//                Log.e("隐藏读取成功","初始化失败");
            }
        }

        if (save) {
            //判断SD卡是否可读写
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                FileUtils.writeObject(txtMap, defaultTxt);
            }
        } else {
            txtMap = (Map<String, String>) FileUtils.readObject(defaultTxt);
        }
        return txtMap;
    }

    //生成原始校验文件
    public static void createJYFile(Activity context) {
        //隐藏校验文件
        File defaultTxt = new File(Constants.APP_DEFAULT + "/JiaoYanData.txt");
        if (!defaultTxt.exists()) {
            //先得到文件的上级目录，并创建上级目录，在创建文件
            defaultTxt.getParentFile().mkdir();
            try {
                //创建文件
                defaultTxt.createNewFile();
                String s2 = JiaoYanDataConstant.JiaoYanJsonData;
                writeJsonFile(s2, Constants.APP_JY);
                //ToastUtils.showToast(context, "生成校验原始数据");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //生成执行文件夹
    public static String execute(Boolean isdelete) {
        //隐藏校验文件
        File defaultTxt = new File(Constants.APP_DO + "/execute.txt");
        if (isdelete) {
            defaultTxt.delete();
        }
        if (!defaultTxt.exists()) {
            //先得到文件的上级目录，并创建上级目录，在创建文件
            defaultTxt.getParentFile().mkdir();
            try {
                //创建文件
                defaultTxt.createNewFile();
                //ToastUtils.showToast(context, "初始化执行文件");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //       读取文件内容保存到resultStr
        String resultStr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(defaultTxt);
            InputStreamReader reader = new InputStreamReader(fileInputStream, "UTF-8");
            InputStreamReader readerG = new InputStreamReader(fileInputStream, "GBK");
            byte[] b = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            resultStr = new String(b, "GBK") + "";
            String GG = new String(b, "GBK") + "";
            resultStr = GG.toString().replaceAll("\r|\n*", "");
            //读取赋值
//          Log.e("隐藏读取成功",resultStr+"");
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
//                Log.e("隐藏读取成功","获取失败"+resultStr);
        }
        return resultStr;
    }

    /**
     * 读取json文件
     */
    public static String readJsonFile(String path) {
        String laststrJson = "";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File(path)));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                laststrJson = laststrJson + tempString;
                line++;
            }
            reader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return laststrJson;
    }

    /**
     * 写出json文件
     */
    public static boolean writeJsonFile(String newJsonString, String path) {
        try {
            FileWriter fw = new FileWriter(path);
            PrintWriter out = new PrintWriter(fw);
            out.write(newJsonString);
            out.println();
            fw.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    //保存校验数据
    public static boolean saveJYX(Activity context, JiaoYanData jiaoYanData) {
        List<SaleAttributeNameVo> bhData = jiaoYanData.getBhData();
        List<SaleAttributeNameVo> fzData = jiaoYanData.getFzData();
        List<SaleAttributeNameVo> itemData2 = new ArrayList<SaleAttributeNameVo>();//不区分分类
        List<SaleAttributeNameVo> itemData3 = new ArrayList<SaleAttributeNameVo>();//不区分分类,一级校验项
        List<SaleAttributeNameVo> itemData4 = new ArrayList<SaleAttributeNameVo>();//详情  保护
        List<SaleAttributeNameVo> itemData5 = new ArrayList<SaleAttributeNameVo>();//详情  辅助

        //按照分类保存校验项到sp
        JiaoYanData jiaoYanDatas = new JiaoYanData();
        for (int i = 0; i < bhData.size(); i++) {
            SaleAttributeNameVo saleName = new SaleAttributeNameVo();
            saleName.setName(bhData.get(i).getName()); //name,即属性名------------------------
            List<SaleAttributeVo> list66 = new ArrayList<SaleAttributeVo>();
            for (int j = 0; j < bhData.get(i).getSaleVo().size(); j++) {
                SaleAttributeVo vo = new SaleAttributeVo();
                if (bhData.get(i).getSaleVo().get(j).isChecked()) {
                    vo.setValue(bhData.get(i).getSaleVo().get(j).getValue());
                    vo.setLieName(bhData.get(i).getSaleVo().get(j).getLieName());
                    list66.add(vo);
                }
            }
            if (list66.size() > 0) {
                saleName.setSaleVo(list66);   //设置saleVo,即属性值------------------
                itemData4.add(saleName);     //将所有选中的属性和属性值放入itemData2
            }
        }
        jiaoYanDatas.setBhData(itemData4);

        for (int i = 0; i < fzData.size(); i++) {
            SaleAttributeNameVo saleName = new SaleAttributeNameVo();
            saleName.setName(fzData.get(i).getName()); //name,即属性名------------------------
            List<SaleAttributeVo> list77 = new ArrayList<SaleAttributeVo>();
            for (int j = 0; j < fzData.get(i).getSaleVo().size(); j++) {
                SaleAttributeVo vo = new SaleAttributeVo();
                if (fzData.get(i).getSaleVo().get(j).isChecked()) {
                    vo.setValue(fzData.get(i).getSaleVo().get(j).getValue());
                    vo.setLieName(fzData.get(i).getSaleVo().get(j).getLieName());
                    list77.add(vo);
                }
            }
            if (list77.size() > 0) {
                saleName.setSaleVo(list77);   //设置saleVo,即属性值------------------
                itemData5.add(saleName);     //将所有选中的属性和属性值放入itemData2
            }
        }
        jiaoYanDatas.setFzData(itemData5);
        Gson gson = new Gson();
        String s22 = gson.toJson(jiaoYanDatas);
        PreferenceUtils.setPrefString(context, "xqJY", s22); //保存详情所需的分类校验项

        //保护校验项保存
        SaleAttributeNameVo saleName = new SaleAttributeNameVo();
        SaleAttributeNameVo saleName2 = new SaleAttributeNameVo();
        saleName.setName("保护校验项");
        saleName2.setName("保护一级校验项");
        List<SaleAttributeVo> list6 = new ArrayList<SaleAttributeVo>();
        List<SaleAttributeVo> list8 = new ArrayList<SaleAttributeVo>();
        for (int i = 0; i < bhData.size(); i++) {
            for (int j = 0; j < bhData.get(i).getSaleVo().size(); j++) {
                SaleAttributeVo vo = new SaleAttributeVo();
                if (bhData.get(i).getSaleVo().get(j).isChecked()) {
                    if (bhData.get(i).getSaleVo().get(j).getValue().equals("设备识别代码")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("实物ID")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("装置名称")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("是否六统一")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("制造厂家")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("装置类别")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("装置型号")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("出厂日期")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("是否就地化设备")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("板卡数量")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("装置分类")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("装置类型")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("一次设备类型")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("一次设备名称")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("电压等级")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("单位名称")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("调度单位")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("所属屏柜")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("投运日期")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("设计单位")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("基建单位")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("运行单位")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("维护单位")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("设备属性")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("套别")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("设备状态")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("数据采集方式")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("定期检验周期")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("跳闸关系")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("名称属性")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("统计运行时间")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("上次检修时间")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("电源插件型号")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("电源插件更换日期")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("数字通道数")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("模拟通道数")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("出口方式")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("额定变比")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("实际变比")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("准确级")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("CT二次额定电流")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("资产编号")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("资产性质")
                            || bhData.get(i).getSaleVo().get(j).getValue().equals("资产单位")
                            ) {
                        vo.setValue(bhData.get(i).getSaleVo().get(j).getValue());
                        vo.setLieName(bhData.get(i).getSaleVo().get(j).getLieName());
                        list6.add(vo);
                        list8.add(vo);
                    } else {
                        vo.setValue(bhData.get(i).getSaleVo().get(j).getValue());
                        vo.setLieName(bhData.get(i).getSaleVo().get(j).getLieName());
                        list6.add(vo);
                    }
                }
            }
        }
        if (list6.size() > 0) {
            saleName.setSaleVo(list6);   //设置saleVo,即属性值------------------
            itemData2.add(saleName);     //将所有选中的属性和属性值放入itemData2
        }

        if (list8.size() > 0) {  //存取一级字段
            saleName2.setSaleVo(list8);
            itemData3.add(saleName2);
        }

        //辅助校验项保存
        SaleAttributeNameVo saleNameFz = new SaleAttributeNameVo();
        SaleAttributeNameVo saleNameFz2 = new SaleAttributeNameVo();
        saleNameFz.setName("辅助校验项");
        saleNameFz2.setName("辅助一级校验项");
        List<SaleAttributeVo> list7 = new ArrayList<SaleAttributeVo>();
        List<SaleAttributeVo> list9 = new ArrayList<SaleAttributeVo>();
        for (int i = 0; i < fzData.size(); i++) {
            for (int j = 0; j < fzData.get(i).getSaleVo().size(); j++) {
                SaleAttributeVo vo = new SaleAttributeVo();
                if (fzData.get(i).getSaleVo().get(j).isChecked()) {
                    if (fzData.get(i).getSaleVo().get(j).getValue().equals("设备识别代码")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("实物ID")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("装置名称")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("是否六统一")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("制造厂家")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("装置类别")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("装置型号")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("出厂日期")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("板卡数量")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("装置分类")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("装置类型")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("一次设备类型")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("一次设备名称")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("电压等级")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("单位名称")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("调度单位")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("投运日期")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("设计单位")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("基建单位")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("运行单位")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("维护单位")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("设备属性")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("套别")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("设备状态")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("定期检验周期")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("上次检修时间")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("资产编号")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("资产性质")
                            || fzData.get(i).getSaleVo().get(j).getValue().equals("资产单位")
                            ) {
                        vo.setValue(fzData.get(i).getSaleVo().get(j).getValue());
                        vo.setLieName(fzData.get(i).getSaleVo().get(j).getLieName());
                        list7.add(vo);
                        list9.add(vo);
                    } else {
                        if (fzData.get(i).getSaleVo().get(j).getValue().equals("发送光纤口数量")
                                && fzData.get(i).getName().equals("智能终端附加信息")) {
                            vo.setValue("发送光纤口数量智能");
                            vo.setLieName(fzData.get(i).getSaleVo().get(j).getLieName());
                            list7.add(vo);
                        } else if (fzData.get(i).getSaleVo().get(j).getValue().equals("接收光纤口数量")
                                && fzData.get(i).getName().equals("智能终端附加信息")) {
                            vo.setValue("接收光纤口数量智能");
                            vo.setLieName(fzData.get(i).getSaleVo().get(j).getLieName());
                            list7.add(vo);
                        } else if (fzData.get(i).getSaleVo().get(j).getValue().equals("智能终端功能")
                                && fzData.get(i).getName().equals("智能终端附加信息")) {
                            vo.setValue("智能终端功能智能");
                            vo.setLieName(fzData.get(i).getSaleVo().get(j).getLieName());
                            list7.add(vo);
                        } else if (fzData.get(i).getSaleVo().get(j).getValue().equals("光纤接口模式")
                                && fzData.get(i).getName().equals("交换机附加信息")) {
                            vo.setValue("光纤接口模式交换机");
                            vo.setLieName(fzData.get(i).getSaleVo().get(j).getLieName());
                            list7.add(vo);
                        } else if (fzData.get(i).getSaleVo().get(j).getValue().equals("光纤接口模式")
                                && fzData.get(i).getName().equals("智能终端附加信息")) {
                            vo.setValue("光纤接口模式智能");
                            vo.setLieName(fzData.get(i).getSaleVo().get(j).getLieName());
                            list7.add(vo);
                        } else {
                            vo.setValue(fzData.get(i).getSaleVo().get(j).getValue());
                            vo.setLieName(fzData.get(i).getSaleVo().get(j).getLieName());
                            list7.add(vo);
                        }
                    }
                }
            }
        }
        if (list7.size() > 0) {
            saleNameFz.setSaleVo(list7);   //设置saleVo,即属性值------------------
            itemData2.add(saleNameFz);     //将所有选中的属性和属性值放入itemData2
        }

        if (list9.size() > 0) {
            saleNameFz2.setSaleVo(list9);
            itemData3.add(saleNameFz2);
        }

        PreferenceUtils.setPrefDataList(context, "jyx", itemData2);
        PreferenceUtils.setPrefDataList(context, "jyxOne", itemData3);

        bhData.clear();
        fzData.clear();
        itemData2.clear();
        itemData3.clear();
        itemData4.clear();
        itemData5.clear();
        list6.clear();
        list7.clear();
        list8.clear();
        list9.clear();
        ToastUtils.showToast(context, "校验保存成功");
        return true;
    }

    /**
     * 判断字符串中是否包含中文
     *
     * @param str 待校验字符串
     * @return 是否为中文   不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        String reg = "[\u4e00-\u9fa5]";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 判断字符串中是否包含字母
     *
     * @param str 待校验字符串
     * @return 是否为字母
     */
    public static boolean isContainZM(String str) {
        String reg = ".*[a-zA-Z]+.*";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 判断当前登录用户是否注册
     *
     * @return
     */
    public static boolean isRegist() {
        boolean isRegist = false;

        File[] files = new File(Constants.APP_DEFAULT).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String extName = FileUtils.getExtensionName(pathname.getName());

                return extName.equalsIgnoreCase("key");
            }
        });
        if (files != null && files.length > 0) {
            isRegist = true;
        }
        return isRegist;
    }
}
