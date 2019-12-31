package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.File;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.customeView.recylerUtil.MyItemClickListener;
import cn.com.sgcc.dev.model2.Accessory;
import cn.com.sgcc.dev.model2.WDGL;

/**
 * Created by xmkc2 on 2016/7/20.
 */
public class AcceRecycleAdapter2 extends RecyclerView.Adapter<AcceRecycleAdapter2.AcceViewHolder> {

    private List<WDGL> list;
    private Context context;
    private MyItemClickListener mItemClickListener;
    private int type;

    public AcceRecycleAdapter2(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    public void setDatas(List<WDGL> list) {
        this.list = list;
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
    public AcceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.acce_recycle_item1, parent, false);
        AcceViewHolder holder = new AcceViewHolder(v, mItemClickListener);
        return holder;
    }


    @Override
    public void onBindViewHolder(AcceViewHolder holder, int position) {
        if (type == 0 || type == 2) {
            holder.acce_tv.setVisibility(View.GONE);
            holder.acce_img.setImageBitmap(list.get(position).getBitmap());
        } else if (type == 1 || type == 3) {
            holder.acce_tv.setVisibility(View.VISIBLE);
            holder.acce_img.setImageResource(R.drawable.ic_launcher_round);
            holder.acce_tv.setText(list.get(position).getName());
        }
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class AcceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView acce_img;
        private TextView acce_tv;
        private MyItemClickListener mListener;

        public AcceViewHolder(View itemView, MyItemClickListener mListener) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            acce_img = (ImageView) itemView.findViewById(R.id.acce_img);
            acce_tv = (TextView) itemView.findViewById(R.id.acce_tv);
            this.mListener = mListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }
        }
    }
}
