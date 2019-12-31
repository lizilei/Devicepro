package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;


/**
 * <p>@description:子属性GridView的适配器</p>
 *
 * @author lxf
 * @version 1.0.0
 * @since 2018/1/2
 */
public class GoodsAttrsAdapter extends BaseAdapter {

    private Context context;
    private List<SaleAttributeVo> data = new ArrayList<SaleAttributeVo>();

    public GoodsAttrsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        final MyView myView;
        if (v == null) {
            myView = new MyView();
            v = View.inflate(context, R.layout.item_goods_attrs, null);
            myView.attr = (TextView) v.findViewById(R.id.attr_name);
            v.setTag(myView);
            AutoUtils.autoSize(v);
        } else {
            myView = (MyView) v.getTag();
        }
        myView.attr.setText(data.get(position).getValue());
        /**
         * 根据选中状态来设置item的背景和字体颜色
         */
        if (data.get(position).isChecked()) {
            myView.attr.setBackgroundResource(R.drawable.goods_attr_selected_shape);
            myView.attr.setTextColor(Color.WHITE);
        } else {
            myView.attr.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
            myView.attr.setTextColor(Color.GRAY);
        }
        return v;
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void notifyDataSetChanged(boolean isUnfold,//是否是展开的,默认是不展开的
                                     final List<SaleAttributeVo> tempData) {
        if (tempData == null || 0 == tempData.size()) {
            return;
        }
        data.clear();
        // 如果是展开的，则加入全部data，反之则只显示3条
        if (isUnfold) {
            data.addAll(tempData);
        } else {
            if (tempData.size()==2){
                data.add(tempData.get(0));
                data.add(tempData.get(1));
            }else{
                data.add(tempData.get(0));
                data.add(tempData.get(1));
                data.add(tempData.get(2));
            }
        }
        notifyDataSetChanged();
    }

    static class MyView {
        public TextView attr;
    }

}
