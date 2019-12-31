package cn.com.sgcc.dev.view.fragment.fzbhsb;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
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
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.SelectActivity;
import cn.com.sgcc.dev.view.fragment.BaseFragment;
import cn.com.sgcc.dev.view.viewinterface.DateChooseListener;

import static android.app.Activity.RESULT_OK;

/**
 * <p>@description:运行基本信息</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/25
 */

public class Details3 extends BaseFragment {

    @BindView(R.id.tv_details3)
    TextView tv_details3;
    @BindViews(value = {R.id.tv_tb, R.id.tv_zzdydy, R.id.tv_sbzt,
            R.id.tv_sjcjfs, R.id.tv_ckfs, R.id.tv_sbzjfs, R.id.tv_tyrq,
            R.id.tv_tjyxsj, R.id.tv_scjxsj, R.id.tv_dycjghrq})
    List<TextView> textViews;
    @BindViews(value = {R.id.tv_dqjyzq, R.id.tv_dycjxh, R.id.tv_sztds, R.id.tv_mntds})
    List<EditText> editTexts;

    @BindViews(value = {R.id.iv_tb, R.id.iv_zzdydy, R.id.iv_sbzt, R.id.iv_sjcjfs, R.id.iv_ckfs,
            R.id.iv_sbzjfs, R.id.iv_tyrq, R.id.iv_tjyxsj, R.id.iv_scjxsj, R.id.iv_dycjghrq})
    List<ImageView> imgViews;
    @BindView(R.id.ll_hastyrq)
    LinearLayout llHastyrq;
    Unbinder unbinder;

    public String tb, tbms, tg = "0";
    public static Details3 instance;

    /**
     * 必填项校验需要
     */
    @BindViews(value = {R.id.tb, R.id.sbzt, R.id.dqjyzq, R.id.tyrq, R.id.scjxsj})
    List<TextView> textViews_key;
    @BindViews(value = {R.id.tv_tb, R.id.tv_sbzt, R.id.tv_dqjyzq, R.id.tv_tyrq, R.id.tv_scjxsj})
    List<TextView> textViews_more;
    private Map<String, TextView> map_key = new HashMap<>();
    private Map<String, TextView> map_more = new HashMap<>();
    private boolean isShow;

    @Override
    public int getLayoutId() {
        return R.layout.item_details3;
    }

    @Override
    public void initview() {
        instance = this;
    }

    @Override
    public void initEvevt() {

        editTexts.get(0).setFilters(new InputFilter[]{new InputFilter.LengthFilter(5) {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String str = dest.toString();
                if (str.contains(".")) {
                    int index = str.indexOf(".");
                    if (source.equals(".") || dend - index > 1) {
                        return "";
                    }
                }
                return super.filter(source, start, end, dest, dstart, dend);

            }
        }});

