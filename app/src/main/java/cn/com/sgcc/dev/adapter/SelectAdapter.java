package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model2.CommonFilter;
import cn.com.sgcc.dev.utils.TextPinyinUtil;

/**
 * <p>@description:</p>
 * 设备详情选择，新适配器
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2018/1/10
 */
public class SelectAdapter extends BaseAdapter {
    private List<String> list;
    private List<CommonFilter> filterList;
    List<String> characterList = new ArrayList<>();
    List<Integer> characterPositionList = new ArrayList<>();
    String type;

    private Context context;
    private Boolean isMore;

    public SelectAdapter(Context context, List<String> list, boolean isMore) {
        this.list = list;
        this.context = context;
        this.isMore = isMore;
    }

    public SelectAdapter(Context context, List<CommonFilter> filterList, boolean isMore, String s) {
        this.filterList = filterList;
        this.context = context;
        this.isMore = isMore;
    }

    public void setData(List<String> list,List<String> characterList,List<Integer> characterPositionList,String type) {
        this.list = list;
        this.characterList=characterList;
        this.characterPositionList=characterPositionList;
        this.type=type;
        notifyDataSetChanged();
    }

    public void setDatas(List<CommonFilter> filterList) {
        this.filterList = filterList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (isMore) {
            return filterList == null ? 0 : filterList.size();
        } else {
            return list == null ? 0 : list.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (isMore) {
            return filterList.get(position);
        } else {
            return list.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_device_item_select, null);
            holder = new ViewHolder(convertView);
            holder.fragment_one_device_item_select_tv = (TextView) convertView.findViewById(R.id.fragment_one_device_item_select_tv);
            holder.iv_end_select = (ImageView) convertView.findViewById(R.id.iv_end_select);
            holder.zimu = (TextView) convertView.findViewById(R.id.zimu);
            convertView.setTag(holder);

            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //判断是否显示字母
        if(list!=null&&list.size()>0){
            if (!type.equals("制造厂家") &&! type.equals("装置型号")&& !type.equals("一次设备名称")
                    && !type.equals("单位名称") && !type.equals("调度单位") && !type.equals("设计单位")
                    && !type.equals("基建单位") && !type.equals("运行单位") && !type.equals("维护单位")) {
                holder.zimu.setVisibility(View.GONE);
            }else{ //显示字母
                if (position==0){
                    String pinyin = TextPinyinUtil.getInstance().getPinyin(list.get(position));
                    if ("".equals(pinyin)){
                        holder.zimu.setVisibility(View.VISIBLE);
                        holder.zimu.setText("#");
                    }else{
                        String character = (pinyin.charAt(0) + "").toUpperCase(Locale.ENGLISH);
                        if (character.hashCode() >= "A".hashCode() && character.hashCode() <= "Z".hashCode()) { // 是字母
                            if (character.equals("U")){
                                holder.zimu.setVisibility(View.VISIBLE);
                                holder.zimu.setText("#");
                            }else{
                                holder.zimu.setVisibility(View.VISIBLE);
                                holder.zimu.setText(character);
                            }
                        } else { //不是字母
                            holder.zimu.setVisibility(View.VISIBLE);
                            holder.zimu.setText("#");
                        }
                    }
                }else {
                    //当前的首字母和上一个对比
                    String pinyin = TextPinyinUtil.getInstance().getPinyin(list.get(position));
                    String character ;
                    if ("".equals(pinyin)){
                        character="#";
                    }else{
                        character = (pinyin.charAt(0) + "").toUpperCase(Locale.ENGLISH);
                    }

                    String pinyin2 = TextPinyinUtil.getInstance().getPinyin(list.get(position - 1));
                    String character2;
                    if ("".equals(pinyin2)){
                        character2="#";
                    }else{
                        character2 = (pinyin2.charAt(0) + "").toUpperCase(Locale.ENGLISH); //上一个
                    }
                    if (character.equals(character2)) {
                        holder.zimu.setVisibility(View.GONE);
                    } else {
                        if (character.equals("±") || character.equals("+") || character.equals("-")) {
                            holder.zimu.setVisibility(View.GONE);
                        } else {
                            if (character.hashCode() >= "A".hashCode() && character.hashCode() <= "Z".hashCode()) { // 是字母
                                holder.zimu.setVisibility(View.VISIBLE);
                                holder.zimu.setText(character);
                            } else { //character不是字母
                                holder.zimu.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            }

            if (isMore) {
                CommonFilter filter = filterList.get(position);
                holder.fragment_one_device_item_select_tv.setText("" + filter.getTitle());
                if (filter.isSelect()) {
                    holder.iv_end_select.setVisibility(View.VISIBLE);
                } else {
                    holder.iv_end_select.setVisibility(View.GONE);
                }
            } else {
                holder.fragment_one_device_item_select_tv.setText("" + list.get(position));
                holder.iv_end_select.setVisibility(View.GONE);
            }

        }

        return convertView;
    }

    //点击最右边的字母栏时可以进行滑动
    public int getScrollPosition(String character) {
        if (characterList.contains(character)) {
            //根据characterList元素的位置获取characterPositionList中对应的元素,即首个字母出现的位置
            return characterPositionList.get(characterList.indexOf(character));
        }
        return -1; // -1不会滑动
    }

    static class ViewHolder {

        TextView fragment_one_device_item_select_tv,zimu;
        //多选按钮
        ImageView iv_end_select;

        public ViewHolder(View convertView) {

        }
    }
}