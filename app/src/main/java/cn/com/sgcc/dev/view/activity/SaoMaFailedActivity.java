package cn.com.sgcc.dev.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.utils.ToastUtils;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/16
 */

public class SaoMaFailedActivity extends BaseActivity {
    private TextView tv;
    private Button btn_search;
    private Button btn_add;
    private ImageView app_toolbar_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saoma_failed);
        tv = (TextView) findViewById(R.id.tv_sbsbm);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_add = (Button) findViewById(R.id.btn_add);
        sbsbdm = getIntent().getStringExtra("sbsbdm");
        tv.setText("设备识别代码：" + sbsbdm);
        app_toolbar_left = (ImageView) findViewById(R.id.app_toolbar_left);
        app_toolbar_left.setVisibility(View.VISIBLE);

        app_toolbar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SaoMaFailedActivity.this, DeviceDetailsActivity.class);
//                intent.putExtra("sbsbdm", sbsbdm);
//                intent.putExtra("ZZTYPE", "FZSB");
//                intent.putExtra("BHSB", new FZBHSB());
//                intent.putExtra("STATE", "C");
//                startActivity(intent);
//                finish();
                showTypeDialog();

            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public CustomDialog type_dialog;
    private List data;
    String sbsbdm;

    public void showTypeDialog() {
        if (type_dialog != null && type_dialog.isShowing()) {
            return;
        } else {
            View view = LayoutInflater.from(SaoMaFailedActivity.this).inflate(R.layout.fragment_one_device_dialog_item, null);
            type_dialog = new CustomDialog(SaoMaFailedActivity.this, R.style.dialog_alert_style, 0);
            // 根据id在布局中找到控件对象
            TextView tv_dialog_protect = (TextView) view.findViewById(R.id.fragment_device_details_dialog_protect);
            TextView tv_dialog_auxiliary = (TextView) view.findViewById(R.id.fragment_device_details__dialog_auxiliary);
            data = new ArrayList();

            //保护设备
            tv_dialog_protect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ToastUtils.showToast(SaoMaFailedActivity.this, "当前选择保护设备");
                    Intent intent = new Intent(SaoMaFailedActivity.this, DeviceDetailsActivity.class);
                    intent.putExtra("sbsbdm", sbsbdm);
                    intent.putExtra("ZZTYPE", "BHSB");
                    intent.putExtra("STATE", "C");
                    startActivity(intent);
                    type_dialog.dismiss();
                }
            });
            //辅助设备
            tv_dialog_auxiliary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ToastUtils.showToast(SaoMaFailedActivity.this, "当前选择辅助设备");
                    Intent intent = new Intent(SaoMaFailedActivity.this, DeviceDetailsActivity.class);
                    intent.putExtra("sbsbdm", sbsbdm);
                    intent.putExtra("ZZTYPE", "FZSB");
                    intent.putExtra("STATE", "C");
                    startActivity(intent);
                    type_dialog.dismiss();

                }
            });
            type_dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            type_dialog.setCanceledOnTouchOutside(true);
            type_dialog.show();
        }
    }


}
