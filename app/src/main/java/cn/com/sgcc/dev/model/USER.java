//package cn.com.sgcc.dev.model;
//
//import org.greenrobot.greendao.annotation.Entity;
//import org.greenrobot.greendao.annotation.Id;
//import org.greenrobot.greendao.annotation.Property;
//
//import java.io.Serializable;
//import org.greenrobot.greendao.annotation.Generated;
//
///**
// * <p>@description:用户信息 </p>
// * 表名：SG_USER
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//
//@Entity(nameInDb = "SG_USER")
//public class USER implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private int id;      // 唯一标识
//    @Property(nameInDb = "SSBM")
//    private String ssbm;    // 所属部门
//    @Property(nameInDb = "SSGS")
//    private String ssgs;    // 所属公司
//    @Property(nameInDb = "USER_NAME")
//    private String username;// 用户名
//    @Property(nameInDb = "PASSWORD")
//    private String password;// 密码
//    @Generated(hash = 815029484)
//    public USER(int id, String ssbm, String ssgs, String username,
//            String password) {
//        this.id = id;
//        this.ssbm = ssbm;
//        this.ssgs = ssgs;
//        this.username = username;
//        this.password = password;
//    }
//    @Generated(hash = 1257176873)
//    public USER() {
//    }
//    public int getId() {
//        return this.id;
//    }
//    public void setId(int id) {
//        this.id = id;
//    }
//    public String getSsbm() {
//        return this.ssbm;
//    }
//    public void setSsbm(String ssbm) {
//        this.ssbm = ssbm;
//    }
//    public String getSsgs() {
//        return this.ssgs;
//    }
//    public void setSsgs(String ssgs) {
//        this.ssgs = ssgs;
//    }
//    public String getUsername() {
//        return this.username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    public String getPassword() {
//        return this.password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//}
