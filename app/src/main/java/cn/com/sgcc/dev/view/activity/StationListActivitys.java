package cn.com.sgcc.dev.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.MyStationRecyclerAdapters;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.LoadingDialog;
import cn.com.sgcc.dev.customeView.recylerUtil.MyItemClickListener;
import cn.com.sgcc.dev.model2.vo.DataHolder;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.utils.EditTextShakeHelper;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.viewinterface.LetterView;
import cn.com.sgcc.dev.view.viewinterface.SideBar;

/**
 * <p>@description:变电站列表页</p>
 *
 * @author lxf
 * @version 1.0.0
 * @since 2018/1/12
 */

public class StationListActivitys extends BaseActivity implements View.OnClickListener {

    private EditText app_search_edit;
    private Button btn_sure;
    private Button btn_unsure;
    private RecyclerView recyclerView;
    private TextView tv_deep;
    private View footerLayout;
    private MyStationRecyclerAdapters adapter;
    private List<CZCS> orglist = new ArrayList<>();//原始数据
    private List<CZCS> list = new ArrayList<>();//要显示的数据  每次10条
    private List<CZCS> searchList = new ArrayList<>(); //输入框搜索到的数据
    private ProgressBar load_progress_bar;
    private ImageView app_toolbar_left, app_toolbar_sao;

    private LetterView letterView;
    private LinearLayoutManager layoutManager;
    private LoadingDialog progressDialog;
    private Timer timer;
    private TimerTask task;
    private SideBar sideBar;
    private TextView mUserDialog, tishi;
    public static String type; //标志厂站顶部的提示信息
    public static String type_bdz; //标志厂站顶部的提示信息
    public static String type_sbsbdm; //扫码获取的设备识别码
    private static long mLastActionTime; // 首次进入变电站的时间


    private TextView sousuo_tx, bdzitem_tx, wode_tx1, bdzitem_tx1, zimu_tx, zimu_tx1;
    private RelativeLayout bzd_zhinan;
    int flag_all = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_stationlists);
        initView();
        //initData();
        startTimer();
        initEvevt();

        mLastActionTime = System.currentTimeMillis();

        IntentFilter filters = new IntentFilter("cn.sgg.finishActivity");
        registerReceiver(receivers, filters);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        letterView.setCharacterListener(new LetterView.CharacterClickListener() {
            //点击字母或#时候的回调
            @Override
            public void clickCharacter(String character) {
                layoutManager.scrollToPositionWithOffset(adapter.getScrollPosition(character), 0);
            }

            //点击顶部箭头的回调
            @Override
            public void clickArrow() {
                layoutManager.scrollToPositionWithOffset(0, 0);
            }
        });

        //保存点击了哪个变电站进入
        adapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(StationListActivitys.this, DeviceListActivity.class);
