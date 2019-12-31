package cn.com.sgcc.dev.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.customeView.MyDatePickerDialog;
import cn.com.sgcc.dev.utils.ToastUtils;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/25
 */
public class DateTimePickerActivity extends FragmentActivity {

    private TextView btn;
    private MyDatePickerDialog dialog;
    private int mYear, mMonth, mDay;
    private Calendar c;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_picker);

        initView();
    }

    public void initView() {
        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);

        dialog = new MyDatePickerDialog(this, new MyDatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear + 1;
                mDay = dayOfMonth;
                btn.setText(mYear + "-" + mMonth + "-" + mDay);
            }
        }, mYear, mMonth, mDay);

        btn = (TextView) findViewById(R.id.btn_choose);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mYear > 0) {
                    dialog.updateDate(mYear, mMonth, mDay);
                }
                dialog.myShow();
            }
        });
    }
}
