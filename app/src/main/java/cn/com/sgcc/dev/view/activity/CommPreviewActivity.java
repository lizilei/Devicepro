package cn.com.sgcc.dev.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.customeView.LoadingDialog;
import cn.com.sgcc.dev.customeView.ZoomTextView;
import cn.com.sgcc.dev.utils.FileUtils;
import cn.com.sgcc.dev.utils.ToastUtils;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/21
 */

public class CommPreviewActivity extends BaseActivity {

    private ScrollView ss;
    private WebView web;
    private TextView tv_center;
    private TextView app_toolbar_center;
    private ImageView app_toolbar_left;
    private PDFView pdfView;
    private String path;
    private String name;
    private String finalStr = "";
    private static final float zoomScale = 0.3f;//缩放比例

    private LoadingDialog loadingDialog;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    web.setVisibility(View.GONE);
                    ss.setVisibility(View.VISIBLE);

                    List<TextView> textViews = new ArrayList<>();
                    textViews.add(tv_center);
                    new ZoomTextView(ss, tv_center.getTextSize(), zoomScale, textViews);
                    tv_center.setText(finalStr);
                    break;
                case 1:
                    web.setVisibility(View.VISIBLE);
                    ss.setVisibility(View.GONE);

                    web.loadUrl("file://sdcard" + FileUtils.getInstance().htmlPath);
                    web.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }
                    });
                    web.getSettings().setJavaScriptEnabled(true);
                    web.getSettings().setSupportZoom(true);
                    web.getSettings().setUseWideViewPort(true);
                    break;
                case 2:
                    ss.setVisibility(View.GONE);
                    web.setVisibility(View.GONE);
                    pdfView.setVisibility(View.VISIBLE);
                    break;
            }
            loadingDialog.dismiss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_preview);

        tv_center = (TextView) findViewById(R.id.tv_center);
        ss = (ScrollView) findViewById(R.id.ss);
        web = (WebView) findViewById(R.id.web);
        app_toolbar_center = (TextView) findViewById(R.id.app_toolbar_center);
        app_toolbar_left = (ImageView) findViewById(R.id.app_toolbar_left);
        path = getIntent().getStringExtra("path");
        name = getIntent().getStringExtra("name");
        app_toolbar_center.setText(name);
        pdfView = (PDFView) findViewById(R.id.pdf_view);

        //不显示右上角关闭按钮
        findViewById(R.id.app_toolbar_sao1).setVisibility(View.GONE);

        loadingDialog = new LoadingDialog(this);
        loadingDialog.setTitle("加载中...");
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(false);
        initData();

        app_toolbar_left.setVisibility(View.VISIBLE);
        app_toolbar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        loadingDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (name.endsWith(".txt")) {
                    finalStr = FileUtils.readTXT(path);
                    mHandler.sendEmptyMessage(0);
                } else if (name.endsWith(".doc")) {
                    boolean isExists = FileUtils.getInstance().getRange("doc", path);
                    if (isExists) {
                        boolean isMake = FileUtils.getInstance().makeFile(name, path);
                        if (isMake) {
                            FileUtils.getInstance().readDOC2();
                        }
                    } else {
                        loadingDialog.dismiss();
                    }
                    mHandler.sendEmptyMessage(1);
                } else if (name.endsWith(".docx")) {
                    boolean isMake = FileUtils.getInstance().makeFile(name, path);
                    if (isMake) {
                        FileUtils.getInstance().readDOCX2();
                    } else {
                        loadingDialog.dismiss();
                    }

                    mHandler.sendEmptyMessage(1);
                } else if (name.endsWith(".xls")) {
                    boolean isMake = FileUtils.getInstance().makeFile(name, path);
                    if (isMake) {
                        try {
                            FileUtils.getInstance().readXLS2();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        loadingDialog.dismiss();
                    }
                    mHandler.sendEmptyMessage(1);
                } else if (name.endsWith(".xlsx")) {
                    boolean isMake = FileUtils.getInstance().makeFile(name, path);
                    if (isMake) {
                        try {
                            FileUtils.getInstance().readXLSX2();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        loadingDialog.dismiss();
                    }
                    mHandler.sendEmptyMessage(1);
                } else if (name.endsWith(".ppt")) {
                    finalStr = FileUtils.getInstance().readPPT(path).get(0);
                    mHandler.sendEmptyMessage(0);
                } else if (name.endsWith(".pptx")) {
                    finalStr = FileUtils.getInstance().readPPTX(path);
                    mHandler.sendEmptyMessage(0);
                } else if (name.endsWith(".pdf")) {
                    File file = new File(path);
                    pdfView.fromFile(file).enableSwipe(true).swipeHorizontal(true).enableDoubletap(false)
                            .defaultPage(1).onLoad(new OnLoadCompleteListener() {
                        @Override
                        public void loadComplete(int nbPages) {

                        }
                    }).onPageChange(new OnPageChangeListener() {
                        @Override
                        public void onPageChanged(int page, int pageCount) {
                            ToastUtils.showToast(CommPreviewActivity.this, page + "/" + pageCount);
                        }
                    })// 渲染风格（就像注释，颜色或表单）
                            .enableAnnotationRendering(false)
                            .password(null)
                            .scrollHandle(null)
                            // 改善低分辨率屏幕上的渲染
                            .enableAntialiasing(true)
                            // 页面间的间距。定义间距颜色，设置背景视图
                            .spacing(0)
                            .load();
                    mHandler.sendEmptyMessage(2);
                }
            }
        }).start();
    }
}