package cn.com.sgcc.dev.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.DeviceSelectAdapter;
import cn.com.sgcc.dev.adapter.FZDeviceOneAdapter;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.customeView.LoadingDialog;
import cn.com.sgcc.dev.customeView.MyDatePickerDialog;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.model2.BHSBXHB;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.model2.FZBHSB;
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
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.DeviceDateilsUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

/**
 * <p>@description:辅助保护设备详情页1</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/13
 */

public class FZDeviceOneFragment extends BaseFragment {
    public EditText tv_sbsbdm; //顶部设备识别代码
    private ListView infor_select;
    public List<DeviceDetailsNameItem> data_name;

    private FZDeviceOneAdapter adapter;
    private CustomDialog dialog;
    public static FZDeviceOneFragment instance = null;

    public IDaoUtil util;
    //是否六统一设备
    public boolean isSix = false;
    public boolean is2013 = false;

    //厂站最高电压等级
    public String czzgdydj = "";
    //六统一标准版本
    public String ltybzbb = "";
    //单位名称
    public String dwmc = "";
    //厂站名称
    public String czmc = "";
    //厂站类型
    public String czlx = "";
    //调度单位
    public String dddw = "";
    //一次设备类型
    public String ycsblx = "";
    //一次设备名称
    public String ycsbmc = "";
    //电压等级
    public String dydj = "";
    //保护类别
    public String bhlb = "";
    //制造厂家
    public String zzcj = "";
    //是否使用国调标准型号
    public String usegddata = "";
    //保护型号
    public String bhxh = "";
    //保护分类
    public String bhfl = "";
    //保护类型
    public String bhlx = "";
    //保护套别
    public String bhtb = "";
    //保护名称
    public String bhmc = "必填项,点击生成";

    //当保护类别选收发信机时  需要显示的属性
    public String zbtdjgx = ""; //载波通道加工相
    public String tdpl = "0"; //通道频率
    //合并单元显示：发送光纤接口数量、光纤接口模式、合并单元功能、接收光纤接口数量、互感器类型
    //合并单元智能终端集成：发送光纤接口数量、光纤接口模式、合并单元功能、接收光纤接口数量、互感器类型、智能终端功能
    //智能终端：发送光纤接口数量、光纤接口模式、智能终端功能、接收光纤接口数量
    //发送光纤接口数量和接收光纤接口数量为同一个，统一存入fsgxsl
    public String dsfs = ""; //对时方式
    public String gddy = ""; //供电电源
    public String zzsx = ""; //装置属性

    public String fsgxsl = "0"; //发送光纤接口数量
    public String gxjkms = ""; //光纤接口模式
    public String hbdygn = ""; //合并单元功能
    public String jsgxsl = "0"; //接收光纤接口数量
    public String hgqlx = ""; //互感器类型
    public String znzdgn = ""; //智能终端功能
    //当保护类别选交换机时  需要显示的属性
    public String jhjlx = ""; //交换机类型
    public String jhjgn = ""; //交换机功能
    public String jhjjls = "0"; //交换机级联数
    //    private String gxjkms=""; //光纤接口模式
    public String sfrstp = "是"; //是否支持RSTP环网
    public String sfds = "是"; //是否支持1588对时
    public String sfzb = "是"; //是否支持动态组播管理
    public String sfsnmp = "是"; //是否支持SNMP网络管理
    public String sfiec = "是"; //是否支持采用IEC61850上送交换机信息

    public List<BHXHRJBB> rjbbList;
    public LTYSBXH ltysbxh;
    public ZZCJ zzcj_add;  //制造厂家
    public BHSBXHB bhsbxhb;//保护设备型号
    public boolean isAddXH = false;//是否新增型号

    public String[] xhCode;
    public String selectCode = "";
    public String[] xhBblx;


    private boolean isAddType = false;
    private boolean isMXChoose = false;//是否选母线、断路器
    public int addTypeNume;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        rjbbList = new ArrayList<>();
        util = new DaoUtil(getActivity());
        ltysbxh = null;
        zzcj_add = null;
        bhsbxhb = null;
        removeAddType(0, 0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_one_device;
    }

    @Override
    public void initview() {
        tv_sbsbdm = (EditText) getActivity().findViewById(R.id.fragment_one_device_item_one_select_bt);
        infor_select = (ListView) getActivity().findViewById(R.id.fragment_device_details_one_lv);
        dialog = new CustomDialog(getActivity(), R.style.dialog_alert_style, 0);

        //未关联到设备信息直接新增后 自动补码
        if (DeviceDetailsActivity.sfsbdm != null) {
            tv_sbsbdm.setText(DeviceDetailsActivity.sfsbdm);
        }
    }

    @Override
    public void initEvevt() {

    }

