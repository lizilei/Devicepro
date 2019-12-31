package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by Administrator on 2016/10/27.
 */
public class ViewHolder {

    private Context mContext;
    private int mPosition;
    private View convertView;
    private final SparseArray<View> mViews;

    public ViewHolder(Context mContext, ViewGroup parent, int resId, int mPosition) {
        this.mContext = mContext;
        this.mViews = new SparseArray<>();

        convertView = LayoutInflater.from(mContext).inflate(resId, parent, false);
        convertView.setTag(this);

        AutoUtils.autoSize(convertView);
        this.mPosition = mPosition;
    }


    public static ViewHolder get(Context mContext, View convertView, ViewGroup parent, int resId, int position) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder(mContext, parent, resId, position);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return holder;
    }

    public int add(int... a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            //计算这几个数字的和
            sum += a[i];
        }
        return sum;
    }


    /**
     * 通过控件id获取对应的控件，如果不存在则加入到mViews里
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 返回convertView
     *
     * @return
     */
    public View getConvertView() {
        return this.convertView;
    }

    /**
     * 返回位置position
     *
     * @return
     */
    public int getPosition() {
//        Log.i("打印当前position的值：", mPosition + "");
        return mPosition;
    }

    /**
     * 为TextView设置数据
     *
     * @param viewId
     * @param str
     * @return
     */
    public ViewHolder setText(int viewId, String str) {
        TextView tv = getView(viewId);
        tv.setText(str);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param resourceId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int resourceId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resourceId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bm);
        return this;
    }

    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView iv = getView(viewId);
        iv.setImageDrawable(drawable);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setImageByUrl(int viewId, String url) {
//        Picasso.with(mContext).load(url).into((ImageView) getView(viewId));
        return this;
    }

    /**
     * 为ImageView设置图片，并设置加载失败后的图片
     *
     * @param viewId
     * @param url
     * @param error
     * @return
     */
    public ViewHolder setImageByUrlWithError(int viewId, String url, int error) {
//        Picasso.with(mContext).load(url).error(error).into((ImageView) getView(viewId));
        return this;
    }

    /**
     * 为ImageView设置图片，并设置加载中和加载失败后的图片
     *
     * @param viewId
     * @param url
     * @param place
     * @param error
     * @return
     */
    public ViewHolder setImageByUrlWithPlaceholder(int viewId, String url, int place, int error) {
//        Picasso.with(mContext).load(url).placeholder(place).error(error).into((ImageView) getView(viewId));
        return this;
    }
}