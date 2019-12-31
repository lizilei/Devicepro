package cn.com.sgcc.dev.adapter;

import android.content.Context;
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
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Inset;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Road_Ak;

import static cn.com.sgcc.dev.R.id.tv_four_device_name_left;
import static cn.com.sgcc.dev.R.id.tv_four_device_name_right;

/**
 * <p>@description:</p>
 * 安控，适配器
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2018/1/2
 */
public class TypeAkAdapter extends BaseAdapter {
    private List<DeviceDetailsNameItem> list;
    private Context context;
    private int type = 0;
    private List<String> item;
    private boolean check= false;

    public TypeAkAdapter(Context context, List<DeviceDetailsNameItem> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.type_ak_adapter, null);
            holder = new ViewHolder(convertView);


            holder.rl_one = (RelativeLayout) convertView.findViewById(R.id.rl_one);

            holder.tv_one_device_name_left = (TextView) convertView.findViewById(R.id.tv_one_device_name_left);
            holder.tv_one_device_name_right = (TextView) convertView.findViewById(R.id.tv_one_device_name_right);
            holder.fragment_road_ak_add_bt = (Button) convertView.findViewById(R.id.fragment_road_ak_add_bt);

            holder.rl_four = (RelativeLayout) convertView.findViewById(R.id.rl_four);
            holder.tv_four_device_name_left = (TextView) convertView.findViewById(tv_four_device_name_left);
            holder.tv_four_device_name_right = (TextView) convertView.findViewById(tv_four_device_name_right);
            holder.fragment_add_bt_four = (Button) convertView.findViewById(R.id.fragment_add_bt_four);

            cuoluan.put(position, convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }
        check = false;
        final ViewHolder finalHolder = holder;
        if (list.size() > 0) {

            holder.tv_one_device_name_left.setText(list.get(position).getName_one() + ":");
            holder.tv_one_device_name_right.setText(list.get(position).getContent_one() + "");


            holder.rl_four.setVisibility(View.VISIBLE);
            holder.tv_four_device_name_left.setText(list.get(position).getName_four() + ":");
            holder.tv_four_device_name_right.setText(list.get(position).getContent_four() + "");

        }
        if (position != 0) {
            //添加按钮隐藏
            holder.fragment_road_ak_add_bt.setText("取消-");
        }
//        holder.et_one_device_name_right.setFocusable(true);
        holder.tv_one_device_name_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_Type_Ak.instance.setIntentData("安控系统调度名",position);
            }
        });
        holder.tv_four_device_name_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_Type_Ak.instance.setIntentData("安控站点类型",position);
            }
        });
        holder.fragment_add_bt_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_Type_Ak.instance.setIntentData("安控站点类型",position);
            }
        });

        holder.fragment_road_ak_add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    DeviceDetailsNameItem item = new DeviceDetailsNameItem();
                    item.setName_one("安控系统调度命名");
                    item.setContent_one("");
                    item.setContent_two("");
                    item.setName_four("安控站点类型");
                    item.setContent_four("");
                    item.setNum(1);
                    Fragment_Type_Ak.instance.list_one_data.add(item);
                    Fragment_Type_Ak.instance.akxt_id.add("");
                } else {
                    if (Fragment_Type_Ak.instance.list_one_data.size() > 1) {
                        Fragment_Type_Ak.instance.list_one_data.remove(position);
                        if (Fragment_Type_Ak.instance.akxt_id.size()>1){
                            Fragment_Type_Ak.instance.akxt_id.remove(position);
                        }
                    }
                }
                Fragment_Type_Ak.instance.adapter.notifyDataSetChanged();
            }
        });

        if (list.get(position).getNum()==0){
            holder.tv_one_device_name_right.setBackground(null);
            holder.fragment_road_ak_add_bt.setVisibility(View.GONE);
            holder.fragment_add_bt_four.setVisibility(View.GONE);
            holder.tv_four_device_name_right.setBackground(null);

            holder.tv_one_device_name_right.setEnabled(false);
            holder.tv_four_device_name_right.setEnabled(false);

            Fragment_Type_Inset.instance.setDrawableLeft(holder.tv_one_device_name_left,false);
            Fragment_Type_Inset.instance.setDrawableLeft(holder.tv_four_device_name_left,false);
            if (Fragment_Type_Ak.instance.saleVo.size()>0) {
                for (SaleAttributeVo vo : Fragment_Type_Ak.instance.saleVo) {
                    if (vo.getValue().equals("安控系统调度命名") && (holder.tv_one_device_name_right.getText().toString() + "").equals("")) {
                        Fragment_Type_Inset.instance.setDrawableLeft(holder.tv_one_device_name_left,true);
                        check = true;
                    }
                    if (vo.getValue().equals("安控站点类型") && (holder.tv_four_device_name_right.getText().toString() + "").equals("")) {
                        Fragment_Type_Inset.instance.setDrawableLeft(holder.tv_four_device_name_left,true);
                        check = true;
                    }
                }
                for (DeviceDetailsNameItem deviceDetailsNameItem : list) {
                    if (deviceDetailsNameItem.getContent_one().equals("")){
                        check = true;
                    }
                    if (deviceDetailsNameItem.getContent_four().equals("")){
                        check = true;
                    }
                }
                DemoActivity.instance.check("安控信息",check);
            }
        }else if (list.get(position).getNum()==1){
            holder.tv_one_device_name_right.setBackgroundResource(R.drawable.device_detials_bg);
            holder.fragment_road_ak_add_bt.setVisibility(View.VISIBLE);
            holder.fragment_add_bt_four.setVisibility(View.VISIBLE);
            holder.tv_four_device_name_right.setBackgroundResource(R.drawable.device_detials_bg);

            holder.tv_one_device_name_right.setEnabled(true);
            holder.tv_four_device_name_right.setEnabled(true);

            Fragment_Type_Inset.instance.setDrawableLeft(holder.tv_one_device_name_left,false);
            Fragment_Type_Inset.instance.setDrawableLeft(holder.tv_four_device_name_left,false);
            if (Fragment_Type_Ak.instance.saleVo.size()>0) {
                for (SaleAttributeVo vo : Fragment_Type_Ak.instance.saleVo) {
                    if (vo.getValue().equals("安控系统调度命名") && (holder.tv_one_device_name_right.getText().toString() + "").equals("")) {
                        holder.tv_one_device_name_right.setBackgroundResource(R.drawable.device_detials_bg2);
                        check = true;
                    }
                    if (vo.getValue().equals("安控站点类型") && (holder.tv_four_device_name_right.getText().toString() + "").equals("")) {
                        holder.tv_four_device_name_right.setBackgroundResource(R.drawable.device_detials_bg2);
                        check = true;
                    }
                }
                for (DeviceDetailsNameItem deviceDetailsNameItem : list) {
                    if (deviceDetailsNameItem.getContent_one().equals("")){
                        check = true;
                    }
                    if (deviceDetailsNameItem.getContent_four().equals("")){
                        check = true;
                    }
                }
                DemoActivity.instance.check("安控信息",check);
            }
        }


        return convertView;
    }

    class ViewHolder {

        RelativeLayout rl_one;

        TextView tv_one_device_name_left;
        Button fragment_road_ak_add_bt;
        TextView tv_one_device_name_right;

        //安控系统
        RelativeLayout rl_four;
        TextView tv_four_device_name_left;
        TextView tv_four_device_name_right;
        Button fragment_add_bt_four;


        public ViewHolder(View convertView) {

        }
    }
}