    @Override
    public void initData() {
        isAddXH = false;
        data_name = new ArrayList<>();
        if (DeviceDetailsActivity.state.equals("M") || DeviceDetailsActivity.isFromSaoma) {
            FZBHSB fzbhsb = DeviceDetailsActivity.fzbhsb;
            if (fzbhsb.getSfltysb() != null) {
                isSix = fzbhsb.getSfltysb().equals("是");
            } else {
                isSix = false;
            }
            ltybzbb = fzbhsb.getLtybzbb() + "";
            is2013 = ltybzbb.equals("2013版");

            usegddata = fzbhsb.getUsegddata();
            czlx = fzbhsb.getBdzlx();
            if (czlx == null || czlx.equals("")) {
                czlx = util.getCZCSByGLDW().getBdzlx();
            }

            if (usegddata != null && !usegddata.equals("null") && !usegddata.equals("")) {
                if (usegddata.equals("true")) {
                    usegddata = "是";
                } else {
                    usegddata = "否";
                }
            } else {
                usegddata = "是";
            }
            if (fzbhsb.getSfsbm() != null && !fzbhsb.getSfsbm().equals("null")) {
                tv_sbsbdm.setText(fzbhsb.getSfsbm());
            }
            CZCS czcs = util.getCZCSByGLDW();
            czzgdydj = czcs.getCzzgdydj() + "";
            if (DeviceDetailsActivity.isFromSaoma) {
                dwmc = czcs.getGldw();
                czmc = czcs.getCzmc();
                dddw = util.getDDDWByDWMC(dwmc).get(0).getDDDW();
            } else {
                dwmc = fzbhsb.getCzgldw();
                dddw = fzbhsb.getDddw();
                czmc = fzbhsb.getCzmc();
            }

            if (dwmc == null || dwmc.equals("")) {
                dwmc = "必填项";
            }
            if (dddw == null || dddw.equals("")) {
                dddw = "必填项";
            }
            if (czmc == null || czmc.equals("")) {
                czmc = "必填项";
            }
            ycsblx = fzbhsb.getYcsblx();
            if (ycsblx == null || ycsblx.equals("")) {
                ycsblx = "必填项";
            }
            ycsbmc = fzbhsb.getYcsbmc();
            if (ycsbmc == null || ycsbmc.equals("")) {
                ycsbmc = "必填项";
            }
            dydj = fzbhsb.getDydj() + "";
            if (dydj.equals("") || dydj.equals("")) {
                dydj = "必填项";
            }
            bhlb = fzbhsb.getFzsblx();
            if (bhlb == null || bhlb.equals("")) {
                bhlb = "必填项";
            }
            zzcj = fzbhsb.getCj();
            if (zzcj == null || zzcj.equals("")) {
                zzcj = "必填项";
            }
            bhxh = fzbhsb.getSbxh();
            if (bhxh == null || bhxh.equals("")) {
                bhxh = "必填项";
            }
            bhfl = fzbhsb.getBhfl();
            if (bhfl == null || bhfl.equals("")) {
                bhfl = "必填项";
            }
            bhlx = fzbhsb.getBhlx();
            if (bhlx == null || bhlx.equals("")) {
                bhlx = "必填项";
            }
            bhtb = fzbhsb.getTb();
            if (bhtb == null || bhtb.equals("")) {
                bhtb = "请选择";
            }
            bhmc = fzbhsb.getSbmc();
            if (bhmc == null || bhmc.equals("")) {
                bhmc = "必填项，点击生成";
            }
            if (DeviceDetailsActivity.isFromSaoma) {
                List<Object> list = (List<Object>) getActivity().getIntent().getSerializableExtra("BHXHRJBB");
                if (is2013 && isSix) {
                    ltysbxh = (LTYSBXH) list.get(0);
                    DeviceDetailsActivity.instance.ltysbxh_fz = new LTYSBXH();
                    DeviceDetailsActivity.instance.ltysbxh_fz = ltysbxh;
                } else {
                    for (Object o : list) {
                        rjbbList.add((BHXHRJBB) o);
                    }
                }
            } else {
                List<Object> list = util.getRJBBByCode(isSix, is2013, fzbhsb.getId() + "");
                if (list.size() > 0) {
                    if (isSix && is2013) {
                        ltysbxh = (LTYSBXH) list.get(0);
                        DeviceDetailsActivity.instance.ltysbxh_fz = new LTYSBXH();
                        DeviceDetailsActivity.instance.ltysbxh_fz = ltysbxh;
                    } else {
                        for (Object o : list) {
                            rjbbList.add((BHXHRJBB) o);
                        }
                    }
                }
            }

            if (bhlb.equals("收发信机")) {
                addTypeNume = 1;
                zbtdjgx = fzbhsb.getZbtdjgx();
                if (zbtdjgx == null) {
                    zbtdjgx = "请选择";
                }
                tdpl = fzbhsb.getTdpl() + "";
                if (tdpl.equals("")) {
                    tdpl = "0";
                }
                for (int i = 0; i < 7; i++) {
                    DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                    if (i == 0) {
                        if (isSix) {
                            item.setNum(2);
                            item.setName_one("是否六统一设备");
                            item.setContent_one("是");
                            item.setName_two("六统一标准版本");
                            item.setContent_two(ltybzbb);
                        } else {
                            item.setNum(1);
                            item.setName_one("是否六统一设备");
                            item.setContent_one("否");
                        }
                    } else if (i == 1) {
                        item.setNum(3);
                        item.setName_one("单位名称");
                        item.setContent_one(dwmc);
                        item.setName_two("调度单位");
                        item.setContent_two(dddw);
                        item.setName_three("厂站名称");
                        item.setContent_three(czmc);
                    } else if (i == 2) {
                        item.setNum(3);
                        item.setName_one("一次设备类型");
                        item.setContent_one(ycsblx);
                        item.setName_two("一次设备名称");
                        item.setContent_two(ycsbmc);
                        item.setName_three("电压等级");
                        item.setContent_three(dydj);
                    } else if (i == 3) {
                        item.setNum(3);
                        item.setName_one("保护类别");
                        item.setContent_one(bhlb);
                        item.setName_two("制造厂家");
                        item.setContent_two(zzcj);
                        item.setName_three("是否使用国调标准型号");
                        item.setContent_three(usegddata);
                    } else if (i == 4) {
                        item.setNum(4);
                        item.setName_one("设备型号");
                        item.setContent_one(bhxh);
                        item.setName_two("保护分类");
                        item.setContent_two(bhfl);
                        item.setName_three("保护类型");
                        item.setContent_three(bhlx);
                        item.setName_four("保护套别");
                        item.setContent_four(bhtb);
                    } else if (i == 5) {
                        item.setName_one("载波通道加工相");
                        item.setContent_one(zbtdjgx);
                        item.setName_two("通道频率");
                        item.setContent_two(tdpl);
                        item.setNum(2);
                    } else if (i == 6) {
                        item.setNum(1);
                        item.setName_one("保护名称");
                        item.setContent_one(bhmc);
                    }
                    data_name.add(item);
                }
            } else if (bhlb.equals("合并单元")) {
                addTypeNume = 2;
                dsfs = fzbhsb.getDsfs();
                if (dsfs == null || dsfs.equals("")) {
                    dsfs = "请选择";
                }
                gddy = fzbhsb.getGddy();
                if (gddy == null || gddy.equals("")) {
                    gddy = "请选择";
                }
                zzsx = fzbhsb.getZzsx();
                if (zzsx == null || zzsx.equals("")) {
                    zzsx = "请选择";
                }
                String[] ss = fzbhsb.getFsgxsl().split("#");
                if (ss.length == 1) {
                    fsgxsl = ss[0];
                    jsgxsl = "0";
                } else if (ss.length == 2) {
                    fsgxsl = ss[0];
                    jsgxsl = ss[1];
                }

                gxjkms = fzbhsb.getGxjkms();
                if (gxjkms == null || gxjkms.equals("")) {
                    gxjkms = "请选择";
                }
                hbdygn = fzbhsb.getHbdygn();
                if (hbdygn == null || hbdygn.equals("")) {
                    hbdygn = "请选择";
                }
                hgqlx = fzbhsb.getHgqlx();
                if (hgqlx == null || hgqlx.equals("")) {
                    hgqlx = "请选择";
                }
                for (int i = 0; i < 8; i++) {
                    DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                    if (i == 0) {
                        if (isSix) {
                            item.setNum(2);
                            item.setName_one("是否六统一设备");
                            item.setContent_one("是");
                            item.setName_two("六统一标准版本");
                            item.setContent_two(ltybzbb);
                        } else {
                            item.setNum(1);
                            item.setName_one("是否六统一设备");
                            item.setContent_one("否");
                        }
                    } else if (i == 1) {
                        item.setNum(3);
                        item.setName_one("单位名称");
                        item.setContent_one(dwmc);
                        item.setName_two("调度单位");
                        item.setContent_two(dddw);
                        item.setName_three("厂站名称");
                        item.setContent_three(czmc);
                    } else if (i == 2) {
                        item.setNum(3);
                        item.setName_one("一次设备类型");
                        item.setContent_one(ycsblx);
                        item.setName_two("一次设备名称");
                        item.setContent_two(ycsbmc);
                        item.setName_three("电压等级");
                        item.setContent_three(dydj);
                    } else if (i == 3) {
                        item.setNum(3);
                        item.setName_one("保护类别");
                        item.setContent_one(bhlb);
                        item.setName_two("制造厂家");
                        item.setContent_two(zzcj);
                        item.setName_three("是否使用国调标准型号");
                        item.setContent_three(usegddata);
                    } else if (i == 4) {
                        item.setNum(4);
                        item.setName_one("设备型号");
                        item.setContent_one(bhxh);
                        item.setName_two("保护分类");
                        item.setContent_two(bhfl);
                        item.setName_three("保护类型");
                        item.setContent_three(bhlx);
                        item.setName_four("保护套别");
                        item.setContent_four(bhtb);
                    } else if (i == 5) {
                        item.setNum(4);
                        item.setName_one("对时方式");
                        item.setContent_one(dsfs);
                        item.setName_two("供电电源");
                        item.setContent_two(gddy);
                        item.setName_three("装置属性");
                        item.setContent_three(zzsx);
                        item.setName_four("发送光纤接口数量");
                        item.setContent_four(fsgxsl);
                    } else if (i == 6) {
                        item.setNum(4);
                        item.setName_one("光纤接口模式");
                        item.setContent_one(gxjkms);
                        item.setName_two("合并单元功能");
                        item.setContent_two(hbdygn);
                        item.setName_three("接收光纤接口数量");
                        item.setContent_three(jsgxsl);
                        item.setName_four("互感器类型");
                        item.setContent_four(hgqlx);
                    } else if (i == 7) {
                        item.setNum(1);
                        item.setName_one("保护名称");
                        item.setContent_one(bhmc);
                    }
                    data_name.add(item);
                }
            } else if (bhlb.equals("合并单元智能终端集成")) {
                addTypeNume = 3;
                dsfs = fzbhsb.getDsfs();
                if (dsfs == null || dsfs.equals("")) {
                    dsfs = "请选择";
                }
                gddy = fzbhsb.getGddy();
                if (gddy == null || gddy.equals("")) {
                    gddy = "请选择";
                }
                zzsx = fzbhsb.getZzsx();
                if (zzsx == null || zzsx.equals("")) {
                    zzsx = "请选择";
                }
                String[] ss = fzbhsb.getFsgxsl().split("#");
                if (ss.length == 1) {
                    fsgxsl = ss[0];
                    jsgxsl = "0";
                } else if (ss.length == 2) {
                    fsgxsl = ss[0];
                    jsgxsl = ss[1];
                }
                gxjkms = fzbhsb.getGxjkms();
                if (gxjkms == null || gxjkms.equals("")) {
                    gxjkms = "请选择";
                }
                hbdygn = fzbhsb.getHbdygn();
                if (hbdygn == null || hbdygn.equals("")) {
                    hbdygn = "请选择";
                }
                hgqlx = fzbhsb.getHgqlx();
                if (hgqlx == null || hgqlx.equals("")) {
                    hgqlx = "请选择";
                }
                znzdgn = fzbhsb.getZnzdgn();
                if (znzdgn == null || znzdgn.equals("")) {
                    znzdgn = "请选择";
                }
                for (int i = 0; i < 9; i++) {
                    DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                    if (i == 0) {
                        if (isSix) {
                            item.setNum(2);
                            item.setName_one("是否六统一设备");
                            item.setContent_one("是");
                            item.setName_two("六统一标准版本");
                            item.setContent_two(ltybzbb);
                        } else {
                            item.setNum(1);
                            item.setName_one("是否六统一设备");
                            item.setContent_one("否");
                        }
                    } else if (i == 1) {
                        item.setNum(3);
                        item.setName_one("单位名称");
                        item.setContent_one(dwmc);
                        item.setName_two("调度单位");
                        item.setContent_two(dddw);
                        item.setName_three("厂站名称");
                        item.setContent_three(czmc);
                    } else if (i == 2) {
                        item.setNum(3);
                        item.setName_one("一次设备类型");
                        item.setContent_one(ycsblx);
                        item.setName_two("一次设备名称");
                        item.setContent_two(ycsbmc);
                        item.setName_three("电压等级");
                        item.setContent_three(dydj);
                    } else if (i == 3) {
                        item.setNum(3);
                        item.setName_one("保护类别");
                        item.setContent_one(bhlb);
                        item.setName_two("制造厂家");
                        item.setContent_two(zzcj);
                        item.setName_three("是否使用国调标准型号");
                        item.setContent_three(usegddata);
                    } else if (i == 4) {
                        item.setNum(4);
                        item.setName_one("设备型号");
                        item.setContent_one(bhxh);
                        item.setName_two("保护分类");
                        item.setContent_two(bhfl);
                        item.setName_three("保护类型");
                        item.setContent_three(bhlx);
                        item.setName_four("保护套别");
                        item.setContent_four(bhtb);
                    } else if (i == 5) {
                        item.setNum(4);
                        item.setName_one("对时方式");
                        item.setContent_one(dsfs);
                        item.setName_two("供电电源");
                        item.setContent_two(gddy);
                        item.setName_three("装置属性");
                        item.setContent_three(zzsx);
                        item.setName_four("发送光纤接口数量");
                        item.setContent_four(fsgxsl);
                    } else if (i == 6) {
                        item.setNum(4);
                        item.setName_one("光纤接口模式");
                        item.setContent_one(gxjkms);
                        item.setName_two("合并单元功能");
                        item.setContent_two(hbdygn);
                        item.setName_three("接收光纤接口数量");
                        item.setContent_three(jsgxsl);
                        item.setName_four("互感器类型");
                        item.setContent_four(hgqlx);
                    } else if (i == 7) {
                        item.setNum(1);
                        item.setName_one("智能终端功能");
                        item.setContent_one(znzdgn);
                    } else if (i == 8) {
                        item.setNum(1);
                        item.setName_one("保护名称");
                        item.setContent_one(bhmc);
                    }
                    data_name.add(item);
                }
            } else if (bhlb.equals("智能终端")) {
                addTypeNume = 1;
                String[] ss = fzbhsb.getFsgxsl().split("#");
                if (ss.length == 1) {
                    fsgxsl = ss[0];
                    jsgxsl = "0";
                } else if (ss.length == 2) {
                    fsgxsl = ss[0];
                    jsgxsl = ss[1];
                }
                gxjkms = fzbhsb.getGxjkms();
                if (gxjkms == null || gxjkms.equals("")) {
                    gxjkms = "请选择";
                }
                znzdgn = fzbhsb.getZnzdgn();
                if (znzdgn == null || znzdgn.equals("")) {
                    znzdgn = "请选择";
                }
                for (int i = 0; i < 7; i++) {
                    DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                    if (i == 0) {
                        if (isSix) {
                            item.setNum(2);
                            item.setName_one("是否六统一设备");
                            item.setContent_one("是");
                            item.setName_two("六统一标准版本");
                            item.setContent_two(ltybzbb);
                        } else {
                            item.setNum(1);
                            item.setName_one("是否六统一设备");
                            item.setContent_one("否");
                        }
                    } else if (i == 1) {
                        item.setNum(3);
                        item.setName_one("单位名称");
                        item.setContent_one(dwmc);
                        item.setName_two("调度单位");
                        item.setContent_two(dddw);
                        item.setName_three("厂站名称");
                        item.setContent_three(czmc);
                    } else if (i == 2) {
                        item.setNum(3);
                        item.setName_one("一次设备类型");
                        item.setContent_one(ycsblx);
                        item.setName_two("一次设备名称");
                        item.setContent_two(ycsbmc);
                        item.setName_three("电压等级");
                        item.setContent_three(dydj);
                    } else if (i == 3) {
                        item.setNum(3);
                        item.setName_one("保护类别");
                        item.setContent_one(bhlb);
                        item.setName_two("制造厂家");
                        item.setContent_two(zzcj);
                        item.setName_three("是否使用国调标准型号");
                        item.setContent_three(usegddata);
                    } else if (i == 4) {
                        item.setNum(4);
                        item.setName_one("设备型号");
                        item.setContent_one(bhxh);
                        item.setName_two("保护分类");
                        item.setContent_two(bhfl);
                        item.setName_three("保护类型");
                        item.setContent_three(bhlx);
                        item.setName_four("保护套别");
                        item.setContent_four(bhtb);
                    } else if (i == 5) {
                        item.setNum(4);
                        item.setName_one("发送光纤接口数量");
                        item.setContent_one(fsgxsl);
                        item.setName_two("光纤接口模式");
                        item.setContent_two(gxjkms);
                        item.setName_three("智能终端功能");
                        item.setContent_three(znzdgn);
                        item.setName_four("接收光纤接口数量");
                        item.setContent_four(jsgxsl);
                    } else if (i == 6) {
                        item.setNum(1);
                        item.setName_one("保护名称");
                        item.setContent_one(bhmc);
                    }
                    data_name.add(item);
                }
            } else if (bhlb.equals("交换机")) {
                addTypeNume = 3;
                jhjlx = fzbhsb.getJhjlx();
                if (jhjlx == null || jhjlx.equals("")) {
                    jhjlx = "请选择";
                }
                jhjgn = fzbhsb.getJhjgn();
                if (jhjgn == null || jhjgn.equals("")) {
                    jhjgn = "请选择";
                }
                jhjjls = fzbhsb.getJhjjls() + "";
                if (jhjjls == null || jhjjls.equals("")) {
                    jhjjls = "0";
                }
                gxjkms = fzbhsb.getGxjkms();
                if (gxjkms == null || gxjkms.equals("")) {
                    gxjkms = "请选择";
                }
                sfrstp = fzbhsb.getSfrstp();
                if (sfrstp == null || sfrstp.equals("")) {
                    sfrstp = "是";
                }
                sfds = fzbhsb.getSfds();
                if (sfds == null || sfds.equals("")) {
                    sfds = "是";
                }
                sfzb = fzbhsb.getSfzb();
                if (sfzb == null || sfzb.equals("")) {
                    sfzb = "是";
                }
                sfsnmp = fzbhsb.getSfsnmp();
                if (sfsnmp == null || sfsnmp.equals("")) {
                    sfsnmp = "是";
                }
                sfiec = fzbhsb.getSfiec();
                if (sfiec == null || sfiec.equals("")) {
                    sfiec = "是";
                }
                for (int i = 0; i < 9; i++) {
                    DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                    if (i == 0) {
                        if (isSix) {
                            item.setNum(2);
                            item.setName_one("是否六统一设备");
                            item.setContent_one("是");
                            item.setName_two("六统一标准版本");
                            item.setContent_two(ltybzbb);
                        } else {
                            item.setNum(1);
                            item.setName_one("是否六统一设备");
                            item.setContent_one("否");
                        }
                    } else if (i == 1) {
                        item.setNum(3);
                        item.setName_one("单位名称");
                        item.setContent_one(dwmc);
                        item.setName_two("调度单位");
                        item.setContent_two(dddw);
                        item.setName_three("厂站名称");
                        item.setContent_three(czmc);
                    } else if (i == 2) {
                        item.setNum(3);
                        item.setName_one("一次设备类型");
                        item.setContent_one(ycsblx);
                        item.setName_two("一次设备名称");
                        item.setContent_two(ycsbmc);
                        item.setName_three("电压等级");
                        item.setContent_three(dydj);
                    } else if (i == 3) {
                        item.setNum(3);
                        item.setName_one("保护类别");
                        item.setContent_one(bhlb);
                        item.setName_two("制造厂家");
                        item.setContent_two(zzcj);
                        item.setName_three("是否使用国调标准型号");
                        item.setContent_three(usegddata);
                    } else if (i == 4) {
                        item.setNum(4);
                        item.setName_one("设备型号");
                        item.setContent_one(bhxh);
                        item.setName_two("保护分类");
                        item.setContent_two(bhfl);
                        item.setName_three("保护类型");
                        item.setContent_three(bhlx);
                        item.setName_four("保护套别");
                        item.setContent_four(bhtb);
                    } else if (i == 5) {
                        item.setNum(4);
                        item.setName_one("交换机类型");
                        item.setContent_one(jhjlx);
                        item.setName_two("交换机功能");
                        item.setContent_two(jhjgn);
                        item.setName_three("交换机级联数");
                        item.setContent_three(jhjjls);
                        item.setName_four("光纤接口模式");
                        item.setContent_four(gxjkms);
                    } else if (i == 6) {
                        item.setName_one("是否支持RSTP环网");
                        item.setContent_one(sfrstp);
                        item.setName_two("是否支持1588对时");
                        item.setContent_two(sfds);
                        item.setName_three("是否支持动态组播管理");
                        item.setContent_three(sfzb);
                        item.setName_four("是否支持SNMP网络管理");
                        item.setContent_four(sfsnmp);
                        item.setNum(4);
                    } else if (i == 7) {
                        item.setNum(1);
                        item.setName_one("是否支持采用IEC61850上送交换机信息");
                        item.setContent_one(sfiec);
                    } else if (i == 8) {
                        item.setNum(1);
                        item.setName_one("保护名称");
                        item.setContent_one(bhmc);
                    }
                    data_name.add(item);
                }
            } else {
                addTypeNume = 0;
                for (int i = 0; i < 6; i++) {
                    DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                    if (i == 0) {
                        if (isSix) {
                            item.setNum(2);
                            item.setName_one("是否六统一设备");
                            item.setContent_one("是");
                            item.setName_two("六统一标准版本");
                            item.setContent_two(ltybzbb);
                        } else {
                            item.setNum(1);
                            item.setName_one("是否六统一设备");
                            item.setContent_one("否");
                        }
                    } else if (i == 1) {
                        item.setNum(3);
                        item.setName_one("单位名称");
                        item.setContent_one(dwmc);
                        item.setName_two("调度单位");
                        item.setContent_two(dddw);
                        item.setName_three("厂站名称");
                        item.setContent_three(czmc);
                    } else if (i == 2) {
                        item.setNum(3);
                        item.setName_one("一次设备类型");
                        item.setContent_one(ycsblx);
                        item.setName_two("一次设备名称");
                        item.setContent_two(ycsbmc);
                        item.setName_three("电压等级");
                        item.setContent_three(dydj);
                    } else if (i == 3) {
                        item.setNum(3);
                        item.setName_one("保护类别");
                        item.setContent_one(bhlb);
                        item.setName_two("制造厂家");
                        item.setContent_two(zzcj);
                        item.setName_three("是否使用国调标准型号");
                        item.setContent_three(usegddata);
                    } else if (i == 4) {
                        item.setNum(4);

                        item.setName_one("设备型号");
                        item.setContent_one(bhxh);
                        item.setName_two("保护分类");
                        item.setContent_two(bhfl);
                        item.setName_three("保护类型");
                        item.setContent_three(bhlx);
                        item.setName_four("保护套别");
                        item.setContent_four(bhtb);
                    } else if (i == 5) {
                        item.setNum(1);
                        item.setName_one("保护名称");
                        item.setContent_one(bhmc);
                    }
                    data_name.add(item);
                }
            }

            setFirstData();
            if (ltysbxh != null) {
                DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                item.setNum(3);
                item.setName_one("软件版本");
                if (ltysbxh.getRjbb() != null && !ltysbxh.getRjbb().equals("") &&
                        !ltysbxh.getRjbb().equals("null")) {
                    item.setContent_one(ltysbxh.getRjbb());
                } else {
                    item.setContent_one("请选择");
                }
                item.setName_two("生成日期");
                selectCode = ltysbxh.getCode();
                if (ltysbxh.getSCSJ() != null) {
                    item.setContent_two(TimeUtil.formatString2(ltysbxh.getSCSJ()));
                } else {
                    item.setContent_two("");
                }
                item.setName_three("ICD文件名");
                if (ltysbxh.getWjmc() != null && !ltysbxh.getWjmc().equals("")) {
                    item.setContent_three(ltysbxh.getWjmc());
                } else {
                    item.setContent_three("必填项");
                }
                data_name.add(5, item);
            } else {
                if (rjbbList != null && rjbbList.size() > 0) {
                    if (DeviceDetailsActivity.isFromSaoma) {
                        if (util.getBHXHRJBBByCode(rjbbList.get(0).getCode()) != null) {
                            BHXHRJBB rjbb = util.getBHXHRJBBByCode(rjbbList.get(0).getCode());
                            selectCode = rjbb.getBhxhcode() + "";
                            rjbbList.get(0).setBblx(rjbb.getBblx());
                        }
                    } else {
                        selectCode = rjbbList.get(0).getBhxhcode() == null ? "" : rjbbList.get(0).getBhxhcode();
                    }
                }
                if (rjbbList != null && rjbbList.size() == 1) {
                    if (rjbbList.get(0).getBblx() != null && rjbbList.get(0).getBblx().equals("非六统一，分模块")) {
                        DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                        item.setNum(3);
                        item.setName_one("模块名称");
                        item.setContent_one(rjbbList.get(0).getMkmc());
                        item.setName_two("软件版本");
                        String rjbb = "";
                        if (rjbbList.get(0).getBb() != null && !rjbbList.get(0).getBb().equals("null")) {
                            rjbb = "版本：" + rjbbList.get(0).getBb();
                        }
                        if (rjbbList.get(0).getJym() != null && !rjbbList.get(0).getJym().equals("null")) {
                            if (!rjbb.equals("")) {
                                rjbb += "，检验码：" + rjbbList.get(0).getJym();
                            } else {
                                rjbb += "版本：无，检验码：" + rjbbList.get(0).getJym();
                            }
                        } else {
                            rjbb = "请选择";
                        }
                        item.setContent_two(rjbb);
                        item.setName_three("生成日期");
                        item.setHaveAdd(true);
                        item.setContent_four("添加+");
                        if (rjbbList.get(0).getSCSJ() != null) {
                            item.setContent_three(TimeUtil.formatString2(rjbbList.get(0).getSCSJ()));
                        }
                        data_name.add(5, item);
                    } else {
                        DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                        item.setNum(2);
                        item.setName_one("软件版本");

                        String rjbb = "";
                        if (rjbbList.get(0).getBb() != null && !rjbbList.get(0).getBb().equals("null")) {
                            rjbb = "版本：" + rjbbList.get(0).getBb();
                        }
                        if (rjbbList.get(0).getJym() != null && !rjbbList.get(0).getJym().equals("null")) {
                            if (!rjbb.equals("")) {
                                rjbb += "，检验码：" + rjbbList.get(0).getJym();
                            } else {
                                rjbb += "版本：无，检验码：" + rjbbList.get(0).getJym();
                            }
                        } else {
                            rjbb = "请选择";
                        }
                        item.setContent_one(rjbb);
                        item.setName_two("生成日期");
                        if (rjbbList.get(0).getSCSJ() != null) {
                            item.setContent_two(TimeUtil.formatString2(rjbbList.get(0).getSCSJ()));
                        }
                        data_name.add(5, item);
                    }
                } else if (rjbbList != null && rjbbList.size() > 1) {
                    for (int i = 0; i < rjbbList.size(); i++) {
                        DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                        item.setNum(3);
                        item.setName_one("模块名称");
                        item.setContent_one(rjbbList.get(i).getMkmc());
                        item.setName_two("软件版本");
                        String rjbb = "";
                        if (rjbbList.get(i).getBb() != null && !rjbbList.get(i).getBb().equals("null")) {
                            rjbb = "版本：" + rjbbList.get(i).getBb();
                        }
                        if (rjbbList.get(i).getJym() != null && !rjbbList.get(i).getJym().equals("null")) {
                            if (!rjbb.equals("")) {
                                rjbb += "，检验码：" + rjbbList.get(i).getJym();
                            } else {
                                rjbb += "版本：无，检验码：" + rjbbList.get(i).getJym();
                            }
                        } else {
                            rjbb = "请选择";
                        }
                        item.setContent_two(rjbb);
                        item.setName_three("生成日期");
                        if (rjbbList.get(i).getSCSJ() != null) {
                            item.setContent_three(TimeUtil.formatString2(rjbbList.get(i).getSCSJ()));
                        }
                        if (i == 0) {
                            item.setHaveAdd(true);
                            item.setContent_four("添加+");
                        } else {
                            item.setHaveAdd(true);
                            item.setContent_four("取消-");
                        }
                        data_name.add(5 + i, item);
                    }
                } else {
                    setRJBB(4, 0);
                }
            }
        } else {
            CZCS czcs = util.getCZCSByGLDW();
            addTypeNume = 0;
            isSix = false;
            is2013 = false;
            ltybzbb = "必填项";
            czzgdydj = czcs.getCzzgdydj() + "";
            dwmc = czcs.getGldw();
            czmc = czcs.getCzmc();
            czlx = czcs.getBdzlx();
            dddw = util.getDDDWByDWMC(dwmc).get(0).getDDDW();
            if (dddw == null && dddw.equals("")) {
                dddw = "必填项";
            }
            dydj = "必填项";
            ycsblx = "必填项";
            ycsbmc = "必填项";
            bhtb = "请选择";
            bhxh = "必填项";
            bhfl = "必填项";
            bhlx = "必填项";
            bhlb = "必填项";
            zzcj = "必填项";
            usegddata = "是";
            bhmc = "必填项，点击生成";
            selectCode = "";
            for (int i = 0; i < 6; i++) {
                DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                if (i == 0) {
                    item.setNum(1);
                    item.setName_one("是否六统一设备");
                    item.setContent_one("否");
                } else if (i == 1) {
                    item.setNum(3);
                    item.setName_one("单位名称");
                    item.setContent_one(dwmc);
                    item.setName_two("调度单位");
                    item.setContent_two(dddw);
                    item.setName_three("厂站名称");
                    item.setContent_three(czmc);
                } else if (i == 2) {
                    item.setNum(3);
                    item.setName_one("一次设备类型");
                    item.setContent_one(ycsblx);
                    item.setName_two("一次设备名称");
                    item.setContent_two(ycsbmc);
                    item.setName_three("电压等级");
                    item.setContent_three(dydj);
                } else if (i == 3) {
                    item.setNum(3);
                    item.setName_one("保护类别");
                    item.setContent_one(bhlb);
                    item.setName_two("制造厂家");
                    item.setContent_two(zzcj);
                    item.setName_three("是否使用国调标准型号");
                    item.setContent_three(usegddata);
                } else if (i == 4) {
                    item.setNum(4);
                    item.setName_one("设备型号");
                    item.setContent_one(bhxh);
                    item.setName_two("保护分类");
                    item.setContent_two(bhfl);
                    item.setName_three("保护类型");
                    item.setContent_three(bhlx);
                    item.setName_four("保护套别");
                    item.setContent_four(bhtb);
                } else if (i == 5) {
                    item.setNum(1);
                    item.setName_one("保护名称");
                    item.setContent_one(bhmc);
                }
                data_name.add(item);
            }
        }
        adapter = new FZDeviceOneAdapter(getActivity(), data_name);
        if (rjbbList.size() > 0) {
            adapter.add_rjbb_count = rjbbList.size();
        }
        infor_select.setAdapter(adapter);
    }

