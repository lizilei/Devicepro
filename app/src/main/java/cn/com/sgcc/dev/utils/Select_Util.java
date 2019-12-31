package cn.com.sgcc.dev.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.BHLBXH;
import cn.com.sgcc.dev.model2.BHSBXHB;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.model2.BZSJ;
import cn.com.sgcc.dev.model2.GXDW;
import cn.com.sgcc.dev.model2.LTYSBXH;
import cn.com.sgcc.dev.model2.ycsb.BYQCS;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.model2.ycsb.DDJCS;
import cn.com.sgcc.dev.model2.ycsb.DKQCS;
import cn.com.sgcc.dev.model2.ycsb.DLQCS;
import cn.com.sgcc.dev.model2.ycsb.DRQCS;
import cn.com.sgcc.dev.model2.ycsb.FDJCS;
import cn.com.sgcc.dev.model2.ycsb.MXCS;
import cn.com.sgcc.dev.model2.ycsb.XLCS;
import cn.com.sgcc.dev.view.fragment.DeviceOneFragment;

/**
 * <p>@description:</p>
 * 设备详情查询匹配工具类
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/9/2
 */

public class Select_Util {
    private static long lastClickTime;

    //搜索内容查询工具类
    public static List<String> DoSelect(final String name, List<String> Select_data, final Context context) {
        List<String> item = new ArrayList<>();

        String inputall = name;
        String inputs = "";
        String inputtwo = "";
        String inputthree = "";
        String inputfour = "";

        if (name.length() == 1) {
            inputs = setZmu(name);
        }
        else if (name.length() == 2){
            inputtwo = setZmutwo(name);
        }
        else if (name.length() >= 3){
            inputthree = setZmuthree(name);
        }else {
//            inputfour = setZmuthree(name);
        }

        for (int i = 0; i < Select_data.size(); i++) {
           if (inputall.length() == 1) {
                if (setZmu(Select_data.get(i)).equalsIgnoreCase(inputs)) {
                    item.add(i+"");
                }
            }
            else if (inputall.length() == 2) {
                if (setZmutwo(Select_data.get(i)).equalsIgnoreCase(inputtwo)) {
                    item.add(i+"");
                }
            }
            else if (inputall.length() >= 3) {
                if (setZmuthree(Select_data.get(i)).equalsIgnoreCase(inputthree)) {
                    item.add(i+"");
                }
            }else {
//               if (setZmutfour(Select_data.get(i)).equalsIgnoreCase(inputfour)) {
//                   item.add(i+"");
//               }
           }
        }
        if (item.size()==0){
            item.add("0");
        }

        return item;
    }

    public static String setZmu(String name) {
        String zimu;

        // 正则表达式，判断首字母是否是英文字母
        if ("".equals(name) || name == null) {
            zimu = "#";
            return zimu;
        } else {
//                String pinyin = converterToPinYin(name);
            String pinyin = PingYinUtil.getPingYin(name);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                zimu = sortString.toUpperCase();
            } else {
                zimu = "#";
            }
        }
        return zimu;
    }

    public static String setZmutwo(String name) {
        String zimu = "";

        // 正则表达式，判断首字母是否是英文字母
        if ("".equals(name) || name == null) {
            zimu = "#";
            return zimu;
        } else if (name.matches("^[A-Za-z]+$")) {
            if (name.length() == 2) {
                zimu = name.substring(0, 2);
            } else {
                zimu = name;
            }
            return zimu;
        } else {
            String pin = "";
            if (name.length() == 2) {
                pin = name.substring(1, 2);

                String pinyin = PingYinUtil.getPingYin(name);
                String sortString = pinyin.substring(0, 1).toUpperCase();

                String pinyintwo = PingYinUtil.getPingYin(pin);
                String sortStringtwo = pinyintwo.substring(0, 1).toUpperCase();

                if (sortString.matches("[A-Z]") && sortStringtwo.matches("[A-Z]")) {
                    zimu = sortString.toUpperCase() + sortStringtwo.toUpperCase();
                } else {
                    zimu = "#";
                }
            }
        }
        return zimu;
    }

    public static String setZmuthree(String name) {
        String zimu = "";

        // 正则表达式，判断首字母是否是英文字母
        if ("".equals(name) || name == null) {
            zimu = "#";
            return zimu;
        } else if (name.matches("^[A-Za-z]+$")) {
            if (name.length() == 3) {
                zimu = name.substring(0, 3);
            } else {
                zimu = name;
            }
            return zimu;
        } else {
            String pin = "";
            String pins = "";
            if (name.length() == 3) {
                pin = name.substring(1, 2);
                pins = name.substring(2, 3);

                String pinyin = PingYinUtil.getPingYin(name);
                String sortString = pinyin.substring(0, 1).toUpperCase();

                String pinyintwo = PingYinUtil.getPingYin(pin);
                String sortStringtwo = pinyintwo.substring(0, 1).toUpperCase();

                String pinyinthree = PingYinUtil.getPingYin(pins);
                String sortpinyinthree = pinyinthree.substring(0, 1).toUpperCase();

                if (sortString.matches("[A-Z]") && sortStringtwo.matches("[A-Z]")) {
                    zimu = sortString.toUpperCase() + sortStringtwo.toUpperCase()+sortpinyinthree.toUpperCase();
                } else {
                    zimu = "#";
                }
            }
        }
        return zimu;
    }
    public static String setZmutfour(String name) {
        String zimu = "";

        // 正则表达式，判断首字母是否是英文字母
        if ("".equals(name) || name == null) {
            zimu = "#";
            return zimu;
        } else if (name.matches("^[A-Za-z]+$")) {
            if (name.length() >= 4) {
                zimu = name.substring(0, 4);
            } else {
                zimu = name;
            }
            return zimu;
        } else {
            String pin = "";
            String pins = "";
            String pinss = "";
            if (name.length() >= 4) {
                pin = name.substring(1, 2);
                pins = name.substring(2, 3);
                pinss = name.substring(3, 4);

                String pinyin = PingYinUtil.getPingYin(name);
                String sortString = pinyin.substring(0, 1).toUpperCase();

                String pinyintwo = PingYinUtil.getPingYin(pin);
                String sortStringtwo = pinyintwo.substring(0, 1).toUpperCase();

                String pinyinthree = PingYinUtil.getPingYin(pins);
                String sortpinyinthree = pinyinthree.substring(0, 1).toUpperCase();

                String pinyintfour = PingYinUtil.getPingYin(pins);
                String sortpinyintfour = pinyintfour.substring(0, 1).toUpperCase();

                if (sortString.matches("[A-Z]") && sortStringtwo.matches("[A-Z]")) {
                    zimu = sortString.toUpperCase() +
                            sortStringtwo.toUpperCase()+
                            sortpinyinthree.toUpperCase()+
                            sortpinyintfour.toUpperCase()
                    ;
                } else {
                    zimu = "#";
                }
            }
        }
        return zimu;
    }
}