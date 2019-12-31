package cn.com.sgcc.dev.view.fragment.BHSB;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import cn.com.sgcc.dev.adapter.TypeBaseAdapter;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.utils.WrapContentListView;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.SelectActivity;
import cn.com.sgcc.dev.view.fragment.BaseFragment;
import cn.com.sgcc.dev.view.viewinterface.DateChooseListener;

import static android.app.Activity.RESULT_OK;


/**
 * <p>@description:</p>
 * 运行 fragment
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */

public class Fragment_Type_Run extends BaseFragment {

    Unbinder unbinder;

    //套别、数据采集方式、出口方式、退运日期、跳闸关系、上次检修时间、电源插件更换日期、名称属性、设备状态
    @BindViews(value = {R.id.tv_one_device_name_right,
            R.id.tv_one_device_four_right,
            R.id.tv_one_device_five_right,
            R.id.tv_one_device_eight_right,
            R.id.tv_one_device_nine_right,
            R.id.tv_one_device_twelve_right,
            R.id.tv_one_device_fourteen_right,
            R.id.tv_one_device_ten_right,
            R.id.tv_one_device_three_right,
            R.id.tv_one_device_seven_right,

    })
    List<TextView> textViews;
    //套别、设备状态、数据采集方式、出口方式、定期检验周期、退运日期、跳闸关系、
    // 名称属性、上次检修时间、电源插件型号、电源插件更换日期、数字通道数、模拟通道数
    @BindViews(value = {R.id.tv_one_device_name_left,
            R.id.tv_one_device_three_left,
            R.id.tv_one_device_four_left,
            R.id.tv_one_device_five_left,
            R.id.tv_one_device_six_left,
            R.id.tv_one_device_eight_left,
            R.id.tv_one_device_nine_left,
            R.id.tv_one_device_ten_left,
            R.id.tv_one_device_twelve_left,
            R.id.tv_one_device_thirteen_left,
            R.id.tv_one_device_fourteen_left,
            R.id.tv_one_device_fifteen_left,
            R.id.tv_one_device_sixteen_left,

    })
    List<TextView> textViews_check;
    //套别、设备状态，数据采集方式、出口方式、定期检验周期、退运日期、跳闸关系、
    // 名称属性、上次检修时间、电源插件型号、电源插件更换日期、数字通道数、模拟通道数
    @BindViews(value = {R.id.tv_one_device_name_right,
            R.id.tv_one_device_three_right,
            R.id.tv_one_device_four_right,
            R.id.tv_one_device_five_right,
            R.id.et_one_device_six_right,
            R.id.tv_one_device_eight_right,
            R.id.et_one_device_nine_right,
            R.id.tv_one_device_ten_right,
            R.id.tv_one_device_twelve_right,
            R.id.et_one_device_thirteen_right,
            R.id.tv_one_device_fourteen_right,
            R.id.et_one_device_fifteen_right,
            R.id.et_one_device_sixteen_right,

    })
    List<TextView> textViews_check_bt;
    //装置电源电压、设备状态、定期检验周期、设备增加方式、名称属性、电压插件型号、数字通道数、模拟通道数
    @BindViews(value = {R.id.et_one_device_two_right,
            R.id.et_one_device_three_right,
            R.id.et_one_device_six_right,
            R.id.et_one_device_seven_right,
            R.id.et_one_device_ten_right,
            R.id.et_one_device_thirteen_right,
            R.id.et_one_device_fifteen_right,
            R.id.et_one_device_sixteen_right,
            R.id.et_one_device_nine_right,
    })
    List<EditText> editTexts;
//    统计运行时间（单选）

    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    @BindView(R.id.cb_end_true)
    CheckBox cbEndTrue;

    @BindViews(value = {
            R.id.fragment_add_bt_one
            , R.id.fragment_add_bt_three
            , R.id.fragment_add_bt_four
            , R.id.fragment_add_bt_five
            , R.id.fragment_add_bt_seven
            , R.id.fragment_add_bt_eight
            , R.id.fragment_add_bt_nine
            , R.id.fragment_add_bt_ten
            , R.id.fragment_add_bt_eleven
            , R.id.fragment_add_bt_twelve
            , R.id.fragment_add_bt_thirteen
            , R.id.fragment_add_bt_fourteen
            , R.id.fragment_add_bt_fifteen
            , R.id.fragment_add_bt_sixteen
    })
    List<Button> buttons;

