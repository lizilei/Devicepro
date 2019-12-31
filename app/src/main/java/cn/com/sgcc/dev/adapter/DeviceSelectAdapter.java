package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.com.sgcc.dev.R;

/**
 * <p>@description:</p>
 *   设备详情选择，适配器
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class DeviceSelectAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public DeviceSelectAdapter(Context context, List<String> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_device_item_select, null);
            holder = new ViewHolder(convertView);
            holder.fragment_one_device_item_select_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_select_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        String name = "";
//        if (position%3==0){
//            name = "北京电力";
//        }else if (position%2==0){
//            name = "西北电力";
//        }else {
//            name = "南方电力";
//        }
        holder.fragment_one_device_item_select_tv.setText(""+list.get(position));


        return convertView;
    }

    static class ViewHolder {
        TextView fragment_one_device_item_select_tv;
//        RelativeLayout fragment_one_device_item_one;
//        RelativeLayout fragment_one_device_item_two;
//        RelativeLayout fragment_one_device_item_three;


        public ViewHolder(View convertView) {

        }
    }
}