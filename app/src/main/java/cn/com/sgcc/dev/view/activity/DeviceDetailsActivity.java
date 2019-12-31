package cn.com.sgcc.dev.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.DeviceDetailsAdapter;
import cn.com.sgcc.dev.adapter.FZDeviceDetailsAdapter;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.customeView.MyDatePickerDialog;
import cn.com.sgcc.dev.customeView.NoScrollViewPager;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.BHSBXHB;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.model2.BKXX;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.FZBHSBXHBBGX;
import cn.com.sgcc.dev.model2.ICDXX;
import cn.com.sgcc.dev.model2.LTYSBXH;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.fragment.DeviceOneFragment;
import cn.com.sgcc.dev.view.fragment.DeviceOtherFragment;
import cn.com.sgcc.dev.view.fragment.DeviceThreeFragment;
import cn.com.sgcc.dev.view.fragment.DeviceTwoFragment;
import cn.com.sgcc.dev.view.fragment.FZDeviceOneFragment;
import cn.com.sgcc.dev.view.fragment.FZDeviceTwoFragment;
import cn.com.sgcc.dev.view.fragment.LjqFragment;

/**
 * <p>@description:</p>
 * 设备详情主界面
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */

public class DeviceDetailsActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private String[] deviceTitle = {"装置信息一", "装置信息二", "装置信息三", "更多信息"};
    private String[] fzDeviceTitle = {"装置信息一", "装置信息二", "更多信息"};

    private TabLayout tabt_indicator;

    private NoScrollViewPager viewPager;
    private TextView save, saoma, app_toolbar_center;

    private ImageView app_toolbar_left;
    InputMyReceiver inputMyReceiver;

    //全局保护装置对象
    public static BHPZ bhpz; //全局保护装置对象
    public static FZBHSB fzbhsb;  //全局辅助保护装置对象
    public static String type;  //标志辅助还是保护   BHSB  FZSB
    public static String state;  //操作状态  C:新增 M:修改
    public static boolean isFromSaoma;  //true:会带一部分信息
    private IDaoUtil util;

    public static DeviceDetailsActivity instance = null;
    public static String sfsbdm = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);
        init();
        initView();
        initData();
        instance = this;
    }

    /**
     * 初始化变量
     */
    private void init() {
        bhpz = null;
        fzbhsb = null;
        isFromSaoma = false;
        type = "";
        state = "";
        sfsbdm = "";
    }

    private void initView() {
        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        util = new DaoUtil(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.CaptureActivity.input");
        inputMyReceiver = new InputMyReceiver();
        registerReceiver(inputMyReceiver, filter);

        app_toolbar_left = (ImageView) findViewById(R.id.app_toolbar_left);
        app_toolbar_left.setVisibility(View.VISIBLE);
        tabt_indicator = (TabLayout) findViewById(R.id.activity_device_details_tabt_indicator);
        tabt_indicator.addOnTabSelectedListener(this);
        viewPager = (NoScrollViewPager) findViewById(R.id.activity_device_details_viewPager);
        save = (TextView) findViewById(R.id.activity_device_details_bottom_tv);
        saoma = (TextView) findViewById(R.id.app_toolbar_sao);
        saoma.setVisibility(View.VISIBLE);
        app_toolbar_center = (TextView) findViewById(R.id.app_toolbar_center);
        app_toolbar_center.setText("装置信息");
        FragmentStatePagerAdapter myCollectAdapter = null;
        state = getIntent().getStringExtra("STATE");

        if (getIntent().hasExtra("ZZTYPE")) {
            type = getIntent().getStringExtra("ZZTYPE");
            if (type.equals("BHSB")) {
                myCollectAdapter = new DeviceDetailsAdapter
                        (getSupportFragmentManager(), deviceTitle, this);
                if (state.equals("M")) {
                    bhpz = (BHPZ) getIntent().getSerializableExtra("BHSB");
                    sfsbdm = bhpz.getSfsbm() + "";
                } else {
                    if (getIntent().hasExtra("saoma")) {
                        isFromSaoma = true;
                        bhpz = (BHPZ) getIntent().getSerializableExtra("BHSB");
                        //*******************************主保护ID******************************************
                        bhpz.setId(util.getInsertId("BHPZ"));
                    } else {
                        isFromSaoma = false;
                        sfsbdm = "";
                        bhpz = new BHPZ();
                        bhpz.setId(util.getInsertId("BHPZ"));
                    }
                    if (getIntent().hasExtra("sbsbdm")) {
                        sfsbdm = getIntent().getStringExtra("sbsbdm");
                    }
                }
            } else if (type.equals("FZSB")) {
                myCollectAdapter = new FZDeviceDetailsAdapter
                        (getSupportFragmentManager(), fzDeviceTitle, this);
                if (state.equals("M")) {
                    fzbhsb = (FZBHSB) getIntent().getSerializableExtra("FZSB");
                    sfsbdm = fzbhsb.getSfsbm() + "";
                } else {
                    if (getIntent().hasExtra("saoma")) {
                        isFromSaoma = true;
                        fzbhsb = (FZBHSB) getIntent().getSerializableExtra("FZSB");
                    } else {
                        isFromSaoma = false;
                        sfsbdm = "";
                        fzbhsb = new FZBHSB();
                    }
                    if (getIntent().hasExtra("sbsbdm")) {
                        sfsbdm = getIntent().getStringExtra("sbsbdm");
                    }
                }
            }
        }

        viewPager.setAdapter(myCollectAdapter);
        //将TabLayout和ViewPager关联起来
        tabt_indicator.setupWithViewPager(viewPager);
        //给Tabs设置适配器
        tabt_indicator.setTabsFromPagerAdapter(myCollectAdapter);

        //viewpager 缓存数量
        viewPager.setOffscreenPageLimit(3);
        //监听viewpager 切换监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
//***********************连接器和ICD动态显示***********************
                if (i == 3 && DeviceDetailsActivity.type.equals("BHSB")) {
                    DeviceOtherFragment.instance.icdxianshi();
                    DeviceOtherFragment.instance.ljqxianshi();
                } else if (i == 2 && DeviceDetailsActivity.type.equals("FZSB")) {
                    DeviceOtherFragment.instance.icdxianshi();
                    DeviceOtherFragment.instance.ljqxianshi();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        //****************保存************************
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("BHSB")) {
                    DeviceOneFragment.instance.saveone();
                    if (DeviceOneFragment.instance.savesuccess) {
                        DeviceTwoFragment.instance.savetwo();
                        if (DeviceTwoFragment.instance.saveSuccess) {
                            DeviceThreeFragment.instance.savethree();
                            if (DeviceThreeFragment.instance.saveSuccess) {
                                viewPager.setCurrentItem(0);
                                DeviceOneFragment.instance.addmore();
                            }
                        }
                    }

                    if (DeviceOneFragment.instance.add_more_success) {
                        if (state.equals("C")) {
                            if (DeviceOneFragment.instance.isLink_device == true && DeviceOtherFragment.instance.newFlag1) {
                                LjqFragment.instance.ljqAddBhpzid(bhpz.getId());  //给连接器添加主保护ID,并保存
                            }
                            //*********保存板卡和ICD*****************
                            if (isFromSaoma && state.equals("C")) {
                                if (getIntent().hasExtra("BKXX")) {
                                    List<BKXX> list = (List<BKXX>) getIntent().getSerializableExtra("BKXX");
                                    for (BKXX bkxx : list) {
                                        bkxx.setId(util.getInsertId("BKXX"));
                                        bkxx.setZsjid(bhpz.getId());
                                        bkxx.setZsjtype("BHPZ");
                                        util.coreSave(bkxx);
                                    }
                                    list.clear();
                                }
                            }
                        } else {
                            //编辑时候连接器的保存
                            if (DeviceOneFragment.instance.isLink_device == true && DeviceOtherFragment.instance.newFlag1) { //是否就地化选择 是
                                LjqFragment.instance.deleteLjq2(); //删库
                                LjqFragment.instance.ljqAddBhpzid2(); //保存ljqxxs
                            } else if (DeviceOneFragment.instance.isLink_device == false && DeviceOtherFragment.instance.newFlag1) { //是否就地化选择 否
                                LjqFragment.instance.deleteLjq2();  //就地化选择"否"保存之前要删除连接器信息,删库
                            }
                        }

                        bhpz.setCzr(PreferenceUtils.getPrefString(DeviceDetailsActivity.this, "userInfo", null).split("-")[0]); //操作人
                        bhpz.setCzsj(TimeUtil.getCurrentTime()); //操作时间

                        //新增时候主保护的ID
                        Long bhpzIDss = null;

                        //身份识别码
                        sfsbdm = DeviceOneFragment.instance.input_id_card.getText().toString().trim();
                        if (bhpz.getEd_tag() != null && !bhpz.getEd_tag().equals("null")
                                && !bhpz.getEd_tag().equals("")) {  //编辑
                            if (!sfsbdm.equals("")) {
                                BHPZ fb = util.getBHPZBySbsbdm(sfsbdm);
                                if (fb != null && !fb.getId().equals(bhpz.getId())) {
                                    if (util.getFZSBBySbsbdm(sfsbdm) != null) {

                                        ToastUtils.showLongToast(DeviceDetailsActivity.this, "该设备识别码已被其他设备使用...");
                                        DeviceDetailsActivity.instance.setpostion(0); //跳到详情1
                                        //注释下面是因: 会导致保存保护设备时主键为空
//                                    bhpz.setId(null);
//                                    bhpz.setEd_tag(null);
//                                    bhpz.setSb(null);
                                        return;
                                    }
                                }
                            }
                            if (bhpz.getEd_tag().equals("S") && !bhpz.getSb().equals("D")) {
                                bhpz.setEd_tag("M");
                            }

                            if (bhpz.getEd_tag().equals("D") && bhpz.getSb().equals("D")) {
                                bhpz.setEd_tag("M");
                                bhpz.setSb("M");
                            }

                            bhpzIDss = bhpz.getId(); //设置主保护ID
                            bhpz.setSfsbm(sfsbdm);
                            util.coreBHPZ("M", bhpz);
                        } else {  //新增
                            if (state.equals("C")) {
                                bhpz.setEd_tag("C");
                                bhpz.setSb("C");
                            } else {
                                bhpz.setEd_tag("M");
                                bhpz.setSb("M");
                            }
                            if (!sfsbdm.equals("")) {
                                BHPZ fb = util.getBHPZBySbsbdm(sfsbdm);
                                if (fb != null && !fb.getId().equals(bhpz.getId())) {
                                    if (util.getFZSBBySbsbdm(sfsbdm) != null) {

                                        ToastUtils.showLongToast(DeviceDetailsActivity.this, "该设备识别码已被其他设备使用...");
                                        DeviceDetailsActivity.instance.setpostion(0);
//                                    bhpz.setId(null);
//                                    bhpz.setEd_tag(null);
//                                    bhpz.setSb(null);
                                        return;
                                    }
                                }
                            }
                            bhpzIDss = bhpz.getId(); //设置主保护ID
                            bhpz.setSfsbm(sfsbdm);
                            util.coreBHPZ(bhpz.getEd_tag(), bhpz);
                        }

                        util.coreSavaRZXX(bhpz);
                        sendBroadcast(new Intent("cn.sgg.fzbhsb"));
//                        ToastUtils.showToast(DeviceDetailsActivity.this, "保存成功");
//                        finish();

                        //**********************************ICD保存*****************************************************
                        if (state.equals("C")) {
                            if (isFromSaoma) {//扫码新增
                                if (DeviceOneFragment.instance.six_one && DeviceOneFragment.instance.six_one_details.equals("2013版")) {
                                    if (getIntent().hasExtra("ICDXX")) {
                                        if (code2 == null) {  //编辑了
                                            //保存新的icd信息
                                            List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code3 + "");
                                            LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                                            ICDXX icdxx1 = getICDXX(ltysbxh);
                                            icdxx1.setID(util.getInsertId("ICDXX"));
                                            icdxx1.setZSJID(bhpzIDss);
                                            icdxx1.setZSJTYPE("BHPZ");
                                            icdxx1.setED_TAG("C");
                                            util.coreSave(icdxx1);
                                            ltysbxhList.clear();

                                            //保存旧的ICD信息 先弹框设置变更时间和变更原因,再保存  dates//变更时间  reasons//变更原因
                                            ICDXX icdxx = (ICDXX) getIntent().getSerializableExtra("ICDXX");
                                            icdChangeDialog2(icdxx, bhpzIDss, "saoma", "BHSB");
                                        } else { //未编辑
                                            ICDXX icdxx = (ICDXX) getIntent().getSerializableExtra("ICDXX");
                                            icdxx.setID(util.getInsertId("ICDXX"));
                                            icdxx.setZSJID(bhpzIDss);
                                            icdxx.setZSJTYPE("BHPZ");
                                            icdxx.setED_TAG("C");
                                            util.coreSave(icdxx);

                                            finish();
                                            ToastUtils.showToast(DeviceDetailsActivity.this, "保存成功");
                                        }
                                    } else {
                                        //新增
                                        if (DeviceOneFragment.instance.six_one && DeviceOneFragment.instance.six_one_details.equals("2013版") && code2 == null) {
                                            //保存新的icd信息
                                            List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code3 + "");
                                            LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                                            ICDXX icdxx1 = getICDXX(ltysbxh);
                                            icdxx1.setID(util.getInsertId("ICDXX"));
                                            icdxx1.setZSJID(bhpzIDss);
                                            icdxx1.setZSJTYPE("BHPZ");
                                            icdxx1.setED_TAG("C");
                                            util.coreSave(icdxx1);
                                            ltysbxhList.clear();

                                            finish();
                                            ToastUtils.showToast(DeviceDetailsActivity.this, "保存成功");
                                        } else {
                                            finish();
                                            ToastUtils.showToast(DeviceDetailsActivity.this, "保存成功");
                                        }
                                    }
                                } else {
                                    //新增
                                    if (DeviceOneFragment.instance.six_one && DeviceOneFragment.instance.six_one_details.equals("2013版") && code2 == null) {
                                        //保存新的icd信息
                                        List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code3 + "");
                                        LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                                        ICDXX icdxx1 = getICDXX(ltysbxh);
                                        icdxx1.setID(util.getInsertId("ICDXX"));
                                        icdxx1.setZSJID(bhpzIDss);
                                        icdxx1.setZSJTYPE("BHPZ");
                                        icdxx1.setED_TAG("C");
                                        util.coreSave(icdxx1);
                                        ltysbxhList.clear();

                                        finish();
                                        ToastUtils.showToast(DeviceDetailsActivity.this, "保存成功");
                                    } else {
                                        finish();
                                        ToastUtils.showToast(DeviceDetailsActivity.this, "保存成功");
                                    }
                                }
                            } else {
                                //新增
                                if (DeviceOneFragment.instance.six_one && DeviceOneFragment.instance.six_one_details.equals("2013版") && code2 == null) {
                                    //保存新的icd信息
                                    List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code3 + "");
                                    LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                                    ICDXX icdxx1 = getICDXX(ltysbxh);
                                    icdxx1.setID(util.getInsertId("ICDXX"));
                                    icdxx1.setZSJID(bhpzIDss);
                                    icdxx1.setZSJTYPE("BHPZ");
                                    icdxx1.setED_TAG("C");
                                    util.coreSave(icdxx1);
                                    ltysbxhList.clear();

                                    finish();
                                    ToastUtils.showToast(DeviceDetailsActivity.this, "保存成功");
                                } else {
                                    finish();
                                    ToastUtils.showToast(DeviceDetailsActivity.this, "保存成功");
                                }
                            }

                        } else {
                            //编辑时候ICD的保存
                            List<Object> icdList = util.getICDOrBKXX(ICDXX.class, DeviceDetailsActivity.bhpz.getId() + "", "BHPZ"); //从库里取ICD信息
                            icdList.toString();
                            if (icdList.size() > 0) {
                                for (int i = 0; i < icdList.size(); i++) {
                                    ICDXX icdxx = (ICDXX) icdList.get(i);
                                    icdxxs2.add(icdxx);
                                    if (isAdd(icdxx)) {
                                        icdxxs.add(icdxx);
                                    }
                                }
                                icdList.clear();
                            }

                            if (DeviceOneFragment.instance.six_one && DeviceOneFragment.instance.six_one_details.equals("2013版")) {
                                if (code2 == null) { //修改过ICD信息
                                    //保存新的icd信息
                                    List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code3 + "");
                                    LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                                    ICDXX icdxx1 = getICDXX(ltysbxh);
                                    icdxx1.setID(util.getInsertId("ICDXX"));
                                    icdxx1.setZSJID(bhpz.getId());
                                    icdxx1.setZSJTYPE("BHPZ");
                                    icdxx1.setED_TAG("C");
                                    util.coreSave(icdxx1);
                                    ltysbxhList.clear();

                                    //保存旧的ICD信息 先弹框设置变更时间和变更原因,再保存  dates//变更时间  reasons//变更原因
                                    if (icdxxs != null && icdxxs.size() > 0) {
                                        ICDXX icdxx = icdxxs.get(0);
                                        icdChangeDialog2(icdxx, bhpz.getId(), "feisaoma", "BHSB");
//                            icdxx.setBGSJ(dates);
//                            icdxx.setBGYY(reasons);
//                            zhikong();
//                            icdxx.setID(util.getInsertId("ICDXX"));
//                            icdxx.setZSJID(fzbhsbId);
//                            icdxx.setZSJTYPE("FZBHSB");
//                            util.coreSave(icdxx);
//                            icdxxs.clear();
                                    } else {
                                        finish();
                                        ToastUtils.showToast(DeviceDetailsActivity.this, "保存成功");
                                    }
                                } else {
                                    finish();
                                    ToastUtils.showToast(DeviceDetailsActivity.this, "保存成功");
                                }

                            } else { //删除库里的ICD信息
                                if (icdxxs2 != null && icdxxs2.size() > 0) {
                                    for (int i = 0; i < icdxxs2.size(); i++) {
                                        ICDXX icdxxss = icdxxs2.get(i);
                                        if (icdxxss.getED_TAG() != null && icdxxss.getED_TAG().equals("C")) {
                                            util.coreICD("D", icdxxss); //删除数据表
                                        } else { //只更新
                                            icdxxss.setED_TAG("D");
                                            icdxxss.setSB("D");
                                            util.coreICD("M", icdxxss); //更新数据表
                                        }

                                    }
                                    icdxxs2.clear();
                                }
                                finish();
                                ToastUtils.showToast(DeviceDetailsActivity.this, "保存成功");
                            }
                        }

