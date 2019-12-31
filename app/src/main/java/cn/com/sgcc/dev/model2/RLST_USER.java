package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * <p>@description:用户表</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/8
 */
@Entity(nameInDb = "RLST_USER", createInDb = false)
public class RLST_USER {
    @Id
    @Property(nameInDb = "ID")
    private int id;
    @Property(nameInDb = "GW")
    private String gw;
    @Property(nameInDb = "ZW")
    private String zw;
    @Property(nameInDb = "GZXZ")
    private String gzxz;
    @Property(nameInDb = "GLTZ")
    private String gltz;
    @Property(nameInDb = "SSBM")
    private String ssbm;
    @Property(nameInDb = "SSGS")
    private String ssgs; //所属公司
    @Property(nameInDb = "USERNAME")
    private String userName;
    @Property(nameInDb = "PASSWORD")
    private String password;
    @Property(nameInDb = "EMAIL")
    private String email;
    @Property(nameInDb = "PHONENUMBER")
    private String phoneNumber;
    @Property(nameInDb = "DWJB")
    private String dwjb; //单位级别
    @Transient
    private String oldpassword;
    @Transient
    private String newpassword;
    @Transient
    private String confimpassword;
    @Transient
    private String sjdw;
    @Transient
    private String ipString;
    @Transient
    private String rolename;
    @Transient
    private String mm;


    @Generated(hash = 595546209)
    public RLST_USER(int id, String gw, String zw, String gzxz, String gltz,
            String ssbm, String ssgs, String userName, String password,
            String email, String phoneNumber, String dwjb) {
        this.id = id;
        this.gw = gw;
        this.zw = zw;
        this.gzxz = gzxz;
        this.gltz = gltz;
        this.ssbm = ssbm;
        this.ssgs = ssgs;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dwjb = dwjb;
    }


    @Generated(hash = 1409970438)
    public RLST_USER() {
    }


    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getGw() {
        return this.gw;
    }


    public void setGw(String gw) {
        this.gw = gw;
    }


    public String getZw() {
        return this.zw;
    }


    public void setZw(String zw) {
        this.zw = zw;
    }


    public String getGzxz() {
        return this.gzxz;
    }


    public void setGzxz(String gzxz) {
        this.gzxz = gzxz;
    }


    public String getGltz() {
        return this.gltz;
    }


    public void setGltz(String gltz) {
        this.gltz = gltz;
    }


    public String getSsbm() {
        return this.ssbm;
    }


    public void setSsbm(String ssbm) {
        this.ssbm = ssbm;
    }


    public String getSsgs() {
        return this.ssgs;
    }


    public void setSsgs(String ssgs) {
        this.ssgs = ssgs;
    }


    public String getUserName() {
        return this.userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getDwjb() {
        return this.dwjb;
    }


    public void setDwjb(String dwjb) {
        this.dwjb = dwjb;
    }

    @Override
    public String toString() {
        return "RLST_USER{" +
                "id=" + id +
                ", gw='" + gw + '\'' +
                ", zw='" + zw + '\'' +
                ", gzxz='" + gzxz + '\'' +
                ", gltz='" + gltz + '\'' +
                ", ssbm='" + ssbm + '\'' +
                ", ssgs='" + ssgs + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dwjb='" + dwjb + '\'' +
                ", oldpassword='" + oldpassword + '\'' +
                ", newpassword='" + newpassword + '\'' +
                ", confimpassword='" + confimpassword + '\'' +
                ", sjdw='" + sjdw + '\'' +
                ", ipString='" + ipString + '\'' +
                ", rolename='" + rolename + '\'' +
                ", mm='" + mm + '\'' +
                '}';
    }
}
