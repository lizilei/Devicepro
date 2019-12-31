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
// * <p>@description:装置板卡信息</p>
// * 表名：SG_PRO_DEV_BOARD
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//
//@Entity(nameInDb = "SG_PRO_DEV_BOARD")
//public class BKXX_SG_PRO_DEV_BOARD implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;           // 唯一标识ID
//    @Property(nameInDb = "ED_TAG")
//    private String ED_TAG;              //修改状态
//    @Property(nameInDb = "DEV_ID")
//    private String DEV_ID;       // 装置ID
//    @Property(nameInDb = "MODEL")
//    private String MODEL;        // 板卡型号
//    @Property(nameInDb = "CLASS")
//    private String CLASS;        // 板卡类别
//    @Property(nameInDb = "VER")
//    private String VER;          // 硬件版本
//    @Property(nameInDb = "CODE")
//    private String CODE;         // 板卡编号
//    @Property(nameInDb = "BOARD_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp BOARD_DATE;   // 板卡生产日期
//    @Property(nameInDb = "EXIT_REASION")
//    private String EXIT_REASION; // 更换原因
//    @Property(nameInDb = "EXIT_TIME")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp EXIT_TIME;    // 更换时间
//    @Property(nameInDb = "HIS_BORDER_ID")
//    private String HIS_BORDER_ID;// 历史板卡ID
//    @Property(nameInDb = "CHECK_STATUS")
//    private int CHECK_STATUS; // 审核状态
//    @Property(nameInDb = "DATA_UNIT")
//    private String DATA_UNIT;    // 数据报送单位
//    @Property(nameInDb = "FAG")
//    private int FAG;          // 数据标识
//    @Generated(hash = 1727092760)
//    public BKXX_SG_PRO_DEV_BOARD(String ID, String ED_TAG, String DEV_ID,
//            String MODEL, String CLASS, String VER, String CODE,
//            Timestamp BOARD_DATE, String EXIT_REASION, Timestamp EXIT_TIME,
//            String HIS_BORDER_ID, int CHECK_STATUS, String DATA_UNIT, int FAG) {
//        this.ID = ID;
//        this.ED_TAG = ED_TAG;
//        this.DEV_ID = DEV_ID;
//        this.MODEL = MODEL;
//        this.CLASS = CLASS;
//        this.VER = VER;
//        this.CODE = CODE;
//        this.BOARD_DATE = BOARD_DATE;
//        this.EXIT_REASION = EXIT_REASION;
//        this.EXIT_TIME = EXIT_TIME;
//        this.HIS_BORDER_ID = HIS_BORDER_ID;
//        this.CHECK_STATUS = CHECK_STATUS;
//        this.DATA_UNIT = DATA_UNIT;
//        this.FAG = FAG;
//    }
//    @Generated(hash = 931550143)
//    public BKXX_SG_PRO_DEV_BOARD() {
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
//    public String getMODEL() {
//        return this.MODEL;
//    }
//    public void setMODEL(String MODEL) {
//        this.MODEL = MODEL;
//    }
//    public String getCLASS() {
//        return this.CLASS;
//    }
//    public void setCLASS(String CLASS) {
//        this.CLASS = CLASS;
//    }
//    public String getVER() {
//        return this.VER;
//    }
//    public void setVER(String VER) {
//        this.VER = VER;
//    }
//    public String getCODE() {
//        return this.CODE;
//    }
//    public void setCODE(String CODE) {
//        this.CODE = CODE;
//    }
//    public Timestamp getBOARD_DATE() {
//        return this.BOARD_DATE;
//    }
//    public void setBOARD_DATE(Timestamp BOARD_DATE) {
//        this.BOARD_DATE = BOARD_DATE;
//    }
//    public String getEXIT_REASION() {
//        return this.EXIT_REASION;
//    }
//    public void setEXIT_REASION(String EXIT_REASION) {
//        this.EXIT_REASION = EXIT_REASION;
//    }
//    public Timestamp getEXIT_TIME() {
//        return this.EXIT_TIME;
//    }
//    public void setEXIT_TIME(Timestamp EXIT_TIME) {
//        this.EXIT_TIME = EXIT_TIME;
//    }
//    public String getHIS_BORDER_ID() {
//        return this.HIS_BORDER_ID;
//    }
//    public void setHIS_BORDER_ID(String HIS_BORDER_ID) {
//        this.HIS_BORDER_ID = HIS_BORDER_ID;
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
