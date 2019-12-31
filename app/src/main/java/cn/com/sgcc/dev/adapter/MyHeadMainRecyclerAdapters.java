package cn.com.sgcc.dev.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.io.Serializable;
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
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/30
 */

public class MyHeadMainRecyclerAdapters extends RecyclerView.Adapter<MyHeadMainRecyclerAdapters.Holder> {

    private List<BHPZ> bhpzList;
    private List<FZBHSB> fzbhsbList;
    private int tag = 0;
    private Context context;
    private MyItemClickListener mItemClickListener;
    private IDaoUtil util;

    private View VIEW_FOOTER;
    private int TYPE_NORMAL = 1000;
    private int TYPE_FOOTER = 1001;

    public MyHeadMainRecyclerAdapters(Context context) {
        this.util = new DaoUtil(context);
        this.context = context;
    }

    /**
     * @param list
     */
    public void setDatas(List<BHPZ> list) {
        this.bhpzList = list;
        this.tag = 0;
        notifyDataSetChanged();
    }

    /**
     * @param tag  0-保护   1  辅助
     * @param list
     */
    public void setDatas(int tag, List<FZBHSB> list) {
        this.fzbhsbList = list;
        this.tag = tag;
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
        View v = LayoutInflater.from(context).inflate(R.layout.station_recycler_itemsss, parent, false);
        if (viewType == TYPE_FOOTER) {
            return new Holder(viewType, VIEW_FOOTER, mItemClickListener);
        } else {
            return new Holder(viewType, v, mItemClickListener);
        }
    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            return;
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            notifyItemInserted(getItemCount() - 1);
        }
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

//    辅助保护类别: 1操作箱 2操作箱/电压切换箱  3电压切换箱 4光电转换装置  5光纤通信接口装置  6合并单元
//                7合并单元智能终端集成  8交换机  9其他  10收发信机  11智能终端
//    保护类别: 12安全自动装置  13保护故障信息系统子站 14变压器保护 15电动机保护 16电抗器保护 17电容器保护
//             18短引线保护 19发变组保护 20发电机保护  21故障测距装置 22故障录波器 23过电压及远方跳闸保护
//             24母线保护 25其他保护 26线路保护 27断路器保护 28站域保护
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (!isFooterView(position)) {
            if (tag == 0) { //保护
                BHPZ bhpz = bhpzList.get(position);
                holder.tv_name.setText(bhpz.getBhmc());
                holder.tv_sbsbdm.setText(bhpz.getSw_id());
//                if (bhpz.getBhlb().equals("安全自动装置")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b12);
//                }else if(bhpz.getBhlb().equals("保护故障信息系统子站")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b13);
//                }else if(bhpz.getBhlb().equals("变压器保护")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b14);
//                }else if(bhpz.getBhlb().equals("电动机保护")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b15);
//                }else if(bhpz.getBhlb().equals("电抗器保护")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b16);
//                }else if(bhpz.getBhlb().equals("电容器保护")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b17);
//                }else if(bhpz.getBhlb().equals("短引线保护")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b18);
//                }else if(bhpz.getBhlb().equals("发变组保护")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b19);
//                }else if(bhpz.getBhlb().equals("发电机保护")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b20);
//                }else if(bhpz.getBhlb().equals("故障测距装置")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b21);
//                }else if(bhpz.getBhlb().equals("故障录波器")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b22);
//                }else if(bhpz.getBhlb().equals("过电压及远方跳闸保护")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b23);
//                }else if(bhpz.getBhlb().equals("母线保护")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b24);
//                }else if(bhpz.getBhlb().equals("其他保护")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b25);
//                }else if(bhpz.getBhlb().equals("线路保护")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b26);
//                }else if(bhpz.getBhlb().equals("断路器保护")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b27);
//                }else if(bhpz.getBhlb().equals("站域保护")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b28);
//                }

//                holder.tvName.setText("装置名称：");
//                holder.tvCompany.setText("电压等级：");
//                holder.tv_company.setText(bhpz.getDydj()+"kV");
//                holder.tv_maxVol.setText("设备识别码：");
//                holder.tv_max_vol.setText(bhpz.getSfsbm());
//                holder.tvType.setText("一次设备名称：");
//                holder.tv_type.setText(bhpz.getYcsbmc());
            } else {
                FZBHSB fzbhsb = fzbhsbList.get(position);
                holder.tv_name.setText(fzbhsb.getSbmc());
                holder.tv_sbsbdm.setText(fzbhsb.getSw_id());
//                if (fzbhsb.getFzsblx().equals("操作箱")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b1);
//                }else if(fzbhsb.getFzsblx().equals("操作箱/电压切换箱")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b2);
//                }else if(fzbhsb.getFzsblx().equals("电压切换箱")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b3);
//                }else if(fzbhsb.getFzsblx().equals("光电转换装置")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b4);
//                }else if(fzbhsb.getFzsblx().equals("光纤通信接口装置")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b5);
//                }else if(fzbhsb.getFzsblx().equals("合并单元")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b6);
//                }else if(fzbhsb.getFzsblx().equals("合并单元智能终端集成")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b7);
//                }else if(fzbhsb.getFzsblx().equals("交换机")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b8);
//                }else if(fzbhsb.getFzsblx().equals("其他")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b9);
//                }else if(fzbhsb.getFzsblx().equals("收发信机")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b10);
//                }else if(fzbhsb.getFzsblx().equals("智能终端")){
//                    holder.device_photo.setBackgroundResource(R.drawable.b11);
//                }

//                holder.tvName.setText("装置名称：");
//                holder.tvCompany.setText("电压等级：");
//                holder.tv_company.setText(fzbhsb.getDydj()+"kV");
//                holder.tv_maxVol.setText("设备识别码：");
//                holder.tv_max_vol.setText(fzbhsb.getSfsbm());
//                holder.tvType.setText("一次设备名称：");
            }
        }
    }

    @Override
    public int getItemCount() {
        if (tag == 0) {
            int count = bhpzList != null ? bhpzList.size() : 0;
            if (VIEW_FOOTER != null) {
                count++;
            }
            return count;
        } else {
            int count = fzbhsbList != null ? fzbhsbList.size() : 0;
            if (VIEW_FOOTER != null) {
                count++;
            }
            return count;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_name,tv_sbsbdm, tv_type, tv_max_vol, tv_company;
        private TextView tvName, tvType, tv_maxVol, tvCompany;
        private ImageView device_photo;
        private LinearLayout ll_main;
        private MyItemClickListener mListener;

        public Holder(int viewType, View itemView, MyItemClickListener mListener) {
            super(itemView);
            AutoUtils.autoSize(itemView);

            if (viewType == TYPE_FOOTER) {

            } else {
                tv_name = (TextView) itemView.findViewById(R.id.tv_name);
                tv_sbsbdm = (TextView) itemView.findViewById(R.id.tv_sbsbdm);
                ll_main = (LinearLayout) itemView.findViewById(R.id.ll_main);
                device_photo= (ImageView) itemView.findViewById(R.id.device_photo);

//                tv_type = (TextView) itemView.findViewById(R.id.tv_type);
//                tv_max_vol = (TextView) itemView.findViewById(R.id.tv_max_vol);
//                tv_company = (TextView) itemView.findViewById(R.id.tv_company);
//                tvName = (TextView) itemView.findViewById(R.id.tvName);
//                tvType = (TextView) itemView.findViewById(R.id.tvType);
//                tv_maxVol = (TextView) itemView.findViewById(R.id.tv_maxVol);
//                tvCompany = (TextView) itemView.findViewById(R.id.tvCompany);
                this.mListener = mListener;
                ll_main.setOnClickListener(this);
//                itemView.findViewById(R.id.delete).setOnClickListener(this);
//                itemView.findViewById(R.id.mark).setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_main:
                    if (mListener != null) {
                        mListener.onItemClick(v, getPosition());
                    }
                    break;
                case R.id.mark:
                    Intent intent = new Intent(context, DeviceDetailsActivity.class);
                    if (tag == 0) {//保护设备
                        intent.putExtra("ZZTYPE", "BHSB");
                        intent.putExtra("BHSB", bhpzList.get(getPosition()));
                    } else {//辅助设备
                        intent.putExtra("ZZTYPE", "FZSB");
                        intent.putExtra("FZSB", fzbhsbList.get(getPosition()));
                    }
                    intent.putExtra("STATE", "M");
                    context.startActivity(intent);
                    break;
                case R.id.delete:
                    if (tag == 0) {
                        BHPZ bhpz = bhpzList.get(getPosition());
                        if (bhpz.getEd_tag() != null && bhpz.getEd_tag().equals("C")) {
                            util.coreBHPZ("D", bhpz);

                            context.sendBroadcast(new Intent("cn.sgg.fzbhsb"));
                        } else {
                            bhpz.setEd_tag("D");
                            bhpz.setSb("D");
                            util.coreSavaRZXX(bhpz);
                            util.coreBHPZ("M", bhpz);
                            context.sendBroadcast(new Intent("cn.sgg.fzbhsb"));
                            ToastUtils.showToast((Activity) context, "删除成功");
                        }
                    } else {
                        FZBHSB fzbhsb = fzbhsbList.get(getPosition());
                        if (fzbhsb.getEd_tag() != null && fzbhsb.getEd_tag().equals("C")) {
                            util.coreFZHBSB("D", fzbhsb);
                            context.sendBroadcast(new Intent("cn.sgg.fzbhsb"));
                        } else {
                            fzbhsb.setEd_tag("D");
                            fzbhsb.setSb("D");
                            util.coreSavaRZXX(fzbhsb);
                            util.coreFZHBSB("M", fzbhsb);
                            context.sendBroadcast(new Intent("cn.sgg.fzbhsb"));
                            ToastUtils.showToast((Activity) context, "删除成功");
                        }
                    }
                    break;
            }
        }
    }
}
