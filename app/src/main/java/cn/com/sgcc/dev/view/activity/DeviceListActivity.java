package cn.com.sgcc.dev.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zxing.activity.CaptureActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.MyHeadMainRecyclerAdapters;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.customeView.LabelLayout;
import cn.com.sgcc.dev.customeView.LoadingDialog;
import cn.com.sgcc.dev.customeView.recylerUtil.MyItemClickListener;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.BKXX;
import cn.com.sgcc.dev.model2.BZSJ;
import cn.com.sgcc.dev.model2.CommonFilter;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.ICDXX;
import cn.com.sgcc.dev.model2.SearchEntity;
import cn.com.sgcc.dev.model2.vo.DataHolder;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.fragment.FilterPopupWindow;

/**
 * <p>@description:装置列表</p>
 *
 * @author lxf
 * @version 1.0.0
 * @since 2018/1/12
 */

public class DeviceListActivity extends BaseActivity {
    //传值: name=SYTYPE value= llbj(进浏览可编辑界面)  ll(进浏览不可编辑界面) xz(进新增界面)  name=sbsbdm value= 设备识别代码
    public static String type_sbsbdm; //扫码获取的设备识别码
    public static String type;
    public static String type_details;
    private Button btn_bh, btn_fz, btn_filter;
    private TextView app_toolbar_center;
    private ImageView app_toolbar_right, add_device;
    private DrawerLayout drawer;
    private LoadingDialog progressDialog;
    private ImageView app_toolbar_left, app_toolbar_sao;
    private EditText app_search_edit;

    private boolean isSearch = false; //标志是否搜索，默认false
    private SearchEntity shEntity;//搜索需要
    private int currentPage = 0;//分页数
    private long totalCount;
    private View footerLayout;
    private ProgressBar load_progress_bar;

    private TextView tv_search_type, tishi;
    private TextView tv_px, tv_bhlb, tv_clzt, tv_sx, tv_deep;
    private Button btn_sure;
    private Button btn_unsure;
    private RecyclerView recyclerView;
    private MyHeadMainRecyclerAdapters adapter;
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

    //zz_zhinan,sousuo_tx,shaixuan_tx,zz_tx,,add_devices_tx
    private TextView sousuo_tx, shaixuan_tx, zz_tx, add_devices_tx, sousuo_tx1, shaixuan_tx1, zz_tx1, add_devices_tx1;
    private ImageView add_devices;
    private RelativeLayout zz_zhinan;
    int flag_all = 0;

    boolean isSwid; //是否为实物ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_mainsss);
        instance = this;

        initview();
        initData();
        initEvevt();

        //刷新装置列表的广播
        IntentFilter filter = new IntentFilter("cn.sgg.fzbhsb");
        registerReceiver(receiver, filter);

