package cn.com.sgcc.dev.view.fragment.fzbhsb;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.datapick.widget.DatePickDialog;
import com.datapick.widget.OnSureLisener;
import com.datapick.widget.bean.DateType;

import org.greenrobot.greendao.Property;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.customeView.MyDatePickerDialog;
import cn.com.sgcc.dev.dao2.LTYSBXHDao;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.ICDXX;
import cn.com.sgcc.dev.model2.LTYSBXH;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;
import cn.com.sgcc.dev.view.activity.SelectActivity;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Base;
import cn.com.sgcc.dev.view.fragment.BaseFragment;
import cn.com.sgcc.dev.view.viewinterface.DateChooseListener;

import static android.app.Activity.RESULT_OK;

/**
 * <p>@description:ICD信息</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/25
 */

public class Details8 extends BaseFragment {
    @BindView(R.id.tv_details8)
    TextView tv;
    Unbinder unbinder;
    @BindView(R.id.tv_wjmc)
    EditText tvWjmc;
    @BindView(R.id.iv_wjmc)
    ImageView ivWjmc;

    @BindView(R.id.tv_wjzzxgsj)
    TextView tvWjzzxgsj;
    @BindView(R.id.iv_wjzzxgsj)
    ImageView ivWjzzxgsj;
    @BindView(R.id.tv_zyjcpc)
    TextView tvZyjcpc;
    @BindView(R.id.tv_xp)
    EditText tvXp;
    @BindView(R.id.iv_xp)
    ImageView ivXp;
    @BindView(R.id.ll_xp)
    LinearLayout llXp;
    @BindView(R.id.tv_xpgn)
    TextView tvXpgn;
    @BindView(R.id.tv_jcsj)
    TextView tvJcsj;
    @BindView(R.id.tv_change_reason)
    EditText tvChangeReason;
    @BindView(R.id.iv_change_reason)
    ImageView ivChangeReason;
    @BindView(R.id.ll_change_reason)
    LinearLayout llChangeReason;
    @BindView(R.id.iv_change_time)
    ImageView ivChangeTime;
    @BindView(R.id.ll_change_time)
    LinearLayout llChangeTime;
    @BindView(R.id.ll_change_info)
    LinearLayout llChangeInfo;
    @BindView(R.id.tv_change_time)
    TextView tvChangeTime;
    @BindView(R.id.tv_wjbb)
    EditText tvWjbb;
    @BindView(R.id.iv_wjbb)
    ImageView ivWjbb;
    @BindView(R.id.tv_crc32)
    EditText tvCrc32;
    @BindView(R.id.iv_crc32)
    ImageView ivCrc32;
    @BindView(R.id.tv_md5)
    EditText tvMd5;
    @BindView(R.id.iv_md5)
    ImageView ivMd5;

    //选配功能、ICD文件名称、ICD文件版本、CRC32文件版本、MD5校验码、ICD最终修改时间
    @BindViews(value = {R.id.tv_xps,
            R.id.tv_icdwjmc,
            R.id.tv_icdwjbb,
            R.id.tv_icdcrc32,
            R.id.tv_md5jym,
            R.id.tv_icdzzxgsj,
    })
    List<TextView> textViews_check;
    @BindViews(value = {R.id.tv_xp,
            R.id.tv_wjmc,
            R.id.tv_wjbb,
            R.id.tv_crc32,
            R.id.tv_md5,
            R.id.tv_wjzzxgsj,
    })
    List<TextView> textViews_check_bt;

    private IDaoUtil util;
    List<ICDXX> icdxxs = new ArrayList<>(); //显示的
    List<ICDXX> icdxxs2 = new ArrayList<>(); //全部的
    public static Details8 instance;
    private boolean icd_isc;
    private Map<Property, String> map = new HashMap();//六统一查询需要
    public List<SaleAttributeVo> saleVo;

    private Map<String, TextView> map_key = new HashMap<>();
    private Map<String, TextView> map_more = new HashMap<>();
    private boolean isShow;

    @Override
    public int getLayoutId() {
        return R.layout.item_details8;
    }

    @Override
    public void initview() {
        instance = this;
    }

