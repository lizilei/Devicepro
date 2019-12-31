package cn.com.sgcc.dev.view.fragment.BHSB;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.datapick.widget.bean.DateType;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import cn.com.sgcc.dev.adapter.TypeBaseAdapter;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.BHSBXHB;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
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
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.utils.WrapContentListView;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.SelectActivity;
import cn.com.sgcc.dev.view.fragment.BaseFragment;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details1;
import cn.com.sgcc.dev.view.viewinterface.DateChooseListener;

import static android.app.Activity.RESULT_OK;


/**
 * <p>@description:</p>
 * 安装及运维 fragment
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */

public class Fragment_Type_Inset extends BaseFragment {
    @BindView(R.id.tv_bottom)
    TextView tvBottom;

    //一次设备类型、电压等级、单位名称、调度单位、投运日期、运检单位、设计单位、基建单位、运行单位、维护单位、设备属性,间隔类型
    @BindViews(value = {R.id.tv_one_device_two_right, R.id.tv_one_device_four_right, R.id.tv_one_device_five_right,
            R.id.tv_one_device_six_right,
            R.id.tv_one_device_nine_right,
            R.id.tv_one_device_ten_right,
            R.id.tv_one_device_thirteen_right,
            R.id.tv_one_device_fourteen_right,
            R.id.tv_one_device_fifteen_right,
            R.id.tv_one_device_sixteen_right,
            R.id.tv_one_device_seventeen_right,
            R.id.tv_jglx_device_name_right,
    })
    List<TextView> textViews;
    //一次设备类型、一次设备名称、电压等级、单位名称、调度单位、所属屏柜、
    // 投运日期、设计单位、基建单位、运行单位、维护单位、设备属性,间隔类型，间隔名称
    @BindViews(value = {R.id.tv_one_device_two_left,
            R.id.tv_one_device_three_left,
            R.id.tv_one_device_four_left,
            R.id.tv_one_device_five_left,
            R.id.tv_one_device_six_left,
            R.id.tv_one_device_eight_left,
            R.id.tv_one_device_nine_left,
            R.id.tv_one_device_thirteen_left,
            R.id.tv_one_device_fourteen_left,
            R.id.tv_one_device_fifteen_left,
            R.id.tv_one_device_sixteen_left,
            R.id.tv_one_device_seventeen_left,
            R.id.tv_jglx_device_name_left,
            R.id.tv_jgmc_device_name_left,
    })
    List<TextView> textViews_check;
    //一次设备类型、一次设备名称、电压等级、单位名称、调度单位、所属屏柜，投运日期、
    //设计单位、基建单位、运行单位、维护单位、设备属性,间隔类型,间隔名称
    @BindViews(value = {R.id.tv_one_device_two_right,
            R.id.et_one_device_three_right,
            R.id.tv_one_device_four_right,
            R.id.tv_one_device_five_right,
            R.id.tv_one_device_six_right,
            R.id.et_one_device_eight_right,
            R.id.tv_one_device_nine_right,
            R.id.tv_one_device_thirteen_right,
            R.id.tv_one_device_fourteen_right,
            R.id.tv_one_device_fifteen_right,
            R.id.tv_one_device_sixteen_right,
            R.id.tv_one_device_seventeen_right,
            R.id.tv_jglx_device_name_right,
            R.id.et_jgmc_device_name_right
    })
    List<TextView> textViews_check_bt;
    //所属间隔、一次设备名称、保护小室名称、所属屏柜、运维班组、检修班组、间隔名称
    @BindViews(value = {R.id.et_one_device_name_right,
            R.id.et_one_device_three_right,
            R.id.et_one_device_seven_right,
            R.id.et_one_device_eight_right,
            R.id.et_one_device_eleven_right,
            R.id.et_one_device_twelve_right,
            R.id.et_jgmc_device_name_right
    })
    List<EditText> editTexts;

