package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//import com.zhy.autolayout.utils.AutoUtils;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import cn.com.sgcc.dev.R;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/25
 */

public class MyTypeAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private List<String> checklist;
    private int currentSelect;

    private int position;

    public MyTypeAdapter(Context context) {
        this.context = context;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDatas(List<String> list, List<String> checklist) {
        this.list = list;
        this.checklist = checklist;
        notifyDataSetChanged();
    }

    public void setSelect(int currentSelect) {
        this.currentSelect = currentSelect;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_app, null);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);

            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position == currentSelect) {
            holder.tv.setSelected(true);
            holder.tv.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            holder.tv.setSelected(false);
            holder.tv.setBackgroundColor(context.getResources().getColor(R.color.commonly_bg_color2));
        }
        if (checklist.contains(list.get(position))) {
            Drawable drawable = context.getResources().getDrawable(R.drawable.tanhao);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//            drawable.setBounds(0,0,0,0);
            holder.tv.setCompoundDrawables(drawable, null, null, null);
        } else {
//            Drawable drawable = context.getResources().getDrawable(R.drawable.tanhao);
//            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
//            drawable.setBounds(0, 0, 0, 0);
            holder.tv.setCompoundDrawables(null, null, null, null);
        }
        holder.tv.setText(list.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView tv;
    }
}
