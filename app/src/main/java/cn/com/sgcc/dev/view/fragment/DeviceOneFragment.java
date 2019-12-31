package cn.com.sgcc.dev.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.DeviceOneAdapter;
import cn.com.sgcc.dev.adapter.DeviceSelectAdapter;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.customeView.LoadingDialog;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.model2.AKXT;
import cn.com.sgcc.dev.model2.AKXTGX;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.BHPZXHBBGX;
import cn.com.sgcc.dev.model2.BHSBXHB;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.model2.FZBHSBXHBBGX;
import cn.com.sgcc.dev.model2.LTYSBXH;
import cn.com.sgcc.dev.model2.PZTDGX;
import cn.com.sgcc.dev.model2.TDXX;
import cn.com.sgcc.dev.model2.ZZCJ;
import cn.com.sgcc.dev.model2.ycsb.BYQCS;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.model2.ycsb.DDJCS;
import cn.com.sgcc.dev.model2.ycsb.DKQCS;
import cn.com.sgcc.dev.model2.ycsb.DLQCS;
import cn.com.sgcc.dev.model2.ycsb.DRQCS;
import cn.com.sgcc.dev.model2.ycsb.FDJCS;
import cn.com.sgcc.dev.model2.ycsb.MXCS;
import cn.com.sgcc.dev.model2.ycsb.XLCS;
import cn.com.sgcc.dev.utils.DeviceDateilsUtils;
import cn.com.sgcc.dev.utils.PinyinComparator;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

/**
 * <p>@description:</p>
 * 设备详情二级界面，详情一
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */

public class DeviceOneFragment extends BaseFragment {

    private ListView Infor_select;
    public EditText input_id_card;
    private List<DeviceDetailsNameItem> data_name;
    private List<DeviceDetailsNameItem> data_name_load;
    private List data;
    private DeviceOneAdapter adapter;
    private DeviceSelectAdapter selectAdapter;

    private CustomDialog dialog;
    private CustomDialog type_dialog;

    private LoadingDialog progressDialog;

    public static DeviceOneFragment instance = null;
    //连接器显示
    private boolean link_device;
    //是否就地化装置
    public boolean isLink_device = false;
    private int Link_device_num = 0;

    public String ljqsl = "";
    public String ljqzzcj = "";
    public String ljqczxh = "";

    //是否有通道类型显示
    private boolean isroadtype;
    //是否有设备类型显示
    private boolean isdevice_type;
    //是否有模块信息显示
    public boolean ismodel_type;
    //是否有选配功能显示
    private boolean ischoice_type;
    //是否有通道类型显示数目
    public int add_protect_type = 0;
    //是否有设备类型显示显示数目
    public int add_device_type = 0;
    //是否有模块信息显示数目
    public int add_model_type = 0;

    //身份识别码
    public String id_card = "";
    //六统一
    public boolean six_one = false;
    //六统一标准版本
    public String six_one_details = "";
    //单位名称
    public String company_name = "";
    //厂站名称
    private String control_company_name = "";
    //调度单位
    public String company_location_name = "";
    //一次设备类型
    private String one_device_type = "";
    //一次设备名称
    private String one_device_name = "";
    //电压等级
    public String electric_level = "";
    //保护类别
    private String protect_type = "";
    //保护类别细化
    private String protect_type_details = "";
    //设备类型
    private String device_type = "";
    //制造厂家
    private String made_company = "";
    //保护型号
    private String protect_type_m = "";
    //保护分类
    private String protect_kind = "";
    //是否国调
    public String isGD = "";
    //厂站类型
    public String company_type = "";
    //保护类型
    private String protect_what_kind = "";
    //软件版本
    public String ver_info = "";
    //生成日期
    private String made_day = "";
    //跳闸关系
    private String relationship = "";
    //保护套别
    private String protect_config = "";
    //名称属性
    private String name_type = "";
    //保护名称
    private String protect_name = "";

    //故障录波器类型
    private String machine_type = "";
    //测距形式
    private String test_type = "";
    //是否调度主
    private String isask = "";
    //设备功能配置
    private String device_work_set = "";
    //选配功能
    public String select_work = "";
    //ICD文件名
    private String ICD = "";
    //软件版本
//    private String ICD = "";

    //判断是添加还是编辑
    private boolean is_add = true;
    //本地添加台账
    private BHPZ newBHPZ;  //保护装置对象;

    private IDaoUtil util;

    //是否分模块类型
    public List<String> model_data_type;
    //是否分模块，模块名称，及软件版本的获取码
    public List<String> model_data_type_code;
    //是否分模块，模块名称，及软件版本的获取码,从型号获取
    public List<String> model_data_type_code_xh;
    //获取码,保护型号code
    public String model_data_type_get_code = "";

    //安控系统ID
    public List<String> akxt_id_list;
    //获取安控系统ID
    public List<String> akxt_id;
    //获取安控系统关系
    public List<AKXTGX> akxtgx_all;
    //获取通道关系
    public List<PZTDGX> tdgx_all;

    //一次设备多选值
    public String reault = "";
    //跳闸关系多选值
    public String KGBHreault = "";
    //保护名称重复判断
    public boolean protect_name_rep = false;

    //对象插入成功
    public boolean savesuccess = false;
    //对象插入成功
    public boolean add_more_success = false;

    //制造厂家本地添加
    public String location_Made_company = "";
    //一次设备名称本地添加
    public String location_One_device_name = "";
    //保护型号本地添加
    private String location_protect_type_m = "";
    //保护分类本地添加
    private String location_protect_kind = "";
    //保护类型本地添加
    private String location_protect_what_kind = "";
    //选配功能本地添加
    private String location_select_work = "";
    //ICD文件名本地添加
    private String location_ICD = "";
    //模块名称获取，软件版本使用
    public String model_one = "";


    @Override
    public int getLayoutId() {
        return R.layout.fragment_one_device;
    }

    @Override
    public void initview() {

        Infor_select = (ListView) getActivity().findViewById(R.id.fragment_device_details_one_lv);
        input_id_card = (EditText) getActivity().findViewById(R.id.fragment_one_device_item_one_select_bt);

        //未关联到设备信息直接新增后 自动补码
        if (DeviceDetailsActivity.sfsbdm != null) {
            if (DeviceDetailsActivity.sfsbdm.equals("")&&DeviceDetailsActivity.sfsbdm.equals("null")){
                input_id_card.setText("");
            }else{
                input_id_card.setText(DeviceDetailsActivity.sfsbdm);
            }
        }else{
            input_id_card.setText("");
        }

//        showTypeDialog("", "");
        progressDialog = new LoadingDialog(getActivity());
        progressDialog.setTitle("保护名称验校中");
        progressDialog.setCanceledOnTouchOutside(true);

    }

    @Override
    public void initEvevt() {

    }

    @Override
    public void initData() {
        util = new DaoUtil(getActivity());

        data_name = new ArrayList<>();
        //通道类型添加最终获取数组。
        data_name_load = new ArrayList<>();
        //通道类型导入获取，及删除
        tdgx_all = new ArrayList<>();

        //模块名称，非六统一是否分模块
        model_data_type = new ArrayList<>();
        //模块名称，类型值
        model_data_type_code = new ArrayList<>();
        //模块名称，类型值，型号获取
        model_data_type_code_xh = new ArrayList<>();
        //安控系统，获取值
        akxtgx_all = new ArrayList<>();
        //安控系统ID组获取
        akxt_id = new ArrayList<>();
        akxt_id_list = new ArrayList<>();

        newBHPZ = new BHPZ();
        //将静态值初始化置空
        cleanDATA();

        if (DeviceDetailsActivity.state.equals("C")) {
            //操作状态  C:新增 M:修改
            //判断是否是扫码进入的修改****************
            if (DeviceDetailsActivity.isFromSaoma) {
                is_add = false;
                newBHPZ = DeviceDetailsActivity.bhpz;
            } else {
                is_add = true;
            }
        } else {
            //操作状态  C:新增 M:修改
            is_add = false;
            newBHPZ = DeviceDetailsActivity.bhpz;
        }

        if (is_add) {
            //添加数据初始化
            add_data();
        } else {
            //编辑数据初始化
            change_data();
        }


        adapter = new DeviceOneAdapter(getActivity(), data_name);
        Infor_select.setAdapter(adapter);

    }

    @Override
    public void initReceiver(boolean isEdit) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

    public void showTypeDialog(String title, String content) {
        if (type_dialog != null && type_dialog.isShowing()) {
            return;
        } else {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_one_device_dialog_item, null);
            type_dialog = new CustomDialog(getActivity(), R.style.dialog_alert_style, 0);
            // 根据id在布局中找到控件对象
            TextView tv_dialog_protect = (TextView) view.findViewById(R.id.fragment_device_details_dialog_protect);
            TextView tv_dialog_auxiliary = (TextView) view.findViewById(R.id.fragment_device_details__dialog_auxiliary);
            data = new ArrayList();

            //保护设备
            tv_dialog_protect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showToast(getActivity(), "当前选择保护设备");
                    type_dialog.dismiss();
                }
            });
            //辅助设备
            tv_dialog_auxiliary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showToast(getActivity(), "当前选择辅助设备");
                    type_dialog.dismiss();
                }
            });
            type_dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            type_dialog.setCanceledOnTouchOutside(true);
