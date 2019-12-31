package cn.com.sgcc.dev.view.fragment.fzbhsb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.datapick.widget.bean.DateType;
import com.zxing.activity.CaptureActivity;

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
import cn.com.sgcc.dev.adapter.DeviceRjbbAdapter;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.MyListView;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.BHSBXHB;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.LTYSBXH;
import cn.com.sgcc.dev.model2.ZZCJ;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.MyScrollView;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.SelectActivity;
import cn.com.sgcc.dev.view.fragment.BaseFragment;
import cn.com.sgcc.dev.view.viewinterface.DateChooseListener;
import cn.com.sgcc.dev.view.viewinterface.ViewAddListener;

import static android.app.Activity.RESULT_OK;

/**
 * <p>@description:装置基本信息</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/25
 */

public class Details1 extends BaseFragment implements MyScrollView.OnScrollListener {
    public static Details1 instance;
    public boolean isSix, is2013;
    public LTYSBXH ltysbxh;
    public LTYSBXH ltysbxh_add;
    public List<BHXHRJBB> rjbbList = new ArrayList<>();
    public List<BHXHRJBB> rjbbListAll = new ArrayList<>();
    public Map<String, String> codeMap = new HashMap<>();//存储型号code
    public Map<String, String> bblxMap = new HashMap<>();//存储型号版本类型
    public Map<String, String> dateMap = new HashMap<>();//存储六统一软件版本时间
    public Map<String, String> bbToCode = new HashMap<>();//存储软件版本对应型号code，key-bb，value-bhxhcode
    public ZZCJ zzcj_add; //厂家新增使用
    public BHSBXHB bhsbxhb;//装置型号
    public String lty_zzxh;  //存储六统一设备型号
    public String lty_xh; //六统一时需要用
    public String lty_rjbb; //六统一时需要用
    public String ccrq; //出厂日期，必须大于投运日期
    public String usegddata; //是否使用国调下发
    public boolean isSave; //是否未保存
    Unbinder unbinder;
    @BindView(R.id.cb_true)
    CheckBox cbTrue;
    @BindView(R.id.cb_xh_sffmk)
    CheckBox cb_xh_sffmk;
    @BindView(R.id.tv_details1)
    TextView tv_details1;
    @BindView(R.id.tv_zzfl2)
    TextView tv_zzfl2;
    @BindView(R.id.tv_zzlx2)
    TextView tv_zzlx2;
    @BindView(R.id.ll_xh_sffmks)
    LinearLayout ll_xh_sffmk;
    @BindViews(value = {R.id.tv_ltybzbb, R.id.tv_zzlb, R.id.tv_rjbbscsj,
            R.id.tv_ccrq, R.id.tv_zzfl, R.id.tv_zzlx})
    List<TextView> textViews;
    @BindViews(value = {R.id.tv_zzmc, R.id.tv_zzcj, R.id.tv_zzxh,
            R.id.tv_rjbb, R.id.tv_jym, R.id.tv_bksl, R.id.tv_rjbb_lty, R.id.tv_swm})
    List<EditText> editTexts;
    @BindViews(value = {R.id.iv_ltybzbb, R.id.iv_zzcj, R.id.iv_zzlb, R.id.iv_zzxh, R.id.iv_rjbb,
            R.id.iv_jym, R.id.iv_rjbbscsj, R.id.iv_ccrq, R.id.iv_zzmc, R.id.iv_zzfl, R.id.iv_zzlx
            , R.id.iv_rjbb_lty, R.id.iv_swm})
    List<ImageView> imgViews;
    @BindView(R.id.ll_ltybzbb)
    LinearLayout llLtybzbb;
    @BindView(R.id.ll_rjbb)
    LinearLayout llRjbb;
    @BindView(R.id.ll_rjbb_lty)
    LinearLayout ll_rjbb_lty; //六统一装置
    @BindView(R.id.ll_hassffmk)
    LinearLayout ll_hassffmk; //新增型号时，焦点失去需要显示
    @BindView(R.id.ll_zzfl2)
    LinearLayout ll_zzfl2;  //装置分类，不可编辑
    @BindView(R.id.lv_rjbb)
    MyListView lvRjbb;
    @BindView(R.id.rl_more_mk)
    public RelativeLayout rl_more_mk;
    @BindView(R.id.fragment_check_all_mk)
    public Button fragment_check_all_mk;
    @BindView(R.id.sv_all)
    MyScrollView svall;
    private ViewAddListener addListener;
    public DeviceRjbbAdapter adapter;
    private IDaoUtil util;

    /**
     * 必填项校验需要
     */
    @BindViews(value = {R.id.swm, R.id.zzmc, R.id.ltybzbb, R.id.zzcj, R.id.zzlb, R.id.zzxh, R.id.zzfl, R.id.zzlx
            , R.id.rjbb, R.id.jym, R.id.rjbbscsj, R.id.rjbb_lty, R.id.ccrq, R.id.bksl})
    List<TextView> textViews_key;
    @BindViews(value = {R.id.tv_swm, R.id.tv_zzmc, R.id.tv_ltybzbb, R.id.tv_zzcj, R.id.tv_zzlb, R.id.tv_zzxh, R.id.tv_zzfl, R.id.tv_zzlx
            , R.id.tv_rjbb, R.id.tv_jym, R.id.tv_rjbbscsj, R.id.tv_rjbb_lty, R.id.tv_ccrq, R.id.tv_bksl})
    List<TextView> textViews_more;
    public Map<String, TextView> map_key = new HashMap<>();
    public Map<String, TextView> map_more = new HashMap<>();
    public boolean isShow;
    public boolean isEdit; //是否为编辑
    public boolean isChooseXh; //是否选择型号

    public Details1() {
    }

    @SuppressLint("ValidFragment")
    public Details1(ViewAddListener addListener) {
        this.addListener = addListener;
    }

    /**
     * 获取当前厂家
     *
     * @return
     */
    public String getLty_zzcj() {
        return editTexts.get(1).getText().toString() + "";
    }

    /**
     * 获取当前类别
     *
     * @return
     */
    public String getLty_bhlb() {
        return textViews.get(1).getText().toString() + "";
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_details1;
    }

    @Override
    public void initview() {
        instance = this;
        util = new DaoUtil(getActivity());
        adapter = new DeviceRjbbAdapter(getActivity());
        lvRjbb.setAdapter(adapter);
        lvRjbb.setVisibility(View.GONE);
        svall.setOnScrollListener(this);
    }

    @Override
    public void initEvevt() {
        //厂家
        editTexts.get(1).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String cj = s.toString();
                String zzcj = DemoActivity.instance.fzbhsb != null ? DemoActivity.instance.fzbhsb.getCj() : null;

                if (zzcj != null && !cj.equals(zzcj)) {
                    editTexts.get(2).setText("");
                }
            }
        });

        //型号
