package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * icd文件信息实体类
 *
 * @see
 */
@Entity(nameInDb = "ICDXX", createInDb = false)
public class ICDXX implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Property(nameInDb = "ID")
    private Long ID;
    @Property(nameInDb = "WJMC")
    private String WJMC;
    @Property(nameInDb = "WJBB")
    private String WJBB;
    @Property(nameInDb = "CRC32")
    private String CRC32;
    @Property(nameInDb = "MD5")
    private String MD5;
    @Property(nameInDb = "JYMSCSJ")
    private String JYMSCSJ;
    @Property(nameInDb = "BGYY")
    private String BGYY;
    @Property(nameInDb = "BGSJ")
    private String BGSJ;
    @Property(nameInDb = "ZSJID")
    private long ZSJID;
    @Property(nameInDb = "ZSJTYPE")
    private String ZSJTYPE;
    @Property(nameInDb = "SFBDSJ")
    private String SFBDSJ;
    @Property(nameInDb = "SB_LS_ID")
    private String SB_LS_ID;
    @Property(nameInDb = "SB")
    private String SB;
    @Property(nameInDb = "SBSJ")
    private String SBSJ;
    @Property(nameInDb = "HZSJ")
    private String HZSJ;
    @Property(nameInDb = "SBDW")
    private String SBDW;
    @Property(nameInDb = "SBCZLX")
    private String SBCZLX;
    @Property(nameInDb = "LS_ZSJ_ID")
    private String LS_ZSJ_ID;
    @Property(nameInDb = "WJ_DW")
    private String WJ_DW;
    @Property(nameInDb = "WJ_LS_ID")
    private String WJ_LS_ID;
    @Property(nameInDb = "SFXTLR")
    private String SFXTLR;
    @Property(nameInDb = "ED_TAG")
    private String ED_TAG;
    @Generated(hash = 89581991)
    public ICDXX(Long ID, String WJMC, String WJBB, String CRC32, String MD5,
            String JYMSCSJ, String BGYY, String BGSJ, long ZSJID, String ZSJTYPE,
            String SFBDSJ, String SB_LS_ID, String SB, String SBSJ, String HZSJ,
            String SBDW, String SBCZLX, String LS_ZSJ_ID, String WJ_DW,
            String WJ_LS_ID, String SFXTLR, String ED_TAG) {
        this.ID = ID;
        this.WJMC = WJMC;
        this.WJBB = WJBB;
        this.CRC32 = CRC32;
        this.MD5 = MD5;
        this.JYMSCSJ = JYMSCSJ;
        this.BGYY = BGYY;
        this.BGSJ = BGSJ;
        this.ZSJID = ZSJID;
        this.ZSJTYPE = ZSJTYPE;
        this.SFBDSJ = SFBDSJ;
        this.SB_LS_ID = SB_LS_ID;
        this.SB = SB;
        this.SBSJ = SBSJ;
        this.HZSJ = HZSJ;
        this.SBDW = SBDW;
        this.SBCZLX = SBCZLX;
        this.LS_ZSJ_ID = LS_ZSJ_ID;
        this.WJ_DW = WJ_DW;
        this.WJ_LS_ID = WJ_LS_ID;
        this.SFXTLR = SFXTLR;
        this.ED_TAG = ED_TAG;
    }
    @Generated(hash = 1683889456)
    public ICDXX() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getWJMC() {
        return this.WJMC;
    }
    public void setWJMC(String WJMC) {
        this.WJMC = WJMC;
    }
    public String getWJBB() {
        return this.WJBB;
    }
    public void setWJBB(String WJBB) {
        this.WJBB = WJBB;
    }
    public String getCRC32() {
        return this.CRC32;
    }
    public void setCRC32(String CRC32) {
        this.CRC32 = CRC32;
    }
    public String getMD5() {
        return this.MD5;
    }
    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }
    public String getJYMSCSJ() {
        return this.JYMSCSJ;
    }
    public void setJYMSCSJ(String JYMSCSJ) {
        this.JYMSCSJ = JYMSCSJ;
    }
    public String getBGYY() {
        return this.BGYY;
    }
    public void setBGYY(String BGYY) {
        this.BGYY = BGYY;
    }
    public String getBGSJ() {
        return this.BGSJ;
    }
    public void setBGSJ(String BGSJ) {
        this.BGSJ = BGSJ;
    }
    public long getZSJID() {
        return this.ZSJID;
    }
    public void setZSJID(long ZSJID) {
        this.ZSJID = ZSJID;
    }
    public String getZSJTYPE() {
        return this.ZSJTYPE;
    }
    public void setZSJTYPE(String ZSJTYPE) {
        this.ZSJTYPE = ZSJTYPE;
    }
    public String getSFBDSJ() {
        return this.SFBDSJ;
    }
    public void setSFBDSJ(String SFBDSJ) {
        this.SFBDSJ = SFBDSJ;
    }
    public String getSB_LS_ID() {
        return this.SB_LS_ID;
    }
    public void setSB_LS_ID(String SB_LS_ID) {
        this.SB_LS_ID = SB_LS_ID;
    }
    public String getSB() {
        return this.SB;
    }
    public void setSB(String SB) {
        this.SB = SB;
    }
    public String getSBSJ() {
        return this.SBSJ;
    }
    public void setSBSJ(String SBSJ) {
        this.SBSJ = SBSJ;
    }
    public String getHZSJ() {
        return this.HZSJ;
    }
    public void setHZSJ(String HZSJ) {
        this.HZSJ = HZSJ;
    }
    public String getSBDW() {
        return this.SBDW;
    }
    public void setSBDW(String SBDW) {
        this.SBDW = SBDW;
    }
    public String getSBCZLX() {
        return this.SBCZLX;
    }
    public void setSBCZLX(String SBCZLX) {
        this.SBCZLX = SBCZLX;
    }
    public String getLS_ZSJ_ID() {
        return this.LS_ZSJ_ID;
    }
    public void setLS_ZSJ_ID(String LS_ZSJ_ID) {
        this.LS_ZSJ_ID = LS_ZSJ_ID;
    }
    public String getWJ_DW() {
        return this.WJ_DW;
    }
    public void setWJ_DW(String WJ_DW) {
        this.WJ_DW = WJ_DW;
    }
    public String getWJ_LS_ID() {
        return this.WJ_LS_ID;
    }
    public void setWJ_LS_ID(String WJ_LS_ID) {
        this.WJ_LS_ID = WJ_LS_ID;
    }
    public String getSFXTLR() {
        return this.SFXTLR;
    }
    public void setSFXTLR(String SFXTLR) {
        this.SFXTLR = SFXTLR;
    }
    public String getED_TAG() {
        return this.ED_TAG;
    }
    public void setED_TAG(String ED_TAG) {
        this.ED_TAG = ED_TAG;
    }

}
