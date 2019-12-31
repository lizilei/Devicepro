package cn.com.sgcc.dev.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.sgcc.dev.BaseApplication;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.DataDownloadAdapter;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.LoadingDialog;
import cn.com.sgcc.dev.customeView.RoundedRectProgressBar;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.CZXX;
import cn.com.sgcc.dev.model2.UserInfo;
import cn.com.sgcc.dev.model2.regist.CzInfo;
import cn.com.sgcc.dev.model2.regist.DecryptKey;
import cn.com.sgcc.dev.model2.regist.UserBean;
import cn.com.sgcc.dev.model2.vo.DataHolder;
import cn.com.sgcc.dev.net.BaseCallModel;
import cn.com.sgcc.dev.net.BaseCallModel3;
import cn.com.sgcc.dev.net.BaseCallModel4;
import cn.com.sgcc.dev.net.FileDownloadService;
import cn.com.sgcc.dev.net.MyCallBack;
import cn.com.sgcc.dev.net.MyCallBack3;
import cn.com.sgcc.dev.net.MyCallBack4;
import cn.com.sgcc.dev.net.ProjectAPIService;
import cn.com.sgcc.dev.net.RetrofitUtils;
import cn.com.sgcc.dev.net.retrofit.DownloadProgressHandler;
import cn.com.sgcc.dev.net.retrofit.ProgressHelper;
import cn.com.sgcc.dev.net.retrofit.ProgressRequestBody;
import cn.com.sgcc.dev.net.retrofit.ProgressResponseBody;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.FileUtils;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.viewinterface.MFCleanEditText;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <p>@description:在线方式获取数据，展示变电站</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/3/20
 */

public class DataDownloadActivity extends BaseActivity {
    @BindView(R.id.app_toolbar_left)
    ImageView appToolbarLeft;
    @BindView(R.id.app_toolbar_center)
    TextView appToolbarCenter;
    @BindView(R.id.app_toolbar_sao)
    ImageView appToolbarSao;
    @BindView(R.id.app_search_edit)
    MFCleanEditText appSearchEdit;
    @BindView(R.id.list_station)
    ListView listStation;
    @BindView(R.id.tv_reTry)
    TextView tv_reTry;

    private List<CzInfo.Czxq> orgList = new ArrayList<>();  //原始数据
    private List<CzInfo.Czxq> searchList = new ArrayList<>();//搜索数据
    private List<CzInfo.Czxq> chooseList = new ArrayList<>();//选中数据
    private DataDownloadAdapter adapter;

    private PopupWindow pop;
    private RoundedRectProgressBar rectProgressBar;
    private TextView tv_hint;

    private LoadingDialog progressDialog;


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case -2:
                    ToastUtils.showLongToast(DataDownloadActivity.this, "下载失败请重试！");
                    pop.dismiss();
                    startTimer_for(); //开始倒计时
                    break;
                case -1:
                    ToastUtils.showLongToast(DataDownloadActivity.this, "文件写入失败请重试！");
                    pop.dismiss();

                    startTimer_for(); //开始倒计时
                    break;
                case 0: //写入成功，进行解压
                    tv_hint.setText("正在拼命解压...");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String show = FileUtils.unZip(mHandler);

