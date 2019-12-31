package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <p>@description:记录在线登录时用户名和密码</p>
 *
 * @author lizilei
 * @version V1.0
 * @Email lizilei_warms@163.com
 * @since 2019/6/28 0028 上午 10:51
 */

@Entity
public class UserInfo {
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    @Property(nameInDb = "USERNAME")
    private String username;
    @Property(nameInDb = "PASSWORD")
    private String password;
    @Property(nameInDb = "NAMEINTF")
    private String nameInTf;  //在统分中的用户名
    @Property(nameInDb = "SSGS")
    private String ssgs; //所属公司
    @Generated(hash = 911985629)
    public UserInfo(Long id, String username, String password, String nameInTf,
            String ssgs) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nameInTf = nameInTf;
        this.ssgs = ssgs;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNameInTf() {
        return this.nameInTf;
    }
    public void setNameInTf(String nameInTf) {
        this.nameInTf = nameInTf;
    }
    public String getSsgs() {
        return this.ssgs;
    }
    public void setSsgs(String ssgs) {
        this.ssgs = ssgs;
    }
}
