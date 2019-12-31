package cn.com.sgcc.dev.view.fragment.fzbhsb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.CommonAdapter;
import cn.com.sgcc.dev.adapter.ViewHolder;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.BKXX;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.fragment.BaseFragment;

/**
 * <p>@description:板卡信息</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/25
 */

public class Details4 extends BaseFragment {
    @BindView(R.id.listView_bk)
    ListView listView;
    @BindView(R.id.tv_details4)
    TextView tv;
    Unbinder unbinder;
    private List<BKXX> list = new ArrayList<>();
    private CommonAdapter<BKXX> adapter;

    @Override
    public int getLayoutId() {
        return R.layout.item_details4;
    }

    @Override
    public void initview() {
        adapter = new CommonAdapter<BKXX>(getActivity(), R.layout.item_list_bk) {
            @Override
            public void convert(ViewHolder helper, BKXX item) {
                helper.setText(R.id.tv_bkxh, item.getBkxh());
                helper.setText(R.id.tv_bklb, item.getBklb());
                helper.setText(R.id.tv_bkbh, item.getBkbh());
                helper.setText(R.id.tv_yjbb, item.getRjbb());
                helper.setText(R.id.tv_bkscrq, item.getBkscrq());
            }
        };
        listView.setAdapter(adapter);
    }

    @Override
    public void initEvevt() {

    }

    @Override
    public void initData() {
        //判断保护还是辅助（保护为true，辅助false）
        if (DemoActivity.instance.BHorFZ) {
            BHPZ bhsb = DemoActivity.instance.bhsb;
            if (bhsb != null) {
                if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C")) {
                    tv.setVisibility(View.GONE);
                    //增加同类设备的板卡信息
                    if(DemoActivity.instance.Similar){
                        tv.setVisibility(View.VISIBLE);
                        if (DemoActivity.instance.rzgl != null) {
                            tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                        } else {
                            tv.setText("本条台账最后一次修改时间：");
                        }
                        if (bhsb.getBksl() > 0) {
                            List<Object> bkList = new DaoUtil(getActivity()).getICDOrBKXX(BKXX.class, bhsb.getId() + "", "BHPZ");
                            for (Object o : bkList) {
                                list.add((BKXX) o);
                            }
                            adapter.setDatas(list);
                        }
                           /* IDaoUtil util = new DaoUtil(getActivity());
                            if (getActivity().getIntent().hasExtra("sbsbdm")){
                                Object o = util.getObjectFromCCXX(getActivity().getIntent().getStringExtra("sbsbdm"));
                                if(o != null){//关联到出厂信息库
                                    List<BKXX> bkxxList = util.getCCXXBK(getActivity().getIntent().getStringExtra("sbsbdm"));
                                    if (bkxxList != null && bkxxList.size() > 0){
                                        for (BKXX bkxx : bkxxList) {
                                            list.add(bkxx);
                                        }
                                        adapter.setDatas(list);
                                    }
                                }
                            }*/

                    }else{//新增
                        if (getActivity().getIntent().hasExtra("BKXX")) {
                            List<Object> bkList = (List<Object>) getActivity().getIntent().getSerializableExtra("BKXX");
                            for (Object o : bkList) {
                                list.add((BKXX) o);
                            }
                            adapter.setDatas(list);
                        }
                    }

                } else {
                    tv.setVisibility(View.VISIBLE);
                    if (DemoActivity.instance.rzgl != null) {
                        tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                    } else {
                        tv.setText("本条台账最后一次修改时间：");
                    }
                    if (bhsb.getBksl() > 0) {
                        List<Object> bkList = new DaoUtil(getActivity()).getICDOrBKXX(BKXX.class, bhsb.getId() + "", "BHPZ");
                        for (Object o : bkList) {
                            list.add((BKXX) o);
                        }
                        adapter.setDatas(list);
                    }
                }
            }
        } else {
            FZBHSB fzbhsb = DemoActivity.instance.fzbhsb;
            if (fzbhsb != null) {
                if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C")) {
                    tv.setVisibility(View.GONE);
                    //增加同类设备的板卡信息
                    if(DemoActivity.instance.Similar){
                        tv.setVisibility(View.VISIBLE);
                        if (DemoActivity.instance.rzgl != null) {
                            tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                        } else {
                            tv.setText("本条台账最后一次修改时间：");
                        }
                        if (fzbhsb.getBksl()  > 0) {
                            List<Object> bkList = new DaoUtil(getActivity()).getICDOrBKXX(BKXX.class, fzbhsb.getId() + "", "FZBHSB");
                            for (Object o : bkList) {
                                list.add((BKXX) o);
                            }
                            adapter.setDatas(list);
                        }
                      /*  IDaoUtil util = new DaoUtil(getActivity());
                        if (getActivity().getIntent().hasExtra("sbsbdm")){
                            Object o = util.getObjectFromCCXX(getActivity().getIntent().getStringExtra("sbsbdm"));
                            if(o != null){//关联到出厂信息库
                                List<BKXX> bkxxList = util.getCCXXBK(getActivity().getIntent().getStringExtra("sbsbdm"));
                                if (bkxxList != null && bkxxList.size() > 0){
                                    for (BKXX bkxx : bkxxList) {
                                        list.add(bkxx);
                                    }
                                    adapter.setDatas(list);
                                }
                            }*/
                         }else {//新增
                            if (getActivity().getIntent().hasExtra("BKXX")) {
                                List<Object> bkList = (List<Object>) getActivity().getIntent().getSerializableExtra("BKXX");
                                for (Object o : bkList) {
                                    list.add((BKXX) o);
                                }
                                adapter.setDatas(list);
                            }
                    }
                } else {
                    tv.setVisibility(View.VISIBLE);
                    if (DemoActivity.instance.rzgl != null) {
                        tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                    } else {
                        tv.setText("本条台账最后一次修改时间：");
                    }
                    if (fzbhsb.getBksl() > 0) {
                        List<Object> bkList = new DaoUtil(getActivity()).getICDOrBKXX(BKXX.class, fzbhsb.getId() + "", "FZBHSB");
                        for (Object o : bkList) {
                            list.add((BKXX) o);
                        }
                        adapter.setDatas(list);
                    }
                }
            }
        }
    }

    @Override
    public void initReceiver(boolean isEdit) {
        if (isEdit) {

        } else {
            if (DemoActivity.instance.rzgl != null &&
                    DemoActivity.instance.rzgl.getCZSJ() != null &&
                    !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                tv.setVisibility(View.VISIBLE);
                tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
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
}
