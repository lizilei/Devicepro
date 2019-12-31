package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/8
 */
@Entity(nameInDb = "DLQK",createInDb = false)
public class DLQK {
    @Id
    @Property(nameInDb = "ID")
    private Long id;
    @Property(nameInDb = "LOGINTIME")
    private String logintime;
    @Property(nameInDb = "LOGINIP")
    private String loginip;
    @Property(nameInDb = "USERID")
    private long userid;
    @Property(nameInDb = "USERNAME")
    private String username;
    @Property(nameInDb = "SSGS")
    private String ssgs;
    @Generated(hash = 1627744639)
    public DLQK(Long id, String logintime, String loginip, long userid,
            String username, String ssgs) {
        this.id = id;
        this.logintime = logintime;
        this.loginip = loginip;
        this.userid = userid;
        this.username = username;
        this.ssgs = ssgs;
    }
    @Generated(hash = 611582638)
    public DLQK() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLogintime() {
        return this.logintime;
    }
    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }
    public String getLoginip() {
        return this.loginip;
    }
    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }
    public long getUserid() {
        return this.userid;
    }
    public void setUserid(long userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getSsgs() {
        return this.ssgs;
    }
    public void setSsgs(String ssgs) {
        this.ssgs = ssgs;
    }
}
