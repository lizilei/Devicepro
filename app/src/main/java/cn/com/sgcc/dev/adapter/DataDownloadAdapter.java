package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model2.CZXX;
import cn.com.sgcc.dev.model2.regist.CzInfo;

/**
 * <p>@description:在线方式获取数据-变电站列表适配器</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/3/20 0020
 */

public class DataDownloadAdapter extends BaseAdapter {
    private List<CzInfo.Czxq> list;
    private Context mContext;

    public DataDownloadAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public DataDownloadAdapter(List<CzInfo.Czxq> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    public void setDatas(List<CzInfo.Czxq> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_device_item_select, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CzInfo.Czxq czxx = list.get(position);
        if (czxx.isChecked()) {
            holder.iv.setVisibility(View.VISIBLE);
        } else {
            holder.iv.setVisibility(View.GONE);
        }
        holder.tv_zimu.setVisibility(View.GONE);
        holder.tv_czmc.setText(czxx.getStationName());
        holder.tv_czzgdydj.setVisibility(View.VISIBLE);
//        holder.tv_czzgdydj.setText(czxx.getCzzgdydj() + "kV");

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.fragment_one_device_item_select_tv)
        TextView tv_czmc;
        @BindView(R.id.fragment_two_device_item_select_tv)
        TextView tv_czzgdydj;
        @BindView(R.id.zimu)
        TextView tv_zimu;
        @BindView(R.id.iv_end_select)
        ImageView iv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