    Unbinder unbinder;
    @BindView(R.id.rl_one)
    RelativeLayout rlOne;
    @BindView(R.id.rl_two)
    RelativeLayout rlTwo;
    @BindView(R.id.rl_three)
    RelativeLayout rlThree;
    @BindView(R.id.rl_four)
    RelativeLayout rlFour;
    @BindView(R.id.rl_five)
    RelativeLayout rlFive;
    @BindView(R.id.rl_six)
    RelativeLayout rlSix;
    @BindView(R.id.rl_seven)
    RelativeLayout rlSeven;
    @BindView(R.id.rl_eight)
    RelativeLayout rlEight;
    @BindView(R.id.rl_nine)
    RelativeLayout rlNine;
    @BindView(R.id.rl_ten)
    RelativeLayout rlTen;
    @BindView(R.id.rl_eleven)
    RelativeLayout rlEleven;
    @BindView(R.id.rl_twelve)
    RelativeLayout rlTwelve;
    @BindView(R.id.rl_thirteen)
    RelativeLayout rlThirteen;
    @BindView(R.id.rl_fourteen)
    RelativeLayout rlFourteen;
    @BindView(R.id.rl_fifteen)
    RelativeLayout rlFifteen;
    @BindView(R.id.rl_sixteen)
    RelativeLayout rlSixteen;
    @BindView(R.id.rl_seventeen)
    RelativeLayout rlSeventeen;

    @BindViews(value = {R.id.fragment_add_bt_two
            , R.id.fragment_add_bt_three
            , R.id.fragment_add_bt_four
            , R.id.fragment_add_bt_five
            , R.id.fragment_add_bt_six
            , R.id.fragment_add_bt_enight
            , R.id.fragment_add_bt_nine
            , R.id.fragment_add_bt_thirteen
            , R.id.fragment_add_bt_fourteen
            , R.id.fragment_add_bt_fifteen
            , R.id.fragment_add_bt_sixteen
            , R.id.fragment_add_bt_seventeen
            , R.id.fragment_add_bt_jgmc
            , R.id.fragment_add_bt_jglx
    })
    List<Button> buttons;
    @BindView(R.id.ll_jgms)
    LinearLayout llJgms;

    private TypeBaseAdapter adapter;
    private WrapContentListView list_one;
    private List<DeviceDetailsNameItem> list_one_data;
    public static Fragment_Type_Inset instance = null;

//    装置名称、是否六统一、六统一标准版本、制造厂家、装置类别、装置型号、
//    模块名称、软件版本、校验码、软件版本生成时间、出厂日期、装置类别细化\设备类型、
//    设备功能配置（若是安自装置才显示）、故障录波器类型、测距形式、是否接入调度主站、
//    是否就地化设备、

    private boolean lty;
    public String ycsblx;
    public String ycsbmc;
    public String dydj;

    private IDaoUtil util;

    public List<SaleAttributeVo> saleVo;

