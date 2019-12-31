package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.customeView.MyDatePickerDialog;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.utils.DeviceDateilsUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;
import cn.com.sgcc.dev.view.fragment.DeviceOneFragment;

/**
 * <p>@description:</p>
 * 设备详情一，适配器
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class DeviceOneAdapter extends BaseAdapter {
    private List<DeviceDetailsNameItem> list;
    private Context context;
    private int add_protect_type = 0;
    private List<String> item;

    public DeviceOneAdapter(Context context, List<DeviceDetailsNameItem> list) {
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
        if (cuoluan.get(position)!=null){
            convertView = cuoluan.get(position);
            holder = (ViewHolder) convertView.getTag();
        }else{
//        final ViewHolder holder;
//        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_one_device_item, null);
            holder = new ViewHolder(convertView);

            holder.c = Calendar.getInstance();
            holder.mYear = holder.c.get(Calendar.YEAR);
            holder.mMonth = holder.c.get(Calendar.MONTH) + 1;
            holder.mDay = holder.c.get(Calendar.DAY_OF_MONTH);

            holder.fragment_one_device_item_one = (LinearLayout) convertView.findViewById(R.id.fragment_one_device_item_one);
            holder.fragment_one_device_item_two = (LinearLayout) convertView.findViewById(R.id.fragment_one_device_item_two);
            holder.fragment_one_device_item_three = (LinearLayout) convertView.findViewById(R.id.fragment_one_device_item_three);

            holder.fragment_one_device_item_one_select_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_one_select_bt);
            holder.fragment_one_device_item_one_name_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_one_name_tv);

            holder.fragment_one_device_item_two_left_select_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_two_left_select_bt);
            holder.fragment_one_device_item_two_right_select_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_two_right_select_bt);
            holder.fragment_one_device_item_two_left_name_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_two_left_name_tv);
            holder.fragment_one_device_item_two_right_name_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_two_right_name_tv);

            holder.fragment_one_device_item_three_left_select_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_three_left_select_bt);
            holder.fragment_one_device_item_three_right_select_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_three_right_select_bt);
            holder.fragment_one_device_item_three_bottom_select_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_three_bottom_select_bt);
            holder.fragment_one_device_item_three_left_name_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_three_left_name_tv);
            holder.fragment_one_device_item_three_right_name_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_three_right_name_tv);
            holder.fragment_one_device_item_three_bottom_name_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_three_bottom_name_tv);

            holder.fragment_one_device_item_bottom_three_right_name_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_bottom_three_right_name_tv);
            holder.fragment_one_device_item_bottom_three_right_select_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_bottom_three_right_select_bt);

            holder.fragment_one_device_item_three_right_add_bt = (Button) convertView.findViewById(R.id.fragment_one_device_item_three_right_add_bt);

            //通道类型单选

            cuoluan.put(position,convertView);
            convertView.setTag(holder);
        }
//        else {
//            holder = (ViewHolder) convertView.getTag();
//        }

        item = new ArrayList<>();

        if (list.get(position).getNum() == 1) {
            holder.fragment_one_device_item_one.setVisibility(View.VISIBLE);
            holder.fragment_one_device_item_two.setVisibility(View.GONE);
            holder.fragment_one_device_item_three.setVisibility(View.GONE);

            holder.fragment_one_device_item_one_name_tv.setText(list.get(position).getName_one() + "");
            holder.fragment_one_device_item_one_select_bt.setText(list.get(position).getContent_one() + "");

            if (list.get(position).getName_one().equals("保护名称")) {
                holder.fragment_one_device_item_one_select_bt.setMaxWidth(600);
            }

            holder.fragment_one_device_item_one_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getName_one().equals("保护名称")) {
                        DeviceOneFragment.instance.made_protect_name(position);
                    } else {
                        //匹配处理，查询数据库
                        item.clear();
                        item = DeviceDateilsUtils.DeviceDateilsFind(list.get(position).getName_one() + "", context);
                        DeviceOneFragment.instance.showDialog("国家电力", "南方电力", position, 1, item);
                    }
                }
            });
        } else if (list.get(position).getNum() == 2) {
            holder.fragment_one_device_item_one.setVisibility(View.GONE);
            holder.fragment_one_device_item_two.setVisibility(View.VISIBLE);
            holder.fragment_one_device_item_three.setVisibility(View.GONE);

            holder.fragment_one_device_item_two_left_name_tv.setText(list.get(position).getName_one() + "");
            holder.fragment_one_device_item_two_left_select_bt.setText(list.get(position).getContent_one() + "");
            holder.fragment_one_device_item_two_right_name_tv.setText(list.get(position).getName_two() + "");
            holder.fragment_one_device_item_two_right_select_bt.setText(list.get(position).getContent_two() + "");

            holder.fragment_one_device_item_two_left_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtils.DeviceDateilsFind(list.get(position).getName_one() + "", context);
                    DeviceOneFragment.instance.showDialog("北京电力", "河北电力", position, 1, item);
                }
            });
            holder.fragment_one_device_item_two_right_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtils.DeviceDateilsFind(list.get(position).getName_two() + "", context);
                    DeviceOneFragment.instance.showDialog("湖南电力", "东北电力", position, 2, item);
                }
            });
        } else {
            holder.fragment_one_device_item_one.setVisibility(View.GONE);
            holder.fragment_one_device_item_two.setVisibility(View.GONE);
            holder.fragment_one_device_item_three.setVisibility(View.VISIBLE);

            holder.fragment_one_device_item_three_left_name_tv.setText(list.get(position).getName_one() + "");
            holder.fragment_one_device_item_three_left_select_bt.setText(list.get(position).getContent_one() + "");
            holder.fragment_one_device_item_three_right_name_tv.setText(list.get(position).getName_two() + "");
            holder.fragment_one_device_item_three_right_select_bt.setText(list.get(position).getContent_two() + "");
            holder.fragment_one_device_item_three_bottom_name_tv.setText(list.get(position).getName_three() + "");
            holder.fragment_one_device_item_three_bottom_select_bt.setText(list.get(position).getContent_three() + "");

            holder.fragment_one_device_item_three_left_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //模块名称code获取

                    item.clear();
                    item = DeviceDateilsUtils.DeviceDateilsFind(list.get(position).getName_one() + "", context);
                    DeviceOneFragment.instance.showDialog("都是电力", "哪哪电力", position, 1, item);
                }
            });
            holder.fragment_one_device_item_three_right_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //模块名称code获取
                    if (DeviceOneFragment.instance.ismodel_type) {
                        int add_device_type = DeviceOneFragment.instance.add_device_type;
                        int add_protect_type = DeviceOneFragment.instance.add_protect_type;
                        int add_model_type = DeviceOneFragment.instance.add_model_type;
                        if (position > 5 + add_device_type + add_protect_type && position <= 5 + add_device_type + add_protect_type + add_model_type) {
                            for (int i = 0; i < add_model_type; i++) {
                                if (position == 6 + add_device_type + add_protect_type + i) {
                                    //模块名称获取
                                    DeviceOneFragment.instance.model_one = "";
                                    DeviceOneFragment.instance.model_one = list.get(position).getContent_one();

                                    if (DeviceOneFragment.instance.model_data_type_code.size() > i) {
//                                        DeviceOneFragment.instance.model_data_type_get_code =
//                                                DeviceOneFragment.instance.model_data_type_code.get(i);
                                    }
                                }
                            }

                        }
                    }

                    item.clear();
                    item = DeviceDateilsUtils.DeviceDateilsFind(list.get(position).getName_two() + "", context);
                    DeviceOneFragment.instance.showDialog("不写电力", "长江电力", position, 2, item);
                }
            });
            final ViewHolder finalHolder = holder;
            holder.fragment_one_device_item_three_bottom_select_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getName_three().equals("生成日期")) {
                        finalHolder.dialog = new MyDatePickerDialog(context, new MyDatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                finalHolder.mYear = year;
                                finalHolder.mMonth = monthOfYear + 1;
                                finalHolder.mDay = dayOfMonth;
                                DeviceOneFragment.instance.set_made_day(position, finalHolder.mYear + "-" + finalHolder.mMonth + "-" + finalHolder.mDay);
                            }
                        }, finalHolder.mYear, finalHolder.mMonth, finalHolder.mDay);

                        if (finalHolder.mYear > 0) {
                            finalHolder.dialog.updateDate(finalHolder.mYear, finalHolder.mMonth, finalHolder.mDay);
                        }
                        finalHolder.dialog.myShow();
                    } else {
                        item.clear();
                        item = DeviceDateilsUtils.DeviceDateilsFind(list.get(position).getName_three() + "", context);
                        DeviceOneFragment.instance.showDialog("重庆电力", "深圳电力", position, 3, item);
                    }
                }
            });
            if (list.get(position).getNum() >= 4) {
                final int[] X = {0};
                X[0] = 4;
                holder.fragment_one_device_item_three_right_add_bt.setVisibility(View.VISIBLE);
                if (list.get(position - 1).getNum() == 4) {
                    holder.fragment_one_device_item_three_right_add_bt.setText("取消 - ");
                    X[0] = 5;
                }
                if (list.get(position).getNum() == 6) {
                    X[0] = 6;
                    if (list.get(position - 1).getNum() == 6) {
                        holder.fragment_one_device_item_three_right_add_bt.setText("取消 - ");
                        X[0] = 7;
                    }
                }
                if (list.get(position).getNum() == 8) {
                    X[0] = 8;
                    if (list.get(position - 1).getNum() == 8) {
                        holder.fragment_one_device_item_three_right_add_bt.setText("取消 - ");
                        X[0] = 9;
                    }
                }
                holder.fragment_one_device_item_three_right_add_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeviceOneFragment.instance.showDialog("重庆电力", "深圳电力", position, X[0], item);
                    }
                });
            } else {
                holder.fragment_one_device_item_three_right_add_bt.setVisibility(View.GONE);
            }

            if (list.get(position).getNum() >= 6 && list.get(position).getNum() < 8) {
                holder.fragment_one_device_item_three_bottom_name_tv.setVisibility(View.GONE);
                holder.fragment_one_device_item_three_bottom_select_bt.setVisibility(View.GONE);
            } else {
                holder.fragment_one_device_item_three_bottom_name_tv.setVisibility(View.VISIBLE);
                holder.fragment_one_device_item_three_bottom_select_bt.setVisibility(View.VISIBLE);
            }
            if (list.get(position).getName_one().equals("制造厂家")&&!DeviceOneFragment.instance.six_one_details.equals("2013版")) {
                holder.fragment_one_device_item_bottom_three_right_name_tv.setVisibility(View.VISIBLE);
                holder.fragment_one_device_item_bottom_three_right_select_bt.setVisibility(View.VISIBLE);
                holder.fragment_one_device_item_bottom_three_right_name_tv.setText(list.get(position).getName_four() + "");
                holder.fragment_one_device_item_bottom_three_right_select_bt.setText(list.get(position).getContent_four() + "");
                holder.fragment_one_device_item_bottom_three_right_select_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.clear();
                        item = DeviceDateilsUtils.DeviceDateilsFind(list.get(position).getName_four() + "", context);
                        DeviceOneFragment.instance.showDialog("", "", position, 40, item);
                    }
                });

            } else {
                holder.fragment_one_device_item_bottom_three_right_name_tv.setVisibility(View.GONE);
                holder.fragment_one_device_item_bottom_three_right_select_bt.setVisibility(View.GONE);
            }
        }


        return convertView;
    }

    static class ViewHolder {

        LinearLayout fragment_one_device_item_one;
        LinearLayout fragment_one_device_item_two;
        LinearLayout fragment_one_device_item_three;
        //通道类型，选择显示多选按钮

        Button fragment_one_device_item_one_select_bt;

        Button fragment_one_device_item_two_left_select_bt;
        Button fragment_one_device_item_two_right_select_bt;

        Button fragment_one_device_item_three_left_select_bt;
        Button fragment_one_device_item_three_right_select_bt;
        Button fragment_one_device_item_three_bottom_select_bt;
        Button fragment_one_device_item_bottom_three_right_select_bt;

        Button fragment_one_device_item_three_right_add_bt;

        TextView fragment_one_device_item_one_name_tv;

        TextView fragment_one_device_item_two_left_name_tv;
        TextView fragment_one_device_item_two_right_name_tv;

        TextView fragment_one_device_item_three_left_name_tv;
        TextView fragment_one_device_item_three_right_name_tv;
        TextView fragment_one_device_item_three_bottom_name_tv;
        TextView fragment_one_device_item_bottom_three_right_name_tv;

        private MyDatePickerDialog dialog;
        private int mYear, mMonth, mDay;
        private Calendar c;


        public ViewHolder(View convertView) {

        }
    }
}