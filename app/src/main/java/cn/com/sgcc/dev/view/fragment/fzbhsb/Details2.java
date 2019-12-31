package cn.com.sgcc.dev.view.fragment.fzbhsb;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.datapick.widget.bean.DateType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.model2.ycsb.BYQCS;
import cn.com.sgcc.dev.model2.ycsb.DDJCS;
import cn.com.sgcc.dev.model2.ycsb.DKQCS;
import cn.com.sgcc.dev.model2.ycsb.DLQCS;
import cn.com.sgcc.dev.model2.ycsb.DRQCS;
import cn.com.sgcc.dev.model2.ycsb.FDJCS;
import cn.com.sgcc.dev.model2.ycsb.MXCS;
import cn.com.sgcc.dev.model2.ycsb.XLCS;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.SelectActivity;
import cn.com.sgcc.dev.view.fragment.BaseFragment;
import cn.com.sgcc.dev.view.viewinterface.DateChooseListener;

import static android.app.Activity.RESULT_OK;

/**
 * <p>@description:安装及运维信息</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/25
 */

public class Details2 extends BaseFragment {

    @BindView(R.id.tv_details2)
    TextView tv_details2;
    @BindViews(value = {R.id.tv_ycsblx, R.id.tv_dydj, R.id.tv_dwmc, R.id.tv_dddw,
            R.id.tv_tyrq, R.id.tv_yjdw, R.id.tv_sjdw, R.id.tv_jjdw, R.id.tv_yxdw,
            R.id.tv_whdw, R.id.tv_sbsx, R.id.tv_jglx})
    List<TextView> textViews;
    @BindViews(value = {R.id.tv_ssjg, R.id.tv_ycsbmc, R.id.tv_bhxsmc, R.id.tv_sspg, R.id.tv_jgmc})
    List<EditText> editTexts;
    @BindViews(value = {R.id.iv_ssjp, R.id.iv_ycsblx, R.id.iv_ycsbmc, R.id.iv_dydj, R.id.iv_dwmc,
            R.id.iv_dddw, R.id.iv_bhxsmc, R.id.iv_sspg, R.id.iv_tyrq, R.id.iv_yjdw, R.id.iv_sjdw, R.id.iv_jjdw,
            R.id.iv_yxdw, R.id.iv_whdw, R.id.iv_sbsx, R.id.iv_jglx, R.id.iv_jgmc})
    List<ImageView> imgViews;
    @BindView(R.id.ll_jgmc)
    LinearLayout ll_jgmc;
    @BindView(R.id.ll_jglx)
    LinearLayout ll_jglx;
    Unbinder unbinder;
    public static Details2 instance;
    private IDaoUtil util;
    public String dydj;
    public String ycsbmc;
    public String tyrq; //投运日期，必须小于出厂日期

    /**
     * 必填项校验需要
     */
    @BindViews(value = {R.id.ycsblx, R.id.ycsbmc, R.id.dydj, R.id.dwmc, R.id.dddw, R.id.tyrq, R.id.sjdw
            , R.id.jjdw, R.id.yxdw, R.id.whdw, R.id.sbsx, R.id.jglx, R.id.jgmc})
    List<TextView> textViews_key;

    @BindViews(value = {R.id.tv_ycsblx, R.id.tv_ycsbmc, R.id.tv_dydj, R.id.tv_dwmc, R.id.tv_dddw, R.id.tv_tyrq, R.id.tv_sjdw
            , R.id.tv_jjdw, R.id.tv_yxdw, R.id.tv_whdw, R.id.tv_sbsx, R.id.tv_jglx, R.id.tv_jgmc})
    List<TextView> textViews_more;
    private Map<String, TextView> map_key = new HashMap<>();
    private Map<String, TextView> map_more = new HashMap<>();
    private boolean isShow;

    @Override
    public int getLayoutId() {
        return R.layout.item_details2;
    }

    @Override
    public void initview() {
        instance = this;
        util = new DaoUtil(getActivity());

        //间隔模式显示
        Map<String, String> keyMap = new HashMap<>();
        Map<String, String> txtMap = AppUtils.setTxt(keyMap, "", false);
        String jgms = txtMap.get("jgms") + "";
        if (!jgms.equals("是")) {
            ll_jgmc.setVisibility(View.GONE);
            ll_jglx.setVisibility(View.GONE);
        }
    }

