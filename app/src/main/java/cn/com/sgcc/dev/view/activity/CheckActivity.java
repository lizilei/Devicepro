package cn.com.sgcc.dev.view.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cn.com.sgcc.dev.BaseApplication;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.application.App;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.regist.DecryptKey;
import cn.com.sgcc.dev.model2.vo.DataHolder;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.DesUtils;
import cn.com.sgcc.dev.utils.FileUtils;
import cn.com.sgcc.dev.utils.MyMD5Util;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.ToastUtils;

public class CheckActivity extends BaseActivity implements View.OnClickListener {
    private TextView checkid;
    private TextView checkid_two;
    private TextView checkid_three;
    private Button check;
    private Button made;
    private Button chooseKey;
    private String choosePath;

    static String uuid = "";
    String imei = "";
    String SerialNumber = "";
    String ANDROID_ID = "";
    String MDCODE = "";

    private IDaoUtil util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_check);
        initview();
        setFinishOnTouchOutside(false);
//        txt();
        checkin();
    }

    private void initview() {
        checkid = (TextView) findViewById(R.id.checkid);
        checkid_two = (TextView) findViewById(R.id.checkid_two);
        checkid_three = (TextView) findViewById(R.id.checkid_three);
        check = (Button) findViewById(R.id.check);
        made = (Button) findViewById(R.id.made);
        chooseKey = (Button) findViewById(R.id.chooseKey);
        check.setOnClickListener(this);
        made.setOnClickListener(this);
        chooseKey.setOnClickListener(this);


        check();
        checkid.setText(imei);
        checkid_two.setText(ANDROID_ID);
        checkid_three.setText(SerialNumber);

        checkid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copy();
            }
        });
        checkid_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copy();
            }
        });
        checkid_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copy();
            }
        });

        MDCODE = MyMD5Util.tomd5_16(imei + "China" + SerialNumber + "DKY" + ANDROID_ID + "");
        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) {
                MDCODE = MyMD5Util.tomd5_16(MDCODE + i + "tjfx");
            } else {
                MDCODE = MyMD5Util.tomd5_16(MDCODE + "ChinaDKY" + i);
            }
        }

