package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model2.BKXX;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.view.fragment.DeviceOneFragment;

import static cn.com.sgcc.dev.utils.TimeUtil.dateToStr;

/**
 * <p>@description:</p>
 * 板卡适配器
 *
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class BkAdapter extends BaseAdapter {
    private List<BKXX> list;
    private Context context;

    public BkAdapter(Context context, List<BKXX> list) {
        this.list = list;
        this.context = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {  //xinghao,leibie,bianhao,banben,date,tuiyun;
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_lv_bk_item, null);
            holder = new ViewHolder(convertView);
            holder.xinghao = (TextView) convertView.findViewById(R.id.xinghao);
            holder.leibie = (TextView) convertView.findViewById(R.id.leibie);
            holder.bianhao = (TextView) convertView.findViewById(R.id.bianhao);
            holder.banben = (TextView) convertView.findViewById(R.id.banben);
            holder.date = (TextView) convertView.findViewById(R.id.date);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (list.get(position).getBkxh() != null) { //型号
            if (list.get(position).getBkxh().equals("null")) {
                holder.xinghao.setText("");
            } else {
                holder.xinghao.setText(list.get(position).getBkxh() + "");
            }
        } else {
            holder.xinghao.setText("");
        }

        if (list.get(position).getBklb() != null) { //类别
            if (list.get(position).getBklb().equals("null")) {
                holder.leibie.setText("");
            } else {
                holder.leibie.setText(list.get(position).getBklb() + "");
            }
        } else {
            holder.leibie.setText("");
        }

        if (list.get(position).getBkbh() != null) { //编号
            if (list.get(position).getBkbh().equals("null")) {
                holder.bianhao.setText("");
            } else {
                holder.bianhao.setText(list.get(position).getBkbh() + "");
            }
        } else {
            holder.bianhao.setText("");
        }

        if (list.get(position).getRjbb() != null) { //版本
            if (list.get(position).getRjbb().equals("null")) {
                holder.banben.setText("");
            } else {
                holder.banben.setText(list.get(position).getRjbb() + "");
            }
        } else {
            holder.banben.setText("");
        }

        if (TimeUtil.dateIsEmpty(list.get(position).getBkscrq())) { //日期
            holder.date.setText("");
        } else {
            if (TimeUtil.formatString2(list.get(position).getBkscrq()) == null) {
                holder.date.setText("");
            } else {
                holder.date.setText(TimeUtil.formatString2(list.get(position).getBkscrq()) + "");
            }

        }

        return convertView;
    }

    public void setDatas(List<BKXX> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView xinghao, leibie, bianhao, banben, date;

        public ViewHolder(View convertView) {

        }
    }
}