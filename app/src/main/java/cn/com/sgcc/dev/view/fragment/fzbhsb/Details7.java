package cn.com.sgcc.dev.view.fragment.fzbhsb;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.SelectActivity;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Base;
import cn.com.sgcc.dev.view.fragment.BaseFragment;

import static android.app.Activity.RESULT_OK;

/**
 * <p>@description:资产信息</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/25
 */

public class Details7 extends BaseFragment {
    @BindViews(value = {R.id.tv_zcbh})
    List<EditText> editTexts;
    @BindViews(value = {R.id.tv_zcxz, R.id.tv_zcdw})
    List<TextView> textViews;
    @BindViews(value = {R.id.iv_zcxz, R.id.iv_zcdw})
    List<ImageView> imgViews;
    @BindView(R.id.tv_details7)
    TextView tv_details7;
    Unbinder unbinder;
    public static Details7 instance;

    /**
     * 必填项校验需要
     */
    @BindViews(value = {R.id.zcbh, R.id.zcxz, R.id.zcdw})
    List<TextView> textViews_key;
    @BindViews(value = {R.id.tv_zcbh, R.id.tv_zcxz, R.id.tv_zcdw})
    List<TextView> textViews_more;
    private Map<String, TextView> map_key = new HashMap<>();
    private Map<String, TextView> map_more = new HashMap<>();
    private boolean isShow;

    @Override
    public int getLayoutId() {
        return R.layout.item_details7;
    }

    @Override
    public void initview() {
        instance = this;
    }

    @Override
    public void initEvevt() {
        /**
         * 加载必填项监听
         */
        if (DemoActivity.instance.BHorFZ){
            Fragment_Type_Base.instance.initOnFocusChangeListener(map_key,map_more,false,"资产信息");
        }else {
            Details1.instance.initOnFocusChangeListener(map_key,map_more,false,"资产信息");
        }
    }

    @Override
    public void initData() {
        init();
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
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.BHorFZ ?
                DemoActivity.instance.jiaoYanData.getBhData() : DemoActivity.instance.jiaoYanData.getFzData()) {
            if (saleAttributeNameVo.getName().equals("资产信息")) {
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

    /**
     * 初始化数据
     */
    private void setFirstData() {
        //判断保护还是辅助（保护为true，辅助false）
        if (DemoActivity.instance.BHorFZ) {
            BHPZ bhpz = DemoActivity.instance.bhsb;
            if (bhpz != null) { //找到设备或者从出厂信息库带出
                if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C")) {
                    tv_details7.setVisibility(View.GONE);
                } else {
                    tv_details7.setVisibility(View.VISIBLE);
                    if (DemoActivity.instance.rzgl != null) {
                        tv_details7.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                    } else {
                        tv_details7.setText("本条台账最后一次修改时间：");
                    }
                }

                if (bhpz.getZcbh() != null) {
                    editTexts.get(0).setText(bhpz.getZcbh());
                }
                if (bhpz.getZcxz() != null) {
                    textViews.get(0).setText(bhpz.getZcxz());
                }
                if (bhpz.getZcdw() != null) {
                    textViews.get(1).setText(bhpz.getZcdw());
                }
            } else {
                tv_details7.setVisibility(View.GONE);
            }
        } else {
            FZBHSB fzbhsb = DemoActivity.instance.fzbhsb;
            if (fzbhsb != null) { //找到设备或者从出厂信息库带出
                if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C")) {
                    tv_details7.setVisibility(View.GONE);
                } else {
                    tv_details7.setVisibility(View.VISIBLE);
                    if (DemoActivity.instance.rzgl != null) {
                        tv_details7.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                    } else {
                        tv_details7.setText("本条台账最后一次修改时间：");
                    }
                }

                if (fzbhsb.getZcbh() != null) {
                    editTexts.get(0).setText(fzbhsb.getZcbh());
                }
                if (fzbhsb.getZcxz() != null) {
                    textViews.get(0).setText(fzbhsb.getZcxz());
                }
                if (fzbhsb.getZcdw() != null) {
                    textViews.get(1).setText(fzbhsb.getZcdw());
                }
            } else {
                tv_details7.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void initReceiver(boolean isEdit) {
        isShow=false;
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

                if (DemoActivity.instance.rzgl != null &&
                        DemoActivity.instance.rzgl.getCZSJ() != null &&
                        !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                    tv_details7.setVisibility(View.VISIBLE);
                    tv_details7.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }
                //清除所有数据，重新初始化
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
                    tv_details7.setVisibility(View.VISIBLE);
                    tv_details7.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                }
            }
        }
        /**
         * 必填项校验设计
         */
        if (DemoActivity.instance.BHorFZ){
            Fragment_Type_Base.instance.checkBtx(isEdit, false, isShow, map_key, map_more, "资产信息");
        }else {
            Details1.instance.checkBtx(isEdit, false, isShow, map_key, map_more, "资产信息");
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

    @OnClick({R.id.tv_zcbh, R.id.tv_zcxz, R.id.tv_zcdw, R.id.iv_zcxz, R.id.iv_zcdw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_zcbh:
                break;
            case R.id.tv_zcxz:
                setIntentData("资产性质");
                break;
            case R.id.tv_zcdw:
                setIntentData("资产单位");
                break;
            case R.id.iv_zcxz:
                setIntentData("资产性质");
                break;
            case R.id.iv_zcdw:
                setIntentData("资产单位");
                break;
        }
    }

    /**
     * @param type 名称类型
     */
    private void setIntentData(String type) {
        Map<String, Object> map = new HashMap<>();
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("type", type);
        map.put("number", "3");
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
                    //资产性质、资产单位、
                    case "资产性质":
                        textViews.get(0).setText(value + "");
                        break;
                    case "资产单位":
                        textViews.get(1).setText(value + "");
                        break;
                }
                break;
            default:
                break;
        }
//        Details1.instance.checkBtx(true, false, isShow, map_key, map_more, "资产信息");
    }

