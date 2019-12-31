package cn.com.sgcc.dev.view.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zxing.activity.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.sgcc.dev.BaseApplication;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.DataOutAdapter;
import cn.com.sgcc.dev.adapter.FileRestoreAdapter;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.customeView.LoadingDialog;
import cn.com.sgcc.dev.customeView.recylerUtil.MyItemClickListener;
import cn.com.sgcc.dev.customeView.recylerUtil.SwipeItemLayout;
import cn.com.sgcc.dev.dao2.DaoMaster;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.BZSJ;
import cn.com.sgcc.dev.model2.GXDW;
import cn.com.sgcc.dev.model2.RLST_USER;
import cn.com.sgcc.dev.model2.StoreFile;
import cn.com.sgcc.dev.model2.regist.UserBean;
import cn.com.sgcc.dev.model2.vo.DataHolder;
import cn.com.sgcc.dev.model2.vo.JiaoYanData;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.net.FileRequestBody;
import cn.com.sgcc.dev.net.FileUploadService;
import cn.com.sgcc.dev.net.RetrofitCallBack;
import cn.com.sgcc.dev.net.RetrofitUtils;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.DataStoreAsyncTask;
import cn.com.sgcc.dev.utils.FileUtils;
import cn.com.sgcc.dev.utils.OutputDBAsyncTask;
import cn.com.sgcc.dev.utils.PinyinComparator;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.ScreenUtils;
import cn.com.sgcc.dev.utils.TextPinyinUtil;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * <p>@description:首页</p>
 *
 * @author lxf
 * @version 1.0.0
 */

public class MainActivitys extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView bianDZ, device, saoyisao, wode, tv_store_road;
    private List<CZCS> orglist = new ArrayList<>();//原始数据
    private DrawerLayout drawer;
    private LoadingDialog dialog;
    private CustomDialog dialog_setting;
    private CustomDialog resDialog;
    private IDaoUtil util;
    public JiaoYanData jiaoYanData;

    private RelativeLayout shouye_zhinan, ll_popup;
    private TextView bdz_img1, bdz_tx, zz_tx, wode_tx, bdz_img11, zz_tx1, bdz_img12, wode_tx1;
    private ImageView bdz_img111;

    //备份文件相关类
    private StoreFile storeFile;
    private List<StoreFile> storeFileList = new ArrayList<>();
    //导出加载
    private List<CZCS> data_out_put = new ArrayList<>();
    private DataOutAdapter adapter;
    //当前登录用户注册状态
    private boolean isRegist;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case -1:
                    ToastUtils.showLongToast(MainActivitys.this, "导出失败，存在校验未通过的数据！");
                    dialog.dismiss();
                    break;
                case 0:
                    ToastUtils.showLongToast(MainActivitys.this, "上传成功！");
                    dialog.dismiss();
                    break;
                case 1:
                    sendBroadcast(new Intent("cn.sgg.fzbhsb"));
                    ToastUtils.showToast(MainActivitys.this, "导出成功！");
                    if (Constants.isLoginOnLine && AppUtils.checkNetwork(MainActivitys.this)) {//有网络
                        upLoadFile();
                    } else {
                        dialog.dismiss();
                    }
                    break;
                case 2:
//                    Bundle bundle = msg.getData();
//                    long progress = bundle.getLong("progress");
//                    long total = bundle.getLong("total");
//                    dialog.setTitle("正在拼命上传：" + +progress + "/" + total);
                    dialog.setTitle("正在拼命上传...");
                    break;
                case 3://上传成功
                    dialog.dismiss();

                    String result = msg.obj.toString();
                    if (!result.equals("上传成功")) {
                        result = "上传失败\n\n  失败原因：" + result;
                    }

                    showDialog(result);

                    break;
                case 4://备份成功
                    //写入备份的数据内容
                    if (storeFileList == null)
                        storeFileList = new ArrayList<>();

                    storeFileList.add(0, storeFile);
                    FileUtils.writeObject(storeFileList, new File(Constants.APP_STORE));

                    dialog.dismiss();
                    ToastUtils.showLongToast(MainActivitys.this, "备份成功！");
                    resDialog.dismiss();
                    break;
                case 5://还原成功
                    dialog.dismiss();
                    ToastUtils.showLongToast(MainActivitys.this, "还原成功，请重新登录！");
                    resDialog.dismiss();
                    startActivity(new Intent(MainActivitys.this, LoginActivity.class));
                    break;
            }
        }
    };

    int flag_all = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainss);

        startTimer_for();
        //自动备份
        bindServer();
        //注册状态
        isRegist = AppUtils.isRegist();

        //bianDZ,device,saoyisao,wode
        bianDZ = (TextView) findViewById(R.id.textView9);
        device = (TextView) findViewById(R.id.textView5);
        saoyisao = (TextView) findViewById(R.id.textView7);
        wode = (TextView) findViewById(R.id.textView);

        //-------------指南------------------
        shouye_zhinan = (RelativeLayout) findViewById(R.id.shouye_zhinan);
        bdz_img1 = (TextView) findViewById(R.id.bdz_img1);
        bdz_tx = (TextView) findViewById(R.id.bdz_tx);
        zz_tx = (TextView) findViewById(R.id.zz_tx);
        wode_tx = (TextView) findViewById(R.id.wode_tx);
        bdz_img11 = (TextView) findViewById(R.id.bdz_img11);
        zz_tx1 = (TextView) findViewById(R.id.zz_tx1);
        bdz_img12 = (TextView) findViewById(R.id.bdz_img12);
        wode_tx1 = (TextView) findViewById(R.id.wode_tx1);
        bdz_img111 = (ImageView) findViewById(R.id.bdz_img111);

        //指南
        if (PreferenceUtils.getPrefBoolean(MainActivitys.this, "zhinan_main", false)) {
            shouye_zhinan.setVisibility(View.GONE);
            bianDZ.setEnabled(true);
            device.setEnabled(true);
            saoyisao.setEnabled(true);
            wode.setEnabled(true);
        } else {
            //PreferenceUtils.setPrefBoolean(MainActivitys.this, "zhinan_main", true);
        }

        initEvevt();
        initView();
        dialog = new LoadingDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        if (getIntent().hasExtra("admin")) {
            return;
        }
        String user = PreferenceUtils.getPrefString(MainActivitys.this, "userInfo", null);
        if (user.split("-")[0].equals(Constants.admin)) {
            return;
        } else {
            util = new DaoUtil(this);
        }
        //先判断是否已经加载过数据,若无则开启子线程加载变电站数据---------------------------------------
        if (DataHolder.getInstance().getData() == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String userAccountJson = PreferenceUtils.getPrefString(MainActivitys.this, "Info", "");
                    UserBean ub = new Gson().fromJson(userAccountJson, UserBean.class);

                    String name = PreferenceUtils.getPrefString(MainActivitys.this, "userInfo", null);
                    if (name.equals(Constants.admin + "-" + Constants.admin_pw))
                        return;


                    RLST_USER user = null;
                    if (ub != null && ub.getUserAccount() != null) {
                        user = util.getUserByName(ub.getUserAccount());
                    } else {
                        user = util.getUserByName(name.split("-")[0]);
                    }

                    if (user == null) {
                        orglist = util.getCZCS(null, isRegist);
                        DataHolder.getInstance().setSsgs("国调");
                    } else {
                        DataHolder.getInstance().setSsgs(user.getSsgs()); //保存所属公司
                        if (user.getDwjb() != null && !user.getDwjb().equals("")) {
                            orglist = util.getCZCS(null, isRegist);
                        } else {
                            orglist = util.getCZCS(user.getSsgs(), isRegist);
                        }
                    }
                    if (orglist != null && orglist.size() > 0) {
                        getCzcs();
                    } else {
                        DataHolder.getInstance().init();
                        PreferenceUtils.setPrefString(MainActivitys.this, "czmc", null);
                        PreferenceUtils.setPrefString(MainActivitys.this, "czmcID", null);
                    }
                }
            }).start();
        } else { //已经加载过变电站 判断是否变更换数据  再判断与上一个用户的所属公司是否一致
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String userAccountJson = PreferenceUtils.getPrefString(MainActivitys.this, "Info", "");
                    UserBean ub = new Gson().fromJson(userAccountJson, UserBean.class);

                    String name = PreferenceUtils.getPrefString(MainActivitys.this, "userInfo", null);
                    if (name.equals(Constants.admin + "-" + Constants.admin_pw))
                        return;


                    RLST_USER users = null;
                    if (ub != null && ub.getUserAccount() != null) {
                        users = util.getUserByName(ub.getUserAccount());
                    } else {
                        users = util.getUserByName(name.split("-")[0]);
                    }

                    if (users == null) {
                        if (DataHolder.getInstance().getSsgs() != null && !DataHolder.getInstance().getSsgs().equals("国调")) {
                            orglist = util.getCZCS(null, isRegist);
                            DataHolder.getInstance().setSsgs("国调");
                            if (orglist != null && orglist.size() > 0) {
                                getCzcs();
                            } else {
                                DataHolder.getInstance().init();
                                PreferenceUtils.setPrefString(MainActivitys.this, "czmc", null);
                                PreferenceUtils.setPrefString(MainActivitys.this, "czmcID", null);
                            }
                        }
                    } else {
                        if (DataHolder.getInstance().getGhsjBoolean()) {//更换过数据
                            DataHolder.getInstance().setSsgs(users.getSsgs()); //保存所属公司
                            if (users.getDwjb() != null && !users.getDwjb().equals("")) {
                                orglist = util.getCZCS(null, isRegist);
                            } else {
                                orglist = util.getCZCS(users.getSsgs(), isRegist);
                            }
                            if (orglist != null && orglist.size() > 0) {
                                getCzcs();
                            } else {
                                DataHolder.getInstance().init();
                                PreferenceUtils.setPrefString(MainActivitys.this, "czmc", null);
                                PreferenceUtils.setPrefString(MainActivitys.this, "czmcID", null);
                            }
                        } else {
                            if (users.getSsgs() == null || users.getSsgs().equals("")) {
                                if (DataHolder.getInstance().getSsgs() != null && !DataHolder.getInstance().getSsgs().equals("")) {
                                    DataHolder.getInstance().setSsgs(users.getSsgs()); //保存所属公司
                                    if (users.getDwjb() != null && !users.getDwjb().equals("")) {
                                        orglist = util.getCZCS(null, isRegist);
                                    } else {
                                        orglist = util.getCZCS(users.getSsgs(), isRegist);
                                    }
                                    if (orglist != null && orglist.size() > 0) {
                                        getCzcs();
                                    } else {
                                        DataHolder.getInstance().init();
                                        PreferenceUtils.setPrefString(MainActivitys.this, "czmc", null);
                                        PreferenceUtils.setPrefString(MainActivitys.this, "czmcID", null);
                                    }
                                }
                            } else {
                                if (DataHolder.getInstance().getSsgs() == null || DataHolder.getInstance().getSsgs().equals("")) {
                                    DataHolder.getInstance().setSsgs(users.getSsgs()); //保存所属公司
                                    if (users.getDwjb() != null && !users.getDwjb().equals("")) {
                                        orglist = util.getCZCS(null, isRegist);
                                    } else {
                                        orglist = util.getCZCS(users.getSsgs(), isRegist);
                                    }
                                    if (orglist != null && orglist.size() > 0) {
                                        getCzcs();
                                    } else {
                                        DataHolder.getInstance().init();
                                        PreferenceUtils.setPrefString(MainActivitys.this, "czmc", null);
                                        PreferenceUtils.setPrefString(MainActivitys.this, "czmcID", null);
                                    }
                                } else {
                                    if (users != null && !users.getSsgs().equals(DataHolder.getInstance().getSsgs())) {
                                        DataHolder.getInstance().setSsgs(users.getSsgs()); //保存所属公司
                                        if (users.getDwjb() != null && !users.getDwjb().equals("")) {
                                            orglist = util.getCZCS(null, isRegist);
                                        } else {
                                            orglist = util.getCZCS(users.getSsgs(), isRegist);
                                        }
                                        if (orglist != null && orglist.size() > 0) {
                                            getCzcs();
                                        } else {
                                            DataHolder.getInstance().init();
                                            PreferenceUtils.setPrefString(MainActivitys.this, "czmc", null);
                                            PreferenceUtils.setPrefString(MainActivitys.this, "czmcID", null);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }).start();
        }

        //获取筛选原始数据
        if (DataHolder.getInstance().getFilter() == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    filterData();
                }
            }).start();
        }

        //初始化一些值
        Map<String, String> txtMaps = AppUtils.setTxt(new HashMap<>(), "", false);
        String sbxx = txtMaps.get("sbxx") + "";
        if (sbxx != null && !sbxx.equals("")) {
            BaseApplication.setPad_id(sbxx.split("-")[4]);
        }

        File file = new File(Constants.APP_STORE);
        if (file.exists()) {
            storeFileList = (List<StoreFile>) FileUtils.readObject(file);
        }

        //校验: 保存默认的校验项
        List<SaleAttributeNameVo> itemData3 = PreferenceUtils.getDataList(MainActivitys.this, "jyx", null);
        if (itemData3 != null && itemData3.size() > 0) {

        } else {
            Gson gson = new Gson();
            String s = AppUtils.readJsonFile(Constants.APP_JY);
            jiaoYanData = gson.fromJson(s, JiaoYanData.class);
            AppUtils.saveJYX(MainActivitys.this, jiaoYanData);
        }
        addNewfield();
    }

    private void addNewfield() {
        //只能执行一次
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, Constants.INPUT_PATH + Constants.DB_NAME, null);
        System.out.println(Constants.DB_NAME);

        SQLiteDatabase db = openHelper.getWritableDatabase();
        db.setLocale(Locale.CHINESE);
        String[] c = {};
        String[] d = {};
        Cursor cursor = db.rawQuery("select * from BHPZ", c);

        Cursor cursorfz = db.rawQuery("select * from FZBHSB", d);
        if (cursor.getColumnIndex("JY") == -1) {
            //小于0，就是没有查到
            db.execSQL("alter table BHPZ add JY varchar(10)");
        }
        if (cursorfz.getColumnIndex("JY") == -1) {
            //小于0，就是没有查到
            db.execSQL("alter table FZBHSB add JY varchar(10)");
        }
        cursor.close();
        cursorfz.close();
        db.close();
    }

    /**
     * 在线上传附件
     */
    private void upLoadFile() {
        File upFile = new File(Constants.OUT_DB_PATH);
        if (upFile != null) {
            Map<String, Object> map = new HashMap<>();
            String userInfo = PreferenceUtils.getPrefString(this, "userInfo", null);
            map.put("fileName", upFile.getName());
            map.put("fileSize", upFile.getTotalSpace());

            if (userInfo.split("-")[0].equals(Constants.admin)) {
                ToastUtils.showLongToast(this, "系统管理员无上传数据操作！");
                return;
            }

            RetrofitCallBack<ResponseBody> callBack = new RetrofitCallBack<ResponseBody>() {
                @Override
                public void onSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String json = response.body().string();

                        JSONObject jsonObject = new JSONObject(json.toString());


                        String msg = jsonObject.getString("msg");

                        Message message = Message.obtain();
                        message.obj = msg;
                        message.what = 3;
                        mHandler.sendMessage(message);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(String msg) {
                    ToastUtils.showLongToast(MainActivitys.this, msg);
                    mHandler.sendEmptyMessage(0);
                }

                @Override
                public void onLoading(long total, long progress) {
                    Message msg = Message.obtain();
                    msg.what = 2;
                    Bundle bundle = new Bundle();
                    bundle.putLong("downloaded", progress);
                    bundle.putLong("fileSize", total);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                }
            };

            FileRequestBody requestBody = new FileRequestBody(upFile, callBack);

            MultipartBody.Part part = MultipartBody.Part.createFormData("file", upFile.getName(), requestBody);
            FileUploadService uploadService = RetrofitUtils.retrofit2.create(FileUploadService.class);
            uploadService.upload(Constants.ROOT_URL + Constants.APP_UP_DBFILE, part, map, Constants.Cookie).
                    enqueue(callBack);
        }
    }

    private void showDialog(String msg) {
        AppUtils.showDialog(this, msg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow pop = (PopupWindow) v.getTag();
                pop.dismiss();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow pop = (PopupWindow) v.getTag();
                pop.dismiss();
            }
        });
    }

    public void initEvevt() {
        //指南
        shouye_zhinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bdz_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_all += 1;
                if (flag_all == 4) {
                    bdz_img1.setVisibility(View.GONE);
                    bdz_img11.setVisibility(View.GONE);
                    bdz_img111.setVisibility(View.GONE);
                    shouye_zhinan.setVisibility(View.GONE);
                    bianDZ.setEnabled(true);
                    device.setEnabled(true);
                    saoyisao.setEnabled(true);
                    wode.setEnabled(true);
                    PreferenceUtils.setPrefBoolean(MainActivitys.this, "zhinan_main", true);
                } else {
                    bdz_img11.setVisibility(View.GONE);
                    bdz_img1.setVisibility(View.GONE);
                    bdz_img111.setVisibility(View.GONE);
                }

            }
        });

        bdz_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_all += 1;
                if (flag_all == 4) {
                    bdz_tx.setVisibility(View.GONE);
                    bdz_img12.setVisibility(View.GONE);
                    shouye_zhinan.setVisibility(View.GONE);
                    bianDZ.setEnabled(true);
                    device.setEnabled(true);
                    saoyisao.setEnabled(true);
                    wode.setEnabled(true);
                    PreferenceUtils.setPrefBoolean(MainActivitys.this, "zhinan_main", true);
                } else {
                    bdz_tx.setVisibility(View.GONE);
                    bdz_img12.setVisibility(View.GONE);
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
                    shouye_zhinan.setVisibility(View.GONE);
                    bianDZ.setEnabled(true);
                    device.setEnabled(true);
                    saoyisao.setEnabled(true);
                    wode.setEnabled(true);
                    PreferenceUtils.setPrefBoolean(MainActivitys.this, "zhinan_main", true);
                } else {
                    zz_tx.setVisibility(View.GONE);
                    zz_tx1.setVisibility(View.GONE);
                }

            }
        });
        wode_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_all += 1;
                if (flag_all == 4) {
                    wode_tx.setVisibility(View.GONE);
                    wode_tx1.setVisibility(View.GONE);
                    shouye_zhinan.setVisibility(View.GONE);
                    bianDZ.setEnabled(true);
                    device.setEnabled(true);
                    saoyisao.setEnabled(true);
                    wode.setEnabled(true);
                    PreferenceUtils.setPrefBoolean(MainActivitys.this, "zhinan_main", true);
                } else {
                    wode_tx.setVisibility(View.GONE);
                    wode_tx1.setVisibility(View.GONE);
                }

            }
        });


        //变电站列表
        bianDZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivitys.this,StationListActivitys.class));
                Intent intent = new Intent(MainActivitys.this, StationListActivitys.class);
                intent.putExtra("SYTYPE", "bdz");//首页
                String user = PreferenceUtils.getPrefString(MainActivitys.this, "userInfo", null);
                if (user.split("-")[0].equals(Constants.admin)) {
                    ToastUtils.showLongToast(MainActivitys.this, "系统管理员无此操作！");
                } else {
                    startActivity(intent);
                }
            }
        });

        //装置列表
        device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = PreferenceUtils.getPrefString(MainActivitys.this, "userInfo", null);
                if (user.split("-")[0].equals(Constants.admin)) {
                    ToastUtils.showLongToast(MainActivitys.this, "系统管理员无此操作！");
                    return;
                }
                String czmc = PreferenceUtils.getPrefString(MainActivitys.this, "czmc", null); //厂站名称
                Intent intent = new Intent(MainActivitys.this, StationListActivitys.class);
                intent.putExtra("SYTYPE", "zz1");//无变电站
                if (czmc == null || "".equals(czmc) || util.getCZCSByGLDW() == null) {
                    startActivity(intent);
                } else {
                    Intent intent2 = new Intent(MainActivitys.this, DeviceListActivity.class);
                    intent2.putExtra("SYTYPE", "zz2");//有变电站
                    startActivity(intent2);
                }
            }
        });

        //扫一扫
        saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = PreferenceUtils.getPrefString(MainActivitys.this, "userInfo", null);
                if (user.split("-")[0].equals(Constants.admin)) {
                    ToastUtils.showLongToast(MainActivitys.this, "系统管理员无此操作！");
                } else {
                    startActivity(new Intent(MainActivitys.this, CaptureActivity.class));
                }
                //startActivityForResult(new Intent(MainActivitys.this, CaptureActivity.class), 100);
            }
        });

        //我的
        wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.END);
            }
        });

    }

    //对获取的变电站排序及获取首字母列表(首次出现的字母列表以及对应的位置)
    public void getCzcs() {
        String czmc = PreferenceUtils.getPrefString(MainActivitys.this, "czmc", null);
        if (czmc != null && !czmc.equals("")) {

            boolean hasCz = false;
            for (CZCS czcs : orglist) {
                if (czcs.getCzmc().equals(czmc)) {
                    hasCz = true;
                }
            }
            if (!hasCz) { //重置厂站信息
                PreferenceUtils.setPrefString(MainActivitys.this, "czmc", null);
                PreferenceUtils.setPrefString(MainActivitys.this, "czmcID", null);
            }
        }

        List<String> characterList = new ArrayList<>();
        List<Integer> characterPositionList = new ArrayList<>();
        //List<CZCS> orglists = new ArrayList<>();//原始数据
//        for (int i = 0; i <orglist.size() ; i++){ //测试加载500条   全部orglist.size()
//            orglists.add(orglist.get(i));
//        }

        Collections.sort(orglist, new PinyinComparator());  //排序
        if (orglist.size() > 0) {
            for (int i = 0; i < orglist.size(); i++) {
                //获取存在的字母
                String pinyin = TextPinyinUtil.getInstance().getPinyin(orglist.get(i).getCzmc());
                String character = (pinyin.charAt(0) + "").toUpperCase(Locale.ENGLISH);
                if (!characterList.contains(character)) {
                    if (character.hashCode() >= "A".hashCode() && character.hashCode() <= "Z".hashCode()) { // 是字母
                        characterList.add(character);
                        characterPositionList.add(i);
                    } else {
                        if (!characterList.contains("#")) {
                            characterList.add("#");
                            characterPositionList.add(i);
                        }
                    }
                }
            }
            DataHolder.getInstance().setData(orglist);
            DataHolder.getInstance().setZiMu(characterList);
            DataHolder.getInstance().setPosition(characterPositionList);
        }
    }

    //获取筛选条件原始数据
    public void filterData() {
        List<String> list1 = new ArrayList<>(); //未操作、已操作、未核对、已核对、已删除、已导出 、新增
        list1.add("未操作");
        list1.add("已操作");
        list1.add("未核对");
        list1.add("已核对");
        list1.add("已删除");
        list1.add("已导出");
        list1.add("新增");

        List<String> list2 = new ArrayList<>(); //电压等级
        List<BZSJ> bzsjList = util.getBZSJ("电压等级");
        for (BZSJ bzsj : bzsjList) {
            list2.add(bzsj.getBzsjSxmc());
        }

        List<String> list3 = new ArrayList<>(); //装置类别
        List<BZSJ> bzsjList33 = util.getBZSJ("主保护类别");
        bzsjList.toString();
        if (bzsjList33.size() > 0) {
            for (int i = 0; i < bzsjList33.size(); i++) {
                String namedata = bzsjList33.get(i).getBzsjSxmc() + "";
                list3.add(namedata);
            }
        }
        List<BZSJ> bzsjList3 = util.getBZSJ("辅助保护类别");
        for (BZSJ bzsj : bzsjList3) {
            list3.add(bzsj.getBzsjSxmc());
        }

        List<String> list4 = new ArrayList<>(); //调度单位
        List<GXDW> bzsjList4 = util.getDDDWByDWMC(null);
        if (bzsjList4 != null && bzsjList4.size() > 0) {
            for (int i = 0; i < bzsjList4.size(); i++) {
                for (int j = bzsjList4.size() - 1; j > i; j--) {
                    if (bzsjList4.get(j).getDDDW().equals(bzsjList4.get(i).getDDDW())) {
                        bzsjList4.remove(j);
                    }
                }
            }
            for (GXDW gxdw : bzsjList4) {
                if (gxdw.getDDDW() != null && !gxdw.getDDDW().equals("")) {
                    list4.add(gxdw.getDDDW());
                }
            }
        }
        List<String> list0 = new ArrayList<>(); //排序
        list0.add("电压等级从高到低");
        list0.add("保护类别");//装置类别
        list0.add("制造厂家");//装置厂家
        list0.add("默认排序先无码后有码");//默认排序
        List<String> list6 = new ArrayList<>(); //通过校验
        list6.add("通过校验");
        list6.add("未通过校验");


        List<List> list5 = new ArrayList<>();
        list5.add(list1);
        list5.add(list2);
        list5.add(list3);
        list5.add(list4);
        list5.add(list0);
        list5.add(list6);
        DataHolder.getInstance().setFilter(list5);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String uName = PreferenceUtils.getPrefString(this, "userInfo", null);
        if (uName != null && uName.equals(Constants.admin + "-" + Constants.admin_pw)) {
        } else {
            if (util != null) {
                util = null;
            }
            util = new DaoUtil(this);
        }
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("");
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View v = navigationView.getHeaderView(0);
        TextView t1 = (TextView) v.findViewById(R.id.header_name);
        TextView t2 = (TextView) v.findViewById(R.id.header_time);
        //默认不出现系统管理员权限
//        String uName = PreferenceUtils.getPrefString(this, "userInfo", null);
//        if (uName != null && !uName.equals(Constants.admin + "-" + Constants.admin_pw)) {
//            navigationView.getMenu().getItem(3).setVisible(false);
//        }

        String name = PreferenceUtils.getPrefString(this, "userInfo", null);
        if (name != null) {
            t1.setText(name.split("-")[0]);
        }
        t2.setText(TimeUtil.getCurrentTime());
    }

    //判断是否为数字
    public static boolean isNumeric(String str) {
        String reg = "[0-9]*";
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(str).matches();
    }

    //获取更新文件
    private File getUpdateFile(String path) {
        File[] files = new File(path).listFiles();
        if (files.length > 0) {
            return files[0];
        } else {
            return null;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        String user = PreferenceUtils.getPrefString(this, "userInfo", null);
        if (user.split("-")[0].equals(Constants.admin)) {
            if (id == R.id.settings || id == R.id.nav_send) {
            } else {
                ToastUtils.showLongToast(this, "系统管理员无此操作！");
                return true;
            }
        }
        switch (id) {
            case R.id.nav_data_changge: //更换数据
                if (AppUtils.checkNetwork(this)) {
                    if (util.hasBhpzOrFzbhsb()) {
                        ToastUtils.showLongToast(this, "本地存在未导出的数据，请导出后操作！");
                        return true;
                    }
                    String userInfo = PreferenceUtils.getPrefString(this, "userInfo", null);
                    if (userInfo.split("-")[0].equals(Constants.admin)) {
                        ToastUtils.showLongToast(this, "系统管理员无此操作！");
                        return true;
                    }

                    AppUtils.showDialog(this, "当前操作将会清空原有数据，请确认是否需要继续？", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {//取消
                            PopupWindow pop = (PopupWindow) v.getTag();
                            pop.dismiss();
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {//确定
                            startActivity(new Intent(MainActivitys.this, DataDownloadActivity.class));
                            PopupWindow pop = (PopupWindow) v.getTag();
                            pop.dismiss();
                            drawer.closeDrawer(GravityCompat.END);
                            finish();
                        }
                    });
                } else {
                    ToastUtils.showLongToast(this, "网络连接断开，请检查网络设置");
                }
                return true;
            case R.id.nav_gallery://导出
                if (AppUtils.isRegist()) {
                    dataOutPut();
                } else {
                    ToastUtils.showLongToast(this, "试用模式下不支持数据导出！");
                }
                break;
            case R.id.nav_send: //退出登录
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                //关闭自动备份
                unBind();
                break;
            case R.id.nav_data_store://数据备份
                dataRestore(true);
                break;
            case R.id.nav_data_restore://数据还原
                dataRestore(false);
                break;
            case R.id.settings: //设置
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    /**
     * 数据备份弹窗，与数据还原共用
     *
     * @param isRestore
     */
    private void dataRestore(final boolean isRestore) {
        View view = LayoutInflater.from(this).inflate(R.layout.app_data_store, null);
        resDialog = new CustomDialog(this, R.style.dialog_alert_style, 0);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_store_road = (TextView) view.findViewById(R.id.tv_store_road);
        final EditText ed_store_name = (EditText) view.findViewById(R.id.ed_store_name);
        final EditText ed_store_auto = (EditText) view.findViewById(R.id.ed_store_auto);
        final CheckBox cb_store_fj = (CheckBox) view.findViewById(R.id.cb_store_fj);
        Button btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        LinearLayout ll_store = (LinearLayout) view.findViewById(R.id.ll_store);
        RecyclerView listView_restore = (RecyclerView) view.findViewById(R.id.recyclerView_restore);

        final int[] currentPosition = {-1};
        Map<String, String> tmpMap = null;

        if (isRestore) {
            tv_title.setText("数据备份");
            ll_store.setVisibility(View.VISIBLE);
            String storeName = "store" + TimeUtil.getCurrentTime();
            ed_store_name.setText(storeName);
            listView_restore.setVisibility(View.GONE);
            storeFile = new StoreFile();

            tmpMap = AppUtils.setTxt(new HashMap(), "", false);
            tv_store_road.setText(tmpMap.get("store_path"));
            ed_store_auto.setText(tmpMap.get("auto_store"));
        } else {
            tv_title.setText("数据还原");
            ll_store.setVisibility(View.GONE);
            listView_restore.setVisibility(View.VISIBLE);

            if (storeFileList != null && storeFileList.size() > 0) {
                final FileRestoreAdapter adapter = new FileRestoreAdapter(this, storeFileList);
                listView_restore.setLayoutManager(new LinearLayoutManager(this));
                listView_restore.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this)); //左滑
                listView_restore.setAdapter(adapter);
                listView_restore.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

                adapter.setOnItemClickListener(new MyItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (!storeFileList.get(position).isFlag()) {
                            currentPosition[0] = position;
                            for (StoreFile storeFile : storeFileList) {
                                storeFile.setFlag(false);
                            }
                            storeFileList.get(position).setFlag(true);
                            adapter.setDatas(storeFileList);
                        }
                    }
                });
            } else { //无备份数据时界面可提示

            }
        }

        //选择备份路径
        tv_store_road.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivitys.this, ChooseFileActivity.class)
                        , ChooseFileActivity.RESULTCODE);
            }
        });