    @Override
    public void initReceiver(boolean isEdit) {

    }

    /**
     * 弹出下拉列表框
     *
     * @param currentPosition 当前item的位置
     * @param num
     * @param item
     */

    public void showDialog(final int currentPosition, final int num, final List<String> item) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_one_device_select_item, null);
        //根据id在布局中找到控件对象
        TextView tv_dialog_title_one = (TextView) view.findViewById(R.id.fragment_device_details_select_item_cancel);
        final EditText app_search_edit = (EditText) view.findViewById(R.id.app_search_edit);
        final ListView lv_dialog = (ListView) view.findViewById(R.id.fragment_device_details_select_item__lv);
        final EditText et_dialog = (EditText) view.findViewById(R.id.app_search_edit);

        Button btn_search = (Button) view.findViewById(R.id.btn_search);
        btn_search.setVisibility(View.GONE);
        Button btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_add.setVisibility(View.GONE);
        final DeviceSelectAdapter devAdapter = new DeviceSelectAdapter(getActivity(), item);

        view.findViewById(R.id.fragment_device_details_select_item_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (app_search_edit.getText().toString().trim().equals("")) {
                    ToastUtils.showToast(getActivity(), "请输入查询内容");
                } else {
                    for (int i = 0; i < item.size(); i++) {
                        if (item.get(i).contains(app_search_edit.getText().toString().trim())) {
                            lv_dialog.setSelection(i);
                            break;
                        }
                    }
                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = app_search_edit.getText().toString().trim();
                if (content.equals("")) {
                    ToastUtils.showToast(getActivity(), "请输入添加内容");
                    return;
                }
                if (data_name.get(num).getName_two() != null &&
                        data_name.get(num).getName_two().equals("一次设备名称")) {
                    data_name.get(num).setContent_two(content);
                    ycsbmc = content;
                    dialog.dismiss();
                    setDydj(ycsblx, ycsbmc.split(",")[0]);
                    data_name.get(data_name.size() - 1).setContent_one("");
                    bhmc = "";
                    data_name.get(num).setContent_three(dydj);
                    adapter.notifyDataSetChanged();
                    isMXChoose = true;
                    return;
                }
//                if (data_name.get(num).getName_three() != null &&
//                        data_name.get(num).getName_three().equals("ICD文件名")) {
//                    if (!content.equals(data_name.get(num).getContent_three()) && ltysbxh != null) {
//                        ltysbxh = null;
//                        ltysbxh = new LTYSBXH();
//                        ltysbxh.setZzcj(zzcj);
//                        ltysbxh.setBhlb(bhlb);
//                        ltysbxh.setBhxh(bhxh);
//                        ltysbxh.setED_TAG("C");
//                        if (usegddata.equals("是"))
//                            ltysbxh.setSfqy("Y");
//                        ltysbxh.setBblx("六统一设备");
//                        ltysbxh.setState("C");
//                        ltysbxh.setSysblx("FZSB");
//                    }
//                    return;
//                }

                if (data_name.get(num).getName_two() != null && data_name.get(num).getName_two().equals("一次设备名称")
                        || data_name.get(num).getName_two() != null && data_name.get(num).getName_two().equals("制造厂家")
                        || data_name.get(num).getName_two() != null && data_name.get(num).getName_two().equals("保护分类")
                        || data_name.get(num).getName_three() != null && data_name.get(num).getName_three().equals("保护类型")
                        || data_name.get(num).getName_two() != null && data_name.get(num).getName_two().equals("软件版本")
                        || data_name.get(num).getName_one() != null && data_name.get(num).getName_one().equals("模块名称")
                        || data_name.get(num).getName_one() != null && data_name.get(num).getName_one().equals("软件版本")
                        || data_name.get(num).getName_one() != null && data_name.get(num).getName_one().equals("保护名称")
                        || data_name.get(num).getName_one() != null && data_name.get(num).getName_one().equals("设备型号")) {
                    if (data_name.get(num).getName_two() != null && data_name.get(num).getName_two().equals("制造厂家") && currentPosition == 2) {
                        for (String s : item) {
                            if (s.equals(content)) {
                                ToastUtils.showToast(getActivity(), "该制造厂家已存在，请不要重复添加");
                                return;
                            }
                        }
                        if (zzcj_add == null) {
                            zzcj_add = new ZZCJ();
                        }
                        zzcj_add.setMC(content);
                        item.clear();
                        item.add(content);
                        devAdapter.notifyDataSetChanged();
                    } else if (data_name.get(num).getName_one() != null && data_name.get(num).getName_one().equals("设备型号") && currentPosition == 1) {
                        for (String s : item) {
                            if (s.equals(content)) {
                                ToastUtils.showToast(getActivity(), "该型号已存在，请不要重复添加");
                                return;
                            }
                        }
                        isAddXH = true;
                        item.clear();
                        if (isSix && is2013) {
                            item.add(content);
                        } else {
                            item.add("不分模块：" + content);
                            item.add("分模块：" + content);
                        }
                        selectCode = "";
                        xhCode = null;
                        devAdapter.notifyDataSetChanged();
                    } else if (data_name.get(num).getName_one() != null && data_name.get(num).getName_one().equals("保护名称") && currentPosition == 1) {
                        bhmc = content;
                        data_name.get(num).setContent_one(bhmc);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                        return;
                    } else if (data_name.get(num).getName_two() != null && data_name.get(num).getName_two().equals("软件版本") && currentPosition == 2) {
                        String[] ss = content.replace(",", "，").split("，");
                        item.clear();
                        if (content.contains("版本：")) {
                            if (content.contains("检验码：")) {
                                item.add(content.replace(",", "，"));
                            } else {
                                item.add(content.replace(",", "，") + "，检验码：无");
                            }
                        } else {
                            if (ss.length == 1) {
                                item.add("版本：" + ss[0] + "，检验码：无");
                            } else if (ss.length == 2) {
                                item.add("版本：" + ss[0] + "，检验码：" + ss[1]);
                            }
                        }
                        devAdapter.notifyDataSetChanged();
                    } else if (data_name.get(num).getName_one() != null && data_name.get(num).getName_one().equals("软件版本") && currentPosition == 1) {
                        String[] ss = content.replace(",", "，").split("，");
                        item.clear();
                        if (content.contains("版本：")) {
                            if (content.contains("检验码：")) {
                                item.add(content.replace(",", "，"));
                            } else {
                                item.add(content.replace(",", "，") + "，检验码：无");
                            }
                        } else {
                            if (ss.length == 1) {
                                item.add("版本：" + ss[0] + "，检验码：无");
                            } else if (ss.length == 2) {
                                item.add("版本：" + ss[0] + "，检验码：" + ss[1]);
                            }
                        }
                        devAdapter.notifyDataSetChanged();
                    } else {
                        item.clear();
                        item.add(content);
                        devAdapter.notifyDataSetChanged();
                    }
                } else {
                    item.clear();
                    if (AppUtils.isNUmeric(content)) {
                        content = Integer.parseInt(content) + "";
                    }
                    item.add(content);
                    devAdapter.notifyDataSetChanged();
                }
            }
        });
        if (item.size() > 0) {
            btn_search.setVisibility(View.VISIBLE);
        }

        if (currentPosition == 1) {
            if (data_name.get(num).getContent_one() != null &&
                    !data_name.get(num).getContent_one().equals("null") &&
                    !data_name.get(num).getContent_one().equals("") &&
                    !data_name.get(num).getContent_one().equals("必填项") &&
                    !data_name.get(num).getContent_one().equals("请选择")) {
                app_search_edit.setText(data_name.get(num).getContent_one());
            }
            if (data_name.get(num).getName_one().equals("软件版本")
                    || data_name.get(num).getName_one().equals("模块名称")
                    || data_name.get(num).getName_one().equals("发送光纤接口数量")
                    || data_name.get(num).getName_one().equals("保护名称")
                    || data_name.get(num).getName_one().equals("设备型号")) {
                btn_add.setVisibility(View.VISIBLE);
                if (data_name.get(num).getName_one().equals("发送光纤接口数量")) {
                    app_search_edit.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    app_search_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                }
                if (data_name.get(num).getName_one().equals("软件版本")) {
                    app_search_edit.setHint("请输入版本和检验码，以逗号隔开");
                }
            }
        } else if (currentPosition == 2) {
            if (data_name.get(num).getContent_two() != null &&
                    !data_name.get(num).getContent_two().equals("null") &&
                    !data_name.get(num).getContent_two().equals("") &&
                    !data_name.get(num).getContent_two().equals("必填项") &&
                    !data_name.get(num).getContent_two().equals("请选择")) {
                app_search_edit.setText(data_name.get(num).getContent_two());
            }
            if (data_name.get(num).getName_two().equals("一次设备名称")
                    || data_name.get(num).getName_two().equals("软件版本")
                    || data_name.get(num).getName_two().equals("保护分类")
                    || data_name.get(num).getName_two().equals("制造厂家")) {
//                if (ycsblx.equals("其他") || data_name.get(num).getName_two().equals("保护分类") && !isAddXH) {
                if (!isAddXH && data_name.get(num).getName_two().equals("保护分类")) {
                    btn_add.setVisibility(View.GONE);
                } else {
                    btn_add.setVisibility(View.VISIBLE);
                }
                app_search_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
                if (data_name.get(num).getName_two().equals("一次设备名称")) {
                    btn_add.setText("添加");
                    app_search_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(200)});
                }
                if (data_name.get(num).getName_two().equals("保护分类")) {
                    app_search_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
                }
                if (data_name.get(num).getName_two().equals("软件版本")) {
                    app_search_edit.setHint("请输入版本和检验码，以逗号隔开");
                }
            } else if (data_name.get(num).getName_two().equals("接收光纤接口数量")
                    || data_name.get(num).getName_two().equals("发送光纤接口数量")
                    || data_name.get(num).getName_two().equals("通道频率")) {
                btn_add.setVisibility(View.VISIBLE);
                app_search_edit.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                app_search_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
            }
        } else if (currentPosition == 3) {
            if (data_name.get(num).getContent_three() != null &&
                    !data_name.get(num).getContent_three().equals("null") &&
                    !data_name.get(num).getContent_three().equals("") &&
                    !data_name.get(num).getContent_three().equals("必填项") &&
                    !data_name.get(num).getContent_three().equals("请选择")) {
                app_search_edit.setText(data_name.get(num).getContent_three());
            }
            if (data_name.get(num).getName_three().equals("交换机级联数")
                    || data_name.get(num).getName_three().equals("接收光纤接口数量")) {
                btn_add.setVisibility(View.VISIBLE);
                app_search_edit.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                app_search_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
            }
            if (data_name.get(num).getName_three().equals("ICD文件名") ||
                    data_name.get(num).getName_three().equals("保护类型") && isAddXH) {
                btn_add.setVisibility(View.VISIBLE);
                app_search_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
            }
        } else if (currentPosition == 4) {
            if (data_name.get(num).getContent_four() != null &&
                    !data_name.get(num).getContent_four().equals("null") &&
                    !data_name.get(num).getContent_four().equals("") &&
                    !data_name.get(num).getContent_four().equals("必填项") &&
                    !data_name.get(num).getContent_four().equals("请选择")) {
                app_search_edit.setText(data_name.get(num).getContent_four());
            }
            if (data_name.get(num).getName_four().equals("发送光纤接口数量")
                    || data_name.get(num).getName_four().equals("接收光纤接口数量")) {
                btn_add.setVisibility(View.VISIBLE);
                app_search_edit.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                app_search_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
            }
        }

        lv_dialog.setAdapter(devAdapter);
        if (!app_search_edit.getText().toString().trim().equals("")) {
            if (item.size() > 0) {
                for (int i = 0; i < item.size(); i++) {
                    if (item.get(i).contains(app_search_edit.getText().toString().trim())) {
                        lv_dialog.setSelection(i);
                    }
                }
            }
        }

        lv_dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (num == 0) {//是否六统一逻辑判断
                    if (currentPosition == 1) {
                        if (item.get(position).equals("是") &&
                                !data_name.get(num).getContent_one().equals(item.get(position))) {
                            data_name.get(num).setContent_two("");
                            data_name.get(num).setName_two("六统一标准版本");
                            data_name.get(num).setNum(2);
                            isSix = true;
                        } else if (item.get(position).equals("否") &&
                                !data_name.get(num).getContent_one().equals(item.get(position))) {
                            if (ltybzbb != null) {
                                isSix = false;
                                is2013 = false;
                                data_name.get(num).setNum(1);
                                if (ltybzbb.equals("2013版")) {
                                    bhxh = "";
                                    data_name.get(4).setContent_one("");
                                }
                                ltybzbb = "";
                            }
                        }
                        data_name.get(num).setContent_one(item.get(position));
                    } else if (currentPosition == 2) {
                        ltybzbb = item.get(position);
                        if (ltybzbb.equals("2013版")) {
                            if (data_name.get(num).getContent_two() != null) {
                                if (!data_name.get(num).getContent_two().equals(item.get(position))) {
                                    data_name.get(num + 4).setContent_one("");
                                    bhxh = "";
                                }
                            } else {
                                data_name.get(num + 4).setContent_one("");
                                bhxh = "";
                            }
                            is2013 = true;
                        } else {
                            if (data_name.get(num).getContent_two() != null) {
                                if (!data_name.get(num).getContent_two().equals(item.get(position))
                                        && data_name.get(num).getContent_two().equals("2013版")) {
                                    data_name.get(num + 4).setContent_one("");
                                    bhxh = "";
                                }
                            }
                            is2013 = false;
                        }
                        data_name.get(num).setContent_two(ltybzbb);
                    }
                } else if (num == 1) {//单位、调度单位、厂站逻辑代码
                    if (currentPosition == 1) {
                        if (data_name.get(num).getContent_one() != null) {
                            if (!data_name.get(num).getContent_one().equals(item.get(position)) &&
                                    !data_name.get(num).getContent_two().equals("")) {
                                data_name.get(num).setContent_two("");
                                dddw = "";
                            }
                        }
                        data_name.get(num).setContent_one(item.get(position));
                        dwmc = item.get(position);
                    } else if (currentPosition == 2) {
                        data_name.get(num).setContent_two(item.get(position));
                        dddw = item.get(position);
                    } else if (currentPosition == 3) {
                        if (data_name.get(num).getContent_three() != null) {
                            if (!data_name.get(num).getContent_three().equals(item.get(position)) &&
                                    !data_name.get(num + 1).getContent_two().equals("")) {
                                data_name.get(num + 1).setContent_two("");
                                ycsbmc = "";
                            }
                        }
                        if (data_name.get(num).getContent_three() != null) {
                            if (!data_name.get(num).getContent_three().equals(item.get(position)) &&
                                    !data_name.get(num + 1).getContent_three().equals("")) {
                                data_name.get(num + 1).setContent_three("");
                                dydj = "";
                            }
                        }
                        if (data_name.get(num).getContent_three() != null) {
                            if (!data_name.get(num).getContent_three().equals(item.get(position)) &&
                                    !data_name.get(data_name.size() - 1).getContent_one().equals("")) {
                                data_name.get(data_name.size() - 1).setContent_one("");
                                bhmc = "";
                            }
                        }
                        data_name.get(num).setContent_three(item.get(position));
                        czmc = item.get(position);
                    }
                } else if (num == 2) { //一次设备类型、一次设备名称、电压等级逻辑处理
                    if (currentPosition == 1) {
                        if (data_name.get(num).getContent_one() != null &&
                                data_name.get(num).getContent_two() != null) {
                            if (!data_name.get(num).getContent_one().equals(item.get(position)) &&
                                    !data_name.get(num).getContent_two().equals("")) {
                                data_name.get(num).setContent_two("");
                                ycsbmc = "";
                            }
                        }
                        data_name.get(num).setContent_one(item.get(position));
                        ycsblx = item.get(position);
                        if (ycsblx.equals("其他")) {
                            ycsbmc = "其他";
                            data_name.get(num).setContent_two(ycsbmc);
                            setDydj(ycsblx, ycsbmc);
                            data_name.get(num).setContent_three(dydj);
                        }
                    } else if (currentPosition == 2) {
                        if (data_name.get(num).getContent_two() != null &&
                                data_name.get(data_name.size() - 1).getContent_one() != null) {
                            if (!data_name.get(num).getContent_two().equals(item.get(position)) &&
                                    !data_name.get(data_name.size() - 1).getContent_one().equals("")) {
                                data_name.get(data_name.size() - 1).setContent_one("");
                                bhmc = "";
                            }
                        }
                        if (ycsblx.equals("母线") || ycsblx.equals("断路器")) {
                            String str = app_search_edit.getText().toString().trim();
                            if (isMXChoose) {
                                app_search_edit.setText("");
                                app_search_edit.setText(item.get(position));
                                isMXChoose = false;
                            } else {
                                for (String s : str.split(",")) {
                                    if (s.equals(item.get(position))) {
                                        ToastUtils.showToast(getActivity(), "请不要重复选择...");
                                        return;
                                    }
                                }
                                if (str.equals("")) {
                                    app_search_edit.setText(item.get(position));
                                } else {
                                    app_search_edit.setText(str + "," + item.get(position));
                                }
                            }
                            return;
                        } else {
                            data_name.get(num).setContent_two(item.get(position));
                            ycsbmc = item.get(position);
                        }
                        setDydj(ycsblx, item.get(position));
                        data_name.get(num).setContent_three(dydj);
                    } else if (currentPosition == 3) {
                        if (data_name.get(num).getContent_three() != null &&
                                data_name.get(data_name.size() - 1).getContent_one() != null) {
                            if (!data_name.get(num).getContent_three().equals(item.get(position)) &&
                                    !data_name.get(data_name.size() - 1).getContent_one().equals("")) {
                                data_name.get(data_name.size() - 1).setContent_one("");
                                bhmc = "";
                            }
                        }
                        data_name.get(num).setContent_three(item.get(position));
                        dydj = item.get(position);
                        int dy = Integer.parseInt(dydj);
                        if (czlx.equals("智能站") && util.getCZCSByGLDW().getCzzgdydj() >= 110
                                || dy >= 220) {
                            data_name.get(num + 1).setContent_three("是");
                            usegddata = "是";
//                            bhxh = "";
//                            data_name.get(num + 2).setContent_one(bhxh);
                        }
                        if (bhxh != null && !bhxh.equals("") && !bhxh.equals("必填项")) {
                            List<Object> bhxhList = util.getBHXH(isSix, is2013, zzcj, bhlb, czlx, dydj, usegddata);
                            boolean isClear = true;
                            if (isSix && is2013) {
                                for (Object o : bhxhList) {
                                    LTYSBXH lty = (LTYSBXH) o;
                                    if (bhxh.equals(lty.getBhxh())) {
                                        isClear = false;
                                        break;
                                    }
                                }
                            } else {
                                for (Object o : bhxhList) {
                                    BHSBXHB lty = (BHSBXHB) o;
                                    if (bhxh.equals(lty.getSbxh())) {
                                        isClear = false;
                                        break;
                                    }
                                }
                            }
                            if (isClear) {
                                bhxh = "";
                                data_name.get(num + 2).setContent_one(bhxh);
                            }
                        }
                    }
                } else if (num == 3) {
                    if (currentPosition == 1) {
                        if (data_name.get(num).getContent_one() != null) {
                            if (!data_name.get(num).getContent_one().equals(item.get(position))) {
                                bhmc = "";
                                bhxh = "";
                                data_name.get(num + 1).setContent_one(bhxh);
                                setBHLXBH(num, item.get(position));
                                data_name.get(num).setContent_one(item.get(position));
                                bhlb = item.get(position);
                            }
                        }
                    } else if (currentPosition == 2) {
                        if (data_name.get(num).getContent_two() != null) {
                            if (!data_name.get(num).getContent_two().equals(item.get(position))) {
                                bhxh = "";
                                data_name.get(num + 1).setContent_one("");
                            }
                        }
                        data_name.get(num).setContent_two(item.get(position));
                        zzcj = item.get(position);
                    } else if (currentPosition == 3) {
                        if (!data_name.get(num).getContent_three().equals(item.get(position))) {
                            bhxh = "";
                            data_name.get(num + 1).setContent_one("");
                        }
                        usegddata = item.get(position);
                        data_name.get(num).setContent_three(usegddata);
                    }
                } else if (num == 4)

                {//保护型号、保护分类、保护类型、保护套别
                    if (currentPosition == 1) {
                        if (data_name.get(num).getContent_one() != null &&
                                !data_name.get(num).getContent_one().equals(item.get(position))) {
                            data_name.get(data_name.size() - 1).setContent_one("");
                            bhmc = "";
                            data_name.get(num).setContent_two("");
                            data_name.get(num).setContent_three("");
                            bhfl = "";
                            bhlx = "";
                        }

                        if (xhCode != null && xhCode.length > 0) {
                            selectCode = xhCode[position];
                        }
                        if (selectCode != null && !selectCode.equals("")) {
                            isAddXH = false;
                        }

                        if (isAddXH) {
                            String[] ss = item.get(position).split("：");
                            if (isSix && is2013) {
                                bhxh = item.get(position);
                                data_name.get(num).setContent_one(bhxh);
                                addRJBB(false, num);
                            } else {
                                if (bhsbxhb == null) {
                                    bhsbxhb = new BHSBXHB();
                                }
                                if (ss.length == 1) {
                                    bhxh = item.get(position);
                                    data_name.get(num).setContent_one(item.get(position));

                                    addRJBB(false, num);
                                } else {
                                    bhxh = ss[1];
                                    data_name.get(num).setContent_one(bhxh);
                                    if (ss[0].equals("分模块")) {
                                        bhsbxhb.setBblx("非六统一，分模块");
                                        addRJBB(true, num);
                                    } else {
                                        bhsbxhb.setBblx("非六统一，不分模块");
                                        addRJBB(false, num);
                                    }
                                }
                            }
                        } else {
                            isAddXH = false;
                            bhxh = item.get(position);
                            if (data_name.get(num).getContent_one() != null &&
                                    data_name.get(num).getContent_one().equals(bhxh)) {
                                data_name.get(num).setContent_one(bhxh);
                            } else {
                                //设置软件版本
                                setRJBB(num, position);
                                setBHFL(num);
                                data_name.get(num).setContent_one(bhxh);
                            }
                        }
                    } else if (currentPosition == 2) {
                        data_name.get(num).setContent_two(item.get(position));
                        bhfl = item.get(position);
                        if (ltysbxh != null) {
                            ltysbxh.setBhfl(bhfl);
                        }
                        if (bhsbxhb != null) {
                            bhsbxhb.setBhfl(bhfl);
                        }
                    } else if (currentPosition == 3) {
                        data_name.get(num).setContent_three(item.get(position));
                        bhlx = item.get(position);
                        if (ltysbxh != null) {
                            ltysbxh.setBhlx(bhlx);
                        }
                        if (bhsbxhb != null) {
                            bhsbxhb.setBhlx(bhlx);
                        }
                    } else if (currentPosition == 4) {
                        if (data_name.get(num).getContent_four() != null &&
                                data_name.get(data_name.size() - 1).getContent_one() != null) {
                            if (!data_name.get(num).getContent_four().equals(item.get(position)) &&
                                    !data_name.get(data_name.size() - 1).getContent_one().equals("")) {
                                data_name.get(data_name.size() - 1).setContent_one("");
                                bhmc = "";
                            }
                        }
                        data_name.get(num).setContent_four(item.get(position));
                        bhtb = item.get(position);
                    }
                } else { //根据保护类别不同会带出部分信息的情况处理
                    if (addTypeNume == 0) {
                        if (currentPosition == 1) {
                            if (data_name.get(num).getName_one().equals("保护名称")) {
                                bhmc = item.get(position);
                            } else {
                                if (ltysbxh != null) {
                                    String[] ss = item.get(position).split("，");
                                    if (ss.length == 1) {
                                        ltysbxh.setRjbb(ss[0]);
                                    } else if (ss.length == 2) {
                                        ltysbxh.setRjbb(ss[0].split("：")[1] + " " + ss[1].split("：")[1]);
                                    }
                                    if (!data_name.get(num).getContent_three().equals("") &&
                                            !data_name.get(num).getContent_three().equals("必填项") &&
                                            !data_name.get(num).getContent_one().equals(item.get(position))) {
                                        ltysbxh.setWjmc("");
                                        data_name.get(num).setContent_three("必填项");
                                    }
                                } else if (rjbbList.size() > 0) {
                                    if (rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                            + num - data_name.size() + 1).getBblx().equals("非六统一，分模块")) {
                                        if (rjbbList.get(adapter.add_rjbb_count + addTypeNume + num
                                                - data_name.size() + 1).getMkmc() != null
                                                && !rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).getMkmc().equals(item.get(position))) {
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setMkmc(item.get(position));
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setBb("");
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setJym("");

                                            data_name.get(num).setContent_two("");
                                        } else {
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setMkmc(item.get(position));
                                        }
                                    } else {
                                        String[] ss = item.get(position).split("，");
                                        if (ss.length == 2) {
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setBb(ss[0].split("：")[1]);
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setJym(ss[1].split("：")[1]);
                                        }
                                    }
                                }
                            }
                            data_name.get(num).setContent_one(item.get(position));
                        } else if (currentPosition == 2) {
                            data_name.get(num).setContent_two(item.get(position));
                            String[] ss = item.get(position).split("，");
                            if (ss.length == 1) {
                                if (ss[0].contains("：")) {
                                    rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                            + num - data_name.size() + 1).setBb(ss[0].split("：")[1]);
                                } else {
                                    rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                            + num - data_name.size() + 1).setBb(ss[0]);
                                }
                            } else if (ss.length == 2) {
                                if (ss[0].contains("：") && ss[1].contains("：")) {
                                    rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                            + num - data_name.size() + 1).setBb(ss[0].split("：")[1]);
                                    rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                            + num - data_name.size() + 1).setJym(ss[1].split("：")[1]);
                                } else {
                                    rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                            + num - data_name.size() + 1).setBb(ss[0]);
                                    rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                            + num - data_name.size() + 1).setJym(ss[1]);
                                }
                            }
                        } else if (currentPosition == 3) {
                            if (ltysbxh != null && !data_name.get(num).getContent_three()
                                    .equals(item.get(position))) {
                                data_name.get(num).setContent_three(item.get(position));

                                setLtysbxh(item.get(position));
                            }
                        }
                    } else if (addTypeNume == 1) {//收发信机、智能终端
                        if (num == data_name.size() - 2) {
                            if (bhlb.equals("收发信机")) {//收发信机
                                if (currentPosition == 1) {
                                    data_name.get(num).setContent_one(item.get(position));
                                    zbtdjgx = item.get(position);
                                } else if (currentPosition == 2) {
                                    data_name.get(num).setContent_two(item.get(position));
                                    tdpl = item.get(position);
                                }
                            } else {//智能终端
                                if (currentPosition == 1) {
                                    data_name.get(num).setContent_one(item.get(position));
                                    fsgxsl = item.get(position);
                                } else if (currentPosition == 2) {
                                    data_name.get(num).setContent_two(item.get(position));
                                    gxjkms = item.get(position);
                                } else if (currentPosition == 3) {
                                    data_name.get(num).setContent_three(item.get(position));
                                    znzdgn = item.get(position);
                                } else if (currentPosition == 4) {
                                    data_name.get(num).setContent_four(item.get(position));
                                    jsgxsl = item.get(position);
                                }
                            }
                        } else {
                            if (currentPosition == 1) {
                                if (ltysbxh != null) {
                                    String[] ss = item.get(position).split("，");
                                    if (ss.length == 1) {
                                        ltysbxh.setRjbb(ss[0]);
                                    } else if (ss.length == 2) {
                                        ltysbxh.setRjbb(ss[0].split("：")[1] + " " + ss[1].split("：")[1]);
                                    }
                                    if (!data_name.get(num).getContent_three().equals("") &&
                                            !data_name.get(num).getContent_three().equals("必填项") &&
                                            !data_name.get(num).getContent_one().equals(item.get(position))) {
                                        ltysbxh.setWjmc("");
                                        data_name.get(num).setContent_three("必填项");
                                    }
                                } else if (rjbbList.size() > 0) {
                                    if (rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                            + num - data_name.size() + 1).getBblx().equals("非六统一，分模块")) {
                                        if (rjbbList.get(adapter.add_rjbb_count + addTypeNume + num
                                                - data_name.size() + 1).getMkmc() != null
                                                && !rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).getMkmc().equals(item.get(position))) {
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setMkmc(item.get(position));
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setBb("");
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setJym("");
                                            data_name.get(num).setContent_two("");
                                        } else {
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setMkmc(item.get(position));
                                        }
                                    } else {
                                        String[] ss = item.get(position).split("，");
                                        if (ss.length == 2) {
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setBb(ss[0].split("：")[1]);
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setJym(ss[1].split("：")[1]);
                                        }
                                    }
                                }
                                data_name.get(num).setContent_one(item.get(position));
                            } else if (currentPosition == 2) {
                                data_name.get(num).setContent_two(item.get(position));
                                String[] ss = item.get(position).split("，");
                                if (ss.length == 1) {
                                    if (ss[0].contains("：")) {
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setBb(ss[0].split("：")[1]);
                                    } else {
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setBb(ss[0]);
                                    }
                                } else if (ss.length == 2) {
                                    if (ss[0].contains("：") && ss[1].contains("：")) {
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setBb(ss[0].split("：")[1]);
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setJym(ss[1].split("：")[1]);
                                    } else {
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setBb(ss[0]);
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setJym(ss[1]);
                                    }
                                }
                            } else if (currentPosition == 3) {
                                if (ltysbxh != null && !data_name.get(num).getContent_three()
                                        .equals(item.get(position))) {
                                    data_name.get(num).setContent_three(item.get(position));
                                    setLtysbxh(item.get(position));
                                }
                            }
                        }
                    } else if (addTypeNume == 2) {//合并单元
                        if (num == data_name.size() - 3) {
                            if (currentPosition == 1) {
                                data_name.get(num).setContent_one(item.get(position));
                                dsfs = item.get(position);
                            } else if (currentPosition == 2) {
                                data_name.get(num).setContent_two(item.get(position));
                                gddy = item.get(position);
                            } else if (currentPosition == 3) {
                                data_name.get(num).setContent_three(item.get(position));
                                zzsx = item.get(position);
                            } else if (currentPosition == 4) {
                                data_name.get(num).setContent_four(item.get(position));
                                fsgxsl = item.get(position);
                            }
                        } else if (num == data_name.size() - 2) {
                            if (currentPosition == 1) {
                                data_name.get(num).setContent_one(item.get(position));
                                gxjkms = item.get(position);
                            } else if (currentPosition == 2) {
                                data_name.get(num).setContent_two(item.get(position));
                                hbdygn = item.get(position);
                            } else if (currentPosition == 3) {
                                data_name.get(num).setContent_three(item.get(position));
                                jsgxsl = item.get(position);
                            } else if (currentPosition == 4) {
                                data_name.get(num).setContent_four(item.get(position));
                                hgqlx = item.get(position);
                            }
                        } else {
                            if (currentPosition == 1) {
                                if (ltysbxh != null) {
                                    String[] ss = item.get(position).split("，");
                                    if (ss.length == 1) {
                                        ltysbxh.setRjbb(ss[0]);
                                    } else if (ss.length == 2) {
                                        ltysbxh.setRjbb(ss[0].split("：")[1] + " " + ss[1].split("：")[1]);
                                    }
                                    if (!data_name.get(num).getContent_three().equals("") &&
                                            !data_name.get(num).getContent_three().equals("必填项") &&
                                            !data_name.get(num).getContent_one().equals(item.get(position))) {
                                        ltysbxh.setWjmc("");
                                        data_name.get(num).setContent_three("必填项");
                                    }
                                } else if (rjbbList.size() > 0) {
                                    if (rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                            + num - data_name.size() + 1).getBblx().equals("非六统一，分模块")) {
                                        if (rjbbList.get(adapter.add_rjbb_count + addTypeNume + num
                                                - data_name.size() + 1).getMkmc() != null
                                                && !rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).getMkmc().equals(item.get(position))) {
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setMkmc(item.get(position));
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setBb("");
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setJym("");
                                            data_name.get(num).setContent_two("");
                                        } else {
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setMkmc(item.get(position));
                                        }
                                    } else {
                                        String[] ss = item.get(position).split("，");
                                        if (ss.length == 2) {
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setBb(ss[0].split("：")[1]);
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setJym(ss[1].split("：")[1]);
                                        }
                                    }
                                }
                                data_name.get(num).setContent_one(item.get(position));
                            } else if (currentPosition == 2) {
                                data_name.get(num).setContent_two(item.get(position));
                                String[] ss = item.get(position).split("，");
                                if (ss.length == 1) {
                                    if (ss[0].contains("：")) {
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setBb(ss[0].split("：")[1]);
                                    } else {
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setBb(ss[0]);
                                    }
                                } else if (ss.length == 2) {
                                    if (ss[0].contains("：") && ss[1].contains("：")) {
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setBb(ss[0].split("：")[1]);
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setJym(ss[1].split("：")[1]);
                                    } else {
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setBb(ss[0]);
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setJym(ss[1]);
                                    }
                                }
                            } else if (currentPosition == 3) {
                                if (ltysbxh != null && !data_name.get(num).getContent_three()
                                        .equals(item.get(position))) {
                                    data_name.get(num).setContent_three(item.get(position));
                                    setLtysbxh(item.get(position));
                                }
                            }
                        }
                    } else if (addTypeNume == 3) {//交换机类型、合并单元智能终端集成
                        if (num == data_name.size() - 4) {
                            if (bhlb.equals("交换机")) {
                                if (currentPosition == 1) {
                                    data_name.get(num).setContent_one(item.get(position));
                                    jhjlx = item.get(position);
                                } else if (currentPosition == 2) {
                                    data_name.get(num).setContent_two(item.get(position));
                                    jhjgn = item.get(position);
                                } else if (currentPosition == 3) {
                                    data_name.get(num).setContent_three(item.get(position));
                                    jhjjls = item.get(position);
                                } else if (currentPosition == 4) {
                                    data_name.get(num).setContent_four(item.get(position));
                                    gxjkms = item.get(position);
                                }
                            } else {
                                if (currentPosition == 1) {
                                    data_name.get(num).setContent_one(item.get(position));
                                    dsfs = item.get(position);
                                } else if (currentPosition == 2) {
                                    data_name.get(num).setContent_two(item.get(position));
                                    gddy = item.get(position);
                                } else if (currentPosition == 3) {
                                    data_name.get(num).setContent_three(item.get(position));
                                    zzsx = item.get(position);
                                } else if (currentPosition == 4) {
                                    data_name.get(num).setContent_four(item.get(position));
                                    fsgxsl = item.get(position);
                                }
                            }
                        } else if (num == data_name.size() - 3) {
                            if (bhlb.equals("交换机")) {
                                if (currentPosition == 1) {
                                    data_name.get(num).setContent_one(item.get(position));
                                    sfrstp = item.get(position);
                                } else if (currentPosition == 2) {
                                    data_name.get(num).setContent_two(item.get(position));
                                    sfds = item.get(position);
                                } else if (currentPosition == 3) {
                                    data_name.get(num).setContent_three(item.get(position));
                                    sfzb = item.get(position);
                                } else if (currentPosition == 4) {
                                    data_name.get(num).setContent_four(item.get(position));
                                    sfsnmp = item.get(position);
                                }
                            } else {
                                if (currentPosition == 1) {
                                    data_name.get(num).setContent_one(item.get(position));
                                    gxjkms = item.get(position);
                                } else if (currentPosition == 2) {
                                    data_name.get(num).setContent_two(item.get(position));
                                    hbdygn = item.get(position);
                                } else if (currentPosition == 3) {
                                    data_name.get(num).setContent_three(item.get(position));
                                    jsgxsl = item.get(position);
                                } else if (currentPosition == 4) {
                                    data_name.get(num).setContent_four(item.get(position));
                                    hgqlx = item.get(position);
                                }
                            }
                        } else if (num == data_name.size() - 2) {
                            if (bhlb.equals("交换机")) {
                                if (currentPosition == 1) {
                                    data_name.get(num).setContent_one(item.get(position));
                                    sfiec = item.get(position);
                                }
                            } else {
                                if (currentPosition == 1) {
                                    data_name.get(num).setContent_one(item.get(position));
                                    znzdgn = item.get(position);
                                }
                            }
                        } else {
                            if (currentPosition == 1) {
                                if (ltysbxh != null) {
                                    String[] ss = item.get(position).split("，");
                                    if (ss.length == 1) {
                                        ltysbxh.setRjbb(ss[0]);
                                    } else if (ss.length == 2) {
                                        ltysbxh.setRjbb(ss[0].split("：")[1] + " " + ss[1].split("：")[1]);
                                    }
                                    if (!data_name.get(num).getContent_three().equals("") &&
                                            !data_name.get(num).getContent_three().equals("必填项") &&
                                            !data_name.get(num).getContent_one().equals(item.get(position))) {
                                        ltysbxh.setWjmc("");
                                        data_name.get(num).setContent_three("必填项");
                                    }
                                } else if (rjbbList.size() > 0) {
                                    if (rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                            + num - data_name.size() + 1).getBblx().equals("非六统一，分模块")) {
                                        if (rjbbList.get(adapter.add_rjbb_count + addTypeNume + num
                                                - data_name.size() + 1).getMkmc() != null
                                                && !rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).getMkmc().equals(item.get(position))) {
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setMkmc(item.get(position));
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setBb("");
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setJym("");
                                            data_name.get(num).setContent_two("");
                                        } else {
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setMkmc(item.get(position));
                                        }
                                    } else {
                                        String[] ss = item.get(position).split("，");
                                        if (ss.length == 2) {
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setBb(ss[0].split("：")[1]);
                                            rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                    + num - data_name.size() + 1).setJym(ss[1].split("：")[1]);
                                        }
                                    }
                                }
                                data_name.get(num).setContent_one(item.get(position));
                            } else if (currentPosition == 2) {
                                data_name.get(num).setContent_two(item.get(position));
                                String[] ss = item.get(position).split("，");
                                if (ss.length == 1) {
                                    if (ss[0].contains("：")) {
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setBb(ss[0].split("：")[1]);
                                    } else {
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setBb(ss[0]);
                                    }
                                } else if (ss.length == 2) {
                                    if (ss[0].contains("：") && ss[1].contains("：")) {
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setBb(ss[0].split("：")[1]);
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setJym(ss[1].split("：")[1]);
                                    } else {
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setBb(ss[0]);
                                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                                + num - data_name.size() + 1).setJym(ss[1]);
                                    }
                                }
                            } else if (currentPosition == 3) {
                                if (ltysbxh != null && !data_name.get(num).getContent_three()
                                        .equals(item.get(position))) {
                                    data_name.get(num).setContent_three(item.get(position));
                                    setLtysbxh(item.get(position));
                                }
                            }
                        }
                    }
                }

                dialog.dismiss();
                adapter.setDatas(data_name);
            }
        });

        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    /**
     * 选择型号带出保护分类逻辑处理
     *
     * @param num
     */
    private void setBHFL(int num) {
        if (isSix && is2013) {
            List<LTYSBXH> ltysbxhList = util.getLTYSBXHBHFLOrBHLX("ALL", zzcj, bhlb, bhxh);
            if (ltysbxhList != null && ltysbxhList.size() > 0) {
                bhfl = ltysbxhList.get(0).getBhfl();
                data_name.get(num).setContent_two(bhfl);
                bhlx = ltysbxhList.get(0).getBhlx();
                data_name.get(num).setContent_three(bhlx);

                if (ltysbxh != null) {
                    ltysbxh.setBhfl(bhfl);
                    ltysbxh.setBhlx(bhlx);
                }
            }
        } else {
            List<BHSBXHB> bhxhList = util.getBHFLOrBHLX("ALL", zzcj, bhlb, bhxh);
            if (bhxhList != null && bhxhList.size() > 0) {
                bhfl = bhxhList.get(0).getBhfl();
                data_name.get(num).setContent_two(bhfl);
                bhlx = bhxhList.get(0).getBhlx();
                data_name.get(num).setContent_three(bhlx);
            }
        }
    }

    /**
     * 时间选择控件
     *
     * @param position
     */

    public void showDateDialog(final int position) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        new MyDatePickerDialog(getActivity(), new MyDatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String time = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                if (ltysbxh != null) {
                    data_name.get(position).setContent_two(time);
                    ltysbxh.setSCSJ(TimeUtil.formatString(time));
                } else {
                    if (data_name.get(position).getNum() == 2) {
                        data_name.get(position).setContent_two(time);
                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                + position - data_name.size() + 1).setSCSJ(TimeUtil.formatString(time));
                    } else {
                        data_name.get(position).setContent_three(time);
                        rjbbList.get(adapter.add_rjbb_count + addTypeNume
                                + position - data_name.size() + 1).setSCSJ(TimeUtil.formatString(time));
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, mYear, mMonth, mDay).myShow();
    }

    /**
     * 保护类别选择后变化
     *
     * @param num
     * @param bhlb
     */
    private void setBHLXBH(int num, String bhlb) {
        DeviceDetailsActivity.sfsbdm = tv_sbsbdm.getText().toString().trim();
        //变化前先remove之前 添加的  如果有
        removeAddType(num, addTypeNume);
        if (bhlb.equals("收发信机")) {//add之前先remove之前的
            isAddType = true;
            addTypeNume = 1;
            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
            item.setName_one("载波通道加工相");
            item.setContent_one("请选择");
            item.setName_two("通道频率");
            item.setContent_two("0");
            item.setNum(2);
            data_name.add(data_name.size() - 1, item);
        } else if (bhlb.equals("合并单元")) {//add
            isAddType = true;
            addTypeNume = 2;
            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
            DeviceDetailsNameItem item1 = new DeviceDetailsNameItem();
            item.setName_one("对时方式");
            item.setContent_one("必填项");
            item.setName_two("供电电源");
            item.setContent_two("必填项");
            item.setName_three("装置属性");
            item.setContent_three("必填项");
            item.setName_four("发送光纤接口数量");
            item.setContent_four("0");
            item.setNum(4);

            item1.setName_one("光纤接口模式");
            item1.setContent_one("必填项");
            item1.setName_two("合并单元功能");
            item1.setContent_two("必填项");
            item1.setName_three("接收光纤接口数量");
            item1.setContent_three("0");
            item1.setName_four("互感器类型");
            item1.setContent_four("必填项");
            item1.setNum(4);
            data_name.add(data_name.size() - 1, item);
            data_name.add(data_name.size() - 1, item1);
        } else if (bhlb.equals("合并单元智能终端集成")) {//add
            isAddType = true;
            addTypeNume = 3;

            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
            DeviceDetailsNameItem item1 = new DeviceDetailsNameItem();
            DeviceDetailsNameItem item2 = new DeviceDetailsNameItem();
            item.setName_one("对时方式");
            item.setContent_one("必填项");
            item.setName_two("供电电源");
            item.setContent_two("必填项");
            item.setName_three("装置属性");
            item.setContent_three("必填项");
            item.setName_four("发送光纤接口数量");
            item.setContent_four("0");
            item.setNum(4);

            item1.setName_one("光纤接口模式");
            item1.setContent_one("必填项");
            item1.setName_two("合并单元功能");
            item1.setContent_two("必填项");
            item1.setName_three("接收光纤接口数量");
            item1.setContent_three("0");
            item1.setName_four("互感器类型");
            item1.setContent_four("必填项");
            item1.setNum(4);

            item2.setName_one("智能终端功能");
            item2.setContent_one("必填项");
            item2.setNum(1);
            data_name.add(data_name.size() - 1, item);
            data_name.add(data_name.size() - 1, item1);
            data_name.add(data_name.size() - 1, item2);
        } else if (bhlb.equals("智能终端")) {//add
            isAddType = true;
            addTypeNume = 1;
            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
            item.setName_one("发送光纤接口数量");
            item.setContent_one("0");
            item.setName_two("光纤接口模式");
            item.setContent_two("必填项");
            item.setName_three("智能终端功能");
            item.setContent_three("必填项");
            item.setName_four("接收光纤接口数量");
            item.setContent_four("0");
            item.setNum(4);
            data_name.add(data_name.size() - 1, item);
        } else if (bhlb.equals("交换机")) {//add
            isAddType = true;
            addTypeNume = 3;

            DeviceDetailsNameItem item1 = new DeviceDetailsNameItem();
            item1.setName_one("交换机类型");
            item1.setContent_one("必填项");
            item1.setName_two("交换机功能");
            item1.setContent_two("必填项");
            item1.setName_three("交换机级联数");
            item1.setContent_three("0");
            item1.setName_four("光纤接口模式");
            item1.setContent_four("必填项");
            item1.setNum(4);
            data_name.add(data_name.size() - 1, item1);

            DeviceDetailsNameItem item2 = new DeviceDetailsNameItem();
            item2.setName_one("是否支持RSTP环网");
            item2.setContent_one("是");
            item2.setName_two("是否支持1588对时");
            item2.setContent_two("是");
            item2.setName_three("是否支持动态组播管理");
            item2.setContent_three("是");
            item2.setName_four("是否支持SNMP网络管理");
            item2.setContent_four("是");
            item2.setNum(4);
            data_name.add(data_name.size() - 1, item2);

            DeviceDetailsNameItem item3 = new DeviceDetailsNameItem();
            item3.setName_one("是否支持采用IEC61850上送交换机信息");
            item3.setContent_one("是");
            item3.setNum(1);
            data_name.add(data_name.size() - 1, item3);
        } else {//remove
            isAddType = false;
            addTypeNume = 0;
        }
    }

    /**
     * 移除动态添加的type
     *
     * @param num
     * @param addType
     */
    private void removeAddType(int num, int addType) {
        dsfs = "";
        gddy = "";
        zzsx = "";
        hgqlx = "";
        zbtdjgx = "";
        tdpl = "0";
        fsgxsl = "0";
        gxjkms = "";
        znzdgn = "";
        jsgxsl = "0";
        hbdygn = "";
        jhjlx = "";
        jhjgn = "";
        jhjjls = "0";
        sfrstp = "是";
        sfds = "是";
        sfzb = "是";
        sfsnmp = "是";
        sfiec = "是";

        if (addType == 1) {
            data_name.remove(data_name.size() - 2);
        } else if (addType == 2) {
            data_name.remove(data_name.size() - 3);
            data_name.remove(data_name.size() - 2);
        } else if (addType == 3) {
            data_name.remove(data_name.size() - 4);
            data_name.remove(data_name.size() - 3);
            data_name.remove(data_name.size() - 2);
        }
    }

    /**
     * 增加型号带出软件版本
     *
     * @param isMKMC
     * @param num
     */
    private void addRJBB(boolean isMKMC, int num) {
        removeAllRJBB(num);
        if (isSix && is2013) {
            DeviceDetailsNameItem item1 = new DeviceDetailsNameItem();
            item1.setNum(3);
            item1.setName_one("软件版本");
            item1.setContent_one("必填项");
            item1.setName_two("生成日期");
            item1.setContent_two("请选择");
            item1.setName_three("ICD文件名");
            item1.setContent_three("必填项");
            data_name.add(num + 1, item1);
            setLtysbxh("");
        } else {
            if (!isMKMC) {//没有增加按钮
                DeviceDetailsNameItem item1 = new DeviceDetailsNameItem();
                item1.setNum(2);
                item1.setName_one("软件版本");
                item1.setContent_one("请选择");
                item1.setName_two("生成日期");
                item1.setContent_two("请选择");
                data_name.add(num + 1, item1);
                BHXHRJBB rjbb = new BHXHRJBB();
                rjbb.setBblx("非六统一，不分模块");
                rjbb.setED_TAG("C");
                if (usegddata != null) {
                    rjbb.setSfqy(usegddata.equals("是") ? "Y" : "N");
                }
                rjbbList.add(rjbb);
                bhsbxhb = new BHSBXHB();
                bhsbxhb.setBblx("非六统一，不分模块");
                bhsbxhb.setSbxh(bhxh);
                bhsbxhb.setED_TAG("C");
                if (usegddata.equals("是")) {
                    bhsbxhb.setSfqy("Y");
                }
                bhsbxhb.setBhlb(bhlb);
                bhsbxhb.setZzcj(zzcj);
            } else {  //有增加按钮
                DeviceDetailsNameItem item1 = new DeviceDetailsNameItem();
                item1.setNum(3);
                item1.setName_one("模块名称");
                item1.setContent_one("请选择");
                item1.setName_two("软件版本");
                item1.setContent_two("请选择");
                item1.setName_three("生成日期");
                item1.setContent_three("请选择");
                item1.setContent_four("添加+");
                item1.setHaveAdd(true);
                data_name.add(num + 1, item1);
                BHXHRJBB rjbb = new BHXHRJBB();
                rjbb.setBblx("非六统一，分模块");
                rjbb.setED_TAG("C");
                if (usegddata != null) {
                    rjbb.setSfqy(usegddata.equals("是") ? "Y" : "N");
                }
                rjbbList.add(rjbb);
                bhsbxhb = new BHSBXHB();
                bhsbxhb.setBblx("非六统一，分模块");
                bhsbxhb.setSbxh(bhxh);
                bhsbxhb.setBhlb(bhlb);
                bhsbxhb.setZzcj(zzcj);
                bhsbxhb.setED_TAG("C");
                if (usegddata.equals("是")) {
                    bhsbxhb.setSfqy("Y");
                }
            }
        }
    }

    /**
     * 设置六统一对象
     *
     * @param wjmc
     */
    public void setLtysbxh(String wjmc) {
        String rjbb = null, scsj = null;
        if (ltysbxh != null) {
            rjbb = ltysbxh.getRjbb();
            scsj = ltysbxh.getSCSJ();
        }
        ltysbxh = new LTYSBXH();
        ltysbxh.setZzcj(zzcj);
        ltysbxh.setED_TAG("C");
        ltysbxh.setBhlb(bhlb);
        ltysbxh.setBhxh(bhxh);
        if (rjbb != null) {
            ltysbxh.setRjbb(rjbb);
        }
        if (scsj != null) {
            ltysbxh.setSCSJ(scsj);
        }
        if (usegddata.equals("是")) {
            ltysbxh.setSfqy("Y");
        }
        ltysbxh.setBblx("六统一设备");
        ltysbxh.setState("C");
        ltysbxh.setSysblx("FZSB");

        if (wjmc != null && !wjmc.equals("")) {
            ltysbxh.setWjmc(wjmc);
        }
    }

    /**
     * 设置软件版本
     *
     * @param position
     */

    private void setRJBB(int num, int position) {
        removeAllRJBB(num);
        if (xhCode != null && xhCode.length > 0) {
            selectCode = xhCode[position];
        }
        if (adapter != null) {
            adapter.add_rjbb_count = 1;
        }
        if (isSix && is2013) {
            DeviceDetailsNameItem item1 = new DeviceDetailsNameItem();
            item1.setNum(3);
            item1.setName_one("软件版本");
            item1.setContent_one("必填项");
            item1.setName_two("生成日期");
            item1.setContent_two("请选择");
            item1.setName_three("ICD文件名");
            item1.setContent_three("必填项");
            data_name.add(num + 1, item1);
            setLtysbxh("");
        } else {
            if (xhBblx != null && xhBblx.length > 0) {
                if (xhBblx[position] != null) {
                    String[] lx = xhBblx[position].split("，");
                    if (lx.length == 1 || lx[0].equals("无版本")) {
                        DeviceDetailsNameItem item1 = new DeviceDetailsNameItem();
                        item1.setNum(2);
                        item1.setName_one("软件版本");
                        item1.setContent_one("请选择");
                        item1.setName_two("生成日期");
                        item1.setContent_two("请选择");
                        data_name.add(num + 1, item1);
                        BHXHRJBB rjbb = new BHXHRJBB();
                        rjbb.setBblx("非六统一，不分模块");
                        rjbb.setED_TAG("C");
                        if (usegddata != null) {
                            rjbb.setSfqy(usegddata.equals("是") ? "Y" : "N");
                        }
                        rjbbList.add(rjbb);
                    } else {
                        if (lx[1].equals("不分模块")) {//没有增加按钮
                            DeviceDetailsNameItem item1 = new DeviceDetailsNameItem();
                            item1.setNum(2);
                            item1.setName_one("软件版本");
                            item1.setContent_one("请选择");
                            item1.setName_two("生成日期");
                            item1.setContent_two("请选择");
                            data_name.add(num + 1, item1);
                            BHXHRJBB rjbb = new BHXHRJBB();
                            rjbb.setBblx("非六统一，不分模块");
                            rjbb.setED_TAG("C");
                            if (usegddata != null) {
                                rjbb.setSfqy(usegddata.equals("是") ? "Y" : "N");
                            }
                            rjbbList.add(rjbb);
                        } else {  //有增加按钮
                            DeviceDetailsNameItem item1 = new DeviceDetailsNameItem();
                            item1.setNum(3);
                            item1.setName_one("模块名称");
                            item1.setContent_one("请选择");
                            item1.setName_two("软件版本");
                            item1.setContent_two("请选择");
                            item1.setName_three("生成日期");
                            item1.setContent_three("请选择");
                            item1.setContent_four("添加+");
                            item1.setHaveAdd(true);
                            data_name.add(num + 1, item1);
                            BHXHRJBB rjbb = new BHXHRJBB();
                            rjbb.setED_TAG("C");
                            if (usegddata != null) {
                                rjbb.setSfqy(usegddata.equals("是") ? "Y" : "N");
                            }
                            rjbb.setBblx("非六统一，分模块");
                            rjbbList.add(rjbb);
                        }
                    }
                }
            }
        }
    }

    /**
     * 移除所有新增的软件版本
     */
    private void removeAllRJBB(int position) {
        if (ltysbxh != null) {
            if (DeviceDetailsActivity.state.equals("M")
                    && ltysbxh.getId() != null) {
                util.coreDelte(DeviceDetailsActivity.fzbhsb.getId() + "");
            }
            data_name.remove(position + 1);
        }
        if (rjbbList.size() > 0) {
            for (BHXHRJBB bhxhrjbb : rjbbList) {
                if (bhxhrjbb.getId() != null) {
                    if (DeviceDetailsActivity.state.equals("M")) {
                        util.coreDelte(DeviceDetailsActivity.fzbhsb.getId() + "");
                    }
                }
                data_name.remove(position + 1);
            }
        }
        ltysbxh = null;
        bhsbxhb = null;
        rjbbList.clear();
        selectCode = "";
    }

    /**
     * 选择完一次设备名称自动带出电压等级
     */
    public void setDydj(String ycsblx, String ycsbmc) {
        dydj = "";
        Object o;
        switch (ycsblx) {
            case "线路":
                o = util.getCZDYDJ(XLCS.class, czmc, ycsbmc, dwmc);
                if (o != null) {
                    dydj = ((XLCS) o).getDYDJ() + "";
                }
                break;
            case "电抗器":
                o = util.getCZDYDJ(DKQCS.class, czmc, ycsbmc, dwmc);
                if (o != null) {
                    dydj = ((DKQCS) o).getDydj() + "";
                }
                break;
            case "电容器":
                o = util.getCZDYDJ(DRQCS.class, czmc, ycsbmc, dwmc);
                if (o != null) {
                    dydj = ((DRQCS) o).getDYDJ() + "";
                }
                break;
            case "电动机":
                o = util.getCZDYDJ(DDJCS.class, czmc, ycsbmc, dwmc);
                if (o != null) {
                    dydj = ((DDJCS) o).getDYDJ() + "";
                }
                break;
            case "母线":
                o = util.getCZDYDJ(MXCS.class, czmc, ycsbmc, dwmc);
                if (o != null) {
                    dydj = ((MXCS) o).getDYDJ() + "";
                }
                break;
            case "断路器":
                o = util.getCZDYDJ(DLQCS.class, czmc, ycsbmc, dwmc);
                if (o != null) {
                    dydj = ((DLQCS) o).getDYDJ() + "";
                }
                break;
            case "变压器":
                o = util.getCZDYDJ(BYQCS.class, czmc, ycsbmc, dwmc);
                if (o != null) {
                    dydj = ((BYQCS) o).getDYDJ() + "";
                }
                break;
            case "发电机":
                o = util.getCZDYDJ(FDJCS.class, czmc, ycsbmc, dwmc);
                if (o != null) {
                    dydj = ((FDJCS) o).getDYDJ() + "";
                }
                break;
        }
        if (dydj == null || dydj.equals("") || dydj.equals("必填项")) {
            dydj = util.getCZCSByGLDW().getCzzgdydj() + "";
        }
    }

    /**
     * 初始化从列表进入部分选项的值
     */
    private void setFirstData() {
        List<Object> list;
        if (bhxh != null && !bhxh.equals("") && !bhxh.equals("必填项")) {
            list = util.getBHXH(isSix, is2013, zzcj, bhlb, czlx, dydj, usegddata, bhxh);
        } else {
            list = util.getBHXH(isSix, is2013, zzcj, bhlb, czlx, dydj, usegddata);
        }
        xhBblx = new String[list.size()];
        xhCode = new String[list.size()];
        if (isSix && is2013) {
            for (int i = 0; i < list.size(); i++) {
                xhBblx[i] = ((LTYSBXH) list.get(i)).getBblx();
                xhCode[i] = ((LTYSBXH) list.get(i)).getCode();
            }
        } else {
//            FZDeviceOneFragment.instance.xhCode = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                xhBblx[i] = ((BHSBXHB) list.get(i)).getBblx();
                xhCode[i] = ((BHSBXHB) list.get(i)).getCode();
            }
        }
    }
}
