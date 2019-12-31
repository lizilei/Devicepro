package cn.com.sgcc.dev.view.fragment.BHSB;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import cn.com.sgcc.dev.adapter.TypeBaseAdapter;
import cn.com.sgcc.dev.customeView.MyListView;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.BHPZXHBBGX;
import cn.com.sgcc.dev.model2.BHSBXHB;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.ICDXX;
import cn.com.sgcc.dev.model2.LTYSBXH;
import cn.com.sgcc.dev.model2.ZZCJ;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.MyScrollView;
import cn.com.sgcc.dev.utils.NewScrollView;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.SelectActivity;
import cn.com.sgcc.dev.view.fragment.BaseFragment;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details2;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details8;
import cn.com.sgcc.dev.view.viewinterface.DateChooseListener;
import cn.com.sgcc.dev.view.viewinterface.ViewAddListener;

import static android.app.Activity.RESULT_OK;


/**
 * <p>@description:</p>
 * 基本信息 fragment
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */

@SuppressLint("ValidFragment")
public class Fragment_Type_Base extends BaseFragment implements MyScrollView.OnScrollListener {
    @BindView(R.id.ll_rjbb)
    LinearLayout llRjbb;
    public TypeBaseAdapter adapter;
    //    private DeviceRjbbAdapter adapter;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    Unbinder unbinder;

    //六统一标准版本、装置类别、出厂日期、装置类别细化、设备功能配置，生成日期,装置分类、装置类型,实物ID
    @BindViews(value = {R.id.tv_one_device_two_right,
            R.id.tv_one_device_four_right,
            R.id.tv_one_device_six_right,
            R.id.tv_one_device_seven_right,
            R.id.tv_one_device_eight_right,
            R.id.tv_scrq_device_name_right,
            R.id.tv_fl_device_name_right,
            R.id.tv_lx_device_name_right,
            R.id.tv_one_device_name_rights,
    })
    List<TextView> textViews;
    //1实物ID,2装置名称，3六统一标准版本、4制造厂家、5装置类别、6装置型号、
    // 7装置分类、8装置类型,9软件版本、10校验码、11生成日期、12出厂日期、13装置类别细化、14设备功能配置，15板卡数量,
    @BindViews(value = {R.id.tv_one_device_name_lefts,
            R.id.tv_one_device_name_left,
            R.id.tv_one_device_two_left,
            R.id.tv_one_device_three_left,
            R.id.tv_one_device_four_left,
            R.id.tv_one_device_five_left,
            R.id.tv_fl_device_name_left,
            R.id.tv_lx_device_name_left,
            R.id.tv_rjbb_device_name_left,
            R.id.tv_jym_device_name_left,
            R.id.tv_scrq_device_name_left,
            R.id.tv_one_device_six_left,
            R.id.tv_one_device_seven_left,
            R.id.tv_one_device_eight_left,
            R.id.tv_one_device_nine_left,
    })
    List<TextView> textViews_check;

    //实物ID,,装置名称、六统一标准版本、制造厂家、装置类别、装置型号、装置分类、装置类型,软件版本、校验码，生成日期
    // 出厂日期、装置类别细化、设备功能配置，板卡数量
    @BindViews(value = {R.id.et_one_device_name_rights, R.id.et_one_device_name_right,
            R.id.tv_one_device_two_right,
            R.id.et_one_device_three_right,
            R.id.tv_one_device_four_right,
            R.id.et_one_device_five_right,
            R.id.tv_fl_device_name_right,
            R.id.tv_lx_device_name_right,
            R.id.et_rjbb_device_name_right,
            R.id.et_jym_device_name_right,
            R.id.tv_scrq_device_name_right,
            R.id.tv_one_device_six_right,
            R.id.tv_one_device_seven_right,
            R.id.tv_one_device_eight_right,
            R.id.et_one_device_nine_right,
    })
    List<TextView> textViews_check_bt;
    //装置类别细化改名称，设备功能配置（改名称）
    @BindViews(value = {
            R.id.tv_one_device_seven_left,
            R.id.tv_one_device_eight_left
    })
    List<TextView> textViewleft;

    //装置名称、制造厂家、装置型号、软件版本、校验码、板卡数量 实物码
    @BindViews(value = {R.id.et_one_device_name_right, R.id.et_one_device_three_right, R.id.et_one_device_five_right
            , R.id.et_rjbb_device_name_right
            , R.id.et_jym_device_name_right
            , R.id.et_one_device_nine_right
            , R.id.et_one_device_name_rights
    })
    List<EditText> editTexts;

    @BindView(R.id.rl_two)
    RelativeLayout rlTwo;
    @BindView(R.id.rl_more_mk)
    public RelativeLayout rl_more_mk;
    @BindView(R.id.fragment_check_all_mk)
    Button fragment_check_all_mk;

    //    public MyListView list_one;
    public List<BHXHRJBB> list_one_data;
    public static Fragment_Type_Base instance = null;
    @BindView(R.id.rl_eight)
    RelativeLayout rlEight;
    @BindView(R.id.cb_ddzz_true)
    CheckBox cbDdzzTrue;
    //装置名称、制造厂家、装置型号、软件版本、校验码、板卡数量、分类、类型、保护名称
    @BindViews(value = {R.id.fragment_add_bt_two
            , R.id.fragment_add_bt_three
            , R.id.fragment_add_bt_four
            , R.id.fragment_add_bt_five
            , R.id.fragment_add_bt_rjbb
            , R.id.fragment_add_bt_jym
            , R.id.fragment_add_bt_scrq
            , R.id.fragment_add_bt_six
            , R.id.fragment_add_bt_seven
            , R.id.fragment_add_bt_eight
            , R.id.fragment_add_bt_swm
            , R.id.fragment_add_bt_fl
            , R.id.fragment_add_bt_lx
            , R.id.fragment_add_bt_one
    })
    List<Button> buttons;
    @BindView(R.id.ll_sfjrddzz)
    LinearLayout llSfjrddzz;

    @BindView(R.id.cb_true)
    CheckBox cbTrue;
    @BindView(R.id.cb_end_true)
    CheckBox cbEndTrue;
    @BindView(R.id.cb_xh_sffmk)
    CheckBox cbXhSffmk;
    @BindView(R.id.ll_hassffmk)
    LinearLayout llHassffmk;
    @BindView(R.id.ll_fl_lx)
    LinearLayout llFlLx;
    @BindView(R.id.rl_jym)
    RelativeLayout rlJym;
    @BindView(R.id.rl_scrq)
    RelativeLayout rlScrq;
    @BindView(R.id.list_one)
    MyListView listOne;
    @BindView(R.id.sv_all)
    MyScrollView svAll;
    private ViewAddListener addListener;

    public boolean isLJQ = false;
    public boolean isAK = false;
    public boolean isTd = false;
    //是否有模块信息显示
    public boolean ismodel_type;

//    装置名称、是否六统一、六统一标准版本、制造厂家、装置类别、装置型号、
//    模块名称、软件版本、校验码、软件版本生成时间、出厂日期、装置类别细化\设备类型、
//    设备功能配置（若是安自装置才显示）、故障录波器类型、测距形式、是否接入调度主站、
//    是否就地化设备、

    private boolean lty;
    public boolean is2013;
    private String ltybzbb = "";
    private String zzcj;
    private String zzlb;
    private String zzxh;
    private String rjbb;
    private String jym;
    private String jymscsj;
    private String ccrq;
    private String zzlbxh;
    private String sbgnpz;
    private String gzlbqlx;
    private String cjxx;
    private String dydj;
    private boolean sfjrddzz;
    public boolean sfjdhsb;
    //保护名称
    private String protect_name = "";
    //保护名称重复判断
    public boolean protect_name_rep = false;
    //保护类别
    private String protect_type = "";
    //保护类别细化
    private String protect_type_details = "";
    //设备类型
    private String device_type = "";
    //制造厂家
    private String made_company = "";
    //故障录波器类型
    private String machine_type = "";
    //测距形式
    private String test_type = "";
    //是否调度主
    private String isask = "";
    //设备功能配置
    private String device_work_set = "";
    //装置型号
    public String protect_type_m = "";
    //装置分类
    private String protect_type_fl = "";
    //装置类型
    private String protect_type_lx = "";
    //软件版本
    private String ver_info = "";
    //校验码
    private String jym_info = "";
    //生成日期
    private String scrq_info = "";
    //生成日期
    public String ccrq_info = "";

    private IDaoUtil util;

    public ZZCJ zzcj_add; //厂家新增使用
    public BHSBXHB bhsbxhb;//装置型号


    public List<BHXHRJBB> rjbbList;
    public LTYSBXH ltysbxh;
    public LTYSBXH ltysbxh_old;

    public static int RoadOrAk = 0;
    public static Map<String, String> codeMap = new HashMap<>();
    public static Map<String, String> bblxMap = new HashMap<>();
    public static Map<String, String> flMap = new HashMap<>();
    public static Map<String, String> lxMap = new HashMap<>();
    public Map<String, String> bbToCode = new HashMap<>();//存储软件版本对应型号code，key-bb，value-bhxhcode

    public Map<String, String> dateMap = new HashMap<>();//存储六统一软件版本时间

    public boolean isSave; //是否未保存
    public boolean isC; //是否新增
    private int local = 0;
    private boolean isEdit;

    private long mLastTime = 0;

    public boolean check = false;
    public List<SaleAttributeVo> saleVo;

    public Map<String, TextView> map_key = new HashMap<>();
    public Map<String, TextView> map_more = new HashMap<>();
    public boolean isShow;
    public boolean bt_mkmc;
    public boolean bt_rjbb;
    public boolean bt_jym;
    public boolean bt_scsj;


    public Fragment_Type_Base(ViewAddListener addListener) {
        this.addListener = addListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_type_base;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
//        mLastTime = System.currentTimeMillis();
        Log.e("times", mLastTime + "onCreate");
    }

    @Override
    public void initview() {
        Log.e("times", System.currentTimeMillis() - mLastTime + "onCreate_initview");
        mLastTime = System.currentTimeMillis();
        if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C") && !DemoActivity.instance.Similar) {
            tvBottom.setVisibility(View.GONE);
        } else {
            tvBottom.setVisibility(View.VISIBLE);
            if (DemoActivity.instance.rzgl != null) {
                tvBottom.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
            } else {
                tvBottom.setText("本条台账最后一次修改时间：");
            }
        }

        svAll.setOnScrollListener(this);

