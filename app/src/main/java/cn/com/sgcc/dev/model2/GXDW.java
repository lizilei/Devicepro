package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <p>@description:管辖单位 </p>
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/8
 */
@Entity(nameInDb = "GXDW", createInDb = false)
public class GXDW {
    @Id
    @Property(nameInDb = "ID")
    private Long ID;
    @Property(nameInDb = "BH")
    private String BH;
    @Property(nameInDb = "DWMC")
    private String DWMC;
    @Property(nameInDb = "JSSJY")
    private String JSSJY;
    @Property(nameInDb = "BZ")
    private String BZ;
    @Property(nameInDb = "DWLB")
    private String DWLB;
    @Property(nameInDb = "VF")
    private int VF;
    @Property(nameInDb = "GLDW")
    private String GLDW;
    @Property(nameInDb = "FL")
    private String FL;
    @Property(nameInDb = "CZR")
    private String CZR;
    @Property(nameInDb = "WDID")
    private int WDID;
    @Property(nameInDb = "WZSDWLB")
    private String WZSDWLB;
    @Property(nameInDb = "DDDW")
    private String DDDW;
    @Property(nameInDb = "SFXTLR")
    private String SFXTLR;
    @Generated(hash = 54188543)
    public GXDW(Long ID, String BH, String DWMC, String JSSJY, String BZ,
            String DWLB, int VF, String GLDW, String FL, String CZR, int WDID,
            String WZSDWLB, String DDDW, String SFXTLR) {
        this.ID = ID;
        this.BH = BH;
        this.DWMC = DWMC;
        this.JSSJY = JSSJY;
        this.BZ = BZ;
        this.DWLB = DWLB;
        this.VF = VF;
        this.GLDW = GLDW;
        this.FL = FL;
        this.CZR = CZR;
        this.WDID = WDID;
        this.WZSDWLB = WZSDWLB;
        this.DDDW = DDDW;
        this.SFXTLR = SFXTLR;
    }
    @Generated(hash = 1982541487)
    public GXDW() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getBH() {
        return this.BH;
    }
    public void setBH(String BH) {
        this.BH = BH;
    }
    public String getDWMC() {
        return this.DWMC;
    }
    public void setDWMC(String DWMC) {
        this.DWMC = DWMC;
    }
    public String getJSSJY() {
        return this.JSSJY;
    }
    public void setJSSJY(String JSSJY) {
        this.JSSJY = JSSJY;
    }
    public String getBZ() {
        return this.BZ;
    }
    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
    public String getDWLB() {
        return this.DWLB;
    }
    public void setDWLB(String DWLB) {
        this.DWLB = DWLB;
    }
    public int getVF() {
        return this.VF;
    }
    public void setVF(int VF) {
        this.VF = VF;
    }
    public String getGLDW() {
        return this.GLDW;
    }
    public void setGLDW(String GLDW) {
        this.GLDW = GLDW;
    }
    public String getFL() {
        return this.FL;
    }
    public void setFL(String FL) {
        this.FL = FL;
    }
    public String getCZR() {
        return this.CZR;
    }
    public void setCZR(String CZR) {
        this.CZR = CZR;
    }
    public int getWDID() {
        return this.WDID;
    }
    public void setWDID(int WDID) {
        this.WDID = WDID;
    }
    public String getWZSDWLB() {
        return this.WZSDWLB;
    }
    public void setWZSDWLB(String WZSDWLB) {
        this.WZSDWLB = WZSDWLB;
    }
    public String getDDDW() {
        return this.DDDW;
    }
    public void setDDDW(String DDDW) {
        this.DDDW = DDDW;
    }
    public String getSFXTLR() {
        return this.SFXTLR;
    }
    public void setSFXTLR(String SFXTLR) {
        this.SFXTLR = SFXTLR;
    }
}
