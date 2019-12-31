package cn.com.sgcc.dev.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.IcdAdapter;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.BKXX;
import cn.com.sgcc.dev.model2.ICDXX;
import cn.com.sgcc.dev.model2.LTYSBXH;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

/**
 * <p>@description:</p>
 * ICD界面
 *
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */

public class IcdFragment extends BaseFragment {
    private ListView fragment_lv_icd;
    public  List<ICDXX> icdxxs ;
    IDaoUtil util;
    private IcdAdapter adapter;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_icd;
    }

    @Override
    public void initview() {
        icdxxs = new ArrayList<>();
        fragment_lv_icd = (ListView) getActivity().findViewById(R.id.fragment_lv_icd);
        util = new DaoUtil(getActivity());
    }

    @Override
    public void initEvevt() {

    }

    //过滤要显示的ICD
    public Boolean isAdd(ICDXX icdxx){
        Boolean yy=false; //原因
        if (icdxx.getBGYY()==null||"".equals(icdxx.getBGYY())){
            yy=true;
        }else{
            yy=false;
        }

        Boolean sj=false; //原因
        if (icdxx.getBGSJ()==null||"".equals(icdxx.getBGSJ())){
            sj=true;
        }else{
            sj=false;
        }
        Boolean edTag=false; //标记删除
        if (icdxx.getED_TAG()!=null&&icdxx.getED_TAG().equals("D")){
            edTag=false;
        }else{
            edTag=true;
        }

        //sb是非D或者变更时间和变更原因都为null时,返回true, 加载
        if (icdxx.getSB()!=null){
            if (!icdxx.getSB().equals("D")&&yy&&sj&&edTag){
                return true;
            }else{
                return false;
            }
        }else if(icdxx.getSB()==null&&yy&&sj&&edTag){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void initData() {
        //ToastUtils.showToast(getActivity(),"ICDinit");
        icdxxs.clear();
        //判断是否是扫码进入的新增或者直接新增的***********************
        if (DeviceDetailsActivity.state.equals("C")) { //扫码新增只显示从出厂信息库带出的ICD信息,编辑
            ICDXX icdListxx = (ICDXX) getActivity().getIntent().getSerializableExtra("ICDXX");
            if (DeviceDetailsActivity.isFromSaoma) { //因扫码无法带出校验码生成时间,需要去查询六统一设备型号表补充
                if (DeviceDetailsActivity.type.equals("BHSB")) { //保护
                    icdxxs.clear();
                    if (icdListxx != null) {
                        List<Object> ltysbxhList=util.getICDOrBKXX(LTYSBXH.class, DeviceDetailsActivity.instance.code3 + "");
                        if (ltysbxhList.size()>0){

                            LTYSBXH ltysbxh=(LTYSBXH)ltysbxhList.get(0);
                            if (ltysbxh.getJymscsj()!=null){
                                icdListxx.setJYMSCSJ(TimeUtil.formatString(ltysbxh.getJymscsj()));
                                icdxxs.add(icdListxx);
                            }else{
                                icdxxs.add(icdListxx);
                            }
                            ltysbxhList.clear();
                        }
                    } else {
                        //ToastUtils.showToast(getActivity(), "无ICD信息");
                    }
                } else if (DeviceDetailsActivity.type.equals("FZSB")) { //辅助
                    icdxxs.clear();
                    if (icdListxx != null) {
                        List<Object> ltysbxhList=util.getICDOrBKXX(LTYSBXH.class, FZDeviceOneFragment.instance.ltysbxh.getCode() + "");
                        LTYSBXH ltysbxh=(LTYSBXH)ltysbxhList.get(0);
                        if (ltysbxh.getJymscsj()!=null){
                            icdListxx.setJYMSCSJ(TimeUtil.formatString(ltysbxh.getJymscsj()));
                            icdxxs.add(icdListxx);
                        }else{
                            icdxxs.add(icdListxx);
                        }
                        ltysbxhList.clear();

                    } else {
                        //ToastUtils.showToast(getActivity(), "无ICD信息");
                    }
                }
            } else {  //点击新增 因未保存没有生成调接口的code,无法显示
                //ToastUtils.showToast(getActivity(), "无ICD信息");
                }

        } else {
            if (DeviceDetailsActivity.type.equals("BHSB")) { //保护
                icdxxs.clear();
                List<Object> icdList = util.getICDOrBKXX(ICDXX.class, DeviceDetailsActivity.bhpz.getId() + "", "BHPZ");
                icdList.toString();
                if (icdList.size() > 0) {
                    for (int i = 0; i < icdList.size(); i++) {
                        ICDXX icdxx = (ICDXX) icdList.get(i);
                        if (isAdd(icdxx)){
                            icdxxs.add(icdxx);
                        }
                    }
                    icdList.clear();
                } else {
                    //ToastUtils.showToast(getActivity(), "无ICD信息");
                }
            } else if (DeviceDetailsActivity.type.equals("FZSB")) { //辅助
                icdxxs.clear();
                List<Object> icdList = util.getICDOrBKXX(ICDXX.class, DeviceDetailsActivity.fzbhsb.getId() + "", "FZBHSB");
                icdList.toString();
                if (icdList.size() > 0) {
                    for (int i = 0; i < icdList.size(); i++) {
                        ICDXX icdxx = (ICDXX) icdList.get(i);
                        if (isAdd(icdxx)){
                            icdxxs.add(icdxx);
                        }
                    }
                    icdList.clear();
                } else {
                    //ToastUtils.showToast(getActivity(), "无ICD信息");
                }
            }
        }
        adapter = new IcdAdapter(getActivity(), icdxxs);
        fragment_lv_icd.setAdapter(adapter);
    }

    @Override
    public void initReceiver(boolean isEdit) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}




