//package cn.com.sgcc.dev.model;
//
//import org.greenrobot.greendao.annotation.Convert;
//import org.greenrobot.greendao.annotation.Entity;
//import org.greenrobot.greendao.annotation.Id;
//import org.greenrobot.greendao.annotation.Property;
//
//import java.io.Serializable;
//import java.sql.Timestamp;
//
//import org.greenrobot.greendao.annotation.Generated;
//
///**
// * <p>@description:软件版本信息</p>
// * 表名：SG_PRO_DEV_VER
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//
//@Entity(nameInDb = "SG_PRO_DEV_VER")
//public class RJBB_SG_PRO_DEV_VER implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;          // 唯一标识ID
//    @Property(nameInDb = "DEV_ID")
//    private String DEV_ID;      // 装置ID
//    @Property(nameInDb = "VER_ID")
//    private String VER_ID;      // 软件版本ID
//    @Property(nameInDb = "EXIT_REASION")
//    private String EXIT_REASION;// 升级原因
//    @Property(nameInDb = "EXIT_TIME")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp EXIT_TIME;   // 升级时间
//    @Property(nameInDb = "FAG")
//    private int FAG;         // 数据标识
//    @Property(nameInDb = "CHECK_STATUS")
//    private int CHECK_STATUS;// 审核状态
//    @Property(nameInDb = "DATA_UNIT")
//    private String DATA_UNIT;   // 数据报送单位
//    @Generated(hash = 737093684)
//    public RJBB_SG_PRO_DEV_VER(String ID, String DEV_ID, String VER_ID,
//            String EXIT_REASION, Timestamp EXIT_TIME, int FAG, int CHECK_STATUS,
//            String DATA_UNIT) {
//        this.ID = ID;
//        this.DEV_ID = DEV_ID;
//        this.VER_ID = VER_ID;
//        this.EXIT_REASION = EXIT_REASION;
//        this.EXIT_TIME = EXIT_TIME;
//        this.FAG = FAG;
//        this.CHECK_STATUS = CHECK_STATUS;
//        this.DATA_UNIT = DATA_UNIT;
//    }
//    @Generated(hash = 1161844584)
//    public RJBB_SG_PRO_DEV_VER() {
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
//    public String getVER_ID() {
//        return this.VER_ID;
//    }
//    public void setVER_ID(String VER_ID) {
//        this.VER_ID = VER_ID;
//    }
//    public String getEXIT_REASION() {
//        return this.EXIT_REASION;
//    }
//    public void setEXIT_REASION(String EXIT_REASION) {
//        this.EXIT_REASION = EXIT_REASION;
//    }
//    public Timestamp getEXIT_TIME() {
//        return this.EXIT_TIME;
//    }
//    public void setEXIT_TIME(Timestamp EXIT_TIME) {
//        this.EXIT_TIME = EXIT_TIME;
//    }
//    public int getFAG() {
//        return this.FAG;
//    }
//    public void setFAG(int FAG) {
//        this.FAG = FAG;
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
//}
