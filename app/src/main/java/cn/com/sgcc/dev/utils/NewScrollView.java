package cn.com.sgcc.dev.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by bryant
 */
public class NewScrollView extends ScrollView {
    private OnScrollChangeListener onmScrollChangeListener;

    public NewScrollView(Context context) {
        super(context);
    }

    public NewScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.onmScrollChangeListener!=null){
//            onmScrollChangeListener.onScrollChange(this,l,t,oldl,oldt);
        }

    }
//    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener){
//        onmScrollChangeListener = onScrollChangeListener;
//    }
//    public interface setOnScrollChangeListener{
//        void onScrollChanged(ScrollView view, int l, int t, int oldl, int oldt);
//    }
}