    private Map<String, TextView> map_key = new HashMap<>();
    private Map<String, TextView> map_more = new HashMap<>();
    private boolean isShow;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_type_inset;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    public void initview() {
        util = new DaoUtil(getActivity());

        //所属间隔隐藏
        rlOne.setVisibility(View.GONE);
        //保护小室名称隐藏
        rlSeven.setVisibility(View.GONE);
        //运检单位隐藏
        rlTen.setVisibility(View.GONE);
        //运维班组隐藏
        rlEleven.setVisibility(View.GONE);
        //检修班组隐藏
        rlTwelve.setVisibility(View.GONE);

        if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C")) {
            tvBottom.setVisibility(View.GONE);
        } else {
            tvBottom.setVisibility(View.VISIBLE);
            if (DemoActivity.instance.rzgl != null) {
                tvBottom.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
            } else {
                tvBottom.setText("本条台账最后一次修改时间：");
            }
        }

//        editTexts.get(1).setText(bhsb.getYcsbmc());
//        ycsbmc = bhsb.getYcsbmc()+"";
        editTexts.get(1).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ycsbmc = editTexts.get(1).getText() + "";
            }
        });

        //间隔模式显示
        Map<String, String> keyMap = new HashMap<>();
        Map<String, String> txtMap = AppUtils.setTxt(keyMap,"",false);
        String jgms=txtMap.get("jgms")+"";
        if (!jgms.equals("是")){
            llJgms.setVisibility(View.GONE);
        }

        setCancelBtx();
    }


    @Override
    public void initEvevt() {

//        boolean isEdit = getArguments().getBoolean("isEdit", false);
//        initReceiver(isEdit);

    }

    @Override
    public void initData() {
        //初始化校验项
        init();
//        新增或者编辑
        if (Fragment_Type_Base.instance.isC) {

        } else {

            //一次设备类型、电压等级、单位名称、调度单位、投运日期、
            // 运检单位、设计单位、基建单位、运行单位、维护单位、设备属性
            BHPZ bhsb = DemoActivity.instance.bhsb;

            if (bhsb.getBhlb()!=null){
//                changeyc(bhsb.getBhlb()+"");
            }

            if (bhsb.getYcsblx() != null) {
                textViews.get(0).setText(bhsb.getYcsblx() + "");
            }
            ycsblx = bhsb.getYcsblx() + "";
            dydj = bhsb.getDydj() + "";
            if (!dydj.equals("null") && !dydj.equals("0")) {
                textViews.get(1).setText(bhsb.getDydj() + "");
            }
            if (bhsb.getCzgldw() != null) {
                textViews.get(2).setText(bhsb.getCzgldw() + "");
            }
            if (bhsb.getDddw() != null) {
                textViews.get(3).setText(bhsb.getDddw() + "");
            }
            if (bhsb.getTyrq() != null) {
                textViews.get(4).setText(TimeUtil.formatString2(bhsb.getTyrq() + "") + "");
            }

//        textViews.get(5).setVisibility(View.GONE);
            if (bhsb.getSjdw() != null) {
                textViews.get(6).setText(bhsb.getSjdw() + "");
            }
            if (bhsb.getScdqjysj() != null) {
//                textViews.get(6).setText(bhsb.getScdqjysj()+"");
            }
            if (bhsb.getJjdw() != null) {
                textViews.get(7).setText(bhsb.getJjdw() + "");
            }
            if (bhsb.getYxdw() != null) {
                textViews.get(8).setText(bhsb.getYxdw() + "");
            }
            if (bhsb.getWhdw() != null) {
                textViews.get(9).setText(bhsb.getWhdw() + "");
            }
            if (bhsb.getBhsx() != null) {
                textViews.get(10).setText(bhsb.getBhsx() + "");
            }
            if (llJgms.getVisibility()==View.VISIBLE){
                if (bhsb.getJglx() != null) {
                    textViews.get(textViews.size()-1).setText(bhsb.getJglx() + "");
                }
            }

            //所属间隔、一次设备名称、保护小室名称、所属屏柜、运维班组、检修班组、
//        editTexts.get(0).setVisibility(View.GONE);
            if (bhsb.getYcsbmc() != null) {
                editTexts.get(1).setText(bhsb.getYcsbmc());
                ycsbmc = bhsb.getYcsbmc() + "";
            }
//        editTexts.get(2).setVisibility(View.GONE);
            if (bhsb.getSzpg() != null) {
                editTexts.get(3).setText(bhsb.getSzpg() + "");
            }
            if (llJgms.getVisibility()==View.VISIBLE){
                if (bhsb.getJgmc() != null) {
                    editTexts.get(editTexts.size()-1).setText(bhsb.getJgmc() + "");
                }
            }
//        editTexts.get(4).setVisibility(View.GONE);
//        editTexts.get(5).setVisibility(View.GONE);
            if (textViews.get(0).getText().toString().equals("其他")&&
                    editTexts.get(1).getText().toString().equals("其他")){
                changeyc("其他");
            }
        }

        saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getBhData()) {
            if (saleAttributeNameVo.getName().equals("安装及运维信息")) {
                saleVo.addAll(saleAttributeNameVo.getSaleVo());
                break;
            }
        }

        boolean isEdit = getArguments().getBoolean("isEdit", false);
        initReceiver(isEdit);

        /**
         * 加载必填项监听
         */
        Fragment_Type_Base.instance.initOnFocusChangeListener(map_key,map_more,false,"安装及运维信息");
    }

    //取消必填项
    public void setCancelBtx(){
//        调度单位非必填
        textViews.get(3).setHint("");
//        投运日期非必填
        textViews.get(4).setHint("");
//        设计单位非必填
        textViews.get(6).setHint("");
//        基建单位非必填
        textViews.get(7).setHint("");
//        运行单位非必填
        textViews.get(8).setHint("");
//        维护单位非必填
        textViews.get(9).setHint("");
//        设备属性非必填
        textViews.get(10).setHint("");

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

        List<SaleAttributeVo> saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getBhData()) {
            if (saleAttributeNameVo.getName().equals("安装及运维信息")) {
                saleVo = saleAttributeNameVo.getSaleVo();
                break;
            }
        }

        for (SaleAttributeVo saleAttributeVo : saleVo) {
//            if (saleAttributeVo.getValue().equals("电压等级")) {
//                map_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "（kV）："));
//                map_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "（kV）："));
//                continue;
//            }
            if (tmp_key.containsKey(saleAttributeVo.getValue() + ":")) {
                map_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + ":"));
                map_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + ":"));
            }
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
            for (Button button : buttons) {
                button.setVisibility(View.VISIBLE);
            }
