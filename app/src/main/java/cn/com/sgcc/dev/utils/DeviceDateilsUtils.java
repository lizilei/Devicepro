package cn.com.sgcc.dev.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.AKXT;
import cn.com.sgcc.dev.model2.BHLBXH;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.BHSBXHB;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.model2.BZSJ;
import cn.com.sgcc.dev.model2.DWLX;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.GXDW;
import cn.com.sgcc.dev.model2.LTYSBXH;
import cn.com.sgcc.dev.model2.ZZCJ;
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
import cn.com.sgcc.dev.view.fragment.FZDeviceOneFragment;

/**
 * <p>@description:</p>
 * 设备详情查询匹配工具类
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/9/2
 */

public class DeviceDateilsUtils {
    private static long lastClickTime;
    private static BHSBXHB bhsbxhb;

    public static List<String> DeviceDateilsFind(final String name, final Context context) {
        List<String> item = new ArrayList<>();
        String result = "1";
        //查询数据库，匹配数据内容，返回给显示界面
        IDaoUtil util = new DaoUtil(context);

        if (name.equals("是否六统一设备")) {
            item.add("是");
            item.add("否");
        } else if (name.equals("六统一标准版本")) {
            List<BZSJ> bzsjList = util.getBZSJ("六统一标准版本");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("单位名称")) {
            CZCS czcs = util.getCZCSByGLDW();
            item.add(czcs.getGldw());
        } else if (name.equals("调度单位")) {
//            if (!DeviceOneFragment.instance.company_name().equals("")) {
            List<GXDW> bzsjList = util.getDDDWByDWMC(null);
//                for (GXDW gxdw : bzsjList) {
//                    item.add(gxdw.getDDDW());
//                }
//            }
            if (bzsjList != null && bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    for (int j = bzsjList.size() - 1; j > i; j--) {
                        if (bzsjList.get(j).getDDDW().equals(bzsjList.get(i).getDDDW())) {
                            bzsjList.remove(j);
                        }
                    }
                }
                for (GXDW gxdw : bzsjList) {
                    if (gxdw.getDDDW() != null && !gxdw.getDDDW().equals("")) {
                        item.add(gxdw.getDDDW());
                    }
                }
            }
        } else if (name.equals("厂站名称")) {
            CZCS czcs = util.getCZCSByGLDW();
            item.add(czcs.getCzmc());
        } else if (name.equals("一次设备类型")) {
//            List<BZSJ> getBZSJ(String bzsjflmc);
            List<BZSJ> bzsjList = util.getBZSJ("一次设备类型");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("一次设备名称")) {
            String type = DeviceOneFragment.instance.one_device_type();
            String company_name = DeviceOneFragment.instance.company_name();
            String control_company_name = DeviceOneFragment.instance.control_company_name();
            if (company_name.equals("") || control_company_name.equals("") || type.equals("")) {

            } else {
//                List<String> data = new ArrayList<>();
                String czmc = DeviceOneFragment.instance.control_company_name() + "";
                String gldw = DeviceOneFragment.instance.company_name() + "";
                List<Object> list;
                switch (type) {
                    case "线路":
                        list = util.getYCSBMC(XLCS.class, czmc, gldw);
                        for (Object o : list) {
                            item.add(((XLCS) o).getXLMC());
                        }
                        break;
                    case "电抗器":
                        list = util.getYCSBMC(DKQCS.class, czmc, gldw);
                        for (Object o : list) {
                            item.add(((DKQCS) o).getDkqmc());
                        }
                        break;
                    case "电容器":
                        list = util.getYCSBMC(DRQCS.class, czmc, gldw);
                        for (Object o : list) {
                            item.add(((DRQCS) o).getDRQMC());
                        }
                        break;
                    case "电动机":
                        list = util.getYCSBMC(DDJCS.class, czmc, gldw);
                        for (Object o : list) {
                            item.add(((DDJCS) o).getDDJMC());
                        }
                        break;
                    case "母线":
                        list = util.getYCSBMC(MXCS.class, czmc, gldw);
                        for (Object o : list) {
                            item.add(((MXCS) o).getMXMC());
                        }
                        break;
                    case "断路器":
                        list = util.getYCSBMC(DLQCS.class, czmc, gldw);
                        for (Object o : list) {
                            item.add(((DLQCS) o).getDLQMC());
                        }
                        break;
                    case "变压器":
                        list = util.getYCSBMC(BYQCS.class, czmc, gldw);
                        for (Object o : list) {
                            item.add(((BYQCS) o).getBYQMC());
                        }
                        break;
                    case "发电机":
                        list = util.getYCSBMC(FDJCS.class, czmc, gldw);
                        for (Object o : list) {
                            item.add(((FDJCS) o).getFDJMC());
                        }
                        break;
                    case "其他":
                        item.add("其他");
                        break;
                }
                if (true) {
                    List<Object> bhpzList = util.getYCSBMCFromBHOrFZ(BHPZ.class, gldw, czmc, type);
                    for (Object o : bhpzList) {
                        if (((BHPZ) o).getYcsbmc().contains(",")) {
                            String[] ycsbmc = ((BHPZ) o).getYcsbmc().split(",");
                            for (String s : ycsbmc) {
                                item.add(s);
                            }
                        } else {
                            item.add(((BHPZ) o).getYcsbmc());
                        }
                    }
                } else {
                    List<Object> fzbhsbList = util.getYCSBMCFromBHOrFZ(FZBHSB.class, gldw, czmc, type);
                    for (Object o : fzbhsbList) {
                        item.add(((FZBHSB) o).getYcsbmc());
                    }
                }
                //去除重复的一次设备名称
                for (int i = 0; i < item.size(); i++) {
                    for (int j = item.size() - 1; j > i; j--) {
                        if (item.get(j).equals(item.get(i))) {
                            item.remove(j);
                        }
                    }
                }
            }
        } else if (name.equals("电压等级")) {
            List<BZSJ> bzsjList = util.getBZSJ("电压等级");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name.equals("保护类别")) {
            List<BZSJ> bzsjList = util.getBZSJ("主保护类别");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("保护类别细化") || name.equals("设备类型")) {
//            List<BHLBXH> getBHLBXH(String bhlb);
            String protect_type = DeviceOneFragment.instance.protect_type() + "";
            if (protect_type.equals("") && protect_type.equals("null")) {

            } else {
                List<BHLBXH> bzsjList = util.getBHLBXH(protect_type + "");
                if (bzsjList.size() > 0) {
                    for (int i = 0; i < bzsjList.size(); i++) {
                        String namedata = bzsjList.get(i).getBhlbxh() + "";
                        item.add(namedata);
                    }
                }
            }
        } else if (name.equals("制造厂家")) {
            List<ZZCJ> bzsjList = util.getZZCJ(null);
            for (ZZCJ zzcj : bzsjList) {
                item.add(zzcj.getMC());
            }
        }
//        参照文件，保护类型 保护分类
        else if (name.equals("保护型号")) {
            boolean six_one = DeviceOneFragment.instance.six_one();
            boolean is2013 = DeviceOneFragment.instance.six_one_details.equals("2013版");
            String protect_type = DeviceOneFragment.instance.protect_type();
            String made_company = DeviceOneFragment.instance.made_company();
            DeviceOneFragment.instance.model_data_type.clear();
            String dydj = DeviceOneFragment.instance.electric_level + "";
            if (protect_type.equals("") || made_company.equals("") || dydj.equals("") || dydj.equals("null")) {
            } else {
                String zzcj = DeviceOneFragment.instance.made_company() + "";
                String bhlb = DeviceOneFragment.instance.protect_type() + "";
                String czlx = DeviceOneFragment.instance.company_type + "";
                String isgd = DeviceOneFragment.instance.isGD + "";
                boolean isSIX = DeviceOneFragment.instance.six_one();
                boolean is_2013 = DeviceOneFragment.instance.six_one_details.equals("2013版");
                List<Object> list = util.getBHXH(isSIX, is2013, zzcj, bhlb, czlx, dydj, isgd);
                if (isSIX && is_2013) {
//                    if (DeviceOneFragment.instance.model_data_type_code.size() > 0) {
//                        DeviceOneFragment.instance.model_data_type_code.clear();
//                    }
                    if (DeviceOneFragment.instance.model_data_type_code_xh.size() > 0) {
                        DeviceOneFragment.instance.model_data_type_code_xh.clear();
                    }
                    for (Object o : list) {
                        item.add(((LTYSBXH) o).getBhxh());
                        String model_data_type_code = ((LTYSBXH) o).getCode();
//                        DeviceOneFragment.instance.model_data_type_code.add(model_data_type_code);
                        DeviceOneFragment.instance.model_data_type_code_xh.add(model_data_type_code);
                    }
                } else {
//                    if (DeviceOneFragment.instance.model_data_type_code.size() > 0) {
//                        DeviceOneFragment.instance.model_data_type_code.clear();
//                    }
                    if (DeviceOneFragment.instance.model_data_type_code_xh.size() > 0) {
                        DeviceOneFragment.instance.model_data_type_code_xh.clear();
                    }
                    if (DeviceOneFragment.instance.model_data_type.size() > 0) {
                        DeviceOneFragment.instance.model_data_type.clear();
                    }
                    for (Object o : list) {
                        String molde_type = ((BHSBXHB) o).getBblx() + "";
                        String model_data_type_code = ((BHSBXHB) o).getCode() + "";
                        DeviceOneFragment.instance.model_data_type.add(molde_type);
//                        DeviceOneFragment.instance.model_data_type_code.add(model_data_type_code);
                        DeviceOneFragment.instance.model_data_type_code_xh.add(model_data_type_code);
                        item.add(((BHSBXHB) o).getSbxh());
                    }
                }
            }
        } else if (name.equals("保护分类")) {
            String protect_type = DeviceOneFragment.instance.protect_type();
            String made_company = DeviceOneFragment.instance.made_company();
            String protect_type_m = DeviceOneFragment.instance.protect_type_m();
            boolean isSIX = DeviceOneFragment.instance.six_one();
            boolean is_2013 = DeviceOneFragment.instance.six_one_details.equals("2013版");
            //默认保护型号
            if (protect_type.equals("") || made_company.equals("") || protect_type_m.equals("")) {

            } else {
                if (isSIX && is_2013) {
                    List<LTYSBXH> list = util.getLTYSBXHBHFLOrBHLX("BHFL", made_company, protect_type, protect_type_m);
                    if (list != null && list.size() > 0) {
                        for (LTYSBXH ltysbxh : list) {
                            item.add(ltysbxh.getBhfl());
                        }
                    }
                } else {
                    List<BHSBXHB> list = util.getBHFLOrBHLX("BHFL", made_company, protect_type, protect_type_m);
                    if (list != null && list.size() > 0) {
                        for (BHSBXHB bhsbxhb : list) {
                            item.add(bhsbxhb.getBhfl());
                        }
                    }
                }

            }
        } else if (name.equals("保护类型")) {
            String protect_type = DeviceOneFragment.instance.protect_type();
            String made_company = DeviceOneFragment.instance.made_company();
            String protect_type_m = DeviceOneFragment.instance.protect_type_m();
            boolean isSIX = DeviceOneFragment.instance.six_one();
            boolean is_2013 = DeviceOneFragment.instance.six_one_details.equals("2013版");
            if (protect_type.equals("") || made_company.equals("") || protect_type_m.equals("")) {

            } else {
                if (isSIX && is_2013) {
                    List<LTYSBXH> list = util.getLTYSBXHBHFLOrBHLX("BHFL", made_company, protect_type, protect_type_m);
                    if (list != null && list.size() > 0) {
                        for (LTYSBXH ltysbxh : list) {
                            item.add(ltysbxh.getBhlx());
                        }
                    }
                } else {
                    List<BHSBXHB> list = util.getBHFLOrBHLX("BHFL", made_company, protect_type, protect_type_m);
                    if (list != null && list.size() > 0) {
                        for (BHSBXHB bhsbxhb : list) {
                            item.add(bhsbxhb.getBhlx());
                        }
                    }
                }
            }
        } else if (name.equals("软件版本")) {
            /**
             * 选择型号、保护类别、保护类型后带出软件版本
             * 参数传值顺序 ：ZZCJ、SBXH、BHLB、BHLX
             */
            boolean isSIX = DeviceOneFragment.instance.six_one();
            boolean is2013 = DeviceOneFragment.instance.six_one_details.equals("2013版");
            List<Object> list;

            if (!DeviceOneFragment.instance.model_data_type_get_code.equals("") &&
                    !DeviceOneFragment.instance.model_data_type_get_code.equals("null")
                    ) {
                if (is2013 && isSIX) {

                    list = util.getFZBHSBXHRJBB(isSIX, is2013, DeviceOneFragment.instance.protect_type_m(),
                            "",DeviceOneFragment.instance.select_work);
                } else {
                    String model = DeviceOneFragment.instance.model_one;
                    if (DeviceOneFragment.instance.ismodel_type) {
                        list = util.getFZBHSBXHRJBB(isSIX, is2013, DeviceOneFragment.instance.model_data_type_get_code, model + "");
                    } else {
                        list = util.getFZBHSBXHRJBB(isSIX, is2013, DeviceOneFragment.instance.model_data_type_get_code);
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    if (is2013 && isSIX) {
                        //六统一2013版取值唯一
                        for (Object o : list) {
                            if (!((LTYSBXH) o).getRjbb().equals("")) {
                                item.add(((LTYSBXH) o).getRjbb() + "");
                            }
                        }
                    } else {
                        BHXHRJBB rjbb = (BHXHRJBB) list.get(i);
//                    多组获取未处理
                        String bb = rjbb.getBb() + "";
                        String jym = rjbb.getJym() + "";
                        if (bb.equals("null")) {
                            bb = "无";
                        }
                        if (jym.equals("null")) {
                            jym = "无";
                        }
                        if (bb.equals("null") && jym.equals("null")) {
                            item.add("版本：无，校验码：无");
                        } else {
                            if (bb.equals("无") && jym.equals("无")) {
                                item.add("版本：无，校验码：无");
                            } else if (bb.equals("无") && !jym.equals("无")) {
                                item.add("版本：无，校验码：" + rjbb.getJym());
                            } else if (!bb.equals("无") && jym.equals("无")) {
                                item.add("版本：" + rjbb.getBb() + "，校验码：无");
                            } else if (!bb.equals("无") && !jym.equals("无")) {
                                item.add("版本：" + rjbb.getBb() + "，校验码：" + rjbb.getJym());
                            }
                        }
                    }
                }
            }
        } else if (name.equals("保护套别")) {
            List<BZSJ> bzsjList = util.getBZSJ("保护套别");
            if (bzsjList != null && bzsjList.size() > 0) {
                for (BZSJ bzsj : bzsjList) {
                    item.add(bzsj.getBzsjSxmc());
                }
            }
        } else if (name.equals("跳闸关系")) {

            String one_device_type = DeviceOneFragment.instance.one_device_type() + "";
            String company_name = DeviceOneFragment.instance.company_name;
            String electric_level = DeviceOneFragment.instance.electric_level;
            String control_company_name = DeviceOneFragment.instance.control_company_name();
            List<Object> list = new ArrayList<>();
//          跳闸关系只从断路器表中取值，变压器不带电压等级
            if (one_device_type.equals("变压器")) {
                list = util.getTZGX(BYQCS.class, control_company_name + "", company_name + "");
                for (Object o : list) {
                    item.add(((DLQCS) o).getKGBH());
                }
            } else {
                list = util.getTZGX(DLQCS.class, control_company_name + "", electric_level, company_name + "");
                for (Object o : list) {
                    item.add(((DLQCS) o).getKGBH());
                }
            }
        } else if (name.equals("保护套别")) {
            List<BZSJ> bzsjList = util.getBZSJ("辅助保护套别");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("名称属性")) {
            List<BZSJ> bzsjList = util.getBZSJ("名称属性");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("保护名称")) {
            result = "20";
        } else if (name.equals("通道装置型号")) {
            //手动输入
        } else if (name.equals("是否复用")) {

            item.add("是");
            item.add("否");
//            item.add("雪拥蓝关马不前");
        } else if (name.equals("通道类型")) {
            List<BZSJ> bzsjList = util.getBZSJ("通道类型");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("安控系统调度名")) {
            List<AKXT> zkxtList = util.getAKXTALL("");
            zkxtList.toString();
            DeviceOneFragment.instance.akxt_id_list.clear();
            for (AKXT akxt : zkxtList) {
                item.add(akxt.getAkxtm() + "");
                DeviceOneFragment.instance.akxt_id_list.add(akxt.getId() + "");
            }

        } else if (name.equals("安控站点类型")) {
            List<BZSJ> bzsjList = util.getBZSJ("安控站点类型");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("测距形式")) {
            List<BZSJ> bzsjList = util.getBZSJ("测距形式");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("故障录波器类型")) {
            List<BZSJ> bzsjList = util.getBZSJ("故障录波器类型");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("设备功能配置")) {
            List<BZSJ> bzsjList = util.getBZSJ("设备功能配置");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("选配功能")) {

            /**
             * 根据制造厂家和保护型号带出选配功能、ICD文件名、软件版本
             * 传值顺序：ZZCJ、BHXH
             */
            List<Object> list;
            if (!DeviceOneFragment.instance.model_data_type_get_code.equals("")) {
                list = util.getFZBHSBXHRJBB(true, true, DeviceOneFragment.instance.protect_type_m(),"","");
                for (int i = 0; i < list.size(); i++) {
                    if (true && true) {
                        //六统一2013版取值唯一
                        for (Object o : list) {
                            if (((LTYSBXH) o).getXp() != null && !((LTYSBXH) o).getXp().equals("")) {
                                item.add(((LTYSBXH) o).getXp() + "");
                            }
                        }
                    }
                }
            }
        } else if (name.equals("ICD文件名")) {
            /**
             * 根据制造厂家和保护型号带出选配功能、ICD文件名、软件版本
             * 传值顺序：ZZCJ、BHXH
             */
            List<Object> list;
            if (!DeviceOneFragment.instance.model_data_type_get_code.equals("")) {
                list = util.getFZBHSBXHRJBB(true, true, DeviceOneFragment.instance.protect_type_m(),
                        DeviceOneFragment.instance.ver_info,DeviceOneFragment.instance.select_work);
//                list = util.getFZBHSBXHRJBB(true, true, DeviceOneFragment.instance.model_data_type_get_code);
                for (int i = 0; i < list.size(); i++) {
                    if (true && true) {
                        //六统一2013版取值唯一
                        for (Object o : list) {
                            if (((LTYSBXH) o).getWjmc() != null && !((LTYSBXH) o).getWjmc().equals("")) {
                                item.add(((LTYSBXH) o).getWjmc() + "");
                            }
                        }
                    }
                }
            }

        } else if (name.equals("是否接入调度主站")) {
            item.add("是");
            item.add("否");
        } else if (name.equals("模块名称")) {
            boolean isSIX = DeviceOneFragment.instance.six_one();
            boolean is2013 = DeviceOneFragment.instance.six_one_details.equals("2013版");
            List<Object> list;
            if (!DeviceOneFragment.instance.model_data_type_get_code.equals("")) {
                list = util.getFZBHSBXHRJBB(isSIX, is2013, DeviceOneFragment.instance.model_data_type_get_code + "");
                for (Object o : list) {
                    if (isSIX && is2013) {

                    } else {
                        BHXHRJBB bhxhrjbb = (BHXHRJBB) o;
                        if (bhxhrjbb.getMkmc() == null || bhxhrjbb.getMkmc().equals("")) {
                            item.add("");
                        } else {
                            item.add(bhxhrjbb.getMkmc());
                        }
                    }
                }
            }
        } else if (name.equals("是否就地化装置")) {
            item.add("是");
            item.add("否");
        } else if (name.equals("是否使用国调标准型号")) {
            String czlx = DeviceOneFragment.instance.company_type + "";
            int dydj = 0;
            if (!czlx.equals("") && !czlx.equals("null") && DeviceOneFragment.instance.electric_level != null &&
                    !DeviceOneFragment.instance.electric_level.equals("") &&
                    !DeviceOneFragment.instance.electric_level.equals("null")) {
                dydj = Integer.parseInt(DeviceOneFragment.instance.electric_level);
            }
            if (dydj >= 220 || czlx.equals("智能站") && util.getCZCSByGLDW().getCzzgdydj() >= 110) {
                item.add("是");
            } else {
                item.add("是");
                item.add("否");
            }
        }
        //查重
        for (int i = 0; i < item.size(); i++) {
            for (int j = item.size() - 1; j > i; j--) {
                if (item.get(j).equals(item.get(i))) {
                    item.remove(j);
                }
            }
            //验空
            if (item.get(i) == null || item.get(i).equals("") || item.get(i).equals("null")) {
                item.remove(i);
            }
        }
        return item;
    }

