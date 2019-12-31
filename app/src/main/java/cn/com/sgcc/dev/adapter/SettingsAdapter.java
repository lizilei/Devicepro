package cn.com.sgcc.dev.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.customeView.recylerUtil.MyItemClickListener;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

/**
 * <p>@description:</p>
 *
 * @author lxf
 * @version 1.0.0
 */

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.Holder> {

    private List<String> stringList;
    private Context context;
    private MyItemClickListener mItemClickListener;
    private int TYPE_NORMAL = 1000;

    public SettingsAdapter(Context context) {
        this.context = context;
    }

    /**
     * @param list
     */
    public void setDatas(List<String> list) {
        this.stringList = list;
        notifyDataSetChanged();
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.station_recycler_settings, parent, false);
        return new Holder(viewType, v, mItemClickListener);
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tv_name.setText(stringList.get(position)); //可以在此设置图标
    }

    @Override
    public int getItemCount() {
            return stringList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_NORMAL;
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_name;
        private ImageView device_photo,jiantou_photo;
        private RelativeLayout ll_main;
        private MyItemClickListener mListener;

        public Holder(int viewType, View itemView, MyItemClickListener mListener) {
            super(itemView);
            AutoUtils.autoSize(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            jiantou_photo = (ImageView) itemView.findViewById(R.id.jiantou_photo);
            device_photo= (ImageView) itemView.findViewById(R.id.device_photo);
            ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_main);
            this.mListener = mListener;
            ll_main.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_main:
                    if (mListener != null) {
                        mListener.onItemClick(v, getPosition());
                    }
                    break;
            }
        }
    }
}
