package cn.com.sgcc.dev.view.fragment.fzbhsb;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.com.sgcc.dev.BaseApplication;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.AcceRecycleAdapter2;
import cn.com.sgcc.dev.application.App;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.recylerUtil.MTLinearLayoutManager;
import cn.com.sgcc.dev.customeView.recylerUtil.MyItemClickListener;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.WDGL;
import cn.com.sgcc.dev.utils.BitmapUtils;
import cn.com.sgcc.dev.utils.FileUtils;
import cn.com.sgcc.dev.utils.PreviewUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.CommPreviewActivity;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.fragment.BaseFragment;

import static android.app.Activity.RESULT_OK;

/**
 * <p>@description:附件信息</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2017/12/25
 */

public class Details10 extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.acce_recyclerView1)
    RecyclerView acceRecyclerView1;
    @BindView(R.id.acce_recyclerView2)
    RecyclerView acceRecyclerView2;
    @BindView(R.id.acce_recyclerView3)
    RecyclerView acceRecyclerView3;
    @BindView(R.id.acce_recyclerView4)
    RecyclerView acceRecyclerView4;
    @BindView(R.id.tv_details10)
    TextView tv;
    @BindView(R.id.iv_dzd)
    ImageView ivDzd;
    @BindView(R.id.iv_ybxx)
    ImageView ivYbxx;
    //    private AcceRecycleAdapter acceRecycleAdapter1, acceRecycleAdapter3;