//                if (searchList.size() > 0) {
//                    PreferenceUtils.setPrefString(StationListActivitys.this, "czmc", searchList.get(position).getCZMC());
//                    PreferenceUtils.setPrefString(StationListActivitys.this, "czmcID", searchList.get(position).getID()+"");
//                } else {
//                    PreferenceUtils.setPrefString(StationListActivitys.this, "czmc", orglist.get(position).getCZMC());
//                    PreferenceUtils.setPrefString(StationListActivitys.this, "czmcID", orglist.get(position).getID()+"");
//                }
//                intent.putExtra("BDZTYPE", type_bdz);
//                intent.putExtra("BDZTYPEsbsbdm", type_sbsbdm);
//                startActivity(intent);
                Intent intent = new Intent();
                if (searchList.size() > 0) {
                    PreferenceUtils.setPrefString(StationListActivitys.this, "czmc", searchList.get(position).getCzmc());
                    PreferenceUtils.setPrefString(StationListActivitys.this, "czmcID", searchList.get(position).getId() + "");
                } else {
                    PreferenceUtils.setPrefString(StationListActivitys.this, "czmc", orglist.get(position).getCzmc());
                    PreferenceUtils.setPrefString(StationListActivitys.this, "czmcID", orglist.get(position).getId() + "");
                }
                if (type_bdz != null && type_bdz.equals("xqbz")) {
                    //设置返回数据
                    StationListActivitys.this.setResult(RESULT_OK, intent);
                    //关闭Activity
                    finish();
                } else {
                    intent.setClass(StationListActivitys.this, DeviceListActivity.class);
                    intent.putExtra("BDZTYPE", type_bdz);
                    intent.putExtra("BDZTYPEsbsbdm", type_sbsbdm);
                    intent.putExtra("isSwid", getIntent().getBooleanExtra("isSwid", true));
                    startActivity(intent);
                }
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来判断是否正在向最后一个滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                if (searchList.size() > 0)
//                    return;
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

                        if (list.size() == orglist.size()) {
                            footerLayout.setVisibility(View.GONE);
                            load_progress_bar.setVisibility(View.GONE);
                            //ToastUtils.showToast(StationListActivitys.this, "没有更多数据了...");
                        } else {
//                            tv_deep.setVisibility(View.VISIBLE);
//                            footerLayout.setVisibility(View.VISIBLE);
//                            load_progress_bar.setVisibility(View.VISIBLE);
//                            //加载更多功能代码
//                            simulateLoadingData();
                        }

                        ToastUtils.showToast(StationListActivitys.this, "没有更多数据了...");
                    }
                }
            }

            //处理右下角数字
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
                                orglist.size());
                    } else {
                        tv_deep.setText((manager.findLastVisibleItemPosition() + 1) + "/" +
                                orglist.size());
                    }
                } else {
                    tv_deep.setText((manager.findLastVisibleItemPosition() + 1) + "/" +
                            orglist.size());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        String czmcs = PreferenceUtils.getPrefString(StationListActivitys.this, "czmc", null); //厂站名称
        if (czmcs != null) {
            tishi.setVisibility(View.GONE);
        }
    }

    //接受详情返回的广播,destroy列表
    BroadcastReceiver receivers = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            StationListActivitys.this.finish();
        }
    };

    // 定时器,不断获取数据
    public void startTimer() {
        timer = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (DataHolder.getInstance().getData() != null) {
                            if (DataHolder.getInstance().getData().size() > 0) {
                                orglist = DataHolder.getInstance().getData();
                                adapter.setDatas(orglist);
                                progressDialog.dismiss();
                                timer.cancel();
                            }
                        } else if (System.currentTimeMillis() - mLastActionTime > Constants.ch_timess) {//db文件中变电站数据为空时  超时时间2分钟
                            progressDialog.dismiss();
                            timer.cancel();
                            ToastUtils.showToast(StationListActivitys.this, "未获取变电站数据");
                        }
                    }
                });
            }
        };
        timer.schedule(task, 20, 20);
    }

    /**
     * 模拟上拉加载更多时获得更多数据
     */
    private void getNewBottomData() {
        int size = list.size();
        if (orglist.size() - size >= 16) {
            for (int i = 0; i < 16; i++) {
                CZCS ss = orglist.get(size + i);
                list.add(ss);
            }
        } else {
            for (int i = size; i < orglist.size(); i++) {
                CZCS ss = orglist.get(i);
                list.add(ss);
            }
        }


        adapter.setDatas(list);
    }

    /**
     * 模拟一个耗时操作，加载完更多底部数据后刷新ListView
     */
    private void simulateLoadingData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getNewBottomData();
                footerLayout.setVisibility(View.GONE);
                load_progress_bar.setVisibility(View.GONE);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                tv_deep.setText((manager.findLastVisibleItemPosition() + 1) + "/" +
                        orglist.size());
                ToastUtils.showToast(StationListActivitys.this, "加载完成......");
            }
        }, 1001);
    }

    private void initData() {
//        IDaoUtil util = new DaoUtil(this);
//        String name = PreferenceUtils.getPrefString(this, "userInfo", null);
//        RLST_USER user = util.getUserByName(name.split("-")[0]);
//        if (user.getDWJB() != null && !user.getDWJB().equals("")) {
//            orglist = util.getCZCS(null);
//        } else {
//            orglist = util.getCZCS(user.getSSGS());
//        }

        if (DataHolder.getInstance().getData() != null) {
            if (DataHolder.getInstance().getData().size() > 0) {
                orglist = DataHolder.getInstance().getData();
                adapter.setDatas(orglist);
                progressDialog.dismiss();
            }
        }

//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                if (orglist.size() >= 16) {
//                    for (int i = 0; i < 16; i++) {
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
    }

    private void initView() {
        //指南
        sousuo_tx = (TextView) findViewById(R.id.sousuo_tx);
        wode_tx1 = (TextView) findViewById(R.id.wode_tx1);
        bdzitem_tx = (TextView) findViewById(R.id.bdzitem_tx);
        bdzitem_tx1 = (TextView) findViewById(R.id.bdzitem_tx1);
        zimu_tx = (TextView) findViewById(R.id.zimu_tx);
        zimu_tx1 = (TextView) findViewById(R.id.zimu_tx1);
        bdzitem_tx1 = (TextView) findViewById(R.id.bdzitem_tx1);
        bzd_zhinan = (RelativeLayout) findViewById(R.id.bzd_zhinan);

        progressDialog = new LoadingDialog(this);
        letterView = (LetterView) findViewById(R.id.letter_view);
        tv_deep = (TextView) findViewById(R.id.tv_deep);
        tishi = (TextView) findViewById(R.id.tishi);
        ((TextView) findViewById(R.id.app_toolbar_center)).setText("变电站列表");
        app_search_edit = (EditText) findViewById(R.id.app_search_edit);
        btn_sure = (Button) findViewById(R.id.btn_sure);
        btn_unsure = (Button) findViewById(R.id.btn_unsure);
        btn_sure.setOnClickListener(this);
        btn_unsure.setOnClickListener(this);
        tv_deep.setOnClickListener(this);
        footerLayout = LayoutInflater.from(this).inflate(R.layout.listview_footer, null);
        load_progress_bar = (ProgressBar) footerLayout.findViewById(R.id.load_progress_bar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        adapter = new MyStationRecyclerAdapters(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        app_toolbar_left = (ImageView) findViewById(R.id.app_toolbar_left);
        app_toolbar_sao = (ImageView) findViewById(R.id.app_toolbar_sao);

        //指南
        if (PreferenceUtils.getPrefBoolean(StationListActivitys.this, "zhinan_station", false)) {
            bzd_zhinan.setVisibility(View.GONE);
        } else {
            //PreferenceUtils.setPrefBoolean(StationListActivitys.this, "zhinan_station", true);
        }

        //搜索输入框监听
        app_search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(s.toString())) { //输入框内容为空
                    adapter.setDatas(orglist);
                    if (!searchList.isEmpty()) {
                        searchList.clear();
                    }
                } else { //输入框内容不为空
                    String content = app_search_edit.getText().toString().trim();
                    if (TextUtils.isEmpty(content)) {
                        new EditTextShakeHelper(StationListActivitys.this).shake(app_search_edit);
                    } else {
                        if (!searchList.isEmpty()) {
                            searchList.clear();
                        }

                        for (CZCS czcs : orglist) {
                            if (czcs.getCzmc().contains(content)) {
                                searchList.add(czcs);
                            }
                        }
                        if (searchList.size() == 0) {
                            ToastUtils.showToast(StationListActivitys.this, "无搜索结果");
                        }
                        adapter.setDatas(searchList);
                    }
                }
            }
        });


        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        sideBar = (SideBar) findViewById(R.id.left_zimu);
        mUserDialog = (TextView) findViewById(R.id.contact_dialog);
        sideBar.setTextView(mUserDialog);
        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
