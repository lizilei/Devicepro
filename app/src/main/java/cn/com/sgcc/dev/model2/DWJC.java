package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <p>@description:单位简称</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/8
 */
@Entity(nameInDb = "DWJC", createInDb = false)
public class DWJC {
    @Id
    @Property(nameInDb = "ID")
    private Long ID;
    @Property(nameInDb = "BH")
    private String BH;
    @Property(nameInDb = "DWJC")
    private String DWJC;
    @Property(nameInDb = "DDDW")
    private String DDDW;
    @Property(nameInDb = "VF")
    private int VF;
    @Property(nameInDb = "CZR")
    private String CZR;
    @Property(nameInDb = "WDID")
    private int WDID;
    @Property(nameInDb = "SSQY")
    private String SSQY;
    @Property(nameInDb = "QYBH")
    private float QYBH;
    @Generated(hash = 1747944686)
    public DWJC(Long ID, String BH, String DWJC, String DDDW, int VF, String CZR,
            int WDID, String SSQY, float QYBH) {
        this.ID = ID;
        this.BH = BH;
        this.DWJC = DWJC;
        this.DDDW = DDDW;
        this.VF = VF;
        this.CZR = CZR;
        this.WDID = WDID;
        this.SSQY = SSQY;
        this.QYBH = QYBH;
    }
    @Generated(hash = 1572159413)
    public DWJC() {
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
    public String getDWJC() {
        return this.DWJC;
    }
    public void setDWJC(String DWJC) {
        this.DWJC = DWJC;
    }
    public String getDDDW() {
        return this.DDDW;
    }
    public void setDDDW(String DDDW) {
        this.DDDW = DDDW;
    }
    public int getVF() {
        return this.VF;
    }
    public void setVF(int VF) {
        this.VF = VF;
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
    public String getSSQY() {
        return this.SSQY;
    }
    public void setSSQY(String SSQY) {
        this.SSQY = SSQY;
    }
    public float getQYBH() {
        return this.QYBH;
    }
    public void setQYBH(float QYBH) {
        this.QYBH = QYBH;
    }
}
