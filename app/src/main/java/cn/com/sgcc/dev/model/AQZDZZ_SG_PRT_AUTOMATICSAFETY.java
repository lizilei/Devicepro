//package cn.com.sgcc.dev.model;
//
//import org.greenrobot.greendao.annotation.Convert;
//import org.greenrobot.greendao.annotation.Entity;
//import org.greenrobot.greendao.annotation.Id;
//import org.greenrobot.greendao.annotation.Property;
//import org.greenrobot.greendao.annotation.ToMany;
//
//import java.io.Serializable;
//import java.sql.Timestamp;
//import java.util.List;
//import org.greenrobot.greendao.annotation.Generated;
//import org.greenrobot.greendao.DaoException;
//import cn.com.sgcc.dev.dao.DaoSession;
//import cn.com.sgcc.dev.dao.RJBB_SG_PRO_DEV_VERDao;
//import cn.com.sgcc.dev.dao.TDXX_SG_PRO_DEV_CHANNELSDao;
//import cn.com.sgcc.dev.dao.LJQXX_SG_PRO_DEV_CONNECTERDao;
//import cn.com.sgcc.dev.dao.ICDXX_SG_PRO_DEV_ICDDao;
//import cn.com.sgcc.dev.dao.BKXX_SG_PRO_DEV_BOARDDao;
//import cn.com.sgcc.dev.dao.AQZDZZ_SG_PRT_AUTOMATICSAFETYDao;
//import cn.com.sgcc.dev.dao2.DaoSession;
//import cn.com.sgcc.dev.dao2.RJBB_SG_PRO_DEV_VERDao;
//import cn.com.sgcc.dev.dao2.TDXX_SG_PRO_DEV_CHANNELSDao;
//import cn.com.sgcc.dev.dao2.LJQXX_SG_PRO_DEV_CONNECTERDao;
//import cn.com.sgcc.dev.dao2.ICDXX_SG_PRO_DEV_ICDDao;
//import cn.com.sgcc.dev.dao2.BKXX_SG_PRO_DEV_BOARDDao;
//import cn.com.sgcc.dev.dao2.AQZDZZ_SG_PRT_AUTOMATICSAFETYDao;
//
///**
// * <p>@description:安全自动装置</p>
// * 表名：SG_PRT_AUTOMATICSAFETY
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//@Entity(nameInDb = "SG_PRT_AUTOMATICSAFETY")
//public class AQZDZZ_SG_PRT_AUTOMATICSAFETY implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;              // 装置ID
//    @Property(nameInDb = "RES_ID")
//    private String RES_ID;              //来源ID
//    @Property(nameInDb = "ED_TAG")
//    private String ED_TAG;              //修改状态
//    @Property(nameInDb = "NAME")
//    private String NAME;            // 装置名称
//    @Property(nameInDb = "ST_ID")
//    private String ST_ID;           // 所属厂站
//    @Property(nameInDb = "SERV_TEAM")
//    private String SERV_TEAM;       // 检修班组
//    @Property(nameInDb = "OPER_TEAM")
//    private String OPER_TEAM;       // 运维班组
//    @Property(nameInDb = "OPER_UNIT")
//    private String OPER_UNIT;       // 运检单位
//    @Property(nameInDb = "PROTECTOR_TYPE")
//    private int PROTECTOR_TYPE;  // 装置类别
//    @Property(nameInDb = "VOLTAGE_TYPE")
//    private int VOLTAGE_TYPE;    // 电压等级
//    @Property(nameInDb = "BAY_ID")
//    private String BAY_ID;          // 所属间隔
//    @Property(nameInDb = "SUB_CABINET")
//    private String SUB_CABINET;     // 所属屏柜
//    @Property(nameInDb = "DEVICE_VOLTAGE")
//    private int DEVICE_VOLTAGE;  // 装置电源电压
//    @Property(nameInDb = "IDENTIFY_CODE")
//    private String IDENTIFY_CODE;   // 设备识别代码
//    @Property(nameInDb = "PRODUCTIN_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp PRODUCTIN_DATE;  // 出厂日期
//    @Property(nameInDb = "PRO_SET")
//    private int PRO_SET;         // 套别
//    @Property(nameInDb = "FAC_ID")
//    private String FAC_ID;          // 生产厂家
//    @Property(nameInDb = "MODEL_ID")
//    private String MODEL_ID;        // 型号
//    @Property(nameInDb = "RUN_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp RUN_DATE;        // 投运日期
//    @Property(nameInDb = "EXIT_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp EXIT_DATE;       // 退运日期
//    @Property(nameInDb = "INCREASE_MODE")
//    private int INCREASE_MODE;   // 设备增加方式
//    @Property(nameInDb = "DEVICE_STATUS")
//    private int DEVICE_STATUS;   // 设备状态
//    @Property(nameInDb = "INSPECTION_CYCLE")
//    private double INSPECTION_CYCLE;// 定期检验周期
//    @Property(nameInDb = "ASSET_NATURE")
//    private int ASSET_NATURE;    // 资产性质
//    @Property(nameInDb = "ASSET_CODE")
//    private String ASSET_CODE;      // 资产编号
//    @Property(nameInDb = "DISPATCH_ORG_ID")
//    private String DISPATCH_ORG_ID; // 调度单位
//    @Property(nameInDb = "ASSET_UNIT_ID")
//    private String ASSET_UNIT_ID;   // 资产单位
//    @Property(nameInDb = "DESIG_NUNIT_ID")
//    private String DESIG_NUNIT_ID;  // 设计单位
//    @Property(nameInDb = "JOB_NUMBER")
//    private String JOB_NUMBER;      // 工程编号
//    @Property(nameInDb = "JOB_NAME")
//    private String JOB_NAME;        // 工程名称
//    @Property(nameInDb = "FUNCTION_CONFIG")
//    private String FUNCTION_CONFIG; // 设备功能配置
//    @Property(nameInDb = "SITE_ID")
//    private String SITE_ID;         // 所属安控系统
//    @Property(nameInDb = "SITE_TYPE")
//    private int SITE_TYPE;       // 安控站点类型
//    @Property(nameInDb = "COMMENT")
//    private String COMMENT;         // 备注
//    @Property(nameInDb = "STAMP")
//    private String STAMP;           // 更新标志
//    @Property(nameInDb = "OWNER")
//    private String OWNER;           // 拥有者
//    @Property(nameInDb = "IS_LOCAL")
//    private String IS_LOCAL;        // 是否就地化设备
//    @Property(nameInDb = "CONNECT_NUM")
//    private int CONNECT_NUM;     // 连接器组件数?
//    @Property(nameInDb = "SOCKET_FAC")
//    private String SOCKET_FAC;      // 连接器插座组?
//    @Property(nameInDb = "SOCKET_MODEL")
//    private String SOCKET_MODEL;    // 连接器插座组?
//    @Property(nameInDb = "CHECK_STATUS")
//    private int CHECK_STATUS;    // 审核状态
//    @Property(nameInDb = "DATA_UNIT")
//    private String DATA_UNIT;       // 数据报送单位
//    @Property(nameInDb = "RFID_FAC")
//    private String RFID_FAC;        // 电子标签制造厂家
//    @Property(nameInDb = "RFID_MODEL")
//    private String RFID_MODEL;      // 电子标签型号
//    @ToMany(referencedJoinProperty = "DEV_ID")
//    private List<BKXX_SG_PRO_DEV_BOARD> bkxxs;             //板卡信息
//    @ToMany(referencedJoinProperty = "DEV_ID")
//    private List<ICDXX_SG_PRO_DEV_ICD> icdxxs;           //ICD信息
//    @ToMany(referencedJoinProperty = "DEV_ID")
//    private List<LJQXX_SG_PRO_DEV_CONNECTER> ljqxxs;           //连接器信息
//    @ToMany(referencedJoinProperty = "DEV_ID")
//    private List<TDXX_SG_PRO_DEV_CHANNELS> tdxxs;           //通道信息
//    @ToMany(referencedJoinProperty = "DEV_ID")
//    private List<RJBB_SG_PRO_DEV_VER> rjbbs;           //软件版本
//    @Property(nameInDb = "ONCE_EQUIPMENT_ID")
//    private String ONCE_EQUIPMENT_ID;             // 一次设备ID
//    @Property(nameInDb = "ONCE_EQUIPMENT_TYPE")
//    private String ONCE_EQUIPMENT_TYPE;             // 一次设备类型
//    /** Used to resolve relations */
//    @Generated(hash = 2040040024)
//    private transient DaoSession daoSession;
//    /** Used for active entity operations. */
//    @Generated(hash = 558162234)
//    private transient AQZDZZ_SG_PRT_AUTOMATICSAFETYDao myDao;
//    @Generated(hash = 1921098494)
//    public AQZDZZ_SG_PRT_AUTOMATICSAFETY(String ID, String RES_ID, String ED_TAG,
//            String NAME, String ST_ID, String SERV_TEAM, String OPER_TEAM,
//            String OPER_UNIT, int PROTECTOR_TYPE, int VOLTAGE_TYPE, String BAY_ID,
//            String SUB_CABINET, int DEVICE_VOLTAGE, String IDENTIFY_CODE,
//            Timestamp PRODUCTIN_DATE, int PRO_SET, String FAC_ID, String MODEL_ID,
//            Timestamp RUN_DATE, Timestamp EXIT_DATE, int INCREASE_MODE,
//            int DEVICE_STATUS, double INSPECTION_CYCLE, int ASSET_NATURE,
//            String ASSET_CODE, String DISPATCH_ORG_ID, String ASSET_UNIT_ID,
//            String DESIG_NUNIT_ID, String JOB_NUMBER, String JOB_NAME,
//            String FUNCTION_CONFIG, String SITE_ID, int SITE_TYPE, String COMMENT,
//            String STAMP, String OWNER, String IS_LOCAL, int CONNECT_NUM,
//            String SOCKET_FAC, String SOCKET_MODEL, int CHECK_STATUS,
//            String DATA_UNIT, String RFID_FAC, String RFID_MODEL,
//            String ONCE_EQUIPMENT_ID, String ONCE_EQUIPMENT_TYPE) {
//        this.ID = ID;
//        this.RES_ID = RES_ID;
//        this.ED_TAG = ED_TAG;
//        this.NAME = NAME;
//        this.ST_ID = ST_ID;
//        this.SERV_TEAM = SERV_TEAM;
//        this.OPER_TEAM = OPER_TEAM;
//        this.OPER_UNIT = OPER_UNIT;
//        this.PROTECTOR_TYPE = PROTECTOR_TYPE;
//        this.VOLTAGE_TYPE = VOLTAGE_TYPE;
//        this.BAY_ID = BAY_ID;
//        this.SUB_CABINET = SUB_CABINET;
//        this.DEVICE_VOLTAGE = DEVICE_VOLTAGE;
//        this.IDENTIFY_CODE = IDENTIFY_CODE;
//        this.PRODUCTIN_DATE = PRODUCTIN_DATE;
//        this.PRO_SET = PRO_SET;
//        this.FAC_ID = FAC_ID;
//        this.MODEL_ID = MODEL_ID;
//        this.RUN_DATE = RUN_DATE;
//        this.EXIT_DATE = EXIT_DATE;
//        this.INCREASE_MODE = INCREASE_MODE;
//        this.DEVICE_STATUS = DEVICE_STATUS;
//        this.INSPECTION_CYCLE = INSPECTION_CYCLE;
//        this.ASSET_NATURE = ASSET_NATURE;
//        this.ASSET_CODE = ASSET_CODE;
//        this.DISPATCH_ORG_ID = DISPATCH_ORG_ID;
//        this.ASSET_UNIT_ID = ASSET_UNIT_ID;
//        this.DESIG_NUNIT_ID = DESIG_NUNIT_ID;
//        this.JOB_NUMBER = JOB_NUMBER;
//        this.JOB_NAME = JOB_NAME;
//        this.FUNCTION_CONFIG = FUNCTION_CONFIG;
//        this.SITE_ID = SITE_ID;
//        this.SITE_TYPE = SITE_TYPE;
//        this.COMMENT = COMMENT;
//        this.STAMP = STAMP;
//        this.OWNER = OWNER;
//        this.IS_LOCAL = IS_LOCAL;
//        this.CONNECT_NUM = CONNECT_NUM;
//        this.SOCKET_FAC = SOCKET_FAC;
//        this.SOCKET_MODEL = SOCKET_MODEL;
//        this.CHECK_STATUS = CHECK_STATUS;
//        this.DATA_UNIT = DATA_UNIT;
//        this.RFID_FAC = RFID_FAC;
//        this.RFID_MODEL = RFID_MODEL;
//        this.ONCE_EQUIPMENT_ID = ONCE_EQUIPMENT_ID;
//        this.ONCE_EQUIPMENT_TYPE = ONCE_EQUIPMENT_TYPE;
//    }
//    @Generated(hash = 683687816)
//    public AQZDZZ_SG_PRT_AUTOMATICSAFETY() {
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
//    public String getSERV_TEAM() {
//        return this.SERV_TEAM;
//    }
//    public void setSERV_TEAM(String SERV_TEAM) {
//        this.SERV_TEAM = SERV_TEAM;
//    }
//    public String getOPER_TEAM() {
//        return this.OPER_TEAM;
//    }
//    public void setOPER_TEAM(String OPER_TEAM) {
//        this.OPER_TEAM = OPER_TEAM;
//    }
//    public String getOPER_UNIT() {
//        return this.OPER_UNIT;
//    }
//    public void setOPER_UNIT(String OPER_UNIT) {
//        this.OPER_UNIT = OPER_UNIT;
//    }
//    public int getPROTECTOR_TYPE() {
//        return this.PROTECTOR_TYPE;
//    }
//    public void setPROTECTOR_TYPE(int PROTECTOR_TYPE) {
//        this.PROTECTOR_TYPE = PROTECTOR_TYPE;
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
//    public String getSUB_CABINET() {
//        return this.SUB_CABINET;
//    }
//    public void setSUB_CABINET(String SUB_CABINET) {
//        this.SUB_CABINET = SUB_CABINET;
//    }
//    public int getDEVICE_VOLTAGE() {
//        return this.DEVICE_VOLTAGE;
//    }
//    public void setDEVICE_VOLTAGE(int DEVICE_VOLTAGE) {
//        this.DEVICE_VOLTAGE = DEVICE_VOLTAGE;
//    }
//    public String getIDENTIFY_CODE() {
//        return this.IDENTIFY_CODE;
//    }
//    public void setIDENTIFY_CODE(String IDENTIFY_CODE) {
//        this.IDENTIFY_CODE = IDENTIFY_CODE;
//    }
//    public Timestamp getPRODUCTIN_DATE() {
//        return this.PRODUCTIN_DATE;
//    }
//    public void setPRODUCTIN_DATE(Timestamp PRODUCTIN_DATE) {
//        this.PRODUCTIN_DATE = PRODUCTIN_DATE;
//    }
//    public int getPRO_SET() {
//        return this.PRO_SET;
//    }
//    public void setPRO_SET(int PRO_SET) {
//        this.PRO_SET = PRO_SET;
//    }
//    public String getFAC_ID() {
//        return this.FAC_ID;
//    }
//    public void setFAC_ID(String FAC_ID) {
//        this.FAC_ID = FAC_ID;
//    }
//    public String getMODEL_ID() {
//        return this.MODEL_ID;
//    }
//    public void setMODEL_ID(String MODEL_ID) {
//        this.MODEL_ID = MODEL_ID;
//    }
//    public Timestamp getRUN_DATE() {
//        return this.RUN_DATE;
//    }
//    public void setRUN_DATE(Timestamp RUN_DATE) {
//        this.RUN_DATE = RUN_DATE;
//    }
//    public Timestamp getEXIT_DATE() {
//        return this.EXIT_DATE;
//    }
//    public void setEXIT_DATE(Timestamp EXIT_DATE) {
//        this.EXIT_DATE = EXIT_DATE;
//    }
//    public int getINCREASE_MODE() {
//        return this.INCREASE_MODE;
//    }
//    public void setINCREASE_MODE(int INCREASE_MODE) {
//        this.INCREASE_MODE = INCREASE_MODE;
//    }
//    public int getDEVICE_STATUS() {
//        return this.DEVICE_STATUS;
//    }
//    public void setDEVICE_STATUS(int DEVICE_STATUS) {
//        this.DEVICE_STATUS = DEVICE_STATUS;
//    }
//    public double getINSPECTION_CYCLE() {
//        return this.INSPECTION_CYCLE;
//    }
//    public void setINSPECTION_CYCLE(double INSPECTION_CYCLE) {
//        this.INSPECTION_CYCLE = INSPECTION_CYCLE;
//    }
//    public int getASSET_NATURE() {
//        return this.ASSET_NATURE;
//    }
//    public void setASSET_NATURE(int ASSET_NATURE) {
//        this.ASSET_NATURE = ASSET_NATURE;
//    }
//    public String getASSET_CODE() {
//        return this.ASSET_CODE;
//    }
//    public void setASSET_CODE(String ASSET_CODE) {
//        this.ASSET_CODE = ASSET_CODE;
//    }
//    public String getDISPATCH_ORG_ID() {
//        return this.DISPATCH_ORG_ID;
//    }
//    public void setDISPATCH_ORG_ID(String DISPATCH_ORG_ID) {
//        this.DISPATCH_ORG_ID = DISPATCH_ORG_ID;
//    }
//    public String getASSET_UNIT_ID() {
//        return this.ASSET_UNIT_ID;
//    }
//    public void setASSET_UNIT_ID(String ASSET_UNIT_ID) {
//        this.ASSET_UNIT_ID = ASSET_UNIT_ID;
//    }
//    public String getDESIG_NUNIT_ID() {
//        return this.DESIG_NUNIT_ID;
//    }
//    public void setDESIG_NUNIT_ID(String DESIG_NUNIT_ID) {
//        this.DESIG_NUNIT_ID = DESIG_NUNIT_ID;
//    }
//    public String getJOB_NUMBER() {
//        return this.JOB_NUMBER;
//    }
//    public void setJOB_NUMBER(String JOB_NUMBER) {
//        this.JOB_NUMBER = JOB_NUMBER;
//    }
//    public String getJOB_NAME() {
//        return this.JOB_NAME;
//    }
//    public void setJOB_NAME(String JOB_NAME) {
//        this.JOB_NAME = JOB_NAME;
//    }
//    public String getFUNCTION_CONFIG() {
//        return this.FUNCTION_CONFIG;
//    }
//    public void setFUNCTION_CONFIG(String FUNCTION_CONFIG) {
//        this.FUNCTION_CONFIG = FUNCTION_CONFIG;
//    }
//    public String getSITE_ID() {
//        return this.SITE_ID;
//    }
//    public void setSITE_ID(String SITE_ID) {
//        this.SITE_ID = SITE_ID;
//    }
//    public int getSITE_TYPE() {
//        return this.SITE_TYPE;
//    }
//    public void setSITE_TYPE(int SITE_TYPE) {
//        this.SITE_TYPE = SITE_TYPE;
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
//    public String getIS_LOCAL() {
//        return this.IS_LOCAL;
//    }
//    public void setIS_LOCAL(String IS_LOCAL) {
//        this.IS_LOCAL = IS_LOCAL;
//    }
//    public int getCONNECT_NUM() {
//        return this.CONNECT_NUM;
//    }
//    public void setCONNECT_NUM(int CONNECT_NUM) {
//        this.CONNECT_NUM = CONNECT_NUM;
//    }
//    public String getSOCKET_FAC() {
//        return this.SOCKET_FAC;
//    }
//    public void setSOCKET_FAC(String SOCKET_FAC) {
//        this.SOCKET_FAC = SOCKET_FAC;
//    }
//    public String getSOCKET_MODEL() {
//        return this.SOCKET_MODEL;
//    }
//    public void setSOCKET_MODEL(String SOCKET_MODEL) {
//        this.SOCKET_MODEL = SOCKET_MODEL;
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
//    public String getRFID_FAC() {
//        return this.RFID_FAC;
//    }
//    public void setRFID_FAC(String RFID_FAC) {
//        this.RFID_FAC = RFID_FAC;
//    }
//    public String getRFID_MODEL() {
//        return this.RFID_MODEL;
//    }
//    public void setRFID_MODEL(String RFID_MODEL) {
//        this.RFID_MODEL = RFID_MODEL;
//    }
//    public String getONCE_EQUIPMENT_ID() {
//        return this.ONCE_EQUIPMENT_ID;
//    }
//    public void setONCE_EQUIPMENT_ID(String ONCE_EQUIPMENT_ID) {
//        this.ONCE_EQUIPMENT_ID = ONCE_EQUIPMENT_ID;
//    }
//    public String getONCE_EQUIPMENT_TYPE() {
//        return this.ONCE_EQUIPMENT_TYPE;
//    }
//    public void setONCE_EQUIPMENT_TYPE(String ONCE_EQUIPMENT_TYPE) {
//        this.ONCE_EQUIPMENT_TYPE = ONCE_EQUIPMENT_TYPE;
//    }
//    /**
//     * To-many relationship, resolved on first access (and after reset).
//     * Changes to to-many relations are not persisted, make changes to the target entity.
//     */
//    @Generated(hash = 1183110478)
//    public List<BKXX_SG_PRO_DEV_BOARD> getBkxxs() {
//        if (bkxxs == null) {
//            final DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            BKXX_SG_PRO_DEV_BOARDDao targetDao = daoSession
//                    .getBKXX_SG_PRO_DEV_BOARDDao();
//            List<BKXX_SG_PRO_DEV_BOARD> bkxxsNew = targetDao
//                    ._queryAQZDZZ_SG_PRT_AUTOMATICSAFETY_Bkxxs(ID);
//            synchronized (this) {
//                if (bkxxs == null) {
//                    bkxxs = bkxxsNew;
//                }
//            }
//        }
//        return bkxxs;
//    }
//    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
//    @Generated(hash = 1347808015)
//    public synchronized void resetBkxxs() {
//        bkxxs = null;
//    }
//    /**
//     * To-many relationship, resolved on first access (and after reset).
//     * Changes to to-many relations are not persisted, make changes to the target entity.
//     */
//    @Generated(hash = 1113724308)
//    public List<ICDXX_SG_PRO_DEV_ICD> getIcdxxs() {
//        if (icdxxs == null) {
//            final DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            ICDXX_SG_PRO_DEV_ICDDao targetDao = daoSession
//                    .getICDXX_SG_PRO_DEV_ICDDao();
//            List<ICDXX_SG_PRO_DEV_ICD> icdxxsNew = targetDao
//                    ._queryAQZDZZ_SG_PRT_AUTOMATICSAFETY_Icdxxs(ID);
//            synchronized (this) {
//                if (icdxxs == null) {
//                    icdxxs = icdxxsNew;
//                }
//            }
//        }
//        return icdxxs;
//    }
//    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
//    @Generated(hash = 447647967)
//    public synchronized void resetIcdxxs() {
//        icdxxs = null;
//    }
//    /**
//     * To-many relationship, resolved on first access (and after reset).
//     * Changes to to-many relations are not persisted, make changes to the target entity.
//     */
//    @Generated(hash = 2020028834)
//    public List<LJQXX_SG_PRO_DEV_CONNECTER> getLjqxxs() {
//        if (ljqxxs == null) {
//            final DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            LJQXX_SG_PRO_DEV_CONNECTERDao targetDao = daoSession
//                    .getLJQXX_SG_PRO_DEV_CONNECTERDao();
//            List<LJQXX_SG_PRO_DEV_CONNECTER> ljqxxsNew = targetDao
//                    ._queryAQZDZZ_SG_PRT_AUTOMATICSAFETY_Ljqxxs(ID);
//            synchronized (this) {
//                if (ljqxxs == null) {
//                    ljqxxs = ljqxxsNew;
//                }
//            }
//        }
//        return ljqxxs;
//    }
//    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
//    @Generated(hash = 1813843247)
//    public synchronized void resetLjqxxs() {
//        ljqxxs = null;
//    }
//    /**
//     * To-many relationship, resolved on first access (and after reset).
//     * Changes to to-many relations are not persisted, make changes to the target entity.
//     */
//    @Generated(hash = 1383263488)
//    public List<TDXX_SG_PRO_DEV_CHANNELS> getTdxxs() {
//        if (tdxxs == null) {
//            final DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            TDXX_SG_PRO_DEV_CHANNELSDao targetDao = daoSession
//                    .getTDXX_SG_PRO_DEV_CHANNELSDao();
//            List<TDXX_SG_PRO_DEV_CHANNELS> tdxxsNew = targetDao
//                    ._queryAQZDZZ_SG_PRT_AUTOMATICSAFETY_Tdxxs(ID);
//            synchronized (this) {
//                if (tdxxs == null) {
//                    tdxxs = tdxxsNew;
//                }
//            }
//        }
//        return tdxxs;
//    }
//    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
//    @Generated(hash = 1900868034)
//    public synchronized void resetTdxxs() {
//        tdxxs = null;
//    }
//    /**
//     * To-many relationship, resolved on first access (and after reset).
//     * Changes to to-many relations are not persisted, make changes to the target entity.
//     */
//    @Generated(hash = 2025670267)
//    public List<RJBB_SG_PRO_DEV_VER> getRjbbs() {
//        if (rjbbs == null) {
//            final DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            RJBB_SG_PRO_DEV_VERDao targetDao = daoSession
//                    .getRJBB_SG_PRO_DEV_VERDao();
//            List<RJBB_SG_PRO_DEV_VER> rjbbsNew = targetDao
//                    ._queryAQZDZZ_SG_PRT_AUTOMATICSAFETY_Rjbbs(ID);
//            synchronized (this) {
//                if (rjbbs == null) {
//                    rjbbs = rjbbsNew;
//                }
//            }
//        }
//        return rjbbs;
//    }
//    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
//    @Generated(hash = 232552189)
//    public synchronized void resetRjbbs() {
//        rjbbs = null;
//    }
//    /**
//     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
//     * Entity must attached to an entity context.
//     */
//    @Generated(hash = 128553479)
//    public void delete() {
//        if (myDao == null) {
//            throw new DaoException("Entity is detached from DAO context");
//        }
//        myDao.delete(this);
//    }
//    /**
//     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
//     * Entity must attached to an entity context.
//     */
//    @Generated(hash = 1942392019)
//    public void refresh() {
//        if (myDao == null) {
//            throw new DaoException("Entity is detached from DAO context");
//        }
//        myDao.refresh(this);
//    }
//    /**
//     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
//     * Entity must attached to an entity context.
//     */
//    @Generated(hash = 713229351)
//    public void update() {
//        if (myDao == null) {
//            throw new DaoException("Entity is detached from DAO context");
//        }
//        myDao.update(this);
//    }
//    /** called by internal mechanisms, do not call yourself. */
//    @Generated(hash = 612278143)
//    public void __setDaoSession(DaoSession daoSession) {
//        this.daoSession = daoSession;
//        myDao = daoSession != null ? daoSession.getAQZDZZ_SG_PRT_AUTOMATICSAFETYDao() : null;
//    }
//}
