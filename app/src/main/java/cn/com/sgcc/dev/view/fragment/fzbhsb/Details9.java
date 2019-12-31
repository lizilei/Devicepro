package cn.com.sgcc.dev.view.fragment.fzbhsb;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.SelectActivity;
import cn.com.sgcc.dev.view.fragment.BaseFragment;

import static android.app.Activity.RESULT_OK;

/**
 * <p>@description:辅助保护类别带出信息</p>
 * 收发信机、合并单元、合并单元智能终端集成、智能终端、交换机
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/25
 */

public class Details9 extends BaseFragment {

    @BindViews(value = {R.id.ll_hasznzd, R.id.ll_hbdy, R.id.ll_znzd, R.id.ll_sfxj, R.id.ll_jhj})
    List<LinearLayout> layoutList;
    @BindViews(value = {R.id.cb_sfrstp_true, R.id.cb_sf1588_true,
            R.id.cb_sfdtzb_true, R.id.cb_sfsnmp_true, R.id.cb_sfiec_true})
    List<CheckBox> boxList;
    @BindViews(value = {R.id.tv_hbdy_dsfs, R.id.tv_hbdy_fsgxkms, R.id.tv_hbdy_jsgxkms,
            R.id.tv_hbdy_hbdygn, R.id.tv_hbdy_hgqlx, R.id.tv_hbdy_gddy, R.id.tv_hbdy_zzsx,
            R.id.tv_hbdy_znzdgn, R.id.tv_znzd_dsfs, R.id.tv_znzd_fsgxkms, R.id.tv_znzd_jsgxkms,
            R.id.tv_znzd_znzdgn, R.id.tv_sfxj_zbtdjgx, R.id.tv_jhj_jhjgn, R.id.tv_jhj_jhjlx, R.id.tv_jhj_gxjkms})
    List<TextView> textViews;
    @BindView(R.id.tv_details9)
    TextView tv_details9;
    @BindViews(value = {R.id.tv_hbdy_fsgxksl, R.id.tv_hbdy_jsgxksl, R.id.tv_znzd_fsgxksl,
            R.id.tv_znzd_jsgxksl, R.id.tv_sfxj_tdpl, R.id.tv_jhj_jhjjls})
    List<EditText> editTexts;
    Unbinder unbinder;

    @BindViews(value = {R.id.iv_hbdy_dsfs, R.id.iv_hbdy_fsgxkms, R.id.iv_hbdy_jsgxkms,
            R.id.iv_hbdy_hbdygn, R.id.iv_hbdy_hgqlx, R.id.iv_hbdy_gddy, R.id.iv_hbdy_zzsx,
            R.id.iv_hbdy_znzdgn, R.id.iv_znzd_dsfs, R.id.iv_znzd_fsgxkms, R.id.iv_znzd_jsgxkms,
            R.id.iv_znzd_znzdgn, R.id.iv_sfxj_zbtdjgx, R.id.iv_jhj_jhjgn, R.id.iv_jhj_jhjlx, R.id.iv_jhj_gxjkms})
    List<ImageView> imgViews;
    public static Details9 instance;

    /**
     * 必填项校验需要
     */
    //合并单元、合智一体
    @BindViews(value = {R.id.hbdy_dsfs, R.id.hbdy_fsgxksl, R.id.hbdy_jsgxksl, R.id.hbdy_fsgxkms
            , R.id.hbdy_hbdygn, R.id.hbdy_hgqlx, R.id.hbdy_gddy, R.id.hbdy_zzsx, R.id.hbdy_znzdgn})
    List<TextView> textViews_hbdy_key;
    @BindViews(value = {R.id.tv_hbdy_dsfs, R.id.tv_hbdy_fsgxksl, R.id.tv_hbdy_jsgxksl, R.id.tv_hbdy_fsgxkms
            , R.id.tv_hbdy_hbdygn, R.id.tv_hbdy_hgqlx, R.id.tv_hbdy_gddy, R.id.tv_hbdy_zzsx, R.id.tv_hbdy_znzdgn})
    List<TextView> textViews_hbdy_more;
    private Map<String, TextView> map_hbdy_key = new HashMap<>();
    private Map<String, TextView> map_hbdy_more = new HashMap<>();
    //智能终端
    @BindViews(value = {R.id.znzd_fsgxksl, R.id.znzd_jsgxksl, R.id.znzd_fsgxkms, R.id.znzd_znzdgn})
    List<TextView> textViews_znzd_key;
    @BindViews(value = {R.id.tv_znzd_fsgxksl, R.id.tv_znzd_jsgxksl, R.id.tv_znzd_fsgxkms, R.id.tv_znzd_znzdgn})
    List<TextView> textViews_znzd_more;
    private Map<String, TextView> map_znzd_key = new HashMap<>();
    private Map<String, TextView> map_znzd_more = new HashMap<>();
    //收发信机
    @BindViews(value = {R.id.sfxj_tdpl, R.id.sfxj_zbtdjgx})
    List<TextView> textViews_sfxj_key;
    @BindViews(value = {R.id.tv_sfxj_tdpl, R.id.tv_sfxj_zbtdjgx})
    List<TextView> textViews_sfxj_more;
    private Map<String, TextView> map_sfxj_key = new HashMap<>();
    private Map<String, TextView> map_sfxj_more = new HashMap<>();
    //交换机
    @BindViews(value = {R.id.jhj_gxjkms, R.id.jhj_jhjgn, R.id.jhj_jhjjls, R.id.jhj_jhjlx})
    List<TextView> textViews_jhj_key;
    @BindViews(value = {R.id.tv_jhj_gxjkms, R.id.tv_jhj_jhjgn, R.id.tv_jhj_jhjjls, R.id.tv_jhj_jhjlx})
    List<TextView> textViews_jhj_more;
    private Map<String, TextView> map_jhj_key = new HashMap<>();
    private Map<String, TextView> map_jhj_more = new HashMap<>();
    private boolean isShow;