        IntentFilter filters = new IntentFilter("cn.sgg.finishActivity");
        registerReceiver(receivers, filters);
    }

    //接受详情返回的广播,destroy列表
    BroadcastReceiver receivers = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            DeviceListActivity.this.finish();
        }
    };

    //接受详情返回的广播,是否刷新列表
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

    @Override
    protected void onStart() {
        super.onStart();

        isSwid = getIntent().getBooleanExtra("isSwid", true);

        //顶部提示信息和新增按钮判断
        if (getIntent().hasExtra("SYTYPE")) {
            type = getIntent().getStringExtra("SYTYPE");
            if (type.equals("zz2")) {
//                add_device.setVisibility(View.GONE);
                tishi.setVisibility(View.GONE);
                type_details = "ll";
            } else if (type.equals("SAOYISAO2")) {
                tishi.setVisibility(View.VISIBLE);
                tishi.setText("未匹配到装置信息，请查询或添加装置信息");
                add_device.setVisibility(View.VISIBLE);
                type_sbsbdm = getIntent().getStringExtra("sbsbdm");
                type_details = "llbj";
            }
        }

        if (getIntent().hasExtra("BDZTYPE")) {
            type = getIntent().getStringExtra("BDZTYPE");
            if (type.equals("zz1")) {
//                add_device.setVisibility(View.GONE);
                tishi.setVisibility(View.GONE);
                type_details = "ll";
            } else if (type.equals("bdz")) {
//                add_device.setVisibility(View.GONE);
                tishi.setVisibility(View.GONE);
                type_details = "ll";
            } else if (type.equals("SAOYISAO1")) {
                tishi.setVisibility(View.VISIBLE);
                tishi.setText("未匹配到装置信息，请查询或添加装置信息");
                add_device.setVisibility(View.VISIBLE);
                type_sbsbdm = getIntent().getStringExtra("BDZTYPEsbsbdm");
                type_details = "llbj";
            }
        }

        //从装置列表进入详情核对后返回到装置列表
        if (getIntent().hasExtra("zzlbxz")) {
            type = getIntent().getStringExtra("zzlbxz");
            tishi.setVisibility(View.GONE);
        }
    }


    private View main;
    private FilterPopupWindow popupWindow;
    private List<SaleAttributeNameVo> itemData; //筛选的原始数据
    List<List> list5;

    public static DeviceListActivity instance = null;

    //***此处可以调用接口,获取筛选结果********
    public void receiveData(List<SaleAttributeNameVo> itemDatas, List<SaleAttributeNameVo> itemData2) {
        //itemDatas是全部数据,itemData2仅是被选中的数据
        itemData = itemDatas;
        shEntity.setNameVos(itemData2);
//        boolean hasJY =false;
//        if (itemData2!=null&&itemData2.size()>0){//判断是否选中了装置校验,选中则添加校验项到shEntity
//            for (int i = 0; i < itemData2.size(); i++){
//                if (itemData2.get(i).getName().equals("装置校验")){
//                    hasJY=true;
//                    List<SaleAttributeNameVo> itemData3=PreferenceUtils.getDataList(DeviceListActivity.this,"jyx",null);
//                    List<SaleAttributeNameVo> itemData4=PreferenceUtils.getDataList(DeviceListActivity.this,"jyxOne",null);
//                    shEntity.setNameVoJYX(itemData3);
//                    shEntity.setNameVoJYXOne(itemData4);
//                }
//            }
//        }
//
//        if (!hasJY){
//            shEntity.setNameVoJYX(null);
//            shEntity.setNameVoJYXOne(null);
//        }

        if (shEntity.getZZType().equals("保护")) {
            currentPage = 0;
            bhpzList = util.getBHPZ(currentPage, shEntity);
            totalCount = util.getTotalSb(BHPZ.class, shEntity);
            if (bhpzList == null || bhpzList.size() == 0) {
                ToastUtils.showToast(DeviceListActivity.this, "无搜索结果");
            }
            adapter.setDatas(bhpzList);
        } else {
            currentPage = 0;
            fzbhsbList = util.getFZBHSB(currentPage, shEntity);
            totalCount = util.getTotalSb(FZBHSB.class, shEntity);
            if (fzbhsbList == null || fzbhsbList.size() == 0) {
                ToastUtils.showToast(DeviceListActivity.this, "无搜索结果");
            }
            adapter.setDatas(1, fzbhsbList);
        }
    }

    //获取筛选条件原始数据
    public void filterDatas() {
        list5 = DataHolder.getInstance().getFilter();
        String[] nameFilter = new String[]{"数据状态", "电压等级", "装置类别", "调度单位", "排序", "装置校验"};
        itemData = new ArrayList<SaleAttributeNameVo>();
        //itemData.clear();
        for (int i = 0; i < list5.size(); i++) {
            SaleAttributeNameVo saleName = new SaleAttributeNameVo();
            saleName.setName(nameFilter[i]); //name,即属性名------------------------
            List<SaleAttributeVo> list = new ArrayList<SaleAttributeVo>();
            List<String> list6 = new ArrayList<>();
            list6 = list5.get(i);
            for (int j = 0; j < list6.size(); j++) {
                SaleAttributeVo vo = new SaleAttributeVo();
                vo.setValue(list6.get(j));
                list.add(vo);
            }
            saleName.setSaleVo(list);   //设置saleVo,即属性值------------------
            // 是否展开
            saleName.setNameChecked(false);
            if (nameFilter[i].equals("排序")) {
                saleName.getSaleVo().get(3).setChecked(true);//把默认排序设置为true选中状态
            }
            itemData.add(saleName);     //将所有的属性和属性值放入itemData
        }

        //一次设备名称
        SaleAttributeNameVo saleName1 = new SaleAttributeNameVo();
        saleName1.setName("一次设备名称");
        List<SaleAttributeVo> list_ycsbmc = new ArrayList<SaleAttributeVo>();
        SaleAttributeVo vo = new SaleAttributeVo();
        vo.setValue("");
        list_ycsbmc.add(vo);
        saleName1.setSaleVo(list_ycsbmc);
        itemData.add(saleName1);
        //制造厂家
        SaleAttributeNameVo saleName2 = new SaleAttributeNameVo();
        saleName2.setName("制造厂家");
        List<SaleAttributeVo> list_zzcj = new ArrayList<SaleAttributeVo>();
        SaleAttributeVo vo2 = new SaleAttributeVo();
        vo.setValue("");
        list_zzcj.add(vo);
        saleName2.setSaleVo(list_zzcj);
        itemData.add(saleName2);
        //型号
        SaleAttributeNameVo saleName3 = new SaleAttributeNameVo();
        saleName3.setName("型号");
        List<SaleAttributeVo> list_xh = new ArrayList<SaleAttributeVo>();
        SaleAttributeVo vo3 = new SaleAttributeVo();
        vo.setValue("");
        list_xh.add(vo);
        saleName3.setSaleVo(list_xh);
        itemData.add(saleName3);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
        }
    };

    public void initview() {
        //指南zz_zhinan,sousuo_tx,shaixuan_tx,zz_tx,add_devices_tx,sousuo_tx1,shaixuan_tx1,zz_tx1,add_devices_tx1, add_devices
        zz_zhinan = (RelativeLayout) findViewById(R.id.zz_zhinan);
        sousuo_tx = (TextView) findViewById(R.id.sousuo_tx);
        zz_tx = (TextView) findViewById(R.id.zz_tx);
        add_devices_tx = (TextView) findViewById(R.id.add_devices_tx);
        shaixuan_tx = (TextView) findViewById(R.id.shaixuan_tx);
        sousuo_tx1 = (TextView) findViewById(R.id.sousuo_tx1);
        shaixuan_tx1 = (TextView) findViewById(R.id.shaixuan_tx1);
        zz_tx1 = (TextView) findViewById(R.id.zz_tx1);
        add_devices_tx1 = (TextView) findViewById(R.id.add_devices_tx1);
        add_devices = (ImageView) findViewById(R.id.add_devices);

        //指南
        if (PreferenceUtils.getPrefBoolean(DeviceListActivity.this, "zhinan_zz", false)) {
            zz_zhinan.setVisibility(View.GONE);
        } else {
            //PreferenceUtils.setPrefBoolean(DeviceListActivity.this, "zhinan_zz", true);
        }

        util = new DaoUtil(this);
        main = findViewById(R.id.main);
        filterDatas();
        btn_filter = (Button) findViewById(R.id.btn_filter);
        //筛选按钮的点击
        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow = new FilterPopupWindow(DeviceListActivity.this, itemData);
                popupWindow.showFilterPopup(main);
            }
        });

        //-------------以上是筛选-----------------------
        progressDialog = new LoadingDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        add_device = (ImageView) findViewById(R.id.add_device);
        tishi = (TextView) findViewById(R.id.tishi);
        btn_bh = (Button) findViewById(R.id.btn_bh);
        btn_fz = (Button) findViewById(R.id.btn_fz);
        tv_deep = (TextView) findViewById(R.id.tv_deep);
        app_search_edit = (EditText) findViewById(R.id.app_search_edit);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        tv_search_type = (TextView) findViewById(R.id.tv_search_type);
        app_toolbar_center = (TextView) findViewById(R.id.app_toolbar_center);
        app_toolbar_center.setText(PreferenceUtils.getPrefString(this, "czmc", null));  //顶部中间控件赋值变电站名称
        app_toolbar_right = (ImageView) findViewById(R.id.app_toolbar_sao);
        app_toolbar_right.setVisibility(View.VISIBLE);
        app_toolbar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(DeviceListActivity.this, CaptureActivity.class), 100);
            }
        });


        app_toolbar_left = (ImageView) findViewById(R.id.app_toolbar_left);
        app_toolbar_sao = (ImageView) findViewById(R.id.app_toolbar_sao);

        //返回按钮
        app_toolbar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeviceListActivity.this.finish();
            }
        });

        //结束按钮X
        app_toolbar_sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeviceListActivity.this, MainActivitys.class));
                sendBroadcast(new Intent("cn.sgg.finishActivity"));
                DeviceListActivity.this.finish();
            }
        });

        //保护装置
        btn_bh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shEntity.getZZType().equals("辅助")) {
                    shEntity.setZZType("保护");
//                    if (!shEntity.getBhlb().equals("")) {
//                        shEntity.setBhlb("");
//                        tv_bhlb.setText("保护类别");
//                        tv_bhlb.setSelected(false);
//                    }
                    currentPage = 0;
                    bhpzList = util.getBHPZ(currentPage, shEntity);
                    totalCount = util.getTotalSb(BHPZ.class, shEntity);
                    adapter.setDatas(bhpzList);
                }
                btn_bh.setBackgroundColor(getResources().getColor(R.color.color_main));
                btn_bh.setTextColor(getResources().getColor(R.color.white));
                btn_fz.setBackgroundColor(getResources().getColor(R.color.color_white));
                btn_fz.setTextColor(getResources().getColor(R.color.commonly_text_color9));
            }
        });

        //辅助装置
        btn_fz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shEntity.getZZType().equals("保护")) {
                    shEntity.setZZType("辅助");
//                    if (!shEntity.getBhlb().equals("")) {
//                        shEntity.setBhlb("");
//                        tv_bhlb.setText("保护类别");
//                        tv_bhlb.setSelected(false);
//                    }
                    currentPage = 0;
                    fzbhsbList = util.getFZBHSB(currentPage, shEntity);
                    totalCount = util.getTotalSb(FZBHSB.class, shEntity);
                    adapter.setDatas(1, fzbhsbList);
                }
                btn_fz.setBackgroundColor(getResources().getColor(R.color.color_main));
                btn_fz.setTextColor(getResources().getColor(R.color.color_white));
                btn_bh.setBackgroundColor(getResources().getColor(R.color.color_white));
                btn_bh.setTextColor(getResources().getColor(R.color.commonly_text_color9));
            }
        });

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
                    //if (isSearch) {
                    shEntity.setZZName("");
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
                    //isSearch = false;
                    //}
                } else { //输入框内容不为空
                    String searchStr = app_search_edit.getText().toString().trim();
                    shEntity.setZZName(searchStr);
                    if (shEntity.getZZType().equals("保护")) {
                        currentPage = 0;
                        bhpzList = util.getBHPZ(currentPage, shEntity);
                        totalCount = util.getTotalSb(BHPZ.class, shEntity);
                        if (bhpzList == null || bhpzList.size() == 0) {
                            ToastUtils.showToast(DeviceListActivity.this, "无搜索结果");
                        }
                        adapter.setDatas(bhpzList);
                    } else {
                        currentPage = 0;
                        fzbhsbList = util.getFZBHSB(currentPage, shEntity);
                        totalCount = util.getTotalSb(FZBHSB.class, shEntity);
                        if (fzbhsbList == null || fzbhsbList.size() == 0) {
                            ToastUtils.showToast(DeviceListActivity.this, "无搜索结果");
                        }
                        adapter.setDatas(1, fzbhsbList);
                    }
                    //isSearch = true;
                }
            }
        });

        add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("正在操作,请稍候！");
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        Object o = null;
                        if (type.equals("zz1") || type.equals("zz2") || type.equals("bdz")) {
                            intent.setClass(DeviceListActivity.this, CaptureActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("request", "zzlbxz");
                            bundle.putString("zztype", shEntity.getZZType());
                            intent.putExtras(bundle);
                            startActivity(intent);
                            return;
                        } else if (isSwid) {
                            intent.setClass(DeviceListActivity.this, DemoActivity.class);
                            intent.putExtra("SYTYPE", "xz");
                            intent.putExtra("sbsbdm", type_sbsbdm);
                            intent.putExtra("saoma", "");
                            intent.putExtra("isSwid", isSwid);
                            if (shEntity.getZZType().equals("保护")) {//保护设备
                                intent.putExtra("ZZTYPE", "BHSB");
                                intent.putExtra("STATE", "C");
                            } else { //辅助设备
                                intent.putExtra("ZZTYPE", "FZSB");
                                intent.putExtra("STATE", "C");
                            }
                            handler.sendEmptyMessage(0);
                            startActivity(intent);
                            return;
                        } else {
                            o = util.getObjectFromCCXX(type_sbsbdm);
                        }

                        if (o == null) {//未关联到出厂库，跳纯新增的页面
                            intent.setClass(DeviceListActivity.this, DemoActivity.class);
                            intent.putExtra("SYTYPE", "xz");
                            intent.putExtra("sbsbdm", type_sbsbdm);
                            intent.putExtra("saoma", "");
                            intent.putExtra("isSwid", isSwid);
                            if (shEntity.getZZType().equals("保护")) {//保护设备
                                intent.putExtra("ZZTYPE", "BHSB");
                                intent.putExtra("STATE", "C");
                            } else { //辅助设备
                                intent.putExtra("ZZTYPE", "FZSB");
                                intent.putExtra("STATE", "C");
                            }
                            handler.sendEmptyMessage(0);
                            startActivity(intent);
                        } else {//关联到出厂库,将出厂信息带到详情
                            List<BKXX> bkxxList = util.getCCXXBK(type_sbsbdm);
                            List<Object> rjbbList;
                            ICDXX icdxx = util.getICDXXFromCCXX(type_sbsbdm);
                            intent.putExtra("SYTYPE", "xz");
                            intent.putExtra("sbsbdm", type_sbsbdm);
                            if (icdxx != null) {
                                intent.putExtra("ICDXX", icdxx);
                            }
                            if (o instanceof BHPZ) {
                                boolean isSix = ((BHPZ) o).getSfltysb().equals("是");
                                boolean is2013 = ((BHPZ) o).getLtybzbb().equals("2013版");
                                rjbbList = util.getCCXXRJBB(isSix, is2013, type_sbsbdm);

                                intent.setClass(DeviceListActivity.this, DemoActivity.class);
                                intent.putExtra("ZZTYPE", "BHSB");
                                intent.putExtra("BHSB", (BHPZ) o);
                                if (bkxxList != null && bkxxList.size() > 0) {
                                    intent.putExtra("BKXX", (Serializable) bkxxList);
                                }
                                if (rjbbList != null && rjbbList.size() > 0) {
                                    intent.putExtra("BHXHRJBB", (Serializable) rjbbList);
                                }
                                intent.putExtra("saoma", "");
                                intent.putExtra("isSwid", isSwid);
                                intent.putExtra("STATE", "C");
                                handler.sendEmptyMessage(0);
                                startActivity(intent);
                            } else if (o instanceof FZBHSB) {
                                boolean isSix = ((FZBHSB) o).getSfltysb().equals("是");
                                boolean is2013 = ((FZBHSB) o).getLtybzbb().equals("2013版");
                                rjbbList = util.getCCXXRJBB(isSix, is2013, type_sbsbdm);
                                intent.setClass(DeviceListActivity.this, DemoActivity.class);
                                intent.putExtra("ZZTYPE", "FZSB");
                                intent.putExtra("FZSB", (FZBHSB) o);
                                if (bkxxList != null && bkxxList.size() > 0) {
                                    intent.putExtra("BKXX", (Serializable) bkxxList);
                                }
                                if (rjbbList != null && rjbbList.size() > 0) {
                                    intent.putExtra("BHXHRJBB", (Serializable) rjbbList);
                                }
                                intent.putExtra("saoma", "");
                                intent.putExtra("isSwid", isSwid);
                                intent.putExtra("STATE", "C");
                                handler.sendEmptyMessage(0);
                                startActivity(intent);
                            }
                        }

                    }
                }).start();

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("");
        setSupportActionBar(toolbar);
