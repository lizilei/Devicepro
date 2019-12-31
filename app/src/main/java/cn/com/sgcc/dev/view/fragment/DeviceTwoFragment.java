package cn.com.sgcc.dev.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.zxing.view.ViewfinderView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.DeviceOneAdapter;
import cn.com.sgcc.dev.adapter.DeviceSelectAdapter;
import cn.com.sgcc.dev.adapter.DeviceTwoAdapter;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.utils.DeviceDateilsUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

import static cn.com.sgcc.dev.utils.TimeUtil.dateToStr;

/**
 * <p>@description:</p>
 *   设备详情二级界面，详情二
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class DeviceTwoFragment extends BaseFragment  {
    private ListView Infor_select;
    private List<DeviceDetailsNameItem> data_name_two;
    private DeviceTwoAdapter adapter;
    private DeviceSelectAdapter selectAdapter;
    private List data;
    public boolean saveSuccess=false;

    private CustomDialog dialog;

    public static DeviceTwoFragment instance = null;

    private void cleanDATA(){
        data_name_two.clear();
        saveSuccess=false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_two_device;
    }

    @Override
    public void initview() {
        Infor_select = (ListView) getActivity().findViewById(R.id.fragment_device_details_two_lv);

    }

    @Override
    public void initEvevt() {

    }

    @Override
    public void initData() {
        data_name_two = new ArrayList<>();
        cleanDATA();
       if (DeviceDetailsActivity.state.equals("C")){
            //添加数据初始化
           if (DeviceDetailsActivity.isFromSaoma){
               change_data();
           }else{
               add_data();
           }
        }else if (DeviceDetailsActivity.state.equals("M")||DeviceDetailsActivity.isFromSaoma){
            //编辑数据初始化
             change_data();
        }
        adapter = new DeviceTwoAdapter(getActivity(),data_name_two);
        Infor_select.setAdapter(adapter);
    }

    @Override
    public void initReceiver(boolean isEdit) {

    }

    //新增台账
    public void  add_data(){
        String select="请选择";
        for (int i = 0;i<5;i++){
            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
            if (i==0){
                item.setNum(4);
                item.setName_one("运行状态");
                item.setContent_one("必填项");
                item.setName_two("退出运行时间");
                item.setContent_two("必填项");
                item.setName_three("设备属性");
                item.setContent_three("必填项");
            }
            else if (i==1){
                item.setNum(3);
                item.setName_one("运行单位");
                item.setContent_one("必填项");
                item.setName_two("维护单位");
                item.setContent_two("必填项");
                item.setName_three("设计单位");
                item.setContent_three("必填项");
            }
            else if (i==2){
                item.setNum(5);
                item.setName_one("基建单位");
                item.setContent_one("必填项");
                item.setName_two("投运日期");
                item.setContent_two("必填项");
                item.setName_three("定期检验周期");
                item.setContent_three("必填项");
            }
            else if (i==3){
                item.setNum(6);
                item.setName_one("上次检修时间");
                item.setContent_one("必填项");
                item.setName_two("出厂日期");
                item.setContent_two(select);
                item.setName_three("出厂编号");
                item.setContent_three(select);
            }
            else if (i==4){
                item.setNum(7);
                item.setName_one("资产单位");
                item.setContent_one(select);
                item.setName_two("资产性质");
                item.setContent_two(select);
                item.setName_three("资产编号");
                item.setContent_three(select);
            }
            data_name_two.add(item);
        };
    }

    public void change_data(){
            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
            item.setNum(4);
            item.setName_one("运行状态");
            if(DeviceDetailsActivity.bhpz.getYxzt()==null){
                item.setContent_one("必填项");
            }else{
                item.setContent_one(DeviceDetailsActivity.bhpz.getYxzt()+"");
            }
            item.setName_two("退出运行时间");
            if(TimeUtil.dateIsEmpty(DeviceDetailsActivity.bhpz.getTcyxsj())){
                item.setContent_two("必填项");
            }else{
                if (TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getTcyxsj())==null){
                    item.setContent_two("必填项");
                }else{
                    item.setContent_two(TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getTcyxsj())+"");
                }
            }
            item.setName_three("设备属性");
            if(DeviceDetailsActivity.bhpz.getBhsx()==null){
                item.setContent_three("必填项");
            }else{
                item.setContent_three(DeviceDetailsActivity.bhpz.getBhsx()+"");
            }
            data_name_two.add(item);

        DeviceDetailsNameItem item2 = new DeviceDetailsNameItem();
        item2.setNum(3);
        item2.setName_one("运行单位");
        if(DeviceDetailsActivity.bhpz.getYxdw()==null){
            item2.setContent_one("必填项");
        }else{
            item2.setContent_one(DeviceDetailsActivity.bhpz.getYxdw()+"");
        }
        item2.setName_two("维护单位");
        if(DeviceDetailsActivity.bhpz.getWhdw()==null){
            item2.setContent_two("必填项");
        }else{
            item2.setContent_two(DeviceDetailsActivity.bhpz.getWhdw()+"");
        }
        item2.setName_three("设计单位");
        if(DeviceDetailsActivity.bhpz.getSjdw()==null){
            item2.setContent_three("必填项");
        }else{
            item2.setContent_three(DeviceDetailsActivity.bhpz.getSjdw()+"");
        }
        data_name_two.add(item2);

        DeviceDetailsNameItem item3 = new DeviceDetailsNameItem();
        item3.setNum(5);
        item3.setName_one("基建单位");
        if(DeviceDetailsActivity.bhpz.getJjdw()==null){
            item3.setContent_one("必填项");
        }else{
            item3.setContent_one(DeviceDetailsActivity.bhpz.getJjdw()+"");
        }
        item3.setName_two("投运日期");
        if(TimeUtil.dateIsEmpty(DeviceDetailsActivity.bhpz.getTyrq())){
            item3.setContent_two("必填项");
        }else{
            if (TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getTyrq())==null){
                item3.setContent_two("必填项");
            }else{
                item3.setContent_two(TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getTyrq())+"");
            }
        }
        item3.setName_three("定期检验周期");
        if(DeviceDetailsActivity.bhpz.getDqjyzq()==0.0){
            item3.setContent_three("必填项");
        }else{
            item3.setContent_three(DeviceDetailsActivity.bhpz.getDqjyzq()+"");
        }
        data_name_two.add(item3);

        DeviceDetailsNameItem item4 = new DeviceDetailsNameItem();
        item4.setNum(6);
        item4.setName_one("上次检修时间");
        if (TimeUtil.dateIsEmpty(DeviceDetailsActivity.bhpz.getScdqjysj())){
            item4.setContent_one("必填项");
        }else{
            if (TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getScdqjysj())==null){
                item4.setContent_one("必填项");
            }else{
                item4.setContent_one(TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getScdqjysj())+"");
            }

        }
        item4.setName_two("出厂日期");
        if (TimeUtil.dateIsEmpty(DeviceDetailsActivity.bhpz.getCcrq())){
            item4.setContent_two("");
        }else{
            if (TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getCcrq())==null){
                item4.setContent_two("");
            }else{
                item4.setContent_two(TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getCcrq())+"");
            }
        }
        item4.setName_three("出厂编号");
        if(DeviceDetailsActivity.bhpz.getCcbh()==null){
            item4.setContent_three("");
        }else{
            item4.setContent_three(DeviceDetailsActivity.bhpz.getCcbh()+"");
        }
        data_name_two.add(item4);

        DeviceDetailsNameItem item5 = new DeviceDetailsNameItem();
        item5.setNum(7);
        item5.setName_one("资产单位");
        if(DeviceDetailsActivity.bhpz.getZcdw()==null){
            item5.setContent_one("");
        }else{
            item5.setContent_one(DeviceDetailsActivity.bhpz.getZcdw()+"");
        }
        item5.setName_two("资产性质");
        if(DeviceDetailsActivity.bhpz.getZcxz()==null){
            item5.setContent_two("");
        }else{
            item5.setContent_two(DeviceDetailsActivity.bhpz.getZcxz()+"");
        }
        item5.setName_three("资产编号");
        if(DeviceDetailsActivity.bhpz.getZcbh()==null){
            item5.setContent_three("");
        }else{
            item5.setContent_three(DeviceDetailsActivity.bhpz.getZcbh()+"");
        }
        data_name_two.add(item5);

        }

        //字符串非空验证
    public static boolean IsNotNull(String name) {
        boolean isnull = true;
        if (name==null||name.equals("")){
            isnull = false;
        }
        return isnull;
    }

     //非空验证
    public void savetwo() {
        DeviceDetailsActivity.instance.setpostion(1);  //跳转到该fragment
        if (!IsNotNull(DeviceDetailsActivity.bhpz.getYxzt())) {
            ToastUtils.showToast(getActivity(), "请选择运行状态");
        } else if (!IsNotNull(DeviceDetailsActivity.bhpz.getBhsx())) {
            ToastUtils.showToast(getActivity(), "请选择设备属性");
        } else if (!IsNotNull(DeviceDetailsActivity.bhpz.getYxdw())) {
            ToastUtils.showToast(getActivity(), "请选择运行单位");
        } else if (!IsNotNull(DeviceDetailsActivity.bhpz.getWhdw())) {
            ToastUtils.showToast(getActivity(), "请选择维护单位");
        } else if (!IsNotNull(DeviceDetailsActivity.bhpz.getSjdw())) {
            ToastUtils.showToast(getActivity(), "请选择设计单位");
        } else if (DeviceDetailsActivity.bhpz.getScdqjysj()==null) {
                ToastUtils.showToast(getActivity(), "请选择上次检修时间");
        } else if (DeviceDetailsActivity.bhpz.getTyrq()==null) {
                ToastUtils.showToast(getActivity(), "请选择投运日期");
        } else if (!IsNotNull(DeviceDetailsActivity.bhpz.getJjdw())) {
            ToastUtils.showToast(getActivity(), "请选择基建单位");
        } else if (DeviceDetailsActivity.bhpz.getDqjyzq()==0) {
            ToastUtils.showToast(getActivity(), "请选择定期检验周期");
        }else if (DeviceDetailsActivity.bhpz.getYxzt().equals("退运")) {
            if (DeviceDetailsActivity.bhpz.getTcyxsj() == null) {
                ToastUtils.showToast(getActivity(), "请选择退出运行时间");
            }else{
                saveSuccess=true;
            }
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
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //属性值选择框
    public void showDialog(final int Y, final int X,final List<String> item_name) {
        if (Y==0&&X==2){//退出运行时间
            data_name_two.get(Y).setContent_two(TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getTcyxsj())+"");
            adapter.notifyDataSetChanged();
            return;
        }else if (Y==2&&X==2){
            data_name_two.get(Y).setContent_two(TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getTyrq())+"");
            adapter.notifyDataSetChanged();
            return;
        }else if (Y==3){
            if (X==1){
                data_name_two.get(Y).setContent_one(TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getScdqjysj())+"");
                adapter.notifyDataSetChanged();
                return;
            }else if(X==2){
                data_name_two.get(Y).setContent_two(TimeUtil.formatString2(DeviceDetailsActivity.bhpz.getCcrq())+"");
                adapter.notifyDataSetChanged();
                return;
            }

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
            final Button btn_add = (Button) view.findViewById(R.id.btn_add);

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
                        //ToastUtils.showToast(getActivity(),"添加信息成功");
                    }
                }
            });

            //添加按钮隐藏与显示
            btn_add.setVisibility(View.GONE);

            //限制输入框输入
            if(Y==2){
                if (X==3){//定期检验周期  [DQJYZQ] NUMBER(6, 1),
                    btn_add.setVisibility(View.VISIBLE);
                    et_dialog.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    et_dialog.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                    //et_dialog.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);//限制输入一个小数点
                }
            }else if(Y==3){
                if (X==3){//出厂编号  [CCBH] VARCHAR2(100)
                    btn_add.setVisibility(View.VISIBLE);
                    et_dialog.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
                }
            }else if(Y==4){
                if (X==3){//资产编号 [ZCBH] VARCHAR2(100),
                    btn_add.setVisibility(View.VISIBLE);
                    et_dialog.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
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
                        if (X==1){//运行状态
                            data_name_two.get(Y).setContent_one(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setYxzt(data.get(position).toString());
                            if (data.get(position).equals("退运")){
                                data_name_two.get(Y).setContent_two("必填项");
                                DeviceDetailsActivity.bhpz.setTcyxsj(null);
                            }

                        }else if (X==3){//设备属性
                            data_name_two.get(Y).setContent_three(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setBhsx(data.get(position).toString());
                        }
                    }else if(Y==1){
                        if (X==1){//运行单位
                            data_name_two.get(Y).setContent_one(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setYxdw(data.get(position).toString());
                        }else if (X==2){//维护单位
                            data_name_two.get(Y).setContent_two(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setWhdw(data.get(position).toString());
                        }else if (X==3){//设计单位
                            data_name_two.get(Y).setContent_three(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setSjdw(data.get(position).toString());
                        }

                    }else if(Y==2){
                        if (X==1){//基建单位
                            data_name_two.get(Y).setContent_one(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setJjdw(data.get(position).toString());
                        }else if(X==3){ //定期检验周期
                            data_name_two.get(Y).setContent_three(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setDqjyzq(Float.parseFloat(data.get(position).toString()));
                        }
                    }else if(Y==3){
                        if (X==3){//出厂编号
                            data_name_two.get(Y).setContent_three(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setCcbh(data.get(position).toString());
                        }
                    }else if(Y==4){
                        if (X==1){//资产单位
                            data_name_two.get(Y).setContent_one(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setZcdw(data.get(position).toString());
                        }else if (X==2){//资产性质
                            data_name_two.get(Y).setContent_two(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setZcxz(data.get(position).toString());
                        }else if (X==3){//资产编号
                            data_name_two.get(Y).setContent_three(data.get(position)+"");
                            DeviceDetailsActivity.bhpz.setZcbh(data.get(position).toString());

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
