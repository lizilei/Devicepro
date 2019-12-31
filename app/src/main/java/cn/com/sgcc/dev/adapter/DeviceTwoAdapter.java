package cn.com.sgcc.dev.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;
import cn.com.sgcc.dev.view.fragment.DeviceOneFragment;
import cn.com.sgcc.dev.view.fragment.DeviceTwoFragment;

/**
 * <p>@description:</p>
 *   设备详情二，适配器
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class DeviceTwoAdapter extends BaseAdapter {
    private List<DeviceDetailsNameItem> list;
    private Context context;
    private List<String> item;

    public DeviceTwoAdapter(Context context, List<DeviceDetailsNameItem> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_two_device_item, null);
            holder = new ViewHolder(convertView);
            holder.c = Calendar.getInstance();
            holder.mYear = holder.c.get(Calendar.YEAR);
            holder.mMonth = holder.c.get(Calendar.MONTH) + 1;
            holder.mDay = holder.c.get(Calendar.DAY_OF_MONTH);

            holder.fragment_one_device_item_three = (RelativeLayout) convertView.findViewById(R.id.fragment_one_device_item_three);

            holder.fragment_one_device_item_three_left_select_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_three_left_select_bt);
            holder.fragment_one_device_item_three_right_select_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_three_right_select_bt);
            holder.fragment_one_device_item_three_bottom_select_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_three_bottom_select_bt);
            holder.fragment_one_device_item_three_left_name_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_three_left_name_tv);
            holder.fragment_one_device_item_three_right_name_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_three_right_name_tv);
            holder.fragment_one_device_item_three_bottom_name_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_three_bottom_name_tv);
            holder.fragment_one_device_item_three_left_select_dateleft = (TextView) convertView.findViewById(R.id.fragment_one_device_item_three_left_select_dateleft);
            holder.fragment_one_device_item_three_right_select_et_right= (EditText) convertView.findViewById(R.id.fragment_one_device_item_three_right_select_et_right);

            holder.fragment_one_device_item_three_right_select_date= (TextView) convertView.findViewById(R.id.fragment_one_device_item_three_right_select_date);
            holder.fragment_one_device_item_three_bottom_select_et= (EditText) convertView.findViewById(R.id.fragment_one_device_item_three_bottom_select_et);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        item = new ArrayList<>();

        if (list.get(position).getNum()==3){//运行单位
            holder.fragment_one_device_item_three_left_name_tv.setText(list.get(position).getName_one()+"");
            holder.fragment_one_device_item_three_left_select_bt.setText(list.get(position).getContent_one()+"");
            holder.fragment_one_device_item_three_right_name_tv.setText(list.get(position).getName_two()+"");
            holder.fragment_one_device_item_three_right_select_bt.setText(list.get(position).getContent_two()+"");
            holder.fragment_one_device_item_three_bottom_name_tv.setText(list.get(position).getName_three()+"");
            holder.fragment_one_device_item_three_bottom_select_bt.setText(list.get(position).getContent_three()+"");

            holder.fragment_one_device_item_three_left_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind("运行/维护单位",context);
                    DeviceTwoFragment.instance.showDialog(position,1,item);
                }
            });
            holder.fragment_one_device_item_three_right_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind("运行/维护单位",context);
                    DeviceTwoFragment.instance.showDialog(position,2,item);
                }
            });
            holder.fragment_one_device_item_three_bottom_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_three()+"",context);
                    DeviceTwoFragment.instance.showDialog(position,3,item);
                }
            });
        }else if (list.get(position).getNum()==4){ //运行状态
            holder.fragment_one_device_item_three_left_name_tv.setText(list.get(position).getName_one()+"");
            holder.fragment_one_device_item_three_left_select_bt.setText(list.get(position).getContent_one()+"");
            holder.fragment_one_device_item_three_right_name_tv.setText(list.get(position).getName_two()+"");
            holder.fragment_one_device_item_three_right_select_bt.setText(list.get(position).getContent_two()+"");
            holder.fragment_one_device_item_three_bottom_name_tv.setText(list.get(position).getName_three()+"");
            holder.fragment_one_device_item_three_bottom_select_bt.setText(list.get(position).getContent_three()+"");

            holder.fragment_one_device_item_three_right_select_bt.setVisibility(View.GONE);
            holder.fragment_one_device_item_three_right_name_tv.setVisibility(View.GONE);
            holder.fragment_one_device_item_three_left_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_one()+"",context);
                    DeviceTwoFragment.instance.showDialog(position,1,item);
                }
            });

            //判断退出运行时间是否显示
            if (list.get(position).getContent_one().equals("退运")){
                holder.fragment_one_device_item_three_right_select_bt.setVisibility(View.VISIBLE);
                holder.fragment_one_device_item_three_right_name_tv.setVisibility(View.VISIBLE);
            }else{
                holder.fragment_one_device_item_three_right_select_bt.setVisibility(View.GONE);
                holder.fragment_one_device_item_three_right_name_tv.setVisibility(View.GONE);
            }

            //退出运行时间
            holder.fragment_one_device_item_three_right_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.dialog = new MyDatePickerDialog(context, new MyDatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            holder.mYear = year;
                            holder.mMonth = monthOfYear + 1;
                            holder.mDay = dayOfMonth;
                            DeviceDetailsActivity.bhpz.setTcyxsj(TimeUtil.formatString(holder.mYear + "-" + holder.mMonth + "-" + holder.mDay));
                            List<String> items = new ArrayList<String>();
                            DeviceTwoFragment.instance.showDialog(position,2,items);
                        }
                    }, holder.mYear, holder.mMonth, holder.mDay);

                    if (holder.mYear > 0) {
                        holder.dialog.updateDate(holder.mYear, holder.mMonth, holder.mDay);
                    }
                    holder.dialog.myShow();
                }
            });

            holder.fragment_one_device_item_three_bottom_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_three()+"",context);
                    DeviceTwoFragment.instance.showDialog(position,3,item);
                }
            });


        }else if (list.get(position).getNum()==5){ //基建单位
            holder.fragment_one_device_item_three_left_name_tv.setText(list.get(position).getName_one()+"");
            holder.fragment_one_device_item_three_left_select_bt.setText(list.get(position).getContent_one()+"");
            holder.fragment_one_device_item_three_right_name_tv.setText(list.get(position).getName_two()+"");
            holder.fragment_one_device_item_three_right_select_bt.setText(list.get(position).getContent_two()+"");
            holder.fragment_one_device_item_three_bottom_name_tv.setText(list.get(position).getName_three()+"");
            holder.fragment_one_device_item_three_bottom_select_bt.setText(list.get(position).getContent_three()+"");
            //holder.fragment_one_device_item_three_right_select_date.setVisibility(View.VISIBLE);
            //holder.fragment_one_device_item_three_bottom_select_bt.setVisibility(View.GONE);
//            holder.fragment_one_device_item_three_bottom_select_et.setVisibility(View.VISIBLE);
//            holder.fragment_one_device_item_three_bottom_select_et.setText(list.get(position).getContent_three()+"");
//
//            //定期检验周期
//            holder.fragment_one_device_item_three_bottom_select_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    if(hasFocus) {
//                        // 此处为获取焦点时的处理内容
//                        holder.fragment_one_device_item_three_bottom_select_et.setText("");
//                    } else {
//                        // 此处为失去焦点时的处理内容
//                        String s=holder.fragment_one_device_item_three_bottom_select_et.getText()+"";
//                        if (!s.isEmpty()){
//                            list.get(position).setContent_three(s);
//                            holder.fragment_one_device_item_three_bottom_select_et.setText(s);
//                            DeviceDetailsActivity.bhpz.setDqjyzq(Float.parseFloat(s));
//                        }
//                    }
//                }
//            });


            holder.fragment_one_device_item_three_left_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_one()+"",context);
                    DeviceTwoFragment.instance.showDialog(position,1,item);
                }
            });

            //投运日期
            holder.fragment_one_device_item_three_right_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.dialog = new MyDatePickerDialog(context, new MyDatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            holder.mYear = year;
                            holder.mMonth = monthOfYear + 1;
                            holder.mDay = dayOfMonth;
                            DeviceDetailsActivity.bhpz.setTyrq(TimeUtil.formatString(holder.mYear + "-" + holder.mMonth + "-" + holder.mDay));
                            List<String> items = new ArrayList<String>();
                            DeviceTwoFragment.instance.showDialog(position,2,items);

                        }
                    }, holder.mYear, holder.mMonth, holder.mDay);

                    if (holder.mYear > 0) {
                        holder.dialog.updateDate(holder.mYear, holder.mMonth, holder.mDay);
                    }
                    holder.dialog.myShow();
                }
            });

            holder.fragment_one_device_item_three_bottom_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_three()+"",context);
                    DeviceTwoFragment.instance.showDialog(position,3,item);
                }
            });
        }else if (list.get(position).getNum()==6){ //上次检修时间
            holder.fragment_one_device_item_three_left_name_tv.setText(list.get(position).getName_one()+"");
            holder.fragment_one_device_item_three_right_name_tv.setText(list.get(position).getName_two()+"");
            holder.fragment_one_device_item_three_bottom_name_tv.setText(list.get(position).getName_three()+"");
            holder.fragment_one_device_item_three_left_select_bt.setText(list.get(position).getContent_one()+"");
            holder.fragment_one_device_item_three_right_select_bt.setText(list.get(position).getContent_two()+"");
            holder.fragment_one_device_item_three_bottom_select_bt.setText(list.get(position).getContent_three()+"");

            //holder.fragment_one_device_item_three_left_select_dateleft.setVisibility(View.VISIBLE);
            //holder.fragment_one_device_item_three_left_select_dateleft.setText(list.get(position).getContent_one()+"");
            //holder.fragment_one_device_item_three_right_select_date.setVisibility(View.VISIBLE);

            //holder.fragment_one_device_item_three_bottom_select_bt.setVisibility(View.GONE);
//            holder.fragment_one_device_item_three_bottom_select_et.setVisibility(View.VISIBLE);
//            //出厂编号
//            list.get(position).setContent_three(holder.fragment_one_device_item_three_bottom_select_et.getText().toString());
//            holder.fragment_one_device_item_three_bottom_select_et.setText(list.get(position).getContent_three()+"");
//            DeviceDetailsActivity.bhpz.setCcbh(list.get(position).getContent_three());

            holder.fragment_one_device_item_three_left_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.dialog = new MyDatePickerDialog(context, new MyDatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            holder.mYear = year;
                            holder.mMonth = monthOfYear + 1;
                            holder.mDay = dayOfMonth;
                            DeviceDetailsActivity.bhpz.setScdqjysj(TimeUtil.formatString(holder.mYear + "-" + holder.mMonth + "-" + holder.mDay));
                            List<String> items = new ArrayList<String>();
                            DeviceTwoFragment.instance.showDialog(position,1,items);
                        }
                    }, holder.mYear, holder.mMonth, holder.mDay);

                    if (holder.mYear > 0) {
                        holder.dialog.updateDate(holder.mYear, holder.mMonth, holder.mDay);
                    }
                    holder.dialog.myShow();
                }
            });

            holder.fragment_one_device_item_three_right_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.dialog = new MyDatePickerDialog(context, new MyDatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            holder.mYear = year;
                            holder.mMonth = monthOfYear + 1;
                            holder.mDay = dayOfMonth;
                            String s=TimeUtil.formatString(holder.mYear + "-" + holder.mMonth + "-" + holder.mDay);
                            DeviceDetailsActivity.bhpz.setCcrq(s);
                            List<String> items = new ArrayList<String>();
                            DeviceTwoFragment.instance.showDialog(position,2,items);
                        }
                    }, holder.mYear, holder.mMonth, holder.mDay);

                    if (holder.mYear > 0) {
                        holder.dialog.updateDate(holder.mYear, holder.mMonth, holder.mDay);
                    }
                    holder.dialog.myShow();

                }
            });

            holder.fragment_one_device_item_three_bottom_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_three()+"",context);
                    DeviceTwoFragment.instance.showDialog(position,3,item);
                }
            });
        }else if (list.get(position).getNum()==7){ //资产单位
            holder.fragment_one_device_item_three_left_name_tv.setText(list.get(position).getName_one()+"");
            holder.fragment_one_device_item_three_left_select_bt.setText(list.get(position).getContent_one()+"");
            holder.fragment_one_device_item_three_right_name_tv.setText(list.get(position).getName_two()+"");
            holder.fragment_one_device_item_three_right_select_bt.setText(list.get(position).getContent_two()+"");
            holder.fragment_one_device_item_three_bottom_name_tv.setText(list.get(position).getName_three()+"");
            holder.fragment_one_device_item_three_bottom_select_bt.setText(list.get(position).getContent_three()+"");
            //holder.fragment_one_device_item_three_bottom_select_bt.setVisibility(View.GONE);
//            holder.fragment_one_device_item_three_bottom_select_et.setVisibility(View.VISIBLE);
//
//            //资产编号
//            list.get(position).setContent_three(holder.fragment_one_device_item_three_bottom_select_et.getText().toString());
//            holder.fragment_one_device_item_three_bottom_select_et.setText(list.get(position).getContent_three()+"");
//            DeviceDetailsActivity.bhpz.setZcbh(list.get(position).getContent_three());


            holder.fragment_one_device_item_three_left_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_one()+"",context);
                    DeviceTwoFragment.instance.showDialog(position,1,item);
                }
            });

            holder.fragment_one_device_item_three_right_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_two()+"",context);
                    DeviceTwoFragment.instance.showDialog(position,2,item);
                }
            });
            holder.fragment_one_device_item_three_bottom_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtilsTwo.DeviceDateilsFind(list.get(position).getName_three()+"",context);
                    DeviceTwoFragment.instance.showDialog(position,3,item);
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {

        RelativeLayout fragment_one_device_item_three;

        Button fragment_one_device_item_three_left_select_bt;
        Button fragment_one_device_item_three_right_select_bt;
        Button fragment_one_device_item_three_bottom_select_bt;

        TextView fragment_one_device_item_three_left_name_tv;
        TextView fragment_one_device_item_three_right_name_tv;
        TextView fragment_one_device_item_three_bottom_name_tv;

        TextView fragment_one_device_item_three_right_select_date;
        EditText fragment_one_device_item_three_bottom_select_et;
        TextView fragment_one_device_item_three_left_select_dateleft;
        EditText fragment_one_device_item_three_right_select_et_right;

        private MyDatePickerDialog dialog;
        private int mYear, mMonth, mDay;
        private Calendar c;

        public ViewHolder(View convertView) {

        }
    }
}