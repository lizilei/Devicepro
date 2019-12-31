package cn.com.sgcc.dev.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.MyHeadMainRecyclerAdapter;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.LabelLayout;
import cn.com.sgcc.dev.customeView.recylerUtil.MyItemClickListener;
import cn.com.sgcc.dev.customeView.recylerUtil.SwipeItemLayout;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.BZSJ;
import cn.com.sgcc.dev.model2.CommonFilter;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.SearchEntity;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.EditTextShakeHelper;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

/**
 * <p>@description:首页设备搜索</p>
 * @author lxf
 * @version 1.0.0
 * @since 2018/1/2
 */

public class DeviceListFragments extends BaseFragment implements View.OnClickListener {

    private EditText app_search_edit;
    private TextView tv_search_type;
    private TextView tv_px, tv_bhlb, tv_clzt, tv_sx, tv_deep;
    private Button btn_sure;
    private Button btn_unsure;
    private RecyclerView recyclerView;
    private MyHeadMainRecyclerAdapter adapter;
    private IDaoUtil util;

    private List<BHPZ> bhpzList = new ArrayList<>(); //保护元数据
    private List<FZBHSB> fzbhsbList = new ArrayList<>();//辅助元数据
    private List<BZSJ> zbhlbList = new ArrayList<>(); //主保护类别
    private List<BZSJ> fzbhlbList = new ArrayList<>(); //辅助保护类别
    private LabelLayout layout;
    private int what;//标记哪一个
    private List<CommonFilter> pxOneList = new ArrayList<>();
    private List<CommonFilter> pxTwoList = new ArrayList<>();
    private List<CommonFilter> bhlbList = new ArrayList<>();
    private List<CommonFilter> fbhlbList = new ArrayList<>();
    private List<CommonFilter> clztList = new ArrayList<>();