//            tv_dialog_title.setText(content);
//            tv_dialog_content.setText(title);
            type_dialog.show();
        }
    }

    public void showDialog(final String title, final String content, final int Y, final int X, final List<String> item_name) {
        //默认手动添加通道类型
        if (X == 4) {
            if (add_protect_type > Constants.ver_info_limt) {
                ToastUtils.showToast(getActivity(), "当前添加达到上限");
                return;
            } else {
                DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                item.setNum(4);
                item.setName_one("通道类型");
                item.setContent_one("");
                item.setName_two("是否复用");
                item.setContent_two("");
                item.setName_three("通道装置型号");
                item.setContent_three("");
                data_name.add(4 + add_protect_type, item);
                add_protect_type++;
                adapter.notifyDataSetChanged();
                return;
            }
        } else if (X == 5) {
            //默认手动删除通道类型
            data_name.remove(Y);
            add_protect_type--;
            adapter.notifyDataSetChanged();
            return;
        }
        if (X == 6) {
            if (add_device_type > Constants.ver_info_limt) {
                ToastUtils.showToast(getActivity(), "当前添加达到上限");
                return;
            } else {

                DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                item.setNum(6);
                item.setName_one("安控系统调度名");
                item.setContent_one("");
                item.setName_two("安控站点类型");
                item.setContent_two("");
                item.setName_three("通道装置型号");
                item.setContent_three("");
                data_name.add(4 + add_device_type, item);
                add_device_type++;
                akxt_id.add("");
                adapter.notifyDataSetChanged();
                return;
            }
        } else if (X == 7) {
            data_name.remove(Y);
            //默认移除其中的ID值
            if (Y > 3 && Y <= 3 + add_device_type) {
                for (int i = 0; i < add_device_type; i++) {
                    if (Y == 4 + i) {
                        akxt_id.remove(i);
                    }
                }
            }
            add_device_type--;
            adapter.notifyDataSetChanged();
            return;
        }
        if (X == 8) {
            if (add_model_type > Constants.ver_info_limt) {
                ToastUtils.showToast(getActivity(), "当前添加达到上限");
                return;
            } else {
                DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                item.setNum(8);
                item.setName_one("模块名称");
                item.setContent_one("");
                item.setName_two("软件版本");
                item.setContent_two("");
                item.setName_three("生成日期");
                item.setContent_three("");
                data_name.add(add_device_type + add_protect_type + add_model_type + 6, item);
                add_model_type++;
                ismodel_type = true;
                adapter.notifyDataSetChanged();
                return;
            }
        } else if (X == 9) {
            data_name.remove(Y);
            add_model_type--;
            adapter.notifyDataSetChanged();
            return;
        }
        //没有数据处理，未统一处理
        if (item_name.size() == 0) {
        }
        if (dialog != null && dialog.isShowing()) {
            return;
        } else {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_one_device_select_item, null);
            dialog = new CustomDialog(getActivity(), R.style.dialog_alert_style, 0);

            //根据id在布局中找到控件对象
            TextView tv_dialog_title_one = (TextView) view.findViewById(R.id.fragment_device_details_select_item_cancel);
            final ListView lv_dialog = (ListView) view.findViewById(R.id.fragment_device_details_select_item__lv);
            final EditText et_dialog = (EditText) view.findViewById(R.id.app_search_edit);

            Button btn_search = (Button) view.findViewById(R.id.btn_search);
            Button btn_add = (Button) view.findViewById(R.id.btn_add);

            data = new ArrayList();
            //全部数据
            final List<String> data_all = new ArrayList();
            //正确数据库来源数据
            data = item_name;
            //第一次添加，之后为替换
            final boolean[] isfirstadd = {true};

            //搜索按钮按钮

            btn_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.size() > 0) {
                        String name = et_dialog.getText() + "";
                        if (name.equals("")) {
                            ToastUtils.showToast(getActivity(), "请输入查询信息");
                        } else {
                            //搜索内容处理
                            List<String> item = new ArrayList<String>();
                            for (int i = 0; i < item_name.size(); i++) {
                                if (item_name.get(i).contains(name)) {
                                    item.add(i + "");
                                    break;
                                }
                            }
                            if (item.size() > 0) {
                                int i = Integer.parseInt(item.get(0));
                                lv_dialog.setSelection(i);
                            } else {
                                ToastUtils.showToast(getActivity(), "输入信息查询无效");
                            }

                        }
                    } else {
                        ToastUtils.showToast(getActivity(), "可选信息为空");
                    }
                }
            });

            //默认输入100个字符
            et_dialog.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
            if (isLink_device && Y == data_name.size() - Link_device_num && X == 1) {
                et_dialog.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                et_dialog.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
            }


            et_dialog.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String input_name = et_dialog.getText() + "";
                    //恢复全部选择信息
                    if (one_device_type.equals("母线") && Y == 2 && X == 2 ||
                            one_device_type.equals("断路器") &&
                                    Y == 2 && X == 2) {
                        reault = input_name;
//                        跳闸关系多选
                    } else if (Y == data_name.size() - 3 - Link_device_num && X == 1) {
                        KGBHreault = input_name;
                    }
                }
            });
            //添加按钮
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //一次设备名称多选
                    if (one_device_type.equals("母线") && Y == 2 && X == 2 ||
                            one_device_type.equals("断路器") &&
                                    Y == 2 && X == 2) {

                        String name = et_dialog.getText() + "";
                        data_name.get(Y).setContent_two(name);
                        one_device_name = name;
                        dialog.dismiss();
                        adapter.notifyDataSetChanged();
//                        跳闸关系多选
                    } else if (Y == data_name.size() - 3 - Link_device_num && X == 1) {
                        String name = et_dialog.getText() + "";
                        data_name.get(Y).setContent_one(name);
                        relationship = name;
                        dialog.dismiss();
                        adapter.notifyDataSetChanged();
                    } else {
                        //正常处理逻辑
                        String name = et_dialog.getText() + "";
                        //正常处理逻辑判断列表是否存在填写值，验重
                        for (Object o : data) {
                            if (name.equals(o + "")) {
                                ToastUtils.showToast(getActivity(), "输入添加信息重复");
                                return;
                            }
                        }
                        if (name.equals("")) {
                            ToastUtils.showToast(getActivity(), "请输入添加信息");
                        } else {
                            if (isfirstadd[0]) {
                                data.add(name);
                                isfirstadd[0] = false;
                            } else {
                                if (data.size() > 0) {
                                    data.remove(data.size() - 1);
                                    data.add(name);
                                }
                            }
                            if (X == 1) {
                                if (data_name.get(Y).getName_one().equals("制造厂家")) {
                                    location_Made_company = name;
                                    data.clear();
                                    data.add(name);
                                }
                                if (data_name.get(Y).getName_one().equals("模块名称")) {
                                    data.clear();
                                    data.add(name);
                                }
                                if (data_name.get(Y).getName_one().equals("选配功能")) {
                                    data.clear();
                                    data.add(name);
                                    location_select_work = name;
                                }
                                if (data_name.get(Y).getName_one().equals("保护类型")) {
                                    data.clear();
                                    data.add(name);
                                    location_protect_what_kind = name;
                                }
                            } else if (X == 2) {
                                if (data_name.get(Y).getName_two().equals("保护型号")) {
                                    //新增验重
                                    boolean xh_rep = true;
                                    if (data.size() > 0) {
                                        for (int i = 0; i < data.size() - 1; i++) {
                                            if (name.equals(data.get(i) + "")) {
                                                xh_rep = false;
                                            }
                                        }
                                    }
                                    //不重复就执行添加逻辑
                                    if (xh_rep) {
                                        location_protect_type_m = name;
                                        data.clear();
                                        if (six_one && six_one_details.equals("2013版")) {
                                            data.add(name);
                                        } else {
                                            data.add("不分模块：" + name);
                                            data.add("分模块：" + name);
                                            model_data_type.clear();
                                            model_data_type.add("非六统一，不分模块");
                                            model_data_type.add("非六统一，分模块");
                                        }
                                    } else {
                                        ToastUtils.showToast(getActivity(), "保护型号重复");
                                    }
                                }
                                if (data_name.get(Y).getName_two().equals("一次设备名称")) {
                                    data.clear();
                                    data.add(name);
                                }
                                if (data_name.get(Y).getName_two().equals("软件版本")) {
                                    data.clear();
                                    if (name.contains(",")) {
                                        String[] bbjym = name.split(",");
                                        if (bbjym.length > 1) {
                                            data.add("版本：" + bbjym[0] + "，校验码：" + bbjym[1]);
                                        } else {
                                            data.add("版本：" + bbjym[0] + "，校验码：无");
                                        }
                                    } else if (name.contains("，")) {
                                        String[] bbjym = name.split("，");
                                        if (bbjym.length > 1) {
                                            data.add("版本：" + bbjym[0] + "，校验码：" + bbjym[1]);
                                        } else {
                                            data.add("版本：" + bbjym[0] + "，校验码：无");
                                        }
                                    } else {
                                        data.add("版本：" + name + "，校验码：无");
                                    }
                                }
                            } else if (X == 3) {
                                if (data_name.get(Y).getName_three().equals("保护分类")) {
                                    data.clear();
                                    data.add(name);
                                    location_protect_kind = name;
                                }
                                if (data_name.get(Y).getName_three().equals("ICD文件名")) {
                                    data.clear();
                                    data.add(name);
                                    location_ICD = name;
                                }
                                if (data_name.get(Y).getName_three().equals("软件版本")) {
                                    data.clear();
                                    if (name.contains(",")) {
                                        String[] bbjym = name.split(",");
                                        if (bbjym.length > 1) {
                                            data.add("版本：" + bbjym[0] + "，校验码：" + bbjym[1]);
                                        } else {
                                            data.add("版本：" + bbjym[0] + "，校验码：无");
                                        }
                                    } else if (name.contains("，")) {
                                        String[] bbjym = name.split("，");
                                        if (bbjym.length > 1) {
                                            data.add("版本：" + bbjym[0] + "，校验码：" + bbjym[1]);
                                        } else {
                                            data.add("版本：" + bbjym[0] + "，校验码：无");
                                        }
                                    } else {
                                        data.add("版本：" + name + "，校验码：无");
                                    }
                                }
                            }
                            selectAdapter.notifyDataSetChanged();
                            ToastUtils.showToast(getActivity(), "添加信息成功");
                        }
                    }
                }
            });
            btn_add.setVisibility(View.GONE);

//           可输入可选择八个， 一次设备名称 ，制造厂家 ，保护型号 ，保护分类
// ，保护类型 ，软件版本 ，选配功能 ，ICD文件名
            if (X == 1) {
                if (data_name.get(Y).getName_one().equals("制造厂家")
                        || data_name.get(Y).getName_one().equals("选配功能")
                        || data_name.get(Y).getName_one().equals("连接器数量")
                        || data_name.get(Y).getName_one().equals("模块名称")
                        || data_name.get(Y).getName_one().equals("跳闸关系") && item_name.size() > 0
                        || data_name.get(Y).getName_one().equals("保护类型")) {
                    btn_add.setVisibility(View.VISIBLE);
                }
            } else if (X == 2) {
                if (data_name.get(Y).getName_two().equals("保护型号")
                        || data_name.get(Y).getName_two().equals("一次设备名称")
                        || data_name.get(Y).getName_two().equals("连接器插座制造厂家")
                        || data_name.get(Y).getName_two().equals("软件版本")) {
                    btn_add.setVisibility(View.VISIBLE);
                }
            } else if (X == 3) {
                if (data_name.get(Y).getName_three().equals("保护分类")
                        || data_name.get(Y).getName_three().equals("ICD文件名")
                        || data_name.get(Y).getName_three().equals("软件版本")
                        || data_name.get(Y).getName_three().equals("连接器插座型号")
                        || data_name.get(Y).getName_three().equals("通道装置型号")
                        ) {
                    btn_add.setVisibility(View.VISIBLE);
                }
            }
            //判断是否为新添型号
            if (location_protect_type_m.equals("")) {
                if (X == 1 && data_name.get(Y).getName_one().equals("保护类型")) {
                    btn_add.setVisibility(View.GONE);
                } else if (X == 3 && data_name.get(Y).getName_three().equals("保护分类")) {
                    btn_add.setVisibility(View.GONE);
                }
            }
            //判断一次设备类型为其他的时候，名称不能输入
            if (X == 2) {
                if (data_name.get(Y).getContent_one().equals("其他")
                        && data_name.get(Y).getName_two().equals("一次设备名称")) {
                    btn_add.setVisibility(View.GONE);
                }
            }

            //软件版本，提醒输入校验码
            if (X == 2) {
                if (data_name.get(Y).getName_two().equals("软件版本")) {
                    et_dialog.setHint("版本和校验码逗号隔开");
                }
            } else if (X == 3) {
                if (data_name.get(Y).getName_three().equals("软件版本")) {
                    et_dialog.setHint("版本和校验码逗号隔开");
                }
            }


            //一次设备名称多选
            if (one_device_type.equals("母线") && Y == 2 && X == 2 ||
                    one_device_type.equals("断路器") &&
                            Y == 2 && X == 2) {
                et_dialog.setHint("可多选");
                if (one_device_name.equals("")) {
                } else {
                    reault = one_device_name;
                    et_dialog.setText(one_device_name + "");
                }
                btn_add.setText("确认");
                btn_add.setVisibility(View.VISIBLE);
                btn_search.setVisibility(View.VISIBLE);
//              跳闸关系多选
            } else if (Y == data_name.size() - 3 - Link_device_num && X == 1) {
                et_dialog.setHint("可多选");
                if (relationship.equals("")) {
                } else {
                    KGBHreault = relationship;
                    et_dialog.setText(relationship + "");
                }
                btn_add.setText("确认");
                btn_add.setVisibility(View.VISIBLE);
                btn_search.setVisibility(View.VISIBLE);

            } else {
                btn_add.setText("添加");
//                btn_add.setVisibility(View.VISIBLE);
//                btn_search.setVisibility(View.GONE);
            }

            //判断跳闸关系，列表没值的时候，不能添加
            if (X == 1) {
                if (data_name.get(Y).getName_one().equals("跳闸关系") && data.size() == 0) {
                    btn_add.setVisibility(View.GONE);
                }
            }

            //保护名称重复，手动修改逻辑处理
            if (X == 10) {
                btn_search.setVisibility(View.GONE);
                btn_add.setVisibility(View.VISIBLE);
                btn_add.setText("确认");
                et_dialog.setText(protect_name);
            }


//          数据按字母排序
//            Collections.sort(data, new PinyinComparator());
            selectAdapter = new DeviceSelectAdapter(getActivity(), data);
            lv_dialog.setAdapter(selectAdapter);

            lv_dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    ToastUtils.showToast(getActivity(), "当前选择+" + Y);
                    //是否六统一设备逻辑处理
                    if (Y == 0) {
//                        //联动制造厂家，保护型号,清空,制造厂家不清空
//                        RemoveChoiceTypeData();
//                        protect_type_m = "";
//                        data_name.get(add_protect_type + add_device_type + 4).setContent_two("");
                        if (X == 1) {
                            data_name.get(Y).setContent_one(data.get(position) + "");
                            if (data.get(position).equals("是") && !six_one) {
                                data_name.get(Y).setNum(2);
                                data_name.get(Y).setName_two("六统一标准版本");
                                data_name.get(Y).setContent_two("");
                                six_one = true;
                                six_one_details = "";

                            } else if (data.get(position).equals("否") && six_one) {
                                data_name.get(Y).setNum(1);
                                six_one = false;
                                six_one_details = "";

                            }
                        } else if (X == 2 && !(six_one_details + "").equals(data.get(position) + "")) {
                            if (six_one_details.equals("2013版") || (data.get(position) + "").equals("2013版")) {
                                //联动制造厂家，保护型号,清空,制造厂家不清空
                                RemoveChoiceTypeData();
                                protect_type_m = "";
                                data_name.get(add_protect_type + add_device_type + 4).setContent_two("");
                            }
                            data_name.get(Y).setContent_two(data.get(position) + "");
                            six_one_details = data.get(position) + "";
                        }
                    }
                    //是否单位名称逻辑处理
                    else if (Y == 1) {
                        //单位名称逻辑处理
                        if (X == 1 && !(company_name + "").equals(data.get(position) + "")) {
                            data_name.get(Y).setNum(3);
//                            data_name.get(Y).setName_one("单位名称");
                            data_name.get(Y).setContent_one(data.get(position) + "");
                            data_name.get(Y).setContent_two("");
                            data_name.get(Y).setContent_three("");

                            company_name = data.get(position) + "";
                            //影响调度单位和厂站名称
                            company_location_name = "";
                            control_company_name = "";
                        }//调度单位逻辑处理
                        else if (X == 2) {
                            data_name.get(Y).setContent_two(data.get(position) + "");
                            company_location_name = data.get(position) + "";
                        }//厂站名称逻辑处理
                        else if (X == 3 && !(control_company_name + "").equals(data.get(position) + "")) {
                            //保护名称置空
                            clean_protect_name();
//                            data_name.get(Y).setContent_three("厂站名称内容");
                            data_name.get(Y).setContent_three(data.get(position) + "");
                            control_company_name = data.get(position) + "";
                        }

                    }
                    //一次设备逻辑处理
                    else if (Y == 2) {
                        //保护名称置空
//                        clean_protect_name();
                        //一次设备类型
                        if (X == 1 && !(one_device_type + "").equals(data.get(position) + "")) {
                            //保护名称置空
                            clean_protect_name();
                            data_name.get(Y).setContent_one(data.get(position) + "");
                            data_name.get(Y).setContent_two("");
                            data_name.get(Y).setContent_three("");
                            one_device_type = data.get(position) + "";
                            //影响一次设备名称和电压等级
                            one_device_name = "";
                            electric_level = "";
                            reault = "";
                            KGBHreault = "";
                            //影响跳闸关系
                            data_name.get(data_name.size() - 3 - Link_device_num).setContent_one("");
                            relationship = "";

                            //一次设备名称
                        } else if (X == 2 && !(one_device_name + "").equals(data.get(position) + "")) {
                            //保护名称置空
                            clean_protect_name();
                            data_name.get(Y).setContent_two(data.get(position) + "");
                            one_device_name = data.get(position) + "";
                            //电压等级
                            setDydj(one_device_name);
                            data_name.get(Y).setContent_three(electric_level + "");
                            //联动国调和型号
                            int dy = Integer.parseInt(electric_level);
                            if (company_type.equals("智能站") && dy >= 110 || dy >= 220) {
                                data_name.get(Y + add_protect_type + add_device_type + 2).setContent_four("是");
                                isGD = "是";
                                //判断型号是否包含，否则清空型号
                                boolean iscleanxh = true;
                                if (protect_type_m != null && !protect_type_m.equals("") && !protect_type_m.equalsIgnoreCase("null")) {
                                    List<String> item = DeviceDateilsUtils.DeviceDateilsFind("保护型号", getActivity());
                                    for (String s : item) {
                                        if (s.equals(protect_type_m)) {
                                            iscleanxh = false;
                                        }
                                    }
                                }
                                if (iscleanxh) {
                                    protect_type_m = "";
                                    data_name.get(Y + add_protect_type + add_device_type + 2).setContent_two(protect_type_m);
                                }
                            }
                        } else if (X == 3 && !(electric_level + "").equals(data.get(position) + "")) {
                            //保护名称置空
                            clean_protect_name();
                            data_name.get(Y).setContent_three(data.get(position) + "");
                            electric_level = data.get(position) + "";
                            int dy = Integer.parseInt(electric_level);
                            //联动国调和型号
                            if (company_type.equals("智能站") && dy >= 110 || dy >= 220) {
                                data_name.get(Y + add_protect_type + add_device_type + 2).setContent_four("是");
                                isGD = "是";
                                //判断型号是否包含，否则清空型号
                                boolean iscleanxh = true;
                                if (protect_type_m != null && !protect_type_m.equals("") && !protect_type_m.equalsIgnoreCase("null")) {
                                    List<String> item = DeviceDateilsUtils.DeviceDateilsFind("保护型号", getActivity());
                                    for (String s : item) {
                                        if (s.equals(protect_type_m)) {
                                            iscleanxh = false;
                                        }
                                    }
                                }
                                if (iscleanxh) {
                                    protect_type_m = "";
                                    data_name.get(Y + add_protect_type + add_device_type + 2).setContent_two(protect_type_m);
                                }
//                                protect_type_m = "";
//                                data_name.get(Y + add_protect_type + add_device_type + 2).setContent_two(protect_type_m);
                            }
                        }
                    }
