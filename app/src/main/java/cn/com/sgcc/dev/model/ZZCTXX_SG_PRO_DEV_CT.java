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
// * <p>@description:装置CT信息</p>
// * 表名：SG_PRO_DEV_CT
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//@Entity(nameInDb = "SG_PRO_DEV_CT")
//public class ZZCTXX_SG_PRO_DEV_CT implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;              // 唯一标识ID
//    @Property(nameInDb = "DEV_ID")
//    private String DEV_ID;          // 装置ID
//    @Property(nameInDb = "PROTECTOR_TYPE")
//    private int PROTECTOR_TYPE;  // 装置类别
//    @Property(nameInDb = "RAT_RATIO")
//    private String RAT_RATIO;       // 额定变比
//    @Property(nameInDb = "ACT_RATIO")
//    private String ACT_RATIO;       // 实际变比
//    @Property(nameInDb = "ACC_CLASS")
//    private int ACC_CLASS;       // 准确级
//    @Property(nameInDb = "CT_RATED_CURRENT")
//    private int CT_RATED_CURRENT;// CT二次额定电流
//    @Property(nameInDb = "CHECK_STATUS")
//    private int CHECK_STATUS;    // 审核状态
//    @Property(nameInDb = "DATA_UNIT")
//    private String DATA_UNIT;       // 数据报送单位
//    @Property(nameInDb = "FAG")
//    private int FAG;             // 数据标识
//    @Generated(hash = 2089347890)
//    public ZZCTXX_SG_PRO_DEV_CT(String ID, String DEV_ID, int PROTECTOR_TYPE,
//            String RAT_RATIO, String ACT_RATIO, int ACC_CLASS, int CT_RATED_CURRENT,
//            int CHECK_STATUS, String DATA_UNIT, int FAG) {
//        this.ID = ID;
//        this.DEV_ID = DEV_ID;
//        this.PROTECTOR_TYPE = PROTECTOR_TYPE;
//        this.RAT_RATIO = RAT_RATIO;
//        this.ACT_RATIO = ACT_RATIO;
//        this.ACC_CLASS = ACC_CLASS;
//        this.CT_RATED_CURRENT = CT_RATED_CURRENT;
//        this.CHECK_STATUS = CHECK_STATUS;
//        this.DATA_UNIT = DATA_UNIT;
//        this.FAG = FAG;
//    }
//    @Generated(hash = 269342347)
//    public ZZCTXX_SG_PRO_DEV_CT() {
//    }
//    public String getID() {
//        return this.ID;
//    }
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//    public String getDEV_ID() {
//        return this.DEV_ID;
//    }
//    public void setDEV_ID(String DEV_ID) {
//        this.DEV_ID = DEV_ID;
//    }
//    public int getPROTECTOR_TYPE() {
//        return this.PROTECTOR_TYPE;
//    }
//    public void setPROTECTOR_TYPE(int PROTECTOR_TYPE) {
//        this.PROTECTOR_TYPE = PROTECTOR_TYPE;
//    }
//    public String getRAT_RATIO() {
//        return this.RAT_RATIO;
//    }
//    public void setRAT_RATIO(String RAT_RATIO) {
//        this.RAT_RATIO = RAT_RATIO;
//    }
//    public String getACT_RATIO() {
//        return this.ACT_RATIO;
//    }
//    public void setACT_RATIO(String ACT_RATIO) {
//        this.ACT_RATIO = ACT_RATIO;
//    }
//    public int getACC_CLASS() {
//        return this.ACC_CLASS;
//    }
//    public void setACC_CLASS(int ACC_CLASS) {
//        this.ACC_CLASS = ACC_CLASS;
//    }
//    public int getCT_RATED_CURRENT() {
//        return this.CT_RATED_CURRENT;
//    }
//    public void setCT_RATED_CURRENT(int CT_RATED_CURRENT) {
//        this.CT_RATED_CURRENT = CT_RATED_CURRENT;
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
//    public int getFAG() {
//        return this.FAG;
//    }
//    public void setFAG(int FAG) {
//        this.FAG = FAG;
//    }
//}