//            for (TextView textView : textViews_check) {
////                setDrawableLeft(textView,false);
////            }
        } else {
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
//            for (TextView textView : textViews_check) {
//                setDrawableLeft(textView,false);
//            }

            if (DemoActivity.instance.rzgl != null &&
                    DemoActivity.instance.rzgl.getCZSJ() != null &&
                    !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                tvBottom.setVisibility(View.VISIBLE);
                tvBottom.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
            }
        }
        //所属屏柜，选择隐藏
        buttons.get(5).setVisibility(View.GONE);
        if (textViews.get(0).getText().toString().equals("其他")&&
                editTexts.get(1).getText().toString().equals("其他")){
            changeyc("其他");
        }
        /**
         * 必填项校验设计
         */
        Fragment_Type_Base.instance.checkBtx(isEdit, false, isShow, map_key, map_more, "安装及运维信息");
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fragment_add_bt_two, R.id.fragment_add_bt_three,
            R.id.fragment_add_bt_four, R.id.fragment_add_bt_five,
            R.id.fragment_add_bt_six, R.id.fragment_add_bt_enight,
            R.id.fragment_add_bt_nine, R.id.fragment_add_bt_thirteen,
            R.id.fragment_add_bt_fourteen, R.id.fragment_add_bt_fifteen,
            R.id.fragment_add_bt_sixteen, R.id.fragment_add_bt_seventeen,
    R.id.fragment_add_bt_jgmc,
    R.id.fragment_add_bt_jglx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_add_bt_two:
                setIntentData("一次设备类型");
                break;
            case R.id.fragment_add_bt_three:
                setIntentData("一次设备名称");
                break;
            case R.id.fragment_add_bt_four:
                setIntentData("电压等级");
                break;
            case R.id.fragment_add_bt_five:
                setIntentData("单位名称");
                break;
            case R.id.fragment_add_bt_six:
                setIntentData("调度单位");
                break;
            case R.id.fragment_add_bt_jglx:
                setIntentData("间隔类型");
                break;
            case R.id.fragment_add_bt_jgmc:
                setIntentData("间隔名称");
                break;
            case R.id.fragment_add_bt_enight:
//                setIntentData("所属屏柜");
                break;
            case R.id.fragment_add_bt_nine:
