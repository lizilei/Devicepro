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
// * <p>@description:ICD信息</p>
// * 表名：SG_PRT_ICDINFORMATION
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//@Entity(nameInDb = "SG_PRT_ICDINFORMATION")
//public class BZ_ICDXX_SG_PRT_ICDINFORMATION implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;              // ICD_ID ID
//    @Property(nameInDb = "APP_VOLTAGE")
//    private String APP_VOLTAGE;     // 适用电源电压
//    @Property(nameInDb = "FILE_VER")
//    private String FILE_VER;        // ICD文件版本
//    @Property(nameInDb = "FILE_NAME")
//    private String FILE_NAME;       // ICD文件名称
//    @Property(nameInDb = "VER_ID")
//    private String VER_ID;          // 软件版本ID
//    @Property(nameInDb = "FILE_SIZE")
//    private String FILE_SIZE;       // 文件大小
//    @Property(nameInDb = "MATCHING")
//    private String MATCHING;        // 选配功能
//    @Property(nameInDb = "FILE_MODIFI_TIME")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp FILE_MODIFI_TIME;// ICD文件最终修改时间
//    @Property(nameInDb = "CRC32_CODE")
//    private String CRC32_CODE;      // CRC32验证码
//    @Property(nameInDb = "CODE_TIME")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp CODE_TIME;       // 验证码生成时
//    @Property(nameInDb = "INFORMATION_TIME")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp INFORMATION_TIME;// 信息生成时间
//    @Property(nameInDb = "TESTING_TIME")
//    @Convert(converter = TimestampConverter.class, columnType = String.class)
//    private Timestamp TESTING_TIME;    // 通过专业检测时间
//    @Property(nameInDb = "TESTING_BATCH")
//    private String TESTING_BATCH;   // 专业检测批次
//    @Property(nameInDb = "MD5_CODE")
//    private String MD5_CODE;        // MD5校验码
//    @Property(nameInDb = "CHECK_STATUS")
//    private int CHECK_STATUS;    // 审核状态
//    @Property(nameInDb = "DATA_UNIT")
//    private String DATA_UNIT;       // 数据报送单位
//    @Property(nameInDb = "COMMENT")
//    private String COMMENT;         // 备注
//    @Property(nameInDb = "STAMP")
//    private String STAMP;           // 更新标志
//    @Property(nameInDb = "OWNER")
//    private String OWNER;           // 拥有者
//    @Generated(hash = 1711144797)
//    public BZ_ICDXX_SG_PRT_ICDINFORMATION(String ID, String APP_VOLTAGE,
//            String FILE_VER, String FILE_NAME, String VER_ID, String FILE_SIZE,
//            String MATCHING, Timestamp FILE_MODIFI_TIME, String CRC32_CODE,
//            Timestamp CODE_TIME, Timestamp INFORMATION_TIME, Timestamp TESTING_TIME,
//            String TESTING_BATCH, String MD5_CODE, int CHECK_STATUS,
//            String DATA_UNIT, String COMMENT, String STAMP, String OWNER) {
//        this.ID = ID;
//        this.APP_VOLTAGE = APP_VOLTAGE;
//        this.FILE_VER = FILE_VER;
//        this.FILE_NAME = FILE_NAME;
//        this.VER_ID = VER_ID;
//        this.FILE_SIZE = FILE_SIZE;
//        this.MATCHING = MATCHING;
//        this.FILE_MODIFI_TIME = FILE_MODIFI_TIME;
//        this.CRC32_CODE = CRC32_CODE;
//        this.CODE_TIME = CODE_TIME;
//        this.INFORMATION_TIME = INFORMATION_TIME;
//        this.TESTING_TIME = TESTING_TIME;
//        this.TESTING_BATCH = TESTING_BATCH;
//        this.MD5_CODE = MD5_CODE;
//        this.CHECK_STATUS = CHECK_STATUS;
//        this.DATA_UNIT = DATA_UNIT;
//        this.COMMENT = COMMENT;
//        this.STAMP = STAMP;
//        this.OWNER = OWNER;
//    }
//    @Generated(hash = 1470653827)
//    public BZ_ICDXX_SG_PRT_ICDINFORMATION() {
//    }
//    public String getID() {
//        return this.ID;
//    }
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//    public String getAPP_VOLTAGE() {
//        return this.APP_VOLTAGE;
//    }
//    public void setAPP_VOLTAGE(String APP_VOLTAGE) {
//        this.APP_VOLTAGE = APP_VOLTAGE;
//    }
//    public String getFILE_VER() {
//        return this.FILE_VER;
//    }
//    public void setFILE_VER(String FILE_VER) {
//        this.FILE_VER = FILE_VER;
//    }
//    public String getFILE_NAME() {
//        return this.FILE_NAME;
//    }
//    public void setFILE_NAME(String FILE_NAME) {
//        this.FILE_NAME = FILE_NAME;
//    }
//    public String getVER_ID() {
//        return this.VER_ID;
//    }
//    public void setVER_ID(String VER_ID) {
//        this.VER_ID = VER_ID;
//    }
//    public String getFILE_SIZE() {
//        return this.FILE_SIZE;
//    }
//    public void setFILE_SIZE(String FILE_SIZE) {
//        this.FILE_SIZE = FILE_SIZE;
//    }
//    public String getMATCHING() {
//        return this.MATCHING;
//    }
//    public void setMATCHING(String MATCHING) {
//        this.MATCHING = MATCHING;
//    }
//    public Timestamp getFILE_MODIFI_TIME() {
//        return this.FILE_MODIFI_TIME;
//    }
//    public void setFILE_MODIFI_TIME(Timestamp FILE_MODIFI_TIME) {
//        this.FILE_MODIFI_TIME = FILE_MODIFI_TIME;
//    }
//    public String getCRC32_CODE() {
//        return this.CRC32_CODE;
//    }
//    public void setCRC32_CODE(String CRC32_CODE) {
//        this.CRC32_CODE = CRC32_CODE;
//    }
//    public Timestamp getCODE_TIME() {
//        return this.CODE_TIME;
//    }
//    public void setCODE_TIME(Timestamp CODE_TIME) {
//        this.CODE_TIME = CODE_TIME;
//    }
//    public Timestamp getINFORMATION_TIME() {
//        return this.INFORMATION_TIME;
//    }
//    public void setINFORMATION_TIME(Timestamp INFORMATION_TIME) {
//        this.INFORMATION_TIME = INFORMATION_TIME;
//    }
//    public Timestamp getTESTING_TIME() {
//        return this.TESTING_TIME;
//    }
//    public void setTESTING_TIME(Timestamp TESTING_TIME) {
//        this.TESTING_TIME = TESTING_TIME;
//    }
//    public String getTESTING_BATCH() {
//        return this.TESTING_BATCH;
//    }
//    public void setTESTING_BATCH(String TESTING_BATCH) {
//        this.TESTING_BATCH = TESTING_BATCH;
//    }
//    public String getMD5_CODE() {
//        return this.MD5_CODE;
//    }
//    public void setMD5_CODE(String MD5_CODE) {
//        this.MD5_CODE = MD5_CODE;
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
//}
