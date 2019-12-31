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
import cn.com.sgcc.dev.adapter.TypeBaseAdapter;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.BHPZXHBBGX;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.SelectActivity;
import cn.com.sgcc.dev.view.fragment.BaseFragment;

import static android.app.Activity.RESULT_OK;


/**
 * <p>@description:</p>
 * 更多模块信息 fragment
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 */

public class Fragment_Type_More_Mk extends BaseFragment {

    public TypeBaseAdapter adapter;
    @BindView(R.id.tv_back)
    TextView tvback;
    @BindView(R.id.tv_check)
    TextView tvcheck;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    Unbinder unbinder;
    @BindView(R.id.list_more_mk)
    ListView listMoreMk;
    public List<BHXHRJBB> list_one_data;
    public static Fragment_Type_More_Mk instance = null;

    public int local;
    private IDaoUtil util;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_type_more_mk;
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

        //模块信息初始化
        if (list_one_data.size() == 0) {
        }
        for (BHXHRJBB bhxhrjbb : Fragment_Type_Base.instance.rjbbList) {
            list_one_data.add(bhxhrjbb);
        }

        boolean isEdit = getArguments().getBoolean("isEdit", false);

        adapter = new TypeBaseAdapter(getActivity());
        adapter.setDatas(list_one_data, isEdit,true);
        listMoreMk.setAdapter(adapter);

