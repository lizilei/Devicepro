package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 自定义CommonAdapter
 * Created by Administrator on 2016/10/27.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    private List<T> list;
    private Context mContext;
    private final int resId;
    private ViewHolder viewHolder;

    public CommonAdapter(Context mContext, int resId) {
        this.mContext = mContext;
        this.resId = resId;
    }

    public CommonAdapter(Context mContext, List<T> list, int resId) {
        this.mContext = mContext;
        this.list = list;
        this.resId = resId;
    }

    public void setDatas(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder helper, T item);

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, resId, position);
    }
}