    @Override
    public void initEvevt() {
        tvXp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !Fragment_Type_Base.instance.isSave) {
                    String xp = tvXp.getText().toString();
                    if (Fragment_Type_Base.instance.ltysbxh_old.getXp() != null && !Fragment_Type_Base.instance.ltysbxh_old.getXp().equals(xp) && !DemoActivity.instance.Similar) {
//                        llChangeInfo.setVisibility(View.VISIBLE);
                        setDataByXh(true);
                    } else {
//                        llChangeInfo.setVisibility(View.GONE);
                        setDataByXh(false);
                    }
                }
            }
        });

        tvXp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String xp = tvXp.getText().toString();
                if (Fragment_Type_Base.instance.ltysbxh_old.getXp() != null && !Fragment_Type_Base.instance.ltysbxh_old.getXp().equals(xp) && !DemoActivity.instance.Similar) {
//                    llChangeInfo.setVisibility(View.VISIBLE);
                    setDataByXh(true);
                } else {
//                        llChangeInfo.setVisibility(View.GONE);
                    setDataByXh(false);
                }
            }
        });
        tvWjbb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String wjbb = tvWjbb.getText().toString();
            }
        });
        tvCrc32.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String crc32 = tvCrc32.getText().toString();
            }
        });
        tvMd5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String md5 = tvMd5.getText().toString();
            }
        });


        ivChangeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMDHM, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        tvChangeTime.setText(date);
                    }
                });
            }
        });

        tvWjmc.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus&&!Details1.instance.isSave) {
                // 此处为失去焦点时的处理内容,全局监听返回键，弹框提示
                String wjmc = "";
                if (DemoActivity.instance.BHorFZ) {
                    if (!Fragment_Type_Base.instance.isSave) {
                        wjmc = tvWjmc.getText().toString() + "";
                    }
                    if (!hasFocus && !Fragment_Type_Base.instance.isSave && Fragment_Type_Base.instance.ltysbxh_old != null) {
                        if (Fragment_Type_Base.instance.ltysbxh_old.getWjmc() != null && !Fragment_Type_Base.instance.ltysbxh_old.getWjmc().equals(wjmc) && !DemoActivity.instance.Similar) {
                            setDataByXh(true);
                        } else {
                            setDataByXh(false);
                        }
                        if (!map.isEmpty()) {
                            map.clear();
                        }

                        if (Fragment_Type_Base.instance.ltysbxh.getBhxh() != null) {
                            map.put(LTYSBXHDao.Properties.Bhxh, Fragment_Type_Base.instance.ltysbxh.getBhxh());
                        }
                        if (Fragment_Type_Base.instance.ltysbxh.getZzcj() != null) {
                            map.put(LTYSBXHDao.Properties.Zzcj, Fragment_Type_Base.instance.ltysbxh.getZzcj());
                        }
                        if (Fragment_Type_Base.instance.ltysbxh.getBhlb() != null) {
                            map.put(LTYSBXHDao.Properties.Bhlb, Fragment_Type_Base.instance.ltysbxh.getBhlb());
                        }
                        if (Fragment_Type_Base.instance.ltysbxh.getRjbb() != null) {
                            map.put(LTYSBXHDao.Properties.Rjbb, Fragment_Type_Base.instance.ltysbxh.getRjbb());
                        }
                        if (Fragment_Type_Base.instance.ltysbxh.getXp() != null) {
                            map.put(LTYSBXHDao.Properties.Xp, Fragment_Type_Base.instance.ltysbxh.getXp());
                        }
                        if (wjmc != null) {
                            map.put(LTYSBXHDao.Properties.Wjmc, wjmc);
                        }

//                        List<Object> list = new DaoUtil(getActivity()).getFZBHSBXHRJBB(true, true,
//                                Fragment_Type_Base.instance.ltysbxh.getBhxh() + "",
//                                Fragment_Type_Base.instance.getzzcj() + "",
//                                Fragment_Type_Base.instance.getBhlb() + "",
//                                Fragment_Type_Base.instance.ltysbxh.getRjbb() + "", tvXp.getText().toString() + "", wjmc);
                        List<LTYSBXH> list = util.getLtyXX(map);
                        if (list.size() > 0) {
                            setDataByWjmc(list.get(0), false);
                            icd_isc = false;
//                            tvWjzzxgsj.setHint("");
//                            ivWjzzxgsj.setVisibility(View.GONE);
                        } else {
                            setDataByWjmc(null, false);
                            icd_isc = true;
//                            tvWjzzxgsj.setHint("必填项，请选择");
//                            ivWjzzxgsj.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    if (!Details1.instance.isSave) {
                        wjmc = tvWjmc.getText().toString() + "";
                    }
                    if (!hasFocus && !Details1.instance.isSave && Details1.instance.ltysbxh != null) {
                        if (Details1.instance.ltysbxh != null && Details1.instance.ltysbxh.getWjmc() != null
                                && !Details1.instance.ltysbxh.getWjmc().equals(wjmc) && !DemoActivity.instance.Similar) {
                            llChangeInfo.setVisibility(View.VISIBLE);
                        } else {
                            llChangeInfo.setVisibility(View.GONE);
                        }
                        if (!map.isEmpty()) {
                            map.clear();
                        }
                        if (Details1.instance.ltysbxh.getBhxh() != null) {
                            map.put(LTYSBXHDao.Properties.Bhxh, Details1.instance.ltysbxh.getBhxh());
                        }
                        if (Details1.instance.ltysbxh.getZzcj() != null) {
                            map.put(LTYSBXHDao.Properties.Zzcj, Details1.instance.ltysbxh.getZzcj());
                        }
                        if (Details1.instance.ltysbxh.getBhlb() != null) {
                            map.put(LTYSBXHDao.Properties.Bhlb, Details1.instance.ltysbxh.getBhlb());
                        }
                        if (Details1.instance.ltysbxh.getRjbb() != null) {
                            map.put(LTYSBXHDao.Properties.Rjbb, Details1.instance.ltysbxh.getRjbb());
                        }
                        if (wjmc != null) {
                            map.put(LTYSBXHDao.Properties.Wjmc, wjmc);
                        }
//                        List<Object> list = new DaoUtil(getActivity()).getFZBHSBXHRJBB(true, true,
//                                Details1.instance.ltysbxh.getBhxh(), Details1.instance.ltysbxh.getZzcj(),
//                                Details1.instance.ltysbxh.getBhlb(), Details1.instance.ltysbxh.getRjbb(), null, wjmc);
                        List<LTYSBXH> list = util.getLtyXX(map);
                        if (list.size() > 0) {
                            setDataByWjmc(list.get(0), false);
                            icd_isc = false;
//                            tvWjzzxgsj.setHint("");
//                            ivWjzzxgsj.setVisibility(View.GONE);
                        } else {
                            setDataByWjmc(null, false);
                            icd_isc = true;
//                            tvWjzzxgsj.setHint("必填项，请选择");
//                            ivWjzzxgsj.setVisibility(View.VISIBLE);
                        }
                    } else {

                    }
                }
//                }
            }
        });

        tvWjmc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    return;
                }
                if (DemoActivity.instance.BHorFZ) {
                    if (Fragment_Type_Base.instance.ltysbxh_old.getWjmc() != null && !Fragment_Type_Base.instance.ltysbxh_old.getWjmc().equals(s.toString()) && !DemoActivity.instance.Similar) {
                        setDataByXh(true);
                    } else {
                        setDataByXh(false);

                        if (!map.isEmpty()) {
                            map.clear();
                        }

                        if (Fragment_Type_Base.instance.ltysbxh.getBhxh() != null) {
                            map.put(LTYSBXHDao.Properties.Bhxh, Fragment_Type_Base.instance.ltysbxh.getBhxh());
                        }
                        if (Fragment_Type_Base.instance.ltysbxh.getZzcj() != null) {
                            map.put(LTYSBXHDao.Properties.Zzcj, Fragment_Type_Base.instance.ltysbxh.getZzcj());
                        }
                        if (Fragment_Type_Base.instance.ltysbxh.getBhlb() != null) {
                            map.put(LTYSBXHDao.Properties.Bhlb, Fragment_Type_Base.instance.ltysbxh.getBhlb());
                        }
                        if (Fragment_Type_Base.instance.ltysbxh.getRjbb() != null) {
                            map.put(LTYSBXHDao.Properties.Rjbb, Fragment_Type_Base.instance.ltysbxh.getRjbb());
                        }
                        if (Fragment_Type_Base.instance.ltysbxh.getXp() != null) {
                            map.put(LTYSBXHDao.Properties.Xp, Fragment_Type_Base.instance.ltysbxh.getXp());
                        }
                        if (s.toString() != null) {
                            map.put(LTYSBXHDao.Properties.Wjmc, s.toString());
                        }

//                        List<Object> list = new DaoUtil(getActivity()).getFZBHSBXHRJBB(true, true,
//                                Fragment_Type_Base.instance.ltysbxh.getBhxh() + "", Fragment_Type_Base.instance.ltysbxh.getRjbb() + "", tvXp.getText().toString() + "", s.toString());
                        List<LTYSBXH> list = util.getLtyXX(map);
                        if (list != null && list.size() > 0) {
                            setDataByWjmc(list.get(0), false);
                        } else {
                            setDataByWjmc(null, false);
                        }
                    }
                } else {
                    if (Details1.instance.ltysbxh != null && Details1.instance.ltysbxh.getWjmc() != null &&
                            !Details1.instance.ltysbxh.getWjmc().equals(s.toString()) && !DemoActivity.instance.Similar) {
                        llChangeInfo.setVisibility(View.VISIBLE);
                    } else {
                        llChangeInfo.setVisibility(View.GONE);
                        if (Details1.instance.ltysbxh == null) {
                            setDataByWjmc(null, false);
                            return;
                        }
                        if (!map.isEmpty()) {
                            map.clear();
                        }
                        if (Details1.instance.ltysbxh.getBhxh() != null) {
                            map.put(LTYSBXHDao.Properties.Bhxh, Details1.instance.ltysbxh.getBhxh());
                        }
                        if (Details1.instance.ltysbxh.getZzcj() != null) {
                            map.put(LTYSBXHDao.Properties.Zzcj, Details1.instance.ltysbxh.getZzcj());
                        }
                        if (Details1.instance.ltysbxh.getBhlb() != null) {
                            map.put(LTYSBXHDao.Properties.Bhlb, Details1.instance.ltysbxh.getBhlb());
                        }
                        if (Details1.instance.ltysbxh.getRjbb() != null) {
                            map.put(LTYSBXHDao.Properties.Rjbb, Details1.instance.ltysbxh.getRjbb());
                        }
                        if (s.toString() != null) {
                            map.put(LTYSBXHDao.Properties.Wjmc, s.toString());
                        }

//                        List<Object> list = new DaoUtil(getActivity()).getFZBHSBXHRJBB(true, true,
//                                Details1.instance.ltysbxh.getBhxh(), Details1.instance.ltysbxh.getZzcj(),
//                                Details1.instance.ltysbxh.getBhlb(), Details1.instance.ltysbxh.getRjbb(), "", s.toString());
                        List<LTYSBXH> list = util.getLtyXX(map);
                        if (list != null && list.size() > 0) {
                            setDataByWjmc(list.get(0), false);
                        } else {
                            setDataByWjmc(null, false);
                        }
                    }
                }
            }
        });

        ivChangeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        tvChangeTime.setText(date);
                    }
                });
            }
        });

    }

    @Override
    public void initData() {
        init();
        util = new DaoUtil(getActivity());
        setFirstData();
        saleVo = new ArrayList<>();
        if (DemoActivity.instance.BHorFZ){
            for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getBhData()) {
                if (saleAttributeNameVo.getName().equals("ICD文件信息")) {
                    saleVo.addAll(saleAttributeNameVo.getSaleVo());
                    break;
                }
            }
        }else {
            for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getFzData()) {
                if (saleAttributeNameVo.getName().equals("ICD文件信息")) {
                    saleVo.addAll(saleAttributeNameVo.getSaleVo());
                    break;
                }
            }
        }
        initReceiver(getArguments().getBoolean("isEdit", false));
        if (DemoActivity.instance.BHorFZ){
            /**
             * 加载必填项监听
             */
            Fragment_Type_Base.instance.initOnFocusChangeListener(map_key,map_more,false,"ICD文件信息");
        }else {
            /**
             * 加载必填项监听
             */
            Details1.instance.initOnFocusChangeListener(map_key,map_more,false,"ICD文件信息");
        }
    }

    /**
     * 初始化数据
     */
    private void setFirstData() {
        if (DemoActivity.instance.BHorFZ) {
            if (Fragment_Type_Base.instance.isC) {

            } else {
                llXp.setVisibility(View.VISIBLE);
                if (Fragment_Type_Base.instance.ltysbxh.getXp() != null) {
                    tvXp.setText(Fragment_Type_Base.instance.ltysbxh.getXp() + "");
                }
                if (Fragment_Type_Base.instance.ltysbxh.getWjmc() != null) {
                    tvWjmc.setText(Fragment_Type_Base.instance.ltysbxh.getWjmc() + "");
//                    ivWjzzxgsj.setVisibility(View.GONE);
                }
                setDataByWjmc(Fragment_Type_Base.instance.ltysbxh, false);

                if (DemoActivity.instance.bhsb != null) {
                    if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C")) {
                        tv.setVisibility(View.GONE);
                    } else {
                        tv.setVisibility(View.VISIBLE);
                        if (DemoActivity.instance.rzgl != null) {
                            tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                        } else {
                            tv.setText("本条台账最后一次修改时间：");
                        }
                    }
                } else {
                    tv.setVisibility(View.GONE);
                }
            }
        } else {
            if (Details1.instance.ltysbxh != null) {
                if (Details1.instance.ltysbxh.getWjmc() != null &&
                        Details1.instance.ltysbxh.getBhxh().equals(Details1.instance.lty_zzxh)
                        && Details1.instance.ltysbxh.getRjbb().equals(Details1.instance.lty_rjbb)) {
                    tvWjmc.setText(Details1.instance.ltysbxh.getWjmc());
                    setDataByWjmc(Details1.instance.ltysbxh, false);
                }
            } else {

            }

            if (DemoActivity.instance.fzbhsb != null) {
                if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C")) {
                    tv.setVisibility(View.GONE);
                } else {
                    tv.setVisibility(View.VISIBLE);
                    if (DemoActivity.instance.rzgl != null) {
                        tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                    } else {
                        tv.setText("本条台账最后一次修改时间：");
                    }
                }
            } else {
                tv.setVisibility(View.GONE);
            }
        }
    }

    private void init() {
        Map<String, TextView> tmp_key = new HashMap<>();
        Map<String, TextView> tmp_value = new HashMap<>();
        for (int i = 0; i < textViews_check.size(); i++) {
            tmp_key.put(textViews_check.get(i).getText().toString(), textViews_check.get(i));
            tmp_value.put(textViews_check.get(i).getText().toString(), textViews_check_bt.get(i));
        }

        List<SaleAttributeVo> saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.BHorFZ ?
                DemoActivity.instance.jiaoYanData.getBhData() : DemoActivity.instance.jiaoYanData.getFzData()) {
            if (saleAttributeNameVo.getName().equals("ICD文件信息")) {
                saleVo = saleAttributeNameVo.getSaleVo();
                break;
            }
        }

        for (SaleAttributeVo saleAttributeVo : saleVo) {
            if (tmp_key.containsKey(saleAttributeVo.getValue() + "：")) {
                map_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "："));
                map_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "："));
            }
        }
    }


    @Override
    public void initReceiver(boolean isEdit) {
        if (isEdit) {
            tvWjmc.setBackgroundResource(R.drawable.device_detials_bg);
            tvWjmc.setEnabled(true);
            tvWjbb.setBackgroundResource(R.drawable.device_detials_bg);
            tvWjbb.setEnabled(true);
            tvCrc32.setBackgroundResource(R.drawable.device_detials_bg);
            tvCrc32.setEnabled(true);
            tvMd5.setBackgroundResource(R.drawable.device_detials_bg);
            tvMd5.setEnabled(true);
            tvWjzzxgsj.setBackgroundResource(R.drawable.device_detials_bg);
            tvWjzzxgsj.setEnabled(true);
            tvWjzzxgsj.setClickable(true);

//            if (!tvWjmc.getText().toString().equals("") || !tvWjmc.getText().toString().equalsIgnoreCase("null")) {
//            } else {
//            }
            ivWjzzxgsj.setVisibility(View.VISIBLE);

            ivWjmc.setVisibility(View.VISIBLE);
            ivWjbb.setVisibility(View.VISIBLE);
            ivCrc32.setVisibility(View.VISIBLE);
            ivMd5.setVisibility(View.VISIBLE);

            tvXp.setBackgroundResource(R.drawable.device_detials_bg);
            tvXp.setEnabled(true);

            ivXp.setVisibility(View.VISIBLE);

//            for (TextView textView : textViews_check) {
//                setDrawableLeft(textView,false);
//            }

        } else {
            if (DemoActivity.instance.isCancel) {//取消
                tvWjmc.setBackground(null);
                tvWjmc.setEnabled(false);

                tvWjbb.setBackground(null);
                tvWjbb.setEnabled(false);
                tvCrc32.setBackground(null);
                tvCrc32.setEnabled(false);
                tvMd5.setBackground(null);
                tvMd5.setEnabled(false);

                tvWjzzxgsj.setBackground(null);
                tvWjzzxgsj.setEnabled(false);
                tvWjzzxgsj.setClickable(false);
                ivWjmc.setVisibility(View.GONE);
                ivWjbb.setVisibility(View.GONE);
                ivCrc32.setVisibility(View.GONE);
                ivMd5.setVisibility(View.GONE);
                ivWjzzxgsj.setVisibility(View.GONE);

                tvXp.setBackground(null);
                tvXp.setEnabled(false);

                ivXp.setVisibility(View.GONE);
                if (DemoActivity.instance.rzgl != null &&
                        DemoActivity.instance.rzgl.getCZSJ() != null &&
                        !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                    tv.setVisibility(View.VISIBLE);
                    tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }

                //先清空数据后初始化数据
                llChangeInfo.setVisibility(View.GONE);
                tvChangeReason.setText("");
                tvChangeTime.setText("");
                setDataByWjmc(null, true);
                setFirstData();
            } else {
                tvWjmc.setBackground(null);
                tvWjmc.setEnabled(false);

                tvWjbb.setBackground(null);
                tvWjbb.setEnabled(false);
                tvCrc32.setBackground(null);
                tvCrc32.setEnabled(false);
                tvMd5.setBackground(null);
                tvMd5.setEnabled(false);

                tvWjzzxgsj.setBackground(null);
                tvWjzzxgsj.setEnabled(false);
                tvWjzzxgsj.setClickable(false);
                ivWjmc.setVisibility(View.GONE);
                ivWjbb.setVisibility(View.GONE);
                ivCrc32.setVisibility(View.GONE);
                ivMd5.setVisibility(View.GONE);
                ivWjzzxgsj.setVisibility(View.GONE);

                tvXp.setBackground(null);
                tvXp.setEnabled(false);

                ivXp.setVisibility(View.GONE);
                llChangeInfo.setVisibility(View.GONE);
                if (DemoActivity.instance.rzgl != null &&
                        DemoActivity.instance.rzgl.getCZSJ() != null &&
                        !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                    tv.setVisibility(View.VISIBLE);
                    tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }
            }

//            for (TextView textView : textViews_check) {
////               setDrawableLeft(textView,false);
////            }

        }
        /**
         * 必填项校验设计
         */
        if (DemoActivity.instance.BHorFZ){
            Fragment_Type_Base.instance.checkBtx(isEdit, false, isShow, map_key, map_more, "ICD文件信息");
        }else {
            Details1.instance.checkBtx(isEdit, false, isShow, map_key, map_more, "ICD文件信息");
        }
    }

    public void setDrawableLeft(TextView TV,boolean ischeck){
        Drawable drawable = getResources().getDrawable(R.drawable.tanhao);
//        drawable.setBounds(10,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        if (ischeck){
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        }else {
            drawable.setBounds(0,0,0,0);
        }
        TV.setCompoundDrawables(drawable,null,null,null);
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

    @OnClick({R.id.iv_xp, R.id.iv_wjmc, R.id.tv_wjzzxgsj, R.id.iv_wjzzxgsj
            , R.id.iv_wjbb, R.id.iv_crc32, R.id.iv_md5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_wjmc:
                Map<String, Object> map = new HashMap<>();
                if (DemoActivity.instance.BHorFZ) {
                    map.put("number", "1");
                    map.put("bhxh", Fragment_Type_Base.instance.ltysbxh.getBhxh() + "");
                    map.put("rjbb", Fragment_Type_Base.instance.ltysbxh.getRjbb() + "");
                    map.put("zzcj", Fragment_Type_Base.instance.getzzcj());
                    map.put("bhlb", Fragment_Type_Base.instance.getBhlb());
                    map.put("xp", tvXp.getText().toString() + "");
                } else {
                    map.put("bhxh", Details1.instance.lty_zzxh);
                    map.put("rjbb", Details1.instance.lty_rjbb);
                    map.put("zzcj", Details1.instance.getLty_zzcj());
                    map.put("bhlb", Details1.instance.getLty_bhlb());
                }
                Intent intent = new Intent(getActivity(), SelectActivity.class);
                intent.putExtra("type", "ICD文件名");
                intent.putExtra("conditions", (Serializable) map);
                startActivityForResult(intent, 0);
                break;
            case R.id.iv_xp:
                Map<String, Object> maps = new HashMap<>();
                maps.put("number", "1");
                maps.put("bhxh", Fragment_Type_Base.instance.ltysbxh.getBhxh() + "");
                maps.put("rjbb", Fragment_Type_Base.instance.ltysbxh.getRjbb() + "");
                maps.put("zzcj", Fragment_Type_Base.instance.getzzcj());
                maps.put("bhlb", Fragment_Type_Base.instance.getBhlb());
                Intent intents = new Intent(getActivity(), SelectActivity.class);
                intents.putExtra("type", "选配功能");
                intents.putExtra("conditions", (Serializable) maps);
                startActivityForResult(intents, 0);
                break;
            case R.id.iv_wjbb:
                Map<String, Object> mapwjbb = new HashMap<>();
                if (DemoActivity.instance.BHorFZ) {
                    mapwjbb.put("number", "1");
                    mapwjbb.put("bhxh", Fragment_Type_Base.instance.ltysbxh.getBhxh() + "");
                    mapwjbb.put("rjbb", Fragment_Type_Base.instance.ltysbxh.getRjbb() + "");
                    mapwjbb.put("zzcj", Fragment_Type_Base.instance.getzzcj());
                    mapwjbb.put("bhlb", Fragment_Type_Base.instance.getBhlb());
                    mapwjbb.put("xp", tvXp.getText().toString() + "");
                    mapwjbb.put("wjmc", tvWjmc.getText().toString() + "");
                } else {
                    mapwjbb.put("bhxh", Details1.instance.lty_zzxh);
                    mapwjbb.put("rjbb", Details1.instance.lty_rjbb);
                    mapwjbb.put("zzcj", Details1.instance.getLty_zzcj());
                    mapwjbb.put("bhlb", Details1.instance.getLty_bhlb());
                    if (tvWjmc.getText().toString() != null && !tvWjmc.getText().toString().equals("")) {
                        mapwjbb.put("wjmc", tvWjmc.getText().toString());
                    }
                }
                Intent intentwjbb = new Intent(getActivity(), SelectActivity.class);
                intentwjbb.putExtra("type", "ICD文件版本");
                intentwjbb.putExtra("conditions", (Serializable) mapwjbb);
                startActivityForResult(intentwjbb, 0);
                break;
            case R.id.iv_crc32:
                Map<String, Object> map32 = new HashMap<>();
                if (DemoActivity.instance.BHorFZ) {
                    map32.put("number", "1");
                    map32.put("bhxh", Fragment_Type_Base.instance.ltysbxh.getBhxh() + "");
                    map32.put("rjbb", Fragment_Type_Base.instance.ltysbxh.getRjbb() + "");
                    map32.put("zzcj", Fragment_Type_Base.instance.getzzcj());
                    map32.put("bhlb", Fragment_Type_Base.instance.getBhlb());
                    map32.put("xp", tvXp.getText().toString() + "");
                    map32.put("wjmc", tvWjmc.getText().toString() + "");
                    map32.put("wjbb", tvWjbb.getText().toString() + "");
                } else {
                    map32.put("bhxh", Details1.instance.lty_zzxh);
                    map32.put("rjbb", Details1.instance.lty_rjbb);
                    map32.put("zzcj", Details1.instance.getLty_zzcj());
                    map32.put("bhlb", Details1.instance.getLty_bhlb());
                    if (tvWjmc.getText().toString() != null && !tvWjmc.getText().toString().equals("")) {
                        map32.put("wjmc", tvWjmc.getText().toString());
                    }
                    if (tvWjbb.getText().toString() != null && !tvWjbb.getText().toString().equals("")) {
                        map32.put("wjbb", tvWjbb.getText().toString());
                    }
                }
                Intent intent32 = new Intent(getActivity(), SelectActivity.class);
                intent32.putExtra("type", "CRC32验证码");
                intent32.putExtra("conditions", (Serializable) map32);
                startActivityForResult(intent32, 0);
                break;
            case R.id.iv_md5:
                Map<String, Object> mapmd5 = new HashMap<>();
                if (DemoActivity.instance.BHorFZ) {
                    mapmd5.put("number", "1");
                    mapmd5.put("bhxh", Fragment_Type_Base.instance.ltysbxh.getBhxh() + "");
                    mapmd5.put("rjbb", Fragment_Type_Base.instance.ltysbxh.getRjbb() + "");
                    mapmd5.put("zzcj", Fragment_Type_Base.instance.getzzcj());
                    mapmd5.put("bhlb", Fragment_Type_Base.instance.getBhlb());
                    mapmd5.put("xp", tvXp.getText().toString() + "");
                    mapmd5.put("wjmc", tvWjmc.getText().toString() + "");
                    mapmd5.put("wjbb", tvWjbb.getText().toString() + "");
                    mapmd5.put("crc32", tvCrc32.getText().toString() + "");
                } else {
                    mapmd5.put("bhxh", Details1.instance.lty_zzxh);
                    mapmd5.put("rjbb", Details1.instance.lty_rjbb);
                    mapmd5.put("zzcj", Details1.instance.getLty_zzcj());
                    mapmd5.put("bhlb", Details1.instance.getLty_bhlb());

                    if (tvWjmc.getText().toString() != null && !tvWjmc.getText().toString().equals("")) {
                        mapmd5.put("wjmc", tvWjmc.getText().toString());
                    }
                    if (tvWjbb.getText().toString() != null && !tvWjbb.getText().toString().equals("")) {
                        mapmd5.put("wjbb", tvWjbb.getText().toString());
                    }
                    if (tvCrc32.getText().toString() != null && !tvCrc32.getText().toString().equals("")) {
                        mapmd5.put("crc32", tvCrc32.getText().toString());
                    }
                }
                Intent intentmd5 = new Intent(getActivity(), SelectActivity.class);
                intentmd5.putExtra("type", "MD5校验码");
                intentmd5.putExtra("conditions", (Serializable) mapmd5);
                startActivityForResult(intentmd5, 0);
                break;
            case R.id.iv_wjzzxgsj:
            case R.id.tv_wjzzxgsj:
                if (DemoActivity.instance.BHorFZ && llChangeInfo.getVisibility() != View.VISIBLE) {
                    tvWjmc.clearFocus();
                    tvWjzzxgsj.setFocusableInTouchMode(true);
                    tvWjzzxgsj.setFocusable(true);
                    tvWjzzxgsj.requestFocus();
                }
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMDHM, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        tvWjzzxgsj.setText(date);
                    }
                });
                break;
        }
    }

    /**
     * 是否出变更
     *
     * @param oldLty  原始六统一对象
     * @param newxh   型号
     * @param newrjbb 软件版本
     */
    public void checkIsBg(LTYSBXH oldLty, String newxh, String newrjbb) {
        if (DemoActivity.instance.Similar) {
            llChangeInfo.setVisibility(View.GONE);
        } else {
            if (oldLty != null && !oldLty.getBhxh().equals(newxh)) {
                llChangeInfo.setVisibility(View.VISIBLE);
            } else if (oldLty != null && !oldLty.getRjbb().equals(newrjbb)) {
                llChangeInfo.setVisibility(View.VISIBLE);
            } else if (oldLty != null && !oldLty.getWjmc().equals(tvWjmc.getText().toString())) {
                llChangeInfo.setVisibility(View.VISIBLE);
            } else {
                llChangeInfo.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String type = data.getStringExtra("puttype");
            String value = data.getStringExtra("result");
            switch (type) {
                case "ICD文件名":
                    tvWjmc.setText(value);
                    icd_isc = false;
//                    tvWjzzxgsj.setHint("");
//                    ivWjzzxgsj.setVisibility(View.GONE);
                    if (DemoActivity.instance.BHorFZ) {
                        String wjmc = value + "";
                        if (Fragment_Type_Base.instance.ltysbxh_old.getWjmc() != null && !Fragment_Type_Base.instance.ltysbxh_old.getWjmc().equals(wjmc)&&!DemoActivity.instance.Similar) {
                            setDataByXh(true);
                        } else {
                            setDataByXh(false);
                        }

                        if (!map.isEmpty()) {
                            map.clear();
                        }

                        if (Fragment_Type_Base.instance.ltysbxh.getBhxh() != null) {
                            map.put(LTYSBXHDao.Properties.Bhxh, Fragment_Type_Base.instance.ltysbxh.getBhxh());
                        }
                        if (Fragment_Type_Base.instance.ltysbxh.getZzcj() != null) {
                            map.put(LTYSBXHDao.Properties.Zzcj, Fragment_Type_Base.instance.ltysbxh.getZzcj());
                        }
                        if (Fragment_Type_Base.instance.ltysbxh.getBhlb() != null) {
                            map.put(LTYSBXHDao.Properties.Bhlb, Fragment_Type_Base.instance.ltysbxh.getBhlb());
                        }
                        if (Fragment_Type_Base.instance.ltysbxh.getRjbb() != null) {
                            map.put(LTYSBXHDao.Properties.Rjbb, Fragment_Type_Base.instance.ltysbxh.getRjbb());
                        }
                        if (Fragment_Type_Base.instance.ltysbxh.getXp() != null) {
                            map.put(LTYSBXHDao.Properties.Xp, Fragment_Type_Base.instance.ltysbxh.getXp());
                        }

                        List<LTYSBXH> list = util.getLtyXX(map);
//                        List<Object> list = new DaoUtil(getActivity()).getFZBHSBXHRJBB(true, true,
//                                Fragment_Type_Base.instance.ltysbxh.getBhxh(),
//                                Fragment_Type_Base.instance.getzzcj(),
//                                Fragment_Type_Base.instance.getBhlb(),
//                                Fragment_Type_Base.instance.ltysbxh.getRjbb(), tvXp.getText().toString() + "");
                        if (list.size() > 0) {
                            setDataByWjmc(list.get(0), false);
                        }
                    } else {
                        if (Details1.instance.ltysbxh != null && Details1.instance.ltysbxh.getWjmc() != null) {
                            checkIsBg(Details1.instance.ltysbxh, Details1.instance.lty_xh, Details1.instance.lty_rjbb);
                        }

                        if (!map.isEmpty()) {
                            map.clear();
                        }
                        if (Details1.instance.lty_xh != null) {
                            map.put(LTYSBXHDao.Properties.Bhxh, Details1.instance.lty_xh);
                        }
                        if (Details1.instance.getLty_zzcj() != null) {
                            map.put(LTYSBXHDao.Properties.Zzcj, Details1.instance.getLty_zzcj());
                        }
                        if (Details1.instance.getLty_bhlb() != null) {
                            map.put(LTYSBXHDao.Properties.Bhlb, Details1.instance.getLty_bhlb());
                        }
                        if (Details1.instance.lty_rjbb != null) {
                            map.put(LTYSBXHDao.Properties.Rjbb, Details1.instance.lty_rjbb);
                        }

//                        List<Object> list = new DaoUtil(getActivity()).getFZBHSBXHRJBB(true, true,
//                                Details1.instance.lty_zzxh, Details1.instance.getLty_zzcj(),
//                                Details1.instance.getLty_bhlb(), Details1.instance.lty_rjbb);
                        List<LTYSBXH> list = util.getLtyXX(map);
                        if (list.size() > 0) {
                            setDataByWjmc(list.get(0), false);
                        }
                    }
                    break;
                case "选配功能":
                    if (!tvXp.getText().toString().equals(value)) {
                        tvXp.setText(value);
                        if (Fragment_Type_Base.instance.ltysbxh_old.getXp() != null && !Fragment_Type_Base.instance.ltysbxh_old.getXp().equals(value)&&!DemoActivity.instance.Similar) {
//                            llChangeInfo.setVisibility(View.VISIBLE);
                            setDataByXh(true);
                        } else {
                            setDataByXh(false);
                        }
                    }
                    break;
                case "ICD文件版本":
                    if (!tvWjbb.getText().toString().equals(value)) {
                        tvWjbb.setText(value);
                    }
                    break;
                case "CRC32验证码":
                    if (!tvCrc32.getText().toString().equals(value)) {
                        tvCrc32.setText(value);
                    }
                    break;
                case "MD5校验码":
                    if (!tvMd5.getText().toString().equals(value)) {
                        tvMd5.setText(value);
                    }
                    break;
            }
        }
    }

    /**
     * 通过ICD文件名称带出其他信息
     *
     * @param ltysbxh
     * @param clearWjmc 是否需要清空文件名称
     */
    public void setDataByWjmc(LTYSBXH ltysbxh, boolean clearWjmc) {
//        tvWjbb.setText("");
//        tvCrc32.setText("");
//        tvMd5.setText("");
//        tvWjzzxgsj.setText("");
        tvZyjcpc.setText("");

        if (ltysbxh != null) {
            if (ltysbxh.getWjbb() != null) {
                tvWjbb.setText(ltysbxh.getWjbb() + "");
            }
            if (ltysbxh.getCrc32() != null) {
                tvCrc32.setText(ltysbxh.getCrc32() + "");
            }
            if (ltysbxh.getMd5() != null) {
                tvMd5.setText(ltysbxh.getMd5() + "");
            }
            if (ltysbxh.getZzxgsj() != null) {
                tvWjzzxgsj.setText(TimeUtil.formatString(ltysbxh.getZzxgsj() + "") + "");
            }
            if (ltysbxh.getZyjcpc() != null && !ltysbxh.getZyjcpc().equals("<NULL>")) {
                tvZyjcpc.setText(ltysbxh.getZyjcpc() + "");
            }
        } else {
            if (clearWjmc) {
                tvWjmc.setText("");
                tvWjbb.setText("");
                tvCrc32.setText("");
                tvMd5.setText("");
                tvWjzzxgsj.setText("");
                tvZyjcpc.setText("");
                if (DemoActivity.instance.BHorFZ) {
                    tvXp.setText("");
                }
            }
        }
    }

    /**
     * 通过新增型号变更
     */
    public void setDataByXh(boolean change) {
        if (change&&!DemoActivity.instance.Similar) {
            llChangeInfo.setVisibility(View.VISIBLE);
        } else {
            boolean ischange = false;
            if (Fragment_Type_Base.instance.ltysbxh_old.getZzcj() != null && !Fragment_Type_Base.instance.ltysbxh_old.getZzcj().equals(Fragment_Type_Base.instance.getzzcj() + "")) {
                ischange = true;
            } else {
            }
            if (Fragment_Type_Base.instance.ltysbxh_old.getBhlb() != null && !Fragment_Type_Base.instance.ltysbxh_old.getBhlb().equals(Fragment_Type_Base.instance.getBhlb() + "")) {
                ischange = true;
            } else {
            }
            if (Fragment_Type_Base.instance.ltysbxh_old.getBhxh() != null && !Fragment_Type_Base.instance.ltysbxh_old.getBhxh().equals(Fragment_Type_Base.instance.ltysbxh.getBhxh() + "")) {
                ischange = true;
            } else {
            }
            if (Fragment_Type_Base.instance.ltysbxh_old.getRjbb() != null && !Fragment_Type_Base.instance.ltysbxh_old.getRjbb().equals(Fragment_Type_Base.instance.ltysbxh.getRjbb() + "")) {
                ischange = true;
            } else {
            }
            if (Fragment_Type_Base.instance.ltysbxh_old.getXp() != null && !Fragment_Type_Base.instance.ltysbxh_old.getXp().equals(tvXp.getText().toString() + "")) {
                ischange = true;
            } else {
            }
            if (Fragment_Type_Base.instance.ltysbxh_old.getWjmc() != null && !Fragment_Type_Base.instance.ltysbxh_old.getWjmc().equals(tvWjmc.getText().toString() + "")) {
                ischange = true;
            } else {
            }
            if (ischange&&!DemoActivity.instance.Similar) {
                llChangeInfo.setVisibility(View.VISIBLE);
            } else {
                llChangeInfo.setVisibility(View.GONE);
            }
        }
    }

    //获取ICDXX
    public ICDXX getICDXX(LTYSBXH ltysbxh) {
        ICDXX icdxx = new ICDXX();
        if (ltysbxh.getWjmc() != null) {
            icdxx.setWJMC(ltysbxh.getWjmc() + "");
        } else {
            icdxx.setWJMC("");
        }

        if (ltysbxh.getWjbb() != null) {
            icdxx.setWJBB(ltysbxh.getWjbb() + "");
        } else {
            icdxx.setWJBB("");
        }

        if (ltysbxh.getCrc32() != null) {
            icdxx.setCRC32(ltysbxh.getCrc32() + "");
        } else {
            icdxx.setCRC32("");
        }

        if (ltysbxh.getMd5() != null) {
            icdxx.setMD5(ltysbxh.getMd5() + "");
        } else {
            icdxx.setMD5("");
        }

        if (ltysbxh.getJymscsj() != null) {
            icdxx.setJYMSCSJ(TimeUtil.formatString(ltysbxh.getJymscsj()) + "");
        } else {
            icdxx.setJYMSCSJ("");
        }
        return icdxx;
    }

    boolean isFromSaoma = false;
    boolean six_one = false;
    String six_one_details = "";
    String state = "";
    String code2 = "";
    String code3 = "";

    public void checkICD(Long id, boolean saoma, boolean sixone,
                         String sixoneD, String states, String code_2, String code_3) {
        //**********************************ICD保存*****************************************************
        isFromSaoma = saoma;
        six_one = sixone;
        six_one_details = sixoneD + "";
        state = states + "";
        code2 = code_2 + "";
        code3 = code_3 + "";

        if (state.equals("C")) {
            if (isFromSaoma) {//扫码新增
                if (six_one && six_one_details.equals("2013版")) {
                    if (DemoActivity.instance.getIntent().hasExtra("ICDXX")) {
                        if (code2 == null) {  //编辑了
                            //保存新的icd信息
                            List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code3 + "");
                            LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                            ICDXX icdxx1 = getICDXX(ltysbxh);
                            icdxx1.setID(util.getInsertId("ICDXX"));
                            icdxx1.setZSJID(id);
                            icdxx1.setZSJTYPE("BHPZ");
                            icdxx1.setED_TAG("C");
                            util.coreSave(icdxx1);
                            ltysbxhList.clear();

                            //保存旧的ICD信息 先弹框设置变更时间和变更原因,再保存  dates//变更时间  reasons//变更原因
                            ICDXX icdxx = (ICDXX) DemoActivity.instance.getIntent().getSerializableExtra("ICDXX");
                            icdChangeDialog2(icdxx, id, "saoma", "BHSB");
                        } else { //未编辑
                            ICDXX icdxx = (ICDXX) DemoActivity.instance.getIntent().getSerializableExtra("ICDXX");
                            icdxx.setID(util.getInsertId("ICDXX"));
                            icdxx.setZSJID(id);
                            icdxx.setZSJTYPE("BHPZ");
                            icdxx.setED_TAG("C");
                            util.coreSave(icdxx);

                            ToastUtils.showToast(getActivity(), "保存成功");
                        }
                    } else {
                        //新增
                        if (six_one && six_one_details.equals("2013版") && code2 == null) {
                            //保存新的icd信息
                            List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code3 + "");
                            LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                            ICDXX icdxx1 = getICDXX(ltysbxh);
                            icdxx1.setID(util.getInsertId("ICDXX"));
                            icdxx1.setZSJID(id);
                            icdxx1.setZSJTYPE("BHPZ");
                            icdxx1.setED_TAG("C");
                            util.coreSave(icdxx1);
                            ltysbxhList.clear();

                            ToastUtils.showToast(getActivity(), "保存成功");
                        } else {
                            ToastUtils.showToast(getActivity(), "保存成功");
                        }
                    }
                } else {
                    //新增
                    if (six_one && six_one_details.equals("2013版") && code2 == null) {
                        //保存新的icd信息
                        List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code3 + "");
                        LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                        ICDXX icdxx1 = getICDXX(ltysbxh);
                        icdxx1.setID(util.getInsertId("ICDXX"));
                        icdxx1.setZSJID(id);
                        icdxx1.setZSJTYPE("BHPZ");
                        icdxx1.setED_TAG("C");
                        util.coreSave(icdxx1);
                        ltysbxhList.clear();

                        ToastUtils.showToast(getActivity(), "保存成功");
                    } else {
                        ToastUtils.showToast(getActivity(), "保存成功");
                    }
                }
            } else {
                //新增
                if (six_one && six_one_details.equals("2013版") && code2 == null) {
                    //保存新的icd信息
                    List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code3 + "");
                    LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                    ICDXX icdxx1 = getICDXX(ltysbxh);
                    icdxx1.setID(util.getInsertId("ICDXX"));
                    icdxx1.setZSJID(id);
                    icdxx1.setZSJTYPE("BHPZ");
                    icdxx1.setED_TAG("C");
                    util.coreSave(icdxx1);
                    ltysbxhList.clear();

                    ToastUtils.showToast(getActivity(), "保存成功");
                } else {
                    ToastUtils.showToast(getActivity(), "保存成功");
                }
            }

        } else {
            //编辑时候ICD的保存
            List<Object> icdList = util.getICDOrBKXX(ICDXX.class, DeviceDetailsActivity.bhpz.getId() + "", "BHPZ"); //从库里取ICD信息
            icdList.toString();
            if (icdList.size() > 0) {
                for (int i = 0; i < icdList.size(); i++) {
                    ICDXX icdxx = (ICDXX) icdList.get(i);
                    icdxxs2.add(icdxx);
                    if (isAdd(icdxx)) {
                        icdxxs.add(icdxx);
                    }
                }
                icdList.clear();
            }

            if (six_one && six_one_details.equals("2013版")) {
                if (code2 == null) { //修改过ICD信息
                    //保存新的icd信息
                    List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code3 + "");
                    LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                    ICDXX icdxx1 = getICDXX(ltysbxh);
                    icdxx1.setID(util.getInsertId("ICDXX"));
                    icdxx1.setZSJID(id);
                    icdxx1.setZSJTYPE("BHPZ");
                    icdxx1.setED_TAG("C");
                    util.coreSave(icdxx1);
                    ltysbxhList.clear();

                    //保存旧的ICD信息 先弹框设置变更时间和变更原因,再保存  dates//变更时间  reasons//变更原因
                    if (icdxxs != null && icdxxs.size() > 0) {
                        ICDXX icdxx = icdxxs.get(0);
                        icdChangeDialog2(icdxx, id, "feisaoma", "BHSB");
//                            icdxx.setBGSJ(dates);
//                            icdxx.setBGYY(reasons);
//                            zhikong();
//                            icdxx.setID(util.getInsertId("ICDXX"));
//                            icdxx.setZSJID(fzbhsbId);
//                            icdxx.setZSJTYPE("FZBHSB");
//                            util.coreSave(icdxx);
//                            icdxxs.clear();
                    } else {
                        ToastUtils.showToast(getActivity(), "保存成功");
                    }
                } else {
                    ToastUtils.showToast(getActivity(), "保存成功");
                }

            } else { //删除库里的ICD信息
                if (icdxxs2 != null && icdxxs2.size() > 0) {
                    for (int i = 0; i < icdxxs2.size(); i++) {
                        ICDXX icdxxss = icdxxs2.get(i);
                        if (icdxxss.getED_TAG() != null && icdxxss.getED_TAG().equals("C")) {
                            util.coreICD("D", icdxxss); //删除数据表
                        } else { //只更新
                            icdxxss.setED_TAG("D");
                            icdxxss.setSB("D");
                            util.coreICD("M", icdxxss); //更新数据表
                        }

                    }
                    icdxxs2.clear();
                }
                ToastUtils.showToast(getActivity(), "保存成功");
            }
        }
    }

    //ICD变更时间和原因添加对话框
    private CustomDialog dialog;
    private MyDatePickerDialog dialogs;
    private int mYear, mMonth, mDay;
    private Calendar c;
    String dates = null; //变更时间
    String reasons = null; //变更原因

    public void zhikong() {
        String dates = null; //变更时间
        String reasons = null; //变更原因
    }


    public void icdChangeDialog2(final ICDXX icdxx, final Long sbID, final String saoma, final String type) {
        if (dialog != null && dialog.isShowing()) {
            return;
        } else {

            View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_icd_change_item, null);
            dialog = new CustomDialog(getActivity(), R.style.dialog_alert_style, 0);

            //Dialog中拦截返回键
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && event.getAction() == KeyEvent.ACTION_UP) {
                        ToastUtils.showToast(getActivity(), "台账正在保存中,请先点击  确定  ");
                        return true;
                    }
                    return false;
                }
            });

            final EditText reason = (EditText) view.findViewById(R.id.reason);
            final TextView qianfeng = (TextView) view.findViewById(R.id.qianfeng);

            Button queding = (Button) view.findViewById(R.id.queding);
            Button quxiao = (Button) view.findViewById(R.id.quxiao);


            //铅封日期
            qianfeng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogs = new MyDatePickerDialog(getActivity(), new MyDatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            mYear = year;
                            mMonth = monthOfYear + 1;
                            mDay = dayOfMonth;
                            qianfeng.setText(mYear + "-" + mMonth + "-" + mDay);
                            dates = TimeUtil.formatString(mYear + "-" + mMonth + "-" + mDay);
                        }
                    }, mYear, mMonth, mDay);

                    if (mYear > 0) {
                        dialogs.updateDate(mYear, mMonth, mDay);
                    }
                    dialogs.myShow();
                }
            });


            //确定
            queding.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String reason2 = reason.getText().toString().trim();
                    if (dates == null || "".equals(dates) || reason2 == null || "".equals(reason2)) {
                        ToastUtils.showLongToast(getActivity(), "变更时间和变更原因不能为空");
                    } else {
                        reasons = reason2;

                        icdxx.setBGSJ(dates);
                        icdxx.setBGYY(reasons);
                        zhikong();
                        if (saoma.equals("saoma")) {
                            if (type.equals("BHSB")) {
                                icdxx.setID(util.getInsertId("ICDXX"));
                                icdxx.setZSJID(sbID);
                                icdxx.setZSJTYPE("BHPZ");
                                icdxx.setED_TAG("C");
                                util.coreSave(icdxx); //保存
                            } else if (type.equals("FZSB")) {
                                icdxx.setID(util.getInsertId("ICDXX"));
                                icdxx.setZSJID(sbID);
                                icdxx.setZSJTYPE("FZBHSB");
                                icdxx.setED_TAG("C");
                                util.coreSave(icdxx); //保存
                            }
                        } else {
                            util.coreICD("M", icdxx); //更新
                        }
                        icdxxs.clear();
                        dialog.dismiss();
//                        DeviceDetailsActivity.this.finish();
                        ToastUtils.showToast(getActivity(), "保存成功");
                    }
                }
            });

            //取消
