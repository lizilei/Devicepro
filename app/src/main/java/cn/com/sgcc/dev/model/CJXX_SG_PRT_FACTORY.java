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
// * <p>@description:厂家信息</p>
// * 表名：SG_PRT_FACTORY
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/9/4
// */
//
//@Entity(nameInDb = "SG_PRT_FACTORY")
//public class CJXX_SG_PRT_FACTORY implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;//厂家ID
//    @Property(nameInDb = "NAME")
//    private String NAME;//厂家名称
//    @Property(nameInDb = "ABBREV")
//    private String ABBREV;//厂家简称
//    @Property(nameInDb = "CODE")
//    private String CODE;//制造厂家代码
//    @Property(nameInDb = "SN")
//    private String SN;//厂家编号
//    @Property(nameInDb = "ADDRESS")
//    private String ADDRESS;//地址
//    @Property(nameInDb = "FAX")
//    private String FAX;//传真
//    @Property(nameInDb = "TELEPHONE")
//    private String TELEPHONE;//联系电话
//    @Property(nameInDb = "CHECK_STATUS")
//    private int CHECK_STATUS;//审核状态
//    @Property(nameInDb = "DATA_UNIT")
//    private String DATA_UNIT;//数据报送单位
//    @Property(nameInDb = "STAMP")
//    private String STAMP;//更新标志
//    @Property(nameInDb = "OWNER")
//    private String OWNER;//拥有者
//    @Property(nameInDb = "COMMENT")
//    private String COMMENT;//备注
//    @Generated(hash = 1432243010)
//    public CJXX_SG_PRT_FACTORY(String ID, String NAME, String ABBREV, String CODE,
//            String SN, String ADDRESS, String FAX, String TELEPHONE,
//            int CHECK_STATUS, String DATA_UNIT, String STAMP, String OWNER,
//            String COMMENT) {
//        this.ID = ID;
//        this.NAME = NAME;
//        this.ABBREV = ABBREV;
//        this.CODE = CODE;
//        this.SN = SN;
//        this.ADDRESS = ADDRESS;
//        this.FAX = FAX;
//        this.TELEPHONE = TELEPHONE;
//        this.CHECK_STATUS = CHECK_STATUS;
//        this.DATA_UNIT = DATA_UNIT;
//        this.STAMP = STAMP;
//        this.OWNER = OWNER;
//        this.COMMENT = COMMENT;
//    }
//    @Generated(hash = 2062092736)
//    public CJXX_SG_PRT_FACTORY() {
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
//    public String getABBREV() {
//        return this.ABBREV;
//    }
//    public void setABBREV(String ABBREV) {
//        this.ABBREV = ABBREV;
//    }
//    public String getCODE() {
//        return this.CODE;
//    }
//    public void setCODE(String CODE) {
//        this.CODE = CODE;
//    }
//    public String getSN() {
//        return this.SN;
//    }
//    public void setSN(String SN) {
//        this.SN = SN;
//    }
//    public String getADDRESS() {
//        return this.ADDRESS;
//    }
//    public void setADDRESS(String ADDRESS) {
//        this.ADDRESS = ADDRESS;
//    }
//    public String getFAX() {
//        return this.FAX;
//    }
//    public void setFAX(String FAX) {
//        this.FAX = FAX;
//    }
//    public String getTELEPHONE() {
//        return this.TELEPHONE;
//    }
//    public void setTELEPHONE(String TELEPHONE) {
//        this.TELEPHONE = TELEPHONE;
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
//    public String getCOMMENT() {
//        return this.COMMENT;
//    }
//    public void setCOMMENT(String COMMENT) {
//        this.COMMENT = COMMENT;
//    }
//}
