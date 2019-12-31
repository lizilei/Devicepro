package cn.com.sgcc.dev.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.Serializable;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.view.activity.JiaoYanActivity;

/**
 * <p>@description:</p>
 *
 * 辅助校验设置
 * @author lxf
 * @version 1.0.0
 */
public class FzJiaoyanFragment extends BaseFragment {
    private String toolsList[];
    private TextView toolsTextViews[];
    private View views[];
    private LayoutInflater inflater;
    private ScrollView scrollView;
    private int scrllViewWidth = 0, scrollViewMiddle = 0;
    private ViewPager shop_pager;
    private int currentItem=0;
    private ShopAdapters shopAdapter;
    private LinearLayout toolsLayout;
    public static FzJiaoyanFragment instance = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        shop_pager=(ViewPager)view.findViewById(R.id.goods_pager);
        toolsLayout=(LinearLayout) view.findViewById(R.id.tools);
        scrollView=(ScrollView) view.findViewById(R.id.tools_scrlllview);

        showToolsView(inflater);  // getActivity().getSupportFragmentManager()
        shopAdapter=new ShopAdapters(getChildFragmentManager());
        shop_pager.setAdapter(shopAdapter);
        shop_pager.setOnPageChangeListener(onPageChangeListener);
        return view;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_jy;
    }

    // 重置方法
    public void reSetJYX2() {
        if (shopAdapter!=null){
            shopAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 动态生成显示items中的textview
     */
    private void showToolsView(LayoutInflater inflater) {
        toolsList=new String[]{"装置基本信息","安装及运维信息","运行基本信息","资产信息","ICD文件信息","载波机附加信息","MU附加信息","智能终端附加信息","交换机附加信息"};
        toolsTextViews=new TextView[toolsList.length];
        views=new View[toolsList.length];
        for (int i = 0; i < toolsList.length; i++) {
            View view=inflater.inflate(R.layout.item_b_top_nav_layout,null);
            view.setId(i);
            view.setOnClickListener(toolsItemListener);
            TextView textView=(TextView) view.findViewById(R.id.texts);
            textView.setText(toolsList[i]);
            toolsLayout.addView(view);
            toolsTextViews[i]=textView;
            views[i]=view;
        }
        changeTextColor(0);
    }

    //左侧选项卡的点击事件
    private View.OnClickListener toolsItemListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            shop_pager.setCurrentItem(v.getId());
        }
    };

    /**
     * 监听ViewPager选项卡变化的事件
     */
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int arg0) {
            if(shop_pager.getCurrentItem()!=arg0){
                shop_pager.setCurrentItem(arg0);
            }
            if(currentItem!=arg0){
                changeTextColor(arg0);
                changeTextLocation(arg0);
            }
            currentItem=arg0;
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    /**
     * ViewPager 加载选项卡
     */
    private class ShopAdapters extends FragmentPagerAdapter {
        public ShopAdapters(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int arg0) {
            Fragment fragment =new Fragment_pro_type();
            Bundle bundle=new Bundle();
            String str=toolsList[arg0];
            bundle.putString("typename",str);
            bundle.putSerializable("jiaoYanData", (Serializable) JiaoYanActivity.instance.jiaoYanData.getFzData());
            fragment.setArguments(bundle);
            return fragment;
        }

        //POSITION_NONE是没有找到child要求重新加载
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return toolsList.length;
        }
    }

    /**
     * 改变textView的颜色
     */
    private void changeTextColor(int id) {
        for (int i = 0; i < toolsTextViews.length; i++) {
            if(i!=id){
                toolsTextViews[i].setBackgroundResource(android.R.color.transparent);
                toolsTextViews[i].setTextColor(0xff000000);
            }
        }
        toolsTextViews[id].setBackgroundResource(android.R.color.white);
        toolsTextViews[id].setTextColor(0xffff5d5e);
    }

    /**
     * 改变栏目位置
     *
     * @param clickPosition
     */
    private void changeTextLocation(int clickPosition) {

        int x = (views[clickPosition].getTop() - getScrollViewMiddle() + (getViewheight(views[clickPosition]) / 2));
        scrollView.smoothScrollTo(0, x);
    }

    /**
     * 返回scrollview的中间位置
     *
     * @return
     */
    private int getScrollViewMiddle() {
        if (scrollViewMiddle == 0)
            scrollViewMiddle = getScrollViewheight() / 2;
        return scrollViewMiddle;
    }

    /**
     * 返回ScrollView的宽度
     *
     * @return
     */
    private int getScrollViewheight() {
        if (scrllViewWidth == 0)
            scrllViewWidth = scrollView.getBottom() - scrollView.getTop();
        return scrllViewWidth;
    }

    /**
     * 返回view的宽度
     *
     * @param view
     * @return
     */
    private int getViewheight(View view) {
        return view.getBottom() - view.getTop();
    }

    @Override
    public void initview() {

    }

    @Override
    public void initEvevt() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initReceiver(boolean isEdit) {

    }
}
