package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.customeView.recylerUtil.MyItemClickListener;
import cn.com.sgcc.dev.model2.Accessory;

/**
 * Created by xmkc2 on 2016/7/20.
 */
public class AcceRecycleAdapter extends RecyclerView.Adapter<AcceRecycleAdapter.AcceViewHolder> {

    private List<Accessory> list;
    private Context context;
    private MyItemClickListener mItemClickListener;

    public AcceRecycleAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<Accessory> list) {
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
        View v = LayoutInflater.from(context).inflate(R.layout.acce_recycle_item, parent, false);
        AcceViewHolder holder = new AcceViewHolder(v, mItemClickListener);
        return holder;
    }


    @Override
    public void onBindViewHolder(AcceViewHolder holder, int position) {
        holder.acce_img.setImageBitmap(list.get(position).getBitmap());
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class AcceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView acce_img;
        private MyItemClickListener mListener;

        public AcceViewHolder(View itemView, MyItemClickListener mListener) {
            super(itemView);
            acce_img = (ImageView) itemView.findViewById(R.id.acce_img);
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