//            quxiao.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });


            dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }


    private void showDatePickDialog(DateType type) {
        DatePickDialog dialog = new DatePickDialog(getActivity());
        //设置上下年分限制
        dialog.setYearLimt(20);
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
//                Toast.makeText(MainActivity.this,date+"",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    //过滤要显示的ICD
    public Boolean isAdd(ICDXX icdxx) {
        Boolean yy = false; //原因
        yy = icdxx.getBGYY() == null || "".equals(icdxx.getBGYY());

        Boolean sj = false; //原因
        sj = icdxx.getBGSJ() == null || "".equals(icdxx.getBGSJ());

        Boolean edTag = false; //标记删除
        edTag = icdxx.getED_TAG() == null || !icdxx.getED_TAG().equals("D");


        //sb是非D或者变更时间和变更原因都为null时,返回true, 加载
        if (icdxx.getSB() != null) {
            return !icdxx.getSB().equals("D") && yy && sj && edTag;
        } else {
            return icdxx.getSB() == null && yy && sj && edTag;
        }
    }

    List<Object> list;

    public boolean checkIcd() {
        boolean checkicd = true;
        String icdwjmc = tvWjmc.getText().toString() + "";
        String zzxgsj = tvWjzzxgsj.getText().toString();
        icd_isc = false;
        if (icdwjmc.isEmpty() || icdwjmc.equals("")) {
            checkicd = false;
            ToastUtils.showToast(getActivity(), "ICD文件名称不能为空");
        } else if (zzxgsj.isEmpty() && icd_isc || zzxgsj.equals("") && icd_isc) {
            checkicd = false;
            ToastUtils.showToast(getActivity(), "ICD文件最终修改时间不能为空");
        } else if (llChangeInfo.getVisibility() == View.VISIBLE) {
            //判断ICD信息，存在变更信息
            String reason = tvChangeReason.getText().toString() + "";
            String time = tvChangeTime.getText().toString() + "";
            if (reason.isEmpty() || reason.equals("")) {
                checkicd = false;
                ToastUtils.showToast(getActivity(), "请填写ICD信息变更原因");
            } else if (time.isEmpty() || reason.equals("")) {
                checkicd = false;
                ToastUtils.showToast(getActivity(), "请填写ICD信息变更时间");
            }
        }
        if (saleVo.size()>0){
            for (SaleAttributeVo vo : saleVo) {
                if (DemoActivity.instance.BHorFZ&&vo.getValue().equals("选配功能")&&(tvXp.getText().toString()+"").equals("")){
                    checkicd = false;
                    ToastUtils.showToast(getActivity(), "请填写选配功能");
                }
                if (vo.getValue().equals("ICD文件名称")&&(tvWjmc.getText().toString()+"").equals("")){
                    checkicd = false;
                    ToastUtils.showToast(getActivity(), "请填写ICD文件名称");
                }
                if (vo.getValue().equals("ICD文件版本")&&(tvWjbb.getText().toString()+"").equals("")){
                    checkicd = false;
                    ToastUtils.showToast(getActivity(), "请填写ICD文件版本");
                }
                if (vo.getValue().equals("CRC32验证码")&&(tvCrc32.getText().toString()+"").equals("")){
                    checkicd = false;
                    ToastUtils.showToast(getActivity(), "请填写CRC32验证码");
                }
                if (vo.getValue().equals("MD5校验码")&&(tvMd5.getText().toString()+"").equals("")){
                    checkicd = false;
                    ToastUtils.showToast(getActivity(), "请填写MD5校验码");
                }
                if (vo.getValue().equals("ICD文件最终修改时间")&&(tvWjzzxgsj.getText().toString()+"").equals("")){
                    checkicd = false;
                    ToastUtils.showToast(getActivity(), "请填写ICD文件最终修改时间");
                }
            }
        }
        if (DemoActivity.instance.BHorFZ) {
            list = util.getBHRJBBByCode(true, true, DemoActivity.instance.bhsb.getId() + "");
        } else {
            DemoActivity.instance.fzbhsb.setIcdwjmc(icdwjmc);
        }
        return checkicd;
    }

    public void saveICD(Long bhpzIDss) {
        //判断是否变更
        String type = "";
        String saoma = "";
        LTYSBXH ltysbxh = new LTYSBXH();
        LTYSBXH ltysbxh_old = new LTYSBXH();
        if (DemoActivity.instance.BHorFZ) {
            //非六统一转六统一新增记得逻辑赋值(界面独立赋值)
//            ltysbxh = Fragment_Type_Base.instance.ltysbxh;
            Fragment_Type_Base.instance.ltysbxh.setWjmc(tvWjmc.getText().toString() + "");
            Fragment_Type_Base.instance.ltysbxh.setXp(tvXp.getText().toString() + "");
            Fragment_Type_Base.instance.ltysbxh.setWjbb(tvWjbb.getText().toString() + "");
            Fragment_Type_Base.instance.ltysbxh.setCrc32(tvCrc32.getText().toString() + "");
            Fragment_Type_Base.instance.ltysbxh.setMd5(tvMd5.getText().toString() + "");
            Fragment_Type_Base.instance.ltysbxh.setZzxgsj(TimeUtil.formatString(tvWjzzxgsj.getText().toString() + "") + "");
            Fragment_Type_Base.instance.ltysbxh.setZyjcpc(tvZyjcpc.getText().toString() + "");
            Fragment_Type_Base.instance.ltysbxh.setBhxh(Fragment_Type_Base.instance.ltysbxh.getBhxh() + "");
            Fragment_Type_Base.instance.ltysbxh.setRjbb(Fragment_Type_Base.instance.ltysbxh.getRjbb() + "");

//            List<Object> list = new DaoUtil(getActivity()).getBHRJBBByCode(true, true, DemoActivity.instance.bhsb.getId() + "");
            if (list != null && list.size() > 0) {
                ltysbxh_old = (LTYSBXH) list.get(0);
            } else {
                ltysbxh_old = Fragment_Type_Base.instance.ltysbxh_old;
            }
            type = "BHPZ";
            DemoActivity.instance.bhsb.setXp(Fragment_Type_Base.instance.ltysbxh.getXp() + "");
            DemoActivity.instance.bhsb.setIcdwjmc(Fragment_Type_Base.instance.ltysbxh.getWjmc() + "");
//            bhpzIDss = DemoActivity.instance.bhsb.getId();
            ltysbxh = Fragment_Type_Base.instance.ltysbxh;
        } else {
            Details1.instance.ltysbxh_add.setWjmc(tvWjmc.getText().toString() + "");
            Details1.instance.ltysbxh_add.setWjbb(tvWjbb.getText().toString() + "");
            Details1.instance.ltysbxh_add.setCrc32(tvCrc32.getText().toString() + "");
            Details1.instance.ltysbxh_add.setMd5(tvMd5.getText().toString() + "");
            Details1.instance.ltysbxh_add.setZzxgsj(TimeUtil.formatString(tvWjzzxgsj.getText().toString() + "") + "");
            Details1.instance.ltysbxh_add.setZyjcpc(tvZyjcpc.getText().toString() + "");
            type = "FZBHSB";
            ltysbxh_old = Details1.instance.ltysbxh;
            ltysbxh = Details1.instance.ltysbxh_add;
        }

        if (llChangeInfo.getVisibility() == View.VISIBLE) {
            String reason = tvChangeReason.getText().toString() + "";
            String time = tvChangeTime.getText().toString() + "";
            //先存新值
            ICDXX icdxx1 = getICDXX(ltysbxh);
            icdxx1.setID(util.getInsertId("ICDXX"));
            icdxx1.setZSJID(bhpzIDss);
            icdxx1.setZSJTYPE(type + "");
            icdxx1.setED_TAG("C");
            util.coreSave(icdxx1);

//          扫码带出的变更保存,
            ICDXX icdxx = getICDXX(ltysbxh_old);
            icdxx.setBGSJ(time);
            icdxx.setBGYY(reason);
            //扫码带出的变更
            if (DemoActivity.instance.getIntent().hasExtra("ICDXX")) {
                icdxx = (ICDXX) DemoActivity.instance.getIntent().getSerializableExtra("ICDXX");
                icdxx.setID(util.getInsertId("ICDXX"));
                icdxx.setZSJID(bhpzIDss);
                icdxx.setZSJTYPE(type + "");
                icdxx.setED_TAG("C");
                icdxx.setBGSJ(time);
                icdxx.setBGYY(reason);
                util.coreSave(icdxx); //保存
            } else {
                //编辑时候ICD的保存
                //从库里取ICD信息
                String icdxxid = "";
                String icd_type = "";
                if (DemoActivity.instance.BHorFZ) {
                    icdxxid = DemoActivity.instance.bhsb.getId() + "";
                    icd_type = "BHPZ";
                } else {
                    icdxxid = DemoActivity.instance.fzbhsb.getId() + "";
                    icd_type = "FZBHSB";
                }
                List<Object> icdList = util.getICDOrBKXX(ICDXX.class, icdxxid + "", icd_type + "");
                if (icdList != null && icdList.size() >= 2) {
                    icdxx = (ICDXX) icdList.get(icdList.size() - 2);
                    icdxx.setBGSJ(time);
                    icdxx.setBGYY(reason);
                    util.coreICD("M", icdxx); //更新
                }
            }
        } else {
            if (DemoActivity.instance.btn_details_right.getText().toString().equals("核对无误")) {
                Log.d("check","0");
            }else if (DemoActivity.instance.getIntent().hasExtra("ICDXX")) {
                ICDXX icdxx = (ICDXX) DemoActivity.instance.getIntent().getSerializableExtra("ICDXX");
                icdxx.setID(util.getInsertId("ICDXX"));
                icdxx.setZSJID(bhpzIDss);
                icdxx.setZSJTYPE(type + "");
                icdxx.setED_TAG("C");
                util.coreSave(icdxx);
            } else if (ltysbxh_old != null && ltysbxh_old.getWjmc() != null &&
                    ltysbxh.getWjmc().equals(ltysbxh_old.getWjmc()) && !DemoActivity.instance.Similar) {
                Log.d("check","1");
            } else {
                ICDXX icdxx1 = getICDXX(ltysbxh);
                icdxx1.setID(util.getInsertId("ICDXX"));
                icdxx1.setZSJID(bhpzIDss);
                icdxx1.setZSJTYPE(type + "");
                icdxx1.setED_TAG("C");
                util.coreSave(icdxx1);
            }
        }
    }
}
