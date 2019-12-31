package cn.com.sgcc.dev.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.ConversionUtil;
import cn.com.sgcc.dev.utils.ToastUtils;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/23
 */

public class AboutActivity extends BaseActivity {

    private TextView tv_center;
    private TextView app_toolbar_center;
    private ImageView app_toolbar_left,app_toolbar_sao1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        tv_center = (TextView) findViewById(R.id.tv_center);
        app_toolbar_center = (TextView) findViewById(R.id.app_toolbar_center);
        app_toolbar_left = (ImageView) findViewById(R.id.app_toolbar_left);
        app_toolbar_sao1 = (ImageView) findViewById(R.id.app_toolbar_sao1);

        app_toolbar_center.setText("关于");
        tv_center.setText("当前版本：" + AppUtils.getVersion(this));
//        app_toolbar_left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                int sp = ConversionUtil.px2sp(AboutActivity.this, 24);
////                int px = ConversionUtil.sp2px(AboutActivity.this, 18);
////                ToastUtils.showLongToast(AboutActivity.this, "24 px2sp:" + sp + "----18sp2px" + px);
//                finish();
//            }
//        });


//        List<RZGL> rzgl = DBManager.getmInstance(this).getSession().getRZGLDao().loadAll();

        //返回按钮
        app_toolbar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutActivity.this.finish();
            }
        });

        //结束按钮X
        app_toolbar_sao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutActivity.this, MainActivitys.class));
                AboutActivity.this.finish();
            }
        });
    }
}