    /**
     * 统一保存校验方法
     */
    public boolean saveDetails7() {
        String zcbh = editTexts.get(0).getText().toString();
        if (zcbh.equals("")) {
            if (map_key.containsKey("资产编号")) {
                ToastUtils.showLongToast(getActivity(), "资产编号不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setZcbh(zcbh);
        }
//        DemoActivity.instance.fzbhsb.setZcbh(zcbh);
        String zcxz = textViews.get(0).getText().toString();
        if (zcxz.equals("")) {
            if (map_key.containsKey("资产性质")) {
                ToastUtils.showLongToast(getActivity(), "资产性质不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setZcxz(zcxz);
        }
//        DemoActivity.instance.fzbhsb.setZcxz(zcxz);
        String zcdw = textViews.get(1).getText().toString();
        if (zcdw.equals("")) {
            if (map_key.containsKey("资产单位")) {
                ToastUtils.showLongToast(getActivity(), "资产单位不能为空");
                return false;
            }
        } else {
            DemoActivity.instance.fzbhsb.setZcdw(zcdw);
        }
//        DemoActivity.instance.fzbhsb.setZcdw(zcdw);
        return true;
    }

    /**
     * 统一保存校验方法
     */
    public boolean saveDetails7BH() {

        String zcbh = editTexts.get(0).getText().toString();
        if (zcbh.equals("")) {
            if (map_key.containsKey("资产编号")) {
                ToastUtils.showLongToast(getActivity(), "资产编号不能为空");
                return false;
            }
        } else {
        }
        DemoActivity.instance.bhsb.setZcbh(zcbh + "");
        String zcxz = textViews.get(0).getText().toString();
        if (zcxz.equals("")) {
            if (map_key.containsKey("资产性质")) {
                ToastUtils.showLongToast(getActivity(), "资产性质不能为空");
                return false;
            }
        } else {
        }
        DemoActivity.instance.bhsb.setZcxz(zcxz + "");
        String zcdw = textViews.get(1).getText().toString();
        if (zcdw.equals("")) {
            if (map_key.containsKey("资产单位")) {
                ToastUtils.showLongToast(getActivity(), "资产单位不能为空");
                return false;
            }
        } else {
        }
        DemoActivity.instance.bhsb.setZcdw(zcdw + "");
        return true;
    }
}
