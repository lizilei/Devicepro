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
 * @since 2017/9/23
 */
@Entity(nameInDb = "BDW", createInDb = false)
public class BDW {
    @Id
    @Property(nameInDb = "ID")
    private Long ID;
    @Property(nameInDb = "BDWNAME")
    private String BDWNAME;
    @Property(nameInDb = "BZ")
    private String BZ;
    @Property(nameInDb = "SJDW")
    private String SJDW;
    @Property(nameInDb = "BH")
    private String BH;
    @Property(nameInDb = "DDDW")
    private String DDDW;
    @Property(nameInDb = "DWJB")
    private String DWJB;
    @Generated(hash = 1401273902)
    public BDW(Long ID, String BDWNAME, String BZ, String SJDW, String BH,
            String DDDW, String DWJB) {
        this.ID = ID;
        this.BDWNAME = BDWNAME;
        this.BZ = BZ;
        this.SJDW = SJDW;
        this.BH = BH;
        this.DDDW = DDDW;
        this.DWJB = DWJB;
    }
    @Generated(hash = 468256636)
    public BDW() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getBDWNAME() {
        return this.BDWNAME;
    }
    public void setBDWNAME(String BDWNAME) {
        this.BDWNAME = BDWNAME;
    }
    public String getBZ() {
        return this.BZ;
    }
    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
    public String getSJDW() {
        return this.SJDW;
    }
    public void setSJDW(String SJDW) {
        this.SJDW = SJDW;
    }
    public String getBH() {
        return this.BH;
    }
    public void setBH(String BH) {
        this.BH = BH;
    }
    public String getDDDW() {
        return this.DDDW;
    }
    public void setDDDW(String DDDW) {
        this.DDDW = DDDW;
    }
    public String getDWJB() {
        return this.DWJB;
    }
    public void setDWJB(String DWJB) {
        this.DWJB = DWJB;
    }

    @Override
    public String toString() {
        return "BDW{" +
                "ID=" + ID +
                ", BDWNAME='" + BDWNAME + '\'' +
                ", BZ='" + BZ + '\'' +
                ", SJDW='" + SJDW + '\'' +
                ", BH='" + BH + '\'' +
                ", DDDW='" + DDDW + '\'' +
                ", DWJB='" + DWJB + '\'' +
                '}';
    }
}