//        editTexts.get(2).addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String xh = s.toString();
//                String sbxh = DemoActivity.instance.fzbhsb != null ? DemoActivity.instance.fzbhsb.getSbxh() : null;
//
//                if (isSix && is2013) {
//                    lty_xh = xh;
//                    if (Details8.instance != null) {
//                        Details8.instance.checkIsBg(ltysbxh, lty_xh, lty_rjbb);
//                    }
//                    setRjbb(xh, true, true);
//                } else if (ll_hassffmk.getVisibility() == View.GONE) {
//                    if (sbxh == null || sbxh != null && !xh.equals(sbxh)) {
//                        setRjbb(xh, true, true);
//                    }
//                } else {
//                    setRjbb(xh, true, true);
//                }
//            }
//        });

        editTexts.get(6).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ltysbxh != null && ltysbxh.getRjbb() != null) {
                    String ss = ltysbxh.getRjbb();
                    String str = s.toString();
                    if (!ss.equals(str)) {
                        lty_rjbb = str;
                    } else {
                        lty_rjbb = ss;
                    }
                    if (Details8.instance != null) {
                        Details8.instance.checkIsBg(ltysbxh, lty_xh, lty_rjbb);
                    }
                } else {
                    String str = editTexts.get(3).getText().toString();
                    if (lty_rjbb != null && !lty_rjbb.equals(str)) {
                        lty_rjbb = str;
                    }
                }
            }
        });

        rl_more_mk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoActivity.instance.showMk(1);
                ToastUtils.showLongToast(getActivity(), "跳转更多模块信息");
            }
        });
        fragment_check_all_mk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoActivity.instance.showMk(1);
                ToastUtils.showLongToast(getActivity(), "跳转更多模块信息");
            }
        });

        /**
         * 加载必填项监听
         */
        initOnFocusChangeListener(map_key, map_more, true, "装置基本信息");
    }

    /**
     * 加载必填项监听方法
     *
     * @param map_key
     * @param map_more
     * @param hasRjbb
     * @param checkName
     */
    public void initOnFocusChangeListener(final Map<String, TextView> map_key, final Map<String, TextView> map_more, final boolean hasRjbb, final String checkName) {
        for (final String s : map_more.keySet()) {
            if (map_more.get(s) == null)
                continue;
            map_more.get(s).addTextChangedListener(new TextWatcher() {
                String currentText;

                @Override
                public void beforeTextChanged(CharSequence str, int start, int count, int after) {
                    currentText = str.toString().trim();
                }

                @Override
                public void onTextChanged(CharSequence str, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable str) {
                    if (!isEdit && DemoActivity.instance.isCancel)
                        return;
                    String tmp = str.toString().trim();
                    if (s.equals("装置型号")) {
                        if (isSix && is2013) {
                            lty_xh = tmp;
                            if (Details8.instance != null) {
                                Details8.instance.checkIsBg(ltysbxh, lty_xh, lty_rjbb);
                            }

                            if (isChooseXh) {
                                setRjbb(tmp, false, true);
                                isChooseXh = false;
                                init();
                            } else {
                                setRjbb(tmp, true, true);
                            }

                            /**
                             * 必填项校验设计
                             */
                            checkBtx(true, true, isShow, map_key, map_more, checkName);
                        } else {
                            if (isChooseXh) {
                                setRjbb(tmp, false, true);
                                isChooseXh = false;
                                init();
                            } else {
                                setRjbb(tmp, true, true);
                            }
                            /**
                             * 必填项校验设计
                             */
                            checkBtx(true, hasRjbb, isShow, map_key, map_more, checkName);
                        }

                        if (!currentText.equals("") && tmp.equals("")) {
                            map_more.get(s).setBackground(getResources().getDrawable(R.drawable.device_detials_bg2));

                            /**
                             * 必填项校验设计
                             */
                            checkBtx(true, hasRjbb, isShow, map_key, map_more, checkName);
                        } else if (currentText.equals("") && !tmp.equals("")) {
                            map_more.get(s).setBackground(getResources().getDrawable(R.drawable.device_detials_bg));

                            /**
                             * 必填项校验设计
                             */
                            checkBtx(true, hasRjbb, isShow, map_key, map_more, checkName);
                        }
                    } else {
                        if (currentText.equals("") && !tmp.equals("")) {
                            map_more.get(s).setBackground(getResources().getDrawable(R.drawable.device_detials_bg));
                            /**
                             * 必填项校验设计
                             */
                            checkBtx(true, hasRjbb, isShow, map_key, map_more, checkName);
                        } else if (!currentText.equals("") && tmp.equals("")) {
                            map_more.get(s).setBackground(getResources().getDrawable(R.drawable.device_detials_bg2));
                            /**
                             * 必填项校验设计
                             */
                            checkBtx(true, hasRjbb, isShow, map_key, map_more, checkName);
                        }
                    }
                    currentText = tmp;
                }
            });
        }
    }

    @Override
    public void initData() {
        isEdit = getArguments().getBoolean("isEdit", false);
        setFirstData(isEdit);

        init();
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
            if (textViews_key.get(i).getId() == R.id.rjbb_lty) {
                tmp_key.put("六统一软件版本", textViews_key.get(i));
                tmp_value.put("六统一软件版本", textViews_more.get(i));
            } else {
                tmp_key.put(textViews_key.get(i).getText().toString(), textViews_key.get(i));
                tmp_value.put(textViews_key.get(i).getText().toString(), textViews_more.get(i));
            }
        }

        List<SaleAttributeVo> saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getFzData()) {
            if (saleAttributeNameVo.getName().equals("装置基本信息")) {
                saleVo = saleAttributeNameVo.getSaleVo();
                break;
            }
        }

        map_key.clear();
        map_more.clear();

        for (SaleAttributeVo saleAttributeVo : saleVo) {
            if (lvRjbb.getVisibility() == View.VISIBLE) {
                if (saleAttributeVo.getValue().equals("模块名称")) {
                    map_key.put(saleAttributeVo.getValue() + "-分模块", null);
                    continue;
                }
                if (saleAttributeVo.getValue().equals("校验码")
                        || saleAttributeVo.getValue().equals("生成时间")) {
                    map_key.put(saleAttributeVo.getValue() + "-分模块", null);
                    map_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "："));
                    map_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "："));
                    continue;
                }

                if (saleAttributeVo.getValue().equals("软件版本")) {
                    map_key.put(saleAttributeVo.getValue() + "-分模块", null);
                    map_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "："));
                    map_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "："));
                    map_key.put("六统一软件版本", tmp_key.get("六统一软件版本"));
                    map_more.put("六统一软件版本", tmp_value.get("六统一软件版本"));
                    continue;
                }

                if (tmp_key.containsKey(saleAttributeVo.getValue() + "：")) {
                    map_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "："));
                    map_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "："));
                }
            } else {
                if (tmp_key.containsKey(saleAttributeVo.getValue() + "：")) {
                    map_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "："));
                    map_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "："));

                    if (saleAttributeVo.getValue().equals("软件版本")) {
                        map_key.put("六统一软件版本", tmp_key.get("六统一软件版本"));
                        map_more.put("六统一软件版本", tmp_value.get("六统一软件版本"));
                    }
                }
            }
        }
    }

    /**
     * 初始化数据
     *
     * @param isEdit
     */
    private void setFirstData(boolean isEdit) {
        FZBHSB fzbhsb = DemoActivity.instance.fzbhsb;
        if (fzbhsb != null) { //找到设备或者从出厂信息库带出
            if (fzbhsb.getSbmc() != null) {
                editTexts.get(0).setText(fzbhsb.getSbmc());
            }
            if (fzbhsb.getSfltysb() != null) {
                isSix = fzbhsb.getSfltysb().equals("是");
            } else {
                isSix = false;
            }
            if (isSix) {
                cbTrue.setChecked(true);
                llLtybzbb.setVisibility(View.VISIBLE);
                if (fzbhsb.getLtybzbb() != null) {
                    textViews.get(0).setText(fzbhsb.getLtybzbb());
                }
                if (fzbhsb.getLtybzbb() != null) {
                    is2013 = fzbhsb.getLtybzbb().equals("2013版");
                }
                if (is2013 && !NoShowRjbb()) {
                    addListener.onChangeListener("ICD文件信息", "ADD");
                }
            } else {
                is2013 = false;
                cbTrue.setChecked(false);
                llLtybzbb.setVisibility(View.GONE);
                addListener.onChangeListener("ICD文件信息", "DEL");
            }

            if (fzbhsb.getBhfl() != null) {
                tv_zzfl2.setText(fzbhsb.getBhfl());
            }
            if (fzbhsb.getBhlx() != null) {
                tv_zzlx2.setText(fzbhsb.getBhlx());
            }

            if (fzbhsb.getCj() != null) {
                editTexts.get(1).setText(fzbhsb.getCj());
            }
            if (fzbhsb.getFzsblx() != null) {
                textViews.get(1).setText(fzbhsb.getFzsblx());
            }
            setZZLB(fzbhsb.getFzsblx() + "");
            if (fzbhsb.getSbxh() != null) {
                editTexts.get(2).setText(fzbhsb.getSbxh());
            }
            if (fzbhsb.getCcrq() != null) {
                textViews.get(3).setText(fzbhsb.getCcrq());
                ccrq = fzbhsb.getCcrq();
            }

            if (fzbhsb.getBksl() != 0) {
                editTexts.get(5).setText(fzbhsb.getBksl() + "");
            } else {
                editTexts.get(5).setText("");
            }

            usegddata = fzbhsb.getUsegddata();
            if (usegddata != null && !usegddata.equals("null") && !usegddata.equals("")) {
                if (usegddata.equals("true")) {
                    usegddata = "是";
                } else {
                    usegddata = "否";
                }
            } else {
                usegddata = "是";
            }

            if (fzbhsb.getSfsbm() != null && !fzbhsb.getSfsbm().equals("") && !DemoActivity.instance.Similar) {
                editTexts.get(7).setText(fzbhsb.getSfsbm());
            }

            List<Object> list;
            if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C") && !DemoActivity.instance.Similar) {
                tv_details1.setVisibility(View.GONE);
                list = (List<Object>) getActivity().getIntent().getSerializableExtra("BHXHRJBB");
            } else {
                tv_details1.setVisibility(View.VISIBLE);
                if (DemoActivity.instance.rzgl != null) {
                    tv_details1.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                } else {
                    tv_details1.setText("本条台账最后一次修改时间：");
                }
                list = util.getRJBBByCode(isSix, is2013, fzbhsb.getId() + "");
            }

            initFirstData(fzbhsb);
            String bhlx = fzbhsb.getBhlx() + "";
//            if (fzbhsb.getSbxh() != null && !fzbhsb.getSbxh().equals("") && codeMap.isEmpty()
//                    && !bhlx.equals("电磁型") && !bhlx.equals("晶体管") && !bhlx.equals("集成电路")) {
//                ll_hassffmk.setVisibility(View.VISIBLE);
//                ll_zzfl2.setVisibility(View.GONE);
//            } else {
//                ll_hassffmk.setVisibility(View.GONE);
//                ll_zzfl2.setVisibility(View.VISIBLE);
//            }
            if (fzbhsb.getSbxh() != null && !fzbhsb.getSbxh().equals("")) {
                ll_hassffmk.setVisibility(View.GONE);
                ll_zzfl2.setVisibility(View.VISIBLE);
            }

            rjbbList.clear();

            if (list != null && list.size() > 0 && !bhlx.equals("电磁型") && !bhlx.equals("晶体管")
                    && !bhlx.equals("集成电路")) {
                if (isSix && is2013) {
                    ltysbxh = (LTYSBXH) list.get(0);
                    llRjbb.setVisibility(View.GONE);
                    lvRjbb.setVisibility(View.GONE);
                    ll_rjbb_lty.setVisibility(View.VISIBLE);

                    editTexts.get(6).setText(ltysbxh.getRjbb());
                    lty_zzxh = ltysbxh.getBhxh();
                    lty_rjbb = ltysbxh.getRjbb();
                    lty_xh = ltysbxh.getBhxh();
                } else if (!NoShowRjbb()) {
                    ll_rjbb_lty.setVisibility(View.GONE);
                    rjbbListAll.clear();
                    for (Object o : list) {
                        BHXHRJBB bhxhrjbb = (BHXHRJBB) o;
                        rjbbList.add(bhxhrjbb);
                        rjbbListAll.add(bhxhrjbb);
                    }
                    if (rjbbList.size() > 1) {
                        cb_xh_sffmk.setChecked(true);
                        llRjbb.setVisibility(View.GONE);
//                        lvRjbb.setVisibility(View.VISIBLE);
                        if (rjbbList.size() > 5) {
                            rl_more_mk.setVisibility(View.VISIBLE);
//                            addListener.onChangeListener("模块信息", "ADD");
                            DemoActivity.instance.showMk(2);
//                            for (int i = 5; i < rjbbList.size(); i++) {
//                                rjbbList.remove(i);
//                            }
                            rjbbList.clear();
                            for (int i = 0; i < 5; i++) {
                                rjbbList.add(rjbbListAll.get(i));
                            }
                        }
                        adapter.setDatas(rjbbList, isEdit, false);
                    } else if (rjbbList.size() == 1) {
                        if (rjbbList.get(0).getBblx().equals("非六统一，分模块")) {
                            cb_xh_sffmk.setChecked(true);
                            llRjbb.setVisibility(View.GONE);
                            lvRjbb.setVisibility(View.VISIBLE);
                            adapter.setDatas(rjbbList, isEdit, false);
                        } else {
                            cb_xh_sffmk.setChecked(false);
                            llRjbb.setVisibility(View.VISIBLE);
                            lvRjbb.setVisibility(View.GONE);
                            if (rjbbList.get(0).getBb() != null) {
                                editTexts.get(3).setText(rjbbList.get(0).getBb());
                            }
                            if (rjbbList.get(0).getJym() != null) {
                                editTexts.get(4).setText(rjbbList.get(0).getJym() + "");
                            }
                            if (rjbbList.get(0).getSCSJ() != null) {
                                textViews.get(2).setText(rjbbList.get(0).getSCSJ() + "");
                            }

                            rjbbList.clear();//控件设置完值后清空集合
                        }
                    }
                } else {
                    llRjbb.setVisibility(View.GONE);
                    lvRjbb.setVisibility(View.GONE);
                    ll_rjbb_lty.setVisibility(View.GONE);
                }
            } else {
                cb_xh_sffmk.setChecked(false);
                setRjbb(fzbhsb.getSbxh(), false, isEdit);
            }
        } else {
            if (!DemoActivity.instance.isSwid) {
                editTexts.get(7).setText(getActivity().getIntent().getStringExtra("sbsbdm"));
            }

            cbTrue.setChecked(false);
            llLtybzbb.setVisibility(View.GONE);
            isSix = false;
            is2013 = false;
            addListener.onChangeListener("ICD文件信息", "DEL");
            setZZLB("");

            tv_details1.setVisibility(View.GONE);
            llRjbb.setVisibility(View.VISIBLE);
            lvRjbb.setVisibility(View.GONE);
        }
    }

    @Override
    public void initReceiver(boolean isEdit) {
        adapter.setDatas(rjbbList, isEdit, false);
        this.isEdit = isEdit;
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

            cbTrue.setEnabled(true);
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
                cbTrue.setEnabled(false);

                if (DemoActivity.instance.rzgl != null &&
                        DemoActivity.instance.rzgl.getCZSJ() != null &&
                        !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                    tv_details1.setVisibility(View.VISIBLE);
                    tv_details1.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }
                //清楚所有的数据，重新加载原始数据
                rjbbList.clear();
                ltysbxh = null;
                ltysbxh_add = null;
                codeMap.clear();
                bblxMap.clear();
                dateMap.clear();
                ll_hassffmk.setVisibility(View.GONE);

                setFirstData(isEdit);
            } else { //正常保存
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
                cbTrue.setEnabled(false);

                if (DemoActivity.instance.rzgl != null &&
                        DemoActivity.instance.rzgl.getCZSJ() != null &&
                        !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                    tv_details1.setVisibility(View.VISIBLE);
                    tv_details1.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }
            }