    private boolean isSearch = false; //标志是否搜索，默认false
    private SearchEntity shEntity;//搜索需要
    private int currentPage = 0;//分页数
    private long totalCount;
    private View footerLayout;
    private ProgressBar load_progress_bar;
//    private boolean isFirst=true;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (shEntity.getZZType().equals("保护")) {
                currentPage = 0;
                bhpzList = util.getBHPZ(currentPage, shEntity);
                totalCount = util.getTotalSb(BHPZ.class, shEntity);
                adapter.setDatas(bhpzList);
            } else {
                currentPage = 0;
                fzbhsbList = util.getFZBHSB(currentPage, shEntity);
                totalCount = util.getTotalSb(FZBHSB.class, shEntity);
                adapter.setDatas(1, fzbhsbList);
            }
        }
    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (what == 0) {
                        shEntity.setPxOne(pxOneList.get(msg.arg1).getTitle());
                        layout.setData2(pxTwoList);
                    } else if (what == 1) {
                        if (shEntity.getZZType().equals("保护")) {
                            shEntity.setBhlb(bhlbList.get(msg.arg1).getTitle());
                            currentPage = 0;
                            bhpzList = util.getBHPZ(currentPage, shEntity);
                            totalCount = util.getTotalSb(BHPZ.class, shEntity);
                            adapter.setDatas(bhpzList);
                        } else {
                            currentPage = 0;
                            shEntity.setBhlb(fbhlbList.get(msg.arg1).getTitle());
                            fzbhsbList = util.getFZBHSB(currentPage, shEntity);
                            totalCount = util.getTotalSb(FZBHSB.class, shEntity);
                            adapter.setDatas(1, fzbhsbList);
                        }
                        if (shEntity.getBhlb().equals("重置")) {
                            bhlbList.get(msg.arg1).setSelect(false);
                            tv_bhlb.setText("保护类别");
                            tv_bhlb.setSelected(false);
                        } else {
                            tv_bhlb.setText(shEntity.getBhlb());
                            tv_bhlb.setSelected(true);
                        }
                    } else if (what == 2) {
                        shEntity.setClzt(clztList.get(msg.arg1).getTitle());
                        if (shEntity.getZZType().equals("保护")) {
                            currentPage = 0;
                            bhpzList = util.getBHPZ(currentPage, shEntity);
                            totalCount = util.getTotalSb(BHPZ.class, shEntity);
                            adapter.setDatas(bhpzList);
                        } else {
                            currentPage = 0;
                            fzbhsbList = util.getFZBHSB(currentPage, shEntity);
                            totalCount = util.getTotalSb(FZBHSB.class, shEntity);
                            adapter.setDatas(1, fzbhsbList);
                        }
                        if (shEntity.getClzt().equals("重置")) {
                            clztList.get(msg.arg1).setSelect(false);
                            tv_clzt.setText("处理状态");
                            tv_clzt.setSelected(false);
                        } else {
                            tv_clzt.setText(shEntity.getClzt());
                            tv_clzt.setSelected(true);
                        }
                    }
                    break;
                case 2:
                    shEntity.setPxTwo(pxTwoList.get(msg.arg2).getTitle());
                    if (shEntity.getPxTwo().equals("重置")) {
                        pxTwoList.get(msg.arg2).setSelect(false);
                        tv_px.setText("排序");
                        tv_px.setSelected(false);
                    } else {
                        tv_px.setText(shEntity.getPxTwo());
                        tv_px.setSelected(true);
                    }
                    if (shEntity.getZZType().equals("保护")) {
                        currentPage = 0;
                        bhpzList = util.getBHPZ(currentPage, shEntity);
                        totalCount = util.getTotalSb(BHPZ.class, shEntity);
                        adapter.setDatas(bhpzList);
                    } else {
                        currentPage = 0;
                        fzbhsbList = util.getFZBHSB(currentPage, shEntity);
                        totalCount = util.getTotalSb(FZBHSB.class, shEntity);
                        adapter.setDatas(1, fzbhsbList);
                    }
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter filter = new IntentFilter("cn.sgg.fzbhsb");
        getActivity().registerReceiver(receiver, filter);
        instance = this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_devicelist;
    }

    private View main;
    private FilterPopupWindow popupWindow;
    private List<SaleAttributeNameVo> itemData;
    List<List> list5 ;

    public static DeviceListFragments instance = null;

    public void receiveData(List<SaleAttributeNameVo> itemDatas,List<SaleAttributeNameVo> itemData2 ) {
        //itemDatas是全部数据,itemData2仅是被选中的数据
        //itemData.clear();
        itemData=itemDatas;
        for (int i = 0; i < itemData2.size(); i++) {
            SaleAttributeNameVo saleName = new SaleAttributeNameVo();
            saleName=itemData2.get(i);
            Toast.makeText(getActivity(), saleName.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initview() {
        main =  getActivity().findViewById(R.id.main);
        List<String> list1 = new ArrayList<>();
        list1.add("220");
        list1.add("110");
        list1.add("330");
        list1.add("440");
        List<String> list2 = new ArrayList<>();
        list2.add("安静的法律空间撒大家");
        list2.add("空间的时候发货路上看到姐夫");
        list2.add("就看到生理健康圣诞节法兰克福交流看法");
        list2.add("算了看得见啊发了看见了开发建设离开的");
        List<String> list3 = new ArrayList<>();
        list3.add("上来大家福利基金");
        list3.add("立刻就收到了尽快");
        list3.add("看时间的累计扣分");
        list3.add("路上就看到了客服经理设计费");
        List<String> list4 = new ArrayList<>();
        list4.add("上来大家福利基金");
        list4.add("立刻就收到了尽快");
        list4.add("看时间的累计扣分");
        list4.add("路上就看到了客服经理设计费");
        list4.add("上来大家福利基金");
        list4.add("立刻就收到了尽快");
        list4.add("看时间的累计扣分");
        list4.add("路上就看到了客服经理设计费");
        list4.add("上来大家福利基金");
        list4.add("立刻就收到了尽快");
        list4.add("看时间的累计扣分");
        list4.add("路上就看到了客服经理设计费");
        list4.add("上来大家福利基金");
        list4.add("立刻就收到了尽快");
        list4.add("看时间的累计扣分");
        list4.add("路上就看到了客服经理设计费");
        list4.add("上来大家福利基金");
        list4.add("立刻就收到了尽快");
        list4.add("看时间的累计扣分");
        list4.add("路上就看到了客服经理设计费");
        list4.add("上来大家福利基金");
        list4.add("立刻就收到了尽快");
        list4.add("看时间的累计扣分");
        list4.add("路上就看到了客服经理设计费");
        list5 = new ArrayList<>();
        list5.add(list1);
        list5.add(list2);
        list5.add(list3);
        list5.add(list4);

        String[] nameFilter = new String[]{"数据状态", "电压等级", "装置类别","调度单位"};
        itemData = new ArrayList<SaleAttributeNameVo>();
        //itemData.clear();
        for (int i = 0; i < list5.size(); i++) {
            SaleAttributeNameVo saleName = new SaleAttributeNameVo();
            saleName.setName(nameFilter[i]); //name,即属性名------------------------
            List<SaleAttributeVo> list = new ArrayList<SaleAttributeVo>();
            List<String> list6 = new ArrayList<>();
            list6=list5.get(i);
            for (int j = 0; j < list6.size(); j++) {
                SaleAttributeVo vo = new SaleAttributeVo();
                vo.setValue(list6.get(j));
                list.add(vo);
            }
            saleName.setSaleVo(list);   //设置saleVo,即属性值------------------
            // 是否展开
            saleName.setNameChecked(false);
            itemData.add(saleName);     //将所有的属性和属性值放入itemData
        }

        //一次设备名称
        SaleAttributeNameVo saleName1 = new SaleAttributeNameVo();
        saleName1.setName("一次设备名称");
        List<SaleAttributeVo> list_ycsbmc = new ArrayList<SaleAttributeVo>();
        SaleAttributeVo vo = new SaleAttributeVo();
        vo.setValue(" ");
        list_ycsbmc.add(vo);
        saleName1.setSaleVo(list_ycsbmc);
        itemData.add(saleName1);
        //制造厂家
        SaleAttributeNameVo saleName2 = new SaleAttributeNameVo();
        saleName2.setName("制造厂家");
        List<SaleAttributeVo> list_zzcj = new ArrayList<SaleAttributeVo>();
        SaleAttributeVo vo2 = new SaleAttributeVo();
        vo.setValue(" ");
        list_zzcj.add(vo);
        saleName2.setSaleVo(list_zzcj);
        itemData.add(saleName2);
        //型号
        SaleAttributeNameVo saleName3 = new SaleAttributeNameVo();
        saleName3.setName("型号");
        List<SaleAttributeVo> list_xh = new ArrayList<SaleAttributeVo>();
        SaleAttributeVo vo3 = new SaleAttributeVo();
        vo.setValue(" ");
        list_xh.add(vo);
        saleName3.setSaleVo(list_xh);
        itemData.add(saleName3);
//-----------------------以上是筛选测试数据----------------------------------------







        tv_search_type = (TextView) getActivity().findViewById(R.id.tv_search_type);
        tv_px = (TextView) getActivity().findViewById(R.id.tv_px);
        tv_bhlb = (TextView) getActivity().findViewById(R.id.tv_bhlb);
        tv_clzt = (TextView) getActivity().findViewById(R.id.tv_clzt);
        tv_sx = (TextView) getActivity().findViewById(R.id.tv_sx);
        tv_deep = (TextView) getActivity().findViewById(R.id.tv_deep);
        layout = new LabelLayout(getActivity(), mHandler, true);
        footerLayout = LayoutInflater.from(getActivity()).inflate(R.layout.listview_footer, null);
        load_progress_bar = (ProgressBar) footerLayout.findViewById(R.id.load_progress_bar);

        app_search_edit = (EditText) getActivity().findViewById(R.id.app_search_edit);
        btn_sure = (Button) getActivity().findViewById(R.id.btn_sure);
        btn_unsure = (Button) getActivity().findViewById(R.id.btn_unsure);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getActivity()));
        adapter = new MyHeadMainRecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来判断是否正在向最后一个滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    tv_deep.setVisibility(View.GONE);
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    //判断是否滚动到底部并且是向下滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        if (!adapter.haveFooterView()) {
                            adapter.addFooterView(footerLayout);
                            adapter.notifyDataSetChanged();
                            recyclerView.scrollToPosition(manager.getItemCount() - 1);
                        }
                        if (shEntity.getZZType().equals("保护")) {//保护设备
                            if (bhpzList.size() == totalCount) {
                                footerLayout.setVisibility(View.GONE);
                                load_progress_bar.setVisibility(View.GONE);
//                                if (isFirst){
//                                    adapter.notifyItemRemoved(adapter.getItemCount()-1);
//                                    isFirst=false;
//                                }
                                ToastUtils.showToast(getActivity(), "没有更多数据了...");
                            } else {
                                tv_deep.setVisibility(View.VISIBLE);
                                footerLayout.setVisibility(View.VISIBLE);
                                load_progress_bar.setVisibility(View.VISIBLE);
                                //加载更多功能代码
                                simulateLoadingData(0);
                            }
                        } else {//辅助设备
                            if (fzbhsbList.size() == totalCount) {
                                footerLayout.setVisibility(View.GONE);
                                load_progress_bar.setVisibility(View.GONE);
//                                if (isFirst){
//                                    adapter.notifyItemRemoved(adapter.getItemCount()-1);
//                                    isFirst=false;
//                                }
                                ToastUtils.showToast(getActivity(), "没有更多数据了...");
                            } else {
                                tv_deep.setVisibility(View.VISIBLE);
                                footerLayout.setVisibility(View.VISIBLE);
                                load_progress_bar.setVisibility(View.VISIBLE);
                                //加载更多功能代码
                                simulateLoadingData(1);
                            }
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //dx用来判断横向滑动放向，dy用来判断纵向滑动方向
                if (dy > 0) {
                    //大于0表示正在向下滚动
                    isSlidingToLast = true;
                    tv_deep.setVisibility(View.VISIBLE);
                } else if (dy < 0) {
                    //小于0表示向上滚动
                    isSlidingToLast = false;
                    tv_deep.setVisibility(View.VISIBLE);
                }
                if (adapter.haveFooterView()) {
                    if (manager.findLastVisibleItemPosition() == manager.getItemCount() - 1 && isSlidingToLast) {
                        tv_deep.setText((manager.findLastVisibleItemPosition()) + "/" +
                                totalCount);
                    } else {
                        tv_deep.setText((manager.findLastVisibleItemPosition() + 1) + "/" +
                                totalCount);
                    }
                } else {
                    tv_deep.setText((manager.findLastVisibleItemPosition() + 1) + "/" +
                            totalCount);
                }
            }
        });
    }

    /**
     * 模拟上拉加载更多时获得更多数据
     */
    private void getNewBottomData(int tag) {
        currentPage++;
        if (tag == 0) {
            bhpzList.addAll(util.getBHPZ(currentPage, shEntity));
            adapter.setDatas(bhpzList);
        } else {
            fzbhsbList.addAll(util.getFZBHSB(currentPage, shEntity));
            adapter.setDatas(1, fzbhsbList);
        }
    }

    /**
     * 模拟一个耗时操作，加载完更多底部数据后刷新ListView
     */
    private void simulateLoadingData(final int tag) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getNewBottomData(tag);
                footerLayout.setVisibility(View.GONE);
                load_progress_bar.setVisibility(View.GONE);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                tv_deep.setText((manager.findLastVisibleItemPosition() + 1) + "/" +
                        totalCount);
                ToastUtils.showToast(getActivity(), "加载完成......");
            }
        }, 1000);
    }

    @Override
    public void initEvevt() {
        btn_sure.setOnClickListener(this);
        btn_unsure.setOnClickListener(this);
        tv_search_type.setOnClickListener(this);
        tv_px.setOnClickListener(this);
        tv_bhlb.setOnClickListener(this);
        tv_clzt.setOnClickListener(this);
        tv_sx.setOnClickListener(this);

        adapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DeviceDetailsActivity.class);
                if (shEntity.getZZType().equals("保护")) {//保护设备
                    intent.putExtra("ZZTYPE", "BHSB");
                    intent.putExtra("BHSB", (Serializable) bhpzList.get(position));
                } else {//辅助设备
                    intent.putExtra("ZZTYPE", "FZSB");
                    intent.putExtra("FZSB", (Serializable) fzbhsbList.get(position));
                }
                intent.putExtra("STATE", "M");
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        shEntity = new SearchEntity();
        shEntity.setZZType("保护");
        util = new DaoUtil(getActivity());
        bhpzList = util.getBHPZ(currentPage, shEntity);
        totalCount = util.getTotalSb(BHPZ.class, shEntity);
        adapter.setDatas(bhpzList);
        zbhlbList = util.getBZSJ("主保护类别");
        fzbhlbList = util.getBZSJ("辅助保护类别");
        for (String s : Constants.pxOne) {
            pxOneList.add(new CommonFilter(s));
        }
        for (String s : Constants.pxTwo) {
            pxTwoList.add(new CommonFilter(s));
        }
        for (String s : Constants.clzt) {
            clztList.add(new CommonFilter(s));
        }
        for (BZSJ bzsj : zbhlbList) {
            bhlbList.add(new CommonFilter(bzsj.getBzsjSxmc()));
        }
        bhlbList.add(new CommonFilter("重置"));
        for (BZSJ bzsj : fzbhlbList) {
            fbhlbList.add(new CommonFilter(bzsj.getBzsjSxmc()));
        }
        fbhlbList.add(new CommonFilter("重置"));
    }

    @Override
    public void initReceiver(boolean isEdit) {

    }

    /**
     * 弹出分类方式的列表
     */
    public void showPopuWindow() {
        View v = getActivity().getLayoutInflater().inflate(R.layout.head_search_popwindow, null);

        final PopupWindow pop = new PopupWindow(v, ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT, true);

        final TextView tv1 = (TextView) v.findViewById(R.id.tv_search_country);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shEntity.getZZType().equals("辅助")) {
                    shEntity.setZZType("保护");
                    tv_search_type.setText(tv1.getText());
                    if (!shEntity.getBhlb().equals("")) {
                        shEntity.setBhlb("");
                        tv_bhlb.setText("保护类别");
                        tv_bhlb.setSelected(false);
                    }
                    currentPage = 0;
                    bhpzList = util.getBHPZ(currentPage, shEntity);
                    totalCount = util.getTotalSb(BHPZ.class, shEntity);
                    adapter.setDatas(bhpzList);
                }
                pop.dismiss();
            }
        });
        final TextView tv2 = (TextView) v.findViewById(R.id.tv_search_org);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shEntity.getZZType().equals("保护")) {
                    shEntity.setZZType("辅助");
                    tv_search_type.setText(tv2.getText());
                    if (!shEntity.getBhlb().equals("")) {
                        shEntity.setBhlb("");
                        tv_bhlb.setText("保护类别");
                        tv_bhlb.setSelected(false);
                    }
                    currentPage = 0;
                    fzbhsbList = util.getFZBHSB(currentPage, shEntity);
                    totalCount = util.getTotalSb(FZBHSB.class, shEntity);
                    adapter.setDatas(1, fzbhsbList);
                }
                pop.dismiss();
            }
        });

        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(true);
