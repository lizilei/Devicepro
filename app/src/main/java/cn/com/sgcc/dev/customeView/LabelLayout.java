package cn.com.sgcc.dev.customeView;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model2.CommonFilter;
import cn.com.sgcc.dev.utils.ConversionUtil;
import cn.com.sgcc.dev.utils.ScreenUtils;

/**
 * <p>
 * Title: LabelLayout
 * </p>
 * <p>
 * Description: 热门标签布局
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author lzl
 * @date 2016/7/7
 */
public class LabelLayout {

    List<LinearLayout> layoutList = new ArrayList<>();
    private Context context;
    // 记录一行的宽度
    private int lineWidth1 = 0;
    private int lineWidth2 = 0;
    // 屏幕宽度
    private int screenWidth;
    // TextView的左右外边距。单位：px
    private int marginLeft = 16;
    private int marginRight = 16;
    // TextView的左右内边距。单位：dp
    private int paddingLeft;
    private int paddingRight;
    // 根布局
    private View rootView;
    // 根布局上部
    private LinearLayout rootLayoutTop;
    // 根布局下部
    private LinearLayout rootLayoutBottom;
    // 子布局
    private LinearLayout childLayout1;
    private LinearLayout childLayout2;
    //外层LinearLayout
    private LinearLayout ll_out;

    private Handler handler;
    private Message msg;

    private boolean isTwo = false;//是否有两级
    private List<TextView> texts = new ArrayList<>();
    private List<TextView> texts2 = new ArrayList<>();

    private PopupWindow mPopupWindow;
    private Boolean isSearch = false;
    private int type;