//****************************************************ICD保存**********************************************************
                    } else {
                        ToastUtils.showToast(DeviceDetailsActivity.this, "保存失败");
                    }
                } else if (type.equals("FZSB")) {
                    checkFZSB(fzbhsb);
                }
                save.setClickable(true);
            }
        });

        saoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startScan = new Intent(DeviceDetailsActivity.this, CaptureActivity.class);
                startActivityForResult(startScan, 0);
            }
        });

        app_toolbar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setpostion(int i) {
        viewPager.setCurrentItem(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0) {
            //扫码获取的识别码
            String result = data.getExtras().getString("result");
            if (type.equals("FZSB")) {
                sfsbdm = FZDeviceOneFragment.instance.tv_sbsbdm.getText().toString().trim();
            } else {
                sfsbdm = DeviceOneFragment.instance.input_id_card.getText().toString().trim();
            }
            if (sfsbdm.equals("") || sfsbdm.equalsIgnoreCase("null")) {
                if (util.getFZSBBySbsbdm(result) != null) {
                    ToastUtils.showLongToast(this, "该设备识别码已被其他设备使用，换个码试试？");
                    return;
                }
                if (util.getBHPZBySbsbdm(result) != null) {
                    ToastUtils.showLongToast(this, "该设备识别码已被其他设备使用，换个码试试？");
                    return;
                }
                sfsbdm = result;
            } else {
                if (sfsbdm.equals(result)) {
                    ToastUtils.showLongToast(this, "校码成功");
                    return;
                } else {
                    ToastUtils.showLongToast(this, "扫到的码与该设备识别码不一致");
                    return;
                }
            }

            if (type.equals("FZSB")) {
                FZDeviceOneFragment.instance.tv_sbsbdm.setText(sfsbdm);
            } else {
                DeviceOneFragment.instance.input_id_card.setText(sfsbdm);
                DeviceOneFragment.instance.id_card = sfsbdm + "";
            }
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition() == 0) {
            saoma.setVisibility(View.VISIBLE);
        } else {
            saoma.setVisibility(View.GONE);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public class InputMyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //用户填写的识别码
            String inputs = intent.getExtras().getString("input");
            //Toast.makeText(getApplicationContext(), "您填写的识别码: " + inputs, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(inputMyReceiver);
    }

    private void initData() {

    }

    /**
     * 辅助设备保存时检验
     *
     * @param fzbhsb
     */
    private void checkFZSB(FZBHSB fzbhsb) {
        if (FZDeviceOneFragment.instance.isSix) {
            if (FZDeviceOneFragment.instance.ltybzbb != null &&
                    !FZDeviceOneFragment.instance.ltybzbb.equals("")) {
                fzbhsb.setSfltysb("是");
                fzbhsb.setLtybzbb(FZDeviceOneFragment.instance.ltybzbb);
            } else {
                viewPager.setCurrentItem(0);
                ToastUtils.showLongToast(this, "请选择六统一标准版本");
                return;
            }
        } else {
            fzbhsb.setSfltysb("否");
        }
        if (FZDeviceOneFragment.instance.dwmc != null &&
                !FZDeviceOneFragment.instance.dwmc.equals("") &&
                !FZDeviceOneFragment.instance.dwmc.equals("必填项")) {
            fzbhsb.setCzgldw(FZDeviceOneFragment.instance.dwmc);
        } else {
            viewPager.setCurrentItem(0);
            ToastUtils.showLongToast(this, "请选择单位名称");
            return;
        }

        if (FZDeviceOneFragment.instance.dddw != null &&
                !FZDeviceOneFragment.instance.dddw.equals("") &&
                !FZDeviceOneFragment.instance.dddw.equals("必填项")) {
            fzbhsb.setDddw(FZDeviceOneFragment.instance.dddw);
        } else {
            viewPager.setCurrentItem(0);
            ToastUtils.showLongToast(this, "请选择调度单位");
            return;
        }
        if (FZDeviceOneFragment.instance.czmc != null &&
                !FZDeviceOneFragment.instance.czmc.equals("") &&
                !FZDeviceOneFragment.instance.czmc.equals("必填项")) {
            fzbhsb.setCzmc(FZDeviceOneFragment.instance.czmc);
        } else {
            viewPager.setCurrentItem(0);
            ToastUtils.showLongToast(this, "请选择厂站名称");
            return;
        }
        if (FZDeviceOneFragment.instance.ycsblx != null &&
                !FZDeviceOneFragment.instance.ycsblx.equals("") &&
                !FZDeviceOneFragment.instance.ycsblx.equals("必填项")) {
            fzbhsb.setYcsblx(FZDeviceOneFragment.instance.ycsblx);
        } else {
            viewPager.setCurrentItem(0);
            ToastUtils.showLongToast(this, "请选择一次设备类型");
            return;
        }

        if (FZDeviceOneFragment.instance.ycsbmc != null &&
                !FZDeviceOneFragment.instance.ycsbmc.equals("") &&
                !FZDeviceOneFragment.instance.ycsbmc.equals("必填项")) {
            fzbhsb.setYcsbmc(FZDeviceOneFragment.instance.ycsbmc);
        } else {
            viewPager.setCurrentItem(0);
            ToastUtils.showLongToast(this, "请选择一次设备名称");
            return;
        }

        if (FZDeviceOneFragment.instance.dydj != null &&
                !FZDeviceOneFragment.instance.dydj.equals("") &&
                !FZDeviceOneFragment.instance.dydj.equals("必填项")) {
            CZCS czcs = util.getCZCSByGLDW();
            int czzgdydj = (int) czcs.getCzzgdydj();
            int dydj = Integer.parseInt(FZDeviceOneFragment.instance.dydj);
            if (dydj > czzgdydj) {
                viewPager.setCurrentItem(0);
                ToastUtils.showLongToast(this, "电压等级不能大于当前厂站最高电压等级");
                return;
            } else {
                fzbhsb.setDydj(dydj);
            }
        } else {
            viewPager.setCurrentItem(0);
            ToastUtils.showLongToast(this, "请选择电压等级");
            return;
        }

        fzbhsb.setCzr(PreferenceUtils.getPrefString(this, "userInfo", null).split("-")[0]);


        if (FZDeviceOneFragment.instance.bhlb != null &&
                !FZDeviceOneFragment.instance.bhlb.equals("") &&
                !FZDeviceOneFragment.instance.bhlb.equals("必填项")) {
            fzbhsb.setFzsblx(FZDeviceOneFragment.instance.bhlb);
            if (FZDeviceOneFragment.instance.zzcj != null &&
                    !FZDeviceOneFragment.instance.zzcj.equals("") &&
                    !FZDeviceOneFragment.instance.zzcj.equals("必填项")) {
                fzbhsb.setCj(FZDeviceOneFragment.instance.zzcj);
            } else {
                viewPager.setCurrentItem(0);
                ToastUtils.showLongToast(this, "请选择制造厂家");
                return;
            }
            if (FZDeviceOneFragment.instance.bhxh != null &&
                    !FZDeviceOneFragment.instance.bhxh.equals("") &&
                    !FZDeviceOneFragment.instance.bhxh.equals("必填项")) {
                fzbhsb.setSbxh(FZDeviceOneFragment.instance.bhxh);
            } else {
                viewPager.setCurrentItem(0);
                ToastUtils.showLongToast(this, "请选择设备型号");
                return;
            }

            if (FZDeviceOneFragment.instance.bhlb != null &&
                    FZDeviceOneFragment.instance.bhlb.equals("收发信机")) {
                fzbhsb.setZbtdjgx(FZDeviceOneFragment.instance.zbtdjgx);
                fzbhsb.setTdpl(Integer.parseInt(FZDeviceOneFragment.instance.tdpl + ""));
            } else if (FZDeviceOneFragment.instance.bhlb != null &&
                    FZDeviceOneFragment.instance.bhlb.equals("合并单元") ||
                    FZDeviceOneFragment.instance.bhlb.equals("合并单元智能终端集成")) {
                if (FZDeviceOneFragment.instance.dsfs != null &&
                        !FZDeviceOneFragment.instance.dsfs.equals("") &&
                        !FZDeviceOneFragment.instance.dsfs.equals("请选择")) {
                    fzbhsb.setDsfs(FZDeviceOneFragment.instance.dsfs);
                } else {
                    viewPager.setCurrentItem(0);
                    ToastUtils.showLongToast(this, "请选择对时方式");
                    return;
                }
                if (FZDeviceOneFragment.instance.gddy != null &&
                        !FZDeviceOneFragment.instance.gddy.equals("") &&
                        !FZDeviceOneFragment.instance.gddy.equals("请选择")) {
                    fzbhsb.setGddy(FZDeviceOneFragment.instance.gddy);
                } else {
                    viewPager.setCurrentItem(0);
                    ToastUtils.showLongToast(this, "请选择供电电源");
                    return;
                }
                if (FZDeviceOneFragment.instance.zzsx != null &&
                        !FZDeviceOneFragment.instance.zzsx.equals("") &&
                        !FZDeviceOneFragment.instance.zzsx.equals("请选择")) {
                    fzbhsb.setZzsx(FZDeviceOneFragment.instance.zzsx);
                } else {
                    viewPager.setCurrentItem(0);
                    ToastUtils.showLongToast(this, "请选择装置属性");
                    return;
                }
                fzbhsb.setFsgxsl(FZDeviceOneFragment.instance.fsgxsl + "#" + FZDeviceOneFragment.instance.jsgxsl);
                if (FZDeviceOneFragment.instance.gxjkms != null &&
                        !FZDeviceOneFragment.instance.gxjkms.equals("") &&
                        !FZDeviceOneFragment.instance.gxjkms.equals("请选择")) {
                    fzbhsb.setGxjkms(FZDeviceOneFragment.instance.gxjkms);
                } else {
                    viewPager.setCurrentItem(0);
                    ToastUtils.showLongToast(this, "请选择光纤接口模式");
                    return;
                }
                if (FZDeviceOneFragment.instance.hbdygn != null &&
                        !FZDeviceOneFragment.instance.hbdygn.equals("") &&
                        !FZDeviceOneFragment.instance.hbdygn.equals("请选择")) {
                    fzbhsb.setHbdygn(FZDeviceOneFragment.instance.hbdygn);
                } else {
                    viewPager.setCurrentItem(0);
                    ToastUtils.showLongToast(this, "请选择合并单元功能");
                    return;
                }
                if (FZDeviceOneFragment.instance.hgqlx != null &&
                        !FZDeviceOneFragment.instance.hgqlx.equals("") &&
                        !FZDeviceOneFragment.instance.hgqlx.equals("请选择")) {
                    fzbhsb.setHgqlx(FZDeviceOneFragment.instance.hgqlx);
                } else {
                    viewPager.setCurrentItem(0);
                    ToastUtils.showLongToast(this, "请选择互感器类型");
                    return;
                }
                if (FZDeviceOneFragment.instance.bhlb.equals("合并单元智能终端集成")) {
                    if (FZDeviceOneFragment.instance.znzdgn != null &&
                            !FZDeviceOneFragment.instance.znzdgn.equals("") &&
                            !FZDeviceOneFragment.instance.znzdgn.equals("请选择")) {
                        fzbhsb.setZnzdgn(FZDeviceOneFragment.instance.znzdgn);
                    } else {
                        viewPager.setCurrentItem(0);
                        ToastUtils.showLongToast(this, "请选择智能终端功能");
                        return;
                    }
                }
            } else if (FZDeviceOneFragment.instance.bhlb != null &&
                    FZDeviceOneFragment.instance.bhlb.equals("智能终端")) {
                fzbhsb.setFsgxsl(FZDeviceOneFragment.instance.fsgxsl + "#" + FZDeviceOneFragment.instance.jsgxsl);
                if (FZDeviceOneFragment.instance.gxjkms != null &&
                        !FZDeviceOneFragment.instance.gxjkms.equals("") &&
                        !FZDeviceOneFragment.instance.gxjkms.equals("请选择")) {
                    fzbhsb.setGxjkms(FZDeviceOneFragment.instance.gxjkms);
                } else {
                    viewPager.setCurrentItem(0);
                    ToastUtils.showLongToast(this, "请选择光纤接口模式");
                    return;
                }
                if (FZDeviceOneFragment.instance.znzdgn != null &&
                        !FZDeviceOneFragment.instance.znzdgn.equals("") &&
                        !FZDeviceOneFragment.instance.znzdgn.equals("请选择")) {
                    fzbhsb.setZnzdgn(FZDeviceOneFragment.instance.znzdgn);
                } else {
                    viewPager.setCurrentItem(0);
                    ToastUtils.showLongToast(this, "请选择智能终端功能");
                    return;
                }
            } else if (FZDeviceOneFragment.instance.bhlb != null &&
                    FZDeviceOneFragment.instance.bhlb.equals("交换机")) {
                if (FZDeviceOneFragment.instance.jhjlx != null &&
                        !FZDeviceOneFragment.instance.jhjlx.equals("") &&
                        !FZDeviceOneFragment.instance.jhjlx.equals("请选择")) {
                    fzbhsb.setJhjlx(FZDeviceOneFragment.instance.jhjlx);
                } else {
                    viewPager.setCurrentItem(0);
                    ToastUtils.showLongToast(this, "请选择交换机类型");
                    return;
                }
                if (FZDeviceOneFragment.instance.jhjgn != null &&
                        !FZDeviceOneFragment.instance.jhjgn.equals("") &&
                        !FZDeviceOneFragment.instance.jhjgn.equals("请选择")) {
                    fzbhsb.setJhjgn(FZDeviceOneFragment.instance.jhjgn);
                } else {
                    viewPager.setCurrentItem(0);
                    ToastUtils.showLongToast(this, "请选择交换机功能");
                    return;
                }
                fzbhsb.setJhjjls(Integer.parseInt(FZDeviceOneFragment.instance.jhjjls + ""));
                if (FZDeviceOneFragment.instance.gxjkms != null &&
                        !FZDeviceOneFragment.instance.gxjkms.equals("") &&
                        !FZDeviceOneFragment.instance.gxjkms.equals("请选择")) {
                    fzbhsb.setGxjkms(FZDeviceOneFragment.instance.gxjkms);
                } else {
                    viewPager.setCurrentItem(0);
                    ToastUtils.showLongToast(this, "请选择光纤接口模式");
                    return;
                }
                fzbhsb.setSfrstp(FZDeviceOneFragment.instance.sfrstp);
                fzbhsb.setSfds(FZDeviceOneFragment.instance.sfds);
                fzbhsb.setSfzb(FZDeviceOneFragment.instance.sfzb);
                fzbhsb.setSfsnmp(FZDeviceOneFragment.instance.sfsnmp);
                fzbhsb.setSfiec(FZDeviceOneFragment.instance.sfiec);
            }
        } else {
            viewPager.setCurrentItem(0);
            ToastUtils.showLongToast(this, "请选择保护类别");
            return;
        }

        if (FZDeviceOneFragment.instance.bhmc != null &&
                !FZDeviceOneFragment.instance.bhmc.equals("") &&
                !FZDeviceOneFragment.instance.bhmc.equals("必填项")) {
            fzbhsb.setSbmc(FZDeviceOneFragment.instance.bhmc);
        } else {
            viewPager.setCurrentItem(0);
            ToastUtils.showLongToast(this, "请点击生成保护名称");
            return;
        }

        if (FZDeviceOneFragment.instance.bhfl != null &&
                !FZDeviceOneFragment.instance.bhfl.equals("") &&
                !FZDeviceOneFragment.instance.bhfl.equals("必填项")) {
            fzbhsb.setBhfl(FZDeviceOneFragment.instance.bhfl);
        } else {
            viewPager.setCurrentItem(0);
            ToastUtils.showLongToast(this, "请选择保护分类");
            return;
        }
        if (FZDeviceOneFragment.instance.bhlx != null &&
                !FZDeviceOneFragment.instance.bhlx.equals("") &&
                !FZDeviceOneFragment.instance.bhlx.equals("必填项")) {
            fzbhsb.setBhlx(FZDeviceOneFragment.instance.bhlx);
        } else {
            viewPager.setCurrentItem(0);
            ToastUtils.showLongToast(this, "请选择保护类型");
            return;
        }

        if (FZDeviceOneFragment.instance.ltysbxh != null) {
            if (FZDeviceOneFragment.instance.ltysbxh.getRjbb() == null ||
                    FZDeviceOneFragment.instance.ltysbxh.getRjbb().equals("") ||
                    FZDeviceOneFragment.instance.ltysbxh.getRjbb().equals("必填项")) {
                viewPager.setCurrentItem(0);
                ToastUtils.showLongToast(this, "请选择软件版本");
                return;
            }

            if (FZDeviceOneFragment.instance.ltysbxh.getWjmc() == null ||
                    FZDeviceOneFragment.instance.ltysbxh.getWjmc().equals("") ||
                    FZDeviceOneFragment.instance.ltysbxh.getWjmc().equals("必填项")) {
                viewPager.setCurrentItem(0);
                ToastUtils.showLongToast(this, "请选择ICD文件名");
                return;
            }
        }

        if (FZDeviceTwoFragment.instance.tyrq != null &&
                !FZDeviceTwoFragment.instance.tyrq.equals("") &&
                !FZDeviceTwoFragment.instance.tyrq.equals("必填项")) {
            fzbhsb.setTyrq(TimeUtil.formatString(FZDeviceTwoFragment.instance.tyrq));
        } else {
            viewPager.setCurrentItem(1);
            ToastUtils.showLongToast(this, "请选择投运日期");
            return;
        }

        if (FZDeviceOneFragment.instance.bhtb != null &&
                !FZDeviceOneFragment.instance.bhtb.equals("") &&
                !FZDeviceOneFragment.instance.bhtb.equals("请选择")) {
            fzbhsb.setTb(FZDeviceOneFragment.instance.bhtb);
        }
        fzbhsb.setDqjyzq(Float.parseFloat(FZDeviceTwoFragment.instance.dqjyzq + ""));
        if (FZDeviceTwoFragment.instance.yxdw != null &&
                !FZDeviceTwoFragment.instance.yxdw.equals("") &&
                !FZDeviceTwoFragment.instance.yxdw.equals("必填项")) {
            fzbhsb.setYxdw(FZDeviceTwoFragment.instance.yxdw);
        } else {
            viewPager.setCurrentItem(1);
            ToastUtils.showLongToast(this, "请选择运行单位");
            return;
        }
        if (FZDeviceTwoFragment.instance.whdw != null &&
                !FZDeviceTwoFragment.instance.whdw.equals("") &&
                !FZDeviceTwoFragment.instance.whdw.equals("必填项")) {
            fzbhsb.setWhdw(FZDeviceTwoFragment.instance.whdw);
        } else {
            viewPager.setCurrentItem(1);
            ToastUtils.showLongToast(this, "请选择维护单位");
            return;
        }
        if (FZDeviceTwoFragment.instance.sjdw != null &&
                !FZDeviceTwoFragment.instance.sjdw.equals("") &&
                !FZDeviceTwoFragment.instance.sjdw.equals("必填项")) {
            fzbhsb.setSjdw(FZDeviceTwoFragment.instance.sjdw);
        } else {
            viewPager.setCurrentItem(1);
            ToastUtils.showLongToast(this, "请选择设计单位");
            return;
        }
        if (FZDeviceTwoFragment.instance.jjdw != null &&
                !FZDeviceTwoFragment.instance.jjdw.equals("") &&
                !FZDeviceTwoFragment.instance.jjdw.equals("必填项")) {
            fzbhsb.setJjdw(FZDeviceTwoFragment.instance.jjdw);
        } else {
            viewPager.setCurrentItem(1);
            ToastUtils.showLongToast(this, "请选择基建单位");
            return;
        }
        if (FZDeviceTwoFragment.instance.yxzt != null &&
                !FZDeviceTwoFragment.instance.yxzt.equals("") &&
                !FZDeviceTwoFragment.instance.yxzt.equals("必填项")) {
            fzbhsb.setYxzt(FZDeviceTwoFragment.instance.yxzt);
        } else {
            viewPager.setCurrentItem(1);
            ToastUtils.showLongToast(this, "请选择运行状态");
            return;
        }
        if (FZDeviceTwoFragment.instance.yxzt != null &&
                FZDeviceTwoFragment.instance.yxzt.equals("退运")) {
            if (FZDeviceTwoFragment.instance.tcyxsj != null &&
                    !FZDeviceTwoFragment.instance.tcyxsj.equals("") &&
                    !FZDeviceTwoFragment.instance.tcyxsj.equals("必填项")) {
                fzbhsb.setTcyxsj(TimeUtil.formatString(FZDeviceTwoFragment.instance.tcyxsj));
            } else {
                viewPager.setCurrentItem(1);
                ToastUtils.showLongToast(this, "请选择退出运行时间");
                return;
            }
        }

        if (FZDeviceTwoFragment.instance.sbsx != null &&
                !FZDeviceTwoFragment.instance.sbsx.equals("") &&
                !FZDeviceTwoFragment.instance.sbsx.equals("必填项")) {
            fzbhsb.setSbsx(FZDeviceTwoFragment.instance.sbsx);
        } else {
            viewPager.setCurrentItem(1);
            ToastUtils.showLongToast(this, "请选择设备属性");
            return;
        }

        if (FZDeviceTwoFragment.instance.scjxsj != null &&
                !FZDeviceTwoFragment.instance.scjxsj.equals("") &&
                !FZDeviceTwoFragment.instance.scjxsj.equals("必填项")) {
            fzbhsb.setScdqjysj(TimeUtil.formatString(FZDeviceTwoFragment.instance.scjxsj));
        } else {
            viewPager.setCurrentItem(1);
            ToastUtils.showLongToast(this, "请选择上次检修时间");
            return;
        }
        if (!FZDeviceTwoFragment.instance.ccrq.equals("请选择")) {
            fzbhsb.setCcrq(TimeUtil.formatString(FZDeviceTwoFragment.instance.ccrq));
        }
        if (!FZDeviceTwoFragment.instance.ccbh.equals("请选择")) {
            fzbhsb.setCcbh(FZDeviceTwoFragment.instance.ccbh);
        }
        if (!FZDeviceTwoFragment.instance.zcdw.equals("请选择")) {
            fzbhsb.setZcdw(FZDeviceTwoFragment.instance.zcdw);
        }
        if (!FZDeviceTwoFragment.instance.zcxz.equals("请选择")) {
            fzbhsb.setZcxz(FZDeviceTwoFragment.instance.zcxz);
        }
        if (!FZDeviceTwoFragment.instance.zcbh.equals("请选择")) {
            fzbhsb.setZcbh(FZDeviceTwoFragment.instance.zcbh);
        }
        fzbhsb.setBksl(Integer.parseInt(FZDeviceTwoFragment.instance.bksl + ""));
        fzbhsb.setCzsj(TimeUtil.getCurrentTime());
        fzbhsb.setBdzlx(FZDeviceOneFragment.instance.czlx);
        fzbhsb.setSbdw(fzbhsb.getCzgldw());
        fzbhsb.setCzzgdydj(Long.parseLong(FZDeviceOneFragment.instance.czzgdydj));
        fzbhsb.setUsegddata(FZDeviceOneFragment.instance.usegddata.equals("是") ? "true" : "false");

        Long fzbhsbId;
        String rjbb = "";
        if (fzbhsb.getId() == null || fzbhsb.getId().equals("")) {
            fzbhsbId = util.getInsertId("FZBHSB");
            fzbhsb.setId(fzbhsbId);
//            if (state.equals("C") || isFromSaoma){
//                fzbhsbId = util.getInsertId("FZBHSB");
//                fzbhsb.setId(fzbhsbId);
//            }
        } else {
            fzbhsbId = fzbhsb.getId();
        }

        sfsbdm = FZDeviceOneFragment.instance.tv_sbsbdm.getText().toString().trim();

        Object obj = util.getFZSBBySbsbdm(fzbhsb.getSbmc(), "");
        Long id = null;
        if (obj instanceof BHPZ) {
            id = ((BHPZ) obj).getId();
        } else if (obj instanceof FZBHSB) {
            id = ((FZBHSB) obj).getId();
        }
        if (obj != null && !id.equals(fzbhsbId)) {
            viewPager.setCurrentItem(0);
            ToastUtils.showLongToast(this, "该设备名称已被其他设备使用...");
            fzbhsb.setId(null);
            fzbhsb.setEd_tag(null);
            fzbhsb.setSb(null);
            return;
        }

        boolean isM;

        if (fzbhsb.getEd_tag() != null && !fzbhsb.getEd_tag().equals("null")
                && !fzbhsb.getEd_tag().equals("")) {
            if (!sfsbdm.equals("")) {
                Object obj1 = util.getFZSBBySbsbdm(sfsbdm);
                Long s1 = null;
                if (obj1 instanceof BHPZ) {
                    s1 = ((BHPZ) obj1).getId();
                } else if (obj1 instanceof FZBHSB) {
                    s1 = ((FZBHSB) obj1).getId();
                }

                if (obj1 != null && !s1.equals(fzbhsbId)) {
                    viewPager.setCurrentItem(0);
                    ToastUtils.showLongToast(this, "该设备识别码已被其他设备使用...");
                    if (state.equals("C")) {
                        fzbhsb.setId(null);
                        fzbhsb.setEd_tag(null);
                        fzbhsb.setSb(null);
                    }
                    return;
                }
            }
            if (fzbhsb.getEd_tag().equals("S") && !fzbhsb.getSb().equals("D")) {
                fzbhsb.setEd_tag("M");
            }
            if (fzbhsb.getEd_tag().equals("D") && fzbhsb.getSb().equals("D")) {
                fzbhsb.setEd_tag("M");
                fzbhsb.setSb("M");
            }
            fzbhsb.setSfsbm(sfsbdm);
            isM = true;
        } else {
            if (!sfsbdm.equals("")) {
                Object obj2 = util.getFZSBBySbsbdm(sfsbdm);
                Long s1 = null;
                if (obj2 instanceof BHPZ) {
                    s1 = ((BHPZ) obj2).getId();
                } else if (obj2 instanceof FZBHSB) {
                    s1 = ((FZBHSB) obj2).getId();
                }

                if (obj2 != null && !s1.equals(fzbhsbId)) {
                    viewPager.setCurrentItem(0);
                    ToastUtils.showLongToast(this, "该设备识别码已被其他设备使用...");
                    if (state.equals("C")) {
                        fzbhsb.setId(null);
                        fzbhsb.setEd_tag(null);
                        fzbhsb.setSb(null);
                    }
                    return;
                }
            }
            if (state.equals("C")) {
                fzbhsb.setEd_tag("C");
                fzbhsb.setSb("C");
            } else {
                fzbhsb.setEd_tag("M");
                fzbhsb.setSb("M");
            }
            fzbhsb.setSfsbm(sfsbdm);
            isM = false;
        }

        util.coreSavaRZXX(fzbhsb);
        util.coreDelte(fzbhsbId + "");
        sendBroadcast(new Intent("cn.sgg.fzbhsb"));

        if (FZDeviceOneFragment.instance.zzcj_add != null &&
                fzbhsb.getCj().equals(FZDeviceOneFragment.instance.zzcj_add.getMC())) {
            FZDeviceOneFragment.instance.zzcj_add.setCZR(PreferenceUtils
                    .getPrefString(this, "userInfo", null).split("-")[0]);
            FZDeviceOneFragment.instance.zzcj_add.setID(util.getInsertId("ZZCJ"));
            FZDeviceOneFragment.instance.zzcj_add.setED_TAG("C");
            util.coreSave(FZDeviceOneFragment.instance.zzcj_add);
        }

        String code = null;

        if (FZDeviceOneFragment.instance.rjbbList.size() > 1) {
            BHSBXHB bhsbxhb = FZDeviceOneFragment.instance.bhsbxhb;
            if (bhsbxhb != null) {
                if (bhsbxhb.getCode() == null || bhsbxhb.getCode().equals("")) {
                    bhsbxhb.setId(util.getInsertId("BHSBXHB"));
                    bhsbxhb.setCode(util.getInsertId("CODE") + "");
                }
            }
            for (BHXHRJBB bhxhrjbb : FZDeviceOneFragment.instance.rjbbList) {
                if (bhxhrjbb.getMkmc() != null && !bhxhrjbb.getMkmc().equals("")) {
                    if (bhxhrjbb.getBb() == null || bhxhrjbb.getBb().equals("")) {
                        viewPager.setCurrentItem(0);
                        ToastUtils.showLongToast(this, "请选择软件版本");
                        return;
                    }
                }

                if (bhxhrjbb.getBb() != null && !bhxhrjbb.getBb().equals("")) {
                    if (bhsbxhb != null) { //新增型号了，下面完全走新增逻辑
                        bhxhrjbb.setBhxhcode(bhsbxhb.getCode());
                        bhxhrjbb.setId(util.getInsertId("BHXHRJBB"));

                        rjbb += "模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成日期:" + bhxhrjbb.getSCSJ() + ";";
                        bhxhrjbb.setRjbbxx("模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym());
                        bhxhrjbb.setCode(util.getInsertId("CODE") + "");
                        FZBHSBXHBBGX bbgx = new FZBHSBXHBBGX();
                        bbgx.setID(util.getInsertId("FZBHSBXHBBGX"));
                        bbgx.setFZBHSBID(fzbhsbId);
                        bbgx.setSCSJ(bhxhrjbb.getSCSJ());
                        bbgx.setRJBBCODE(bhxhrjbb.getCode());
                        util.coreSave(bbgx);
                        util.coreSave(bhxhrjbb);
                    } else { //没有新增型号
                        bhxhrjbb.setBhxhcode(FZDeviceOneFragment.instance.selectCode);
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
                            rjbb += "模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成日期:" + bhxhrjbb.getSCSJ() + ";";
                            bhxhrjbb.setRjbbxx("模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym());
                            bhxhrjbb.setCode(util.getInsertId("CODE") + "");
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
//                else {
//                    rjbb += "模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成日期:" + bhxhrjbb.getSCSJ() + ";";
//                }
            }
            if (bhsbxhb != null) {
                util.coreSave(bhsbxhb);
            }
        } else if (FZDeviceOneFragment.instance.rjbbList.size() == 1) {
            BHSBXHB bhsbxhb = FZDeviceOneFragment.instance.bhsbxhb;
            if (bhsbxhb != null) {
                bhsbxhb.setId(util.getInsertId("BHSBXHB"));
                bhsbxhb.setCode(util.getInsertId("CODE") + "");
            }

            BHXHRJBB bhxhrjbb = FZDeviceOneFragment.instance.rjbbList.get(0);
            if (bhxhrjbb.getBblx().equals("非六统一，分模块")) {
                if (bhxhrjbb.getMkmc() != null && !bhxhrjbb.getMkmc().equals("")) {
                    if (bhxhrjbb.getBb() == null || bhxhrjbb.getBb().equals("")) {
                        viewPager.setCurrentItem(0);
                        ToastUtils.showLongToast(this, "请选择软件版本");
                        return;
                    }
                }

                if (bhxhrjbb.getBb() != null && !bhxhrjbb.getBb().equals("")) {
                    if (bhsbxhb != null) { //新增型号了，下面完全走新增逻辑
                        bhxhrjbb.setBhxhcode(bhsbxhb.getCode());
                        bhxhrjbb.setId(util.getInsertId("BHXHRJBB"));

                        rjbb += "模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成日期:" + bhxhrjbb.getSCSJ();
                        bhxhrjbb.setRjbbxx("模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym());
                        bhxhrjbb.setCode(util.getInsertId("CODE") + "");
                        FZBHSBXHBBGX bbgx = new FZBHSBXHBBGX();
                        bbgx.setID(util.getInsertId("FZBHSBXHBBGX"));
                        bbgx.setFZBHSBID(fzbhsbId);
                        bbgx.setSCSJ(bhxhrjbb.getSCSJ());
                        bbgx.setRJBBCODE(bhxhrjbb.getCode());
                        util.coreSave(bbgx);
                        util.coreSave(bhxhrjbb);
                    } else { //没有新增型号
                        bhxhrjbb.setBhxhcode(FZDeviceOneFragment.instance.selectCode);
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
                            rjbb += "模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成日期:" + bhxhrjbb.getSCSJ();
                            bhxhrjbb.setRjbbxx("模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym());
                            bhxhrjbb.setCode(util.getInsertId("CODE") + "");
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
//                else {
//                    rjbb += "模块名称:" + bhxhrjbb.getMkmc() + ",软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成日期:" + bhxhrjbb.getSCSJ();
//                }
            } else {
                if (bhxhrjbb.getBb() != null && !bhxhrjbb.getBb().equals("")) {
                    if (bhsbxhb != null) { //新增型号了，下面完全走新增逻辑
                        bhxhrjbb.setBhxhcode(bhsbxhb.getCode());
                        bhxhrjbb.setId(util.getInsertId("BHXHRJBB"));

                        rjbb += "软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成日期:" + bhxhrjbb.getSCSJ();
                        bhxhrjbb.setRjbbxx("软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym());
                        bhxhrjbb.setCode(util.getInsertId("CODE") + "");
                        FZBHSBXHBBGX bbgx = new FZBHSBXHBBGX();
                        bbgx.setID(util.getInsertId("FZBHSBXHBBGX"));
                        bbgx.setFZBHSBID(fzbhsbId);
                        bbgx.setSCSJ(bhxhrjbb.getSCSJ());
                        bbgx.setRJBBCODE(bhxhrjbb.getCode());
                        util.coreSave(bbgx);
                        util.coreSave(bhxhrjbb);
                    } else { //没有新增型号
                        bhxhrjbb.setBhxhcode(FZDeviceOneFragment.instance.selectCode);
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
                            rjbb += "软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成日期:" + bhxhrjbb.getSCSJ();
                            bhxhrjbb.setRjbbxx("软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym());
                            bhxhrjbb.setCode(util.getInsertId("CODE") + "");
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
//                else {
//                    rjbb += "软件版本:" + bhxhrjbb.getBb() + ",检验码:" + bhxhrjbb.getJym() + ",生成日期:" + bhxhrjbb.getSCSJ();
//                }
            }
            if (bhsbxhb != null) {
                util.coreSave(bhsbxhb);
            }
        } else if (FZDeviceOneFragment.instance.ltysbxh != null) {
            if (FZDeviceOneFragment.instance.ltysbxh.getRjbb() != null
                    && !FZDeviceOneFragment.instance.ltysbxh.getRjbb().equals("")) {
                FZDeviceOneFragment.instance.ltysbxh.setBhfl(FZDeviceOneFragment.instance.bhfl);
                FZDeviceOneFragment.instance.ltysbxh.setBhlx(FZDeviceOneFragment.instance.bhlx);
                code = util.getCodeByBhxhRjbb(FZDeviceOneFragment.instance.ltysbxh);
                fzbhsb.setIcdwjmc(FZDeviceOneFragment.instance.ltysbxh.getWjmc());
                if (code != null) {
//                    FZDeviceOneFragment.instance.ltysbxh.setCode(code);
                    if (state.equals("C")) {
                        if (DeviceDetailsActivity.isFromSaoma) {
                            //编辑过返回true,未编辑返回false
                            if (DeviceDetailsActivity.instance.ltysbxh_fz == null && FZDeviceOneFragment.instance.ltysbxh != null
                                    && FZDeviceOneFragment.instance.ltysbxh.getCode() != null) {
                                DeviceDetailsActivity.instance.code2 = null;
                                DeviceDetailsActivity.instance.code3 = code + "";
                            } else {
                                LTYSBXH ltysbxh = new LTYSBXH();
                                ltysbxh.setCode(code);
                                if (DeviceDetailsActivity.instance.shifouEdit(FZDeviceOneFragment.instance.ltysbxh, ltysbxh)) {
                                    DeviceDetailsActivity.instance.code2 = null;
                                } else {
                                    DeviceDetailsActivity.instance.code2 = code;
                                }
                            }
                        } else {
                            DeviceDetailsActivity.instance.code2 = null;
                        }
                    } else {
                        if (DeviceDetailsActivity.instance.ltysbxh_fz == null && FZDeviceOneFragment.instance.ltysbxh != null
                                && FZDeviceOneFragment.instance.ltysbxh.getCode() != null
                                && !"".equals(FZDeviceOneFragment.instance.ltysbxh.getCode())
                                && !"null".equals(FZDeviceOneFragment.instance.ltysbxh.getCode())) {
                            DeviceDetailsActivity.instance.code2 = null;
                        } else {
                            LTYSBXH ltysbxh = new LTYSBXH();
                            ltysbxh.setCode(code);
                            if (DeviceDetailsActivity.instance.shifouEdit(FZDeviceOneFragment.instance.ltysbxh, ltysbxh)) {
                                DeviceDetailsActivity.instance.code2 = null;
                            } else {
                                DeviceDetailsActivity.instance.code2 = code;
                            }
                        }
                    }

                    rjbb += FZDeviceOneFragment.instance.ltysbxh.getRjbb() + ",生成日期:" + FZDeviceOneFragment.instance.ltysbxh.getJymscsj();
                    FZBHSBXHBBGX bbgx = new FZBHSBXHBBGX();
                    bbgx.setID(util.getInsertId("FZBHSBXHBBGX"));
                    bbgx.setFZBHSBID(fzbhsbId);
                    bbgx.setSCSJ(FZDeviceOneFragment.instance.ltysbxh.getSCSJ());
                    bbgx.setRJBBCODE(code);
                    util.coreSave(bbgx);
                } else {
                    code2 = null;

                    FZDeviceOneFragment.instance.ltysbxh.setId(util.getInsertId("LTYSBXH"));

                    FZDeviceOneFragment.instance.ltysbxh.setCode(util.getInsertId("CODE") + "");
                    FZDeviceOneFragment.instance.ltysbxh.setED_TAG("C");
                    FZDeviceOneFragment.instance.ltysbxh.setSfqy("Y");
                    FZDeviceOneFragment.instance.ltysbxh.setState("C");
                    FZDeviceOneFragment.instance.ltysbxh.setSysblx("FZSB");
                    code = FZDeviceOneFragment.instance.ltysbxh.getCode();

                    rjbb += FZDeviceOneFragment.instance.ltysbxh.getRjbb() + ",生成日期:" + FZDeviceOneFragment.instance.ltysbxh.getJymscsj();

                    FZBHSBXHBBGX bbgx = new FZBHSBXHBBGX();
                    bbgx.setID(util.getInsertId("FZBHSBXHBBGX"));
                    bbgx.setFZBHSBID(fzbhsbId);
                    bbgx.setSCSJ(FZDeviceOneFragment.instance.ltysbxh.getSCSJ());
                    bbgx.setRJBBCODE(FZDeviceOneFragment.instance.ltysbxh.getCode());
                    util.coreSave(bbgx);
                    util.coreSave(FZDeviceOneFragment.instance.ltysbxh);
                    fzbhsb.setIcdwjmc(FZDeviceOneFragment.instance.ltysbxh.getWjmc());
                }
            }
//            else {
//                rjbb += FZDeviceOneFragment.instance.ltysbxh.getRjbb() + ",生成日期:" + FZDeviceOneFragment.instance.ltysbxh.getJymscsj();
//            }
        }

        fzbhsb.setRjbb(rjbb);

        if (isM) {
            util.coreFZHBSB("M", fzbhsb);
        } else {
            util.coreFZHBSB(fzbhsb.getEd_tag(), fzbhsb);
        }

        //**********************保存板卡
        if (state.equals("C")) {
            if (isFromSaoma) {//扫码新增
                if (getIntent().hasExtra("BKXX")) {
                    List<BKXX> list = (List<BKXX>) getIntent().getSerializableExtra("BKXX");
                    for (BKXX bkxx : list) {
                        bkxx.setId(util.getInsertId("BKXX"));
                        bkxx.setZsjid(fzbhsbId);
                        bkxx.setZsjtype("FZBHSB");
                        util.coreSave(bkxx);
                    }
                    list.clear();
                }

                //**********************保存ICD**************************
                if (FZDeviceOneFragment.instance.isSix && FZDeviceOneFragment.instance.ltybzbb.equals("2013版")) {
                    if (getIntent().hasExtra("ICDXX")) {
                        if (code2 == null) {
                            //保存新的icd信息
                            List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code + "");
                            LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                            ICDXX icdxx1 = getICDXX(ltysbxh);
                            icdxx1.setID(util.getInsertId("ICDXX"));
                            icdxx1.setZSJID(fzbhsbId);
                            icdxx1.setZSJTYPE("FZBHSB");
                            icdxx1.setED_TAG("C");
                            util.coreSave(icdxx1);
                            ltysbxhList.clear();

                            //保存旧的ICD信息 先弹框设置变更时间和变更原因,再保存  dates//变更时间  reasons//变更原因
                            ICDXX icdxx = (ICDXX) getIntent().getSerializableExtra("ICDXX");
                            icdChangeDialog2(icdxx, fzbhsbId, "saoma", "FZSB");
//                            icdxx.setBGSJ(dates);
//                            icdxx.setBGYY(reasons);
//                            zhikong();
//                            icdxx.setID(util.getInsertId("ICDXX"));
//                            icdxx.setZSJID(fzbhsbId);
//                            icdxx.setZSJTYPE("FZBHSB");
//                            util.coreSave(icdxx);
                        } else {
                            ICDXX icdxx = (ICDXX) getIntent().getSerializableExtra("ICDXX");
                            icdxx.setID(util.getInsertId("ICDXX"));
                            icdxx.setZSJID(fzbhsbId);
                            icdxx.setZSJTYPE("FZBHSB");
                            icdxx.setED_TAG("C");
                            util.coreSave(icdxx);

                            finish();
                            ToastUtils.showToast(this, "保存成功");
                        }
                    } else {
                        //新增
                        if (FZDeviceOneFragment.instance.isSix && FZDeviceOneFragment.instance.ltybzbb.equals("2013版") && code2 == null) {
                            //保存新的icd信息
                            List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code + "");
                            LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                            ICDXX icdxx1 = getICDXX(ltysbxh);
                            icdxx1.setID(util.getInsertId("ICDXX"));
                            icdxx1.setZSJID(fzbhsbId);
                            icdxx1.setZSJTYPE("FZBHSB");
                            icdxx1.setED_TAG("C");
                            util.coreSave(icdxx1);
                            ltysbxhList.clear();

                            finish();
                            ToastUtils.showToast(this, "保存成功");
                        } else {
                            finish();
                            ToastUtils.showToast(this, "保存成功");
                        }
                    }
                } else {
                    //新增
                    if (FZDeviceOneFragment.instance.isSix && FZDeviceOneFragment.instance.ltybzbb.equals("2013版") && code2 == null) {
                        //保存新的icd信息
                        List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code + "");
                        LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                        ICDXX icdxx1 = getICDXX(ltysbxh);
                        icdxx1.setID(util.getInsertId("ICDXX"));
                        icdxx1.setZSJID(fzbhsbId);
                        icdxx1.setZSJTYPE("FZBHSB");
                        icdxx1.setED_TAG("C");
                        util.coreSave(icdxx1);
                        ltysbxhList.clear();

                        finish();
                        ToastUtils.showToast(this, "保存成功");
                    } else {
                        finish();
                        ToastUtils.showToast(this, "保存成功");
                    }
                }
            } else {
                //新增
                if (FZDeviceOneFragment.instance.isSix && FZDeviceOneFragment.instance.ltybzbb.equals("2013版") && code2 == null) {
                    //保存新的icd信息
                    List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code + "");
                    LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                    ICDXX icdxx1 = getICDXX(ltysbxh);
                    icdxx1.setID(util.getInsertId("ICDXX"));
                    icdxx1.setZSJID(fzbhsbId);
                    icdxx1.setZSJTYPE("FZBHSB");
                    icdxx1.setED_TAG("C");
                    util.coreSave(icdxx1);
                    ltysbxhList.clear();

                    finish();
                    ToastUtils.showToast(this, "保存成功");
                } else {
                    finish();
                    ToastUtils.showToast(this, "保存成功");
                }
            }

        } else {
            //编辑时候ICD的保存
//            List<ICDXX> icdxxs = new ArrayList<>(); //显示的
//            List<ICDXX> icdxxs2 = new ArrayList<>(); //全部的
            List<Object> icdList = util.getICDOrBKXX(ICDXX.class, DeviceDetailsActivity.fzbhsb.getId() + "", "FZBHSB");
            //从库里取ICD信息
//            icdList.toString();
            if (icdList.size() > 0) {
                for (int i = 0; i < icdList.size(); i++) {
                    ICDXX icdxx = (ICDXX) icdList.get(i);
                    icdxxs2.add(icdxx);
                    if (isAdd(icdxx)) {
                        icdxxs.add(icdxx);
                    }
                }
                icdList.clear();
            }

            if (FZDeviceOneFragment.instance.isSix && FZDeviceOneFragment.instance.ltybzbb.equals("2013版")) {
                if (code2 == null) { //修改过ICD信息
                    //保存新的icd信息
                    List<Object> ltysbxhList = util.getICDOrBKXX(LTYSBXH.class, code + "");
                    LTYSBXH ltysbxh = (LTYSBXH) ltysbxhList.get(0);
                    ICDXX icdxx1 = getICDXX(ltysbxh);
                    icdxx1.setID(util.getInsertId("ICDXX"));
                    icdxx1.setZSJID(fzbhsbId);
                    icdxx1.setZSJTYPE("FZBHSB");
                    icdxx1.setED_TAG("C");
                    util.coreSave(icdxx1);
                    ltysbxhList.clear();

                    //保存旧的ICD信息 先弹框设置变更时间和变更原因,再保存  dates//变更时间  reasons//变更原因
                    if (icdxxs != null && icdxxs.size() > 0) {
                        ICDXX icdxx = icdxxs.get(0);
                        icdChangeDialog2(icdxx, fzbhsbId, "feisaoma", "FZSB");
                    } else {
                        finish();
                        ToastUtils.showToast(this, "保存成功");
                    }
                } else {
                    finish();
                    ToastUtils.showToast(this, "保存成功");
                }

            } else { //删除库里的ICD信息
                if (icdxxs2 != null && icdxxs2.size() > 0) {
                    for (int i = 0; i < icdxxs2.size(); i++) {
                        ICDXX icdxxss = icdxxs2.get(i);
                        if (icdxxss.getED_TAG() != null && icdxxss.getED_TAG().equals("C")) {
                            util.coreICD("D", icdxxss); //删除数据表
                        } else { //只更新
                            icdxxss.setED_TAG("D");
                            icdxxss.setSB("D");
                            util.coreICD("M", icdxxss); //更新数据表
                        }
                    }
                    icdxxs2.clear();
                }
                finish();
                ToastUtils.showToast(this, "保存成功");
            }
        }
//        finish();
//        ToastUtils.showToast(this, "保存成功");
    }

    public String code2 = null; //判断是否变化
    public String code3 = null; //接口使用(保护)
    public LTYSBXH ltysbxh_bh; //保护saoma的六统一对象
    public LTYSBXH ltysbxh_fz; //辅助saoma的六统一对象
    List<ICDXX> icdxxs = new ArrayList<>(); //显示的
    List<ICDXX> icdxxs2 = new ArrayList<>(); //全部的

    //获取ICDXX
    public ICDXX getICDXX(LTYSBXH ltysbxh) {
        ICDXX icdxx = new ICDXX();
        if (ltysbxh.getWjmc() != null) {
            icdxx.setWJMC(ltysbxh.getWjmc() + "");
        } else {
            icdxx.setWJMC("");
        }

        if (ltysbxh.getWjbb() != null) {
            icdxx.setWJBB(ltysbxh.getWjbb() + "");
        } else {
            icdxx.setWJBB("");
        }

        if (ltysbxh.getCrc32() != null) {
            icdxx.setCRC32(ltysbxh.getCrc32() + "");
        } else {
            icdxx.setCRC32("");
        }

        if (ltysbxh.getMd5() != null) {
            icdxx.setMD5(ltysbxh.getMd5() + "");
        } else {
            icdxx.setMD5("");
        }

        if (ltysbxh.getJymscsj() != null) {
            icdxx.setJYMSCSJ(TimeUtil.formatString(ltysbxh.getJymscsj()) + "");
        } else {
            icdxx.setJYMSCSJ("");
        }
        return icdxx;
    }

    //ICD变更时间和原因添加对话框
    //Long fzbhsbId;
    private CustomDialog dialog;
    private MyDatePickerDialog dialogs;
    private int mYear, mMonth, mDay;
    private Calendar c;
    String dates = null; //变更时间
    String reasons = null; //变更原因

    public void zhikong() {
        String dates = null; //变更时间
        String reasons = null; //变更原因
    }

    public void icdChangeDialog2(final ICDXX icdxx, final Long sbID, final String saoma, final String type) {
        if (dialog != null && dialog.isShowing()) {
            return;
        } else {

            View view = LayoutInflater.from(this).inflate(R.layout.fragment_icd_change_item, null);
            dialog = new CustomDialog(this, R.style.dialog_alert_style, 0);

            //Dialog中拦截返回键
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && event.getAction() == KeyEvent.ACTION_UP) {
                        ToastUtils.showToast(DeviceDetailsActivity.this, "台账正在保存中,请先点击  确定  ");
                        return true;
                    }
                    return false;
                }
            });

            final EditText reason = (EditText) view.findViewById(R.id.reason);
            final TextView qianfeng = (TextView) view.findViewById(R.id.qianfeng);

            Button queding = (Button) view.findViewById(R.id.queding);
            Button quxiao = (Button) view.findViewById(R.id.quxiao);


            //铅封日期
            qianfeng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogs = new MyDatePickerDialog(DeviceDetailsActivity.this, new MyDatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            mYear = year;
                            mMonth = monthOfYear + 1;
                            mDay = dayOfMonth;
                            qianfeng.setText(mYear + "-" + mMonth + "-" + mDay);
                            dates = TimeUtil.formatString(mYear + "-" + mMonth + "-" + mDay);
                        }
                    }, mYear, mMonth, mDay);

                    if (mYear > 0) {
                        dialogs.updateDate(mYear, mMonth, mDay);
                    }
                    dialogs.myShow();
                }
            });


            //确定
            queding.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String reason2 = reason.getText().toString().trim();
                    if (dates == null || "".equals(dates) || reason2 == null || "".equals(reason2)) {
                        ToastUtils.showLongToast(DeviceDetailsActivity.this, "变更时间和变更原因不能为空");
                    } else {
                        reasons = reason2;

                        icdxx.setBGSJ(dates);
                        icdxx.setBGYY(reasons);
                        zhikong();
                        if (saoma.equals("saoma")) {
                            if (type.equals("BHSB")) {
                                icdxx.setID(util.getInsertId("ICDXX"));
                                icdxx.setZSJID(sbID);
                                icdxx.setZSJTYPE("BHPZ");
                                icdxx.setED_TAG("C");
                                util.coreSave(icdxx); //保存
                            } else if (type.equals("FZSB")) {
                                icdxx.setID(util.getInsertId("ICDXX"));
                                icdxx.setZSJID(sbID);
                                icdxx.setZSJTYPE("FZBHSB");
                                icdxx.setED_TAG("C");
                                util.coreSave(icdxx); //保存
                            }
                        } else {
                            util.coreICD("M", icdxx); //更新
                        }
                        icdxxs.clear();
                        dialog.dismiss();
                        DeviceDetailsActivity.this.finish();
                        ToastUtils.showToast(DeviceDetailsActivity.this, "保存成功");
                    }
                }
            });

            //取消
