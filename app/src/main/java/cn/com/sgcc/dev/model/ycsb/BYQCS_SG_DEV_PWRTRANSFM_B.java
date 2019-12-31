//package cn.com.sgcc.dev.model.ycsb;
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
//import cn.com.sgcc.dev.model.TimestampConverter;
//
///**
// * <p>@description:变压器基本信息</p>
// * 表名：SG_DEV_PWRTRANSFM_B
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//@Entity
//public class BYQCS_SG_DEV_PWRTRANSFM_B implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;                 // 设备ID
//    @Property(nameInDb = "RES_ID")
//    private String RES_ID;              //来源ID
//    @Property(nameInDb = "ED_TAG")
//    private String ED_TAG;              //修改状态
//    @Property(nameInDb = "NAME")
//    private String NAME;               // 设备名称
//    @Property(nameInDb = "ST_ID")
//    private String ST_ID;              // 所属厂站
//    @Property(nameInDb = "SUPPLIER_ID")
//    private String SUPPLIER_ID;        // 生产厂家
//    @Property(nameInDb = "MODEL")
//    private String MODEL;              // 型号
//    @Property(nameInDb = "MVA_RATE")
//    private double MVA_RATE;           // 额定容量
//    @Property(nameInDb = "VOLTAGE_TYPE")
//    private int VOLTAGE_TYPE;       // 最高电压等级
//    @Property(nameInDb = "USAGE")
//    private int USAGE;              // 用途
//    @Property(nameInDb = "ASSETS_COMPANY_ID")
//    private String ASSETS_COMPANY_ID;  // 资产单位
//    @Property(nameInDb = "MAINT_ORG_ID")
//    private String MAINT_ORG_ID;       // 检修机构
//    @Property(nameInDb = "DISPATCH_ORG_ID")
//    private String DISPATCH_ORG_ID;    // 调度机构
//    @Property(nameInDb = "LICENSE_ORG_ID")
//    private String LICENSE_ORG_ID;     // 许可机构
//    @Property(nameInDb = "MONITOR_ORG_ID")
//    private String MONITOR_ORG_ID;     // 监控机构
//    @Property(nameInDb = "OPERATE_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp OPERATE_DATE;       // 投运日期
//    @Property(nameInDb = "EXPIRY_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp EXPIRY_DATE;        // 退役日期
//    @Property(nameInDb = "INSULATING_MEDIUM")
//    private String INSULATING_MEDIUM;  // 绝缘介质
//    @Property(nameInDb = "STRUCTURAL_STYLE")
//    private String STRUCTURAL_STYLE;   // 结构型式
//    @Property(nameInDb = "VOL_REGULATING_MODE")
//    private String VOL_REGULATING_MODE;// 调压方式
//    @Property(nameInDb = "RUNNING_STATE")
//    private int RUNNING_STATE;      // 运行状态
//    @Property(nameInDb = "STAMP")
//    private String STAMP;              // 更新标志
//    @Property(nameInDb = "OWNER")
//    private String OWNER;              // 拥有者
//    @Property(nameInDb = "Alias")
//    private String Alias;              // 别名
//    @Property(nameInDb = "TransType")
//    private int TransType;          // 变压器类型
//    @Property(nameInDb = "STATUS")
//    private int STATUS;             // 状态
//    @Generated(hash = 1531699834)
//    public BYQCS_SG_DEV_PWRTRANSFM_B(String ID, String RES_ID, String ED_TAG,
//            String NAME, String ST_ID, String SUPPLIER_ID, String MODEL,
//            double MVA_RATE, int VOLTAGE_TYPE, int USAGE, String ASSETS_COMPANY_ID,
//            String MAINT_ORG_ID, String DISPATCH_ORG_ID, String LICENSE_ORG_ID,
//            String MONITOR_ORG_ID, Timestamp OPERATE_DATE, Timestamp EXPIRY_DATE,
//            String INSULATING_MEDIUM, String STRUCTURAL_STYLE,
//            String VOL_REGULATING_MODE, int RUNNING_STATE, String STAMP,
//            String OWNER, String Alias, int TransType, int STATUS) {
//        this.ID = ID;
//        this.RES_ID = RES_ID;
//        this.ED_TAG = ED_TAG;
//        this.NAME = NAME;
//        this.ST_ID = ST_ID;
//        this.SUPPLIER_ID = SUPPLIER_ID;
//        this.MODEL = MODEL;
//        this.MVA_RATE = MVA_RATE;
//        this.VOLTAGE_TYPE = VOLTAGE_TYPE;
//        this.USAGE = USAGE;
//        this.ASSETS_COMPANY_ID = ASSETS_COMPANY_ID;
//        this.MAINT_ORG_ID = MAINT_ORG_ID;
//        this.DISPATCH_ORG_ID = DISPATCH_ORG_ID;
//        this.LICENSE_ORG_ID = LICENSE_ORG_ID;
//        this.MONITOR_ORG_ID = MONITOR_ORG_ID;
//        this.OPERATE_DATE = OPERATE_DATE;
//        this.EXPIRY_DATE = EXPIRY_DATE;
//        this.INSULATING_MEDIUM = INSULATING_MEDIUM;
//        this.STRUCTURAL_STYLE = STRUCTURAL_STYLE;
//        this.VOL_REGULATING_MODE = VOL_REGULATING_MODE;
//        this.RUNNING_STATE = RUNNING_STATE;
//        this.STAMP = STAMP;
//        this.OWNER = OWNER;
//        this.Alias = Alias;
//        this.TransType = TransType;
//        this.STATUS = STATUS;
//    }
//    @Generated(hash = 1980880876)
//    public BYQCS_SG_DEV_PWRTRANSFM_B() {
//    }
//    public String getID() {
//        return this.ID;
//    }
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//    public String getRES_ID() {
//        return this.RES_ID;
//    }
//    public void setRES_ID(String RES_ID) {
//        this.RES_ID = RES_ID;
//    }
//    public String getED_TAG() {
//        return this.ED_TAG;
//    }
//    public void setED_TAG(String ED_TAG) {
//        this.ED_TAG = ED_TAG;
//    }
//    public String getNAME() {
//        return this.NAME;
//    }
//    public void setNAME(String NAME) {
//        this.NAME = NAME;
//    }
//    public String getST_ID() {
//        return this.ST_ID;
//    }
//    public void setST_ID(String ST_ID) {
//        this.ST_ID = ST_ID;
//    }
//    public String getSUPPLIER_ID() {
//        return this.SUPPLIER_ID;
//    }
//    public void setSUPPLIER_ID(String SUPPLIER_ID) {
//        this.SUPPLIER_ID = SUPPLIER_ID;
//    }
//    public String getMODEL() {
//        return this.MODEL;
//    }
//    public void setMODEL(String MODEL) {
//        this.MODEL = MODEL;
//    }
//    public double getMVA_RATE() {
//        return this.MVA_RATE;
//    }
//    public void setMVA_RATE(double MVA_RATE) {
//        this.MVA_RATE = MVA_RATE;
//    }
//    public int getVOLTAGE_TYPE() {
//        return this.VOLTAGE_TYPE;
//    }
//    public void setVOLTAGE_TYPE(int VOLTAGE_TYPE) {
//        this.VOLTAGE_TYPE = VOLTAGE_TYPE;
//    }
//    public int getUSAGE() {
//        return this.USAGE;
//    }
//    public void setUSAGE(int USAGE) {
//        this.USAGE = USAGE;
//    }
//    public String getASSETS_COMPANY_ID() {
//        return this.ASSETS_COMPANY_ID;
//    }
//    public void setASSETS_COMPANY_ID(String ASSETS_COMPANY_ID) {
//        this.ASSETS_COMPANY_ID = ASSETS_COMPANY_ID;
//    }
//    public String getMAINT_ORG_ID() {
//        return this.MAINT_ORG_ID;
//    }
//    public void setMAINT_ORG_ID(String MAINT_ORG_ID) {
//        this.MAINT_ORG_ID = MAINT_ORG_ID;
//    }
//    public String getDISPATCH_ORG_ID() {
//        return this.DISPATCH_ORG_ID;
//    }
//    public void setDISPATCH_ORG_ID(String DISPATCH_ORG_ID) {
//        this.DISPATCH_ORG_ID = DISPATCH_ORG_ID;
//    }
//    public String getLICENSE_ORG_ID() {
//        return this.LICENSE_ORG_ID;
//    }
//    public void setLICENSE_ORG_ID(String LICENSE_ORG_ID) {
//        this.LICENSE_ORG_ID = LICENSE_ORG_ID;
//    }
//    public String getMONITOR_ORG_ID() {
//        return this.MONITOR_ORG_ID;
//    }
//    public void setMONITOR_ORG_ID(String MONITOR_ORG_ID) {
//        this.MONITOR_ORG_ID = MONITOR_ORG_ID;
//    }
//    public Timestamp getOPERATE_DATE() {
//        return this.OPERATE_DATE;
//    }
//    public void setOPERATE_DATE(Timestamp OPERATE_DATE) {
//        this.OPERATE_DATE = OPERATE_DATE;
//    }
//    public Timestamp getEXPIRY_DATE() {
//        return this.EXPIRY_DATE;
//    }
//    public void setEXPIRY_DATE(Timestamp EXPIRY_DATE) {
//        this.EXPIRY_DATE = EXPIRY_DATE;
//    }
//    public String getINSULATING_MEDIUM() {
//        return this.INSULATING_MEDIUM;
//    }
//    public void setINSULATING_MEDIUM(String INSULATING_MEDIUM) {
//        this.INSULATING_MEDIUM = INSULATING_MEDIUM;
//    }
//    public String getSTRUCTURAL_STYLE() {
//        return this.STRUCTURAL_STYLE;
//    }
//    public void setSTRUCTURAL_STYLE(String STRUCTURAL_STYLE) {
//        this.STRUCTURAL_STYLE = STRUCTURAL_STYLE;
//    }
//    public String getVOL_REGULATING_MODE() {
//        return this.VOL_REGULATING_MODE;
//    }
//    public void setVOL_REGULATING_MODE(String VOL_REGULATING_MODE) {
//        this.VOL_REGULATING_MODE = VOL_REGULATING_MODE;
//    }
//    public int getRUNNING_STATE() {
//        return this.RUNNING_STATE;
//    }
//    public void setRUNNING_STATE(int RUNNING_STATE) {
//        this.RUNNING_STATE = RUNNING_STATE;
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
//    public String getAlias() {
//        return this.Alias;
//    }
//    public void setAlias(String Alias) {
//        this.Alias = Alias;
//    }
//    public int getTransType() {
//        return this.TransType;
//    }
//    public void setTransType(int TransType) {
//        this.TransType = TransType;
//    }
//    public int getSTATUS() {
//        return this.STATUS;
//    }
//    public void setSTATUS(int STATUS) {
//        this.STATUS = STATUS;
//    }
//}
