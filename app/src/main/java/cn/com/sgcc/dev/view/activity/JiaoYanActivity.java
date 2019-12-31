package cn.com.sgcc.dev.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.internal.bind.TreeTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.GoodsAttrsAdapter;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.LoadingDialog;
import cn.com.sgcc.dev.model2.SearchEntity;
import cn.com.sgcc.dev.model2.vo.JiaoYanData;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.fragment.BhJiaoyanFragment;
import cn.com.sgcc.dev.view.fragment.Fragment_pro_type;
import cn.com.sgcc.dev.view.fragment.FzJiaoyanFragment;

/**
 * <p>@description:</p>
 *  校验设置界面
 * @author lxf
 * @version 1.0.0
 */

public class JiaoYanActivity extends BaseActivity {
    private ImageView app_toolbar_left,app_toolbar_sao1;
    private SearchEntity shEntity;
    private TextView tvSure,tvReset; //确定 重置
    private Button btn_bh, btn_fz; //保护辅助切换
    public JiaoYanData jiaoYanData;
    private List<SaleAttributeNameVo> bhData; //保护校验数据
    private List<SaleAttributeNameVo> fzData;//辅助校验数据
    private LoadingDialog progressDialog;

    public BhJiaoyanFragment bhJyFragment;
    public FzJiaoyanFragment fzJyFragment;
    private FragmentManager fm;
    private FragmentTransaction ft;
    public Fragment mContent;
    public static JiaoYanActivity instance = null;
    private List<SaleAttributeNameVo> itemData2=new ArrayList<SaleAttributeNameVo>(); //校验选中项


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jy);
        instance = this;
        initView();
        initData();
        initEvevt();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
            //startActivity(new Intent(JiaoYanActivity.this, MainActivitys.class));
            JiaoYanActivity.this.finish();
        }
    };

    private void initView() {
        app_toolbar_left = (ImageView) findViewById(R.id.app_toolbar_left);
        app_toolbar_sao1 = (ImageView) findViewById(R.id.app_toolbar_sao1);
        btn_bh = (Button) findViewById(R.id.btn_bh);
        btn_fz = (Button) findViewById(R.id.btn_fz);
        tvSure= (TextView) findViewById(R.id.filter_sure);
        tvReset= (TextView) findViewById(R.id.filter_reset);
        ((TextView) findViewById(R.id.app_toolbar_center)).setText("校验设置");
        shEntity = new SearchEntity();
        shEntity.setZZType("保护");
        bhJyFragment=new BhJiaoyanFragment();
        setDefaultFragment(bhJyFragment);

        progressDialog = new LoadingDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        IntentFilter filters = new IntentFilter("cn.sgg.finishActivity");
        registerReceiver(receivers, filters);

    }

    BroadcastReceiver receivers = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            JiaoYanActivity.this.finish();
        }
    };

    private void initData() {
        //从txt读取数据
        Gson gson = new Gson();
        String s= AppUtils.readJsonFile(Constants.APP_JY);
        jiaoYanData=gson.fromJson(s,JiaoYanData.class);
    }

    // 设置默认显示的fragment
    public void setDefaultFragment(Fragment fragment) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.fl_container, fragment).commit();
        mContent = fragment;
    }

    //不可重复点击
    private long lastClickTime = 0L;
    private static final int FAST_CLICK_DELAY_TIME = 10000;  // 快速点击间隔
    private void initEvevt() {
        //确定
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (System.currentTimeMillis() - lastClickTime < FAST_CLICK_DELAY_TIME){
//                    ToastUtils.showToast(JiaoYanActivity.this,"请勿重复操作");
//                    return;
//                }
//                lastClickTime = System.currentTimeMillis();

                progressDialog.setTitle("保存中");
                progressDialog.show();

                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        String s2 = gson.toJson(jiaoYanData);
                        boolean b1=AppUtils.writeJsonFile(s2,  Constants.APP_JY);
                        boolean b2=AppUtils.saveJYX(JiaoYanActivity.this,jiaoYanData); //写入sp

                        if (b1&&b2){
                            handler.sendEmptyMessage(0);
                        }
                    }
                }).start();
            }
        });

        //重置
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<SaleAttributeNameVo> bhData=jiaoYanData.getBhData();
                List<SaleAttributeNameVo> fzData=jiaoYanData.getFzData();

                if (mContent == bhJyFragment){
                    //保护重置
                    for (int i = 0; i < bhData.size(); i++) {
                        for (int j = 0; j < bhData.get(i).getSaleVo().size(); j++) {
                            if (bhData.get(i).getSaleVo().get(j).getValue().equals("实物ID")||bhData.get(i).getSaleVo().get(j).getValue().equals("装置名称")
                                    ||bhData.get(i).getSaleVo().get(j).getValue().equals("是否六统一")||bhData.get(i).getSaleVo().get(j).getValue().equals("六统一标准版本")
                                    ||bhData.get(i).getSaleVo().get(j).getValue().equals("制造厂家")||bhData.get(i).getSaleVo().get(j).getValue().equals("装置类别")
                                    ||bhData.get(i).getSaleVo().get(j).getValue().equals("装置型号")||bhData.get(i).getSaleVo().get(j).getValue().equals("模块名称")
                                    ||bhData.get(i).getSaleVo().get(j).getValue().equals("软件版本")||bhData.get(i).getSaleVo().get(j).getValue().equals("校验码")
                                    ||bhData.get(i).getSaleVo().get(j).getValue().equals("装置类别细化")||bhData.get(i).getSaleVo().get(j).getValue().equals("设备类型")
                                    ||bhData.get(i).getSaleVo().get(j).getValue().equals("故障录波器类型")||bhData.get(i).getSaleVo().get(j).getValue().equals("测距形式")
                                    ||bhData.get(i).getSaleVo().get(j).getValue().equals("装置分类")||bhData.get(i).getSaleVo().get(j).getValue().equals("装置类型")
                                    ||bhData.get(i).getSaleVo().get(j).getValue().equals("一次设备类型")||bhData.get(i).getSaleVo().get(j).getValue().equals("一次设备名称")
                                    ||bhData.get(i).getSaleVo().get(j).getValue().equals("电压等级")||bhData.get(i).getSaleVo().get(j).getValue().equals("设备状态")
                                    ||bhData.get(i).getSaleVo().get(j).getValue().equals("单位名称")||bhData.get(i).getSaleVo().get(j).getValue().equals("定期检验周期")
                                    ||bhData.get(i).getSaleVo().get(j).getValue().equals("退运日期")||bhData.get(i).getSaleVo().get(j).getValue().equals("通道类型")
                                    ||bhData.get(i).getSaleVo().get(j).getValue().equals("安控系统调度命名")||bhData.get(i).getSaleVo().get(j).getValue().equals("安控站点类型")
                                    ||bhData.get(i).getSaleVo().get(j).getValue().equals("ICD文件名称")){
                                //不重置
                            }else{
                                bhData.get(i).getSaleVo().get(j).setChecked(false);
                            }

                        }
                    }
                    jiaoYanData.setBhData(bhData);

                    BhJiaoyanFragment.instance.reSetJYX1(); //重置界面
                }else if (mContent == fzJyFragment){
                    //辅助重置
                    for (int i = 0; i < fzData.size(); i++) {
                        for (int j = 0; j < fzData.get(i).getSaleVo().size(); j++) {
                            if (fzData.get(i).getSaleVo().get(j).getValue().equals("实物ID")||fzData.get(i).getSaleVo().get(j).getValue().equals("装置名称")
                                    ||fzData.get(i).getSaleVo().get(j).getValue().equals("是否六统一")||fzData.get(i).getSaleVo().get(j).getValue().equals("六统一标准版本")
                                    ||fzData.get(i).getSaleVo().get(j).getValue().equals("制造厂家")||fzData.get(i).getSaleVo().get(j).getValue().equals("装置类别")
                                    ||fzData.get(i).getSaleVo().get(j).getValue().equals("装置型号")||fzData.get(i).getSaleVo().get(j).getValue().equals("模块名称")
                                    ||fzData.get(i).getSaleVo().get(j).getValue().equals("软件版本")||fzData.get(i).getSaleVo().get(j).getValue().equals("校验码")
                                    ||fzData.get(i).getSaleVo().get(j).getValue().equals("装置类别细化")||fzData.get(i).getSaleVo().get(j).getValue().equals("设备类型")
                                    ||fzData.get(i).getSaleVo().get(j).getValue().equals("故障录波器类型")||fzData.get(i).getSaleVo().get(j).getValue().equals("测距形式")
                                    ||fzData.get(i).getSaleVo().get(j).getValue().equals("装置分类")||fzData.get(i).getSaleVo().get(j).getValue().equals("装置类型")
                                    ||fzData.get(i).getSaleVo().get(j).getValue().equals("一次设备类型")||fzData.get(i).getSaleVo().get(j).getValue().equals("一次设备名称")
                                    ||fzData.get(i).getSaleVo().get(j).getValue().equals("电压等级")||fzData.get(i).getSaleVo().get(j).getValue().equals("设备状态")
                                    ||fzData.get(i).getSaleVo().get(j).getValue().equals("单位名称")||fzData.get(i).getSaleVo().get(j).getValue().equals("定期检验周期")
                                    ||fzData.get(i).getSaleVo().get(j).getValue().equals("退运日期")||fzData.get(i).getSaleVo().get(j).getValue().equals("通道类型")
                                    ||fzData.get(i).getSaleVo().get(j).getValue().equals("安控系统调度命名")||fzData.get(i).getSaleVo().get(j).getValue().equals("安控站点类型")
                                    ||fzData.get(i).getSaleVo().get(j).getValue().equals("ICD文件名称")){

                            }else{
                                fzData.get(i).getSaleVo().get(j).setChecked(false);
                            }
                        }
                    }
                    jiaoYanData.setFzData(fzData);

                    FzJiaoyanFragment.instance.reSetJYX2();//重置界面
                }
            }
        });

        //保护装置设置
        btn_bh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shEntity.getZZType().equals("辅助")) {
                    shEntity.setZZType("保护");
                }
                btn_bh.setBackgroundColor(getResources().getColor(R.color.color_main));
                btn_bh.setTextColor(getResources().getColor(R.color.white));
                btn_fz.setBackgroundColor(getResources().getColor(R.color.color_white));
                btn_fz.setTextColor(getResources().getColor(R.color.commonly_text_color9));

                if (bhJyFragment==null){
                    bhJyFragment=new BhJiaoyanFragment();
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    ft.hide(fzJyFragment);
                    ft.add(R.id.fl_container, bhJyFragment);
                    ft.commit();
                    mContent = bhJyFragment;
                }else{
                    switchContent(bhJyFragment);
                    if(BhJiaoyanFragment.instance!=null){
                        BhJiaoyanFragment.instance.reSetJYX1();
                    }
                }

            }
        });

        //辅助装置设置
        btn_fz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shEntity.getZZType().equals("保护")) {
                    shEntity.setZZType("辅助");
                }
                btn_fz.setBackgroundColor(getResources().getColor(R.color.color_main));
                btn_fz.setTextColor(getResources().getColor(R.color.color_white));
                btn_bh.setBackgroundColor(getResources().getColor(R.color.color_white));
                btn_bh.setTextColor(getResources().getColor(R.color.commonly_text_color9));

                if (fzJyFragment==null){
                    fzJyFragment = new FzJiaoyanFragment();
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    ft.hide(bhJyFragment);
                    ft.add(R.id.fl_container, fzJyFragment);
                    ft.commit();
                    mContent = fzJyFragment;
                }else{
                    switchContent(fzJyFragment);
                    if(FzJiaoyanFragment.instance!=null){
                        FzJiaoyanFragment.instance.reSetJYX2();
                    }
                }

            }
        });

        //返回按钮
        app_toolbar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JiaoYanActivity.this.finish();
            }
        });

        //结束按钮X
        app_toolbar_sao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JiaoYanActivity.this, MainActivitys.class));
                sendBroadcast(new Intent("cn.sgg.finishActivity"));
                JiaoYanActivity.this.finish();
            }
        });
    }

    //切换fragment   显示和隐藏
    public void switchContent(Fragment to) {
        if (mContent != to) {
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                ft.hide(mContent).add(R.id.fl_container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                ft.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            mContent = to;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
