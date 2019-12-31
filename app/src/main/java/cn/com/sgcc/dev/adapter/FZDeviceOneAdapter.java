package cn.com.sgcc.dev.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.model.DeviceDetailsNameItem;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.utils.DeviceDateilsUtils;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.fragment.FZDeviceOneFragment;

/**
 * <p>@description:</p>
 * 辅助设备详情一，适配器
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/31
 */
public class FZDeviceOneAdapter extends BaseAdapter {
    private List<DeviceDetailsNameItem> list;
    private Context context;
    public int add_rjbb_count = 1;
    private List<String> item;

    public FZDeviceOneAdapter(Context context, List<DeviceDetailsNameItem> list) {
        this.list = list;
        this.context = context;
    }

    public void setDatas(List<DeviceDetailsNameItem> list) {
        this.list = list;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_fz_one_item, null);
            holder = new ViewHolder();
            holder.ll_fz_one_type = (LinearLayout) convertView.findViewById(R.id.ll_fz_one_type);
            holder.ll_fz_two_type = (LinearLayout) convertView.findViewById(R.id.ll_fz_two_type);
            holder.rl_fz_first_two = (RelativeLayout) convertView.findViewById(R.id.rl_fz_first_two);
            holder.rl_fz_second_two = (RelativeLayout) convertView.findViewById(R.id.rl_fz_second_two);

            holder.fz_one_type_name = (TextView) convertView.findViewById(R.id.fz_one_type_name);
            holder.fz_two_type_name = (TextView) convertView.findViewById(R.id.fz_two_type_name);
            holder.fz_three_type_name = (TextView) convertView.findViewById(R.id.fz_three_type_name);
            holder.fz_four_type_name = (TextView) convertView.findViewById(R.id.fz_four_type_name);

