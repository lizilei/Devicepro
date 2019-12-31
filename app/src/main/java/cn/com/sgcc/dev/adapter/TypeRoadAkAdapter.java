package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.HashMap;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Ak;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Base;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Inset;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Road_Ak;
import cn.com.sgcc.dev.view.fragment.IcdFragment;

import static cn.com.sgcc.dev.R.id.tv_four_device_name_left;
import static cn.com.sgcc.dev.R.id.tv_four_device_name_right;

/**
 * <p>@description:</p>
 * 通道信息和安控，适配器
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2018/1/2
 */
public class TypeRoadAkAdapter extends BaseAdapter {
    private List<DeviceDetailsNameItem> list;
    private Context context;
    private int type = 0;
    private List<String> item;
    private boolean check;

    public TypeRoadAkAdapter(Context context, List<DeviceDetailsNameItem> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HashMap<Integer, View> cuoluan = new HashMap<Integer, View>();
        ViewHolder holder = null;
        if (cuoluan.get(position) != null) {
            convertView = cuoluan.get(position);
            holder = (ViewHolder) convertView.getTag();
        } else {
//        final ViewHolder holder;
//        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.type_road_ak_adapter, null);
            holder = new ViewHolder(convertView);


            holder.rl_one = (RelativeLayout) convertView.findViewById(R.id.rl_one);
            holder.rl_three = (RelativeLayout) convertView.findViewById(R.id.rl_three);
            holder.ll_two = (LinearLayout) convertView.findViewById(R.id.ll_two);

            holder.cb_end_false = (CheckBox) convertView.findViewById(R.id.cb_end_false);
            holder.cb_end_true = (CheckBox) convertView.findViewById(R.id.cb_end_true);
            holder.tv_one_device_name_left = (TextView) convertView.findViewById(R.id.tv_one_device_name_left);
            holder.tv_one_device_name_right = (TextView) convertView.findViewById(R.id.tv_one_device_name_right);
            holder.fragment_road_ak_add_bt = (Button) convertView.findViewById(R.id.fragment_road_ak_add_bt);
            holder.tv_three_device_name_left = (TextView) convertView.findViewById(R.id.tv_three_device_name_left);
            holder.et_three_device_name_right = (EditText) convertView.findViewById(R.id.et_three_device_name_right);

            cuoluan.put(position, convertView);
            convertView.setTag(holder);

            AutoUtils.autoSize(convertView);
        }
        check = false;
        final ViewHolder finalHolder = holder;
        if (list.size() > 0) {

            holder.tv_one_device_name_left.setText(list.get(position).getName_one() + ":");
            holder.tv_one_device_name_right.setText(list.get(position).getContent_one() + "");

            holder.tv_three_device_name_left.setText(list.get(position).getName_three() + ":");
            holder.et_three_device_name_right.setText(list.get(position).getContent_three() + "");

        }
        if (position != 0) {
            //添加按钮隐藏
            holder.fragment_road_ak_add_bt.setText("取消-");
        }
        if (list.get(position).getContent_two().equals("是")){
            holder.cb_end_true.setChecked(true);
            holder.cb_end_false.setChecked(false);
        }else {
            holder.cb_end_false.setChecked(true);
            holder.cb_end_true.setChecked(false);
        }
//        holder.et_one_device_name_right.setFocusable(true);

        holder.tv_one_device_name_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_Type_Road_Ak.instance.setIntentData("通道类型",position);
            }
        });
        final ViewHolder finalHolder1 = holder;
        holder.cb_end_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalHolder1.cb_end_true.isChecked()){
                    finalHolder1.cb_end_false.setChecked(false);
                    list.get(position).setContent_two("是");
                }else {
                    finalHolder1.cb_end_false.setChecked(true);
                    list.get(position).setContent_two("否");
                }
            }
        });
        holder.cb_end_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalHolder1.cb_end_false.isChecked()){
                    finalHolder1.cb_end_true.setChecked(false);
                    list.get(position).setContent_two("否");
                }else {
                    finalHolder1.cb_end_true.setChecked(true);
                    list.get(position).setContent_two("是");
                }
            }
        });
        final ViewHolder finalHolder2 = holder;
        holder.et_three_device_name_right.addTextChangedListener(new TextWatcher() {
            String currentText;
            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after) {
                currentText = str.toString().trim();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String value = finalHolder1.et_three_device_name_right.getText()+"";
                if (position<list.size()){
                    list.get(position).setContent_three(value+"");
                }
                boolean isbt = false;
                if (Fragment_Type_Road_Ak.instance.saleVo.size()>0) {
                    for (SaleAttributeVo vo : Fragment_Type_Road_Ak.instance.saleVo) {
                        if (vo.getValue().equals("通道装置型号")) {
                            isbt = true;
                        }
                    }
                }
                if (!isbt){
                    return;
                }
                String tmp = s.toString().trim();
                if (currentText.equals("") && !tmp.equals("")) {
                    finalHolder2.et_three_device_name_right.setBackground(context.getResources().getDrawable(R.drawable.device_detials_bg));

                    /**
                     * 必填项校验设计
                     */
                    check = false;
                    if (Fragment_Type_Road_Ak.instance.saleVo.size()>0) {
                        for (SaleAttributeVo vo : Fragment_Type_Road_Ak.instance.saleVo) {
                            if (vo.getValue().equals("通道类型") && (finalHolder2.tv_one_device_name_right.getText().toString() + "").equals("")) {
                                check = true;
                            }
                        }
                        for (DeviceDetailsNameItem deviceDetailsNameItem : list) {
                            if (deviceDetailsNameItem.getContent_one().equals("")){
                                check = true;
                            }
                            if (deviceDetailsNameItem.getContent_three().equals("")){
                                check = true;
                            }
                        }
                        DemoActivity.instance.check("通道信息",check);
                    }
                } else if (!currentText.equals("") && tmp.equals("")) {
                    finalHolder2.et_three_device_name_right.setBackground(context.getResources().getDrawable(R.drawable.device_detials_bg2));
                    /**
                     * 必填项校验设计
                     */
                    check = true;
                    DemoActivity.instance.check("通道信息",check);
                }
                currentText = tmp;
            }
        });



        holder.fragment_road_ak_add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                    item.setName_one("通道类型");
                    item.setContent_one("");
                    item.setContent_two("否");
                    item.setName_three("通道装置型号");
                    item.setContent_three("");
                    item.setNum(1);
                    Fragment_Type_Road_Ak.instance.list_one_data.add(item);
                } else {
                    if (Fragment_Type_Road_Ak.instance.list_one_data.size() > 1) {
                        Fragment_Type_Road_Ak.instance.list_one_data.remove(position);
                    }
                }
                Fragment_Type_Road_Ak.instance.adapter.notifyDataSetChanged();
            }
        });

        if (list.get(position).getNum()==0){
            holder.tv_one_device_name_right.setBackground(null);
            holder.tv_one_device_name_right.setEnabled(false);
            holder.fragment_road_ak_add_bt.setVisibility(View.GONE);
            holder.et_three_device_name_right.setBackground(null);
            holder.et_three_device_name_right.setEnabled(false);
            holder.cb_end_true.setEnabled(false);
            holder.cb_end_false.setEnabled(false);

            Fragment_Type_Inset.instance.setDrawableLeft(holder.tv_one_device_name_left,false);
            Fragment_Type_Inset.instance.setDrawableLeft(holder.tv_three_device_name_left,false);
            boolean isxh = false;
            if (Fragment_Type_Road_Ak.instance.saleVo.size()>0) {
                for (SaleAttributeVo vo : Fragment_Type_Road_Ak.instance.saleVo) {
                    if (vo.getValue().equals("通道类型") && (holder.tv_one_device_name_right.getText().toString() + "").equals("")) {
                        Fragment_Type_Inset.instance.setDrawableLeft(holder.tv_one_device_name_left,true);
                        check = true;
                    }
                    if (vo.getValue().equals("通道装置型号") && (holder.et_three_device_name_right.getText().toString() + "").equals("")) {
                        Fragment_Type_Inset.instance.setDrawableLeft(holder.tv_three_device_name_left,true);
                        check = true;
                    }
                    if (vo.getValue().equals("通道装置型号")) {
                        isxh = true;
                    }
                }
                for (DeviceDetailsNameItem deviceDetailsNameItem : list) {
                    if (deviceDetailsNameItem.getContent_one().equals("")){
                        check = true;
                    }
                    if (isxh&&deviceDetailsNameItem.getContent_three().equals("")){
                        check = true;
                    }
                }
                DemoActivity.instance.check("通道信息",check);
            }
        }else if (list.get(position).getNum()==1){
            holder.tv_one_device_name_right.setBackgroundResource(R.drawable.device_detials_bg);
            holder.tv_one_device_name_right.setEnabled(true);
            holder.fragment_road_ak_add_bt.setVisibility(View.VISIBLE);
            holder.et_three_device_name_right.setBackgroundResource(R.drawable.device_detials_bg);
            holder.et_three_device_name_right.setEnabled(true);
            holder.cb_end_true.setEnabled(true);
            holder.cb_end_false.setEnabled(true);

            Fragment_Type_Inset.instance.setDrawableLeft(holder.tv_one_device_name_left,false);
            Fragment_Type_Inset.instance.setDrawableLeft(holder.tv_three_device_name_left,false);
            boolean isxh = false;
            if (Fragment_Type_Road_Ak.instance.saleVo.size()>0) {
                for (SaleAttributeVo vo : Fragment_Type_Road_Ak.instance.saleVo) {
                    if (vo.getValue().equals("通道类型") && (holder.tv_one_device_name_right.getText().toString() + "").equals("")) {
                        holder.tv_one_device_name_right.setBackgroundResource(R.drawable.device_detials_bg2);
                        check = true;
                    }
                    if (vo.getValue().equals("通道装置型号") && (holder.et_three_device_name_right.getText().toString() + "").equals("")) {
                        holder.et_three_device_name_right.setBackgroundResource(R.drawable.device_detials_bg2);
                        holder.et_three_device_name_right.setHint("必填项");
                        check = true;
                    }
                    if (vo.getValue().equals("通道装置型号")) {
                        isxh = true;
                    }
                }
                for (DeviceDetailsNameItem deviceDetailsNameItem : list) {
                    if (deviceDetailsNameItem.getContent_one().equals("")){
                        check = true;
                    }
                    if (isxh&&deviceDetailsNameItem.getContent_three().equals("")){
                        check = true;
                    }
                }
                DemoActivity.instance.check("通道信息",check);
            }
        }

        Fragment_Type_Road_Ak.instance.check = check;
        return convertView;
    }

    class ViewHolder {

        RelativeLayout rl_one;
        LinearLayout ll_two;
        RelativeLayout rl_three;

        TextView tv_one_device_name_left;
        Button fragment_road_ak_add_bt;
        TextView tv_one_device_name_right;

        CheckBox cb_end_true;
        CheckBox cb_end_false;

        TextView tv_three_device_name_left;
        EditText et_three_device_name_right;
        //通道类型，选择显示多选按钮,安控


        public ViewHolder(View convertView) {

        }
    }
}