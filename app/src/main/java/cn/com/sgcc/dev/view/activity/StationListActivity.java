package cn.com.sgcc.dev.view.activity;//package cn.com.sgcc.dev.view.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.com.sgcc.dev.R;
//import cn.com.sgcc.dev.adapter.MyStationRecyclerAdapter;
//import cn.com.sgcc.dev.customeView.recylerUtil.MyItemClickListener;
//import cn.com.sgcc.dev.dao2.CZCSDao;
//import cn.com.sgcc.dev.dbUtils.DBManager;
//import cn.com.sgcc.dev.dbUtils.DaoUtil;
//import cn.com.sgcc.dev.dbUtils.IDaoUtil;
//import cn.com.sgcc.dev.model2.RLST_USER;
//import cn.com.sgcc.dev.model2.ycsb.CZCS;
//import cn.com.sgcc.dev.utils.EditTextShakeHelper;
//import cn.com.sgcc.dev.utils.PreferenceUtils;
//import cn.com.sgcc.dev.utils.ToastUtils;
//
///**
// * <p>@description:厂站列表页</p>
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/28
// */
//
//public class StationListActivity extends BaseActivity implements View.OnClickListener {
//
//    private EditText app_search_edit;
//    private Button btn_sure;
//    private Button btn_unsure;
//    private RecyclerView recyclerView;
//    private TextView tv_deep;
//    private View footerLayout;
//    private MyStationRecyclerAdapter adapter;
//    private List<CZCS> orglist = new ArrayList<>();//原始数据
//    private List<CZCS> list = new ArrayList<>();//要显示的数据  每次10条
//    private List<CZCS> searchList = new ArrayList<>();
//    private ProgressBar load_progress_bar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_stationlist);
//        initView();
//        initData();
//
//        adapter.setOnItemClickListener(new MyItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(StationListActivity.this, MainActivity.class);
//                if (searchList.size() > 0) {
//                    PreferenceUtils.setPrefString(StationListActivity.this, "czmc", searchList.get(position).getCzmc());
//                    PreferenceUtils.setPrefString(StationListActivity.this, "czmcID", searchList.get(position).getId()+"");
//                } else {
//                    PreferenceUtils.setPrefString(StationListActivity.this, "czmc", orglist.get(position).getCzmc());
//                    PreferenceUtils.setPrefString(StationListActivity.this, "czmcID", orglist.get(position).getId()+"");
//                }
//                startActivity(intent);
//            }
//        });
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            //用来判断是否正在向最后一个滑动
//            boolean isSlidingToLast = false;
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (searchList.size() > 0)
//                    return;
//                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                //当不滚动时
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    tv_deep.setVisibility(View.GONE);
//                    //获取最后一个完全显示的ItemPosition
//                    int lastVisibleItem = manager.findLastVisibleItemPosition();
//                    int totalItemCount = manager.getItemCount();
//                    //判断是否滚动到底部并且是向下滚动
//                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
//                        if (!adapter.haveFooterView()) {
//                            adapter.addFooterView(footerLayout);
//                            adapter.notifyDataSetChanged();
//                            recyclerView.scrollToPosition(manager.getItemCount() - 1);
//                        }
//
//                        if (list.size() == orglist.size()) {
//                            footerLayout.setVisibility(View.GONE);
//                            load_progress_bar.setVisibility(View.GONE);
//                            ToastUtils.showToast(StationListActivity.this, "没有更多数据了...");
//                        } else {
//                            tv_deep.setVisibility(View.VISIBLE);
//                            footerLayout.setVisibility(View.VISIBLE);
//                            load_progress_bar.setVisibility(View.VISIBLE);
//                            //加载更多功能代码
//                            simulateLoadingData();
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                //dx用来判断横向滑动放向，dy用来判断纵向滑动方向
//                if (dy > 0) {
//                    //大于0表示正在向下滚动
//                    isSlidingToLast = true;
//                    tv_deep.setVisibility(View.VISIBLE);
//                } else if (dy < 0) {
//                    //小于0表示向上滚动
//                    isSlidingToLast = false;
//                    tv_deep.setVisibility(View.VISIBLE);
//                }
//                if (adapter.haveFooterView()) {
//                    if (manager.findLastVisibleItemPosition() == manager.getItemCount() - 1 && isSlidingToLast) {
//                        tv_deep.setText((manager.findLastVisibleItemPosition()) + "/" +
//                                orglist.size());
//                    } else {
//                        tv_deep.setText((manager.findLastVisibleItemPosition() + 1) + "/" +
//                                orglist.size());
//                    }
//                } else {
//                    tv_deep.setText((manager.findLastVisibleItemPosition() + 1) + "/" +
//                            orglist.size());
//                }
//            }
//        });
//    }
//
//    /**
//     * 模拟上拉加载更多时获得更多数据
//     */
//    private void getNewBottomData() {
//        int size = list.size();
//        if (orglist.size() - size >= 10) {
//            for (int i = 0; i < 10; i++) {
//                CZCS ss = orglist.get(size + i);
//                list.add(ss);
//            }
//        } else {
//            for (int i = size; i < orglist.size(); i++) {
//                CZCS ss = orglist.get(i);
//                list.add(ss);
//            }
//        }
//        adapter.setDatas(list);
//    }
//
//    /**
//     * 模拟一个耗时操作，加载完更多底部数据后刷新ListView
//     */
//    private void simulateLoadingData() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getNewBottomData();
//                footerLayout.setVisibility(View.GONE);
//                load_progress_bar.setVisibility(View.GONE);
//                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                tv_deep.setText((manager.findLastVisibleItemPosition() + 1) + "/" +
//                        orglist.size());
//                ToastUtils.showToast(StationListActivity.this, "加载完成......");
//            }
//        }, 1000);
//    }
//
//    private void initData() {
//        IDaoUtil util = new DaoUtil(this);
//        String name = PreferenceUtils.getPrefString(this, "userInfo", null);
//        RLST_USER user = util.getUserByName(name.split("-")[0]);
//        if (user.getDwjb() != null && !user.getDwjb().equals("")) {
//            orglist = util.getCZCS(null);
//        } else {
//            orglist = util.getCZCS(user.getSsgs());
//        }
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                if (orglist.size() >= 10) {
//                    for (int i = 0; i < 10; i++) {
//                        list.add(orglist.get(i));
//                    }
//                } else {
//                    for (int i = 0; i < orglist.size(); i++) {
//                        list.add(orglist.get(i));
//                    }
//                }
//                adapter.setDatas(list);
//            }
//        });
//    }
//
//    private void initView() {
//        tv_deep = (TextView) findViewById(R.id.tv_deep);
//        ((TextView) findViewById(R.id.app_toolbar_center)).setText("厂站列表");
//        app_search_edit = (EditText) findViewById(R.id.app_search_edit);
//        btn_sure = (Button) findViewById(R.id.btn_sure);
//        btn_unsure = (Button) findViewById(R.id.btn_unsure);
//        btn_sure.setOnClickListener(this);
//        btn_unsure.setOnClickListener(this);
//        tv_deep.setOnClickListener(this);
//        footerLayout = LayoutInflater.from(this).inflate(R.layout.listview_footer, null);
//        load_progress_bar = (ProgressBar) footerLayout.findViewById(R.id.load_progress_bar);
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        recyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
//        adapter = new MyStationRecyclerAdapter(this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
////            case R.id.tv_deep:
////                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////                break;
//            case R.id.btn_sure:
//                String content = app_search_edit.getText().toString().trim();
//                if (TextUtils.isEmpty(content)) {
//                    new EditTextShakeHelper(this).shake(app_search_edit);
//                } else {
//                    if (!searchList.isEmpty())
//                        searchList.clear();
//                    for (CZCS czcs : orglist) {
//                        if (czcs.getCzmc().contains(content))
//                            searchList.add(czcs);
//                    }
//                    adapter.setDatas(searchList);
//                }
//                break;
//            case R.id.btn_unsure:
//                app_search_edit.setText("");
//                adapter.setDatas(list);
//                if (!searchList.isEmpty())
//                    searchList.clear();
//                break;
//        }
//    }
//
//    // 退出时间
//    private long exitTime = 0;
//
//    // 退出
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK
//                && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if (System.currentTimeMillis() - exitTime > 2000) {
//                ToastUtils.showToast(this, "再按一次退出程序");
//                exitTime = System.currentTimeMillis();
//            } else {
//                finish();
//                System.exit(0);
//            }
//        }
//        return true;
//    }
//}
