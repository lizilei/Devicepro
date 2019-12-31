package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.customeView.MyDatePickerDialog;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.utils.DeviceDateilsUtils;
import cn.com.sgcc.dev.utils.DeviceDateilsUtilsTwo;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;
import cn.com.sgcc.dev.view.fragment.DeviceOneFragment;
import cn.com.sgcc.dev.view.fragment.DeviceThreeFragment;
import cn.com.sgcc.dev.view.fragment.DeviceTwoFragment;

import static cn.com.sgcc.dev.R.id.fragment_one_device_item_three_bottom_select_et_wtite;

/**
 * <p>@description:</p>
 *   设备详情三，适配器
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class DeviceThreeAdapter extends BaseAdapter {
    private List<DeviceDetailsNameItem> list;
    private Context context;
    private List<String> item;

    public DeviceThreeAdapter(Context context, List<DeviceDetailsNameItem> list) {
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
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_three_device_item, null);
            holder = new ViewHolder(convertView);

            holder.c = Calendar.getInstance();
            holder.mYear = holder.c.get(Calendar.YEAR);
            holder.mMonth = holder.c.get(Calendar.MONTH) + 1;
            holder.mDay = holder.c.get(Calendar.DAY_OF_MONTH);

            //holder.fragment_one_device_item_three = (RelativeLayout) convertView.findViewById(R.id.fragment_one_device_item_three);
            holder.fragment_one_device_item_three_left_name_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_three_left_name_tv);
            holder.fragment_one_device_item_three_right_name_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_three_right_name_tv);
            holder.fragment_one_device_item_three_bottom_name_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_three_bottom_name_tv);
            holder.fragment_one_device_item_three_left_select_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_three_left_select_bt);

            holder.fragment_one_device_item_three_right_select_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_three_right_select_bt);

            holder.fragment_one_device_item_three_bottom_select_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_three_bottom_select_bt);
            holder.fragment_one_device_item_three_bottom_select_et_wtite= (EditText)convertView.findViewById(fragment_one_device_item_three_bottom_select_et_wtite);
            holder.fragment_one_device_item_three_right_select_et_right=(EditText)convertView.findViewById(R.id.fragment_one_device_item_three_right_select_et_right);
            holder.fragment_one_device_item_three_left_select_et_left=(EditText)convertView.findViewById(R.id.fragment_one_device_item_three_left_select_et_left);
            holder.fragment_one_device_item_three_bottom_select_date=(TextView) convertView.findViewById(R.id.fragment_one_device_item_three_bottom_select_date);
            holder.fragment_one_device_item_three_left_select_cb=(CheckBox) convertView.findViewById(R.id.fragment_one_device_item_three_left_select_cb);
            holder.fragment_one_device_item_three_right_select_cb=(CheckBox) convertView.findViewById(R.id.fragment_one_device_item_three_right_select_cb);

            holder.fengexian2=(TextView) convertView.findViewById(R.id.fengexian2);  //分割线

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        item = new ArrayList<>();

        if (list.get(position).getNum()==3){ //数据采集方式
            holder.fragment_one_device_item_three_left_name_tv.setText(list.get(position).getName_one()+"");
            holder.fragment_one_device_item_three_right_name_tv.setText(list.get(position).getName_two()+"");
            holder.fragment_one_device_item_three_bottom_name_tv.setText(list.get(position).getName_three()+"");

            holder.fragment_one_device_item_three_left_select_bt.setText(list.get(position).getContent_one()+"");
            holder.fragment_one_device_item_three_right_select_bt.setText(list.get(position).getContent_two()+"");
            holder.fragment_one_device_item_three_bottom_select_bt.setText(list.get(position).getContent_three()+"");
            //holder.fragment_one_device_item_three_bottom_select_bt.setVisibility(View.GONE);
//            holder.fragment_one_device_item_three_bottom_select_et_wtite.setVisibility(View.VISIBLE);
//            //所在屏柜
//            list.get(position).setContent_three(holder.fragment_one_device_item_three_bottom_select_et_wtite.getText().toString());
//            holder.fragment_one_device_item_three_bottom_select_et_wtite.setText(list.get(position).getContent_three()+"");
//            DeviceDetailsActivity.bhpz.setSzpg(list.get(position).getContent_three());

            holder.fragment_one_device_item_three_left_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_one()+"",context);
                    DeviceThreeFragment.instance.showDialog(position,1,item);
                }
            });
            holder.fragment_one_device_item_three_right_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_two()+"",context);
                    DeviceThreeFragment.instance.showDialog(position,2,item);
                }
            });
            holder.fragment_one_device_item_three_bottom_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_three()+"",context);
                    DeviceThreeFragment.instance.showDialog(position,3,item);
                }
            });
        }else if(list.get(position).getNum()==4){ //模拟通道数
            holder.fragment_one_device_item_three_left_name_tv.setText(list.get(position).getName_one()+"");
            holder.fragment_one_device_item_three_right_name_tv.setText(list.get(position).getName_two()+"");
            holder.fragment_one_device_item_three_bottom_name_tv.setText(list.get(position).getName_three()+"");
            holder.fragment_one_device_item_three_left_select_bt.setText(list.get(position).getContent_one()+"");
            holder.fragment_one_device_item_three_right_select_bt.setText(list.get(position).getContent_two()+"");
            holder.fragment_one_device_item_three_bottom_select_bt.setText(list.get(position).getContent_three()+"");

            holder.fragment_one_device_item_three_left_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_one()+"",context);
                    DeviceThreeFragment.instance.showDialog(position,1,item);
                }
            });
            holder.fragment_one_device_item_three_right_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_two()+"",context);
                    DeviceThreeFragment.instance.showDialog(position,2,item);
                }
            });
            holder.fragment_one_device_item_three_bottom_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_three()+"",context);
                    DeviceThreeFragment.instance.showDialog(position,3,item);
                }
            });

//            holder.fragment_one_device_item_three_left_select_bt.setVisibility(View.GONE);
//            holder.fragment_one_device_item_three_right_select_bt.setVisibility(View.GONE);
//            holder.fragment_one_device_item_three_bottom_select_bt.setVisibility(View.GONE);

//            //模拟通道数
//            holder.fragment_one_device_item_three_left_select_et_left.setVisibility(View.VISIBLE);
//            list.get(position).setContent_one(holder.fragment_one_device_item_three_left_select_et_left.getText().toString());
//            holder.fragment_one_device_item_three_left_select_et_left.setText(list.get(position).getContent_one()+"");
//            DeviceDetailsActivity.bhpz.setMntds(list.get(position).getContent_one());
//            //数字通道数
//            holder.fragment_one_device_item_three_right_select_et_right.setVisibility(View.VISIBLE);
//            list.get(position).setContent_two(holder.fragment_one_device_item_three_right_select_et_right.getText().toString());
//            holder.fragment_one_device_item_three_right_select_et_right.setText(list.get(position).getContent_two()+"");
//            DeviceDetailsActivity.bhpz.setSztds(list.get(position).getContent_two());
//            //实际变比
//            holder.fragment_one_device_item_three_bottom_select_et_wtite.setVisibility(View.VISIBLE);
//            list.get(position).setContent_three(holder.fragment_one_device_item_three_bottom_select_et_wtite.getText().toString());
//            holder.fragment_one_device_item_three_bottom_select_et_wtite.setText(list.get(position).getContent_three()+"");
//            DeviceDetailsActivity.bhpz.setSjbb(list.get(position).getContent_two());
        }else if(list.get(position).getNum()==5){ //额定变比
            holder.fragment_one_device_item_three_left_name_tv.setText(list.get(position).getName_one()+"");
            holder.fragment_one_device_item_three_right_name_tv.setText(list.get(position).getName_two()+"");
            holder.fragment_one_device_item_three_bottom_name_tv.setText(list.get(position).getName_three()+"");

            //holder.fragment_one_device_item_three_left_select_bt.setVisibility(View.GONE);
            //额定变比
//            holder.fragment_one_device_item_three_left_select_et_left.setVisibility(View.VISIBLE);
//            list.get(position).setContent_one(holder.fragment_one_device_item_three_left_select_et_left.getText().toString());
//            holder.fragment_one_device_item_three_left_select_et_left.setText(list.get(position).getContent_one()+"");
//            DeviceDetailsActivity.bhpz.setEdbb(list.get(position).getContent_one());

            holder.fragment_one_device_item_three_left_select_bt.setText(list.get(position).getContent_one()+"");
            holder.fragment_one_device_item_three_right_select_bt.setText(list.get(position).getContent_two()+"");
            holder.fragment_one_device_item_three_bottom_select_bt.setText(list.get(position).getContent_three()+"");

            holder.fragment_one_device_item_three_left_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_one()+"",context);
                    DeviceThreeFragment.instance.showDialog(position,1,item);
                }
            });

            holder.fragment_one_device_item_three_right_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_two()+"",context);
                    DeviceThreeFragment.instance.showDialog(position,2,item);
                }
            });

            holder.fragment_one_device_item_three_bottom_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_three()+"",context);
                    DeviceThreeFragment.instance.showDialog(position,3,item);
                }
            });
        }else if(list.get(position).getNum()==6){ //CT二次额定电流
            holder.fragment_one_device_item_three_left_name_tv.setText(list.get(position).getName_one()+"");
            holder.fragment_one_device_item_three_right_name_tv.setText(list.get(position).getName_two()+"");
            holder.fragment_one_device_item_three_bottom_name_tv.setText(list.get(position).getName_three()+"");

            holder.fragment_one_device_item_three_left_select_bt.setText(list.get(position).getContent_one()+"");
            holder.fragment_one_device_item_three_right_select_bt.setText(list.get(position).getContent_two()+"");
            holder.fragment_one_device_item_three_bottom_select_bt.setText(list.get(position).getContent_three()+"");
            //holder.fragment_one_device_item_three_right_select_bt.setVisibility(View.GONE);
            //holder.fragment_one_device_item_three_bottom_select_bt.setVisibility(View.GONE);

            //电源插件型号
//            holder.fragment_one_device_item_three_right_select_et_right.setVisibility(View.VISIBLE);
//            list.get(position).setContent_two(holder.fragment_one_device_item_three_right_select_et_right.getText().toString());
//            holder.fragment_one_device_item_three_right_select_et_right.setText(list.get(position).getContent_two()+"");
//            DeviceDetailsActivity.bhpz.setDycjxh(list.get(position).getContent_two());

            //holder.fragment_one_device_item_three_bottom_select_date.setVisibility(View.VISIBLE);

            holder.fragment_one_device_item_three_left_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_one()+"",context);
                    DeviceThreeFragment.instance.showDialog(position,1,item);
                }
            });

            holder.fragment_one_device_item_three_right_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_two()+"",context);
                    DeviceThreeFragment.instance.showDialog(position,2,item);
                }
            });

            //电源插件更换日期
            holder.fragment_one_device_item_three_bottom_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.dialog = new MyDatePickerDialog(context, new MyDatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            holder.mYear = year;
                            holder.mMonth = monthOfYear + 1;
                            holder.mDay = dayOfMonth;
                            DeviceDetailsActivity.bhpz.setDycjghrq(TimeUtil.formatString(holder.mYear + "-" + holder.mMonth + "-" + holder.mDay)+"");
                            List<String> items = new ArrayList<String>();
                            DeviceThreeFragment.instance.showDialog(position,3,items);
                        }
                    }, holder.mYear, holder.mMonth, holder.mDay);
                    if (holder.mYear > 0) {
                        holder.dialog.updateDate(holder.mYear, holder.mMonth, holder.mDay);
                    }
                    holder.dialog.myShow();

                }
            });

        }else if (list.get(position).getNum()==7){  //统计运行时间
            holder.fengexian2.setVisibility(View.GONE); //将分割线隐藏
            holder.fragment_one_device_item_three_left_name_tv.setText(list.get(position).getName_one()+"");
            holder.fragment_one_device_item_three_right_name_tv.setText(list.get(position).getName_two()+"");
            holder.fragment_one_device_item_three_right_select_bt.setText(list.get(position).getContent_two()+"");

            holder.fragment_one_device_item_three_left_select_bt.setVisibility(View.GONE);
            holder.fragment_one_device_item_three_left_select_cb.setVisibility(View.VISIBLE);
            holder.fragment_one_device_item_three_bottom_name_tv.setVisibility(View.GONE);
            holder.fragment_one_device_item_three_bottom_select_bt.setVisibility(View.GONE);

            //统计运行时间
            if ((list.get(position).getContent_one().equals("是"))){
                holder.fragment_one_device_item_three_left_select_cb.setChecked(true);
            }else{
                holder.fragment_one_device_item_three_left_select_cb.setChecked(false);
            }

            holder.fragment_one_device_item_three_left_select_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        DeviceDetailsActivity.bhpz.setSftjyxsj("是");
                        List<String> items = new ArrayList<String>();
                        DeviceThreeFragment.instance.showDialog(position,1,items);
                    }else{
                        DeviceDetailsActivity.bhpz.setSftjyxsj("否");
                        List<String> items = new ArrayList<String>();
                        DeviceThreeFragment.instance.showDialog(position,1,items);
                    }
                }
            });

            //板卡数量
            holder.fragment_one_device_item_three_right_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_two()+"",context);
                    DeviceThreeFragment.instance.showDialog(position,2,item);
                }
            });


            //关联退出运行保护配置
//            if ((list.get(position).getContent_two().equals("是"))){
//                holder.fragment_one_device_item_three_right_select_cb.setChecked(true);
//            }else{
//                holder.fragment_one_device_item_three_right_select_cb.setChecked(false);
//            }

//            holder.fragment_one_device_item_three_right_select_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked){
//                        DeviceDetailsActivity.bhpz.setTcyxid("是");
//                        List<String> items = new ArrayList<String>();
//                        DeviceThreeFragment.instance.showDialog(position,2,items);
//                    }else{
//                        DeviceDetailsActivity.bhpz.setTcyxid("否");
//                        List<String> items = new ArrayList<String>();
//                        DeviceThreeFragment.instance.showDialog(position,2,items);
//                    }
//                }
//            });

            //板卡数量
//            holder.fragment_one_device_item_three_bottom_select_et_wtite.setVisibility(View.VISIBLE);
//            holder.fragment_one_device_item_three_bottom_select_et_wtite.setText(list.get(position).getContent_three()+"");
//            holder.fragment_one_device_item_three_bottom_select_et_wtite.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    if(hasFocus) {
//                        // 此处为获取焦点时的处理内容
//                        holder.fragment_one_device_item_three_bottom_select_et_wtite.setText("");
//                    } else {
//                     // 此处为失去焦点时的处理内容
//                        String s=holder.fragment_one_device_item_three_bottom_select_et_wtite.getText()+"";
//                        if (!s.isEmpty()){
//                            list.get(position).setContent_three(s);
//                            holder.fragment_one_device_item_three_bottom_select_et_wtite.setText(s);
//                            DeviceDetailsActivity.bhpz.setBksl(Integer.parseInt(s));
//                        }
//                    }
//                }
//            });

        }

        return convertView;
    }

    static class ViewHolder {

        //RelativeLayout fragment_one_device_item_three;
        Button fragment_one_device_item_three_left_select_bt;
        Button fragment_one_device_item_three_right_select_bt;
        Button fragment_one_device_item_three_bottom_select_bt;

        TextView fragment_one_device_item_three_left_name_tv;
        TextView fragment_one_device_item_three_right_name_tv;
        TextView fragment_one_device_item_three_bottom_name_tv;
        TextView fragment_one_device_item_three_bottom_select_date;

        TextView fengexian2;

        EditText fragment_one_device_item_three_bottom_select_et_wtite;
        EditText fragment_one_device_item_three_right_select_et_right;
        EditText  fragment_one_device_item_three_left_select_et_left;

        CheckBox fragment_one_device_item_three_left_select_cb;
        CheckBox fragment_one_device_item_three_right_select_cb;

        private MyDatePickerDialog dialog;
        private int mYear, mMonth, mDay;
        private Calendar c;

        public ViewHolder(View convertView) {

        }
    }
}