//    private AcceRecycleAdapter2 acceRecycleAdapter2, acceRecycleAdapter4;
    private AcceRecycleAdapter2 acceRecycleAdapter1, acceRecycleAdapter2, acceRecycleAdapter3, acceRecycleAdapter4;

    public List<WDGL> dzd_img = new ArrayList<>(); //定值单图片集合
    public List<WDGL> yb_img = new ArrayList<>(); //压板图片集合
    public List<WDGL> wdgl_dzd = new ArrayList<>(); //定值单文档集合
    public List<WDGL> wdgl_yb = new ArrayList<>(); //压板文档集合
    public List<WDGL> del_img = new ArrayList<>(); //记录所有需要删除/标记删除的图片

    private final int TAKE_PICTURE = 0x000001;
    private final int Choose_PICTURE = 0x000002;
    private int type;  //0 -定值单  1-压板   默认定值单
    public static Details10 instance;
    private IDaoUtil utils;
    private boolean isEdit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_accessory;
    }

    @Override
    public void initview() {
        instance = this;
        acceRecycleAdapter1 = new AcceRecycleAdapter2(getActivity(), 0);
        acceRecycleAdapter2 = new AcceRecycleAdapter2(getActivity(), 1);
        acceRecycleAdapter3 = new AcceRecycleAdapter2(getActivity(), 2);
        acceRecycleAdapter4 = new AcceRecycleAdapter2(getActivity(), 3);
//        acceRecycleAdapter = new AcceRecycleAdapter2(getActivity());

        MTLinearLayoutManager manager1 = new MTLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,
                false);
        MTLinearLayoutManager manager2 = new MTLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,
                false);
        MTLinearLayoutManager manager3 = new MTLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,
                false);
        MTLinearLayoutManager manager4 = new MTLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,
                false);
        acceRecyclerView1.setLayoutManager(manager1);
        acceRecyclerView1.setSaveEnabled(true);
        acceRecyclerView1.setAdapter(acceRecycleAdapter1);
        acceRecyclerView2.setLayoutManager(manager2);
        acceRecyclerView2.setSaveEnabled(true);
        acceRecyclerView2.setAdapter(acceRecycleAdapter2);
        acceRecyclerView3.setLayoutManager(manager3);
        acceRecyclerView3.setSaveEnabled(true);
        acceRecyclerView3.setAdapter(acceRecycleAdapter3);
        acceRecyclerView4.setLayoutManager(manager4);
        acceRecyclerView4.setSaveEnabled(true);
        acceRecyclerView4.setAdapter(acceRecycleAdapter4);

        acceRecycleAdapter1.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PreviewUtils.previewAsPopupWindows(getActivity()
                        , position, dzd_img, acceRecycleAdapter1, isEdit);
            }
        });
        acceRecycleAdapter2.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String exteName = FileUtils.getExtensionName(wdgl_dzd.get(position).getName());
                if (exteName.equals("txt") || exteName.equals("doc") || exteName.equals("docx") ||
                        exteName.equals("xls") || exteName.equals("xlsx") || exteName.equals("pdf")) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), CommPreviewActivity.class);
                    intent.putExtra("path", Constants.INPUT_TMPWD + "/" + wdgl_dzd.get(position).getName());
                    intent.putExtra("name", wdgl_dzd.get(position).getName());
                    startActivity(intent);
                } else {
                    ToastUtils.showToast(getActivity(), "很抱歉，暂不支持该类型文档浏览！");
                }
            }
        });
        acceRecycleAdapter3.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PreviewUtils.previewAsPopupWindows(getActivity()
                        , position, yb_img, acceRecycleAdapter3, isEdit);
            }
        });
        acceRecycleAdapter4.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String exteName = FileUtils.getExtensionName(wdgl_yb.get(position).getName());
                if (exteName.equals("txt") || exteName.equals("doc") || exteName.equals("docx") ||
                        exteName.equals("xls") || exteName.equals("xlsx") || exteName.equals("pdf")) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), CommPreviewActivity.class);
                    intent.putExtra("path", Constants.INPUT_TMPWD + "/" + wdgl_yb.get(position).getName());
                    intent.putExtra("name", wdgl_yb.get(position).getName());
                    startActivity(intent);
                } else {
                    ToastUtils.showToast(getActivity(), "很抱歉，暂不支持该类型文档浏览！");
                }
            }
        });
    }

    @Override
    public void initEvevt() {

    }

    @Override
    public void initData() {
        isEdit = getArguments().getBoolean("isEdit", false);
        initReceiver(isEdit);
        utils = new DaoUtil(getActivity());
        setFirstData();
    }

    /**
     * 初始化数据
     */
    private void setFirstData() {
        if (DemoActivity.instance.BHorFZ) {
            if (DemoActivity.instance.bhsb != null) {
                if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C")) {
                    tv.setVisibility(View.GONE);
                } else {
                    tv.setVisibility(View.VISIBLE);
                    if (DemoActivity.instance.rzgl != null) {
                        tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                    } else {
                        tv.setText("本条台账最后一次修改时间：");
                    }
                }
            } else {
                tv.setVisibility(View.GONE);
            }
        } else {
            if (DemoActivity.instance.fzbhsb != null) {
                if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C")) {
                    tv.setVisibility(View.GONE);
                } else {
                    tv.setVisibility(View.VISIBLE);
                    if (DemoActivity.instance.rzgl != null) {
                        tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
                    } else {
                        tv.setText("本条台账最后一次修改时间：");
                    }
                }
            } else {
                tv.setVisibility(View.GONE);
            }
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                String wdId = "";
                if (DemoActivity.instance.BHorFZ) {//保护
                    if (DemoActivity.instance.bhsb != null && DemoActivity.instance.bhsb.getId() != null) {
                        wdId = DemoActivity.instance.bhsb.getId() + "";
                    }
                } else {//辅助
                    if (DemoActivity.instance.fzbhsb != null && DemoActivity.instance.fzbhsb.getId() != null) {
                        wdId = DemoActivity.instance.fzbhsb.getId() + "";
                    }
                }
                if (wdId != null && !wdId.equals("")) {
                    if (utils == null) {
                        utils = new DaoUtil(getActivity());
                    }
                    List<Object> wdgls = utils.getICDOrBKXX(WDGL.class, wdId);
                    for (Object o : wdgls) {
                        WDGL wdgl = (WDGL) o;
                        if (wdgl.getType().equals("定值单")) {//定值单
                            String extName = FileUtils.getExtensionName(wdgl.getName());
                            if (extName.equalsIgnoreCase("jpg") ||
                                    extName.equalsIgnoreCase("png") ||
                                    extName.equalsIgnoreCase("jpeg") ||
                                    extName.equalsIgnoreCase("bmp")) {//图片
                                if (wdgl.getEd_tag() != null && wdgl.getEd_tag().equals("C")) {
                                    wdgl.setBitmap(BitmapUtils.getBitmapByFile(Constants.APP_IMG+ wdgl.getName()));
                                } else {
                                    wdgl.setBitmap(BitmapUtils.getBitmapByFile(Constants.INPUT_TMPWD+ wdgl.getName()));
                                }
                                dzd_img.add(wdgl);
                            } else {//文档
                                wdgl_dzd.add(wdgl);
                            }
                        } else if (wdgl.getType().equals("压板")) {  //压板
                            String extName = FileUtils.getExtensionName(wdgl.getName());
                            if (extName.equalsIgnoreCase("jpg") ||
                                    extName.equalsIgnoreCase("png") ||
                                    extName.equalsIgnoreCase("jpeg") ||
                                    extName.equalsIgnoreCase("bmp")) {//图片
                                if (wdgl.getEd_tag() != null && wdgl.getEd_tag().equals("C")) {
                                    wdgl.setBitmap(BitmapUtils.getBitmapByFile(Constants.APP_IMG+ wdgl.getName()));
                                } else {
                                    wdgl.setBitmap(BitmapUtils.getBitmapByFile(Constants.INPUT_TMPWD+ wdgl.getName()));
                                }
                                yb_img.add(wdgl);
                            } else {//文档
                                wdgl_yb.add(wdgl);
                            }
                        }
                    }
                    acceRecycleAdapter1.setDatas(dzd_img);
                    acceRecycleAdapter2.setDatas(wdgl_dzd);
                    acceRecycleAdapter3.setDatas(yb_img);
                    acceRecycleAdapter4.setDatas(wdgl_yb);
                }
            }
        }).run();
    }

    @Override
    public void initReceiver(boolean isEdit) {
        this.isEdit = isEdit;
        if (isEdit) {
            ivDzd.setVisibility(View.VISIBLE);
            ivYbxx.setVisibility(View.VISIBLE);
        } else {
            ivDzd.setVisibility(View.GONE);
            ivYbxx.setVisibility(View.GONE);
            if (DemoActivity.instance.isCancel && !DemoActivity.instance.BHorFZ) {//取消

                //初始化数据，重新加载
                dzd_img.clear();
                yb_img.clear();
                wdgl_dzd.clear();
                wdgl_yb.clear();

                setFirstData();
            }
            if (DemoActivity.instance.rzgl != null &&
                    DemoActivity.instance.rzgl.getCZSJ() != null &&
                    !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                tv.setVisibility(View.VISIBLE);
                tv.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
            }
        }
    }

    /**
     * 弹出图片、拍照
     */
    private void showPopupWindows() {
        final PopupWindow pop = new PopupWindow(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        final RelativeLayout rl_popup = (RelativeLayout) view.findViewById(R.id.rl_popup);
        rl_popup.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.activity_translate_in));

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        TextView tv_photo = (TextView) view.findViewById(R.id.tv_photo);
        TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                rl_popup.clearAnimation();
            }
        });
        tv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(openCameraIntent, TAKE_PICTURE);
                pop.dismiss();
                rl_popup.clearAnimation();
            }
        });

        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, Choose_PICTURE);
                pop.dismiss();
                rl_popup.clearAnimation();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                rl_popup.clearAnimation();
            }
        });

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });

        pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            final WDGL wdgl = new WDGL();
            String imgPath = "";
            if (requestCode == TAKE_PICTURE) {
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                imgPath = Constants.APP_IMG  + BaseApplication.getPad_id() + "_" + System.currentTimeMillis() + ".jpg";
                BitmapUtils.compressBitmap(bm, 150, imgPath);
            } else if (requestCode == Choose_PICTURE) {
                Uri selectedImage = data.getData();
                String[] filePathColumns = {MediaStore.Images.Media.DATA};
                Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePathColumns[0]);
                String imagePath = c.getString(columnIndex);
                c.close();
                imgPath = Constants.APP_IMG + BaseApplication.getPad_id() + "_" + System.currentTimeMillis() + ".jpg";
                BitmapUtils.compressBitmap(imagePath, 150, imgPath);
            }
            wdgl.setName(new File(imgPath).getName());
            wdgl.setEd_tag("C");
            wdgl.setKeyWord("");
            wdgl.setMslx("用途管理模式");

            wdgl.setBitmap(BitmapUtils.getBitmapByFile(imgPath));
            if (type == 0) {
                wdgl.setType("定值单");
                dzd_img.add(wdgl);
                acceRecycleAdapter1.setDatas(dzd_img);
            } else if (type == 1) {
                wdgl.setType("压板");
                yb_img.add(wdgl);
                acceRecycleAdapter3.setDatas(yb_img);
            }
//
//            Picasso.with(getActivity()).load(new File(imgPath)).into(new Target() {
//                @Override
//                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                    wdgl.setBitmap(bitmap);
//                    if (type == 0) {
//                        wdgl.setType("定值单");
//                        dzd_img.add(wdgl);
//                        acceRecycleAdapter1.setDatas(dzd_img);
//                    } else if (type == 1) {
//                        wdgl.setType("压板");
//                        yb_img.add(wdgl);
//                        acceRecycleAdapter3.setDatas(yb_img);
//                    }
//                }
//
//                @Override
//                public void onBitmapFailed(Drawable errorDrawable) {
//
//                }
//
//                @Override
//                public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                }
//            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_dzd, R.id.iv_ybxx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_dzd:
                type = 0;
                break;
            case R.id.iv_ybxx:
                type = 1;
                break;
        }
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        showPopupWindows();
    }
}
