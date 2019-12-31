package cn.com.sgcc.dev.view.viewinterface;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @Version 1.0
 * @Email lizilei_warms@163.com
 * @since 2019-09-05
 */
public interface PermissionGrant {
    /**
     * 获取到权限的回调
     * @param requestCode
     */
    void onPermissionGranted(int requestCode);

    /**
     * 权限被永久拒绝的回调，默认给出提示框，并提供前往系统权限修改页面的跳转
     */
    void onPermissionDenied();

    /**
     * 权限被拒绝的回调
     */
    void onPermissionDeniedOnce();
}
