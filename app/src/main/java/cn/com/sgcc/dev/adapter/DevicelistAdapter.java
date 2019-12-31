package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.com.sgcc.dev.R;

/**
 * <p>@description:</p>
 *   设备列表适配器
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class DevicelistAdapter extends BaseAdapter implements View.OnClickListener {
    private List<String> list;
    private Context context;
    private Callback mCallback;

   //自定义接口，用于回调按钮点击事件到Activity
    public interface Callback {
         void click(View v);
     }

    public DevicelistAdapter(Context context, List<String> list,Callback callback ) {
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

    @Override   //one_name,code,dianya,device_name
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.device_recycler_item, null);
            holder = new ViewHolder(convertView);
            holder.one_name = (TextView) convertView.findViewById(R.id.one_name);
            holder.code = (TextView) convertView.findViewById(R.id.code);
            holder.dianya = (TextView) convertView.findViewById(R.id.dianya);
            holder.device_name = (TextView) convertView.findViewById(R.id.device_name);
            holder.item_edit = (TextView) convertView.findViewById(R.id.item_edit);
            holder.item_delete = (TextView) convertView.findViewById(R.id.item_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.device_name.setText("大坝三期电厂220kV启备变");
        holder.code.setText("44654s645df6a4sd64sa6f5d4a654df65a4f");
        holder.dianya.setText("220KV");
        holder.dianya.setText("1号联络变");

        holder.item_edit.setOnClickListener(this);
        holder.item_edit.setTag(position);

        holder.item_delete.setOnClickListener(this);
        holder.item_delete.setTag(position);

        return convertView;
    }

    static class ViewHolder {
        TextView one_name,code,dianya,device_name,item_edit,item_delete;

        public ViewHolder(View convertView) {

        }
    }

    //响应按钮点击事件,调用子定义接口，并传入View
     @Override
     public void onClick(View v) {
                 mCallback.click(v);
            }
}