        //软件版本必填
        editTexts.get(3).setHint(R.string.hint2);
        //校验码必填
        editTexts.get(4).setHint(R.string.hint2);
        //出厂日期非必填
        textViews.get(2).setHint("");
        //板卡数量非必填
        editTexts.get(5).setHint("");
        //设备功能配置默认隐藏
        rlEight.setVisibility(View.GONE);
        //默认不显示
        rl_more_mk.setVisibility(View.GONE);
//        textViewleft.get(0).setdraw
    }


    @Override
    public void initEvevt() {
        Log.e("times", System.currentTimeMillis() - mLastTime + "initview_initEvevt");
        mLastTime = System.currentTimeMillis();
        list_one_data = new ArrayList<>();
//
        adapter = new TypeBaseAdapter(getActivity());
        listOne.setAdapter(adapter);
        listOne.setVisibility(View.GONE);
//        setListViewHeightBasedOnChildren(list_one);
        ltysbxh = new LTYSBXH();
        ltysbxh_old = new LTYSBXH();

        //保护名称
        editTexts.get(0).addTextChangedListener(new TextWatcher() {
            String before = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                before = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                protect_name_rep = false;
            }
        });

        //制造厂家
        editTexts.get(1).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //装置型号清空
                editTexts.get(2).setText("");
            }
        });
        //装置型号
        editTexts.get(2).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //装置型号查询
                changeXh();
            }
        });
        //装置型号
        editTexts.get(2).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus && !isSave) {
                if (false) {
                    //判断型号是否需要新增
                    List<Object> xhList = util.getBHXH(lty, is2013, editTexts.get(1).getText() + "", textViews.get(1).getText() + "", DemoActivity.instance.bhsb.getBdzlx() + ""
                            , DemoActivity.instance.bhsb.getDydj() + "", "", editTexts.get(2).getText().toString() + "");
                    if (xhList != null && xhList.size() > 0) {
                        if (lty && is2013) {
                            if (xhList.get(0) != null) {
                                LTYSBXH ltysbxh = (LTYSBXH) xhList.get(0);
                                codeMap.put(ltysbxh.getBhxh(), ltysbxh.getCode());
                                bblxMap.put(ltysbxh.getBhxh(), ltysbxh.getBblx());
                                textViews.get(6).setText(ltysbxh.getBhfl() + "");
                                textViews.get(7).setText(ltysbxh.getBhlx() + "");
                                setRjbb(ltysbxh.getBhxh() + "", false, true);
                                buttons.get(buttons.size() - 2).setVisibility(View.GONE);
                                buttons.get(buttons.size() - 3).setVisibility(View.GONE);
                            } else {
                                cbXhSffmk.setChecked(false);
                                setRjbb(editTexts.get(2).getText().toString(), true, true);
                                llFlLx.setVisibility(View.VISIBLE);
                                codeMap.clear();
                                //分类类型选择
                                buttons.get(buttons.size() - 2).setVisibility(View.VISIBLE);
                                buttons.get(buttons.size() - 3).setVisibility(View.VISIBLE);
//                                textViews.get(6).setText("");
//                                textViews.get(7).setText("");
                            }
                        } else {
                            if (xhList.get(0) != null) {
                                BHSBXHB bhsbxhb = (BHSBXHB) xhList.get(0);
                                codeMap.put(bhsbxhb.getSbxh(), bhsbxhb.getCode());
                                bblxMap.put(bhsbxhb.getSbxh(), bhsbxhb.getBblx());
                                textViews.get(6).setText(bhsbxhb.getBhfl() + "");
                                textViews.get(7).setText(bhsbxhb.getBhlx() + "");
                                setRjbb(editTexts.get(2).getText().toString() + "", false, true);
                                buttons.get(buttons.size() - 2).setVisibility(View.GONE);
                                buttons.get(buttons.size() - 3).setVisibility(View.GONE);
                            } else {
                                cbXhSffmk.setChecked(false);
                                setRjbb(editTexts.get(2).getText().toString(), true, true);
                                llFlLx.setVisibility(View.VISIBLE);
                                codeMap.clear();
                                //分类类型选择
                                buttons.get(buttons.size() - 2).setVisibility(View.VISIBLE);
                                buttons.get(buttons.size() - 3).setVisibility(View.VISIBLE);
//                                textViews.get(6).setText("");
//                                textViews.get(7).setText("");
                            }
                        }
                    } else {

                        cbXhSffmk.setChecked(false);
                        setRjbb(editTexts.get(2).getText().toString(), true, true);
                        llFlLx.setVisibility(View.VISIBLE);
                        codeMap.clear();
                        //分类类型选择
                        buttons.get(buttons.size() - 2).setVisibility(View.VISIBLE);
                        buttons.get(buttons.size() - 3).setVisibility(View.VISIBLE);
//                        textViews.get(6).setText("");
//                        textViews.get(7).setText("");
                    }
                }
            }
        });
        //软件版本
        editTexts.get(3).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //icd清空
                if (is2013) {
                    //  软件版本和校验码和生成日期
                    String lty_rjbb = editTexts.get(3).getText().toString() + "";
                    ltysbxh.setRjbb(lty_rjbb + "");
                    if (ltysbxh_old.getRjbb() != null && !ltysbxh_old.getRjbb().equals(lty_rjbb)) {
                        if (Details8.instance != null) {
//                            Details8.instance.setDataByWjmc(null, true);
                            Details8.instance.setDataByXh(true);
                        }
                    } else {
                        if (Details8.instance != null) {
                            Details8.instance.setDataByXh(false);
                        }
                    }
                }
            }
        });
        //校验码
        editTexts.get(4).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //清空ICD
                if (false) {
                    ltysbxh.setRjbb(editTexts.get(3).getText() + " " + textViews.get(5).getText() + " " + editTexts.get(4).getText() + "");
                    if (lty && is2013) {
                        //  软件版本和校验码和生成日期
                        String lty_rjbb = editTexts.get(3).getText().toString() + " " + textViews.get(5).getText().toString() + " " + editTexts.get(4).getText().toString();
                        ltysbxh.setRjbb(lty_rjbb + "");
                        if (Details8.instance != null) {
                            Details8.instance.setDataByWjmc(null, true);
                        }
                    }
                }
            }
        });

        llFlLx.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          editTexts.get(3).setFocusable(true);
                                          editTexts.get(3).requestFocus();
