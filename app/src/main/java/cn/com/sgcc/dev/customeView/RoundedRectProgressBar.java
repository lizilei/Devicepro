package cn.com.sgcc.dev.customeView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.com.sgcc.dev.R;

/**
 * <p>@description:带百分比显示的条形进度条</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/2
 */

public class RoundedRectProgressBar extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private String currentText;
    private int barColor;
    private int backColor;
    private int textColor;
    private float radius;

    private float progress = 0;
    private float total = 1;

    public RoundedRectProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedRectProgressBar(Context context) {
        this(context, null);
    }

    public RoundedRectProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        /*获取自定义参数的颜色值*/
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundedRectProgressBar, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.RoundedRectProgressBar_backColor:
                    backColor = a.getColor(attr, Color.GRAY);
                    break;
                case R.styleable.RoundedRectProgressBar_barColor:
                    barColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.RoundedRectProgressBar_textColor:
                    textColor = a.getColor(attr, Color.WHITE);
                    break;

            }

        }
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        radius = this.getMeasuredHeight() / 5;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //背景
        mPaint.setColor(backColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(0, 0, this.getMeasuredWidth(),
                this.getMeasuredHeight()), radius, radius, mPaint);
        //进度条
        mPaint.setColor(barColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(0, 0, this.getMeasuredWidth() * progress / total,
                this.getMeasuredHeight()), radius, radius, mPaint);
        //进度
        mPaint.setColor(textColor);
        mPaint.setTextSize(this.getMeasuredHeight() / 1.2f);
        String text = "" + Math.round((progress / total) * 100f) + "%";

        float x = this.getMeasuredWidth() * progress / total - mPaint.measureText(text) - 10;
        float y = this.getMeasuredHeight() / 2f - mPaint.getFontMetrics().ascent / 2f
                - mPaint.getFontMetrics().descent / 2f;
        canvas.drawText(text, x, y, mPaint);
        this.currentText = text;
    }

    public String getCurrentText() {
        return currentText;
    }

    /**
     * 设置进度条进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        if (progress > 100) {
            this.progress = 100;
        } else if (progress < 0) {
            this.progress = 0;
        } else {
            this.progress = progress;
        }
        this.postInvalidate();
    }

    public void init() {
        this.total = 1;
        this.progress = 0;
    }

    /**
     * 设置进度条进度
     *
     * @param progress
     */
    public void setProgress(long progress, long total) {
        if (this.total == 1) {
//            this.total = total;
            this.total = Float.parseFloat(String.valueOf(total));
        }
//        this.progress = progress;
        this.progress = Float.parseFloat(String.valueOf(progress));
        this.postInvalidate();
    }
}