        /**
         * 加载必填项监听
         */
        Details1.instance.initOnFocusChangeListener(map_key, map_more, false, "运行基本信息");
    }

    @Override
    public void initData() {
        init();
        //套别模式显示数值
        Map<String, String> keyMap = new HashMap<>();
        Map<String, String> txtMap = AppUtils.setTxt(keyMap, "", false);
        tbms = txtMap.get("tbms") + "";
        if (tbms.equals("是")) {
            String zzlb = Details1.instance.getZzlb();//二次设备为辅助装置(辅助设备的装置类别)；
            String ycsblx = Details2.instance.getYcsblx();//一次设备；
            if (ycsblx.equals("线路") && (zzlb.equals("光电转换装置") || zzlb.equals("通信接口装置"))) {
                tg = "1";
            } else if (ycsblx.equals("线路") && zzlb.equals("合并单元")) {
                tg = "2";
            } else if (DemoActivity.instance.czcs != null && DemoActivity.instance.czcs.getBdzlx().equals("智能站") && zzlb.equals("交换机")) {
                tg = "3";
            } else if ((ycsblx.equals("变压器") || ycsblx.equals("母线")) && (zzlb.equals("合并单元") || zzlb.equals("智能终端") || zzlb.equals("合并单元智能终端集成"))) {
                tg = "4";
            } else {
                tg = "0";
            }
            //当一次设备是线路，二次设备为辅助装置(光电转换装置、通信接口装置)时
            //当一次设备是线路、二次设备为辅助装置(合并单元时）
            //智能变电站交换机
            //一次设备是变压器或母线，二次设备为辅助装置(合并单元、智能终端、合并单元智能终端集成)时
        } else {
            tg = "0";
        }
        setFirstData();

        initReceiver(getArguments().getBoolean("isEdit", false));

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
            if (saleAttributeNameVo.getName().equals("运行基本信息")) {
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

    private void getTg() {
        if (tbms.equals("是")) {
            String zzlb = Details1.instance.getZzlb();//二次设备为辅助装置(辅助设备的装置类别)；
            String ycsblx = Details2.instance.getYcsblx();//一次设备；
            if (ycsblx.equals("线路") && (zzlb.equals("光电转换装置") || zzlb.equals("光纤通信接口装置"))) {
                tg = "1";
            } else if (ycsblx.equals("线路") && zzlb.equals("合并单元")) {
                tg = "2";
            } else if (DemoActivity.instance.czcs != null && DemoActivity.instance.czcs.getBdzlx().equals("智能站") && zzlb.equals("交换机")) {
                tg = "3";
            } else if ((ycsblx.equals("变压器") || ycsblx.equals("母线")) && (zzlb.equals("合并单元") || zzlb.equals("智能终端") || zzlb.equals("合并单元智能终端集成"))) {
                tg = "4";
            } else {
                tg = "0";
            }
            //当一次设备是线路，二次设备为辅助装置(光电转换装置、通信接口装置)时
            //当一次设备是线路、二次设备为辅助装置(合并单元时）
            //智能变电站交换机
            //一次设备是变压器或母线，二次设备为辅助装置(合并单元、智能终端、合并单元智能终端集成)时
        } else {
            tg = "0";
        }
    }

    /**
     * 初始化数据
     */
    private void setFirstData() {
        FZBHSB fzbhsb = DemoActivity.instance.fzbhsb;

        if (fzbhsb != null) { //找到设备或者从出厂信息库带出
            if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C")) {
                tv_details3.setVisibility(View.GONE);
            } else {
                tv_details3.setVisibility(View.VISIBLE);
                if (DemoActivity.instance.rzgl != null &&
                        DemoActivity.instance.rzgl.getCZSJ() != null &&
                        !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                    tv_details3.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                } else {
                    tv_details3.setText("本条台账最后一次修改时间：");
                }
            }

            if (fzbhsb.getTb() != null) {
                textViews.get(0).setText(fzbhsb.getTb());
                tb = fzbhsb.getTb();
            }
            textViews.get(1).setText("");
            if (fzbhsb.getYxzt() != null) {
                textViews.get(2).setText(fzbhsb.getYxzt());
            }
            if (fzbhsb.getYxzt() != null && fzbhsb.getYxzt().equals("退运")) {
                llHastyrq.setVisibility(View.VISIBLE);
                if (fzbhsb.getTcyxsj() != null) {
                    textViews.get(6).setText(fzbhsb.getTcyxsj());
                }
            } else {
                llHastyrq.setVisibility(View.GONE);
            }
            textViews.get(3).setText("");
            textViews.get(4).setText("");

            if (fzbhsb.getDqjyzq() != 0) {
                editTexts.get(0).setText(fzbhsb.getDqjyzq() + "");
            }
            if (fzbhsb.getSbsx() != null) {
                textViews.get(5).setText(fzbhsb.getSbsx());
            }
            if (fzbhsb.getTjr() != null) {
                textViews.get(7).setText(fzbhsb.getTjr());
            }
            if (fzbhsb.getScdqjysj() != null) {
                textViews.get(8).setText(fzbhsb.getScdqjysj());
            }
            editTexts.get(1).setText("");
            textViews.get(9).setText("");
            editTexts.get(2).setText("");
            editTexts.get(3).setText("");
        } else {
            tv_details3.setVisibility(View.GONE);
            llHastyrq.setVisibility(View.GONE);
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
            if (DemoActivity.instance.isCancel) { //取消
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
                    tv_details3.setVisibility(View.VISIBLE);
                    tv_details3.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }

                //清除所有数据，重新加载数据
                setFirstData();
            } else {
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
                    tv_details3.setVisibility(View.VISIBLE);
                    tv_details3.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }
            }
        }
        String sbzt = textViews.get(2).getText().toString();
        if (!sbzt.equals("退运")) {
            map_key.remove("退运日期");
            map_more.remove("退运日期");
        }
        /**
         * 必填项校验设计
         */
        Details1.instance.checkBtx(isEdit, false, isShow, map_key, map_more, "运行基本信息");
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

    @OnClick({R.id.tv_tb, R.id.tv_zzdydy, R.id.tv_sbzt, R.id.tv_sjcjfs, R.id.tv_ckfs, R.id.tv_sbzjfs,
            R.id.tv_tyrq, R.id.tv_tjyxsj, R.id.tv_scjxsj, R.id.tv_dycjghrq, R.id.iv_tb, R.id.iv_zzdydy,
            R.id.iv_sbzt, R.id.iv_sjcjfs, R.id.iv_ckfs, R.id.iv_sbzjfs, R.id.iv_tyrq, R.id.iv_tjyxsj,
            R.id.iv_scjxsj, R.id.iv_dycjghrq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tb:
                getTg();
                setIntentData("辅助保护套别");
                break;
            case R.id.tv_zzdydy:
                break;
            case R.id.tv_sbzt:
                setIntentData("运行状态");
                break;
            case R.id.tv_sjcjfs:
                break;
            case R.id.tv_ckfs:
                break;
            case R.id.tv_sbzjfs:
                setIntentData("设备属性");
                break;
            case R.id.tv_tyrq:
            case R.id.iv_tyrq:
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        textViews.get(6).setText(date);

                        /**
                         * 必填项校验设计
                         */
                        Details1.instance.checkBtx(true, false, isShow, map_key, map_more, "运行基本信息");
                    }
                });
                break;
            case R.id.tv_scjxsj:
            case R.id.iv_scjxsj:
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        textViews.get(8).setText(date);

