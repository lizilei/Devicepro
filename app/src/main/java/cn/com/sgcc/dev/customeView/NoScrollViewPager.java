package cn.com.sgcc.dev.customeView;

/**
 * Created by Administrator1 on 2017/6/20.
 */

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 *
 * <p>@description:自定义ViewPager控件</p>
 *  滑动禁止，但是可以setCurrentItem的ViewPager
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */


public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
}


