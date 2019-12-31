//package cn.com.sgcc.dev.model;
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
///**
// * <p>@description:装置就地化保护连接器信息</p>
// * 表名：SG_PRO_DEV_CONNECTER
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//
//@Entity(nameInDb = "SG_PRO_DEV_CONNECTER")
//public class LJQXX_SG_PRO_DEV_CONNECTER implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;            // 唯一标识ID
//    @Property(nameInDb = "ED_TAG")
//    private String ED_TAG;              //修改状态
//    @Property(nameInDb = "DEV_ID")
//    private String DEV_ID;        // 装置ID
//    @Property(nameInDb = "PLUG_CODE")
//    private String PLUG_CODE;     // 插头组件编号
//    @Property(nameInDb = "PLUG_FAC")
//    private String PLUG_FAC;      // 插头组件制造厂家
//    @Property(nameInDb = "PLUG_MODEL")
//    private String PLUG_MODEL;    // 插头组件型号
//    @Property(nameInDb = "INTERFACE_TYPE")
//    private int INTERFACE_TYPE;// 接口类型
//    @Property(nameInDb = "INTERFACE_USE")
//    private String INTERFACE_USE; // 接口用途
//    @Property(nameInDb = "PLUG_SEAL_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp PLUG_SEAL_DATE;// 插头组件铅封日期
//    @Property(nameInDb = "EXIT_TIME")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp EXIT_TIME;     // 更换时间
//    @Property(nameInDb = "EXIT_REASION")
//    private String EXIT_REASION;  // 更换原因
//    @Property(nameInDb = "HIS_PLUG_ID")
//    private String HIS_PLUG_ID;   // 历史插头ID
//    @Property(nameInDb = "FAG")
//    private int FAG;           // 数据标识
//    @Property(nameInDb = "CHECK_STATUS")
//    private int CHECK_STATUS;  // 审核状态
//    @Property(nameInDb = "DATA_UNIT")
//    private String DATA_UNIT;     // 数据报送单位
//    @Generated(hash = 1589561498)
//    public LJQXX_SG_PRO_DEV_CONNECTER(String ID, String ED_TAG, String DEV_ID,
//            String PLUG_CODE, String PLUG_FAC, String PLUG_MODEL,
//            int INTERFACE_TYPE, String INTERFACE_USE, Timestamp PLUG_SEAL_DATE,
//            Timestamp EXIT_TIME, String EXIT_REASION, String HIS_PLUG_ID, int FAG,
//            int CHECK_STATUS, String DATA_UNIT) {
//        this.ID = ID;
//        this.ED_TAG = ED_TAG;
//        this.DEV_ID = DEV_ID;
//        this.PLUG_CODE = PLUG_CODE;
//        this.PLUG_FAC = PLUG_FAC;
//        this.PLUG_MODEL = PLUG_MODEL;
//        this.INTERFACE_TYPE = INTERFACE_TYPE;
//        this.INTERFACE_USE = INTERFACE_USE;
//        this.PLUG_SEAL_DATE = PLUG_SEAL_DATE;
//        this.EXIT_TIME = EXIT_TIME;
//        this.EXIT_REASION = EXIT_REASION;
//        this.HIS_PLUG_ID = HIS_PLUG_ID;
//        this.FAG = FAG;
//        this.CHECK_STATUS = CHECK_STATUS;
//        this.DATA_UNIT = DATA_UNIT;
//    }
//    @Generated(hash = 208820291)
//    public LJQXX_SG_PRO_DEV_CONNECTER() {
//    }
//    public String getID() {
//        return this.ID;
//    }
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//    public String getED_TAG() {
//        return this.ED_TAG;
//    }
//    public void setED_TAG(String ED_TAG) {
//        this.ED_TAG = ED_TAG;
//    }
//    public String getDEV_ID() {
//        return this.DEV_ID;
//    }
//    public void setDEV_ID(String DEV_ID) {
//        this.DEV_ID = DEV_ID;
//    }
//    public String getPLUG_CODE() {
//        return this.PLUG_CODE;
//    }
//    public void setPLUG_CODE(String PLUG_CODE) {
//        this.PLUG_CODE = PLUG_CODE;
//    }
//    public String getPLUG_FAC() {
//        return this.PLUG_FAC;
//    }
//    public void setPLUG_FAC(String PLUG_FAC) {
//        this.PLUG_FAC = PLUG_FAC;
//    }
//    public String getPLUG_MODEL() {
//        return this.PLUG_MODEL;
//    }
//    public void setPLUG_MODEL(String PLUG_MODEL) {
//        this.PLUG_MODEL = PLUG_MODEL;
//    }
//    public int getINTERFACE_TYPE() {
//        return this.INTERFACE_TYPE;
//    }
//    public void setINTERFACE_TYPE(int INTERFACE_TYPE) {
//        this.INTERFACE_TYPE = INTERFACE_TYPE;
//    }
//    public String getINTERFACE_USE() {
//        return this.INTERFACE_USE;
//    }
//    public void setINTERFACE_USE(String INTERFACE_USE) {
//        this.INTERFACE_USE = INTERFACE_USE;
//    }
//    public Timestamp getPLUG_SEAL_DATE() {
//        return this.PLUG_SEAL_DATE;
//    }
//    public void setPLUG_SEAL_DATE(Timestamp PLUG_SEAL_DATE) {
//        this.PLUG_SEAL_DATE = PLUG_SEAL_DATE;
//    }
//    public Timestamp getEXIT_TIME() {
//        return this.EXIT_TIME;
//    }
//    public void setEXIT_TIME(Timestamp EXIT_TIME) {
//        this.EXIT_TIME = EXIT_TIME;
//    }
//    public String getEXIT_REASION() {
//        return this.EXIT_REASION;
//    }
//    public void setEXIT_REASION(String EXIT_REASION) {
//        this.EXIT_REASION = EXIT_REASION;
//    }
//    public String getHIS_PLUG_ID() {
//        return this.HIS_PLUG_ID;
//    }
//    public void setHIS_PLUG_ID(String HIS_PLUG_ID) {
//        this.HIS_PLUG_ID = HIS_PLUG_ID;
//    }
//    public int getFAG() {
//        return this.FAG;
//    }
//    public void setFAG(int FAG) {
//        this.FAG = FAG;
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
//}
