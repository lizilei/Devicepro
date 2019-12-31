package cn.com.sgcc.dev.model2.regist;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version V1.0
 * @Email lizilei_warms@163.com
 * @since 2019/6/28 0028 上午 11:37
 */

public class UserBean {
    /**
     * userAccount : 当前登录的统分账号
     * realName : 真实姓名
     * orgName : 所属单位
     */

    private String userAccount;
    private String realName;
    private String orgName;

    public UserBean() {
    }

    public UserBean(String userAccount, String realName, String orgName) {
        this.userAccount = userAccount;
        this.realName = realName;
        this.orgName = orgName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return "inforBean{" +
                "userAccount='" + userAccount + '\'' +
                ", realName='" + realName + '\'' +
                ", orgName='" + orgName + '\'' +
                '}';
    }
}