//        tv_px = (TextView) getActivity().findViewById(R.id.tv_px);
//        tv_bhlb = (TextView) getActivity().findViewById(R.id.tv_bhlb);
//        tv_clzt = (TextView) getActivity().findViewById(R.id.tv_clzt);
//        tv_sx = (TextView) getActivity().findViewById(R.id.tv_sx);
//        layout = new LabelLayout(this, mHandler, true);
        footerLayout = LayoutInflater.from(this).inflate(R.layout.listview_footer, null);
        load_progress_bar = (ProgressBar) footerLayout.findViewById(R.id.load_progress_bar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this)); //左滑
        adapter = new MyHeadMainRecyclerAdapters(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

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
                                ToastUtils.showToast(DeviceListActivity.this, "没有更多数据了...");
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
                                ToastUtils.showToast(DeviceListActivity.this, "没有更多数据了...");
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

    public void initData() {
        type_sbsbdm = null;
        shEntity = new SearchEntity();
        shEntity.setZZType("保护");
        btn_bh.setBackgroundColor(getResources().getColor(R.color.color_main));
        btn_bh.setTextColor(getResources().getColor(R.color.white));
        btn_fz.setBackgroundColor(getResources().getColor(R.color.color_white));
        btn_fz.setTextColor(getResources().getColor(R.color.commonly_text_color9));
        //util = new DaoUtil(this);
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

    public void initEvevt() {

        //指南 zz_zhinan,sousuo_tx,shaixuan_tx,zz_tx,add_devices_tx,sousuo_tx1,shaixuan_tx1,zz_tx1,add_devices_tx1, add_devices
        zz_zhinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtils.showToast(StationListActivitys.this,"哈哈哈哈好");
            }
        });

        sousuo_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_all += 1;
                if (flag_all == 4) {
                    sousuo_tx.setVisibility(View.GONE);
                    sousuo_tx1.setVisibility(View.GONE);
                    zz_zhinan.setVisibility(View.GONE);
                    PreferenceUtils.setPrefBoolean(DeviceListActivity.this, "zhinan_zz", true);
                } else {
                    sousuo_tx.setVisibility(View.GONE);
                    sousuo_tx1.setVisibility(View.GONE);
                }

            }
        });

        shaixuan_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_all += 1;
                if (flag_all == 4) {
                    shaixuan_tx.setVisibility(View.GONE);
                    shaixuan_tx1.setVisibility(View.GONE);
                    zz_zhinan.setVisibility(View.GONE);
                    PreferenceUtils.setPrefBoolean(DeviceListActivity.this, "zhinan_zz", true);
                } else {
                    shaixuan_tx.setVisibility(View.GONE);
                    shaixuan_tx1.setVisibility(View.GONE);
                }

            }
        });

        zz_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_all += 1;
                if (flag_all == 4) {
                    zz_tx.setVisibility(View.GONE);
                    zz_tx1.setVisibility(View.GONE);
                    zz_zhinan.setVisibility(View.GONE);
                    PreferenceUtils.setPrefBoolean(DeviceListActivity.this, "zhinan_zz", true);
                } else {
                    zz_tx.setVisibility(View.GONE);
                    zz_tx1.setVisibility(View.GONE);
                }

            }
        });

        add_devices_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_all += 1;
                if (flag_all == 4) {
                    add_devices_tx.setVisibility(View.GONE);
                    add_devices_tx1.setVisibility(View.GONE);
                    add_devices.setVisibility(View.GONE);
                    zz_zhinan.setVisibility(View.GONE);
                    PreferenceUtils.setPrefBoolean(DeviceListActivity.this, "zhinan_zz", true);
                } else {
                    add_devices_tx.setVisibility(View.GONE);
                    add_devices_tx1.setVisibility(View.GONE);
                    add_devices.setVisibility(View.GONE);
                }

            }
        });


        adapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("SYTYPE", type_details);
                intent.putExtra("sbsbdm", type_sbsbdm);
                if (shEntity.getZZType().equals("保护")) {//保护设备
                    intent.setClass(DeviceListActivity.this, DemoActivity.class);
                    intent.putExtra("ZZTYPE", "BHSB");
                    intent.putExtra("BHSB", (Serializable) bhpzList.get(position));
                } else {//辅助设备
                    intent.setClass(DeviceListActivity.this, DemoActivity.class);
                    intent.putExtra("ZZTYPE", "FZSB");
                    intent.putExtra("FZSB", (Serializable) fzbhsbList.get(position));
                }
                if (type_details.equals("llbj")) {
                    intent.putExtra("saoma", "");
                    intent.putExtra("isSwid", isSwid);
                }
                intent.putExtra("STATE", "M");
                if ((type.equals("zz1") || type.equals("zz2") || type.equals("bdz"))) {
                    intent.putExtra("zzlbxz", "bdz");
                }
                startActivity(intent);
                progressDialog.setTitle("加载中");
                progressDialog.show();
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
                ToastUtils.showToast(DeviceListActivity.this, "加载完成......");
            }
        }, 1000);
    }

    public CustomDialog type_dialog;
    private List data;
    String sbsbdm;

    //新增设备的dialog
    public void showTypeDialog() {
        if (type_dialog != null && type_dialog.isShowing()) {
            return;
        } else {
            View view = LayoutInflater.from(DeviceListActivity.this).inflate(R.layout.fragment_one_device_dialog_item, null);
            type_dialog = new CustomDialog(DeviceListActivity.this, R.style.dialog_alert_style, 0);
            // 根据id在布局中找到控件对象
            TextView tv_dialog_protect = (TextView) view.findViewById(R.id.fragment_device_details_dialog_protect);
            TextView tv_dialog_auxiliary = (TextView) view.findViewById(R.id.fragment_device_details__dialog_auxiliary);
            data = new ArrayList();

            //保护设备
            tv_dialog_protect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ToastUtils.showToast(MainActivity.this, "当前选择保护设备");
                    Intent intent = new Intent(DeviceListActivity.this, DeviceDetailsActivity.class);
                    intent.putExtra("ZZTYPE", "BHSB");
                    intent.putExtra("STATE", "C");
                    startActivity(intent);
                    type_dialog.dismiss();
                }
            });
            //辅助设备
            tv_dialog_auxiliary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ToastUtils.showToast(MainActivity.this, "当前选择辅助设备");
                    Intent intent = new Intent(DeviceListActivity.this, DeviceDetailsActivity.class);
                    intent.putExtra("ZZTYPE", "FZSB");
                    intent.putExtra("STATE", "C");
                    startActivity(intent);
                    type_dialog.dismiss();

                }
            });
            type_dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            type_dialog.setCanceledOnTouchOutside(true);
            type_dialog.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        unregisterReceiver(receivers);
    }
}



