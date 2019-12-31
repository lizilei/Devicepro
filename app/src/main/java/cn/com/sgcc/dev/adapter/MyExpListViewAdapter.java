package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model2.Accessory;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/21
 */

public class MyExpListViewAdapter extends BaseExpandableListAdapter {
    private List<String> groupData;
    private List<List<Accessory>> childData;
    private Context mContext;

    public MyExpListViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setDatas(List<String> groupData, List<List<Accessory>> childData) {
        this.groupData = groupData;
        this.childData = childData;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return groupData != null ? groupData.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childData != null ? childData.get(groupPosition).size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expand_listview_group_item, null);
            holder = new GroupHolder();
            holder.groupName = (TextView) convertView.findViewById(R.id.group_name);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.groupName.setText(groupData.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expand_listview_child_item, null);
            holder.name = (TextView) convertView.findViewById(R.id.acce_name);
            holder.size = (TextView) convertView.findViewById(R.id.acce_size);
            holder.time = (TextView) convertView.findViewById(R.id.acce_time);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        Accessory accessory = childData.get(groupPosition).get(childPosition);

        holder.name.setText(accessory.getName());
        holder.size.setText(accessory.getSize());
        holder.time.setText(accessory.getTime());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        private TextView groupName;
    }

    class ChildHolder {
        private TextView name;
        private TextView size;
        private TextView time;
    }
}
