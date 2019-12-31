package com.zxing.activity;

import android.animation.Animator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.zxing.camera.CameraManager;
import com.zxing.decoding.CaptureActivityHandler;
import com.zxing.decoding.InactivityTimer;
import com.zxing.view.ViewfinderView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.CommonAdapter;
import cn.com.sgcc.dev.adapter.ViewHolder;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.customeView.LoadingDialog;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.BKXX;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.ICDXX;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.utils.AnimUtil;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.ConversionUtil;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.ScreenUtils;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.BaseActivity;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.DeviceListActivity;
import cn.com.sgcc.dev.view.activity.StationListActivitys;

import static javax.xml.datatype.DatatypeConstants.DURATION;

/**
 * <p>@description:</p>
 * 扫码主界面
 *
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class CaptureActivity extends BaseActivity implements Callback, OnClickListener {

    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private CustomDialog dialog;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;
    private ImageView cancelScan;
    private IDaoUtil util;
    private LoadingDialog progressDialog;
    private TextView tv_camera_type;   //二维码类型
    private LinearLayout ll_camera_type;
    private Button btn_shoudong;
    private Button btn_light;

    private Handler handlers = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
        }
    };

    public static CaptureActivity instance = null;

    private AnimUtil animUtil;
    private float bgAlpha = 1f;
    private boolean bright = false;

    public static final long DURATION = 500;
    private static final float START_ALPHA = 0.7f;
    private static final float END_ALPHA = 1f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.cameras);
        CameraManager.init(getApplication());
        viewfinderView = findViewById(R.id.viewfinder_view);
        cancelScan = findViewById(R.id.cancelScan);
        tv_camera_type = findViewById(R.id.tv_camera_type);
        ll_camera_type = findViewById(R.id.ll_camera_type);
        btn_shoudong = findViewById(R.id.btn_shoudong);
        btn_light = findViewById(R.id.btn_light);
        hasSurface = false;
        isTorchOn = false;
        util = new DaoUtil(this);
        animUtil = new AnimUtil();

        if (getIntent() != null) {
            String changeTitle = getIntent().getStringExtra("request") != null ? getIntent().getStringExtra("request") : "";
            if (changeTitle != null && !changeTitle.equals("zzlbxz") && !changeTitle.equals("")) {
                ll_camera_type.setVisibility(View.GONE);
            }
        }

        IntentFilter filters = new IntentFilter("cn.sgg.finishActivity");
        registerReceiver(receivers, filters);

        progressDialog = new LoadingDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        cancelScan.setOnClickListener(this);
        ll_camera_type.setOnClickListener(this);
        btn_shoudong.setOnClickListener(this);
        btn_light.setOnClickListener(this);

        instance = this;
    }

    //接受详情返回的广播,destroy之前生成的CaptureActivity
    BroadcastReceiver receivers = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            CaptureActivity.this.finish();
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        //initBeepSound();
        vibrate = true;

        inactivityTimer = new InactivityTimer(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onStop() {
        inactivityTimer.shutdown();
        progressDialog.dismiss();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
        unregisterReceiver(receivers);
    }

    private void showPop() {
        final PopupWindow pop = new PopupWindow(this);
        final List<String> list = new ArrayList<>();
        list.add("实物ID");
        list.add("识别码");
//        list.add("连接器");

        View view = LayoutInflater.from(this).inflate(R.layout.pop_view_item1, null);
        ListView listView = view.findViewById(R.id.pop_listView);

        CommonAdapter<String> moreAdapter = new CommonAdapter<String>(this, R.layout.item_list_pop) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.tv_name, item);
                helper.getView(R.id.iv_icon).setVisibility(View.GONE);

                if (helper.getPosition() == list.size() - 1) {
                    helper.getView(R.id.vv_diver).setVisibility(View.GONE);
                }
            }
        };

        listView.setAdapter(moreAdapter);
        moreAdapter.setDatas(list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_camera_type.setText(list.get(i));
                pop.dismiss();
            }
        });

//        pop.setAnimationStyle(R.style.pop_add);
        pop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAsDropDown(ll_camera_type, ConversionUtil.px2dip(this, -26), 0);

        pop.setOnDismissListener(() -> {
//                toggleBright();
        });
    }

    private void toggleBright() {
        // 三个参数分别为：起始值 结束值 时长，那么整个动画回调过来的值就是从0.5f--1f的
        animUtil.setValueAnimator(START_ALPHA, END_ALPHA, DURATION);
        animUtil.addUpdateListener(new AnimUtil.UpdateListener() {
            @Override
            public void progress(float progress) {
                // 此处系统会根据上述三个值，计算每次回调的值是多少，我们根据这个值来改变透明度
                bgAlpha = bright ? progress : (START_ALPHA + END_ALPHA - progress);
                backgroundAlpha(bgAlpha);
            }
        });
        animUtil.addEndListner(new AnimUtil.EndListener() {
            @Override
            public void endUpdate(Animator animator) {
                // 在一次动画结束的时候，翻转状态
                bright = !bright;
            }
        });
        animUtil.startAnimator();
    }

    /**
     * 此方法用于改变背景的透明度，从而达到“变暗”的效果
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        // everything behind this window will be dimmed.
        // 此方法用来设置浮动层，防止部分手机变暗无效
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    /**
     * Handler scan result
     *
     * @param result
     * @param barcode 返回扫码结果
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String resultString = result.getText().toString().trim();
        if ("".equals(resultString)) {
            Toast.makeText(CaptureActivity.this, "未获取任何信息", Toast.LENGTH_LONG).show();
        } else {
            progressDialog.setTitle("正在处理,请稍候！");
            progressDialog.show();
            String changeTitles = "";
            if (getIntent() != null) {
                changeTitles = getIntent().getStringExtra("request");
            }
            if (changeTitles != null && "ljq".equals(changeTitles)) { //来自详情的连接器添加扫码
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("result", resultString);
                resultIntent.putExtras(bundle);
                this.setResult(RESULT_OK, resultIntent);
                CaptureActivity.this.finish();
            } else if (changeTitles != null && "swm".equals(changeTitles)) { //来自详情的实物码添加
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("result", resultString);
                bundle.putString("puttype", "实物码");
                resultIntent.putExtras(bundle);
                this.setResult(RESULT_OK, resultIntent);
                CaptureActivity.this.finish();
            } else if (changeTitles != null && "xqsbm".equals(changeTitles)) { //来自详情的识别码添加
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("result", resultString);
                bundle.putString("puttype", "身份识别码");
                resultIntent.putExtras(bundle);
                this.setResult(RESULT_OK, resultIntent);
                CaptureActivity.this.finish();
            } else if (changeTitles != null && "zzlbxz".equals(changeTitles)) { //来自装置列表新增按钮
                if (isSpecialChar(resultString) || isContainChinese(resultString)) { //不是
                    ToastUtils.showLongToast(CaptureActivity.this, "二维码不合法");
                    CaptureActivity.this.finish();
                } else {
                    if (getIntent() != null) {
                        progressDialog.dismiss();
                        String zztype = getIntent().getStringExtra("zztype") != null ? getIntent().getStringExtra("zztype") : "";
                        zzlbSaoMaDeal(resultString, zztype);
                    }
                }
            } else { //来自首页的扫码  判断是否是设备识别码或实物ID
                if (isSpecialChar(resultString) || isContainChinese(resultString)) { //不是
                    ToastUtils.showLongToast(CaptureActivity.this, "二维码不合法");
                    CaptureActivity.this.finish();
                } else {
                    saoMaDeal(resultString);
                }
            }
        }
    }

    //装置列表新增按钮
    public void zzlbSaoMaDeal(final String type_sbsbdm, final String zztype) {
        instance.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Intent intent = new Intent();
                final BHPZ bhpz;
                boolean isSwid = tv_camera_type.getText().toString().equals("实物ID") ? true : false;
                if (isSwid) {
                    bhpz = util.getBHPZBySwid(type_sbsbdm);
                } else {
                    bhpz = util.getBHPZBySbsbdm(type_sbsbdm);
                }
                if (bhpz != null) {
                    AppUtils.showDialogs(CaptureActivity.this, new OnClickListener() {
                        @Override
                        public void onClick(View v) { //cancel
                            PopupWindow pop = (PopupWindow) v.getTag();
                            pop.dismiss();
                            if (handler != null) {
                                handler.restartPreviewAndDecode();
                            }
                        }
                    }, new OnClickListener() {
                        @Override
                        public void onClick(View v) { //ensure
                            PopupWindow pop = (PopupWindow) v.getTag();
                            pop.dismiss();
                            progressDialog.setTitle("正在操作,请稍候！");
                            progressDialog.show();
                            //找到装置都保存全局唯一的变电站
                            CZCS czcs = util.getCZCSONE(bhpz.getCzmc(), bhpz.getCzgldw(), bhpz.getCzzgdydj());
                            if (czcs != null) {
                                PreferenceUtils.setPrefString(CaptureActivity.this, "czmc", czcs.getCzmc());
                                PreferenceUtils.setPrefString(CaptureActivity.this, "czmcID", czcs.getId() + "");
                            }
                            intent.setClass(CaptureActivity.this, DemoActivity.class);
                            intent.putExtra("SYTYPE", "llbj");
                            intent.putExtra("ZZTYPE", "BHSB");
                            intent.putExtra("BHSB", bhpz);
                            intent.putExtra("STATE", "M");
                            intent.putExtra("sbsbdm", type_sbsbdm);
                            //handlers.sendEmptyMessage(0);
                            startActivity(intent);
                        }
                    });
                } else {
                    final FZBHSB fzbhsb;
                    if (isSwid) {
                        fzbhsb = (FZBHSB) util.getFZSBBySbsbdm(type_sbsbdm, "", "");
                    } else {
                        fzbhsb = (FZBHSB) util.getFZSBBySbsbdm(type_sbsbdm, "", "", "");
                    }
                    if (fzbhsb != null) {
                        AppUtils.showDialogs(CaptureActivity.this, new OnClickListener() {
                            @Override
                            public void onClick(View v) { //cancel
                                PopupWindow pop = (PopupWindow) v.getTag();
                                pop.dismiss();
                                if (handler != null) {
                                    handler.restartPreviewAndDecode();
                                }
                            }
                        }, new OnClickListener() {
                            @Override
                            public void onClick(View v) { //ensure
                                PopupWindow pop = (PopupWindow) v.getTag();
                                pop.dismiss();
                                progressDialog.setTitle("正在操作,请稍候！");
                                progressDialog.show();
                                CZCS czcss = util.getCZCSONE(fzbhsb.getCzmc(), fzbhsb.getCzgldw(), fzbhsb.getCzzgdydj());
                                if (czcss != null) {
                                    PreferenceUtils.setPrefString(CaptureActivity.this, "czmc", czcss.getCzmc());
                                    PreferenceUtils.setPrefString(CaptureActivity.this, "czmcID", czcss.getId() + "");
                                }
                                intent.setClass(CaptureActivity.this, DemoActivity.class);
                                intent.putExtra("SYTYPE", "llbj");
                                intent.putExtra("ZZTYPE", "FZSB");
                                intent.putExtra("FZSB", fzbhsb);
                                intent.putExtra("STATE", "M");
                                intent.putExtra("sbsbdm", type_sbsbdm);
                                //handlers.sendEmptyMessage(0);
                                startActivity(intent);
                            }
                        });
                    } else {//去查出厂信息库----以下是未匹配到装置--------
                        progressDialog.setTitle("正在操作,请稍候！");
                        progressDialog.show();

                        if (isSwid) {
                            intent.setClass(CaptureActivity.this, DemoActivity.class);
                            intent.putExtra("SYTYPE", "xz");
                            intent.putExtra("sbsbdm", type_sbsbdm);
                            intent.putExtra("saoma", "");
                            intent.putExtra("isSwid", isSwid);
                            if ("保护".equals(zztype)) {//保护设备
                                intent.putExtra("ZZTYPE", "BHSB");
                                intent.putExtra("STATE", "C");
                            } else { //辅助设备
                                intent.putExtra("ZZTYPE", "FZSB");
                                intent.putExtra("STATE", "C");
                            }
                            intent.putExtra("zzlbxz", "bdz");
                            startActivity(intent);
                        } else {
                            Object o = util.getObjectFromCCXX(type_sbsbdm);
                            if (o == null) {//未关联到出厂库，跳纯新增的页面
                                intent.setClass(CaptureActivity.this, DemoActivity.class);
                                intent.putExtra("SYTYPE", "xz");
                                intent.putExtra("sbsbdm", type_sbsbdm);
                                intent.putExtra("saoma", "");
                                intent.putExtra("isSwid", isSwid);
                                if ("保护".equals(zztype)) {//保护设备
                                    intent.putExtra("ZZTYPE", "BHSB");
                                    intent.putExtra("STATE", "C");
                                } else { //辅助设备
                                    intent.putExtra("ZZTYPE", "FZSB");
                                    intent.putExtra("STATE", "C");
                                }
                                intent.putExtra("zzlbxz", "bdz");
                                //handlers.sendEmptyMessage(0);
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
                                    boolean isSix = "是".equals(((BHPZ) o).getSfltysb());
                                    boolean is2013 = "2013版".equals(((BHPZ) o).getLtybzbb());
                                    rjbbList = util.getCCXXRJBB(isSix, is2013, type_sbsbdm);

                                    intent.setClass(CaptureActivity.this, DemoActivity.class);
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
                                    intent.putExtra("zzlbxz", "bdz");
                                    //handlers.sendEmptyMessage(0);
                                    startActivity(intent);
                                } else if (o instanceof FZBHSB) {
                                    boolean isSix = "是".equals(((FZBHSB) o).getSfltysb());
                                    boolean is2013 = "2013版".equals(((FZBHSB) o).getLtybzbb());
                                    rjbbList = util.getCCXXRJBB(isSix, is2013, type_sbsbdm);
                                    intent.setClass(CaptureActivity.this, DemoActivity.class);
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
                                    intent.putExtra("zzlbxz", "bdz");
                                    //handlers.sendEmptyMessage(0);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public void saomaDialog() {
        if (dialog != null && dialog.isShowing()) {
            return;
        } else {
            View view = LayoutInflater.from(this).inflate(R.layout.fragment_saoma_dialogs, null);
            final EditText erweima = (EditText) view.findViewById(R.id.shuru);
            dialog = new CustomDialog(this, R.style.dialog_alert_style, 0);
            Button queding = (Button) view.findViewById(R.id.queding);
            Button quxiao = (Button) view.findViewById(R.id.quxiao);
            TextView title = (TextView) view.findViewById(R.id.title);

            String changeTitle = "";
            //动态修改手动输码对话框标题
            if (getIntent() != null) {
                changeTitle = getIntent().getStringExtra("request") != null ? getIntent().getStringExtra("request") : "";

                if (changeTitle != null && "ljq".equals(changeTitle)) {
                    title.setText("请输入连接器二维码");
                    erweima.setKeyListener(DigitsKeyListener.getInstance("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ- ; () = ,* ."));
                    erweima.setFilters(new InputFilter[]{new InputFilter.LengthFilter(40)});
                } else if (changeTitle != null && "swm".equals(changeTitle)) {
                    title.setText("请输入实物ID");
                    erweima.setKeyListener(DigitsKeyListener.getInstance("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
                    erweima.setFilters(new InputFilter[]{new InputFilter.LengthFilter(44)});
                } else if (changeTitle != null && "xqsbm".equals(changeTitle)) {
                    title.setText("请输入设备识别代码");
                    erweima.setKeyListener(DigitsKeyListener.getInstance("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
                    erweima.setFilters(new InputFilter[]{new InputFilter.LengthFilter(44)});
                } else if ("实物ID".equals(tv_camera_type.getText().toString())) {//首页扫一扫实物ID
                    title.setText("请输入实物ID");
                    erweima.setKeyListener(DigitsKeyListener.getInstance("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
                    erweima.setFilters(new InputFilter[]{new InputFilter.LengthFilter(44)});
                } else if ("识别码".equals(tv_camera_type.getText().toString())) {//首页扫一扫设备识别码
                    title.setText("请输入设备识别代码");
                    erweima.setKeyListener(DigitsKeyListener.getInstance("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
                    erweima.setFilters(new InputFilter[]{new InputFilter.LengthFilter(44)});
                }
            }

            //确定
            final String finalChangeTitle = changeTitle;
            queding.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    progressDialog.setTitle("正在处理,请稍候！");
                    progressDialog.show();
                    String input = erweima.getText().toString().trim();
                    if ("".equals(input)) {
                        Toast.makeText(getApplicationContext(), "未输入任何信息", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    } else {
                        if (finalChangeTitle != null && "ljq".equals(finalChangeTitle)) { //来自详情的连接器
                            Intent resultIntent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("result", input);
                            resultIntent.putExtras(bundle);
                            setResult(RESULT_OK, resultIntent);
                            CaptureActivity.this.finish();
                        } else if (finalChangeTitle != null && "swm".equals(finalChangeTitle)) {//来自详情的设备识别代码
                            Intent resultIntent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("result", input);
                            bundle.putString("puttype", "身份识别码");
                            resultIntent.putExtras(bundle);
                            setResult(RESULT_OK, resultIntent);
                            CaptureActivity.this.finish();
                        } else if (finalChangeTitle != null && "xqsbm".equals(finalChangeTitle)) {//来自详情的实物ID添加
                            Intent resultIntent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("result", input);
                            bundle.putString("puttype", "实物码");
                            resultIntent.putExtras(bundle);
                            setResult(RESULT_OK, resultIntent);
                            CaptureActivity.this.finish();
                        } else if (finalChangeTitle != null && "zzlbxz".equals(finalChangeTitle)) {//来自装置列表新增按钮
                            if (getIntent() != null) {
                                String zztype = getIntent().getStringExtra("zztype") != null ? getIntent().getStringExtra("zztype") : "";
                                progressDialog.dismiss();
                                zzlbSaoMaDeal(input, zztype);
                            }
                        } else { //来自首页的扫码
                            saoMaDeal(input);
                        }
                    }
                }
            });

            //取消
            quxiao.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.setContentView(view, new ViewGroup.LayoutParams(ScreenUtils.getScreenWidth(this) * 3 / 4,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    //处理扫码结果
    public void saoMaDeal(final String sbsbdm) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                boolean isSwid = tv_camera_type.getText().toString().equals("实物ID") ? true : false;

                BHPZ bhpz;
                if (isSwid) {
                    bhpz = util.getBHPZBySwid(sbsbdm);
                } else {
                    bhpz = util.getBHPZBySbsbdm(sbsbdm);
                }

                if (bhpz != null) {
                    //找到装置都保存全局唯一的变电站
                    CZCS czcs = util.getCZCSONE(bhpz.getCzmc(), bhpz.getCzgldw(), bhpz.getCzzgdydj());
                    if (czcs != null) {
                        PreferenceUtils.setPrefString(CaptureActivity.this, "czmc", czcs.getCzmc());
                        PreferenceUtils.setPrefString(CaptureActivity.this, "czmcID", czcs.getId() + "");
                    }
                    intent.setClass(CaptureActivity.this, DemoActivity.class);
                    intent.putExtra("SYTYPE", "llbj");
                    intent.putExtra("ZZTYPE", "BHSB");
                    intent.putExtra("BHSB", bhpz);
                    intent.putExtra("STATE", "M");
                    intent.putExtra("sbsbdm", sbsbdm);
                    startActivity(intent);
                } else {
                    FZBHSB fzbhsb;

                    if (isSwid) {
                        fzbhsb = (FZBHSB) util.getFZSBBySbsbdm(sbsbdm, "", "");
                    } else {
                        fzbhsb = (FZBHSB) util.getFZSBBySbsbdm(sbsbdm, "", "", "");
                    }
                    if (fzbhsb != null) {
                        CZCS czcss = util.getCZCSONE(fzbhsb.getCzmc(), fzbhsb.getCzgldw(), fzbhsb.getCzzgdydj());
                        if (czcss != null) {
                            PreferenceUtils.setPrefString(CaptureActivity.this, "czmc", czcss.getCzmc());
                            PreferenceUtils.setPrefString(CaptureActivity.this, "czmcID", czcss.getId() + "");
                        }

                        intent.setClass(CaptureActivity.this, DemoActivity.class);
                        intent.putExtra("SYTYPE", "llbj");
                        intent.putExtra("ZZTYPE", "FZSB");
                        intent.putExtra("FZSB", fzbhsb);
                        intent.putExtra("STATE", "M");
                        intent.putExtra("sbsbdm", sbsbdm);
                        startActivity(intent);
                    } else {//去查出厂信息库----以下是未匹配到装置--------
                        String czmcs = PreferenceUtils.getPrefString(CaptureActivity.this, "czmc", null); //厂站名称
                        if ("".equals(czmcs) || czmcs == null || util.getCZCSByGLDW() == null) {
                            intent.setClass(CaptureActivity.this, StationListActivitys.class);
                            intent.putExtra("SYTYPE", "SAOYISAO1");//扫码无变电站
                            intent.putExtra("sbsbdm", sbsbdm);//将扫到的识别码带走
                            intent.putExtra("isSwid", isSwid);//是否为实物ID
                            startActivity(intent);
                        } else {
                            intent.setClass(CaptureActivity.this, DeviceListActivity.class);
                            intent.putExtra("SYTYPE", "SAOYISAO2");//扫码有变电站
                            intent.putExtra("sbsbdm", sbsbdm);
                            intent.putExtra("isSwid", isSwid);//是否为实物ID
                            startActivity(intent);
                        }
                    }
                }
            }
        }).start();
    }

    //判断是否包含中文
    public static boolean isContainChinese(String str) {
        String reg = "[\u4e00-\u9fa5]";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.find();
    }

    String bianhao = null, xinghao = null;

    //判断是否含有特殊字符  true为包含，false为不包含
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats,
                    characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    // 点击返回键退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
        }
        return true;
    }

    //用户输入的识别码校验

    //手动输入识别码,并返回
    public static final String ACTION = "com.Zxing.InputMyReceiver";

    private boolean isTorchOn = true;  //默认是打开状态

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_camera_type:
                showPop();
                break;
            case R.id.cancelScan:
                CaptureActivity.this.finish();
                break;
            case R.id.btn_shoudong:
                saomaDialog();
                break;
            case R.id.btn_light:
                if (isTorchOn) {
                    isTorchOn = false;  //关灯
                    CameraManager.turnLightOff();
                } else {
                    isTorchOn = true;  //开灯
                    CameraManager.turnLightOn();
                }
                break;
        }
    }
}