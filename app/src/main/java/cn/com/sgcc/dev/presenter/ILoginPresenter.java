package cn.com.sgcc.dev.presenter;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/16
 */

public interface ILoginPresenter {

    void remember(String name, String password);
    void doLogin(String name,String password);
    void setProgressBarVisibity(int visibity);
}