//

//                    保护类别处理逻辑
                    if (Y == 3 && X == 1 && !(protect_type + "").equals(data.get(position) + "")) {
                        //预先处理下级逻辑是否有联动
                        RemoveDeciveData();
                        RemoveLoadTypeData();
                        //默认保护类别细化显示
                        data_name.get(Y).setName_two("保护类别细化");
                        data_name.get(Y).setContent_two("");
                        //--- 通用处理逻辑 ---
                        data_name.get(Y).setNum(2);
                        data_name.get(Y).setContent_one(data.get(position) + "");
                        data_name.get(Y).setContent_two("");
                        data_name.get(Y).setContent_three("");
                        protect_type = data.get(position) + "";
                        //保护类别影响
                        //保护类别影响保护型号，保护分类，保护类型
                        protect_type_m = "";
                        protect_kind = "";
                        protect_what_kind = "";
                        data_name.get(Y + 1).setContent_two("");
                        data_name.get(Y + 1).setContent_three("");
                        data_name.get(Y + 2).setContent_one("");

                        model_data_type_get_code = "";
                        //保护类别影响，故障录波器，测距形式，调度主，保护类别细化，设备类型，设备功能配置
                        machine_type = "";
                        test_type = "";
                        isask = "";
                        protect_type_details = "";
                        device_type = "";
                        device_work_set = "";
                        //保护名称置空
                        clean_protect_name();
                        //---一次设备为其他处理问题 ---
                        if (one_device_type.equals("其他") && one_device_name.equals("其他")) {
                            //默认处理，将一次设备类型和一次设备名称为其他置为空
//                            data_name.get(Y-1).setContent_one("");
//                            one_device_type = "";
//                            data_name.get(Y-1).setContent_two("");
//                            one_device_name = "";
                        }
                        //---差异化处理逻辑 ---
                        if (data.get(position).equals("线路保护")) {

                            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                            DeviceDetailsNameItem item2 = new DeviceDetailsNameItem();
                            item.setNum(4);
                            item.setName_one("通道类型");
                            item.setContent_one("");
                            item.setName_two("是否复用");
                            item.setContent_two("");
                            item.setName_three("通道装置型号");
                            item.setContent_three("");
                            data_name.add(4, item);
                            add_protect_type++;
                            isroadtype = true;

                        } else if (data.get(position).equals("故障录波器")) {
                            data_name.get(Y).setNum(3);
                            data_name.get(Y).setName_three("故障录波器类型");
                            data_name.get(Y).setContent_three("");

                            //默认处理，将一次设备类型和一次设备名称改为其他
                            data_name.get(Y - 1).setContent_one("其他");
                            one_device_type = "其他";
                            data_name.get(Y - 1).setContent_two("其他");
                            one_device_name = "其他";


                        } else if (data.get(position).equals("故障测距装置")) {
                            data_name.get(Y).setNum(3);
                            data_name.get(Y).setName_three("测距形式");
                            data_name.get(Y).setContent_three("");

                            //默认处理，将一次设备类型和一次设备名称改为其他
                            data_name.get(Y - 1).setContent_one("其他");
                            one_device_type = "其他";
                            data_name.get(Y - 1).setContent_two("其他");
                            one_device_name = "其他";


                        } else if (data.get(position).equals("保护故障信息系统子站")) {
                            data_name.get(Y).setNum(3);
                            data_name.get(Y).setName_three("是否接入调度主站");
                            data_name.get(Y).setContent_three("是");

                            //默认处理，将一次设备类型和一次设备名称改为其他
                            data_name.get(Y - 1).setContent_one("其他");
                            one_device_type = "其他";
                            data_name.get(Y - 1).setContent_two("其他");
                            one_device_name = "其他";

                        } else if (data.get(position).equals("安全自动装置")) {
                            data_name.get(Y).setNum(2);
                            data_name.get(Y).setName_two("设备类型");
                            data_name.get(Y).setContent_two("");

                            //默认处理，将一次设备类型和一次设备名称改为其他
                            data_name.get(Y - 1).setContent_one("其他");
                            one_device_type = "其他";
                            data_name.get(Y - 1).setContent_two("其他");
                            one_device_name = "其他";


                        } else if (data.get(position).equals("过电压及远方跳闸保护")) {
                            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                            item.setNum(4);
                            item.setName_one("通道类型");
                            item.setContent_one("");
                            item.setName_two("是否复用");
                            item.setContent_two("");
                            item.setName_three("通道装置型号");
                            item.setContent_three("");
                            data_name.add(4, item);
                            add_protect_type++;
                            isroadtype = true;
                        }

                    }

