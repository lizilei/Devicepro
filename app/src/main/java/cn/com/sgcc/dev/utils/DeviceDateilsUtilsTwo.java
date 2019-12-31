package cn.com.sgcc.dev.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.BZSJ;
import cn.com.sgcc.dev.model2.DWLX;
import cn.com.sgcc.dev.model2.GXDW;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.view.fragment.DeviceOneFragment;

import static android.R.attr.name;

/**
 * <p>@description:</p>
 *设备详情查询匹配工具类2
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/9/2
 */

public class DeviceDateilsUtilsTwo {
    private static long lastClickTime;

    public static List<String> DeviceDateilsFind(final String name, final Context context) {
        List<String> item = new ArrayList<>();
        String result = "1";
        //查询数据库，匹配数据内容，返回给显示界面
        IDaoUtil util = new DaoUtil(context);


        if (name.equals("运行状态")) {
            List<BZSJ> bzsjList = util.getBZSJ("运行状态");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("设备属性")) {
            List<BZSJ> bzsjList = util.getBZSJ("设备属性");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("运行/维护单位")) {
            List<DWLX> dwlxList = util.getDWLX("运行/维护单位");
            dwlxList.toString();
            if (dwlxList.size() > 0) {
                for (int i = 0; i < dwlxList.size(); i++) {
                    String namedata = dwlxList.get(i).getDWMC() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("运行单位")) {
            List<DWLX> dwlxList = util.getDWLX("运行/维护单位");
            dwlxList.toString();
            if (dwlxList.size() > 0) {
                for (int i = 0; i < dwlxList.size(); i++) {
                    String namedata = dwlxList.get(i).getDWMC() + "";
                    item.add(namedata);
                }
            }
        }else if (name.equals("维护单位")) {
            List<DWLX> dwlxList = util.getDWLX("运行/维护单位");
            dwlxList.toString();
            if (dwlxList.size() > 0) {
                for (int i = 0; i < dwlxList.size(); i++) {
                    String namedata = dwlxList.get(i).getDWMC() + "";
                    item.add(namedata);
                }
            }
        }  else if (name.equals("设计单位")) {
            List<DWLX> dwlxList = util.getDWLX("设计单位");
            dwlxList.toString();
            if (dwlxList.size() > 0) {
                for (int i = 0; i < dwlxList.size(); i++) {
                    String namedata = dwlxList.get(i).getDWMC() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("基建单位")) {
            List<DWLX> dwlxList = util.getDWLX("基建单位");
            dwlxList.toString();
            if (dwlxList.size() > 0) {
                for (int i = 0; i < dwlxList.size(); i++) {
                    String namedata = dwlxList.get(i).getDWMC() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("资产单位")) {
            List<BZSJ> bzsjList = util.getBZSJ("资产单位");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }

            }
        } else if (name.equals("资产性质")) {
            List<BZSJ> bzsjList = util.getBZSJ("资产性质");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("数据采集方式")) {
            List<BZSJ> bzsjList = util.getBZSJ("数据采集方式");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("出口方式")) {
            List<BZSJ> bzsjList = util.getBZSJ("出口方式");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("准确级")) {
            List<BZSJ> bzsjList = util.getBZSJ("准确级");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("直流额定电压")) {
            List<BZSJ> bzsjList = util.getBZSJ("直流额定电压");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("CT二次额定电流")) {
            List<BZSJ> bzsjList = util.getBZSJ("CT二次额定电流");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        }

        //查重
        for (int i = 0; i < item.size(); i++) {
            //验空
            for (int j = item.size() - 1; j > i; j--) {
                if (item.get(j) == null || item.get(j).equals("")|| item.get(j).equals(null) || item.get(i).equals("null")) {
                    item.remove(j);
                }else if (item.get(j).equals(item.get(i))) {
                    item.remove(j);
                }
            }
            if (item.get(i) == null || item.get(i).equals("")|| item.get(i).equals(null) || item.get(i).equals("null")) {
                item.remove(i);
            }
        }
        //去除重复
        for (int i = 0; i < item.size(); i++) {
            for (int j = item.size() - 1; j > i; j--) {
                if (item.get(j).equals(item.get(i))) {
                    item.remove(j);
                }
            }
        }

        //必填项不加空值
        if (item.size() > 0){
            if (name.equals("运行状态")) {
            }else{
                item.add(0, "");
            }
        }
        return item;
    }
}