                            if (show != null && !show.equals("")) {
                                File[] allFiles = new File(Constants.INPUT_PATH).listFiles();
                                for (File f : allFiles) {
                                    String name = f.getName();
                                    if (name.substring(name.length() - 2, name.length()).equals("db")) {
                                        Constants.DB_NAME = name;
                                        break;
                                    }
                                }

                                //超时重置
                                Constants.loginout = false;
                                //清空保存的变电站信息
                                PreferenceUtils.setPrefString(DataDownloadActivity.this, "czmc", null);
                                PreferenceUtils.setPrefString(DataDownloadActivity.this, "czmcID", null);
                                DataHolder.getInstance().init();

                                IDaoUtil util = new DaoUtil(DataDownloadActivity.this);
                                UserInfo info = new UserInfo();
                                info.setId(Long.valueOf(TimeUtil.getCurrentTime2()));
                                String userInfo = PreferenceUtils.getPrefString(DataDownloadActivity.this, "userInfo", null);
                                String userAccountJson = PreferenceUtils.getPrefString(DataDownloadActivity.this, "Info", "");
                                UserBean ub = new Gson().fromJson(userAccountJson, UserBean.class);

                                info.setUsername(userInfo.split("-")[0]);
                                info.setPassword(userInfo.split("-")[1]);
                                info.setNameInTf(ub.getUserAccount());
                                info.setSsgs(ub.getOrgName());

                                util.coreSave(info);

                                DataDownloadActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pop.dismiss();
                                    }
                                });

                                startActivity(new Intent(DataDownloadActivity.this, MainActivitys.class));
                                finish();
                                startTimer_for(); //开始倒计时}
                            } else {
                                DataDownloadActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pop.dismiss();
                                    }
                                });
                                ToastUtils.showLongToast(DataDownloadActivity.this, show);
                            }
                        }
                    }).start();
                    break;
                case 1://更新解压进度
                    int precentDone = msg.getData().getInt("precent");
                    rectProgressBar.init();
                    rectProgressBar.setProgress(precentDone, 100);
                    break;
                case 2: //解压失败
                    Bundle bundle = msg.getData();
                    DataDownloadActivity.this.

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pop.dismiss();
                                }
                            });
                    ToastUtils.showLongToast(DataDownloadActivity.this, bundle.getString("errorMsg"));
                    break;
                case 3: //解压成功
//                    File[] allFile = new File(Constants.INPUT_PATH).listFiles();
//                    for (File f : allFile) {
//                        String name = f.getName();
//                        if (name.substring(name.length() - 2, name.length()).equals("db")) {
//                            Constants.DB_NAME = name;
//                            break;
//                        }
//                    }
//
//                    //超时重置
//                    Constants.loginout = false;
//                    //清空保存的变电站信息
//                    PreferenceUtils.setPrefString(DataDownloadActivity.this, "czmc", null);
//                    PreferenceUtils.setPrefString(DataDownloadActivity.this, "czmcID", null);
//                    DataHolder.getInstance().init();
//
//                    IDaoUtil util = new DaoUtil(DataDownloadActivity.this);
//                    UserInfo info = new UserInfo();
//                    info.setId(Long.valueOf(TimeUtil.getCurrentTime2()));
//                    String userInfo = PreferenceUtils.getPrefString(DataDownloadActivity.this, "userInfo", null);
//                    String userAccountJson = PreferenceUtils.getPrefString(DataDownloadActivity.this, "Info", "");
//                    UserBean ub = new Gson().fromJson(userAccountJson, UserBean.class);
//
//                    info.setUsername(userInfo.split("-")[0]);
//                    info.setPassword(userInfo.split("-")[1]);
//                    info.setNameInTf(ub.getUserAccount());
//                    info.setSsgs(ub.getOrgName());
//
//                    util.coreSave(info);
//
//                    startActivity(new Intent(DataDownloadActivity.this, MainActivitys.class));
//                    finish();
//
//                    DataDownloadActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            pop.dismiss();
//                        }
//                    });
//
//                    startTimer_for(); //开始倒计时
                    break;
            }
        }
    };

    /**
     * 写入关联信息
     *
     * @param info
     */
    public void writeUserInfo(UserInfo info) {
        IDaoUtil util = new DaoUtil(this);

        util.coreSave(info);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_download);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        progressDialog = new LoadingDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("加载中...");
        appToolbarLeft.setVisibility(View.GONE);
        appToolbarSao.setVisibility(View.GONE);
        appToolbarCenter.setText("数据下载");