//            /**
//             * 重新初始化校验项
//             */
//            init();
        }
        /**
         * 必填项校验设计
         */
        checkBtx(isEdit, true, isShow, map_key, map_more, "装置基本信息");
    }

    /**
     * 必填项校验设计方法
     *
     * @param isEdit
     * @param hasRjbb
     * @param isShow
     * @param map_key
     * @param map_more
     * @param checkName
     */

    public void checkBtx(boolean isEdit, boolean hasRjbb, boolean isShow, Map<String, TextView> map_key
            , Map<String, TextView> map_more, String checkName) {
        if (isShow == true)
            isShow = false;
        if (isEdit) {
            for (String s : map_more.keySet()) {
                if (hasRjbb && lvRjbb.getVisibility() == View.VISIBLE) {
                    if (s.equals("软件版本") || s.equals("校验码") || s.equals("生成时间"))
                        continue;
                }

                if (rjbbList != null && rjbbList.size() > 0) {
                    if (s.contains("模块名称") || s.contains("软件版本") || s.contains("校验码") || s.contains("生成时间"))
                        continue;
                }


                if (hasRjbb && llRjbb.getVisibility() == View.VISIBLE) {
                    if (s.equals("模块名称-分模块") || s.equals("软件版本-分模块") || s.equals("校验码-分模块") || s.equals("生成时间-分模块"))
                        continue;
                }

                if (NoShowRjbb() || ll_rjbb_lty.getVisibility() == View.VISIBLE) {
                    if (s.equals("六统一软件版本") && isSix && is2013) {

                    } else if (s.contains("模块名称") || s.contains("软件版本") ||
                            s.contains("校验码") || s.contains("生成时间")) {
                        continue;
                    }
                } else {
                    if (s.equals("六统一软件版本") && !is2013)
                        continue;
                }

                if (!isSix && s.equals("六统一标准版本")) {
                    continue;
                }

                map_key.get(s).setCompoundDrawables(null, null, null, null);
                if (map_more.get(s).getText().toString().trim().equals("")) {
                    if (s.equals("装置分类") && !tv_zzfl2.getText().toString().equals("")) {
                        continue;
                    }
                    if (s.equals("装置类型") && !tv_zzfl2.getText().toString().equals("")) {
                        continue;
                    }
                    map_more.get(s).setBackground(getResources().getDrawable(R.drawable.device_detials_bg2));
                    map_more.get(s).setHint("必填项");
                    isShow = true;
                } else {
                    if (!s.equals("装置分类") && !s.equals("装置类型"))
                        map_more.get(s).setBackground(getResources().getDrawable(R.drawable.device_detials_bg));
                }
            }
        } else {
            for (String s : map_more.keySet()) {
                if (hasRjbb && lvRjbb.getVisibility() == View.VISIBLE) {
                    if (s.equals("软件版本") || s.equals("校验码") || s.equals("生成时间"))
                        continue;
                }

                if (rjbbList != null && rjbbList.size() > 0) {
                    if (s.contains("模块名称") || s.contains("软件版本") || s.contains("校验码") || s.contains("生成时间"))
                        continue;
                }


                if (hasRjbb && llRjbb.getVisibility() == View.VISIBLE) {
                    if (s.equals("模块名称-分模块") || s.equals("软件版本-分模块") || s.equals("校验码-分模块") || s.equals("生成时间-分模块"))
                        continue;
                }

                if (NoShowRjbb() || ll_rjbb_lty.getVisibility() == View.VISIBLE) {
                    if (s.contains("模块名称") || s.contains("软件版本") || s.contains("校验码") || s.contains("生成时间"))
                        continue;
                } else {
                    if (s.contains("六统一软件版本"))
                        continue;
                }
                if (!isSix && s.contains("六统一标准版本")) {
                    continue;
                }
                if (s.equals("装置分类") || s.equals("装置类型")) {
                    continue;
                }

                if (map_more.get(s).getText().toString().trim().equals("")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.tanhao);
                    drawable.setBounds(0, 0, 25, 25);
                    map_key.get(s).setCompoundDrawables(drawable, null, null, null);
                    isShow = true;
                }
            }
        }
        if (hasRjbb)
            DemoActivity.instance.checkFZ(checkName, isShow);
        else
            DemoActivity.instance.check(checkName, isShow);
    }

    /**
     * 装置类别带出部分信息
     *
     * @param zzlb
     */
    private void setZZLB(String zzlb) {
        if (zzlb.equals("合并单元")) {
            addListener.onChangeListener("MU附加信息", "HBDY");
        } else if (zzlb.equals("合并单元智能终端集成")) {
            addListener.onChangeListener("MU附加信息", "HBDY2");
        } else if (zzlb.equals("收发信机")) {
            addListener.onChangeListener("载波机附加信息", "SFXJ");
        } else if (zzlb.equals("智能终端")) {
            addListener.onChangeListener("智能终端附加信息", "ZNZD");
        } else if (zzlb.equals("交换机")) {
            addListener.onChangeListener("交换机附加信息", "JHJ");
        } else {
            addListener.onChangeListener("ZZLB", "DEL");
        }
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

    @OnClick({R.id.cb_xh_sffmk, R.id.tv_zzmc, R.id.cb_true, R.id.tv_ltybzbb, R.id.tv_zzlb, R.id.tv_rjbbscsj, R.id.tv_ccrq
            , R.id.iv_ltybzbb, R.id.iv_zzcj, R.id.iv_zzlb, R.id.iv_zzxh, R.id.iv_rjbb, R.id.iv_jym, R.id.iv_rjbbscsj, R.id.iv_ccrq, R.id.iv_zzmc
            , R.id.tv_zzfl, R.id.tv_zzlx, R.id.iv_zzfl, R.id.iv_zzlx, R.id.tv_rjbb_lty, R.id.iv_rjbb_lty, R.id.iv_swm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_zzmc:  //自动生成装置名称
                String zzmc = DemoActivity.instance.czcs.getCzmc();
                String dydj = Details2.instance.dydj != null ? Details2.instance.dydj : "";
                String ycsbmc = Details2.instance.ycsbmc != null ? Details2.instance.ycsbmc : "";
                String tb = Details3.instance.tb != null ? Details3.instance.tb : "";
                String zzxh = editTexts.get(2).getText().toString();
                String zzlb = textViews.get(1).getText().toString();
                if (!dydj.equals("")) {
                    dydj += "kV";
                }
//                当检测到一次设备是主变压器、母线，二次设备为辅助装置(合并单元、智能终端、合智一体)时，
//                设备名称双击产生程序应将电压等级放在一次设备后面，
//                并且套别应显示“第1套本体、第2套本体、本体（非电量）、第1套电压、第2套电压”。
                //装置名称：变电站名称+一次设备名称+电压等级+装置型号+装置类别。（一次设备类型为“变压器”）
                String ycsblx = Details2.instance.getYcsblx();
                if (!ycsblx.equals("") && (ycsblx.equals("变压器") || ycsblx.equals("母线")) && !zzlb.equals("") && (zzlb.equals("合并单元") || zzlb.equals("智能终端") || zzlb.equals("合并单元智能终端集成"))) {
                    editTexts.get(0).setText(zzmc + ycsbmc + dydj + tb + zzxh + zzlb);
                } else {
                    editTexts.get(0).setText(zzmc + dydj + ycsbmc + tb + zzxh + zzlb);
                }
                break;
            case R.id.cb_xh_sffmk:
                setRjbb("cb_xh_sffmk", true, true);

                init();
                /**
                 * 必填项校验设计
                 */
                checkBtx(true, true, isShow, map_key, map_more, "装置基本信息");
                break;
            case R.id.tv_zzmc:
                break;
            case R.id.cb_true:
                if (cbTrue.isChecked()) {//选中
                    llLtybzbb.setVisibility(View.VISIBLE);
                    textViews.get(0).setText("");
                    isSix = true;

                    if (!map_key.containsKey("六统一标准版本"))
                        map_key.put("六统一标准版本", textViews_key.get(2));
                    if (!map_more.containsKey("六统一标准版本"))
                        map_more.put("六统一标准版本", textViews_more.get(2));
                    checkBtx(true, true, isShow, map_key, map_more, "装置基本信息");
                } else { //未选中
                    String ltybzbb = textViews.get(0).getText().toString() + "";
                    if (ltybzbb.equals("2013版")) {
                        editTexts.get(2).setText("");
                    }
                    llLtybzbb.setVisibility(View.GONE);
                    textViews.get(0).setText("");
                    isSix = false;
                    addListener.onChangeListener("ICD文件信息", "DEL");
                    if (map_key.containsKey("六统一标准版本"))
                        map_key.remove("六统一标准版本");
                    if (map_more.containsKey("六统一标准版本"))
                        map_more.remove("六统一标准版本");
                    checkBtx(true, true, isShow, map_key, map_more, "装置基本信息");
                }
                break;
            case R.id.tv_ltybzbb:
            case R.id.iv_ltybzbb:
                setIntentData("六统一标准版本", 0);
                break;
            case R.id.tv_zzlb:
            case R.id.iv_zzlb:
                setIntentData("装置类别", 0);
                break;
            case R.id.tv_rjbbscsj:
            case R.id.iv_rjbbscsj:
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        textViews.get(2).setText(date);

//                        /**
//                         * 必填项校验设计
//                         */
//                        checkBtx(true, true, isShow, map_key, map_more, "装置基本信息");
                    }
                });
                break;
            case R.id.tv_ccrq:
            case R.id.iv_ccrq:
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        if (Details2.instance.tyrq != null && !Details2.instance.tyrq.equals("")
                                && date != null && !date.equals("")) {
                            if (TimeUtil.timeCompare(date, Details2.instance.tyrq) > 0) {
                                ToastUtils.showLongToast(getActivity(), "投运日期不能早于出厂日期");
                                return;
                            }
                        }
                        textViews.get(3).setText(date);
                        ccrq = date;