//        pop.showAtLocation(tv_search_type, Gravity.LEFT, 10, 0);
        pop.showAsDropDown(tv_search_type, 23, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search_type:
                showPopuWindow();
                break;
            case R.id.btn_sure: //确定
                String searchStr = app_search_edit.getText().toString().trim();
                if (searchStr.equals("")) {
                    new EditTextShakeHelper(getActivity()).shake(app_search_edit);
                    return;
                }
                shEntity.setZZName(searchStr);
                if (shEntity.getZZType().equals("保护")) {
                    currentPage = 0;
                    bhpzList = util.getBHPZ(currentPage, shEntity);
                    totalCount = util.getTotalSb(BHPZ.class, shEntity);
                    adapter.setDatas(bhpzList);
                } else {
                    currentPage = 0;
                    fzbhsbList = util.getFZBHSB(currentPage, shEntity);
                    totalCount = util.getTotalSb(FZBHSB.class, shEntity);
                    adapter.setDatas(1, fzbhsbList);
                }
                isSearch = true;
                break;
            case R.id.btn_unsure: //重置 改为筛选
//                if (isSearch) {
//                    shEntity.setZZName("");
//                    app_search_edit.setText("");
//                    if (shEntity.getZZType().equals("保护")) {
//                        currentPage = 0;
//                        bhpzList = util.getBHPZ(currentPage, shEntity);
//                        totalCount = util.getTotalSb(BHPZ.class, shEntity);
//                        adapter.setDatas(bhpzList);
//                    } else {
//                        currentPage = 0;
//                        fzbhsbList = util.getFZBHSB(currentPage, shEntity);
//                        totalCount = util.getTotalSb(FZBHSB.class, shEntity);
//                        adapter.setDatas(1, fzbhsbList);
//                    }
//                    isSearch = false;
//                }
                popupWindow = new FilterPopupWindow(getActivity(),itemData);
                popupWindow.showFilterPopup(main);
                break;
            case R.id.tv_px://排序
                what = 0;
                layout.setData(pxOneList, tv_px);
                break;
            case R.id.tv_bhlb://保护类别
                what = 1;
                if (tv_search_type.getText().toString().equals("保护")) {
                    new LabelLayout(getActivity(), mHandler).setData(bhlbList, tv_px);
                } else {
                    new LabelLayout(getActivity(), mHandler).setData(fbhlbList, tv_px);
                }
                break;
            case R.id.tv_clzt://处理状态
                what = 2;
                new LabelLayout(getActivity(), mHandler).setData(clztList, tv_px);
                break;
            case R.id.tv_sx://筛选
                what = 3;
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
