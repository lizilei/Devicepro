package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/8
 */
@Entity(nameInDb = "RZGL", createInDb = false)
public class RZGL {

    @Id
    @Property(nameInDb = "ID")
    private Long ID;
    @Property(nameInDb = "DWMC")
    private String DWMC;
    @Property(nameInDb = "CZR")
    private String CZR;
    @Property(nameInDb = "CZLX")
    private String CZLX;
    @Property(nameInDb = "CZSJ")
    private String CZSJ;
    @Property(nameInDb = "BBS")
    private String BBS;
    @Property(nameInDb = "FLAGDYDZ")
    private String FLAGDYDZ;
    @Property(nameInDb = "SB")
    private String SB;
    @Property(nameInDb = "SBSJ")
    private String SBSJ;
    @Property(nameInDb = "SFBDSJ")
    private String SFBDSJ;
    @Property(nameInDb = "SB_LS_ID")
    private String SB_LS_ID;
    @Property(nameInDb = "SBDW")
    private String SBDW;
    @Property(nameInDb = "BZ")
    private String BZ;
    @Property(nameInDb = "HZSJ")
    private String HZSJ;
    @Property(nameInDb = "SBCZLX")
    private String SBCZLX;
    @Property(nameInDb = "SB_BHPZ_ID")
    private String SB_BHPZ_ID;
    @Property(nameInDb = "WJ_DW")
    private String WJ_DW;
    @Property(nameInDb = "WJ_LS_ID")
    private String WJ_LS_ID;
    @Property(nameInDb = "DXZJ")
    private long DXZJ;
    @Property(nameInDb = "SFXTLR")
    private String SFXTLR;
    @Generated(hash = 1611958225)
    public RZGL(Long ID, String DWMC, String CZR, String CZLX, String CZSJ,
            String BBS, String FLAGDYDZ, String SB, String SBSJ, String SFBDSJ,
            String SB_LS_ID, String SBDW, String BZ, String HZSJ, String SBCZLX,
            String SB_BHPZ_ID, String WJ_DW, String WJ_LS_ID, long DXZJ,
            String SFXTLR) {
        this.ID = ID;
        this.DWMC = DWMC;
        this.CZR = CZR;
        this.CZLX = CZLX;
        this.CZSJ = CZSJ;
        this.BBS = BBS;
        this.FLAGDYDZ = FLAGDYDZ;
        this.SB = SB;
        this.SBSJ = SBSJ;
        this.SFBDSJ = SFBDSJ;
        this.SB_LS_ID = SB_LS_ID;
        this.SBDW = SBDW;
        this.BZ = BZ;
        this.HZSJ = HZSJ;
        this.SBCZLX = SBCZLX;
        this.SB_BHPZ_ID = SB_BHPZ_ID;
        this.WJ_DW = WJ_DW;
        this.WJ_LS_ID = WJ_LS_ID;
        this.DXZJ = DXZJ;
        this.SFXTLR = SFXTLR;
    }
    @Generated(hash = 315268306)
    public RZGL() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getDWMC() {
        return this.DWMC;
    }
    public void setDWMC(String DWMC) {
        this.DWMC = DWMC;
    }
    public String getCZR() {
        return this.CZR;
    }
    public void setCZR(String CZR) {
        this.CZR = CZR;
    }
    public String getCZLX() {
        return this.CZLX;
    }
    public void setCZLX(String CZLX) {
        this.CZLX = CZLX;
    }
    public String getCZSJ() {
        return this.CZSJ;
    }
    public void setCZSJ(String CZSJ) {
        this.CZSJ = CZSJ;
    }
    public String getBBS() {
        return this.BBS;
    }
    public void setBBS(String BBS) {
        this.BBS = BBS;
    }
    public String getFLAGDYDZ() {
        return this.FLAGDYDZ;
    }
    public void setFLAGDYDZ(String FLAGDYDZ) {
        this.FLAGDYDZ = FLAGDYDZ;
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
    public String getSBDW() {
        return this.SBDW;
    }
    public void setSBDW(String SBDW) {
        this.SBDW = SBDW;
    }
    public String getBZ() {
        return this.BZ;
    }
    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
    public String getHZSJ() {
        return this.HZSJ;
    }
    public void setHZSJ(String HZSJ) {
        this.HZSJ = HZSJ;
    }
    public String getSBCZLX() {
        return this.SBCZLX;
    }
    public void setSBCZLX(String SBCZLX) {
        this.SBCZLX = SBCZLX;
    }
    public String getSB_BHPZ_ID() {
        return this.SB_BHPZ_ID;
    }
    public void setSB_BHPZ_ID(String SB_BHPZ_ID) {
        this.SB_BHPZ_ID = SB_BHPZ_ID;
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
    public long getDXZJ() {
        return this.DXZJ;
    }
    public void setDXZJ(long DXZJ) {
        this.DXZJ = DXZJ;
    }
    public String getSFXTLR() {
        return this.SFXTLR;
    }
    public void setSFXTLR(String SFXTLR) {
        this.SFXTLR = SFXTLR;
    }
}
