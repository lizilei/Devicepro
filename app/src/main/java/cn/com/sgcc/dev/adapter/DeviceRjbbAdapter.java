package cn.com.sgcc.dev.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
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
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Base;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_More_Mk;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details1;
import cn.com.sgcc.dev.view.fragment.fzbhsb.Details11;
import cn.com.sgcc.dev.view.viewinterface.DateChooseListener;

/**
 * <p>@description:装置基本信息软件版本</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/1/9
 */

public class DeviceRjbbAdapter extends BaseAdapter {
    private List<BHXHRJBB> list;
    private Context context;
    private boolean isEdit;
    private boolean ismk;
    private boolean isShow;
    private int del_size;

    /**
     * 标志是否用户输入或刷新
     */
    private boolean isRefresh;

    private int position;
    private String mkmc;
    private String bb;

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
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

    public DeviceRjbbAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<BHXHRJBB> list, boolean isEdit, boolean ismk) {
        this.list = list;
        this.isEdit = isEdit;
        this.ismk = ismk;
//        isRefresh = true;
        notifyDataSetChanged();
        System.out.println("调用了Adapter刷新方法。。。。");

//        isRefresh = false;
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
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_rjbb, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final BHXHRJBB item = list.get(position);
        String mkmc = (item.getMkmc() + "").equalsIgnoreCase("null") ? "" : item.getMkmc();
        String rjbb = (item.getBb() + "").equalsIgnoreCase("null") ? "" : item.getBb();
        String jym = (item.getJym() + "").equalsIgnoreCase("null") ? "" : item.getJym();
        String scsj = (item.getSCSJ() + "").equalsIgnoreCase("null") ? "" : item.getSCSJ();

        for (EditText editText : holder.editTexts) {
            if (editText.getTag() instanceof TextWatcher) {
                editText.removeTextChangedListener((TextWatcher) editText.getTag());
            }
        }
        if (holder.tvRjbbscsj.getTag() instanceof TextWatcher) {
            holder.tvRjbbscsj.removeTextChangedListener((TextWatcher) holder.tvRjbbscsj.getTag());
        }

        holder.editTexts.get(0).setText(mkmc);
        holder.editTexts.get(1).setText(rjbb);
        holder.editTexts.get(2).setText(jym);
        holder.tvRjbbscsj.setText(scsj);

        isShow = false;

        if (isEdit) {
            for (TextView textView : holder.textViews) {
                textView.setCompoundDrawables(null, null, null, null);
            }
            for (EditText editText : holder.editTexts) {
                if (editText.getText().toString().trim().equals("")) {
                    editText.setBackgroundResource(R.drawable.device_detials_bg2);
                    editText.setHint("必填项");
                    isShow = true;
                } else {
                    editText.setBackgroundResource(R.drawable.device_detials_bg);
                }
                editText.setEnabled(true);
            }
            for (ImageView imgView : holder.imgViews) {
                imgView.setVisibility(View.VISIBLE);
            }

            if (Details1.instance.map_key.containsKey("生成时间") || Details1.instance.map_key.containsKey("生成时间-分模块")) {
                if (holder.tvRjbbscsj.getText().toString().trim().equals("")) {
                    holder.tvRjbbscsj.setBackgroundResource(R.drawable.device_detials_bg2);
                    isShow = true;
                } else {
                    holder.tvRjbbscsj.setBackgroundResource(R.drawable.device_detials_bg);
                }
            } else {
                holder.tvRjbbscsj.setBackgroundResource(R.drawable.device_detials_bg);
            }
            holder.tvRjbbscsj.setEnabled(true);
            holder.btnCore.setEnabled(true);
            holder.btnCore.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < holder.editTexts.size(); i++) {
                holder.editTexts.get(i).setBackground(null);
                holder.editTexts.get(i).setEnabled(false);
                if (holder.editTexts.get(i).getText().toString().trim().equals("")) {
                    Drawable drawable = context.getResources().getDrawable(R.drawable.tanhao);
                    drawable.setBounds(0, 0, 25, 25);
                    holder.textViews.get(i).setCompoundDrawables(drawable, null, null, null);
                    isShow = true;
                } else {
                    holder.textViews.get(i).setCompoundDrawables(null, null, null, null);
                }
            }

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
            if (Details1.instance.map_key.containsKey("生成时间") || Details1.instance.map_key.containsKey("生成时间-分模块")) {
                if (holder.tvRjbbscsj.getText().toString().trim().equals("")) {
                    Drawable drawable = context.getResources().getDrawable(R.drawable.tanhao);
                    drawable.setBounds(0, 0, 25, 25);
                    holder.textViews.get(3).setCompoundDrawables(drawable, null, null, null);
                    isShow = true;
                } else {
                    holder.textViews.get(3).setCompoundDrawables(null, null, null, null);
                }
            }
        }

        DemoActivity.instance.checkFZ("装置基本信息-分模块", isShow);

        for (EditText editText : holder.editTexts) {
            TextWatcher watcher = getTextWatcher(editText, false);
            editText.addTextChangedListener(watcher);
            editText.setTag(watcher);
        }

        TextWatcher watcher = getTextWatcher(holder.tvRjbbscsj, true);
        holder.tvRjbbscsj.addTextChangedListener(watcher);
        holder.tvRjbbscsj.setTag(watcher);

        if (position == 0) {
            holder.btnCore.setText("添加+");
        } else {
            holder.btnCore.setText("取消-");
        }

        holder.btnCore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.btnCore.getText().toString().equals("添加+")) {
                    if (list.size() == 5 && !ismk) {
                        if (Details1.instance.rl_more_mk.getVisibility() == View.GONE) {
                            Details1.instance.rl_more_mk.setVisibility(View.VISIBLE);
                        }
                        DemoActivity.instance.showMk(1);
                        ToastUtils.showToast((Activity) context, "跳转更多模块信息去操作");
                        return;
                    }
                    if (list.size() >= 5 && !ismk) {
//                        ToastUtils.showToast((Activity) context, "您只能添加" + list.size() + "条...");
                        return;
                    }
                    BHXHRJBB bhxhrjbb = new BHXHRJBB();
                    bhxhrjbb.setBblx("非六统一，分模块");
                    bhxhrjbb.setED_TAG("C");
                    bhxhrjbb.setSfqy("Y");
                    list.add(bhxhrjbb);
                } else {
                    if (list.size() == 5 && !ismk && Details1.instance.rl_more_mk.getVisibility() == View.VISIBLE) {
//                        DemoActivity.instance.showMk(1);
//                        ToastUtils.showToast((Activity) context, "跳转更多模块信息去操作");
//                        if (Details1.instance.rl_more_mk.getVisibility() == View.GONE) {
//                            Details1.instance.rl_more_mk.setVisibility(View.VISIBLE);
//                        }
                        Details11.instance.list_one_data.remove(position);
                        list.remove(position);
                        list.add(Details11.instance.list_one_data.get(4));
                        Details11.instance.updataMoreMk();
                        return;
                    } else if (ismk && list.size() == 6) {
                        if (Details1.instance.rl_more_mk.getVisibility() == View.VISIBLE) {
                            Details1.instance.rl_more_mk.setVisibility(View.GONE);
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
                        holder.tvRjbbscsj.setText(date);
                        list.get(position).setSCSJ(date);
                    }
                });
            }
        });

        holder.imgViews.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPosition(position);
                if (ismk) {
                    Details11.instance.setIntentData("模块名称", 1);
                } else {
                    Details1.instance.setIntentData("模块名称", 1);
                }
            }
        });
        holder.imgViews.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPosition(position);
                setMkmc(item.getMkmc());
                if (ismk) {
                    Details11.instance.setIntentData("软件版本", 1);
                } else {
                    Details1.instance.setIntentData("软件版本", 1);
                }
            }
        });
        holder.imgViews.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPosition(position);
                setMkmc(item.getMkmc());
                setBb(item.getBb());
                if (ismk) {
                    Details11.instance.setIntentData("校验码", 1);
                } else {
                    Details1.instance.setIntentData("校验码", 1);
                }
            }
        });
        holder.imgViews.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.showDatePickDialog(context, DateType.TYPE_YMD, new DateChooseListener() {
                    @Override
                    public void onDateChooseListener(String date) {
                        holder.tvRjbbscsj.setText(date);
                        list.get(position).setSCSJ(date);
                    }
                });
            }
        });

        for (int i = 0; i < holder.editTexts.size(); i++) {
            final int finalI = i;
            holder.editTexts.get(i).setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        return convertView;
    }

    /**
     * 获取TextWatcher
     *
     * @param editText
     * @param isSCSJ
     * @return
     */
    public TextWatcher getTextWatcher(final EditText editText, final boolean isSCSJ) {
        return new TextWatcher() {
            String currentText;

            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after) {
                currentText = str.toString().trim();
            }

            @Override
            public void onTextChanged(CharSequence str, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable str) {
                System.out.println("调用了输入框内容改变回调方法。。。。");
                if (isSCSJ) {
                    if (Details1.instance.map_key.containsKey("生成时间") || Details1.instance.map_key.containsKey("生成时间-分模块")) {
                        String tmp = str.toString().trim();
                        if (tmp.equals("") && !currentText.equals("")) {
                            editText.setBackgroundResource(R.drawable.device_detials_bg2);
                            editText.setHint("必填项");
                            isShow = true;
                            DemoActivity.instance.checkFZ("装置基本信息-分模块", isShow);
                        } else if (currentText.equals("") && !tmp.equals("")) {
                            editText.setBackgroundResource(R.drawable.device_detials_bg);
                            isShow = false;
                            DemoActivity.instance.checkFZ("装置基本信息-分模块", isShow);
                        }
                    }
                } else {
                    String tmp = str.toString().trim();
                    if (tmp.equals("") && !currentText.equals("")) {
                        editText.setBackgroundResource(R.drawable.device_detials_bg2);
                        editText.setHint("必填项");
                        isShow = true;
                        DemoActivity.instance.checkFZ("装置基本信息-分模块", isShow);
                    } else if (currentText.equals("") && !tmp.equals("")) {
                        editText.setBackgroundResource(R.drawable.device_detials_bg);
                        isShow = false;
                        DemoActivity.instance.checkFZ("装置基本信息-分模块", isShow);
                    }
                }
            }
        };
    }

    class ViewHolder {
        @BindViews(value = {R.id.tv_mkmc, R.id.tv_rjbb, R.id.tv_jym})
        List<EditText> editTexts;
        @BindViews(value = {R.id.iv_mkmc, R.id.iv_rjbb, R.id.iv_jym, R.id.iv_rjbbscsj})
        List<ImageView> imgViews;
        @BindView(R.id.tv_rjbbscsj)
        EditText tvRjbbscsj;
        @BindView(R.id.btn_core)
        Button btnCore;
        @BindViews(value = {R.id.mkmc, R.id.rjbb, R.id.jym, R.id.rjbbscsj})
        List<TextView> textViews;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
