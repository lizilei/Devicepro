package cn.com.sgcc.dev.customeView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/1/2
 */

public class MyExpListView extends ExpandableListView {
    public MyExpListView(Context context) {
        super(context);
    }

    public MyExpListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyExpListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
