package cn.com.sgcc.dev.adapter;//package cn.com.sgcc.dev.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.BindViews;
//import butterknife.ButterKnife;
//import cn.com.sgcc.dev.R;
//import cn.com.sgcc.dev.model2.BHXHRJBB;
//import cn.com.sgcc.dev.model2.ICDXX;
//import cn.com.sgcc.dev.utils.AppUtils;
//import cn.com.sgcc.dev.view.viewinterface.DateChooseListener;
//
///**
// * <p>@description:装置基本信息ICD</p>
// *
// * @author lizilei
// * @version 1.0
// * @Email lizilei_warms@163.com
// * @since 2018/1/9
// */
//
//public class DeviceICDAdapter extends BaseAdapter {
//    private List<ICDXX> list;
//    private Context context;
//    private boolean isEdit;
//
//    public DeviceICDAdapter(Context context) {
//        this.context = context;
//    }
//
//    public void setDatas(List<ICDXX> list, boolean isEdit) {
//        this.list = list;
//        this.isEdit = isEdit;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getCount() {
//        return list == null ? 0 : list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        final ViewHolder holder;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_icd, null);
//            holder = new ViewHolder(convertView);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        ICDXX item = list.get(position);
//        if (isEdit) {
//            for (TextView textView : holder.textViews) {
//                textView.setBackgroundResource(R.drawable.device_detials_bg);
//                textView.setEnabled(true);
//            }
//        } else {
//            for (TextView textView : holder.textViews) {
//                textView.setBackground(null);
//                textView.setEnabled(false);
//            }
//        }
//        holder.textViews.get(0).setText(item.getWJMC() + "");
//        holder.textViews.get(1).setText(item.getWJBB() + "");
//        holder.textViews.get(2).setText(item.getCRC32() + "");
//        holder.textViews.get(3).setText(item.getMD5() + "");
//        holder.textViews.get(4).setText(item.getJYMSCSJ() + "");
//        holder.textViews.get(5).setText("");
//        holder.textViews.get(6).setText("");
//        holder.textViews.get(7).setText("");
//
//        holder.textViews.get(4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppUtils.showDateDialog(context, new DateChooseListener() {
//                    @Override
//                    public void onDateChooseListener(String date) {
//                        holder.textViews.get(4).setText(date);
//                    }
//                });
//            }
//        });
//        holder.textViews.get(5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppUtils.showDateDialog(context, new DateChooseListener() {
//                    @Override
//                    public void onDateChooseListener(String date) {
//                        holder.textViews.get(5).setText(date);
//                    }
//                });
//            }
//        });
//        holder.textViews.get(6).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppUtils.showDateDialog(context, new DateChooseListener() {
//                    @Override
//                    public void onDateChooseListener(String date) {
//                        holder.textViews.get(6).setText(date);
//                    }
//                });
//            }
//        });
//        return convertView;
//    }
//
//    class ViewHolder {
//        @BindViews(value = {R.id.tv_wjmc, R.id.tv_wjbb, R.id.tv_crc32, R.id.tv_md5, R.id.tv_wjscsj
//                , R.id.tv_wjzzxgsj, R.id.tv_jcsj, R.id.tv_jcpc})
//        List<TextView> textViews;
//
//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }
//}
