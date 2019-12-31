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
// * <p>@description:线路基本信息</p>
// * 表名：SG_DEV_ACLINE_B
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//
//@Entity
//public class XLCS_SG_DEV_ACLINE_B implements Serializable{
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;                 // 设备ID
//    @Property(nameInDb = "RES_ID")
//    private String RES_ID;              //来源ID
//    @Property(nameInDb = "ED_TAG")
//    private String ED_TAG;              //修改状态
//    @Property(nameInDb = "NAME")
//    private String NAME;               // 线路名称
//    @Property(nameInDb = "GRID_ID")
//    private String GRID_ID;            // 所属电网
//    @Property(nameInDb = "VOLTAGE_TYPE")
//    private int    VOLTAGE_TYPE;       // 电压等级
//    @Property(nameInDb = "SUPPLIER_ID")
//    private String SUPPLIER_ID;        // 生产厂家
//    @Property(nameInDb = "MODEL")
//    private String MODEL;              // 型号？线路编号
//    @Property(nameInDb = "LENGTH")
//    private double LENGTH;             // 线路全长?线路长度
//    @Property(nameInDb = "START_ST_ID")
//    private String START_ST_ID;        // 起点厂站
//    @Property(nameInDb = "START_BAY_ID")
//    private String START_BAY_ID;       // 起点所属间隔
//    @Property(nameInDb = "END_ST_ID")
//    private String END_ST_ID;          // 终点厂站
//    @Property(nameInDb = "END_BAY_ID")
//    private String END_BAY_ID;         // 终点所属间隔
//    @Property(nameInDb = "ISOPTICFIBEREQUIPED")
//    private String ISOPTICFIBEREQUIPED;// 是否有光纤
//    @Property(nameInDb = "ERECTINGMETHOD")
//    private String ERECTINGMETHOD;     // 架设方式
//    @Property(nameInDb = "ASSETS_COMPANY_ID")
//    private String ASSETS_COMPANY_ID;  // 资产单位
//    @Property(nameInDb = "DISPATCH_ORG_ID")
//    private String DISPATCH_ORG_ID;    // 调度机构？调度单位
//    @Property(nameInDb = "LICENSE_ORG_ID")
//    private String LICENSE_ORG_ID;     // 许可机构
//    @Property(nameInDb = "MONITOR_ORG_ID")
//    private String MONITOR_ORG_ID;     // 监控机构
//    @Property(nameInDb = "OPERATE_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp OPERATE_DATE;       // 投运日期 ? bhpz的投运日期为timestamp
//    @Property(nameInDb = "EXPIRY_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp   EXPIRY_DATE;        // 退役日期
//    @Property(nameInDb = "RUNNING_STATE")
//    private int    RUNNING_STATE;      // 运行状态
//    @Property(nameInDb = "STAMP")
//    private String STAMP;              // 更新标志
//    @Property(nameInDb = "OWNER")
//    private String OWNER;              // 拥有者
//    @Property(nameInDb = "linetype")
//    private int    linetype;           // 线路类型？
//    @Property(nameInDb = "STATUS")
//    private int    STATUS;             // 状态
//    @Property(nameInDb = "alias")
//    private String alias;              // 别名
//    @Generated(hash = 684288216)
//    public XLCS_SG_DEV_ACLINE_B(String ID, String RES_ID, String ED_TAG,
//            String NAME, String GRID_ID, int VOLTAGE_TYPE, String SUPPLIER_ID,
//            String MODEL, double LENGTH, String START_ST_ID, String START_BAY_ID,
//            String END_ST_ID, String END_BAY_ID, String ISOPTICFIBEREQUIPED,
//            String ERECTINGMETHOD, String ASSETS_COMPANY_ID, String DISPATCH_ORG_ID,
//            String LICENSE_ORG_ID, String MONITOR_ORG_ID, Timestamp OPERATE_DATE,
//            Timestamp EXPIRY_DATE, int RUNNING_STATE, String STAMP, String OWNER,
//            int linetype, int STATUS, String alias) {
//        this.ID = ID;
//        this.RES_ID = RES_ID;
//        this.ED_TAG = ED_TAG;
//        this.NAME = NAME;
//        this.GRID_ID = GRID_ID;
//        this.VOLTAGE_TYPE = VOLTAGE_TYPE;
//        this.SUPPLIER_ID = SUPPLIER_ID;
//        this.MODEL = MODEL;
//        this.LENGTH = LENGTH;
//        this.START_ST_ID = START_ST_ID;
//        this.START_BAY_ID = START_BAY_ID;
//        this.END_ST_ID = END_ST_ID;
//        this.END_BAY_ID = END_BAY_ID;
//        this.ISOPTICFIBEREQUIPED = ISOPTICFIBEREQUIPED;
//        this.ERECTINGMETHOD = ERECTINGMETHOD;
//        this.ASSETS_COMPANY_ID = ASSETS_COMPANY_ID;
//        this.DISPATCH_ORG_ID = DISPATCH_ORG_ID;
//        this.LICENSE_ORG_ID = LICENSE_ORG_ID;
//        this.MONITOR_ORG_ID = MONITOR_ORG_ID;
//        this.OPERATE_DATE = OPERATE_DATE;
//        this.EXPIRY_DATE = EXPIRY_DATE;
//        this.RUNNING_STATE = RUNNING_STATE;
//        this.STAMP = STAMP;
//        this.OWNER = OWNER;
//        this.linetype = linetype;
//        this.STATUS = STATUS;
//        this.alias = alias;
//    }
//    @Generated(hash = 1866632296)
//    public XLCS_SG_DEV_ACLINE_B() {
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
//    public String getGRID_ID() {
//        return this.GRID_ID;
//    }
//    public void setGRID_ID(String GRID_ID) {
//        this.GRID_ID = GRID_ID;
//    }
//    public int getVOLTAGE_TYPE() {
//        return this.VOLTAGE_TYPE;
//    }
//    public void setVOLTAGE_TYPE(int VOLTAGE_TYPE) {
//        this.VOLTAGE_TYPE = VOLTAGE_TYPE;
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
//    public double getLENGTH() {
//        return this.LENGTH;
//    }
//    public void setLENGTH(double LENGTH) {
//        this.LENGTH = LENGTH;
//    }
//    public String getSTART_ST_ID() {
//        return this.START_ST_ID;
//    }
//    public void setSTART_ST_ID(String START_ST_ID) {
//        this.START_ST_ID = START_ST_ID;
//    }
//    public String getSTART_BAY_ID() {
//        return this.START_BAY_ID;
//    }
//    public void setSTART_BAY_ID(String START_BAY_ID) {
//        this.START_BAY_ID = START_BAY_ID;
//    }
//    public String getEND_ST_ID() {
//        return this.END_ST_ID;
//    }
//    public void setEND_ST_ID(String END_ST_ID) {
//        this.END_ST_ID = END_ST_ID;
//    }
//    public String getEND_BAY_ID() {
//        return this.END_BAY_ID;
//    }
//    public void setEND_BAY_ID(String END_BAY_ID) {
//        this.END_BAY_ID = END_BAY_ID;
//    }
//    public String getISOPTICFIBEREQUIPED() {
//        return this.ISOPTICFIBEREQUIPED;
//    }
//    public void setISOPTICFIBEREQUIPED(String ISOPTICFIBEREQUIPED) {
//        this.ISOPTICFIBEREQUIPED = ISOPTICFIBEREQUIPED;
//    }
//    public String getERECTINGMETHOD() {
//        return this.ERECTINGMETHOD;
//    }
//    public void setERECTINGMETHOD(String ERECTINGMETHOD) {
//        this.ERECTINGMETHOD = ERECTINGMETHOD;
//    }
//    public String getASSETS_COMPANY_ID() {
//        return this.ASSETS_COMPANY_ID;
//    }
//    public void setASSETS_COMPANY_ID(String ASSETS_COMPANY_ID) {
//        this.ASSETS_COMPANY_ID = ASSETS_COMPANY_ID;
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
//    public int getLinetype() {
//        return this.linetype;
//    }
//    public void setLinetype(int linetype) {
//        this.linetype = linetype;
//    }
//    public int getSTATUS() {
//        return this.STATUS;
//    }
//    public void setSTATUS(int STATUS) {
//        this.STATUS = STATUS;
//    }
//    public String getAlias() {
//        return this.alias;
//    }
//    public void setAlias(String alias) {
//        this.alias = alias;
//    }
//}