//                                          editTexts.get(2).setFocusable(true);
                                      }
                                  }
        );

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
    }

    @Override
    public void initData() {
        //初始化必填项
        init();
        mLastTime = System.currentTimeMillis();
        isEdit = getArguments().getBoolean("isEdit", false);
//        initReceiver(isEdit);

        util = new DaoUtil(getActivity());

        rjbbList = new ArrayList<>();

        if (DemoActivity.instance.bhsb == null) {
            isC = true;
        }
//        isC = DemoActivity.instance.bhsb.getEd_tag().equals("C");
//        isC = true;

        //新增或者编辑开关
        if (isC) {
            if (!DemoActivity.instance.isSwid) {
                editTexts.get(6).setText(getActivity().getIntent().getStringExtra("sbsbdm"));
            }
        } else {
            local = 1;
            //获取数据
            GetData();
            local = 0;
        }
        saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getBhData()) {
            if (saleAttributeNameVo.getName().equals("装置基本信息")) {
                saleVo.addAll(saleAttributeNameVo.getSaleVo());
                break;
            }
        }
        if (Fragment_Type_Base.instance.saleVo.size() > 0) {
            for (SaleAttributeVo vo : Fragment_Type_Base.instance.saleVo) {
                if (vo.getValue().equals("模块名称")) {
                    bt_mkmc = true;
                }
                if (vo.getValue().equals("软件版本")) {
                    bt_rjbb = true;
                }
                if (vo.getValue().equals("校验码")) {
                    bt_jym = true;
                }
                if (vo.getValue().equals("生成时间")) {
                    bt_scsj = true;
                }
            }
        }
        initReceiver(isEdit);

        initOnFocusChangeListener(map_key, map_more, true, "装置基本信息");
    }

    /**
     * 初始化校验项
     */
    private void init() {
        Map<String, TextView> tmp_key = new HashMap<>();
        Map<String, TextView> tmp_value = new HashMap<>();
        for (int i = 0; i < textViews_check.size(); i++) {
            tmp_key.put(textViews_check.get(i).getText().toString(), textViews_check.get(i));
            tmp_value.put(textViews_check.get(i).getText().toString(), textViews_check_bt.get(i));
        }
        tmp_key.put("设备类型：", textViews_check.get(textViews_check.size() - 3));
        tmp_value.put("设备类型：", textViews_check_bt.get(textViews_check_bt.size() - 3));
        tmp_key.put("设备功能配置：", textViews_check.get(textViews_check.size() - 2));
        tmp_value.put("设备功能配置：", textViews_check_bt.get(textViews_check_bt.size() - 2));
        tmp_key.put("测距形式：", textViews_check.get(textViews_check.size() - 2));
        tmp_value.put("测距形式：", textViews_check_bt.get(textViews_check_bt.size() - 2));
        tmp_key.put("故障录波器类型：", textViews_check.get(textViews_check.size() - 2));
        tmp_value.put("故障录波器类型：", textViews_check_bt.get(textViews_check_bt.size() - 2));

        List<SaleAttributeVo> saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getBhData()) {
            if (saleAttributeNameVo.getName().equals("装置基本信息")) {
                saleVo = saleAttributeNameVo.getSaleVo();
                break;
            }
        }

        map_key.clear();
        map_more.clear();

        for (SaleAttributeVo saleAttributeVo : saleVo) {
            if (listOne.getVisibility() == View.VISIBLE) {
                if (saleAttributeVo.getValue().equals("模块名称") || saleAttributeVo.getValue().equals("软件版本") || saleAttributeVo.getValue().equals("校验码")
                        || saleAttributeVo.getValue().equals("生成时间")) {
                    map_key.put(saleAttributeVo.getValue() + "-分模块", null);
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
                }
            }
        }
    }

    private void GetData() {
        Log.e("times", System.currentTimeMillis() - mLastTime + "initData_GetData");
        mLastTime = System.currentTimeMillis();
        //六统一标准版本、装置类别、出厂日期、装置类别细化、设备功能配置，生成日期
        //装置名称、制造厂家、装置型号、软件版本、校验码、板卡数量


        BHPZ bhsb = DemoActivity.instance.bhsb;

        //取值设备识别代码
        if (bhsb.getSfsbm() != null && !DemoActivity.instance.Similar) {
            editTexts.get(6).setText(bhsb.getSfsbm() + "");
        }
        if (bhsb.getBhmc() != null) {
            editTexts.get(0).setText(bhsb.getBhmc());
        }
        if (bhsb.getSfltysb() != null) {
            lty = bhsb.getSfltysb().equals("是");
        }
        if (lty) {
            cbTrue.setChecked(true);
            rlTwo.setVisibility(View.VISIBLE);
            if (bhsb.getLtybzbb() != null) {
                textViews.get(0).setText(bhsb.getLtybzbb());
                is2013 = bhsb.getLtybzbb().equals("2013版");
                if (is2013) {
                    addListener.onChangeListener("ICD文件信息", "ADD");
                }
            }
        } else {
            cbTrue.setChecked(false);
            rlTwo.setVisibility(View.GONE);
        }
        if (bhsb.getZzcj() != null) {
            editTexts.get(1).setText(bhsb.getZzcj());
        }
//        装置类别
        if (bhsb.getBhlb() != null) {

            textViews.get(1).setText(bhsb.getBhlb() + "");
            rlEight.setVisibility(View.GONE);
            llSfjrddzz.setVisibility(View.GONE);
            switch (bhsb.getBhlb() + "") {
                case "故障录波器":
                    rlEight.setVisibility(View.VISIBLE);
                    textViewleft.get(1).setText("故障录波器类型：");
                    if (bhsb.getGzlbqlx() != null) {
                        textViews.get(4).setText(bhsb.getGzlbqlx() + "");
                    }
                    break;
                case "故障测距装置":
                    rlEight.setVisibility(View.VISIBLE);
                    textViewleft.get(1).setText("测距形式：");
                    if (bhsb.getCjxx() != null) {
                        textViews.get(4).setText(bhsb.getCjxx() + "");
                    }
                    break;
                case "保护故障信息系统子站":
                    rlEight.setVisibility(View.GONE);
                    llSfjrddzz.setVisibility(View.VISIBLE);
                    if (bhsb != null && bhsb.getSfjrzz() != null) {
                        if (bhsb.getSfjrzz().toString().equals("是")) {
                            cbDdzzTrue.setChecked(true);
                        } else {
                            cbDdzzTrue.setChecked(false);
                        }
                    }
                    break;
                case "安全自动装置":
                    textViewleft.get(0).setText("设备类型");
                    break;
                case "线路保护":
                    addListener.onChangeListener("通道信息", "ADD");
                    isTd = true;
                    break;
                case "过电压及远方跳闸保护":
                    addListener.onChangeListener("通道信息", "ADD");
                    isTd = true;
                    break;
            }
        }


        if (bhsb.getBhlbxh() != null) {
            //保护类别细化，安全自动装置时，名称改变为设备类型。设备类型为安全稳定控制装置时带出安控页
            //保护类别细化，设备类型为频率电压紧急控制装置时，带出设备功能配置
            textViews.get(3).setText(bhsb.getBhlbxh() + "");
            if (bhsb.getBhlb().equals("安全自动装置")) {
                switch (bhsb.getBhlbxh() + "") {
                    case "安全稳定控制装置":
                        addListener.onChangeListener("安控信息", "ADD");
                        isAK = true;
                        break;
                    case "频率电压紧急控制装置":
                        rlEight.setVisibility(View.VISIBLE);
                        if (bhsb.getSbgnpz() != null) {
                            textViews.get(4).setText(bhsb.getSbgnpz() + "");
                        }
                        break;
                }
            }
        }

        if (bhsb.getBhxh() != null) {
            editTexts.get(2).setText(bhsb.getBhxh());
        }
        if (bhsb.getBhfl() != null) {
            flMap.put(editTexts.get(2).getText().toString() + "", bhsb.getBhfl() + "");
            textViews.get(6).setText(bhsb.getBhfl() + "");
        }
        if (bhsb.getBhlx() != null) {
            lxMap.put(editTexts.get(2).getText().toString() + "", bhsb.getBhlx() + "");
            textViews.get(7).setText(bhsb.getBhlx() + "");
        }
        if (bhsb.getCcrq() != null) {
            textViews.get(2).setText(TimeUtil.formatString2(bhsb.getCcrq() + "") + "");
        }
        if (is2013) {
            ltysbxh.setBhxh(editTexts.get(2).getText() + "");
        }

        initFirstData(bhsb);

        //板卡数量
        String bksl = bhsb.getBksl() + "";
        if (!bksl.equals("null") && !bksl.equals("0")) {
            editTexts.get(5).setText(bhsb.getBksl() + "");
        } else {
            editTexts.get(5).setText("");
        }
        editTexts.get(5).setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        editTexts.get(5).setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});

        if (bhsb.getSfjdhzz() != null) {
            if (bhsb.getSfjdhzz().equals("是")) {
                addListener.onChangeListener("连接器信息", "ADD");
                sfjdhsb = true;
                cbEndTrue.setChecked(true);
            }
        }

        List<Object> list;
        if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C") && !DemoActivity.instance.Similar) {
            tvBottom.setVisibility(View.GONE);
            list = (List<Object>) getActivity().getIntent().getSerializableExtra("BHXHRJBB");
        } else {
            tvBottom.setVisibility(View.VISIBLE);
            if (DemoActivity.instance.rzgl != null) {
                tvBottom.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
            } else {
                tvBottom.setText("本条台账最后一次修改时间：");
            }
            list = new DaoUtil(getActivity()).getBHRJBBByCode(lty, is2013, bhsb.getId() + "");
        }
        change_jym(is2013);

        if (list != null && list.size() > 0) {
            if (lty && is2013) {
                ltysbxh = (LTYSBXH) list.get(0);
                if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C") && !DemoActivity.instance.Similar) {
                    List<Object> old = (List<Object>) getActivity().getIntent().getSerializableExtra("BHXHRJBB");
                    if (old != null && old.size() > 0) {
//                        ltysbxh_old = (LTYSBXH) old.get(0);
                        ltysbxh_old.setZzcj(((LTYSBXH) old.get(0)).getZzcj());
                        ltysbxh_old.setBhlb(((LTYSBXH) old.get(0)).getBhlb());
                        ltysbxh_old.setBhxh(((LTYSBXH) old.get(0)).getBhxh());
                        ltysbxh_old.setRjbb(((LTYSBXH) old.get(0)).getRjbb());
                        ltysbxh_old.setXp(((LTYSBXH) old.get(0)).getXp());
                        ltysbxh_old.setWjmc(((LTYSBXH) old.get(0)).getWjmc());
                        ltysbxh_old.setWjbb(((LTYSBXH) old.get(0)).getWjbb());
                        ltysbxh_old.setCrc32(((LTYSBXH) old.get(0)).getCrc32());
                        ltysbxh_old.setMd5(((LTYSBXH) old.get(0)).getMd5());
                        ltysbxh_old.setJymscsj(((LTYSBXH) old.get(0)).getJymscsj());
                    }
                } else {
                    List<Object> olds = new DaoUtil(getActivity()).getBHRJBBByCode(lty, is2013, bhsb.getId() + "");
                    if (olds != null && olds.size() > 0) {
                        ltysbxh_old = (LTYSBXH) olds.get(0);
                    }
                }
                llRjbb.setVisibility(View.VISIBLE);
                listOne.setVisibility(View.GONE);

                change_jym(true);

                if (ltysbxh.getRjbb() != null) {
                    editTexts.get(3).setText(ltysbxh.getRjbb() + "");
                }
                if (!editTexts.get(2).getText().toString().equals("")) {
                    ltysbxh.setBhxh(editTexts.get(2).getText() + "");
                }
                codeMap.put(editTexts.get(2).getText() + "", ltysbxh.getCode() + "");
                bblxMap.put(editTexts.get(2).getText().toString(), ltysbxh.getBblx() + "");
            } else {
                for (Object o : list) {
                    rjbbList.add((BHXHRJBB) o);
                }
                if (rjbbList.size() == 1 && rjbbList.get(0) != null && rjbbList.get(0).getBblx() == null) {
                    rjbbList.get(0).setBblx("非六统一，不分模块");
                    bblxMap.put(editTexts.get(2).getText().toString(), "非六统一，不分模块");
                }
                if (rjbbList.size() > 1 || rjbbList.size() == 1 && rjbbList.get(0) != null
                        && rjbbList.get(0).getBblx() != null && rjbbList.get(0).getBblx().equals("非六统一，分模块")) {
                    llRjbb.setVisibility(View.GONE);
//                    for (BHXHRJBB bhxhrjbb : rjbbList) {
//                        list_one_data.add(bhxhrjbb);
//                    }
                    //默认不能超过五个,
                    if (rjbbList.size() > 5) {
                        //默认显示五个，多了跳转
                        rl_more_mk.setVisibility(View.VISIBLE);
//                        addListener.onChangeListener("模块信息","ADD");
                        DemoActivity.instance.showMk(2);
                        for (int i = 0; i < 5; i++) {
                            list_one_data.add(rjbbList.get(i));
                        }
                    } else {
                        for (int i = 0; i < rjbbList.size(); i++) {
                            list_one_data.add(rjbbList.get(i));
                        }
                    }
                    adapter.setDatas(list_one_data, isEdit, false);
                    if (rjbbList.get(0) != null) {
                        codeMap.put(editTexts.get(2).getText() + "", rjbbList.get(0).getBhxhcode() + "");
                        bblxMap.put(editTexts.get(2).getText().toString(), "非六统一，分模块");
                    }
                    adapter = new TypeBaseAdapter(getActivity());
                    listOne.setAdapter(adapter);

                } else if (rjbbList.size() == 1 && rjbbList.get(0) != null && rjbbList.get(0).getBblx() != null
                        && rjbbList.get(0).getBblx() != null && rjbbList.get(0).getBblx().equals("非六统一，不分模块")) {
                    llRjbb.setVisibility(View.VISIBLE);
                    listOne.setVisibility(View.GONE);
                    if (rjbbList.get(0).getBb() != null) {
                        editTexts.get(3).setText(rjbbList.get(0).getBb() + "");
                    } else {
                        editTexts.get(3).setText("");
                    }
                    if (rjbbList.get(0).getJym() != null) {
                        editTexts.get(4).setText(rjbbList.get(0).getJym() + "");
                    } else {
                        editTexts.get(4).setText("");
                    }
                    if (rjbbList.get(0).getSCSJ() != null) {
                        textViews.get(5).setText(TimeUtil.formatString2(rjbbList.get(0).getSCSJ() + "") + "");
                    }
                    codeMap.put(editTexts.get(2).getText() + "", rjbbList.get(0).getBhxhcode() + "");
                    bblxMap.put(editTexts.get(2).getText().toString(), "非六统一，不分模块");
                } else {
                    //默认初始化

                }
            }
        } else {

            String xh = editTexts.get(2).getText().toString() + "";
            String bblx = bblxMap.get(xh) + "";
            if (bblx.equals("非六统一，分模块")) {
                setRjbb(xh, false, true);
            }
            if (bhsb.getBhlx() != null && !bhsb.getBhlx().equals("微机型")) {
                //软件版本隐藏
                llRjbb.setVisibility(View.GONE);
                listOne.setVisibility(View.GONE);
                llHassffmk.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public void initReceiver(boolean isEdit) {
        check = false;
        if (isEdit) {
            adapter.setDatas(list_one_data, isEdit, false);
            if (rl_more_mk.getVisibility() == View.VISIBLE) {
//                updataMoreMk();
            }
            for (EditText editText : editTexts) {
                editText.setBackgroundResource(R.drawable.device_detials_bg);
                editText.setEnabled(true);
            }

            for (TextView textView : textViews) {
                textView.setBackgroundResource(R.drawable.device_detials_bg);
                textView.setEnabled(true);
            }
            for (Button button : buttons) {
                button.setVisibility(View.VISIBLE);
            }
            //分类类型隐藏选择
            buttons.get(buttons.size() - 2).setVisibility(View.GONE);
            buttons.get(buttons.size() - 3).setVisibility(View.GONE);
            cbTrue.setEnabled(true);
            cbEndTrue.setEnabled(true);
            cbXhSffmk.setEnabled(true);

            isSave = false;

//            for (TextView textView : textViews_check) {
//                setDrawableLeft(textView, false);
//            }

        } else {
            adapter.setDatas(list_one_data, isEdit, false);
            if (rl_more_mk.getVisibility() == View.VISIBLE) {
//                updataMoreMk();
            }
            for (EditText editText : editTexts) {
                editText.setBackground(null);
                editText.setEnabled(false);
            }
            for (TextView textView : textViews) {
                textView.setBackground(null);
                textView.setEnabled(false);
            }
            for (Button button : buttons) {
                button.setVisibility(View.GONE);
            }
            cbTrue.setEnabled(false);
            cbEndTrue.setEnabled(false);
            cbXhSffmk.setEnabled(false);

//            for (TextView textView : textViews_check) {
//                setDrawableLeft(textView, false);
//            }

            if (DemoActivity.instance.rzgl != null &&
                    DemoActivity.instance.rzgl.getCZSJ() != null &&
                    !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                tvBottom.setVisibility(View.VISIBLE);
                tvBottom.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
            }
        }
//        adapter.notifyDataSetChanged();
        /**
         * 必填项校验设计
         */
        checkBtx(isEdit, true, isShow, map_key, map_more, "装置基本信息");
    }

    private boolean isWJX() {
        boolean iswjx = false;
        if ((textViews.get(7).getText().toString() + "").equals("微机型") && list_one_data.size() <= 0 ||
                (textViews.get(7).getText().toString() + "").equals("") && list_one_data.size() <= 0) {
            iswjx = true;
        }
        return iswjx;
    }

    public void setDrawableLeft(TextView TV, boolean ischeck) {
        Drawable drawable = getResources().getDrawable(R.drawable.tanhao);
//        drawable.setBounds(10,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        if (ischeck) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        } else {
            drawable.setBounds(0, 0, 0, 0);
        }
        TV.setCompoundDrawables(drawable, null, null, null);
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    private void changeXh() {
        if (local == 1) {
            return;
        }
        //判断型号是否需要新增
        List<Object> xhList = util.getBHXH(lty, is2013, editTexts.get(1).getText() + "", textViews.get(1).getText() + "", DemoActivity.instance.bhsb.getBdzlx() + ""
                , DemoActivity.instance.bhsb.getDydj() + "", "", editTexts.get(2).getText().toString() + "");
        if (xhList != null && xhList.size() > 0) {
            if (lty && is2013) {
                if (xhList.get(0) != null) {
                    LTYSBXH ltysbxh = (LTYSBXH) xhList.get(0);
                    codeMap.put(ltysbxh.getBhxh(), ltysbxh.getCode());
                    bblxMap.put(ltysbxh.getBhxh(), ltysbxh.getBblx());
                    setRjbb(ltysbxh.getBhxh() + "", false, true);
                    textViews.get(6).setText(ltysbxh.getBhfl() + "");
                    textViews.get(7).setText(ltysbxh.getBhlx() + "");
                    buttons.get(buttons.size() - 2).setVisibility(View.GONE);
                    buttons.get(buttons.size() - 3).setVisibility(View.GONE);
                } else {
                    cbXhSffmk.setChecked(false);
                    if (list_one_data.size() > 0) {
                        cbXhSffmk.setChecked(true);
                    }
                    setRjbb(editTexts.get(2).getText().toString(), true, true);
                    llFlLx.setVisibility(View.VISIBLE);
                    codeMap.clear();
                    //分类类型选择
                    buttons.get(buttons.size() - 2).setVisibility(View.VISIBLE);
                    buttons.get(buttons.size() - 3).setVisibility(View.VISIBLE);
//                    textViews.get(6).setText("");
//                    textViews.get(7).setText("");
                }
            } else {
                if (xhList.get(0) != null) {
                    BHSBXHB bhsbxhb = (BHSBXHB) xhList.get(0);
                    codeMap.put(bhsbxhb.getSbxh(), bhsbxhb.getCode());
                    bblxMap.put(bhsbxhb.getSbxh(), bhsbxhb.getBblx());
                    textViews.get(6).setText(bhsbxhb.getBhfl() + "");
                    textViews.get(7).setText(bhsbxhb.getBhlx() + "");
                    setRjbb(editTexts.get(2).getText().toString() + "", false, true);
                    buttons.get(buttons.size() - 2).setVisibility(View.GONE);
                    buttons.get(buttons.size() - 3).setVisibility(View.GONE);
                } else {
                    cbXhSffmk.setChecked(false);
                    if (list_one_data.size() > 0) {
                        cbXhSffmk.setChecked(true);
                    }
                    setRjbb(editTexts.get(2).getText().toString(), true, true);
                    llFlLx.setVisibility(View.VISIBLE);
                    codeMap.clear();
                    //分类类型选择
                    buttons.get(buttons.size() - 2).setVisibility(View.VISIBLE);
                    buttons.get(buttons.size() - 3).setVisibility(View.VISIBLE);
//                    textViews.get(6).setText("");
//                    textViews.get(7).setText("");
                }
            }
        } else {
            cbXhSffmk.setChecked(false);
            if (list_one_data.size() > 0) {
                cbXhSffmk.setChecked(true);
            }
            setRjbb(editTexts.get(2).getText().toString(), true, true);
            llFlLx.setVisibility(View.VISIBLE);
            codeMap.clear();
            //分类类型选择
            buttons.get(buttons.size() - 2).setVisibility(View.VISIBLE);
            buttons.get(buttons.size() - 3).setVisibility(View.VISIBLE);
//            textViews.get(6).setText("");
//            textViews.get(7).setText("");
        }
        if (listOne.getVisibility() == View.GONE) {
            rl_more_mk.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 99) {
            //实物码返回取值
            Bundle bunde = data.getExtras();
            String value = bunde.getString("result") + "";
            if (value != null && !value.equals("")) {
                editTexts.get(6).setText(value + "");
            }
            return;
        }
        switch (resultCode) {
            case RESULT_OK: /* 取得数据，并显示于画面上 */
                Bundle bunde = data.getExtras();
                String value = bunde.getString("result") + "";
                String puttype = bunde.getString("puttype") + "";
                int putposition = Integer.parseInt(bunde.getString("putposition") + "");
                if (DemoActivity.instance.ismoreMk) {
                    return;
                }
                switch (puttype) {
                    case "六统一标准版本":
                        textViews.get(0).setText(value + "");
                        if (is2013) {
                            if (value.equals("2013版")) {
                                //带出保护分类、类型
//                                textViews.get(6).setText(flMap.get(value) + "");
                                textViews.get(7).setText("微机型");
                            } else {
                                //联动型号清空
                                editTexts.get(2).setText("");
                                is2013 = false;
                                addListener.onChangeListener("ICD文件信息", "DEL");
                                change_jym(false);
                            }
                        } else {
                            if (value.equals("2013版")) {
                                //带出保护分类、类型
//                                textViews.get(6).setText(flMap.get(value) + "");
                                textViews.get(7).setText("微机型");
                                is2013 = true;
                                //联动型号清空
                                editTexts.get(2).setText("");
                                addListener.onChangeListener("ICD文件信息", "ADD");
                                change_jym(true);
                            } else {
                            }
                        }
                        break;
                    case "制造厂家":
                        if (!value.equals(editTexts.get(1).getText().toString())) {
                            editTexts.get(1).setText(value + "");
                            //联动型号清空
                            editTexts.get(2).setText("");
                        }
                        break;
                    case "装置类别":
                        if (!value.equals(textViews.get(1).getText().toString())) {
                            //联动型号清空
                            editTexts.get(2).setText("");
                            //装置类别细化清空
                            textViews.get(3).setText("");
                            if (value.equals("站域保护")) {
                                textViews.get(3).setHint("");
                            } else {
                                textViews.get(3).setHint("必填项，请选择");
                            }
                            //设备功能配置清空
                            if (textViews.get(4).getVisibility() == View.VISIBLE) {
                                textViews.get(4).setText("");
                            }
                            //默认设备功能配置不显示
                            rlEight.setVisibility(View.GONE);
                            llSfjrddzz.setVisibility(View.GONE);

                            textViews.get(1).setText(value + "");
                            textViewleft.get(0).setText("装置类别细化：");
                            //判断通道信息是否显示
                            if (isTd && !value.equals("线路保护") || isTd && !value.equals("过电压及远方跳闸保护")) {
                                addListener.onChangeListener("通道信息", "DEL");
                                isTd = false;
                            }
                            //判断安控信息是否显示
                            if (isAK && !value.equals("安全自动装置")) {
                                addListener.onChangeListener("安控信息", "DEL");
                                isAK = false;
                            }
                            //默认初始不给红框
                            textViews.get(4).setBackgroundResource(R.drawable.device_detials_bg);
                            switch (value) {
                                case "故障录波器":
                                    rlEight.setVisibility(View.VISIBLE);
                                    textViewleft.get(1).setText("故障录波器类型：");
                                    for (SaleAttributeVo vo : saleVo) {
                                        if (vo.getValue().equals("设备功能配置") && textViews.get(3).getText().toString().equals("频率电压紧急控制装置") && (textViews.get(4).getText().toString() + "").equals("") ||
                                                vo.getValue().equals("故障录波器类型") && textViews.get(1).getText().toString().equals("故障录波器") && (textViews.get(4).getText().toString() + "").equals("") ||
                                                vo.getValue().equals("测距形式") && textViews.get(1).getText().toString().equals("故障测距装置") && (textViews.get(4).getText().toString() + "").equals("")) {
                                            textViews.get(4).setBackgroundResource(R.drawable.device_detials_bg2);
//                                            check= true;
                                        }
                                    }
                                    break;
                                case "故障测距装置":
                                    rlEight.setVisibility(View.VISIBLE);
                                    textViewleft.get(1).setText("测距形式：");
                                    for (SaleAttributeVo vo : saleVo) {
                                        if (vo.getValue().equals("设备功能配置") && textViews.get(3).getText().toString().equals("频率电压紧急控制装置") && (textViews.get(4).getText().toString() + "").equals("") ||
                                                vo.getValue().equals("故障录波器类型") && textViews.get(1).getText().toString().equals("故障录波器") && (textViews.get(4).getText().toString() + "").equals("") ||
                                                vo.getValue().equals("测距形式") && textViews.get(1).getText().toString().equals("故障测距装置") && (textViews.get(4).getText().toString() + "").equals("")) {
                                            textViews.get(4).setBackgroundResource(R.drawable.device_detials_bg2);
//                                            check= true;
                                        }
                                    }
                                    break;
                                case "保护故障信息系统子站":
                                    rlEight.setVisibility(View.GONE);
                                    llSfjrddzz.setVisibility(View.VISIBLE);
                                    //                cbDdzzTrue
                                    break;
                                case "安全自动装置":
                                    textViewleft.get(0).setText("设备类型");
                                    break;
                                case "线路保护":
                                    if (isTd) {

                                    } else {
                                        addListener.onChangeListener("通道信息", "ADD");
                                        isTd = true;
                                    }
                                    break;
                                case "过电压及远方跳闸保护":
                                    if (isTd) {

                                    } else {
                                        addListener.onChangeListener("通道信息", "ADD");
                                        isTd = true;
                                    }
                                    break;
                            }
                            //同步更新一次信息
                            Fragment_Type_Inset.instance.changeyc(value);
                        }

                        break;
                    case "装置型号":
                        if (value.equals("")) {
                            editTexts.get(2).setText("");
                        } else if (!value.equals(editTexts.get(2).getText().toString())) {
                            editTexts.get(2).setText(value + "");
                            //带出保护分类、类型
                            textViews.get(6).setText(flMap.get(value) + "");
                            textViews.get(7).setText(lxMap.get(value) + "");
                            buttons.get(buttons.size() - 2).setVisibility(View.GONE);
                            buttons.get(buttons.size() - 3).setVisibility(View.GONE);

                            if (is2013) {
                                listOne.setVisibility(View.GONE);
                                llRjbb.setVisibility(View.VISIBLE);
                                //    影响软件版本，校验码，生成时间
                                setRjbb(value, false, true);

                            } else if (bblxMap.get(value).equals("非六统一，不分模块")) {
                                listOne.setVisibility(View.GONE);
                                llRjbb.setVisibility(View.VISIBLE);

                                //    影响软件版本，校验码，生成日期
                                setRjbb(value, false, true);
                                checkBtx(true, true, false, map_key, map_more, "装置基本信息");
                            } else if (bblxMap.get(value).equals("非六统一，分模块")) {
                                //影响软件版本，校验码，生成日期
                                setRjbb(value, false, true);
                            } else {
                                setRjbb(value, false, true);
                            }
                        }

                        break;
                    case "模块名称":
                        if (list_one_data.get(adapter.getPosition()).getMkmc() != null &&
                                !list_one_data.get(adapter.getPosition()).getMkmc().equals(value)) {
                            list_one_data.get(adapter.getPosition()).setBb("");
                            list_one_data.get(adapter.getPosition()).setJym("");
                        }
                        list_one_data.get(adapter.getPosition()).setMkmc(value);
                        adapter.setDatas(list_one_data, true, false);
                        break;
                    case "软件版本":
                        if (putposition > -1) {
                            if (!value.equals(list_one_data.get(adapter.getPosition()).getBb())) {
                                list_one_data.get(adapter.getPosition()).setJym("");
                            }
                            list_one_data.get(adapter.getPosition()).setBb(value);
                            adapter.setDatas(list_one_data, true, false);
                        } else {
                            if (!value.equals(editTexts.get(3).getText() + "")) {
                                //  清空校验码和生成日期
//                                editTexts.get(4).setText("");
//                                textViews.get(5).setText("");
                                editTexts.get(3).setText(value + "");
                                if (is2013) {
//                                    textViews.get(5).setText(dateMap.get(value) + "");
//                                    ltysbxh.setRjbb(value+"");
                                    if (lty && is2013) {
                                        //  软件版本和校验码和生成日期
                                        String lty_rjbb = value;
                                        ltysbxh.setRjbb(lty_rjbb + "");
                                        if (Details8.instance != null) {
//                                            Details8.instance.setDataByWjmc(null, true);
                                        }
                                        if (ltysbxh_old.getRjbb() != null && !ltysbxh_old.getRjbb().equals(lty_rjbb)) {
                                            if (Details8.instance != null) {
                                                Details8.instance.setDataByXh(true);
                                            }
                                        } else {
                                            if (Details8.instance != null) {
                                                Details8.instance.setDataByXh(false);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "校验码":
                        if (putposition > -1) {
//                            list_one_data.get(putposition).setContent_three(value + "");
//                            adapter.notifyDataSetChanged();
                            list_one_data.get(adapter.getPosition()).setJym(value);
                            adapter.setDatas(list_one_data, true, false);
                        } else {
                            editTexts.get(4).setText(value + "");
                        }
                        if (is2013) {
                            ltysbxh.setRjbb(editTexts.get(3).getText() + " " + textViews.get(5).getText() + " " + value);
                            if (lty && is2013) {
                                //  软件版本和校验码和生成日期
                                String lty_rjbb = editTexts.get(3).getText().toString() + " " + textViews.get(5).getText().toString() + " " + editTexts.get(4).getText().toString();
                                ltysbxh.setRjbb(lty_rjbb + "");
                                if (Details8.instance != null) {
                                    Details8.instance.setDataByWjmc(null, true);
                                }
                            }
                        }
                        break;
                    case "装置类别细化":
                        textViews.get(3).setText(value + "");
                        break;
                    case "设备类型":
                        textViews.get(3).setText(value + "");
                        if (isAK && !value.equals("安全稳定控制装置")) {
                            addListener.onChangeListener("安控信息", "DEL");
                            isAK = false;
                        }
                        rlEight.setVisibility(View.GONE);
                        textViews.get(4).setText("");
                        switch (value) {
                            case "安全稳定控制装置":
                                if (isAK) {
                                } else {
                                    addListener.onChangeListener("安控信息", "ADD");
                                    isAK = true;
                                }
                                break;
                            case "频率电压紧急控制装置":
                                rlEight.setVisibility(View.VISIBLE);
                                textViewleft.get(1).setText("设备功能配置：");
                                break;
                        }
                        break;
                    case "故障录波器类型":
                        textViews.get(4).setText(value + "");
                        break;
                    case "测距形式":
                        textViews.get(4).setText(value + "");
                        break;
                    case "设备功能配置":
                        textViews.get(4).setText(value + "");
                        break;
                    case "装置分类":
                        textViews.get(6).setText(value + "");
                        break;
                    case "装置类型":
                        String lastvalue = textViews.get(7).getText().toString();
                        textViews.get(7).setText(value + "");
                        setRjbb(value + "", true, true);
                        if (lastvalue.equals("微机型") && !value.equals("微机型")) {
                            checkBtx(true, false, false, map_key, map_more, "装置基本信息");
                        } else if (!lastvalue.equals("微机型") && value.equals("微机型")) {
                            checkBtx(true, false, false, map_key, map_more, "装置基本信息");
                        }
                        break;
                }
                break;

            default:
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cb_true, R.id.fragment_add_bt_two, R.id.fragment_add_bt_three,
            R.id.fragment_add_bt_four, R.id.fragment_add_bt_five, R.id.fragment_add_bt_rjbb,
            R.id.fragment_add_bt_jym, R.id.fragment_add_bt_scrq, R.id.fragment_add_bt_six,
            R.id.fragment_add_bt_seven, R.id.fragment_add_bt_eight, R.id.cb_end_true,
            R.id.cb_xh_sffmk, R.id.fragment_add_bt_fl, R.id.fragment_add_bt_lx,
            R.id.fragment_add_bt_one, R.id.fragment_add_bt_swm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_add_bt_one:
                //生成装置名称
                made_protect_name(0);
                break;
            case R.id.fragment_add_bt_swm:
                //获取实物码
                Intent startScan = new Intent(getActivity(), CaptureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("request", "swm");
                startScan.putExtras(bundle);
                startActivityForResult(startScan, 99);
                break;
            case R.id.cb_true:
                //是否六统一，显示或者隐藏ICD文件信息
                if (!lty) {
                    lty = true;
                    rlTwo.setVisibility(View.VISIBLE);
                    if (saleVo.size() > 0) {
                        for (SaleAttributeVo vo : saleVo) {
                            if (vo.getValue().equals("六统一标准版本") && lty && (textViews.get(0).getText().toString() + "").equals("")) {
                                textViews.get(0).setBackgroundResource(R.drawable.device_detials_bg2);
//                                check = true;
                            }
                        }
                    }
//                    initReceiver(true);
                } else {
                    lty = false;
                    textViews.get(0).setText("");
                    //联动型号清空
                    editTexts.get(2).setText("");
                    rlTwo.setVisibility(View.GONE);

                    if (is2013) {
                        is2013 = false;
                        addListener.onChangeListener("ICD文件信息", "DEL");
                        change_jym(false);
                    } else {
                    }
                }
                break;
            case R.id.fragment_add_bt_two:
                //六统一标准版本
                setIntentData("六统一标准版本", -1);
                break;
            case R.id.fragment_add_bt_three:
                //制造厂家
                setIntentData("制造厂家", -1);
                break;
            case R.id.fragment_add_bt_four:
                //装置类别
                setIntentData("装置类别", -1);
                break;
            case R.id.fragment_add_bt_five:
                //装置型号
                setIntentData("装置型号", -1);
                break;
            case R.id.fragment_add_bt_rjbb:
                //软件版本
                setIntentData("软件版本", -1);
                break;
            case R.id.fragment_add_bt_jym:
                //校验码
                setIntentData("校验码", -1);
                break;
            case R.id.fragment_add_bt_scrq:
                //生成日期
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        textViews.get(5).setText(date);
                        if (lty && is2013) {
                            //  软件版本和校验码和生成日期
                            String lty_rjbb = editTexts.get(3).getText().toString() + " " + date + " " + editTexts.get(4).getText().toString();
                            ltysbxh.setRjbb(lty_rjbb + "");
                            if (Details8.instance != null) {
                                Details8.instance.setDataByWjmc(null, true);
                            }
                        }
                    }
                });
                break;
            case R.id.fragment_add_bt_six:
                //出厂日期
//                AppUtils.showDateDialog(getActivity(), new DateChooseListener() {
//                    @Override
//                    public void onDateChooseListener(String date) {
//                        textViews.get(2).setText(date);
//                    }
//                });
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        textViews.get(2).setText(date);
                    }
                });
                break;
            case R.id.fragment_add_bt_seven:
                //装置类别细化
                if (textViews.get(1).getText().toString().equals("安全自动装置")) {
                    setIntentData("设备类型", -1);
                } else {
                    setIntentData("装置类别细化", -1);
                }
                break;
            case R.id.fragment_add_bt_eight:
                String nametype = textViewleft.get(1).getText() + "";
                if (textViewleft.get(1).getText().toString().equals("测距形式：")) {
                    nametype = "测距形式";
                } else if (textViewleft.get(1).getText().toString().equals("故障录波器类型：")) {
                    nametype = "故障录波器类型";
                } else if (textViewleft.get(1).getText().toString().equals("设备功能配置：")) {
                    nametype = "设备功能配置";
                }
                setIntentData(nametype, -1);
                break;
            case R.id.cb_end_true:
                //是否就地化，显示或者隐藏连接器信息
                if (!sfjdhsb) {
                    addListener.onChangeListener("连接器信息", "ADD");
                    sfjdhsb = true;
                } else {
                    addListener.onChangeListener("连接器信息", "DEL");
                    sfjdhsb = false;
                }
                break;
            case R.id.cb_xh_sffmk:
                //是否分模块
                setRjbb(editTexts.get(2).getText().toString(), true, true);
                if (!cbXhSffmk.isChecked()) {
                    checkBtx(true, true, false, map_key, map_more, "装置基本信息");
                }
                break;
            case R.id.fragment_add_bt_fl:
                //装置分类
                setIntentData("装置分类", -1);
                break;
            case R.id.fragment_add_bt_lx:
                //装置分类
                setIntentData("装置类型", -1);
                break;

        }

    }

    /**
     * @param type 类型
     */
    public void setIntentData(String type, int position) {
        local = position;
        Map<String, Object> map = new HashMap<>();
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("type", type);
        map.put("number", "1");
        boolean isSix = cbTrue.isChecked();
        boolean is2013 = textViews.get(0).getText().toString().equals("2013版");
        map.put("isSIX", isSix);
        map.put("is2013", is2013);
        map.put("position", position + "");
        if (position == -1) {

            if (type.equals("装置型号")) {
                map.put("zzcj", editTexts.get(1).getText().toString() + "");
                map.put("bhlb", textViews.get(1).getText().toString() + "");
                map.put("czlx", new DaoUtil(getActivity()).getCZCSByGLDW().getBdzlx());
                map.put("dydj", Fragment_Type_Inset.instance.getdydj() + "");
                map.put("usegddate", "");
            } else if (type.equals("软件版本")) {
                if (isSix && is2013) {
                    map.put("zzcj", getzzcj() + "");
                    map.put("bhlb", getBhlb() + "");
                    map.put("bhxh", editTexts.get(2).getText().toString() + "");

                    map.put("czlx", new DaoUtil(getActivity()).getCZCSByGLDW().getBdzlx());
                    map.put("dydj", Fragment_Type_Inset.instance.getdydj() + "");
                } else {
                    map.put("bhxh", codeMap.get(editTexts.get(2).getText().toString()));
                    map.put("bblx", bblxMap.get(editTexts.get(2).getText().toString()));
                    map.put("czlx", new DaoUtil(getActivity()).getCZCSByGLDW().getBdzlx());
                    map.put("dydj", Fragment_Type_Inset.instance.getdydj() + "");
                }
            } else if (type.equals("校验码")) {
                map.put("bb", editTexts.get(3).getText().toString());
                map.put("bhxh", codeMap.get(editTexts.get(2).getText().toString()));
            } else if (type.equals("装置类别细化") || type.equals("设备类型")) {
                map.put("bhlb", textViews.get(1).getText().toString() + "");
            }
        } else if (position > -1) {
            if (type.equals("模块名称")) {
                map.put("selectCode", codeMap.get(editTexts.get(2).getText().toString()));
            } else if (type.equals("软件版本")) {
                map.put("bhxh", codeMap.get(editTexts.get(2).getText().toString()));
                map.put("mkmc", list_one_data.get(position).getMkmc() + "");
                map.put("bblx", bblxMap.get(editTexts.get(2).getText().toString()));

                map.put("czlx", new DaoUtil(getActivity()).getCZCSByGLDW().getBdzlx());
                map.put("dydj", Fragment_Type_Inset.instance.getdydj() + "");
            } else if (type.equals("校验码")) {
                map.put("mkmc", list_one_data.get(position).getMkmc() + "");
                map.put("bb", list_one_data.get(position).getBb() + "");
                map.put("bhxh", codeMap.get(editTexts.get(2).getText().toString()));
            }
        }

        intent.putExtra("conditions", (Serializable) map);
        startActivityForResult(intent, 0);
    }

    /**
     * 更改装置型号时需重置这些信息
     *
     * @param value
     */
    private void setRjbb(String value, boolean isAdd, boolean isEdit) {
        if (!textViews.get(7).getText().toString().equals("微机型")) {
            //软件版本隐藏
            llRjbb.setVisibility(View.GONE);
            listOne.setVisibility(View.GONE);
            llHassffmk.setVisibility(View.GONE);
            rl_more_mk.setVisibility(View.GONE);
            return;
        }
        if (value.equals("微机型")) {
            value = editTexts.get(2).toString();
        }
        if (lty && is2013) {
            llHassffmk.setVisibility(View.GONE);
            llRjbb.setVisibility(View.VISIBLE);
            listOne.setVisibility(View.GONE);
            rl_more_mk.setVisibility(View.GONE);
//            list_one_data.clear();

            //    影响软件版本，校验码，生成日期
            if (llRjbb.getVisibility() == View.VISIBLE) {
//                editTexts.get(3).setText("");2013，都不清空
//                editTexts.get(4).setText("");
//                textViews.get(5).setText("");
            }
            ltysbxh.setBhxh(value + "");
            if (isAdd && Details8.instance != null) {
                Details8.instance.setDataByXh(true);
            } else if (!isAdd && Details8.instance != null) {
                Details8.instance.setDataByXh(false);
            }
        } else {
            String bblx = "";
            if (isAdd) {
                if (!value.equals("")) {
                    llHassffmk.setVisibility(View.VISIBLE);
                }
                bblx = cbXhSffmk.isChecked() ? "非六统一，分模块" : "非六统一，不分模块";
            } else {
                llHassffmk.setVisibility(View.GONE);
                bblx = bblxMap.get(value) + "";
            }
            if (bblx.equals("非六统一，分模块")) { //分模块
                llRjbb.setVisibility(View.GONE);
                listOne.setVisibility(View.VISIBLE);
//                rjbbList.clear();
                if (llHassffmk.getVisibility() == View.VISIBLE && !cbXhSffmk.isChecked()) {
                    rl_more_mk.setVisibility(View.GONE);
                }
                if (rjbbList.size() > 5) {
                    rl_more_mk.setVisibility(View.VISIBLE);
                }
                BHXHRJBB rjbb = new BHXHRJBB();
                rjbb.setBblx("非六统一，分模块");
                rjbb.setED_TAG("C");
                rjbb.setSfqy("Y");
                if (!isAdd) {
                    rjbb.setBhxhcode(codeMap.get(value));
                }
//                rjbbList.add(rjbb);

                if (list_one_data.size() > 0) {
                } else {
                    list_one_data.clear();
                    BHXHRJBB rjbbdef = new BHXHRJBB();
                    rjbbdef.setBblx("非六统一，分模块");
                    rjbbdef.setED_TAG("C");
                    rjbbdef.setSfqy("Y");
                    if (!isAdd) {
                        rjbbdef.setBhxhcode(codeMap.get(value));
                    }
                    list_one_data.add(rjbbdef);
                    adapter.setDatas(list_one_data, isEdit, false);
                }
//                if (!cbXhSffmk.isChecked()) {
//                    rl_more_mk.setVisibility(View.GONE);
//                }
            } else { //剩下的都是不分模块
                llRjbb.setVisibility(View.VISIBLE);
                listOne.setVisibility(View.GONE);
                rl_more_mk.setVisibility(View.GONE);
//                list_one_data.clear();

                //    影响软件版本，校验码，生成日期
                if (llRjbb.getVisibility() == View.VISIBLE) {
//                    editTexts.get(3).setText("");不清空
//                    editTexts.get(4).setText("");
//                    textViews.get(5).setText("");
                }
//                rjbbList.clear();
            }
        }

    }

    /**
     * 初始化从列表进入部分选项的值
     */
    private void initFirstData(BHPZ bhsb) {
        String bdzlx = bhsb.getBdzlx() + "";
        if (bdzlx == null || bdzlx.equals("null")) {
            bdzlx = DemoActivity.instance.czcs.getBdzlx();
        }
        List<Object> list = util.getBHXH(lty, is2013, bhsb.getZzcj() + "", bhsb.getBhlb() + "",
                bdzlx + "", bhsb.getDydj() + "", "是", bhsb.getBhxh() + "");
//        if (bhsb.getZzcj()!=null&&bhsb.getBhlb()!=null&&bhsb.getBhxh()!=null){
//        }
        if (list != null && list.size() > 0) {
            if (lty && is2013) {
                LTYSBXH ltysbxh = (LTYSBXH) list.get(0);
                codeMap.put(ltysbxh.getBhxh(), ltysbxh.getCode());
                bblxMap.put(ltysbxh.getBhxh(), ltysbxh.getBblx());
                flMap.put(ltysbxh.getBhxh(), ltysbxh.getBhfl());
                lxMap.put(ltysbxh.getBhxh(), ltysbxh.getBhlx());
            } else {
                BHSBXHB bhsbxhb = (BHSBXHB) list.get(0);
                codeMap.put(bhsbxhb.getSbxh(), bhsbxhb.getCode());
                bblxMap.put(bhsbxhb.getSbxh(), bhsbxhb.getBblx());
                flMap.put(bhsbxhb.getSbxh(), bhsbxhb.getBhfl());
                lxMap.put(bhsbxhb.getSbxh(), bhsbxhb.getBhlx());
            }
        }
    }

    //同步多模块信息，超过五个。
    public void updataMoreMk() {
        if (Fragment_Type_Base.instance.list_one_data.size() == 5 && Fragment_Type_More_Mk.instance.list_one_data.size() > 5) {
            for (int i = 0; i < 5; i++) {
                if (i >= list_one_data.size() || i >= Fragment_Type_More_Mk.instance.list_one_data.size()) {
                    break;
                }
                if (!(Fragment_Type_More_Mk.instance.list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null") &&
                        !(list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null") &&
                        !Fragment_Type_More_Mk.instance.list_one_data.get(i).getMkmc().equals(list_one_data.get(i).getMkmc() + "") ||
                        !(Fragment_Type_More_Mk.instance.list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null") &&
                                (list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null")) {
                    list_one_data.add(i, Fragment_Type_More_Mk.instance.list_one_data.get(i));
                } else if (!(Fragment_Type_More_Mk.instance.list_one_data.get(i).getBb() + "").equalsIgnoreCase("null") &&
                        !(list_one_data.get(i).getBb() + "").equalsIgnoreCase("null") &&
                        !Fragment_Type_More_Mk.instance.list_one_data.get(i).getBb().equals(list_one_data.get(i).getBb() + "") ||
                        !(Fragment_Type_More_Mk.instance.list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null") &&
                                (list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null")) {
                    list_one_data.add(i, Fragment_Type_More_Mk.instance.list_one_data.get(i));
                } else if (!(Fragment_Type_More_Mk.instance.list_one_data.get(i).getJym() + "").equalsIgnoreCase("null") &&
                        !(list_one_data.get(i).getJym() + "").equalsIgnoreCase("null") &&
                        !Fragment_Type_More_Mk.instance.list_one_data.get(i).getJym().equals(list_one_data.get(i).getJym() + "") ||
                        !(Fragment_Type_More_Mk.instance.list_one_data.get(i).getJym() + "").equalsIgnoreCase("null") &&
                                (list_one_data.get(i).getJym() + "").equalsIgnoreCase("null")) {
                    list_one_data.add(i, Fragment_Type_More_Mk.instance.list_one_data.get(i));
                } else if (!(Fragment_Type_More_Mk.instance.list_one_data.get(i).getSCSJ() + "").equalsIgnoreCase("null") &&
                        !(list_one_data.get(i).getSCSJ() + "").equalsIgnoreCase("null") &&
                        !Fragment_Type_More_Mk.instance.list_one_data.get(i).getSCSJ().equals(list_one_data.get(i).getSCSJ() + "") ||
                        !(Fragment_Type_More_Mk.instance.list_one_data.get(i).getSCSJ() + "").equalsIgnoreCase("null") &&
                                (list_one_data.get(i).getSCSJ() + "").equalsIgnoreCase("null")) {
                    list_one_data.add(i, Fragment_Type_More_Mk.instance.list_one_data.get(i));
                }
            }
        } else if (list_one_data.size() == 5 && Fragment_Type_More_Mk.instance.list_one_data.size() <= 5) {
            list_one_data.clear();
            rl_more_mk.setVisibility(View.GONE);
            for (BHXHRJBB list_one_da : Fragment_Type_More_Mk.instance.list_one_data) {
                list_one_data.add(list_one_da);
            }
        }
        adapter.notifyDataSetChanged();
    }


    public boolean insetBase() {
        boolean insetbase = false;
        //装置名称
//        DemoActivity.instance.bhsb.setBhmc(editTexts.get(0).getText()+"");
        protect_name = editTexts.get(0).getText() + "";
        //是否六统一
        if (lty) {
            ltybzbb = textViews.get(0).getText() + "";
        } else {
        }

        protect_type = textViews.get(1).getText() + "";
        if (protect_type.equals("安全自动装置")) {
            device_type = textViews.get(3).getText() + "";
        } else {
            protect_type_details = textViews.get(3).getText() + "";
        }

        switch (protect_type) {
            case "故障录波器":
                machine_type = textViews.get(4).getText() + "";
                break;
            case "故障测距装置":
                test_type = textViews.get(4).getText() + "";
                break;
            case "保护故障信息系统子站":
                if (cbDdzzTrue.isChecked()) {
                    isask = "是";
                } else {
                    isask = "否";
                }
                break;
            case "安全自动装置":
                if (device_type.equals("频率电压紧急控制装置")) {
                    device_work_set = textViews.get(4).getText() + "";
                }
                break;
        }

        made_company = editTexts.get(1).getText() + "";
        protect_name = editTexts.get(0).getText() + "";
        protect_type_m = editTexts.get(2).getText() + "";

        if (llFlLx.getVisibility() == View.VISIBLE) {
            protect_type_fl = textViews.get(6).getText().toString() + "";
            protect_type_lx = textViews.get(7).getText().toString() + "";
        } else {
            protect_type_fl = flMap.get(protect_type_m) + "";
            protect_type_lx = lxMap.get(protect_type_m) + "";
        }

        if (llRjbb.getVisibility() == View.VISIBLE) {
            ver_info = editTexts.get(3).getText() + "";
            jym_info = editTexts.get(4).getText() + "";
            scrq_info = textViews.get(5).getText() + "";
        }
        ccrq_info = textViews.get(2).getText().toString() + "";

        String bksl = editTexts.get(5).getText().toString() + "";

        //设备识别代码校验
        String swm = editTexts.get(6).getText() + "";
        boolean issw = true;
        if (swm != null && !swm.equals("")) {
            if (util.isSWIDUsed(swm, DemoActivity.instance.bhsb.getId() + "")) {
                issw = false;
            } else {
            }
        }

        if (!issw) {
            ToastUtils.showToast(getActivity(), "该装置设备识别代码已被其他装置使用");
        } else if (protect_name.equals("")) {
            ToastUtils.showToast(getActivity(), "请输入或点击生成装置名称");
        } else if (lty && textViews.get(0).getText().equals("")) {
            ToastUtils.showToast(getActivity(), "请选择六统一标准版本号");
            //保护类别不为空
        } else if (protect_type.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择装置类别");
        } else if (protect_type.equals("安全自动装置") && device_type.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择设备类型");
        } else if (protect_type.equals("安全自动装置") && device_type.equals("频率电压紧急控制装置") && device_work_set.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择设备功能配置");
        } else if (!protect_type.equals("站域保护") && !protect_type.equals("安全自动装置") && protect_type_details.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择装置类别细化");
        } else if (protect_type.equals("故障录波器") && machine_type.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择故障录波器类型");
        } else if (protect_type.equals("故障测距装置") && test_type.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择测距形式");
        } else if (protect_type.equals("保护故障信息系统子站") && isask.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择是否接入调度主站");
        } else if (made_company.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择制造厂家");
        } else if (protect_type_m.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择或填写装置型号");
        } else if (protect_type_fl.equals("") || protect_type_fl.equals("null")) {
//            llFlLx.setVisibility(View.VISIBLE);
            ToastUtils.showToast(getActivity(), "请选择装置分类");
        } else if (protect_type_lx.equals("") || protect_type_lx.equals("null")) {
//            llFlLx.setVisibility(View.VISIBLE);
            ToastUtils.showToast(getActivity(), "请选择装置类型");
        } else if (ltybzbb.equals("2013版") && ver_info.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择或填写软件版本");
        } else if (!is2013 && llRjbb.getVisibility() == View.VISIBLE && ver_info.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择或填写软件版本");
        } else if (!is2013 && llRjbb.getVisibility() == View.VISIBLE && jym_info.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择或填写校验码");
        }
//        else if ((textViews.get(2).getText().toString() + "").equals("")) {
//            ToastUtils.showToast(getActivity(), "请选择出厂日期");
        else if (!bksl.equals("") && bksl.equals("0")) {
            ToastUtils.showToast(getActivity(), "板卡数量不能为0");
        } else if (protect_name.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择生成保护名称");
        } else if (protect_name_rep) {
            ToastUtils.showToast(getActivity(), "保护名称重复，请手动修改");
        } else if (rl_more_mk.getVisibility() == View.GONE && isaddOk() && isCheckOk()) {
//            InSetData();
            insetbase = true;
        } else if (rl_more_mk.getVisibility() == View.VISIBLE && Fragment_Type_More_Mk.instance.isaddOk() && isCheckOk()) {
            insetbase = true;
        }
        return insetbase;

    }

    //模块多选合法性判断
    public boolean isaddOk() {
        boolean isok = true;
        boolean isjym = false;
        boolean issj = false;
        if (saleVo.size() > 0) {
            for (SaleAttributeVo vo : saleVo) {
                if (vo.getValue().equals("校验码")) {
                    isjym = true;
                }
                if (vo.getValue().equals("生成时间")) {
                    issj = true;
                }
            }
        }
        if (list_one_data.size() > 0 && listOne.getVisibility() == View.VISIBLE) {
            if (Fragment_Type_More_Mk.instance != null && Fragment_Type_More_Mk.instance.list_one_data.size() > 5) {
                isok = Fragment_Type_More_Mk.instance.isaddOk();
                if (isok) {
                    updataMoreMk();
                }
            } else {
                for (BHXHRJBB item : list_one_data) {
                    //填写了模块名称，软件版本为必填
                    if ((item.getMkmc() + "").equalsIgnoreCase("null") || item.getMkmc().equals("")) {
                        ToastUtils.showToast(getActivity(), "请选择填写模块名称");
                        isok = false;
                        break;
                    } else if ((item.getBb() + "").equalsIgnoreCase("null") || item.getBb().equals("")) {
                        ToastUtils.showToast(getActivity(), "请选择填写软件版本");
                        isok = false;
                        break;
                    } else if (isjym && (item.getJym() + "").equalsIgnoreCase("null") || isjym && item.getJym().equals("")) {
                        ToastUtils.showToast(getActivity(), "请选择填写校验码");
                        isok = false;
                        break;
                    } else if (issj && (item.getSCSJ() + "").equalsIgnoreCase("null") || issj && item.getSCSJ().equals("")) {
                        ToastUtils.showToast(getActivity(), "请选择生成时间");
                        isok = false;
                        break;
                    }
                }
            }
        }
        return isok;
    }

    //组合必填项校验
    public boolean isCheckOk() {
        boolean isok = true;
        if (saleVo.size() > 0) {
            for (SaleAttributeVo vo : saleVo) {
                if (vo.getValue().equals("设备识别代码") && (editTexts.get(6).getText().toString() + "").equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择填写设备识别代码");
                    isok = false;
                    break;
                } else if (vo.getValue().equals("生成时间") && (textViews.get(5).getText().toString() + "").equals("") && !is2013 && isWJX()) {
                    ToastUtils.showToast(getActivity(), "请选择填写生成时间");
                    isok = false;
                    break;
                } else if (vo.getValue().equals("出厂日期") && (textViews.get(2).getText().toString() + "").equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择填写出厂日期");
                    isok = false;
                    break;
                } else if (vo.getValue().equals("板卡数量") && (editTexts.get(5).getText().toString()).equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择填写板卡数量");
                    isok = false;
                    break;
                }
            }
        }
        return isok;
    }

    public void addmk() {
        //是否模块名称，非必填项目
        //在插其他表关系之前删除
//        util.deleteBHPZXHBBGX(sb_bhpzid);
        if (list_one_data.size() > 0 && listOne.getVisibility() == View.VISIBLE) {
            ismodel_type = true;
            String rjbb = "";
            for (int i = 0; i < list_one_data.size(); i++) {
                //判断至少一个有值再存
                if (!list_one_data.get(i).getMkmc().equals("") ||
                        !list_one_data.get(i).getBb().equals("")) {
                    BHXHRJBB bhxhrjbb = new BHXHRJBB();
                    bhxhrjbb.setRjbbxx("模块名称：" + list_one_data.get(i).getMkmc() + "," +
                            "软件版本：" + list_one_data.get(i).getBb() + ","
                            + "校验码：" + list_one_data.get(i).getJym() + ","
                            + "生成时间：" + list_one_data.get(i).getSCSJ() + ""
                    );
                    rjbb += bhxhrjbb.getRjbbxx() + "";
                    if (i == list_one_data.size() - 1) {
//                        DemoActivity.instance.bhsb.setRjbb(bhxhrjbb.getRjbbxx()+"");
                        DemoActivity.instance.bhsb.setRjbb(rjbb + "");
                    }
                    bhxhrjbb.setBblx("非六统一，分模块");
                    bhxhrjbb.setMkmc(list_one_data.get(i).getMkmc() + "");
                    String bbinfo = list_one_data.get(i).getBb() + "";
                    String jyminfo = list_one_data.get(i).getJym() + "";
                    bhxhrjbb.setBb(bbinfo + "");
                    bhxhrjbb.setJym(jyminfo + "");

                    bhxhrjbb.setId(util.getInsertId("BHXHRJBB"));
                    bhxhrjbb.setBhxhcode(codeMap.get(protect_type_m) + "");
//                    bhxhrjbb.setCode(util.getInsertId("CODE") + "");
                    bhxhrjbb.setCode(util.getInsertId() + "");
                    bhxhrjbb.setState("C");
                    bhxhrjbb.setED_TAG("C");
                    //先存关系表
//                      util.coreSave(bhxhrjbb);

                    String code = util.getCodeByBhxhRjbb(bhxhrjbb);
                    if (code != null) {
                        BHPZXHBBGX bbgx = new BHPZXHBBGX();
                        bbgx.setID(util.getInsertId("BHPZXHBBGX"));
                        bbgx.setBHPZID(DemoActivity.instance.bhsb.getId());
                        bbgx.setRJBBCODE(code);
                        if (list_one_data.get(i).getSCSJ() != null) {
                            bbgx.setSCSJ(list_one_data.get(i).getSCSJ() + "");
                        }
                        util.coreSave(bbgx);
                        //有code不存
//                        util.coreSave(bhxhrjbb);
                    } else {
                        BHPZXHBBGX bbgx = new BHPZXHBBGX();
                        bbgx.setID(util.getInsertId("BHPZXHBBGX"));
                        bbgx.setBHPZID(DemoActivity.instance.bhsb.getId());
                        bbgx.setRJBBCODE(bhxhrjbb.getCode());
                        if (list_one_data.get(i).getSCSJ() != null) {
                            bbgx.setSCSJ(list_one_data.get(i).getSCSJ() + "");
                        }
                        util.coreSave(bbgx);
                        //先存关系表
                        util.coreSave(bhxhrjbb);
                    }
                }
            }
        }
    }

    public void savemore() {
        //选配功能
        if (lty && is2013) {
            //code 获取来自LTYSBXHB
            ltysbxh.setId(util.getInsertId("LTYSBXH"));
            ltysbxh.setXp(DemoActivity.instance.bhsb.getXp() + "");
            ltysbxh.setWjmc(DemoActivity.instance.bhsb.getIcdwjmc() + "");
//            ltysbxh.setRjbb(ver_info + " " + scrq_info + " " + jym_info);
            ltysbxh.setRjbb(ver_info + "");
            ltysbxh.setZzcj(made_company + "");
            ltysbxh.setBhlb(protect_type + "");
            ltysbxh.setBhxh(protect_type_m + "");
            if (llFlLx.getVisibility() == View.VISIBLE) {
                ltysbxh.setBhfl(protect_type_fl + "");
                ltysbxh.setBhlx(protect_type_lx + "");
            } else {
                ltysbxh.setBhfl(flMap.get(protect_type_m) + "");
                ltysbxh.setBhlx(lxMap.get(protect_type_m) + "");
            }
            ltysbxh.setBblx("六统一设备");
            ltysbxh.setED_TAG("C");
            ltysbxh.setState("C");
            ltysbxh.setSfqy("Y");
            ltysbxh.setSCSJ(TimeUtil.formatString(scrq_info + "") + "");

            if (Details8.instance != null) {
                Details8.instance.saveICD(DemoActivity.instance.bhsb.getId());
            }

//            ltysbxh.setCode(util.getInsertId("CODE") + "");
            ltysbxh.setCode(util.getInsertId() + "");

            String code = util.getCodeByBhxhRjbb(ltysbxh);
            if (code != null) {
                ltysbxh.setCode(code + "");

                BHPZXHBBGX bbgx = new BHPZXHBBGX();
                bbgx.setID(util.getInsertId("BHPZXHBBGX"));
                bbgx.setBHPZID(DemoActivity.instance.bhsb.getId());
                bbgx.setRJBBCODE(code + "");
                if (!scrq_info.equals("")) {
                    bbgx.setSCSJ(TimeUtil.formatString(scrq_info + "") + "");
                }
                util.coreSave(bbgx);
                //有code，只存关系表
//                util.coreSave(ltysbxh);
            } else {
                BHPZXHBBGX bbgx = new BHPZXHBBGX();
                bbgx.setID(util.getInsertId("BHPZXHBBGX"));
                bbgx.setBHPZID(DemoActivity.instance.bhsb.getId());
                bbgx.setRJBBCODE(ltysbxh.getCode() + "");
                if (!scrq_info.equals("")) {
                    bbgx.setSCSJ(TimeUtil.formatString(scrq_info + "") + "");
                }
                util.coreSave(bbgx);
                //先存关系表
                util.coreSave(ltysbxh);
            }
            List<Object> listold = new DaoUtil(getActivity()).getBHRJBBByCode(true, true, DemoActivity.instance.bhsb.getId() + "");
            if (listold != null && listold.size() > 0) {
                ltysbxh_old = (LTYSBXH) listold.get(0);
            }
        } else if (!ismodel_type) {
//            不分模块
            //code 获取来自BHXHRJBB
            if (!ver_info.equals("") && !is2013 && DemoActivity.instance.bhsb.getBhlx().equals("微机型")) {
                BHXHRJBB bhxhrjbb = new BHXHRJBB();
                bhxhrjbb.setBblx("非六统一，不分模块");
//                bhxhrjbb.setBb(ver_info + "");
                String data = "";
                if (!scrq_info.equals("")) {
                }
                bhxhrjbb.setRjbbxx(
                        "软件版本：" + ver_info + "," + "校验码：" + jym_info
                                + "," + "生成时间：" + scrq_info
                );
                DemoActivity.instance.bhsb.setRjbb(bhxhrjbb.getRjbbxx() + "");

                bhxhrjbb.setBb(ver_info + "");
                bhxhrjbb.setJym(jym_info + "");
                bhxhrjbb.setSCSJ(scrq_info + "");

                bhxhrjbb.setId(util.getInsertId("BHXHRJBB"));
                bhxhrjbb.setBhxhcode(codeMap.get(protect_type_m) + "");
//                bhxhrjbb.setCode(util.getInsertId("CODE") + "");
                bhxhrjbb.setCode(util.getInsertId() + "");
                bhxhrjbb.setState("C");
                bhxhrjbb.setED_TAG("C");
//                util.coreSave(bhxhrjbb);

                String code = util.getCodeByBhxhRjbb(bhxhrjbb);
                if (code != null) {
                    BHPZXHBBGX bbgx = new BHPZXHBBGX();
                    bbgx.setID(util.getInsertId("BHPZXHBBGX"));
                    bbgx.setBHPZID(DemoActivity.instance.bhsb.getId());
                    bbgx.setRJBBCODE(code);
                    if (!scrq_info.equals("")) {
                        bbgx.setSCSJ(scrq_info + "");
                    }
                    util.coreSave(bbgx);
                    //先存关系表
//                    util.coreSave(bhxhrjbb);
                } else {
                    BHPZXHBBGX bbgx = new BHPZXHBBGX();
                    bbgx.setID(util.getInsertId("BHPZXHBBGX"));
                    bbgx.setBHPZID(DemoActivity.instance.bhsb.getId());
                    bbgx.setRJBBCODE(bhxhrjbb.getCode());
                    if (!scrq_info.equals("")) {
                        bbgx.setSCSJ(TimeUtil.formatString(scrq_info + "") + "");
                    }
                    util.coreSave(bbgx);
                    //先存关系表
                    util.coreSave(bhxhrjbb);
                }
            }
        }

        if (!is2013) {
            List<ICDXX> icdxxs2 = new ArrayList<>();
            //删除库里的ICD信息
            List<Object> icdList = util.getICDOrBKXX(ICDXX.class, DemoActivity.instance.bhsb.getId() + "", "BHPZ"); //从库里取ICD信息
            if (icdList.size() > 0) {
                for (int i = 0; i < icdList.size(); i++) {
                    ICDXX icdxx = (ICDXX) icdList.get(i);
                    icdxxs2.add(icdxx);
//                    if (isAdd(icdxx)) {
//                        icdxxs.add(icdxx);
//                    }
                }
                icdList.clear();
            }
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
            if (DemoActivity.instance.getIntent().hasExtra("ICDXX")) {
                ICDXX icdxxss = (ICDXX) DemoActivity.instance.getIntent().getSerializableExtra("ICDXX");
                if (icdxxss.getED_TAG() != null && icdxxss.getED_TAG().equals("C")) {
                    util.coreICD("D", icdxxss); //删除数据表
                } else { //只更新
                    icdxxss.setED_TAG("D");
                    icdxxss.setSB("D");
                    util.coreICD("M", icdxxss); //更新数据表
                }
            }

        }


        //是否新增型号,型号保存放最后
        if (bhsbxhb != null) {
            util.coreSave(bhsbxhb);
        }
        //之后清空型号,核对
        bhsbxhb = null;
    }

    //保护名称，点击自动生成
    public void made_protect_name(final int position) {
        //保护名称逻辑处理
        //默认已经生成，需要修改
        if (false) {
            ToastUtils.showToast(getActivity(), "手动修改保护名称");
        } else {
            String control_company_name = DemoActivity.instance.bhsb.getCzmc() + "";
            if (control_company_name.equals("null")) {
                control_company_name = "";
            }
            String electric_level = Fragment_Type_Inset.instance.dydj + "";
            if (electric_level.equals("null")) {
                electric_level = "";
            }
            protect_type_m = editTexts.get(2).getText() + "";
            protect_type = textViews.get(1).getText() + "";
            String one_device_name = Fragment_Type_Inset.instance.ycsbmc + "";
            if (one_device_name.equals("null")) {
                one_device_name = "";
            }
            String protect_config = DemoActivity.instance.bhsb.getTb() + "";
            if (protect_config.equals("null")) {
                protect_config = "";
            }
            CZCS czcs = util.getCZCSByGLDW();
            if (czcs != null && czcs.getCzmc() != null) {
                control_company_name = czcs.getCzmc() + "";
            }
//           厂站名称 电压等级、+KV 一次设备名称 、套别、型号、保护类别
            String made_name = control_company_name + electric_level + "kV" +
                    one_device_name +
                    protect_config +
                    protect_type_m +
                    protect_type + "装置";
            protect_name = made_name + "";
            editTexts.get(0).setText(protect_name + "");
            ToastUtils.showToast(getActivity(), "保护名称已生成");
            //查重
            protect_name_rep = false;
//            makesure_protect_name(position);
        }
    }

    public String getzzcj() {
        String zzcj = editTexts.get(1).getText() + "";
        return zzcj;
    }

    public String getBhlb() {
        String bhlb = textViews.get(1).getText() + "";
        return bhlb;
    }

    public String getBhxh() {
        String bhxh = editTexts.get(2).getText() + "";
        return bhxh;
    }

    public void setBhxh() {
        editTexts.get(2).setText("");
    }

    public void change_jym(boolean is2013) {
        if (is2013) {
            //2013版 校验码、生成日期隐藏,不清空
//            editTexts.get(4).setText("");
//            textViews.get(5).setText("");
            rlJym.setVisibility(View.GONE);
            rlScrq.setVisibility(View.GONE);
        } else {
//            editTexts.get(4).setText("");
//            textViews.get(5).setText("");
            rlJym.setVisibility(View.VISIBLE);
            rlScrq.setVisibility(View.VISIBLE);
        }
    }


    //保护名称生成查重
    public void makesure_protect_name(int position) {
        Object obj = util.getFZSBBySbsbdm(protect_name.trim(), "");
        Long id = null;
        if (obj instanceof BHPZ) {
            id = ((BHPZ) obj).getId();
        } else if (obj instanceof FZBHSB) {
            id = ((FZBHSB) obj).getId();
        }
//        BHPZ bhpz = util.getBHPZMC(protect_name);
        //判断是否是修改已经保存的,id 唯一
//            ToastUtils.showToast(getActivity(), "保护名称重复，请手动修改");
        protect_name_rep = id != null && !id.equals(DemoActivity.instance.bhsb.getId());
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
                    String tmp = str.toString().trim();
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
                    currentText = tmp;
                }
            });
        }
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
                if (rjbbList != null && rjbbList.size() > 0) {
                    if (s.contains("模块名称") || s.contains("软件版本") || s.contains("校验码") || s.contains("生成时间"))
                        continue;
                }

                if (hasRjbb && llRjbb.getVisibility() == View.GONE && listOne.getVisibility() == View.GONE) {
                    if (s.contains("模块名称") || s.contains("软件版本") || s.contains("校验码") || s.contains("生成时间"))
                        continue;
                }
                boolean lastone = isShow;
                map_key.get(s).setCompoundDrawables(null, null, null, null);
                if (map_more.get(s).getText().toString().trim().equals("")) {
                    map_more.get(s).setBackground(getResources().getDrawable(R.drawable.device_detials_bg2));
                    map_more.get(s).setHint("必填项");
                    isShow = true;
                } else {
                    map_more.get(s).setBackground(getResources().getDrawable(R.drawable.device_detials_bg));
                }
                if (checkName.equals("装置基本信息")) {

                    if (s.equals("软件版本") && map_more.get(s).getText().toString().trim().equals("") &&
                            !(map_more.get("装置类型").getText().toString().trim().equals("微机型") ||
                                    map_more.get("装置类型").getText().toString().trim().equals(""))) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("校验码") && map_more.get(s).getText().toString().trim().equals("") && is2013 ||
                            s.equals("校验码") && map_more.get(s).getText().toString().trim().equals("") &&
                                    !(map_more.get("装置类型").getText().toString().trim().equals("微机型") ||
                                            map_more.get("装置类型").getText().toString().trim().equals(""))) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("生成时间") && map_more.get(s).getText().toString().trim().equals("") && is2013 ||
                            s.equals("校验码") && map_more.get(s).getText().toString().trim().equals("") &&
                                    !(map_more.get("装置类型").getText().toString().trim().equals("微机型") ||
                                            map_more.get("装置类型").getText().toString().trim().equals(""))) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("六统一标准版本") && map_more.get(s).getText().toString().trim().equals("") && !lty) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("设备类型") && map_more.get(s).getText().toString().trim().equals("") && !map_more.get("装置类别").getText().toString().trim().equals("安全自动装置")) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("测距形式") && map_more.get(s).getText().toString().trim().equals("") && !map_more.get("装置类别").getText().toString().trim().equals("故障测距装置")) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("故障录波器类型") && map_more.get(s).getText().toString().trim().equals("") && !map_more.get("装置类别").getText().toString().trim().equals("故障录波器")) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("设备功能配置") && map_more.get(s).getText().toString().trim().equals("") && !(map_more.get("装置类别").getText().toString().trim().equals("安全自动装置") && map_more.get("设备类型").getText().toString().trim().equals("安全稳定控制装置"))) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                }
                if (s.equals("退运日期") && map_more.get(s).getText().toString().trim().equals("") && !map_more.get("设备状态").getText().toString().trim().equals("退运")) {
                    if (!lastone) {
                        isShow = false;
                    }
                }
            }
        } else {
            for (String s : map_more.keySet()) {
                if (rjbbList != null && rjbbList.size() > 0) {
                    if (s.contains("模块名称") || s.contains("软件版本") || s.contains("校验码") || s.contains("生成时间"))
                        continue;
                }

                if (hasRjbb && llRjbb.getVisibility() == View.GONE && listOne.getVisibility() == View.GONE) {
                    if (s.contains("模块名称") || s.contains("软件版本") || s.contains("校验码") || s.contains("生成时间"))
                        continue;
                }
                boolean lastone = isShow;
                if (map_more.get(s).getText().toString().trim().equals("")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.tanhao);
                    drawable.setBounds(0, 0, 25, 25);
                    map_key.get(s).setCompoundDrawables(drawable, null, null, null);
                    isShow = true;
                }
                if (checkName.equals("装置基本信息")) {

                    if (s.equals("软件版本") && map_more.get(s).getText().toString().trim().equals("") &&
                            !(map_more.get("装置类型").getText().toString().trim().equals("微机型") ||
                                    map_more.get("装置类型").getText().toString().trim().equals(""))) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("校验码") && map_more.get(s).getText().toString().trim().equals("") && is2013 ||
                            s.equals("校验码") && map_more.get(s).getText().toString().trim().equals("") &&
                                    !(map_more.get("装置类型").getText().toString().trim().equals("微机型") ||
                                            map_more.get("装置类型").getText().toString().trim().equals(""))) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("生成时间") && map_more.get(s).getText().toString().trim().equals("") && is2013 ||
                            s.equals("校验码") && map_more.get(s).getText().toString().trim().equals("") &&
                                    !(map_more.get("装置类型").getText().toString().trim().equals("微机型") ||
                                            map_more.get("装置类型").getText().toString().trim().equals(""))) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("六统一标准版本") && map_more.get(s).getText().toString().trim().equals("") && !lty) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("设备类型") && map_more.get(s).getText().toString().trim().equals("") && !map_more.get("装置类别").getText().toString().trim().equals("安全自动装置")) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("测距形式") && map_more.get(s).getText().toString().trim().equals("") && !map_more.get("装置类别").getText().toString().trim().equals("故障测距装置")) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("故障录波器类型") && map_more.get(s).getText().toString().trim().equals("") && !map_more.get("装置类别").getText().toString().trim().equals("故障录波器")) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                    if (s.equals("设备功能配置") && map_more.get(s).getText().toString().trim().equals("") && !(map_more.get("装置类别").getText().toString().trim().equals("安全自动装置") && map_more.get("设备类型").getText().toString().trim().equals("安全稳定控制装置"))) {
                        if (!lastone) {
                            isShow = false;
                        }
                    }
                }
                if (s.equals("退运日期") && map_more.get(s).getText().toString().trim().equals("") && !map_more.get("设备状态").getText().toString().trim().equals("退运")) {
                    if (!lastone) {
                        isShow = false;
                    }
                }
            }
        }