//        copy();
    }

    private void check() {
        ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
//        ToastUtils.showToast(this,ANDROID_ID);
        SerialNumber = android.os.Build.SERIAL;
        TelephonyManager tm = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        imei = tm.getDeviceId() + "";
        imei.toString();
    }

    private void copy() {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText("imei码：" + imei + "；id码：" + ANDROID_ID + "；serial码：" + SerialNumber);
        ToastUtils.showToast(CheckActivity.this, "设备信息已复制成功，请及时反馈");
    }

    private void checkin() {
        Map<String, String> keyMaps = new HashMap<>();
        final Map<String, String> txtMaps = AppUtils.setTxt(keyMaps, "", false);
        String sbxx = txtMaps.get("sbxx") + "";
        if (sbxx != null && !sbxx.equals("") && !sbxx.equalsIgnoreCase("null")) {
        } else {
            String sbxxsave = "1-" + imei + "-" + ANDROID_ID + "-" + SerialNumber + "-" + MDCODE;
            txtMaps.put("sbxx", sbxxsave + "");
            keyMaps = AppUtils.setTxt(txtMaps, "", true);
        }
    }

    public static String getDeviceId(Context context) {
        StringBuilder deviceId = new StringBuilder();
        // 渠道标志
        deviceId.append("a");
        Log.e("getDeviceId : ", deviceId.toString());
        return deviceId.toString();
    }

    /**
     * 得到全局唯一UUID
     */
    public static String getUUID(Context context) {
        String mShare = PreferenceUtils.getPrefString(context, "uuid", "");
        if (mShare != null) {
            uuid = mShare;
        }
        if (uuid == null && uuid.equals("")) {
            uuid = UUID.randomUUID().toString();
            PreferenceUtils.setPrefString(context, "uuid", uuid + "");
        }
        Log.e("getUUID : " + uuid, "");
        return uuid;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            ToastUtils.showToast(this, "请选择");
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check: //确定按钮
                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (chooseKey.getText().equals("请选择KEY文件")) {
                            ToastUtils.showLongToast(CheckActivity.this, "请先选择KEY文件！");
                            return;
                        }

                        DecryptKey dy = BaseApplication.getInstance().getDecryptKey();
                        if (dy != null) {
                            if (!dy.getANDROID_ID().equals(ANDROID_ID) || !dy.getIMEI().equals(imei) ||
                                    !dy.getSERIALNUMBER().equals(SerialNumber) || !dy.getZCM().equals(MDCODE)) {
                                //key文件内容与设备信息不符，注册失败
                                ToastUtils.showLongToast(CheckActivity.this, "无效的KEY文件,请重新选择！");
                                chooseKey.setText("请选择KEY文件");
                                return;
                            }

                            Map<String, String> keyMap = new HashMap<>();
                            final Map<String, String> txtMap = AppUtils.setTxt(keyMap, "", false);
                            //存到参数里
                            PreferenceUtils.setPrefBoolean(CheckActivity.this, "check", true);
                            txtMap.put("bdms", "是");
                            txtMap.put("syms", "否");
                            Map<String, String> saveMap = AppUtils.setTxt(txtMap, "", true);
                            //存到数据库里
                            String sbxx = "1-" + imei + "-" + ANDROID_ID + "-" + SerialNumber + "-" + MDCODE;

                            PreferenceUtils.setPrefString(CheckActivity.this, "sbxx", sbxx);

                            //将key文件复制到工程目录下
                            FileUtils.copyFile(choosePath, Constants.APP_DEFAULT);

                            //清空保存的变电站信息
                            PreferenceUtils.setPrefString(CheckActivity.this, "czmc", null);
                            PreferenceUtils.setPrefString(CheckActivity.this, "czmcID", null);
                            DataHolder.getInstance().init();

                            //验校跳转
                            startActivity(new Intent(CheckActivity.this, LoginActivity.class));
                            ToastUtils.showToast(CheckActivity.this, "注册成功");
                            PreferenceUtils.setPrefBoolean(CheckActivity.this, "qy_sy", false);
                            finish();
                        }
                    }
                });
                break;
            case R.id.made://试用，生成按钮
                made.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //先存到数据库里
                        String sbxx = "1-" + imei + "-" + ANDROID_ID + "-" + SerialNumber + "-" + MDCODE;
                        PreferenceUtils.setPrefString(CheckActivity.this, "sbxx", sbxx);

                        Map<String, String> keyMap = new HashMap<>();
                        final Map<String, String> txtMap = AppUtils.setTxt(keyMap, "", false);
                        String sycs = txtMap.get("sycs") + "";
                        String symax = txtMap.get("symax") + "";
                        int cs = Integer.parseInt(sycs);
                        int max = Integer.parseInt(symax);
                        if (cs < max) {
                            txtMap.put("bdms", "是");
                            txtMap.put("syms", "是");
                            Map<String, String> saveMap = AppUtils.setTxt(txtMap, "", true);
                            PreferenceUtils.setPrefBoolean(CheckActivity.this, "qy_sy", true);
                            //存到参数里
                            PreferenceUtils.setPrefBoolean(CheckActivity.this, "check", true);
                            //验校跳转
                            startActivity(new Intent(CheckActivity.this, LoginActivity.class));
                            ToastUtils.showToast(CheckActivity.this, "试用模式");
                            finish();
                        } else {
                            ToastUtils.showToast(CheckActivity.this, "试用次数已经达到上限，请输入注册码绑定设备");
                        }
                    }
                });
                break;
            case R.id.chooseKey: //选择key文件
                Intent intent = new Intent(this, ChooseFileActivity.class);
                intent.putExtra("chooseKey", "");
                startActivityForResult(intent, ChooseFileActivity.RESULTCODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ChooseFileActivity.RESULTCODE) {
            choosePath = data.getStringArrayListExtra(ChooseFileActivity.SELECTPATH).get(0);
            //调用解密的方法
            String decryptKey;
            try {
                DesUtils des = new DesUtils("tongjifenxjiamichuli");
                decryptKey = des.decrypt(FileUtils.readAppKey(choosePath));
            } catch (Exception e) {
                ToastUtils.showLongToast(this, "key文件不合法请重新选择！");
                chooseKey.setText("请选择KEY文件");
                return;
            }

            chooseKey.setText("已选中" + choosePath.substring(choosePath.lastIndexOf("/") + 1));
            BaseApplication.getInstance().setDecryptKey(new Gson().fromJson(decryptKey, DecryptKey.class));
        }
    }
}
