package cn.com.sgcc.dev.view.activity;//package cn.com.sgcc.dev.view.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.widget.TextView;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import cn.com.sgcc.dev.R;
//import cn.com.sgcc.dev.constants.Constants;
//import cn.com.sgcc.dev.customeView.RoundedRectProgressBar;
//
///**
// * <p>@description:app首次进入数据导入页</p>
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/9/2
// */
//
//public class DataSourceImportActivity extends BaseActivity {
//
//    private List<File> fileList = new ArrayList<>();
//    private RoundedRectProgressBar progressBar;
//    private TextView tv_hint;
//    private int progress;
//    private Timer timer;
//
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 1:
//                    tv_hint.setText("正在导入数据，请稍候...");
//                    mHandler.sendEmptyMessage(2);
//                    break;
//                case 2:
//                    progress = 0;
//                    timer = new Timer();
//                    timer.schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            progressBar.setProgress(progress);
//                            progress++;
//                            if (progress > 100) {
//                                timer.cancel();
//                                startActivity(new Intent(DataSourceImportActivity.this, LoginActivity.class));
//                                finish();
//                            }
//                        }
//                    }, 0, 30);
//                    break;
//            }
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_datasource_import);
//
//        progressBar = (RoundedRectProgressBar) findViewById(R.id.round_progressBar);
//        tv_hint = (TextView) findViewById(R.id.tv_hint);
//        getAllFiles(Constants.INPUT_PATH);
//    }
//
//    /**
//     * 获取指定目录下的所有文件
//     *
//     * @param path
//     */
//    private void getAllFiles(String path) {
//        File[] allFiles = new File(path).listFiles();
//        if (allFiles.length <= 0) {
//            mHandler.obtainMessage(2).sendToTarget();
//        } else {
//            for (File f : allFiles) {
//                if (f.exists()) {
//                    tv_hint.setText("解压中.....");
//                    //new UnzipAsyncTask(f, mHandler, progressBar).execute();
//                }
//            }
//        }
//    }
//}