//            quxiao.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });


            dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    //过滤要显示的ICD
    public Boolean isAdd(ICDXX icdxx) {
        Boolean yy = false; //原因
        yy = icdxx.getBGYY() == null || "".equals(icdxx.getBGYY());

        Boolean sj = false; //原因
        sj = icdxx.getBGSJ() == null || "".equals(icdxx.getBGSJ());

        Boolean edTag = false; //标记删除
        edTag = icdxx.getED_TAG() == null || !icdxx.getED_TAG().equals("D");


        //sb是非D或者变更时间和变更原因都为null时,返回true, 加载
        if (icdxx.getSB() != null) {
            return !icdxx.getSB().equals("D") && yy && sj && edTag;
        } else {
            return icdxx.getSB() == null && yy && sj && edTag;
        }
    }

    //判断扫码新增带出的六统一对象是否编辑过,编辑过返回true,未编辑返回false
    public Boolean shifouEdit(LTYSBXH ltysbxhOld, LTYSBXH ltysbxhNew) {
        if (ltysbxhOld.getCode() == null || "".equals(ltysbxhOld.getCode()) || "null".equals(ltysbxhOld.getCode())) { //无值
            return ltysbxhNew.getCode() != null && !"".equals(ltysbxhNew.getCode()) && !"null".equals(ltysbxhNew.getCode());
        } else {//ltysbxhOld有值
            if (ltysbxhNew.getCode() == null || "".equals(ltysbxhNew.getCode()) || "null".equals(ltysbxhNew.getCode())) {
                return true;
            } else {
                return !ltysbxhOld.getCode().equals(ltysbxhNew.getCode());
            }
        }
    }
}
