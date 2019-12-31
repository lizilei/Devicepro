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
// * <p>@description:装置开关信息</p>
// * 表名：SG_PRO_DEV_BREAKER
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//@Entity(nameInDb = "SG_PRO_DEV_BREAKER")
//public class ZZKGXX_SG_PRO_DEV_BREAKER implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;            // 唯一标识ID
//    @Property(nameInDb = "DEV_ID")
//    private String DEV_ID;        // 装置ID
//    @Property(nameInDb = "PROTECTOR_TYPE")
//    private int PROTECTOR_TYPE;// 装置类别
//    @Property(nameInDb = "CODE")
//    private String CODE;          // 开关编号
//    @Property(nameInDb = "CHECK_STATUS")
//    private int CHECK_STATUS;  // 审核状态
//    @Property(nameInDb = "DATA_UNIT")
//    private String DATA_UNIT;     // 数据报送单位
//    @Property(nameInDb = "FAG")
//    private int FAG;           // 数据标识
//    @Generated(hash = 635650953)
//    public ZZKGXX_SG_PRO_DEV_BREAKER(String ID, String DEV_ID, int PROTECTOR_TYPE,
//            String CODE, int CHECK_STATUS, String DATA_UNIT, int FAG) {
//        this.ID = ID;
//        this.DEV_ID = DEV_ID;
//        this.PROTECTOR_TYPE = PROTECTOR_TYPE;
//        this.CODE = CODE;
//        this.CHECK_STATUS = CHECK_STATUS;
//        this.DATA_UNIT = DATA_UNIT;
//        this.FAG = FAG;
//    }
//    @Generated(hash = 2026582159)
//    public ZZKGXX_SG_PRO_DEV_BREAKER() {
//    }
//    public String getID() {
//        return this.ID;
//    }
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//    public String getDEV_ID() {
//        return this.DEV_ID;
//    }
//    public void setDEV_ID(String DEV_ID) {
//        this.DEV_ID = DEV_ID;
//    }
//    public int getPROTECTOR_TYPE() {
//        return this.PROTECTOR_TYPE;
//    }
//    public void setPROTECTOR_TYPE(int PROTECTOR_TYPE) {
//        this.PROTECTOR_TYPE = PROTECTOR_TYPE;
//    }
//    public String getCODE() {
//        return this.CODE;
//    }
//    public void setCODE(String CODE) {
//        this.CODE = CODE;
//    }
//    public int getCHECK_STATUS() {
//        return this.CHECK_STATUS;
//    }
//    public void setCHECK_STATUS(int CHECK_STATUS) {
//        this.CHECK_STATUS = CHECK_STATUS;
//    }
//    public String getDATA_UNIT() {
//        return this.DATA_UNIT;
//    }
//    public void setDATA_UNIT(String DATA_UNIT) {
//        this.DATA_UNIT = DATA_UNIT;
//    }
//    public int getFAG() {
//        return this.FAG;
//    }
//    public void setFAG(int FAG) {
//        this.FAG = FAG;
//    }
//}