//搜索输入
                    //设备类型处理逻辑
                    if (Y == 3 && X == 2) {
                        //通用处理逻辑
//                        data_name.get(Y).setNum(2);
                        data_name.get(Y).setContent_two(data.get(position) + "");
                        RemoveDeciveData();
                        if (data_name.get(Y).getName_two().equals("设备类型")) {
                            data_name.get(Y).setNum(2);
                            //设备类型传值处理
                            device_type = data.get(position) + "";
                            if (data.get(position).equals("安全稳定控制装置")) {
                                DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                                item.setNum(6);
                                item.setName_one("安控系统调度名");
                                item.setContent_one("");
                                item.setName_two("安控站点类型");
                                item.setContent_two("");
                                item.setName_three("通道装置型号");
                                item.setContent_three("");
                                data_name.add(4, item);
                                add_device_type++;
                                akxt_id.add("");
                                isdevice_type = true;
                            } else if (data.get(position).equals("频率电压紧急控制装置")) {
                                data_name.get(Y).setNum(3);
                                data_name.get(Y).setName_three("设备功能配置");
                                data_name.get(Y).setContent_three("");
                            } else {
//                            上级处理逻辑未添加，重复代码可以封装复用
                                data_name.get(Y).setNum(2);
                            }
                        } else {
                            //保护类别细化传值处理
//                            data_name.get(Y).setNum(2);
                            protect_type_details = data.get(position) + "";
                        }

                    }
                    //保护类别，三级逻辑处理，最终赋值
                    if (Y == 3 && X == 3) {
                        data_name.get(Y).setContent_three(data.get(position) + "");
//                        故障录波器，是否调度主，测距
                        if (data_name.get(Y).getName_three().equals("故障录波器类型")) {
                            machine_type = data.get(position) + "";
                        } else if (data_name.get(Y).getName_three().equals("测距形式")) {
                            test_type = data.get(position) + "";
                        } else if (data_name.get(Y).getName_three().equals("是否接入调度主站")) {
                            isask = data.get(position) + "";
                        } else if (data_name.get(Y).getName_three().equals("设备功能配置")) {
                            device_work_set = data.get(position) + "";
                        }
                    }

                    //通道类型处理逻辑，内容值，最终保持，循环获取
                    if (isroadtype) {
                        if (Y > 3 && Y <= 3 + add_protect_type) {
                            for (int i = 0; i < add_protect_type; i++) {
                                if (Y == 4 + i) {
                                    if (X == 1) {
//                                        data_name_load.get(i).setContent_one(data.get(position) + "");
                                        data_name.get(Y).setContent_one(data.get(position) + "");
                                    } else if (X == 2) {
                                        data_name.get(Y).setContent_two(data.get(position) + "");
                                    } else {
//                                        data_name_load.get(i).setContent_three(data.get(position) + "");
                                        data_name.get(Y).setContent_three(data.get(position) + "");
                                    }
                                }
                            }
                        }
                    }


                    //安控处理逻辑
                    if (isdevice_type) {
                        if (Y > 3 && Y <= 3 + add_device_type) {
                            for (int i = 0; i < add_device_type; i++) {
                                if (Y == 4 + i) {
                                    if (X == 1) {
                                        data_name.get(Y).setContent_one(data.get(position) + "");
                                        //获取安控名ID
                                        akxt_id.set(i, akxt_id_list.get(position));
                                    } else if (X == 2) {
                                        data_name.get(Y).setContent_two(data.get(position) + "");
                                    }
                                }
                            }
                        }
                    }


                    //制造厂家逻辑处理
                    if (Y == add_protect_type + add_device_type + 4) {
                        if (X == 1) {
                            if (!(made_company + "").equals(data.get(position) + "")) {

                                data_name.get(Y).setContent_one(data.get(position) + "");
                                made_company = data.get(position) + "";
                                //制造厂家影响保护型号，保护分类，保护类型
                                protect_type_m = "";
                                protect_kind = "";
                                protect_what_kind = "";
                                data_name.get(Y).setContent_two("");
                                data_name.get(Y).setContent_three("");
                                data_name.get(Y + 1).setContent_one("");

                                model_data_type_get_code = "";
                            }
                        } else if (X == 2) {
//                            //保护名称置空
//                            clean_protect_name();
//                            data_name.get(Y).setContent_two(data.get(position) + "");
//                            protect_type_m = data.get(position) + "";
//
//                            //保护分类和类型置空
//                            protect_kind = "";
//                            protect_what_kind = "";
//                            data_name.get(Y).setContent_three("");
//                            data_name.get(Y + 1).setContent_one("");
                        } else if (X == 3) {
                            data_name.get(Y).setContent_three(data.get(position) + "");
                            protect_kind = data.get(position) + "";
                        } else {
                            if (!(isGD + "").equals(data.get(position) + "")) {

                                if (isGD == null || isGD.equals("") || isGD.equalsIgnoreCase("null") || !isGD.equals(data.get(position) + "")) {
                                    //影响保护型号，保护分类，保护类型
                                    protect_type_m = "";
                                    protect_kind = "";
                                    protect_what_kind = "";
                                    data_name.get(Y).setContent_two("");
                                    data_name.get(Y).setContent_three("");
                                    data_name.get(Y + 1).setContent_one("");
                                    //保护名称置空
                                    clean_protect_name();
                                }
                                data_name.get(Y).setContent_four(data.get(position) + "");
                                isGD = data.get(position) + "";
                            }
                        }

                    }

                    //                    上级逻辑
                    //保护型号处理逻辑
                    if (Y == add_device_type + add_protect_type + 4 && X == 2 && !(protect_type_m + "").equals(data.get(position) + "")) {
                        //保护名称置空
                        clean_protect_name();
                        data_name.get(Y).setContent_two(data.get(position) + "");
                        protect_type_m = data.get(position) + "";

                        //保护分类和类型置空
                        protect_kind = "";
                        protect_what_kind = "";
                        data_name.get(Y).setContent_three("");
                        data_name.get(Y + 1).setContent_one("");

                        RemoveChoiceTypeData();
                        String name = data.get(position) + "";
                        protect_type_m = name + "";
                        if (!location_protect_type_m.equals("")) {
                            //截取，分模块：型号，型号值
                            String[] ss = name.split("：");
                            if (ss.length == 2) {
                                protect_type_m = ss[1];
                                data_name.get(Y).setContent_two(protect_type_m + "");
                                location_protect_type_m = protect_type_m;
                            }
                        }
                        //转行型号code，点击进行转换
                        model_data_type_code.clear();
                        if (model_data_type_code_xh.size() > 0) {
                            model_data_type_code.addAll(model_data_type_code_xh);
                        }
                        //新添加没有code
                        if (location_protect_type_m.equals(protect_type_m)) {
                            model_data_type_code.clear();
                            model_data_type_get_code = "";
                        }
                        //是否六统一
                        if (six_one && six_one_details.equals("2013版")) {
                            RemoveChoiceTypeData();
                            data_name.get(Y + 1).setNum(1);
                            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                            item.setNum(3);
                            item.setName_one("选配功能");
                            item.setContent_one("");
                            item.setName_two("软件版本");
                            item.setContent_two("");
                            item.setName_three("ICD文件名");
                            item.setContent_three("");
                            data_name.add(add_device_type + add_protect_type + 6, item);
                            ischoice_type = true;
                            //获取码
                            if (model_data_type_code.size() > 0) {
                                model_data_type_get_code = model_data_type_code.get(position);
                            }
                            //重置选配，软件版本，ICD
                            select_work = "";
                            ver_info = "";
                            ICD = "";
                        } else {
                            //分模块型号
                            if (model_data_type.get(position).equals("非六统一，分模块")) {
                                //获取码
                                if (model_data_type_code.size() > 0) {
                                    model_data_type_get_code = model_data_type_code.get(position);
                                }

                                data_name.get(Y + 1).setNum(1);
                                DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                                item.setNum(8);
                                item.setName_one("模块名称");
                                item.setContent_one("");
                                item.setName_two("软件版本");
                                item.setContent_two("");
                                item.setName_three("生成日期");
                                item.setContent_three("");
                                data_name.add(add_device_type + add_protect_type + 6, item);
                                add_model_type++;
                                ismodel_type = true;
                            } else {
                                //不分模块型号
                                RemoveChoiceTypeData();
                                data_name.get(Y + 1).setNum(3);
                                data_name.get(Y + 1).setContent_two("");
                                data_name.get(Y + 1).setContent_three("");
                                ver_info = "";

                                //获取码
                                if (model_data_type_code.size() > 0) {
                                    model_data_type_get_code = model_data_type_code.get(position) + "";
                                }
                            }
                        }
                        //保护分类和保护类型联动，六统一2013或者保护型号表
                        if (protect_type.equals("") || made_company.equals("") || protect_type_m.equals("")) {
                        } else {
                            boolean isSIX = DeviceOneFragment.instance.six_one();
                            boolean is_2013 = DeviceOneFragment.instance.six_one_details.equals("2013版");
                            if (isSIX && is_2013) {
                                List<LTYSBXH> list = util.getLTYSBXHBHFLOrBHLX("BHFL", made_company, protect_type, protect_type_m);
                                if (list != null && list.size() > 0) {
                                    if (list.get(0).getBhfl() != null && !list.get(0).getBhfl().equals("") && !list.get(0).getBhfl().equals("null")
                                            && list.get(0).getBhlx() != null && !list.get(0).getBhlx().equals("") && !list.get(0).getBhlx().equals("null")
                                            ) {
                                        protect_kind = list.get(0).getBhfl();
                                        protect_what_kind = list.get(0).getBhlx();
                                        data_name.get(Y).setContent_three(protect_kind + "");
                                        data_name.get(Y + 1).setContent_one(protect_what_kind + "");
                                    }
                                }
                            } else {
                                List<BHSBXHB> list = util.getBHFLOrBHLX("BHFL", made_company, protect_type, protect_type_m);
                                if (list != null && list.size() > 0) {
                                    if (list.get(0).getBhfl() != null && !list.get(0).getBhfl().equals("") && !list.get(0).getBhfl().equals("null")
                                            && list.get(0).getBhlx() != null && !list.get(0).getBhlx().equals("") && !list.get(0).getBhlx().equals("null")
                                            ) {
                                        protect_kind = list.get(0).getBhfl();
                                        protect_what_kind = list.get(0).getBhlx();
                                        data_name.get(Y).setContent_three(protect_kind + "");
                                        data_name.get(Y + 1).setContent_one(protect_what_kind + "");
                                    }
                                }
                            }
                        }

                    }

                    //选配功能处理逻辑，取值，赋值
                    if (ischoice_type) {
                        if (Y == add_device_type + add_protect_type + 6) {
                            if (X == 1 && !(select_work + "").equals(data.get(position) + "")) {//选配功能
                                data_name.get(Y).setContent_one(data.get(position) + "");
                                select_work = data.get(position) + "";
                                //联动软件版本
                                data_name.get(Y).setContent_two("");
                                ver_info = "";
                            } else if (X == 2 && !(ver_info + "").equals(data.get(position) + "")) {//软件版本，需要联动ICD
                                data_name.get(Y).setContent_two(data.get(position) + "");
                                ver_info = data.get(position) + "";
                                //联动ICD
                                data_name.get(Y).setContent_three("");
                                ICD = "";
                            } else if (X == 3) {//ICD文件名
                                data_name.get(Y).setContent_three(data.get(position) + "");
                                ICD = data.get(position) + "";
                            }
                        }
                    }


                    //分模块添加处理逻辑
                    if (ismodel_type) {
                        if (Y > 5 + add_device_type + add_protect_type && Y <= 5 + add_device_type + add_protect_type + add_model_type) {
                            for (int i = 0; i < add_model_type; i++) {
                                if (Y == 6 + add_device_type + add_protect_type + i) {
                                    if (X == 1) {
                                        if (!(data_name.get(Y).getContent_one() + "").equals(data.get(position) + "")) {

                                            data_name.get(Y).setContent_one(data.get(position) + "");
                                            //选择模块，默认清空此模块下的版本
                                            data_name.get(Y).setContent_two("");
                                        }
                                    } else if (X == 2) {
                                        data_name.get(Y).setContent_two(data.get(position) + "");
                                    } else {
                                        data_name.get(Y).setContent_three(data.get(position) + "");
                                    }
                                }
                            }
                        }
                    }

                    //保护类型逻辑处理,逻辑获取值，不是反向处理
                    if (Y == add_device_type + add_protect_type + 5) {
                        if (X == 1) {
                            data_name.get(Y).setContent_one(data.get(position) + "");
                            protect_what_kind = data.get(position) + "";
                        } else if (X == 2) {
                            data_name.get(Y).setContent_two(data.get(position) + "");
                            ver_info = data.get(position) + "";
                        } else {
                            made_day = data.get(position) + "";
                        }
                    }
                    //反向逻辑处理
                    //跳闸关系逻辑处理
                    else if (Y == data_name.size() - 3 - Link_device_num) {
                        if (X == 1) {
                            data_name.get(Y).setContent_one(data.get(position) + "");
                            relationship = data.get(position) + "";
                        } else if (X == 2) {
                            if (!(protect_config + "").equals(data.get(position) + "")) {
                                //保护套别
                                data_name.get(Y).setContent_two(data.get(position) + "");
                                protect_config = data.get(position) + "";
                                clean_protect_name();
                            }
                        } else {
                            //名称属性
                            data_name.get(Y).setContent_three(data.get(position) + "");
                            name_type = data.get(position) + "";

                        }
                    }
                    //保护名称逻辑处理
                    else if (Y == data_name.size() - 2 - Link_device_num) {
                        data_name.get(Y).setContent_one(data.get(position) + "");
                        protect_name = data.get(position) + "";
                        protect_name_rep = false;
                        makesure_protect_name(0);
                    }
                    //就地化装置
                    else if (Y == data_name.size() - 1 - Link_device_num) {
                        data_name.get(Y).setContent_one(data.get(position) + "");
                        if (data_name.get(Y).getContent_one().equals("是")) {
                            if (!isLink_device) {
                                isLink_device = true;
                                DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                                item.setNum(3);
                                item.setName_one("连接器数量");
                                item.setContent_one("");
                                item.setName_two("连接器插座制造厂家");
                                item.setContent_two("");
                                item.setName_three("连接器插座型号");
                                item.setContent_three("");
                                data_name.add(data_name.size(), item);
                                Link_device_num = 1;

                            }
                        } else {
                            if (data_name.get(data_name.size() - 1).getName_one().equals("连接器数量")) {
                                isLink_device = false;
                                Link_device_num = 0;
                                data_name.remove(data_name.size() - 1);
                                //连接器重置
                                ljqsl = "";
                                ljqzzcj = "";
                                ljqczxh = "";
                            }
                        }
                    }
                    //连接器
                    if (isLink_device) {
                        if (Y == data_name.size() - Link_device_num) {
                            if (X == 1) {
                                //连接器数量
                                data_name.get(Y).setContent_one(data.get(position) + "");
                                ljqsl = data.get(position) + "";
                            } else if (X == 2) {
                                //连接器插座制造厂家
                                data_name.get(Y).setContent_two(data.get(position) + "");
                                ljqzzcj = data.get(position) + "";
                            } else {
                                //连接器插座制造厂家
                                data_name.get(Y).setContent_three(data.get(position) + "");
                                ljqczxh = data.get(position) + "";
                            }
                        }
                    }

                    //一次设备名称多选
                    if (one_device_type.equals("母线") && Y == 2 && X == 2 ||
                            one_device_type.equals("断路器") &&
                                    Y == 2 && X == 2) {
                        String datas = data.get(position) + "";
                        if (reault.contains(datas)) {
                            ToastUtils.showToast(getActivity(), "选择重复");
                        } else {
                            if (reault.equals("")) {
                                reault = datas;
                            } else {
                                reault = reault + "," + datas;
                            }
                            et_dialog.setText(reault);
//                                带出电压等级
                            setDydj(datas);
                            data_name.get(Y).setContent_three(electric_level + "");
                        }
                        //跳闸关系多选
                    } else if (Y == data_name.size() - 3 - Link_device_num && X == 1) {
                        String datas = data.get(position) + "";
                        if (KGBHreault.contains(datas)) {
                            ToastUtils.showToast(getActivity(), "选择重复");
                        } else {
                            if (KGBHreault.equals("")) {
                                KGBHreault = datas;
                            } else {
                                KGBHreault = KGBHreault + "," + datas;
                            }
                            et_dialog.setText(KGBHreault);
                        }
                    } else {
                        dialog.dismiss();
                        adapter.notifyDataSetChanged();
                    }
                }
            });

            //取消
            tv_dialog_title_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setCanceledOnTouchOutside(true);
