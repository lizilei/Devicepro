package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.customeView.recylerUtil.MyItemClickListener;
import cn.com.sgcc.dev.model2.vo.DataHolder;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.utils.PingYinUtil;
import cn.com.sgcc.dev.utils.PinyinComparator;
import cn.com.sgcc.dev.utils.TextPinyinUtil;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/30
 */

public class MyStationRecyclerAdapters extends RecyclerView.Adapter<MyStationRecyclerAdapters.Holder> {

    private List<CZCS> list;
    private Context context;
    private MyItemClickListener mItemClickListener;
    private View VIEW_FOOTER;

    private int TYPE_NORMAL = 1000;
    private int TYPE_FOOTER = 1001;

    private List<String>  characterList;
    private List<Integer>  characterPositionList;


    public MyStationRecyclerAdapters(Context context) {
        this.context = context;
        characterList= new ArrayList<>();//字母list
        characterPositionList=new ArrayList<>();
    }

    public  void setDatas(List<CZCS> list) {
        this.list = list;
        characterList=DataHolder.getInstance().getZiMu();
        characterPositionList=DataHolder.getInstance().getPosition();
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
        View v = LayoutInflater.from(context).inflate(R.layout.station_recycler_itemss, parent, false);
        if (viewType == TYPE_FOOTER) {
            return new Holder(viewType, VIEW_FOOTER, mItemClickListener);
        } else {
            return new Holder(viewType, v, mItemClickListener);
        }
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (!isFooterView(position)) {
            if (position==0){
                String pinyin = TextPinyinUtil.getInstance().getPinyin(list.get(position).getCzmc());
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
            }else {
                //当前的首字母和上一个对比
                String pinyin = TextPinyinUtil.getInstance().getPinyin(list.get(position).getCzmc());
                String character = (pinyin.charAt(0) + "").toUpperCase(Locale.ENGLISH);
                String pinyin2 = TextPinyinUtil.getInstance().getPinyin(list.get(position - 1).getCzmc());
                String character2 = (pinyin2.charAt(0) + "").toUpperCase(Locale.ENGLISH); //上一个

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
//                            if (character2.hashCode() >= "A".hashCode() && character2.hashCode() <= "Z".hashCode()) {
//                                holder.zimu.setVisibility(View.VISIBLE);
//                                holder.zimu.setText("#");
//                            } else {
//                                holder.zimu.setVisibility(View.GONE);
//                            }
                        }
                    }
                }
            }

            holder.tv_name.setText(list.get(position).getCzmc());
            holder.tv_max_vol.setText(list.get(position).getCzzgdydj() + "kV");

//            holder.tvName.setText("厂站名称：");
//            holder.tvCompany.setText("管理单位：");
//            holder.tv_company.setText(list.get(position).getGLDW());
//            holder.tv_maxVol.setText("厂站最高电压等级：");
//            holder.tvType.setText("厂站类型：");
//            holder.tv_type.setText(list.get(position).getBDZLX());
        }
    }

    //点击最右边的字母栏时可以进行滑动
    public int getScrollPosition(String character) {
        if (characterList.contains(character)) {
            //根据characterList元素的位置获取characterPositionList中对应的元素,即首个字母出现的位置
            return characterPositionList.get(characterList.indexOf(character));
        }
        return -1; // -1不会滑动
    }

    @Override
    public int getItemCount() {
        int count = list != null ? list.size() : 0;
        if (VIEW_FOOTER != null) {
            count++;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
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

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_name, tv_type, tv_max_vol, tv_company;
        private TextView tvName, tvType, tv_maxVol, tvCompany;
        private RelativeLayout ll_main;
        private MyItemClickListener mListener;
        private TextView zimu;

        public Holder(int viewType, View itemView, MyItemClickListener mListener) {
            super(itemView);

            AutoUtils.autoSize(itemView);
            if (viewType == TYPE_FOOTER) {

            } else {
                tv_name = (TextView) itemView.findViewById(R.id.tv_name);
                tv_max_vol = (TextView) itemView.findViewById(R.id.tv_max_vol);
                zimu = (TextView) itemView.findViewById(R.id.zimu);

//                tv_type = (TextView) itemView.findViewById(R.id.tv_type);
//                tv_company = (TextView) itemView.findViewById(R.id.tv_company);
//                tvName = (TextView) itemView.findViewById(R.id.tvName);
//                tvType = (TextView) itemView.findViewById(R.id.tvType);
//                tv_maxVol = (TextView) itemView.findViewById(R.id.tv_maxVol);
//                tvCompany = (TextView) itemView.findViewById(R.id.tvCompany);

                ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_main);
                this.mListener = mListener;
                ll_main.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }
        }
    }
}
