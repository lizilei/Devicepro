package cn.com.sgcc.dev.view.viewinterface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import cn.com.sgcc.dev.R;


public class SideBar extends View{
	// 触摸事件
		private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
		// 26个字母
		public static String[] b = { "#" ,"A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z"};
		private int choose = -1;// 选中
		private Paint paint = new Paint();

		private TextView mTextDialog;
	    private Context mContext;

    //为SideBar设置显示字母的TextView
	public void setTextView(TextView mTextDialog) {
			this.mTextDialog = mTextDialog;
		}


		public SideBar(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			mContext = context;
		}

		public SideBar(Context context, AttributeSet attrs) {
			super(context, attrs);
			mContext = context;
		}

		public SideBar(Context context) {
			super(context);
			mContext = context;
		}

		/**
		 * 重写这个方法
		 */
		@Override
        protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			// 获取焦点改变背景颜色.
			int height = getHeight();// 获取对应高度
			int width = getWidth(); // 获取对应宽度
			int singleHeight = height / b.length;// 获取每一个字母的高度

			for (int i = 0; i < b.length; i++) {
				//paint.setColor(Color.rgb(33, 65, 98)); Color.parseColor("#999999")
				paint.setColor(Color.BLACK);
				paint.setColor(Color.parseColor("#333333"));
				paint.setTypeface(Typeface.DEFAULT_BOLD);
				paint.setAntiAlias(true);
				paint.setTextSize(dip2px(mContext, 16));

				// 选中的状态
				if (i == choose) {
					//paint.setColor(Color.parseColor("#8C8C8C"));
					paint.setColor(Color.parseColor("#2A7168"));
					paint.setFakeBoldText(true);
				}
				// x坐标等于中间-字符串宽度的一半.
				float xPos = width / 2 - paint.measureText(b[i]) / 2;
				float yPos = singleHeight * i + singleHeight;
				canvas.drawText(b[i], xPos, yPos, paint);
				paint.reset();// 重置画笔
			}

		}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

		@Override
		public boolean dispatchTouchEvent(MotionEvent event) {
			final int action = event.getAction();
			final float y = event.getY();// 点击y坐标
			final int oldChoose = choose;
			final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
			final int c = (int) (y / getHeight() * b.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.

			switch (action) {
			case MotionEvent.ACTION_UP:
				//setBackgroundDrawable(new ColorDrawable(0x00000000));
				setBackgroundColor(Color.TRANSPARENT);
				choose = -1;//
				invalidate();
				if (mTextDialog != null) {
					mTextDialog.setVisibility(View.INVISIBLE);
				}
				break;

			default:
				setBackgroundResource(R.drawable.sidebar_background_pressed);
				if (oldChoose != c) {
					if (c >= 0 && c < b.length) {
						if (listener != null && b[c] != null) {
							listener.onTouchingLetterChanged(b[c]);
						}
						if (mTextDialog != null) {
							mTextDialog.setText(b[c]);
							mTextDialog.setVisibility(View.VISIBLE);
						}
						
						choose = c;
						invalidate();
					}
				}

				break;
			}
			return true;
		}

		/**
		 * 向外公开的方法
		 * 
		 * @param onTouchingLetterChangedListener
		 */
		public void setOnTouchingLetterChangedListener(
				OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
			this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
		}

		/**
		 * 接口
		 * 
		 * @author coder
		 * 
		 */
		public interface OnTouchingLetterChangedListener {
			public void onTouchingLetterChanged(String s);
		}
}
