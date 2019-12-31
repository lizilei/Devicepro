package cn.com.sgcc.dev.view.activity;//package cn.com.sgcc.dev.view.activity;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.FragmentActivity;
//import android.util.Log;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import cn.com.sgcc.dev.R;
//import cn.com.sgcc.dev.constants.Constants;
//import cn.com.sgcc.dev.net.FileDownloadService;
//import cn.com.sgcc.dev.net.RetrofitUtils;
//import cn.com.sgcc.dev.net.retrofit.ProgressResponseBody;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * <p>@description:</p>
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//public class SimpleActivity extends FragmentActivity {
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.common_list);
//
//        init();
//    }
//
//    private void init() {
//        final FileDownloadService downloadService = RetrofitUtils.retrofit.create(FileDownloadService.class);
//
//        new AsyncTask<Void, Long, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                Call<ProgressResponseBody> call = downloadService.downloadFileWithDynamicUrlAsync("",null);
//
//                call.enqueue(new Callback<ProgressResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ProgressResponseBody> call, Response<ProgressResponseBody> response) {
//                        if (response.isSuccessful()) {
//                            Log.d("TAG", "server contacted and has file");
//
//                            boolean writeToDisk = writeResponseBodyToDisk(response.body());
//                            if (writeToDisk) {
//                                Log.d("TAG", "writeToDisk success!");
//                            } else {
//                                Log.d("TAG", "writeToDisk failed!");
//                            }
//                        } else {
//                            Log.d("TAG", "server contacted failed");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ProgressResponseBody> call, Throwable throwable) {
//                        Log.d("TAG", throwable.getMessage());
//                    }
//                });
//
//                return null;
//            }
//        }.execute();
//    }
//
//    /**
//     * 将请求的文件写到本地地址
//     *
//     * @param body
//     * @return
//     */
//    private boolean writeResponseBodyToDisk(ResponseBody body) {
//        File file = new File(Constants.INPUT_PATH);
//
//        InputStream is = null;
//        OutputStream os = null;
//
//        try {
//            byte[] fileReader = new byte[4096];
//            long fileSize = body.contentLength();
//            long fileSizeDownloaded = 0;
//            is = body.byteStream();
//            os = new FileOutputStream(file);
//
//            int read;
//            while ((read = is.read(fileReader)) != -1) {
//                os.write(fileReader, 0, read);
//                fileSizeDownloaded += read;
//                Log.d("TAG", "file download：" + fileSizeDownloaded + "of" + fileSize);
//            }
//            os.flush();
//
//            return true;
//        } catch (FileNotFoundException e) {
//            return false;
//        } catch (IOException e) {
//            return false;
//        } finally {
//            try {
//                if (is != null)
//                    is.close();
//                if (os != null)
//                    os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
