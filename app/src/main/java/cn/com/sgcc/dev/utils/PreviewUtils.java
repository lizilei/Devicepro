package cn.com.sgcc.dev.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.AcceRecycleAdapter2;
import cn.com.sgcc.dev.customeView.TouchImageView;
import cn.com.sgcc.dev.model2.WDGL;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details10;

/**
 * Created by lzl on 2017/1/4.
 * 邮箱：976561093@qq.com
 * 图片预览工具类
 */
public class PreviewUtils {
    public static MyVpAdapter adapter;
    public static int currentPosition;

    /**
     * 显示PopupWindow展示图片
     */
    public static void previewAsPopupWindows(final Context context, final int position, final List<WDGL> imgList,
                                             final AcceRecycleAdapter2 acceRecycleAdapter, boolean isEdit) {
        currentPosition = position;
        final PopupWindow pop = new PopupWindow(context);
        View v = LayoutInflater.from(context).inflate(R.layout.edit_note_popuwindow, null);
        final ViewPager vp = (ViewPager) v.findViewById(R.id.vp_gallery);
        RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.rl_gallery);
        final TextView tv = (TextView) v.findViewById(R.id.tv_gallery);
        final ImageView img_del = (ImageView) v.findViewById(R.id.img_del);
        if (isEdit) {
            img_del.setVisibility(View.VISIBLE);
        } else {
            img_del.setVisibility(View.GONE);
        }
        tv.setText(position + 1 + "/" + imgList.size());

        //获取图片的方法
        final List<ImageView> dataImg = new ArrayList<>();
        for (int i = 0; i < imgList.size(); i++) {
            TouchImageView iv = new TouchImageView(context);
            iv.setMinZoom(0.5f);
            iv.setImageBitmap(imgList.get(i).getBitmap());
            dataImg.add(iv);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                }
            });
        }

        img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(context, imgList, acceRecycleAdapter, vp, dataImg, tv, pop);
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("提示").setMessage("确定要删除图片吗？").setPositiveButton(
//                        "确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                if (imgList.get(currentPosition).getEd_tag().equals("C") &&
//                                        imgList.get(currentPosition).getId() == null) {//直接删
//                                    imgList.remove(currentPosition);
//                                } else if (imgList.get(currentPosition).getEd_tag().equals("C") &&
//                                        imgList.get(currentPosition).getId() != null) {//标记在库中操作为删除
//                                    WDGL wdgl = imgList.get(currentPosition);
//                                    wdgl.setEd_tag("DEL");
//                                    Details10.instance.del_img.add(wdgl);
//                                    imgList.remove(currentPosition);
//
//                                } else { //标记在库中操作为标记删除
//                                    WDGL wdgl = imgList.get(currentPosition);
//                                    wdgl.setEd_tag("D");
//                                    Details10.instance.del_img.add(wdgl);
//                                    imgList.remove(currentPosition);
//                                }
//                                acceRecycleAdapter.setDatas(imgList);
//                                dataImg.remove(currentPosition);
//                                adapter = new MyVpAdapter(dataImg);
//                                vp.setAdapter(adapter);
//
//                                if (currentPosition == 0) {
//                                    tv.setText("1/" + imgList.size());
//                                } else {
//                                    tv.setText(currentPosition + "/" + imgList.size());
//                                    vp.setCurrentItem(currentPosition);
//                                }
//
//                                if (imgList.size() == 0)
//                                    pop.dismiss();
//                                dialog.cancel();
//                            }
//                        }
//                ).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                }).create().show();
            }
        });

        adapter = new MyVpAdapter(dataImg);
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv.setText(position + 1 + "/" + imgList.size());
                currentPosition = position;
                vp.setCurrentItem(currentPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setCurrentItem(currentPosition);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(v);
        pop.showAtLocation(v, Gravity.CENTER, 0, 0);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
    }

    /**
     * 图片删除弹窗
     *
     * @param context
     * @param imgList
     * @param acceRecycleAdapter
     * @param vp
     * @param dataImg
     * @param tv
     * @param pwindow
     */
    private static void showDialog(Context context, final List<WDGL> imgList, final AcceRecycleAdapter2 acceRecycleAdapter, final ViewPager vp, final List<ImageView> dataImg, final TextView tv, final PopupWindow pwindow) {
        final PopupWindow pop = new PopupWindow(context);
        View view = LayoutInflater.from(context).inflate(R.layout.common_dialog_view, null);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_ensure = (TextView) view.findViewById(R.id.tv_ensure);
        tv_message.setText("是否确定删除该图片");

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //取消
                pop.dismiss();
            }
        });
        tv_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgList.get(currentPosition).getEd_tag() != null &&
                        imgList.get(currentPosition).getEd_tag().equals("C") &&
                        imgList.get(currentPosition).getId() == null) {//直接删
                    imgList.remove(currentPosition);
                } else if (imgList.get(currentPosition).getEd_tag() != null &&
                        imgList.get(currentPosition).getEd_tag().equals("C") &&
                        imgList.get(currentPosition).getId() != null) {//标记在库中操作为删除
                    WDGL wdgl = imgList.get(currentPosition);
                    wdgl.setEd_tag("DEL");
                    Details10.instance.del_img.add(wdgl);
                    imgList.remove(currentPosition);
                } else { //标记在库中操作为标记删除
                    WDGL wdgl = imgList.get(currentPosition);
                    wdgl.setEd_tag("D");
                    Details10.instance.del_img.add(wdgl);
                    imgList.remove(currentPosition);
                }
                acceRecycleAdapter.setDatas(imgList);
                dataImg.remove(currentPosition);
                if (currentPosition == 1 && dataImg.size() == 1) {
                    currentPosition = 0;
                }

                adapter = new MyVpAdapter(dataImg);
                vp.setAdapter(adapter);

                if (currentPosition == 0) {
                    tv.setText("1/" + imgList.size());
                } else {
                    tv.setText(currentPosition + "/" + imgList.size());
                    vp.setCurrentItem(currentPosition);
                }

                pop.dismiss();
                if (imgList.size() == 0) {
                    pwindow.dismiss();
                }
            }
        });

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(false);
        pop.setContentView(view);
        pop.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public static class MyVpAdapter extends PagerAdapter {
//        View[] views;

        List<ImageView> views;
        public boolean isLoop = false; //是否开启循环

        public MyVpAdapter(List<ImageView> views) {
            super();
            this.views = views;
        }

        public void setDatas(List<ImageView> views) {
            this.views = views;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (isLoop) {
                return Integer.MAX_VALUE;
            }
            return views.size();
        }

        /**
         * 判断出去的view是否等于进来的view 如果为true直接复用
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (object);
        }

        @Override
        public Object instantiateItem(View collection, int position) {
            if (isLoop) {
                try {
                    ((ViewPager) collection).addView(views.get(position), 0);
                } catch (Exception e) {
                    //handler something
                }
                return views.get(position);
            } else {
                View listV;
                listV = views.get(position);

                ((ViewPager) collection).addView(listV);
                return listV;
            }
        }

        /**
         * 销毁预加载以外的view对象, 会把需要销毁的对象的索引位置传进来就是position
         */
        @Override
        public void destroyItem(View collection, int position, Object view) {
            if (isLoop) {
                ((ViewPager) collection).removeView(views.get(position));
            } else {
                ((ViewPager) collection).removeView((View) view);
            }

        }
    }
}