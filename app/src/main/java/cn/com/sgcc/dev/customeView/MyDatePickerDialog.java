package cn.com.sgcc.dev.customeView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import cn.com.sgcc.dev.R;

/**
 * <p>@description:自定义时间选择控件</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/25
 */

public class MyDatePickerDialog extends Dialog implements DatePicker.OnDateChangedListener {
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";

    private final DatePicker mDatePicker;
    private final OnDateSetListener mCallback;

    private View view;

    public MyDatePickerDialog(Context context, OnDateSetListener mCallback, int year, int monthOfYear, int dayOfMonth) {
        this(context, 0, mCallback, year, monthOfYear, dayOfMonth);
    }

    protected MyDatePickerDialog(Context context, int themeResId, OnDateSetListener mCallback, int year, int monthOfYear, int dayOfMonth) {
        super(context, themeResId);
        this.mCallback = mCallback;
        view = LayoutInflater.from(context).inflate(R.layout.date_picker_dialog, null);

        mDatePicker = (DatePicker) view.findViewById(R.id.datePicker);
        mDatePicker.init(year, monthOfYear, dayOfMonth, this);
        //实现自己的标题和ok按钮
        setTitle("选择日期");
        setButton();
    }

    /**
     * 自己实现show方法，主要是为了把setContentView方法放到show方法后面，否则会报错
     */
    public void myShow() {
        show();
        setContentView(view);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    private void setTitle(String title) {
        ((TextView) view.findViewById(R.id.date_picker_title))
                .setText(title);
    }

    /**
     * 获取自己定义的响应按钮并 设置监听，直接调用构造时传进来的callback接口，同时关闭对话框
     */
    private void setButton() {
        view.findViewById(R.id.date_picker_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) {
                    mDatePicker.clearFocus();
                    mCallback.onDateSet(mDatePicker, mDatePicker.getYear()
                            , mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mDatePicker.init(year, monthOfYear, dayOfMonth, null);
    }

    public DatePicker getDatePicker() {
        return mDatePicker;
    }

    public void updateDate(int year, int monthOfYear, int dayOfMonth) {
        mDatePicker.updateDate(year, monthOfYear - 1, dayOfMonth);
    }

    @Override
    public Bundle onSaveInstanceState() {
        Bundle state = super.onSaveInstanceState();
        state.putInt(YEAR, mDatePicker.getYear());
        state.putInt(MONTH, mDatePicker.getMonth());
        state.putInt(DAY, mDatePicker.getDayOfMonth());
        return state;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        int year = savedInstanceState.getInt(YEAR);
        int month = savedInstanceState.getInt(MONTH);
        int day = savedInstanceState.getInt(DAY);
        mDatePicker.init(year, month, day, this);
    }

    /**
     * 接口回调
     */
    public interface OnDateSetListener {
        void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth);
    }
}