        updataMoreMk();
        initReceiver(isEdit);

        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoActivity.instance.showMk(0);
            }
        });
        tvcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoActivity.instance.showMk(0);
            }
        });

    }

    public void setIntentData(String type, int position) {
        local = position;
        Map<String, Object> map = new HashMap<>();
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("type", type);
        map.put("isSIX", false);
        map.put("is2013", false);
        map.put("number", "1");
        map.put("position", position + "");


        if (type.equals("模块名称")) {
            map.put("selectCode", Fragment_Type_Base.instance.codeMap.get(Fragment_Type_Base.instance.getBhxh()));
        } else if (type.equals("软件版本")) {
            map.put("bhxh", Fragment_Type_Base.instance.codeMap.get(Fragment_Type_Base.instance.getBhxh()));
            map.put("mkmc", list_one_data.get(position).getMkmc() + "");
            map.put("bblx", Fragment_Type_Base.instance.bblxMap.get(Fragment_Type_Base.instance.getBhxh()));
            map.put("czlx", new DaoUtil(getActivity()).getCZCSByGLDW().getBdzlx());
            map.put("dydj", Fragment_Type_Inset.instance.getdydj() + "");
        } else if (type.equals("校验码")) {
            map.put("mkmc", list_one_data.get(position).getMkmc() + "");
            map.put("bb", list_one_data.get(position).getBb() + "");
        }
        map.put("bhxh", Fragment_Type_Base.instance.codeMap.get(Fragment_Type_Base.instance.getBhxh()));

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
                int putposition = Integer.parseInt(bunde.getString("putposition") + "");
                if (!DemoActivity.instance.ismoreMk) {
                    return;
                }
                switch (puttype) {
                    case "模块名称":
                        if (list_one_data.get(adapter.getPosition()).getMkmc() != null &&
                                !list_one_data.get(adapter.getPosition()).getMkmc().equals(value)) {
                            list_one_data.get(adapter.getPosition()).setBb("");
                            list_one_data.get(adapter.getPosition()).setJym("");
                        }
                        list_one_data.get(adapter.getPosition()).setMkmc(value);
                        adapter.setDatas(list_one_data, true,true);
                        break;
                    case "软件版本":
                        if (putposition > -1) {
                            if (!value.equals(list_one_data.get(adapter.getPosition()).getBb())) {
                                list_one_data.get(adapter.getPosition()).setJym("");
                            }
                            list_one_data.get(adapter.getPosition()).setBb(value);
                            adapter.setDatas(list_one_data, true,true);
                        }
                        break;
                    case "校验码":
                        if (putposition > -1) {
                            list_one_data.get(adapter.getPosition()).setJym(value);
                            adapter.setDatas(list_one_data, true,true);
                        }
                        break;
                    default:
                        break;
                }
        }
    }

    @Override
    public void initReceiver(boolean isEdit) {
        if (isEdit) {
            adapter.setDatas(list_one_data, isEdit,true);
            tvcheck.setVisibility(View.VISIBLE);
        } else {
            tvcheck.setVisibility(View.GONE);
            adapter.setDatas(list_one_data, isEdit,true);
            if (DemoActivity.instance.rzgl != null &&
                    DemoActivity.instance.rzgl.getCZSJ() != null &&
                    !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                tvBottom.setVisibility(View.VISIBLE);
                tvBottom.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
            }
        }
    }

    public void updataMoreMk() {
        if (Fragment_Type_Base.instance.list_one_data.size() <= 5 && list_one_data.size() > 5) {
            for (int i = 0; i < 5; i++) {
                if(i>=list_one_data.size()||i>=Fragment_Type_Base.instance.list_one_data.size()){
                    break;
                }
                if (!(Fragment_Type_Base.instance.list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null") &&
                        !(list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null") &&
                        !Fragment_Type_Base.instance.list_one_data.get(i).getMkmc().equals(list_one_data.get(i).getMkmc() + "") ||
                        !(Fragment_Type_Base.instance.list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null") &&
                                (list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null")) {
                    list_one_data.add(i, Fragment_Type_Base.instance.list_one_data.get(i));
                } else if (!(Fragment_Type_Base.instance.list_one_data.get(i).getBb() + "").equalsIgnoreCase("null") &&
                        !(list_one_data.get(i).getBb() + "").equalsIgnoreCase("null") &&
                        !Fragment_Type_Base.instance.list_one_data.get(i).getBb().equals(list_one_data.get(i).getBb() + "") ||
                        !(Fragment_Type_Base.instance.list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null") &&
                                (list_one_data.get(i).getMkmc() + "").equalsIgnoreCase("null")) {
                    list_one_data.add(i, Fragment_Type_Base.instance.list_one_data.get(i));
                } else if (!(Fragment_Type_Base.instance.list_one_data.get(i).getJym() + "").equalsIgnoreCase("null") &&
                        !(list_one_data.get(i).getJym() + "").equalsIgnoreCase("null") &&
                        !Fragment_Type_Base.instance.list_one_data.get(i).getJym().equals(list_one_data.get(i).getJym() + "") ||
                        !(Fragment_Type_Base.instance.list_one_data.get(i).getJym() + "").equalsIgnoreCase("null") &&
                                (list_one_data.get(i).getJym() + "").equalsIgnoreCase("null")) {
                    list_one_data.add(i, Fragment_Type_Base.instance.list_one_data.get(i));
                } else if (!(Fragment_Type_Base.instance.list_one_data.get(i).getSCSJ() + "").equalsIgnoreCase("null") &&
                        !(list_one_data.get(i).getSCSJ() + "").equalsIgnoreCase("null") &&
                        !Fragment_Type_Base.instance.list_one_data.get(i).getSCSJ().equals(list_one_data.get(i).getSCSJ() + "") ||
                        !(Fragment_Type_Base.instance.list_one_data.get(i).getSCSJ() + "").equalsIgnoreCase("null") &&
                                (list_one_data.get(i).getSCSJ() + "").equalsIgnoreCase("null")) {
                    list_one_data.add(i, Fragment_Type_Base.instance.list_one_data.get(i));
                }
            }
        }else if (Fragment_Type_Base.instance.list_one_data.size()==5&&list_one_data.size()<5){
            list_one_data.clear();
            for (BHXHRJBB list_one_datum : Fragment_Type_Base.instance.list_one_data) {
                list_one_data.add(list_one_datum);
            }
        }
//        adapter.notifyDataSetChanged();
    }

    //模块多选合法性判断
    public boolean isaddOk() {
        if (!DemoActivity.instance.ismoreMk){
            updataMoreMk();
        }

        boolean isok = true;
        boolean isjym = false;
        boolean issj = false;
        if (Fragment_Type_Base.instance.saleVo.size()>0) {
            for (SaleAttributeVo vo : Fragment_Type_Base.instance.saleVo) {
                if (vo.getValue().equals("校验码")){
                    isjym = true;
                }
                if (vo.getValue().equals("生成时间")){
                    issj = true;
                }
            }
        }
        for (BHXHRJBB item : list_one_data) {
            //填写了模块名称，软件版本为必填
            if ((item.getMkmc()+"").equalsIgnoreCase("null")||item.getMkmc().equals("")) {
                ToastUtils.showToast(getActivity(), "请选择填写模块名称");
                isok = false;
                break;
            } else if ((item.getBb()+"").equalsIgnoreCase("null")||item.getBb().equals("")) {
                ToastUtils.showToast(getActivity(), "请选择填写软件版本");
                isok = false;
                break;
            }
            else if (isjym&&(item.getJym() + "").equalsIgnoreCase("null") || isjym&&item.getJym().equals("")){
                ToastUtils.showToast(getActivity(), "请选择填写校验码");
                isok = false;
                break;
            } else if (issj&&(item.getSCSJ() + "").equalsIgnoreCase("null") || issj&&item.getSCSJ().equals("")) {
                ToastUtils.showToast(getActivity(), "请选择生成时间");
                isok = false;
                break;
            }
        }
        return isok;
    }

    public void saveMoreMk() {
        String rjbb = "";
        for (int i = 0; i < list_one_data.size(); i++) {
            //判断至少一个有值再存
            if (!list_one_data.get(i).getMkmc().equals("") ||
                    !list_one_data.get(i).getBb().equals("")) {
                BHXHRJBB bhxhrjbb = new BHXHRJBB();
                bhxhrjbb.setRjbbxx("模块名称：" + list_one_data.get(i).getMkmc() + "," +
                        "软件版本：" + list_one_data.get(i).getBb() + ","
                        + "校验码：" + list_one_data.get(i).getJym() + ","
                        + "生成日期：" + list_one_data.get(i).getSCSJ() + ""
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
                bhxhrjbb.setBhxhcode(Fragment_Type_Base.instance.codeMap.get(Fragment_Type_Base.instance.protect_type_m) + "");
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

    public void setPosition(){
        listMoreMk.post(new Runnable() {
            @Override
            public void run() {
                listMoreMk.smoothScrollToPosition(list_one_data.size()-1);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