    @BindView(R.id.rl_two)
    RelativeLayout rlTwo;
    @BindView(R.id.rl_ten)
    RelativeLayout rlTen;
    @BindView(R.id.rl_seven)
    RelativeLayout rlSeven;
    @BindView(R.id.rl_three)
    RelativeLayout rlThree;
    @BindView(R.id.rl_eight)
    RelativeLayout rlEight;
    private TypeBaseAdapter adapter;
    private WrapContentListView list_one;
    private List<DeviceDetailsNameItem> list_one_data;
    public static Fragment_Type_Run instance = null;
    public List<SaleAttributeVo> saleVo;

    private Map<String, TextView> map_key = new HashMap<>();
    private Map<String, TextView> map_more = new HashMap<>();
    private boolean isShow;

//    装置名称、是否六统一、六统一标准版本、制造厂家、装置类别、装置型号、
//    模块名称、软件版本、校验码、软件版本生成时间、出厂日期、装置类别细化\设备类型、
//    设备功能配置（若是安自装置才显示）、故障录波器类型、测距形式、是否接入调度主站、
//    是否就地化设备、



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_type_run;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    public void initview() {

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

        //装置电源电压隐藏
        rlTwo.setVisibility(View.GONE);
        //设备增加方式隐藏
        rlSeven.setVisibility(View.GONE);

        setCancelBtx();
    }


    @Override
    public void initEvevt() {
//        boolean isEdit = getArguments().getBoolean("isEdit", false);
//        initReceiver(isEdit);
    }

    @Override
    public void initData() {
        //必填项初始化
        init();
//        新增或者编辑
        if (Fragment_Type_Base.instance.isC) {

        } else {

            BHPZ bhsb = DemoActivity.instance.bhsb;
//        /套别0、数据采集方式1、出口方式2、退运日期3、跳闸关系4、上次检修时间5、电源插件更换日期6、
// 名称属性7（选择）、设备状态8、
            if (bhsb.getYxzt() != null) {
                textViews.get(8).setText(bhsb.getYxzt() + "");
                if (bhsb.getYxzt().equals("退运")) {
                    rlEight.setVisibility(View.VISIBLE);
                } else {
                    rlEight.setVisibility(View.GONE);
                }
            }
            if (bhsb.getSftjyxsj()!=null){
                if (bhsb.getSftjyxsj().equals("否")){
                    cbEndTrue.setChecked(false);
                }else {
                    cbEndTrue.setChecked(true);
                }
            }

            if (bhsb.getTb() != null) {
                textViews.get(0).setText(bhsb.getTb() + "");
            }
            if (bhsb.getSjcjfs() != null) {
                textViews.get(1).setText(bhsb.getSjcjfs() + "");
            }
            if (bhsb.getCkfs() != null) {
                textViews.get(2).setText(bhsb.getCkfs() + "");
            }
            if (bhsb.getTcyxsj() != null) {
                textViews.get(3).setText(TimeUtil.formatString2(bhsb.getTcyxsj() + "")+"");
            }
            if (bhsb.getKgbh() != null) {
                editTexts.get(8).setText(bhsb.getKgbh() + "");
            }
            if (bhsb.getScdqjysj() != null) {
                textViews.get(5).setText(TimeUtil.formatString2(bhsb.getScdqjysj()+"") + "");
            }
            if (bhsb.getDycjghrq() != null) {
                textViews.get(6).setText(TimeUtil.formatString2(bhsb.getDycjghrq() + "")+"");
            }
            if (bhsb.getBhmcsx() != null) {
                textViews.get(7).setText(bhsb.getBhmcsx() + "");
            }

            //装置电源电压、设备状态、定期检验周期、设备增加方式、名称属性、电压插件型号、数字通道数、模拟通道数
            //定期检验周期、电压插件型号、数字通道数、模拟通道数
//        editTexts.get(0).setText(bhsb.getDydj() + "");//
//        editTexts.get(1).setText(bhsb.getSb() + "");//
            String dqjyzq = bhsb.getDqjyzq() + "";
            if (!dqjyzq.equals("null")&&!dqjyzq.equals("0.0")) {
                editTexts.get(2).setText(bhsb.getDqjyzq() + "");
            }
            editTexts.get(2).setKeyListener(DigitsKeyListener.getInstance("0123456789."));
            editTexts.get(2).setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
            editTexts.get(2).setFilters(new InputFilter[]{new InputFilter.LengthFilter(5) {
                @Override
                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                    String dqjyzq = dest.toString()+"";
                    //保存逻辑

                    if (dqjyzq.length()>=5){
                        return "";
                    }else if (dqjyzq.contains(".")){
                        int index = dqjyzq.indexOf(".");
                        if (dend-index >1){
                            return "";
                        }else  if (source.equals(".")) {
                            return "";
                        }
                    }
                    return null;
                }
            }});
//        editTexts.get(3).setText("");//
//        editTexts.get(4).setText("");//
            if (bhsb.getDycjxh() != null) {
                editTexts.get(5).setText(bhsb.getDycjxh() + "");//
            }
            if (bhsb.getSztds() != null) {
                editTexts.get(6).setText(bhsb.getSztds() + "");//
            }
            editTexts.get(6).setKeyListener(DigitsKeyListener.getInstance("0123456789"));
            editTexts.get(6).setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
            if (bhsb.getMntds() != null) {
                editTexts.get(7).setText(bhsb.getMntds() + "");//
            }
            editTexts.get(7).setKeyListener(DigitsKeyListener.getInstance("0123456789"));
            editTexts.get(7).setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        }

        saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getBhData()) {
            if (saleAttributeNameVo.getName().equals("运行基本信息")) {
                saleVo.addAll(saleAttributeNameVo.getSaleVo());
                break;
            }
        }

        boolean isEdit = getArguments().getBoolean("isEdit", false);
        initReceiver(isEdit);

        /**
         * 加载必填项监听
         */
        Fragment_Type_Base.instance.initOnFocusChangeListener(map_key,map_more,false,"运行基本信息");
    }

    private void init() {
        Map<String, TextView> tmp_key = new HashMap<>();
        Map<String, TextView> tmp_value = new HashMap<>();
        for (int i = 0; i < textViews_check.size(); i++) {
            tmp_key.put(textViews_check.get(i).getText().toString(), textViews_check.get(i));
            tmp_value.put(textViews_check.get(i).getText().toString(), textViews_check_bt.get(i));
        }

        List<SaleAttributeVo> saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getBhData()) {
            if (saleAttributeNameVo.getName().equals("运行基本信息")) {
                saleVo = saleAttributeNameVo.getSaleVo();
                break;
            }
        }

        for (SaleAttributeVo saleAttributeVo : saleVo) {
            if (tmp_key.containsKey(saleAttributeVo.getValue() + ":")) {
                map_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + ":"));
                map_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + ":"));
            }
        }
    }

    //取消必填项
    public void setCancelBtx(){
//        上次检修时间非必填
        textViews.get(5).setHint("");
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
                textView.setEnabled(false);
            }
            for (Button button : buttons) {
                button.setVisibility(View.VISIBLE);
            }
            cbEndTrue.setEnabled(true);

//            for (TextView textView : textViews_check) {
//                setDrawableLeft(textView,false);
//            }

        } else {
            cbEndTrue.setEnabled(false);
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
        buttons.get(buttons.size()-1).setVisibility(View.GONE);
        buttons.get(buttons.size()-1).setVisibility(View.GONE);
        buttons.get(buttons.size()-2).setVisibility(View.GONE);
        buttons.get(buttons.size()-4).setVisibility(View.GONE);
        buttons.get(buttons.size()-6).setVisibility(View.GONE);

        /**
         * 必填项校验设计
         */
        Fragment_Type_Base.instance.checkBtx(isEdit, false, isShow, map_key, map_more, "运行基本信息");
    }

    private void setDrawableLeft(TextView TV,boolean ischeck){
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

    @OnClick({R.id.fragment_add_bt_one, R.id.fragment_add_bt_three, R.id.fragment_add_bt_four, R.id.fragment_add_bt_five, R.id.fragment_add_bt_seven, R.id.fragment_add_bt_eight, R.id.fragment_add_bt_nine, R.id.fragment_add_bt_ten, R.id.cb_end_true, R.id.fragment_add_bt_eleven, R.id.fragment_add_bt_twelve, R.id.fragment_add_bt_thirteen, R.id.fragment_add_bt_fourteen, R.id.fragment_add_bt_fifteen, R.id.fragment_add_bt_sixteen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_add_bt_one:
                setIntentData("保护套别");
                break;
            case R.id.fragment_add_bt_three:
//                setIntentData("设备状态");
                setIntentData("运行状态");
                break;
            case R.id.fragment_add_bt_four:
                setIntentData("数据采集方式");
                break;
            case R.id.fragment_add_bt_five:
                setIntentData("出口方式");
                break;
//            case R.id.fragment_add_bt_seven:
////                setIntentData("设备增加方式");
////                设备增加方式取值为设备属性
//                setIntentData("设备属性");
//                break;
            case R.id.fragment_add_bt_eight:
//                setIntentData("退运日期");
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        textViews.get(3).setText(date);
                    }
                });
                break;
            case R.id.fragment_add_bt_nine:
                setIntentData("跳闸关系");
                break;
            case R.id.fragment_add_bt_ten:
                setIntentData("名称属性");