//        if (hasRjbb)
//            DemoActivity.instance.checkBH(checkName, isShow);
//        else
        if (checkName.equals("装置基本信息") && listOne.getVisibility() == View.VISIBLE) {
            for (BHXHRJBB bhxhrjbb : list_one_data) {
                if (bhxhrjbb == null || bhxhrjbb.getMkmc() == null || bhxhrjbb.getMkmc().equals("")) {
                    isShow = true;
                } else if (bhxhrjbb == null || bhxhrjbb.getBb() == null || bhxhrjbb.getBb().equals("")) {
                    isShow = true;
                } else if (bhxhrjbb == null || bhxhrjbb.getJym() == null || bhxhrjbb.getJym().equals("")) {
                    isShow = true;
                } else if (Fragment_Type_Base.instance.bt_scsj && bhxhrjbb == null
                        || Fragment_Type_Base.instance.bt_scsj && bhxhrjbb.getSCSJ() == null
                        || Fragment_Type_Base.instance.bt_scsj && bhxhrjbb.getSCSJ().equals("")) {
                    isShow = true;
                }
            }
        }
        if (checkName.equals("连接器信息") && Fragment_Type_Ljq.instance != null) {
            Fragment_Type_Ljq.instance.check = isShow;
        }
        if (checkName.equals("连接器信息") && Fragment_Type_Ljq.instance != null && Fragment_Type_Ljq.instance.checklist()) {
            isShow = Fragment_Type_Ljq.instance.checklist();
        }
        DemoActivity.instance.check(checkName, isShow);
    }


    public void InSetData() {

        //在插其他表关系之前删除,非六统一，模块、版本
        util.deleteBHPZXHBBGX(DemoActivity.instance.bhsb.getId() + "");
        //在插其他表关系之前删除,通道
        String sb_bhpzid = DemoActivity.instance.bhsb.getId() + "";
        util.deleteTDXX(sb_bhpzid);
//       在插其他表关系之前删除, 安控等
        util.deleteAKXTGX(DemoActivity.instance.bhsb.getId() + "");

        //在插入安控等，先清空保护配置安控通道等值,连接器
        DemoActivity.instance.bhsb.setSsakxtddmm("");
        DemoActivity.instance.bhsb.setAkzdlx("");
        DemoActivity.instance.bhsb.setTdlx("");

        //存值设备识别代码
        DemoActivity.instance.bhsb.setSfsbm(editTexts.get(6).getText() + "");
        //装置名称
        DemoActivity.instance.bhsb.setBhmc(editTexts.get(0).getText() + "");

        //六统一标准版本
        if (lty) {
            DemoActivity.instance.bhsb.setSfltysb("是");
            DemoActivity.instance.bhsb.setLtybzbb(ltybzbb + "");
        } else {
            DemoActivity.instance.bhsb.setSfltysb("否");
        }
        if (is2013) {
//            DemoActivity.instance.bhsb.setXp(ltysbxh.getXp() + "");
//            DemoActivity.instance.bhsb.setIcdwjmc(ltysbxh.getWjmc() + "");
        } else {
            //默认清空ICD信息
            DemoActivity.instance.bhsb.setXp("");
            DemoActivity.instance.bhsb.setIcdwjmc("");
        }
        //制造厂家
        DemoActivity.instance.bhsb.setZzcj(made_company + "");
        //装置类别
        DemoActivity.instance.bhsb.setBhlb(protect_type + "");
        //装置型号
        DemoActivity.instance.bhsb.setBhxh(protect_type_m + "");
        //型号新增取值分类、类型
        if (llFlLx.getVisibility() == View.VISIBLE) {
            DemoActivity.instance.bhsb.setBhfl(protect_type_fl + "");
            DemoActivity.instance.bhsb.setBhlx(protect_type_lx + "");
        } else {
            DemoActivity.instance.bhsb.setBhfl(flMap.get(protect_type_m) + "");
            DemoActivity.instance.bhsb.setBhlx(lxMap.get(protect_type_m) + "");
        }

//        DemoActivity.instance.bhsb.setBhlb(protect_type + "");
        //设备类型传值，需要单独处理
        if (protect_type.equals("安全自动装置")) {
            DemoActivity.instance.bhsb.setBhlbxh(device_type + "");
        } else {
            DemoActivity.instance.bhsb.setBhlbxh(protect_type_details + "");
        }

        //故障录波器类型
        //测距形式
        //是否调度主
        //设备功能配置
        DemoActivity.instance.bhsb.setGzlbqlx(machine_type + "");
        DemoActivity.instance.bhsb.setCjxx(test_type + "");
        DemoActivity.instance.bhsb.setSfjrzz(isask + "");
        DemoActivity.instance.bhsb.setSbgnpz(device_work_set + "");
        //出厂日期
        DemoActivity.instance.bhsb.setCcrq(TimeUtil.formatString(textViews.get(2).getText().toString() + "") + "");

        //板卡数量
        int bksl = 0;
        if (editTexts.get(5).getText().toString() != null && !editTexts.get(5).getText().toString().equals("")) {
            bksl = Integer.parseInt(editTexts.get(5).getText().toString());
        }
        DemoActivity.instance.bhsb.setBksl(bksl);

        if (sfjdhsb) {
            DemoActivity.instance.bhsb.setSfjdhzz("是");
        } else {
            DemoActivity.instance.bhsb.setSfjdhzz("否");
        }

        DemoActivity.instance.bhsb.setRjbb(ver_info + "");
    }

    public void saveNew() {
        //判断厂家是否需要新增
        List<ZZCJ> cjList = util.getZZCJ(made_company);
        if (cjList == null || cjList.size() == 0) {//厂家新增
            zzcj_add = new ZZCJ();
            zzcj_add.setMC(made_company);
            zzcj_add.setCZR(PreferenceUtils.getPrefString(getActivity(), "userInfo"
                    , null).split("-")[0]);
            zzcj_add.setID(util.getInsertId("ZZCJ"));
            zzcj_add.setED_TAG("C");
            util.coreSave(zzcj_add);
        }

        if (!is2013) {
            //判断型号是否需要新增,默认给低电压去查全部型号
            List<Object> xhList = util.getBHXH(lty, is2013, made_company, protect_type, DemoActivity.instance.bhsb.getBdzlx() + ""
                    , "0", "", protect_type_m);
            if (xhList == null || xhList.size() == 0) {//需要新增型号
                bhsbxhb = new BHSBXHB();
                bhsbxhb.setBhlx(protect_type_lx + "");
                bhsbxhb.setBhfl(protect_type_fl + "");
                if (llHassffmk.getVisibility() == View.VISIBLE) {
                    bhsbxhb.setBblx(cbXhSffmk.isChecked() ? "非六统一，分模块" : "非六统一，不分模块");
                } else if (!protect_type_lx.equals("微机型")) {
                    bhsbxhb.setBblx("无版本");
                } else if (list_one_data.size() > 0 && listOne.getVisibility() == View.VISIBLE) {
                    bhsbxhb.setBblx("非六统一，分模块");
                } else {
                    bhsbxhb.setBblx("非六统一，不分模块");
                }
                bhsbxhb.setSbxh(protect_type_m);
                bhsbxhb.setED_TAG("C");
                CZCS czcs = util.getCZCSByGLDW();
                int dydjs = Integer.parseInt(DemoActivity.instance.bhsb.getDydj() + "");

                if (dydjs >= 220 ||
                        DemoActivity.instance.bhsb.getBdzlx().equals("智能站") &&
                                czcs.getCzzgdydj() >= 110) {
                    bhsbxhb.setSfqy("Y");
                }
                bhsbxhb.setBhlb(protect_type);
                bhsbxhb.setZzcj(made_company);


                //是否新增型号
                if (bhsbxhb != null) {
                    bhsbxhb.setCzr(PreferenceUtils.getPrefString(getActivity(), "userInfo", null).split("-")[0]);
                    bhsbxhb.setId(util.getInsertId("BHSBXHB"));
                    bhsbxhb.setCode(util.getInsertId() + "");
                    codeMap.put(bhsbxhb.getSbxh() + "", bhsbxhb.getCode() + "");
//                    util.coreSave(bhsbxhb);
                }
            } else {

                String usegddata = "false";
                if (bbToCode.size() > 0 && list_one_data != null && list_one_data.size() > 0
                        && list_one_data.get(0).getBb() != null && bbToCode.containsKey(list_one_data.get(0).getBb())) {
                    BHSBXHB xh = (BHSBXHB) util.getBHXHByCode(bbToCode.get(list_one_data.get(0).getBb()) + "", lty, is2013);
                    if (xh != null) {
                        String xhs = xh.getSfqy() + "";
                        usegddata = xhs.equals("Y") ? "true" : "false";
                    }
                } else {
                    BHSBXHB xh = (BHSBXHB) util.getBHXHByCode(codeMap.get(protect_type_m) + "", lty, is2013);
                    if (xh != null) {
                        String xhs = xh.getSfqy() + "";
                        usegddata = xhs.equals("Y") ? "true" : "false";
                    }
                }
                DemoActivity.instance.bhsb.setUsegddata(usegddata);

                CZCS czcs = util.getCZCSByGLDW();
                int dydjs = Integer.parseInt(DemoActivity.instance.bhsb.getDydj() + "");


                if (xhList.size() == 1 && dydjs >= 220 || xhList.size() == 1 &&
                        DemoActivity.instance.bhsb.getBdzlx().equals("智能站") &&
                        czcs.getCzzgdydj() >= 110) {
                    //本地新增，低电压改为高电压国调，同步修改涉及保护台账。
                    if (((BHSBXHB) xhList.get(0)).getED_TAG() != null && ((BHSBXHB) xhList.get(0)).getED_TAG().equals("C")) {
                        if (((BHSBXHB) xhList.get(0)).getSfqy() != null && ((BHSBXHB) xhList.get(0)).getSfqy().equals("Y")) {

                        } else {
                            ((BHSBXHB) xhList.get(0)).setSfqy("Y");
                            bhsbxhb = (BHSBXHB) xhList.get(0);
                            bhsbxhb.setSfqy("Y");
                            bhsbxhb.setSh("M");
                            codeMap.put(bhsbxhb.getSbxh() + "", bhsbxhb.getCode() + "");
                            //同步修改涉及保护台账
                            util.getBHPZorFZSBbyxh(bhsbxhb.getSbxh(), "BHPZ");
//                            util.coreSave(((BHSBXHB)xhList.get(0)));
                        }
                    } else {
                        //库里有低电压，需要新增一条高电压国调
                        if (((BHSBXHB) xhList.get(0)).getSfqy() != null && ((BHSBXHB) xhList.get(0)).getSfqy().equals("Y")) {

                        } else {
                            bhsbxhb = (BHSBXHB) xhList.get(0);
                            bhsbxhb.setSfqy("Y");
                            bhsbxhb.setED_TAG("C");
                            bhsbxhb.setId(util.getInsertId("BHSBXHB"));
                            bhsbxhb.setCode(util.getInsertId("CODE") + "");
                            codeMap.put(bhsbxhb.getSbxh() + "", bhsbxhb.getCode() + "");
//                            util.coreSave(bhsbxhb);
                        }
                    }
                }
            }
        }
    }

    public void saveBase() {
        //保存之前，先删除关系表，模块版本，六统一，
        InSetData();
        saveNew();
        if (rl_more_mk.getVisibility() == View.VISIBLE) {
            Fragment_Type_More_Mk.instance.saveMoreMk();
        } else {
            addmk();
        }
        savemore();
    }

    Boolean isload = true;

    @Override
    public void onScroll(int scrollY) {
        if (llHassffmk.getVisibility() == View.VISIBLE) {
            if (list_one_data.size() > 0 && listOne.getVisibility() == View.GONE && scrollY > 50 && cbXhSffmk.isChecked()) {
                listOne.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }
        } else {
            String bblx = bblxMap.get(editTexts.get(2).getText().toString()) + "";
            if (bblx.equals("非六统一，分模块") && list_one_data.size() > 0 && listOne.getVisibility() == View.GONE && scrollY > 50) {
                listOne.setVisibility(View.VISIBLE);
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
        String ycmc = Fragment_Type_Inset.instance.editTexts.get(1).getText().toString().trim();
        String zzmc = editTexts.get(0).getText().toString().trim();
        if (ycmc.contains("kV")) {

        }
        if (ycmc.equals("") || zzmc.equals(""))
            return true;
        return zzmc.contains(ycmc);
    }
}
