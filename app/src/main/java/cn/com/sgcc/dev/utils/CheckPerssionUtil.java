package cn.com.sgcc.dev.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

/**
 * <p>@description:检查权限</p>
 *
 * @author lizilei
 * @Version 1.0
 * @Email lizilei_warms@163.com
 * @since 2019-09-05
 */
public class CheckPerssionUtil {

    private static CheckPerssionUtil checkPerssionUtil;
    private static Context mContext;

    public static CheckPerssionUtil getInstance(Context context) {
        if (checkPerssionUtil == null) {
            synchronized (CheckPerssionUtil.class) {
                if (checkPerssionUtil == null) {
                    checkPerssionUtil = new CheckPerssionUtil();
                }
            }
        }
        mContext=context;
        return checkPerssionUtil;
    }




    /**
     * 判断是否具有权限
     *
     * @param permissions 权限列表
     * @return true:全部都具有
     */
    public boolean hasPermissions(@NonNull String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(mContext, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 永久拒绝提示框
     */
    public void deniedEverDialog(String message, String positive, String negative) {
        new AlertDialog.Builder(mContext).setMessage(message)
                .setPositiveButton(positive, (dialog, which) -> gotoSettings())
                .setNegativeButton(negative, (dialog, which) -> dialog.dismiss()).show();
    }

    /**
     * 跳转到系统的应用信息页面
     */
    public void gotoSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", AppsUtils.getPackageName(mContext), null);
        intent.setData(uri);
        mContext.startActivity(intent);
    }
}
