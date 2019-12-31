package cn.com.sgcc.dev.view.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.LjqAdapter;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.customeView.MyDatePickerDialog;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.LJQXX;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

import static android.app.Activity.RESULT_OK;
import static cn.com.sgcc.dev.view.activity.DeviceDetailsActivity.state;

/**
 * <p>@description:</p>
 * 连接器界面
 *
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class LjqFragment extends BaseFragment implements LjqAdapter.Callback {
    private SwipeMenuListView fragment_lv_ljq;
    private CustomDialog dialog;
    private LjqAdapter adapter;
    private TextView fragment_lv_ljq_add;
    public RelativeLayout fragment_ljq_details;
    IDaoUtil util;

    private MyDatePickerDialog dialogs;
    private int mYear, mMonth, mDay;
    private Calendar c;
    public static LjqFragment instance = null;


    public LjqFragment() {
    }

    //连接器信息添加对话框
    public static List<String> list_erweima ; //二维码信息集合
    public List<LJQXX> ljqxxs;
    public List<LJQXX> ljqxxs3; //仅放库里带出的连接器信息
    String yongtus = "";
    String dates = null;

    //连接器添加对话框
    public void ljqAddDialog(String s) {
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

                    if (state.equals("M")) {
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
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //index的值就是在SwipeMenu依次添加SwipeMenuItem顺序值，类似数组的下标。 从0开始，依次是：0、1、2、3...
                switch (index) {
                    case 0: //判断要删除的连接器二维码信息是否存在于 list_erweima集合中
                        if (list_erweima.contains(ljqxxs.get(position).getEwmxx())) {
                            list_erweima.remove(ljqxxs.get(position).getEwmxx());
                        }

                        //都不删除库
                        if (state.equals("C")) {
                            ljqxxs.remove(position);
                            adapter.notifyDataSetChanged();
                            ToastUtils.showToast(getActivity(), "删除成功");
                            break;
                        } else {
                            //util.coreLJQ("D", ljqxxs.get(position));
                            ljqxxs.remove(position);
                            adapter.notifyDataSetChanged();
                            ToastUtils.showToast(getActivity(), "删除成功");
                            break;
                        }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0) {
            //扫码获取的识别码
            String erweima = data.getExtras().getString("result");
            ljqAddDialog(erweima);
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
                    LJQXX ljqxx1 = (LJQXX) ljqxxs3.get(i);
                    if (ljqxx1.getED_TAG()!=null&&ljqxx1.getED_TAG().equals("C")){
                        util.coreLJQ("D", ljqxx1); //删除数据表
                    }else{ //只更新
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
                LJQXX ljqxx1 = (LJQXX) ljqxxs.get(i);
                ljqxx1.setBhpzid(l); //添加主保护ID
                ljqxx1.setId(util.getInsertId("LJQXX")); //添加连接器ID
                ljqxx1.setED_TAG("C");
                ljqxx1.setGhyy("");
                util.coreLJQ("C", ljqxx1); //插入数据库
            }
            ljqxxs.clear();
            list_erweima.clear();
        }
    }

    //编辑台账时添加连接器
    public void ljqAddBhpzid2() {
        if (ljqxxs.size() > 0) {
            for (int i = 0; i < ljqxxs.size(); i++) {
                LJQXX ljqxx1 = (LJQXX) ljqxxs.get(i);
                ljqxx1.setBhpzid(DeviceDetailsActivity.bhpz.getId()); //添加主保护ID
                ljqxx1.setId(util.getInsertId("LJQXX")); //添加连接器ID
                ljqxx1.setED_TAG("C");
                ljqxx1.setGhyy("");
                util.coreLJQ("C", ljqxx1); //插入数据库
            }
            ljqxxs.clear();
            list_erweima.clear();
        }
    }


    @Override
    public void initEvevt() {
    }

    @Override
    public void initData() {
        //ToastUtils.showLongToast(getActivity(),"连接器信息initData调用了************************************");
        if (state.equals("C")) {
            //ToastUtils.showToast(getActivity(),"没有连接器信息");
            ljqxxs.clear();
            ljqxxs3.clear();
            list_erweima.clear();
        } else {
            //编辑数据初始化
            change_data();
        }
        adapter = new LjqAdapter(getActivity(), ljqxxs, this);
        fragment_lv_ljq.setAdapter(adapter);
    }

    @Override
    public void initReceiver(boolean isEdit) {

    }

    //过滤要显示的连接器
    public Boolean isAdd(LJQXX ljqxx1){
        Boolean yy=false; //原因
        if (ljqxx1.getGhyy()==null||"".equals(ljqxx1.getGhyy())){
            yy=true;
        }else{
            yy=false;
        }
        Boolean sj=false; //原因
        if (ljqxx1.getGhsj()==null||"".equals(ljqxx1.getGhsj())){
            sj=true;
        }else{
            sj=false;
        }
        Boolean edTag=false; //标记删除
        if (ljqxx1.getED_TAG()!=null&&ljqxx1.getED_TAG().equals("D")){
            edTag=false;
        }else{
            edTag=true;
        }


        //sb是非D或者变更时间和变更原因都为null时,返回true, 加载
        if (ljqxx1.getSb()!=null){
            if (!ljqxx1.getSb().equals("D")&&yy&&sj&&edTag){
                return true;
            }else{
                return false;
            }
        }else if(ljqxx1.getSb()==null&&yy&&sj&&edTag){
            return true;
        }else{
            return false;
        }
    }

    public void change_data() {
        ljqxxs.clear();
        ljqxxs3.clear();
        list_erweima.clear();
        //IDaoUtil util=new DaoUtil(getActivity(),"");   //暂时注释
        List<Object> ljqList = util.getICDOrBKXX(LJQXX.class, DeviceDetailsActivity.bhpz.getId() + "");

        if (ljqList.size() > 0) {
            for (int i = 0; i < ljqList.size(); i++) {
                LJQXX ljqxx1 = (LJQXX) ljqList.get(i);
                if (isAdd(ljqxx1)){
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
}
