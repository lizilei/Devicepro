package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.customeView.MyTextView;
import cn.com.sgcc.dev.model2.LJQXX;
import cn.com.sgcc.dev.utils.TimeUtil;

import static cn.com.sgcc.dev.utils.TimeUtil.dateToStr;

/**
 * <p>@description:</p>
 *   连接器适配器
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class LjqAdapter extends BaseAdapter implements View.OnClickListener{
    private List<LJQXX> list;
    private Context context;
    private Callback mCallback;

    //自定义接口，用于回调按钮点击事件到Activity
    public interface Callback {
        void click(View v);
    }

    public LjqAdapter(Context context, List<LJQXX> list,Callback callback ) {
        this.list = list;
        this.context = context;
        mCallback = callback;
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
        if (convertView == null) { //erweima,bianhao ,xinghao ,changjia , qianfeng, leixing,yongtu
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_lv_ljq_item, null);
            holder = new ViewHolder(convertView);
            holder.erweima = (TextView) convertView.findViewById(R.id.erweima);
            holder.bianhao = (TextView) convertView.findViewById(R.id.bianhao);
            holder.xinghao = (TextView) convertView.findViewById(R.id.xinghao);
            holder.changjia = (TextView) convertView.findViewById(R.id.changjia);
            holder.qianfeng = (TextView) convertView.findViewById(R.id.qianfeng);
            holder.leixing = (TextView) convertView.findViewById(R.id.leixing);
            holder.yongtu = (TextView) convertView.findViewById(R.id.yongtu);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.erweima.setText(list.get(position).getEwmxx()+"");
        holder.bianhao.setText(list.get(position).getCtzjbh()+"");
        holder.xinghao.setText(list.get(position).getCtzjxh()+"");
        //holder.xinghao.setText("GDW-01-00-8160-1(L=3000,16*1.5)-02200");
        holder.changjia.setText(list.get(position).getCtzjzzcj()+"");
        holder.leixing.setText(list.get(position).getJklx()+"");

        holder.yongtu.setText(list.get(position).getJkyt()+"");

        if (TimeUtil.dateIsEmpty(list.get(position).getJtzjqfrq())){
            holder.qianfeng.setText("");
        }else{
            if (TimeUtil.formatString2(list.get(position).getJtzjqfrq())==null){
                holder.qianfeng.setText("");
            }else{
                holder.qianfeng.setText(TimeUtil.formatString2(list.get(position).getJtzjqfrq())+"");
            }
        }
        return convertView;
    }

    static class ViewHolder {
        TextView erweima,bianhao,changjia ,xinghao, leixing,qianfeng,yongtu;
        public ViewHolder(View convertView) {

        }
    }

    //响应按钮点击事件,调用子定义接口，并传入View
    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }
}