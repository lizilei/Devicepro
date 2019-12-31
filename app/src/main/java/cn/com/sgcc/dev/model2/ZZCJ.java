package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/8
 */
@Entity(nameInDb = "ZZCJ", createInDb = false)
public class ZZCJ {
    @Id
    @Property(nameInDb = "ID")
    private Long ID;
    @Property(nameInDb = "MC")
    private String MC;
    @Property(nameInDb = "BZ")
    private String BZ;
    @Property(nameInDb = "BH")
    private String BH;
    @Property(nameInDb = "VF")
    private int VF;
    @Property(nameInDb = "WZ")
    private String WZ;
    @Property(nameInDb = "CZR")
    private String CZR;
    @Property(nameInDb = "WDID")
    private int WDID;
    @Property(nameInDb = "CJBM")
    private String CJBM;
    @Property(nameInDb = "SBXH")
    private int SBXH;
    @Property(nameInDb = "ED_TAG")
    private String ED_TAG;
    @Generated(hash = 1852901576)
    public ZZCJ(Long ID, String MC, String BZ, String BH, int VF, String WZ,
            String CZR, int WDID, String CJBM, int SBXH, String ED_TAG) {
        this.ID = ID;
        this.MC = MC;
        this.BZ = BZ;
        this.BH = BH;
        this.VF = VF;
        this.WZ = WZ;
        this.CZR = CZR;
        this.WDID = WDID;
        this.CJBM = CJBM;
        this.SBXH = SBXH;
        this.ED_TAG = ED_TAG;
    }
    @Generated(hash = 1810509100)
    public ZZCJ() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getMC() {
        return this.MC;
    }
    public void setMC(String MC) {
        this.MC = MC;
    }
    public String getBZ() {
        return this.BZ;
    }
    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
    public String getBH() {
        return this.BH;
    }
    public void setBH(String BH) {
        this.BH = BH;
    }
    public int getVF() {
        return this.VF;
    }
    public void setVF(int VF) {
        this.VF = VF;
    }
    public String getWZ() {
        return this.WZ;
    }
    public void setWZ(String WZ) {
        this.WZ = WZ;
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
    public String getCJBM() {
        return this.CJBM;
    }
    public void setCJBM(String CJBM) {
        this.CJBM = CJBM;
    }
    public int getSBXH() {
        return this.SBXH;
    }
    public void setSBXH(int SBXH) {
        this.SBXH = SBXH;
    }
    public String getED_TAG() {
        return this.ED_TAG;
    }
    public void setED_TAG(String ED_TAG) {
        this.ED_TAG = ED_TAG;
    }
}