//                        /**
//                         * 必填项校验设计
//                         */
//                        checkBtx(true, true, isShow, map_key, map_more, "装置基本信息");
                    }
                });
                break;
            case R.id.iv_zzcj:
                setIntentData("制造厂家", 0);
                break;
            case R.id.iv_zzxh:
                setIntentData("装置型号", 0);
                break;
            case R.id.iv_rjbb:
                setIntentData("软件版本", 0);
                break;
            case R.id.iv_jym:
                setIntentData("校验码", 0);
                break;
            case R.id.tv_zzfl:
            case R.id.iv_zzfl:
                setIntentData("装置分类", 0);
                break;
            case R.id.tv_zzlx:
            case R.id.iv_zzlx:
                setIntentData("装置类型", 0);
                break;
            case R.id.iv_rjbb_lty:
                setIntentData("软件版本", 0);
                break;
            case R.id.iv_swm://设备识别代码
                Intent startScan = new Intent(getActivity(), CaptureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("request", "xqsbm");
                startScan.putExtras(bundle);
                startActivityForResult(startScan, 99);
                break;
        }
    }

    /**
     * @param type 类型
     */
    public void setIntentData(String type, int requestCode) {
        Map<String, Object> map = new HashMap<>();
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("type", type);
        boolean isSix = cbTrue.isChecked();
        boolean is2013 = textViews.get(0).getText().toString().equals("2013版");
        map.put("isSIX", isSix);
        map.put("is2013", is2013);
        if (requestCode == 0) { //六统一或非六统一不分模块
            if (type.equals("装置型号")) {
                map.put("zzcj", editTexts.get(1).getText().toString() + "");
                map.put("bhlb", textViews.get(1).getText().toString() + "");
                map.put("czlx", DemoActivity.instance.czcs.getBdzlx());
                map.put("dydj", Details2.instance.dydj + "");
                map.put("usegddate", usegddata + "");
            } else if (type.equals("软件版本")) {
                if (isSix && is2013) {
                    map.put("bhxh", editTexts.get(2).getText().toString());
                    map.put("zzcj", editTexts.get(1).getText().toString() + "");
                    map.put("bhlb", textViews.get(1).getText().toString() + "");
                } else {
                    map.put("bhxh", codeMap.get(editTexts.get(2).getText().toString()));
                    map.put("bblx", bblxMap.get(editTexts.get(2).getText().toString()));
                }
            } else if (type.equals("校验码")) {
                map.put("bb", editTexts.get(3).getText().toString());
                if (bbToCode.size() > 0 && bbToCode.containsKey(editTexts.get(3).getText().toString())) {
                    map.put("bhxh", bbToCode.get(editTexts.get(3).getText().toString()));
                } else {
                    map.put("bhxh", codeMap.get(editTexts.get(2).getText().toString()));
                }
            }
        } else if (requestCode == 1) { //分模块有多个软件版本
            if (type.equals("模块名称")) {
                map.put("selectCode", codeMap.get(editTexts.get(2).getText().toString()));
            } else if (type.equals("软件版本")) {
                map.put("bhxh", codeMap.get(editTexts.get(2).getText().toString()));
                map.put("mkmc", adapter.getMkmc());
            } else if (type.equals("校验码")) {
                map.put("bb", adapter.getBb());
                if (bbToCode.size() > 0 && bbToCode.containsKey(adapter.getBb())) {
                    map.put("bhxh", bbToCode.get(adapter.getBb()));
                } else {
                    map.put("bhxh", codeMap.get(editTexts.get(2).getText().toString()));
                }
                map.put("mkmc", adapter.getMkmc());
            }
        }

        intent.putExtra("conditions", (Serializable) map);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 99) {
                //实物码返回取值
                Bundle bunde = data.getExtras();
                String value = bunde.getString("result") + "";
                if (value != null && !value.equals("")) {
                    editTexts.get(7).setText(value + "");
                }
                return;
            }
            String type = data.getStringExtra("puttype");
            String value = data.getStringExtra("result");
            switch (type) {
                case "六统一标准版本":
                    if (!value.equals(textViews.get(0).getText().toString())) {
                        String bblx = "";
                        if (!editTexts.get(2).getText().toString().equals("")) {
                            bblx = bblxMap.get(editTexts.get(2).getText().toString()) + "";
                        }
                        textViews.get(0).setText(value);
                        if (value.equals("2013版")) {
                            if (!bblx.equals("六统一设备")) {
                                editTexts.get(2).setText("");
                            }
                            is2013 = true;
                            addListener.onChangeListener("ICD文件信息", "ADD");
                        } else {
                            if (bblx.equals("六统一设备")) {
                                editTexts.get(2).setText("");
                            }
                            is2013 = false;
                            addListener.onChangeListener("ICD文件信息", "DEL");
                        }

//                        /**
//                         * 重新初始化必填项
//                         */
//                        init();

                        checkBtx(true, true, isShow, map_key, map_more, "装置基本信息");
                    }
                    break;
                case "制造厂家":
                    if (!value.equals(editTexts.get(1).getText().toString())) {
                        editTexts.get(1).setFocusable(true);
                        editTexts.get(2).setText("");
                        editTexts.get(1).setText(value);
                        editTexts.get(1).setFocusable(false);
                    }
                    break;
                case "装置类别":
                    if (!value.equals(textViews.get(1).getText().toString())) {
                        editTexts.get(2).setText("");
                        DemoActivity.instance.isfirstChange = true;
                        textViews.get(1).setText(value);
                        Map<String, String> keyMap = new HashMap<>();
                        Map<String, String> txtMap = AppUtils.setTxt(keyMap, "", false);

                        String tbms = txtMap.get("tbms") + "";
                        //在选择套别模式的情况下，装置类别发生变化，运行装置信息中的套别就要清空
                        if (tbms.equals("是")) {
                            Details3.instance.setTb();
                            if (value.equals("合并单元") || value.equals("智能终端") || value.equals("合并单元智能终端集成")) {
                                String ycsblx = Details2.instance.getYcsblx();
                                if (!ycsblx.equals("") && (ycsblx.equals("变压器") || ycsblx.equals("母线"))) {
                                    ToastUtils.showLongToast(getActivity(), "装置名称生成规则发生变化，请重新生成");
                                    //需要强制清空装置名称
                                    //  Details1.instance.setZzmc();
                                }
                            }
                        }
                        setZZLB(value);
                    }
                    break;
                case "装置型号":
                    if (!value.equals(editTexts.get(2).getText().toString())) {
                        isChooseXh = true;
                        editTexts.get(2).setText(value);
                        if (value == null || value.equals("")) {
                            return;
                        }
                        //自动带出分类和类型
                        ll_zzfl2.setVisibility(View.VISIBLE);
                        if (isSix && is2013) {
                            lty_xh = value;
                            Details8.instance.checkIsBg(ltysbxh, lty_xh, lty_rjbb);
                            LTYSBXH ltysbxh = (LTYSBXH) util.getBHXHByCode(codeMap.get(value), isSix, is2013);
                            tv_zzfl2.setText(ltysbxh.getBhfl());
                            tv_zzlx2.setText(ltysbxh.getBhlx());
                        } else {
                            BHSBXHB bhsbxhb = (BHSBXHB) util.getBHXHByCode(codeMap.get(value), isSix, is2013);
                            tv_zzfl2.setText(bhsbxhb.getBhfl());
                            tv_zzlx2.setText(bhsbxhb.getBhlx());
                        }
                    }
                    break;
                case "装置分类":
                    textViews.get(4).setText(value);
                    break;
                case "装置类型":
                    textViews.get(5).setText(value);
                    if (value != null && !value.equals("")) {
                        setRjbb(value, true, true);
                    }
                    break;
                case "模块名称":
                    if (rjbbList.get(adapter.getPosition()).getMkmc() != null &&
                            !rjbbList.get(adapter.getPosition()).getMkmc().equals(value)) {
                        rjbbList.get(adapter.getPosition()).setBb("");
                        rjbbList.get(adapter.getPosition()).setJym("");
                    }
                    rjbbList.get(adapter.getPosition()).setMkmc(value);
                    adapter.setDatas(rjbbList, true, false);
                    break;
                case "软件版本":
                    if (requestCode == 0) {
                        if (isSix && is2013) {
                            editTexts.get(6).setText(value);
                            lty_rjbb = value;
                            if (Details8.instance != null) {
                                Details8.instance.checkIsBg(ltysbxh, lty_xh, lty_rjbb);
                            }
                        } else {
                            if (!value.equals(editTexts.get(3).getText().toString())) {
                                editTexts.get(4).setText("");
                            }
                            editTexts.get(3).setText(value);
                        }
                    } else if (requestCode == 1) {
                        if (!value.equals(rjbbList.get(adapter.getPosition()).getBb())) {
                            rjbbList.get(adapter.getPosition()).setJym("");
                        }
                        rjbbList.get(adapter.getPosition()).setBb(value);
                        adapter.setDatas(rjbbList, true, false);
                    }
                    break;
                case "校验码":
                    if (requestCode == 0) {
                        editTexts.get(4).setText(value);
                    } else if (requestCode == 1) {
                        rjbbList.get(adapter.getPosition()).setJym(value);
                        adapter.setDatas(rjbbList, true, false);
                    }
                    break;
            }
