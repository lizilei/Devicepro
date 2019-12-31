package cn.com.sgcc.dev.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.BkAdapter;
import cn.com.sgcc.dev.adapter.DeviceOneAdapter;
import cn.com.sgcc.dev.adapter.DeviceSelectAdapter;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.BKXX;
import cn.com.sgcc.dev.model2.BZSJ;
import cn.com.sgcc.dev.utils.DeviceDateilsUtilsTwo;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

/**
 * <p>@description:</p>
 *   板卡界面
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */

public class BkFragment extends BaseFragment {
    List<BKXX> bkxxs = new ArrayList<>();
    private ListView fragment_lv_bk;
    private BkAdapter adapter;
    IDaoUtil util;

    public static BkFragment instance = null;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_bk;
    }

    @Override
    public void initview() {
        //ToastUtils.showToast(getActivity(),"板卡信息initview调用了");
        fragment_lv_bk = (ListView) getActivity().findViewById(R.id.fragment_lv_bk);
        util= new DaoUtil(getActivity());
    }

    @Override
    public void initEvevt() {
    }

    //过滤要显示的板卡
    public Boolean isAdd(BKXX bkxx){
        Boolean yy=false; //原因
        if (bkxx.getBkbgyy()==null||"".equals(bkxx.getBkbgyy())){
            yy=true;
        }else{
            yy=false;
        }
        Boolean sj=false; //原因
        if (bkxx.getBgsj()==null||"".equals(bkxx.getBgsj())){
            sj=true;
        }else{
            sj=false;
        }

        //sb是非D或者变更时间和变更原因都为null时,返回true, 加载
        if (bkxx.getSb()!=null){
            if (!bkxx.getSb().equals("D")&&yy&&sj){
                return true;
            }else{
                return false;
            }
        }else if(bkxx.getSb()==null&&yy&&sj){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void initData() {
        //ToastUtils.showLongToast(getActivity(),"板卡信息initData调用了");
        bkxxs.clear();

        //如果是新增直接返回,若是编辑则继续*********************************
        if (DeviceDetailsActivity.state.equals("C")){
            //判断是否是扫码进入的编辑********************************************************
            if (DeviceDetailsActivity.isFromSaoma){
                List<BKXX> bkListxx=(List<BKXX>)getActivity().getIntent().getSerializableExtra("BKXX");
                if (DeviceDetailsActivity.type.equals("BHSB")){ //保护
                    bkxxs.clear();
                    if (bkListxx.size()>0){
                        for (int i= 0;i<bkListxx.size();i++){
                            BKXX bkxx=(BKXX)bkListxx.get(i);
                            if (isAdd(bkxx)){
                                bkxxs.add(bkxx);
                            }
                        }
                    }else{
                        //ToastUtils.showToast(getActivity(),"无板卡信息");
                    }
                }else if (DeviceDetailsActivity.type.equals("FZSB")){
                    bkxxs.clear();
                    if (bkListxx.size()>0){
                        for (int i= 0;i<bkListxx.size();i++){
                            BKXX bkxx=(BKXX)bkListxx.get(i);
                            if (isAdd(bkxx)){
                                bkxxs.add(bkxx);
                            }
                        }
                    }else{
                        //ToastUtils.showToast(getActivity(),"无板卡信息");
                    }
                }
            }else{
                //ToastUtils.showToast(getActivity(),"无板卡信息");
            }
        }else{
                if (DeviceDetailsActivity.type.equals("BHSB")){ //保护
                    bkxxs.clear();
                    List<Object> bkList = util.getICDOrBKXX(BKXX.class, DeviceDetailsActivity.bhpz.getId()+"" ,"BHPZ");
                    bkList.toString();
                    if (bkList.size()>0){
                        for (int i= 0;i<bkList.size();i++){
                            BKXX bkxx=(BKXX)bkList.get(i);
                            if (isAdd(bkxx)){
                                bkxxs.add(bkxx);
                            }
                        }
                    }else{
                        //ToastUtils.showToast(getActivity(),"无板卡信息");
                    }
                }else if (DeviceDetailsActivity.type.equals("FZSB")){
                    bkxxs.clear();
                    List<Object> bkList = util.getICDOrBKXX(BKXX.class, DeviceDetailsActivity.fzbhsb.getId()+"" ,"FZBHSB");
                    bkList.toString();
                    if (bkList.size()>0){
                        for (int i= 0;i<bkList.size();i++){
                            BKXX bkxx=(BKXX)bkList.get(i);
                            if (isAdd(bkxx)){
                                bkxxs.add(bkxx);
                            }
                        }
                    }else{
                        //ToastUtils.showToast(getActivity(),"无板卡信息");
                    }
                }
        }

        adapter = new BkAdapter(getActivity(),bkxxs);
        fragment_lv_bk.setAdapter(adapter);
    }

    @Override
    public void initReceiver(boolean isEdit) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
