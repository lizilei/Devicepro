package cn.com.sgcc.dev.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.FileAdapter;
import cn.com.sgcc.dev.model2.regist.FileInfo;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2019/1/17 0017
 */

public class ChooseFileActivity extends BaseActivity {

    private static final int REQUESCODE = 5;
    private static final String NEED_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private String mPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    private RecyclerView rvFileView;
    private EditText editText;
    private TextView btChose, tv_center;
    private ImageView app_toolbar_left;
    private List<FileInfo> data;
    private List<FileInfo> searchData;
    private FileAdapter fileAdapter;
    private boolean isChooseKey;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int c, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String res = editable.toString();
            if (res.trim().equals("")) {
                fileAdapter.setData(data, isChooseKey);
                return;
            }
            if (searchData == null) {
                searchData = new ArrayList<>();
            }
            searchData.clear();
            for (int i = 0; i < data.size(); i++) {
                String content = data.get(i).getFilePath().substring(data.get(i).getFilePath().lastIndexOf("/")).toLowerCase();
                if (content.contains(res)) {
                    searchData.add(data.get(i));
                }
            }
            fileAdapter.setData(searchData, isChooseKey);
        }
    };

    public static final String SELECTPATH = "SELECTPATH";
    public static final int RESULTCODE = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.filechoose_activity_main);
        isChooseKey = getIntent().hasExtra("chooseKey") ? true : false;

        initView();
        initData();
    }

    private void initData() {
        if (Build.VERSION.SDK_INT >= 23) {//判断系统版本是否大于6.0
            if (checkSelfPermission(NEED_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
                //检查是否有读写权限
                loadDataFrompATH(mPath);//从路径中加载数据
            } else {
                requestPermissions(new String[]{NEED_PERMISSION}, REQUESCODE);//申请权限
            }
            return;
        }
        loadDataFrompATH(mPath);//系统版本小于6.0直接加载数据
    }

    private void loadDataFrompATH(final String mPath) {
        data.clear();//data为RecyclerView中要显示的数据
        new Thread() {
            public void run() {
                super.run();
                File file = new File(mPath);
                File[] listFiles = file.listFiles();//获取子文件
                for (File f : listFiles) {
                    FileInfo fileInfo = new FileInfo();
                    if (isChooseKey) {
                        if (f.isDirectory()) {
                            if (f.getName().startsWith("."))
                                continue;
                            fileInfo.setDirectory(true);
                        } else {
                            if (f.getName().startsWith("."))
                                continue;
                            if (f.getName().contains(".")) {
                                String suffix = f.getName().substring(f.getName().lastIndexOf(".") + 1);
                                if (!suffix.equalsIgnoreCase("key"))
                                    continue;
                            } else {
                                continue;
                            }
                            fileInfo.setDirectory(false);
                        }
                    } else {
                        if (!f.isDirectory() || f.getName().startsWith(".")) {//如果不是路径或者以 . 开头的文件夹 则直接跳过
                            continue;
                        }
                    }

                    fileInfo.setFileName(f.getName());
                    fileInfo.setFilePath(f.getAbsolutePath());
                    data.add(fileInfo);//往集合中添加符合条件的数据
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fileAdapter.setData(data, isChooseKey);//将数据载入适配器当中
                    }
                });
            }
        }.start();
    }

    private void initView() {
        app_toolbar_left = (ImageView) findViewById(R.id.app_toolbar_left);
        findViewById(R.id.app_toolbar_sao).setVisibility(View.GONE);
        tv_center = (TextView) findViewById(R.id.app_toolbar_center);
        if (isChooseKey) {
            tv_center.setText("请选择KEY文件");
        } else {
            tv_center.setText("请选择备份路径");
        }
        editText = (EditText) findViewById(R.id.app_search_edit);
        editText.setHint("请输入文件夹名称");
        rvFileView = (RecyclerView) findViewById(R.id.rvFileView);
        btChose = (TextView) findViewById(R.id.btChose);
        data = new ArrayList<>();
        rvFileView.setLayoutManager(new LinearLayoutManager(this));
        fileAdapter = new FileAdapter(data, this, R.layout.filechoose_layout_fileitme, ChooseFileActivity.this);
        fileAdapter.setEvent(new FileAdapter.Event() {
            @Override
            public void enterNextDir(FileInfo fileInfo) {
                editText.setText("");
                mPath = fileInfo.getFilePath();
                loadDataFrompATH(mPath);
            }
        });
        rvFileView.setAdapter(fileAdapter);
        editText.addTextChangedListener(textWatcher);
        //返回按钮
        app_toolbar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPath.trim().equals(Environment.getExternalStorageDirectory().getAbsolutePath())) {
                    ChooseFileActivity.this.finish();
                } else {
                    File file = new File(mPath);
                    if (!file.exists()) {
                    }
                    mPath = file.getParent();
                    loadDataFrompATH(mPath);
                }
            }
        });

        //确定
        btChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ArrayList<String> selectData = fileAdapter.getSelectData();
                if (selectData == null || selectData.size() == 0) {
                    if (isChooseKey) {
                        Toast.makeText(ChooseFileActivity.this, "未选中KEY文件", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ChooseFileActivity.this, "未选中备份路径", Toast.LENGTH_SHORT).show();
                    }

                } else if (selectData != null && selectData.size() == 1) {
                    intent.putStringArrayListExtra(SELECTPATH, fileAdapter.getSelectData());
                    setResult(RESULTCODE, intent);
                    if (!isChooseKey) {
                        Toast.makeText(ChooseFileActivity.this, "备份路径设置成功", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                } else {
                    Toast.makeText(ChooseFileActivity.this, "只能选择一个备份路径,请重新选择", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESCODE && permissions[0].equals(NEED_PERMISSION)) {
            loadDataFrompATH(mPath);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {//点击的是返回键
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {//按键的按下事件
                if (mPath.trim().equals(Environment.getExternalStorageDirectory().getAbsolutePath())) {
                    return super.onKeyDown(keyCode, event);
                } else {
                    File file = new File(mPath);
                    if (!file.exists()) {
                        return super.onKeyDown(keyCode, event);
                    }
                    mPath = file.getParent();
                    loadDataFrompATH(mPath);
                    return false;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
