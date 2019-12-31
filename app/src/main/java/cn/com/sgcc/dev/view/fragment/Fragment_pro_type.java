package cn.com.sgcc.dev.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.GoodsAttrsAdapter;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.ToastUtils;

/**
 * <p>@description:</p>
 *
 * 校验设置内容选择区域
 * @author lxf
 * @version 1.0.0
 */
public class Fragment_pro_type extends Fragment {
    private GridView listGridView;
    private String typename;
    private GoodsAttrsAdapter serviceAdapter;
    private List<SaleAttributeVo> serviceList;
    private List<SaleAttributeVo> serviceList2;
    private List<SaleAttributeNameVo> data;
    public static Fragment_pro_type instance = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pro_type, null);
        listGridView = (GridView) view.findViewById(R.id.listView);
        instance = this;

        typename=getArguments().getString("typename");
        data=(List<SaleAttributeNameVo>)getArguments().getSerializable("jiaoYanData");
        serviceList = new ArrayList<SaleAttributeVo>();

        for(int i=0;i<data.size();i++){
            if (data.get(i).getName().equals(typename)){
                serviceList=data.get(i).getSaleVo();
                break;
            }
        }

        serviceAdapter = new GoodsAttrsAdapter(getActivity());
        listGridView.setAdapter(serviceAdapter);
        serviceAdapter.notifyDataSetChanged(true, serviceList);
        listGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                data.get(position).getSaleVo().get(arg2).
//                        setChecked(!data.get(position).getSaleVo().get(arg2).isChecked());
//                adapter.notifyDataSetChanged(data.get(position).isNameChecked(),
//                        data.get(position).getSaleVo());
                if (serviceList.get(arg2).getValue().equals("实物ID")||serviceList.get(arg2).getValue().equals("装置名称")
                        ||serviceList.get(arg2).getValue().equals("是否六统一")||serviceList.get(arg2).getValue().equals("六统一标准版本")
                        ||serviceList.get(arg2).getValue().equals("制造厂家")||serviceList.get(arg2).getValue().equals("装置类别")
                        ||serviceList.get(arg2).getValue().equals("装置型号")||serviceList.get(arg2).getValue().equals("模块名称")
                        ||serviceList.get(arg2).getValue().equals("软件版本")||serviceList.get(arg2).getValue().equals("校验码")
                        ||serviceList.get(arg2).getValue().equals("装置类别细化")||serviceList.get(arg2).getValue().equals("设备类型")
                        ||serviceList.get(arg2).getValue().equals("故障录波器类型")||serviceList.get(arg2).getValue().equals("测距形式")
                        ||serviceList.get(arg2).getValue().equals("装置分类")||serviceList.get(arg2).getValue().equals("装置类型")
                        ||serviceList.get(arg2).getValue().equals("一次设备类型")||serviceList.get(arg2).getValue().equals("一次设备名称")
                        ||serviceList.get(arg2).getValue().equals("电压等级")||serviceList.get(arg2).getValue().equals("设备状态")
                        ||serviceList.get(arg2).getValue().equals("单位名称")||serviceList.get(arg2).getValue().equals("定期检验周期")
                        ||serviceList.get(arg2).getValue().equals("退运日期")||serviceList.get(arg2).getValue().equals("通道类型")
                        ||serviceList.get(arg2).getValue().equals("安控系统调度命名")||serviceList.get(arg2).getValue().equals("安控站点类型")
                        ||serviceList.get(arg2).getValue().equals("ICD文件名称")){
                    ToastUtils.showLongToast(getActivity(),"默认校验项,不可取消");
                }else{
                    serviceList.get(arg2).setChecked(!serviceList.get(arg2).isChecked());
                }
                serviceAdapter.notifyDataSetChanged(true,serviceList);

            }
        });
        return view;
    }

}
