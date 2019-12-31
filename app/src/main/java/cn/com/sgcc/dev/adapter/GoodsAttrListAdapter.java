package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;

/**
 * <p>@description:属性listview的适配器</p>
 * @author lxf
 * @version 1.0.0
 * @since 2018/1/2
 */
public class GoodsAttrListAdapter extends BaseAdapter {

    private Context context;
    private List<SaleAttributeNameVo> data;
    private boolean reset = false;

    public GoodsAttrListAdapter(Context context, List<SaleAttributeNameVo> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : (data.size()-3);
        //return data == null ? 0 : 6; //只取选择填入的筛选数据,输入的数据不取
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public void getReset(Boolean b){
            reset = b;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        final MyView myView;
        if (v == null) {
            myView = new MyView();
            v = View.inflate(context, R.layout.item_goods_attr_list, null);
            myView.name = (TextView) v.findViewById(R.id.attr_list_name);
            myView.img = (ImageView) v.findViewById(R.id.attr_list_img);
            myView.grid = (GridView) v.findViewById(R.id.attr_list_grid);
            myView.grid.setSelector(new ColorDrawable(Color.TRANSPARENT));

            v.setTag(myView);
        } else {
            myView = (MyView) v.getTag();
        }
        myView.name.setText(data.get(position).getName());
        final GoodsAttrsAdapter adapter = new GoodsAttrsAdapter(context);
        myView.grid.setAdapter(adapter);
        adapter.notifyDataSetChanged(data.get(position).isNameChecked(), data.get(position).getSaleVo());
        if(data.get(position).getName().equals("排序") && !reset){//默认选择默认排序先无码后有码
            for (SaleAttributeVo nameVo : data.get(position).getSaleVo()) {
                    if(!nameVo.getValue().equals("默认排序先无码后有码")){
                             if(nameVo.isChecked()){
                                 break;
                             }else{
                             }
                        }else {
                            nameVo.setChecked(true);
                        }
                    }
        }
       if (data.get(position).isNameChecked()){ //设置上下箭头变化
           myView.img.setImageResource(R.drawable.sort_common_up);
       }else{
           myView.img.setImageResource(R.drawable.sort_common_down);
       }

       myView.img.setOnClickListener(new OnClickListener() { //上下箭头控制

            @Override
            public void onClick(View v) {
                if (data.get(position).isNameChecked()) {
                    //((ImageView) v).setImageResource(R.drawable.sort_common_up);
                    ((ImageView) v).setImageResource(R.drawable.sort_common_down);
                } else {
                    //((ImageView) v).setImageResource(R.drawable.sort_common_down);
                    ((ImageView) v).setImageResource(R.drawable.sort_common_up);
                }
                data.get(position).setNameChecked(!data.get(position).isNameChecked());
                adapter.notifyDataSetChanged(data.get(position).isNameChecked(), data.get(position).getSaleVo());

            }
        });
        myView.grid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if(data.get(position).getName().equals("排序")||data.get(position).getName().equals("装置校验")) {
                    //这是单选, 设置当前选中的位置的状态为非-----------排序和装置校验为单选其他为多选-------------------------
                    data.get(position).getSaleVo().get(arg2).setChecked(!data.get(position).getSaleVo().get(arg2).isChecked());
                    for (int i = 0; i < data.get(position).getSaleVo().size(); i++) {
                        //跳过已设置的选中的位置的状态
                        if (i == arg2) {
                            continue;
                        }
                        data.get(position).getSaleVo().get(i).setChecked(false);
                    }
//                    if (!data.get(position).isNameChecked()) {
//                        myView.img.setImageResource(R.drawable.sort_common_up);
//                    } else {
//                        myView.img.setImageResource(R.drawable.sort_common_down);
//                    }
                    adapter.notifyDataSetChanged(data.get(position).isNameChecked(), data.get(position).getSaleVo());
                }else {
                    //这是多选, 修改当前选中位置的状态,变为相反状态-----------------------------------------------------------
                    data.get(position).getSaleVo().get(arg2).
                            setChecked(!data.get(position).getSaleVo().get(arg2).isChecked());
                    adapter.notifyDataSetChanged(data.get(position).isNameChecked(),
                            data.get(position).getSaleVo());
                }
            }
        });
        return v;
    }

    static class MyView {
        public TextView name;
        public ImageView img;
        public GridView grid;

    }

}
