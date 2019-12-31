package cn.com.sgcc.dev.customeView.xListView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.sgcc.dev.R;

/**
 * Created by Administrator on 2016/11/4.
 */
public class XListViewFooter extends LinearLayout {
    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_LOADING = 2;

    private Context mContext;
    private View mContentView;
    private View mProgressBar;
    private TextView mHintView;

    public XListViewFooter(Context context) {
        super(context);
        initView(context);
    }

    public XListViewFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LinearLayout moreView = (LinearLayout) LayoutInflater.from(mContext)
                .inflate(R.layout.xlistview_footer, null);
        addView(moreView);
        moreView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        mContentView = moreView.findViewById(R.id.xlistview_footer_content);
        mProgressBar = moreView.findViewById(R.id.xlistview_footer_progressbar);
        mHintView = (TextView) moreView.findViewById(R.id.xlistview_footer_hint_textview);
    }

    public void setState(int state) {
        mHintView.setVisibility(INVISIBLE);//开始底部控件都隐藏
        mProgressBar.setVisibility(INVISIBLE);
        mHintView.setVisibility(INVISIBLE);
        if (state == STATE_READY) {// 如果是第一页状态，那么“查看更多”显示
            mHintView.setVisibility(VISIBLE);
            mHintView.setText("松开载入更多");
        } else if (state == STATE_LOADING) {
            mProgressBar.setVisibility(VISIBLE);
        } else {
            mHintView.setVisibility(VISIBLE);
            mHintView.setText("查看更多");
        }
    }

    public void setBottomMargin(int height) {
        if (height < 0) {
            return;
        }
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        lp.bottomMargin = height;
        mContentView.setLayoutParams(lp);
    }

    public int getBottomMargin() {
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        return lp.bottomMargin;
    }

    /**
     * normal status
     */
    public void normal() {
        mHintView.setVisibility(VISIBLE);
        mProgressBar.setVisibility(GONE);
    }

    /**
     * loading status
     */
    public void loading() {
        mHintView.setVisibility(GONE);
        mProgressBar.setVisibility(VISIBLE);
    }

    /**
     * 当禁用下拉加载更多 隐藏底部视图
     */
    public void hide() {
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        lp.height = 0;
        mContentView.setLayoutParams(lp);
    }

    /**
     * 显示底部视图
     */
    public void show() {
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        lp.height = LayoutParams.WRAP_CONTENT;
        mContentView.setLayoutParams(lp);
    }
}
