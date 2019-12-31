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
// * <p>@description:板卡信息</p>
// * 表名：SG_PRT_FACRMA_BOARD
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//@Entity(nameInDb = "SG_PRT_FACRMA_BOARD")
//public class BZ_BKXX_SG_PRT_FACRMA_BOARD implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;//板卡ID
//    @Property(nameInDb = "IDENTIFY_CODE")
//    private String IDENTIFY_CODE;//设备识别代码
//    @Property(nameInDb = "BORAD_SEQ")
//    private String BORAD_SEQ;//板卡序号
//    @Property(nameInDb = "BORAD_MODEL")
//    private String BORAD_MODEL;//板卡型号
//    @Property(nameInDb = "BORAD_CLASS")
//    private String BORAD_CLASS;//板卡类别/用途
//    @Property(nameInDb = "BOARD_VER")
//    private String BOARD_VER;//板卡硬件版本
//    @Property(nameInDb = "BOARD_CODE")
//    private String BOARD_CODE;//板卡编号
//    @Property(nameInDb = "CREATION_DATE")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp CREATION_DATE;//板卡生产日期
//    @Property(nameInDb = "CHECK_STATUS")
//    private int CHECK_STATUS;//审核状态
//    @Generated(hash = 1948235525)
//    public BZ_BKXX_SG_PRT_FACRMA_BOARD(String ID, String IDENTIFY_CODE,
//            String BORAD_SEQ, String BORAD_MODEL, String BORAD_CLASS,
//            String BOARD_VER, String BOARD_CODE, Timestamp CREATION_DATE,
//            int CHECK_STATUS) {
//        this.ID = ID;
//        this.IDENTIFY_CODE = IDENTIFY_CODE;
//        this.BORAD_SEQ = BORAD_SEQ;
//        this.BORAD_MODEL = BORAD_MODEL;
//        this.BORAD_CLASS = BORAD_CLASS;
//        this.BOARD_VER = BOARD_VER;
//        this.BOARD_CODE = BOARD_CODE;
//        this.CREATION_DATE = CREATION_DATE;
//        this.CHECK_STATUS = CHECK_STATUS;
//    }
//    @Generated(hash = 1744940460)
//    public BZ_BKXX_SG_PRT_FACRMA_BOARD() {
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
//    public String getBORAD_SEQ() {
//        return this.BORAD_SEQ;
//    }
//    public void setBORAD_SEQ(String BORAD_SEQ) {
//        this.BORAD_SEQ = BORAD_SEQ;
//    }
//    public String getBORAD_MODEL() {
//        return this.BORAD_MODEL;
//    }
//    public void setBORAD_MODEL(String BORAD_MODEL) {
//        this.BORAD_MODEL = BORAD_MODEL;
//    }
//    public String getBORAD_CLASS() {
//        return this.BORAD_CLASS;
//    }
//    public void setBORAD_CLASS(String BORAD_CLASS) {
//        this.BORAD_CLASS = BORAD_CLASS;
//    }
//    public String getBOARD_VER() {
//        return this.BOARD_VER;
//    }
//    public void setBOARD_VER(String BOARD_VER) {
//        this.BOARD_VER = BOARD_VER;
//    }
//    public String getBOARD_CODE() {
//        return this.BOARD_CODE;
//    }
//    public void setBOARD_CODE(String BOARD_CODE) {
//        this.BOARD_CODE = BOARD_CODE;
//    }
//    public Timestamp getCREATION_DATE() {
//        return this.CREATION_DATE;
//    }
//    public void setCREATION_DATE(Timestamp CREATION_DATE) {
//        this.CREATION_DATE = CREATION_DATE;
//    }
//    public int getCHECK_STATUS() {
//        return this.CHECK_STATUS;
//    }
//    public void setCHECK_STATUS(int CHECK_STATUS) {
//        this.CHECK_STATUS = CHECK_STATUS;
//    }
//}
