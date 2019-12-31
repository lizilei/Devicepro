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
//import cn.com.sgcc.dev.dao.BZ_RJBB_SG_PRT_SOFTWAREVERSIONDao;
//import cn.com.sgcc.dev.dao.BZ_BKXX_SG_PRT_FACRMA_BOARDDao;
//import cn.com.sgcc.dev.dao.CCXX_SG_PRT_FACRMA_INFODao;
//import cn.com.sgcc.dev.dao2.DaoSession;
//import cn.com.sgcc.dev.dao2.BZ_RJBB_SG_PRT_SOFTWAREVERSIONDao;
//import cn.com.sgcc.dev.dao2.BZ_BKXX_SG_PRT_FACRMA_BOARDDao;
//import cn.com.sgcc.dev.dao2.CCXX_SG_PRT_FACRMA_INFODao;
//
///**
// * <p>@description:出厂信息</p>
// * 表名：SG_PRT_FACRMA_INFO
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//@Entity(nameInDb = "SG_PRT_FACRMA_INFO")
//public class CCXX_SG_PRT_FACRMA_INFO implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;                 // 出厂信息ID
//    @Property(nameInDb = "IDENTIFY_CODE")
//    private String IDENTIFY_CODE;      // 设备识别代码
//    @Property(nameInDb = "VER_ID")
//    private String VER_ID;             // 软件版本ID
//    @Property(nameInDb = "ICD_ID")
//    private String ICD_ID;             // ICD文件ID
//    @Property(nameInDb = "FACRMA_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp FACRMA_DATE;        // 出厂日期
//    @Property(nameInDb = "CARD_NUMBER")
//    private int CARD_NUMBER;        // 板卡数量
//    @Property(nameInDb = "TRANSF_TYPE")
//    private int TRANSF_TYPE;        // 互感器类型
//    @Property(nameInDb = "TYPE_REFINE")
//    private int TYPE_REFINE;        // 装置类别细化
//    @Property(nameInDb = "FUNCTION_CONFIG")
//    private String FUNCTION_CONFIG;    // 装置功能配置
//    @Property(nameInDb = "IS_AFFICHE")
//    private String IS_AFFICHE;         // 是否通过专业检测
//    @Property(nameInDb = "CT_RATED_CURRENT")
//    private int CT_RATED_CURRENT;   // CT二次额定电流
//    @Property(nameInDb = "DC_RATED_VOLTAGE")
//    private String DC_RATED_VOLTAGE;   // 直流额定电压
//    @Property(nameInDb = "CHANNELS_TYPE")
//    private String CHANNELS_TYPE;      // 通道类型
//    @Property(nameInDb = "IS_LOCAL")
//    private String IS_LOCAL;           // 是否就地化装置
//    @Property(nameInDb = "FRID_FACTORY")
//    private String FRID_FACTORY;       // 电子标签制造厂家
//    @Property(nameInDb = "FRID_TYPE")
//    private String FRID_TYPE;          // 电子标签型号
//    @Property(nameInDb = "CONNECT_NUMBER")
//    private int CONNECT_NUMBER;     // 连接器数量
//    @Property(nameInDb = "SOCKET_FACTORY")
//    private String SOCKET_FACTORY;     // 连接器（插座）制造厂家
//    @Property(nameInDb = "SOCKET_MODEL")
//    private String SOCKET_MODEL;       // 连接器（插座）型号
//    @Property(nameInDb = "SAFETY_STATION_TYPE")
//    private String SAFETY_STATION_TYPE;// 安控站点类型
//    @Property(nameInDb = "RECEIVE_FIBER_MODE")
//    private String RECEIVE_FIBER_MODE; // 接收光纤接口模式
//    @Property(nameInDb = "SWITCH_FUNCTION")
//    private String SWITCH_FUNCTION;    // 交换机功能
//    @Property(nameInDb = "SWITCH_NUMBER")
//    private int SWITCH_NUMBER;      // 交换机级联数
//    @Property(nameInDb = "IS_RSTP")
//    private String IS_RSTP;            // 是否支持RSTP环网
//    @Property(nameInDb = "IS_1588")
//    private String IS_1588;            // 是否支持1588对时
//    @Property(nameInDb = "IS_DYNA_MULT")
//    private String IS_DYNA_MULT;       // 是否支持动态组播管理
//    @Property(nameInDb = "IS_61850")
//    private String IS_61850;           // 是否支持采用IEC61850上送交换机信息
//    @Property(nameInDb = "SEND_PROBINCE_CODE")
//    private String SEND_PROBINCE_CODE; // 发往省份
//    @Property(nameInDb = "CHECK_STATUS")
//    private int CHECK_STATUS;       // 审核状态
//    //-----------------------
//    @ToMany(referencedJoinProperty = "IDENTIFY_CODE")
//    private List<BZ_BKXX_SG_PRT_FACRMA_BOARD> bz_bkxx;        //板卡信息
//    @ToMany(referencedJoinProperty = "MODEL_ID")
//    private List<BZ_RJBB_SG_PRT_SOFTWAREVERSION> bz_rjbb;        //软件版本信息
//    /** Used to resolve relations */
//    @Generated(hash = 2040040024)
//    private transient DaoSession daoSession;
//    /** Used for active entity operations. */
//    @Generated(hash = 42001351)
//    private transient CCXX_SG_PRT_FACRMA_INFODao myDao;
//    @Generated(hash = 1790390116)
//    public CCXX_SG_PRT_FACRMA_INFO(String ID, String IDENTIFY_CODE, String VER_ID,
//            String ICD_ID, Timestamp FACRMA_DATE, int CARD_NUMBER, int TRANSF_TYPE,
//            int TYPE_REFINE, String FUNCTION_CONFIG, String IS_AFFICHE,
//            int CT_RATED_CURRENT, String DC_RATED_VOLTAGE, String CHANNELS_TYPE,
//            String IS_LOCAL, String FRID_FACTORY, String FRID_TYPE,
//            int CONNECT_NUMBER, String SOCKET_FACTORY, String SOCKET_MODEL,
//            String SAFETY_STATION_TYPE, String RECEIVE_FIBER_MODE,
//            String SWITCH_FUNCTION, int SWITCH_NUMBER, String IS_RSTP,
//            String IS_1588, String IS_DYNA_MULT, String IS_61850,
//            String SEND_PROBINCE_CODE, int CHECK_STATUS) {
//        this.ID = ID;
//        this.IDENTIFY_CODE = IDENTIFY_CODE;
//        this.VER_ID = VER_ID;
//        this.ICD_ID = ICD_ID;
//        this.FACRMA_DATE = FACRMA_DATE;
//        this.CARD_NUMBER = CARD_NUMBER;
//        this.TRANSF_TYPE = TRANSF_TYPE;
//        this.TYPE_REFINE = TYPE_REFINE;
//        this.FUNCTION_CONFIG = FUNCTION_CONFIG;
//        this.IS_AFFICHE = IS_AFFICHE;
//        this.CT_RATED_CURRENT = CT_RATED_CURRENT;
//        this.DC_RATED_VOLTAGE = DC_RATED_VOLTAGE;
//        this.CHANNELS_TYPE = CHANNELS_TYPE;
//        this.IS_LOCAL = IS_LOCAL;
//        this.FRID_FACTORY = FRID_FACTORY;
//        this.FRID_TYPE = FRID_TYPE;
//        this.CONNECT_NUMBER = CONNECT_NUMBER;
//        this.SOCKET_FACTORY = SOCKET_FACTORY;
//        this.SOCKET_MODEL = SOCKET_MODEL;
//        this.SAFETY_STATION_TYPE = SAFETY_STATION_TYPE;
//        this.RECEIVE_FIBER_MODE = RECEIVE_FIBER_MODE;
//        this.SWITCH_FUNCTION = SWITCH_FUNCTION;
//        this.SWITCH_NUMBER = SWITCH_NUMBER;
//        this.IS_RSTP = IS_RSTP;
//        this.IS_1588 = IS_1588;
//        this.IS_DYNA_MULT = IS_DYNA_MULT;
//        this.IS_61850 = IS_61850;
//        this.SEND_PROBINCE_CODE = SEND_PROBINCE_CODE;
//        this.CHECK_STATUS = CHECK_STATUS;
//    }
//    @Generated(hash = 401499299)
//    public CCXX_SG_PRT_FACRMA_INFO() {
//    }
//    public String getID() {
//        return this.ID;
//    }
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//    public String getIDENTIFY_CODE() {
//        return this.IDENTIFY_CODE;
//    }
//    public void setIDENTIFY_CODE(String IDENTIFY_CODE) {
//        this.IDENTIFY_CODE = IDENTIFY_CODE;
//    }
//    public String getVER_ID() {
//        return this.VER_ID;
//    }
//    public void setVER_ID(String VER_ID) {
//        this.VER_ID = VER_ID;
//    }
//    public String getICD_ID() {
//        return this.ICD_ID;
//    }
//    public void setICD_ID(String ICD_ID) {
//        this.ICD_ID = ICD_ID;
//    }
//    public Timestamp getFACRMA_DATE() {
//        return this.FACRMA_DATE;
//    }
//    public void setFACRMA_DATE(Timestamp FACRMA_DATE) {
//        this.FACRMA_DATE = FACRMA_DATE;
//    }
//    public int getCARD_NUMBER() {
//        return this.CARD_NUMBER;
//    }
//    public void setCARD_NUMBER(int CARD_NUMBER) {
//        this.CARD_NUMBER = CARD_NUMBER;
//    }
//    public int getTRANSF_TYPE() {
//        return this.TRANSF_TYPE;
//    }
//    public void setTRANSF_TYPE(int TRANSF_TYPE) {
//        this.TRANSF_TYPE = TRANSF_TYPE;
//    }
//    public int getTYPE_REFINE() {
//        return this.TYPE_REFINE;
//    }
//    public void setTYPE_REFINE(int TYPE_REFINE) {
//        this.TYPE_REFINE = TYPE_REFINE;
//    }
//    public String getFUNCTION_CONFIG() {
//        return this.FUNCTION_CONFIG;
//    }
//    public void setFUNCTION_CONFIG(String FUNCTION_CONFIG) {
//        this.FUNCTION_CONFIG = FUNCTION_CONFIG;
//    }
//    public String getIS_AFFICHE() {
//        return this.IS_AFFICHE;
//    }
//    public void setIS_AFFICHE(String IS_AFFICHE) {
//        this.IS_AFFICHE = IS_AFFICHE;
//    }
//    public int getCT_RATED_CURRENT() {
//        return this.CT_RATED_CURRENT;
//    }
//    public void setCT_RATED_CURRENT(int CT_RATED_CURRENT) {
//        this.CT_RATED_CURRENT = CT_RATED_CURRENT;
//    }
//    public String getDC_RATED_VOLTAGE() {
//        return this.DC_RATED_VOLTAGE;
//    }
//    public void setDC_RATED_VOLTAGE(String DC_RATED_VOLTAGE) {
//        this.DC_RATED_VOLTAGE = DC_RATED_VOLTAGE;
//    }
//    public String getCHANNELS_TYPE() {
//        return this.CHANNELS_TYPE;
//    }
//    public void setCHANNELS_TYPE(String CHANNELS_TYPE) {
//        this.CHANNELS_TYPE = CHANNELS_TYPE;
//    }
//    public String getIS_LOCAL() {
//        return this.IS_LOCAL;
//    }
//    public void setIS_LOCAL(String IS_LOCAL) {
//        this.IS_LOCAL = IS_LOCAL;
//    }
//    public String getFRID_FACTORY() {
//        return this.FRID_FACTORY;
//    }
//    public void setFRID_FACTORY(String FRID_FACTORY) {
//        this.FRID_FACTORY = FRID_FACTORY;
//    }
//    public String getFRID_TYPE() {
//        return this.FRID_TYPE;
//    }
//    public void setFRID_TYPE(String FRID_TYPE) {
//        this.FRID_TYPE = FRID_TYPE;
//    }
//    public int getCONNECT_NUMBER() {
//        return this.CONNECT_NUMBER;
//    }
//    public void setCONNECT_NUMBER(int CONNECT_NUMBER) {
//        this.CONNECT_NUMBER = CONNECT_NUMBER;
//    }
//    public String getSOCKET_FACTORY() {
//        return this.SOCKET_FACTORY;
//    }
//    public void setSOCKET_FACTORY(String SOCKET_FACTORY) {
//        this.SOCKET_FACTORY = SOCKET_FACTORY;
//    }
//    public String getSOCKET_MODEL() {
//        return this.SOCKET_MODEL;
//    }
//    public void setSOCKET_MODEL(String SOCKET_MODEL) {
//        this.SOCKET_MODEL = SOCKET_MODEL;
//    }
//    public String getSAFETY_STATION_TYPE() {
//        return this.SAFETY_STATION_TYPE;
//    }
//    public void setSAFETY_STATION_TYPE(String SAFETY_STATION_TYPE) {
//        this.SAFETY_STATION_TYPE = SAFETY_STATION_TYPE;
//    }
//    public String getRECEIVE_FIBER_MODE() {
//        return this.RECEIVE_FIBER_MODE;
//    }
//    public void setRECEIVE_FIBER_MODE(String RECEIVE_FIBER_MODE) {
//        this.RECEIVE_FIBER_MODE = RECEIVE_FIBER_MODE;
//    }
//    public String getSWITCH_FUNCTION() {
//        return this.SWITCH_FUNCTION;
//    }
//    public void setSWITCH_FUNCTION(String SWITCH_FUNCTION) {
//        this.SWITCH_FUNCTION = SWITCH_FUNCTION;
//    }
//    public int getSWITCH_NUMBER() {
//        return this.SWITCH_NUMBER;
//    }
//    public void setSWITCH_NUMBER(int SWITCH_NUMBER) {
//        this.SWITCH_NUMBER = SWITCH_NUMBER;
//    }
//    public String getIS_RSTP() {
//        return this.IS_RSTP;
//    }
//    public void setIS_RSTP(String IS_RSTP) {
//        this.IS_RSTP = IS_RSTP;
//    }
//    public String getIS_1588() {
//        return this.IS_1588;
//    }
//    public void setIS_1588(String IS_1588) {
//        this.IS_1588 = IS_1588;
//    }
//    public String getIS_DYNA_MULT() {
//        return this.IS_DYNA_MULT;
//    }
//    public void setIS_DYNA_MULT(String IS_DYNA_MULT) {
//        this.IS_DYNA_MULT = IS_DYNA_MULT;
//    }
//    public String getIS_61850() {
//        return this.IS_61850;
//    }
//    public void setIS_61850(String IS_61850) {
//        this.IS_61850 = IS_61850;
//    }
//    public String getSEND_PROBINCE_CODE() {
//        return this.SEND_PROBINCE_CODE;
//    }
//    public void setSEND_PROBINCE_CODE(String SEND_PROBINCE_CODE) {
//        this.SEND_PROBINCE_CODE = SEND_PROBINCE_CODE;
//    }
//    public int getCHECK_STATUS() {
//        return this.CHECK_STATUS;
//    }
//    public void setCHECK_STATUS(int CHECK_STATUS) {
//        this.CHECK_STATUS = CHECK_STATUS;
//    }
//    /**
//     * To-many relationship, resolved on first access (and after reset).
//     * Changes to to-many relations are not persisted, make changes to the target entity.
//     */
//    @Generated(hash = 1286797338)
//    public List<BZ_BKXX_SG_PRT_FACRMA_BOARD> getBz_bkxx() {
//        if (bz_bkxx == null) {
//            final DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            BZ_BKXX_SG_PRT_FACRMA_BOARDDao targetDao = daoSession
//                    .getBZ_BKXX_SG_PRT_FACRMA_BOARDDao();
//            List<BZ_BKXX_SG_PRT_FACRMA_BOARD> bz_bkxxNew = targetDao
//                    ._queryCCXX_SG_PRT_FACRMA_INFO_Bz_bkxx(ID);
//            synchronized (this) {
//                if (bz_bkxx == null) {
//                    bz_bkxx = bz_bkxxNew;
//                }
//            }
//        }
//        return bz_bkxx;
//    }
//    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
//    @Generated(hash = 1479279868)
//    public synchronized void resetBz_bkxx() {
//        bz_bkxx = null;
//    }
//    /**
//     * To-many relationship, resolved on first access (and after reset).
//     * Changes to to-many relations are not persisted, make changes to the target entity.
//     */
//    @Generated(hash = 764381461)
//    public List<BZ_RJBB_SG_PRT_SOFTWAREVERSION> getBz_rjbb() {
//        if (bz_rjbb == null) {
//            final DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            BZ_RJBB_SG_PRT_SOFTWAREVERSIONDao targetDao = daoSession
//                    .getBZ_RJBB_SG_PRT_SOFTWAREVERSIONDao();
//            List<BZ_RJBB_SG_PRT_SOFTWAREVERSION> bz_rjbbNew = targetDao
//                    ._queryCCXX_SG_PRT_FACRMA_INFO_Bz_rjbb(ID);
//            synchronized (this) {
//                if (bz_rjbb == null) {
//                    bz_rjbb = bz_rjbbNew;
//                }
//            }
//        }
//        return bz_rjbb;
//    }
//    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
//    @Generated(hash = 253362698)
//    public synchronized void resetBz_rjbb() {
//        bz_rjbb = null;
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
//    @Generated(hash = 954081506)
//    public void __setDaoSession(DaoSession daoSession) {
//        this.daoSession = daoSession;
//        myDao = daoSession != null ? daoSession.getCCXX_SG_PRT_FACRMA_INFODao() : null;
//    }
//}
