package cn.com.sgcc.dev.view.activity;//package cn.com.sgcc.dev.view.activity;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.drawable.BitmapDrawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.AnimationUtils;
//import android.widget.ExpandableListView;
//import android.widget.ImageView;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import java.io.File;
//import java.io.FileFilter;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import cn.com.sgcc.dev.R;
//import cn.com.sgcc.dev.adapter.AcceRecycleAdapter;
//import cn.com.sgcc.dev.adapter.MyExpListViewAdapter;
//import cn.com.sgcc.dev.constants.Constants;
//import cn.com.sgcc.dev.customeView.recylerUtil.MTLinearLayoutManager;
//import cn.com.sgcc.dev.customeView.recylerUtil.MyItemClickListener;
//import cn.com.sgcc.dev.model2.Accessory;
//import cn.com.sgcc.dev.utils.FileUtils;
//import cn.com.sgcc.dev.utils.PreviewUtils;
//import cn.com.sgcc.dev.utils.TimeUtil;
//
///**
// * <p>@description:</p>
// *
// * @author lizilei
// * @version 1.0
// * @Email lizilei_warms@163.com
// * @since 2017/12/21
// */
//
//public class AccessoryActivity extends BaseActivity {
//
//    private RecyclerView recyclerView;
//    private AcceRecycleAdapter acceRecycleAdapter;
//
//    private ExpandableListView exp_listView;
//    private MyExpListViewAdapter adapter;
//    private List<String> groupData = new ArrayList<>();
//    private List<List<Accessory>> childData = new ArrayList<>();
//    private List<Accessory> txtList = new ArrayList<>();
//    private List<Accessory> wordList = new ArrayList<>();
//    private List<Accessory> pdfList = new ArrayList<>();
//
//    private TextView app_toolbar_center;
//    private ImageView app_toolbar_left;
//
//    private List<Accessory> imgList = new ArrayList<>();
//    private final int TAKE_PICTURE = 0x000001;
//    private final int Choose_PICTURE = 0x000002;
//    private String currentPath;//当前拍照的存储路径
//    private final String fileName = new SimpleDateFormat("yyyyMMddHHMMss").format(new Date());
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_accessory);
//        initView();
//        initData();
//    }
//
//    private void initView() {
//        recyclerView = (RecyclerView) findViewById(R.id.acce_recyclerView);
//        acceRecycleAdapter = new AcceRecycleAdapter(this);
//
//        MTLinearLayoutManager manager = new MTLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
//                false);
//        recyclerView.setLayoutManager(manager);
////        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
//        recyclerView.setSaveEnabled(true);
//        recyclerView.setAdapter(acceRecycleAdapter);
//
//        exp_listView = (ExpandableListView) findViewById(R.id.exp_listView);
//
//        adapter = new MyExpListViewAdapter(this);
//        exp_listView.setAdapter(adapter);
//
//        app_toolbar_center = (TextView) findViewById(R.id.app_toolbar_center);
//        app_toolbar_left = (ImageView) findViewById(R.id.app_toolbar_left);
//        app_toolbar_center.setText("附件");
//        app_toolbar_left.setVisibility(View.VISIBLE);
//        app_toolbar_left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(AccessoryActivity.this, DemoActivity.class));
//            }
//        });
//
//
//        acceRecycleAdapter.setOnItemClickListener(new MyItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                if (position == imgList.size()) {
//                    showPopupWindows();
//                } else {
//                    PreviewUtils.previewAsPopupWindows(AccessoryActivity.this
//                            , position, imgList, acceRecycleAdapter);
//                }
//            }
//        });
//
//        exp_listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Intent intent = new Intent();
//                intent.setClass(AccessoryActivity.this, CommPreviewActivity.class);
//                intent.putExtra("path", childData.get(groupPosition).get(childPosition).getPath());
//                intent.putExtra("name", childData.get(groupPosition).get(childPosition).getName());
//                startActivity(intent);
//                return false;
//            }
//        });
//    }
//
//    private void initData() {
//        for (int i = 0; i < Constants.fjfl.length; i++) {
//            groupData.add(Constants.fjfl[i]);
//        }
//
//        getVideoFile(Environment.getExternalStorageDirectory());
//        childData.add(txtList);
//        childData.add(wordList);
//        childData.add(pdfList);
//        adapter.setDatas(groupData, childData);
//    }
//
//    /**
//     * 获取本地所有文件的方法
//     *
//     * @param file
//     */
//    private void getVideoFile(final File file) {
//
//        file.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File file) {
//                String name = file.getName();
//                int i = name.indexOf(".");
//                if (i != -1) {
//                    name = name.substring(i);
//                    if (name.equalsIgnoreCase(".txt")) {
//                        Accessory accessory = new Accessory();
//                        accessory.setPath(file.getAbsolutePath());
//                        accessory.setName(file.getName());
//                        accessory.setSize(FileUtils.formatFileSize(FileUtils.getFileSize(file)));
//                        accessory.setTime(TimeUtil.dateToStr(new Date(file.lastModified())));
//                        txtList.add(accessory);
//                    } else if (name.equalsIgnoreCase(".doc")
//                            || name.equalsIgnoreCase(".docx")
//                            || name.equalsIgnoreCase(".xls")
//                            || name.equalsIgnoreCase(".xlsx")
//                            || name.equalsIgnoreCase(".ppt")
//                            || name.equalsIgnoreCase(".pptx")) {
//                        Accessory accessory = new Accessory();
//                        accessory.setPath(file.getAbsolutePath());
//                        accessory.setName(file.getName());
//                        accessory.setSize(FileUtils.formatFileSize(FileUtils.getFileSize(file)));
//                        accessory.setTime(TimeUtil.dateToStr(new Date(file.lastModified())));
//                        wordList.add(accessory);
//                    } else if (name.equalsIgnoreCase(".pdf")) {
//                        Accessory accessory = new Accessory();
//                        accessory.setPath(file.getAbsolutePath());
//                        accessory.setName(file.getName());
//                        accessory.setSize(FileUtils.formatFileSize(FileUtils.getFileSize(file)));
//                        accessory.setTime(TimeUtil.dateToStr(new Date(file.lastModified())));
//                        pdfList.add(accessory);
//                    }
//                } else if (file.isDirectory()) {
//                    getVideoFile(file);
//                }
//                return false;
//            }
//        });
//    }
//
//    /**
//     * 弹出图片、拍照
//     */
//    private void showPopupWindows() {
//        final PopupWindow pop = new PopupWindow(this);
//        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);
//
//        final RelativeLayout rl_popup = (RelativeLayout) view.findViewById(R.id.rl_popup);
//        rl_popup.startAnimation(AnimationUtils.loadAnimation(this, R.anim.activity_translate_in));
//
//        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        pop.setBackgroundDrawable(new BitmapDrawable());
//        pop.setFocusable(true);
//        pop.setOutsideTouchable(true);
//        pop.setContentView(view);
//
//        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
//        TextView tv_photo = (TextView) view.findViewById(R.id.tv_photo);
//        TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
//        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
//
//        parent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pop.dismiss();
//                rl_popup.clearAnimation();
//            }
//        });
//        tv_photo.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                File file = null;
//                try {
//                    file = File.createTempFile(fileName, ".jpg", new File(Constants.APP_IMG));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                currentPath = file.getAbsolutePath();
//                Uri imageUri = Uri.fromFile(file);
//                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                startActivityForResult(openCameraIntent, TAKE_PICTURE);
//                pop.dismiss();
//                rl_popup.clearAnimation();
//            }
//        });
//
//        tv_phone.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, Choose_PICTURE);
//                pop.dismiss();
//                rl_popup.clearAnimation();
//            }
//        });
//        tv_cancel.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                pop.dismiss();
//                rl_popup.clearAnimation();
//            }
//        });
//
//        pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            Accessory accessory = new Accessory();
//            if (requestCode == TAKE_PICTURE) {
//                accessory.setPath(currentPath);
//            } else if (requestCode == Choose_PICTURE) {
//                Uri selectedImage = data.getData();
//                String[] filePathColumns = {MediaStore.Images.Media.DATA};
//                Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
//                c.moveToFirst();
//                int columnIndex = c.getColumnIndex(filePathColumns[0]);
//                String imagePath = c.getString(columnIndex);
//                c.close();
//                accessory.setPath(imagePath);
//            }
//            imgList.add(accessory);
//            acceRecycleAdapter.setDatas(imgList,0);
//        }
//    }
//}
