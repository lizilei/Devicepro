package cn.com.sgcc.dev.customeView.xListView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import cn.com.sgcc.dev.R;

/**
 * Created by Administrator on 2016/11/4.
 */
public class XListViewHeader extends LinearLayout {

    private LinearLayout mContainer;
    private ImageView mArrowImageView;
    private ProgressBar mProgressBar;
    private TextView mHintTextView;
    private int mState = STATE_NORMAL;//初始状态

    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;

    private final int ROTATE_ANIM_DURATION = 180;

    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;

    public XListViewHeader(Context context) {
        super(context);
        initView(context);
    }

    public XListViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        //初始情况，设置下拉刷新View高度为0
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, 0);
        //时间TextView
        mContainer = (LinearLayout) LayoutInflater.from(context)
                .inflate(R.layout.xlistview_header, null);
        addView(mContainer, lp);
        setGravity(Gravity.BOTTOM);
        //找到头部页面里的控件
        mArrowImageView = (ImageView) findViewById(R.id.xlistview_header_arrow);
        mHintTextView = (TextView) findViewById(R.id.xlistview_header_hint_textview);
        mProgressBar = (ProgressBar) findViewById(R.id.xlistview_header_progressbar);

        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f, Animation.RELATIVE_TO_SELF
                , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f, Animation.RELATIVE_TO_SELF
                , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);
    }

    /**
     * 设置状态
     *
     * @param state
     */
    public void setState(int state) {
        if (state == mState) {
            return;
        }
        if (state == STATE_REFRESHING) {//显示进度
            mArrowImageView.clearAnimation();
            mArrowImageView.setVisibility(INVISIBLE);//隐藏图片
            mProgressBar.setVisibility(VISIBLE);    //显示进度条
        } else {//显示箭头图片
            mArrowImageView.setVisibility(VISIBLE);
            mProgressBar.setVisibility(INVISIBLE);
        }

        switch (state) {
            case STATE_NORMAL:
                if (mState == STATE_READY)//当状态是准备的时候，显示动画
                {
                    mArrowImageView.startAnimation(mRotateDownAnim);
                }
                if (mState == STATE_REFRESHING)//当状态显示进度条的时候，清除动画
                {
                    mArrowImageView.clearAnimation();
                }
                mHintTextView.setText("下拉刷新");
                break;
            case STATE_READY:
                if (mState != STATE_READY) {
                    mArrowImageView.clearAnimation();
                    mArrowImageView.startAnimation(mRotateUpAnim);
                    mHintTextView.setText("松开刷新数据");
                }
                break;
            case STATE_REFRESHING:
                mHintTextView.setText("正在加载...");
                break;
        }
        mState = state;
    }

    public void setVisiableHeight(int height) {
        if (height < 0) {
            height = 0;
        }
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        lp.height=height;
        mContainer.setLayoutParams(lp);
    }

    public int getVisiableHeight(){
        return mContainer.getHeight();
    }
}