//            tv_dialog_title.setText(content);
//            tv_dialog_content.setText(title);
            dialog.show();
        }
    }

    private void clean_protect_name() {
        protect_name = "";
        for (DeviceDetailsNameItem item : data_name) {
            if (item.getName_one().equals("保护名称")) {
                item.setContent_one("");
                protect_name = "";
            }
        }
    }

    //回退删除手动添加设备类型item联动
    private void RemoveDeciveData() {
        if (isdevice_type) {
            isdevice_type = false;
            if (add_device_type > 0) {

                for (int i = 0; i < add_device_type; i++) {
                    data_name.remove(4);
                }
                add_device_type = 0;
                akxt_id.clear();
            }
        }
        adapter.notifyDataSetChanged();
    }

    //回退删除手动添加通道类型item联动
    private void RemoveLoadTypeData() {
        if (isroadtype) {
            isroadtype = false;
            if (add_protect_type > 0) {
                for (int i = 0; i < add_protect_type; i++) {
                    data_name.remove(4);
                }
                add_protect_type = 0;
            }
        }

        adapter.notifyDataSetChanged();
    }

    //回退删除手动添加模块名称item联动,反向逻辑处理
    private void RemoveChoiceTypeData() {
        //删除多添加的ICD条目
        if (ischoice_type) {
            ischoice_type = false;
            data_name.remove(data_name.size() - 4 - Link_device_num);
        }
        if (ismodel_type) {
            ismodel_type = false;
            if (add_model_type > 0) {
                for (int i = 0; i < add_model_type; i++) {
                    int num = data_name.size() - 4 - Link_device_num;
                    data_name.remove(num);
                }
                add_model_type = 0;
            }
        }
        if (add_model_type > 0) {
            for (int i = 0; i < add_model_type; i++) {
                int num = data_name.size() - 4 - Link_device_num;
                data_name.remove(num);
            }
            add_model_type = 0;
        }
        adapter.notifyDataSetChanged();
    }

    //添加信息初始化方法
    public void add_data() {
        for (int i = 0; i < 9; i++) {
//            data.add("数字+"+i);
            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
            if (i == 0) {
                item.setNum(1);
                item.setName_one("是否六统一设备");
                item.setContent_one("否");
            } else if (i == 1) {
                item.setNum(3);
                item.setName_one("单位名称");
                item.setContent_one("必填项");
                item.setName_two("调度单位");
                item.setContent_two("必填项");
                item.setName_three("厂站名称");
                item.setContent_three("必填项");
                CZCS czcs = util.getCZCSByGLDW();
                company_name = czcs.getGldw();
                item.setContent_one(company_name + "");
                control_company_name = czcs.getCzmc();
                item.setContent_three(control_company_name + "");
                company_location_name = util.getDDDWByDWMC(company_name).get(0).getDDDW();
                item.setContent_two(company_location_name + "");

                company_type = czcs.getBdzlx();
            } else if (i == 2) {
                item.setNum(3);
                item.setName_one("一次设备类型");
                item.setContent_one("必填项");
                item.setName_two("一次设备名称");
                item.setContent_two("必填项");
                item.setName_three("电压等级");
                item.setContent_three("必填项");
            } else if (i == 3) {
                item.setNum(2);
                item.setName_one("保护类别");
                item.setContent_one("必填项");
                item.setName_two("保护类别细化");
                item.setContent_two("必填项");
//                item.setName_one("电压等级");
//                item.setContent_one("35");
            } else if (i == 4) {
                item.setNum(3);
                item.setName_one("制造厂家");
                item.setContent_one("必填项");
                item.setName_two("保护型号");
                item.setContent_two("必填项");
                item.setName_three("保护分类");
                item.setContent_three("必填项");
                item.setName_four("是否使用国调标准型号");
                item.setContent_four("是");
                isGD = "是";
            } else if (i == 5) {
                item.setNum(3);
                item.setName_one("保护类型");
                item.setContent_one("");
                item.setName_two("软件版本");
                item.setContent_two("");
                item.setName_three("生成日期");
                item.setContent_three("");
            } else if (i == 6) {
                item.setNum(3);
                item.setName_one("跳闸关系");
                item.setContent_one("");
                item.setName_two("保护套别");
                item.setContent_two("");
                item.setName_three("名称属性");
                item.setContent_three("");
            } else if (i == 7) {
                item.setNum(1);
                item.setName_one("保护名称");
                item.setContent_one("必填项，点击生成");
//                item.setName_two("保护套别");
//                item.setContent_two("无");
//                item.setName_three("名称属性");
//                item.setContent_three("无");
            } else if (i == 8) {
                item.setNum(1);
                item.setName_one("是否就地化装置");
                item.setContent_one("否");
            }
            data_name.add(item);

        }
    }

    //编辑信息初始化方法
    public void change_data() {

        //身份识别码输入,关联
        id_card = newBHPZ.getSfsbm() + "";
        if (id_card.equals("") || id_card.equals("null")) {
            id_card = "";
            input_id_card.setText("");
        } else {
            input_id_card.setText(id_card + "");
        }

        if (DeviceDateilsUtils.IsNotNull(newBHPZ.getSfltysb())) {
            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
            item.setNum(1);
            item.setName_one("是否六统一设备");
            item.setContent_one(newBHPZ.getSfltysb() + "");
            if (newBHPZ.getSfltysb().equals("是")) {
                six_one = true;
            } else {
                six_one = false;
            }
            if (six_one) {
                item.setNum(2);
                item.setName_two("六统一标准版本");
                if (DeviceDateilsUtils.IsNotNull(newBHPZ.getLtybzbb())) {
                    item.setContent_two(newBHPZ.getLtybzbb() + "");
                    six_one_details = newBHPZ.getLtybzbb() + "";
                }
            }
            data_name.add(item);
        }

        DeviceDetailsNameItem item2 = new DeviceDetailsNameItem();
        item2.setNum(3);
        item2.setName_one("单位名称");
        item2.setContent_one("");
        item2.setName_two("调度单位");
        item2.setContent_two("");
        item2.setName_three("厂站名称");
        item2.setContent_three("");


        //单位名称
        if (newBHPZ.getCzgldw() != null) {
            item2.setContent_one(newBHPZ.getCzgldw() + "");
            company_name = newBHPZ.getCzgldw() + "";
        }
        if (newBHPZ.getDddw() != null) {
            item2.setContent_two(newBHPZ.getDddw() + "");
            company_location_name = newBHPZ.getDddw() + "";
        }
        if (newBHPZ.getCzmc() != null) {
            item2.setContent_three(newBHPZ.getCzmc() + "");
            control_company_name = newBHPZ.getCzmc() + "";
        }
        //获取厂站类型
        company_type = newBHPZ.getBdzlx();
        if (company_type == null || company_type.equals("")) {
            company_type = util.getCZCSByGLDW().getBdzlx() + "";
        }
        //都为空值的处理,从扫码里面带出来的
        if (DeviceDetailsActivity.isFromSaoma) {
            CZCS czcs = util.getCZCSByGLDW();
            company_name = czcs.getGldw() + "";
            item2.setContent_one(company_name + "");
            control_company_name = czcs.getCzmc() + "";
            item2.setContent_three(control_company_name + "");
            company_location_name = util.getDDDWByDWMC(company_name).get(0).getDDDW() + "";
            item2.setContent_two(company_location_name + "");

            company_type = czcs.getBdzlx() + "";
        }

        data_name.add(item2);


        DeviceDetailsNameItem item3 = new DeviceDetailsNameItem();
        item3.setNum(3);
        item3.setName_one("一次设备类型");
        item3.setContent_one("");
        item3.setName_two("一次设备名称");
        item3.setContent_two("");
        item3.setName_three("电压等级");
        item3.setContent_three("");


        if (newBHPZ.getYcsblx() != null) {
            item3.setContent_one(newBHPZ.getYcsblx() + "");
            one_device_type = newBHPZ.getYcsblx() + "";
        }
        if (newBHPZ.getYcsbmc() != null) {
            item3.setContent_two(newBHPZ.getYcsbmc() + "");
            one_device_name = newBHPZ.getYcsbmc() + "";
        }
        if (newBHPZ.getDydj() >= 0) {
            item3.setContent_three(newBHPZ.getDydj() + "");
            electric_level = newBHPZ.getDydj() + "";
        }
        data_name.add(item3);

        //保护列表初始化添加
        DeviceDetailsNameItem item4 = new DeviceDetailsNameItem();
        item4.setNum(2);
        item4.setName_one("保护类别");
        item4.setContent_one("必填项");
        item4.setName_two("保护类别细化");
        item4.setContent_two("必填项");
//                item.setName_one("电压等级");
//                item.setContent_one("35");


        if (newBHPZ.getBhlb() != null) {
            item4.setContent_one(newBHPZ.getBhlb() + "");
            protect_type = newBHPZ.getBhlb() + "";
        }
        if (newBHPZ.getBhlbxh() != null) {
            item4.setContent_two(newBHPZ.getBhlbxh() + "");
            if (newBHPZ.getBhlb() != null && newBHPZ.getBhlb().equals("安全自动装置")) {
                item4.setName_two("设备类型");
                device_type = newBHPZ.getBhlbxh();
            } else {
                protect_type_details = newBHPZ.getBhlbxh() + "";
            }
        }
        //设备类型处理，获取值同保护类型细化
        if (newBHPZ.getBhlb() != null && newBHPZ.getBhlb().equals("安全自动装置")) {
//            item4.setName_two("设备类型");
//            device_type =  newBHPZ.getBhlbxh();
        }

        if (newBHPZ.getGzlbqlx() != null && newBHPZ.getBhlb().equals("故障录波器")) {
            item4.setNum(3);
            item4.setName_three("故障录波器类型");
            item4.setContent_three(newBHPZ.getGzlbqlx() + "");
            machine_type = newBHPZ.getGzlbqlx() + "";
        }
        if (newBHPZ.getCjxx() != null && newBHPZ.getBhlb().equals("故障测距装置")) {
            item4.setNum(3);
            item4.setName_three("测距形式");
            item4.setContent_three(newBHPZ.getCjxx() + "");
            test_type = newBHPZ.getCjxx() + "";
        }
        if (newBHPZ.getSfjrzz() != null && newBHPZ.getBhlb().equals("保护故障信息系统子站")) {
            item4.setNum(3);
            item4.setName_three("是否接入调度主站");
            item4.setContent_three(newBHPZ.getSfjrzz() + "");
            isask = newBHPZ.getSfjrzz() + "";
        }
        if (newBHPZ.getSbgnpz() != null && newBHPZ.getBhlb().equals("安全自动装置") && newBHPZ.getBhlbxh().equals("频率电压紧急控制装置")) {
            item4.setNum(3);
            item4.setName_three("设备功能配置");
            item4.setContent_three(newBHPZ.getSbgnpz() + "");
            device_work_set = newBHPZ.getSbgnpz() + "";
        }
        data_name.add(item4);

        if (newBHPZ.getBhlb() != null &&
                newBHPZ.getBhlb().equals("线路保护") ||
                newBHPZ.getBhlb() != null &&
                        newBHPZ.getBhlb().equals("过电压及远方跳闸保护")) {

            /**
             * 获取通道信息
             * @param bhpzId 保护配置Id
             */
//        List<TDXX> getTDXX(String bhpzId);
            List<TDXX> load_data = new ArrayList<>();
            String pzid = newBHPZ.getId() + "";
            if (pzid != null && !pzid.equals("") && !pzid.equals("null")) {
                load_data = util.getTDXX(newBHPZ.getId() + "");

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
                            data_name.add(load_item);
                            add_protect_type++;
                            isroadtype = true;
                        }
                    }
                }
            }
        }

        if (newBHPZ.getBhlb() != null && newBHPZ.getBhlb().equals("安全自动装置") &&
                newBHPZ.getBhlb() != null && newBHPZ.getBhlbxh() != null && newBHPZ.getBhlbxh().equals("安全稳定控制装置")) {
            /**
             * 获取安控信息
             * @param bhpzId 保护配置Id
             */
            List<AKXTGX> akxtgx_data = new ArrayList<>();
            String pzid_ak = newBHPZ.getId() + "";
            if (pzid_ak != null && !pzid_ak.equals("") && !pzid_ak.equals("null")) {
                akxtgx_data = util.getAKXTGX(newBHPZ.getId() + "");
                akxtgx_all = akxtgx_data;


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
                            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                            item.setNum(6);
                            item.setName_one("安控系统调度名");
                            item.setContent_one(akxtm + "");
                            item.setName_two("安控站点类型");
                            item.setContent_two(akxtgx_data.get(i).getAkzdlx() + "");
                            item.setName_three("通道装置型号");
                            item.setContent_three("");
                            data_name.add(4, item);
                            //安控名称存ID
                            akxt_id.add(akxtgx_data.get(i).getAkxtid() + "");
                            add_device_type++;
                            isdevice_type = true;
                        }
                    }
                }
            }
        }


        DeviceDetailsNameItem item5 = new DeviceDetailsNameItem();
        item5.setNum(3);
        item5.setName_one("制造厂家");
        item5.setContent_one("必填项");
        item5.setName_two("保护型号");
        item5.setContent_two("必填项");
        item5.setName_three("保护分类");
        item5.setContent_three("必填项");
        item5.setName_four("是否使用国调标准型号");
        item5.setContent_four("是");
        //国标缺获取值
        isGD = newBHPZ.getUsegddata();

        if (isGD != null && !isGD.equals("null") && !isGD.equals("")) {
            if (isGD.equals("否")) {
                isGD = "否";
            } else {
                isGD = "是";
            }
        } else {
            isGD = "是";
        }
        item5.setContent_four(isGD + "");


        if (newBHPZ.getZzcj() != null) {
            item5.setContent_one(newBHPZ.getZzcj() + "");
            made_company = newBHPZ.getZzcj() + "";
        }
        if (newBHPZ.getBhxh() != null) {
            item5.setContent_two(newBHPZ.getBhxh() + "");
            protect_type_m = newBHPZ.getBhxh() + "";
        }
        if (newBHPZ.getBhfl() != null) {
            item5.setContent_three(newBHPZ.getBhfl() + "");
            protect_kind = newBHPZ.getBhfl() + "";
        }
        data_name.add(item5);

        //保护分类后面跟着保护类型
        DeviceDetailsNameItem item7 = new DeviceDetailsNameItem();
        item7.setNum(3);
        item7.setName_one("保护类型");
        item7.setContent_one("");
        item7.setName_two("软件版本");
        item7.setContent_two("");
        item7.setName_three("生成日期");
        item7.setContent_three("");

        //保护类型
        if (newBHPZ.getBhlx() != null) {
            item7.setContent_one(newBHPZ.getBhlx() + "");
            protect_what_kind = newBHPZ.getBhlx() + "";
        }
        //模块名称和软件版本及生成日期处理后取值判断
        data_name.add(item7);
