package cn.com.sgcc.dev.view.viewinterface;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.HorizontalScrollView;

/**
 * <p>@description:</p>
 *
 * @author lxf
 * @version 1.0.0
 * @since 2017/8/16
 */

public class LeftDeleteView extends HorizontalScrollView {

    private int start;//开始滑动的位置
    private int end;//结束滑动的位置
    private VelocityTracker velocityTracker;

    public LeftDeleteView(Context context) {
        super(context);
    }

    public LeftDeleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeftDeleteView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                start = (int) event.getX();//得到相对于屏幕左上角的x坐标值
                if (velocityTracker == null) {
                    velocityTracker = VelocityTracker.obtain();//获得VelocityTracker类实例
                }
                break;
            case MotionEvent.ACTION_UP:
                end = (int) event.getX();
                int width = 140;
                if (start > end) {//如果手指按下的X坐标大于手指抬起的X坐标
                    if (getScrollX() > width / 2 //得到x轴的按下和抬起的距离
                            || velocityTracker.getXVelocity() > 600) {//获得横向滑动的速度
                        // 注意一下：但是使用getXVelocity()之前请先调用computeCurrentVelocity(int)来初始化速率的单位
                        smoothScrollTo(width, 0);
                    } else {
                        smoothScrollTo(0, 0);
                    }
                }
                if (start < end) {
                    if (getScrollX() < width / 2 || velocityTracker.getXVelocity() > 600) {
                        smoothScrollTo(width, 0);
                    } else {
                        smoothScrollTo(0, 0);
                    }
                }
                velocityTracker.clear();
                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.addMovement(event);//将滑动事件加入到VelocityTracker类实例中
                break;
        }
        return super.onTouchEvent(event);
    }
}