    public LabelLayout(Context context, final Handler handler, boolean isTwo) {
        this.isTwo = isTwo;
        this.context = context;
        paddingLeft = ConversionUtil.dip2px(context, 10);
        paddingRight = ConversionUtil.dip2px(context, 10);
        screenWidth = ScreenUtils.getScreenWidth(context);

        rootView = LayoutInflater.from(context).inflate(
                R.layout.filter_pop_root, null);
        ll_out = (LinearLayout) rootView.findViewById(R.id.ll_out);
        rootLayoutTop = (LinearLayout) rootView
                .findViewById(R.id.chile_layout_one);

        rootLayoutBottom = (LinearLayout) rootView
                .findViewById(R.id.chile_layout_two);
        this.handler = handler;
        //PopupWindow
        mPopupWindow = new PopupWindow(context);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setContentView(rootView);

        ll_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(0);
                mPopupWindow.dismiss();
            }
        });
    }

    public LabelLayout(Context context, final Handler handler) {
        this.context = context;
        paddingLeft = ConversionUtil.dip2px(context, 10);
        paddingRight = ConversionUtil.dip2px(context, 10);
        screenWidth = ScreenUtils.getScreenWidth(context);

        rootView = LayoutInflater.from(context).inflate(
                R.layout.filter_pop_root, null);
        ll_out = (LinearLayout) rootView.findViewById(R.id.ll_out);
        rootLayoutTop = (LinearLayout) rootView
                .findViewById(R.id.chile_layout_one);

        rootLayoutBottom = (LinearLayout) rootView
                .findViewById(R.id.chile_layout_two);
        this.handler = handler;
        //PopupWindow
        mPopupWindow = new PopupWindow(context);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setContentView(rootView);

        ll_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(0);
                mPopupWindow.dismiss();
            }
        });
    }

    public LabelLayout(Context context, final Handler handler, RelativeLayout layout, int type) {
        this.type = type;
        this.context = context;
        paddingLeft = ConversionUtil.dip2px(context, 10);
        paddingRight = ConversionUtil.dip2px(context, 10);
        screenWidth = ScreenUtils.getScreenWidth(context);

        rootView = LayoutInflater.from(context).inflate(
                R.layout.filter_pop_root, null);
        ll_out = (LinearLayout) rootView.findViewById(R.id.ll_out);
        rootLayoutTop = (LinearLayout) rootView
                .findViewById(R.id.chile_layout_one);

        rootLayoutBottom = (LinearLayout) rootView
                .findViewById(R.id.chile_layout_two);
        this.handler = handler;
        layout.addView(rootView);
        isSearch = true;
    }

    /**
     * 获取一级布局
     *
     * @param list
     * @return
     */

    public void setData(List<CommonFilter> list, View view) {
        texts.clear();
        lineWidth1 = 0;
        rootLayoutTop.removeAllViews();
        childLayout1 = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.filter_pop_child_top, null);
        rootLayoutTop.addView(childLayout1);
        for (int i = 0, j = list.size(); i < j; i++) {
            getView(list, i);
            rootLayoutTop.setVisibility(View.VISIBLE);
        }
        mPopupWindow.showAsDropDown(view);
    }

    public void setData(List<CommonFilter> list) {
        texts.clear();
        lineWidth1 = 0;
        rootLayoutTop.removeAllViews();
        childLayout1 = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.filter_pop_child_top, null);
        rootLayoutTop.addView(childLayout1);
        for (int i = 0, j = list.size(); i < j; i++) {
            getView(list, i);
            rootLayoutTop.setVisibility(View.VISIBLE);
        }
    }

    private void getView(List<CommonFilter> list, int position) {
        // 测量返回的view的宽度，并累加到当前行的宽度上
        TextView textView = getTextView(list, position);
        // 测量文本的长度
        TextPaint paint = textView.getPaint();
        texts.add(textView);

        float textWidth = paint.measureText(list.get(position).getTitle());
        lineWidth1 += textWidth + marginLeft + marginRight + paddingLeft
                + paddingRight;

        if (lineWidth1 > screenWidth) {
            // 要换行，重新创建一个LinearLayout.
            lineWidth1 = 0;
            lineWidth1 += textWidth + marginLeft + marginRight + paddingLeft
                    + paddingRight;
            childLayout1 = (LinearLayout) LayoutInflater.from(context).inflate(
                    R.layout.filter_pop_child_top, null);

            childLayout1.addView(textView);
            rootLayoutTop.addView(childLayout1);
        } else {
            // 设置到当前行
            childLayout1.addView(textView);
        }
    }

    private TextView getTextView(final List<CommonFilter> list, final int position) {
        final TextView textView = (TextView) LayoutInflater.from(context).inflate(
                R.layout.filter_pop_text_top, null);
        textView.setText(list.get(position).getTitle());
        LayoutParams para = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        para.setMargins(marginLeft, 0, marginRight, 0); // left,top,right,
        // bottom
        textView.setLayoutParams(para);
        textView.setTag(position);
        if (!isSearch) {
            textView.setSelected(list.get(position).isSelect());
        } else {
            //textView.setBackgroundResource(R.drawable.head_search_bg);
            textView.setTextColor(context.getResources().getColor(R.color.commonly_text_color6));
            textView.setTextSize(13);
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg = new Message();
                if (type != 0) {
                    if (type == 1) {
                        msg.what = 1;
                    } else if (type == 2) {
                        msg.what = 2;
                    }
                } else {
                    msg.what = 1;
                }
                msg.arg1 = (Integer) textView.getTag();
                handler.sendMessage(msg);
                for (int i = 0; i < list.size(); i++) {
                    if (i == position) {
                        list.get(i).setSelect(true);
                        texts.get(i).setSelected(true);
                    } else {
                        list.get(i).setSelect(false);
                        texts.get(i).setSelected(false);
                    }
                }
                if (isTwo && !texts2.isEmpty()) {
                    for (TextView view : texts2) {
                        view.setSelected(false);
                    }
                }
                if (!isSearch) {
                    if (!isTwo) {
                        mPopupWindow.dismiss();
                    }
                }
            }
        });
        return textView;
    }

    public void setData2(List<CommonFilter> list) {
        texts2.clear();
        lineWidth2 = 0;
        rootLayoutBottom.removeAllViews();
        if (list == null) {
            return;
        }
        childLayout2 = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.filter_pop_child_bottom, null);

        rootLayoutBottom.addView(childLayout2);
        for (int i = 0, j = list.size(); i < j; i++) {
            if (i == j - 1) {
                // 最后一个
                getView2(list, true, i);
            } else {
                getView2(list, false, i);
            }
            rootLayoutBottom.setVisibility(View.VISIBLE);
        }
    }

    private void getView2(List<CommonFilter> list, boolean isLast, int position) {
        // 测量返回的view的宽度，并累加到当前行的宽度上
        LinearLayout layout = getTextView2(list, position);
        if (isLast) {
            View line = layout.findViewById(R.id.text_two_line);
            line.setVisibility(View.GONE);
        }
        // 测量布局的长度
        TextView textView = (TextView) layout.findViewById(R.id.text_two);
        TextPaint paint = textView.getPaint();
        texts2.add(textView);
        float textWidth = paint.measureText(list.get(position).getTitle());

        lineWidth2 += textWidth + marginLeft + marginRight + paddingLeft
                + paddingRight;

        if (lineWidth2 > screenWidth) {
            // 要换行，重新创建一个LinearLayout.
            // 隐藏上一行最后一个的竖线
            LinearLayout l = layoutList.get(layoutList.size() - 1);
            View line = l.findViewById(R.id.text_two_line);
            line.setVisibility(View.GONE);

            lineWidth2 = 0;
            lineWidth2 += textWidth + marginLeft + marginRight + paddingLeft
                    + paddingRight;
            childLayout2 = (LinearLayout) LayoutInflater.from(context).inflate(
                    R.layout.filter_pop_child_bottom, null);
            childLayout2.addView(layout);
            layoutList.add(childLayout2);
            rootLayoutBottom.addView(childLayout2);
        } else {
            // 设置到当前行
            layoutList.add(layout);
            childLayout2.addView(layout);
        }
    }

    public void dismiss() {
        mPopupWindow.dismiss();
    }

    private LinearLayout getTextView2(final List<CommonFilter> list, final int position) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context)
                .inflate(R.layout.filter_pop_text_bottom, null);
        final TextView textView = (TextView) layout.findViewById(R.id.text_two);
        textView.setText(list.get(position).getTitle());
        LayoutParams para = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        para.setMargins(marginLeft, 0, marginRight, 0); // left,top,right,
        // bottom
        textView.setLayoutParams(para);
        textView.setTag(position);
        textView.setSelected(list.get(position).isSelect());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg = new Message();
                msg.what = 2;
                msg.arg2 = (Integer) textView.getTag();
                handler.sendMessage(msg);
                for (int i = 0; i < list.size(); i++) {
                    if (i == position & !list.get(i).getTitle().equals("重置")) {
                        list.get(i).setSelect(true);
                        texts2.get(i).setSelected(true);
                    } else {
                        list.get(i).setSelect(false);
                        texts2.get(i).setSelected(false);
                    }
                }
                if (!isSearch) {
                    mPopupWindow.dismiss();
                }
            }
        });
        return layout;
    }
}