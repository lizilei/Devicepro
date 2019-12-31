//package cn.com.sgcc.dev.model;
//
//import org.greenrobot.greendao.annotation.Entity;
//import org.greenrobot.greendao.annotation.Id;
//import org.greenrobot.greendao.annotation.Property;
//import org.greenrobot.greendao.annotation.ToMany;
//
//import java.io.Serializable;
//import java.util.List;
//import org.greenrobot.greendao.annotation.Generated;
//import org.greenrobot.greendao.DaoException;
//import cn.com.sgcc.dev.dao.DaoSession;
//import cn.com.sgcc.dev.dao.BZ_ICDXX_SG_PRT_ICDINFORMATIONDao;
//import cn.com.sgcc.dev.dao.BZ_RJBB_SG_PRT_SOFTWAREVERSIONDao;
//import cn.com.sgcc.dev.dao.BHSBXHB_SG_PRT_DEVICEMODELDao;
//import cn.com.sgcc.dev.dao2.DaoSession;
//import cn.com.sgcc.dev.dao2.BZ_ICDXX_SG_PRT_ICDINFORMATIONDao;
//import cn.com.sgcc.dev.dao2.BZ_RJBB_SG_PRT_SOFTWAREVERSIONDao;
//import cn.com.sgcc.dev.dao2.BHSBXHB_SG_PRT_DEVICEMODELDao;
//
///**
// * <p>@description:型号信息</p>
// * 表名：SG_PRT_DEVICEMODEL
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//
//@Entity(nameInDb = "SG_PRT_DEVICEMODEL")
//public class BHSBXHB_SG_PRT_DEVICEMODEL implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;               // 型号ID
//    @Property(nameInDb = "MODEL_CODE")
//    private String MODEL_CODE;       // 型号编码
//    @Property(nameInDb = "NAME")
//    private String NAME;             // 型号名称
//    @Property(nameInDb = "PROTECTION_CLASS")
//    private int PROTECTION_CLASS; // 装置类别
//    @Property(nameInDb = "PROTECTION_TYPE")
//    private int PROTECTION_TYPE;  // 装置类型
//    @Property(nameInDb = "FAC_ID")
//    private String FAC_ID;           // 制造厂家
//    @Property(nameInDb = "IS_DOMESTIC")
//    private String IS_DOMESTIC;      // 是否国产
//    @Property(nameInDb = "IS_SUB_MODULE")
//    private String IS_SUB_MODULE;    // 是否分模块
//    @Property(nameInDb = "COMMENT")
//    private String COMMENT;          // 备注
//    @Property(nameInDb = "STAMP")
//    private String STAMP;            // 更新标志
//    @Property(nameInDb = "OWNER")
//    private String OWNER;            // 拥有者
//    @Property(nameInDb = "IS_SIX_UNITY")
//    private String IS_SIX_UNITY;     // 是否六统一设备
//    @Property(nameInDb = "SIX_UNITY_VERSION")
//    private int SIX_UNITY_VERSION;// 六统一标准版本
//    @Property(nameInDb = "IS_START_USING")
//    private String IS_START_USING;   // 是否启用
//    @Property(nameInDb = "BATCH")
//    private String BATCH;            // 发布批次
//    @Property(nameInDb = "FAM_ID")
//    private String FAM_ID;           // 家族型号ID
//    @Property(nameInDb = "CHECK_STATUS")
//    private int CHECK_STATUS;     // 审核状态
//    @Property(nameInDb = "DATA_UNIT")
//    private String DATA_UNIT;        // 数据报送单位
//    @Property(nameInDb = "TECH_NOTE")
//    private String TECH_NOTE;        // 设备技术说明书（附件）
//    @Property(nameInDb = "MAINTAIN_NOTE")
//    private String MAINTAIN_NOTE;    // 运行维护手册（附件）
//    @Property(nameInDb = "XSH_REPORT")
//    private String XSH_REPORT;       // 型式试验报告（附件）
//    @Property(nameInDb = "DM_REPORT")
//    private String DM_REPORT;        // 动模试验报告（附件）
//    // -----------------------------------------------
//    @ToMany(referencedJoinProperty = "MODEL_ID")
//    private List<BZ_RJBB_SG_PRT_SOFTWAREVERSION> bz_rjbb;          // 软件版本信息
//    @ToMany(referencedJoinProperty = "ID")
//    private List<BZ_ICDXX_SG_PRT_ICDINFORMATION> bz_icdxx;         // icd信息
//    /** Used to resolve relations */
//    @Generated(hash = 2040040024)
//    private transient DaoSession daoSession;
//    /** Used for active entity operations. */
//    @Generated(hash = 1216500925)
//    private transient BHSBXHB_SG_PRT_DEVICEMODELDao myDao;
//    @Generated(hash = 1513554520)
//    public BHSBXHB_SG_PRT_DEVICEMODEL(String ID, String MODEL_CODE, String NAME,
//            int PROTECTION_CLASS, int PROTECTION_TYPE, String FAC_ID,
//            String IS_DOMESTIC, String IS_SUB_MODULE, String COMMENT, String STAMP,
//            String OWNER, String IS_SIX_UNITY, int SIX_UNITY_VERSION,
//            String IS_START_USING, String BATCH, String FAM_ID, int CHECK_STATUS,
//            String DATA_UNIT, String TECH_NOTE, String MAINTAIN_NOTE,
//            String XSH_REPORT, String DM_REPORT) {
//        this.ID = ID;
//        this.MODEL_CODE = MODEL_CODE;
//        this.NAME = NAME;
//        this.PROTECTION_CLASS = PROTECTION_CLASS;
//        this.PROTECTION_TYPE = PROTECTION_TYPE;
//        this.FAC_ID = FAC_ID;
//        this.IS_DOMESTIC = IS_DOMESTIC;
//        this.IS_SUB_MODULE = IS_SUB_MODULE;
//        this.COMMENT = COMMENT;
//        this.STAMP = STAMP;
//        this.OWNER = OWNER;
//        this.IS_SIX_UNITY = IS_SIX_UNITY;
//        this.SIX_UNITY_VERSION = SIX_UNITY_VERSION;
//        this.IS_START_USING = IS_START_USING;
//        this.BATCH = BATCH;
//        this.FAM_ID = FAM_ID;
//        this.CHECK_STATUS = CHECK_STATUS;
//        this.DATA_UNIT = DATA_UNIT;
//        this.TECH_NOTE = TECH_NOTE;
//        this.MAINTAIN_NOTE = MAINTAIN_NOTE;
//        this.XSH_REPORT = XSH_REPORT;
//        this.DM_REPORT = DM_REPORT;
//    }
//    @Generated(hash = 1953025845)
//    public BHSBXHB_SG_PRT_DEVICEMODEL() {
//    }
//    public String getID() {
//        return this.ID;
//    }
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//    public String getMODEL_CODE() {
//        return this.MODEL_CODE;
//    }
//    public void setMODEL_CODE(String MODEL_CODE) {
//        this.MODEL_CODE = MODEL_CODE;
//    }
//    public String getNAME() {
//        return this.NAME;
//    }
//    public void setNAME(String NAME) {
//        this.NAME = NAME;
//    }
//    public int getPROTECTION_CLASS() {
//        return this.PROTECTION_CLASS;
//    }
//    public void setPROTECTION_CLASS(int PROTECTION_CLASS) {
//        this.PROTECTION_CLASS = PROTECTION_CLASS;
//    }
//    public int getPROTECTION_TYPE() {
//        return this.PROTECTION_TYPE;
//    }
//    public void setPROTECTION_TYPE(int PROTECTION_TYPE) {
//        this.PROTECTION_TYPE = PROTECTION_TYPE;
//    }
//    public String getFAC_ID() {
//        return this.FAC_ID;
//    }
//    public void setFAC_ID(String FAC_ID) {
//        this.FAC_ID = FAC_ID;
//    }
//    public String getIS_DOMESTIC() {
//        return this.IS_DOMESTIC;
//    }
//    public void setIS_DOMESTIC(String IS_DOMESTIC) {
//        this.IS_DOMESTIC = IS_DOMESTIC;
//    }
//    public String getIS_SUB_MODULE() {
//        return this.IS_SUB_MODULE;
//    }
//    public void setIS_SUB_MODULE(String IS_SUB_MODULE) {
//        this.IS_SUB_MODULE = IS_SUB_MODULE;
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
//    public String getIS_SIX_UNITY() {
//        return this.IS_SIX_UNITY;
//    }
//    public void setIS_SIX_UNITY(String IS_SIX_UNITY) {
//        this.IS_SIX_UNITY = IS_SIX_UNITY;
//    }
//    public int getSIX_UNITY_VERSION() {
//        return this.SIX_UNITY_VERSION;
//    }
//    public void setSIX_UNITY_VERSION(int SIX_UNITY_VERSION) {
//        this.SIX_UNITY_VERSION = SIX_UNITY_VERSION;
//    }
//    public String getIS_START_USING() {
//        return this.IS_START_USING;
//    }
//    public void setIS_START_USING(String IS_START_USING) {
//        this.IS_START_USING = IS_START_USING;
//    }
//    public String getBATCH() {
//        return this.BATCH;
//    }
//    public void setBATCH(String BATCH) {
//        this.BATCH = BATCH;
//    }
//    public String getFAM_ID() {
//        return this.FAM_ID;
//    }
//    public void setFAM_ID(String FAM_ID) {
//        this.FAM_ID = FAM_ID;
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
//    public String getTECH_NOTE() {
//        return this.TECH_NOTE;
//    }
//    public void setTECH_NOTE(String TECH_NOTE) {
//        this.TECH_NOTE = TECH_NOTE;
//    }
//    public String getMAINTAIN_NOTE() {
//        return this.MAINTAIN_NOTE;
//    }
//    public void setMAINTAIN_NOTE(String MAINTAIN_NOTE) {
//        this.MAINTAIN_NOTE = MAINTAIN_NOTE;
//    }
//    public String getXSH_REPORT() {
//        return this.XSH_REPORT;
//    }
//    public void setXSH_REPORT(String XSH_REPORT) {
//        this.XSH_REPORT = XSH_REPORT;
//    }
//    public String getDM_REPORT() {
//        return this.DM_REPORT;
//    }
//    public void setDM_REPORT(String DM_REPORT) {
//        this.DM_REPORT = DM_REPORT;
//    }
//    /**
//     * To-many relationship, resolved on first access (and after reset).
//     * Changes to to-many relations are not persisted, make changes to the target entity.
//     */
//    @Generated(hash = 1084757233)
//    public List<BZ_RJBB_SG_PRT_SOFTWAREVERSION> getBz_rjbb() {
//        if (bz_rjbb == null) {
//            final DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            BZ_RJBB_SG_PRT_SOFTWAREVERSIONDao targetDao = daoSession
//                    .getBZ_RJBB_SG_PRT_SOFTWAREVERSIONDao();
//            List<BZ_RJBB_SG_PRT_SOFTWAREVERSION> bz_rjbbNew = targetDao
//                    ._queryBHSBXHB_SG_PRT_DEVICEMODEL_Bz_rjbb(ID);
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
//     * To-many relationship, resolved on first access (and after reset).
//     * Changes to to-many relations are not persisted, make changes to the target entity.
//     */
//    @Generated(hash = 1245380151)
//    public List<BZ_ICDXX_SG_PRT_ICDINFORMATION> getBz_icdxx() {
//        if (bz_icdxx == null) {
//            final DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            BZ_ICDXX_SG_PRT_ICDINFORMATIONDao targetDao = daoSession
//                    .getBZ_ICDXX_SG_PRT_ICDINFORMATIONDao();
//            List<BZ_ICDXX_SG_PRT_ICDINFORMATION> bz_icdxxNew = targetDao
//                    ._queryBHSBXHB_SG_PRT_DEVICEMODEL_Bz_icdxx(ID);
//            synchronized (this) {
//                if (bz_icdxx == null) {
//                    bz_icdxx = bz_icdxxNew;
//                }
//            }
//        }
//        return bz_icdxx;
//    }
//    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
//    @Generated(hash = 1520754240)
//    public synchronized void resetBz_icdxx() {
//        bz_icdxx = null;
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
//    @Generated(hash = 877664236)
//    public void __setDaoSession(DaoSession daoSession) {
//        this.daoSession = daoSession;
//        myDao = daoSession != null ? daoSession.getBHSBXHB_SG_PRT_DEVICEMODELDao() : null;
//    }
//}
