package cn.com.sgcc.dev.view.fragment;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.GoodsAttrListAdapter;
import cn.com.sgcc.dev.adapter.GoodsAttrsAdapter;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.view.activity.DeviceListActivity;

/**
 * <p>@description:筛选属性选择的popupwindow</p>
 * @author lxf
 * @version 1.0.0
 * @since 2018/1/2
 */
public class FilterPopupWindow extends PopupWindow {
    private View contentView;
    private Context context;
    private View goodsNoView;

    private ImageView attr_list_imgs;
    private GridView serviceGrid;
    private ListView selectionList;
    private TextView filterReset;
    private TextView filterSure;
    private EditText et_ycsbmc,et_zzcj,et_xh;
    private GoodsAttrListAdapter adapter;
    private GoodsAttrsAdapter serviceAdapter;
    private List<SaleAttributeNameVo> itemData=new ArrayList<SaleAttributeNameVo>(); //所有要展示的属性和属性值
    private List<SaleAttributeNameVo> itemData2=new ArrayList<SaleAttributeNameVo>(); //被选中的属性和属性值,ischeck是true
    private List<SaleAttributeVo> serviceList;
    private String[] shuruValue =new String[3];
    String ycsbmc=""; //"一次设备名称", "制造厂家", "型号"
    String zzcj="";
    String xh="";
    private String[] serviceStr = new String[]{"南瑞继保", "国电南自", "四方继保",
            "许继电气", "长园深瑞", "国电南瑞"};
    Boolean flag_quanbu=false;

    /**
     * 商品属性选择的popupwindow  构造方法
     */
    public FilterPopupWindow(final Activity context, final List<SaleAttributeNameVo> itemData) {
//        itemData.clear();
//        itemData2.clear();
        this.context = context;
        this.itemData = itemData;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popup_goods_details, null);
        goodsNoView = contentView.findViewById(R.id.popup_goods_noview);
        serviceGrid = (GridView) contentView.findViewById(R.id.yuguo_service);
        selectionList = (ListView) contentView.findViewById(R.id.selection_list);
        filterReset = (TextView) contentView.findViewById(R.id.filter_reset);
        filterSure = (TextView) contentView.findViewById(R.id.filter_sure);
        et_ycsbmc=(EditText) contentView.findViewById(R.id.et_ycsbmc);
        et_zzcj=(EditText) contentView.findViewById(R.id.et_zzcj);
        et_xh=(EditText) contentView.findViewById(R.id.et_xh);
        attr_list_imgs=(ImageView) contentView.findViewById(R.id.attr_list_imgs);
        goodsNoView.setOnClickListener(new CancelOnClickListener()); //点击左侧空白消失

