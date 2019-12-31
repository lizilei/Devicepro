package cn.com.sgcc.dev.view.fragment.fzbhsb;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.SelectActivity;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Base;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Inset;
import cn.com.sgcc.dev.view.fragment.BaseFragment;

import static android.app.Activity.RESULT_OK;

/**
 * <p>@description:CT信息</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/25
 */

public class Details6 extends BaseFragment {

    @BindView(R.id.tv_details6)
    TextView tvDetails6;
    Unbinder unbinder;
    @BindView(R.id.tv_edbb)
    EditText tvEdbb;
    @BindView(R.id.tv_sjbb)
    EditText tvSjbb;
    @BindView(R.id.fragment_add_bt_zqj)
    Button fragmentAddBtZqj;
    @BindView(R.id.fragment_add_bt_ct)
    Button fragmentAddBtCt;
    private TextView tv;
    //额定变比、实际变比、准确级、CT二次额定电流
    @BindViews(value = {R.id.item_details_edbj,
            R.id.item_details_sjbj,
            R.id.item_details_zqj,
            R.id.item_details_ct
    })
    List<TextView> textViews;
    //额定变比、实际变比、准确级、CT二次额定电流
    @BindViews(value = {R.id.tv_edbb,
            R.id.tv_sjbb,
            R.id.item_details_zqj,
            R.id.item_details_ct
    })
    List<TextView> textViews_check_bt;
    //额定变比、实际变比、准确级、CT二次额定电流
    @BindViews(value = {R.id.tv_edbbs,
            R.id.tv_sjbbs,
            R.id.tv_zqj,
            R.id.tv_ct
    })
    List<TextView> textViews_check;

    private Map<String, TextView> map_key = new HashMap<>();
    private Map<String, TextView> map_more = new HashMap<>();
    private boolean isShow;

    public static Details6 instance = null;

    public List<SaleAttributeVo> saleVo;

    @Override
    public int getLayoutId() {
        return R.layout.item_details6;
    }

    @Override
    public void initview() {
        tv = (TextView) getActivity().findViewById(R.id.tv_details6);

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
        instance = this;
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
        //判断保护还是辅助（保护为true，辅助false）
        if (DemoActivity.instance.BHorFZ) {
            //额定变比、实际变比、准确级、CT二次额定电流
            BHPZ bhsb = DemoActivity.instance.bhsb;
            if (bhsb.getEdbb() != null) {
                tvEdbb.setText(bhsb.getEdbb() + "");
            }
            if (bhsb.getSjbb() != null) {
                tvSjbb.setText(bhsb.getSjbb() + "");
            }
            if (bhsb.getZqj() != null) {
                textViews.get(2).setText(bhsb.getZqj() + "");
            }
            if (bhsb.getCteceddl() != null) {
                textViews.get(3).setText(bhsb.getCteceddl() + "");//
            }
        } else {

        }

        saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getBhData()) {
            if (saleAttributeNameVo.getName().equals("CT信息")) {
                saleVo.addAll(saleAttributeNameVo.getSaleVo());
                break;
            }
        }

