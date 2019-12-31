package cn.com.sgcc.dev.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.DeviceSelectAdapter;
import cn.com.sgcc.dev.adapter.DeviceThreeAdapter;
import cn.com.sgcc.dev.adapter.DeviceTwoAdapter;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

import static cn.com.sgcc.dev.utils.TimeUtil.dateToStr;

/**
 * <p>@description:</p>
 *   设备详情二级界面，详情三
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */

public class DeviceThreeFragment extends BaseFragment  {
    private ListView Infor_select;
    private List<DeviceDetailsNameItem> data_name_three;
    private DeviceThreeAdapter adapter;
    private DeviceSelectAdapter selectAdapter;
    public boolean saveSuccess=false;
    private CustomDialog dialog;
    private List data;

    public static DeviceThreeFragment instance = null;

    private void cleanDATA(){
        data_name_three.clear();
        saveSuccess=false;

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_three_device;
    }

    @Override
    public void initview() {
        Infor_select = (ListView) getActivity().findViewById(R.id.fragment_device_details_three_lv);
    }

    @Override
    public void initEvevt() {

    }

    @Override
    public void initData() {
        data_name_three = new ArrayList<>();
        cleanDATA();
        if (DeviceDetailsActivity.state.equals("C")){
            //添加数据初始化或者关联出厂库
            if (DeviceDetailsActivity.isFromSaoma){
                change_data();
            }else{
                add_data();
            }
        }else if (DeviceDetailsActivity.state.equals("M")||DeviceDetailsActivity.isFromSaoma){
            //编辑数据初始化
            change_data();
        }
        adapter = new DeviceThreeAdapter(getActivity(),data_name_three);
        Infor_select.setAdapter(adapter);
    }

    @Override
    public void initReceiver(boolean isEdit) {

    }

    //新增台账
    public void  add_data(){
        String select="请选择";
        String input="请输入";
        for (int i = 0;i<5;i++){
            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
            if (i==0){
                item.setNum(3);
                item.setName_one("数据采集方式");
                item.setContent_one(select);
                item.setName_two("出口方式");
                item.setContent_two(select);
                item.setName_three("所在屏柜");
                item.setContent_three(select);
            }
            else if (i==1){
                item.setNum(4);
                item.setName_one("模拟通道数");
                item.setContent_one(select);
                item.setName_two("数字通道数");
                item.setContent_two(select);
                item.setName_three("实际变比");
                item.setContent_three(select);
            }
            else if (i==2){
                item.setNum(5);
                item.setName_one("额定变比");
                item.setContent_one(select);
                item.setName_two("准确级");
                item.setContent_two(select);
                item.setName_three("直流额定电压");
                item.setContent_three(select);
            }
            else if (i==3){
                item.setNum(6);
                item.setName_one("CT二次额定电流");
                item.setContent_one(select);
                item.setName_two("电源插件型号");
                item.setContent_two(select);
                item.setName_three("电源插件更换日期");
                item.setContent_three(select);
            }
            else if (i==4){
                item.setNum(7);
                item.setName_one("统计运行时间");
                item.setContent_one("否");
                DeviceDetailsActivity.bhpz.setSftjyxsj("否");
//                item.setName_two("关联退出运行保护配置");
//                item.setContent_two("否");
                item.setName_two("板卡数量");
                item.setContent_two("必填项");
            }
            data_name_three.add(item);

        };
    }

