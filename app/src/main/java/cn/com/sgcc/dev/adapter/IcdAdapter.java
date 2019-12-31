package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model2.ICDXX;
import cn.com.sgcc.dev.utils.TimeUtil;

import static cn.com.sgcc.dev.utils.TimeUtil.dateToStr;

/**
 * <p>@description:</p>
 *   ICD适配器
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class IcdAdapter extends BaseAdapter {
    private List<ICDXX> list;
    private Context context;

    public IcdAdapter(Context context, List<ICDXX> list) {
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
        if (convertView == null) { //wjmc,wjbb,md,crc,jymscsj
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_lv_icd_item, null);
            holder = new ViewHolder(convertView);
            holder.wjmc = (TextView) convertView.findViewById(R.id.wjmc);
            holder.wjbb = (TextView) convertView.findViewById(R.id.wjbb);
            holder.md = (TextView) convertView.findViewById(R.id.md);
            holder.crc = (TextView) convertView.findViewById(R.id.crc);
            holder.jymscsj = (TextView) convertView.findViewById(R.id.jymscsj);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //赋值**************************************
        if (list.get(position).getWJMC()!=null){
            if(list.get(position).getWJMC().equals("null")){
                holder.wjmc.setText("");
            }else{
                holder.wjmc.setText(list.get(position).getWJMC()+"");
            }
        }else{
            holder.wjmc.setText("");
        }

        if (list.get(position).getWJBB()!=null){
            if(list.get(position).getWJBB().equals("null")){
                holder.wjbb.setText("");
            }else{
                holder.wjbb.setText(list.get(position).getWJBB()+"");
            }
        }else{
            holder.wjbb.setText("");
        }

        if (list.get(position).getMD5()!=null){
            if (list.get(position).getMD5().equals("null")){
                holder.md.setText("");
            }else{
                holder.md.setText(list.get(position).getMD5()+"");
            }
        }else{
            holder.md.setText("");
        }

        if (list.get(position).getCRC32()!=null){
            if (list.get(position).getCRC32().equals("null")){
                holder.crc.setText("");
            }else{
                holder.crc.setText(list.get(position).getCRC32()+"");
            }
        }else{
            holder.crc.setText("");
        }

//        if (TimeUtil.formatString2(list.get(position).getJYMSCSJ())==null){
//            holder.jymscsj.setText("");
//        }else{
//            holder.jymscsj.setText(TimeUtil.formatString2(list.get(position).getJYMSCSJ())+"");
//        }
        if (TimeUtil.dateIsEmpty(list.get(position).getJYMSCSJ())){ //日期
            holder.jymscsj.setText("");
        }else{
            if (TimeUtil.formatString2(list.get(position).getJYMSCSJ())==null){
                holder.jymscsj.setText("");
            }else{
                holder.jymscsj.setText(TimeUtil.formatString2(list.get(position).getJYMSCSJ())+"");
            }
        }
        return convertView;
    }

    static class ViewHolder {
        TextView wjmc,wjbb,md,crc,jymscsj;

        public ViewHolder(View convertView) {

        }
    }
}