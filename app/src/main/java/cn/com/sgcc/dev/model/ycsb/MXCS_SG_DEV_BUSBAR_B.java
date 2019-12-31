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
// * <p>@description:母线基本信息</p>
// * 表名：SG_DEV_BUSBAR_B
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//@Entity
//public class MXCS_SG_DEV_BUSBAR_B implements Serializable {
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
//    @Property(nameInDb = "VOLTAGE_TYPE")
//    private String VOLTAGE_TYPE;     // 电压等级
//    @Property(nameInDb = "BAY_ID")
//    private String BAY_ID;           // 所属间隔
//    @Property(nameInDb = "SUPPLIER_ID")
//    private String SUPPLIER_ID;      // 生产厂家
//    @Property(nameInDb = "MODEL")
//    private String MODEL;            // 型号
//    @Property(nameInDb = "ARRANGE_TYPE")
//    private int ARRANGE_TYPE;     // 布置类型
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
//    @Property(nameInDb = "RUNNING_STATE")
//    private int RUNNING_STATE;    // 运行状态
//    @Property(nameInDb = "STAMP")
//    private String STAMP;            // 更新标志
//    @Property(nameInDb = "OWNER")
//    private String OWNER;            // 拥有者
//    @Property(nameInDb = "BASE_V")
//    private String BASE_V;           // 基准电压
//    @Property(nameInDb = "WAY")
//    private int WAY;              // 接线方式
//    @Property(nameInDb = "STATUS")
//    private int STATUS;           // 状态
//    @Property(nameInDb = "OLDEQAREAID1")
//    private String OLDEQAREAID1;     // 源设备所属调度
//    @Property(nameInDb = "NODE")
//    private String NODE;             // 连接点
//    @Generated(hash = 2043004222)
//    public MXCS_SG_DEV_BUSBAR_B(String ID, String RES_ID, String ED_TAG,
//            String NAME, String ST_ID, String VOLTAGE_TYPE, String BAY_ID,
//            String SUPPLIER_ID, String MODEL, int ARRANGE_TYPE,
//            String ASSETS_COMPANY_ID, String MAINT_ORG_ID, String DISPATCH_ORG_ID,
//            String LICENSE_ORG_ID, String MONITOR_ORG_ID, Timestamp OPERATE_DATE,
//            Timestamp EXPIRY_DATE, int RUNNING_STATE, String STAMP, String OWNER,
//            String BASE_V, int WAY, int STATUS, String OLDEQAREAID1, String NODE) {
//        this.ID = ID;
//        this.RES_ID = RES_ID;
//        this.ED_TAG = ED_TAG;
//        this.NAME = NAME;
//        this.ST_ID = ST_ID;
//        this.VOLTAGE_TYPE = VOLTAGE_TYPE;
//        this.BAY_ID = BAY_ID;
//        this.SUPPLIER_ID = SUPPLIER_ID;
//        this.MODEL = MODEL;
//        this.ARRANGE_TYPE = ARRANGE_TYPE;
//        this.ASSETS_COMPANY_ID = ASSETS_COMPANY_ID;
//        this.MAINT_ORG_ID = MAINT_ORG_ID;
//        this.DISPATCH_ORG_ID = DISPATCH_ORG_ID;
//        this.LICENSE_ORG_ID = LICENSE_ORG_ID;
//        this.MONITOR_ORG_ID = MONITOR_ORG_ID;
//        this.OPERATE_DATE = OPERATE_DATE;
//        this.EXPIRY_DATE = EXPIRY_DATE;
//        this.RUNNING_STATE = RUNNING_STATE;
//        this.STAMP = STAMP;
//        this.OWNER = OWNER;
//        this.BASE_V = BASE_V;
//        this.WAY = WAY;
//        this.STATUS = STATUS;
//        this.OLDEQAREAID1 = OLDEQAREAID1;
//        this.NODE = NODE;
//    }
//    @Generated(hash = 655793556)
//    public MXCS_SG_DEV_BUSBAR_B() {
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
//    public String getVOLTAGE_TYPE() {
//        return this.VOLTAGE_TYPE;
//    }
//    public void setVOLTAGE_TYPE(String VOLTAGE_TYPE) {
//        this.VOLTAGE_TYPE = VOLTAGE_TYPE;
//    }
//    public String getBAY_ID() {
//        return this.BAY_ID;
//    }
//    public void setBAY_ID(String BAY_ID) {
//        this.BAY_ID = BAY_ID;
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
//    public int getARRANGE_TYPE() {
//        return this.ARRANGE_TYPE;
//    }
//    public void setARRANGE_TYPE(int ARRANGE_TYPE) {
//        this.ARRANGE_TYPE = ARRANGE_TYPE;
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
//    public String getBASE_V() {
//        return this.BASE_V;
//    }
//    public void setBASE_V(String BASE_V) {
//        this.BASE_V = BASE_V;
//    }
//    public int getWAY() {
//        return this.WAY;
//    }
//    public void setWAY(int WAY) {
//        this.WAY = WAY;
//    }
//    public int getSTATUS() {
//        return this.STATUS;
//    }
//    public void setSTATUS(int STATUS) {
//        this.STATUS = STATUS;
//    }
//    public String getOLDEQAREAID1() {
//        return this.OLDEQAREAID1;
//    }
//    public void setOLDEQAREAID1(String OLDEQAREAID1) {
//        this.OLDEQAREAID1 = OLDEQAREAID1;
//    }
//    public String getNODE() {
//        return this.NODE;
//    }
//    public void setNODE(String NODE) {
//        this.NODE = NODE;
//    }
//}
