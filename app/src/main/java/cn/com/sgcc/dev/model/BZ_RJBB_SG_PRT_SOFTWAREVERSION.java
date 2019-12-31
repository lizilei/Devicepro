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
// * <p>@description:软件版本</p>
// * 表名：SG_PRT_SOFTWAREVERSION
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//@Entity(nameInDb = "SG_PRT_SOFTWAREVERSION")
//public class BZ_RJBB_SG_PRT_SOFTWAREVERSION implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;                        // 软件版本ID
//    @Property(nameInDb = "VER_CODE")
//    private String VER_CODE;                  // 软件版本编码
//    @Property(nameInDb = "MODEL_ID")
//    private String MODEL_ID;                  // 型号ID
//    @Property(nameInDb = "MODULE_NAME")
//    private String MODULE_NAME;               // 模块名称
//    @Property(nameInDb = "VER_NAME")
//    private String VER_NAME;                  // 软件版本
//    @Property(nameInDb = "CHECK_CODE")
//    private String CHECK_CODE;                // 校验码
//    @Property(nameInDb = "COMMENT")
//    private String COMMENT;                   // 备注
//    @Property(nameInDb = "STAMP")
//    private String STAMP;                     // 更新标志
//    @Property(nameInDb = "OWNER")
//    private String OWNER;                     // 拥有者
//    @Property(nameInDb = "APPLY_VOLTAGE")
//    private String APPLY_VOLTAGE;             // 适用电压等级
//    @Property(nameInDb = "APPLY_PROTEECTIONTYPE_CODE")
//    private String APPLY_PROTEECTIONTYPE_CODE;// 适用装置类型
//    @Property(nameInDb = "IS_START_USING")
//    private String IS_START_USING;            // 是否启用
//    @Property(nameInDb = "CHECK_STATUS")
//    private int CHECK_STATUS;              // 审核状态
//    @Property(nameInDb = "DATA_UNIT")
//    private String DATA_UNIT;                 // 数据报送单位
//    @Generated(hash = 241886075)
//    public BZ_RJBB_SG_PRT_SOFTWAREVERSION(String ID, String VER_CODE,
//            String MODEL_ID, String MODULE_NAME, String VER_NAME, String CHECK_CODE,
//            String COMMENT, String STAMP, String OWNER, String APPLY_VOLTAGE,
//            String APPLY_PROTEECTIONTYPE_CODE, String IS_START_USING,
//            int CHECK_STATUS, String DATA_UNIT) {
//        this.ID = ID;
//        this.VER_CODE = VER_CODE;
//        this.MODEL_ID = MODEL_ID;
//        this.MODULE_NAME = MODULE_NAME;
//        this.VER_NAME = VER_NAME;
//        this.CHECK_CODE = CHECK_CODE;
//        this.COMMENT = COMMENT;
//        this.STAMP = STAMP;
//        this.OWNER = OWNER;
//        this.APPLY_VOLTAGE = APPLY_VOLTAGE;
//        this.APPLY_PROTEECTIONTYPE_CODE = APPLY_PROTEECTIONTYPE_CODE;
//        this.IS_START_USING = IS_START_USING;
//        this.CHECK_STATUS = CHECK_STATUS;
//        this.DATA_UNIT = DATA_UNIT;
//    }
//    @Generated(hash = 821598833)
//    public BZ_RJBB_SG_PRT_SOFTWAREVERSION() {
//    }
//    public String getID() {
//        return this.ID;
//    }
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//    public String getVER_CODE() {
//        return this.VER_CODE;
//    }
//    public void setVER_CODE(String VER_CODE) {
//        this.VER_CODE = VER_CODE;
//    }
//    public String getMODEL_ID() {
//        return this.MODEL_ID;
//    }
//    public void setMODEL_ID(String MODEL_ID) {
//        this.MODEL_ID = MODEL_ID;
//    }
//    public String getMODULE_NAME() {
//        return this.MODULE_NAME;
//    }
//    public void setMODULE_NAME(String MODULE_NAME) {
//        this.MODULE_NAME = MODULE_NAME;
//    }
//    public String getVER_NAME() {
//        return this.VER_NAME;
//    }
//    public void setVER_NAME(String VER_NAME) {
//        this.VER_NAME = VER_NAME;
//    }
//    public String getCHECK_CODE() {
//        return this.CHECK_CODE;
//    }
//    public void setCHECK_CODE(String CHECK_CODE) {
//        this.CHECK_CODE = CHECK_CODE;
//    }
//    public String getCOMMENT() {
//        return this.COMMENT;
//    }
//    public void setCOMMENT(String COMMENT) {
//        this.COMMENT = COMMENT;
//    }
//    public String getSTAMP() {
//        return this.STAMP;
//    }
//    public void setSTAMP(String STAMP) {
//        this.STAMP = STAMP;
//    }
//    public String getOWNER() {
//        return this.OWNER;
//    }
//    public void setOWNER(String OWNER) {
//        this.OWNER = OWNER;
//    }
//    public String getAPPLY_VOLTAGE() {
//        return this.APPLY_VOLTAGE;
//    }
//    public void setAPPLY_VOLTAGE(String APPLY_VOLTAGE) {
//        this.APPLY_VOLTAGE = APPLY_VOLTAGE;
//    }
//    public String getAPPLY_PROTEECTIONTYPE_CODE() {
//        return this.APPLY_PROTEECTIONTYPE_CODE;
//    }
//    public void setAPPLY_PROTEECTIONTYPE_CODE(String APPLY_PROTEECTIONTYPE_CODE) {
//        this.APPLY_PROTEECTIONTYPE_CODE = APPLY_PROTEECTIONTYPE_CODE;
//    }
//    public String getIS_START_USING() {
//        return this.IS_START_USING;
//    }
//    public void setIS_START_USING(String IS_START_USING) {
//        this.IS_START_USING = IS_START_USING;
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
