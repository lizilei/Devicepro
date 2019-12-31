//package cn.com.sgcc.dev.model;
//
//import org.greenrobot.greendao.annotation.Entity;
//import org.greenrobot.greendao.annotation.Id;
//import org.greenrobot.greendao.annotation.Property;
//
//import java.io.Serializable;
//
//import org.greenrobot.greendao.annotation.Generated;
//
///**
// * <p>@description:安控系统</p>
// * 表名：SG_PRT_SAFETYSITE
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//@Entity(nameInDb = "SG_PRT_SAFETYSITE")
//public class AKXT_SG_PRT_SAFETYSITE implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;          // 系统ID
//    @Property(nameInDb = "NAME")
//    private String NAME;        // 安控系统名
//    @Property(nameInDb = "AREA_ID")
//    private String AREA_ID;     // 所属调度单位
//    @Property(nameInDb = "VOLTAGE_TYPE")
//    private int VOLTAGE_TYPE;// 安控系统电压等级
//    @Property(nameInDb = "COMMENT")
//    private String COMMENT;     // 备注
//    @Property(nameInDb = "STAMP")
//    private String STAMP;       // 更新标志
//    @Property(nameInDb = "OWNER")
//    private String OWNER;       // 拥有者
//    @Property(nameInDb = "CHECK_STATUS")
//    private int CHECK_STATUS;// 审核状态
//    @Property(nameInDb = "DATA_UNIT")
//    private String DATA_UNIT;   // 数据报送单位
//
//    @Generated(hash = 758824978)
//    public AKXT_SG_PRT_SAFETYSITE(String ID, String NAME, String AREA_ID,
//                                  int VOLTAGE_TYPE, String COMMENT, String STAMP, String OWNER,
//                                  int CHECK_STATUS, String DATA_UNIT) {
//        this.ID = ID;
//        this.NAME = NAME;
//        this.AREA_ID = AREA_ID;
//        this.VOLTAGE_TYPE = VOLTAGE_TYPE;
//        this.COMMENT = COMMENT;
//        this.STAMP = STAMP;
//        this.OWNER = OWNER;
//        this.CHECK_STATUS = CHECK_STATUS;
//        this.DATA_UNIT = DATA_UNIT;
//    }
//
//    @Generated(hash = 985853579)
//    public AKXT_SG_PRT_SAFETYSITE() {
//    }
//
//    public String getID() {
//        return this.ID;
//    }
//
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//
//    public String getNAME() {
//        return this.NAME;
//    }
//
//    public void setNAME(String NAME) {
//        this.NAME = NAME;
//    }
//
//    public String getAREA_ID() {
//        return this.AREA_ID;
//    }
//
//    public void setAREA_ID(String AREA_ID) {
//        this.AREA_ID = AREA_ID;
//    }
//
//    public int getVOLTAGE_TYPE() {
//        return this.VOLTAGE_TYPE;
//    }
//
//    public void setVOLTAGE_TYPE(int VOLTAGE_TYPE) {
//        this.VOLTAGE_TYPE = VOLTAGE_TYPE;
//    }
//
//    public String getCOMMENT() {
//        return this.COMMENT;
//    }
//
//    public void setCOMMENT(String COMMENT) {
//        this.COMMENT = COMMENT;
//    }
//
//    public String getSTAMP() {
//        return this.STAMP;
//    }
//
//    public void setSTAMP(String STAMP) {
//        this.STAMP = STAMP;
//    }
//
//    public String getOWNER() {
//        return this.OWNER;
//    }
//
//    public void setOWNER(String OWNER) {
//        this.OWNER = OWNER;
//    }
//
//    public int getCHECK_STATUS() {
//        return this.CHECK_STATUS;
//    }
//
//    public void setCHECK_STATUS(int CHECK_STATUS) {
//        this.CHECK_STATUS = CHECK_STATUS;
//    }
//
//    public String getDATA_UNIT() {
//        return this.DATA_UNIT;
//    }
//
//    public void setDATA_UNIT(String DATA_UNIT) {
//        this.DATA_UNIT = DATA_UNIT;
//    }
//}
