package cn.com.sgcc.dev.utils;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;

import cn.com.sgcc.dev.view.fragment.DeviceOtherFragment;
import cn.com.sgcc.dev.view.fragment.FZDeviceOneFragment;
import cn.com.sgcc.dev.view.fragment.FZDeviceTwoFragment;

/**
 * <p>@description:</p>
 * 设备详情主界面，切换界面工厂
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class FZDeviceDFragmentFactory {
    SparseArray<Fragment> mCaches = new SparseArray<Fragment>();

    private static FZDeviceDFragmentFactory sInstance;

    private FZDeviceDFragmentFactory() {
    }

    public static FZDeviceDFragmentFactory getInstance() {
        if (sInstance == null) {
            sInstance = new FZDeviceDFragmentFactory();
        }
        return sInstance;
    }

    public Fragment getFragment(int position) {
        //优先从缓存中取，防止重复
        if (mCaches.get(position) != null) {
            Log.e("DeviceDetailsActivity", "取出fragment");
            return mCaches.get(position);
        }
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FZDeviceOneFragment();
                break;
            case 1:
                fragment = new FZDeviceTwoFragment();
                break;
            case 2:
                fragment = new DeviceOtherFragment();
                break;

        }
        mCaches.put(position, fragment);
        Log.e("DeviceDetailsActivity", "保存fragment");
        return fragment;
    }
}
