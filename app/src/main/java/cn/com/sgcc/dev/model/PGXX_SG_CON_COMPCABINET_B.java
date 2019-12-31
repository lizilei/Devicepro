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
// * <p>@description:屏柜信息</p>
// * 表名：SG_CON_COMPCABINET_B
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//
//@Entity(nameInDb = "SG_CON_COMPCABINET_B")
//public class PGXX_SG_CON_COMPCABINET_B implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String COMPCABINET_ID;  // 屏柜ID
//    @Property(nameInDb = "COMPCABINET_NAME")
//    private String COMPCABINET_NAME;// 屏柜名称
//    @Property(nameInDb = "ROOM_ID")
//    private String ROOM_ID;         // 所属机房
//    @Property(nameInDb = "LENGTH")
//    private double LENGTH;          // 长度
//    @Property(nameInDb = "WIDTH")
//    private double WIDTH;           // 宽度
//    @Property(nameInDb = "HEIGHT")
//    private double HEIGHT;          // 高度
//    @Property(nameInDb = "CLOSET_ID")
//    private String CLOSET_ID;       // 小室ID??
//    @Property(nameInDb = "RUN_NUMBER")
//    private String RUN_NUMBER;      // 运行编号
//    @Property(nameInDb = "IS_STATI")
//    private String IS_STATI;        // 是否统计
//    @Property(nameInDb = "U_NUM")
//    private int U_NUM;           // 机柜U数
//    @Property(nameInDb = "DUAL_POWER")
//    private String DUAL_POWER;      // 是否双电源
//    @Property(nameInDb = "RATED_POWER")
//    private double RATED_POWER;     // 电源额定功率
//    @Property(nameInDb = "STAMP")
//    private String STAMP;           // 更新标志
//    @Property(nameInDb = "OWNER")
//    private String OWNER;           // 拥有者
//    @Property(nameInDb = "CHECK_STATUS")
//    private String CHECK_STATUS;    // 审核状态
//    @Property(nameInDb = "DATA_UNIT")
//    private String DATA_UNIT;       // 数据报送单位
//    @Generated(hash = 986889375)
//    public PGXX_SG_CON_COMPCABINET_B(String COMPCABINET_ID, String COMPCABINET_NAME,
//            String ROOM_ID, double LENGTH, double WIDTH, double HEIGHT,
//            String CLOSET_ID, String RUN_NUMBER, String IS_STATI, int U_NUM,
//            String DUAL_POWER, double RATED_POWER, String STAMP, String OWNER,
//            String CHECK_STATUS, String DATA_UNIT) {
//        this.COMPCABINET_ID = COMPCABINET_ID;
//        this.COMPCABINET_NAME = COMPCABINET_NAME;
//        this.ROOM_ID = ROOM_ID;
//        this.LENGTH = LENGTH;
//        this.WIDTH = WIDTH;
//        this.HEIGHT = HEIGHT;
//        this.CLOSET_ID = CLOSET_ID;
//        this.RUN_NUMBER = RUN_NUMBER;
//        this.IS_STATI = IS_STATI;
//        this.U_NUM = U_NUM;
//        this.DUAL_POWER = DUAL_POWER;
//        this.RATED_POWER = RATED_POWER;
//        this.STAMP = STAMP;
//        this.OWNER = OWNER;
//        this.CHECK_STATUS = CHECK_STATUS;
//        this.DATA_UNIT = DATA_UNIT;
//    }
//    @Generated(hash = 768054214)
//    public PGXX_SG_CON_COMPCABINET_B() {
//    }
//    public String getCOMPCABINET_ID() {
//        return this.COMPCABINET_ID;
//    }
//    public void setCOMPCABINET_ID(String COMPCABINET_ID) {
//        this.COMPCABINET_ID = COMPCABINET_ID;
//    }
//    public String getCOMPCABINET_NAME() {
//        return this.COMPCABINET_NAME;
//    }
//    public void setCOMPCABINET_NAME(String COMPCABINET_NAME) {
//        this.COMPCABINET_NAME = COMPCABINET_NAME;
//    }
//    public String getROOM_ID() {
//        return this.ROOM_ID;
//    }
//    public void setROOM_ID(String ROOM_ID) {
//        this.ROOM_ID = ROOM_ID;
//    }
//    public double getLENGTH() {
//        return this.LENGTH;
//    }
//    public void setLENGTH(double LENGTH) {
//        this.LENGTH = LENGTH;
//    }
//    public double getWIDTH() {
//        return this.WIDTH;
//    }
//    public void setWIDTH(double WIDTH) {
//        this.WIDTH = WIDTH;
//    }
//    public double getHEIGHT() {
//        return this.HEIGHT;
//    }
//    public void setHEIGHT(double HEIGHT) {
//        this.HEIGHT = HEIGHT;
//    }
//    public String getCLOSET_ID() {
//        return this.CLOSET_ID;
//    }
//    public void setCLOSET_ID(String CLOSET_ID) {
//        this.CLOSET_ID = CLOSET_ID;
//    }
//    public String getRUN_NUMBER() {
//        return this.RUN_NUMBER;
//    }
//    public void setRUN_NUMBER(String RUN_NUMBER) {
//        this.RUN_NUMBER = RUN_NUMBER;
//    }
//    public String getIS_STATI() {
//        return this.IS_STATI;
//    }
//    public void setIS_STATI(String IS_STATI) {
//        this.IS_STATI = IS_STATI;
//    }
//    public int getU_NUM() {
//        return this.U_NUM;
//    }
//    public void setU_NUM(int U_NUM) {
//        this.U_NUM = U_NUM;
//    }
//    public String getDUAL_POWER() {
//        return this.DUAL_POWER;
//    }
//    public void setDUAL_POWER(String DUAL_POWER) {
//        this.DUAL_POWER = DUAL_POWER;
//    }
//    public double getRATED_POWER() {
//        return this.RATED_POWER;
//    }
//    public void setRATED_POWER(double RATED_POWER) {
//        this.RATED_POWER = RATED_POWER;
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
//}