    public void change_data() {
        DeviceDetailsNameItem item = new DeviceDetailsNameItem();
        item.setNum(3);
        item.setName_one("数据采集方式");
        if(DeviceDetailsActivity.bhpz.getSjcjfs()==null){
            item.setContent_one("");
        }else{
            item.setContent_one(DeviceDetailsActivity.bhpz.getSjcjfs() + "");
        }
        item.setName_two("出口方式");
        if(DeviceDetailsActivity.bhpz.getCkfs()==null){
            item.setContent_two("");
        }else{
            item.setContent_two(DeviceDetailsActivity.bhpz.getCkfs() + "");
        }
        item.setName_three("所在屏柜");
        if(DeviceDetailsActivity.bhpz.getSzpg()==null){
            item.setContent_three("");
        }else{
            item.setContent_three(DeviceDetailsActivity.bhpz.getSzpg() + "");
        }
        data_name_three.add(item);

        DeviceDetailsNameItem item2 = new DeviceDetailsNameItem();
        item2.setNum(4);
        item2.setName_one("模拟通道数");
        if(DeviceDetailsActivity.bhpz.getMntds()==null){
            item2.setContent_one("");
        }else{
            item2.setContent_one(DeviceDetailsActivity.bhpz.getMntds() + "");
        }
        item2.setName_two("数字通道数");
        if(DeviceDetailsActivity.bhpz.getSztds()==null){
            item2.setContent_two("");
        }else{
            item2.setContent_two(DeviceDetailsActivity.bhpz.getSztds() + "");
        }
        item2.setName_three("实际变比");
        if(DeviceDetailsActivity.bhpz.getSjbb()==null){
            item2.setContent_three("");
        }else{
            item2.setContent_three(DeviceDetailsActivity.bhpz.getSjbb() + "");
        }
        data_name_three.add(item2);

        DeviceDetailsNameItem item3 = new DeviceDetailsNameItem();
        item3.setNum(5);
        item3.setName_one("额定变比");
        if(DeviceDetailsActivity.bhpz.getEdbb()==null){
            item3.setContent_one("");
        }else{
            item3.setContent_one(DeviceDetailsActivity.bhpz.getEdbb() + "");
        }
        item3.setName_two("准确级");
        if(DeviceDetailsActivity.bhpz.getZqj()==null){
            item3.setContent_two("");
        }else{
            item3.setContent_two(DeviceDetailsActivity.bhpz.getZqj() + "");
        }
        item3.setName_three("直流额定电压");
        if(DeviceDetailsActivity.bhpz.getZleddy()==0){
            item3.setContent_three("");
        }else{
            item3.setContent_three(DeviceDetailsActivity.bhpz.getZleddy() + "");
        }
        data_name_three.add(item3);

        DeviceDetailsNameItem item4 = new DeviceDetailsNameItem();
        item4.setNum(6);
        item4.setName_one("CT二次额定电流");
        if(DeviceDetailsActivity.bhpz.getCteceddl()==null){
            item4.setContent_one("");
        }else{
            item4.setContent_one(DeviceDetailsActivity.bhpz.getCteceddl() + "");
        }
        item4.setName_two("电源插件型号");
        if(DeviceDetailsActivity.bhpz.getDycjxh()==null){
            item4.setContent_two("");
        }else{
            item4.setContent_two(DeviceDetailsActivity.bhpz.getDycjxh() + "");
        }
        item4.setName_three("电源插件更换日期");
        if(TimeUtil.dateIsEmpty(DeviceDetailsActivity.bhpz.getDycjghrq())){
            item4.setContent_three("");
        }else{
            if (TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getDycjghrq())==null){
                item4.setContent_three("");
            }else{
                item4.setContent_three(TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getDycjghrq()) + "");
            }

        }
        data_name_three.add(item4);

        DeviceDetailsNameItem item5 = new DeviceDetailsNameItem();
        item5.setNum(7);
        item5.setName_one("统计运行时间");
        if(DeviceDetailsActivity.bhpz.getSftjyxsj()==null||DeviceDetailsActivity.bhpz.getSftjyxsj().equals("")){
            item5.setContent_one("否");
            DeviceDetailsActivity.bhpz.setSftjyxsj("否");
        }else{
            item5.setContent_one(DeviceDetailsActivity.bhpz.getSftjyxsj() + "");
        }