//                setIntentData("设备属性");
                break;
            case R.id.cb_end_true:
//                统计运行时间
                break;
            case R.id.fragment_add_bt_eleven:
//                统计运行时间
                break;
            case R.id.fragment_add_bt_twelve:
//                setIntentData("上次检修时间");
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        textViews.get(5).setText(date);
                    }
                });
                break;
//            电源插件型号输入
//            case R.id.fragment_add_bt_thirteen:
//                setIntentData("电源插件型号");
//                break;
            case R.id.fragment_add_bt_fourteen:
//                setIntentData("电源插件更换日期");
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        textViews.get(6).setText(date);
                    }
                });
                break;
//            case R.id.fragment_add_bt_fifteen:
//                setIntentData("数字通道数");
//                break;
//            case R.id.fragment_add_bt_sixteen:
//                setIntentData("模拟通道数");
//                break;
        }
    }

    /**
     * @param type 类型
     */
    private void setIntentData(String type) {
        Map<String, Object> map = new HashMap<>();
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("type", type);
        map.put("number", "3");
//        if (type.equals("")) {
//            map.put("", textViews.get(0).getText().toString());
//        }
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
                    case "保护套别":
                        textViews.get(0).setText(value + "");
                        DemoActivity.instance.bhsb.setTb(value + "");
                        break;
                    case "运行状态":
//                    case "运行状态改为设备状态":
                        textViews.get(8).setText(value + "");
                        if (value.equals("退运")) {
                            rlEight.setVisibility(View.VISIBLE);
                            textViews.get(3).setBackgroundResource(R.drawable.device_detials_bg);
                            for (SaleAttributeVo vo : saleVo) {
                                if (vo.getValue().equals("退运日期")&&textViews.get(8).getText().toString().equals("退运")&&(textViews.get(3).getText().toString() + "").equals("")){
                                    textViews.get(3).setBackgroundResource(R.drawable.device_detials_bg2);
                                    DemoActivity.instance.check("运行基本信息",true);
                                    break;
                                }
                            }
                        } else {
                            rlEight.setVisibility(View.GONE);
                        }
//                        textViews.get(8).setText(value + "");
                        break;
                    case "设备状态":
                        textViews.get(8).setText(value + "");
                        break;
                    case "数据采集方式":
                        textViews.get(1).setText(value + "");
                        break;
                    case "出口方式":
                        textViews.get(2).setText(value + "");
                        break;
//                    case "设备属性":
////                        设备增加方式
//                        textViews.get(9).setText(value + "");
//                        break;
                    case "跳闸关系":
                        editTexts.get(8).setText(value + "");
                        break;
                    case "名称属性":
                        textViews.get(7).setText(value + "");
//                        editTexts.get(4).setText(value + "");
                        break;
                    case "电源插件型号":
                        editTexts.get(5).setText(value + "");
                        break;
                    case "数字通道数":
                        editTexts.get(6).setText(value + "");
                        break;
                    case "模拟通道数":
                        editTexts.get(7).setText(value + "");
                        break;
                }
                break;
            default:
                break;
        }
    }

    public boolean CheckRun() {
        boolean CheckRun_success = false;


        boolean dqjyzq_success = false;
        String dqjyzq = editTexts.get(2).getText().toString()+"";
            if (dqjyzq.contains(".")){
                int index = dqjyzq.indexOf(".");
                String ends = dqjyzq.substring(index)+"";
                if (index ==dqjyzq.length()-1){
                    dqjyzq_success = true;
                }else if (index==0){
                    dqjyzq_success = true;
                }else if (ends.equals("..")){
                    dqjyzq_success = true;
                }
            }

        if (editTexts.get(2).getText().toString().isEmpty() ||
                editTexts.get(2).getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "定期检验周期不能为空");
        } else if (editTexts.get(2).getText().toString().isEmpty() ||
                editTexts.get(2).getText().toString().equals("0")) {
            ToastUtils.showToast(getActivity(), "定期检验周期不能为0");
        }
        else if (dqjyzq_success) {
            ToastUtils.showToast(getActivity(), "定期检验周期格式不正确");
        }
        else if (!textViews.get(8).getText().toString().isEmpty() &&
                !textViews.get(8).getText().toString().equals("") &&
                textViews.get(8).getText().toString().equals("退运") &&
                textViews.get(3).getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "退运时间不能为空");
        }
        else if (textViews.get(8).getText().toString().isEmpty() ||
                textViews.get(8).getText().toString().equals("")) {
            ToastUtils.showToast(getActivity(), "设备状态不能为空");
        }else if (!isCheckOk()){

        }
