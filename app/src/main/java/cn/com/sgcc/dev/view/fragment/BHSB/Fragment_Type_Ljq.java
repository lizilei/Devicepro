package cn.com.sgcc.dev.view.fragment.BHSB;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.zxing.activity.CaptureActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.LjqTypeAdapter;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.customeView.MyDatePickerDialog;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.LJQXX;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;
import cn.com.sgcc.dev.view.activity.SelectActivity;
import cn.com.sgcc.dev.view.fragment.BaseFragment;

import static android.app.Activity.RESULT_OK;

/**
 * <p>@description:</p>
 * 连接器界面
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2018/1/2
 */
public class Fragment_Type_Ljq extends BaseFragment implements LjqTypeAdapter.Callback {
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    Unbinder unbinder;
    @BindView(R.id.et_one_device_right)
    EditText etOneDeviceRight;
    @BindView(R.id.et_two_device_right)
    EditText etTwoDeviceRight;
    @BindView(R.id.et_three_device_right)
    EditText etThreeDeviceRight;
    @BindView(R.id.tv_one_device_left)
    TextView tv_one_device_left;
    @BindView(R.id.tv_two_device_left)
    TextView tv_two_device_left;
    @BindView(R.id.tv_three_device_left)
    TextView tv_three_device_left;
    private SwipeMenuListView fragment_lv_ljq;
    private CustomDialog dialog;
    private LjqTypeAdapter adapter;
    private TextView fragment_lv_ljq_add;
    public RelativeLayout fragment_ljq_details;
    IDaoUtil util;

    /**
     * 必填项校验需要
     */
    @BindViews(value = {R.id.tv_one_device_left, R.id.tv_two_device_left, R.id.tv_three_device_left})
    List<TextView> textViews_key;
    @BindViews(value = {R.id.et_one_device_right, R.id.et_two_device_right, R.id.et_three_device_right})
    List<TextView> textViews_more;

    private MyDatePickerDialog dialogs;
    private int mYear, mMonth, mDay;
    private Calendar c;
    public static Fragment_Type_Ljq instance = null;

    public boolean is_Edit;
    public boolean check = false;
    private int location;

    public Map<String, TextView> map_key = new HashMap<>();
    public Map<String, TextView> map_more = new HashMap<>();
    private boolean isShow;
    public boolean isjkyt;
    public boolean isqfrq;
    public boolean isdzp;


    public Fragment_Type_Ljq() {
    }

    //连接器信息添加对话框
    public static List<String> list_erweima; //二维码信息集合
    public List<LJQXX> ljqxxs;
    public List<LJQXX> ljqxxs3; //仅放库里带出的连接器信息
    String yongtus = "";
    String dates = null;

    public List<SaleAttributeVo> saleVo;