//                int position = classAdapter.getPositionForSection(s.charAt(0));
//                if (position != -1) {
//                    classListView.setSelection(position);
//                }
                if (s.equals("#")) {
                    layoutManager.scrollToPositionWithOffset(0, 0);
                } else {
                    layoutManager.scrollToPositionWithOffset(adapter.getScrollPosition(s), 0);
                }
            }
        });

        //顶部提示信息判断
        if (getIntent().hasExtra("SYTYPE")) {
            type = getIntent().getStringExtra("SYTYPE");
            type_bdz = type;
            if (type.equals("zz1")) {
                tishi.setVisibility(View.VISIBLE);
                tishi.setText("获取变电站失败,请手动选择变电站");
            } else if (type.equals("SAOYISAO1")) {
                type_sbsbdm = getIntent().getStringExtra("sbsbdm");
                tishi.setVisibility(View.VISIBLE);
                tishi.setText("匹配装置信息失败，请选择变电站再查询或添加装置信息");
            } else {
                tishi.setVisibility(View.GONE);
            }
        }

        //详情界面换站
        if (getIntent().hasExtra("XQBZ")) {
            type_bdz = getIntent().getStringExtra("XQBZ");
            if (type_bdz != null && type_bdz.equals("xqbz")) {
                app_toolbar_sao.setVisibility(View.GONE);
            }
        }

    }

    public void initEvevt() {
        //指南--优先抢占点击事件
        bzd_zhinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtils.showToast(StationListActivitys.this,"哈哈哈哈好");
            }
        });

        sousuo_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_all += 1;
                if (flag_all == 3) {
                    sousuo_tx.setVisibility(View.GONE);
                    wode_tx1.setVisibility(View.GONE);
                    bzd_zhinan.setVisibility(View.GONE);
                    PreferenceUtils.setPrefBoolean(StationListActivitys.this, "zhinan_station", true);
                } else {
                    sousuo_tx.setVisibility(View.GONE);
                    wode_tx1.setVisibility(View.GONE);
                }

            }
        });

        bdzitem_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_all += 1;
                if (flag_all == 3) {
                    bdzitem_tx.setVisibility(View.GONE);
                    bdzitem_tx1.setVisibility(View.GONE);
                    bzd_zhinan.setVisibility(View.GONE);
                    PreferenceUtils.setPrefBoolean(StationListActivitys.this, "zhinan_station", true);
                } else {
                    bdzitem_tx.setVisibility(View.GONE);
                    bdzitem_tx1.setVisibility(View.GONE);
                }

            }
        });

        zimu_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_all += 1;
                if (flag_all == 3) {
                    zimu_tx.setVisibility(View.GONE);
                    zimu_tx1.setVisibility(View.GONE);
                    bzd_zhinan.setVisibility(View.GONE);
                    PreferenceUtils.setPrefBoolean(StationListActivitys.this, "zhinan_station", true);
                } else {
                    zimu_tx.setVisibility(View.GONE);
                    zimu_tx1.setVisibility(View.GONE);
                }
            }
        });


        //返回按钮
        app_toolbar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StationListActivitys.this.finish();
            }
        });

        //结束按钮X
        app_toolbar_sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StationListActivitys.this, MainActivitys.class));
                sendBroadcast(new Intent("cn.sgg.finishActivity"));
                StationListActivitys.this.finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tv_deep:
//                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                break;
            case R.id.btn_sure:
                String content = app_search_edit.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    new EditTextShakeHelper(this).shake(app_search_edit);
                } else {
                    if (!searchList.isEmpty()) {
                        searchList.clear();
                    }
                    for (CZCS czcs : orglist) {
                        if (czcs.getCzmc().contains(content)) {
                            searchList.add(czcs);
                        }
                    }
                    adapter.setDatas(searchList);
                }
                break;
            case R.id.btn_unsure:
                app_search_edit.setText("");
                adapter.setDatas(list);
                if (!searchList.isEmpty()) {
                    searchList.clear();
                }
                break;
        }
    }

    // 点击返回键退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receivers);
    }
}
