package cn.com.sgcc.dev.customeView.recylerUtil;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.lang.ref.WeakReference;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/28
 */
public class MTLinearLayoutManager_H extends LinearLayoutManager {
    private float mMilliSecondPerInch = 0f;
    private MTLinearSmoothScroller mMTLinearLayoutSmoothScroller = null;
    private WeakReference<RecyclerView> mRecyclerViewReference;

    public MTLinearLayoutManager_H(Context context) {
        super(context);
    }

    public MTLinearLayoutManager_H(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public void setMilliSecondPerInch(float milliSecondPerInch) {
        mMilliSecondPerInch = milliSecondPerInch;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        if (mMilliSecondPerInch > 0) {
            MTLinearSmoothScroller.setMilliSecondPerInch(mMilliSecondPerInch);
        }

        MTLinearSmoothScroller linearSmoothScroller;
        if (mRecyclerViewReference != null && mRecyclerViewReference.get() == recyclerView) {
            linearSmoothScroller = mMTLinearLayoutSmoothScroller;
        } else {
            linearSmoothScroller = new MTLinearSmoothScroller(recyclerView.getContext()) {
                @Override
                public PointF computeScrollVectorForPosition(int targetPosition) {
                    return MTLinearLayoutManager_H.this.computeScrollVectorForPosition(targetPosition);
                }
            };
            if (mRecyclerViewReference != null) {
                mRecyclerViewReference.clear();
            }
            mRecyclerViewReference = new WeakReference<RecyclerView>(recyclerView);
            mMTLinearLayoutSmoothScroller = linearSmoothScroller;
        }

        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
}