    //连接器添加对话框
    public void ljqAddDialog(final String s) {
        if (dialog != null && dialog.isShowing()) {
            return;
        } else {

            View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ljq_add_item, null);
            dialog = new CustomDialog(getActivity(), R.style.dialog_alert_style, 0);

            final TextView erweima = (TextView) view.findViewById(R.id.erweima);
            final TextView bianhao = (TextView) view.findViewById(R.id.bianhao);
            final TextView xinghao = (TextView) view.findViewById(R.id.xinghao);
            final TextView changjia = (TextView) view.findViewById(R.id.changjia);
            final TextView qianfeng = (TextView) view.findViewById(R.id.qianfeng);
            final TextView leixing = (TextView) view.findViewById(R.id.leixing);
            final TextView yongtu = (TextView) view.findViewById(R.id.yongtu);

            Button queding = (Button) view.findViewById(R.id.queding);
            Button quxiao = (Button) view.findViewById(R.id.quxiao);

            erweima.setText(s);

            //解析二维码
            String bianhaos = null, xinghaos = null, changjias = null, changjias2 = null, leixings = null;
            try {
                String[] s1 = s.split(";");
                if (s1.length >= 2) {
                    bianhaos = s1[0];
                    bianhao.setText(bianhaos);
                } else {
                    ToastUtils.showToast(getActivity(), "二维码不合法");
                    return;
                }

                String[] s2 = bianhaos.split("-");
                if (s2.length >= 3) {
                    String changjiass = s2[0];
                    if (changjiass.equals("JDGL")) {
                        changjia.setText("健达国力");
                        changjias2 = "健达国力";
                    } else if (changjiass.equals("FNKS")) {
                        changjia.setText("菲尼克斯");
                        changjias2 = "菲尼克斯";
                    } else if (changjiass.equals("HTDQ")) {
                        changjia.setText("航天电器");
                        changjias2 = "航天电器";
                    } else if (changjiass.equals("RTZN")) {
                        changjia.setText("瑞通智能");
                        changjias2 = "瑞通智能";
                    } else if (changjiass.equals("ZHGD")) {
                        changjia.setText("中航光电");
                        changjias2 = "中航光电";
                    } else if (changjiass.equals("GYDQ")) {
                        changjia.setText("冠源电气");
                        changjias2 = "冠源电气";
                    }
                } else {
                    ToastUtils.showToast(getActivity(), "二维码不合法");
                    return;
                }

                xinghaos = s1[1];
                xinghao.setText(xinghaos);
                String[] s3 = xinghaos.split("-");
                if (s3.length >= 3) {
                    String leixingss = s3[1];
                    if (leixingss.equals("CA")) {
                        leixing.setText("电接口组件");
                        leixings = "电接口组件";
                    } else if (leixingss.equals("CG")) {
                        leixing.setText("光纤接口组件");
                        leixings = "光纤接口组件";
                    }
                } else {
                    ToastUtils.showToast(getActivity(), "二维码不合法");
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showToast(getActivity(), "二维码不合法");
            } finally {
                //ToastUtils.showToast(getActivity(),"二维码不合法");
            }

            //接口用途
            yongtu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 创建PopupMenu，其中第二个参数为要关联显示的控件
                    PopupMenu tPopupMenu = new PopupMenu(getActivity(), yongtu);
                    // 装载视图，Menu在构造函数中已经生成，此处getMenu直接用即可
                    tPopupMenu.getMenuInflater().inflate(R.menu.pop_menu, tPopupMenu.getMenu());
                    // 设置item的点击监听
                    tPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.pop_menu_item_dianyuan:
                                    yongtu.setText("电源+开入接口");
                                    yongtus = "电源+开入接口";
                                    break;
                                case R.id.pop_menu_item_jiaoliu:
                                    yongtu.setText("交流量接口");
                                    yongtus = "交流量接口";
                                    break;
                                case R.id.pop_menu_item_kaichu:
                                    yongtu.setText("开出接口");
                                    yongtus = "开出接口";
                                    break;
                                case R.id.pop_menu_item_guangqian:
                                    yongtu.setText("光纤接口");
                                    yongtus = "光纤接口";
                                    break;
                            }
                            return true;
                        }
                    });
                    // 显示
                    tPopupMenu.show();
                }
            });

            //铅封日期
            qianfeng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogs = new MyDatePickerDialog(getActivity(), new MyDatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            mYear = year;
                            mMonth = monthOfYear + 1;
                            mDay = dayOfMonth;
                            qianfeng.setText(mYear + "-" + mMonth + "-" + mDay);
                            dates = TimeUtil.formatString(mYear + "-" + mMonth + "-" + mDay);
                        }
                    }, mYear, mMonth, mDay);

                    if (mYear > 0) {
                        dialogs.updateDate(mYear, mMonth, mDay);
                    }
                    dialogs.myShow();
                }
            });

            //判断该连接器是否已添加
            if (s != null && !s.isEmpty()) {
                if (list_erweima.contains(s)) {
                    ToastUtils.showToast(getActivity(), "该连接器已添加");
                    return;
                } else {
                    list_erweima.add(s);
                }
            }

            //确定  生成一个连接器对象
            queding.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (dates != null&&yongtus != null){
//                        //继续
//                    }else{
//                        boolean b=yesOrNodialog();
//                        if (b){
//                            //继续
//                            flag = false;
//                        }else{
//                            return;
//                        }
//                    }

                    LJQXX ljqxx = new LJQXX();

                    ljqxx.setEwmxx(erweima.getText() + "");
                    ljqxx.setCtzjbh(bianhao.getText() + "");
                    ljqxx.setCtzjxh(xinghao.getText() + "");
                    ljqxx.setCtzjzzcj(changjia.getText() + "");
                    ljqxx.setJklx(leixing.getText() + "");
                    if (dates != null) {
                        ljqxx.setJtzjqfrq(dates);
                    }
                    if (yongtus != null) {
                        ljqxx.setJkyt(yongtus + "");
                    }

                    if (DemoActivity.instance.bhsb.getEd_tag().equals("M")) {
                        //编辑台账
//                        ljqxx.setBhpzid(DeviceDetailsActivity.bhpz.getId()); //添加主保护ID
//                        ljqxx.setId(util.getInsertId("LJQXX")); //添加连接器ID
//                        util.coreLJQ("C", ljqxx); //插入数据库
//                        ToastUtils.showToast(getActivity(), "连接器添加成功");

                        ljqxxs.add(ljqxx);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                        yongtus = "";
                        dates = null;
                    } else {
                        //新增台账
                        ljqxxs.add(ljqxx);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                        yongtus = "";
                        dates = null;
                    }
                }
            });


            //取消
            quxiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list_erweima.contains(s)) {
                        list_erweima.remove(s);
                    }
                    dialog.dismiss();
                }
            });
            dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    //连接器信息不完整时候是否继续保存*****************************
    boolean flag = false;  //false 不继续  true继续

    protected boolean yesOrNodialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("连接器信息填写不完整,是否继续？");
        builder.setTitle("提示");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                flag = true;
            }
        });

        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                flag = false;
            }
        });
        builder.create().show();
        return flag;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ljq;
    }

    @Override
    public void initview() {

        if (DemoActivity.instance.hasSaoma && DemoActivity.instance.state.equals("C")) {
            tvBottom.setVisibility(View.GONE);
        } else {
            tvBottom.setVisibility(View.VISIBLE);
            if (DemoActivity.instance.rzgl != null) {
                tvBottom.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
            } else {
                tvBottom.setText("本条台账最后一次修改时间：");
            }
        }

        //ToastUtils.showToast(getActivity(),"连接器信息initview调用了");
        ljqxxs = new ArrayList<>();
        ljqxxs3 = new ArrayList<>();
        list_erweima = new ArrayList<String>(); //二维码信息集合

        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);


        fragment_ljq_details = (RelativeLayout) getActivity().findViewById(R.id.fragment_ljq_details);


        util = new DaoUtil(getActivity());
        fragment_lv_ljq = (SwipeMenuListView) getActivity().findViewById(R.id.fragment_lv_ljq);
        fragment_lv_ljq_add = (TextView) getActivity().findViewById(R.id.fragment_lv_ljq_add);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity());
                //deleteItem.setBackground(new ColorDrawable(Color.LTGRAY));
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                //deleteItem.setTitleColor(Color.parseColor("#EDEDED"));
                //deleteItem.setIcon(android.R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };


        fragment_lv_ljq.setMenuCreator(creator);
        fragment_lv_ljq.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
                //index的值就是在SwipeMenu依次添加SwipeMenuItem顺序值，类似数组的下标。 从0开始，依次是：0、1、2、3...
                switch (index) {
                    case 0:
                        AppUtils.showDialog(getActivity(), "是否删除此条连接器信息？", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //cancel
                                PopupWindow pop = (PopupWindow) v.getTag();
                                pop.dismiss();
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //ensure
                                PopupWindow pop = (PopupWindow) v.getTag();

                                //判断要删除的连接器二维码信息是否存在于 list_erweima集合中
                                if (list_erweima.contains(ljqxxs.get(position).getEwmxx())) {
                                    list_erweima.remove(ljqxxs.get(position).getEwmxx());
                                }

                                //都不删除库
//                        if (state.equals("C")) {
                                if (true) {
                                    if (is_Edit) {
                                        ljqxxs.remove(position);
                                        adapter.notifyDataSetChanged();
                                        ToastUtils.showToast(getActivity(), "删除成功");
                                    } else {
                                        ToastUtils.showToast(getActivity(), "当前为浏览状态");
                                    }
//                                    break;
                                } else {
                                    //util.coreLJQ("D", ljqxxs.get(position));
                                    ljqxxs.remove(position);
                                    adapter.notifyDataSetChanged();
                                    ToastUtils.showToast(getActivity(), "删除成功");
//                                    break;
                                }

                                pop.dismiss();
                            }
                        });
                }

                return false; // false : 当用户触发其他地方的屏幕时候，自动收起菜单。  true : 不改变已经打开菜单的样式，保持原样不收起。
            }
        });

        //添加连接器按钮点击事件
        fragment_lv_ljq_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startScan = new Intent(getActivity(), CaptureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("request", "ljq");
                startScan.putExtras(bundle);
                startActivityForResult(startScan, 0);
            }
        });
    }

    /**
     * 初始化校验项
     */
    private void init() {
        Map<String, TextView> tmp_key = new HashMap<>();
        Map<String, TextView> tmp_value = new HashMap<>();
        for (int i = 0; i < textViews_key.size(); i++) {
            tmp_key.put(textViews_key.get(i).getText().toString(), textViews_key.get(i));
            tmp_value.put(textViews_key.get(i).getText().toString(), textViews_more.get(i));
        }

        List<SaleAttributeVo> saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getBhData()) {
            if (saleAttributeNameVo.getName().equals("连接器信息")) {
                saleVo = saleAttributeNameVo.getSaleVo();
                break;
            }
        }

        for (SaleAttributeVo saleAttributeVo : saleVo) {
            if (tmp_key.containsKey(saleAttributeVo.getValue() + ":")) {
                map_key.put(saleAttributeVo.getValue(), tmp_key.get(saleAttributeVo.getValue() + ":"));
                map_more.put(saleAttributeVo.getValue(), tmp_value.get(saleAttributeVo.getValue() + ":"));
            }
            if (saleAttributeVo.getValue().equals("接口用途")){
                isjkyt = true;
            }
            if (saleAttributeVo.getValue().equals("铅封日期")){
                isqfrq = true;
            }
            if (saleAttributeVo.getValue().equals("端子箱")){
                isdzp = true;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0) {

            //扫码获取的识别码
            String erweima = data.getExtras().getString("result");

            //解析二维码
            String bh = "";
            String xh = "";
            String[] s1 = erweima.split(";");
            if (s1.length >= 2) {
                bh = s1[0];
                xh = s1[1];
            } else {
                ToastUtils.showToast(getActivity(), "二维码不合法");
                return;
            }

            BHPZ bhpzs = util.getBHPZByLjqSbsbdm(bh, xh);
            String id1 =  "";
            if(bhpzs!=null){
              id1 =  bhpzs.getId()+"";
            }
            String id2 =  DemoActivity.instance.bhsb.getId()+"";
            if (bhpzs != null&&DemoActivity.instance.bhsb.getId()!=null&&!id1.equals(id2)) {
                ToastUtils.showToast(getActivity(), "该连接器已经被其他设备使用");
                return;
            }else if (bhpzs != null&&DemoActivity.instance.bhsb.getId()==null){
                ToastUtils.showToast(getActivity(), "该连接器已经被其他设备使用");
                return;
            }else {
                ljqAddDialog(erweima);
            }
        } else {
            switch (resultCode) {
                case RESULT_OK: /* 取得数据，并显示于画面上 */
                    Bundle bunde = data.getExtras();
                    String value = bunde.getString("result") + "";
                    String puttype = bunde.getString("puttype") + "";
                    String putposition = bunde.getString("putposition") + "";
                    switch (puttype) {
                        case "接口用途":
                            ljqxxs.get(location).setJkyt(value);
                            adapter.notifyDataSetChanged();
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    //仅删除连接器信息ljqxxs,不删库
    public void deleteLjq() {
        if (ljqxxs != null) {
            if (ljqxxs.size() > 0) {
                for (int i = 0; i < ljqxxs.size(); i++) {
                    if (list_erweima.contains(ljqxxs.get(i).getEwmxx())) {
                        list_erweima.remove(ljqxxs.get(i).getEwmxx());
                    }
                    //util.coreLJQ("D", ljqxxs.get(i)); //删除数据表
                    ljqxxs.remove(i);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    //保存之前要删除连接器信息ljqxxs3,直接删库
    public void deleteLjq2() {
        if (ljqxxs3 != null) {
            if (ljqxxs3.size() > 0) {
                for (int i = 0; i < ljqxxs3.size(); i++) {
                    LJQXX ljqxx1 = ljqxxs3.get(i);
                    if (ljqxx1.getED_TAG() != null && ljqxx1.getED_TAG().equals("C")) {
                        util.coreLJQ("D", ljqxx1); //删除数据表
                    } else { //只更新
                        ljqxx1.setED_TAG("D");
                        ljqxx1.setSb("D");
                        ljqxx1.setGhyy("");
                        util.coreLJQ("M", ljqxx1); //更新数据表
                    }
                }
                ljqxxs3.clear();
            }
        }
    }

    //新增台账时添加连接器
    public void ljqAddBhpzid(Long l) {
        if (ljqxxs.size() > 0) {
            for (int i = 0; i < ljqxxs.size(); i++) {
                LJQXX ljqxx1 = ljqxxs.get(i);
                ljqxx1.setBhpzid(l); //添加主保护ID
                ljqxx1.setId(util.getInsertId("LJQXX")); //添加连接器ID
                ljqxx1.setED_TAG("C");
                ljqxx1.setGhyy("");
                util.coreLJQ("C", ljqxx1); //插入数据库
            }
//            ljqxxs.clear();
//            list_erweima.clear();
        }
    }

    //编辑台账时添加连接器
    public void ljqAddBhpzid2() {
        if (ljqxxs.size() > 0) {
            for (int i = 0; i < ljqxxs.size(); i++) {
                LJQXX ljqxx1 = ljqxxs.get(i);
                ljqxx1.setBhpzid(DemoActivity.instance.bhsb.getId()); //添加主保护ID
                ljqxx1.setId(util.getInsertId("LJQXX")); //添加连接器ID
                ljqxx1.setED_TAG("C");
                ljqxx1.setGhyy("");
                util.coreLJQ("C", ljqxx1); //插入数据库
            }
//            ljqxxs.clear();
//            list_erweima.clear();
        }
    }


    @Override
    public void initEvevt() {


    }

    @Override
    public void initData() {
        init();

        etOneDeviceRight.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        etOneDeviceRight.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});

        if (DemoActivity.instance.bhsb.getLjqsl() != null) {
            etOneDeviceRight.setText(DemoActivity.instance.bhsb.getLjqsl() + "");
        }
        if (DemoActivity.instance.bhsb.getLjqzzcj() != null) {
            etTwoDeviceRight.setText(DemoActivity.instance.bhsb.getLjqzzcj() + "");
        }
        if (DemoActivity.instance.bhsb.getLjqxh() != null) {
            etThreeDeviceRight.setText(DemoActivity.instance.bhsb.getLjqxh() + "");
        }

        //ToastUtils.showLongToast(getActivity(),"连接器信息initData调用了************************************");
        if (Fragment_Type_Base.instance.isC) {
            //ToastUtils.showToast(getActivity(),"没有连接器信息");
            ljqxxs.clear();
            ljqxxs3.clear();
            list_erweima.clear();
        } else {
            //编辑数据初始化
            change_data();
        }
        adapter = new LjqTypeAdapter(getActivity(), ljqxxs, this, is_Edit);
        fragment_lv_ljq.setAdapter(adapter);

        saleVo = new ArrayList<>();
        for (SaleAttributeNameVo saleAttributeNameVo : DemoActivity.instance.jiaoYanData.getBhData()) {
            if (saleAttributeNameVo.getName().equals("连接器信息")) {
                saleVo.addAll(saleAttributeNameVo.getSaleVo());
                break;
            }
        }

        boolean isEdit = getArguments().getBoolean("isEdit", false);
        initReceiver(isEdit);
        Fragment_Type_Base.instance.initOnFocusChangeListener(map_key,map_more,false,"连接器信息");
    }

    /**
     * @param type 类型
     */
    public void setIntentData(String type, int position) {
        location = position;
        Map<String, Object> map = new HashMap<>();
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        intent.putExtra("type", type);
        map.put("number", "1");
        map.put("position", position + "");
        intent.putExtra("conditions", (Serializable) map);
        startActivityForResult(intent, 1);
    }


    @Override
    public void initReceiver(boolean isEdit) {
        check = false;
        if (isEdit) {
            is_Edit = true;
            etOneDeviceRight.setBackgroundResource(R.drawable.device_detials_bg);
            etOneDeviceRight.setEnabled(true);
            etTwoDeviceRight.setBackgroundResource(R.drawable.device_detials_bg);
            etTwoDeviceRight.setEnabled(true);
            etThreeDeviceRight.setBackgroundResource(R.drawable.device_detials_bg);
            etThreeDeviceRight.setEnabled(true);
            fragment_lv_ljq_add.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
            if(DemoActivity.instance.Similar){
                change_data();
            }

            for (String s : map_more.keySet()) {
                map_key.get(s).setCompoundDrawables(null, null, null, null);
                if (map_more.get(s).getText().toString().trim().equals("")) {
                    map_more.get(s).setBackground(getResources().getDrawable(R.drawable.device_detials_bg2));
                    map_more.get(s).setHint("必填项");
                    check=true;
                } else {
                    map_more.get(s).setBackground(getResources().getDrawable(R.drawable.device_detials_bg));
                }
            }

        } else {
            is_Edit = false;
            etOneDeviceRight.setBackground(null);
            etOneDeviceRight.setEnabled(false);
            etTwoDeviceRight.setBackground(null);
            etTwoDeviceRight.setEnabled(false);
            etThreeDeviceRight.setBackground(null);
            etThreeDeviceRight.setEnabled(false);
            fragment_lv_ljq_add.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();

            /**
             * 必填项校验设计
             */
            for (String s : map_more.keySet()) {
                if (map_more.get(s).getText().toString().trim().equals("")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.tanhao);
                    drawable.setBounds(0, 0, 25, 25);
                    map_key.get(s).setCompoundDrawables(drawable, null, null, null);
                    check=true;
                }
            }

            if (DemoActivity.instance.rzgl != null &&
                    DemoActivity.instance.rzgl.getCZSJ() != null &&
                    !DemoActivity.instance.rzgl.getCZSJ().equals("")) {
                tvBottom.setVisibility(View.VISIBLE);
                tvBottom.setText("本条台账最后一次修改时间：" + DemoActivity.instance.rzgl.getCZSJ());
            }
        }
        if (checklist()){
            DemoActivity.instance.check("连接器信息", checklist());
        }else{
            DemoActivity.instance.check("连接器信息", check);
        }
    }

    public boolean checklist(){
        boolean show = false;
        for (LJQXX ljqxx : ljqxxs) {
            if (isjkyt&&(ljqxx==null||ljqxx.getJkyt()==null||ljqxx.getJkyt().equals(""))) {
                show = true;
            } else if (isqfrq&&(ljqxx==null||ljqxx.getJtzjqfrq()==null||ljqxx.getJtzjqfrq().equals(""))) {
                show = true;
            } else if (isdzp&&(ljqxx==null||ljqxx.getDzpxx()==null||ljqxx.getDzpxx().equals(""))) {
                show = true;
            }
        }
        return show;
    }

    //过滤要显示的连接器
    public Boolean isAdd(LJQXX ljqxx1) {
        Boolean yy = false; //原因
        yy = ljqxx1.getGhyy() == null || "".equals(ljqxx1.getGhyy());
        Boolean sj = false; //原因
        sj = ljqxx1.getGhsj() == null || "".equals(ljqxx1.getGhsj());
        Boolean edTag = false; //标记删除
        edTag = ljqxx1.getED_TAG() == null || !ljqxx1.getED_TAG().equals("D");


        //sb是非D或者变更时间和变更原因都为null时,返回true, 加载
        if (ljqxx1.getSb() != null) {
            return !ljqxx1.getSb().equals("D") && yy && sj && edTag;
        } else {
            return ljqxx1.getSb() == null && yy && sj && edTag;
        }
    }

    public void change_data() {
        ljqxxs.clear();
        ljqxxs3.clear();
        list_erweima.clear();
//        IDaoUtil util=new DaoUtil(getActivity(),"");   //暂时注释
        List<Object> ljqList = util.getICDOrBKXX(LJQXX.class, DemoActivity.instance.bhsb.getId() + "");
        if(DemoActivity.instance.Similar){
            ljqList = null;
        }
        if (ljqList != null && ljqList.size() > 0) {
            for (int i = 0; i < ljqList.size(); i++) {
                LJQXX ljqxx1 = (LJQXX) ljqList.get(i);
                if (isAdd(ljqxx1)) {
                    ljqxxs.add(ljqxx1);
                    ljqxxs3.add(ljqxx1);
                    list_erweima.add(ljqxx1.getEwmxx());
                }
            }
            ljqList.clear();
        } else {
            //ToastUtils.showToast(getActivity(),"没有连接器信息");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //getActivity().unregisterReceiver(inputMyReceiver);
        unbinder.unbind();
    }

    @Override
    public void click(View v) {
        switch (v.getId()) {
//            case R.id.item_delete:
//                ToastUtils.showToast(getActivity(),"删除item的position是:"+(Integer) v.getTag());
//                break;
        }
    }

    public int dp2px(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public void saveLjq() {

        String ljqsl = etOneDeviceRight.getText().toString() + "";
        String ljqzzcj = etTwoDeviceRight.getText().toString() + "";
        String ljqxh = etThreeDeviceRight.getText().toString() + "";

        DemoActivity.instance.bhsb.setLjqsl(ljqsl + "");
        DemoActivity.instance.bhsb.setLjqzzcj(ljqzzcj + "");
        DemoActivity.instance.bhsb.setLjqxh(ljqxh + "");

    }
    //连接器组合必填校核
    public boolean checkljq() {
        boolean ischeckok = true;
        boolean isjkyt = false;
        boolean isqfrq = false;
        boolean isdzx = false;

        if (saleVo.size()>0) {
            for (SaleAttributeVo vo : saleVo) {
                if (vo.getValue().equals("连接器数量") && (etOneDeviceRight.getText().toString() + "").equals("")) {
                    ToastUtils.showToast(getActivity(), "请填写连接器数量");
                    ischeckok = false;
                    break;
                }
                else if (vo.getValue().equals("连接器插座组件制造厂家") && (etTwoDeviceRight.getText().toString() + "").equals("")) {
                    ToastUtils.showToast(getActivity(), "请填写连接器插座组件制造厂家");
                    ischeckok = false;
                    break;
                }
                else if (vo.getValue().equals("连接器插座组件型号") && (etThreeDeviceRight.getText().toString() + "").equals("")) {
                    ToastUtils.showToast(getActivity(), "请填写连接器插座组件型号");
                    ischeckok = false;
                    break;
                }
                if (vo.getValue().equals("接口用途")){
                    isjkyt = true;
                }
                if (vo.getValue().equals("铅封日期")){
                    isqfrq = true;
                }
                if (vo.getValue().equals("端子箱")){
                    isdzx = true;
                }
            }
        }
        if (isjkyt&&ischeckok||isqfrq&&ischeckok||isdzx&&ischeckok){
            for (LJQXX ljqxx : ljqxxs) {
                if (isjkyt&&ljqxx.getJkyt()==null||isjkyt&&(ljqxx.getJkyt().toString()+"").equals("")){
                    ToastUtils.showToast(getActivity(), "请选择接口用途");
                    ischeckok = false;
                    break;
                }else  if (isqfrq&&ljqxx.getJtzjqfrq()==null||isqfrq&&(ljqxx.getJtzjqfrq().toString()+"").equals("")){
                    ToastUtils.showToast(getActivity(), "请选择铅封日期");
                    ischeckok = false;
                    break;
                }else  if (isdzx&&ljqxx.getDzpxx()==null||isdzx&&(ljqxx.getDzpxx().toString()+"").equals("")){
                    ToastUtils.showToast(getActivity(), "请填写端子箱");
                    ischeckok = false;
                    break;
                }
            }
        }
        return ischeckok;
    }
}
