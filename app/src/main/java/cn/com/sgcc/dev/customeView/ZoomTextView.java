package cn.com.sgcc.dev.customeView;

import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

/**
 * TextView手势缩放动态改变字体大小
 * Created by Administrator on 2017/1/22.
 */

public class ZoomTextView extends ZoomView<ScrollView> {

    /**
     * 最小字体尺寸
     */
    public static final float MIN_TEXT_SIZE = 16f;
    /**
     * 最大字体尺寸
     */
    public static final float MAX_TEXT_SIZE = 30.0f;
    /**
     * 缩放比例
     */
    private float scale;
    /**
     * 设置字体大小
     */
    private float textSize;

    private List<TextView> textViews;

    public ZoomTextView(ScrollView view, float textSize, float scale, List<TextView> textViews) {
        super(view);
        this.scale = scale;
        this.textSize = textSize;
        this.textViews = textViews;
    }

    @Override
    protected void zoomIn() {
        textSize -= scale;
        if (textSize < MIN_TEXT_SIZE) {
            textSize = MIN_TEXT_SIZE;
        }
        for (TextView tv : textViews) {
            tv.setTextSize(textSize);
        }
    }

    @Override
    protected void zoomOut() {
        textSize += scale;
        if (textSize > MAX_TEXT_SIZE) {
            textSize = MAX_TEXT_SIZE;
        }
        for (TextView tv : textViews) {
            tv.setTextSize(textSize);
        }
    }
}