    @Override
    public void initEvevt() {
        editTexts.get(1).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                ycsbmc = s.toString();
            }
        });

        /**
         * 加载必填项监听
         */
        Details1.instance.initOnFocusChangeListener(map_key,map_more,false,"安装及运维信息");
    }

    @Override
    public void initData() {
        init();

        boolean isEdit = getArguments().getBoolean("isEdit", false);
        setFirstData();
        initReceiver(isEdit);

        //加载监听
        initEvevt();
    }

    /**
     * 初始化校验项
     */
    private void init() {
        Map<String, TextView> tmp_key = new HashMap<>();
        Map<String, TextView> tmp_value = new HashMap<>();
        for (int i = 0; i < textViews_key.size(); i++) {
            tmp_key.put(textViews_key.get(i).getText().toString(), textViews_key.get(i));
            tmp_value.put(textViews_key.get(i).getText().toString(), textViews_more.get(i));
        }

        List<SaleAttributeVo> saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getFzData()) {
            if (saleAttributeNameVo.getName().equals("安装及运维信息")) {
                saleVo = saleAttributeNameVo.getSaleVo();
                break;
            }
        }

        for (SaleAttributeVo saleAttributeVo : saleVo) {
            if (saleAttributeVo.getValue().equals("电压等级")) {
                map_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "（kV）："));
                map_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "（kV）："));
                continue;
            }
            if (tmp_key.containsKey(saleAttributeVo.getValue() + "：")) {
                map_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "："));
                map_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "："));
            }
        }
    }

    /**
     * 初始化数据
     */
    private void setFirstData() {
        FZBHSB fzbhsb = DemoActivity.instance.fzbhsb;

        if (fzbhsb != null) { //找到设备或者从出厂信息库带出
            if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C")) {
                tv_details2.setVisibility(View.GONE);
            } else {
                tv_details2.setVisibility(View.VISIBLE);
                if (DemoActivity.instance.rzgl != null &&
                        DemoActivity.instance.rzgl.getCZSJ() != null &&
                        !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                    tv_details2.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                } else {
                    tv_details2.setText("本条台账最后一次修改时间：");
                }
            }

            editTexts.get(0).setText("");
            if (fzbhsb.getYcsblx() != null) {
                textViews.get(0).setText(fzbhsb.getYcsblx());
            }
            if (fzbhsb.getYcsbmc() != null) {
                editTexts.get(1).setText(fzbhsb.getYcsbmc());
                ycsbmc = fzbhsb.getYcsbmc();
            }
            if (fzbhsb.getDydj() != 0) {
                textViews.get(1).setText(fzbhsb.getDydj() + "");
                dydj = fzbhsb.getDydj() + "";
            }
            textViews.get(2).setText(DemoActivity.instance.czcs.getGldw());
            if (fzbhsb.getDddw() != null) {
                textViews.get(3).setText(fzbhsb.getDddw());
            }
            editTexts.get(2).setText("");
            editTexts.get(3).setText("");
            if (fzbhsb.getTyrq() != null) {
                textViews.get(4).setText(fzbhsb.getTyrq());
                tyrq = fzbhsb.getTyrq();
            }
            textViews.get(6).setText(fzbhsb.getSjdw());
            if (fzbhsb.getJjdw() != null) {
                textViews.get(7).setText(fzbhsb.getJjdw());
            }
            if (fzbhsb.getYxdw() != null) {
                textViews.get(8).setText(fzbhsb.getYxdw());
            }
            if (fzbhsb.getWhdw() != null) {
                textViews.get(9).setText(fzbhsb.getWhdw());
            }
            if (fzbhsb.getSbsx() != null) {
                textViews.get(10).setText(fzbhsb.getSbsx());
            }
            if (fzbhsb.getJgmc() != null) {
                editTexts.get(4).setText(fzbhsb.getJgmc());
            }
            if (fzbhsb.getJglx() != null) {
                textViews.get(11).setText(fzbhsb.getJglx());
            }
        } else {
            tv_details2.setVisibility(View.GONE);
        }
    }

    @Override
    public void initReceiver(boolean isEdit) {
        if (isEdit) {
            for (EditText editText : editTexts) {
                editText.setBackgroundResource(R.drawable.device_detials_bg);
                editText.setEnabled(true);
            }
            for (TextView textView : textViews) {
                textView.setBackgroundResource(R.drawable.device_detials_bg);
                textView.setEnabled(true);
            }

            for (ImageView imgView : imgViews) {
                imgView.setVisibility(View.VISIBLE);
            }
        } else {
            if (DemoActivity.instance.isCancel) {//点击了取消
                for (EditText editText : editTexts) {
                    editText.setBackground(null);
                    editText.setEnabled(false);
                    editText.setText("");
                }
                for (TextView textView : textViews) {
                    textView.setBackground(null);
                    textView.setEnabled(false);
                    textView.setText("");
                }
                for (ImageView imgView : imgViews) {
                    imgView.setVisibility(View.GONE);
                }

                if (DemoActivity.instance.rzgl != null &&
                        DemoActivity.instance.rzgl.getCZSJ() != null &&
                        !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                    tv_details2.setVisibility(View.VISIBLE);
                    tv_details2.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }
                //清楚所有的数据，重新加载原始数据
                setFirstData();

            } else {//正常保存
                for (EditText editText : editTexts) {
                    editText.setBackground(null);
                    editText.setEnabled(false);
                }
                for (TextView textView : textViews) {
                    textView.setBackground(null);
                    textView.setEnabled(false);
                }
                for (ImageView imgView : imgViews) {
                    imgView.setVisibility(View.GONE);
                }
                if (DemoActivity.instance.rzgl != null &&
                        DemoActivity.instance.rzgl.getCZSJ() != null &&
                        !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                    tv_details2.setVisibility(View.VISIBLE);
                    tv_details2.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }
            }
        }
        /**
         * 必填项校验设计
         */
        Details1.instance.checkBtx(isEdit, false, isShow, map_key, map_more, "安装及运维信息");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_ycsblx, R.id.tv_dydj, R.id.tv_dwmc, R.id.tv_dddw, R.id.tv_tyrq, R.id.tv_yjdw, R.id.tv_sjdw,
            R.id.tv_jjdw, R.id.tv_yxdw, R.id.tv_whdw, R.id.tv_sbsx, R.id.iv_ssjp, R.id.iv_ycsblx, R.id.iv_ycsbmc,
            R.id.iv_dydj, R.id.iv_dwmc, R.id.iv_dddw, R.id.iv_bhxsmc, R.id.iv_sspg, R.id.iv_tyrq, R.id.iv_yjdw,
            R.id.iv_sjdw, R.id.iv_jjdw, R.id.iv_yxdw, R.id.iv_whdw, R.id.iv_sbsx, R.id.tv_jglx, R.id.iv_jglx, R.id.iv_jgmc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_jgmc:
                setIntentData("间隔名称");
                break;
            case R.id.tv_ycsblx:
            case R.id.iv_ycsblx:
                setIntentData("一次设备类型");
                break;
            case R.id.tv_dydj:
            case R.id.iv_dydj:
                setIntentData("电压等级");
                break;
            case R.id.tv_dwmc:
            case R.id.iv_dwmc:
                setIntentData("单位名称");
                break;
            case R.id.tv_dddw:
            case R.id.iv_dddw:
                setIntentData("调度单位");
                break;
            case R.id.tv_tyrq:
            case R.id.iv_tyrq:
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        if (Details1.instance.ccrq != null && !Details1.instance.ccrq.equals("")
                                && date != null && !date.equals("")) {
                            if (TimeUtil.timeCompare(Details1.instance.ccrq, date) > 0) {
                                ToastUtils.showLongToast(getActivity(), "投运日期不能早于出厂日期");
                                return;
                            }
                        }
                        textViews.get(4).setText(date);
                        tyrq = date;

//                        /**
//                         * 必填项校验设计
//                         */
//                        Details1.instance.checkBtx(true, false, isShow, map_key, map_more, "安装及运维信息");
                    }
                });
                break;
            case R.id.tv_yjdw:
            case R.id.iv_yjdw:
                setIntentData("运检单位");
                break;
            case R.id.tv_sjdw:
            case R.id.iv_sjdw:
                setIntentData("设计单位");
                break;
            case R.id.tv_jjdw:
            case R.id.iv_jjdw:
                setIntentData("基建单位");
                break;
            case R.id.tv_yxdw:
            case R.id.iv_yxdw:
                setIntentData("运行单位");
                break;
            case R.id.tv_whdw:
            case R.id.iv_whdw:
                setIntentData("维护单位");
                break;
            case R.id.tv_sbsx:
            case R.id.iv_sbsx:
                setIntentData("设备属性");
                break;
            case R.id.iv_ssjp:
                setIntentData("所属屏柜");
                break;
            case R.id.iv_ycsbmc:
                setIntentData("一次设备名称");
                break;
            case R.id.iv_bhxsmc:
                setIntentData("保护小室名称");
                break;
            case R.id.iv_sspg:
                setIntentData("所属屏柜");
                break;
            case R.id.tv_jglx:
            case R.id.iv_jglx:
                setIntentData("间隔类型");
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String type = data.getStringExtra("puttype");
            String value = data.getStringExtra("result");
            switch (type) {
                case "一次设备类型":
                    if (!textViews.get(0).getText().toString().equals(value)) {
                        textViews.get(0).setText(value);
                        //在选择套别模式的情况下，增加清空运行基本信息中套别的代码，只要一次设备类型发生变化就需要清空套别，需要重新选择套别
                        Map<String, String> keyMap = new HashMap<>();
                        Map<String, String> txtMap = AppUtils.setTxt(keyMap, "", false);

                        String tbms = txtMap.get("tbms") + "";
                        //在选择套别模式的情况下，装置类别发生变化，运行装置信息中的套别就要清空
                        if (tbms.equals("是")) {
                            Details3.instance.setTb();
                            if (value.equals("变压器") || value.equals("母线")) {
                                String zzlb = Details1.instance.getZzlb();
                                if (!zzlb.equals("") && (zzlb.equals("合并单元") || zzlb.equals("智能终端") || zzlb.equals("合并单元智能终端集成"))) {
                                    ToastUtils.showLongToast(getActivity(), "装置名称生成规则发生变化，请重新生成");
                                    // Details1.instance.setZzmc();
                                }
                            }
                        }
                        editTexts.get(1).setText("");
                    }
                    break;
                case "一次设备名称":
                    if (!editTexts.get(1).getText().toString().equals(value)) {
                        ycsbmc = value;
                        editTexts.get(1).setText(value);
                        String ycsblx = textViews.get(0).getText().toString();
                        String czmc = DemoActivity.instance.czcs.getCzmc();
                        String dwmc = DemoActivity.instance.czcs.getGldw();

                        dydj = setDydj(ycsblx, value, czmc, dwmc);
//                        Details1.instance.changeDydj(dydj, textViews.get(1).getText().toString());
                        textViews.get(1).setText(dydj);
                    }
                    break;
                case "电压等级":
                    Long dydj0 = DemoActivity.instance.czcs.getCzzgdydj();
                    if (value != null && !value.equals("") && Long.parseLong(value) > dydj0) {
                        ToastUtils.showLongToast(getActivity(), "电压等级不能大于厂站最高电压等级");
                    } else {
//                        Details1.instance.changeDydj(value, textViews.get(1).getText().toString());
                        textViews.get(1).setText(value);
                        dydj = value;
                    }
                    break;
                case "单位名称":
                    textViews.get(2).setText(value);
                    break;
                case "调度单位":
                    textViews.get(3).setText(value);
                    break;
                case "设计单位":
                    textViews.get(6).setText(value);
                    break;
                case "基建单位":
                    textViews.get(7).setText(value);
                    break;
                case "运行单位":
                    textViews.get(8).setText(value);
                    break;
                case "维护单位":
                    textViews.get(9).setText(value);
                    break;
                case "设备属性":
                    textViews.get(10).setText(value);
                    break;
                case "间隔类型":
                    if (!textViews.get(11).getText().toString().equals(value)) {
                        textViews.get(11).setText(value);
                        editTexts.get(4).setText("");
                    }
                    break;
                case "间隔名称":
                    if (!value.equals(editTexts.get(4).getText().toString())) {
                        editTexts.get(1).setText("");
                    }
                    editTexts.get(4).setText(value);
                    break;
            }