//                setIntentData("投运日期");
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        textViews.get(4).setText(date);
                    }
                });
                break;
            case R.id.fragment_add_bt_thirteen:
                setIntentData("设计单位");
                break;
            case R.id.fragment_add_bt_fourteen:
                setIntentData("基建单位");
                break;
            case R.id.fragment_add_bt_fifteen:
                setIntentData("运行单位");
                break;
            case R.id.fragment_add_bt_sixteen:
                setIntentData("维护单位");
                break;
            case R.id.fragment_add_bt_seventeen:
                setIntentData("设备属性");
                break;
        }
    }

    /**
     * @param type 类型
     */
    private void setIntentData(String type) {
        Map<String, Object> map = new HashMap<>();
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("type", type);
        map.put("number", "2");
        if (type.equals("一次设备名称")) {
            if (llJgms.getVisibility()==View.VISIBLE){
                map.put("jglx", textViews.get(textViews.size()-1).getText().toString()+"");
                map.put("jgmc", editTexts.get(editTexts.size()-1).getText().toString()+"");
            }
            map.put("ycsblx", textViews.get(0).getText().toString());
        }
        if (type.equals("间隔类型")) {
            map.put("number", "1");
        }
        if (type.equals("间隔名称")) {
            CZCS czcs = util.getCZCSByGLDW();
            map.put("czmc",czcs.getCzmc()+"");
            map.put("dwmc",czcs.getGldw()+"");
            map.put("jglx",textViews.get(textViews.size()-1).getText().toString()+"");
            map.put("number", "1");
        }
        intent.putExtra("conditions", (Serializable) map);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK: /* 取得数据，并显示于画面上 */
                Bundle bunde = data.getExtras();
                String value = bunde.getString("result") + "";
                String puttype = bunde.getString("puttype") + "";
                switch (puttype) {
                    //一次设备类型、电压等级、单位名称、调度单位、投运日期、
                    // 运检单位、设计单位、基建单位、运行单位、维护单位、设备属性
                    case "一次设备类型":
                        if (ycsblx.equals(value)) {
                            textViews.get(0).setText(value + "");
                        } else {
                            textViews.get(0).setText(value + "");
                            ycsblx = value + "";
//                      清空一次设备名称
                            editTexts.get(1).setText("");
                            ycsbmc = "";
                        }
                        break;
                    case "一次设备名称":
                        editTexts.get(1).setText(value + "");
                        ycsbmc = value + "";

                        String ycsblx = textViews.get(0).getText().toString();
                        String czmc = DemoActivity.instance.czcs.getCzmc();
                        String dwmc = DemoActivity.instance.czcs.getGldw();

                        if (value.contains("，")) {
                            String[] s1 = value.split("，");
                            if (s1.length > 0) {
                                value = s1[s1.length - 1];
                            }
                        }
                        String getdydj = setDydj(ycsblx, value, czmc, dwmc);
                        if (!textViews.get(1).getText().toString().equals(getdydj)){
//                            changeDydj(getdydj,textViews.get(1).getText().toString()+"");
                            textViews.get(1).setText(getdydj + "");
                            dydj = getdydj + "";
                        }
                        break;
                    case "电压等级":
                        if (value.equals("")){
                            textViews.get(1).setText("");
                        }else if (!textViews.get(1).getText().toString().equals(value)){
                            Long  dydj0 =  DemoActivity.instance.czcs.getCzzgdydj();
                            if(Long.parseLong(value) > dydj0){
                                ToastUtils.showLongToast(getActivity(), "电压等级的不能大于厂站最高电压等级");
                            }else{
//                                changeDydj(value,textViews.get(1).getText().toString()+"");
                                textViews.get(1).setText(value + "");
                                dydj = value + "";
                            }
                        }
                        break;
                    case "单位名称":
                        textViews.get(2).setText(value + "");
                        break;
                    case "调度单位":
                        textViews.get(3).setText(value + "");
                        break;
                    case "间隔类型":
                        if (!textViews.get(textViews.size()-1).getText().toString().equals(value)){
                            textViews.get(textViews.size()-1).setText(value + "");
                            //清空间隔名称
                            editTexts.get(editTexts.size()-1).setText("");
                        }
                        break;
                    case "间隔名称":
                        editTexts.get(editTexts.size()-1).setText(value + "");
                        break;
                    case "所属屏柜":
                        editTexts.get(3).setText(value + "");
                        break;
                    case "设计单位":
                        textViews.get(6).setText(value + "");
                        break;
                    case "基建单位":
                        textViews.get(7).setText(value + "");
                        break;
                    case "运行单位":
                        textViews.get(8).setText(value + "");
                        break;
                    case "维护单位":
                        textViews.get(9).setText(value + "");
                        break;
                    case "设备属性":
                        textViews.get(10).setText(value + "");
                        break;
                }
                break;
            default:
                break;
        }
    }

    /**
     * 选择完一次设备名称自动带出电压等级
     */
    public String setDydj(String ycsblx, String ycsbmc, String czmc, String dwmc) {
        IDaoUtil util = new DaoUtil(getActivity());
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

    public String getdydj(){
        String dydj="";
        dydj = textViews.get(1).getText().toString()+"";
        return dydj;
    }

    public void changeDydj(String Dydj,String value) {
        final int dydjnew = Integer.parseInt(Dydj);
        int dydjold = 0;
        if (value.equals("")){
        }else {
            dydjold = Integer.parseInt(value);
        }
        if (dydjold<220&&dydjnew>=220){
            List<Object> list = util.getBHXH(false, false, Fragment_Type_Base.instance.getzzcj(), Fragment_Type_Base.instance.getBhlb(), "czlx", dydjnew+"", "");
            boolean isbh = false;
            for (Object o : list) {
                String xh = ((BHSBXHB)o).getSbxh()+"";
                if (xh.equals(Fragment_Type_Base.instance.getBhxh())){
                    isbh = true;
                }else {
                }
            }
            if (list!=null&&list.size()>0&&!isbh){
//            if (true){
                AppUtils.showDialog(getActivity(), "电压等级变更，请重新选择装置型号", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { //cancel
                        PopupWindow pop = (PopupWindow) v.getTag();
                        pop.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { //ensure
                        PopupWindow pop = (PopupWindow) v.getTag();
                        pop.dismiss();
                        textViews.get(1).setText(dydjnew + "");
                        dydj = dydjnew + "";
                        ToastUtils.showToast(getActivity(),"电压等级变更，请重新选择装置型号");
                        Fragment_Type_Base.instance.setBhxh();
                        DemoActivity.instance.setChangexh();
                    }
                });
            }else {
                textViews.get(1).setText(dydjnew + "");
                dydj = dydjnew + "";
            }
        }else {
            textViews.get(1).setText(dydjnew + "");
            dydj = dydjnew + "";
        }

    }

    public void changeyc(String bhlb) {
        if (bhlb.equals("安全自动装置")||
                bhlb.equals("故障录波器")||
                bhlb.equals("保护故障信息系统子站")||
                bhlb.equals("故障测距装置")||
                bhlb.equals("其他")
                ){
            textViews.get(0).setText("其他");
            editTexts.get(1).setText("其他");
            editTexts.get(1).setEnabled(false);
            buttons.get(0).setVisibility(View.GONE);
            buttons.get(1).setVisibility(View.GONE);
        }else {
            textViews.get(0).setText("");
            editTexts.get(1).setText("");
            editTexts.get(1).setEnabled(true);
            buttons.get(0).setVisibility(View.VISIBLE);
            buttons.get(1).setVisibility(View.VISIBLE);
        }
    }

    public boolean CheckInset() {
        boolean checkinset_success = false;

        boolean time_before = false;

        //如果想比较日期则写成"yyyy-MM-dd"就可以了
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //将字符串形式的时间转化为Date类型的时间
        try {
            Date a = sdf.parse(textViews.get(4).getText().toString() + "");
            Date b = sdf.parse(Fragment_Type_Base.instance.ccrq_info + "");
            if (a.before(b)) {
                time_before = true;
            } else {

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Date类的一个方法，如果a早于b返回true，否则返回false
        if (llJgms.getVisibility()==View.VISIBLE&&textViews.get(textViews.size()-1).getText().toString().isEmpty()
                ||llJgms.getVisibility()==View.VISIBLE&&textViews.get(textViews.size()-1).getText().toString().equals("")){
            ToastUtils.showToast(getActivity(), "间隔类型不能为空");
        }else if (llJgms.getVisibility()==View.VISIBLE&&(editTexts.get(editTexts.size()-1).getText().toString().isEmpty()
                ||llJgms.getVisibility()==View.VISIBLE&&(editTexts.get(editTexts.size()-1).getText().toString().equals("")))){
            ToastUtils.showToast(getActivity(), "间隔名称不能为空");
        }else if (textViews.get(0).getText().toString().isEmpty() ||
                textViews.get(0).getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "一次设备类型不能为空");
        } else if (editTexts.get(1).getText().toString().isEmpty() ||
                editTexts.get(1).getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "一次设备名称不能为空");
        } else if (textViews.get(1).getText().toString().isEmpty() ||
                textViews.get(1).getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "电压等级不能为空");
        }else if (textViews.get(2).getText().toString().isEmpty() ||
                textViews.get(2).getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "单位名称不能为空");
        }