    @Override
    public int getLayoutId() {
        return R.layout.item_details9;
    }

    @Override
    public void initview() {
        instance = this;
    }

    @Override
    public void initEvevt() {
        if (DemoActivity.instance.isfirstChange) {
            setChange(true, getArguments().getString("tag"), true);
        } else {
            if (DemoActivity.instance.fzbhsb != null) {
                setChange(true, DemoActivity.instance.fzbhsb.getFzsblx(), true);
            } else {
                setChange(true, getArguments().getString("tag"), true);
            }
        }
    }

    @Override
    public void initData() {
        String zzlb;
        if (DemoActivity.instance.isfirstChange) {
            zzlb = getArguments().getString("tag");
        } else {
            if (DemoActivity.instance.fzbhsb != null) {
                zzlb = DemoActivity.instance.fzbhsb.getFzsblx();
            } else {
                zzlb = getArguments().getString("tag");
            }
        }
        init(zzlb);

        setFirstData();
        initReceiver(getArguments().getBoolean("isEdit", false));

        //加载监听
        initEvevt();
    }

    /**
     * 初始化校验项
     */
    private void init(String zzlb) {
        Map<String, TextView> tmp_key = new HashMap<>();
        Map<String, TextView> tmp_value = new HashMap<>();
        List<SaleAttributeVo> saleVo = new ArrayList<>();

        switch (zzlb) {
            case "合并单元":
            case "合并单元智能终端集成":
            case "HBDY":
            case "HBDY2":
                for (int i = 0; i < textViews_hbdy_key.size(); i++) {
                    tmp_key.put(textViews_hbdy_key.get(i).getText().toString(), textViews_hbdy_key.get(i));
                    tmp_value.put(textViews_hbdy_key.get(i).getText().toString(), textViews_hbdy_more.get(i));
                }

                for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getFzData()) {
                    if (saleAttributeNameVo.getName().equals("MU附加信息")) {
                        saleVo = saleAttributeNameVo.getSaleVo();
                        break;
                    }
                }

                for (SaleAttributeVo saleAttributeVo : saleVo) {
                    if (tmp_key.containsKey(saleAttributeVo.getValue() + "：")) {
                        if (zzlb.equals("合并单元智能终端集成") || zzlb.equals("HBDY2")) {
                            map_hbdy_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "："));
                            map_hbdy_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "："));
                        } else {
                            if (saleAttributeVo.getValue().equals("智能终端功能")) {
                                continue;
                            }
                            map_hbdy_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "："));
                            map_hbdy_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "："));
                        }
                    }
                }
                break;
            case "智能终端":
            case "ZNZD":
                for (int i = 0; i < textViews_znzd_key.size(); i++) {
                    tmp_key.put(textViews_znzd_key.get(i).getText().toString(), textViews_znzd_key.get(i));
                    tmp_value.put(textViews_znzd_key.get(i).getText().toString(), textViews_znzd_more.get(i));
                }

                for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getFzData()) {
                    if (saleAttributeNameVo.getName().equals("智能终端附加信息")) {
                        saleVo = saleAttributeNameVo.getSaleVo();
                        break;
                    }
                }

                for (SaleAttributeVo saleAttributeVo : saleVo) {
                    if (tmp_key.containsKey(saleAttributeVo.getValue() + "：")) {
                        map_znzd_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "："));
                        map_znzd_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "："));
                    }
                }
                break;
            case "收发信机":
            case "SFXJ":
                for (int i = 0; i < textViews_sfxj_key.size(); i++) {
                    tmp_key.put(textViews_sfxj_key.get(i).getText().toString(), textViews_sfxj_key.get(i));
                    tmp_value.put(textViews_sfxj_key.get(i).getText().toString(), textViews_sfxj_more.get(i));
                }

                for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getFzData()) {
                    if (saleAttributeNameVo.getName().equals("载波机附加信息")) {
                        saleVo = saleAttributeNameVo.getSaleVo();
                        break;
                    }
                }

                for (SaleAttributeVo saleAttributeVo : saleVo) {
                    if (tmp_key.containsKey(saleAttributeVo.getValue() + "：")) {
                        map_sfxj_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "："));
                        map_sfxj_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "："));
                    }
                }
                break;
            case "交换机":
            case "JHJ":
                for (int i = 0; i < textViews_jhj_key.size(); i++) {
                    tmp_key.put(textViews_jhj_key.get(i).getText().toString(), textViews_jhj_key.get(i));
                    tmp_value.put(textViews_jhj_key.get(i).getText().toString(), textViews_jhj_more.get(i));
                }

                for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getFzData()) {
                    if (saleAttributeNameVo.getName().equals("交换机附加信息")) {
                        saleVo = saleAttributeNameVo.getSaleVo();
                        break;
                    }
                }

                for (SaleAttributeVo saleAttributeVo : saleVo) {
                    if (tmp_key.containsKey(saleAttributeVo.getValue() + "：")) {
                        map_jhj_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "："));
                        map_jhj_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "："));
                    }
                }
                break;
        }
    }

    /**
     * 显示必填项的方法
     *
     * @param isEdit
     * @param zzlb
     */
    private void setChange(boolean isEdit, String zzlb, boolean isAddListener) {
        switch (zzlb) {
            case "合并单元":
            case "合并单元智能终端集成":
            case "HBDY":
            case "HBDY2":
                if (isAddListener) {
                    /**
                     * 加载必填项监听
                     */
                    Details1.instance.initOnFocusChangeListener(map_hbdy_key, map_hbdy_more, false, "MU附加信息");
                } else {
                    /**
                     * 必填项校验设计
                     */
                    Details1.instance.checkBtx(isEdit, false, isShow, map_hbdy_key, map_hbdy_more, "MU附加信息");
                }
                break;
            case "智能终端":
            case "ZNZD":
                if (isAddListener) {
                    /**
                     * 加载必填项监听
                     */
                    Details1.instance.initOnFocusChangeListener(map_znzd_key, map_znzd_more, false, "智能终端附加信息");
                } else {
                    /**
                     * 必填项校验设计
                     */
                    Details1.instance.checkBtx(isEdit, false, isShow, map_znzd_key, map_znzd_more, "智能终端附加信息");
                }
                break;
            case "收发信机":
            case "SFXJ":
                if (isAddListener) {
                    /**
                     * 加载必填项监听
                     */
                    Details1.instance.initOnFocusChangeListener(map_sfxj_key, map_sfxj_more, false, "载波机附加信息");
                } else {
                    /**
                     * 必填项校验设计
                     */
                    Details1.instance.checkBtx(isEdit, false, isShow, map_sfxj_key, map_sfxj_more, "载波机附加信息");
                }
                break;
            case "交换机":
            case "JHJ":
                if (isAddListener) {
                    /**
                     * 加载必填项监听
                     */
                    Details1.instance.initOnFocusChangeListener(map_jhj_key, map_jhj_more, false, "交换机附加信息");
                } else {
                    /**
                     * 必填项校验设计
                     */
                    Details1.instance.checkBtx(isEdit, false, isShow, map_jhj_key, map_jhj_more, "交换机附加信息");
                }
                break;
        }
    }

    /**
     * 装置类别带出部分信息
     *
     * @param zzlb
     */
    private String getZZLB(String zzlb) {
        String str = "";
        if (zzlb.equals("合并单元")) {
            str = "HBDY";
        } else if (zzlb.equals("合并单元智能终端集成")) {
            str = "HBDY2";
        } else if (zzlb.equals("收发信机")) {
            str = "SFXJ";
        } else if (zzlb.equals("智能终端")) {
            str = "ZNZD";
        } else if (zzlb.equals("交换机")) {
            str = "JHJ";
        }
        return str;
    }

    /**
     * 初始化数据
     */
    private void setFirstData() {
        if (DemoActivity.instance.isfirstChange) {
            initCurrentState(getArguments().getString("tag"));
        } else {
            if (DemoActivity.instance.fzbhsb != null) {
                setCurrentState(getZZLB(DemoActivity.instance.fzbhsb.getFzsblx()));
            } else {
                initCurrentState(getArguments().getString("tag"));
            }
        }

        if (DemoActivity.instance.fzbhsb != null) {
            if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C")) {
                tv_details9.setVisibility(View.GONE);
            } else {
                tv_details9.setVisibility(View.VISIBLE);
                if (DemoActivity.instance.rzgl != null) {
                    tv_details9.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                } else {
                    tv_details9.setText("本条台账最后一次修改时间：");
                }
            }
        } else {
            tv_details9.setVisibility(View.GONE);
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
            for (CheckBox checkBox : boxList) {
                checkBox.setEnabled(true);
            }
        } else {
            if (DemoActivity.instance.isCancel) {//取消
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
                for (CheckBox checkBox : boxList) {
                    checkBox.setEnabled(false);
                }
                if (DemoActivity.instance.rzgl != null &&
                        DemoActivity.instance.rzgl.getCZSJ() != null &&
                        !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                    tv_details9.setVisibility(View.VISIBLE);
                    tv_details9.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }
                //清除所有数据，重新初始化
                setFirstData();
            } else { //保存
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
                for (CheckBox checkBox : boxList) {
                    checkBox.setEnabled(false);
                }
                if (DemoActivity.instance.rzgl != null &&
                        DemoActivity.instance.rzgl.getCZSJ() != null &&
                        !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                    tv_details9.setVisibility(View.VISIBLE);
                    tv_details9.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }
            }
        }

        if (DemoActivity.instance.isfirstChange) {
            setChange(isEdit, getArguments().getString("tag"), false);
        } else {
            if (DemoActivity.instance.fzbhsb != null) {
                setChange(isEdit, DemoActivity.instance.fzbhsb.getFzsblx(), false);
            } else {
                setChange(isEdit, getArguments().getString("tag"), false);
            }
        }
    }

    /**
     * 设置当前需要显示的分类内容
     *
     * @param type
     */
    public void setCurrentState(String type) {
        FZBHSB fzbhsb = DemoActivity.instance.fzbhsb;
        switch (type) {
            case "HBDY": //合并单元
            case "HBDY2": //合并单元智能终端集成
                layoutList.get(1).setVisibility(View.VISIBLE);
                layoutList.get(3).setVisibility(View.GONE);
                layoutList.get(2).setVisibility(View.GONE);
                layoutList.get(4).setVisibility(View.GONE);

                if (fzbhsb.getDsfs() != null) {
                    textViews.get(0).setText(fzbhsb.getDsfs());
                }
                if (fzbhsb.getFsgxsl() != null) {
                    String[] ss1 = fzbhsb.getFsgxsl().split("#");

                    if (ss1[0] != null && !ss1[0].equals("") && !ss1[0].equals("0")) {
                        editTexts.get(0).setText(ss1[0] + "");
                    } else {
                        editTexts.get(0).setText("");
                    }
                    if (ss1[ss1.length - 1] != null && !ss1[ss1.length - 1].equals("") && !ss1[ss1.length - 1].equals("0")) {
                        editTexts.get(1).setText(ss1[ss1.length - 1] + "");
                    } else {
                        editTexts.get(1).setText("");
                    }
                }

//                String[] ss2 = fzbhsb.getGxjkms().split("#");
                if (fzbhsb.getGxjkms() != null) {
                    textViews.get(1).setText(fzbhsb.getGxjkms());
//                    textViews.get(2).setText(fzbhsb.getGxjkms());
                }
                if (fzbhsb.getHbdygn() != null) {
                    textViews.get(3).setText(fzbhsb.getHbdygn());
                }
                if (fzbhsb.getHgqlx() != null) {
                    textViews.get(4).setText(fzbhsb.getHgqlx());
                }
                if (fzbhsb.getGddy() != null) {
                    textViews.get(5).setText(fzbhsb.getGddy());
                }
                if (fzbhsb.getZzsx() != null) {
                    textViews.get(6).setText(fzbhsb.getZzsx());
                }
                if (type.equals("HBDY")) {
                    layoutList.get(0).setVisibility(View.GONE);
                } else {
                    layoutList.get(0).setVisibility(View.VISIBLE);
                    if (fzbhsb.getZnzdgn() != null) {
                        textViews.get(7).setText(fzbhsb.getZnzdgn());
                    }
                }
                break;
            case "SFXJ": //收发信机
                layoutList.get(3).setVisibility(View.VISIBLE);
                layoutList.get(1).setVisibility(View.GONE);
                layoutList.get(2).setVisibility(View.GONE);
                layoutList.get(4).setVisibility(View.GONE);

                if (fzbhsb.getTdpl() != 0) {
                    editTexts.get(4).setText(fzbhsb.getTdpl() + "");
                }
                if (fzbhsb.getZbtdjgx() != null) {
                    textViews.get(12).setText(fzbhsb.getZbtdjgx());
                }
                break;
            case "ZNZD": //智能终端
                layoutList.get(2).setVisibility(View.VISIBLE);
                layoutList.get(1).setVisibility(View.GONE);
                layoutList.get(3).setVisibility(View.GONE);
                layoutList.get(4).setVisibility(View.GONE);
//                if (fzbhsb.getDsfs() != null)
//                    textViews.get(8).setText(fzbhsb.getDsfs());
                if (fzbhsb.getFsgxsl() != null) {
                    String[] s1 = fzbhsb.getFsgxsl().split("#");

                    if (s1[0] != null && !s1[0].equals("") && !s1[0].equals("0")) {
                        editTexts.get(2).setText(s1[0] + "");
                    } else {
                        editTexts.get(2).setText("");
                    }
                    if (s1[s1.length - 1] != null && !s1[s1.length - 1].equals("") && !s1[s1.length - 1].equals("")) {
                        editTexts.get(3).setText(s1[s1.length - 1] + "");
                    } else {
                        editTexts.get(3).setText("");
                    }
                }

//                String[] s2 = fzbhsb.getGxjkms().split("#");
                if (fzbhsb.getGxjkms() != null) {
                    textViews.get(9).setText(fzbhsb.getGxjkms());
//                    textViews.get(10).setText(fzbhsb.getGxjkms());
                }
                if (fzbhsb.getZnzdgn() != null) {
                    textViews.get(11).setText(fzbhsb.getZnzdgn());
                }
                break;
            case "JHJ": //交换机
                layoutList.get(4).setVisibility(View.VISIBLE);
                layoutList.get(1).setVisibility(View.GONE);
                layoutList.get(3).setVisibility(View.GONE);
                layoutList.get(2).setVisibility(View.GONE);

                if (fzbhsb.getGxjkms() != null) {
                    textViews.get(15).setText(fzbhsb.getGxjkms());
                }

                if (fzbhsb.getJhjgn() != null) {
                    textViews.get(13).setText(fzbhsb.getJhjgn());
                }
                editTexts.get(5).setText(fzbhsb.getJhjjls() + "");
                if (fzbhsb.getJhjlx() != null && !fzbhsb.getJhjlx().equals("")) {
                    textViews.get(14).setText(fzbhsb.getJhjlx());
                }
                if (fzbhsb.getSfrstp() != null && fzbhsb.getSfrstp().equals("是")) {
                    boxList.get(0).setChecked(true);
                } else {
                    boxList.get(0).setChecked(false);
                }
                if (fzbhsb.getSfds() != null && fzbhsb.getSfds().equals("是")) {
                    boxList.get(1).setChecked(true);
                } else {
                    boxList.get(1).setChecked(false);
                }
                if (fzbhsb.getSfzb() != null && fzbhsb.getSfzb().equals("是")) {
                    boxList.get(2).setChecked(true);
                } else {
                    boxList.get(2).setChecked(false);
                }
                if (fzbhsb.getSfsnmp() != null && fzbhsb.getSfsnmp().equals("是")) {
                    boxList.get(3).setChecked(true);
                } else {
                    boxList.get(3).setChecked(false);
                }
                if (fzbhsb.getSfiec() != null && fzbhsb.getSfiec().equals("是")) {
                    boxList.get(4).setChecked(true);
                } else {
                    boxList.get(4).setChecked(false);
                }
                break;
        }
    }

    /**
     * 初始化当前需要显示的分类内容
     *
     * @param type
     */
    public void initCurrentState(String type) {
        //先清空FZBHSB对象所有设置过的值
//        clearFZBHSB(type.equals("JHJ"));
        switch (type) {
            case "HBDY": //合并单元
            case "HBDY2": //合并单元智能终端集成
                layoutList.get(1).setVisibility(View.VISIBLE);
                layoutList.get(3).setVisibility(View.GONE);
                layoutList.get(2).setVisibility(View.GONE);
                layoutList.get(4).setVisibility(View.GONE);
                if (type.equals("HBDY")) {
                    layoutList.get(0).setVisibility(View.GONE);
                } else {
                    layoutList.get(0).setVisibility(View.VISIBLE);
                }
                break;
            case "SFXJ": //收发信机
                layoutList.get(3).setVisibility(View.VISIBLE);
                layoutList.get(1).setVisibility(View.GONE);
                layoutList.get(2).setVisibility(View.GONE);
                layoutList.get(4).setVisibility(View.GONE);
                break;
            case "ZNZD": //智能终端
                layoutList.get(2).setVisibility(View.VISIBLE);
                layoutList.get(1).setVisibility(View.GONE);
                layoutList.get(3).setVisibility(View.GONE);
                layoutList.get(4).setVisibility(View.GONE);
                break;
            case "JHJ": //交换机
                layoutList.get(4).setVisibility(View.VISIBLE);
                layoutList.get(1).setVisibility(View.GONE);
                layoutList.get(3).setVisibility(View.GONE);
                layoutList.get(2).setVisibility(View.GONE);
                break;
        }
        init(type);
        setChange(true, type,false);
    }

    /**
     * 清空FZBHSB不部分值
     *
     * @param isJHJ
     */
    private void clearFZBHSB(boolean isJHJ) {
        FZBHSB fzbhsb = DemoActivity.instance.fzbhsb;
        if (fzbhsb == null) {
            return;
        }
        fzbhsb.setDsfs("");
        fzbhsb.setFsgxsl("");
        fzbhsb.setGxjkms("");
        fzbhsb.setHbdygn("");
        fzbhsb.setHgqlx("");
        fzbhsb.setGddy("");
        fzbhsb.setZzsx("");
        fzbhsb.setZnzdgn("");
        fzbhsb.setJhjgn("");
        fzbhsb.setJhjjls(0);
        fzbhsb.setJhjlx("");
        fzbhsb.setTdpl(0);
        fzbhsb.setZbtdjgx("");
        if (isJHJ) {
            fzbhsb.setSfrstp("否");
            fzbhsb.setSfds("否");
            fzbhsb.setSfzb("否");
            fzbhsb.setSfsnmp("否");
            fzbhsb.setSfiec("否");
        } else {
            fzbhsb.setSfrstp("");
            fzbhsb.setSfds("");
            fzbhsb.setSfzb("");
            fzbhsb.setSfsnmp("");
            fzbhsb.setSfiec("");
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

    @OnClick({R.id.tv_hbdy_dsfs, R.id.tv_hbdy_fsgxkms, R.id.tv_hbdy_jsgxkms, R.id.tv_hbdy_hbdygn,
            R.id.tv_hbdy_hgqlx, R.id.tv_hbdy_gddy, R.id.tv_hbdy_zzsx, R.id.tv_hbdy_znzdgn, R.id.tv_znzd_dsfs,
            R.id.tv_znzd_fsgxkms, R.id.tv_znzd_jsgxkms, R.id.tv_znzd_znzdgn, R.id.tv_sfxj_zbtdjgx, R.id.tv_jhj_jhjgn,
            R.id.tv_jhj_jhjlx, R.id.tv_details9, R.id.iv_hbdy_dsfs, R.id.iv_hbdy_fsgxkms, R.id.iv_hbdy_jsgxkms,
            R.id.iv_hbdy_hbdygn, R.id.iv_hbdy_hgqlx, R.id.iv_hbdy_gddy, R.id.iv_hbdy_zzsx, R.id.iv_hbdy_znzdgn,
            R.id.iv_znzd_dsfs, R.id.iv_znzd_fsgxkms, R.id.iv_znzd_jsgxkms, R.id.iv_znzd_znzdgn,
            R.id.iv_sfxj_zbtdjgx, R.id.iv_jhj_jhjgn, R.id.iv_jhj_jhjlx, R.id.tv_jhj_gxjkms, R.id.iv_jhj_gxjkms})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_hbdy_dsfs:
                setIntentData("对时方式", 0);
                break;
            case R.id.tv_hbdy_fsgxkms:
                setIntentData("光纤接口模式", 1);
                break;
            case R.id.tv_hbdy_jsgxkms:
                setIntentData("光纤接口模式", 2);
                break;
            case R.id.tv_hbdy_hbdygn:
                setIntentData("合并单元功能", 3);
                break;
            case R.id.tv_hbdy_hgqlx:
                setIntentData("互感器类型", 4);
                break;
            case R.id.tv_hbdy_gddy:
                setIntentData("供电电源", 5);
                break;
            case R.id.tv_hbdy_zzsx:
                setIntentData("装置属性", 6);
                break;
            case R.id.tv_hbdy_znzdgn:
                setIntentData("智能终端功能", 7);
                break;
            case R.id.tv_znzd_dsfs:
                setIntentData("对时方式", 8);
                break;
            case R.id.tv_znzd_fsgxkms:
                setIntentData("光纤接口模式", 9);
                break;
            case R.id.tv_znzd_jsgxkms:
                setIntentData("光纤接口模式", 10);
                break;
            case R.id.tv_znzd_znzdgn:
                setIntentData("智能终端功能", 11);
                break;
            case R.id.tv_sfxj_zbtdjgx:
                setIntentData("载波通道加工相", 12);
                break;
            case R.id.tv_jhj_jhjgn:
                setIntentData("交换机功能", 13);
                break;
            case R.id.tv_jhj_jhjlx:
                setIntentData("交换机类型", 14);
                break;

            case R.id.iv_hbdy_dsfs:
                setIntentData("对时方式", 15);
                break;
            case R.id.iv_hbdy_fsgxkms:
                setIntentData("光纤接口模式", 16);
                break;
            case R.id.iv_hbdy_jsgxkms:
                setIntentData("光纤接口模式", 17);
                break;
            case R.id.iv_hbdy_hbdygn:
                setIntentData("合并单元功能", 18);
                break;
            case R.id.iv_hbdy_hgqlx:
                setIntentData("互感器类型", 19);
                break;
            case R.id.iv_hbdy_gddy:
                setIntentData("供电电源", 20);
                break;
            case R.id.iv_hbdy_zzsx:
                setIntentData("装置属性", 21);
                break;
            case R.id.iv_hbdy_znzdgn:
                setIntentData("智能终端功能", 22);
                break;
            case R.id.iv_znzd_dsfs:
                setIntentData("对时方式", 23);
                break;
            case R.id.iv_znzd_fsgxkms:
                setIntentData("光纤接口模式", 24);
                break;
            case R.id.iv_znzd_jsgxkms:
                setIntentData("光纤接口模式", 25);
                break;
            case R.id.iv_znzd_znzdgn:
                setIntentData("智能终端功能", 26);
                break;
            case R.id.iv_sfxj_zbtdjgx:
                setIntentData("载波通道加工相", 27);
                break;
            case R.id.iv_jhj_jhjgn:
                setIntentData("交换机功能", 28);
                break;
            case R.id.iv_jhj_jhjlx:
                setIntentData("交换机类型", 29);
                break;
            case R.id.tv_jhj_gxjkms:
                setIntentData("光纤接口模式", 30);
                break;
            case R.id.iv_jhj_gxjkms:
                setIntentData("光纤接口模式", 31);
                break;
        }
    }

    /**
     * @param type 类型
     */
    private void setIntentData(String type, int requestCode) {
        Map<String, Object> map = new HashMap<>();
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("type", type);

        intent.putExtra("conditions", (Serializable) map);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String value = data.getStringExtra("result");
            switch (requestCode) {
                case 0:
                case 15:
                    textViews.get(0).setText(value);
                    break;
                case 1:
                case 16:
                    textViews.get(1).setText(value);
                    break;
                case 2:
                case 17:
                    textViews.get(2).setText(value);
                    break;
                case 3:
                case 18:
                    textViews.get(3).setText(value);
                    break;
                case 4:
                case 19:
                    textViews.get(4).setText(value);
                    break;
                case 5:
                case 20:
                    textViews.get(5).setText(value);
                    break;
                case 6:
                case 21:
                    textViews.get(6).setText(value);
                    break;
                case 7:
                case 22:
                    textViews.get(7).setText(value);
                    break;
                case 8:
                case 23:
                    textViews.get(8).setText(value);
                    break;
                case 9:
                case 24:
                    textViews.get(9).setText(value);
                    break;
                case 10:
                case 25:
                    textViews.get(10).setText(value);
                    break;
                case 11:
                case 26:
                    textViews.get(11).setText(value);
                    break;
                case 12:
                case 27:
                    textViews.get(12).setText(value);
                    break;
                case 13:
                case 28:
                    textViews.get(13).setText(value);
                    break;
                case 14:
                case 29:
                    textViews.get(14).setText(value);
                    break;
                case 30:
                case 31:
                    textViews.get(15).setText(value);
                    break;
            }
//            /**
//             * 选择完输入框重新初始化
//             */
//            if (DemoActivity.instance.isfirstChange) {
//                setChange(true, getArguments().getString("tag"),false);
//            } else {
//                if (DemoActivity.instance.fzbhsb != null) {
//                    setChange(true, DemoActivity.instance.fzbhsb.getFzsblx(),false);
//                } else {
//                    setChange(true, getArguments().getString("tag"),false);
//                }
//            }
        }
    }

    /**
     * 统一保存校验方法
     */
    public boolean saveDetails9(String zzlb) {
        clearFZBHSB(false);
        switch (zzlb) {
            case "合并单元":
            case "合并单元智能终端集成":
                String dsfs = textViews.get(0).getText().toString();
                String fsgxksl = editTexts.get(0).getText().toString() + "";
                String jsgxksl = editTexts.get(1).getText().toString() + "";
                String fsgxkms = textViews.get(1).getText().toString();
//                String jsgxkms = textViews.get(2).getText().toString();
                String hbdygn = textViews.get(3).getText().toString();
                String hgqlx = textViews.get(4).getText().toString();
                String gddy = textViews.get(5).getText().toString();
                String zzsx = textViews.get(6).getText().toString();
                if (dsfs.equals("")) {
                    if (map_hbdy_key.containsKey("对时方式")) {
                        ToastUtils.showLongToast(getActivity(), "对时方式不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setDsfs(dsfs);
                }
//                DemoActivity.instance.fzbhsb.setDsfs(dsfs);
                if (fsgxksl.equals("") || fsgxksl.equals("0")) {
                    if (map_hbdy_key.containsKey("发送光纤口数量")) {
                        ToastUtils.showLongToast(getActivity(), "发送光纤口数量不能为空或0");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setFsgxsl((fsgxksl.equals("") ? "0" : fsgxksl) + "#" + (jsgxksl.equals("") ? "0" : jsgxksl));
                }
//                DemoActivity.instance.fzbhsb.setFsgxsl((fsgxksl.equals("") ? "0" : fsgxksl) + "#" + (jsgxksl.equals("") ? "0" : jsgxksl));
                if (jsgxksl.equals("") || jsgxksl.equals("0")) {
                    if (map_hbdy_key.containsKey("接收光纤口数量")) {
                        ToastUtils.showLongToast(getActivity(), "接收光纤口数量不能为空或0");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setFsgxsl((fsgxksl.equals("") ? "0" : fsgxksl) + "#" + (jsgxksl.equals("") ? "0" : jsgxksl));
                }
//                DemoActivity.instance.fzbhsb.setFsgxsl((fsgxksl.equals("") ? "0" : fsgxksl) + "#" + (jsgxksl.equals("") ? "0" : jsgxksl));
                if (fsgxkms.equals("")) {
                    if (map_hbdy_key.containsKey("光纤接口模式")) {
                        ToastUtils.showLongToast(getActivity(), "光纤接口模式不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setGxjkms(fsgxkms);
                }
//                DemoActivity.instance.fzbhsb.setGxjkms(fsgxkms);

                if (hbdygn.equals("")) {
                    if (map_hbdy_key.containsKey("合并单元功能")) {
                        ToastUtils.showLongToast(getActivity(), "合并单元功能不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setHbdygn(hbdygn);
                }
//                DemoActivity.instance.fzbhsb.setHbdygn(hbdygn);
                if (hgqlx.equals("")) {
                    if (map_hbdy_key.containsKey("互感器类型")) {
                        ToastUtils.showLongToast(getActivity(), "互感器类型不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setHgqlx(hgqlx);
                }
//                DemoActivity.instance.fzbhsb.setHgqlx(hgqlx);
                if (gddy.equals("")) {
                    if (map_hbdy_key.containsKey("供电电源")) {
                        ToastUtils.showLongToast(getActivity(), "供电电源不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setGddy(gddy);
                }
//                DemoActivity.instance.fzbhsb.setGddy(gddy);
                if (zzsx.equals("")) {
                    if (map_hbdy_key.containsKey("装置属性")) {
                        ToastUtils.showLongToast(getActivity(), "装置属性不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setZzsx(zzsx);
                }
//                DemoActivity.instance.fzbhsb.setZzsx(zzsx);
                if (zzlb.equals("合并单元智能终端集成")) {
                    String znzdgn = textViews.get(7).getText().toString();
                    if (znzdgn.equals("")) {
                        if (map_hbdy_key.containsKey("智能终端功能")) {
                            ToastUtils.showLongToast(getActivity(), "智能终端功能不能为空");
                            return false;
                        }
                    } else {
                        DemoActivity.instance.fzbhsb.setZnzdgn(znzdgn);
                    }
//                    DemoActivity.instance.fzbhsb.setZnzdgn(znzdgn);
                }

                break;
            case "收发信机":
                String tdpl = editTexts.get(4).getText().toString();
                String zbtdjgx = textViews.get(12).getText().toString();
                if (tdpl.equals("")) {
                    DemoActivity.instance.fzbhsb.setTdpl(0);
                    if (map_sfxj_key.containsKey("通道频率")) {
                        ToastUtils.showLongToast(getActivity(), "通道频率不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setTdpl(Integer.parseInt(tdpl));
                }
                if (zbtdjgx.equals("")) {
                    if (map_sfxj_key.containsKey("载波通道加工相")) {
                        ToastUtils.showLongToast(getActivity(), "载波通道加工相不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setZbtdjgx(zbtdjgx);
                }
//                DemoActivity.instance.fzbhsb.setZbtdjgx(zbtdjgx);
                break;
            case "智能终端":
//                String znzd_dsfs = textViews.get(8).getText().toString();
                String znzd_fsgxksl = editTexts.get(2).getText().toString() + "";
                String znzd_jsgxksl = editTexts.get(3).getText().toString() + "";
                String znzd_fsgxkms = textViews.get(9).getText().toString();
//                String znzd_jsgxkms = textViews.get(10).getText().toString();
                String znzdgn = textViews.get(11).getText().toString();
//                if (znzd_dsfs.equals("")) {
//                    ToastUtils.showLongToast(getActivity(), "对时方式不能为空");
//                    return false;
//                }
                if (znzd_fsgxksl.equals("") || znzd_fsgxksl.equals("0")) {
                    if (map_znzd_key.containsKey("发送光纤口数量")) {
                        ToastUtils.showLongToast(getActivity(), "发送光纤口数量不能为空或0");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setFsgxsl((znzd_fsgxksl.equals("") ? "0" : znzd_fsgxksl) + "#" + (znzd_jsgxksl.equals("") ? "0" : znzd_jsgxksl));
                }
                if (znzd_jsgxksl.equals("") || znzd_jsgxksl.equals("0")) {
                    if (map_znzd_key.containsKey("接收光纤口数量")) {
                        ToastUtils.showLongToast(getActivity(), "接收光纤口数量不能为空或0");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setFsgxsl((znzd_fsgxksl.equals("") ? "0" : znzd_fsgxksl) + "#" + (znzd_jsgxksl.equals("") ? "0" : znzd_jsgxksl));
                }
//                DemoActivity.instance.fzbhsb.setFsgxsl((znzd_fsgxksl.equals("") ? "0" : znzd_fsgxksl) + "#" + (znzd_jsgxksl.equals("") ? "0" : znzd_jsgxksl));

                if (znzd_fsgxkms.equals("")) {
                    if (map_znzd_key.containsKey("光纤接口模式")) {
                        ToastUtils.showLongToast(getActivity(), "光纤接口模式不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setGxjkms(znzd_fsgxkms);
                }
//                DemoActivity.instance.fzbhsb.setGxjkms(znzd_fsgxkms);
//                if (znzd_jsgxkms.equals("")) {
//                    ToastUtils.showLongToast(getActivity(), "接收光纤口模式不能为空");
//                    return false;
//                }
                if (znzdgn.equals("")) {
                    if (map_znzd_key.containsKey("智能终端功能")) {
                        ToastUtils.showLongToast(getActivity(), "智能终端功能不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setZnzdgn(znzdgn);
                }
//                DemoActivity.instance.fzbhsb.setZnzdgn(znzdgn);
//                DemoActivity.instance.fzbhsb.setDsfs(znzd_dsfs);
                break;
            case "交换机":
                String gxjkms = textViews.get(15).getText().toString();
                String jhjgn = textViews.get(13).getText().toString();
                String jhjjls = editTexts.get(5).getText().toString();
                String jhjlx = textViews.get(14).getText().toString();
                if (gxjkms.equals("")) {
                    if (map_jhj_key.containsKey("光纤接口模式")) {
                        ToastUtils.showLongToast(getActivity(), "光纤接口模式不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setGxjkms(gxjkms);
                }
//                DemoActivity.instance.fzbhsb.setGxjkms(gxjkms);
                if (jhjgn.equals("")) {
                    if (map_jhj_key.containsKey("交换机功能")) {
                        ToastUtils.showLongToast(getActivity(), "交换机功能不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setJhjgn(jhjgn);
                }
//                DemoActivity.instance.fzbhsb.setJhjgn(jhjgn);
                if (jhjjls.equals("")) {
                    DemoActivity.instance.fzbhsb.setJhjjls(0);
                    if (map_jhj_key.containsKey("交换机级联数")) {
                        ToastUtils.showLongToast(getActivity(), "交换机级联数不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setJhjjls(Integer.parseInt(jhjjls));
                }

                if (jhjlx.equals("")) {
                    if (map_jhj_key.containsKey("交换机类型")) {
                        ToastUtils.showLongToast(getActivity(), "交换机类型不能为空");
                        return false;
                    }
                } else {
                    DemoActivity.instance.fzbhsb.setJhjlx(jhjlx);
                }
//                DemoActivity.instance.fzbhsb.setJhjlx(jhjlx);

                DemoActivity.instance.fzbhsb.setSfrstp(boxList.get(0).isChecked() ? "是" : "否");
                DemoActivity.instance.fzbhsb.setSfds(boxList.get(1).isChecked() ? "是" : "否");
                DemoActivity.instance.fzbhsb.setSfzb(boxList.get(2).isChecked() ? "是" : "否");
                DemoActivity.instance.fzbhsb.setSfsnmp(boxList.get(3).isChecked() ? "是" : "否");
                DemoActivity.instance.fzbhsb.setSfiec(boxList.get(4).isChecked() ? "是" : "否");
                break;
            default:
                clearFZBHSB(false);
                break;
        }
        return true;
    }
}