//            Details1.instance.checkBtx(true, false, isShow, map_key, map_more, "安装及运维信息");
        }
    }

    public void setDydj(int dydj) {
        textViews.get(1).setText(dydj + "");
        this.dydj = dydj + "";
    }

    /**
     * 选择完一次设备名称自动带出电压等级
     */
    public String setDydj(String ycsblx, String ycsbmc, String czmc, String dwmc) {
        String dydj = "";
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
            dydj = DemoActivity.instance.czcs.getCzzgdydj() + "";
        }

        return dydj;
    }

    /**
     * @param type 类型
     */
    private void setIntentData(String type) {
        Map<String, Object> map = new HashMap<>();
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("type", type);
//        boolean isSix = cbTrue.isChecked();
//        boolean is2013 = textViews.get(0).getText().toString().equals("2013版");
//        map.put("isSIX", isSix);
//        map.put("is2013", is2013);
        if (type.equals("所属屏柜")) {

        } else if (type.equals("一次设备类型")) {

        } else if (type.equals("一次设备名称")) {
            map.put("ycsblx", textViews.get(0).getText().toString());

            String jgmc = editTexts.get(4).getText().toString();
            if (jgmc != null && !jgmc.equals("")) {
                map.put("jgmc", jgmc);
            }
            String jglx = textViews.get(11).getText().toString();
            if (jglx != null && !jglx.equals("")) {
                map.put("jglx", jglx);
            }
        } else if (type.equals("间隔名称")) {
            String jglx = textViews.get(11).getText().toString();
            if (jglx != null && !jglx.equals("")) {
                map.put("jglx", jglx);
            }
        }
//        else if (type.equals("电压等级")) {
//
//        } else if (type.equals("单位名称")) {
//
//        } else if (type.equals("调度单位")) {
//
//        } else if (type.equals("保护小室名称")) {
//
//        } else if (type.equals("所属屏柜")) {
//
//        } else if (type.equals("运检单位")) {
//
//        } else if (type.equals("设计单位")) {
//
//        } else if (type.equals("基建单位")) {
//
//        } else if (type.equals("运行单位")) {
//
//        } else if (type.equals("维护单位")) {
//
//        } else if (type.equals("设备属性")) {
//
//        }
        intent.putExtra("conditions", (Serializable) map);
        startActivityForResult(intent, 0);
    }

    public String getYcsblx() {
        String ycsblx = textViews.get(0).getText().toString();
        if (ycsblx.equals("")) {
            //  ToastUtils.showLongToast(getActivity(), "一次设备类型不能为空");
            return "";
        } else {
            return ycsblx;
        }
    }

    /**
     * 统一保存校验方法
     */
    public boolean saveDetails2() {
        String ycsblx = textViews.get(0).getText().toString();
        if (ycsblx.equals("")) {
            DemoActivity.instance.fzbhsb.setYcsblx(ycsblx);
            if (map_key.containsKey("一次设备类型")) {
                ToastUtils.showLongToast(getActivity(), "一次设备类型不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setYcsblx(ycsblx);
        }
        String ycsbmc = editTexts.get(1).getText().toString();
        if (ycsbmc.equals("")) {
            DemoActivity.instance.fzbhsb.setYcsbmc(ycsbmc);
            if (map_key.containsKey("一次设备名称")) {
                ToastUtils.showLongToast(getActivity(), "一次设备名称不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setYcsbmc(ycsbmc.replaceAll("，", ","));
        }
        String dydj = textViews.get(1).getText().toString();
        if (dydj.equals("")) {
            if (map_key.containsKey("电压等级")) {
                ToastUtils.showLongToast(getActivity(), "电压等级不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setDydj(Integer.parseInt(dydj));
        }

        String dwmc = textViews.get(2).getText().toString();
        if (dwmc.equals("")) {
            DemoActivity.instance.fzbhsb.setCzgldw(dwmc);
            DemoActivity.instance.fzbhsb.setSbdw(dwmc);  //保存上报单位
            if (map_key.containsKey("单位名称")) {
                ToastUtils.showLongToast(getActivity(), "单位名称不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setCzgldw(dwmc);
            DemoActivity.instance.fzbhsb.setSbdw(dwmc);  //保存上报单位
        }

        String dddw = textViews.get(3).getText().toString();
        if (dddw.equals("")) {
            DemoActivity.instance.fzbhsb.setDddw(dddw);
            if (map_key.containsKey("调度单位")) {
                ToastUtils.showLongToast(getActivity(), "调度单位不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setDddw(dddw);
        }
//        DemoActivity.instance.fzbhsb.setDddw(dddw);
        String tyrq = textViews.get(4).getText().toString();
        if (tyrq.equals("")) {
            DemoActivity.instance.fzbhsb.setTyrq(tyrq);
            if (map_key.containsKey("投运日期")) {
                ToastUtils.showLongToast(getActivity(), "投运日期不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setTyrq(tyrq);
        }
//        DemoActivity.instance.fzbhsb.setTyrq(tyrq);
        String sjdw = textViews.get(6).getText().toString();
        if (sjdw.equals("")) {
            DemoActivity.instance.fzbhsb.setSjdw(sjdw);
            if (map_key.containsKey("设计单位")) {
                ToastUtils.showLongToast(getActivity(), "设计单位不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setSjdw(sjdw);
        }
//        DemoActivity.instance.fzbhsb.setSjdw(sjdw);
        String jjdw = textViews.get(7).getText().toString();
        if (jjdw.equals("")) {
            DemoActivity.instance.fzbhsb.setJjdw(jjdw);
            if (map_key.containsKey("基建单位")) {
                ToastUtils.showLongToast(getActivity(), "基建单位不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setJjdw(jjdw);
        }
//        DemoActivity.instance.fzbhsb.setJjdw(jjdw);
        String yxdw = textViews.get(8).getText().toString();
        if (yxdw.equals("")) {
            DemoActivity.instance.fzbhsb.setYxdw(yxdw);
            if (map_key.containsKey("运行单位")) {
                ToastUtils.showLongToast(getActivity(), "运行单位不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setYxdw(yxdw);
        }
//        DemoActivity.instance.fzbhsb.setYxdw(yxdw);
        String whdw = textViews.get(9).getText().toString();
        if (whdw.equals("")) {
            DemoActivity.instance.fzbhsb.setWhdw(whdw);
            if (map_key.containsKey("维护单位")) {
                ToastUtils.showLongToast(getActivity(), "维护单位不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setWhdw(whdw);
        }
//        DemoActivity.instance.fzbhsb.setWhdw(whdw);
        String sbsx = textViews.get(10).getText().toString();
        if (sbsx.equals("")) {
            DemoActivity.instance.fzbhsb.setSbsx(sbsx);
            if (map_key.containsKey("设备属性")) {
                ToastUtils.showLongToast(getActivity(), "设备属性不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setSbsx(sbsx);
        }
//        DemoActivity.instance.fzbhsb.setSbsx(sbsx);
        String jgmc = editTexts.get(4).getText().toString();
        if (jgmc.equals("")) {
            DemoActivity.instance.fzbhsb.setJgmc(jgmc);
            if (map_key.containsKey("间隔名称")) {
                ToastUtils.showLongToast(getActivity(), "间隔名称不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setJgmc(jgmc);
        }
//        DemoActivity.instance.fzbhsb.setJgmc(jgmc);
        String jglx = textViews.get(11).getText().toString();
        if (jglx.equals("")) {
            DemoActivity.instance.fzbhsb.setJglx(jglx);
            if (map_key.containsKey("间隔类型")) {
                ToastUtils.showLongToast(getActivity(), "间隔类型不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setJglx(jglx);
        }
//        DemoActivity.instance.fzbhsb.setJglx(jglx);
        return true;
    }
}