//        listStation.setEmptyView(tv_reTry);
        adapter = new DataDownloadAdapter(this);
        listStation.setAdapter(adapter);

        //请求数据
        getCzxx();

        listStation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (searchList.size() > 0) {//搜索
                    if (searchList.get(position).isChecked()) { //选中-未选中
                        chooseList.remove(searchList.get(position));
                        searchList.get(position).setChecked(false);
                    } else { //未选中-选中
                        searchList.get(position).setChecked(true);
                        chooseList.add(searchList.get(position));
                    }
                    adapter.setDatas(searchList);
                } else {
                    if (orgList.get(position).isChecked()) {
                        chooseList.remove(orgList.get(position));
                        orgList.get(position).setChecked(false);
                    } else {
                        orgList.get(position).setChecked(true);
                        chooseList.add(orgList.get(position));
                    }
                    adapter.setDatas(orgList);
                }
            }
        });

        appSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                if (!str.equals("")) {
                    if (searchList != null && searchList.size() > 0) {
                        searchList.clear();
                    }
                    for (CzInfo.Czxq CZXX : orgList) {
                        if (CZXX.getStationName().contains(str)) {
                            searchList.add(CZXX);
                        }
                    }
                    adapter.setDatas(searchList);
                } else {
                    adapter.setDatas(orgList);
                    searchList.clear();
                }
            }
        });
    }

    /**
     * 获取厂站信息
     */
    private void getCzxx() {
        stopTimer();//停止倒计时

        progressDialog.show();
        String userAccountJson = PreferenceUtils.getPrefString(this, "Info", "");
        UserBean ub = new Gson().fromJson(userAccountJson, UserBean.class);

        String account = "";
        if (ub != null && ub.getUserAccount() != null) {
            account = ub.getUserAccount();
        }
        ProjectAPIService apiService = RetrofitUtils.retrofit.create(ProjectAPIService.class);
        apiService.getCZCS(Constants.ROOT_URL + Constants.GET_CZXX + "/" + account, Constants.Cookie).enqueue(new MyCallBack3<BaseCallModel3<CzInfo>>() {
            @Override
            public void onSuc(Response<BaseCallModel3<CzInfo>> response) {
                tv_reTry.setVisibility(View.GONE);
                orgList = response.body().resultValue.getTfczList();
                searchList.addAll(orgList);

                //如果用户已注册则过滤key文件之外的站
                if (AppUtils.isRegist()) {
                    if (BaseApplication.getInstance().getDecryptKey() == null) {
                        BaseApplication.getInstance().initDecryptKey();
                    }
                    DecryptKey dy = BaseApplication.getInstance().getDecryptKey();

                    for (CzInfo.Czxq czxq : searchList) {
                        boolean has = false;
                        for (DecryptKey.CzInfo czInfo : dy.getList()) {
                            if (czxq.getStationName().equals(czInfo.getCzmc()) &&
                                    czxq.getOrgName().equals(czInfo.getCzgldw())) {
                                has = true;
                                break;
                            }
                        }
                        if (!has) {
                            orgList.remove(czxq);
                        }
                    }
                }
                searchList.clear();

                adapter.setDatas(orgList);
                progressDialog.dismiss();

                startTimer_for();//开始即时
            }

            @Override
            public void onFail(String msg) {
                progressDialog.dismiss();
                tv_reTry.setVisibility(View.VISIBLE);
                ToastUtils.showLongToast(DataDownloadActivity.this, msg);
            }

            @Override
            public void onAutoLogin() {

            }
        });
    }

    @OnClick({R.id.btn_down, R.id.btn_choose_all, R.id.tv_reTry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reTry:
                getCzxx();
                break;
            case R.id.btn_down:
                List<Map<String, String>> list = new ArrayList<>();
                if (chooseList.size() > 0) {
                    for (CzInfo.Czxq czxx : chooseList) {
                        Map<String, String> map = new HashMap<>();
                        map.put("stationName", czxx.getStationName());
                        map.put("orgName", czxx.getOrgName());
                        list.add(map);
                    }
                } else {
                    ToastUtils.showLongToast(this, "请至少选择一个厂站！");
                    return;
                }

                showDownloadWindow();
                Map<String, Object> map = new HashMap<>();
                map.put("items", list);

                downLoad(map);
                break;
            case R.id.btn_choose_all:
                boolean isCheck = false;
                if (searchList != null && searchList.size() > 0) {//搜索
                    for (CzInfo.Czxq czxx : searchList) {
                        if (!czxx.isChecked()) {
                            isCheck = true;
                            break;
                        }
                    }
                    if (isCheck) {//全选
                        for (CzInfo.Czxq czxx : searchList) {
                            czxx.setChecked(true);
                        }
                        chooseList.clear();
                        chooseList.addAll(searchList);
                    } else {//全不选
                        for (CzInfo.Czxq czxx : searchList) {
                            czxx.setChecked(false);
                        }
                        chooseList.clear();
                    }
                    adapter.setDatas(searchList);
                } else {
                    for (CzInfo.Czxq czxx : orgList) {
                        if (!czxx.isChecked()) {
                            isCheck = true;
                            break;
                        }
                    }
                    if (isCheck) {//全选
                        for (CzInfo.Czxq czxx : orgList) {
                            czxx.setChecked(true);
                        }
                        chooseList.clear();
                        chooseList.addAll(orgList);
                    } else {//全不选
                        for (CzInfo.Czxq czxx : orgList) {
                            czxx.setChecked(false);
                        }
                        chooseList.clear();
                    }
                    adapter.setDatas(orgList);
                }
                break;
        }
    }


    private void showDownloadWindow() {
        listStation.setEnabled(false);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_datasource_import, null);
        rectProgressBar = (RoundedRectProgressBar) view.findViewById(R.id.round_progressBar);
        tv_hint = (TextView) view.findViewById(R.id.tv_hint);
        tv_hint.setText("正在拼命下载...");

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
                listStation.setEnabled(true);
            }
        });