//        除了2013版之外，都是非六统一
        if (!six_one_details.equals("2013版") && !DeviceDetailsActivity.isFromSaoma) {
//
            //模块名称
            List<BHPZXHBBGX> list = new ArrayList<>();
            list = util.getBHPZXHBBGX(newBHPZ.getId() + "");
            for (BHPZXHBBGX bhpzxhbbgx : list) {
                String data = TimeUtil.formatString2(bhpzxhbbgx.getSCSJ() + "") + "";
                List<BHXHRJBB> bzsjList = util.getBHSBXHRJBBbyCode(bhpzxhbbgx.getRJBBCODE() + "");
                if (bzsjList.size() > 0) {
                    for (BHXHRJBB bhxhrjbb : bzsjList) {
                        if (bhxhrjbb.getBblx() != null && bhxhrjbb.getBblx().equals("非六统一，分模块")) {
                            item7.setNum(1);
                            item7.setContent_three("");
                            item7.setContent_two("");
                            made_day = "";
                            ver_info = "";
                            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                            item.setNum(8);
                            item.setName_one("模块名称");
                            if (bhxhrjbb.getMkmc() != null && !bhxhrjbb.getMkmc().equalsIgnoreCase("null")) {
                                item.setContent_one(bhxhrjbb.getMkmc() + "");
                            }
                            String jym = "";
                            if (bhxhrjbb.getJym() != null) {
                                jym = bhxhrjbb.getJym() + "";
                            }
                            item.setName_two("软件版本");
                            if (bhxhrjbb.getBb() != null && !bhxhrjbb.getBb().equalsIgnoreCase("null")) {
                                item.setContent_two("版本：" + bhxhrjbb.getBb() + "，校验码：" + jym);
                            }
                            item.setName_three("生成日期");
                            item.setContent_three("");
                            if (data != null && !data.equals("") && !data.equals("null")) {
                                item.setContent_three(data + "");
                            }
                            data_name.add(add_device_type + add_protect_type + 6, item);
                            add_model_type++;
                            ismodel_type = true;
                            model_data_type_code.add(bhxhrjbb.getBhxhcode() + "");
                            model_data_type_get_code = bhxhrjbb.getBhxhcode() + "";
                        } else if (bhxhrjbb.getBblx().equals("非六统一，不分模块")) {
                            item7.setNum(3);
                            item7.setContent_three("");
                            item7.setContent_two("");
                            if (bhxhrjbb.getBb() != null && !bhxhrjbb.getBb().equalsIgnoreCase("null")) {
                                model_data_type_get_code = bhxhrjbb.getBhxhcode() + "";
                                String jym = "";
                                if (bhxhrjbb.getJym() != null) {
                                    jym = bhxhrjbb.getJym() + "";
                                }
                                ver_info = "版本：" + bhxhrjbb.getBb() + "，校验码：" + jym + "";
                                item7.setContent_two(ver_info + "");
                                if (data != null && !data.equals("") && !data.equals("null")) {
                                    item7.setContent_three(data + "");
                                    made_day = data;
                                }
                            }
                        }
                    }
                }
            }
            //不存在关系表
            if (list == null || list.size() == 0) {
                List<Object> listcode = util.getBHXH(six_one, six_one_details.equals("2013版"), made_company, protect_type, company_type, electric_level, isGD, protect_type_m);
                if (listcode != null && listcode.size() > 0) {
                    String bblx_type = ((BHSBXHB) listcode.get(0)).getBblx() + "";
                    String bblx_code = ((BHSBXHB) listcode.get(0)).getCode() + "";
                    if (bblx_type.equals("非六统一，分模块")) {
                        item7.setNum(1);
                        item7.setContent_three("");
                        item7.setContent_two("");
                        made_day = "";
                        ver_info = "";
                        DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                        item.setNum(8);
                        item.setName_one("模块名称");
                        item.setContent_one("");
                        item.setName_two("软件版本");
                        item.setContent_two("");
                        item.setName_three("生成日期");
                        item.setContent_three("");
                        data_name.add(add_device_type + add_protect_type + 6, item);
                        add_model_type++;
                        ismodel_type = true;
                        model_data_type_code.add(bblx_code + "");
                        model_data_type_get_code = bblx_code + "";
                    }

                }
            }
            //扫码带入
        } else if (!six_one_details.equals("2013版") && DeviceDetailsActivity.isFromSaoma) {
            List<Object> list = (List<Object>) getActivity().getIntent().getSerializableExtra("BHXHRJBB");
            List<BHXHRJBB> bzsjList = new ArrayList<>();
            for (Object o : list) {
                bzsjList.add((BHXHRJBB) o);
            }
            if (bzsjList.size() > 0) {
                for (BHXHRJBB bhxhrjbb : bzsjList) {
                    String data = TimeUtil.formatString2(bhxhrjbb.getTbsj() + "") + "";
                    if (bhxhrjbb.getBblx() != null && bhxhrjbb.getBblx().equals("非六统一，分模块")) {
                        item7.setNum(1);
                        item7.setContent_three("");
                        item7.setContent_two("");
                        made_day = "";
                        ver_info = "";
                        DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                        item.setNum(8);
                        item.setName_one("模块名称");
                        if (bhxhrjbb.getMkmc() != null && !bhxhrjbb.getMkmc().equalsIgnoreCase("null")) {
                            item.setContent_one(bhxhrjbb.getMkmc() + "");
                        }
                        String jym = "";
                        if (bhxhrjbb.getJym() != null) {
                            jym = bhxhrjbb.getJym() + "";
                        }
                        item.setName_two("软件版本");
                        if (bhxhrjbb.getBb() != null && !bhxhrjbb.getBb().equalsIgnoreCase("null")) {
                            item.setContent_two("版本：" + bhxhrjbb.getBb() + "，校验码：" + jym);
                        }
                        item.setName_three("生成日期");
                        item.setContent_three("");
                        if (data != null && !data.equals("") && !data.equals("null")) {
                            item.setContent_three(data + "");
                        }
                        data_name.add(add_device_type + add_protect_type + 6, item);
                        add_model_type++;
                        ismodel_type = true;
                        model_data_type_code.add(bhxhrjbb.getBhxhcode() + "");
                        model_data_type_get_code = bhxhrjbb.getBhxhcode() + "";
                        if (model_data_type_get_code.equals("") || model_data_type_get_code.equalsIgnoreCase("null")) {
                            model_data_type_get_code = util.getbhxhCode(bhxhrjbb.getCode() + "");
                            model_data_type_code.add(model_data_type_get_code + "");
                        }
                    } else {
                        item7.setNum(3);
                        item7.setContent_three("");
                        item7.setContent_two("");
                        model_data_type_get_code = bhxhrjbb.getBhxhcode() + "";
                        if (model_data_type_get_code.equals("") || model_data_type_get_code.equalsIgnoreCase("null")) {
                             model_data_type_get_code = util.getbhxhCode(bhxhrjbb.getCode() + "");
                            model_data_type_code.add(model_data_type_get_code + "");
                        }
                        String jym = "";
                        if (bhxhrjbb.getJym() != null) {
                            jym = bhxhrjbb.getJym() + "";
                        }
                        if (bhxhrjbb.getBb() != null && !bhxhrjbb.getBb().equalsIgnoreCase("null")) {
                            ver_info = "版本：" + bhxhrjbb.getBb() + "，校验码：" + jym + "";
                            item7.setContent_two(ver_info + "");
                        }
                        if (data != null && !data.equals("") && !data.equals("null")) {
                            item7.setContent_three(data + "");
                            made_day = data;
                        }
                    }
                }
            }
        }
        //六统一的2013版软件版本除外都是非六统一处理
        if (six_one && six_one_details.equals("2013版")) {
            item7.setNum(1);
            item7.setContent_three("");
            item7.setContent_two("");
            made_day = "";
            ver_info = "";
            //选配功能判断，缺逻辑
            DeviceDetailsNameItem item6 = new DeviceDetailsNameItem();
            item6.setNum(3);
            item6.setName_one("选配功能");
            item6.setContent_one("");
            item6.setName_two("软件版本");
            item6.setContent_two("");
            item6.setName_three("ICD文件名");
            item6.setContent_three("");
            ischoice_type = true;

            //选配功能，台账里没有对应数据
            if (newBHPZ.getXp() != null && !newBHPZ.getXp().equalsIgnoreCase("null")) {
                item6.setContent_one(newBHPZ.getXp() + "");
                select_work = newBHPZ.getXp() + "";
            }
            if (newBHPZ.getIcdwjmc() != null && !newBHPZ.getIcdwjmc().equalsIgnoreCase("null")) {
                item6.setContent_three(newBHPZ.getIcdwjmc() + "");
                ICD = newBHPZ.getIcdwjmc() + "";
            }
            List<BHPZXHBBGX> listxp = new ArrayList<>();
            List<LTYSBXH> listxpbb = new ArrayList<>();
            listxp = util.getBHPZXHBBGX(newBHPZ.getId() + "");
            for (BHPZXHBBGX bhpzxhbbgx : listxp) {
                listxpbb = util.getLTYSBXHbyCode(bhpzxhbbgx.getRJBBCODE() + "");
            }
            if (listxpbb.size() > 0) {
                for (LTYSBXH ltysbxh : listxpbb) {
                    if (ltysbxh.getRjbb() != null && !ltysbxh.getRjbb().equals("") && !ltysbxh.getRjbb().equalsIgnoreCase("null")) {
                        model_data_type_get_code = ltysbxh.getCode() + "";
//                        ver_info = "版本：" + ltysbxh.getRjbb() + "，校验码：无";
                        ver_info = ltysbxh.getRjbb() + "";
                        item6.setContent_two(ver_info);

                        DeviceDetailsActivity.instance.ltysbxh_bh = new LTYSBXH();
                        DeviceDetailsActivity.instance.ltysbxh_bh = ltysbxh;
                        DeviceDetailsActivity.instance.code3 = ltysbxh.getCode() + "";
                    }
                }
            }
            //扫码带入的
            if (DeviceDetailsActivity.isFromSaoma) {
                List<Object> list = (List<Object>) getActivity().getIntent().getSerializableExtra("BHXHRJBB");
                if (list != null && list.size() > 0) {
                    LTYSBXH ltysbxh = (LTYSBXH) list.get(0);
                    DeviceDetailsActivity.instance.ltysbxh_bh = new LTYSBXH();
                    DeviceDetailsActivity.instance.ltysbxh_bh = ltysbxh;
                    model_data_type_get_code = ltysbxh.getCode() + "";
                    DeviceDetailsActivity.instance.code3 = ltysbxh.getCode() + "";
                    if (ltysbxh.getRjbb() != null && !ltysbxh.getRjbb().equalsIgnoreCase("null")) {
//                        ver_info = "版本：" + ltysbxh.getRjbb() + "，校验码：无";
                        ver_info = ltysbxh.getRjbb() + "";
                        item6.setContent_two(ver_info);
                    }

                    if (ltysbxh.getXp() != null && !ltysbxh.getXp().equalsIgnoreCase("null")) {
                        item6.setContent_one(ltysbxh.getXp() + "");
                        select_work = ltysbxh.getXp() + "";
                    }
                    if (ltysbxh.getWjmc() != null && !ltysbxh.getWjmc().equalsIgnoreCase("null")) {
                        item6.setContent_three(ltysbxh.getWjmc() + "");
                        ICD = ltysbxh.getWjmc() + "";
                    }
                }
            }

            data_name.add(item6);
        }

        DeviceDetailsNameItem item8 = new DeviceDetailsNameItem();
        item8.setNum(3);
        item8.setName_one("跳闸关系");
        item8.setContent_one("");
        item8.setName_two("保护套别");
        item8.setContent_two("");
        item8.setName_three("名称属性");
        item8.setContent_three("");


        //跳闸关系
        if (newBHPZ.getKgbh() != null) {
            item8.setContent_one(newBHPZ.getKgbh() + "");
            relationship = newBHPZ.getKgbh() + "";
        }
        if (newBHPZ.getTb() != null) {
            item8.setContent_two(newBHPZ.getTb() + "");
            protect_config = newBHPZ.getTb() + "";
        }
        if (newBHPZ.getBhmcsx() != null) {
            item8.setContent_three(newBHPZ.getBhmcsx() + "");
            name_type = newBHPZ.getBhmcsx() + "";
        }
        data_name.add(item8);

        DeviceDetailsNameItem item9 = new DeviceDetailsNameItem();
        item9.setNum(1);
        item9.setName_one("保护名称");
        item9.setContent_one("");

        //保护名称
        if (newBHPZ.getBhmc() != null) {
            item9.setContent_one(newBHPZ.getBhmc() + "");
            protect_name = newBHPZ.getBhmc() + "";
        }
        data_name.add(item9);

        DeviceDetailsNameItem item10 = new DeviceDetailsNameItem();
        item10.setNum(1);
        item10.setName_one("是否就地化装置");
        item10.setContent_one("否");

        //是否就地化装置
        if (newBHPZ.getSfjdhzz() != null) {
            item10.setContent_one(newBHPZ.getSfjdhzz() + "");
        }
        data_name.add(item10);

        //是否就地化装置，数据挂靠
        if (newBHPZ.getSfjdhzz() != null && newBHPZ.getSfjdhzz().equals("是")) {
            isLink_device = true;

            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
            item.setNum(3);
            item.setName_one("连接器数量");
            item.setContent_one("");
            if (newBHPZ.getLjqsl() != null) {
                item.setContent_one(newBHPZ.getLjqsl() + "");
                ljqsl = newBHPZ.getLjqsl() + "";
            }
            item.setName_two("连接器插座制造厂家");
            item.setContent_two("");
            if (newBHPZ.getLjqzzcj() != null) {
                item.setContent_two(newBHPZ.getLjqzzcj() + "");
                ljqzzcj = newBHPZ.getLjqzzcj() + "";
            }
            item.setName_three("连接器插座型号");
            if (newBHPZ.getLjqxh() != null) {
                item.setContent_three(newBHPZ.getLjqxh() + "");
                ljqczxh = newBHPZ.getLjqxh() + "";
            }
            data_name.add(data_name.size(), item);
            Link_device_num = 1;
        }


    }


    /**
     * 选择完一次设备名称自动带出电压等级
     */
    public void setDydj(String ycsbmc) {
        Object o;
        electric_level = "";
        switch (one_device_type) {
            case "线路":
                o = util.getCZDYDJ(XLCS.class, control_company_name, ycsbmc, company_name);
                if (o != null) {
                    electric_level = ((XLCS) o).getDYDJ() + "";
                }
                break;
            case "电抗器":
                o = util.getCZDYDJ(DKQCS.class, control_company_name, ycsbmc, company_name);
                if (o != null) {
                    electric_level = ((DKQCS) o).getDydj() + "";
                }
                break;
            case "电容器":
                o = util.getCZDYDJ(DRQCS.class, control_company_name, ycsbmc, company_name);
                if (o != null) {
                    electric_level = ((DRQCS) o).getDYDJ() + "";
                }
                break;
            case "电动机":
                o = util.getCZDYDJ(DDJCS.class, control_company_name, ycsbmc, company_name);
                if (o != null) {
                    electric_level = ((DDJCS) o).getDYDJ() + "";
                }
                break;
            case "母线":
                o = util.getCZDYDJ(MXCS.class, control_company_name, ycsbmc, company_name);
                if (o != null) {
                    electric_level = ((MXCS) o).getDYDJ() + "";
                }
                break;
            case "断路器":
                o = util.getCZDYDJ(DLQCS.class, control_company_name, ycsbmc, company_name);
                if (o != null) {
                    electric_level = ((DLQCS) o).getDYDJ() + "";
                }
                break;
            case "变压器":
                o = util.getCZDYDJ(BYQCS.class, control_company_name, ycsbmc, company_name);
                if (o != null) {
                    electric_level = ((BYQCS) o).getDYDJ() + "";
                }
                break;
            case "发电机":
                o = util.getCZDYDJ(FDJCS.class, control_company_name, ycsbmc, company_name);
                if (o != null) {
                    electric_level = ((FDJCS) o).getDYDJ() + "";
                }
                break;
        }
        if (electric_level == null || electric_level.equals("") || electric_level.equals("必填项")) {
            electric_level = util.getCZCSByGLDW().getCzzgdydj() + "";
        }
    }

    //显示单位名称
    public String company_name() {
        if (company_name.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择单位名称");
        }
        return company_name;
    }

    //显示厂站名称
    public String control_company_name() {
        return control_company_name;
    }

    //显示一次设备类型
    public String one_device_type() {
        return one_device_type;
    }

    //显示保护类别
    public String protect_type() {
        return protect_type;
    }

    //显示制造厂家
    public String made_company() {
        return made_company;
    }

    //显示是否六统一设备
    public boolean six_one() {
        return six_one;
    }

    //显示保护型号
    public String protect_type_m() {
        return protect_type_m;
    }

    //通道类型复用，单选框值
    public void is_use_load(int Y, String use) {
        data_name.get(Y).setContent_two(use + "");
    }

    //生成日期，调值选取
    public void set_made_day(int Y, String made_days) {
        data_name.get(Y).setContent_three(made_days + "");
        made_day = made_days;
        adapter.notifyDataSetChanged();
    }

    //保护名称，点击自动生成
    public void made_protect_name(final int position) {
        //保护名称逻辑处理
        if (control_company_name.equals("")) {
            ToastUtils.showToast(getActivity(), "保护名称无法生成，请先选择厂站名称");
        } else if (electric_level.equals("")) {
            ToastUtils.showToast(getActivity(), "保护名称无法生成，请先选择电压等级");
        } else if (one_device_name.equals("")) {
            ToastUtils.showToast(getActivity(), "保护名称无法生成，请先一次设备名称");
        } else if (protect_type_m.equals("")) {
            ToastUtils.showToast(getActivity(), "保护名称无法生成，请先选择保护型号");
        }
        //默认已经生成，需要修改
        else if (!protect_name.equals("")) {
            ToastUtils.showToast(getActivity(), "手动修改保护名称");
            List<String> item = new ArrayList<>();
            showDialog("国家电力", "南方电力", position, 10, item);
        }
//        三个保存，两个获取
        else {
            //暂时缺少查重，可能为第二套，第三套等,,,
            String made_name = control_company_name + electric_level + "KV" +
                    one_device_name +
                    protect_config +
                    protect_type_m +
                    protect_type + "装置";
            data_name.get(data_name.size() - 2 - Link_device_num).setContent_one(made_name + "");
            protect_name = made_name + "";
            ToastUtils.showToast(getActivity(), "保护名称已生成");
            //查重
            protect_name_rep = false;
            makesure_protect_name(position);
            adapter.notifyDataSetChanged();
        }

    }

    //保护名称生成查重
    public void makesure_protect_name(int position) {
        BHPZ bhpz = util.getBHPZMC(protect_name);
        if (bhpz != null && !bhpz.getId().equals(DeviceDetailsActivity.bhpz.getId())) {
            //判断是否是修改已经保存的,id 唯一
            ToastUtils.showToast(getActivity(), "保护名称重复，请手动修改");
            protect_name_rep = true;
        } else {
            protect_name_rep = false;
        }
    }

    public void saveone() {
        DeviceDetailsActivity.instance.setpostion(0);

        CZCS czcs = util.getCZCSByGLDW();
        int BZdydj = (int) czcs.getCzzgdydj();
        int dydj = -1;
        if (!electric_level.equals("") && !electric_level.equalsIgnoreCase("null")) {
            dydj = Integer.parseInt(electric_level);
        }

        if (six_one && six_one_details.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择六统一标准版本号");
        } else if (company_name.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择单位名称");
        } else if (control_company_name.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择厂站名称");
        } else if (company_location_name.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择调度单位");
        } else if (one_device_type.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择一次设备类型");
        } else if (one_device_name.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择一次设备名称");
        } else if (electric_level.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择电压等级");
        } else if (dydj > BZdydj) {
            ToastUtils.showToast(getActivity(), "电压等级不能超过厂站最高电压等级");
        } else if (protect_type.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择保护类别");
        } else if (protect_type.equals("安全自动装置") && device_type.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择设备类型");
        } else if (protect_type.equals("安全自动装置") && device_type.equals("频率电压紧急控制装置") && device_work_set.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择设备功能配置");
        } else if (!protect_type.equals("站域保护") && !protect_type.equals("安全自动装置") && protect_type_details.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择保护类别细化");
        } else if (protect_type.equals("故障录波器") && machine_type.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择故障录波器类型");
        } else if (protect_type.equals("故障测距装置") && test_type.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择测距形式");
        } else if (protect_type.equals("保护故障信息系统子站") && isask.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择是否接入调度主站");
        } else if (made_company.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择制造厂家");
        } else if (protect_type_m.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择保护型号");
        } else if (protect_kind.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择保护分类");
        } else if (protect_what_kind.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择保护类型");
        } else if (six_one_details.equals("2013版") && ver_info.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择或填写软件版本");
        } else if (six_one_details.equals("2013版") && ICD.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择或填写ICD");
        } else if (protect_name.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择生成保护名称");
        } else if (protect_name_rep) {
            ToastUtils.showToast(getActivity(), "保护名称重复，请手动修改");
        } else if (isaddOk()) {
            insetdata();
        }


    }

    //多选合法性判断
    public boolean isaddOk() {
        boolean isok = true;
        //通道输入是否合法
        if (isroadtype) {
            for (int i = 4; i < 4 + add_protect_type; i++) {
                if (data_name.get(i).getContent_one().equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择通道类型");
                    isok = false;
                    break;
                } else if (data_name.get(i).getContent_two().equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择是否复用");
                    isok = false;
                    break;
                }
            }
        }
        //安控系统是否合法
        if (isdevice_type) {
            for (int i = 4; i < 4 + add_device_type; i++) {
                if (data_name.get(i).getContent_one().equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择安控系统调度名");
                    isok = false;
                    break;
                } else if (data_name.get(i).getContent_two().equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择安控站点类型");
                    isok = false;
                    break;
                }
            }
        }
        //模块名称是否合法
        if (ismodel_type) {
            for (int i = 6 + add_device_type + add_protect_type; i < 6 + add_device_type + add_protect_type + add_model_type; i++) {
                //填写了模块名称，软件版本为必填
                if (!data_name.get(i).getContent_one().equals("") && data_name.get(i).getContent_two().equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择填写软件版本");
                    isok = false;
                    break;
                }
            }
        }

        return isok;
    }

    //台账保存成功后调方法，保存插入关联数据
    public void addmore() {
        //是否有通道类型显示，有添加获取值，前两个必填值，最后一个手输入
        String sb_bhpzid = DeviceDetailsActivity.bhpz.getId() + "";
        util.deleteTDXX(sb_bhpzid);
        if (isroadtype) {
            String roadname = "";
            String roadone = "";
            List<TDXX> tdxxList = new ArrayList<>();
            for (int i = 4; i < 4 + add_protect_type; i++) {
                if (data_name.get(i).getContent_one().equals("")) {
                    ToastUtils.showToast(getActivity(), "请选择通道类型");
                    break;
                }
                roadone = data_name.get(i).getContent_one() + "";
                if (roadname.equals("")) {
                    roadname = roadone + "";
                } else {
                    roadname = roadname + "," + roadone;
                }

                TDXX tdxx = new TDXX();
                tdxx.setTdlx(data_name.get(i).getContent_one() + "");
                tdxx.setSffy(data_name.get(i).getContent_two() + "");
                tdxx.setTdzzxh(data_name.get(i).getContent_three() + "");
                long tdxxid = util.getInsertId("TDXX");
                String tdxxcode = util.checkTDXX(data_name.get(i).getContent_one() + "",
                        data_name.get(i).getContent_two() + "",
                        data_name.get(i).getContent_three() + "");
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
                pztdgx.setBHPZID(DeviceDetailsActivity.bhpz.getId());
                pztdgx.setTDXXID(tdxxid);
                //通道信息关联保存
                util.saveTDXXPZ(pztdgx);
                tdxxList.add(tdxx);
            }
            //通道类型插主表
            if (!roadname.equals("")) {
                DeviceDetailsActivity.bhpz.setTdlx(roadname + "");
            }
            data_name_load.toString();
        }
        util.deleteAKXTGX(sb_bhpzid);
        if (isdevice_type) {
            //是否有设备类型显示,安控系统，必填项目，两个值都需要有
            //保存新的之前，默认删除旧表
            for (int i = 4; i < 4 + add_device_type; i++) {
                DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                item.setContent_one(data_name.get(i).getContent_one() + "");
                item.setContent_two(data_name.get(i).getContent_two() + "");
                data_name_load.add(item);
                //存到主表里面
                if (i == 4) {
//                        DeviceDetailsActivity.bhpz.setAkzdm(data_name.get(i).getContent_one() + "");
                    DeviceDetailsActivity.bhpz.setSsakxtddmm(data_name.get(i).getContent_one() + "");
                    DeviceDetailsActivity.bhpz.setAkzdlx(data_name.get(i).getContent_two() + "");
                }

                AKXTGX akxtgx = new AKXTGX();

                Long axktgxid = util.getInsertId("AKXTGX");
                akxtgx.setId(axktgxid);
                akxtgx.setAkzdlx(data_name.get(i).getContent_two() + "");
                akxtgx.setAkxtid(akxt_id.get(i - 4) + "");
                akxtgx.setBhpzid(DeviceDetailsActivity.bhpz.getId() + "");
//              安控类型保存未处理
                util.saveAKXTGX(akxtgx);
            }
            data_name_load.toString();
        }
        //是否模块名称，非必填项目
        //在插其他表关系之前删除
//        util.deleteBHPZXHBBGX(sb_bhpzid);
        if (ismodel_type) {
            for (int i = 6 + add_device_type + add_protect_type; i < 6 + add_device_type + add_protect_type + add_model_type; i++) {
                //判断至少一个有值再存
                if (!data_name.get(i).getContent_one().equals("") ||
                        !data_name.get(i).getContent_two().equals("")) {
                    BHXHRJBB bhxhrjbb = new BHXHRJBB();
                    bhxhrjbb.setRjbbxx("模块名称：" + data_name.get(i).getContent_one() + "," +
                            "软件" + data_name.get(i).getContent_two() + "生成日期：" +
                            data_name.get(i).getContent_three() + ""
                    );
                    bhxhrjbb.setBblx("非六统一，分模块");
                    bhxhrjbb.setMkmc(data_name.get(i).getContent_one() + "");
                    String bball = data_name.get(i).getContent_two() + "";
                    String bbinfo = "";
                    String jyminfo = "";
                    String[] bb = bball.split("版本：");
                    if (bb.length > 1) {
                        String[] jym = bb[1].split("，校验码：");
                        if (jym.length > 1) {
                            bbinfo = jym[0];
                            jyminfo = jym[1];
                        }
                    }
//                    bhxhrjbb.setBb(data_name.get(i).getContent_two() + "");
                    bhxhrjbb.setBb(bbinfo + "");
                    bhxhrjbb.setJym(jyminfo + "");

                    bhxhrjbb.setId(util.getInsertId("BHXHRJBB"));
                    bhxhrjbb.setBhxhcode(model_data_type_get_code + "");
                    bhxhrjbb.setCode(util.getInsertId("CODE") + "");
                    bhxhrjbb.setState("C");
                    bhxhrjbb.setED_TAG("C");
                    //先存关系表
//                      util.coreSave(bhxhrjbb);

                    String code = util.getCodeByBhxhRjbb(bhxhrjbb);
                    if (code != null) {
                        BHPZXHBBGX bbgx = new BHPZXHBBGX();
                        bbgx.setID(util.getInsertId("BHPZXHBBGX"));
                        bbgx.setBHPZID(DeviceDetailsActivity.bhpz.getId());
                        bbgx.setRJBBCODE(code);
                        if (!data_name.get(i).getContent_three().equals("")) {
                            bbgx.setSCSJ(TimeUtil.formatString(data_name.get(i).getContent_three() + "") + "");
                        }
                        util.coreSave(bbgx);
                        //有code不存
//                        util.coreSave(bhxhrjbb);
                    } else {
                        BHPZXHBBGX bbgx = new BHPZXHBBGX();
                        bbgx.setID(util.getInsertId("BHPZXHBBGX"));
                        bbgx.setBHPZID(DeviceDetailsActivity.bhpz.getId());
                        bbgx.setRJBBCODE(bhxhrjbb.getCode());
                        if (!data_name.get(i).getContent_three().equals("")) {
                            bbgx.setSCSJ(TimeUtil.formatString(data_name.get(i).getContent_three() + "") + "");
                        }
                        util.coreSave(bbgx);
                        //先存关系表
                        util.coreSave(bhxhrjbb);
                    }

//                    BHPZXHBBGX bbgx = new BHPZXHBBGX();
//                    bbgx.setID(util.getInsertId("BHPZXHBBGX"));
//                    bbgx.setBHPZID(DeviceDetailsActivity.bhpz.getId());
//                    bbgx.setRJBBCODE(bhxhrjbb.getCode());
//                    if (!data_name.get(i).getContent_three().equals("")) {
//                        bbgx.setSCSJ(TimeUtil.formatString(data_name.get(i).getContent_three() + "") + "");
//                    }
//                    util.coreSave(bbgx);
//                    //先存关系表
//                    util.coreSave(bhxhrjbb);
                }
            }
        }
        if (!six_one_details.equals("2013版") && protect_type_m.equals(location_protect_type_m)) {
            if (bhsbxhb != null) {
                util.coreSave(bhsbxhb);
            }
        }

        add_more_success = true;
    }

    private BHSBXHB bhsbxhb;

    public void insetdata() {
        //在插其他表关系之前删除
        util.deleteBHPZXHBBGX(DeviceDetailsActivity.bhpz.getId() + "");

        //手动添加存表判断
        //一次设备名称本地添加
        if (one_device_name.equals(location_One_device_name)) {

        }
        //制造厂家本地添加
        if (made_company.equals(location_Made_company)) {
//            制造厂家
            ZZCJ zzcj_add = new ZZCJ();
            zzcj_add.setMC(location_Made_company);
            zzcj_add.setCZR(PreferenceUtils
                    .getPrefString(getActivity(), "userInfo", null).split("-")[0]);
            zzcj_add.setID(util.getInsertId("ZZCJ"));
            zzcj_add.setED_TAG("C");
            util.coreSave(zzcj_add);
        }

        //保护型号本地添加
        if (!six_one_details.equals("2013版") && protect_type_m.equals(location_protect_type_m)) {
//            BHSBXHB bhsbxhb = new BHSBXHB();
            bhsbxhb = new BHSBXHB();
            bhsbxhb.setId(util.getInsertId("BHSBXHB"));
            //可能需要给关系表插code
            Long codes = util.getInsertId("CODE");
            model_data_type_get_code = codes + "";
            bhsbxhb.setCode(codes + "");
            bhsbxhb.setSbxh(protect_type_m + "");
            bhsbxhb.setState("C");
            bhsbxhb.setED_TAG("C");
            if (isGD.equals("是")) {
                bhsbxhb.setSfqy("Y");
            } else {
            }
            //保护分类本地添加
            bhsbxhb.setBhfl(protect_kind + "");
            //保护类型本地添加
            bhsbxhb.setBhlx(protect_what_kind + "");
            bhsbxhb.setZzcj(made_company + "");
            bhsbxhb.setBhlb(protect_type + "");
            if (ismodel_type) {
                bhsbxhb.setBblx("非六统一，分模块");
            } else {
                bhsbxhb.setBblx("非六统一，不分模块");
            }
//            util.coreSave(bhsbxhb);
        }

        //选配功能本地添加
        //ICD本地添加

        //身份识别码输入
        id_card = input_id_card.getText() + "";
        DeviceDetailsActivity.bhpz.setSfsbm(id_card + "");

        if (six_one) {
            DeviceDetailsActivity.bhpz.setSfltysb("是");
            DeviceDetailsActivity.bhpz.setLtybzbb(six_one_details + "");
        } else {
            DeviceDetailsActivity.bhpz.setSfltysb("否");
            DeviceDetailsActivity.bhpz.setLtybzbb("");
        }

        DeviceDetailsActivity.bhpz.setCzgldw(company_name + "");
        DeviceDetailsActivity.bhpz.setDddw(company_location_name + "");
        DeviceDetailsActivity.bhpz.setCzmc(control_company_name + "");

        DeviceDetailsActivity.bhpz.setYcsblx(one_device_type + "");
        DeviceDetailsActivity.bhpz.setYcsbmc(one_device_name + "");
        Long electric = Long.parseLong(electric_level + "");
        DeviceDetailsActivity.bhpz.setDydj(electric);

        DeviceDetailsActivity.bhpz.setBhlb(protect_type + "");
        //设备类型传值，需要单独处理
        if (data_name.get(3).getName_two().equals("设备类型")) {
            DeviceDetailsActivity.bhpz.setBhlbxh(device_type + "");
        } else {
            DeviceDetailsActivity.bhpz.setBhlbxh(protect_type_details + "");
        }

        //故障录波器类型
        //测距形式
        //是否调度主
        //设备功能配置
        DeviceDetailsActivity.bhpz.setGzlbqlx(machine_type + "");
        DeviceDetailsActivity.bhpz.setCjxx(test_type + "");
        DeviceDetailsActivity.bhpz.setSfjrzz(isask + "");
        DeviceDetailsActivity.bhpz.setSbgnpz(device_work_set + "");

        DeviceDetailsActivity.bhpz.setZzcj(made_company + "");
        DeviceDetailsActivity.bhpz.setBhxh(protect_type_m + "");
        DeviceDetailsActivity.bhpz.setBhfl(protect_kind + "");

        DeviceDetailsActivity.bhpz.setUsegddata(isGD + "");
        DeviceDetailsActivity.bhpz.setBdzlx(company_type + "");


        //选配功能
        DeviceDetailsActivity.bhpz.setXp(select_work + "");
        DeviceDetailsActivity.bhpz.setIcdwjmc(ICD + "");
//        if (six_one && six_one_details.equals("2013版") && ICD.equals(location_ICD) ||
//                six_one && six_one_details.equals("2013版") && select_work.equals(location_select_work)
//                ) {
        if (six_one && six_one_details.equals("2013版")) {
            //code 获取来自LTYSBXHB
            LTYSBXH ltysbxh = new LTYSBXH();
            ltysbxh.setId(util.getInsertId("LTYSBXH"));
            ltysbxh.setXp(select_work);
            ltysbxh.setWjmc(ICD);
            ltysbxh.setRjbb(ver_info + "");
            ltysbxh.setZzcj(made_company + "");
            ltysbxh.setBhlb(protect_type + "");
            ltysbxh.setBhxh(protect_type_m + "");
            ltysbxh.setBhfl(protect_kind + "");
            ltysbxh.setBhlx(protect_what_kind + "");
            ltysbxh.setBblx("六统一设备");
            ltysbxh.setED_TAG("C");
            ltysbxh.setState("C");
            if (isGD.equals("是")) {
                ltysbxh.setSfqy("Y");
            } else {
            }
            ltysbxh.setCode(util.getInsertId("CODE") + "");
//            ltysbxh.setCode(model_data_type_get_code);

            String code = util.getCodeByBhxhRjbb(ltysbxh);
            if (code != null) {
                ltysbxh.setCode(code + "");
                //icd信息调用
                if (DeviceDetailsActivity.state.equals("C")) {
                    if (DeviceDetailsActivity.isFromSaoma) {
                        //编辑过返回true,未编辑返回false
                        if (DeviceDetailsActivity.instance.ltysbxh_bh == null && ltysbxh != null && ltysbxh.getCode() != null
                                && !"".equals(ltysbxh.getCode()) && !"null".equals(ltysbxh.getCode())) {
                            DeviceDetailsActivity.instance.code2 = null;
                            DeviceDetailsActivity.instance.code3 = code + "";

                        } else {
                            if (DeviceDetailsActivity.instance.shifouEdit(DeviceDetailsActivity.instance.ltysbxh_bh, ltysbxh)) {
                                DeviceDetailsActivity.instance.code2 = null;
                                DeviceDetailsActivity.instance.code3 = code + "";
                            } else {
                                DeviceDetailsActivity.instance.code2 = code;
                                DeviceDetailsActivity.instance.code3 = code + "";
                            }
                        }
                    } else {
                        DeviceDetailsActivity.instance.code2 = null;
                        DeviceDetailsActivity.instance.code3 = code + "";
                    }
                } else {
//                    DeviceDetailsActivity.instance.code2=code;
//                    DeviceDetailsActivity.instance.code3=code+"";

                    //编辑过返回true,未编辑返回false
                    if (DeviceDetailsActivity.instance.ltysbxh_bh == null && ltysbxh != null && ltysbxh.getCode() != null
                            && !"".equals(ltysbxh.getCode()) && !"null".equals(ltysbxh.getCode())) {
                        DeviceDetailsActivity.instance.code2 = null;
                        DeviceDetailsActivity.instance.code3 = code + "";

                    } else {
                        if (DeviceDetailsActivity.instance.shifouEdit(DeviceDetailsActivity.instance.ltysbxh_bh, ltysbxh)) {
                            DeviceDetailsActivity.instance.code2 = null;
                            DeviceDetailsActivity.instance.code3 = code + "";
                        } else {
                            DeviceDetailsActivity.instance.code2 = code;
                            DeviceDetailsActivity.instance.code3 = code + "";
                        }
                    }
                }

                BHPZXHBBGX bbgx = new BHPZXHBBGX();
                bbgx.setID(util.getInsertId("BHPZXHBBGX"));
                bbgx.setBHPZID(DeviceDetailsActivity.bhpz.getId());
                bbgx.setRJBBCODE(code + "");
                if (!made_day.equals("")) {
                    bbgx.setSCSJ(TimeUtil.formatString(made_day + "") + "");
                }
                util.coreSave(bbgx);
                //有code，只存关系表
//                util.coreSave(ltysbxh);
            } else {
                //icd信息调用
                DeviceDetailsActivity.instance.code2 = null;
                DeviceDetailsActivity.instance.code3 = ltysbxh.getCode() + "";
                BHPZXHBBGX bbgx = new BHPZXHBBGX();
                bbgx.setID(util.getInsertId("BHPZXHBBGX"));
                bbgx.setBHPZID(DeviceDetailsActivity.bhpz.getId());
                bbgx.setRJBBCODE(ltysbxh.getCode() + "");
                if (!made_day.equals("")) {
                    bbgx.setSCSJ(TimeUtil.formatString(made_day + "") + "");
                }
                util.coreSave(bbgx);
                //先存关系表
                util.coreSave(ltysbxh);
            }

        } else if (!ismodel_type) {
            //code 获取来自BHXHRJBB
            if (!ver_info.equals("") && !six_one_details.equals("2013版")) {
                BHXHRJBB bhxhrjbb = new BHXHRJBB();
                bhxhrjbb.setBblx("非六统一，不分模块");
//                bhxhrjbb.setBb(ver_info + "");
                String data = "";
                if (!made_day.equals("")) {
                    TimeUtil.formatString(made_day + "");
                }
                bhxhrjbb.setRjbbxx(
                        "" + ver_info + ""
                                + "生成日期：" + data
                );

                String bball = ver_info + "";
                String bbinfo = "";
                String jyminfo = "";
                String[] bb = bball.split("版本：");
                if (bb.length > 1) {
                    String[] jym = bb[1].split("，校验码：");
                    if (jym.length > 1) {
                        bbinfo = jym[0];
                        jyminfo = jym[1];
                    }
                }
                bhxhrjbb.setBb(bbinfo + "");
                bhxhrjbb.setJym(jyminfo + "");

                bhxhrjbb.setId(util.getInsertId("BHXHRJBB"));
                bhxhrjbb.setBhxhcode(model_data_type_get_code + "");
                bhxhrjbb.setCode(util.getInsertId("CODE") + "");
                bhxhrjbb.setState("C");
                bhxhrjbb.setED_TAG("C");
//                util.coreSave(bhxhrjbb);

                String code = util.getCodeByBhxhRjbb(bhxhrjbb);
                if (code != null) {
                    BHPZXHBBGX bbgx = new BHPZXHBBGX();
                    bbgx.setID(util.getInsertId("BHPZXHBBGX"));
                    bbgx.setBHPZID(DeviceDetailsActivity.bhpz.getId());
                    bbgx.setRJBBCODE(code);
                    if (!made_day.equals("")) {
                        bbgx.setSCSJ(TimeUtil.formatString(made_day + "") + "");
                    }
                    util.coreSave(bbgx);
                    //先存关系表
                    util.coreSave(bhxhrjbb);
                } else {
                    BHPZXHBBGX bbgx = new BHPZXHBBGX();
                    bbgx.setID(util.getInsertId("BHPZXHBBGX"));
                    bbgx.setBHPZID(DeviceDetailsActivity.bhpz.getId());
                    bbgx.setRJBBCODE(bhxhrjbb.getCode());
                    if (!made_day.equals("")) {
                        bbgx.setSCSJ(TimeUtil.formatString(made_day + "") + "");
                    }
                    util.coreSave(bbgx);
                    //先存关系表
                    util.coreSave(bhxhrjbb);
                }


            }
        }

        DeviceDetailsActivity.bhpz.setBhlx(protect_what_kind + "");
        DeviceDetailsActivity.bhpz.setRjbb(ver_info + "");

        DeviceDetailsActivity.bhpz.setKgbh(relationship + "");
        DeviceDetailsActivity.bhpz.setTb(protect_config + "");
        DeviceDetailsActivity.bhpz.setBhmcsx(name_type + "");

        DeviceDetailsActivity.bhpz.setBhmc(protect_name + "");

//        ljqsl ="";
//        ljqzzcj ="";
//        ljqczxh ="";
        if (isLink_device) {
            DeviceDetailsActivity.bhpz.setSfjdhzz("是");
            //就地化为是的时候，连接器保存
            DeviceDetailsActivity.bhpz.setLjqsl(ljqsl + "");
            DeviceDetailsActivity.bhpz.setLjqzzcj(ljqzzcj + "");
            DeviceDetailsActivity.bhpz.setLjqxh(ljqczxh + "");
        } else {
            DeviceDetailsActivity.bhpz.setSfjdhzz("否");
            DeviceDetailsActivity.bhpz.setLjqsl("");
            DeviceDetailsActivity.bhpz.setLjqzzcj("");
            DeviceDetailsActivity.bhpz.setLjqxh("");
        }
        savesuccess = true;

    }

    private void cleanDATA() {
        //连接器显示
        link_device = false;
        //是否就地化装置
        isLink_device = false;
        Link_device_num = 0;

        ljqsl = "";
        ljqzzcj = "";
        ljqczxh = "";
        //是否有通道类型显示
        isroadtype = false;
        //是否有设备类型显示
        isdevice_type = false;
        //是否有模块信息显示
        ismodel_type = false;
        //是否有选配功能显示
        ischoice_type = false;
        //是否有通道类型显示数目
        add_protect_type = 0;
        //是否有设备类型显示显示数目
        add_device_type = 0;
        //是否有模块信息显示数目
        add_model_type = 0;

        //身份识别码
        id_card = "";
        //六统一
        six_one = false;
        //六统一标准版本
        six_one_details = "";
        //单位名称
        company_name = "";
        //厂站名称
        control_company_name = "";
        //调度单位
        company_location_name = "";
        //一次设备类型
        one_device_type = "";
        //一次设备名称
        one_device_name = "";
        //电压等级
        electric_level = "";
        //保护类别
        protect_type = "";
        //保护类别细化
        protect_type_details = "";
        //设备类型
        device_type = "";
        //制造厂家
        made_company = "";
        //保护型号
        protect_type_m = "";
        //保护分类
        protect_kind = "";
        //是否国调
        isGD = "";
        //厂站类型
        company_type = "";
        //保护类型
        protect_what_kind = "";
        //软件版本
        ver_info = "";
        //生成日期
        made_day = "";
        //跳闸关系
        relationship = "";
        //保护套别
        protect_config = "";
        //名称属性
        name_type = "";
        //保护名称
        protect_name = "";

        //故障录波器类型
        machine_type = "";
        //测距形式
        test_type = "";
        //是否调度主
        isask = "";
        //设备功能配置
        device_work_set = "";
        //选配功能
        select_work = "";
        //ICD文件名
        ICD = "";
        //软件版本
//    private String ICD = "";

        //判断是添加还是编辑
        is_add = true;
        //本地添加台账
//        private  BHPZ newBHPZ;  //保护装置对象;
//
//        private IDaoUtil util ;

        //是否分模块类型
        model_data_type.clear();
        //是否分模块，模块名称，及软件版本的获取码
        model_data_type_code.clear();
        //是否分模块，模块名称，及软件版本的获取码,型号获取
        model_data_type_code_xh.clear();
        //获取码,保护型号code
        model_data_type_get_code = "";


        //安控系统ID
        akxt_id_list.clear();
        //获取安控系统ID
        akxt_id.clear();
        //获取安控系统关系
        akxtgx_all.clear();
        //获取通道关系
        tdgx_all.clear();

        //一次设备多选值
        reault = "";
        //跳闸关系多选值
        KGBHreault = "";

        //保护名称重复判断
        protect_name_rep = false;

        //对象插入成功
        savesuccess = false;
        //对象附表插入成功
        add_more_success = false;

        //制造厂家本地添加
        location_Made_company = "";
        //一次设备名称本地添加
        location_One_device_name = "";
        //保护型号本地添加
        location_protect_type_m = "";
        //保护分类本地添加
        location_protect_kind = "";
        //保护类型本地添加
        location_protect_what_kind = "";
        //选配功能本地添加
        location_select_work = "";
        //ICD文件名本地添加
        location_ICD = "";
        model_one = "";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