        //-----------制造厂家功能改进---------------------------------
        serviceList = new ArrayList<SaleAttributeVo>();
        for (int i = 0; i < serviceStr.length; i++) {
            SaleAttributeVo vo = new SaleAttributeVo();
            vo.setValue(serviceStr[i]);
            serviceList.add(vo);
        }
        serviceAdapter = new GoodsAttrsAdapter(context);
        serviceGrid.setAdapter(serviceAdapter);
        serviceAdapter.notifyDataSetChanged(false, serviceList);
        serviceGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //设置当前选中的位置的状态为非。  arg2是点击的位置
                serviceList.get(arg2).setChecked(!serviceList.get(arg2).isChecked());
                et_zzcj.setText(serviceList.get(arg2).getValue());
                for (int i = 0; i < serviceList.size(); i++) {
                    //跳过已设置的选中的位置的状态
                    if (i == arg2) {
                        continue;
                    }
                    serviceList.get(i).setChecked(false);
                }
                serviceAdapter.notifyDataSetChanged(flag_quanbu, serviceList);
            }
        });

        attr_list_imgs.setOnClickListener(new OnClickListener() { //上下箭头控制
            @Override
            public void onClick(View v) {
                if (flag_quanbu){
                    flag_quanbu=false;
                    ((ImageView) v).setImageResource(R.drawable.sort_common_down);
                    serviceAdapter.notifyDataSetChanged(false, serviceList);
                }else{
                    flag_quanbu=true;
                    ((ImageView) v).setImageResource(R.drawable.sort_common_up);
                    serviceAdapter.notifyDataSetChanged(true, serviceList);
                }
            }
        });
        //--------------------------------------------------------------------------------

        for (int i = 0; i < itemData.size(); i++) {
            if (itemData.get(i).getName().equals("一次设备名称")){
                et_ycsbmc.setText(itemData.get(i).getSaleVo().get(0).getValue());
            }else if (itemData.get(i).getName().equals("制造厂家")){
                et_zzcj.setText(itemData.get(i).getSaleVo().get(0).getValue());
            }else if (itemData.get(i).getName().equals("型号")){
                et_xh.setText(itemData.get(i).getSaleVo().get(0).getValue());
            }
        }

        adapter = new GoodsAttrListAdapter(context, itemData);
        selectionList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // 重置的点击监听，将所有选项全设为false
        filterReset.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int i = 0; i < itemData.size(); i++) {
                    for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                        itemData.get(i).getSaleVo().get(j).setChecked(false);
                    }
                }
                adapter.notifyDataSetChanged();
                adapter.getReset(true);

                //清空三个输入框  et_ycsbmc  et_zzcj  et_xh
                et_ycsbmc.setText("");
                et_zzcj.setText("");
                et_xh.setText("");

                for (int i = 0; i < serviceList.size(); i++) {
                    serviceList.get(i).setChecked(false);
                }
                serviceAdapter.notifyDataSetChanged(flag_quanbu, serviceList);

            }
        });

        // 确定的点击监听，将所有已选中项列出
        filterSure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //getEditValue();
                dismiss(); //筛选消失
            }
        });

        this.setContentView(contentView);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.MATCH_PARENT);
        ColorDrawable dw = new ColorDrawable(00000000);
        this.setBackgroundDrawable(dw);
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        //返回键的监听   dismiss监听
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                getEditValue();
            }
        });
        this.update();

    }

    //popupwindow消失时取值
    public void getEditValue(){
            for (int i = 0; i < itemData.size(); i++) {
                SaleAttributeNameVo saleName = new SaleAttributeNameVo();
                saleName.setName(itemData.get(i).getName()); //name,即属性名------------------------
                List<SaleAttributeVo> list6 = new ArrayList<SaleAttributeVo>();
                for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                    SaleAttributeVo vo = new SaleAttributeVo();
                    if (itemData.get(i).getSaleVo().get(j).isChecked()){
                        vo.setValue(itemData.get(i).getSaleVo().get(j).getValue());
                        list6.add(vo);
                    }
                }
                if (list6.size()>0){
                    saleName.setSaleVo(list6);   //设置saleVo,即属性值------------------
                    itemData2.add(saleName);     //将所有选中的属性和属性值放入itemData2
                    //Toast.makeText(FilterPopupWindow.this.context, saleName.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            //取值三个输入框  et_ycsbmc  et_zzcj  et_xh
            ycsbmc=et_ycsbmc.getText().toString();
            SaleAttributeNameVo saleName = new SaleAttributeNameVo();
            saleName.setName("一次设备名称");
            List<SaleAttributeVo> list_ycsbmc = new ArrayList<SaleAttributeVo>();
            SaleAttributeVo vo = new SaleAttributeVo();
            vo.setValue(ycsbmc);
            list_ycsbmc.add(vo);
            saleName.setSaleVo(list_ycsbmc);
            if (StringEmpty(ycsbmc)){
                itemData2.add(saleName);
                itemData.set(itemData.size()-3,saleName);
            }else{
                itemData.set(itemData.size()-3,saleName);
            }

            xh=et_xh.getText().toString();
            SaleAttributeNameVo saleName3 = new SaleAttributeNameVo();
            saleName3.setName("型号");
            List<SaleAttributeVo> list_xh = new ArrayList<SaleAttributeVo>();
            SaleAttributeVo vo3 = new SaleAttributeVo();
            vo3.setValue(xh);
            list_xh.add(vo3);
            saleName3.setSaleVo(list_xh);
            if (StringEmpty(xh)){
                itemData2.add(saleName3);
                itemData.set(itemData.size()-2,saleName3);
            }else{
                itemData.set(itemData.size()-2,saleName3);
            }

            zzcj=et_zzcj.getText().toString();
            SaleAttributeNameVo saleName2 = new SaleAttributeNameVo();
            saleName2.setName("制造厂家");
            List<SaleAttributeVo> list_zzcj = new ArrayList<SaleAttributeVo>();
            SaleAttributeVo vo2 = new SaleAttributeVo();
            vo2.setValue(zzcj);
            list_zzcj.add(vo2);
            saleName2.setSaleVo(list_zzcj);
            if (StringEmpty(zzcj)) {
                itemData2.add(saleName2);
                itemData.set(itemData.size()-1,saleName2);
            }else{
                itemData.set(itemData.size()-1,saleName2);
            }

            DeviceListActivity.instance.receiveData(itemData,itemData2);
    }

    public Boolean StringEmpty(String s){
        if (s!=null&&!"".equals(s)&&!"null".equals(s)){
            return true;
        }else{
            return false;
        }
    }

    //点击左侧空白消失
    public class CancelOnClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    }

    //显示筛选
    public void showFilterPopup(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }

}
