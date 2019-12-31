package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <p>@description:单位类型</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/8
 */
@Entity(nameInDb = "DWLX",createInDb = false)
public class DWLX {
    @Id
    @Property(nameInDb = "ID")
    private Long ID;
    @Property(nameInDb = "DWMC")
    private String DWMC;
    @Property(nameInDb = "BZ")
    private String BZ;
    @Property(nameInDb = "DWLX")
    private String DWLX;
    @Property(nameInDb = "VF")
    private int VF;
    @Property(nameInDb = "MC")
    private String MC;
    @Property(nameInDb = "CZR")
    private String CZR;
    @Property(nameInDb = "WDID")
    private int WDID;
    @Generated(hash = 225385552)
    public DWLX(Long ID, String DWMC, String BZ, String DWLX, int VF, String MC,
            String CZR, int WDID) {
        this.ID = ID;
        this.DWMC = DWMC;
        this.BZ = BZ;
        this.DWLX = DWLX;
        this.VF = VF;
        this.MC = MC;
        this.CZR = CZR;
        this.WDID = WDID;
    }
    @Generated(hash = 1598090636)
    public DWLX() {
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
    public String getBZ() {
        return this.BZ;
    }
    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
    public String getDWLX() {
        return this.DWLX;
    }
    public void setDWLX(String DWLX) {
        this.DWLX = DWLX;
    }
    public int getVF() {
        return this.VF;
    }
    public void setVF(int VF) {
        this.VF = VF;
    }
    public String getMC() {
        return this.MC;
    }
    public void setMC(String MC) {
        this.MC = MC;
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
}
