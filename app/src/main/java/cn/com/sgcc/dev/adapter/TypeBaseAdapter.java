package cn.com.sgcc.dev.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.datapick.widget.bean.DateType;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Base;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_More_Mk;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Road_Ak;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details1;
import cn.com.sgcc.dev.view.viewinterface.DateChooseListener;

/**
 * <p>@description:装置基本信息软件版本</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/1/9
 */

public class TypeBaseAdapter extends BaseAdapter {
    private List<BHXHRJBB> list;
    private Context context;
    private boolean isEdit;
    private boolean isdmk;
    private int del_size;

    private int position;
    private String mkmc;
    private String bb;
    private boolean isShow;

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public String getMkmc() {
        return mkmc;
    }

    public void setMkmc(String mkmc) {
        this.mkmc = mkmc;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public TypeBaseAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<BHXHRJBB> list, boolean isEdit, boolean isdmk) {
        this.list = list;
        this.isEdit = isEdit;
        this.isdmk = isdmk;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_rjbb_bh, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        isShow = false;

        final BHXHRJBB item = list.get(position);

        holder.editTexts.get(0).setText((item.getMkmc() + "").equalsIgnoreCase("null") ? "" : item.getMkmc());
        holder.editTexts.get(1).setText((item.getBb() + "").equalsIgnoreCase("null") ? "" : item.getBb());
        holder.editTexts.get(2).setText((item.getJym() + "").equalsIgnoreCase("null") ? "" : item.getJym());
        holder.tvRjbbscsj.setText((item.getSCSJ() + "").equalsIgnoreCase("null") ? "" : item.getSCSJ());

        if (isEdit) {
            for (EditText editText : holder.editTexts) {
                editText.setBackgroundResource(R.drawable.device_detials_bg);
                editText.setEnabled(true);
            }
            for (ImageView imgView : holder.imgViews) {
                imgView.setVisibility(View.VISIBLE);
            }
            holder.tvRjbbscsj.setBackgroundResource(R.drawable.device_detials_bg);
            holder.tvRjbbscsj.setEnabled(true);
            holder.btnCore.setEnabled(true);

            holder.btnCore.setVisibility(View.VISIBLE);

            for (TextView textView : holder.textViews_check) {
                Fragment_Type_Base.instance.setDrawableLeft(textView, false);
            }
            if (Fragment_Type_Base.instance.bt_mkmc && (holder.editTexts.get(0).getText().toString() + "").equals("")) {
                holder.editTexts.get(0).setBackgroundResource(R.drawable.device_detials_bg2);
                isShow = true;
            }
            if (Fragment_Type_Base.instance.bt_rjbb && (holder.editTexts.get(1).getText().toString() + "").equals("")) {
                holder.editTexts.get(1).setBackgroundResource(R.drawable.device_detials_bg2);
                isShow = true;
            }
            if (Fragment_Type_Base.instance.bt_jym && (holder.editTexts.get(2).getText().toString()).equals("")) {
                holder.editTexts.get(2).setBackgroundResource(R.drawable.device_detials_bg2);
                holder.editTexts.get(2).setHint("必填项");
                isShow = true;
            }
            if (Fragment_Type_Base.instance.bt_scsj && (holder.tvRjbbscsj.getText().toString()).equals("")) {
                holder.tvRjbbscsj.setBackgroundResource(R.drawable.device_detials_bg2);
                holder.tvRjbbscsj.setHint("必填项");
                isShow = true;
            }
            for (BHXHRJBB bhxhrjbb : list) {
                if (bhxhrjbb==null||bhxhrjbb.getMkmc()==null||bhxhrjbb.getMkmc().equals("")) {
                    isShow = true;
                } else if (bhxhrjbb==null||bhxhrjbb.getBb()==null||bhxhrjbb.getBb().equals("")) {
                    isShow = true;
                } else if (bhxhrjbb==null||bhxhrjbb.getJym()==null||bhxhrjbb.getJym().equals("")) {
                    isShow = true;
                } else if (Fragment_Type_Base.instance.bt_scsj &&bhxhrjbb==null
                        ||Fragment_Type_Base.instance.bt_scsj &&bhxhrjbb.getSCSJ()==null
                        ||Fragment_Type_Base.instance.bt_scsj && bhxhrjbb.getSCSJ().equals("")) {
                    isShow = true;
                }
            }
        } else {
            for (EditText editText : holder.editTexts) {
                editText.setBackground(null);
                editText.setEnabled(false);
            }
            for (ImageView imgView : holder.imgViews) {
                imgView.setVisibility(View.GONE);
            }
            holder.tvRjbbscsj.setBackground(null);
            holder.tvRjbbscsj.setEnabled(false);
            holder.btnCore.setEnabled(false);
            holder.btnCore.setVisibility(View.GONE);

            for (TextView textView : holder.textViews_check) {
                Fragment_Type_Base.instance.setDrawableLeft(textView, false);
            }
            if (Fragment_Type_Base.instance.bt_mkmc && (holder.editTexts.get(0).getText().toString() + "").equals("")) {
                Fragment_Type_Base.instance.setDrawableLeft(holder.textViews_check.get(0), true);
                isShow = true;
            }
            if (Fragment_Type_Base.instance.bt_rjbb && (holder.editTexts.get(1).getText().toString() + "").equals("")) {
                Fragment_Type_Base.instance.setDrawableLeft(holder.textViews_check.get(1), true);
                isShow = true;
            }
            if (Fragment_Type_Base.instance.bt_jym && (holder.editTexts.get(2).getText().toString()).equals("")) {
                Fragment_Type_Base.instance.setDrawableLeft(holder.textViews_check.get(2), true);
                isShow = true;
            }
            if (Fragment_Type_Base.instance.bt_scsj && (holder.tvRjbbscsj.getText().toString()).equals("")) {
                Fragment_Type_Base.instance.setDrawableLeft(holder.textViews_check.get(3), true);
                isShow = true;
            }
            for (BHXHRJBB bhxhrjbb : list) {
                if (bhxhrjbb==null||bhxhrjbb.getMkmc()==null||bhxhrjbb.getMkmc().equals("")) {
                    isShow = true;
                } else if (bhxhrjbb==null||bhxhrjbb.getBb()==null||bhxhrjbb.getBb().equals("")) {
                    isShow = true;
                } else if (bhxhrjbb==null||bhxhrjbb.getJym()==null||bhxhrjbb.getJym().equals("")) {
                    isShow = true;
                } else if (Fragment_Type_Base.instance.bt_scsj &&bhxhrjbb==null
                        ||Fragment_Type_Base.instance.bt_scsj &&bhxhrjbb.getSCSJ()==null
                        ||Fragment_Type_Base.instance.bt_scsj && bhxhrjbb.getSCSJ().equals("")) {
                    isShow = true;
                }
            }
        }
        if (isShow) {
            DemoActivity.instance.check("装置基本信息", isShow);
        } else if (Fragment_Type_Base.instance.check) {
            DemoActivity.instance.check("装置基本信息", Fragment_Type_Base.instance.check);
        } else {
            DemoActivity.instance.check("装置基本信息", false);
        }

        if (position == 0) {
            holder.btnCore.setText("添加+");
        } else {
            holder.btnCore.setText("取消-");
        }

        holder.btnCore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.btnCore.getText().toString().equals("添加+")) {
                    if (list.size() == 5 && !isdmk) {
                        DemoActivity.instance.showMk(1);
                        ToastUtils.showToast((Activity) context, "跳转更多模块信息去操作");
                        if (Fragment_Type_Base.instance.rl_more_mk.getVisibility() == View.GONE) {
                            Fragment_Type_Base.instance.rl_more_mk.setVisibility(View.VISIBLE);
                        }
                        return;
                    }
                    if (list.size() >= 5 && !isdmk) {
//                        ToastUtils.showToast((Activity) context, "您只能添加" + list.size() + "条...");
                        return;
                    }
                    BHXHRJBB bhxhrjbb = new BHXHRJBB();
                    bhxhrjbb.setBblx("非六统一，分模块");
                    bhxhrjbb.setED_TAG("C");
                    bhxhrjbb.setSfqy("Y");
                    list.add(bhxhrjbb);
//                    if (isdmk){
//                        Fragment_Type_More_Mk.instance.setPosition();
//                    }else {
//                        Fragment_Type_Base.instance.setPosition();
//                    }
                } else {
                    if (list.size() == 5 && !isdmk && Fragment_Type_Base.instance.rl_more_mk.getVisibility() == View.VISIBLE) {
//                        DemoActivity.instance.showMk(1);
//                        ToastUtils.showToast((Activity) context, "跳转更多模块信息去操作");
//                        if (Fragment_Type_Base.instance.rl_more_mk.getVisibility() == View.GONE) {
//                            Fragment_Type_Base.instance.rl_more_mk.setVisibility(View.VISIBLE);
//                        }
//                        return;
                        Fragment_Type_More_Mk.instance.list_one_data.remove(position);
                        list.remove(position);
                        list.add(Fragment_Type_More_Mk.instance.list_one_data.get(4));
                        Fragment_Type_Base.instance.updataMoreMk();
                        return;
                    } else if (isdmk && list.size() == 6) {
                        if (Fragment_Type_Base.instance.rl_more_mk.getVisibility() == View.VISIBLE) {
                            Fragment_Type_Base.instance.rl_more_mk.setVisibility(View.GONE);
                        }
                    }
                    list.remove(position);
                }
                notifyDataSetChanged();
            }
        });

        holder.tvRjbbscsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.showDatePickDialog(context, DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        list.get(position).setSCSJ(date);
                        holder.tvRjbbscsj.setText(date);
                    }
                });
            }
        });

        holder.imgViews.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPosition(position);
                if (isdmk) {
                    Fragment_Type_More_Mk.instance.setIntentData("模块名称", position);
                } else {
                    Fragment_Type_Base.instance.setIntentData("模块名称", position);
                }
            }
        });
        holder.imgViews.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPosition(position);
                setMkmc(item.getMkmc());
                if (isdmk) {
                    Fragment_Type_More_Mk.instance.setIntentData("软件版本", position);
                } else {
                    Fragment_Type_Base.instance.setIntentData("软件版本", position);
                }
            }
        });
        holder.imgViews.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPosition(position);
                setMkmc(item.getMkmc());
                setBb(item.getBb());
                if (isdmk) {
                    Fragment_Type_More_Mk.instance.setIntentData("校验码", position);
                } else {
                    Fragment_Type_Base.instance.setIntentData("校验码", position);
                }
            }
        });
        holder.imgViews.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.showDatePickDialog(context, DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        list.get(position).setSCSJ(date);
                        holder.tvRjbbscsj.setText(date);
                    }
                });
            }
        });

        for (int i = 0; i < holder.editTexts.size(); i++) {
            final int finalI = i;
            holder.editTexts.get(finalI).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus && del_size == list.size()) {
                        if (finalI == 0 && position < list.size()) {
                            list.get(position).setMkmc(holder.editTexts.get(0).getText().toString());
                        }
                        if (finalI == 1 && position < list.size()) {
                            list.get(position).setBb(holder.editTexts.get(1).getText().toString());
                        }
                        if (finalI == 2 && position < list.size()) {
                            list.get(position).setJym(holder.editTexts.get(2).getText().toString());
                        }
                    }
                    if (del_size != list.size()) {
                        del_size = list.size();
                    }
                }
            });
        }
        for (int i = 0; i < holder.editTexts.size(); i++) {
            final int finalI = i;
            holder.editTexts.get(finalI).addTextChangedListener(new TextWatcher() {
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
                    if (finalI==3&&!Fragment_Type_Base.instance.bt_scsj){
                        //生成时间不必填返回
                        return;
                    }
                    String tmp = s.toString().trim();
                    if (currentText.equals("") && !tmp.equals("")) {
                        holder.editTexts.get(finalI).setBackground(context.getResources().getDrawable(R.drawable.device_detials_bg));
                        /**
                         * 必填项校验设计
                         */
                        isShow = false;
                        for (BHXHRJBB bhxhrjbb : list) {
                            if (bhxhrjbb==null||bhxhrjbb.getMkmc()==null||bhxhrjbb.getMkmc().equals("")) {
                                isShow = true;
                            } else if (bhxhrjbb==null||bhxhrjbb.getBb()==null||bhxhrjbb.getBb().equals("")) {
                                isShow = true;
                            } else if (bhxhrjbb==null||bhxhrjbb.getJym()==null||bhxhrjbb.getJym().equals("")) {
                                isShow = true;
                            } else if (Fragment_Type_Base.instance.bt_scsj &&bhxhrjbb==null
                                    ||Fragment_Type_Base.instance.bt_scsj &&bhxhrjbb.getSCSJ()==null
                                    ||Fragment_Type_Base.instance.bt_scsj && bhxhrjbb.getSCSJ().equals("")) {
                                isShow = true;
                            }
                        }

                        if (isShow) {
                            DemoActivity.instance.check("装置基本信息", isShow);
                        } else if (Fragment_Type_Base.instance.check) {
                            DemoActivity.instance.check("装置基本信息", Fragment_Type_Base.instance.check);
                        } else {
                            DemoActivity.instance.check("装置基本信息", false);
                        }
                    } else if (!currentText.equals("") && tmp.equals("")) {
                        holder.editTexts.get(finalI).setBackground(context.getResources().getDrawable(R.drawable.device_detials_bg2));
                        /**
                         * 必填项校验设计
                         */
                        isShow = true;
                        DemoActivity.instance.check("装置基本信息", isShow);
                    }
                    currentText = tmp;
                }
            });
        }

        holder.tvRjbbscsj.addTextChangedListener(new TextWatcher() {
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
                if (!Fragment_Type_Base.instance.bt_scsj){
                    //生成时间不必填返回
                    return;
                }
                String tmp = s.toString().trim();
                if (currentText.equals("") && !tmp.equals("")) {
                    holder.tvRjbbscsj.setBackground(context.getResources().getDrawable(R.drawable.device_detials_bg));
                    /**
                     * 必填项校验设计
                     */
                    isShow = false;
                    for (BHXHRJBB bhxhrjbb : list) {
                        if (bhxhrjbb==null||bhxhrjbb.getMkmc()==null||bhxhrjbb.getMkmc().equals("")) {
                            isShow = true;
                        } else if (bhxhrjbb==null||bhxhrjbb.getBb()==null||bhxhrjbb.getBb().equals("")) {
                            isShow = true;
                        } else if (bhxhrjbb==null||bhxhrjbb.getJym()==null||bhxhrjbb.getJym().equals("")) {
                            isShow = true;
                        } else if (Fragment_Type_Base.instance.bt_scsj &&bhxhrjbb==null
                                ||Fragment_Type_Base.instance.bt_scsj &&bhxhrjbb.getSCSJ()==null
                                ||Fragment_Type_Base.instance.bt_scsj && bhxhrjbb.getSCSJ().equals("")) {
                            isShow = true;
                        }
                    }

                    if (isShow) {
                        DemoActivity.instance.check("装置基本信息", isShow);
                    } else if (Fragment_Type_Base.instance.check) {
                        DemoActivity.instance.check("装置基本信息", Fragment_Type_Base.instance.check);
                    } else {
                        DemoActivity.instance.check("装置基本信息", false);
                    }
                } else if (!currentText.equals("") && tmp.equals("")) {
                    holder.tvRjbbscsj.setBackground(context.getResources().getDrawable(R.drawable.device_detials_bg2));
                    /**
                     * 必填项校验设计
                     */
                    isShow = true;
                    DemoActivity.instance.check("装置基本信息", isShow);
                }
                currentText = tmp;
            }
        });

        return convertView;
    }

    class ViewHolder {
        @BindViews(value = {R.id.tv_mkmc, R.id.tv_rjbb, R.id.tv_jym})
        List<EditText> editTexts;
        @BindViews(value = {R.id.tv_mk, R.id.tv_bb, R.id.tv_jyms, R.id.tv_scsj})
        List<TextView> textViews_check;
        @BindViews(value = {R.id.iv_mkmc, R.id.iv_rjbb, R.id.iv_jym, R.id.iv_rjbbscsj})
        List<ImageView> imgViews;
        @BindView(R.id.tv_rjbbscsj)
        TextView tvRjbbscsj;
        @BindView(R.id.btn_core)
        Button btnCore;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