//        pop.setWidth(500);
//        pop.setHeight(200);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(false);
        pop.setContentView(view);
        pop.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    @Override
    public void onBackPressed() {
        if (pop != null && pop.isShowing()) {
            return;
        }
        super.onBackPressed();
    }

    /**
     * 数据下载
     *
     * @param map
     */
    private void downLoad(Map<String, Object> map) {
        stopTimer();//停止倒计时

        FileDownloadService downloadService = RetrofitUtils.retrofit3.create(FileDownloadService.class);

        ProgressHelper.setProgressHandler(new DownloadProgressHandler() {
            @Override
            protected void onProgress(long bytesRead, long contentLength, boolean done) {
                rectProgressBar.setProgress(bytesRead, contentLength);

                tv_hint.setText("正在拼命下载 （" + FileUtils.formatFileSize(bytesRead)
                        + "/" + FileUtils.formatFileSize(contentLength) + "）");
            }
        });

        downloadService.downloadFileWithDynamicUrlAsync(Constants.ROOT_URL + Constants.APP_DOWN_DBFILE, Constants.Cookie, map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful() && response.raw().code() == 200) {
                    boolean iswrite = writeResponseBodyToDisk(response.body());
                    if (iswrite) {
                        mHandler.sendEmptyMessage(0);
                    }
                } else {
                    mHandler.sendEmptyMessage(-2);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mHandler.sendEmptyMessage(-2);
            }
        });
    }

    /**
     * 将请求的文件写到本地地址
     *
     * @param body
     * @return
     */
    private boolean writeResponseBodyToDisk(ResponseBody body) {
        File file = new File(Constants.INPUT_PATH + "rlst" + TimeUtil.getCurrentTime2() + ".zip");

        InputStream is = null;
        OutputStream os = null;
        try {
            byte[] fileReader = new byte[4096];
            is = body.byteStream();
            os = new FileOutputStream(file);

            is.available();

            int read;
            while ((read = is.read(fileReader)) != -1) {
                os.write(fileReader, 0, read);
            }
            os.flush();
            return true;
        } catch (FileNotFoundException e) {
            file.delete();
            return false;
        } catch (IOException e) {
            file.delete();
            return false;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}