    public static List<String> getFZDateilsFind(final Context context, final String... name) {
        List<String> item = new ArrayList<>();
        IDaoUtil util = new DaoUtil(context);
        if (name[0].equals("是否六统一设备") ||
                name[0].equals("是否支持RSTP环网") ||
                name[0].equals("是否支持1588对时") ||
                name[0].equals("是否支持动态组播管理") ||
                name[0].equals("是否支持SNMP网络管理") ||
                name[0].equals("是否支持采用IEC61850上送交换机信息")) {
            item.add("是");
            item.add("否");
        } else if (name[0].equals("是否使用国调标准型号")) {
            String czlx = FZDeviceOneFragment.instance.czlx;
            int dydj = 0;
            if (FZDeviceOneFragment.instance.dydj != null &&
                    !FZDeviceOneFragment.instance.dydj.equals("") &&
                    !FZDeviceOneFragment.instance.dydj.equals("必填项")) {
                dydj = Integer.parseInt(FZDeviceOneFragment.instance.dydj);
            }
            if (dydj >= 220 || czlx.equals("智能站") && util.getCZCSByGLDW().getCzzgdydj() >= 110) {
                item.add("是");
            } else {
                item.add("是");
                item.add("否");
            }
        } else if (name[0].equals("保护类别")) {
            List<BZSJ> bzsjList = util.getBZSJ("辅助保护类别");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("六统一标准版本")) {
            List<BZSJ> bzsjList = util.getBZSJ("六统一标准版本");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("单位名称")) {
            CZCS czcs = util.getCZCSByGLDW();
            item.add(czcs.getGldw());
        } else if (name[0].equals("调度单位")) {
            List<GXDW> bzsjList = util.getDDDWByDWMC(null);
            if (bzsjList != null && bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    for (int j = bzsjList.size() - 1; j > i; j--) {
                        if (bzsjList.get(j).getDDDW().equals(bzsjList.get(i).getDDDW())) {
                            bzsjList.remove(j);
                        }
                    }
                }
                for (GXDW gxdw : bzsjList) {
                    if (gxdw.getDDDW() != null && !gxdw.getDDDW().equals("")) {
                        item.add(gxdw.getDDDW());
                    }
                }
            }
        } else if (name[0].equals("厂站名称")) {
            CZCS czcs = util.getCZCSByGLDW();
            item.add(czcs.getCzmc());
        } else if (name[0].equals("一次设备类型")) {
            List<BZSJ> bzsjList = util.getBZSJ("一次设备类型");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("一次设备名称")) {
            String type = FZDeviceOneFragment.instance.ycsblx + "";
            item.addAll(getYCSBMC(util, type, false));
        } else if (name[0].equals("电压等级")) {
            List<BZSJ> bzsjList = util.getBZSJ("电压等级");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("制造厂家")) {
            List<ZZCJ> bzsjList = util.getZZCJ(null);
            for (ZZCJ zzcj : bzsjList) {
                item.add(zzcj.getMC());
            }
        } else if (name[0].equals("保护套别")) {
            List<BZSJ> bzsjList = util.getBZSJ("保护套别");
            if (bzsjList != null && bzsjList.size() > 0) {
                for (BZSJ bzsj : bzsjList) {
                    item.add(bzsj.getBzsjSxmc());
                }
            }
        } else if (name[0].equals("设备型号")) {
            String zzcj = FZDeviceOneFragment.instance.zzcj + "";
            String bhlb = FZDeviceOneFragment.instance.bhlb + "";
            boolean isSIX = FZDeviceOneFragment.instance.isSix;
            boolean is2013 = FZDeviceOneFragment.instance.is2013;
            String czlx = FZDeviceOneFragment.instance.czlx;
            String dydj = FZDeviceOneFragment.instance.dydj;
            String usegddate = FZDeviceOneFragment.instance.usegddata;

            List<Object> list = util.getBHXH(isSIX, is2013, zzcj, bhlb, czlx, dydj, usegddate);
            if (list != null && list.size() > 0) {
                FZDeviceOneFragment.instance.xhBblx = new String[list.size()];
                FZDeviceOneFragment.instance.xhCode = new String[list.size()];
            }
            if (isSIX && is2013) {
                for (int i = 0; i < list.size(); i++) {
                    item.add(((LTYSBXH) list.get(i)).getBhxh());
                    FZDeviceOneFragment.instance.xhBblx[i] = ((LTYSBXH) list.get(i)).getBblx();
                    FZDeviceOneFragment.instance.xhCode[i] = ((LTYSBXH) list.get(i)).getCode();
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    item.add(((BHSBXHB) list.get(i)).getSbxh());
                    FZDeviceOneFragment.instance.xhBblx[i] = ((BHSBXHB) list.get(i)).getBblx();
                    FZDeviceOneFragment.instance.xhCode[i] = ((BHSBXHB) list.get(i)).getCode();
                }
            }
            for (int i = 0; i < item.size(); i++) {
                for (int j = item.size() - 1; j > i; j--) {
                    if (item.get(j).equals(item.get(i))) {
                        item.remove(j);
                    }
                }
            }
        } else if (name[0].equals("保护分类")) {
            String zzcj = FZDeviceOneFragment.instance.zzcj + "";
            String bhlb = FZDeviceOneFragment.instance.bhlb + "";
            String sbxh = FZDeviceOneFragment.instance.bhxh + "";
            if (FZDeviceOneFragment.instance.isSix &&
                    FZDeviceOneFragment.instance.is2013) {
                List<LTYSBXH> list = util.getLTYSBXHBHFLOrBHLX("BHFL", zzcj, bhlb, sbxh);
                if (list != null && list.size() > 0) {
                    for (LTYSBXH ltysbxh : list) {
                        if (ltysbxh.getBhfl() != null && !ltysbxh.getBhfl().equals("")) {
                            item.add(ltysbxh.getBhfl());
                        }
                    }
                }
            } else {
                List<BHSBXHB> list = util.getBHFLOrBHLX("BHFL", zzcj, bhlb, sbxh);
                if (list != null && list.size() > 0) {
                    for (BHSBXHB bhsbxhb : list) {
                        if (bhsbxhb.getBhfl() != null && !bhsbxhb.getBhfl().equals("")) {
                            item.add(bhsbxhb.getBhfl());
                        }
                    }
                }
            }
            if (item.size() > 0) {
                for (int i = 0; i < item.size(); i++) {
                    for (int j = item.size() - 1; j > i; j--) {
                        if (item.get(j).equals(item.get(i))) {
                            item.remove(j);
                        }
                    }
                }
            }
        } else if (name[0].equals("保护类型")) {
            String zzcj = FZDeviceOneFragment.instance.zzcj + "";
            String bhlb = FZDeviceOneFragment.instance.bhlb + "";
            String sbxh = FZDeviceOneFragment.instance.bhxh + "";
            if (FZDeviceOneFragment.instance.isSix &&
                    FZDeviceOneFragment.instance.is2013) {
                List<LTYSBXH> list = util.getLTYSBXHBHFLOrBHLX("BHLX", zzcj, bhlb, sbxh);
                if (list != null && list.size() > 0) {
                    for (LTYSBXH ltysbxh : list) {
                        if (ltysbxh.getBhlx() != null && !ltysbxh.getBhlx().equals("")) {
                            item.add(ltysbxh.getBhlx());
                        }
                    }
                }
            } else {
                List<BHSBXHB> list = util.getBHFLOrBHLX("BHLX", zzcj, bhlb, sbxh);
                if (list != null && list.size() > 0) {
                    for (BHSBXHB bhsbxhb : list) {
                        if (bhsbxhb.getBhlx() != null && !bhsbxhb.getBhlx().equals("")) {
                            item.add(bhsbxhb.getBhlx());
                        }
                    }
                }
            }
            if (item.size() > 0) {
                for (int i = 0; i < item.size(); i++) {
                    for (int j = item.size() - 1; j > i; j--) {
                        if (item.get(j).equals(item.get(i))) {
                            item.remove(j);
                        }
                    }
                }
            }
        } else if (name[0].equals("载波通道加工相")) {
            List<BZSJ> bzsjList = util.getBZSJ("载波通道加工相");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("光纤接口模式")) {
            List<BZSJ> bzsjList = util.getBZSJ("光纤接口模式");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("合并单元功能")) {
            List<BZSJ> bzsjList = util.getBZSJ("合并单元功能");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("智能终端功能")) {
            List<BZSJ> bzsjList = util.getBZSJ("智能终端功能");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("互感器类型")) {
            List<BZSJ> bzsjList = util.getBZSJ("互感器类型");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("交换机类型")) {
            List<BZSJ> bzsjList = util.getBZSJ("交换机类型");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("交换机功能")) {
            List<BZSJ> bzsjList = util.getBZSJ("交换机功能");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("对时方式")) {
            List<BZSJ> bzsjList = util.getBZSJ("对时方式");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("供电电源")) {
            List<BZSJ> bzsjList = util.getBZSJ("供电电源");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("装置属性")) {
            List<BZSJ> bzsjList = util.getBZSJ("装置属性");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("运行单位") || name[0].equals("维护单位")) {
            List<DWLX> dwlxList = util.getDWLX("运行/维护单位");
            for (DWLX dwlx : dwlxList) {
                item.add(dwlx.getDWMC());
            }
        } else if (name[0].equals("设计单位")) {
            List<DWLX> dwlxList = util.getDWLX("设计单位");
            for (DWLX dwlx : dwlxList) {
                item.add(dwlx.getDWMC());
            }
        } else if (name[0].equals("基建单位")) {
            List<DWLX> dwlxList = util.getDWLX("基建单位");
            for (DWLX dwlx : dwlxList) {
                item.add(dwlx.getDWMC());
            }
        } else if (name[0].equals("运行状态")) {
            List<BZSJ> bzsjList = util.getBZSJ("运行状态");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("设备属性")) {
            List<BZSJ> bzsjList = util.getBZSJ("设备属性");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("资产性质")) {
            List<BZSJ> bzsjList = util.getBZSJ("资产性质");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("资产单位")) {
            List<BZSJ> bzsjList = util.getBZSJ("资产单位");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name[0].equals("模块名称")) {
            boolean isSIX = FZDeviceOneFragment.instance.isSix;
            boolean is2013 = FZDeviceOneFragment.instance.ltybzbb != null && FZDeviceOneFragment.instance.ltybzbb.equals("2013版");
            List<Object> list;
            if (!FZDeviceOneFragment.instance.selectCode.equals("")) {
                list = util.getFZBHSBXHRJBB(isSIX, is2013, FZDeviceOneFragment.instance.selectCode + "");
                for (Object o : list) {
                    if (isSIX && is2013) {

                    } else {
                        BHXHRJBB bhxhrjbb = (BHXHRJBB) o;
                        if (bhxhrjbb.getMkmc() != null) {
                            item.add(bhxhrjbb.getMkmc());
                        }
                    }
                }
            }
            if (item.size() > 0) {
                for (int i = 0; i < item.size(); i++) {
                    for (int j = item.size() - 1; j > i; j--) {
                        if (item.get(j).equals(item.get(i))) {
                            item.remove(j);
                        }
                    }
                }
            }
        } else if (name[0].equals("软件版本")) {
            boolean isSIX = FZDeviceOneFragment.instance.isSix;
            boolean is2013 = FZDeviceOneFragment.instance.ltybzbb != null && FZDeviceOneFragment.instance.ltybzbb.equals("2013版");
            List<Object> list;
            if (!FZDeviceOneFragment.instance.selectCode.equals("")) {
                if (name.length == 1) {
                    if (isSIX && is2013) {
                        list = util.getFZBHSBXHRJBB(isSIX, is2013, FZDeviceOneFragment.instance.bhxh);
                    } else {
                        list = util.getFZBHSBXHRJBB(isSIX, is2013, FZDeviceOneFragment.instance.selectCode + "");
                    }
                } else {
                    list = util.getFZBHSBXHRJBB(isSIX, is2013, FZDeviceOneFragment.instance.selectCode + "", name[1]);
                }
                for (int i = 0; i < list.size(); i++) {
                    if (is2013 && isSIX) {
                        LTYSBXH ltysbxh = (LTYSBXH) list.get(i);
                        item.add(ltysbxh.getRjbb() + "");
                    } else {
                        BHXHRJBB rjbb = (BHXHRJBB) list.get(i);
                        if ((rjbb.getBb() + "").equals("无") && (rjbb.getJym() + "").equals("无")) {
                            item.add("版本：无，检验码：无");
                        } else if ((rjbb.getBb() + "").equals("无") && !(rjbb.getJym() + "").equals("无")) {
                            item.add("版本：无，检验码：" + rjbb.getJym());
                        } else if (!(rjbb.getBb() + "").equals("无") && (rjbb.getJym() + "").equals("无")) {
                            item.add("版本：" + rjbb.getBb() + "，检验码：无");
                        } else if (!(rjbb.getBb() + "").equals("无") && !(rjbb.getJym() + "").equals("无")) {
                            item.add("版本：" + rjbb.getBb() + "，检验码：" + rjbb.getJym());
                        }
                    }
                }
                if (item.size() > 0) {
                    for (int i = 0; i < item.size(); i++) {
                        for (int j = item.size() - 1; j > i; j--) {
                            if (item.get(j).equals(item.get(i))) {
                                item.remove(j);
                            }
                        }
                    }
                }
            }
        } else if (name[0].equals("ICD文件名")) {
            List<Object> list;
            if (!FZDeviceOneFragment.instance.selectCode.equals("")) {
                list = util.getFZBHSBXHRJBB(true, true, FZDeviceOneFragment.instance.bhxh, FZDeviceOneFragment.instance.ltysbxh.getRjbb());
                for (Object o : list) {
                    LTYSBXH ltysbxh = (LTYSBXH) o;
                    if (ltysbxh.getWjmc() != null & !ltysbxh.getWjmc().equals("")) {
                        item.add(ltysbxh.getWjmc());
                    }
                }
            }
            if (item.size() > 0) {
                for (int i = 0; i < item.size(); i++) {
                    for (int j = item.size() - 1; j > i; j--) {
                        if (item.get(j).equals(item.get(i))) {
                            item.remove(j);
                        }
                    }
                }
            }
        }
        return item;
    }

    /**
     * 获取一次设备名称
     *
     * @param util 数据库操作类
     * @param type 一次设备类型
     * @param isBh 是否为保护
     */
    public static List<String> getYCSBMC(IDaoUtil util, String type, boolean isBh) {
        List<String> data = new ArrayList<>();
        String czmc = FZDeviceOneFragment.instance.czmc + "";
        String gldw = FZDeviceOneFragment.instance.dwmc + "";
        List<Object> list;
        switch (type) {
            case "线路":
                list = util.getYCSBMC(XLCS.class, czmc, gldw);
                for (Object o : list) {
                    data.add(((XLCS) o).getXLMC());
                }
                break;
            case "电抗器":
                list = util.getYCSBMC(DKQCS.class, czmc, gldw);
                for (Object o : list) {
                    data.add(((DKQCS) o).getDkqmc());
                }
                break;
            case "电容器":
                list = util.getYCSBMC(DRQCS.class, czmc, gldw);
                for (Object o : list) {
                    data.add(((DRQCS) o).getDRQMC());
                }
                break;
            case "电动机":
                list = util.getYCSBMC(DDJCS.class, czmc, gldw);
                for (Object o : list) {
                    data.add(((DDJCS) o).getDDJMC());
                }
                break;
            case "母线":
                list = util.getYCSBMC(MXCS.class, czmc, gldw);
                for (Object o : list) {
                    data.add(((MXCS) o).getMXMC());
                }
                break;
            case "断路器":
                list = util.getYCSBMC(DLQCS.class, czmc, gldw);
                for (Object o : list) {
                    data.add(((DLQCS) o).getDLQMC());
                }
                break;
            case "变压器":
                list = util.getYCSBMC(BYQCS.class, czmc, gldw);
                for (Object o : list) {
                    data.add(((BYQCS) o).getBYQMC());
                }
                break;
            case "发电机":
                list = util.getYCSBMC(FDJCS.class, czmc, gldw);
                for (Object o : list) {
                    data.add(((FDJCS) o).getFDJMC());
                }
                break;
            case "其他":
                data.add("其他");
                break;
        }
        if (isBh) {
            List<Object> bhpzList = util.getYCSBMCFromBHOrFZ(BHPZ.class, gldw, czmc, type);
            for (Object o : bhpzList) {
                String ycsbmc = ((BHPZ) o).getYcsbmc();
                if (ycsbmc.contains(",")) {
                    for (String s : ycsbmc.split(",")) {
                        data.add(s);
                    }
                } else {
                    data.add(ycsbmc);
                }
            }
        } else {
            List<Object> fzbhsbList = util.getYCSBMCFromBHOrFZ(FZBHSB.class, gldw, czmc, type);
            for (Object o : fzbhsbList) {
                String ycsbmc = ((FZBHSB) o).getYcsbmc();
                if (ycsbmc.contains(",")) {
                    for (String s : ycsbmc.split(",")) {
                        data.add(s);
                    }
                } else {
                    data.add(ycsbmc);
                }
            }
        }
        //去除重复的一次设备名称
        for (int i = 0; i < data.size(); i++) {
            for (int j = data.size() - 1; j > i; j--) {
                if (data.get(j).equals(data.get(i))) {
                    data.remove(j);
                }
            }
        }
        return data;
    }

    /**
     * 判断是否快速双击点击
     *
     * @return
     */
    public static boolean IsNotNull(String name) {
        boolean isnull = true;
        if (name == null || name.equals("")) {
            isnull = false;
        }
        return isnull;
    }

    /**
     * 判断是否快速双击点击
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 300) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 是否快速的点击按钮
     *
     * @return true为快速点击
     */
    public static boolean isFastClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}