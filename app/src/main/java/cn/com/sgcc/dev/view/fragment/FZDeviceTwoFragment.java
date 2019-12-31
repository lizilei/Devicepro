package cn.com.sgcc.dev.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.format.DateUtils;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.DeviceSelectAdapter;
import cn.com.sgcc.dev.adapter.FZDeviceTwoAdapter;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.customeView.MyDatePickerDialog;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

/**
 * <p>@description:辅助保护设备详情页2</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/13
 */

public class FZDeviceTwoFragment extends BaseFragment {
    private ListView infor_select;
    private List<DeviceDetailsNameItem> data_name;

    private FZDeviceTwoAdapter adapter;
    private CustomDialog dialog;
    public static FZDeviceTwoFragment instance = null;

    private MyDatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay;
    private Calendar c;

    //投运日期
    public String tyrq;
    //定期检验周期
    public String dqjyzq;
    //运行单位
    public String yxdw;
    //维护单位
    public String whdw;
    //设计单位
    public String sjdw;
    //基建单位
    public String jjdw;
    //运行状态
    public String yxzt;
    //退出运行时间   非必填项
    public String tcyxsj;
    //设备属性
    public String sbsx;
    //上次检修时间
    public String scjxsj;
    //出厂日期   非必填项
    public String ccrq;
    //出厂编号   非必填项
    public String ccbh;
    //资产单位   非必填项
    public String zcdw;
    //资产性质   非必填项
    public String zcxz;
    //资产编号   非必填项
    public String zcbh;
    //板卡数量
    public String bksl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_two_device;
    }

    @Override
    public void initview() {
        infor_select = (ListView) getActivity().findViewById(R.id.fragment_device_details_two_lv);
        dialog = new CustomDialog(getActivity(), R.style.dialog_alert_style, 0);
    }

    @Override
    public void initEvevt() {

    }

    @Override
    public void initData() {
        data_name = new ArrayList<>();
        if (DeviceDetailsActivity.state.equals("M") || DeviceDetailsActivity.isFromSaoma) {
            if (DeviceDetailsActivity.fzbhsb.getTyrq() != null) {
                tyrq = TimeUtil.formatString2(DeviceDetailsActivity.fzbhsb.getTyrq());
            }
            if (tyrq == null || tyrq.equals("")) {
                tyrq = "必填项";
            }
            dqjyzq = DeviceDetailsActivity.fzbhsb.getDqjyzq() + "";
            if (dqjyzq.equals("")) {
                dqjyzq = "0";
            }
            yxdw = DeviceDetailsActivity.fzbhsb.getYxdw();
            if (yxdw == null || yxdw.equals("")) {
                yxdw = "必填项";
            }
            whdw = DeviceDetailsActivity.fzbhsb.getWhdw();
            if (whdw == null || whdw.equals("")) {
                whdw = "必填项";
            }
            sjdw = DeviceDetailsActivity.fzbhsb.getSjdw();
            if (sjdw == null || sjdw.equals("")) {
                sjdw = "必填项";
            }
            jjdw = DeviceDetailsActivity.fzbhsb.getJjdw();
            if (jjdw == null || jjdw.equals("")) {
                jjdw = "必填项";
            }
            yxzt = DeviceDetailsActivity.fzbhsb.getYxzt();
            if (yxzt == null || yxzt.equals("")) {
                yxzt = "必填项";
            }
            if (DeviceDetailsActivity.fzbhsb.getTcyxsj() != null) {
                tcyxsj = TimeUtil.formatString2(DeviceDetailsActivity.fzbhsb.getTcyxsj());
            }
            if (tcyxsj == null || tcyxsj.equals("")) {
                tcyxsj = "必填项";
            }
            sbsx = DeviceDetailsActivity.fzbhsb.getSbsx();
            if (sbsx == null || sbsx.equals("")) {
                sbsx = "必填项";
            }
            if (DeviceDetailsActivity.fzbhsb.getScdqjysj() != null) {
                scjxsj = TimeUtil.formatString2(DeviceDetailsActivity.fzbhsb.getScdqjysj());
            }
            if (scjxsj == null || scjxsj.equals("")) {
                scjxsj = "必填项";
            }
            if (DeviceDetailsActivity.fzbhsb.getCcrq() != null) {
                ccrq = TimeUtil.formatString2(DeviceDetailsActivity.fzbhsb.getCcrq());
            }
            if (ccrq == null || ccrq.equals("")) {
                ccrq = "请选择";
            }
            ccbh = DeviceDetailsActivity.fzbhsb.getCcbh();
            if (ccbh == null || ccbh.equals("")) {
                ccbh = "请选择";
            }
            zcdw = DeviceDetailsActivity.fzbhsb.getZcdw();
            if (zcdw == null || zcdw.equals("")) {
                zcdw = "请选择";
            }
            zcxz = DeviceDetailsActivity.fzbhsb.getZcxz();
            if (zcxz == null || zcxz.equals("")) {
                zcxz = "请选择";
            }
            zcbh = DeviceDetailsActivity.fzbhsb.getZcbh();
            if (zcbh == null || zcbh.equals("")) {
                zcbh = "请选择";
            }
            bksl = DeviceDetailsActivity.fzbhsb.getBksl() + "";
            if (bksl == null || bksl.equals("")) {
                bksl = "0";
            }
        } else {
            tyrq = "必填项";
            dqjyzq = "0";
            yxdw = "必填项";
            whdw = "必填项";
            sjdw = "必填项";
            jjdw = "必填项";
            yxzt = "必填项";
            tcyxsj = "必填项";
            sbsx = "必填项";
            scjxsj = "必填项";
            ccrq = "请选择";
            ccbh = "请选择";
            zcdw = "请选择";
            zcxz = "请选择";
            zcbh = "请选择";
            bksl = "0";
        }
        for (int i = 0; i < 5; i++) {
            DeviceDetailsNameItem item = new DeviceDetailsNameItem();
            if (i == 0) {
                item.setNum(3);
                item.setName_one("投运日期");
                item.setContent_one(tyrq);
                item.setName_two("定期检验周期");
                item.setContent_two(dqjyzq);
                item.setName_three("运行单位");
                item.setContent_three(yxdw);
            } else if (i == 1) {
                item.setNum(4);
                item.setName_one("维护单位");
                item.setContent_one(whdw);
                item.setName_two("设计单位");
                item.setContent_two(sjdw);
                item.setName_three("基建单位");
                item.setContent_three(jjdw);
                item.setName_four("运行状态");
                item.setContent_four(yxzt);
            } else if (i == 2) {
                if (yxzt.equals("退运")) {
                    item.setNum(3);
                    item.setName_one("退出运行时间");
                    item.setContent_one(tcyxsj);
                    item.setName_two("设备属性");
                    item.setContent_two(sbsx);
                    item.setName_three("上次检修时间");
                    item.setContent_three(scjxsj);
                } else {
                    item.setNum(2);
                    item.setName_one("设备属性");
                    item.setContent_one(sbsx);
                    item.setName_two("上次检修时间");
                    item.setContent_two(scjxsj);
                }
            } else if (i == 3) {
                item.setNum(4);
                item.setName_one("出厂日期");
                item.setContent_one(ccrq);
                item.setName_two("出厂编号");
                item.setContent_two(ccbh);
                item.setName_three("资产单位");
                item.setContent_three(zcdw);
                item.setName_four("资产性质");
                item.setContent_four(zcxz);
            } else if (i == 4) {
                item.setNum(2);
                item.setName_one("资产编号");
                item.setContent_one(zcbh);
                item.setName_two("板卡数量");
                item.setContent_two(bksl);
            }
            data_name.add(item);
        }
        adapter = new FZDeviceTwoAdapter(getActivity(), data_name);
        infor_select.setAdapter(adapter);
    }

    @Override
    public void initReceiver(boolean isEdit) {

    }

    /**
     * 弹出下拉列表框
     *
     * @param currentPosition 当前item的位置
     * @param num
     * @param item
     */
    public void showDialog(final int currentPosition, final int num, final List<String> item) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_one_device_select_item, null);
        //根据id在布局中找到控件对象
        TextView tv_dialog_title_one = (TextView) view.findViewById(R.id.fragment_device_details_select_item_cancel);
        final EditText app_search_edit = (EditText) view.findViewById(R.id.app_search_edit);
        final ListView lv_dialog = (ListView) view.findViewById(R.id.fragment_device_details_select_item__lv);
        final EditText et_dialog = (EditText) view.findViewById(R.id.app_search_edit);

        Button btn_search = (Button) view.findViewById(R.id.btn_search);
        Button btn_add = (Button) view.findViewById(R.id.btn_add);
        final DeviceSelectAdapter devAdapter = new DeviceSelectAdapter(getActivity(), item);

        view.findViewById(R.id.fragment_device_details_select_item_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (app_search_edit.getText().toString().trim().equals("")) {
                    ToastUtils.showToast(getActivity(), "请输入添加内容");
                } else {
                    for (int i = 0; i < item.size(); i++) {
                        if (item.get(i).contains(app_search_edit.getText().toString().trim())) {
                            lv_dialog.setSelection(i);
                            break;
                        }
                    }
                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = app_search_edit.getText().toString().trim();
                if (content.equals("")) {
                    ToastUtils.showToast(getActivity(), "请输入添加内容");
                } else {
                    item.clear();
                    if (currentPosition == 2 && data_name.get(num).
                            getName_two().equals("定期检验周期")) {
                        content = Float.parseFloat(content) + "";
                    }
                    item.add(content);
                    devAdapter.notifyDataSetChanged();
                }
            }
        });

        if (currentPosition == 1) {
            if (data_name.get(num).getContent_one() != null &&
                    !data_name.get(num).getContent_one().equals("null") &&
                    !data_name.get(num).getContent_one().equals("") &&
                    !data_name.get(num).getContent_one().equals("必填项") &&
                    !data_name.get(num).getContent_one().equals("请选择")) {
                app_search_edit.setText(data_name.get(num).getContent_one());
            }
            if (data_name.get(num).getName_one().equals("资产编号")) {
//                app_search_edit.setKeyListener(DigitsKeyListener.
//                        getInstance("0123456789.abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
                app_search_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
            }
        } else if (currentPosition == 2) {
            if (data_name.get(num).getContent_two() != null &&
                    !data_name.get(num).getContent_two().equals("null") &&
                    !data_name.get(num).getContent_two().equals("") &&
                    !data_name.get(num).getContent_two().equals("必填项") &&
                    !data_name.get(num).getContent_two().equals("请选择")) {
                app_search_edit.setText(data_name.get(num).getContent_two());
            }
            if (data_name.get(num).getName_two().equals("板卡数量")) {
                app_search_edit.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                app_search_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
            } else if (data_name.get(num).getName_two().equals("定期检验周期")) {
                app_search_edit.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                //app_search_edit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                app_search_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
            } else if (data_name.get(num).getName_two().equals("出厂编号")) {
//                app_search_edit.setKeyListener(DigitsKeyListener.
//                        getInstance("0123456789.abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
                app_search_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
            }
        } else if (currentPosition == 3) {
            if (data_name.get(num).getContent_three() != null &&
                    !data_name.get(num).getContent_three().equals("null") &&
                    !data_name.get(num).getContent_three().equals("") &&
                    !data_name.get(num).getContent_three().equals("必填项") &&
                    !data_name.get(num).getContent_three().equals("请选择")) {
                app_search_edit.setText(data_name.get(num).getContent_three());
            }
        } else if (currentPosition == 4) {
            if (data_name.get(num).getContent_four() != null &&
                    !data_name.get(num).getContent_four().equals("null") &&
                    !data_name.get(num).getContent_four().equals("") &&
                    !data_name.get(num).getContent_four().equals("必填项") &&
                    !data_name.get(num).getContent_four().equals("请选择")) {
                app_search_edit.setText(data_name.get(num).getContent_four());
            }
        }

        if (item.size() == 0) {
            btn_search.setVisibility(View.GONE);
        } else {
            btn_add.setVisibility(View.GONE);
        }
        lv_dialog.setAdapter(devAdapter);

        if (!app_search_edit.getText().toString().trim().equals("")) {
            if (item.size() > 0) {
                for (int i = 0; i < item.size(); i++) {
                    if (item.get(i).contains(app_search_edit.getText().toString().trim())) {
                        lv_dialog.setSelection(i);
                    }
                }
            }
        }

        lv_dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (num == 0) {
                    if (currentPosition == 2) {
                        dqjyzq = item.get(position);
                        data_name.get(num).setContent_two(dqjyzq);
                    } else if (currentPosition == 3) {
                        yxdw = item.get(position);
                        data_name.get(num).setContent_three(yxdw);
                    }
                } else if (num == 1) {
                    if (currentPosition == 1) {
                        whdw = item.get(position);
                        data_name.get(num).setContent_one(whdw);
                    } else if (currentPosition == 2) {
                        sjdw = item.get(position);
                        data_name.get(num).setContent_two(sjdw);
                    } else if (currentPosition == 3) {
                        jjdw = item.get(position);
                        data_name.get(num).setContent_three(jjdw);
                    } else if (currentPosition == 4) {
                        yxzt = item.get(position);
                        data_name.get(num).setContent_four(yxzt);

                        tcyxsj = "";
                        if (yxzt.equals("退运")) {
                            data_name.get(num + 1).setNum(3);
                            data_name.get(num + 1).setName_one("退出运行时间");
                            data_name.get(num + 1).setContent_one("必填项");
                            data_name.get(num + 1).setName_two("设备属性");
                            data_name.get(num + 1).setContent_two(sbsx);
                            data_name.get(num + 1).setName_three("上次检修时间");
                            data_name.get(num + 1).setContent_three(scjxsj);
                        } else {
                            data_name.get(num + 1).setNum(2);
                            data_name.get(num + 1).setName_one("设备属性");
                            data_name.get(num + 1).setContent_one(sbsx);
                            data_name.get(num + 1).setName_two("上次检修时间");
                            data_name.get(num + 1).setContent_two(scjxsj);
                        }
                    }
                } else if (num == 2) {
                    if (data_name.get(num).getNum() == 2) {
                        sbsx = item.get(position);
                        data_name.get(num).setContent_one(sbsx);
                    } else {
                        sbsx = item.get(position);
                        data_name.get(num).setContent_two(sbsx);
                    }
                } else if (num == 3) {
                    if (currentPosition == 2) {
                        ccbh = item.get(position);
                        data_name.get(num).setContent_two(ccbh);
                    } else if (currentPosition == 3) {
                        zcdw = item.get(position);
                        data_name.get(num).setContent_three(zcdw);
                    } else if (currentPosition == 4) {
                        zcxz = item.get(position);
                        data_name.get(num).setContent_four(zcxz);
                    }
                } else if (num == 4) {
                    if (currentPosition == 1) {
                        zcbh = item.get(position);
                        data_name.get(num).setContent_one(zcbh);
                    } else if (currentPosition == 2) {
                        bksl = item.get(position);
                        data_name.get(num).setContent_two(bksl);
                    }
                }

                dialog.dismiss();
                adapter.notifyDataSetChanged();
            }
        });

        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /**
     * 时间选择控件
     *
     * @param position
     * @param num
     */
    public void showDateDialog(final int position, final int num) {
        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new MyDatePickerDialog(getActivity(), new MyDatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear + 1;
                mDay = dayOfMonth;
                if (position == 0) {
                    tyrq = mYear + "-" + mMonth + "-" + mDay;
                    data_name.get(position).setContent_one(tyrq);
                } else if (position == 2) {
                    if (num == 1) {
                        tcyxsj = mYear + "-" + mMonth + "-" + mDay;
                        data_name.get(position).setContent_one(tcyxsj);
                    } else if (num == 2) {
                        scjxsj = mYear + "-" + mMonth + "-" + mDay;
                        data_name.get(position).setContent_two(scjxsj);
                    } else if (num == 3) {
                        scjxsj = mYear + "-" + mMonth + "-" + mDay;
                        data_name.get(position).setContent_three(scjxsj);
                    }
                } else if (position == 3) {
                    ccrq = mYear + "-" + mMonth + "-" + mDay;
                    data_name.get(position).setContent_one(ccrq);
                }
                adapter.notifyDataSetChanged();
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.myShow();
    }

    /**
     * 检验输入的内容是否合法
     *
     * @param type
     * @param str
     * @return
     */
    private boolean checkIsTrue(String type, String str) {
        if (str.equals("")) {
            ToastUtils.showToast(getActivity(), "未输入任何内容");
            return false;
        }
        if (type.equals("定期检验周期") || type.equals("板卡数量")) {
            if (AppUtils.isNUmeric(str)) {
                return true;
            }
            return false;
        }
        return true;
    }
}
