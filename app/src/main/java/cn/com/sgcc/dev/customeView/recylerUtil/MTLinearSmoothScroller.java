package cn.com.sgcc.dev.customeView.recylerUtil;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;
import android.util.DisplayMetrics;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/28
 */
public abstract class MTLinearSmoothScroller extends LinearSmoothScroller {

    private static final float DEFAULT_MILLI_SECOND_PER_INCH = 500f;

    private static float sGlogalMilliSecondPerInch = DEFAULT_MILLI_SECOND_PER_INCH;

    public MTLinearSmoothScroller(Context context) {
        super(context);

        /* Reset milli second per inch to default */
        setMilliSecondPerInch(DEFAULT_MILLI_SECOND_PER_INCH);
    }

    /* Call this static setter before initiate a MTLinearSmoothScroller instance if you want a custom scroll speed */
    public static void setMilliSecondPerInch(float milliSecondPerInch) {
        sGlogalMilliSecondPerInch = milliSecondPerInch;
    }

    @Override
    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return sGlogalMilliSecondPerInch / displayMetrics.densityDpi;
    }
}