        boolean isEdit = getArguments().getBoolean("isEdit", false);
        initReceiver(isEdit);
        Fragment_Type_Base.instance.initOnFocusChangeListener(map_key,map_more,false,"CT信息");
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
            if (saleAttributeNameVo.getName().equals("CT信息")) {
                saleVo = saleAttributeNameVo.getSaleVo();
                break;
            }
        }

        for (SaleAttributeVo saleAttributeVo : saleVo) {
            if (saleAttributeVo.getValue().equals("CT二次额定电流")) {
                map_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "（A）："));
                map_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "（A）："));
                continue;
            }
            if (tmp_key.containsKey(saleAttributeVo.getValue() + "：")) {
                map_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + "："));
                map_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + "："));
            }
        }
    }

    @Override
    public void initReceiver(boolean isEdit) {
        if (isEdit) {
            for (TextView textView : textViews) {
                textView.setBackgroundResource(R.drawable.device_detials_bg);
                textView.setEnabled(true);
            }
            tvEdbb.setBackgroundResource(R.drawable.device_detials_bg);
            tvEdbb.setEnabled(true);
            tvSjbb.setBackgroundResource(R.drawable.device_detials_bg);
            tvSjbb.setEnabled(true);
            fragmentAddBtCt.setVisibility(View.VISIBLE);
            fragmentAddBtZqj.setVisibility(View.VISIBLE);

//            for (TextView textView : textViews_check) {
//                Fragment_Type_Inset.instance.setDrawableLeft(textView,false);
//            }

        } else {
            if (DemoActivity.instance.isCancel) {//取消
                for (TextView textView : textViews) {
                    textView.setBackground(null);
                    textView.setEnabled(false);
                    textView.setText("");
                }
                tvEdbb.setBackground(null);
                tvEdbb.setEnabled(false);
                tvSjbb.setBackground(null);
                tvSjbb.setEnabled(false);
                fragmentAddBtCt.setVisibility(View.GONE);
                fragmentAddBtZqj.setVisibility(View.GONE);

                if (DemoActivity.instance.rzgl != null &&
                        DemoActivity.instance.rzgl.getCZSJ() != null &&
                        !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                    tv.setVisibility(View.VISIBLE);
                    tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }
//                initData();
            } else {
                for (TextView textView : textViews) {
                    textView.setBackground(null);
                    textView.setEnabled(false);
                }
                tvEdbb.setBackground(null);
                tvEdbb.setEnabled(false);
                tvSjbb.setBackground(null);
                tvSjbb.setEnabled(false);
                fragmentAddBtCt.setVisibility(View.GONE);
                fragmentAddBtZqj.setVisibility(View.GONE);

                if (DemoActivity.instance.rzgl != null &&
                        DemoActivity.instance.rzgl.getCZSJ() != null &&
                        !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                    tv.setVisibility(View.VISIBLE);
                    tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }
            }

//            for (TextView textView : textViews_check) {
//                Fragment_Type_Inset.instance.setDrawableLeft(textView,false);
//            }

        }
        /**
         * 必填项校验设计
         */
        Fragment_Type_Base.instance.checkBtx(isEdit, false, isShow, map_key, map_more, "CT信息");
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


    /**
     * @param type 类型
     */
    private void setIntentData(String type) {
        Map<String, Object> map = new HashMap<>();
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("type", type);
        map.put("number", "6");
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
                    //额定变比、实际变比、准确级、CT二次额定电流
                    case "额定变比":
                        textViews.get(0).setText(value + "");
                        break;
                    case "实际变比":
                        textViews.get(1).setText(value + "");
                        break;
                    case "准确级":
                        textViews.get(2).setText(value + "");
                        break;
                    case "CT二次额定电流":
                        textViews.get(3).setText(value + "");//有疑问
                        break;
                }
                break;
            default:
                break;
        }
    }

    public boolean checkCT(){
        boolean isCtOk = true;
        if (saleVo.size()>0){
            for (SaleAttributeVo vo : saleVo) {
                if (vo.getValue().equals("额定变比")&&(tvEdbb.getText().toString()+"").equals("")){
                    ToastUtils.showToast(getActivity(), "请填写额定变比");
                    isCtOk = false;
                    break;
                }
                if (vo.getValue().equals("实际变比")&&(tvSjbb.getText().toString()+"").equals("")){
                    ToastUtils.showToast(getActivity(), "请填写实际变比");
                    isCtOk = false;
                    break;
                }
                if (vo.getValue().equals("准确级")&&(textViews.get(2).getText().toString()+"").equals("")){
                    ToastUtils.showToast(getActivity(), "请选择准确级");
                    isCtOk = false;
                    break;
                }
                if (vo.getValue().equals("CT二次额定电流")&&(textViews.get(3).getText().toString()+"").equals("")){
                    ToastUtils.showToast(getActivity(), "请选择CT二次额定电流");
                    isCtOk = false;
                    break;
                }
            }
        }
        return isCtOk;
    }

    public void saveCT() {
        //额定变比、实际变比、准确级、CT二次额定电流
        BHPZ bhsb = DemoActivity.instance.bhsb;
        DemoActivity.instance.bhsb.setEdbb(tvEdbb.getText() + "");
        DemoActivity.instance.bhsb.setSjbb(tvSjbb.getText() + "");
        DemoActivity.instance.bhsb.setZqj(textViews.get(2).getText() + "");
        DemoActivity.instance.bhsb.setCteceddl(textViews.get(3).getText() + "");
    }

    @OnClick({R.id.fragment_add_bt_zqj, R.id.fragment_add_bt_ct})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_add_bt_zqj:
                setIntentData("准确级");
                break;
            case R.id.fragment_add_bt_ct:
                setIntentData("CT二次额定电流");
                break;
        }
    }
}
