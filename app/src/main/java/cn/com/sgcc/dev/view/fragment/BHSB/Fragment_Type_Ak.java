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
import cn.com.sgcc.dev.adapter.TypeAkAdapter;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.model2.AKXTGX;
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
 * 安控 fragment
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */

public class Fragment_Type_Ak extends BaseFragment {

    public TypeAkAdapter adapter;
    Unbinder unbinder;
    public List<DeviceDetailsNameItem> list_one_data;
    public static Fragment_Type_Ak instance = null;
    @BindView(R.id.list_ak)
    ListView listAk;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;

    private int local;
    public boolean check= false;

    private IDaoUtil util;
    //安控系统ID
    public List<String> akxt_id_list;
    //获取安控系统ID
    public List<String> akxt_id;

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
        return R.layout.fragment_type_ak;
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
        akxt_id = new ArrayList<>();
        akxt_id_list = new ArrayList<>();
        list_one_data = new ArrayList<>();

        if (Fragment_Type_Base.instance.isC){

        } else {
            /**
             * 获取安控信息
             * @param bhpzId 保护配置Id
             */
            List<AKXTGX> akxtgx_data = new ArrayList<>();
            String pzid_ak = DemoActivity.instance.bhsb.getId() + "";
            if (pzid_ak != null && !pzid_ak.equals("") && !pzid_ak.equals("null")) {
                akxtgx_data = util.getAKXTGX(DemoActivity.instance.bhsb.getId() + "");

                if (!akxtgx_data.isEmpty() && akxtgx_data.size() > 0) {
                    for (int i = 0; i < akxtgx_data.size(); i++) {
                        String akxtm = "";
                        if (util.getAKXT(akxtgx_data.get(i).getAkxtid()).size() > 0) {
                            akxtm = util.getAKXT(akxtgx_data.get(i).getAkxtid()).get(0).getAkxtm() + "";
                        }
                        if (akxtm != null &&
                                !akxtm.equals("") && !akxtm.equals("null") &&
                                akxtgx_data.get(i).getAkzdlx() != null &&
                                !akxtgx_data.get(i).getAkzdlx().equals("")) {
//                        //安控站点类型，和安控调度命名必填，判断有值获取
                            DeviceDetailsNameItem items = new DeviceDetailsNameItem();
                            items.setName_one("安控系统调度名");
                            items.setContent_one(akxtm + "");
                            items.setName_four("安控站点类型");
                            items.setContent_four(akxtgx_data.get(i).getAkzdlx() + "");
                            items.setNum(0);
                            list_one_data.add(items);
                            //安控名称存ID
                            akxt_id.add(akxtgx_data.get(i).getAkxtid() + "");
                        }
                    }
                }
            }
        }
        //无数据默认初始化一个
        if (list_one_data.size()==0){
            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
            item.setName_one("安控系统调度命名");
            item.setContent_one("");
            item.setName_four("安控站点类型");
            item.setContent_four("");
            item.setNum(0);
            list_one_data.add(item);
            akxt_id.add("");
        }

    }

    @Override
    public void initData() {

        adapter = new TypeAkAdapter(getActivity(), list_one_data);
        listAk.setAdapter(adapter);

        saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getBhData()) {
            if (saleAttributeNameVo.getName().equals("安控信息")) {
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
        map.put("position", position+"");
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
                String putposition = bunde.getString("putposition") + "";
                switch (puttype) {
                    case "安控系统调度名":
                        String id  =  akxt_id_list.get(Integer.parseInt(putposition))+"";
                        akxt_id.set(local,id);

                        list_one_data.get(local).setContent_one(value);
                        adapter.notifyDataSetChanged();
                        break;
                    case "安控站点类型":
                        list_one_data.get(local).setContent_four(value);
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

    public boolean MakeSureAk(){
        //安控系统是否合法
        boolean isAkok = true;
        if (true) {
            for (int i = 0; i < list_one_data.size(); i++) {
                if (list_one_data.get(i).getContent_one().equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择安控系统调度名");
                    isAkok = false;
                    break;
                } else if (list_one_data.get(i).getContent_four().equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择安控站点类型");
                    isAkok = false;
                    break;
                }
            }
        }
        return isAkok;
    }


    public void saveAk(){

        util.deleteAKXTGX(DemoActivity.instance.bhsb.getId()+"");
        if (true) {
            //是否有设备类型显示,安控系统，必填项目，两个值都需要有
            //保存新的之前，默认删除旧表
            for (int i = 0; i < list_one_data.size(); i++) {
                //存到主表里面
                if (i == 0) {
                    DemoActivity.instance.bhsb.setSsakxtddmm(list_one_data.get(i).getContent_one() + "");
                    DemoActivity.instance.bhsb.setAkzdlx(list_one_data.get(i).getContent_four() + "");
                }

                AKXTGX akxtgx = new AKXTGX();

                Long axktgxid = util.getInsertId("AKXTGX");
                akxtgx.setId(axktgxid);
                akxtgx.setAkzdlx(list_one_data.get(i).getContent_four() + "");
                akxtgx.setAkxtid(akxt_id.get(i) + "");
                akxtgx.setBhpzid(DemoActivity.instance.bhsb.getId() + "");
//              安控类型保存未处理
                util.saveAKXTGX(akxtgx);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
