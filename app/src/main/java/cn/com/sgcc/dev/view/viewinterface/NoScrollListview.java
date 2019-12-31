package cn.com.sgcc.dev.view.viewinterface;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
/**
 * <p>@description:</p>
 * @author lxf
 * @version 1.0.0
 * @since 2018/1/2
 */
public class NoScrollListview extends ListView {

    public NoScrollListview(Context context) {
        super(context);
    }

    public NoScrollListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollListview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }


}
