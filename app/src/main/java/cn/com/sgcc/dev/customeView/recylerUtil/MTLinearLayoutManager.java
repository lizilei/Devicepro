package cn.com.sgcc.dev.customeView.recylerUtil;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/28
 */
public class MTLinearLayoutManager extends LinearLayoutManager {
    private float mMilliSecondPerInch = 0f;
    private MTLinearSmoothScroller mMTLinearLayoutSmoothScroller = null;
    private WeakReference<RecyclerView> mRecyclerViewReference;

    private int[] mMeasuredDimension = new int[2];

    public MTLinearLayoutManager(Context context) {
        super(context);
    }

    public MTLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
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
                    return MTLinearLayoutManager.this.computeScrollVectorForPosition(targetPosition);
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

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);

        int width = 0;
        int height = 0;
        for (int i = 0; i < getItemCount(); i++) {
            measureScrapChild(recycler, i,
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    mMeasuredDimension);

            if (getOrientation() == HORIZONTAL) {
                width = width + mMeasuredDimension[0];
                if (i == 0) {
                    height = mMeasuredDimension[1];
                }
            } else {
                height = height + mMeasuredDimension[1];
                if (i == 0) {
                    width = mMeasuredDimension[0];
                }
            }
        }
        switch (widthMode) {
            case View.MeasureSpec.EXACTLY:
                width = widthSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        switch (heightMode) {
            case View.MeasureSpec.EXACTLY:
                height = heightSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        setMeasuredDimension(width, height);
    }

    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
                                   int heightSpec, int[] measuredDimension) {
        try {
            View view = recycler.getViewForPosition(0);//fix 动态添加时报IndexOutOfBoundsException

            if (view != null) {
                RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();

                int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                        getPaddingLeft() + getPaddingRight(), p.width);

                int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
                        getPaddingTop() + getPaddingBottom(), p.height);

                view.measure(childWidthSpec, childHeightSpec);
                measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
                measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;
                recycler.recycleView(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}