//        else if (textViews.get(3).getText().toString().isEmpty() ||
//                textViews.get(3).getText().toString().equals("")) {
//            ToastUtils.showToast(getActivity(), "调度单位不能为空");
//        } else if (textViews.get(4).getText().toString().isEmpty() ||
//                textViews.get(4).getText().toString().equals("")) {
//            ToastUtils.showToast(getActivity(), "投运日期不能为空");
//        }
        else if (time_before) {
            ToastUtils.showToast(getActivity(), "投运日期不能早于出厂日期");
        }else if (!isCheckOk()){

        }
//        else if (textViews.get(6).getText().toString().isEmpty() ||
//                textViews.get(6).getText().toString().equals("")) {
//            ToastUtils.showToast(getActivity(), "设计单位不能为空");
//        } else if (textViews.get(7).getText().toString().isEmpty() ||
//                textViews.get(7).getText().toString().equals("")) {
//            ToastUtils.showToast(getActivity(), "基建单位不能为空");
//        } else if (textViews.get(8).getText().toString().isEmpty() ||
//                textViews.get(8).getText().toString().equals("")) {
//            ToastUtils.showToast(getActivity(), "运行单位不能为空");
//        } else if (textViews.get(9).getText().toString().isEmpty() ||
//                textViews.get(9).getText().toString().equals("")) {
//            ToastUtils.showToast(getActivity(), "维护单位不能为空");
//        } else if (textViews.get(10).getText().toString().isEmpty() ||
//                textViews.get(10).getText().toString().equals("")) {
//            ToastUtils.showToast(getActivity(), "设备属性不能为空");
//        }
        else {
            checkinset_success = true;
            //电压等级默认保存
            Long DYDJ = Long.valueOf(textViews.get(1).getText() + "");
            DemoActivity.instance.bhsb.setDydj(DYDJ);
        }
        return checkinset_success;

    }

    //组合必填项校验
    public boolean isCheckOk() {
        boolean isok = true;
        if (saleVo.size()>0) {
            for (SaleAttributeVo vo : saleVo) {
                if (vo.getValue().equals("调度单位")&&(textViews.get(3).getText().toString()+"").equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写调度单位");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("所属屏柜")&&(editTexts.get(3).getText().toString()+"").equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写所属屏柜");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("投运日期")&&(textViews.get(4).getText().toString() + "").equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写投运日期");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("设计单位")&&(textViews.get(6).getText().toString()).equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写设计单位");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("基建单位")&&(textViews.get(7).getText().toString()).equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写基建单位");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("运行单位")&&(textViews.get(8).getText().toString()).equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写运行单位");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("维护单位")&&(textViews.get(9).getText().toString()).equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写维护单位");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("设备属性")&&(textViews.get(10).getText().toString()).equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写设备属性");
                    isok = false;
                    break;
                }
            }
        }
        return isok;
    }

    public void saveInset() {
        //一次设备类型、电压等级、单位名称、调度单位、投运日期、
        // 运检单位、设计单位、基建单位、运行单位、维护单位、设备属性,间隔类型
        BHPZ bhsb = DemoActivity.instance.bhsb;
        DemoActivity.instance.bhsb.setYcsblx(textViews.get(0).getText() + "");

        Long DYDJ = Long.valueOf(textViews.get(1).getText() + "");
        DemoActivity.instance.bhsb.setDydj(DYDJ);

        DemoActivity.instance.bhsb.setCzgldw(textViews.get(2).getText() + "");
        //保存上报单位
        DemoActivity.instance.bhsb.setSbdw(textViews.get(2).getText() + "");

        DemoActivity.instance.bhsb.setDddw(textViews.get(3).getText() + "");
        DemoActivity.instance.bhsb.setTyrq(textViews.get(4).getText() + "");
        DemoActivity.instance.bhsb.setDddw(textViews.get(3).getText() + "");
        DemoActivity.instance.bhsb.setTyrq(TimeUtil.formatString(textViews.get(4).getText().toString() + "") + "");

//        textViews.get(5).setVisibility(View.GONE);

        DemoActivity.instance.bhsb.setSjdw(textViews.get(6).getText() + "");
        DemoActivity.instance.bhsb.setJjdw(textViews.get(7).getText() + "");
        DemoActivity.instance.bhsb.setYxdw(textViews.get(8).getText() + "");
        DemoActivity.instance.bhsb.setWhdw(textViews.get(9).getText() + "");
        DemoActivity.instance.bhsb.setBhsx(textViews.get(10).getText() + "");
        if (llJgms.getVisibility()==View.VISIBLE){
            DemoActivity.instance.bhsb.setJglx(textViews.get(textViews.size()-1).getText() + "");
        }

        //所属间隔、一次设备名称、保护小室名称、所属屏柜、运维班组、检修班组、间隔名称
        ycsbmc = editTexts.get(1).getText() + "";
        if (ycsbmc.contains("，")) {
            ycsbmc.replaceAll("，",",");
        }
        DemoActivity.instance.bhsb.setYcsbmc(ycsbmc + "");
        DemoActivity.instance.bhsb.setSzpg(editTexts.get(3).getText() + "");//所属屏柜
        if (llJgms.getVisibility()==View.VISIBLE){
            DemoActivity.instance.bhsb.setJgmc(editTexts.get(editTexts.size()-1).getText() + "");
        }
    }

}
