package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.datapick.widget.bean.DateType;

import java.util.HashMap;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.model2.LJQXX;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Base;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Inset;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Ljq;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Road_Ak;
import cn.com.sgcc.dev.view.viewinterface.DateChooseListener;

/**
 * <p>@description:</p>
 * 连接器适配器
 *
 * @author tq
 * @version 1.0.0
 * @Email
 * @since 2018/1/2
 */
public class LjqTypeAdapter extends BaseAdapter implements View.OnClickListener {
    private List<LJQXX> list;
    private Context context;
    private Callback mCallback;
    private boolean isEdit;

    //自定义接口，用于回调按钮点击事件到Activity
    public interface Callback {
        void click(View v);
    }

    public LjqTypeAdapter(Context context, List<LJQXX> list, Callback callback, boolean isEdit) {
        this.list = list;
        this.context = context;
        this.isEdit = isEdit;
        mCallback = callback;
    }

    public void setDatas(List<LJQXX> list, boolean isEdit) {
        this.list = list;
        this.isEdit = isEdit;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            //erweima,bianhao ,xinghao ,changjia , qianfeng, leixing,yongtu
            convertView = LayoutInflater.from(context).inflate(R.layout.type_ljq_adapter, null);
            holder = new ViewHolder(convertView);
            holder.duanzixiang = (TextView) convertView.findViewById(R.id.tv_seven_device_name_right);
            holder.bianhao = (TextView) convertView.findViewById(R.id.tv_two_device_name_right);
            holder.xinghao = (TextView) convertView.findViewById(R.id.tv_one_device_name_right);
            holder.changjia = (TextView) convertView.findViewById(R.id.tv_three_device_name_right);
            holder.qianfeng = (TextView) convertView.findViewById(R.id.tv_six_device_name_right);
            holder.leixing = (TextView) convertView.findViewById(R.id.tv_four_device_name_right);
            holder.yongtu = (TextView) convertView.findViewById(R.id.tv_five_device_name_right);
            holder.duanzx = (EditText) convertView.findViewById(R.id.et_seven__device_right);

            holder.yongtu_tv = (TextView) convertView.findViewById(R.id.tv_five_device_name_left);
            holder.qianfeng_tv = (TextView) convertView.findViewById(R.id.tv_six_device_name_left);
            holder.duanzx_tv = (TextView) convertView.findViewById(R.id.tv_seven_device_name_left);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.duanzx.setText("");
        if (list.get(position).getDzpxx() != null) {
            holder.duanzx.setText(list.get(position).getDzpxx() + "");//端子箱缺失
        }
        if (list.get(position).getCtzjbh() != null) {
            holder.bianhao.setText(list.get(position).getCtzjbh() + "");
        }
        if (list.get(position).getCtzjxh() != null) {
            holder.xinghao.setText(list.get(position).getCtzjxh() + "");
        }
        if (list.get(position).getCtzjzzcj() != null) {
            holder.changjia.setText(list.get(position).getCtzjzzcj() + "");
        }
        if (list.get(position).getJklx() != null) {
            holder.leixing.setText(list.get(position).getJklx() + "");
        }
        if (list.get(position).getJkyt() != null) {
            holder.yongtu.setText(list.get(position).getJkyt() + "");
        }
        //holder.xinghao.setText("GDW-01-00-8160-1(L=3000,16*1.5)-02200");


        if (TimeUtil.dateIsEmpty(list.get(position).getJtzjqfrq())) {
            holder.qianfeng.setText("");
        } else {
            if (TimeUtil.formatString2(list.get(position).getJtzjqfrq()) == null) {
                holder.qianfeng.setText("");
            } else {
                holder.qianfeng.setText(TimeUtil.formatString2(list.get(position).getJtzjqfrq()) + "");
            }
        }
        //接口用途
        holder.yongtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_Type_Ljq.instance.setIntentData("接口用途", position);
            }
        });
        //铅封日期
        final ViewHolder finalHolder = holder;
        holder.qianfeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.showDatePickDialog(context, DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        Fragment_Type_Ljq.instance.ljqxxs.get(position).setJtzjqfrq(date + "");
                        finalHolder.qianfeng.setText(date);
                    }
                });
            }
        });
        //端子箱
        holder.duanzx.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = finalHolder.duanzx.getText() + "";
                Fragment_Type_Ljq.instance.ljqxxs.get(position).setDzpxx(value);
            }
        });
        holder.duanzx.addTextChangedListener(new TextWatcher() {
            String currentText;

            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after) {
                currentText = str.toString().trim();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!Fragment_Type_Ljq.instance.isdzp) {
                    return;
                }
                String tmp = s.toString().trim();
                if (currentText.equals("") && !tmp.equals("")) {
                    Fragment_Type_Ljq.instance.ljqxxs.get(position).setDzpxx(tmp);
                    finalHolder.duanzx.setBackground(context.getResources().getDrawable(R.drawable.device_detials_bg));
                    /**
                     * 必填项校验设计
                     */
                    boolean isShow = false;
                    for (LJQXX ljqxx : list) {
                        if (Fragment_Type_Ljq.instance.isjkyt && (ljqxx == null || ljqxx.getJkyt() == null || ljqxx.getJkyt().equals(""))) {
                            isShow = true;
                        } else if (Fragment_Type_Ljq.instance.isqfrq && (ljqxx == null || ljqxx.getJtzjqfrq() == null || ljqxx.getJtzjqfrq().equals(""))) {
                            isShow = true;
                        } else if (Fragment_Type_Ljq.instance.isdzp && (ljqxx == null || ljqxx.getDzpxx() == null || ljqxx.getDzpxx().equals(""))) {
                            isShow = true;
                        }
                    }
                    if (isShow) {
                        DemoActivity.instance.check("连接器信息", isShow);
                    } else {
//                        Fragment_Type_Base.instance.checkBtx(isEdit,false,isShow,
//                                Fragment_Type_Ljq.instance.map_key,Fragment_Type_Ljq.instance.map_more,"连接器信息");
                        DemoActivity.instance.check("连接器信息", Fragment_Type_Ljq.instance.check);
                    }
                } else if (!currentText.equals("") && tmp.equals("")) {
                    finalHolder.duanzx.setBackground(context.getResources().getDrawable(R.drawable.device_detials_bg2));
                    /**
                     * 必填项校验设计
                     */
//                    Fragment_Type_Ljq.instance.check = true;
                    DemoActivity.instance.check("连接器信息", true);
                }
                currentText = tmp;
            }
        });
        holder.qianfeng.addTextChangedListener(new TextWatcher() {
            String currentText;

            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after) {
                currentText = str.toString().trim();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!Fragment_Type_Ljq.instance.isqfrq) {
                    return;
                }
                String tmp = s.toString().trim();
                if (currentText.equals("") && !tmp.equals("")) {
                    finalHolder.qianfeng.setBackground(context.getResources().getDrawable(R.drawable.device_detials_bg));
                    /**
                     * 必填项校验设计
                     */
                    boolean isShow = false;
                    for (LJQXX ljqxx : list) {
                        if (Fragment_Type_Ljq.instance.isjkyt && (ljqxx == null || ljqxx.getJkyt() == null || ljqxx.getJkyt().equals(""))) {
                            isShow = true;
                        } else if (Fragment_Type_Ljq.instance.isqfrq && (ljqxx == null || ljqxx.getJtzjqfrq() == null || ljqxx.getJtzjqfrq().equals(""))) {
                            isShow = true;
                        } else if (Fragment_Type_Ljq.instance.isdzp && (ljqxx == null || ljqxx.getDzpxx() == null || ljqxx.getDzpxx().equals(""))) {
                            isShow = true;
                        }
                    }
                    if (isShow) {
                        DemoActivity.instance.check("连接器信息", isShow);
                    } else {
                        DemoActivity.instance.check("连接器信息", Fragment_Type_Ljq.instance.check);
                    }
                } else if (!currentText.equals("") && tmp.equals("")) {
                    finalHolder.qianfeng.setBackground(context.getResources().getDrawable(R.drawable.device_detials_bg2));
                    /**
                     * 必填项校验设计
                     */
//                    Fragment_Type_Ljq.instance.check = true;
                    DemoActivity.instance.check("连接器信息", true);
                }
                currentText = tmp;
            }
        });
        holder.yongtu.addTextChangedListener(new TextWatcher() {
            String currentText;

            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after) {
                currentText = str.toString().trim();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!Fragment_Type_Ljq.instance.isjkyt) {
                    return;
                }
                String tmp = s.toString().trim();
                if (currentText.equals("") && !tmp.equals("")) {
                    finalHolder.yongtu.setBackground(context.getResources().getDrawable(R.drawable.device_detials_bg));
                    /**
                     * 必填项校验设计
                     */
                    boolean isShow = false;
                    for (LJQXX ljqxx : list) {
                        if (Fragment_Type_Ljq.instance.isjkyt && (ljqxx == null || ljqxx.getJkyt() == null || ljqxx.getJkyt().equals(""))) {
                            isShow = true;
                        } else if (Fragment_Type_Ljq.instance.isqfrq && (ljqxx == null || ljqxx.getJtzjqfrq() == null || ljqxx.getJtzjqfrq().equals(""))) {
                            isShow = true;
                        } else if (Fragment_Type_Ljq.instance.isdzp && (ljqxx == null || ljqxx.getDzpxx() == null || ljqxx.getDzpxx().equals(""))) {
                            isShow = true;
                        }
                    }
                    if (isShow) {
                        DemoActivity.instance.check("连接器信息", isShow);
                    } else {
                        DemoActivity.instance.check("连接器信息", Fragment_Type_Ljq.instance.check);
                    }
                } else if (!currentText.equals("") && tmp.equals("")) {
                    finalHolder.yongtu.setBackground(context.getResources().getDrawable(R.drawable.device_detials_bg2));
                    /**
                     * 必填项校验设计
                     */
//                    Fragment_Type_Ljq.instance.check = true;
                    DemoActivity.instance.check("连接器信息", true);
                }
                currentText = tmp;
            }
        });

        if (Fragment_Type_Ljq.instance.is_Edit) {
            holder.yongtu.setBackgroundResource(R.drawable.device_detials_bg);
            holder.yongtu.setEnabled(true);
            holder.qianfeng.setBackgroundResource(R.drawable.device_detials_bg);
            holder.qianfeng.setEnabled(true);
            holder.duanzx.setBackgroundResource(R.drawable.device_detials_bg);
            holder.duanzx.setEnabled(true);

            Fragment_Type_Inset.instance.setDrawableLeft(holder.yongtu_tv, false);
            Fragment_Type_Inset.instance.setDrawableLeft(holder.qianfeng_tv, false);
            Fragment_Type_Inset.instance.setDrawableLeft(holder.duanzx_tv, false);
            //待补充，型号，编号，制造厂家，接口类型（默认带出，但属于校核项）。
            if (Fragment_Type_Ljq.instance.isjkyt && (holder.yongtu.getText().toString() + "").equals("")) {
                holder.yongtu.setBackgroundResource(R.drawable.device_detials_bg2);
                holder.yongtu.setHint("必填项");
            }
            if (Fragment_Type_Ljq.instance.isqfrq && (holder.qianfeng.getText().toString() + "").equals("")) {
                holder.qianfeng.setBackgroundResource(R.drawable.device_detials_bg2);
                holder.qianfeng.setHint("必填项");
            }
            if (Fragment_Type_Ljq.instance.isdzp && (holder.duanzx.getText().toString() + "").equals("")) {
                holder.duanzx.setBackgroundResource(R.drawable.device_detials_bg2);
                holder.duanzx.setHint("必填项");
            }
        } else {
            holder.yongtu.setBackground(null);
            holder.yongtu.setEnabled(false);

            holder.qianfeng.setBackground(null);
            holder.qianfeng.setEnabled(false);

            holder.duanzx.setBackground(null);
            holder.duanzx.setEnabled(false);

            Fragment_Type_Inset.instance.setDrawableLeft(holder.yongtu_tv, false);
            Fragment_Type_Inset.instance.setDrawableLeft(holder.qianfeng_tv, false);
            Fragment_Type_Inset.instance.setDrawableLeft(holder.duanzx_tv, false);

            if (Fragment_Type_Ljq.instance.isjkyt && (holder.yongtu.getText().toString() + "").equals("")) {
                Fragment_Type_Inset.instance.setDrawableLeft(holder.yongtu_tv, true);
            }
            if (Fragment_Type_Ljq.instance.isqfrq && (holder.qianfeng.getText().toString() + "").equals("")) {
                Fragment_Type_Inset.instance.setDrawableLeft(holder.qianfeng_tv, true);
            }
            if (Fragment_Type_Ljq.instance.isdzp && (holder.duanzx.getText().toString() + "").equals("")) {
                Fragment_Type_Inset.instance.setDrawableLeft(holder.duanzx_tv, true);
            }
        }

        boolean isShow = false;
        for (LJQXX ljqxx : list) {
            if (Fragment_Type_Ljq.instance.isjkyt && (ljqxx == null || ljqxx.getJkyt() == null || ljqxx.getJkyt().equals(""))) {
                isShow = true;
            } else if (Fragment_Type_Ljq.instance.isqfrq && (ljqxx == null || ljqxx.getJtzjqfrq() == null || ljqxx.getJtzjqfrq().equals(""))) {
                isShow = true;
            } else if (Fragment_Type_Ljq.instance.isdzp && (ljqxx == null || ljqxx.getDzpxx() == null || ljqxx.getDzpxx().equals(""))) {
                isShow = true;
            }
        }
        if (isShow) {
            DemoActivity.instance.check("连接器信息", isShow);
        } else {
            DemoActivity.instance.check("连接器信息", Fragment_Type_Ljq.instance.check);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView duanzixiang, bianhao, changjia, xinghao, leixing, qianfeng, yongtu;
        TextView duanzx_tv, qianfeng_tv, yongtu_tv;
        EditText duanzx;

        public ViewHolder(View convertView) {

        }
    }

    //响应按钮点击事件,调用子定义接口，并传入View
    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }
}