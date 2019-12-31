package cn.com.sgcc.dev.view.fragment.BHSB;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.TypeRoadAkAdapter;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.model2.PZTDGX;
import cn.com.sgcc.dev.model2.TDXX;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;
import cn.com.sgcc.dev.view.activity.SelectActivity;
import cn.com.sgcc.dev.view.fragment.BaseFragment;

import static android.app.Activity.RESULT_OK;


/**
 * <p>@description:</p>
 * 通道信息 fragment
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */

public class Fragment_Type_Road_Ak extends BaseFragment {

    public TypeRoadAkAdapter adapter;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    Unbinder unbinder;
    @BindView(R.id.list_road_ak)
    ListView listRoadAk;
    public List<DeviceDetailsNameItem> list_one_data;
    public static Fragment_Type_Road_Ak instance = null;

    public int local;
    public boolean check= false;
    private IDaoUtil util;

    public List<SaleAttributeVo> saleVo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_type_road_ak;
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

    }


    @Override
    public void initEvevt() {

        util = new DaoUtil(getActivity());

        list_one_data = new ArrayList<>();
    }

    @Override
    public void initData() {


        if (true){
            /**
             * 获取通道信息
             * @param bhpzId 保护配置Id
             */
            List<TDXX> load_data = new ArrayList<>();
            String pzid = DemoActivity.instance.bhsb.getId() + "";
            if (pzid != null && !pzid.equals("") && !pzid.equals("null")) {
                load_data = util.getTDXX(DemoActivity.instance.bhsb.getId() + "");

                if (!load_data.isEmpty() && load_data.size() > 0) {
                    for (int i = 0; i < load_data.size(); i++) {
                        if (load_data.get(i).getTdlx() != null &&
                                !load_data.get(i).getTdlx().equals("") &&
                                load_data.get(i).getSffy() != null &&
                                !load_data.get(i).getSffy().equals("")) {
                            //通道类型，和是否复用必填，判断有值获取
                            DeviceDetailsNameItem load_item = new DeviceDetailsNameItem();
                            load_item.setNum(4);
                            load_item.setName_one("通道类型");
                            load_item.setContent_one(load_data.get(i).getTdlx() + "");
                            load_item.setName_two("是否复用");
                            load_item.setContent_two(load_data.get(i).getSffy() + "");
                            load_item.setName_three("通道装置型号");
                            String xh = load_data.get(i).getTdzzxh() + "";
                            if (!xh.equals("") && !xh.equals("null")) {
                                load_item.setContent_three(xh + "");
                            } else {
                                load_item.setContent_three("");
                            }
                            load_item.setNum(0);
                            list_one_data.add(load_item);
                        }
                    }
                }
            }
        }
        //通道信息初始化
        if (list_one_data.size()==0){
            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
            item.setName_one("通道类型");
            item.setContent_one("");
            item.setContent_two("否");
            item.setName_three("通道装置型号");
            item.setContent_three("");
            item.setNum(0);
            list_one_data.add(item);
        }

        adapter = new TypeRoadAkAdapter(getActivity(), list_one_data);
        listRoadAk.setAdapter(adapter);

        saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getBhData()) {
            if (saleAttributeNameVo.getName().equals("通道信息")) {
                saleVo.addAll(saleAttributeNameVo.getSaleVo());
                break;
            }
        }

        boolean isEdit = getArguments().getBoolean("isEdit", false);
        initReceiver(isEdit);

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
                    case "通道类型":
                        list_one_data.get(local).setContent_one(value);
                        if (value.equals("复用光纤")){
                            list_one_data.get(local).setContent_two("是");
                        }
                        adapter.notifyDataSetChanged();
                        break;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void initReceiver(boolean isEdit) {
        check = false;
        if (isEdit) {
            for (DeviceDetailsNameItem item : list_one_data) {
                item.setNum(1);
            }
        } else {
            for (DeviceDetailsNameItem item : list_one_data) {
                item.setNum(0);
            }

            if (DemoActivity.instance.rzgl != null &&
                    DemoActivity.instance.rzgl.getCZSJ() != null &&
                    !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                tvBottom.setVisibility(View.VISIBLE);
                tvBottom.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
            }
        }
        adapter.notifyDataSetChanged();
    }

    public boolean MakeSureRoad(){
        //通道是否合法
        boolean isRoadok = true;
        boolean ischeck = false;
        if (true) {
            if (saleVo.size()>0) {
                for (SaleAttributeVo vo : saleVo) {
                    if (vo.getValue().equals("通道装置型号")){
                        ischeck = true;
                    }
                }
            }
            for (int i = 0; i < list_one_data.size(); i++) {
                if (list_one_data.get(i).getContent_one().equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择通道类型");
                    isRoadok = false;
                    break;
                } else if (list_one_data.get(i).getContent_two().equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择是否复用");
                    isRoadok = false;
                    break;
                } else if (ischeck&&list_one_data.get(i).getContent_three().equals("")) {
                    ToastUtils.showToast(getActivity(), "请填写通道装置型号");
                    isRoadok = false;
                    break;
                }
            }

        }
        return isRoadok;
    }

    public void saveRoad(){
//是否有通道类型显示，有添加获取值，前两个必填值，最后一个手输入
        String sb_bhpzid = DemoActivity.instance.bhsb.getId() + "";
        util.deleteTDXX(sb_bhpzid);
        if (true) {
            String roadname = "";
            String roadone = "";
            List<TDXX> tdxxList = new ArrayList<>();
            for (int i = 0; i < list_one_data.size(); i++) {
                if (list_one_data.get(i).getContent_one().equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择通道类型");
                    break;
                }
                roadone = list_one_data.get(i).getContent_one() + "";
                if (roadname.equals("")) {
                    roadname = roadone + "";
                } else {
                    roadname = roadname + "," + roadone;
                }

                TDXX tdxx = new TDXX();
                tdxx.setTdlx(list_one_data.get(i).getContent_one() + "");
                tdxx.setSffy(list_one_data.get(i).getContent_two() + "");
                tdxx.setTdzzxh(list_one_data.get(i).getContent_three() + "");
                long tdxxid = util.getInsertId("TDXX");
                String tdxxcode = util.checkTDXX(list_one_data.get(i).getContent_one() + "",
                        list_one_data.get(i).getContent_two() + "",
                        list_one_data.get(i).getContent_three() + "");
                if (tdxxcode != null && !tdxxcode.equals("") && !tdxxcode.equalsIgnoreCase("null")) {
                    tdxxid = Long.parseLong(tdxxcode);
                } else {
                    tdxx.setId(tdxxid);
                    //通道信息保存
                    util.saveTDXX(tdxx);
                }
                //通道信息关联
                PZTDGX pztdgx = new PZTDGX();
                long tdxxgxid = util.getInsertId("PZTDGX");
                pztdgx.setID(tdxxgxid);
                pztdgx.setBHPZID(DemoActivity.instance.bhsb.getId());
                pztdgx.setTDXXID(tdxxid);
                //通道信息关联保存
                util.saveTDXXPZ(pztdgx);
                tdxxList.add(tdxx);
            }
            //通道类型插主表
            if (!roadname.equals("")) {
                DemoActivity.instance.bhsb.setTdlx(roadname + "");
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
