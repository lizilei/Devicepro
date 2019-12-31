package cn.com.sgcc.dev.view.fragment.fzbhsb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.fragment.BaseFragment;

/**
 * <p>@description:电子标签信息</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/25
 */

public class Details5 extends BaseFragment {

    @BindView(R.id.tv_details5)
    TextView tv_details5;
    @BindViews(value = {R.id.tv_dzbqzzcj, R.id.tv_dzbqxh})
    List<TextView> textViews;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.item_details5;
    }

    @Override
    public void initview() {
    }

    @Override
    public void initEvevt() {

    }

    @Override
    public void initData() {
        initReceiver(getArguments().getBoolean("isEdit", false));

        tv_details5.setText("本条台账最后一次修改时间：" + TimeUtil.getCurrentTime());

        //判断保护还是辅助（保护为true，辅助false）
        if (DemoActivity.instance.BHorFZ){

        }else {

        }
    }

    @Override
    public void initReceiver(boolean isEdit) {
        if (isEdit) {
            for (TextView textView : textViews) {
                textView.setBackgroundResource(R.drawable.device_detials_bg);
                textView.setClickable(true);
            }
        } else {
            for (TextView textView : textViews) {
                textView.setBackground(null);
                textView.setClickable(false);
            }
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

    @OnClick({R.id.tv_dzbqzzcj, R.id.tv_dzbqxh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_dzbqzzcj:
                break;
            case R.id.tv_dzbqxh:
                break;
        }
    }
}
