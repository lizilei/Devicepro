package cn.com.sgcc.dev.customeView;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/1/22.
 */

public abstract class ZoomView<V extends View> {
    protected V view;
    private static final int NONE = 0;//空
    private static final int DRAG = 1;//按下第一个点
    private static final int ZOOM = 2;//按下第二个点
    /**
     * 屏幕上点的数量
     */
    private int mode = NONE;
    /**
     * 记录下第二个点距离第一个点的距离
     */
    float oldDist;

    public ZoomView(V view) {
        this.view = view;
        setTouchListener();
    }

    private void setTouchListener() {
        /**
         * 缩放监听器
         */
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        mode = DRAG;
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        if (oldDist > 10f) {
                            mode = ZOOM;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == ZOOM) {
                            //正在移动的点距离初始点的距离
                            float newDist = spacing(event);
                            if (newDist > oldDist) {
                                zoomOut();
                            }
                            if (newDist < oldDist) {
                                zoomIn();
                            }
                        }
                        break;
                }
                return false;
            }

            /**
             * 求出2个触点间的距离
             * @param event
             * @return
             */
            private float spacing(MotionEvent event) {
                float x = event.getX(0) - event.getX(1);
                float y = event.getY(0) - event.getY(1);
                return (float) Math.sqrt(x * x + y * y);
            }
        });
    }

    protected abstract void zoomIn();

    protected abstract void zoomOut();
}