//确定
        final Map<String, String> finalTmpMap = tmpMap;
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRestore) {//数据备份
                    String fileName = ed_store_name.getText().toString().trim();
                    String filePath = tv_store_road.getText().toString().trim();
                    if (fileName.equals("")) {
                        ToastUtils.showLongToast(MainActivitys.this, "无效的文件名称！");
                        return;
                    }

                    String time = ed_store_auto.getText().toString().trim();
                    if (time.equals("")) {
                        ToastUtils.showLongToast(MainActivitys.this, "无效的自动备份时间！");
                        return;
                    } else {
                        int autoTime = Integer.parseInt(time);
                        if (autoTime < 60) {
                            ToastUtils.showLongToast(MainActivitys.this, "自动备份时间不小于60分钟！");
                            return;
                        }
                    }

                    if (finalTmpMap != null) {
                        if (!filePath.equals(Constants.APP_DATA_STORE)) {
                            //需要存储备份路径
                            finalTmpMap.put("store_path", filePath);
                        }

                        if (!time.equals(finalTmpMap.get("auto_store"))) {
                            //需要存自动备份时间设置
                            finalTmpMap.put("auto_store", time);
                            Constants.store_time = Integer.parseInt(time) * 60000;
                        }
                        AppUtils.setTxt(finalTmpMap, "", true);
                    }

                    dialog.setTitle("正在拼命备份...");
                    dialog.show();

                    String fileTime = TimeUtil.getCurrentTime();
                    if (storeFileList != null && storeFileList.size() > 0) {
                        for (StoreFile file : storeFileList) {
                            if (file.getFileName().equals(fileName)) {
                                fileName += fileTime;
                                break;
                            }
                        }
                    }

                    storeFile.setFileName(fileName);
                    storeFile.setFilePath(filePath + "/" + fileName + ".zip");
                    storeFile.setFileTime(fileTime);

                    new DataStoreAsyncTask(cb_store_fj.isChecked(), fileName, filePath, mHandler).execute();
                } else {//数据还原
                    if (currentPosition[0] == -1) {
                        ToastUtils.showLongToast(MainActivitys.this, "未选中还原文件！");
                        return;
                    }

                    dialog.setTitle("正在拼命还原...");
                    dialog.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            FileUtils.unZip(storeFileList.get(currentPosition[0]).getFilePath());
                            mHandler.sendEmptyMessage(5);
                        }
                    }).start();
                }
            }
        });

        //取消
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resDialog.dismiss();
            }
        });

        resDialog.setContentView(view, new ViewGroup.LayoutParams(ScreenUtils.getScreenWidth(this) * 3 / 4,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        resDialog.setCanceledOnTouchOutside(false);
        resDialog.show();
    }

    /**
     * 数据导出弹窗
     */
    private void dataOutPut() {
        View view = LayoutInflater.from(this).inflate(R.layout.app_data_store, null);
        resDialog = new CustomDialog(this, R.style.dialog_alert_style, 0);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_store_road = (TextView) view.findViewById(R.id.tv_store_road);
        final CheckBox cb_choose_all = (CheckBox) view.findViewById(R.id.cb_choose_all);
        final CheckBox cb_out = (CheckBox) view.findViewById(R.id.cb_out);
        Button btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        RelativeLayout ll_choose_all = (RelativeLayout) view.findViewById(R.id.ll_choose_all);
        LinearLayout ll_store = (LinearLayout) view.findViewById(R.id.ll_store);
        RecyclerView listView_restore = (RecyclerView) view.findViewById(R.id.recyclerView_restore);

        List<CZCS> org;//原始数据
        RLST_USER user;
        if (Constants.isLoginOnLine) {
            String userAccountJson = PreferenceUtils.getPrefString(MainActivitys.this, "Info", "");
            UserBean ub = new Gson().fromJson(userAccountJson, UserBean.class);
            user = util.getUserByName(ub.getUserAccount());
        } else {
            String name = PreferenceUtils.getPrefString(MainActivitys.this, "userInfo", "");
            user = util.getUserByName(name.split("-")[0]);
        }
        if (user == null) {
            org = util.getCZCS(null, isRegist);
        } else {
            if (user.getDwjb() != null && !user.getDwjb().equals("")) {
                org = util.getCZCS(null, isRegist);
            } else {
                org = util.getCZCS(user.getSsgs(), isRegist);
            }
        }


        tv_title.setText("数据导出");
        ll_store.setVisibility(View.GONE);
        ll_choose_all.setVisibility(View.VISIBLE);
        listView_restore.setVisibility(View.VISIBLE);
        data_out_put.clear();
        if (org != null && org.size() > 0) {
            for (CZCS czcs : org) {
                czcs.setChecked(false);
                data_out_put.add(czcs);
            }
        }

        adapter = new DataOutAdapter(this, data_out_put);
        listView_restore.setLayoutManager(new LinearLayoutManager(this));
//                listView_restore.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this)); //左滑
        listView_restore.setAdapter(adapter);
        listView_restore.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (!data_out_put.get(position).isChecked()) {
                    data_out_put.get(position).setChecked(true);
                    adapter.setDatas(data_out_put);
                } else {
                    data_out_put.get(position).setChecked(false);
                    adapter.setDatas(data_out_put);
                }
            }
        });

        //全选
        cb_choose_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数据全选导出
                if (cb_choose_all.isChecked()) {
                    for (CZCS file : data_out_put) {
                        file.setChecked(true);
                    }
                } else {
                    for (CZCS file : data_out_put) {
                        file.setChecked(false);
                    }
                }
                adapter.setDatas(data_out_put);
            }
        });
        //确定
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数据导出,是否包含已导出
                boolean iscontain = false;
                if (cb_out.isChecked()) {
                    iscontain = true;
                }

                dialog.setTitle("正在拼命导出...");
                dialog.show();
                List<String> data_out_put_choice = new ArrayList<>();
                for (CZCS czcs : data_out_put) {
                    if (czcs.isChecked()) {
                        data_out_put_choice.add(czcs.getCzmc() + "");
                    }
                }
                if (data_out_put_choice.size() > 0) {
                    resDialog.dismiss();
                    new OutputDBAsyncTask(MainActivitys.this, mHandler, data_out_put_choice, iscontain).execute();
                } else {
                    dialog.dismiss();
                    ToastUtils.showLongToast(MainActivitys.this, "请选择至少一个厂站！");
                }
            }
        });

        //取消
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resDialog.dismiss();
            }
        });

        resDialog.setContentView(view, new ViewGroup.LayoutParams(ScreenUtils.getScreenWidth(this) * 3 / 4,
                ScreenUtils.getScreenHeight(this) / 2));
        resDialog.setCanceledOnTouchOutside(false);
        resDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ChooseFileActivity.RESULTCODE) {
            String storePath = data.getStringArrayListExtra(ChooseFileActivity.SELECTPATH).get(0);
            tv_store_road.setText(storePath);
        }
    }

    //修改默认设置
    private void changesetting() {
        if (dialog_setting != null && dialog_setting.isShowing()) {
            return;
        } else {
            View view = LayoutInflater.from(this).inflate(R.layout.activity_setting, null);
            dialog_setting = new CustomDialog(this, R.style.dialog_alert_style, 0);
            final EditText tcsj = (EditText) view.findViewById(R.id.et_setting_tcsj);
            final EditText sycs = (EditText) view.findViewById(R.id.et_setting_sycs);
            final EditText et_url = (EditText) view.findViewById(R.id.et_setting_url);
            final CheckBox cb_jgms = (CheckBox) view.findViewById(R.id.cb_jgms_true);
            final CheckBox cb_tbms = (CheckBox) view.findViewById(R.id.cb_tbms_true);

            final LinearLayout ll_sycs = (LinearLayout) view.findViewById(R.id.ll_sycs);
            final LinearLayout ll_tcsj = (LinearLayout) view.findViewById(R.id.ll_tcsj);

            //默认不出现系统管理员权限,出现不同显示
            String uName = PreferenceUtils.getPrefString(this, "userInfo", null);
            if (uName != null && !uName.equals(Constants.admin + "-" + Constants.admin_pw)) {
                ll_sycs.setVisibility(View.GONE);
                ll_tcsj.setVisibility(View.GONE);
            }

            Map<String, String> keyMap = new HashMap<>();
            final Map<String, String> txtMap = AppUtils.setTxt(keyMap, "", false);
            String symax = txtMap.get("symax") + "";
            String csmax = txtMap.get("csmax") + "";
            String jgms = txtMap.get("jgms") + "";
            String tbms = txtMap.get("tbms") + "";
            int time = Constants.ch_times / 60000;
            Constants.sy_cout = Integer.parseInt(symax);
            tcsj.setText(time + "");
            sycs.setText(Constants.sy_cout + "");
            et_url.setText(txtMap.get("base_url"));
            if (jgms.equals("是")) {
                cb_jgms.setChecked(true);
            }
            if (tbms.equals("是")) {
                cb_tbms.setChecked(true);
            }

            Button okay = (Button) view.findViewById(R.id.queding);
            Button cancel = (Button) view.findViewById(R.id.quxiao);

            //确定
            okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int times = Constants.ch_times / 60000;
                    int count = Constants.sy_cout;
                    if (ll_sycs.getVisibility() == View.VISIBLE) {
                        times = Integer.parseInt(tcsj.getText() + "");
                        count = Integer.parseInt(sycs.getText() + "");
                    }
//                    (Constants.ch_times/60000+"");

                    String baseUrl = et_url.getText().toString() + "";
                    if (times < 30) {
                        ToastUtils.showToast(MainActivitys.this, "超时时间不小于30分钟");
                    } else if (count < 5) {
                        ToastUtils.showToast(MainActivitys.this, "试用次数不小于5次");
                    } else {
                        Constants.ch_times = times * 60000;
                        Constants.sy_cout = count;
                        txtMap.put("symax", count + "");
                        txtMap.put("csmax", Constants.ch_times + "");
                        if (!baseUrl.equals("") && baseUrl.startsWith("http://") ||
                                !baseUrl.equals("") && baseUrl.startsWith("https://")) {
                            if (AppUtils.isContainChinese(baseUrl) || AppUtils.isContainZM(baseUrl.split("//")[1])) {
                                ToastUtils.showToast(MainActivitys.this, "无效的url地址！");
                                return;
                            } else {
                                txtMap.put("base_url", baseUrl);
                            }
                        } else {
                            ToastUtils.showToast(MainActivitys.this, "无效的url地址！");
                            return;
                        }
                        if (cb_jgms.isChecked()) {
                            txtMap.put("jgms", "是");
                        } else {
                            txtMap.put("jgms", "否");
                        }
                        if (cb_tbms.isChecked()) {
                            txtMap.put("tbms", "是");
                        } else {
                            txtMap.put("tbms", "否");
                        }
                        //放开生成码使用
//                        txtMap.put("syms","否");
//                        txtMap.put("bdms","否");
                        Map<String, String> keyMap = AppUtils.setTxt(txtMap, "", true);

                        dialog_setting.dismiss();
                        ToastUtils.showToast(MainActivitys.this, "修改成功");
                    }
                }
            });
            //取消
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog_setting.dismiss();
                }
            });

            dialog_setting.setContentView(view, new ViewGroup.LayoutParams(ScreenUtils.getScreenWidth(this) * 3 / 4,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog_setting.setCanceledOnTouchOutside(false);
            dialog_setting.show();
        }
    }


    //判断是否含有特殊字符  true为包含，false为不包含
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    String bianhao = null, xinghao = null;

    private long exitTime = 0; // 退出时间

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }
}