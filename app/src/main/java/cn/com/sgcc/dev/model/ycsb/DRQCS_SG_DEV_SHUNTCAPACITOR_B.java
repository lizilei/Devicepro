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
// * <p>@description:电容器基本信息</p>
// * 表名：SG_DEV_SHUNTCAPACITOR_B
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//
//@Entity
//public class DRQCS_SG_DEV_SHUNTCAPACITOR_B implements Serializable {//有串并联之分
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
//    private String ST_ID;            // 所属厂站
//    @Property(nameInDb = "VOLTAGE_TYPE")
//    private int VOLTAGE_TYPE;     // 电压等级
//    @Property(nameInDb = "BAY_ID")
//    private String BAY_ID;           // 所属间隔
//    @Property(nameInDb = "SUPPLIER_ID")
//    private String SUPPLIER_ID;      // 生产厂家
//    @Property(nameInDb = "MODEL")
//    private String MODEL;            // 型号
//    @Property(nameInDb = "ASSETS_COMPANY_ID")
//    private String ASSETS_COMPANY_ID;// 资产单位
//    @Property(nameInDb = "MAINT_ORG_ID")
//    private String MAINT_ORG_ID;     // 检修机构
//    @Property(nameInDb = "DISPATCH_ORG_ID")
//    private String DISPATCH_ORG_ID;  // 调度机构
//    @Property(nameInDb = "LICENSE_ORG_ID")
//    private String LICENSE_ORG_ID;   // 许可机构
//    @Property(nameInDb = "MONITOR_ORG_ID")
//    private String MONITOR_ORG_ID;   // 监控机构
//    @Property(nameInDb = "CAPACITY_RATE")
//    private float CAPACITY_RATE;    // 额定容量
//    @Property(nameInDb = "V_RATE")
//    private float V_RATE;           // 额定电压
//    @Property(nameInDb = "OPERATE_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp OPERATE_DATE;     // 投运日期
//    @Property(nameInDb = "EXPIRY_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp EXPIRY_DATE;      // 退役日期
//    @Property(nameInDb = "RUNNING_STATE")
//    private int RUNNING_STATE;    // 运行状态
//    @Property(nameInDb = "STAMP")
//    private String STAMP;            // 更新标志
//    @Property(nameInDb = "OWNER")
//    private String OWNER;            //
//    @Property(nameInDb = "Alias")// 拥有者
//    private String Alias;            //别名
//    @Property(nameInDb = "Xground")
//    private float Xground;          // 中性点小电抗
//    @Property(nameInDb = "NominalCurrent")
//    private float NominalCurrent;   // 额定电流
//    @Property(nameInDb = "STATUS")
//    private int STATUS;           //
//    @Property(nameInDb = "BASE_V")// 状态
//    private String BASE_V;           // 基准电压
//    @Property(nameInDb = "INODE")
//    private String INODE;            // I端连接点
//    @Property(nameInDb = "POSITION")
//    private String POSITION;         // 连接位置
//    @Property(nameInDb = "JNODE")
//    private String JNODE;            // J端连接点
//    @Generated(hash = 1719479882)
//    public DRQCS_SG_DEV_SHUNTCAPACITOR_B(String ID, String RES_ID, String ED_TAG,
//            String NAME, String ST_ID, int VOLTAGE_TYPE, String BAY_ID,
//            String SUPPLIER_ID, String MODEL, String ASSETS_COMPANY_ID,
//            String MAINT_ORG_ID, String DISPATCH_ORG_ID, String LICENSE_ORG_ID,
//            String MONITOR_ORG_ID, float CAPACITY_RATE, float V_RATE,
//            Timestamp OPERATE_DATE, Timestamp EXPIRY_DATE, int RUNNING_STATE,
//            String STAMP, String OWNER, String Alias, float Xground,
//            float NominalCurrent, int STATUS, String BASE_V, String INODE,
//            String POSITION, String JNODE) {
//        this.ID = ID;
//        this.RES_ID = RES_ID;
//        this.ED_TAG = ED_TAG;
//        this.NAME = NAME;
//        this.ST_ID = ST_ID;
//        this.VOLTAGE_TYPE = VOLTAGE_TYPE;
//        this.BAY_ID = BAY_ID;
//        this.SUPPLIER_ID = SUPPLIER_ID;
//        this.MODEL = MODEL;
//        this.ASSETS_COMPANY_ID = ASSETS_COMPANY_ID;
//        this.MAINT_ORG_ID = MAINT_ORG_ID;
//        this.DISPATCH_ORG_ID = DISPATCH_ORG_ID;
//        this.LICENSE_ORG_ID = LICENSE_ORG_ID;
//        this.MONITOR_ORG_ID = MONITOR_ORG_ID;
//        this.CAPACITY_RATE = CAPACITY_RATE;
//        this.V_RATE = V_RATE;
//        this.OPERATE_DATE = OPERATE_DATE;
//        this.EXPIRY_DATE = EXPIRY_DATE;
//        this.RUNNING_STATE = RUNNING_STATE;
//        this.STAMP = STAMP;
//        this.OWNER = OWNER;
//        this.Alias = Alias;
//        this.Xground = Xground;
//        this.NominalCurrent = NominalCurrent;
//        this.STATUS = STATUS;
//        this.BASE_V = BASE_V;
//        this.INODE = INODE;
//        this.POSITION = POSITION;
//        this.JNODE = JNODE;
//    }
//    @Generated(hash = 1399294446)
//    public DRQCS_SG_DEV_SHUNTCAPACITOR_B() {
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
//    public int getVOLTAGE_TYPE() {
//        return this.VOLTAGE_TYPE;
//    }
//    public void setVOLTAGE_TYPE(int VOLTAGE_TYPE) {
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
//    public float getCAPACITY_RATE() {
//        return this.CAPACITY_RATE;
//    }
//    public void setCAPACITY_RATE(float CAPACITY_RATE) {
//        this.CAPACITY_RATE = CAPACITY_RATE;
//    }
//    public float getV_RATE() {
//        return this.V_RATE;
//    }
//    public void setV_RATE(float V_RATE) {
//        this.V_RATE = V_RATE;
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
//    public String getAlias() {
//        return this.Alias;
//    }
//    public void setAlias(String Alias) {
//        this.Alias = Alias;
//    }
//    public float getXground() {
//        return this.Xground;
//    }
//    public void setXground(float Xground) {
//        this.Xground = Xground;
//    }
//    public float getNominalCurrent() {
//        return this.NominalCurrent;
//    }
//    public void setNominalCurrent(float NominalCurrent) {
//        this.NominalCurrent = NominalCurrent;
//    }
//    public int getSTATUS() {
//        return this.STATUS;
//    }
//    public void setSTATUS(int STATUS) {
//        this.STATUS = STATUS;
//    }
//    public String getBASE_V() {
//        return this.BASE_V;
//    }
//    public void setBASE_V(String BASE_V) {
//        this.BASE_V = BASE_V;
//    }
//    public String getINODE() {
//        return this.INODE;
//    }
//    public void setINODE(String INODE) {
//        this.INODE = INODE;
//    }
//    public String getPOSITION() {
//        return this.POSITION;
//    }
//    public void setPOSITION(String POSITION) {
//        this.POSITION = POSITION;
//    }
//    public String getJNODE() {
//        return this.JNODE;
//    }
//    public void setJNODE(String JNODE) {
//        this.JNODE = JNODE;
//    }
//}
