package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model2.ycsb.CZCS;

/**
 * <p>Title: MainCountresLvAdapter</p>
 * <p>Description: 城市选择适配器</p>
 * <p>Company: </p>
 *
 * @author wxx
 * @date 2016/6/7
 */
public class CityCheckLvAdapter extends BaseAdapter {

    private Context context;

    private List<CZCS> data;

    private String first, oldFirst = "";

    public CityCheckLvAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CZCS> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        first = data.get(position).getFirst();

//        Log.d("week",first.equals(oldFirst)+">>>"+first+">>>>"+oldFirst);

        if (first.equals(oldFirst)) {
            return 0;
        } else {
            oldFirst = first;
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        getItemViewType(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.item_main_countres_lv, null);

            viewHolder.firstLetter = (TextView) convertView.findViewById(R.id.main_countres_ls_first);

            viewHolder.countryName = (TextView) convertView.findViewById(R.id.main_countres_ls_country);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CZCS mainCountres = data.get(position);
        String header = mainCountres.getFirst();
        if (!TextUtils.isEmpty(header)) {
            if (position == 0) {
                viewHolder.firstLetter.setVisibility(View.VISIBLE);
                viewHolder.firstLetter.setText(header);
            } else if (!header.equals(((CZCS) getItem(position - 1)).getFirst())) {
                viewHolder.firstLetter.setVisibility(View.VISIBLE);
                viewHolder.firstLetter.setText(header);
            } else {
                viewHolder.firstLetter.setVisibility(View.GONE);
            }
        } else {
            viewHolder.firstLetter.setVisibility(View.GONE);
        }
        viewHolder.countryName.setText(mainCountres.getCzmc());

        return convertView;
    }

    class ViewHolder {

        TextView firstLetter;

        TextView countryName;

    }
}