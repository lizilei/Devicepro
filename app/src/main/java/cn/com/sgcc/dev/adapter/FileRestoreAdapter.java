package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.io.File;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.recylerUtil.MyItemClickListener;
import cn.com.sgcc.dev.customeView.recylerUtil.SwipeItemLayout;
import cn.com.sgcc.dev.model2.StoreFile;
import cn.com.sgcc.dev.utils.FileUtils;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2019/1/17
 */

public class FileRestoreAdapter extends RecyclerView.Adapter<FileRestoreAdapter.Holder> {

    private List<StoreFile> list;
    private Context context;
    private MyItemClickListener mItemClickListener;


    public FileRestoreAdapter(Context context, List<StoreFile> list) {
        this.context = context;
        this.list = list;
    }

    public void setDatas(List<StoreFile> list) {
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
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_restore, parent, false);
        return new Holder(v, mItemClickListener);
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        StoreFile storeFile = list.get(position);
        holder.tv_name.setText(storeFile.getFileName());
        holder.cb_choose.setChecked(storeFile.isFlag());
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_name;
        private CheckBox cb_choose;
        private RelativeLayout ll_main;
        private MyItemClickListener mListener;

        public Holder(View itemView, MyItemClickListener mListener) {
            super(itemView);
            AutoUtils.autoSize(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name_restore);
            cb_choose = (CheckBox) itemView.findViewById(R.id.cb_choose_restore);
            cb_choose.setClickable(false);

            ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_main_restore);
            this.mListener = mListener;
            ll_main.setOnClickListener(this);
            itemView.findViewById(R.id.delete_restore).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_main_restore:
                    if (mListener != null) {
                        mListener.onItemClick(v, getPosition());
                    }
                    break;
                case R.id.delete_restore:
                    FileUtils.deleteFile(list.get(getAdapterPosition()).getFilePath());
                    list.remove(getAdapterPosition());
                    notifyDataSetChanged();
                    FileUtils.writeObject(list, new File(Constants.APP_STORE));
                    break;
            }
        }
    }
}