//            checkBtx(true, true, isShow, map_key, map_more, "装置基本信息");
        }
    }

    /**
     * 初始化从列表进入部分选项的值
     */
    private void initFirstData(FZBHSB fzbhsb) {
        String bdzlx = fzbhsb.getBdzlx();
        if (bdzlx == null || bdzlx.equals("")) {
            bdzlx = DemoActivity.instance.czcs.getBdzlx();
        }
        List<Object> list = util.getBHXH(isSix, is2013, fzbhsb.getCj(), fzbhsb.getFzsblx(),
                bdzlx, fzbhsb.getDydj() + "", usegddata, fzbhsb.getSbxh());
        if (list != null && list.size() > 0) {
            if (isSix && is2013) {
                LTYSBXH ltysbxh = (LTYSBXH) list.get(0);
                codeMap.put(ltysbxh.getBhxh(), ltysbxh.getCode());
                bblxMap.put(ltysbxh.getBhxh(), ltysbxh.getBblx());

                if (tv_zzfl2.getText().toString().equals("")) {
                    tv_zzfl2.setText(ltysbxh.getBhfl());
                }
                if (tv_zzlx2.getText().toString().equals("")) {
                    tv_zzlx2.setText(ltysbxh.getBhlx());
                }
            } else {
                BHSBXHB bhsbxhb = (BHSBXHB) list.get(0);
                codeMap.put(bhsbxhb.getSbxh(), bhsbxhb.getCode());
                bblxMap.put(bhsbxhb.getSbxh(), bhsbxhb.getBblx());

                if (tv_zzfl2.getText().toString().equals("")) {
                    tv_zzfl2.setText(bhsbxhb.getBhfl());
                }
                if (tv_zzlx2.getText().toString().equals("")) {
                    tv_zzlx2.setText(bhsbxhb.getBhlx());
                }
            }
        }
    }

    /**
     * 更改装置型号时需重置这些信息
     *
     * @param value
     */
    private void setRjbb(String value, boolean isAdd, boolean isEdit) {
        //根据装置类型判断是否要显示软件版本
        String zzlx = "";
        if (ll_zzfl2.getVisibility() == View.VISIBLE) {
            zzlx = tv_zzlx2.getText().toString();
        } else if (isAdd) {
            zzlx = textViews.get(5).getText().toString();
        }

        if (textViews.get(4).getText().toString().equals("") && !tv_zzfl2.getText().toString().equals("")) {
            textViews.get(4).setText(tv_zzfl2.getText().toString());
        }

        textViews.get(5).setText(zzlx);

        if (zzlx.equals("电磁型") || zzlx.equals("晶体管") || zzlx.equals("集成电路")) {
            llRjbb.setVisibility(View.GONE);
            lvRjbb.setVisibility(View.GONE);
            if (isAdd) {
                ll_hassffmk.setVisibility(View.VISIBLE);
                ll_xh_sffmk.setVisibility(View.GONE);
                ll_zzfl2.setVisibility(View.GONE);
                if (isSix && is2013) {
                    textViews.get(5).setText("");
                } else {
                    textViews.get(5).setText(zzlx);
                }
            } else {
                ll_hassffmk.setVisibility(View.GONE);
                ll_zzfl2.setVisibility(View.VISIBLE);
            }
            return;
        }

        if (value.equals("电磁型") || value.equals("晶体管") || value.equals("集成电路")) {
            llRjbb.setVisibility(View.GONE);
            lvRjbb.setVisibility(View.GONE);
            if (isAdd) {
                ll_hassffmk.setVisibility(View.VISIBLE);
                ll_xh_sffmk.setVisibility(View.GONE);
                ll_zzfl2.setVisibility(View.GONE);
                if (isSix && is2013) {
                    textViews.get(5).setText("");
                } else {
                    textViews.get(5).setText(value);
                }
            } else {
                ll_hassffmk.setVisibility(View.GONE);
                ll_zzfl2.setVisibility(View.VISIBLE);
            }
            return;
        }

        if (isSix && is2013) {
            if (isAdd) {
                ll_hassffmk.setVisibility(View.VISIBLE);
                ll_xh_sffmk.setVisibility(View.GONE);
                ll_zzfl2.setVisibility(View.GONE);
            } else {
                ll_hassffmk.setVisibility(View.GONE);
                ll_zzfl2.setVisibility(View.VISIBLE);
            }
            if (textViews.get(5).getText().equals("电磁型")
                    || textViews.get(5).getText().equals("晶体管")
                    || textViews.get(5).getText().equals("集成电路")) {
                textViews.get(5).setText("");
            }

            ll_rjbb_lty.setVisibility(View.VISIBLE);
            llRjbb.setVisibility(View.GONE);
            lvRjbb.setVisibility(View.GONE);
            lty_zzxh = value;
        } else {
            ll_rjbb_lty.setVisibility(View.GONE);
            String bblx = "";
            if (isAdd) {
                if (!value.equals("")) {
                    ll_hassffmk.setVisibility(View.VISIBLE);
                    ll_xh_sffmk.setVisibility(View.VISIBLE);
                    ll_zzfl2.setVisibility(View.GONE);
                }
                bblx = cb_xh_sffmk.isChecked() ? "非六统一，分模块" : "非六统一，不分模块";
            } else {
//                rjbbList.clear();  //型号选择，需要清软件版本
                ll_hassffmk.setVisibility(View.GONE);
                ll_zzfl2.setVisibility(View.VISIBLE);
                bblx = bblxMap.get(value) != null ? bblxMap.get(value) : "非六统一，不分模块";
            }
            if (bblx.equals("非六统一，分模块")) { //分模块
                cb_xh_sffmk.setChecked(true);
                llRjbb.setVisibility(View.GONE);
                lvRjbb.setVisibility(View.VISIBLE);
                if (rjbbList.size() <= 0) {
                    BHXHRJBB rjbb = new BHXHRJBB();
                    rjbb.setBblx("非六统一，分模块");
                    rjbb.setED_TAG("C");
                    rjbb.setSfqy("Y");
                    if (!isAdd) {
                        rjbb.setBhxhcode(codeMap.get(value));
                    }
                    rjbbList.add(rjbb);
                    adapter.setDatas(rjbbList, isEdit, false);
                } else {
                    for (BHXHRJBB bhxhrjbb : rjbbList) {
                        bhxhrjbb.setBblx("非六统一，分模块");
                        bhxhrjbb.setED_TAG("C");
                        bhxhrjbb.setSfqy("Y");
                    }
                    adapter.setDatas(rjbbList, isEdit, false);
                }
            } else { //剩下的都是不分模块
                cb_xh_sffmk.setChecked(false);
                llRjbb.setVisibility(View.VISIBLE);
                lvRjbb.setVisibility(View.GONE);
            }
        }
        //模块跟随适配器隐藏
        if (lvRjbb.getVisibility() == View.GONE) {
            rl_more_mk.setVisibility(View.GONE);
        }
        if (NoShowRjbb()) {
            llRjbb.setVisibility(View.GONE);
            lvRjbb.setVisibility(View.GONE);
            rl_more_mk.setVisibility(View.GONE);
            ll_xh_sffmk.setVisibility(View.GONE);
//            rjbbList.clear();
        }
    }

    public String getZzlb() {
        String zzlb = textViews.get(1).getText().toString();
        if (zzlb.equals("")) {
            return "";
        } else {
            return zzlb;
        }
    }

    public boolean NoShowRjbb() {
//        Constants.zzlxNoRjbb = new String[]{"", ""};
        boolean noshowrjbb = false;
        String zzlb = textViews.get(1).getText().toString();
        for (String zzlxs : Constants.zzlxNoRjbb) {
            if (zzlb.equals(zzlxs + "")) {
                noshowrjbb = true;
                break;
            }
        }
//        noshowrjbb = false;
        return noshowrjbb;
    }

    public String getBhxh() {
        String bhxh = editTexts.get(2).getText().toString() + "";
        return bhxh;
    }

    //同步多模块信息，超过五个。
    public void updataMoreMk() {
        if (rjbbList.size() == 5 && Details11.instance.list_one_data.size() > 5) {
            for (int i = 0; i < 5; i++) {
                if (!(Details11.instance.list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null") &&
                        !(rjbbList.get(i).getMkmc() + "").equalsIgnoreCase("null") &&
                        !Details11.instance.list_one_data.get(i).getMkmc().equals(rjbbList.get(i).getMkmc() + "") ||
                        !(Details11.instance.list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null") &&
                                (rjbbList.get(i).getMkmc() + "").equalsIgnoreCase("null")) {
                    rjbbList.add(i, Details11.instance.list_one_data.get(i));
                } else if (!(Details11.instance.list_one_data.get(i).getBb() + "").equalsIgnoreCase("null") &&
                        !(rjbbList.get(i).getBb() + "").equalsIgnoreCase("null") &&
                        !Details11.instance.list_one_data.get(i).getBb().equals(rjbbList.get(i).getBb() + "") ||
                        !(Details11.instance.list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null") &&
                                (rjbbList.get(i).getMkmc() + "").equalsIgnoreCase("null")) {
                    rjbbList.add(i, Details11.instance.list_one_data.get(i));
                } else if (!(Details11.instance.list_one_data.get(i).getJym() + "").equalsIgnoreCase("null") &&
                        !(rjbbList.get(i).getJym() + "").equalsIgnoreCase("null") &&
                        !Details11.instance.list_one_data.get(i).getJym().equals(rjbbList.get(i).getJym() + "") ||
                        !(Details11.instance.list_one_data.get(i).getJym() + "").equalsIgnoreCase("null") &&
                                (rjbbList.get(i).getJym() + "").equalsIgnoreCase("null")) {
                    rjbbList.add(i, Details11.instance.list_one_data.get(i));
                } else if (!(Details11.instance.list_one_data.get(i).getSCSJ() + "").equalsIgnoreCase("null") &&
                        !(rjbbList.get(i).getSCSJ() + "").equalsIgnoreCase("null") &&
                        !Details11.instance.list_one_data.get(i).getSCSJ().equals(rjbbList.get(i).getSCSJ() + "") ||
                        !(Details11.instance.list_one_data.get(i).getSCSJ() + "").equalsIgnoreCase("null") &&
                                (rjbbList.get(i).getSCSJ() + "").equalsIgnoreCase("null")) {
                    rjbbList.add(i, Details11.instance.list_one_data.get(i));
                }
            }
        } else if (rjbbList.size() == 5 && Details11.instance.list_one_data.size() <= 5) {
            rjbbList.clear();
            rl_more_mk.setVisibility(View.GONE);
            for (BHXHRJBB list_one_da : Details11.instance.list_one_data) {
                rjbbList.add(list_one_da);
            }
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 统一保存校验方法
     */
    public boolean saveDetails1() {

//        //重新初始化校验项防止六统一2013版切换
//        init();

        String zzmc = editTexts.get(0).getText().toString();

        String ltybzbb = textViews.get(0).getText().toString();
        if (zzmc.equals("")) {
            DemoActivity.instance.fzbhsb.setSbmc(zzmc);
            if (map_key.containsKey("装置名称")) {
                ToastUtils.showLongToast(getActivity(), "装置名称不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setSbmc(zzmc);
        }
        if (isSix) {
            DemoActivity.instance.fzbhsb.setSfltysb("是");
            if (ltybzbb.equals("")) {
                DemoActivity.instance.fzbhsb.setLtybzbb(ltybzbb);
                if (map_key.containsKey("六统一标准版本")) {
                    ToastUtils.showLongToast(getActivity(), "六统一标准版本不能为空");
                    return false;
                }
            } else {
                DemoActivity.instance.fzbhsb.setLtybzbb(ltybzbb);
            }
        } else {
            DemoActivity.instance.fzbhsb.setSfltysb("否");
            DemoActivity.instance.fzbhsb.setLtybzbb(null);
        }
        String zzcj = editTexts.get(1).getText().toString();
        if (zzcj.equals("")) {
            DemoActivity.instance.fzbhsb.setCj(zzcj);
            if (map_key.containsKey("制造厂家")) {
                ToastUtils.showLongToast(getActivity(), "制造厂家不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setCj(zzcj);
        }
        String zzlb = textViews.get(1).getText().toString();

        if (zzlb.equals("")) {
            DemoActivity.instance.fzbhsb.setFzsblx(zzlb);
            if (map_key.containsKey("装置类别")) {
                ToastUtils.showLongToast(getActivity(), "装置类别不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setFzsblx(zzlb);
        }
        String zzxh = editTexts.get(2).getText().toString();
        if (zzxh.equals("")) {
            DemoActivity.instance.fzbhsb.setSbxh(zzxh);
            if (map_key.containsKey("装置型号")) {
                ToastUtils.showLongToast(getActivity(), "装置型号不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setSbxh(zzxh);
        }
        String ccrq = textViews.get(3).getText().toString();
        if (ccrq.equals("")) {
            DemoActivity.instance.fzbhsb.setCcrq(ccrq);
            if (map_key.containsKey("出厂日期")) {
                ToastUtils.showLongToast(getActivity(), "出厂日期不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setCcrq(ccrq);
        }

        String bksl = editTexts.get(5).getText().toString().trim();
        if (bksl.equals("") || bksl.equals("0")) {
            DemoActivity.instance.fzbhsb.setBksl(0);
            if (map_key.containsKey("板卡数量")) {
                ToastUtils.showLongToast(getActivity(), "板卡数量不能为空或0");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setBksl(Integer.parseInt(bksl));
        }

        String sfsbm = editTexts.get(7).getText().toString();
        if (sfsbm.equals("")) {
            DemoActivity.instance.fzbhsb.setSfsbm(sfsbm);
            if (map_key.containsKey("实物ID")) {
                ToastUtils.showLongToast(getActivity(), "设备识别代码不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setSfsbm(sfsbm);
        }

        //判断厂家是否需要新增
        List<ZZCJ> cjList = util.getZZCJ(zzcj);
        if (cjList == null || cjList.size() == 0) {//厂家新增
            zzcj_add = new ZZCJ();
            zzcj_add.setMC(zzcj);
            zzcj_add.setCZR(PreferenceUtils.getPrefString(getActivity(), "userInfo"
                    , null).split("-")[0]);
            zzcj_add.setID(util.getInsertId("ZZCJ"));
            zzcj_add.setED_TAG("C");
        }

        if (isSix && is2013) {
            String zzfl, zzlx;
            if (ll_zzfl2.getVisibility() == View.VISIBLE) {
                zzfl = tv_zzfl2.getText().toString();
                zzlx = tv_zzlx2.getText().toString();
            } else {
                zzfl = textViews.get(4).getText().toString();
                zzlx = textViews.get(5).getText().toString();
            }

            if (zzfl.equals("")) {
                ToastUtils.showLongToast(getActivity(), "装置分类不能为空");
                return false;
            }
            if (zzlx.equals("")) {
                ToastUtils.showLongToast(getActivity(), "装置类型不能为空");
                return false;
            }
            String rjbb = editTexts.get(6).getText().toString();
            if (rjbb.equals("")) {
                ToastUtils.showLongToast(getActivity(), "软件版本不能为空");
                return false;
            }
            ltysbxh_add = new LTYSBXH();
            ltysbxh_add.setRjbb(rjbb);
            ltysbxh_add.setZzcj(zzcj);
            ltysbxh_add.setED_TAG("C");
            ltysbxh_add.setBhfl(zzfl);
            ltysbxh_add.setBhlx(zzlx);
            ltysbxh_add.setSfqy("Y");
            ltysbxh_add.setBblx("六统一设备");
            ltysbxh_add.setState("C");
            ltysbxh_add.setBhxh(zzxh);
            ltysbxh_add.setBhlb(zzlb);
            ltysbxh_add.setSysblx("FZSB");
            usegddata = "true";

            DemoActivity.instance.fzbhsb.setBhfl(zzfl);
            DemoActivity.instance.fzbhsb.setBhlx(zzlx);
            DemoActivity.instance.fzbhsb.setUsegddata(usegddata);
        } else {
            usegddata = "false";
            boolean isXhC = false;
            String zzfl, zzlx;
            //判断型号是否需要新增
            List<Object> xhList = util.getBHXH(isSix, is2013, zzcj, zzlb, DemoActivity.instance.czcs.getBdzlx() + ""
                    , "", "", zzxh);
            if (xhList == null || xhList.size() == 0) {//需要新增型号
                bhsbxhb = new BHSBXHB();
                if (ll_zzfl2.getVisibility() == View.VISIBLE) {
                    zzfl = tv_zzfl2.getText().toString();
                    zzlx = tv_zzlx2.getText().toString();
                } else {
                    zzfl = textViews.get(4).getText().toString();
                    zzlx = textViews.get(5).getText().toString();
                }
                if (zzfl.equals("")) {
                    ToastUtils.showLongToast(getActivity(), "装置分类不能为空");
                    bhsbxhb = null;
                    return false;
                } else {
                    bhsbxhb.setBhfl(zzfl);
                }

                if (zzlx.equals("")) {
                    ToastUtils.showLongToast(getActivity(), "装置类型不能为空");
                    bhsbxhb = null;
                    return false;
                } else {
                    bhsbxhb.setBhlx(zzlx);
                    if (zzlx.equals("电磁型") || zzlx.equals("晶体管") || zzlx.equals("集成电路")) {
                        bhsbxhb.setBblx("无版本");
                    } else {
                        bhsbxhb.setBblx(cb_xh_sffmk.isChecked() ? "非六统一，分模块" : "非六统一，不分模块");
                    }
                }

                int dydjs = Integer.parseInt(Details2.instance.dydj != null ? Details2.instance.dydj : "0");

                if (dydjs >= 220 ||
                        DemoActivity.instance.czcs.getBdzlx().equals("智能站") &&
                                DemoActivity.instance.czcs.getCzzgdydj() >= 110) {
                    bhsbxhb.setSfqy("Y");
                    usegddata = "true";
                }

                bhsbxhb.setSbxh(zzxh);
                bhsbxhb.setED_TAG("C");
                bhsbxhb.setBhlb(zzlb);
                bhsbxhb.setZzcj(zzcj);
                isXhC = true;
            } else {
                if (ll_zzfl2.getVisibility() == View.VISIBLE) {
                    zzfl = tv_zzfl2.getText().toString();
                    zzlx = tv_zzlx2.getText().toString();
                } else {
                    zzfl = textViews.get(4).getText().toString();
                    zzlx = textViews.get(5).getText().toString();
                }
                int dydjs = Integer.parseInt(Details2.instance.dydj != null ? Details2.instance.dydj : "0");

                if (!is2013 && xhList.size() == 1 && dydjs >= 220 || !is2013 && xhList.size() == 1
                        && DemoActivity.instance.czcs.getBdzlx().equals("智能站")
                        && DemoActivity.instance.czcs.getCzzgdydj() >= 110) {
                    BHSBXHB xhb = (BHSBXHB) xhList.get(0);
                    if (xhb.getED_TAG() != null && xhb.getED_TAG().equals("C")) {
                        if (xhb.getSfqy() == null || xhb.getSfqy() != null
                                && !xhb.getSfqy().equals("Y")) {
                            xhb.setSfqy("Y");
                            bhsbxhb = xhb;
                            usegddata = "true";
                        }
                    } else {
                        if (xhb.getSfqy() == null || xhb.getSfqy() != null && !xhb.getSfqy().equals("Y")) {
                            bhsbxhb = xhb;
                            bhsbxhb.setId(null);
                            bhsbxhb.setCode(null);
                            bhsbxhb.setSbxh(zzxh);
                            bhsbxhb.setSfqy("Y");
                            bhsbxhb.setED_TAG("C");
                            usegddata = "true";
                        }
                    }
                }
            }

            //保存分类和类型
            DemoActivity.instance.fzbhsb.setBhfl(zzfl);
            DemoActivity.instance.fzbhsb.setBhlx(zzlx);

            if (zzlx.equals("电磁型") || zzlx.equals("晶体管") || zzlx.equals("集成电路")) {
                rjbbList.clear();
            }

            if (rjbbList != null && rjbbList.size() > 0 && rjbbList.get(0)
                    .getBblx().equals("非六统一，不分模块")) {
                for (int i = 1; i < rjbbList.size(); i++) {
                    rjbbList.remove(i);
                }
            }

            if (llRjbb.getVisibility() == View.VISIBLE) {
                rjbbList.clear();

                BHXHRJBB rjbb = new BHXHRJBB();
                rjbb.setBblx("非六统一，不分模块");
                rjbb.setED_TAG("C");
                rjbb.setSfqy("Y");
                rjbbList.add(rjbb);
            }

            if (rjbbList != null && rjbbList.size() > 0 && !NoShowRjbb()) {
                if (rjbbList.size() == 1) {  //非六统一不/分模块
                    if (rjbbList.get(0).getBblx() != null && rjbbList.get(0).getBblx().equals("非六统一，分模块")) {
                        rjbbList.get(0).setBhxhcode(codeMap.get(zzxh) != null ? codeMap.get(zzxh) : "");
                        String mkmc = rjbbList.get(0).getMkmc() != null ? rjbbList.get(0).getMkmc() : "";
                        String rjbb = rjbbList.get(0).getBb() != null ? rjbbList.get(0).getBb() : "";
                        String jym = rjbbList.get(0).getJym() != null ? rjbbList.get(0).getJym() : "";
                        String scsj = rjbbList.get(0).getSCSJ() != null ? rjbbList.get(0).getSCSJ() : "";

                        if (mkmc.equals("")) {
                            ToastUtils.showLongToast(getActivity(), "模块名称不能为空");
                            return false;
                        }

                        if (!mkmc.equals("") && rjbb.equals("")) {
                            ToastUtils.showLongToast(getActivity(), "软件版本不能为空");
                            return false;
                        }
                        if (map_key.containsKey("校验码-分模块") && jym.equals("")) {
                            ToastUtils.showLongToast(getActivity(), "校验码不能为空");
                            return false;
                        }
                        if (map_key.containsKey("生成时间-分模块") && scsj.equals("")) {
                            ToastUtils.showLongToast(getActivity(), "生成时间不能为空");
                            return false;
                        }

                        if (mkmc.equals("") && rjbb.equals("") && jym.equals("") && scsj.equals("")) {
                            rjbbList.clear();
                        }

                    } else {
                        String rjbb = editTexts.get(3).getText().toString();
                        String jym = editTexts.get(4).getText().toString();
                        String scsj = textViews.get(2).getText().toString();
                        if (map_key.containsKey("软件版本") && rjbb.equals("")) {
                            ToastUtils.showLongToast(getActivity(), "软件版本不能为空");
                            return false;
                        }
                        if (map_key.containsKey("校验码") && jym.equals("")) {
                            ToastUtils.showLongToast(getActivity(), "校验码不能为空");
                            return false;
                        }
                        if (map_key.containsKey("生成时间") && scsj.equals("")) {
                            ToastUtils.showLongToast(getActivity(), "生成时间不能为空");
                            return false;
                        }

                        rjbbList.get(0).setBhxhcode(codeMap.get(zzxh) != null ? codeMap.get(zzxh) : "");
                        rjbbList.get(0).setED_TAG("C");
                        rjbbList.get(0).setBb(rjbb);
                        rjbbList.get(0).setJym(jym);
                        rjbbList.get(0).setMkmc(null);
                        rjbbList.get(0).setSCSJ(scsj);
                        rjbbList.get(0).setBblx("非六统一，不分模块");

                        if (rjbb.equals("") && jym.equals("") && scsj.equals("")) {
                            rjbbList.clear();
                        }
                    }
                } else { //非六统一分模块
                    if (Details11.instance != null && Details11.instance.list_one_data.size() > 5) {
                        if (!Details11.instance.isaddOk()) {
                            return false;
                        }
                    } else {
                        for (BHXHRJBB bhxhrjbb : rjbbList) {
                            String mkmc = bhxhrjbb.getMkmc() != null ? bhxhrjbb.getMkmc() : "";
                            String rjbb = bhxhrjbb.getBb() != null ? bhxhrjbb.getBb() : "";
                            String jym = bhxhrjbb.getJym() != null ? bhxhrjbb.getJym() : "";
                            String scsj = bhxhrjbb.getSCSJ() != null ? bhxhrjbb.getSCSJ() : "";

                            if (mkmc.equals("")) {
                                ToastUtils.showLongToast(getActivity(), "模块名称不能为空");
                                return false;
                            }
                            if (!mkmc.equals("") && rjbb.equals("")) {
                                ToastUtils.showLongToast(getActivity(), "软件版本不能为空");
                                return false;
                            }
                            if (map_key.containsKey("校验码-分模块") && jym.equals("")) {
                                ToastUtils.showLongToast(getActivity(), "校验码不能为空");
                                return false;
                            }
                            if (map_key.containsKey("生成时间-分模块") && scsj.equals("")) {
                                ToastUtils.showLongToast(getActivity(), "生成时间不能为空");
                                return false;
                            }

                            bhxhrjbb.setBhxhcode(codeMap.get(zzxh) != null ? codeMap.get(zzxh) : "");

                            if (mkmc.equals("") && rjbb.equals("") && jym.equals("") && scsj.equals("")) {
                                rjbbList.remove(bhxhrjbb);
                            }
                        }
                    }
                }
            }
            if (!is2013 && !isXhC) {
                if (bbToCode.size() > 0 && rjbbList != null && rjbbList.size() > 0
                        && rjbbList.get(0).getBb() != null && bbToCode.containsKey(rjbbList.get(0).getBb())) {
                    BHSBXHB xh = (BHSBXHB) util.getBHXHByCode(bbToCode.get(rjbbList.get(0).getBb()), isSix, is2013);
                    usegddata = xh != null && xh.getSfqy() != null ? "true" : "false";
                } else {
                    BHSBXHB xh = (BHSBXHB) util.getBHXHByCode(codeMap.get(zzxh), isSix, is2013);
                    usegddata = xh != null && xh.getSfqy() != null ? "true" : "false";
                }
            }
            DemoActivity.instance.fzbhsb.setUsegddata(usegddata);
            DemoActivity.instance.fzbhsb.setIcdwjmc(null);
        }
        return true;
    }

    @Override
    public void onScroll(int scrollY) {
        if (ll_xh_sffmk.getVisibility() == View.VISIBLE) {
            if (rjbbList.size() > 0 && lvRjbb.getVisibility() == View.GONE && scrollY > 50 && cb_xh_sffmk.isChecked() && !NoShowRjbb()) {
                lvRjbb.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }
        } else {
            String bblx = bblxMap.get(editTexts.get(2).getText().toString()) + "";
            if (bblx.equals("非六统一，分模块") && rjbbList.size() > 0 && lvRjbb.getVisibility() == View.GONE && scrollY > 50 && !NoShowRjbb()) {
                lvRjbb.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 一次设备名称和装置名称是否一致
     *
     * @return
     */

    public boolean isYcsbmcLikeZzmc() {
        String ycsbmc = Details2.instance.ycsbmc != null ? Details2.instance.ycsbmc : "";
        String zzmc = editTexts.get(0).getText().toString();
        if (ycsbmc.contains("kV")) {

        }

        if (ycsbmc.equals("") || zzmc.equals(""))
            return true;

        return zzmc.contains(ycsbmc);
    }
}