            holder.fz_one_type_choose = (Button) convertView.findViewById(R.id.fz_one_type_choose);
            holder.fz_two_type_choose = (Button) convertView.findViewById(R.id.fz_two_type_choose);
            holder.fz_three_type_choose = (Button) convertView.findViewById(R.id.fz_three_type_choose);
            holder.fz_four_type_choose = (Button) convertView.findViewById(R.id.fz_four_type_choose);
            holder.fz_btn_add = (Button) convertView.findViewById(R.id.fz_btn_add);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        item = new ArrayList<>();
        if (list.get(position).getNum() == 1) {
            holder.fz_btn_add.setVisibility(View.GONE);
            holder.ll_fz_one_type.setVisibility(View.VISIBLE);
            holder.ll_fz_two_type.setVisibility(View.GONE);
            holder.fz_two_type_name.setVisibility(View.GONE);
            holder.fz_two_type_choose.setVisibility(View.GONE);

            if (list.get(position).getName_one().equals("保护名称") ||
                    list.get(position).getName_one().equals("是否支持采用IEC61850上送交换机信息")) {
                holder.fz_one_type_choose.setMaxWidth(600);
                holder.rl_fz_first_two.setVisibility(View.GONE);
            } else {
                holder.fz_one_type_choose.setMaxWidth(220);
                holder.rl_fz_first_two.setVisibility(View.VISIBLE);
            }

            holder.fz_one_type_name.setText(list.get(position).getName_one());
            holder.fz_one_type_choose.setText(list.get(position).getContent_one());

            holder.fz_one_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getName_one().equals("保护名称") && list.get(position).getContent_one().equals("")
                            || list.get(position).getContent_one().equals("必填项，点击生成")) {
                        String czmc = FZDeviceOneFragment.instance.czmc;
                        String dydj = FZDeviceOneFragment.instance.dydj;
                        String ycsbmc = FZDeviceOneFragment.instance.ycsbmc;
                        String bhtb = "";
                        if (!FZDeviceOneFragment.instance.bhtb.equals("请选择")) {
                            bhtb = FZDeviceOneFragment.instance.bhtb;
                        }
                        String bhxh = FZDeviceOneFragment.instance.bhxh;
                        String bhlb = FZDeviceOneFragment.instance.bhlb;
                        if (czmc.equals("") || czmc.equals("NULL")) {
                            ToastUtils.showToast((Activity) context, "当前厂站名称为空，无法自动生成");
                            return;
                        }
                        if (dydj.equals("") || dydj.equals("必填项")) {
                            ToastUtils.showToast((Activity) context, "当前电压等级为空，无法自动生成");
                            return;
                        }
                        if (ycsbmc.equals("") || ycsbmc.equals("NULL") || ycsbmc.equals("必填项")) {
                            ToastUtils.showToast((Activity) context, "当前一次设备名称为空，无法自动生成");
                            return;
                        }
                        if (bhxh.equals("") || bhxh.equals("NULL") || bhxh.equals("必填项")) {
                            ToastUtils.showToast((Activity) context, "当前保护型号为空，无法自动生成");
                            return;
                        }
                        if (bhlb.equals("") || bhlb.equals("NULL") || bhlb.equals("必填项")) {
                            ToastUtils.showToast((Activity) context, "当前保护类别为空，无法自动生成");
                            return;
                        }
                        FZDeviceOneFragment.instance.bhmc = czmc + dydj + "kV" + ycsbmc + bhtb + bhxh + bhlb;
                        list.get(position).setContent_one(FZDeviceOneFragment.instance.bhmc);
                        holder.fz_one_type_choose.setText(FZDeviceOneFragment.instance.bhmc);
                    } else {
                        //匹配处理，查询数据库
                        item.clear();
                        item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_one() + "");
                        FZDeviceOneFragment.instance.showDialog(1, position, item);
                    }
                }
            });
        } else if (list.get(position).getNum() == 2) {
            holder.fz_btn_add.setVisibility(View.GONE);
            holder.ll_fz_one_type.setVisibility(View.VISIBLE);
            holder.ll_fz_two_type.setVisibility(View.GONE);
            holder.rl_fz_first_two.setVisibility(View.VISIBLE);
            holder.fz_two_type_name.setVisibility(View.VISIBLE);
            holder.fz_two_type_choose.setVisibility(View.VISIBLE);

            holder.fz_one_type_name.setText(list.get(position).getName_one());
            holder.fz_one_type_choose.setText(list.get(position).getContent_one());
            holder.fz_two_type_name.setText(list.get(position).getName_two());
            holder.fz_two_type_choose.setText(list.get(position).getContent_two());
            holder.fz_one_type_choose.setMaxWidth(220);

            holder.fz_one_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_one() + "");
                    FZDeviceOneFragment.instance.showDialog(1, position, item);
                }
            });
            holder.fz_two_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getName_two().equals("生成日期")) {
                        FZDeviceOneFragment.instance.showDateDialog(position);
                    } else {
                        item.clear();
                        item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_two() + "");
                        FZDeviceOneFragment.instance.showDialog(2, position, item);
                    }
                }
            });
        } else if (list.get(position).getNum() == 3) {
            holder.ll_fz_one_type.setVisibility(View.VISIBLE);
            holder.ll_fz_two_type.setVisibility(View.VISIBLE);
            holder.rl_fz_first_two.setVisibility(View.VISIBLE);
            holder.fz_two_type_name.setVisibility(View.VISIBLE);
            holder.fz_two_type_choose.setVisibility(View.VISIBLE);
            if (FZDeviceOneFragment.instance.isSix &&
                    FZDeviceOneFragment.instance.is2013 &&
                    list.get(position).getName_three().equals("是否使用国调标准型号")) {
                holder.ll_fz_two_type.setVisibility(View.GONE);
            } else {
                holder.ll_fz_two_type.setVisibility(View.VISIBLE);
            }

            holder.fz_one_type_name.setText(list.get(position).getName_one());
            holder.fz_one_type_choose.setText(list.get(position).getContent_one());
            holder.fz_one_type_choose.setMaxWidth(220);
            holder.fz_two_type_name.setText(list.get(position).getName_two());
            holder.fz_two_type_choose.setText(list.get(position).getContent_two());
            holder.fz_three_type_name.setText(list.get(position).getName_three());
            holder.fz_three_type_choose.setText(list.get(position).getContent_three());

            if (list.get(position).isHaveAdd()) {
                holder.fz_four_type_name.setVisibility(View.GONE);
                holder.fz_four_type_choose.setVisibility(View.GONE);
                holder.fz_btn_add.setVisibility(View.VISIBLE);
                holder.fz_btn_add.setText(list.get(position).getContent_four());
            } else {
                holder.fz_four_type_name.setVisibility(View.GONE);
                holder.fz_four_type_choose.setVisibility(View.GONE);
                holder.fz_btn_add.setVisibility(View.GONE);
            }

            holder.fz_one_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_one() + "");
                    FZDeviceOneFragment.instance.showDialog(1, position, item);
                }
            });
            holder.fz_two_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getName_two().equals("生成日期")) {
                        FZDeviceOneFragment.instance.showDateDialog(position);
                    } else {
                        item.clear();
                        if (list.get(position).getName_two().equals("软件版本")) {
                            item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_two() + "", list.get(position).getContent_one());
                        } else {
                            item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_two() + "");
                        }
                        FZDeviceOneFragment.instance.showDialog(2, position, item);
                    }
                }
            });
            holder.fz_three_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getName_three().equals("生成日期")) {
                        FZDeviceOneFragment.instance.showDateDialog(position);
                    } else {
                        item.clear();
                        item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_three() + "");
                        FZDeviceOneFragment.instance.showDialog(3, position, item);
                    }
                }
            });
            holder.fz_btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getContent_four().equals("添加+")) {
                        if (add_rjbb_count == Constants.rjbb_cout) {
                            ToastUtils.showToast((Activity) context, "您只能添加" + add_rjbb_count + "条...");
                            return;
                        }
                        DeviceDetailsNameItem item1 = new DeviceDetailsNameItem();
                        item1.setNum(3);
                        item1.setName_one("模块名称");
                        item1.setContent_one("请选择");
                        item1.setName_two("软件版本");
                        item1.setContent_two("请选择");
                        item1.setName_three("生成日期");
                        item1.setContent_three("请选择");
                        item1.setContent_four("取消-");
                        item1.setHaveAdd(true);
                        FZDeviceOneFragment.instance.data_name.add(position + 1, item1);
                        BHXHRJBB rjbb = new BHXHRJBB();
                        rjbb.setBblx("非六统一，分模块");
                        rjbb.setED_TAG("C");
                        if (FZDeviceOneFragment.instance.usegddata != null) {
                            rjbb.setSfqy(FZDeviceOneFragment.instance.usegddata.equals("是") ? "Y" : "N");
                        }
                        rjbb.setCode(FZDeviceOneFragment.instance.selectCode);
                        FZDeviceOneFragment.instance.rjbbList.add(1, rjbb);
                        notifyDataSetChanged();
                        add_rjbb_count++;
                    } else {
                        FZDeviceOneFragment.instance.data_name.remove(position);
                        FZDeviceOneFragment.instance.rjbbList.remove(
                                add_rjbb_count + FZDeviceOneFragment.instance.addTypeNume + position - getCount());
                        notifyDataSetChanged();
                        add_rjbb_count--;
                    }
                }
            });

        } else if (list.get(position).getNum() == 4) {
            holder.fz_btn_add.setVisibility(View.GONE);
            holder.ll_fz_one_type.setVisibility(View.VISIBLE);
            holder.ll_fz_two_type.setVisibility(View.VISIBLE);
            holder.rl_fz_first_two.setVisibility(View.VISIBLE);
            holder.fz_two_type_name.setVisibility(View.VISIBLE);
            holder.fz_two_type_choose.setVisibility(View.VISIBLE);
            holder.fz_four_type_name.setVisibility(View.VISIBLE);
            holder.fz_four_type_choose.setVisibility(View.VISIBLE);

            holder.fz_one_type_name.setText(list.get(position).getName_one());
            holder.fz_one_type_choose.setText(list.get(position).getContent_one());
            holder.fz_one_type_choose.setMaxWidth(220);
            holder.fz_two_type_name.setText(list.get(position).getName_two());
            holder.fz_two_type_choose.setText(list.get(position).getContent_two());
            holder.fz_three_type_name.setText(list.get(position).getName_three());
            holder.fz_three_type_choose.setText(list.get(position).getContent_three());
            holder.fz_four_type_name.setText(list.get(position).getName_four());
            holder.fz_four_type_choose.setText(list.get(position).getContent_four());

            holder.fz_one_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_one() + "");
                    FZDeviceOneFragment.instance.showDialog(1, position, item);
                }
            });
            holder.fz_two_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_two() + "");
                    FZDeviceOneFragment.instance.showDialog(2, position, item);
                }
            });
            holder.fz_three_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_three() + "");
                    FZDeviceOneFragment.instance.showDialog(3, position, item);
                }
            });
            holder.fz_four_type_choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.clear();
                    item = DeviceDateilsUtils.getFZDateilsFind(context, list.get(position).getName_four() + "");
                    FZDeviceOneFragment.instance.showDialog(4, position, item);
                }
            });
        }

        return convertView;
    }

    static class ViewHolder {
        LinearLayout ll_fz_one_type;
        LinearLayout ll_fz_two_type;
        RelativeLayout rl_fz_first_two;
        RelativeLayout rl_fz_second_two;

        TextView fz_one_type_name;
        Button fz_one_type_choose;
        TextView fz_two_type_name;
        Button fz_two_type_choose;
        TextView fz_three_type_name;
        Button fz_three_type_choose;
        TextView fz_four_type_name;
        Button fz_four_type_choose;

        Button fz_btn_add;
    }
}