//        item5.setName_two("关联退出运行保护配置");
//        item5.setContent_two(DeviceDetailsActivity.bhpz.getTcyxid() + "");
        item5.setName_two("板卡数量");
        if(DeviceDetailsActivity.bhpz.getBksl()==0){
            item5.setContent_two("必填项");
        }else{
            item5.setContent_two(DeviceDetailsActivity.bhpz.getBksl() + "");
        }
        data_name_three.add(item5);

    }

    public static boolean IsNotNull(String name) {
        boolean isnull = true;
        if (name==null||name.equals("")){
            isnull = false;
        }
        return isnull;
    }

    //非空验证
    public void savethree() {
        DeviceDetailsActivity.instance.setpostion(2);
        if (DeviceDetailsActivity.bhpz.getBksl()==0) {
            ToastUtils.showToast(getActivity(), "请选择板卡数量");
        }else{
            saveSuccess=true;
        }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //属性值选择框
    public void showDialog(final int Y, final int X,final List<String> item_name) {
        if (Y==3&&X==3){//电源插件更换日期
            data_name_three.get(Y).setContent_three(TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getDycjghrq())+"");
            adapter.notifyDataSetChanged();
            return;
        }else if (Y==4){
            if (X==1){//统计运行时间
                data_name_three.get(Y).setContent_one(DeviceDetailsActivity.bhpz.getSftjyxsj()+"");
                adapter.notifyDataSetChanged();
                return;
            }
//            else if (X==2){//关联退出运行保护
//            data_name_three.get(Y).setContent_two(DeviceDetailsActivity.bhpz.getTcyxid()+"");
//                adapter.notifyDataSetChanged();
//                return;
//            }
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
            //搜索按钮
            btn_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.size() > 0) {
                        String name = et_dialog.getText() + "";
                        if (name.equals("")) {
                            ToastUtils.showToast(getActivity(), "请输入查询信息");
                        } else {
                            //搜索内容处理
                            if (name.matches("^[A-Za-z]+$") || name.matches("[\\u4e00-\\u9fa5]+")){
                                List<String>  item = new ArrayList<String>();
                                for (int i = 0; i < item_name.size(); i++) {
                                    if (item_name.get(i).contains(name)){
                                        item.add(i+"");
                                        break;
                                    }
                                }
                                if (item.size()>0){
                                    int i= Integer.parseInt(item.get(0));
                                    lv_dialog.setSelection(i);
                                }

                            }else {
                                ToastUtils.showToast(getActivity(), "请输入中英文查询");
                            }
                        }
                    } else {
                        ToastUtils.showToast(getActivity(), "可选信息为空");
                    }
                }
            });
            //添加按钮
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = et_dialog.getText()+"";
                    if (name.equals("")){
                        ToastUtils.showToast(getActivity(),"请输入添加信息");
                    }else {
                        data.add(0,name);
                        selectAdapter.notifyDataSetChanged();
                    }
                }
            });

            //添加按钮隐藏与显示
            btn_add.setVisibility(View.GONE);
            //各属性值输入限制
            if(Y==0){
                if (X==3){//所在屏柜   [SZPG] VARCHAR2(80)
                    btn_add.setVisibility(View.VISIBLE);
                    //et_dialog.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                    et_dialog.setFilters(new InputFilter[]{new InputFilter.LengthFilter(80)});
                }
            }else if(Y==1){
                if (X==1){//模拟通道数 [MNTDS] VARCHAR2(100)
                    btn_add.setVisibility(View.VISIBLE);
                    et_dialog.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    et_dialog.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                }else if (X==2){//数字通道数 [SZTDS] VARCHAR2(100),
                    btn_add.setVisibility(View.VISIBLE);
                    et_dialog.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    et_dialog.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                }else if (X==3){//实际变比 [SJBB] VARCHAR2(500),
                    btn_add.setVisibility(View.VISIBLE);
                    et_dialog.setFilters(new InputFilter[]{new InputFilter.LengthFilter(500)});
                }
            } else if(Y==2){
              if (X==1){//额定变比  [EDBB] VARCHAR2(500)
                  btn_add.setVisibility(View.VISIBLE);
                  et_dialog.setFilters(new InputFilter[]{new InputFilter.LengthFilter(500)});
                }
            }else if(Y==3){
             if (X==2){//电源插件型号 [DYCJXH] VARCHAR2(40)
                 btn_add.setVisibility(View.VISIBLE);
                 et_dialog.setFilters(new InputFilter[]{new InputFilter.LengthFilter(40)});
                }
            }else if(Y==4){
                if (X==2){//板卡数量 [BKSL] NUMBER(16)
                    btn_add.setVisibility(View.VISIBLE);
                    et_dialog.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    et_dialog.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                }
            }

            //正确数据库来源数据
            data = item_name;

            selectAdapter = new DeviceSelectAdapter(getActivity(),data);
            lv_dialog.setAdapter(selectAdapter);

            lv_dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(Y==0){
                        if (X==1){//数据采集方式
                            data_name_three.get(Y).setContent_one(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setSjcjfs(data.get(position).toString());
                        }else if (X==2){//出口方式
                            data_name_three.get(Y).setContent_two(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setCkfs(data.get(position).toString());
                        }else if (X==3){//所在屏柜
                            data_name_three.get(Y).setContent_three(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setSzpg(data.get(position).toString());
                        }
                    }else if(Y==1){
                        if (X==1){//模拟通道数
                            data_name_three.get(Y).setContent_one(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setMntds(data.get(position).toString());
                        }else if (X==2){//数字通道数
                            data_name_three.get(Y).setContent_two(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setSztds(data.get(position).toString());
                        }else if (X==3){//实际变比
                            data_name_three.get(Y).setContent_three(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setSjbb(data.get(position).toString());
                        }
                    } else if(Y==2){
                        if (X==2){//准确级
                            data_name_three.get(Y).setContent_two(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setZqj(data.get(position).toString());
                        }else if (X==3){//直流额定电压
                            data_name_three.get(Y).setContent_three(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setZleddy(Long.parseLong(data.get(position).toString()));
                        }else if (X==1){//额定变比
                            data_name_three.get(Y).setContent_one(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setEdbb(data.get(position).toString());
                        }
                    }else if(Y==3){
                        if (X==1){//CT二次额定电流
                            data_name_three.get(Y).setContent_one(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setCteceddl(data.get(position).toString());
                        }else if (X==2){//电源插件型号
                            data_name_three.get(Y).setContent_two(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setDycjxh(data.get(position).toString());
                        }
                    }else if(Y==4){
                        if (X==2){//板卡数量
                            data_name_three.get(Y).setContent_two(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setBksl(Integer.parseInt(data.get(position).toString()));
                        }
                    }

                    dialog.dismiss();
                    adapter.notifyDataSetChanged();
                    data.clear();
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
            dialog.show();
        }
    }

}