//        else if (textViews.get(5).getText().toString().isEmpty() ||
//                textViews.get(5).getText().toString().equals("")) {
//            ToastUtils.showToast(getActivity(), "上次检修时间不能为空");
//        }
        else {
            CheckRun_success = true;
        }
        return CheckRun_success;

    }


    //组合必填项校验
    public boolean isCheckOk() {
        boolean isok = true;
        if (saleVo.size()>0) {
            for (SaleAttributeVo vo : saleVo) {
                if (vo.getValue().equals("套别")&&(textViews.get(0).getText().toString()+"").equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写套别");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("数据采集方式")&&(textViews.get(1).getText().toString()+"").equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写数据采集方式");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("出口方式")&&(textViews.get(2).getText().toString() + "").equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写出口方式");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("跳闸关系")&&(editTexts.get(8).getText().toString()).equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写跳闸关系");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("名称属性")&&(textViews.get(7).getText().toString()).equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写名称属性");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("上次检修时间")&&(textViews.get(5).getText().toString()).equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写上次检修时间");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("电源插件型号")&&(editTexts.get(5).getText().toString()).equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写电压插件型号");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("电源插件更换日期")&&(textViews.get(6).getText().toString()).equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写电源插件更换日期");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("数字通道数")&&(editTexts.get(6).getText().toString()).equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写数字通道数");
                    isok = false;
                    break;
                }
                else if (vo.getValue().equals("模拟通道数")&&(editTexts.get(7).getText().toString()).equals("")){
                    ToastUtils.showToast(getActivity(), "请选择填写模拟通道数");
                    isok = false;
                    break;
                }
            }
        }
        return isok;
    }

    public void saveRun() {
        BHPZ bhsb = DemoActivity.instance.bhsb;
//        /套别0、数据采集方式1、出口方式2、退运日期3、跳闸关系4、上次检修时间5、电源插件更换日期6、
// 名称属性7（选择）、设备状态8、
        DemoActivity.instance.bhsb.setTb(textViews.get(0).getText() + "");
        DemoActivity.instance.bhsb.setSjcjfs(textViews.get(1).getText() + "");
        DemoActivity.instance.bhsb.setCkfs(textViews.get(2).getText() + "");
        DemoActivity.instance.bhsb.setTcyxsj(TimeUtil.formatString(textViews.get(3).getText().toString() + "")+"");
        String kgbh = editTexts.get(8).getText() + "";
        if (kgbh.contains("，")) {
            kgbh.replaceAll("，",",");
        }
        DemoActivity.instance.bhsb.setKgbh(kgbh + "");
        DemoActivity.instance.bhsb.setScdqjysj(TimeUtil.formatString(textViews.get(5).getText() + "")+"");
        DemoActivity.instance.bhsb.setDycjghrq(TimeUtil.formatString(textViews.get(6).getText() + "")+"");
        DemoActivity.instance.bhsb.setBhmcsx(textViews.get(7).getText() + "");
        DemoActivity.instance.bhsb.setYxzt(textViews.get(8).getText() + "");

        if (textViews.get(8).toString()!=null&&textViews.get(8).toString().equals("退运")){
            DemoActivity.instance.bhsb.setSftcyx("是");
        }else {
            DemoActivity.instance.bhsb.setSftcyx("否");
        }

        //装置电源电压、设备状态、定期检验周期、设备增加方式、名称属性、电压插件型号、数字通道数、模拟通道数
        //定期检验周期、电压插件型号、数字通道数、模拟通道数
        DemoActivity.instance.bhsb.setDqjyzq(Float.parseFloat(editTexts.get(2).getText() + ""));
        DemoActivity.instance.bhsb.setDycjxh(editTexts.get(5).getText() + "");
        DemoActivity.instance.bhsb.setSztds(editTexts.get(6).getText() + "");
        DemoActivity.instance.bhsb.setMntds(editTexts.get(7).getText() + "");


        if (cbEndTrue.isChecked()){
            DemoActivity.instance.bhsb.setSftjyxsj("是");
        }else {
            DemoActivity.instance.bhsb.setSftjyxsj("否");
        }
    }

}
