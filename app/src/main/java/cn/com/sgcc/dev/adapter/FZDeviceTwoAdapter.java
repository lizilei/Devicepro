package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.utils.DeviceDateilsUtils;
import cn.com.sgcc.dev.view.fragment.FZDeviceTwoFragment;

/**
 * <p>@description:</p>
 * 辅助设备详情一，适配器
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/31
 */
public class FZDeviceTwoAdapter extends BaseAdapter {
    private List<DeviceDetailsNameItem> list;
    private Context context;
    private List<String> item;

    public FZDeviceTwoAdapter(Context context, List<DeviceDetailsNameItem> list) {
        this.list = list;
        this.context = context;
    }

    public void setDatas(List<DeviceDetailsNameItem> list) {
        this.list = list;
        notifyDataSetChanged();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_fz_one_item, null);
            holder = new ViewHolder();
            holder.ll_fz_one_type = (LinearLayout) convertView.findViewById(R.id.ll_fz_one_type);
            holder.ll_fz_two_type = (LinearLayout) convertView.findViewById(R.id.ll_fz_two_type);
            holder.rl_fz_first_two = (RelativeLayout) convertView.findViewById(R.id.rl_fz_first_two);
            holder.rl_fz_second_two = (RelativeLayout) convertView.findViewById(R.id.rl_fz_second_two);

            holder.fz_one_type_name = (TextView) convertView.findViewById(R.id.fz_one_type_name);
            holder.fz_two_type_name = (TextView) convertView.findViewById(R.id.fz_two_type_name);
            holder.fz_three_type_name = (TextView) convertView.findViewById(R.id.fz_three_type_name);
            holder.fz_four_type_name = (TextView) convertView.findViewById(R.id.fz_four_type_name);

