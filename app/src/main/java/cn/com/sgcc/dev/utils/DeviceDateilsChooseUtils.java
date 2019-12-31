package cn.com.sgcc.dev.utils;

import android.content.Context;

import org.greenrobot.greendao.Property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.dao2.LTYSBXHDao;
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
import cn.com.sgcc.dev.model2.ycsb.JGCS;
import cn.com.sgcc.dev.model2.ycsb.MXCS;
import cn.com.sgcc.dev.model2.ycsb.XLCS;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Ak;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Base;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Inset;
import cn.com.sgcc.dev.view.fragment.DeviceOneFragment;
import cn.com.sgcc.dev.view.fragment.FZDeviceOneFragment;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details1;

/**
 * <p>@description:装置数据选择工具类</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/1/12
 */

public class DeviceDateilsChooseUtils {
    private static long lastClickTime;
    private static BHSBXHB bhsbxhb;

    public static List<String> DeviceDateilsFind(final String name, final Context context, Map<String, Object> map) {
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

            String ycsblx = (String) map.get("ycsblx");
            String jglx = map.containsKey("jglx") ? map.get("jglx").toString() : "";
            String jgmc = map.containsKey("jgmc") ? map.get("jgmc").toString() : "";
            item.addAll(getYCSBMC(util, ycsblx, false, jglx, jgmc));
//          一次设备名称取所有，不分保护辅助。
        } else if (name.equals("电压等级")) {
            List<BZSJ> bzsjList = util.getBZSJ("电压等级");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (name.equals("装置类别")) {
            List<BZSJ> bzsjList = util.getBZSJ("主保护类别");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (name.equals("装置类别细化") || name.equals("设备类型")) {
            String protect_type = map.get("bhlb") + "";
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
        else if (name.equals("装置型号")) {
            String zzcj = (String) map.get("zzcj");
            String bhlb = (String) map.get("bhlb");
            boolean isSIX = (boolean) map.get("isSIX");
            boolean is2013 = (boolean) map.get("is2013");
            String czlx = (String) map.get("czlx");
            String dydj = map.get("dydj") + "";
            String usegddate = (String) map.get("usegddate");

            int dydjs = 0;
            if (dydj != null && !dydj.equals("")) {
                dydjs = Integer.parseInt(dydj);
            }
            CZCS czcs = util.getCZCSByGLDW();

            if (bhlb.equals("") || bhlb.equals("")) {
            } else {
                List<Object> list = util.getBHXH(isSIX, is2013, zzcj, bhlb, czlx, dydj, usegddate);
                if (list.size() > 0) {
                    if (!is2013 && dydjs < 220) {
                        if (!czlx.equals("智能站") || czlx.equals("智能站") && czcs.getCzzgdydj() < 110) {
                            //查重
                            List<Object> listdelete = new ArrayList<>();
                            for (int i = 0; i < list.size(); i++) {
                                for (int j = list.size() - 1; j > i; j--) {
                                    String xh1 = ((BHSBXHB) list.get(i)).getSbxh() + "";
                                    String xh2 = ((BHSBXHB) list.get(j)).getSbxh() + "";
                                    if (xh1.equals(xh2)) {
                                        if (((BHSBXHB) list.get(i)).getSfqy() != null && !((BHSBXHB) list.get(i)).getSfqy().equals("")) {
                                            listdelete.add(list.get(j));
                                        } else {
//                                            list.remove(i);
                                            listdelete.add(list.get(i));
                                        }
                                    }
                                }
                            }
                            for (Object o : listdelete) {
                                list.remove(o);
                            }
                            listdelete.clear();
                        }
                    }

                    Fragment_Type_Base.codeMap.clear();
                    Fragment_Type_Base.bblxMap.clear();
                    Fragment_Type_Base.flMap.clear();
                    Fragment_Type_Base.lxMap.clear();
                }
                if (isSIX && is2013) {
                    for (Object o : list) {
                        item.add(((LTYSBXH) o).getBhxh());
                        Fragment_Type_Base.codeMap.put(((LTYSBXH) o).getBhxh(), ((LTYSBXH) o).getCode());
                        Fragment_Type_Base.flMap.put(((LTYSBXH) o).getBhxh(), ((LTYSBXH) o).getBhfl());
                        Fragment_Type_Base.lxMap.put(((LTYSBXH) o).getBhxh(), ((LTYSBXH) o).getBhlx());
                    }
                } else {

                    for (Object o : list) {
                        BHSBXHB bhsbxhb = ((BHSBXHB) o);
                        item.add(((BHSBXHB) o).getSbxh());
                        Fragment_Type_Base.codeMap.put(bhsbxhb.getSbxh(), bhsbxhb.getCode());
                        Fragment_Type_Base.bblxMap.put(bhsbxhb.getSbxh(), bhsbxhb.getBblx());
                        Fragment_Type_Base.flMap.put(bhsbxhb.getSbxh(), bhsbxhb.getBhfl());
                        Fragment_Type_Base.lxMap.put(bhsbxhb.getSbxh(), bhsbxhb.getBhlx());

                    }
                }
            }
        } else if (name.equals("装置分类")) {
            String protect_type = "";
            String made_company = "";
            String protect_type_m = "";
            boolean isSIX;
            boolean is_2013;
            //默认保护型号
//            if (protect_type.equals("") || made_company.equals("") || protect_type_m.equals("")) {
            if (true) {
                item.add("国产");
                item.add("进口");
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
        } else if (name.equals("装置类型")) {
            String protect_type = "";
            String made_company = "";
            String protect_type_m = "";
            boolean isSIX;
            boolean is_2013 = (boolean) map.get("is2013");
            //            if (protect_type.equals("") || made_company.equals("") || protect_type_m.equals("")) {
            if (true) {
                if (is_2013) {
                    item.add("微机型");
                } else {
                    item.add("微机型");
                    item.add("电磁型");
//                    item.add("微机化");
                    item.add("集成电路");
                    item.add("晶体管");
                }
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

            boolean isrjbb = name.equals("软件版本");
            String bhxh = map.get("bhxh") + "";
            if (bhxh.equals("") || bhxh.equals("null")) {
                bhxh = "";
            } else {
                boolean isSIX = (boolean) map.get("isSIX");
                boolean is2013 = (boolean) map.get("is2013");
                String bblx = map.get("bblx") + "";
                String dydj = (String) map.get("dydj");
                String czlx = map.get("czlx") + "";
                CZCS czcs = util.getCZCSByGLDW();
                int dydjs = 0;
                if (dydj != null && !dydj.equals("")) {
                    dydjs = Integer.parseInt(dydj);
                }
                List<Object> list = null;
                if (!is2013) {
                    BHSBXHB bhsbxhb = (BHSBXHB) util.getBHXHByCode(bhxh, isSIX, is2013);

                    String sameCode = "";
                    if (!is2013 && dydjs < 220) {
                        if (!czlx.equals("智能站") || czlx.equals("智能站") && czcs.getCzzgdydj() < 110) {
                            sameCode = util.getSameBhxhCode(bhxh, bblx);
                        }
                    }

                    if (sameCode != null && !sameCode.equals("")) {
                        if (map.containsKey("mkmc")) {
                            if (Fragment_Type_Base.instance.list_one_data.size() > 1) {
                                if (sameCode.equals(bhsbxhb.getCode())) {
                                    list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "", (String) map.get("mkmc"));
                                } else {
                                    list = util.getFZBHSBXHRJBB(isSIX, is2013, sameCode + "", (String) map.get("mkmc"));
                                }
                            } else {
                                list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "", (String) map.get("mkmc"));
                                List<Object> sameList = util.getFZBHSBXHRJBB(isSIX, is2013, sameCode + "", (String) map.get("mkmc"));
                                if (sameList != null && sameList.size() > 0) {
                                    list.addAll(sameList);
                                }
                            }
                        } else {
                            list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "");
                            List<Object> sameList = util.getFZBHSBXHRJBB(isSIX, is2013, sameCode + "");
                            if (sameList != null && sameList.size() > 0) {
                                list.addAll(sameList);
                            }
                        }
                    } else {
                        if (map.containsKey("mkmc")) {
                            list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "", (String) map.get("mkmc"));
                        } else {
                            list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "");
                        }
                    }
                    if (list != null && list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            BHXHRJBB bb1 = (BHXHRJBB) list.get(i);
                            for (int j = list.size() - 1; j > i; j--) {
                                BHXHRJBB bb2 = (BHXHRJBB) list.get(j);
                                if (bb1.getBb().equals(bb2.getBb())) {
                                    BHSBXHB xhb = (BHSBXHB) util.getBHXHByCode(bb1.getBhxhcode(),
                                            false, false);
                                    if (xhb.getSfqy() != null && xhb.getSfqy().equals("Y") || xhb.getSfqy() != null && xhb.getSfqy().equals("N")) {
                                        list.remove(j);
                                    } else {
                                        list.remove(i);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    String zzcj = (String) map.get("zzcj");
                    String bhlb = (String) map.get("bhlb");
                    list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "", zzcj, bhlb);
//                    list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "");
                }

                Fragment_Type_Base.instance.bbToCode.clear();

                for (int i = 0; i < list.size(); i++) {
                    if (is2013 && isSIX) {
                        LTYSBXH ltysbxh = (LTYSBXH) list.get(i);
                        String[] ss = ltysbxh.getRjbb().split(" ");
                        if (isrjbb) {
                            if (ltysbxh.getRjbb() != null && !ltysbxh.getRjbb().equals("")) {
//                                String scsj = ltysbxh.getRjbb().replace(ss[0] + " ", "").replace(" " + ss[ss.length - 1], "");
//                                Fragment_Type_Base.instance.dateMap.put(ss[0], scsj);
//                                item.add(ss[0]);
                                item.add(ltysbxh.getRjbb() + "");
                            }
                        } else {
                            if (ltysbxh.getRjbb() != null && !ltysbxh.getRjbb().equals("")) {
                                item.add(ss[ss.length - 1]);
                            }
                        }
                    } else {
                        BHXHRJBB rjbb = (BHXHRJBB) list.get(i);
                        Fragment_Type_Base.instance.bbToCode.put(rjbb.getBb(), rjbb.getBhxhcode());
                        if (isrjbb) {
                            if ((rjbb.getBb() + "").equals("无") || (rjbb.getBb() + "").equals("")) {
                                item.add("无");
                            } else {
                                item.add("" + rjbb.getBb());
                            }
                        } else {
                            if ((rjbb.getJym() + "").equals("无") || (rjbb.getJym() + "").equals("")) {
                                item.add("无");
                            } else {
                                item.add("" + rjbb.getJym());
                            }
                        }
                    }
                }
            }
        } else if (name.equals("校验码")) {
            boolean isSIX = (boolean) map.get("isSIX");
            boolean is2013 = (boolean) map.get("is2013");
            String bhxh = map.get("bhxh") + "";
            String bb = map.get("bb") + "";

            if (bb.equals("")) {
                return item;
            }

            List<Object> list;
            if (map.containsKey("mkmc")) {
                list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "", (String) map.get("mkmc"), bb);
            } else {
                list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "", "", bb);
            }

            for (int i = 0; i < list.size(); i++) {
                if (is2013 && isSIX) {
                    LTYSBXH ltysbxh = (LTYSBXH) list.get(i);
                    item.add(ltysbxh.getRjbb());
                } else {
                    BHXHRJBB rjbb = (BHXHRJBB) list.get(i);
                    if ((rjbb.getJym() + "").equals("无") || (rjbb.getJym() + "").equals("")) {
                        item.add("无");
                    } else {
                        item.add(rjbb.getJym());
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

            String one_device_type = Fragment_Type_Inset.instance.ycsblx + "";
            String electric_level = Fragment_Type_Inset.instance.dydj + "";

            CZCS czcs = util.getCZCSByGLDW();
            String company_name = czcs.getGldw();
            String control_company_name = czcs.getCzmc();
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
            list = util.getTZGX(BHPZ.class, control_company_name + "", electric_level, company_name + "");
            for (Object o : list) {
                String kgbh = ((BHPZ) o).getKgbh();
                if (kgbh.contains(",")) {
                    for (String s : kgbh.split(",")) {
                        item.add(s+"");
                    }
                } else if (kgbh.contains("，")) {
                    for (String s : kgbh.split("，")) {
                        item.add(s+"");
                    }
                } else if (kgbh!=null&&!kgbh.equals("")){
                    item.add(kgbh+"");
                }
            }
        } else if (name.equals("保护套别")) {
            List<BZSJ> bzsjList = util.getBZSJ("保护套别");
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
            Fragment_Type_Ak.instance.akxt_id_list.clear();
            //查重
            for (int i = 0; i < zkxtList.size(); i++) {
                //验空
                if (zkxtList.get(i).getAkxtm() == null || zkxtList.get(i).getAkxtm().equals("")|| zkxtList.get(i).getAkxtm().equals(null) || zkxtList.get(i).getAkxtm().equals("null")) {
                    zkxtList.remove(i);
                }
            }
            //去除重复
            for (int i = 0; i < zkxtList.size(); i++) {
                for (int j = zkxtList.size() - 1; j > i; j--) {
                    if (zkxtList.get(j).getAkxtm().equals(zkxtList.get(i).getAkxtm())) {
                        zkxtList.remove(j);
                    }
                }
            }
            for (AKXT akxt : zkxtList) {
                item.add(akxt.getAkxtm() + "");
                Fragment_Type_Ak.instance.akxt_id_list.add(akxt.getId() + "");
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
            } else {
                item.add("暂无");
            }
        } else if (name.equals("选配功能")) {

            /**
             * 根据制造厂家和保护型号带出选配功能、ICD文件名、软件版本
             * 传值顺序：ZZCJ、BHXH
             */
            List<Object> list;
            String bhxh = map.get("bhxh") + "";
            String rjbb = map.get("rjbb") + "";
            String zzcj = (String) map.get("zzcj");
            String bhlb = (String) map.get("bhlb");

            if (true) {
                list = util.getFZBHSBXHRJBB(true, true, bhxh, zzcj, bhlb, rjbb);
//                list = util.getFZBHSBXHRJBB(true, true, bhxh, rjbb + "");
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
            String bhxh = (String) map.get("bhxh");
            String rjbb = (String) map.get("rjbb");
            String xp = map.get("xp") + "";
            String code = Fragment_Type_Base.codeMap.get(bhxh) + "";
            String zzcj = (String) map.get("zzcj");
            String bhlb = (String) map.get("bhlb");

            list = util.getFZBHSBXHRJBB(true, true, bhxh, zzcj, bhlb, rjbb, xp);
//            list = util.getFZBHSBXHRJBB(true, true, bhxh, rjbb,xp);
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

        } else if (name.equals("MD5校验码") || name.equals("CRC32验证码") || name.equals("ICD文件版本")) {
            /**
             * 根据制造厂家和保护型号带出选配功能、ICD文件名、软件版本
             * 传值顺序：ZZCJ、BHXH
             */
            List<LTYSBXH> list;
            String bhxh = (String) map.get("bhxh");
            String rjbb = (String) map.get("rjbb");
            String xp = map.get("xp") + "";
            String code = Fragment_Type_Base.codeMap.get(bhxh) + "";
            String zzcj = (String) map.get("zzcj");
            String bhlb = (String) map.get("bhlb");
            String wjmc = (String) map.get("wjmc");
            String wjbb = (String) map.get("wjbb");
            String crc32 = (String) map.get("crc32");
            String md5 = (String) map.get("md5");

            Map<Property, String> maps = new HashMap();
            if (bhxh != null) {
                maps.put(LTYSBXHDao.Properties.Bhxh, bhxh);
            }
            if (zzcj != null) {
                maps.put(LTYSBXHDao.Properties.Zzcj, zzcj);
            }
            if (bhlb != null) {
                maps.put(LTYSBXHDao.Properties.Bhlb, bhlb);
            }
            if (rjbb != null) {
                maps.put(LTYSBXHDao.Properties.Rjbb, rjbb);
            }
            if (xp != null) {
                maps.put(LTYSBXHDao.Properties.Xp, xp);
            }
            if (wjmc != null) {
                maps.put(LTYSBXHDao.Properties.Wjmc, wjmc);
            }
            if (wjbb != null) {
                maps.put(LTYSBXHDao.Properties.Wjbb, wjbb);
            }
            if (crc32 != null) {
                maps.put(LTYSBXHDao.Properties.Crc32, crc32);
            }
            if (md5 != null) {
                maps.put(LTYSBXHDao.Properties.Md5, md5);
            }

//            list = util.getFZBHSBXHRJBB(true, true, bhxh, zzcj, bhlb, rjbb, xp);
            list = util.getLtyXX(maps);
            for (int i = 0; i < list.size(); i++) {
                if (true && true) {
                    //六统一2013版取值唯一
                    for (Object o : list) {
                        if (((LTYSBXH) o).getWjmc() != null && !((LTYSBXH) o).getWjmc().equals("")) {
                            if (name.equals("ICD文件版本")) {
                                item.add(((LTYSBXH) o).getWjbb() + "");
                            } else if (name.equals("CRC32验证码")) {
                                item.add(((LTYSBXH) o).getCrc32() + "");
                            } else if (name.equals("MD5校验码")) {
                                item.add(((LTYSBXH) o).getMd5() + "");
                            }
                        }
                    }
                }
            }

        } else if (name.equals("是否接入调度主站")) {
            item.add("是");
            item.add("否");
        } else if (name.equals("模块名称")) {
            boolean isSIX = (boolean) map.get("isSIX");
            boolean is2013 = (boolean) map.get("is2013");
            String selectCode = (String) map.get("selectCode");

            List<Object> list;
            if (selectCode != null && !selectCode.equals("")) {
                list = util.getFZBHSBXHRJBB(isSIX, is2013, selectCode);
                for (Object o : list) {
                    BHXHRJBB bhxhrjbb = (BHXHRJBB) o;
                    if (bhxhrjbb.getMkmc() != null) {
                        item.add(bhxhrjbb.getMkmc());
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
        } else if (name.equals("接口用途")) {
            item.add("电源+开入接口");
            item.add("交流量接口");
            item.add("开出接口");
            item.add("光纤接口");
        } else if (name.equals("间隔类型")) {
            List<BZSJ> bzsjList = util.getBZSJ("间隔类型");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            } else {
//                item.add("暂无");
            }
        } else if (name.equals("间隔名称")) {
            String jglx = map.containsKey("jglx") ? map.get("jglx").toString() : "";
            item.addAll(getYCSBMC(util, "间隔参数", false, jglx, null));
            //间隔名称，不区分保护辅助
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
        if (item.size() > 0) {
            if (name.equals("制造厂家") || name.equals("装置类别") || name.equals("装置型号") || name.equals("装置型号")|| name.equals("装置分类")
                    || name.equals("装置类型") || name.equals("软件版本") || name.equals("校验码") || name.equals("装置类别细化")
                    || name.equals("模块名称") || name.equals("一次设备类型") || name.equals("一次设备名称") || name.equals("电压等级")
                    || name.equals("单位名称") || name.equals("通道类型") || name.equals("设备功能配置") || name.equals("六统一标准版本")
                    || name.equals("ICD文件名") || name.equals("测距形式") || name.equals("故障录波器类型") || name.equals("设备类型")
                    || name.equals("安控系统调度名") || name.equals("安控站点类型")) {

            } else {
                item.add(0, "");
            }
        }
        return item;
    }

    public static List<String> getFZDateilsFind(final Context context, final String type, Map<String, Object> map,String tg) {
        List<String> item = new ArrayList<>();
        IDaoUtil util = new DaoUtil(context);
        if (type.equals("是否使用国调标准型号")) {
            String czlx = (String) map.get("czlx");
            int dydj = (int) map.get("dydj");
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
        } else if (type.equals("装置类别")) {
            List<BZSJ> bzsjList = util.getBZSJ("辅助保护类别");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("六统一标准版本")) {
            List<BZSJ> bzsjList = util.getBZSJ("六统一标准版本");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("单位名称")) {
            CZCS czcs = util.getCZCSByGLDW();
            item.add(czcs.getGldw());
        } else if (type.equals("调度单位")) {
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
        } else if (type.equals("厂站名称")) {
            CZCS czcs = util.getCZCSByGLDW();
            item.add(czcs.getCzmc());
        } else if (type.equals("一次设备类型")) {
            List<BZSJ> bzsjList = util.getBZSJ("一次设备类型");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("一次设备名称")) {
            String ycsblx = (String) map.get("ycsblx");
            String jglx = map.containsKey("jglx") ? map.get("jglx").toString() : "";
            String jgmc = map.containsKey("jgmc") ? map.get("jgmc").toString() : "";
            item.addAll(getYCSBMC(util, ycsblx, false, jglx, jgmc));
        } else if (type.equals("电压等级")) {
            List<BZSJ> bzsjList = util.getBZSJ("电压等级");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("制造厂家")) {
            List<ZZCJ> bzsjList = util.getZZCJ(null);
            for (ZZCJ zzcj : bzsjList) {
                item.add(zzcj.getMC());
            }
        } else if (type.equals("辅助保护套别")) {
            //根据tg的值，去查询对应的辅助套别数据
            //tg = “0”就是默认的第1套','第2套','第3套','第4套','第5套','第6套','第7套','第8套','第9套','第10套'
            //tg = “1”第1套、第2套、第3套、第4套、第1套线路保护A通道、第1套线路保护B通道、第2套线路保护A通道、第2套线路保护B通道、
            // 第1套过压远跳保护A通道、第1套过压远跳保护保护B通道、第2套过压远跳保护保护A通道、第2套过压远跳保护保护B通道
            //tg = “2” 第1套、第2套、 '第1套电压'、'第2套电压'
            //tg = “3” '过程层A网','过程层B网','过程层A网中心','过程层B网中心','第X串SV过程层A网','第X串SV过程层B网',
            // '第X串GOOSE过程层A网','第X串GOOSE过程层B网','SV过程层A网中心','SV过程层B网中心','GOOSE过程层A网中心','GOOSE过程层B网中心'
            //tg = “4” '第1套','第2套'， '第1套本体'、 '第2套本体'、 '第1套电压'、'第2套电压'，本体
            /*List<String> list1 =  new ArrayList<>();
            list1.add("第1套");list1.add("第2套");list1.add("第3套");list1.add("第4套");list1.add("第1套线路保护A通道");list1.add("第1套线路保护B通道");
            list1.add("第2套线路保护A通道");list1.add("第2套线路保护B通道");list1.add("第1套过压远跳保护A通道");list1.add("第1套过压远跳保护B通道");
            list1.add("第2套过压远跳保护保护A通道");list1.add("第2套过压远跳保护保护B通道");*/
            if(tg.equals("1")){
               /* if (list1 != null && list1.size() > 0) {
                    item.addAll(list1);//实验一下，如果tg = “1”，看结果是否是正确
                }*/
                List<BZSJ> bzsjList = util.getBZSJ("辅助保护套别-1");
                if (bzsjList != null && bzsjList.size() > 0) {
                    for (BZSJ bzsj : bzsjList) {
                        item.add(bzsj.getBzsjSxmc());
                    }
                }
            }else if(tg.equals("2")){
                List<BZSJ> bzsjList = util.getBZSJ("辅助保护套别-2");
                if (bzsjList != null && bzsjList.size() > 0) {
                    for (BZSJ bzsj : bzsjList) {
                        item.add(bzsj.getBzsjSxmc());
                    }
                }
            }else if(tg.equals("3")){
                List<BZSJ> bzsjList = util.getBZSJ("辅助保护套别-3");
                if (bzsjList != null && bzsjList.size() > 0) {
                    for (BZSJ bzsj : bzsjList) {
                        item.add(bzsj.getBzsjSxmc());
                    }
                }
            }else if(tg.equals("4")){
                List<BZSJ> bzsjList = util.getBZSJ("辅助保护套别-4");
                if (bzsjList != null && bzsjList.size() > 0) {
                    for (BZSJ bzsj : bzsjList) {
                        item.add(bzsj.getBzsjSxmc());
                    }
                }
            }else{
                List<BZSJ> bzsjList = util.getBZSJ("辅助保护套别");
                if (bzsjList != null && bzsjList.size() > 0) {
                    for (BZSJ bzsj : bzsjList) {
                        item.add(bzsj.getBzsjSxmc());
                    }
                }
            }
        } else if (type.equals("装置型号")) {
            String zzcj = (String) map.get("zzcj");
            String bhlb = (String) map.get("bhlb");
            boolean isSIX = (boolean) map.get("isSIX");
            boolean is2013 = (boolean) map.get("is2013");
            String czlx = (String) map.get("czlx");
            String dydj = (String) map.get("dydj");
            String usegddate = (String) map.get("usegddate");

            List<Object> list = util.getBHXH(isSIX, is2013, zzcj, bhlb, czlx, dydj, usegddate);

            if (isSIX && is2013) {
                for (int i = 0; i < list.size(); i++) {
                    LTYSBXH xh1 = (LTYSBXH) list.get(i);
                    for (int j = list.size() - 1; j > i; j--) {
                        LTYSBXH xh2 = (LTYSBXH) list.get(j);
                        if (xh1.getBhxh().equals(xh2.getBhxh())) {
                            list.remove(j);
                        }
                    }
                }

                for (int i = 0; i < list.size(); i++) {
                    LTYSBXH ltysbxh = (LTYSBXH) list.get(i);
                    item.add(ltysbxh.getBhxh());
                    Details1.instance.codeMap.put(ltysbxh.getBhxh(), ltysbxh.getCode());
                    Details1.instance.bblxMap.put(ltysbxh.getBhxh(), ltysbxh.getBblx());
                }
            } else {
                //型号去重,型号相同时去掉自行维护的型号
                for (int i = 0; i < list.size(); i++) {
                    BHSBXHB xhb1 = (BHSBXHB) list.get(i);
                    for (int j = list.size() - 1; j > i; j--) {
                        BHSBXHB xhb2 = (BHSBXHB) list.get(j);
                        if (xhb1.getSbxh().equals(xhb2.getSbxh())) {
                            if (xhb1.getSfqy() != null && xhb1.getSfqy().equals("Y")
                                    || xhb1.getSfqy() != null && xhb1.getSfqy().equals("N")) {
                                list.remove(j);
                            } else {
                                list.remove(i);
                            }
                        }
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    BHSBXHB bhsbxhb = ((BHSBXHB) list.get(i));
                    item.add(bhsbxhb.getSbxh());
                    Details1.instance.codeMap.put(bhsbxhb.getSbxh(), bhsbxhb.getCode());
                    Details1.instance.bblxMap.put(bhsbxhb.getSbxh(), bhsbxhb.getBblx());
                }
            }
        } else if (type.equals("装置分类")) {
            for (String s : Constants.zzfl) {
                item.add(s);
            }
        } else if (type.equals("装置类型")) {
            boolean isSix = Details1.instance.isSix;
            boolean is2013 = Details1.instance.is2013;
            if (isSix && is2013) {
                item.add("微机型");
            } else {
                for (String s : Constants.zzlx) {
                    item.add(s);
                }
            }
        } else if (type.equals("载波通道加工相")) {
            List<BZSJ> bzsjList = util.getBZSJ("载波通道加工相");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("光纤接口模式")) {
            List<BZSJ> bzsjList = util.getBZSJ("光纤接口模式");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("合并单元功能")) {
            List<BZSJ> bzsjList = util.getBZSJ("合并单元功能");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("智能终端功能")) {
            List<BZSJ> bzsjList = util.getBZSJ("智能终端功能");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("互感器类型")) {
            List<BZSJ> bzsjList = util.getBZSJ("互感器类型");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("交换机类型")) {
            List<BZSJ> bzsjList = util.getBZSJ("交换机类型");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("交换机功能")) {
            List<BZSJ> bzsjList = util.getBZSJ("交换机功能");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("对时方式")) {
            List<BZSJ> bzsjList = util.getBZSJ("对时方式");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("供电电源")) {
            List<BZSJ> bzsjList = util.getBZSJ("供电电源");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("装置属性")) {
            List<BZSJ> bzsjList = util.getBZSJ("装置属性");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("运行单位") || type.equals("维护单位")) {
            List<DWLX> dwlxList = util.getDWLX("运行/维护单位");
            for (DWLX dwlx : dwlxList) {
                item.add(dwlx.getDWMC());
            }
        } else if (type.equals("设计单位")) {
            List<DWLX> dwlxList = util.getDWLX("设计单位");
            for (DWLX dwlx : dwlxList) {
                item.add(dwlx.getDWMC());
            }
        } else if (type.equals("基建单位")) {
            List<DWLX> dwlxList = util.getDWLX("基建单位");
            for (DWLX dwlx : dwlxList) {
                item.add(dwlx.getDWMC());
            }
        } else if (type.equals("运行状态")) {
            List<BZSJ> bzsjList = util.getBZSJ("运行状态");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("设备属性")) {
            List<BZSJ> bzsjList = util.getBZSJ("设备属性");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("资产性质")) {
            List<BZSJ> bzsjList = util.getBZSJ("资产性质");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("资产单位")) {
            List<BZSJ> bzsjList = util.getBZSJ("资产单位");
            for (BZSJ bzsj : bzsjList) {
                item.add(bzsj.getBzsjSxmc());
            }
        } else if (type.equals("模块名称")) {
            boolean isSIX = (boolean) map.get("isSIX");
            boolean is2013 = (boolean) map.get("is2013");
            String selectCode = (String) map.get("selectCode");

            List<Object> list;
            if (selectCode != null && !selectCode.equals("")) {
                list = util.getFZBHSBXHRJBB(isSIX, is2013, selectCode);
                for (Object o : list) {
                    BHXHRJBB bhxhrjbb = (BHXHRJBB) o;
                    if (bhxhrjbb.getMkmc() != null) {
                        item.add(bhxhrjbb.getMkmc());
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
        } else if (type.equals("软件版本")) {
            boolean isrjbb = type.equals("软件版本");
            boolean isSIX = (boolean) map.get("isSIX");
            boolean is2013 = (boolean) map.get("is2013");
            String bhxh = map.get("bhxh") + "";
            String bblx = map.get("bblx") + "";
            String dydj = map.get("dydj") + "";

            BHSBXHB bhsbxhb = null;
            String sameCode = null;
            int dydjs = 0;
            if (dydj != null && !dydj.equals("") && !dydj.equalsIgnoreCase("null")) {
                dydjs = Integer.parseInt(dydj);
            }

            if (!is2013 && bhxh != null && !bhxh.equals("")
                    && !bhxh.equalsIgnoreCase("null")) {
                bhsbxhb = (BHSBXHB) util.getBHXHByCode(bhxh, isSIX, is2013);

                if (dydjs < 220) {
                    CZCS czcs = DemoActivity.instance.czcs;
                    if (!czcs.getBdzlx().equals("智能站") || czcs.getBdzlx().equals("智能站")
                            && czcs.getCzzgdydj() < 110) {
                        sameCode = util.getSameBhxhCode(bhxh, bblx);
                    }
                }
            }

            List<Object> list;
            if (sameCode != null) {
                if (map.containsKey("mkmc")) {
                    if (Details1.instance.rjbbList.size() > 1) {
                        if (bhsbxhb != null && bhsbxhb.getCode() != null &&
                                Details1.instance.rjbbList.get(0).getBhxhcode().equals(bhsbxhb.getCode())) {
                            list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "", (String) map.get("mkmc"));
                        } else {
                            list = util.getFZBHSBXHRJBB(isSIX, is2013, sameCode + "", (String) map.get("mkmc"));
                        }
                    } else {
                        list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "", (String) map.get("mkmc"));
                        List<Object> sameList = util.getFZBHSBXHRJBB(isSIX, is2013, sameCode + "", (String) map.get("mkmc"));
                        if (sameList != null && sameList.size() > 0) {
                            list.addAll(sameList);
                        }
                    }
                } else {
                    list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "");
                    List<Object> sameList = util.getFZBHSBXHRJBB(isSIX, is2013, sameCode + "");
                    if (sameList != null && sameList.size() > 0) {
                        list.addAll(sameList);
                    }
                }
            } else {
                if (map.containsKey("mkmc")) {
                    list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "", (String) map.get("mkmc"));
                } else {
                    if (isSIX && is2013) {
                        String zzcj = (String) map.get("zzcj");
                        String bhlb = (String) map.get("bhlb");
                        list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "", zzcj, bhlb);
                    } else {
                        list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "");
                    }
                }
            }

            Details1.instance.dateMap.clear();
            Details1.instance.bbToCode.clear();
            if (is2013 && isSIX) {
                for (Object o : list) {
                    LTYSBXH ltysbxh = (LTYSBXH) o;
                    item.add(ltysbxh.getRjbb());
                }

                for (int i = 0; i < item.size(); i++) {
                    for (int j = item.size() - 1; j > i; j--) {
                        if (item.get(j).equals(item.get(i))) {
                            item.remove(j);
                        }
                    }
                }
            } else {
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        BHXHRJBB bb1 = (BHXHRJBB) list.get(i);
                        for (int j = list.size() - 1; j > i; j--) {
                            BHXHRJBB bb2 = (BHXHRJBB) list.get(j);
                            if (bb1.getBb().equals(bb2.getBb())) {
                                BHSBXHB xhb = (BHSBXHB) util.getBHXHByCode(bb1.getBhxhcode(),
                                        false, false);
                                if (xhb.getSfqy().equals("Y")) {
                                    list.remove(j);
                                } else {
                                    list.remove(i);
                                }
                            }
                        }
                    }
                } else {
                    return item;
                }

                for (Object o : list) {
                    BHXHRJBB rjbb = (BHXHRJBB) o;
                    Details1.instance.bbToCode.put(rjbb.getBb(), rjbb.getBhxhcode());
                    if (isrjbb) {
                        item.add(rjbb.getBb());
                    } else {
                        item.add(rjbb.getJym());
                    }
                }
            }
        } else if (type.equals("校验码")) {
            boolean isSIX = (boolean) map.get("isSIX");
            boolean is2013 = (boolean) map.get("is2013");
            String bhxh = map.get("bhxh") + "";
            String bb = map.get("bb") + "";

            if (bb.equals("")) {
                return item;
            }

            List<Object> list;
            if (map.containsKey("mkmc")) {
                list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "", (String) map.get("mkmc"));
            } else {
                list = util.getFZBHSBXHRJBB(isSIX, is2013, bhxh + "", "", bb);
            }

            Details1.instance.dateMap.clear();
            for (int i = 0; i < list.size(); i++) {
                if (is2013 && isSIX) {
                    LTYSBXH ltysbxh = (LTYSBXH) list.get(i);
                    item.add(ltysbxh.getRjbb());
                } else {
                    BHXHRJBB rjbb = (BHXHRJBB) list.get(i);
                    item.add(rjbb.getJym());
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
        } else if (type.equals("ICD文件名")) {
            String bhxh = (String) map.get("bhxh");
            String rjbb = (String) map.get("rjbb");
            String zzcj = (String) map.get("zzcj");
            String bhlb = (String) map.get("bhlb");
            Map<Property, String> queryMap = new HashMap<>();
            queryMap.put(LTYSBXHDao.Properties.Bhxh, bhxh);
            queryMap.put(LTYSBXHDao.Properties.Rjbb, rjbb);
            queryMap.put(LTYSBXHDao.Properties.Zzcj, zzcj);
            queryMap.put(LTYSBXHDao.Properties.Bhlb, bhlb);

            List<LTYSBXH> ltys = util.getLtyXX(queryMap);

            for (LTYSBXH lty : ltys) {
                item.add(lty.getWjmc());
            }
            //去重
            if (item.size() > 0) {
                for (int i = 0; i < item.size(); i++) {
                    for (int j = item.size() - 1; j > i; j--) {
                        if (item.get(j).equals(item.get(i))) {
                            item.remove(j);
                        }
                    }
                }
            }
        } else if (type.equals("间隔类型")) {
            List<BZSJ> bzsjList = util.getBZSJ("间隔类型");
            bzsjList.toString();
            if (bzsjList.size() > 0) {
                for (int i = 0; i < bzsjList.size(); i++) {
                    String namedata = bzsjList.get(i).getBzsjSxmc() + "";
                    item.add(namedata);
                }
            }
        } else if (type.equals("间隔名称")) {
            String jglx = map.containsKey("jglx") ? map.get("jglx").toString() : "";
            item.addAll(getYCSBMC(util, "间隔参数", false, jglx, null));
        } else if (type.equals("ICD文件版本")) {
            String bhxh = (String) map.get("bhxh");
            String rjbb = (String) map.get("rjbb");
            String zzcj = (String) map.get("zzcj");
            String bhlb = (String) map.get("bhlb");
            if (map.containsKey("wjmc")) {
                String wjmc = (String) map.get("wjmc");
                Map<Property, String> queryMap = new HashMap<>();
                queryMap.put(LTYSBXHDao.Properties.Bhxh, bhxh);
                queryMap.put(LTYSBXHDao.Properties.Rjbb, rjbb);
                queryMap.put(LTYSBXHDao.Properties.Zzcj, zzcj);
                queryMap.put(LTYSBXHDao.Properties.Bhlb, bhlb);
                queryMap.put(LTYSBXHDao.Properties.Wjmc, wjmc);

                List<LTYSBXH> ltys = util.getLtyXX(queryMap);

                for (LTYSBXH lty : ltys) {
                    item.add(lty.getWjbb());
                }
                //去重
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

        } else if (type.equals("CRC32验证码")) {
            String bhxh = (String) map.get("bhxh");
            String rjbb = (String) map.get("rjbb");
            String zzcj = (String) map.get("zzcj");
            String bhlb = (String) map.get("bhlb");
            if (map.containsKey("wjmc") && map.containsKey("wjbb")) {
                String wjmc = (String) map.get("wjmc");
                String wjbb = (String) map.get("wjbb");
                Map<Property, String> queryMap = new HashMap<>();
                queryMap.put(LTYSBXHDao.Properties.Bhxh, bhxh);
                queryMap.put(LTYSBXHDao.Properties.Rjbb, rjbb);
                queryMap.put(LTYSBXHDao.Properties.Zzcj, zzcj);
                queryMap.put(LTYSBXHDao.Properties.Bhlb, bhlb);
                queryMap.put(LTYSBXHDao.Properties.Wjmc, wjmc);
                queryMap.put(LTYSBXHDao.Properties.Wjbb, wjbb);

                List<LTYSBXH> ltys = util.getLtyXX(queryMap);

                for (LTYSBXH lty : ltys) {
                    item.add(lty.getCrc32());
                }
                //去重
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
        } else if (type.equals("MD5校验码")) {
            String bhxh = (String) map.get("bhxh");
            String rjbb = (String) map.get("rjbb");
            String zzcj = (String) map.get("zzcj");
            String bhlb = (String) map.get("bhlb");
            if (map.containsKey("wjmc") && map.containsKey("wjbb") && map.containsKey("crc32")) {
                String wjmc = (String) map.get("wjmc");
                String wjbb = (String) map.get("wjbb");
                String crc32 = (String) map.get("crc32");
                Map<Property, String> queryMap = new HashMap<>();
                queryMap.put(LTYSBXHDao.Properties.Bhxh, bhxh);
                queryMap.put(LTYSBXHDao.Properties.Rjbb, rjbb);
                queryMap.put(LTYSBXHDao.Properties.Zzcj, zzcj);
                queryMap.put(LTYSBXHDao.Properties.Bhlb, bhlb);
                queryMap.put(LTYSBXHDao.Properties.Wjmc, wjmc);
                queryMap.put(LTYSBXHDao.Properties.Wjbb, wjbb);
                queryMap.put(LTYSBXHDao.Properties.Crc32, crc32);

                List<LTYSBXH> ltys = util.getLtyXX(queryMap);

                for (LTYSBXH lty : ltys) {
                    item.add(lty.getMd5());
                }
                //去重
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
        }

        for (int i = 0; i < item.size(); i++) {
            if (item.get(i) == null || item.get(i).equals("")) {
                item.remove(i);
            }
        }


        if (item.size() <= 0) {
            return item;
        }

        boolean isBT = false;
        for (String s : Constants.BTX_FZ) {
            if (s.equals(type)) {
                isBT = true;
                break;
            } else {
                isBT = false;
            }
        }
        if (!isBT) {
            item.add(0, "");
        }
        if (type.equals("软件版本") && Details1.instance.is2013) {
            item.remove(0);
        }
        return item;
    }

    /**
     * 获取一次设备名称
     *
     * @param util 数据库操作类
     * @param type 一次设备类型
     * @param isBh 是否为保护
     * @param jglx 间隔类型
     * @param jgmc 间隔名称
     */
    public static List<String> getYCSBMC(IDaoUtil util, String type, boolean isBh, String jglx, String jgmc) {
        List<String> data = new ArrayList<>();

        CZCS czcs = util.getCZCSByGLDW();
        String czmc = czcs.getCzmc();
        String gldw = czcs.getGldw();
        List<Object> list;
        switch (type) {
            case "线路":
                list = util.getYCSBMC(XLCS.class, czmc, gldw, jglx, jgmc);
                for (Object o : list) {
                    data.add(((XLCS) o).getXLMC());
                }
                break;
            case "电抗器":
                list = util.getYCSBMC(DKQCS.class, czmc, gldw, jglx, jgmc);
                for (Object o : list) {
                    data.add(((DKQCS) o).getDkqmc());
                }
                break;
            case "电容器":
                list = util.getYCSBMC(DRQCS.class, czmc, gldw, jglx, jgmc);
                for (Object o : list) {
                    data.add(((DRQCS) o).getDRQMC());
                }
                break;
            case "电动机":
                list = util.getYCSBMC(DDJCS.class, czmc, gldw, jglx, jgmc);
                for (Object o : list) {
                    data.add(((DDJCS) o).getDDJMC());
                }
                break;
            case "母线":
                list = util.getYCSBMC(MXCS.class, czmc, gldw, jglx, jgmc);
                for (Object o : list) {
                    data.add(((MXCS) o).getMXMC());
                }
                break;
            case "断路器":
                list = util.getYCSBMC(DLQCS.class, czmc, gldw, jglx, jgmc);
                for (Object o : list) {
                    data.add(((DLQCS) o).getDLQMC());
                }
                break;
            case "变压器":
                list = util.getYCSBMC(BYQCS.class, czmc, gldw, jglx, jgmc);
                for (Object o : list) {
                    data.add(((BYQCS) o).getBYQMC());
                }
                break;
            case "发电机":
                list = util.getYCSBMC(FDJCS.class, czmc, gldw, jglx, jgmc);
                for (Object o : list) {
                    data.add(((FDJCS) o).getFDJMC());
                }
                break;
            case "间隔参数":
                list = util.getYCSBMC(JGCS.class, czmc, gldw, jglx);
                for (Object o : list) {
                    data.add(((JGCS) o).getJgmc());
                }
                break;
            case "其他":
                data.add("其他");
                break;
        }
        if (isBh) {
            List<Object> bhpzList = util.getYCSBMCFromBHOrFZ(BHPZ.class, gldw, czmc, type, jglx, jgmc);
            for (Object o : bhpzList) {
                String ycsbmc = ((BHPZ) o).getYcsbmc();
                if (ycsbmc.contains(",")) {
                    for (String s : ycsbmc.split(",")) {
                        data.add(s);
                    }
                } else if (ycsbmc.contains("，")) {
                    for (String s : ycsbmc.split("，")) {
                        data.add(s);
                    }
                } else {
                    data.add(ycsbmc);
                }
            }
        } else {
            List<Object> fzbhsbList = util.getYCSBMCFromBHOrFZ(FZBHSB.class, gldw, czmc, type, jglx, jgmc);
            if (type.equals("间隔参数")) {
                for (Object o : fzbhsbList) {
                    String mc = ((FZBHSB) o).getJgmc();
                    data.add(mc);
                }
            } else {
                for (Object o : fzbhsbList) {
                    String ycsbmc = ((FZBHSB) o).getYcsbmc();
                    if (ycsbmc.contains(",")) {
                        for (String s : ycsbmc.split(",")) {
                            data.add(s);
                        }
                    } else if (ycsbmc.contains("，")) {
                        for (String s : ycsbmc.split("，")) {
                            data.add(s);
                        }
                    } else {
                        data.add(ycsbmc);
                    }
                }
            }

            List<Object> bhpzList = util.getYCSBMCFromBHOrFZ(BHPZ.class, gldw, czmc, type, jglx, jgmc);
            if (type.equals("间隔参数")) {
                for (Object o : bhpzList) {
                    String mc = ((BHPZ) o).getJgmc();
                    data.add(mc);
                }
            } else {
                for (Object o : bhpzList) {
                    String ycsbmc = ((BHPZ) o).getYcsbmc();
                    if (ycsbmc.contains(",")) {
                        for (String s : ycsbmc.split(",")) {
                            data.add(s);
                        }
                    } else if (ycsbmc.contains("，")) {
                        for (String s : ycsbmc.split("，")) {
                            data.add(s);
                        }
                    } else {
                        data.add(ycsbmc);
                    }
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
}