package cn.com.sgcc.dev.view.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zxing.activity.CaptureActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.CommonAdapter;
import cn.com.sgcc.dev.adapter.MyTypeAdapter;
import cn.com.sgcc.dev.adapter.ViewHolder;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.LoadingDialog;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.AKXTGX;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.BHSBXHB;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.model2.BKXX;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.FZBHSBXHBBGX;
import cn.com.sgcc.dev.model2.LJQXX;
import cn.com.sgcc.dev.model2.LTYSBXH;
import cn.com.sgcc.dev.model2.RZGL;
import cn.com.sgcc.dev.model2.TDXX;
import cn.com.sgcc.dev.model2.WDGL;
import cn.com.sgcc.dev.model2.print.Print;
import cn.com.sgcc.dev.model2.vo.JiaoYanData;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Ak;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Base;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Inset;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Ljq;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_More_Mk;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Road_Ak;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Run;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details1;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details10;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details11;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details2;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details3;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details4;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details5;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details6;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details7;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details8;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details9;
import cn.com.sgcc.dev.view.viewinterface.ViewAddListener;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/1
 */

public class DemoActivity extends BaseActivity implements ViewAddListener, View.OnClickListener {

    private TextView tv_czmc, tv_czzgdydj, tv_swid;
    private ImageView iv_details_more, app_toolbar_left, app_toolbar_sao1, iv_bgcz;
    public Button btn_details_left, btn_details_right, btn_details_undo;
    private LinearLayout ll_bottom_opt;
    private ListView lv_type;
    private MyTypeAdapter adapter;
    private List<String> data = new ArrayList<>();
    private List<String> data_check = new ArrayList<>();
    private Fragment details1, details2, details3, details4, details5,
            details6, details7, details8, details9, details10, details11;
    private Fragment fragment_Type_Base, fragment_Type_Inset, fragment_Type_Run,
            fragment_Type_Road_Ak, fragment_Type_Ak,
            fragment_Type_Ljq, fragment_Type_mk;
    private FragmentManager fm;

    public boolean BHorFZ = true, Similar = false;//保护为true，辅助为false
    public boolean hasSaoma;//是否来自扫码
    public boolean isSwid;//是否为实物ID
    public boolean isUndo;//还原装置，且存装置名称重复
    public String state;//当前状态  C-新增，M-编辑
    private boolean isEdit;//true-编辑，false-浏览
    public boolean isfirstChange;//辅助标志第一次类别未初始化时是否切换了装置类别

    private String currentFl;
    private String currentType;
    public FZBHSB fzbhsb;
    public BHPZ bhsb;
    public static DemoActivity instance = null;
    public LoadingDialog loadingDialog;
    private IDaoUtil util;
    public boolean isC; //标志是否为新增  true-新增  false-修改
    public boolean isCancel; //标志是否点击了取消 true-是  false-否
    public Long coreId;  //新增时记录保存后的id
    public String faildType;  //记录保存失败的页签
    public CZCS czcs;
    public RZGL rzgl;

    //需要补码
    public boolean setsbm;

    private TextView xuanxk_tx, xuanxk_tx1, hedui_tx, hedui_tx1, bianji_tx, bianji_tx1, save_tx, save_tx1, qx_tx, qx_tx1;
    private RelativeLayout details_zhinan;
    int flag_all = 0;
    public List<SaleAttributeVo> saleVo;

    public JiaoYanData jiaoYanData;//校验数据


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    isEdit = false;
                    btn_details_left.setText("编辑");
                    btn_details_right.setText("核对无误");
                    Intent intentSave = new Intent("com.lzl.details");
                    intentSave.putExtra("SEE", "");
                    sendBroadcast(intentSave);
                    iv_bgcz.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                    break;
                case 1:
                    break;
                case -1:
                    if (BHorFZ) {
                        showBHFragment(fm, faildType, currentType);
                        adapter.setSelect(data.indexOf(faildType));
                    } else {
                        showFragment(fm, faildType, currentType);
                        adapter.setSelect(data.indexOf(faildType));
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        util = new DaoUtil(this);
        init();
        initView();
        initData();
        instance = this;

        //获取校验项配置
        String json = PreferenceUtils.getPrefString(this, "xqJY", null);
        jiaoYanData = new Gson().fromJson(json, JiaoYanData.class);

        nameVoJYX = PreferenceUtils.getDataList(DemoActivity.this, "jyx", null);
    }

    /**
     * 初始化变量
     */
    private void init() {
        fzbhsb = null;
        bhsb = null;
        coreId = null;
        BHorFZ = true;
        isfirstChange = false;
        isEdit = false;
        isC = false;
        currentFl = null;
        currentType = null;
        hasSaoma = false;
        isSwid = false;
        state = null;
        czcs = null;
        Similar = false;
    }