//                        /**
//                         * 必填项校验设计
//                         */
//                        Details1.instance.checkBtx(true, false, isShow, map_key, map_more, "运行基本信息");
                    }
                });
                break;
            case R.id.tv_dycjghrq:
            case R.id.iv_dycjghrq:
                AppUtils.showDatePickDialog(getActivity(), DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        textViews.get(9).setText(date);

//                        /**
//                         * 必填项校验设计
//                         */
//                        Details1.instance.checkBtx(true, false, isShow, map_key, map_more, "运行基本信息");
                    }
                });
                break;
            case R.id.tv_tjyxsj:
                break;
            case R.id.iv_tb:
                getTg();
                setIntentData("辅助保护套别");
                break;
            case R.id.iv_zzdydy:
                break;
            case R.id.iv_sbzt:
                setIntentData("运行状态");
                break;
            case R.id.iv_sjcjfs:
                break;
            case R.id.iv_ckfs:
                break;
            case R.id.iv_sbzjfs:
                setIntentData("设备属性");
                break;
            case R.id.iv_tjyxsj:
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
        if (type.equals("辅助保护套别")) {
            intent.putExtra("tg", tg);
        }
        intent.putExtra("conditions", (Serializable) map);
        startActivityForResult(intent, 0);
    }

    public void setTb() {
        //当是套别模式，一次设备类型或装置类别发生变化时，清空套别；
        if (textViews.get(0).getText() != null && !textViews.get(0).getText().equals("")) {
            // ToastUtils.showLongToast(getActivity(), "投运日期不能早于出厂日期");
            ToastUtils.showToast(getActivity(), "套别值被清空，请重新选择");
            textViews.get(0).setText("");
            tb = "";
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String type = data.getStringExtra("puttype");
            String value = data.getStringExtra("result");
            switch (type) {
                case "辅助保护套别":
                    textViews.get(0).setText(value);
                    tb = value;
                    break;
                case "设备属性":
                    textViews.get(5).setText(value);
                    break;
                case "运行状态":
                    if (value.equals("退运")) {
                        llHastyrq.setVisibility(View.VISIBLE);
                        if (!map_key.containsKey("退运日期"))
                            map_key.put("退运日期", textViews.get(2));
                        if (!map_more.containsKey("退运日期"))
                            map_more.put("退运日期", textViews.get(6));
                        if (textViews.get(6).getText().toString().equals("")) {
                            Details1.instance.checkBtx(true, false, isShow, map_key, map_more, "运行基本信息");
                        }
                    } else {
                        if (map_key.containsKey("退运日期"))
                            map_key.remove("退运日期");
                        if (map_more.containsKey("退运日期"))
                            map_more.remove("退运日期");
                        if (llHastyrq.getVisibility() == View.VISIBLE)
                            Details1.instance.checkBtx(true, false, isShow, map_key, map_more, "运行基本信息");
                        llHastyrq.setVisibility(View.GONE);
                    }
                    textViews.get(2).setText(value);
                    break;
            }
        }
    }

    /**
     * 统一保存校验方法
     */
    public boolean saveDetails3() {
        String tb = textViews.get(0).getText().toString();
        if (tb.equals("")) {
            DemoActivity.instance.fzbhsb.setTb(tb);
            if (map_key.containsKey("套别")) {
                ToastUtils.showLongToast(getActivity(), "套别不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setTb(tb);
        }
//        DemoActivity.instance.fzbhsb.setTb(tb);
        String sbzt = textViews.get(2).getText().toString();
        if (sbzt.equals("")) {
            DemoActivity.instance.fzbhsb.setYxzt(sbzt);
            DemoActivity.instance.fzbhsb.setSftcyx("否");
            if (map_key.containsKey("设备状态")) {
                ToastUtils.showLongToast(getActivity(), "设备状态不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setYxzt(sbzt);
            DemoActivity.instance.fzbhsb.setSftcyx("否");
        }
        String dqjyzq = editTexts.get(0).getText().toString();
        if (dqjyzq.equals("") || Float.parseFloat(dqjyzq) == 0) {
            DemoActivity.instance.fzbhsb.setDqjyzq(Float.parseFloat(dqjyzq));
            if (map_key.containsKey("定期检验周期")) {
                ToastUtils.showLongToast(getActivity(), "定期检验周期不能为空或0");
                return false;
            }
        } else {
            if (dqjyzq.charAt(0) == '.' || dqjyzq.charAt(dqjyzq.length() - 1) == '.') {
                ToastUtils.showToast(getActivity(), "定期检验周期格式不正确");
                return false;
            } else {
                DemoActivity.instance.fzbhsb.setDqjyzq(Float.parseFloat(dqjyzq));
            }
        }
        if (sbzt.equals("退运")) {
            DemoActivity.instance.fzbhsb.setSftcyx("是");
            String tyrq = textViews.get(6).getText().toString();
            if (tyrq.equals("")) {
                DemoActivity.instance.fzbhsb.setTcyxsj(tyrq);
                if (map_key.containsKey("退运日期")) {
                    ToastUtils.showLongToast(getActivity(), "退运日期不能为空");
                    return false;
                }
            } else {
                DemoActivity.instance.fzbhsb.setTcyxsj(tyrq);
            }
        }

        String scjxsj = textViews.get(8).getText().toString();
        if (scjxsj.equals("")) {
            DemoActivity.instance.fzbhsb.setScdqjysj(scjxsj);
            if (map_key.containsKey("上次检修时间")) {
                ToastUtils.showLongToast(getActivity(), "上次检修时间不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setScdqjysj(scjxsj);
        }
//        DemoActivity.instance.fzbhsb.setScdqjysj(scjxsj);
        return true;
    }
}