            holder.fz_one_type_choose = (Button) convertView.findViewById(R.id.fz_one_type_choose);
            holder.fz_two_type_choose = (Button) convertView.findViewById(R.id.fz_two_type_choose);
            holder.fz_three_type_choose = (Button) convertView.findViewById(R.id.fz_three_type_choose);
            holder.fz_four_type_choose = (Button) convertView.findViewById(R.id.fz_four_type_choose);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        item = new ArrayList<>();
        if (list.get(position).getNum() == 1) {
            holder.ll_fz_one_type.setVisibility(View.VISIBLE);
            holder.ll_fz_two_type.setVisibility(View.GONE);
            holder.rl_fz_first_two.setVisibility(View.VISIBLE);
            holder.fz_two_type_name.setVisibility(View.GONE);
            holder.fz_two_type_choose.setVisibility(View.GONE);

            holder.fz_one_type_name.setText(list.get(position).getName_one());
            holder.fz_one_type_choose.setText(list.get(position).getContent_one());

            holder.fz_one_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //匹配处理，查询数据库
                    item.clear();
                    item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_one());
                    FZDeviceTwoFragment.instance.showDialog(1, position, item);
                }
            });
        } else if (list.get(position).getNum() == 2) {
            holder.ll_fz_one_type.setVisibility(View.VISIBLE);
            holder.ll_fz_two_type.setVisibility(View.GONE);
            holder.rl_fz_first_two.setVisibility(View.VISIBLE);
            holder.fz_two_type_name.setVisibility(View.VISIBLE);
            holder.fz_two_type_choose.setVisibility(View.VISIBLE);

            holder.fz_one_type_name.setText(list.get(position).getName_one());
            holder.fz_one_type_choose.setText(list.get(position).getContent_one());
            holder.fz_two_type_name.setText(list.get(position).getName_two());
            holder.fz_two_type_choose.setText(list.get(position).getContent_two());

            holder.fz_one_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_one());
                    FZDeviceTwoFragment.instance.showDialog(1, position, item);
                }
            });
            holder.fz_two_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getName_two().equals("上次检修时间")) {
                        FZDeviceTwoFragment.instance.showDateDialog(position, 2);
                    } else {
                        item.clear();
                        item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_two());
                        FZDeviceTwoFragment.instance.showDialog(2, position, item);
                    }
                }
            });
        } else if (list.get(position).getNum() == 3) {

            holder.ll_fz_one_type.setVisibility(View.VISIBLE);
            holder.ll_fz_two_type.setVisibility(View.VISIBLE);
            holder.rl_fz_first_two.setVisibility(View.VISIBLE);
            holder.fz_two_type_name.setVisibility(View.VISIBLE);
            holder.fz_two_type_choose.setVisibility(View.VISIBLE);
            holder.rl_fz_second_two.setVisibility(View.VISIBLE);
            holder.fz_four_type_name.setVisibility(View.GONE);
            holder.fz_four_type_choose.setVisibility(View.GONE);

            holder.fz_one_type_name.setText(list.get(position).getName_one());
            holder.fz_one_type_choose.setText(list.get(position).getContent_one());
            holder.fz_two_type_name.setText(list.get(position).getName_two());
            holder.fz_two_type_choose.setText(list.get(position).getContent_two());
            holder.fz_three_type_name.setText(list.get(position).getName_three());
            holder.fz_three_type_choose.setText(list.get(position).getContent_three());
            holder.fz_one_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //直接跳选时间
                    FZDeviceTwoFragment.instance.showDateDialog(position, 1);
                }
            });
            holder.fz_two_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_two());
                    FZDeviceTwoFragment.instance.showDialog(2, position, item);
                }
            });
            holder.fz_three_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getName_three().equals("上次检修时间")) {
                        FZDeviceTwoFragment.instance.showDateDialog(position, 3);
                    } else {
                        item.clear();
                        item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_three());
                        FZDeviceTwoFragment.instance.showDialog(3, position, item);
                    }
                }
            });
        } else if (list.get(position).getNum() == 4) {
            holder.ll_fz_one_type.setVisibility(View.VISIBLE);
            holder.ll_fz_two_type.setVisibility(View.VISIBLE);
            holder.rl_fz_first_two.setVisibility(View.VISIBLE);
            holder.fz_two_type_name.setVisibility(View.VISIBLE);
            holder.fz_two_type_choose.setVisibility(View.VISIBLE);
            holder.rl_fz_second_two.setVisibility(View.VISIBLE);
            holder.fz_four_type_name.setVisibility(View.VISIBLE);
            holder.fz_four_type_choose.setVisibility(View.VISIBLE);

            holder.fz_one_type_name.setText(list.get(position).getName_one());
            holder.fz_one_type_choose.setText(list.get(position).getContent_one());
            holder.fz_two_type_name.setText(list.get(position).getName_two());
            holder.fz_two_type_choose.setText(list.get(position).getContent_two());
            holder.fz_three_type_name.setText(list.get(position).getName_three());
            holder.fz_three_type_choose.setText(list.get(position).getContent_three());
            holder.fz_four_type_name.setText(list.get(position).getName_four());
            holder.fz_four_type_choose.setText(list.get(position).getContent_four());

            holder.fz_one_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getName_one().equals("出厂日期")) {
                        FZDeviceTwoFragment.instance.showDateDialog(position, 1);
                    } else {
                        item.clear();
                        item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_one());
                        FZDeviceTwoFragment.instance.showDialog(1, position, item);
                    }
                }
            });
            holder.fz_two_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_two());
                    FZDeviceTwoFragment.instance.showDialog(2, position, item);
                }
            });
            holder.fz_three_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_three());
                    FZDeviceTwoFragment.instance.showDialog(3, position, item);
                }
            });
            holder.fz_four_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_four());
                    FZDeviceTwoFragment.instance.showDialog(4, position, item);
                }
            });
        }

        return convertView;
    }

    static class ViewHolder {
        LinearLayout ll_fz_one_type;
        LinearLayout ll_fz_two_type;
        RelativeLayout rl_fz_first_two;
        RelativeLayout rl_fz_second_two;

        TextView fz_one_type_name;
        Button fz_one_type_choose;
        TextView fz_two_type_name;
        Button fz_two_type_choose;
        TextView fz_three_type_name;
        Button fz_three_type_choose;
        TextView fz_four_type_name;
        Button fz_four_type_choose;
    }
}