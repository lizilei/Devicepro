package cn.com.sgcc.dev.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zxing.view.ViewfinderView;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

/**
 * <p>@description:</p>
 *   设备详情二级界面，其他详情
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */

public class DeviceOtherFragment extends BaseFragment  {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Button btn_ljq ,btn_bk ,btn_icd;
    public LjqFragment ljq;
    public BkFragment bk;
    public IcdFragment icd;
    public Fragment mContent;
    public static DeviceOtherFragment instance = null;
    public boolean newFlag1=false; //判断连接器界面是否new了
    public boolean icdFlag2=false; //判断ICD界面是否new了

    @Override
    public int getLayoutId() {
        return R.layout.fragment_other_device;
    }

    @Override
    public void initview() {
        //ToastUtils.showLongToast(getActivity(),"initview调用了************************************");
        ljq=new  LjqFragment();
        bk=new  BkFragment();
        icd=new  IcdFragment();

        //只有六统一并且是2013版本才显示ICD
        if (DeviceDetailsActivity.type.equals("BHSB")&&DeviceOneFragment.instance.six_one&&DeviceOneFragment.instance.six_one_details.equals("2013版")){ //保护
            btn_icd.setVisibility(View.VISIBLE);
        }else if (DeviceDetailsActivity.type.equals("FZSB")&&FZDeviceOneFragment.instance.isSix&&FZDeviceOneFragment.instance.ltybzbb.equals("2013版")){
            btn_icd.setVisibility(View.VISIBLE);
        }else{
            btn_icd.setVisibility(View.GONE);
        }

        //只有就地化保护装置才显示连接器
        if(DeviceDetailsActivity.type.equals("BHSB")&& DeviceOneFragment.instance.isLink_device==true){
            setDefaultFragment(ljq);
            newFlag1=true;

            btn_ljq.setBackgroundColor(getResources().getColor(R.color.color_main));  //绿色  ff25978f
            btn_bk.setBackgroundColor(Color.parseColor("#999999")); //灰色
            btn_icd.setBackgroundColor(Color.parseColor("#999999"));
        }else{
            btn_ljq.setVisibility(View.GONE);
            setDefaultFragment(bk);
            newFlag1=false;

            btn_bk.setBackgroundColor(getResources().getColor(R.color.color_main));
            btn_icd.setBackgroundColor(Color.parseColor("#999999"));
        }
    }

    //显示连接器
    public void ljqxianshi(){
        //ToastUtils.showLongToast(getActivity(),"ljqxianshi调用了************************************");
        if(DeviceDetailsActivity.type.equals("BHSB")&& DeviceOneFragment.instance.isLink_device==true ){
            btn_ljq.setVisibility(View.VISIBLE);
            switchContent(ljq);
            newFlag1=true;

            btn_ljq.setBackgroundColor(getResources().getColor(R.color.color_main));
            btn_bk.setBackgroundColor(Color.parseColor("#999999"));
            btn_icd.setBackgroundColor(Color.parseColor("#999999"));

        }else{
            btn_ljq.setVisibility(View.GONE);
            switchContent(bk);
            newFlag1=false;

            btn_bk.setBackgroundColor(getResources().getColor(R.color.color_main));
            btn_icd.setBackgroundColor(Color.parseColor("#999999"));

        }
    }
    //动态显示ICD
    public void icdxianshi(){
        //只有六统一并且是2013版本才显示ICD
        if (DeviceDetailsActivity.type.equals("BHSB")&&DeviceOneFragment.instance.six_one&&DeviceOneFragment.instance.six_one_details.equals("2013版")){ //保护
            btn_icd.setVisibility(View.VISIBLE);
            switchContent(icd);
            icdFlag2=true;
        }else if (DeviceDetailsActivity.type.equals("FZSB")&&FZDeviceOneFragment.instance.isSix&&FZDeviceOneFragment.instance.ltybzbb.equals("2013版")){
            btn_icd.setVisibility(View.VISIBLE);
            switchContent(icd);
            icdFlag2=true;
        }else{
            btn_icd.setVisibility(View.GONE);
            icdFlag2=false;
        }
    }

    // 设置默认显示的fragment
    public void setDefaultFragment(Fragment fragment) {
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.fl_container, fragment).commit();
        mContent = fragment;
    }

    //切换fragment   显示和隐藏
    public void switchContent(Fragment to) {
        if (mContent != to) {
            fm = getFragmentManager();
            ft = fm.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                ft.hide(mContent).add(R.id.fl_container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                ft.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            mContent = to;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        btn_ljq= (Button)rootView.findViewById(R.id.btn_ljq);
        btn_bk= (Button)rootView.findViewById(R.id.btn_bk);
        btn_icd= (Button)rootView.findViewById(R.id.btn_icd);

        btn_ljq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ljq==null){
                    newFlag1=true;
                    fm = getFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.fl_container, ljq);
                    ft.commit();
                }else{
                    switchContent(ljq);
                }

                btn_ljq.setBackgroundColor(getResources().getColor(R.color.color_main));
                btn_bk.setBackgroundColor(Color.parseColor("#999999"));
                btn_icd.setBackgroundColor(Color.parseColor("#999999"));

            }
        });

        btn_bk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bk==null){
                    fm = getFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.fl_container, bk);
                    ft.commit();
                }else{
                    switchContent(bk);
                }

                btn_ljq.setBackgroundColor(Color.parseColor("#999999"));
                btn_bk.setBackgroundColor(getResources().getColor(R.color.color_main));
                btn_icd.setBackgroundColor(Color.parseColor("#999999"));

            }
        });

        btn_icd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (icd==null){
                    icdFlag2=true;
                    fm = getFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.fl_container, icd);
                    ft.commit();
                }else{
                    switchContent(icd);
                }
                btn_ljq.setBackgroundColor(Color.parseColor("#999999"));
                btn_bk.setBackgroundColor(Color.parseColor("#999999"));
                btn_icd.setBackgroundColor(getResources().getColor(R.color.color_main));
            }
        });

        return rootView;
    }

    @Override
    public void initEvevt() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initReceiver(boolean isEdit) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
