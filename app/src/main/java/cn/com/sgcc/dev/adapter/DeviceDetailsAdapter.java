package cn.com.sgcc.dev.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cn.com.sgcc.dev.utils.DeviceDFragmentFactory;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

/**
 * <p>@description:</p>
 *   设备详情主界面适配器
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class DeviceDetailsAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    private String[] buyTicketTitle;

    public DeviceDetailsAdapter(FragmentManager fm, String[] buyTicketTitle, DeviceDetailsActivity deviceDetailsActivity) {
        super(fm);
        this.buyTicketTitle = buyTicketTitle;
        mContext = deviceDetailsActivity;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fm = DeviceDFragmentFactory.getInstance().getFragment(position);
        return fm;
    }

    @Override
    public int getCount() {
        return buyTicketTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return buyTicketTitle[position];
    }
}
