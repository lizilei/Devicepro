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
// * <p>@description:发电机基本信息</p>
// * 表名：SG_DEV_GENERATOR_B
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//@Entity
//public class FDJCS_SG_DEV_GENERATOR_B implements Serializable {
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
//    private String ST_ID;            // 所属发电厂
//    @Property(nameInDb = "VOLTAGE_TYPE")
//    private String VOLTAGE_TYPE;     // 并入电网电压等级
//    @Property(nameInDb = "BAY_ID")
//    private String BAY_ID;           // 所属间隔
//    @Property(nameInDb = "ASSETS_COMPANY_ID")
//    private String ASSETS_COMPANY_ID;// 资产单位
//    @Property(nameInDb = "MAINT_ORG_ID")
//    private String MAINT_ORG_ID;     // 运维机构
//    @Property(nameInDb = "DISPATCH_ORG_ID")
//    private String DISPATCH_ORG_ID;  // 调度机构
//    @Property(nameInDb = "SUPPLIER_ID")
//    private String SUPPLIER_ID;      // 生产厂家
//    @Property(nameInDb = "MODEL")
//    private String MODEL;            // 型号
//    @Property(nameInDb = "TERMINAL_VOLTAGE")
//    private float TERMINAL_VOLTAGE; // 机端额定电压
//    @Property(nameInDb = "MVA_RATE")
//    private double MVA_RATE;         // 额定容量
//    @Property(nameInDb = "IS_INCRCAPACITY")
//    private String IS_INCRCAPACITY;  // 是否增容
//    @Property(nameInDb = "MAX_OUTPUT")
//    private double MAX_OUTPUT;       // 最大出力
//    @Property(nameInDb = "POWER_RATE")
//    private float POWER_RATE;       // 额定功率因数
//    @Property(nameInDb = "ENVIRO_PRO")
//    private int ENVIRO_PRO;       // 环保属性
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
//    private String OWNER;            // 拥有者
//    @Property(nameInDb = "ALIAS")
//    private String ALIAS;            // 别名
//    @Property(nameInDb = "R")
//    private float R;                // 正序电阻
//    @Property(nameInDb = "X")
//    private float X;                // 正序电抗
//    @Property(nameInDb = "Rn")
//    private float Rn;               // 负序电阻
//    @Property(nameInDb = "R0")
//    private float R0;               // 零序电阻
//    @Property(nameInDb = "Xdpp")
//    private float Xdpp;             // 次暂态电抗 X"d
//    @Property(nameInDb = "RatedMW")
//    private float RatedMW;          // 额定功率
//    @Property(nameInDb = "BASE_V")
//    private String BASE_V;           // 基准电压
//    @Property(nameInDb = "STATUS")
//    private int STATUS;           // 状态
//    @Property(nameInDb = "NODE")
//    private String NODE;             // 连接点
//    @Generated(hash = 1244104016)
//    public FDJCS_SG_DEV_GENERATOR_B(String ID, String RES_ID, String ED_TAG,
//            String NAME, String ST_ID, String VOLTAGE_TYPE, String BAY_ID,
//            String ASSETS_COMPANY_ID, String MAINT_ORG_ID, String DISPATCH_ORG_ID,
//            String SUPPLIER_ID, String MODEL, float TERMINAL_VOLTAGE,
//            double MVA_RATE, String IS_INCRCAPACITY, double MAX_OUTPUT,
//            float POWER_RATE, int ENVIRO_PRO, Timestamp OPERATE_DATE,
//            Timestamp EXPIRY_DATE, int RUNNING_STATE, String STAMP, String OWNER,
//            String ALIAS, float R, float X, float Rn, float R0, float Xdpp,
//            float RatedMW, String BASE_V, int STATUS, String NODE) {
//        this.ID = ID;
//        this.RES_ID = RES_ID;
//        this.ED_TAG = ED_TAG;
//        this.NAME = NAME;
//        this.ST_ID = ST_ID;
//        this.VOLTAGE_TYPE = VOLTAGE_TYPE;
//        this.BAY_ID = BAY_ID;
//        this.ASSETS_COMPANY_ID = ASSETS_COMPANY_ID;
//        this.MAINT_ORG_ID = MAINT_ORG_ID;
//        this.DISPATCH_ORG_ID = DISPATCH_ORG_ID;
//        this.SUPPLIER_ID = SUPPLIER_ID;
//        this.MODEL = MODEL;
//        this.TERMINAL_VOLTAGE = TERMINAL_VOLTAGE;
//        this.MVA_RATE = MVA_RATE;
//        this.IS_INCRCAPACITY = IS_INCRCAPACITY;
//        this.MAX_OUTPUT = MAX_OUTPUT;
//        this.POWER_RATE = POWER_RATE;
//        this.ENVIRO_PRO = ENVIRO_PRO;
//        this.OPERATE_DATE = OPERATE_DATE;
//        this.EXPIRY_DATE = EXPIRY_DATE;
//        this.RUNNING_STATE = RUNNING_STATE;
//        this.STAMP = STAMP;
//        this.OWNER = OWNER;
//        this.ALIAS = ALIAS;
//        this.R = R;
//        this.X = X;
//        this.Rn = Rn;
//        this.R0 = R0;
//        this.Xdpp = Xdpp;
//        this.RatedMW = RatedMW;
//        this.BASE_V = BASE_V;
//        this.STATUS = STATUS;
//        this.NODE = NODE;
//    }
//    @Generated(hash = 1631355749)
//    public FDJCS_SG_DEV_GENERATOR_B() {
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
//    public float getTERMINAL_VOLTAGE() {
//        return this.TERMINAL_VOLTAGE;
//    }
//    public void setTERMINAL_VOLTAGE(float TERMINAL_VOLTAGE) {
//        this.TERMINAL_VOLTAGE = TERMINAL_VOLTAGE;
//    }
//    public double getMVA_RATE() {
//        return this.MVA_RATE;
//    }
//    public void setMVA_RATE(double MVA_RATE) {
//        this.MVA_RATE = MVA_RATE;
//    }
//    public String getIS_INCRCAPACITY() {
//        return this.IS_INCRCAPACITY;
//    }
//    public void setIS_INCRCAPACITY(String IS_INCRCAPACITY) {
//        this.IS_INCRCAPACITY = IS_INCRCAPACITY;
//    }
//    public double getMAX_OUTPUT() {
//        return this.MAX_OUTPUT;
//    }
//    public void setMAX_OUTPUT(double MAX_OUTPUT) {
//        this.MAX_OUTPUT = MAX_OUTPUT;
//    }
//    public float getPOWER_RATE() {
//        return this.POWER_RATE;
//    }
//    public void setPOWER_RATE(float POWER_RATE) {
//        this.POWER_RATE = POWER_RATE;
//    }
//    public int getENVIRO_PRO() {
//        return this.ENVIRO_PRO;
//    }
//    public void setENVIRO_PRO(int ENVIRO_PRO) {
//        this.ENVIRO_PRO = ENVIRO_PRO;
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
//    public String getALIAS() {
//        return this.ALIAS;
//    }
//    public void setALIAS(String ALIAS) {
//        this.ALIAS = ALIAS;
//    }
//    public float getR() {
//        return this.R;
//    }
//    public void setR(float R) {
//        this.R = R;
//    }
//    public float getX() {
//        return this.X;
//    }
//    public void setX(float X) {
//        this.X = X;
//    }
//    public float getRn() {
//        return this.Rn;
//    }
//    public void setRn(float Rn) {
//        this.Rn = Rn;
//    }
//    public float getR0() {
//        return this.R0;
//    }
//    public void setR0(float R0) {
//        this.R0 = R0;
//    }
//    public float getXdpp() {
//        return this.Xdpp;
//    }
//    public void setXdpp(float Xdpp) {
//        this.Xdpp = Xdpp;
//    }
//    public float getRatedMW() {
//        return this.RatedMW;
//    }
//    public void setRatedMW(float RatedMW) {
//        this.RatedMW = RatedMW;
//    }
//    public String getBASE_V() {
//        return this.BASE_V;
//    }
//    public void setBASE_V(String BASE_V) {
//        this.BASE_V = BASE_V;
//    }
//    public int getSTATUS() {
//        return this.STATUS;
//    }
//    public void setSTATUS(int STATUS) {
//        this.STATUS = STATUS;
//    }
//    public String getNODE() {
//        return this.NODE;
//    }
//    public void setNODE(String NODE) {
//        this.NODE = NODE;
//    }
//}
