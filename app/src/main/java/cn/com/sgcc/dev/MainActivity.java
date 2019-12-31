package cn.com.sgcc.dev;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dlxx.mam.Internal.sdk.BaseResponse;
import com.dlxx.mam.Internal.sdk.IYDMHAPI;
import com.dlxx.mam.Internal.sdk.OauthManager;
import com.dlxx.mam.Internal.sdk.YDMHAPIFactory;

import cn.com.sgcc.dev.utils.ToastUtils;


public class MainActivity extends Activity {

    private IYDMHAPI api;

    //应用在平台注册后由平台提供
    private String appid = "ff808081630a22ba0163387375be0018";

    private static Dialog dialog;

    private static boolean showDialog = true;

    private OauthManager mOauthManager;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showWaitDialog();
        setContentView(R.layout.activity_main_for_mip);
        createHandler();
        //获取用户信息
        SharedPreferences userPreference = getSharedPreferences("pingresult", Context.MODE_PRIVATE);
        boolean status = userPreference.getBoolean("status",false);
        if (status) {
            //开发环境
            mOauthManager = new OauthManager("http://2.0.0.1:29000/up/appstore/rest/user");
            //生产环境
//            mOauthManager = new OauthManager("http://2.0.0.1:11111/up/appstore/rest/user");
        } else {
            //开发环境
            mOauthManager = new OauthManager("http://127.0.0.1:29000/up/appstore/rest/user");
            //生产环境
//            mOauthManager = new OauthManager("http://127.0.0.1:11111/up/appstore/rest/user");
        }
        mOauthManager.setAppid(BaseApplication.appid);
        mOauthManager.setSecretString(BaseApplication.secretString);
        mOauthManager.registerHandler(mHandler);

        api = YDMHAPIFactory.createYDMHAPI(this, BaseApplication.appid);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!mOauthManager.ydmhAuth(api,MainActivity.this)){
//                    Toast.makeText(MainActivity.this , "请先安装移动商店", Toast.LENGTH_SHORT).show();
//                };
				ToastUtils.showToast(MainActivity.this,"网络有误");
            }
        });
    }

    private void showWaitDialog() {
        if (showDialog) {
            dialog = new Dialog(this, R.style.progress_dialog);
            View content = LayoutInflater.from(this)
                    .inflate(R.layout.wait_dialog, null);
            dialog.setContentView(content);
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    private static void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public static class PingResultReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if("cn.com.sgcc.dev.action.pingresult".equals(intent.getAction())){
                showDialog = false;
                dismissDialog();
            }
        }
    }

    private void createHandler() {
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case OauthManager.MSG_WHAT_ACHIEVE_USERINFO_FAILED:
                        BaseResponse userinfoFail = (BaseResponse) msg.obj;
                        Toast.makeText(MainActivity.this, userinfoFail.resultDesc, Toast.LENGTH_LONG).show();
                        break;
                    case OauthManager.MSG_WHAT_ACHIEVE_USERINFO_SUCCESS:
                        //获取离线用户信息获取成功
                        //TODO:obj为获取到的用户信息，json格式，开发自行处理登录后逻辑
                        String obj = (String)msg.obj;
                    //    Toast.makeText(MainActivity.this, "离线", Toast.LENGTH_LONG).show();
                        Toast.makeText(MainActivity.this, obj, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };
    }
}
