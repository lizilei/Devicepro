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
// * <p>@description:厂站参数</p>
// * 表名：SG_CON_SUBSTATION_B
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//public class CZCS_SG_CON_SUBSTATION_B implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;                  // 厂站ID
//    @Property(nameInDb = "NAME")
//    private String NAME;                // 厂站名称
//    @Property(nameInDb = "NAME_ABBREV")
//    private String NAME_ABBREV;         // 厂站简称
//    @Property(nameInDb = "SUBSTATION_TYPE")
//    private int SUBSTATION_TYPE;     // 类型?厂站类型，是否负荷站都要用这个
//    @Property(nameInDb = "SUBSTATION_ATTRIBUTE")
//    private int SUBSTATION_ATTRIBUTE;// 厂站属性
//    @Property(nameInDb = "OPERATE_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp OPERATE_DATE;        // 投运日期
//    @Property(nameInDb = "EXPIRY_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp EXPIRY_DATE;         // 退运日期
//    @Property(nameInDb = "OPERATE_STATE")
//    private int OPERATE_STATE;       // 运行状态
//    @Property(nameInDb = "DC_JOB")
//    private String DC_JOB;              // 直流工程
//    @Property(nameInDb = "IS_IND")
//    private String IS_IND;              // 是否独立
//    @Property(nameInDb = "AV_MAX_VOL")
//    private int AV_MAX_VOL;          // 交流最高电压等级?厂站最高电压等级
//    @Property(nameInDb = "DC_MAX_VOL")
//    private int DC_MAX_VOL;          // 直流电压等级
//    @Property(nameInDb = "CONNECTIVE_PG_ID")
//    private String CONNECTIVE_PG_ID;    // 接入电网
//    @Property(nameInDb = "REGISTER_NAME")
//    private String REGISTER_NAME;       // 工商注册名称
//    @Property(nameInDb = "COMPANY_ID")
//    private String COMPANY_ID;          // 所属公司？运行管理单位
//    @Property(nameInDb = "DCC_ID")
//    private String DCC_ID;              // 所属调度机构 ？是调度单位
//    @Property(nameInDb = "ASSETS_OWNERSHIP")
//    private int ASSETS_OWNERSHIP;    // 资产归属性质
//    @Property(nameInDb = "ASSET_UNIT_ID")
//    private String ASSET_UNIT_ID;       // 资产单位ID
//    @Property(nameInDb = "REGION")
//    private int REGION;              // 行政区划？厂站所在地区
//    @Property(nameInDb = "LONGITUDE")
//    private double LONGITUDE;           // 经度
//    @Property(nameInDb = "LATITUDE")
//    private double LATITUDE;            // 纬度
//    @Property(nameInDb = "ALTITUDE")
//    private double ALTITUDE;            // 海拔
//    @Property(nameInDb = "ADDRESS")
//    private String ADDRESS;             // 通信地址
//    @Property(nameInDb = "POSTCODE")
//    private String POSTCODE;            // 邮政编码
//    @Property(nameInDb = "FAX_NO")
//    private String FAX_NO;              // 联系传真
//    @Property(nameInDb = "PHONE_NO")
//    private String PHONE_NO;            // 联系电话
//    @Property(nameInDb = "EMAIL")
//    private String EMAIL;               // 电子邮箱
//    @Property(nameInDb = "STAMP")
//    private String STAMP;               // 更新标志
//    @Property(nameInDb = "OWNER")
//    private String OWNER;               // 拥有者
//    @Property(nameInDb = "RES_ID")
//    private String CHECK_STATUS;        // 审核状态
//    @Property(nameInDb = "RES_ID")
//    private String DATA_UNIT;           // 数据报送单位
//    @Property(nameInDb = "SCD_ID")
//    private String SCD_ID;              // 所属SCD文件
//    @Generated(hash = 656812721)
//    public CZCS_SG_CON_SUBSTATION_B(String ID, String NAME, String NAME_ABBREV,
//            int SUBSTATION_TYPE, int SUBSTATION_ATTRIBUTE, Timestamp OPERATE_DATE,
//            Timestamp EXPIRY_DATE, int OPERATE_STATE, String DC_JOB, String IS_IND,
//            int AV_MAX_VOL, int DC_MAX_VOL, String CONNECTIVE_PG_ID,
//            String REGISTER_NAME, String COMPANY_ID, String DCC_ID,
//            int ASSETS_OWNERSHIP, String ASSET_UNIT_ID, int REGION,
//            double LONGITUDE, double LATITUDE, double ALTITUDE, String ADDRESS,
//            String POSTCODE, String FAX_NO, String PHONE_NO, String EMAIL,
//            String STAMP, String OWNER, String CHECK_STATUS, String DATA_UNIT,
//            String SCD_ID) {
//        this.ID = ID;
//        this.NAME = NAME;
//        this.NAME_ABBREV = NAME_ABBREV;
//        this.SUBSTATION_TYPE = SUBSTATION_TYPE;
//        this.SUBSTATION_ATTRIBUTE = SUBSTATION_ATTRIBUTE;
//        this.OPERATE_DATE = OPERATE_DATE;
//        this.EXPIRY_DATE = EXPIRY_DATE;
//        this.OPERATE_STATE = OPERATE_STATE;
//        this.DC_JOB = DC_JOB;
//        this.IS_IND = IS_IND;
//        this.AV_MAX_VOL = AV_MAX_VOL;
//        this.DC_MAX_VOL = DC_MAX_VOL;
//        this.CONNECTIVE_PG_ID = CONNECTIVE_PG_ID;
//        this.REGISTER_NAME = REGISTER_NAME;
//        this.COMPANY_ID = COMPANY_ID;
//        this.DCC_ID = DCC_ID;
//        this.ASSETS_OWNERSHIP = ASSETS_OWNERSHIP;
//        this.ASSET_UNIT_ID = ASSET_UNIT_ID;
//        this.REGION = REGION;
//        this.LONGITUDE = LONGITUDE;
//        this.LATITUDE = LATITUDE;
//        this.ALTITUDE = ALTITUDE;
//        this.ADDRESS = ADDRESS;
//        this.POSTCODE = POSTCODE;
//        this.FAX_NO = FAX_NO;
//        this.PHONE_NO = PHONE_NO;
//        this.EMAIL = EMAIL;
//        this.STAMP = STAMP;
//        this.OWNER = OWNER;
//        this.CHECK_STATUS = CHECK_STATUS;
//        this.DATA_UNIT = DATA_UNIT;
//        this.SCD_ID = SCD_ID;
//    }
//    @Generated(hash = 91944000)
//    public CZCS_SG_CON_SUBSTATION_B() {
//    }
//    public String getID() {
//        return this.ID;
//    }
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//    public String getNAME() {
//        return this.NAME;
//    }
//    public void setNAME(String NAME) {
//        this.NAME = NAME;
//    }
//    public String getNAME_ABBREV() {
//        return this.NAME_ABBREV;
//    }
//    public void setNAME_ABBREV(String NAME_ABBREV) {
//        this.NAME_ABBREV = NAME_ABBREV;
//    }
//    public int getSUBSTATION_TYPE() {
//        return this.SUBSTATION_TYPE;
//    }
//    public void setSUBSTATION_TYPE(int SUBSTATION_TYPE) {
//        this.SUBSTATION_TYPE = SUBSTATION_TYPE;
//    }
//    public int getSUBSTATION_ATTRIBUTE() {
//        return this.SUBSTATION_ATTRIBUTE;
//    }
//    public void setSUBSTATION_ATTRIBUTE(int SUBSTATION_ATTRIBUTE) {
//        this.SUBSTATION_ATTRIBUTE = SUBSTATION_ATTRIBUTE;
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
//    public int getOPERATE_STATE() {
//        return this.OPERATE_STATE;
//    }
//    public void setOPERATE_STATE(int OPERATE_STATE) {
//        this.OPERATE_STATE = OPERATE_STATE;
//    }
//    public String getDC_JOB() {
//        return this.DC_JOB;
//    }
//    public void setDC_JOB(String DC_JOB) {
//        this.DC_JOB = DC_JOB;
//    }
//    public String getIS_IND() {
//        return this.IS_IND;
//    }
//    public void setIS_IND(String IS_IND) {
//        this.IS_IND = IS_IND;
//    }
//    public int getAV_MAX_VOL() {
//        return this.AV_MAX_VOL;
//    }
//    public void setAV_MAX_VOL(int AV_MAX_VOL) {
//        this.AV_MAX_VOL = AV_MAX_VOL;
//    }
//    public int getDC_MAX_VOL() {
//        return this.DC_MAX_VOL;
//    }
//    public void setDC_MAX_VOL(int DC_MAX_VOL) {
//        this.DC_MAX_VOL = DC_MAX_VOL;
//    }
//    public String getCONNECTIVE_PG_ID() {
//        return this.CONNECTIVE_PG_ID;
//    }
//    public void setCONNECTIVE_PG_ID(String CONNECTIVE_PG_ID) {
//        this.CONNECTIVE_PG_ID = CONNECTIVE_PG_ID;
//    }
//    public String getREGISTER_NAME() {
//        return this.REGISTER_NAME;
//    }
//    public void setREGISTER_NAME(String REGISTER_NAME) {
//        this.REGISTER_NAME = REGISTER_NAME;
//    }
//    public String getCOMPANY_ID() {
//        return this.COMPANY_ID;
//    }
//    public void setCOMPANY_ID(String COMPANY_ID) {
//        this.COMPANY_ID = COMPANY_ID;
//    }
//    public String getDCC_ID() {
//        return this.DCC_ID;
//    }
//    public void setDCC_ID(String DCC_ID) {
//        this.DCC_ID = DCC_ID;
//    }
//    public int getASSETS_OWNERSHIP() {
//        return this.ASSETS_OWNERSHIP;
//    }
//    public void setASSETS_OWNERSHIP(int ASSETS_OWNERSHIP) {
//        this.ASSETS_OWNERSHIP = ASSETS_OWNERSHIP;
//    }
//    public String getASSET_UNIT_ID() {
//        return this.ASSET_UNIT_ID;
//    }
//    public void setASSET_UNIT_ID(String ASSET_UNIT_ID) {
//        this.ASSET_UNIT_ID = ASSET_UNIT_ID;
//    }
//    public int getREGION() {
//        return this.REGION;
//    }
//    public void setREGION(int REGION) {
//        this.REGION = REGION;
//    }
//    public double getLONGITUDE() {
//        return this.LONGITUDE;
//    }
//    public void setLONGITUDE(double LONGITUDE) {
//        this.LONGITUDE = LONGITUDE;
//    }
//    public double getLATITUDE() {
//        return this.LATITUDE;
//    }
//    public void setLATITUDE(double LATITUDE) {
//        this.LATITUDE = LATITUDE;
//    }
//    public double getALTITUDE() {
//        return this.ALTITUDE;
//    }
//    public void setALTITUDE(double ALTITUDE) {
//        this.ALTITUDE = ALTITUDE;
//    }
//    public String getADDRESS() {
//        return this.ADDRESS;
//    }
//    public void setADDRESS(String ADDRESS) {
//        this.ADDRESS = ADDRESS;
//    }
//    public String getPOSTCODE() {
//        return this.POSTCODE;
//    }
//    public void setPOSTCODE(String POSTCODE) {
//        this.POSTCODE = POSTCODE;
//    }
//    public String getFAX_NO() {
//        return this.FAX_NO;
//    }
//    public void setFAX_NO(String FAX_NO) {
//        this.FAX_NO = FAX_NO;
//    }
//    public String getPHONE_NO() {
//        return this.PHONE_NO;
//    }
//    public void setPHONE_NO(String PHONE_NO) {
//        this.PHONE_NO = PHONE_NO;
//    }
//    public String getEMAIL() {
//        return this.EMAIL;
//    }
//    public void setEMAIL(String EMAIL) {
//        this.EMAIL = EMAIL;
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
//    public String getCHECK_STATUS() {
//        return this.CHECK_STATUS;
//    }
//    public void setCHECK_STATUS(String CHECK_STATUS) {
//        this.CHECK_STATUS = CHECK_STATUS;
//    }
//    public String getDATA_UNIT() {
//        return this.DATA_UNIT;
//    }
//    public void setDATA_UNIT(String DATA_UNIT) {
//        this.DATA_UNIT = DATA_UNIT;
//    }
//    public String getSCD_ID() {
//        return this.SCD_ID;
//    }
//    public void setSCD_ID(String SCD_ID) {
//        this.SCD_ID = SCD_ID;
//    }
//}