    private void initView() {
        //指南 details_zhinan,xuanxk_tx,xuanxk_tx1,hedui_tx,hedui_tx1, bianji_tx,bianji_tx1,save_tx,save_tx1,qx_tx,qx_tx1
        details_zhinan = (RelativeLayout) findViewById(R.id.details_zhinan);
        xuanxk_tx = (TextView) findViewById(R.id.xuanxk_tx);
        xuanxk_tx1 = (TextView) findViewById(R.id.xuanxk_tx1);
        hedui_tx = (TextView) findViewById(R.id.hedui_tx);
        hedui_tx1 = (TextView) findViewById(R.id.hedui_tx1);
        bianji_tx = (TextView) findViewById(R.id.bianji_tx);
        bianji_tx1 = (TextView) findViewById(R.id.bianji_tx1);
        save_tx = (TextView) findViewById(R.id.save_tx);
        save_tx1 = (TextView) findViewById(R.id.save_tx1);
        qx_tx = (TextView) findViewById(R.id.qx_tx);
        qx_tx1 = (TextView) findViewById(R.id.qx_tx1);
        iv_bgcz = (ImageView) findViewById(R.id.iv_arrow_icon_cz);
        iv_details_more = (ImageView) findViewById(R.id.iv_details_more);

        if (getIntent().hasExtra("SYTYPE")) {
            String type = getIntent().getStringExtra("SYTYPE");
            if (type != null && type.equals("xz")) {
                save_tx.setVisibility(View.VISIBLE);
                save_tx1.setVisibility(View.VISIBLE);
                qx_tx.setVisibility(View.VISIBLE);
                qx_tx1.setVisibility(View.VISIBLE);
                hedui_tx.setVisibility(View.GONE);
                hedui_tx1.setVisibility(View.GONE);
                bianji_tx.setVisibility(View.GONE);
                bianji_tx1.setVisibility(View.GONE);
            } else {
                save_tx.setVisibility(View.GONE);
                save_tx1.setVisibility(View.GONE);
                qx_tx.setVisibility(View.GONE);
                qx_tx1.setVisibility(View.GONE);
                hedui_tx.setVisibility(View.VISIBLE);
                hedui_tx1.setVisibility(View.VISIBLE);
                bianji_tx.setVisibility(View.VISIBLE);
                bianji_tx1.setVisibility(View.VISIBLE);

                //扫码未找到设备
                iv_bgcz.setVisibility(View.VISIBLE);
                iv_bgcz.setVisibility(View.GONE);
            }
        }

        //指南
        if (PreferenceUtils.getPrefBoolean(DemoActivity.this, "zhinan_details", false)) {
            details_zhinan.setVisibility(View.GONE);
        } else {
            //PreferenceUtils.setPrefBoolean(DemoActivity.this, "zhinan_details", true);
        }

        loadingDialog = new LoadingDialog(this);
        loadingDialog.setTitle("正在操作");
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(false);

        lv_type = (ListView) findViewById(R.id.lv_type);
        TextView app_toolbar_center = (TextView) findViewById(R.id.app_toolbar_center);
        app_toolbar_center.setText("装置详情");
        tv_czmc = (TextView) findViewById(R.id.tv_czmc);
        tv_czzgdydj = (TextView) findViewById(R.id.tv_czzgdydj);
        tv_swid = (TextView) findViewById(R.id.tv_swid);
        btn_details_left = (Button) findViewById(R.id.btn_details_left);
        btn_details_right = (Button) findViewById(R.id.btn_details_right);
        btn_details_undo = (Button) findViewById(R.id.btn_details_undo);
        app_toolbar_left = (ImageView) findViewById(R.id.app_toolbar_left);
        app_toolbar_sao1 = (ImageView) findViewById(R.id.app_toolbar_sao1);
        ll_bottom_opt = (LinearLayout) findViewById(R.id.ll_bottom_opt);

        btn_details_left.setOnClickListener(this);
        btn_details_right.setOnClickListener(this);
        btn_details_undo.setOnClickListener(this);
        iv_details_more.setOnClickListener(this);
        app_toolbar_left.setOnClickListener(this);
        app_toolbar_sao1.setOnClickListener(this);

        //指南
        details_zhinan.setOnClickListener(this);
        xuanxk_tx.setOnClickListener(this);
        save_tx.setOnClickListener(this);
        qx_tx.setOnClickListener(this);
        hedui_tx.setOnClickListener(this);
        bianji_tx.setOnClickListener(this);

        adapter = new MyTypeAdapter(this);
        lv_type.setAdapter(adapter);

        lv_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelect(position);
                if (BHorFZ) {
                    showBHFragment(fm, data.get(position), currentType);
                } else {
                    showFragment(fm, data.get(position), currentType);
                }
            }
        });

        iv_bgcz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更换厂站,详情变站
                Intent intent = new Intent(DemoActivity.this, StationListActivitys.class);
                intent.putExtra("XQBZ", "xqbz");
                startActivityForResult(intent, 9);
            }
        });
    }

    /**
     * 初始化所有fragment
     *
     * @param fm
     * @param currentType
     */
    private void showFirtFragment(FragmentManager fm, String currentType) {
        FragmentTransaction ft = fm.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putBoolean("isEdit", isEdit);
        if (currentType != null) {
            bundle.putCharSequence("tag", currentType);
        }
        //装置基本信息
        details1 = new Details1(this);
        ft.add(R.id.fl_layout, details1);
        details1.setArguments(bundle);
        //安装及运维信息
        details2 = new Details2();
        ft.add(R.id.fl_layout, details2);
        details2.setArguments(bundle);
        //运行基本信息
        details3 = new Details3();
        ft.add(R.id.fl_layout, details3);
        details3.setArguments(bundle);
        //板卡信息
        details4 = new Details4();
        ft.add(R.id.fl_layout, details4);
        //资产信息
        details7 = new Details7();
        ft.add(R.id.fl_layout, details7);
        details7.setArguments(bundle);
        //附件信息
        details10 = new Details10();
        ft.add(R.id.fl_layout, details10);
        details10.setArguments(bundle);
//        switch (data.get(position)) {
//     switch (data.get(position)) {
//          case "装置基本信息":
//            case "安装及运维信息":
//            case "运行基本信息":
//            case "板卡信息":
//            case "电子标签信息":
//                details5 = new Details5();
//                ft.add(R.id.fl_layout, details5);
//                details5.setArguments(bundle);
//            case "CT信息":
//                details6 = new Details6();
//                ft.add(R.id.fl_layout, details6);
//                details6.setArguments(bundle);
//            case "资产信息":
//            case "ICD文件信息":
//                details8 = new Details8();
//                ft.add(R.id.fl_layout, details8);
//                details8.setArguments(bundle);
//            case "附件信息":
//            case "载波机附加信息":
//            case "MU附加信息":
//            case "智能终端附加信息":
//            case "交换机附加信息":
//                details9 = new Details9();
//                bundle.putBoolean("isChange", isfirstChange);
//                details9.setArguments(bundle);
//                ft.add(R.id.fl_layout, details9);
//                details9.setArguments(bundle);
//                break;
//        }
        ft.commit();
        hideFragment(ft);
        ft.show(details1);
    }

    /**
     * 显示fragment的方法
     *
     * @param fm
     * @param currentType
     */
    private void showFragment(FragmentManager fm, String type, String currentType) {
        FragmentTransaction ft = fm.beginTransaction();

        hideFragment(ft);
        Bundle bundle = new Bundle();
        bundle.putBoolean("isEdit", isEdit);
        if (currentType != null) {
            bundle.putCharSequence("tag", currentType);
        }

        switch (type) {
            case "装置基本信息":
                if (details1 == null) {
                    details1 = new Details1(this);
                    ft.add(R.id.fl_layout, details1);
                    details1.setArguments(bundle);
                } else {
                    ft.show(details1);
                }
                //多模块同步
                if (ismoreMk) {
                    if (Details1.instance != null) {
                        Details1.instance.updataMoreMk();
                    }
                }
                ismoreMk = false;
                break;
            case "安装及运维信息":
                if (details2 == null) {
                    details2 = new Details2();
                    ft.add(R.id.fl_layout, details2);
                    details2.setArguments(bundle);
                } else {
                    ft.show(details2);
                }
                break;
            case "运行基本信息":
                if (details3 == null) {
                    details3 = new Details3();
                    ft.add(R.id.fl_layout, details3);
                    details3.setArguments(bundle);
                } else {
                    ft.show(details3);
                }
                break;
            case "板卡信息":
                if (details4 == null) {
                    details4 = new Details4();
                    ft.add(R.id.fl_layout, details4);
                } else {
                    ft.show(details4);
                }
                break;
            case "电子标签信息":
                if (details5 == null) {
                    details5 = new Details5();
                    ft.add(R.id.fl_layout, details5);
                    details5.setArguments(bundle);
                } else {
                    ft.show(details5);
                }
                break;
            case "CT信息":
                if (details6 == null) {
                    details6 = new Details6();
                    ft.add(R.id.fl_layout, details6);
                    details6.setArguments(bundle);
                } else {
                    ft.show(details6);
                }
                break;
            case "资产信息":
                if (details7 == null) {
                    details7 = new Details7();
                    ft.add(R.id.fl_layout, details7);
                    details7.setArguments(bundle);
                } else {
                    ft.show(details7);
                }
                break;
            case "ICD文件信息":
                if (details8 == null) {
                    details8 = new Details8();
                    details8.setArguments(bundle);
                    ft.add(R.id.fl_layout, details8);
                } else {
                    ft.show(details8);
                }
                break;
            case "附件信息":
                if (details10 == null) {
                    details10 = new Details10();
                    ft.add(R.id.fl_layout, details10);
                    details10.setArguments(bundle);
                } else {
                    ft.show(details10);
                }
                break;
            case "载波机附加信息":
            case "MU附加信息":
            case "智能终端附加信息":
            case "交换机附加信息":
                if (details9 == null) {
                    details9 = new Details9();
                    bundle.putBoolean("isChange", isfirstChange);
                    details9.setArguments(bundle);
                    ft.add(R.id.fl_layout, details9);
                    details9.setArguments(bundle);
                } else {
                    ft.show(details9);
                }
                break;
            case "模块信息":
                if (details11 == null) {
                    details11 = new Details11();
                    details11.setArguments(bundle);
                    ft.add(R.id.fl_layout, details11);
                } else {
                    ft.show(details11);
                }
                break;
        }
        ft.commit();
    }


    /**
     * 初始化所有fragment
     *
     * @param fm
     * @param currentType
     */
    private void showBhFirtFragment(FragmentManager fm, String currentType) {
        FragmentTransaction ft = fm.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putBoolean("isEdit", isEdit);
        if (currentType != null) {
            bundle.putCharSequence("tag", currentType);
        }
        //装置基本信息
        fragment_Type_Base = new Fragment_Type_Base(this);
        ft.add(R.id.fl_layout, fragment_Type_Base);
        fragment_Type_Base.setArguments(bundle);
        //安装及运维信息
        fragment_Type_Inset = new Fragment_Type_Inset();
        ft.add(R.id.fl_layout, fragment_Type_Inset);
        fragment_Type_Inset.setArguments(bundle);
        //运行基本信息
        fragment_Type_Run = new Fragment_Type_Run();
        ft.add(R.id.fl_layout, fragment_Type_Run);
        fragment_Type_Run.setArguments(bundle);
        //板卡信息
        details4 = new Details4();
        ft.add(R.id.fl_layout, details4);
        details4.setArguments(bundle);
        //CT信息
        details6 = new Details6();
        ft.add(R.id.fl_layout, details6);
        details6.setArguments(bundle);
        //资产信息
        details7 = new Details7();
        ft.add(R.id.fl_layout, details7);
        details7.setArguments(bundle);
        //附件信息
        details10 = new Details10();
        ft.add(R.id.fl_layout, details10);
        details10.setArguments(bundle);
        ft.commit();
        hideBHFragment(ft);
        ft.show(fragment_Type_Base);
    }

    public void setChangexh() {
        adapter.setSelect(0);
        if (BHorFZ) {
            showBHFragment(fm, "装置基本信息", currentType);
        } else {
            showFragment(fm, "装置基本信息", currentType);
            adapter.setSelect(data.indexOf("装置基本信息"));
        }
    }

    public Boolean ismoreMk = false;

    public void showMk(int i) {
        if (BHorFZ) {
            if (i == 0) {
                showBHFragment(fm, "装置基本信息", currentType);
                ismoreMk = false;
                if (Fragment_Type_Base.instance != null) {
                    Fragment_Type_Base.instance.updataMoreMk();
                }
            } else if (i == 2) {
                showBHFragment(fm, "模块信息", currentType);
                showBHFragment(fm, "装置基本信息", currentType);
            } else {
                showBHFragment(fm, "模块信息", currentType);
                ismoreMk = true;
                if (Fragment_Type_More_Mk.instance != null) {
                    Fragment_Type_More_Mk.instance.updataMoreMk();
                    Fragment_Type_More_Mk.instance.adapter.notifyDataSetChanged();
                }
            }
        } else {
            if (i == 0) {
                showFragment(fm, "装置基本信息", currentType);
                ismoreMk = false;
                if (Details1.instance != null) {
                    Details1.instance.updataMoreMk();
                }
            } else if (i == 2) {
                showFragment(fm, "模块信息", currentType);
                showFragment(fm, "装置基本信息", currentType);
            } else {
                showFragment(fm, "模块信息", currentType);
                ismoreMk = true;
                if (Details11.instance != null) {
                    Details11.instance.updataMoreMk();
                    Details11.instance.adapter.notifyDataSetChanged();
                }
            }
        }
    }


    //保护装置显示标签
    private void showBHFragment(FragmentManager fm, String type, String currentType) {
        FragmentTransaction ft = fm.beginTransaction();
        hideBHFragment(ft);
        Bundle bundle = new Bundle();
        bundle.putBoolean("isEdit", isEdit);
        if (currentType != null) {
            bundle.putCharSequence("tag", currentType);
        }
        switch (type) {
            case "装置基本信息":
                if (fragment_Type_Base == null) {
                    fragment_Type_Base = new Fragment_Type_Base(this);
                    fragment_Type_Base.setArguments(bundle);
                    ft.add(R.id.fl_layout, fragment_Type_Base);
                } else {
                    ft.show(fragment_Type_Base);
                }
                //默认关闭多模块
                if (ismoreMk) {
                    if (Fragment_Type_Base.instance != null) {
                        Fragment_Type_Base.instance.updataMoreMk();
                    }
                }
                ismoreMk = false;
                break;
            case "安装及运维信息":
                if (fragment_Type_Inset == null) {
                    fragment_Type_Inset = new Fragment_Type_Inset();
                    fragment_Type_Inset.setArguments(bundle);
                    ft.add(R.id.fl_layout, fragment_Type_Inset);
                } else {
                    ft.show(fragment_Type_Inset);
                }
                break;
            case "运行基本信息":
                if (fragment_Type_Run == null) {
                    fragment_Type_Run = new Fragment_Type_Run();
                    fragment_Type_Run.setArguments(bundle);
                    ft.add(R.id.fl_layout, fragment_Type_Run);
                } else {
                    ft.show(fragment_Type_Run);
                }
                break;
            case "通道信息":
                if (fragment_Type_Road_Ak == null) {
                    fragment_Type_Road_Ak = new Fragment_Type_Road_Ak();
                    fragment_Type_Road_Ak.setArguments(bundle);
                    ft.add(R.id.fl_layout, fragment_Type_Road_Ak);
                } else {
                    ft.show(fragment_Type_Road_Ak);
                }
                break;
            case "安控信息":
                if (fragment_Type_Ak == null) {
                    fragment_Type_Ak = new Fragment_Type_Ak();
                    fragment_Type_Ak.setArguments(bundle);
                    ft.add(R.id.fl_layout, fragment_Type_Ak);
                } else {
                    ft.show(fragment_Type_Ak);
                }
                break;
            case "板卡信息":
                if (details4 == null) {
                    details4 = new Details4();
                    details4.setArguments(bundle);
                    ft.add(R.id.fl_layout, details4);
                } else {
                    ft.show(details4);
                }
                break;
            case "ICD文件信息":
                if (details8 == null) {
                    details8 = new Details8();
                    details8.setArguments(bundle);
                    ft.add(R.id.fl_layout, details8);
                } else {
                    ft.show(details8);
                }
                break;
            case "连接器信息":
                if (fragment_Type_Ljq == null) {
                    fragment_Type_Ljq = new Fragment_Type_Ljq();
                    fragment_Type_Ljq.setArguments(bundle);
                    ft.add(R.id.fl_layout, fragment_Type_Ljq);
                } else {
                    ft.show(fragment_Type_Ljq);
                }
                break;
            case "电子标签信息":
                if (details5 == null) {
                    details5 = new Details5();
                    details5.setArguments(bundle);
                    ft.add(R.id.fl_layout, details5);
                } else {
                    ft.show(details5);
                }
                break;
            case "CT信息":
                if (details6 == null) {
                    details6 = new Details6();
                    details6.setArguments(bundle);
                    ft.add(R.id.fl_layout, details6);
                } else {
                    ft.show(details6);
                }
                break;
            case "资产信息":
                if (details7 == null) {
                    details7 = new Details7();
                    details7.setArguments(bundle);
                    ft.add(R.id.fl_layout, details7);
                } else {
                    ft.show(details7);
                }
                break;
            case "附件信息":
                if (details10 == null) {
                    details10 = new Details10();
                    details10.setArguments(bundle);
                    ft.add(R.id.fl_layout, details10);
                } else {
                    ft.show(details10);
                }
                break;
            case "模块信息":
                if (fragment_Type_mk == null) {
                    fragment_Type_mk = new Fragment_Type_More_Mk();
                    fragment_Type_mk.setArguments(bundle);
                    ft.add(R.id.fl_layout, fragment_Type_mk);
                } else {
                    ft.show(fragment_Type_mk);
                }
                break;
        }
        ft.commit();
    }

    /**
     * 显示与隐藏的方法
     *
     * @param ft
     */
    private void hideFragment(FragmentTransaction ft) {
        if (details1 != null) {
            ft.hide(details1);
        }
        if (details2 != null) {
            ft.hide(details2);
        }
        if (details3 != null) {
            ft.hide(details3);
        }
        if (details4 != null) {
            ft.hide(details4);
        }
        if (details5 != null) {
            ft.hide(details5);
        }
        if (details6 != null) {
            ft.hide(details6);
        }
        if (details7 != null) {
            ft.hide(details7);
        }
        if (details8 != null) {
            ft.hide(details8);
        }
        if (details9 != null) {
            ft.hide(details9);
        }
        if (details10 != null) {
            ft.hide(details10);
        }
        if (details11 != null) {
            ft.hide(details11);
        }
    }

    //保护装置隐藏标签
    private void hideBHFragment(FragmentTransaction ft) {
        if (fragment_Type_Base != null) {
            ft.hide(fragment_Type_Base);
        }
        if (fragment_Type_Road_Ak != null) {
            ft.hide(fragment_Type_Road_Ak);
        }
        if (fragment_Type_Ak != null) {
            ft.hide(fragment_Type_Ak);
        }
        if (fragment_Type_Ljq != null) {
            ft.hide(fragment_Type_Ljq);
        }
        if (fragment_Type_Inset != null) {
            ft.hide(fragment_Type_Inset);
        }
        if (fragment_Type_Run != null) {
            ft.hide(fragment_Type_Run);
        }
        if (details4 != null) {
            ft.hide(details4);
        }
        if (details5 != null) {
            ft.hide(details5);
        }
        if (details6 != null) {
            ft.hide(details6);
        }
        if (details7 != null) {
            ft.hide(details7);
        }
        if (details8 != null) {
            ft.hide(details8);
        }
        if (details10 != null) {
            ft.hide(details10);
        }
        if (fragment_Type_mk != null)
            ft.hide(fragment_Type_mk);
    }

    //保护装置隐藏标签,删除
    private void removeBHFragment(FragmentTransaction ft) {
        if (fragment_Type_Base != null) {
            ft.remove(fragment_Type_Base);
        }
        if (fragment_Type_Road_Ak != null) {
            ft.remove(fragment_Type_Road_Ak);
        }
        if (fragment_Type_Ak != null) {
            ft.remove(fragment_Type_Ak);
        }
        if (fragment_Type_Ljq != null) {
            ft.remove(fragment_Type_Ljq);
        }
        if (fragment_Type_Inset != null) {
            ft.remove(fragment_Type_Inset);
        }
        if (fragment_Type_Run != null) {
            ft.remove(fragment_Type_Run);
        }
        if (details4 != null) {
            ft.remove(details4);
        }
        if (details5 != null) {
            ft.remove(details5);
        }
        if (details6 != null) {
            ft.remove(details6);
        }
        if (details7 != null) {
            ft.remove(details7);
        }
        if (details8 != null) {
            ft.remove(details8);
        }
        if (details10 != null) {
            ft.remove(details10);
        }
//        if (details9 != null)
//            ft.hide(details9);
    }

    private void initData() {
        if (getIntent().hasExtra("ZZTYPE")) {
            BHorFZ = getIntent().getStringExtra("ZZTYPE").equals("BHSB");
        }
        if (getIntent().hasExtra("STATE")) {
            state = getIntent().getStringExtra("STATE");
        }
        if (getIntent().hasExtra("saoma")) {
            hasSaoma = true;
            isSwid = getIntent().getBooleanExtra("isSwid", true);
        }

        boolean isLL = false;
//        name=SYTYPE    value= llbj(进浏览可编辑界面)    ll(进浏览不可编辑界面)
        if (getIntent().hasExtra("SYTYPE")) {
            String stype = getIntent().getStringExtra("SYTYPE") + "";
            if (stype.equals("ll") && false) {
                isLL = true;
                btn_details_left.setEnabled(false);
                btn_details_right.setEnabled(false);
//                iv_add_new.setVisibility(View.GONE);
            }
        }

        if (Similar) {
            state = "C";
        }
        if (state.equals("C")) {
            isEdit = true;
//            iv_details_del.setVisibility(View.GONE);
            btn_details_left.setText("取消");
            btn_details_right.setText("保存");
            ll_bottom_opt.setVisibility(View.VISIBLE);
            btn_details_undo.setVisibility(View.GONE);
        } else {
            isEdit = false;
//            iv_details_del.setVisibility(View.VISIBLE);
        }
        String sbm = tv_swid.getText().toString();
        //判断保护设备还是辅助设备
        if (BHorFZ) {
            for (int i = 0; i < Constants.Bhfl.length; i++) {
                data.add(Constants.Bhfl[i]);
            }
            adapter.setDatas(data, data_check);
            //判断状态
            if (bhsb != null) {
                bhsb = null;
            }
            if (Similar && sbm != null && !sbm.equals("")) {
                bhsb = util.getBHPZBySwid(sbm);
            } else {
                bhsb = (BHPZ) getIntent().getSerializableExtra("BHSB");
            }

            if (getIntent().hasExtra("sbsbdm")) {
                //添加同类设备按钮，识别码一致不显示
                if (bhsb != null && bhsb.getSfsbm().equals(getIntent().getStringExtra("sbsbdm"))) {
//                    iv_add_new.setVisibility(View.GONE);
                }
            }
            if (Similar && bhsb != null) {
                //bhsb.setBhmc("");
                bhsb.setSw_id("");
            }
            czcs = util.getCZCSByGLDW();
            tv_czmc.setText("厂站名称：" + czcs.getCzmc());
            tv_czzgdydj.setText("电压等级（kV）：" + czcs.getCzzgdydj() + "");

            if (bhsb != null) {
                if (bhsb.getId() != null) {
                    rzgl = util.getRzxx(bhsb.getId(), "BHPZ");
                }
                if (hasSaoma && state.equals("M")) {
                    if (isSwid) {  //扫描的是实物ID
                        if (bhsb.getSw_id() != null && !bhsb.getSw_id().equals("")) {
                            if (!bhsb.getSw_id().equals(getIntent().getStringExtra("sbsbdm"))) {
                                ToastUtils.showLongToast(this, "扫描的实物ID与原有的实物ID不一致");
//                            iv_details_del.setVisibility(View.VISIBLE);
                                if (Similar) {
                                    btn_details_left.setEnabled(true);
                                    btn_details_right.setEnabled(true);
                                } else {
                                    btn_details_left.setEnabled(false);
                                    btn_details_right.setEnabled(false);
                                }
                            }
                            tv_swid.setText(bhsb.getSw_id());
                        } else {
                            if (getIntent().hasExtra("sbsbdm")) {
                                tv_swid.setText(getIntent().getStringExtra("sbsbdm"));
                            }
                        }
                    } else {//扫描的是设备识别代码
                        if (bhsb.getSfsbm() != null && !bhsb.getSfsbm().equals("")) {
                            if (!bhsb.getSfsbm().equals(getIntent().getStringExtra("sbsbdm"))) {
                                ToastUtils.showLongToast(this, "扫描的设备识别代码与原有的识别代码不一致");
//                            iv_details_del.setVisibility(View.VISIBLE);
                                if (Similar) {
                                    btn_details_left.setEnabled(true);
                                    btn_details_right.setEnabled(true);
                                } else {
                                    btn_details_left.setEnabled(false);
                                    btn_details_right.setEnabled(false);
                                }
                            }
                        } else {
                            if (getIntent().hasExtra("sbsbdm")) {
                                bhsb.setSfsbm(getIntent().getStringExtra("sbsbdm"));
                            }
                        }
                    }
                } else {
                    if (state.equals("C") && Similar) {
                        btn_details_left.setEnabled(true);
                        btn_details_right.setEnabled(true);
                    }
                    if (bhsb.getSw_id() != null) {
                        tv_swid.setText(bhsb.getSw_id());
                    }
                }
            } else {
                if (bhsb == null) {
                    bhsb = new BHPZ();
                }
                if (getIntent().hasExtra("sbsbdm")) {
                    if (isSwid) {
                        tv_swid.setText(getIntent().getStringExtra("sbsbdm"));
                    } else {
                        bhsb.setSfsbm(getIntent().getStringExtra("sbsbdm"));
                    }
                }
            }
//            保护初始化,id问题
            if (bhsb == null) {
                bhsb = new BHPZ();
//                bhsb.setId(util.getInsertId("BHPZ"));
            } else if (bhsb.getId() == null) {
//                bhsb.setId(util.getInsertId("BHPZ"));
            }
//            bhsb.setSfsbm(tv_swid.getText().toString());

            if (state.equals("C")) {  //新增
                bhsb.setEd_tag("C");
                isC = true;
            } else {   //修改
                if (bhsb.getEd_tag() != null && !bhsb.getEd_tag().equals("null")
                        && !bhsb.getEd_tag().equals("") && bhsb.getEd_tag().equals("C")
                        || bhsb.getEd_tag() != null && bhsb.getEd_tag().equals("CH")) {
                    bhsb.setEd_tag("C");
                    isC = false;
                } else if (bhsb.getEd_tag() != null && !bhsb.getEd_tag().equals("null")
                        && bhsb.getEd_tag().equals("D") && !Similar && !isLL) {
                    btn_details_left.setEnabled(false);
                    btn_details_right.setEnabled(false);
//                    iv_details_del.setVisibility(View.GONE);
                    ll_bottom_opt.setVisibility(View.GONE);
                    btn_details_undo.setVisibility(View.VISIBLE);
                } else {
                    bhsb.setEd_tag("M");
                    isC = false;
                }
            }

            fm = getSupportFragmentManager();
            showBhFirtFragment(fm, currentType);
        } else {
            for (int i = 0; i < Constants.xqfl.length; i++) {
                data.add(Constants.xqfl[i]);
            }
            adapter.setDatas(data, data_check);
            if (Similar && sbm != null && !sbm.equals("")) {
                fzbhsb = (FZBHSB) util.getFZSBBySbsbdm(sbm, "", "");
            } else {
                fzbhsb = (FZBHSB) getIntent().getSerializableExtra("FZSB");
            }

            if (getIntent().hasExtra("sbsbdm")) {
                //添加同类设备按钮，识别码一致不显示
                if (fzbhsb != null && fzbhsb.getSfsbm().equals(getIntent().getStringExtra("sbsbdm"))) {
//                    iv_add_new.setVisibility(View.GONE);
                }
            }
            if (Similar && fzbhsb != null) {
                // fzbhsb.setSbmc("");
                fzbhsb.setSw_id("");
            }
            czcs = util.getCZCSByGLDW();
            tv_czmc.setText("厂站名称：" + czcs.getCzmc());
            tv_czzgdydj.setText("电压等级（kV）：" + czcs.getCzzgdydj() + "");
            if (fzbhsb != null) {
                if (fzbhsb.getId() != null) {
                    rzgl = util.getRzxx(fzbhsb.getId(), "FZSB");
                }
                if (hasSaoma && state.equals("M")) {
                    if (isSwid) {  //扫描的是实物ID
                        if (fzbhsb.getSw_id() != null && !fzbhsb.getSw_id().equals("")) {
                            if (!fzbhsb.getSw_id().equals(getIntent().getStringExtra("sbsbdm"))) {
                                ToastUtils.showLongToast(this, "扫描的实物ID与原有的实物ID不一致");
//                            iv_details_del.setVisibility(View.VISIBLE);
                                if (Similar) {
                                    btn_details_left.setEnabled(true);
                                    btn_details_right.setEnabled(true);
                                } else {
                                    btn_details_left.setEnabled(false);
                                    btn_details_right.setEnabled(false);
                                }
                            }
                            tv_swid.setText(fzbhsb.getSw_id());
                        } else {
                            if (getIntent().hasExtra("sbsbdm")) {
                                tv_swid.setText(getIntent().getStringExtra("sbsbdm"));
                            }
                        }
                    } else {//扫描的是设备识别代码
                        if (fzbhsb.getSfsbm() != null && !fzbhsb.getSfsbm().equals("")) {
                            if (!fzbhsb.getSfsbm().equals(getIntent().getStringExtra("sbsbdm"))) {
                                ToastUtils.showLongToast(this, "扫描的设备识别代码与原有的识别代码不一致");
//                            iv_details_del.setVisibility(View.VISIBLE);
                                if (Similar) {
                                    btn_details_left.setEnabled(true);
                                    btn_details_right.setEnabled(true);
                                } else {
                                    btn_details_left.setEnabled(false);
                                    btn_details_right.setEnabled(false);
                                }
                            }
                        } else {
                            if (getIntent().hasExtra("sbsbdm")) {
                                fzbhsb.setSfsbm(getIntent().getStringExtra("sbsbdm"));
                            }
                        }
                    }
                } else {
                    if (state.equals("C") && Similar) {
                        btn_details_left.setEnabled(true);
                        btn_details_right.setEnabled(true);
                    }
                    if (fzbhsb.getSw_id() != null) {
                        tv_swid.setText(fzbhsb.getSw_id());
                    }
                }

                if (fzbhsb.getEd_tag() != null && !fzbhsb.getEd_tag().equals("null")
                        && fzbhsb.getEd_tag().equals("D") && !Similar && !isLL) {
                    btn_details_left.setEnabled(false);
                    btn_details_right.setEnabled(false);
//                    iv_details_del.setVisibility(View.GONE);
                    ll_bottom_opt.setVisibility(View.GONE);
                    btn_details_undo.setVisibility(View.VISIBLE);
                }
            } else {
                if (getIntent().hasExtra("sbsbdm")) {
                    if (isSwid) {
                        tv_swid.setText(getIntent().getStringExtra("sbsbdm"));
                    }
                }
            }
            fm = getSupportFragmentManager();
            showFirtFragment(fm, currentType);
        }

        //补码时需要显示补码按钮
        String sbsbdm = tv_swid.getText().toString() + "";
        if (sbsbdm.equals("")) {
            setsbm = true;
        }
        if (BHorFZ) {
            List<SaleAttributeNameVo> itemData3 = PreferenceUtils.getDataList(this, "jyx", "");
            itemData3.get(0).getSaleVo();
            //模拟数据
            saleVo = new ArrayList<>();
            saleVo.addAll(itemData3.get(0).getSaleVo());
        }
    }


    @Override
    public void onChangeListener(String xqfl, String type) {
        if (type.equals("DEL")) {
            if (xqfl.equals("ZZLB")) {
                if (currentFl == null) {
                    return;
                }
                data.remove(currentFl);
            } else {
                data.remove(xqfl);
            }
            currentType = "";
            adapter.setDatas(data, data_check);
        } else if (type.equals("ADD")) { //ICD信息
            if (data.contains(xqfl)) {
                return;
            }
            data.add(data.indexOf("板卡信息") + 1, xqfl);
            adapter.setDatas(data, data_check);
            if (BHorFZ) {
                String bhtype = currentType;
                showBHFragment(fm, xqfl, currentType);
                showBHFragment(fm, "装置基本信息", currentType);
            } else {
                if (details8 == null) {
                    showFragment(fm, "ICD文件信息", currentType);
                    showFragment(fm, "装置基本信息", currentType);
                }
            }
        } else {
            if (currentType != null) {
                if (currentType.equals(type)) {
                    return;
                } else {
                    if (details9 != null) {
                        ((Details9) details9).initCurrentState(type);
                    }
                    data.remove(currentFl);
                }
            }
            currentType = type;
            currentFl = xqfl;
            data.add(data.indexOf("板卡信息") + 1, xqfl);
            adapter.setDatas(data, data_check);
            if (details9 == null) {
                showFragment(fm, currentFl, currentType);
                showFragment(fm, "装置基本信息", currentType);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_toolbar_left:
                finish();
                break;
            case R.id.app_toolbar_sao1:
                sendBroadcast(new Intent("cn.sgg.finishActivity"));
                startActivity(new Intent(this, MainActivitys.class));
                finish();
                break;
            case R.id.btn_details_undo://点击还原按钮
                //先去查设备名称是否在库里存在，此时不应包含已删除和已退运的设备
                if (BHorFZ) {
                    checkHasSbmc(bhsb.getBhmc(), bhsb.getId());
                } else {
                    checkHasSbmc(fzbhsb.getSbmc(), fzbhsb.getId());
                }
                break;
            case R.id.btn_details_left:  //编辑
                final Intent intent = new Intent("com.lzl.details");
                if (!isEdit) {//编辑
                    isEdit = true;
                    btn_details_left.setText("取消");
                    btn_details_right.setText("保存");
                    intent.putExtra("EDIT", "");
                    iv_bgcz.setVisibility(View.VISIBLE);
                    sendBroadcast(intent);
                } else {//浏览
                    if (state.equals("C")) { //新增点击取消
                        AppUtils.showDialog(this, "是否放弃新增？", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //cancel
                                PopupWindow pop = (PopupWindow) v.getTag();
                                pop.dismiss();
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //ensure
                                PopupWindow pop = (PopupWindow) v.getTag();
                                pop.dismiss();
                                finish();
                            }
                        });
                    } else if (Similar) {
                        AppUtils.showDialog(this, "是否放弃新增？", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //cancel
                                PopupWindow pop = (PopupWindow) v.getTag();
                                pop.dismiss();
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //ensure
                                PopupWindow pop = (PopupWindow) v.getTag();
                                pop.dismiss();
                                finish();
                                Similar = false;
                                initData();
//                                iv_add_new.setVisibility(View.VISIBLE);
                            }
                        });
                    } else if (isUndo) {
                        AppUtils.showDialog(this, "是否放弃还原？", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //cancel
                                PopupWindow pop = (PopupWindow) v.getTag();
                                pop.dismiss();
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //ensure
                                PopupWindow pop = (PopupWindow) v.getTag();
                                pop.dismiss();
                                finish();
                            }
                        });
                    } else {
                        AppUtils.showDialog(this, "是否放弃修改？", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //cancel
                                PopupWindow pop = (PopupWindow) v.getTag();
                                pop.dismiss();
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //ensure
                                isEdit = false;
                                isCancel = true;
                                isfirstChange = false;
                                btn_details_left.setText("编辑");
                                btn_details_right.setText("核对无误");
                                intent.putExtra("SEE", "");
                                iv_bgcz.setVisibility(View.GONE);
                                sendBroadcast(intent);
                                PopupWindow pop = (PopupWindow) v.getTag();
                                pop.dismiss();
                                if (BHorFZ) {
                                    fm = getSupportFragmentManager();
                                    FragmentTransaction ft = fm.beginTransaction();
                                    removeBHFragment(ft);
                                    adapter.setSelect(0);
                                    data.clear();
                                    initData();
                                }

                                if (setsbm) {
                                    tv_swid.setText("");
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.btn_details_right: //核对无误 和 保存按钮
                isCancel = false;
                if (btn_details_right.getText().toString().equals("核对无误")) {//核对无误
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 0.5f;
                    getWindow().setAttributes(lp);
                    showDialog(false, true);
                } else {//保存
                    if (BHorFZ) {
                        Fragment_Type_Base.instance.onScroll(60);
                        if (!Fragment_Type_Base.instance.isYcsbmcLikeZzmc()) {
                            WindowManager.LayoutParams lp = getWindow().getAttributes();
                            lp.alpha = 0.5f;
                            getWindow().setAttributes(lp);
                            showDialog(false, false);
                            return;
                        }
                    } else {
                        Details1.instance.onScroll(60);
                        if (!Details1.instance.isYcsbmcLikeZzmc()) {
                            WindowManager.LayoutParams lp = getWindow().getAttributes();
                            lp.alpha = 0.5f;
                            getWindow().setAttributes(lp);
                            showDialog(false, false);
                            return;
                        }
                    }

                    loadingDialog.setTitle("正在操作");
                    loadingDialog.show();
                    new Thread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    if (BHorFZ) { //保护
                                        if (savebhsb(false)) {
                                            jybhsb(bhsb);
                                            handler.sendEmptyMessage(0);
                                        } else {
                                            handler.sendEmptyMessage(-1);
                                        }
                                    } else {
                                        if (saveFzbhsb(false)) {
                                            jyfzbhsb(fzbhsb);
                                            handler.sendEmptyMessage(0);
                                        } else {
                                            handler.sendEmptyMessage(-1);
                                        }
                                    }
                                }
                            }).start();
                }
                break;
            case R.id.details_zhinan:
                break;
            case R.id.xuanxk_tx:
                flag_all += 1;
                if (flag_all == 3) {
                    xuanxk_tx.setVisibility(View.GONE);
                    xuanxk_tx1.setVisibility(View.GONE);
                    details_zhinan.setVisibility(View.GONE);
                    PreferenceUtils.setPrefBoolean(DemoActivity.this, "zhinan_details", true);
                } else {
                    xuanxk_tx.setVisibility(View.GONE);
                    xuanxk_tx1.setVisibility(View.GONE);
                }
                break;
            case R.id.save_tx:
                flag_all += 1;
                if (flag_all == 3) {
                    save_tx.setVisibility(View.GONE);
                    save_tx1.setVisibility(View.GONE);
                    details_zhinan.setVisibility(View.GONE);
                    PreferenceUtils.setPrefBoolean(DemoActivity.this, "zhinan_details", true);
                } else {
                    save_tx.setVisibility(View.GONE);
                    save_tx1.setVisibility(View.GONE);
                }
                break;
            case R.id.qx_tx:
                flag_all += 1;
                if (flag_all == 3) {
                    qx_tx.setVisibility(View.GONE);
                    qx_tx1.setVisibility(View.GONE);
                    details_zhinan.setVisibility(View.GONE);
                    PreferenceUtils.setPrefBoolean(DemoActivity.this, "zhinan_details", true);
                } else {
                    qx_tx.setVisibility(View.GONE);
                    qx_tx1.setVisibility(View.GONE);
                }
                break;
            case R.id.hedui_tx:
                flag_all += 1;
                if (flag_all == 3) {
                    hedui_tx.setVisibility(View.GONE);
                    hedui_tx1.setVisibility(View.GONE);
                    details_zhinan.setVisibility(View.GONE);
                    PreferenceUtils.setPrefBoolean(DemoActivity.this, "zhinan_details", true);
                } else {
                    hedui_tx.setVisibility(View.GONE);
                    hedui_tx1.setVisibility(View.GONE);
                }
                break;
            case R.id.bianji_tx:
                flag_all += 1;
                if (flag_all == 3) {
                    bianji_tx.setVisibility(View.GONE);
                    bianji_tx1.setVisibility(View.GONE);
                    details_zhinan.setVisibility(View.GONE);
                    PreferenceUtils.setPrefBoolean(DemoActivity.this, "zhinan_details", true);
                } else {
                    bianji_tx.setVisibility(View.GONE);
                    bianji_tx1.setVisibility(View.GONE);
                }
                break;
            case R.id.iv_details_more: //点点点，弹出popupWindow
                showOptPopupWindow();
                break;
        }
    }

    /**
     * 左侧识别码后点点点
     */
    private void showOptPopupWindow() {
        final PopupWindow pop = new PopupWindow(this);

        final List<String> list = new ArrayList<>();
        if (!state.equals("C")) {
            list.add("打印");
            if (setsbm && btn_details_right.getText().toString().equals("保存")) {
                list.add("扫码");
            }
            //非新增，来自于扫码且扫到的码与该装置的设备识别码不一致都出增加同类设备
            if (BHorFZ) {
//                if (hasSaoma && getIntent().hasExtra("sbsbdm") && bhsb != null &&
//                        !bhsb.getSfsbm().equals(getIntent().getStringExtra("sbsbdm"))) {
                list.add(1, "增加同类设备");
                if (bhsb != null && bhsb.getEd_tag() == null || bhsb != null && bhsb.getEd_tag() != null && !bhsb.getEd_tag().equals("D")) {
                    //非新增,非删除-都有删除
                    list.add(list.size(), "删除");
                }
            } else {
//                if (hasSaoma && getIntent().hasExtra("sbsbdm") && fzbhsb != null &&
//                        !fzbhsb.getSfsbm().equals(getIntent().getStringExtra("sbsbdm"))) {
                list.add(1, "增加同类设备");
                if (fzbhsb != null && fzbhsb.getEd_tag() == null || fzbhsb != null && fzbhsb.getEd_tag() != null && !fzbhsb.getEd_tag().equals("D")) {
                    //非新增,非删除-都有删除
                    list.add(list.size(), "删除");
                }
            }
        } else if (state.equals("C") && setsbm) {
            if (setsbm && btn_details_right.getText().toString().equals("保存")) {
                list.add("扫码");
            }
        }

        View view = LayoutInflater.from(this).inflate(R.layout.pop_view_item, null);
        ListView listView = (ListView) view.findViewById(R.id.pop_listView);

        CommonAdapter<String> moreAdapter = new CommonAdapter<String>(this, R.layout.item_list_pop) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.tv_name, item);
                ImageView iv = helper.getView(R.id.iv_icon);

                if (item.equals("增加同类设备")) {
                    iv.setImageResource(R.drawable.iv_addsame_white);
                } else if (item.equals("打印")) {
                    iv.setImageResource(R.drawable.iv_print_white);
                } else if (item.equals("删除")) {
                    iv.setImageResource(R.drawable.iv_delete_white);
                } else if (item.equals("扫码")) {
                    iv.setImageResource(R.drawable.iv_addsame_white);
                }

                if (helper.getPosition() == list.size() - 1) {
                    helper.getView(R.id.vv_diver).setVisibility(View.GONE);
                }
            }
        };

        listView.setAdapter(moreAdapter);
        moreAdapter.setDatas(list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (list.get(position)) {
                    case "增加同类设备":
                        Similar = true;
                        ToastUtils.showToast(DemoActivity.this, "添加同类设备");
                        data.clear();
                        currentType = "装置基本信息";
                        Intent intent2 = new Intent("com.lzl.details");
                        isEdit = true;
                        intent2.putExtra("EDIT", "");
                        iv_bgcz.setVisibility(View.VISIBLE);
                        sendBroadcast(intent2);

                        initData();
                        adapter.setSelect(0);

                        if (getIntent().hasExtra("sbsbdm") && isSwid) {
                            tv_swid.setText("");
                        }

                        if (!tv_swid.getText().toString().equals("")) {
                            tv_swid.setText("");
                        }

                        if (tv_swid.getText().toString().equals("")) {
                            setsbm = true;
                        }
                        break;
                    case "打印":
                        Intent intent1 = new Intent();
                        intent1.setClassName("an.qt.android", "an.qt.android.MainActivity");
                        Print printBean2 = new Print();
                        printBean2.setQrcode(tv_swid.getText().toString());
                        printBean2.setCzmc(czcs.getCzmc());
                        if (BHorFZ) {//保护
                            printBean2.setBh_sb_mc(bhsb.getBhmc());
                            printBean2.setBhlb(bhsb.getBhlb());
                            printBean2.setZzcj(bhsb.getZzcj());
                            printBean2.setBh_sb_xh(bhsb.getBhxh());
                            printBean2.setYcsblx(bhsb.getYcsblx());
                            printBean2.setYcsbmc(bhsb.getYcsbmc());
                            printBean2.setYxdw(bhsb.getYxdw());
                            printBean2.setWhdw(bhsb.getWhdw());
                            printBean2.setDddw(bhsb.getDddw());
                            printBean2.setGldwmc(bhsb.getCzgldw());
                            printBean2.setTyrq(bhsb.getTyrq());
                            printBean2.setCcrq(bhsb.getCcrq());
                        } else {//辅助
                            printBean2.setBh_sb_mc(fzbhsb.getSbmc());
                            printBean2.setBhlb(fzbhsb.getFzsblx());
                            printBean2.setZzcj(fzbhsb.getCj());
                            printBean2.setBh_sb_xh(fzbhsb.getSbxh());
                            printBean2.setYcsblx(fzbhsb.getYcsblx());
                            printBean2.setYcsbmc(fzbhsb.getYcsbmc());
                            printBean2.setYxdw(fzbhsb.getYxdw());
                            printBean2.setWhdw(fzbhsb.getWhdw());
                            printBean2.setDddw(fzbhsb.getDddw());
                            printBean2.setGldwmc(fzbhsb.getCzgldw());
                            printBean2.setTyrq(fzbhsb.getTyrq());
                            printBean2.setCcrq(fzbhsb.getCcrq());
                        }

                        if (printBean2.getQrcode() != null && !printBean2.getQrcode().equals("")) {
                            printBean2.setForm_type("FORM_PRINT");
                        } else {
                            printBean2.setForm_type("FORM_CUSTOM_PRINT");
                        }

                        intent1.putExtra("json", new Gson().toJson(printBean2));
                        try {
                            startActivity(intent1);
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtils.showToast(DemoActivity.this, "未安装打印机应用,请先安装");
                        }
                        break;
                    case "删除":
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 0.5f;
                        getWindow().setAttributes(lp);
                        showDialog(true, false);
                        break;
                    case "扫码":
                        //获取实物ID
                        Intent startScan = new Intent(DemoActivity.this, CaptureActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("request", "swm");
                        startScan.putExtras(bundle);
                        startActivityForResult(startScan, 98);
                        break;
                }
                pop.dismiss();
            }
        });

        pop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAsDropDown(iv_details_more, -50, 0);
    }

    /**
     * 统一弹窗
     *
     * @param isDel
     * @param isHD
     */
    private void showDialog(final boolean isDel, final boolean isHD) {
        final PopupWindow pop = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.common_dialog_view, null);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_ensure = (TextView) view.findViewById(R.id.tv_ensure);
        if (isDel) {
            tv_message.setText("是否确定删除该装置信息？");
        } else {
            if (BHorFZ) {//保护
                if (isHD && !Fragment_Type_Base.instance.isYcsbmcLikeZzmc()) {
                    tv_message.setText("当前一次设备名称和装置名称不一致，是否继续核对无误？");
                } else if (!isHD && !Fragment_Type_Base.instance.isYcsbmcLikeZzmc()) {
                    tv_message.setText("当前一次设备名称和装置名称不一致，是否继续保存？");
                }
            } else { //辅助
                if (isHD && !Details1.instance.isYcsbmcLikeZzmc()) {
                    tv_message.setText("当前一次设备名称和装置名称不一致，是否继续核对无误？");
                } else if (!isHD && !Details1.instance.isYcsbmcLikeZzmc()) {
                    tv_message.setText("当前一次设备名称和装置名称不一致，是否继续保存？");
                }
            }
        }

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //取消
                pop.dismiss();
            }
        });
        tv_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDel) {//删除
                    boolean isD;
                    if (BHorFZ) {
                        if (bhsb.getSb() != null && bhsb.getSb().equals("F")) {
                            isD = false;
                        } else {
                            isD = bhsb.getEd_tag() != null && bhsb.getEd_tag().equals("C")
                                    || bhsb.getEd_tag() != null && bhsb.getEd_tag().equals("CH");
                        }
                        if (isD) {
                            if (coreId != null && !coreId.equals("")) {
                                bhsb.setId(coreId);
                            }
                            util.coreBHPZ("D", bhsb);
                        } else {
                            bhsb.setEd_tag("D");
                            bhsb.setSb("D");
                            util.coreSavaRZXX(bhsb);
                            util.coreBHPZ("M", bhsb);
                        }
                        sendBroadcast(new Intent("cn.sgg.fzbhsb"));
                    } else {
                        if (fzbhsb.getSb() != null && fzbhsb.getSb().equals("F")) {
                            isD = false;
                        } else {
                            isD = fzbhsb.getEd_tag() != null && fzbhsb.getEd_tag().equals("C")
                                    || fzbhsb.getEd_tag() != null && fzbhsb.getEd_tag().equals("CH");
                        }
                        if (isD) {
                            if (coreId != null && !coreId.equals("")) {
                                fzbhsb.setId(coreId);
                            }
                            util.coreFZHBSB("D", fzbhsb);
                        } else {
                            fzbhsb.setEd_tag("D");
                            fzbhsb.setSb("D");
                            util.coreSavaRZXX(fzbhsb);
                            util.coreFZHBSB("M", fzbhsb);
                        }
                        sendBroadcast(new Intent("cn.sgg.fzbhsb"));
                    }
                    ToastUtils.showToast(DemoActivity.this, "删除成功");
                    pop.dismiss();
                    finish();
                } else { //核对或保存
                    pop.dismiss();
                    if (isHD) { //核对
                        if (BHorFZ) {
                            Fragment_Type_Base.instance.onScroll(60);
                        } else {
                            Details1.instance.onScroll(60);
                        }
                        loadingDialog.setTitle("正在操作");
                        loadingDialog.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                if (BHorFZ) {
//                                    savebhsb(true);
                                    if (savebhsb(true)) {
//                                        jybhsb(bhsb);
                                        handler.sendEmptyMessage(1);
                                    } else {
                                        handler.sendEmptyMessage(-1);
                                    }
                                } else {
                                    if (saveFzbhsb(true)) {
                                        handler.sendEmptyMessage(1);
                                    } else {
                                        handler.sendEmptyMessage(-1);
                                    }
                                }
                            }
                        }).start();
                    } else { //保存
                        if (BHorFZ) {
                            Fragment_Type_Base.instance.onScroll(60);
                        } else {
                            Details1.instance.onScroll(60);
                        }
                        loadingDialog.setTitle("正在操作");
                        loadingDialog.show();
                        new Thread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        if (BHorFZ) {
                                            if (savebhsb(false)) {
                                                jybhsb(bhsb);
                                                handler.sendEmptyMessage(0);
                                            } else {
                                                handler.sendEmptyMessage(-1);
                                            }
                                        } else {
                                            if (saveFzbhsb(false)) {
                                                jyfzbhsb(fzbhsb);
                                                handler.sendEmptyMessage(0);
                                            } else {
                                                handler.sendEmptyMessage(-1);
                                            }
                                        }
                                    }
                                }).start();
                    }
                }
            }
        });

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(false);
        pop.setContentView(view);
        pop.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    /**
     * 还原时检测设备名称是否存在一样的
     *
     * @param sbmc
     * @param orgId
     */
    private void checkHasSbmc(String sbmc, Long orgId) {
        Object obj = util.getFZSBBySbsbdm(sbmc, "");
        Long id = null;
        if (obj instanceof BHPZ) {
            id = ((BHPZ) obj).getId();
        } else if (obj instanceof FZBHSB) {
            id = ((FZBHSB) obj).getId();
        }
        if (obj != null && !id.equals(orgId)) {//弹出窗口让用户确认是否要继续还原
            AppUtils.showDialog(this, "检测到存在相同的装置名称是否继续还原？", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((PopupWindow) v.getTag()).dismiss();
                    ToastUtils.showLongToast(DemoActivity.this, "用户取消还原");
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((PopupWindow) v.getTag()).dismiss();
                    btn_details_left.setEnabled(true);
                    btn_details_right.setEnabled(true);
                    ll_bottom_opt.setVisibility(View.VISIBLE);
                    btn_details_undo.setVisibility(View.GONE);

                    Intent intent = new Intent("com.lzl.details");
                    isUndo = true;
                    isEdit = true;
                    btn_details_left.setText("取消");
                    btn_details_right.setText("保存");
                    intent.putExtra("EDIT", "");
                    sendBroadcast(intent);
                }
            });
        } else {//直接进行还原
            if (BHorFZ) {//保护
                bhsb.setEd_tag("M");
                bhsb.setSb("S");
                util.coreBHPZ("M", bhsb);
            } else {//辅助
                fzbhsb.setEd_tag("M");
                fzbhsb.setSb("S");
                util.coreFZHBSB("M", fzbhsb);
            }
            btn_details_left.setEnabled(true);
            btn_details_right.setEnabled(true);
//            iv_details_del.setVisibility(View.VISIBLE);
            ll_bottom_opt.setVisibility(View.VISIBLE);
            btn_details_undo.setVisibility(View.GONE);
            //更新列表数据
//            sendBroadcast(new Intent("cn.sgg.fzbhsb"));

//            initData();
        }
    }

    //辅助装置校验
    public void jyfzbhsb(FZBHSB fzbhsb) {
        boolean TGJY = true; //通过校验
        if (nameVoJYX != null && nameVoJYX.size() > 0) {
            SaleAttributeNameVo nameVojy = (SaleAttributeNameVo) nameVoJYX.get(1);
            List<SaleAttributeVo> vo2 = nameVojy.getSaleVo();
            for (SaleAttributeVo saleAttributeVo : vo2) {
                if (saleAttributeVo.getValue().equals("设备识别代码")) {
                    if (fzbhsb.getSfsbm() == null || (fzbhsb.getSfsbm() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;

                } else if (saleAttributeVo.getValue().equals("实物ID")) {
                    if (fzbhsb.getSw_id() == null || (fzbhsb.getSw_id() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("装置名称")) {
                    if (fzbhsb.getSbmc() == null || (fzbhsb.getSbmc() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("是否六统一")) {
                    if (fzbhsb.getSfltysb() == null || (fzbhsb.getSfltysb() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("制造厂家")) {
                    if (fzbhsb.getCj() == null || (fzbhsb.getCj() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("装置类别")) {
                    if (fzbhsb.getFzsblx() == null || (fzbhsb.getFzsblx() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("装置型号")) {
                    if (fzbhsb.getSbxh() == null || (fzbhsb.getSbxh() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("出厂日期")) {
                    if (fzbhsb.getCcrq() == null || (fzbhsb.getCcrq() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("板卡数量")) {
                    if ((fzbhsb.getBksl() + "") == null || (fzbhsb.getBksl() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("装置分类")) {
                    if (fzbhsb.getBhfl() == null || (fzbhsb.getBhfl() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("装置类型")) {
                    if (fzbhsb.getBhlx() == null || (fzbhsb.getBhlx() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("一次设备类型")) {
                    if (fzbhsb.getYcsblx() == null || (fzbhsb.getYcsblx() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("一次设备名称")) {
                    if (fzbhsb.getYcsbmc() == null || (fzbhsb.getYcsbmc() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("电压等级")) {
                    if ((fzbhsb.getDydj() + "") == null || (fzbhsb.getDydj() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("单位名称")) {
                    if (fzbhsb.getCzgldw() == null || (fzbhsb.getCzgldw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("调度单位")) {
                    if (fzbhsb.getDddw() == null || (fzbhsb.getDddw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("投运日期")) {
                    if (fzbhsb.getTyrq() == null || (fzbhsb.getTyrq() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("设计单位")) {
                    if (fzbhsb.getSjdw() == null || (fzbhsb.getSjdw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("基建单位")) {
                    if (fzbhsb.getJjdw() == null || (fzbhsb.getJjdw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("运行单位")) {
                    if (fzbhsb.getYxdw() == null || (fzbhsb.getYxdw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("维护单位")) {
                    if (fzbhsb.getWhdw() == null || (fzbhsb.getWhdw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("设备属性")) {
                    if (fzbhsb.getSbsx() == null || (fzbhsb.getSbsx() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("套别")) {
                    if (fzbhsb.getTb() == null || (fzbhsb.getTb() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("设备状态")) {
                    if (fzbhsb.getYxzt() == null || (fzbhsb.getYxzt() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("定期检验周期")) {
                    if ((fzbhsb.getDqjyzq() + "") == null || (fzbhsb.getDqjyzq() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("上次检修时间")) {
                    if (fzbhsb.getScdqjysj() == null || (fzbhsb.getScdqjysj() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("资产编号")) {
                    if (fzbhsb.getZcbh() == null || (fzbhsb.getZcbh() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("资产性质")) {
                    if (fzbhsb.getZcxz() == null || (fzbhsb.getZcxz() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("资产单位")) {
                    if (fzbhsb.getZcdw() == null || (fzbhsb.getZcdw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } //以上辅助一级校验项
                else if (saleAttributeVo.getValue().equals("六统一标准版本")) {
                    if (fzbhsb.getSfltysb().equals("是") && (fzbhsb.getLtybzbb() == null || (fzbhsb.getLtybzbb() + "").trim().equals(""))) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("退运日期")) {
                    if (fzbhsb.getYxzt().equals("退运") && (fzbhsb.getTcyxsj() == null || (fzbhsb.getTcyxsj() + "").trim().equals(""))) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("通道频率")) {
                    if (fzbhsb.getFzsblx().equals("收发信机") && ((fzbhsb.getTdpl() + "").trim().equals("") || (fzbhsb.getTdpl() + "") == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("载波通道加工相")) {
                    if (fzbhsb.getFzsblx().equals("收发信机") && ((fzbhsb.getZbtdjgx() + "").trim().equals("") || fzbhsb.getZbtdjgx() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("对时方式")) {
                    if (fzbhsb.getFzsblx().equals("合并单元智能终端集成") && ((fzbhsb.getDsfs() + "").trim().equals("") || fzbhsb.getDsfs() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("发送光纤口数量")) {
                    if (fzbhsb.getFzsblx().equals("合并单元智能终端集成") && ((fzbhsb.getFsgxsl() + "").trim().equals("") || fzbhsb.getFsgxsl() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("发送光纤口数量智能")) {
                    if (fzbhsb.getFzsblx().equals("智能终端") && ((fzbhsb.getFsgxsl() + "").trim().equals("") || fzbhsb.getFsgxsl() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("接收光纤口数量")) {
                    if (fzbhsb.getFzsblx().equals("合并单元智能终端集成") && ((fzbhsb.getFsgxsl() + "").trim().equals("") || fzbhsb.getFsgxsl() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("接收光纤口数量智能")) {
                    if (fzbhsb.getFzsblx().equals("智能终端") && ((fzbhsb.getFsgxsl() + "").trim().equals("") || fzbhsb.getFsgxsl() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("光纤接口模式")) {
                    if (fzbhsb.getFzsblx().equals("合并单元智能终端集成") && ((fzbhsb.getGxjkms() + "").trim().equals("") || fzbhsb.getGxjkms() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("光纤接口模式智能")) {
                    if (fzbhsb.getFzsblx().equals("智能终端") && ((fzbhsb.getGxjkms() + "").trim().equals("") || fzbhsb.getGxjkms() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("光纤接口模式交换机")) {
                    if (fzbhsb.getFzsblx().equals("交换机") && ((fzbhsb.getGxjkms() + "").trim().equals("") || fzbhsb.getGxjkms() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("合并单元功能")) {
                    if (fzbhsb.getFzsblx().equals("合并单元智能终端集成") && ((fzbhsb.getHbdygn() + "").trim().equals("") || fzbhsb.getHbdygn() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("互感器类型")) {
                    if (fzbhsb.getFzsblx().equals("合并单元智能终端集成") && ((fzbhsb.getHgqlx() + "").trim().equals("") || fzbhsb.getHgqlx() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("供电电源")) {
                    if (fzbhsb.getFzsblx().equals("合并单元智能终端集成") && ((fzbhsb.getGddy() + "").trim().equals("") || fzbhsb.getGddy() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("装置属性")) {
                    if (fzbhsb.getFzsblx().equals("合并单元智能终端集成") && ((fzbhsb.getZzsx() + "").trim().equals("") || fzbhsb.getZzsx() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("智能终端功能")) {
                    if (fzbhsb.getFzsblx().equals("合并单元智能终端集成") && ((fzbhsb.getZnzdgn() + "").trim().equals("") || fzbhsb.getZnzdgn() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("智能终端功能智能")) {
                    if (fzbhsb.getFzsblx().equals("智能终端") && ((fzbhsb.getZnzdgn() + "").trim().equals("") || fzbhsb.getZnzdgn() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("交换机功能")) {
                    if (fzbhsb.getFzsblx().equals("交换机") && ((fzbhsb.getJhjgn() + "").trim().equals("") || fzbhsb.getJhjgn() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("交换机级联数")) {
                    if (fzbhsb.getFzsblx().equals("交换机") && ((fzbhsb.getJhjjls() + "").trim().equals("") || (fzbhsb.getJhjjls() + "") == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("是否支持RSTP环网")) {
                    if (fzbhsb.getFzsblx().equals("交换机") && ((fzbhsb.getSfrstp() + "").trim().equals("") || fzbhsb.getSfrstp() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("是否支持1588对时")) {
                    if (fzbhsb.getFzsblx().equals("交换机") && ((fzbhsb.getSfds() + "").trim().equals("") || fzbhsb.getSfds() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("是否支持动态组播管理")) {
                    if (fzbhsb.getFzsblx().equals("交换机") && ((fzbhsb.getSfzb() + "").trim().equals("") || fzbhsb.getSfzb() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("是否支持SNMP网络管理")) {
                    if (fzbhsb.getFzsblx().equals("交换机") && ((fzbhsb.getSfsnmp() + "").trim().equals("") || fzbhsb.getSfsnmp() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("是否支持采用IEC61850上送交换机信息")) {
                    if (fzbhsb.getFzsblx().equals("交换机") && ((fzbhsb.getSfiec() + "").trim().equals("") || fzbhsb.getSfiec() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("交换机类型")) {
                    if (fzbhsb.getFzsblx().equals("交换机") && ((fzbhsb.getJhjlx() + "").trim().equals("") || fzbhsb.getJhjlx() == null)) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("ICD文件名称") || saleAttributeVo.getValue().equals("ICD文件版本")
                        || saleAttributeVo.getValue().equals("CRC32验证码") || saleAttributeVo.getValue().equals("MD5校验码")
                        || saleAttributeVo.getValue().equals("ICD文件最终修改时间") || saleAttributeVo.getValue().equals("专业检测批次")) {
                    if (fzbhsb.getSfltysb().equals("是") && fzbhsb.getLtybzbb().equals("2013版")) {
                        List<Object> ltysbxhList = util.getRJBBByCode(true, true, fzbhsb.getId() + "");
                        if (ltysbxhList != null && ltysbxhList.size() > 0) {
                            if (saleAttributeVo.getValue().equals("ICD文件名称")) {
                                for (int i = 0; i < ltysbxhList.size(); i++) {
                                    if (((LTYSBXH) ltysbxhList.get(i)).getWjmc() == null || (((LTYSBXH) ltysbxhList.get(i)).getWjmc() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("ICD文件版本")) {
                                for (int i = 0; i < ltysbxhList.size(); i++) {
                                    if (((LTYSBXH) ltysbxhList.get(i)).getWjbb() == null || (((LTYSBXH) ltysbxhList.get(i)).getWjbb() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("CRC32验证码")) {
                                for (int i = 0; i < ltysbxhList.size(); i++) {
                                    if (((LTYSBXH) ltysbxhList.get(i)).getCrc32() == null || (((LTYSBXH) ltysbxhList.get(i)).getCrc32() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("MD5校验码")) {
                                for (int i = 0; i < ltysbxhList.size(); i++) {
                                    if (((LTYSBXH) ltysbxhList.get(i)).getMd5() == null || (((LTYSBXH) ltysbxhList.get(i)).getMd5() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("ICD文件最终修改时间")) {
                                for (int i = 0; i < ltysbxhList.size(); i++) {
                                    if (((LTYSBXH) ltysbxhList.get(i)).getZzxgsj() == null || (((LTYSBXH) ltysbxhList.get(i)).getZzxgsj() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("专业检测批次")) {
                                for (int i = 0; i < ltysbxhList.size(); i++) {
                                    if (((LTYSBXH) ltysbxhList.get(i)).getZyjcpc() == null || (((LTYSBXH) ltysbxhList.get(i)).getZyjcpc() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            }

                        } else {
                            TGJY = false;
                            break;
                        }
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("模块名称") || saleAttributeVo.getValue().equals("软件版本")
                        || saleAttributeVo.getValue().equals("校验码") || saleAttributeVo.getValue().equals("生成时间")) {
                    if (fzbhsb.getSfltysb().equals("是") && fzbhsb.getLtybzbb().equals("2013版")) { //六统一
                        List<Object> list = util.getRJBBByCode(true, true, fzbhsb.getId() + "");
                        if (list != null && list.size() > 0) {
                            LTYSBXH ltysbxh = (LTYSBXH) list.get(0);
                            if (saleAttributeVo.getValue().equals("软件版本")) {
                                if (ltysbxh.getRjbb() == null || (ltysbxh.getRjbb() + "").trim().equals("")) {
                                    TGJY = false;
                                }
                                continue;
                            }
                            continue;
                        } else {
                            TGJY = false;
                            break;
                        }
                    } else {//非六统一: 分模块和不分模块
                        if (Details1.instance.NoShowRjbb()) {
                            continue;
                        }
                        if (fzbhsb.getBhlx() != null && fzbhsb.getBhlx().equals("微机型")) {
                            List<Object> list = util.getRJBBByCode(false, false, fzbhsb.getId() + "");
                            if (list != null && list.size() > 0) {
                                List<BHXHRJBB> rjbbList = new ArrayList<>();
                                for (Object o : list) {
                                    rjbbList.add((BHXHRJBB) o);
                                }

                                //非六统一，不分模块   装置类别是"操作箱", "电压切换箱", "操作箱/电压切换箱", "光纤通信接口装置",不显示软件版本
                                if (rjbbList.size() == 1 && rjbbList.get(0) != null && rjbbList.get(0).getBblx().equals("非六统一，不分模块")) {
                                    if (!fzbhsb.getFzsblx().equals("操作箱") && !fzbhsb.getFzsblx().equals("电压切换箱") && !fzbhsb.getFzsblx().equals("操作箱/电压切换箱") && !fzbhsb.getFzsblx().equals("光纤通信接口装置")) {
                                        if (saleAttributeVo.getValue().equals("软件版本") && (rjbbList.get(0).getBb() == null || (rjbbList.get(0).getBb() + "").trim().equals(""))) {
                                            TGJY = false;
                                            break;
                                        } else if (saleAttributeVo.getValue().equals("校验码") && (rjbbList.get(0).getJym() == null || (rjbbList.get(0).getJym() + "").trim().equals(""))) {
                                            TGJY = false;
                                            break;
                                        } else if (saleAttributeVo.getValue().equals("生成时间") && (rjbbList.get(0).getSCSJ() == null || (rjbbList.get(0).getSCSJ() + "").trim().equals(""))) {
                                            TGJY = false;
                                            break;
                                        }
                                        continue;
                                    }
                                } else if ((rjbbList.size() > 1 || rjbbList.size() == 1) && rjbbList.get(0) != null
                                        && rjbbList.get(0).getBblx() != null && rjbbList.get(0).getBblx().equals("非六统一，分模块")) {//非六统一,分模块
                                    if (saleAttributeVo.getValue().equals("模块名称")) {
                                        for (int i = 0; i < rjbbList.size(); i++) {
                                            if (rjbbList.get(i).getMkmc() == null || (rjbbList.get(i).getMkmc() + "").trim().equals("")) {
                                                TGJY = false;
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;

                                    } else if (saleAttributeVo.getValue().equals("软件版本")) {
                                        for (int i = 0; i < rjbbList.size(); i++) {
                                            if (rjbbList.get(i).getBb() == null || (rjbbList.get(i).getBb() + "").trim().equals("")) {
                                                TGJY = false;
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("软件校验码版本")) {
                                        for (int i = 0; i < rjbbList.size(); i++) {
                                            if (rjbbList.get(i).getJym() == null || (rjbbList.get(i).getJym() + "").trim().equals("")) {
                                                TGJY = false;
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("生成时间")) {
                                        for (int i = 0; i < rjbbList.size(); i++) {
                                            if (rjbbList.get(i).getSCSJ() == null || (rjbbList.get(i).getSCSJ() + "").trim().equals("")) {
                                                TGJY = false;
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    }
                                }
                            } else {
                                TGJY = false;
                                break;
                            }
                        }
                    }
                }
            }

            if (TGJY) {
                fzbhsb.setJy("TG"); //改为校验字段
            } else {
                fzbhsb.setJy("WTG");
            }

            util.coreFZHBSB("M", fzbhsb);
        }

    }

    /**
     * 辅助设备保存逻辑
     *
     * @param isH
     * @return
     */
    private boolean saveFzbhsb(boolean isH) {
        util = new DaoUtil(this);
        String originfzId = null;
        if (fzbhsb == null) {
            fzbhsb = new FZBHSB();
        }

      /*   if(Similar || isH){
            state = "C";
         }*/
        if (Details1.instance.saveDetails1()) {
            Log.e("Details1：", "Details1校验通过");
        } else {
            faildType = "装置基本信息";
            loadingDialog.dismiss();
            return false;
        }
        if (fzbhsb.getSfltysb().equals("是") && fzbhsb.getLtybzbb().equals("2013版")) {
            if (details8 == null || Details8.instance.checkIcd()) {
                Log.e("Details8：", "Details8校验通过");
            } else {
                faildType = "ICD文件信息";
                loadingDialog.dismiss();
                return false;
            }
        }
        if (details2 == null || Details2.instance.saveDetails2()) {
            Log.e("Details2：", "Details2校验通过");
        } else {
            faildType = "安装及运维信息";
            loadingDialog.dismiss();
            return false;
        }
        if (details3 == null || Details3.instance.saveDetails3()) {
            Log.e("Details3：", "Details3校验通过");
        } else {
            faildType = "运行基本信息";
            loadingDialog.dismiss();
            return false;
        }
        if (details7 == null || Details7.instance.saveDetails7()) {
            Log.e("Details7：", "Details7校验通过");
        } else {
            faildType = "资产信息";
            loadingDialog.dismiss();
            return false;
        }
        if (details9 == null || Details9.instance.saveDetails9(fzbhsb.getFzsblx())) {
            Log.e("Details9：", "Details9校验通过");
        } else {
            faildType = currentFl;
            loadingDialog.dismiss();
            return false;
        }
        fzbhsb.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
        fzbhsb.setCzsj(TimeUtil.getCurrentTime());
        fzbhsb.setCzmc(czcs.getCzmc());
        fzbhsb.setCzzgdydj(czcs.getCzzgdydj());
        fzbhsb.setCzgldw(czcs.getGldw());
        fzbhsb.setBdzlx(czcs.getBdzlx());
        if (fzbhsb.getSb() == null || fzbhsb.getSb().equals("")) {
            fzbhsb.setSb("S");
        }
        fzbhsb.setSw_id(tv_swid.getText().toString());
        if (fzbhsb.getSb() != null && fzbhsb.getSb().equals("F"))
            fzbhsb.setSb("FF");
        Long fzbhsbId;
        String rjbb = "";
        if (fzbhsb.getId() != null && !fzbhsb.getId().equals("")) {
            if (fzbhsb.getEd_tag() != null && fzbhsb.getEd_tag().equals("C") && coreId != null) {
                fzbhsb.setId(coreId);
            }
            //如果是新增同类的设备需要新插入一个id
            if (Similar) {
                originfzId = fzbhsb.getId() + "";
                fzbhsbId = util.getInsertId("FZBHSB");
                fzbhsb.setId(fzbhsbId);
            } else {
                fzbhsbId = fzbhsb.getId();
            }
        } else {
            fzbhsbId = util.getInsertId("FZBHSB");
            fzbhsb.setId(fzbhsbId);
        }

        if (tv_swid.getText().toString().equals("")) {
            ToastUtils.showLongToast(this, "该装置实物ID为空，请补码");
            coreId = fzbhsbId;
            loadingDialog.dismiss();
            faildType = "装置基本信息";
            return false;
        }

        Object obj = util.getFZSBBySbsbdm(fzbhsb.getSbmc(), "");
        Long id = null;
        if (obj instanceof BHPZ) {
            id = ((BHPZ) obj).getId();
        } else if (obj instanceof FZBHSB) {
            id = ((FZBHSB) obj).getId();
        }
        if (obj != null && !id.equals(fzbhsbId)) {
            ToastUtils.showLongToast(this, "该装置名称已被其他装置使用，请手动修改");
            coreId = fzbhsbId;
            loadingDialog.dismiss();
            faildType = "装置基本信息";
            return false;
        }

        Object obj1 = util.getFZSBBySbsbdm(fzbhsb.getSw_id());
        Long id1 = null;
        if (obj1 instanceof BHPZ) {
            id1 = ((BHPZ) obj1).getId();
        } else if (obj1 instanceof FZBHSB) {
            id1 = ((FZBHSB) obj1).getId();
        }

        if (obj1 != null && !id1.equals(fzbhsbId)) {
            ToastUtils.showLongToast(this, "该装置实物ID已被其他装置使用");
            coreId = fzbhsbId;
            faildType = "装置基本信息";
            loadingDialog.dismiss();
            return false;
        }

        if (state.equals("C")) {  //新增
            coreId = fzbhsbId;
            fzbhsb.setEd_tag("C");
            isC = true;
        } else {   //修改
            isC = false;
            if (fzbhsb.getEd_tag() != null && !fzbhsb.getEd_tag().equals("null")
                    && !fzbhsb.getEd_tag().equals("") && fzbhsb.getEd_tag().equals("C")
                    || fzbhsb.getEd_tag() != null && fzbhsb.getEd_tag().equals("CH")) {
                fzbhsb.setEd_tag("C");
            } else {
                fzbhsb.setEd_tag("M");
            }
        }

        if (fzbhsb.getSfsbm() != null && !fzbhsb.getSfsbm().equals("") &&
                util.isSWIDUsed(fzbhsb.getSfsbm() + "", fzbhsbId + "")) {
            ToastUtils.showLongToast(this, "该装置设备识别代码已被其他装置使用");
            coreId = fzbhsbId;
            faildType = "装置基本信息";
            loadingDialog.dismiss();
            return false;
        }

        util.coreSavaRZXX(fzbhsb);
        if (!Details1.instance.NoShowRjbb()) {
            util.coreDelte(fzbhsbId + "");
        }

        rzgl = util.getRzxx(fzbhsbId, "FZSB");

        Details1.instance.isSave = true;
        //存储新增的厂家
        if (Details1.instance.zzcj_add != null) {
            util.coreSave(Details1.instance.zzcj_add);
        }

        String code;
        if (Details1.instance.isSix && Details1.instance.is2013) {
            Details8.instance.saveICD(fzbhsbId);

            code = util.getCodeByBhxhRjbb(Details1.instance.ltysbxh_add);
            if (code != null) {
                rjbb += Details1.instance.ltysbxh_add.getRjbb();
                FZBHSBXHBBGX bbgx = new FZBHSBXHBBGX();
                bbgx.setID(util.getInsertId("FZBHSBXHBBGX"));
                bbgx.setFZBHSBID(fzbhsbId);
                bbgx.setSCSJ(Details1.instance.ltysbxh_add.getSCSJ());
                bbgx.setRJBBCODE(code);
                util.coreSave(bbgx);
            } else {
                Details1.instance.ltysbxh_add.setId(util.getInsertId("LTYSBXH"));
                Details1.instance.ltysbxh_add.setCode(util.getInsertId());

                rjbb += Details1.instance.ltysbxh_add.getRjbb();

                FZBHSBXHBBGX bbgx = new FZBHSBXHBBGX();
                bbgx.setID(util.getInsertId("FZBHSBXHBBGX"));
                bbgx.setFZBHSBID(fzbhsbId);
                bbgx.setSCSJ(Details1.instance.ltysbxh_add.getSCSJ());
                bbgx.setRJBBCODE(Details1.instance.ltysbxh_add.getCode());
                util.coreSave(bbgx);
                util.coreSave(Details1.instance.ltysbxh_add);
            }
        } else {
            //是否新增型号
            BHSBXHB bhsbxhb = Details1.instance.bhsbxhb;
            boolean isMXH = true;
            if (bhsbxhb != null) {
                if (bhsbxhb.getId() == null || bhsbxhb.getId().equals("")) {
                    bhsbxhb.setId(util.getInsertId("BHSBXHB"));
                    isMXH = false;
                }

                if (bhsbxhb.getCode() == null || bhsbxhb.getCode().equals("")) {
                    bhsbxhb.setCode(util.getInsertId());
                    isMXH = false;
                }

                bhsbxhb.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
            }

            //软件版本
            if (Details1.instance.rjbbList.size() == 1 && !Details1.instance.NoShowRjbb()) {
                BHXHRJBB bhxhrjbb = Details1.instance.rjbbList.get(0);
                if (bhsbxhb != null) {
                    bhxhrjbb.setBhxhcode(bhsbxhb.getCode());
                }
                if (bhxhrjbb.getBblx().equals("非六统一，分模块")) {
                    rjbb += "模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成日期:" + bhxhrjbb.getSCSJ() + ";";
                    bhxhrjbb.setRjbbxx("模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成时间:" + bhxhrjbb.getSCSJ());
                } else {
                    rjbb += "软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成日期:" + bhxhrjbb.getSCSJ();
                    bhxhrjbb.setRjbbxx("软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成时间:" + bhxhrjbb.getSCSJ());
                }
                code = util.getCodeByBhxhRjbb(bhxhrjbb);
                if (code != null) {
                    FZBHSBXHBBGX bbgx = new FZBHSBXHBBGX();
                    bbgx.setID(util.getInsertId("FZBHSBXHBBGX"));
                    bbgx.setFZBHSBID(fzbhsbId);
                    bbgx.setSCSJ(bhxhrjbb.getSCSJ());
                    bbgx.setRJBBCODE(code);
                    util.coreSave(bbgx);
                } else {
                    bhxhrjbb.setId(util.getInsertId("BHXHRJBB"));
                    bhxhrjbb.setCode(util.getInsertId());
                    FZBHSBXHBBGX bbgx = new FZBHSBXHBBGX();
                    bbgx.setID(util.getInsertId("FZBHSBXHBBGX"));
                    bbgx.setFZBHSBID(fzbhsbId);
                    bbgx.setSCSJ(bhxhrjbb.getSCSJ());
                    bbgx.setRJBBCODE(bhxhrjbb.getCode());
                    util.coreSave(bbgx);
                    util.coreSave(bhxhrjbb);
                }
            } else if (Details1.instance.rjbbList.size() > 1 && !Details1.instance.NoShowRjbb()) {
                if (Details1.instance.rl_more_mk.getVisibility() == View.VISIBLE) {
                    Details11.instance.updataMoreMk();
                    for (BHXHRJBB bhxhrjbb : Details11.instance.list_one_data) {
                        if (bhsbxhb != null) {
                            bhxhrjbb.setBhxhcode(bhsbxhb.getCode());
                        }
                        if (bhxhrjbb.getCode() == null) {
                            bhxhrjbb.setCode(util.getInsertId() + "");
                        }
                        rjbb += "模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成日期:" + bhxhrjbb.getSCSJ() + ";";
                        bhxhrjbb.setRjbbxx("模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成时间:" + bhxhrjbb.getSCSJ());
                        code = util.getCodeByBhxhRjbb(bhxhrjbb);
                        if (code != null) {
                            FZBHSBXHBBGX bbgx = new FZBHSBXHBBGX();
                            bbgx.setID(util.getInsertId("FZBHSBXHBBGX"));
                            bbgx.setFZBHSBID(fzbhsbId);
                            bbgx.setSCSJ(bhxhrjbb.getSCSJ());
                            bbgx.setRJBBCODE(code);
                            util.coreSave(bbgx);
                        } else {
                            bhxhrjbb.setId(util.getInsertId("BHXHRJBB"));
                            if (bhsbxhb != null) {
                                bhxhrjbb.setBhxhcode(bhsbxhb.getCode());
                            }
                            bhxhrjbb.setCode(util.getInsertId());
                            FZBHSBXHBBGX bbgx = new FZBHSBXHBBGX();
                            bbgx.setID(util.getInsertId("FZBHSBXHBBGX"));
                            bbgx.setFZBHSBID(fzbhsbId);
                            bbgx.setSCSJ(bhxhrjbb.getSCSJ());
                            bbgx.setRJBBCODE(bhxhrjbb.getCode());
                            util.coreSave(bbgx);
                            util.coreSave(bhxhrjbb);
                        }
                    }
                } else {
                    for (BHXHRJBB bhxhrjbb : Details1.instance.rjbbList) {
                        if (bhsbxhb != null) {
                            bhxhrjbb.setBhxhcode(bhsbxhb.getCode());
                        }
                        rjbb += "模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成日期:" + bhxhrjbb.getSCSJ() + ";";
                        bhxhrjbb.setRjbbxx("模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成时间:" + bhxhrjbb.getSCSJ());
                        code = util.getCodeByBhxhRjbb(bhxhrjbb);
                        if (code != null) {
                            FZBHSBXHBBGX bbgx = new FZBHSBXHBBGX();
                            bbgx.setID(util.getInsertId("FZBHSBXHBBGX"));
                            bbgx.setFZBHSBID(fzbhsbId);
                            bbgx.setSCSJ(bhxhrjbb.getSCSJ());
                            bbgx.setRJBBCODE(code);
                            util.coreSave(bbgx);
                        } else {
                            bhxhrjbb.setId(util.getInsertId("BHXHRJBB"));
                            if (bhsbxhb != null) {
                                bhxhrjbb.setBhxhcode(bhsbxhb.getCode());
                            }
                            bhxhrjbb.setCode(util.getInsertId());
                            FZBHSBXHBBGX bbgx = new FZBHSBXHBBGX();
                            bbgx.setID(util.getInsertId("FZBHSBXHBBGX"));
                            bbgx.setFZBHSBID(fzbhsbId);
                            bbgx.setSCSJ(bhxhrjbb.getSCSJ());
                            bbgx.setRJBBCODE(bhxhrjbb.getCode());
                            util.coreSave(bbgx);
                            util.coreSave(bhxhrjbb);
                        }
                    }
                }
            }

            if (bhsbxhb != null) {
                if (isMXH) {
                    util.getBHPZorFZSBbyxh(bhsbxhb.getSbxh(), "");
                    bhsbxhb.setSh("M");
                    util.coreSave(bhsbxhb);
                } else {
                    util.coreSave(bhsbxhb);
                }
            }
        }

        fzbhsb.setRjbb(rjbb);

        //保存版卡信息
        if (state.equals("C") && hasSaoma) {
            if (getIntent().hasExtra("BKXX")) {
                List<BKXX> list = (List<BKXX>) getIntent().getSerializableExtra("BKXX");
                for (BKXX bkxx : list) {
                    bkxx.setId(util.getInsertId("BKXX"));
                    bkxx.setZsjid(fzbhsbId);
                    bkxx.setZsjtype("FZBHSB");
                    util.coreSave(bkxx);
                }
                list.clear();
            } else {
                if (Similar) {//新增同类设备的板卡信息保存
                    if (originfzId != null) {
                        if (fzbhsb.getBksl() > 0) {
                            List<Object> bkList = util.getICDOrBKXX(BKXX.class, originfzId + "", "FZBHPZ");
                            if (bkList != null && bkList.size() > 0) {
                                for (Object o : bkList) {
                                    ((BKXX) o).setId(util.getInsertId("BKXX"));
                                    ((BKXX) o).setZsjid(fzbhsbId);
                                    ((BKXX) o).setZsjtype("FZBHPZ");
                                    util.coreSave(o);
                                }
                            }
                            bkList.clear();
                        }
                    }
                   /* if (getIntent().hasExtra("sbsbdm")) {
                        Object o = util.getObjectFromCCXX(getIntent().getStringExtra("sbsbdm"));
                        if (o != null) {//关联到出厂信息库
                            List<BKXX> bkxxList = util.getCCXXBK(getIntent().getStringExtra("sbsbdm"));
                            if (bkxxList != null && bkxxList.size() > 0) {
                                for (BKXX bkxx : bkxxList) {
                                    bkxx.setId(util.getInsertId("BKXX"));
                                    bkxx.setZsjid(fzbhsbId);
                                    bkxx.setZsjtype("FZBHSB");
                                    util.coreSave(bkxx);
                                }
                                bkxxList.clear();
                            }
                        }
                    }*/
                }

            }
        }

//        if (fzbhsb.getWdId() == null || fzbhsb.getWdId().equals("")) {
//            fzbhsb.setWdId(util.getInsertId("CODE"));
//        }

        if (isH) {
            //记录已核对的数据
            if (fzbhsb.getEd_tag().equals("C") || fzbhsb.getEd_tag().equals("M")) {
                fzbhsb.setEd_tag(fzbhsb.getEd_tag() + "H");
            }
        }

        //保存附件信息
        List<WDGL> dzd_img = Details10.instance.dzd_img;
        List<WDGL> yb_img = Details10.instance.yb_img;
        List<WDGL> del_img = Details10.instance.del_img;
        if (Similar) {//只有新增同类设备信息的时候才保存附件中的文件信息
            List<WDGL> wdgl_dzd = Details10.instance.wdgl_dzd;//定值单文档集合
            List<WDGL> wdgl_yb = Details10.instance.wdgl_yb; //压板文档集合
            if (wdgl_dzd != null && wdgl_dzd.size() > 0) {
                for (WDGL wdgl : wdgl_dzd) {
                    if (state.equals("C")) {
                        if (!wdgl.getWdId().equals(fzbhsbId + "")) {
                            wdgl.setId(util.getInsertId("WDGL"));
                            wdgl.setEd_tag("C");
                            wdgl.setWdId(fzbhsbId + "");
                            wdgl.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
                            util.coreSave(wdgl);
                        }
                    }
                }
            }
            if (wdgl_yb != null && wdgl_yb.size() > 0) {
                for (WDGL wdgl : wdgl_yb) {
                    if (state.equals("C")) {
                        if (!wdgl.getWdId().equals(fzbhsbId + "")) {
                            wdgl.setId(util.getInsertId("WDGL"));
                            wdgl.setWdId(fzbhsbId + "");
                            wdgl.setEd_tag("C");
                            wdgl.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
                            util.coreSave(wdgl);

                        }
                    }
                }
            }
        }

        if (dzd_img != null && dzd_img.size() > 0) {
            for (WDGL wdgl : dzd_img) {
                if (wdgl.getEd_tag() != null && wdgl.getEd_tag().equals("C") && wdgl.getId() == null) {
                    wdgl.setId(util.getInsertId("WDGL"));
                    wdgl.setWdId(fzbhsbId + "");
                    wdgl.setCslx("辅助保护设备");
                    wdgl.setCstable("FZBHSB");
                    wdgl.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
                    util.coreSave(wdgl);
                } else {
                    if (Similar && !isH) {//新增同类设备的附件保存
                        if (state.equals("C")) {
                            if (wdgl.getId() == null || !wdgl.getWdId().equals(fzbhsbId + "")) {
                                wdgl.setId(util.getInsertId("WDGL"));
                                wdgl.setWdId(fzbhsbId + "");
                                wdgl.setCstable("FZBHSB");
                                wdgl.setCslx("辅助保护设备");
                                wdgl.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
                                util.coreSave(wdgl);
                            }
                        }
                    }
                }
            }
        }
        if (yb_img != null && yb_img.size() > 0) {
            for (WDGL wdgl : yb_img) {
                if (wdgl.getEd_tag() != null && wdgl.getEd_tag().equals("C") && wdgl.getId() == null) {
                    wdgl.setId(util.getInsertId("WDGL"));
                    wdgl.setCslx("辅助保护设备");
                    wdgl.setWdId(fzbhsbId + "");
                    wdgl.setCstable("FZBHSB");
                    wdgl.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
                    util.coreSave(wdgl);
                } else {
                    if (Similar && !isH) {//新增同类设备的附件保存
                        if (state.equals("C")) {
                            if (wdgl.getId() == null || !wdgl.getWdId().equals(fzbhsbId + "")) {
                                wdgl.setId(util.getInsertId("WDGL"));
                                wdgl.setWdId(fzbhsbId + "");
                                wdgl.setCstable("FZBHSB");
                                wdgl.setCslx("辅助保护设备");
                                wdgl.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
                                util.coreSave(wdgl);
                            }
                        }
                    }
                }
            }
        }
        if (del_img != null && del_img.size() > 0) {
            for (WDGL wdgl : del_img) {
                //删除不需要的附件
                if (wdgl.getEd_tag() != null && wdgl.getEd_tag().equals("DEL")) {
                    File file = new File(Constants.APP_IMG + wdgl.getName());
                    if (file.isFile() && file.exists()) {
                        file.delete();
                    }
                }
                util.coreSave(wdgl);
            }
        }


        if (isC) {
            state = "M";
            util.coreFZHBSB("C", fzbhsb);
        } else {
            util.coreFZHBSB("M", fzbhsb);
        }

        //删除无用的型号和版本
        util.deleteXhAndRjbb();

        if (isH) { //核对时:校验装置
            jyfzbhsb(fzbhsb);
        }

        if (isH) {
            Details8.instance = null;
            ToastUtils.showToast(this, "核对成功");
            sendBroadcast(new Intent("cn.sgg.fzbhsb"));
            sendBroadcast(new Intent("cn.sgg.finishActivity"));

            try {
                Thread.sleep(500);//休眠0.5秒,让其他的Activity结束
                loadingDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //startActivity(new Intent(this, MainActivitys.class));
            if (getIntent().hasExtra("zzlbxz")) {
                String type = getIntent().getStringExtra("zzlbxz");
                if (type.equals("bdz")) {
                    startActivity(new Intent(this, DeviceListActivity.class).putExtra("zzlbxz", "bdz"));
                } else {
                    startActivity(new Intent(this, CaptureActivity.class));
                }
            } else {
                startActivity(new Intent(this, CaptureActivity.class));
            }
            finish();

        } else {
//            Details8.instance = null;
            Similar = false;
            ToastUtils.showToast(this, "操作成功");
            if (coreId != null && !coreId.equals("")) {
                fzbhsb.setId(coreId);
            }
//            isCancel = true;
            isUndo = false;
            sendBroadcast(new Intent("cn.sgg.fzbhsb"));
//            if (isfirstChange) {
//                isCancel = false;
//            } else {
//                isCancel = true;
//            }
            isfirstChange = false;
            isCancel = false;

            sendBroadcast(new Intent("com.lzl.details"));
            loadingDialog.dismiss();
        }
        return true;
    }

    //保护装置校验
    List<SaleAttributeNameVo> nameVoJYX;

    public void jybhsb(BHPZ bhsb) {
        boolean TGJY = true; //通过校验
        if (nameVoJYX != null && nameVoJYX.size() > 0) {
            SaleAttributeNameVo nameVojy = (SaleAttributeNameVo) nameVoJYX.get(0);
            List<SaleAttributeVo> vo2 = nameVojy.getSaleVo();
            for (SaleAttributeVo saleAttributeVo : vo2) {
                if (saleAttributeVo.getValue().equals("设备识别代码")) {
                    if (bhsb.getSfsbm() == null || (bhsb.getSfsbm() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;

                } else if (saleAttributeVo.getValue().equals("实物ID")) {
                    if (bhsb.getSw_id() == null || (bhsb.getSw_id() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("装置名称")) {
                    if (bhsb.getBhmc() == null || (bhsb.getBhmc() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("是否六统一")) {
                    if (bhsb.getSfltysb() == null || (bhsb.getSfltysb() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("制造厂家")) {
                    if (bhsb.getZzcj() == null || (bhsb.getZzcj() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("装置类别")) {
                    if (bhsb.getBhlb() == null || (bhsb.getBhlb() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("装置型号")) {
                    if (bhsb.getBhxh() == null || (bhsb.getBhxh() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("出厂日期")) {
                    if (bhsb.getCcrq() == null || (bhsb.getCcrq() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("是否就地化设备")) {
                    if (bhsb.getSfjdhzz() == null || (bhsb.getSfjdhzz() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("板卡数量")) {
                    if ((bhsb.getBksl() + "") == null || (bhsb.getBksl() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("装置分类")) {
                    if (bhsb.getBhfl() == null || (bhsb.getBhfl() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("装置类型")) {
                    if (bhsb.getBhlx() == null || (bhsb.getBhlx() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("一次设备类型")) {
                    if (bhsb.getYcsblx() == null || (bhsb.getYcsblx() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("一次设备名称")) {
                    if (bhsb.getYcsbmc() == null || (bhsb.getYcsbmc() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("电压等级")) {
                    if ((bhsb.getDydj() + "") == null || (bhsb.getDydj() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("单位名称")) {
                    if (bhsb.getCzgldw() == null || (bhsb.getCzgldw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("调度单位")) {
                    if (bhsb.getDddw() == null || (bhsb.getDddw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("所属屏柜")) {
                    if (bhsb.getSzpg() == null || (bhsb.getSzpg() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("投运日期")) {
                    if (bhsb.getTyrq() == null || (bhsb.getTyrq() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("设计单位")) {
                    if (bhsb.getSjdw() == null || (bhsb.getSjdw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("基建单位")) {
                    if (bhsb.getJjdw() == null || (bhsb.getJjdw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("运行单位")) {
                    if (bhsb.getYxdw() == null || (bhsb.getYxdw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("维护单位")) {
                    if (bhsb.getWhdw() == null || (bhsb.getWhdw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("设备属性")) {
                    if (bhsb.getBhsx() == null || (bhsb.getBhsx() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("套别")) {
                    if (bhsb.getTb() == null || (bhsb.getTb() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("设备状态")) {
                    if (bhsb.getYxzt() == null || (bhsb.getYxzt() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("数据采集方式")) {
                    if (bhsb.getSjcjfs() == null || (bhsb.getSjcjfs() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("出口方式")) {
                    if (bhsb.getCkfs() == null || (bhsb.getCkfs() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("定期检验周期")) {
                    if ((bhsb.getDqjyzq() + "") == null || (bhsb.getDqjyzq() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("跳闸关系")) {
                    if (bhsb.getKgbh() == null || (bhsb.getKgbh() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("名称属性")) {
                    if (bhsb.getBhmcsx() == null || (bhsb.getBhmcsx() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("统计运行时间")) {
                    if (bhsb.getSftjyxsj() == null || (bhsb.getSftjyxsj() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("上次检修时间")) {
                    if (bhsb.getScdqjysj() == null || (bhsb.getScdqjysj() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("电源插件型号")) {
                    if (bhsb.getDycjxh() == null || (bhsb.getDycjxh() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("电源插件更换日期")) {
                    if (bhsb.getDycjghrq() == null || (bhsb.getDycjghrq() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("数字通道数")) {
                    if (bhsb.getSztds() == null || (bhsb.getSztds() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("模拟通道数")) {
                    if (bhsb.getMntds() == null || (bhsb.getMntds() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("额定变比")) {
                    if (bhsb.getEdbb() == null || (bhsb.getEdbb() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("实际变比")) {
                    if (bhsb.getSjbb() == null || (bhsb.getSjbb() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("准确级")) {
                    if (bhsb.getZqj() == null || (bhsb.getZqj() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("CT二次额定电流")) {
                    if (bhsb.getCteceddl() == null || (bhsb.getCteceddl() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("资产编号")) {
                    if (bhsb.getZcbh() == null || (bhsb.getZcbh() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("资产性质")) {
                    if (bhsb.getZcxz() == null || (bhsb.getZcxz() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("资产单位")) {
                    if (bhsb.getZcdw() == null || (bhsb.getZcdw() + "").trim().equals("")) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } //以上保护校验一级项

                else if (saleAttributeVo.getValue().equals("六统一标准版本")) {
                    if (bhsb.getSfltysb().equals("是") && (bhsb.getLtybzbb() == null || (bhsb.getLtybzbb() + "").trim().equals(""))) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("故障录波器类型")) {
                    if (bhsb.getBhlb().equals("故障录波器") && (bhsb.getGzlbqlx() == null || (bhsb.getGzlbqlx() + "").trim().equals(""))) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("装置类别细化")) {
                    if (!bhsb.getBhlb().equals("安全自动装置") && (bhsb.getBhlbxh() == null || (bhsb.getBhlbxh() + "").trim().equals(""))) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("设备类型")) {
                    if (bhsb.getBhlb().equals("安全自动装置") && (bhsb.getBhlbxh() == null || (bhsb.getBhlbxh() + "").trim().equals(""))) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("设备功能配置")) {
                    if (bhsb.getBhlb().equals("安全自动装置") && bhsb.getBhlbxh().equals("频率电压紧急控制装置") && (bhsb.getSbgnpz() == null || (bhsb.getSbgnpz() + "").trim().equals(""))) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("测距形式")) {
                    if (bhsb.getBhlb().equals("故障测距装置") && (bhsb.getCjxx() == null || (bhsb.getCjxx() + "").trim().equals(""))) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("是否接入调度主站")) {
                    if (bhsb.getBhlb().equals("保护故障信息系统子站") && (bhsb.getSfjrzz() == null || (bhsb.getSfjrzz() + "").trim().equals(""))) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("退运日期")) {
                    if (bhsb.getYxzt().equals("退运") && (bhsb.getTcyxsj() == null || (bhsb.getTcyxsj() + "").trim().equals(""))) {
                        TGJY = false;
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("通道类型") || saleAttributeVo.getValue().equals("是否复用") || saleAttributeVo.getValue().equals("通道装置型号")) {
                    //先获取通道
                    if ((bhsb.getBhlb().equals("线路保护") || bhsb.getBhlb().equals("过电压及远方跳闸保护"))) {
                        List<TDXX> load_data = new ArrayList<>();
                        load_data = util.getTDXX(bhsb.getId() + "");
                        if (!load_data.isEmpty() && load_data.size() > 0) {
                            if (saleAttributeVo.getValue().equals("通道类型")) {
                                for (int i = 0; i < load_data.size(); i++) {
                                    if (load_data.get(i).getTdlx() == null || (load_data.get(i).getTdlx() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;

                            } else if (saleAttributeVo.getValue().equals("是否复用")) {
                                for (int i = 0; i < load_data.size(); i++) {
                                    if (load_data.get(i).getSffy() == null || (load_data.get(i).getSffy() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;

                            } else if (saleAttributeVo.getValue().equals("通道装置型号")) {
                                for (int i = 0; i < load_data.size(); i++) {
                                    if (load_data.get(i).getTdzzxh() == null || (load_data.get(i).getTdzzxh() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            }
                        } else {
                            TGJY = false;
                            break;
                        }
                    }
                    continue;

                } else if (saleAttributeVo.getValue().equals("所属安控系统调度命名") || saleAttributeVo.getValue().equals("安控站点类型")) {
                    //先获取安控
                    if ((bhsb.getBhlb().equals("安全自动装置") && bhsb.getBhlbxh().equals("安全稳定控制装置"))) {
                        List<AKXTGX> akxtgx_data = new ArrayList<>();
                        akxtgx_data = util.getAKXTGX(bhsb.getId() + "");
                        if (!akxtgx_data.isEmpty() && akxtgx_data.size() > 0) {
                            if (saleAttributeVo.getValue().equals("所属安控系统调度命名")) {
                                for (int i = 0; i < akxtgx_data.size(); i++) {
                                    String akxtm = "";
                                    if (util.getAKXT(akxtgx_data.get(i).getAkxtid()).size() > 0) {
                                        akxtm = util.getAKXT(akxtgx_data.get(i).getAkxtid()).get(0).getAkxtm() + "";
                                        if (akxtm == null || akxtm.trim().equals("")) {
                                            TGJY = false;
                                            break;
                                        }
                                        continue;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("安控站点类型")) {
                                for (int i = 0; i < akxtgx_data.size(); i++) {
                                    if (akxtgx_data.get(i).getAkzdlx() == null || (akxtgx_data.get(i).getAkzdlx() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            }

                        } else {
                            TGJY = false;
                            break;
                        }
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("连接器数量")
                        || saleAttributeVo.getValue().equals("连接器插座组件制造厂家")
                        || saleAttributeVo.getValue().equals("连接器插座组件型号")) {
                    if (bhsb.getSfjdhzz().equals("是")) {
                        if (saleAttributeVo.getValue().equals("连接器数量")) {
                            if (bhsb.getLjqsl() == null || (bhsb.getLjqsl() + "").trim().equals("")) {
                                TGJY = false;
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("连接器插座组件制造厂家")) {
                            if (bhsb.getLjqzzcj() == null || (bhsb.getLjqzzcj() + "").trim().equals("")) {
                                TGJY = false;
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("连接器插座组件型号")) {
                            if (bhsb.getLjqxh() == null || (bhsb.getLjqxh() + "").trim().equals("")) {
                                TGJY = false;
                                break;
                            }
                            continue;
                        }
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("插头组件型号") || saleAttributeVo.getValue().equals("插头组件编号")
                        || saleAttributeVo.getValue().equals("插头组件制造厂家") || saleAttributeVo.getValue().equals("铅封日期")
                        || saleAttributeVo.getValue().equals("插头组件接口类型") || saleAttributeVo.getValue().equals("接口用途")
                        || saleAttributeVo.getValue().equals("端子箱")) {
                    if (bhsb.getSfjdhzz().equals("是")) {
                        List<Object> ljqList = util.getICDOrBKXX(LJQXX.class, bhsb.getId() + "");
                        if (ljqList != null && ljqList.size() > 0) {
                            //去掉标记删除的连接器
                            for (int i = 0; i < ljqList.size(); i++) {
                                LJQXX ljqxx1 = (LJQXX) ljqList.get(i);
                                if (!isAdd(ljqxx1)) {
                                    ljqList.remove(i);
                                }
                            }

                            if (saleAttributeVo.getValue().equals("插头组件型号")) {
                                for (int i = 0; i < ljqList.size(); i++) {
                                    if (((LJQXX) ljqList.get(i)).getCtzjxh() == null || (((LJQXX) ljqList.get(i)).getCtzjxh() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("插头组件编号")) {
                                for (int i = 0; i < ljqList.size(); i++) {
                                    if (((LJQXX) ljqList.get(i)).getCtzjbh() == null || (((LJQXX) ljqList.get(i)).getCtzjbh() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("插头组件制造厂家")) {
                                for (int i = 0; i < ljqList.size(); i++) {
                                    if (((LJQXX) ljqList.get(i)).getCtzjzzcj() == null || (((LJQXX) ljqList.get(i)).getCtzjzzcj() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("铅封日期")) {
                                for (int i = 0; i < ljqList.size(); i++) {
                                    if (((LJQXX) ljqList.get(i)).getJtzjqfrq() == null || (((LJQXX) ljqList.get(i)).getJtzjqfrq() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("插头组件接口类型")) {
                                for (int i = 0; i < ljqList.size(); i++) {
                                    if (((LJQXX) ljqList.get(i)).getJklx() == null || (((LJQXX) ljqList.get(i)).getJklx() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("接口用途")) {
                                for (int i = 0; i < ljqList.size(); i++) {
                                    if (((LJQXX) ljqList.get(i)).getJkyt() == null || (((LJQXX) ljqList.get(i)).getJkyt() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("端子箱")) {
                                for (int i = 0; i < ljqList.size(); i++) {
                                    if (((LJQXX) ljqList.get(i)).getDzpxx() == null || (((LJQXX) ljqList.get(i)).getDzpxx() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            }
                        }
                        break;
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("ICD文件名称") || saleAttributeVo.getValue().equals("ICD文件版本")
                        || saleAttributeVo.getValue().equals("CRC32验证码") || saleAttributeVo.getValue().equals("MD5校验码")
                        || saleAttributeVo.getValue().equals("ICD文件最终修改时间") || saleAttributeVo.getValue().equals("选配功能")
                        || saleAttributeVo.getValue().equals("专业检测批次")) {
                    if (bhsb.getSfltysb().equals("是") && bhsb.getLtybzbb().equals("2013版")) {
                        List<Object> ltysbxhList = util.getBHRJBBByCode(true, true, bhsb.getId() + "");
                        if (ltysbxhList != null && ltysbxhList.size() > 0) {
                            if (saleAttributeVo.getValue().equals("ICD文件名称")) {
                                for (int i = 0; i < ltysbxhList.size(); i++) {
                                    if (((LTYSBXH) ltysbxhList.get(i)).getWjmc() == null || (((LTYSBXH) ltysbxhList.get(i)).getWjmc() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("ICD文件版本")) {
                                for (int i = 0; i < ltysbxhList.size(); i++) {
                                    if (((LTYSBXH) ltysbxhList.get(i)).getWjbb() == null || (((LTYSBXH) ltysbxhList.get(i)).getWjbb() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("CRC32验证码")) {
                                for (int i = 0; i < ltysbxhList.size(); i++) {
                                    if (((LTYSBXH) ltysbxhList.get(i)).getCrc32() == null || (((LTYSBXH) ltysbxhList.get(i)).getCrc32() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("MD5校验码")) {
                                for (int i = 0; i < ltysbxhList.size(); i++) {
                                    if (((LTYSBXH) ltysbxhList.get(i)).getMd5() == null || (((LTYSBXH) ltysbxhList.get(i)).getMd5() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("ICD文件最终修改时间")) {
                                for (int i = 0; i < ltysbxhList.size(); i++) {
                                    if (((LTYSBXH) ltysbxhList.get(i)).getZzxgsj() == null || (((LTYSBXH) ltysbxhList.get(i)).getZzxgsj() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("选配功能")) {
                                for (int i = 0; i < ltysbxhList.size(); i++) {
                                    if (((LTYSBXH) ltysbxhList.get(i)).getXp() == null || (((LTYSBXH) ltysbxhList.get(i)).getXp() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            } else if (saleAttributeVo.getValue().equals("专业检测批次")) {
                                for (int i = 0; i < ltysbxhList.size(); i++) {
                                    if (((LTYSBXH) ltysbxhList.get(i)).getZyjcpc() == null || (((LTYSBXH) ltysbxhList.get(i)).getZyjcpc() + "").trim().equals("")) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                }
                                continue;
                            }

                        } else {
                            TGJY = false;
                            break;
                        }
                    }
                    continue;
                } else if (saleAttributeVo.getValue().equals("模块名称") || saleAttributeVo.getValue().equals("软件版本")
                        || saleAttributeVo.getValue().equals("校验码") || saleAttributeVo.getValue().equals("生成时间")) {
                    if (bhsb.getSfltysb().equals("是") && bhsb.getLtybzbb().equals("2013版")) { //六统一
                        List<Object> list = util.getBHRJBBByCode(true, true, bhsb.getId() + "");
                        if (list != null && list.size() > 0) {
                            LTYSBXH ltysbxh = (LTYSBXH) list.get(0);
                            if (saleAttributeVo.getValue().equals("软件版本")) {
                                if (ltysbxh.getRjbb() == null || (ltysbxh.getRjbb() + "").trim().equals("")) {
                                    TGJY = false;
                                }
                                continue;
                            }
                            continue;
                        } else {
                            TGJY = false;
                            break;
                        }
                    } else {//非六统一: 分模块和不分模块
                        if (bhsb.getBhlx() != null && bhsb.getBhlx().equals("微机型")) {
                            List<Object> list = util.getBHRJBBByCode(false, false, bhsb.getId() + "");
                            if (list != null && list.size() > 0) {
                                List<BHXHRJBB> rjbbList = new ArrayList<>();
                                for (Object o : list) {
                                    rjbbList.add((BHXHRJBB) o);
                                }

                                //非六统一，不分模块
                                if (rjbbList.size() == 1 && rjbbList.get(0) != null && rjbbList.get(0).getBblx().equals("非六统一，不分模块")) {
                                    if (saleAttributeVo.getValue().equals("软件版本") && (rjbbList.get(0).getBb() == null || (rjbbList.get(0).getBb() + "").trim().equals(""))) {
                                        TGJY = false;
                                        break;
                                    } else if (saleAttributeVo.getValue().equals("校验码") && (rjbbList.get(0).getJym() == null || (rjbbList.get(0).getJym() + "").trim().equals(""))) {
                                        TGJY = false;
                                        break;
                                    } else if (saleAttributeVo.getValue().equals("生成时间") && (rjbbList.get(0).getSCSJ() == null || (rjbbList.get(0).getSCSJ() + "").trim().equals(""))) {
                                        TGJY = false;
                                        break;
                                    }
                                    continue;
                                } else if ((rjbbList.size() > 1 || rjbbList.size() == 1) && rjbbList.get(0) != null
                                        && rjbbList.get(0).getBblx() != null && rjbbList.get(0).getBblx().equals("非六统一，分模块")) {//非六统一,分模块
                                    if (saleAttributeVo.getValue().equals("模块名称")) {
                                        for (int i = 0; i < rjbbList.size(); i++) {
                                            if (rjbbList.get(i).getMkmc() == null || (rjbbList.get(i).getMkmc() + "").trim().equals("")) {
                                                TGJY = false;
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;

                                    } else if (saleAttributeVo.getValue().equals("软件版本")) {
                                        for (int i = 0; i < rjbbList.size(); i++) {
                                            if (rjbbList.get(i).getBb() == null || (rjbbList.get(i).getBb() + "").trim().equals("")) {
                                                TGJY = false;
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("软件校验码版本")) {
                                        for (int i = 0; i < rjbbList.size(); i++) {
                                            if (rjbbList.get(i).getJym() == null || (rjbbList.get(i).getJym() + "").trim().equals("")) {
                                                TGJY = false;
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("生成时间")) {
                                        for (int i = 0; i < rjbbList.size(); i++) {
                                            if (rjbbList.get(i).getSCSJ() == null || (rjbbList.get(i).getSCSJ() + "").trim().equals("")) {
                                                TGJY = false;
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    }
                                }
                            } else {
                                TGJY = false;
                                break;
                            }
                        }
                    }
                }

            }

            if (TGJY) {
                bhsb.setJy("TG"); //改为校验字段
            } else {
                bhsb.setJy("WTG");
            }

            util.coreBHPZ("M", bhsb);
//        if (isC) {
//            state = "M";
//            isC = false;
//            coreId = bhsb.getId();
//            util.coreBHPZ("C", bhsb);
//        } else {
//            util.coreBHPZ("M", bhsb);
//        }
        }
    }

    //过滤要显示的连接器
    public Boolean isAdd(LJQXX ljqxx1) {
        Boolean yy = false; //原因
        if (ljqxx1.getGhyy() == null || "".equals(ljqxx1.getGhyy())) {
            yy = true;
        } else {
            yy = false;
        }
        Boolean sj = false; //原因
        if (ljqxx1.getGhsj() == null || "".equals(ljqxx1.getGhsj())) {
            sj = true;
        } else {
            sj = false;
        }
        Boolean edTag = false; //标记删除
        if (ljqxx1.getED_TAG() != null && ljqxx1.getED_TAG().equals("D")) {
            edTag = false;
        } else {
            edTag = true;
        }


        //sb是非D或者变更时间和变更原因都为null时,返回true, 加载
        if (ljqxx1.getSb() != null) {
            if (!ljqxx1.getSb().equals("D") && yy && sj && edTag) {
                return true;
            } else {
                return false;
            }
        } else if (ljqxx1.getSb() == null && yy && sj && edTag) {
            return true;
        } else {
            return false;
        }
    }


    //保护装置保存
    private boolean savebhsb(boolean isH) {
        util = new DaoUtil(this);
        String originId = null;
        //id单独处理
        if (bhsb.getId() == null) {
            bhsb.setId(util.getInsertId("BHPZ"));
        } else {
            //新增同类设备需要插入一个新的ID
            if (Similar && !isH) {
                originId = bhsb.getId() + "";
                bhsb.setId(util.getInsertId("BHPZ"));
            }

        }
        //赋值保护配置设备识别码
        bhsb.setSw_id(tv_swid.getText().toString());

        Long bhsbId;
        if (bhsb.getId() != null && !bhsb.getId().equals("")) {
            if (bhsb.getEd_tag() != null && bhsb.getEd_tag().equals("C") && coreId != null) {
                bhsb.setId(coreId);
            }
            bhsbId = bhsb.getId();
        } else {
            bhsbId = util.getInsertId("BHPZ");
            bhsb.setId(bhsbId);
        }


        if (Similar) {
            bhsb.setSw_id(tv_swid.getText().toString());
            bhsbId = util.getInsertId("BHPZ");
            bhsb.setId(bhsbId);
        }
        Object obj1 = util.getFZSBBySbsbdm(bhsb.getSw_id());

        Long id1 = null;
        if (obj1 instanceof BHPZ) {
            id1 = ((BHPZ) obj1).getId();
        } else if (obj1 instanceof FZBHSB) {
            id1 = ((FZBHSB) obj1).getId();
        }

        if ("".equals(tv_swid.getText().toString())) {
            ToastUtils.showLongToast(this, "该装置实物ID为空，请补码");
            loadingDialog.dismiss();
            faildType = "装置基本信息";
            return false;
        }

        if (obj1 != null && !id1.equals(bhsb.getId())) {
            ToastUtils.showLongToast(this, "该装置实物ID已被其他装置使用");
            faildType = "装置基本信息";
            loadingDialog.dismiss();
            return false;
        }


        if (Fragment_Type_Base.instance.insetBase()) {
            Log.e("Fragment_Type_Base：", "装置基本信息校验通过");
        } else {
            loadingDialog.dismiss();
            faildType = "装置基本信息";
            if (ismoreMk) {
                faildType = "模块信息";
            }
            handler.sendEmptyMessage(-1);
            return false;
        }


        Fragment_Type_Base.instance.makesure_protect_name(0);
        if (Fragment_Type_Base.instance.protect_name_rep) {
            ToastUtils.showLongToast(this, "该装置名称已被其他装置使用，请手动修改");
            faildType = "装置基本信息";
            loadingDialog.dismiss();
            return false;
        }


        if (Fragment_Type_Inset.instance.CheckInset()) {
            Log.e("Fragment_Type_Inset：", "装置安装及运维信息校验通过");
        } else {
            loadingDialog.dismiss();
            faildType = "安装及运维信息";
            handler.sendEmptyMessage(-1);
            return false;
        }
        if (Fragment_Type_Run.instance.CheckRun()) {
            Log.e("Fragment_Type_Run：", "装置运行信息校验通过");
        } else {
            loadingDialog.dismiss();
            faildType = "运行基本信息";
            handler.sendEmptyMessage(-1);
            return false;
        }
        if (fragment_Type_Road_Ak == null || fragment_Type_Road_Ak != null && !data.contains("通道信息") || data.contains("通道信息") && Fragment_Type_Road_Ak.instance.MakeSureRoad()) {
            Log.e("Fragment_Type_Road_Ak：", "装置通道信息校验通过");
        } else {
            loadingDialog.dismiss();
            faildType = "通道信息";
            handler.sendEmptyMessage(-1);
            return false;
        }
        if (fragment_Type_Ak == null || fragment_Type_Ak != null && !data.contains("安控信息") || data.contains("安控信息") && Fragment_Type_Ak.instance.MakeSureAk()) {
            Log.e("Fragment_Type_Ak：", "装置安控信息校验通过");
        } else {
            loadingDialog.dismiss();
            faildType = "安控信息";
            handler.sendEmptyMessage(-1);
            return false;
        }
        //连接器组合验校
        if (Fragment_Type_Base.instance.sfjdhsb) {
            if (data.contains("连接器信息") && fragment_Type_Ljq != null && Fragment_Type_Ljq.instance.checkljq()) {
                Fragment_Type_Ljq.instance.saveLjq();
                Log.e("Fragment_Type_Ak：", "装置连接器信息校验通过");
            } else {
                bhsb.setLjqsl("");
                bhsb.setLjqzzcj("");
                bhsb.setLjqxh("");

                faildType = "连接器信息";
                handler.sendEmptyMessage(-1);
                loadingDialog.dismiss();
                return false;
            }
        }
        //板卡信息
        if (details4 != null) {
            Log.e("Fragment_Type_Ak：", "装置板卡信息校验通过");
        } else {
            loadingDialog.dismiss();
            faildType = "板卡信息";
            handler.sendEmptyMessage(-1);
            return false;
        }
        //ICD信息
        if (details8 == null || !Fragment_Type_Base.instance.is2013 || Details8.instance.checkIcd()) {
            Log.e("Fragment_Type_Ak：", "装置ICD信息校验通过");
        } else {
            loadingDialog.dismiss();
            faildType = "ICD文件信息";
            handler.sendEmptyMessage(-1);
            return false;
        }
        //CT信息
        if (details6 != null && Details6.instance.checkCT()) {
            Log.e("Fragment_Type_Ak：", "装置CT信息校验通过");
        } else {
            loadingDialog.dismiss();
            faildType = "CT信息";
            handler.sendEmptyMessage(-1);
            return false;
        }
        //资产信息
        if (details7 != null && Details7.instance.saveDetails7BH()) {
            Log.e("Fragment_Type_Ak：", "装置资产信息校验通过");
        } else {
            loadingDialog.dismiss();
            faildType = "资产信息";
            handler.sendEmptyMessage(-1);
            return false;
        }

        bhsb.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
        bhsb.setCzsj(TimeUtil.getCurrentTime());
        bhsb.setCzmc(czcs.getCzmc());
        bhsb.setCzzgdydj(czcs.getCzzgdydj());
        bhsb.setCzgldw(czcs.getGldw());
        bhsb.setBdzlx(czcs.getBdzlx());
        if (bhsb.getSb() == null || bhsb.getSb().equals("")) {
            bhsb.setSb("S");
        }

        if (bhsb.getSb() != null && bhsb.getSb().equals("F"))
            bhsb.setSb("FF");

        //装置基本信息赋值保存
        Fragment_Type_Base.instance.saveBase();
        //装置安装信息赋值保存
        Fragment_Type_Inset.instance.saveInset();
        //装置运行信息赋值保存
        Fragment_Type_Run.instance.saveRun();

        bhsb.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
        bhsb.setCzsj(TimeUtil.getCurrentTime());
        bhsb.setCzmc(czcs.getCzmc());
        bhsb.setCzzgdydj(czcs.getCzzgdydj());
        bhsb.setCzgldw(czcs.getGldw());
        bhsb.setBdzlx(czcs.getBdzlx());

        if (fragment_Type_Ak != null && data.contains("安控信息")) {
            Fragment_Type_Ak.instance.saveAk();
        }
        if (fragment_Type_Road_Ak != null && data.contains("通道信息")) {
            Fragment_Type_Road_Ak.instance.saveRoad();
        }

        if (fragment_Type_Ljq != null && bhsb.getSfjdhzz().equals("是")) { //是否就地化选择 是
//            Fragment_Type_Ljq.instance.deleteLjq2(); //删库
            //就地化保存之前要删除连接器信息,删库
            List<Object> ljqList = util.getICDOrBKXX(LJQXX.class, bhsb.getId() + "");
//                Fragment_Type_Ljq.instance.deleteLjq2();
            for (int i = 0; i < ljqList.size(); i++) {
                LJQXX ljqxx1 = (LJQXX) ljqList.get(i);
                if (ljqxx1.getED_TAG() != null && ljqxx1.getED_TAG().equals("C")) {
                    util.coreLJQ("D", ljqxx1); //删除数据表
                } else { //只更新
                    ljqxx1.setED_TAG("D");
                    ljqxx1.setSb("D");
                    ljqxx1.setGhyy("");
                    util.coreLJQ("M", ljqxx1); //更新数据表
                }
            }
            //然后新增
            Fragment_Type_Ljq.instance.ljqAddBhpzid2(); //保存ljqxxs
        } else { //是否就地化选择 否
            bhsb.setLjqsl("");
            bhsb.setLjqzzcj("");
            bhsb.setLjqxh("");

            //就地化选择"否"保存之前要删除连接器信息,删库
            List<Object> ljqList = util.getICDOrBKXX(LJQXX.class, bhsb.getId() + "");
//                Fragment_Type_Ljq.instance.deleteLjq2();
            for (int i = 0; i < ljqList.size(); i++) {
                LJQXX ljqxx1 = (LJQXX) ljqList.get(i);
                if (ljqxx1.getED_TAG() != null && ljqxx1.getED_TAG().equals("C")) {
                    util.coreLJQ("D", ljqxx1); //删除数据表
                } else { //只更新
                    ljqxx1.setED_TAG("D");
                    ljqxx1.setSb("D");
                    ljqxx1.setGhyy("");
                    util.coreLJQ("M", ljqxx1); //更新数据表
                }
            }
        }


        //板卡信息，仅查看不维护
        if (details4 != null) {
        }
        //ICD信息
        if (details8 != null) {
//            Details8.instance.saveICD(bhsb.getId());
        }
        //CT信息
        if (details6 != null) {
            Details6.instance.saveCT();
        }
        //资产信息（赋值逻辑已经判断时添加）
        if (details7 != null) {
//            Details6.instance.saveCT();
        }
        //保存板卡和附件信息
        if (true) {
            //保存版卡信息
            if (state.equals("C") && hasSaoma) {
                if (getIntent().hasExtra("BKXX")) {
                    List<BKXX> list = (List<BKXX>) getIntent().getSerializableExtra("BKXX");
                    for (BKXX bkxx : list) {
                        bkxx.setId(util.getInsertId("BKXX"));
                        bkxx.setZsjid(bhsb.getId());
                        bkxx.setZsjtype("BHPZ");
                        util.coreSave(bkxx);
                    }
                    list.clear();
                } else {
                    if (Similar) {//新增同类设备的板卡信息保存
                        if (originId != null) {
                            if (bhsb.getBksl() > 0) {
                                List<Object> bkList = util.getICDOrBKXX(BKXX.class, originId + "", "BHPZ");
                                if (bkList != null && bkList.size() > 0) {
                                    for (Object o : bkList) {
                                        ((BKXX) o).setId(util.getInsertId("BKXX"));
                                        ((BKXX) o).setZsjid(bhsb.getId());
                                        ((BKXX) o).setZsjtype("BHPZ");
                                        util.coreSave(o);
                                    }
                                }
                                bkList.clear();
                            }
                        }

                    }
                        /*if (getIntent().hasExtra("sbsbdm")) {
                            Object o = util.getObjectFromCCXX(getIntent().getStringExtra("sbsbdm"));
                            if (o != null) {//关联到出厂信息库
                                List<BKXX> bkxxList = util.getCCXXBK(getIntent().getStringExtra("sbsbdm"));
                                if (bkxxList != null && bkxxList.size() > 0) {
                                    for (BKXX bkxx : bkxxList) {
                                        bkxx.setId(util.getInsertId("BKXX"));
                                        bkxx.setZsjid(bhsb.getId());
                                        bkxx.setZsjtype("BHPZ");
                                        util.coreSave(bkxx);
                                    }
                                    bkxxList.clear();
                                }
                            }
                        }*/
                }
            }

            //放开文件id
//            if (bhsb.getWDID() == null || bhsb.getWDID().equals("")) {
//                bhsb.setWDID(util.getInsertId("CODE"));
//            }

            //保存附件信息
            List<WDGL> dzd_img = Details10.instance.dzd_img;
            List<WDGL> yb_img = Details10.instance.yb_img;
            List<WDGL> del_img = Details10.instance.del_img;

            if (Similar && !isH) {//只有新增同类设备信息的时候才保存附件中的文件信息
                List<WDGL> wdgl_dzd = Details10.instance.wdgl_dzd;//定值单文档集合
                List<WDGL> wdgl_yb = Details10.instance.wdgl_yb; //压板文档集合
                if (wdgl_dzd != null && wdgl_dzd.size() > 0) {
                    for (WDGL wdgl : wdgl_dzd) {
                        if (!wdgl.getWdId().equals(bhsbId)) {
                            wdgl.setId(util.getInsertId("WDGL"));
                            wdgl.setWdId(bhsbId + "");
                            wdgl.setEd_tag("C");
                            wdgl.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
                            util.coreSave(wdgl);
                        }
                    }
                }
                if (wdgl_yb != null && wdgl_yb.size() > 0) {
                    for (WDGL wdgl : wdgl_yb) {
                        if (!wdgl.getWdId().equals(bhsbId + "")) {
                            wdgl.setId(util.getInsertId("WDGL"));
                            wdgl.setWdId(bhsbId + "");
                            wdgl.setEd_tag("C");
                            wdgl.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
                            util.coreSave(wdgl);
                        }
                    }
                }
            }

            if (dzd_img != null && dzd_img.size() > 0) {
                for (WDGL wdgl : dzd_img) {
                    if (wdgl.getEd_tag() != null && wdgl.getEd_tag().equals("C") && wdgl.getId() == null) {
                        wdgl.setId(util.getInsertId("WDGL"));
                        wdgl.setWdId(bhsbId + "");
                        wdgl.setCstable("BHPZ");
                        wdgl.setCslx("保护配置");
                        wdgl.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
                        util.coreSave(wdgl);
                    } else {
                        if (Similar && !isH) {//新增同类设备的附件保存
                            if (wdgl.getId() == null || !wdgl.getWdId().equals(bhsbId + "")) {
                                wdgl.setId(util.getInsertId("WDGL"));
                                wdgl.setWdId(bhsbId + "");
                                wdgl.setCstable("BHPZ");
                                wdgl.setCslx("保护配置");
                                wdgl.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
                                util.coreSave(wdgl);
                            }
                        }
                    }
                }
            }
            if (yb_img != null && yb_img.size() > 0) {
                for (WDGL wdgl : yb_img) {
                    if (wdgl.getEd_tag() != null && wdgl.getEd_tag().equals("C") && wdgl.getId() == null) {
                        wdgl.setId(util.getInsertId("WDGL"));
                        wdgl.setWdId(bhsbId + "");
                        wdgl.setCstable("BHPZ");
                        wdgl.setCslx("保护配置");
                        wdgl.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
                        util.coreSave(wdgl);
                    } else {
                        if (Similar && !isH) {//新增同类设备的附件保存
                            if (wdgl.getId() == null || !wdgl.getWdId().equals(bhsbId + "")) {
                                wdgl.setId(util.getInsertId("WDGL"));
                                wdgl.setWdId(bhsbId + "");
                                wdgl.setCstable("BHPZ");
                                wdgl.setCslx("保护配置");
                                wdgl.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);
                                util.coreSave(wdgl);
                            }
                        }
                    }
                }
            }
            if (del_img != null && del_img.size() > 0) {
                for (WDGL wdgl : del_img) {
                    //删除不需要的附件
                    if (wdgl.getEd_tag() != null && wdgl.getEd_tag().equals("DEL")) {
                        File file = new File(Constants.APP_IMG + wdgl.getName());
                        if (file.isFile() && file.exists()) {
                            file.delete();
                        }
                    }
                    util.coreSave(wdgl);
                }
            }
        }

        util.coreSavaRZXX(bhsb);

        Fragment_Type_Base.instance.isSave = true;

        rzgl = util.getRzxx(bhsbId, "BHPZ");


        if (isH) {
            //记录已核对的数据
            if (bhsb.getEd_tag().equals("C") || bhsb.getEd_tag().equals("M")) {
                bhsb.setEd_tag(bhsb.getEd_tag() + "H");
            }
        }

        if (isC) {
            state = "M";
            isC = false;
            coreId = bhsb.getId();
            util.coreBHPZ("C", bhsb);
        } else {
            util.coreBHPZ("M", bhsb);
        }

        if (isH) { //核对时: 校验装置
            jybhsb(bhsb);
        }

        //删除无用的型号和版本
        util.deleteXhAndRjbb();
        //默认清空左侧问题图标
        data_check.clear();

        if (isH) {
            Details8.instance = null;
            ToastUtils.showToast(this, "核对成功");
            sendBroadcast(new Intent("cn.sgg.fzbhsb"));
            sendBroadcast(new Intent("cn.sgg.finishActivity"));
            try {
                Thread.sleep(500);//休眠0.5秒,让其他的Activity结束
                loadingDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //startActivity(new Intent(this, MainActivitys.class));
//            intent.putExtra("zzlbxz", "bdz");
            if (getIntent().hasExtra("zzlbxz")) {
                String type = getIntent().getStringExtra("zzlbxz") + "";
                if (type.equals("bdz")) {
                    startActivity(new Intent(this, DeviceListActivity.class).putExtra("zzlbxz", "bdz"));
                } else {
                    startActivity(new Intent(this, CaptureActivity.class));
                }
            } else {
                startActivity(new Intent(this, CaptureActivity.class));
            }
            finish();
        } else {
            Similar = false;
//            Details8.instance = null;
            isUndo = false;
            //保存完成后，不能再补码
            setsbm = false;
            ToastUtils.showToast(this, "操作成功");
            sendBroadcast(new Intent("cn.sgg.fzbhsb"));
            loadingDialog.dismiss();
            handler.sendEmptyMessage(0);
        }

        return true;
    }

    //判断页签是否在第一个，校验多模块
    public boolean getFirstType() {
        boolean isbase = false;
        if (adapter.getPosition() == 0) {
            isbase = true;
        }
        return isbase;
    }

    public void check(String checkName, boolean isCheck) {
        if (checkName.equals("装置基本信息-分模块"))
            checkName = checkName.split("-")[0];
        if (isCheck) {
            if (!data_check.contains(checkName)) {
                data_check.add(checkName);
                adapter.setDatas(data, data_check);
            }
        } else {
            if (data_check.contains(checkName)) {
                data_check.remove(checkName);
                adapter.setDatas(data, data_check);
            }
        }
    }


    public void checkBH(String checkName, boolean isCheck) {
        if (checkName.equals("装置基本信息-分模块")) {
            checkName = checkName.split("-")[0];
            if (isCheck) {
                if (!data_check.contains(checkName)) {
                    data_check.add(checkName);
                    adapter.setDatas(data, data_check);
                }
            } else {
                Fragment_Type_Base.instance.isShow = false;
                for (String s : Fragment_Type_Base.instance.map_more.keySet()) {
                    if (s.equals("模块名称") || s.equals("软件版本") || s.equals("校验码") || s.equals("生成时间"))
                        continue;
                    if (Fragment_Type_Base.instance.map_more.get(s).getText().toString().trim().equals("")) {
                        Fragment_Type_Base.instance.isShow = true;
                    }
                }
                if (!Fragment_Type_Base.instance.isShow) {
                    if (data_check.contains(checkName)) {
                        data_check.remove(checkName);
                        adapter.setDatas(data, data_check);
                    }
                }
            }
        } else {
            check(checkName, isCheck);
        }
    }

    boolean isOther = false;

    public void checkFZ(String checkName, boolean isCheck) {
        if (checkName.equals("装置基本信息-分模块")) {
            checkName = checkName.split("-")[0];
            if (isCheck) {
                if (!data_check.contains(checkName)) {
                    data_check.add(checkName);
                    adapter.setDatas(data, data_check);
                }
            } else {
                for (BHXHRJBB bhxhrjbb : Details1.instance.rjbbList) {
                    if (Details1.instance.map_key.containsKey("模块名称") || Details1.instance.map_key.containsKey("模块名称-分模块")) {
                        if (bhxhrjbb.getMkmc() == null || bhxhrjbb.getMkmc().equals("")) {
                            checkFZ("装置基本信息-分模块", true);
                            return;
                        }
                    } else if (Details1.instance.map_key.containsKey("软件版本") || Details1.instance.map_key.containsKey("软件版本-分模块")) {
                        if (bhxhrjbb.getBb() == null || bhxhrjbb.getBb().equals("")) {
                            checkFZ("装置基本信息-分模块", true);
                            return;
                        }
                    } else if (Details1.instance.map_key.containsKey("校验码") || Details1.instance.map_key.containsKey("校验码-分模块")) {
                        if (bhxhrjbb.getJym() == null || bhxhrjbb.getJym().equals("")) {
                            checkFZ("装置基本信息-分模块", true);
                            return;
                        }
                    } else if (Details1.instance.map_key.containsKey("生成时间") || Details1.instance.map_key.containsKey("生成时间-分模块")) {
                        if (bhxhrjbb.getSCSJ() == null || bhxhrjbb.getSCSJ().equals("")) {
                            checkFZ("装置基本信息-分模块", true);
                            return;
                        }
                    }
                }
                if (!isOther) {
                    checkFZ("装置基本信息", false);
                }
            }
        } else {
            check(checkName, isCheck);
            if (isCheck && !checkName.equals("装置基本信息-分模块"))
                isOther = isCheck;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 9) {
//            String datas = data.getStringExtra("");
            czcs = util.getCZCSByGLDW();
            tv_czmc.setText("厂站名称：" + czcs.getCzmc());
            tv_czzgdydj.setText("电压等级（kV）：" + czcs.getCzzgdydj() + "");
        } else if (resultCode == RESULT_OK && requestCode == 98) {
            //扫码返回取值
            Bundle bunde = data.getExtras();
            String value = bunde.getString("result") + "";
            if (value != null && !value.equals("")) {
                tv_swid.setText(value + "");
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (BHorFZ) {
                Fragment_Type_Base.instance.isSave = true;
            } else {
                Details1.instance.isSave = true;
            }
            Details8.instance = null;
            finish();
        }
        return true;
    }

    @Override
    public void finish() {
        if (BHorFZ) {
            Fragment_Type_Base.instance.isSave = true;
        } else {
            Details1.instance.isSave = true;
        }

        Details8.instance = null;
        Details10.instance.dzd_img = null;
        Details10.instance.yb_img = null;
        Details10.instance.del_img = null;
        super.finish();
    }
}
