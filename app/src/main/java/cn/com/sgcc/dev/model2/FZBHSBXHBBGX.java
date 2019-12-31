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
@Entity(nameInDb = "FZBHSBXHBBGX", createInDb = false)
public class FZBHSBXHBBGX {
    @Id
    @Property(nameInDb = "ID")
    private Long ID;
    @Property(nameInDb = "FZBHSBID")
    private long FZBHSBID;
    @Property(nameInDb = "RJBBCODE")
    private String RJBBCODE;
    @Property(nameInDb = "SCSJ")
    private String SCSJ;
    @Property(nameInDb = "SFXTLR")
    private String SFXTLR;
    @Property(nameInDb = "SB_FZBHSB_ID")
    private String SB_FZBHSB_ID;
    @Generated(hash = 2098333925)
    public FZBHSBXHBBGX(Long ID, long FZBHSBID, String RJBBCODE, String SCSJ,
            String SFXTLR, String SB_FZBHSB_ID) {
        this.ID = ID;
        this.FZBHSBID = FZBHSBID;
        this.RJBBCODE = RJBBCODE;
        this.SCSJ = SCSJ;
        this.SFXTLR = SFXTLR;
        this.SB_FZBHSB_ID = SB_FZBHSB_ID;
    }
    @Generated(hash = 868075510)
    public FZBHSBXHBBGX() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public long getFZBHSBID() {
        return this.FZBHSBID;
    }
    public void setFZBHSBID(long FZBHSBID) {
        this.FZBHSBID = FZBHSBID;
    }
    public String getRJBBCODE() {
        return this.RJBBCODE;
    }
    public void setRJBBCODE(String RJBBCODE) {
        this.RJBBCODE = RJBBCODE;
    }
    public String getSCSJ() {
        return this.SCSJ;
    }
    public void setSCSJ(String SCSJ) {
        this.SCSJ = SCSJ;
    }
    public String getSFXTLR() {
        return this.SFXTLR;
    }
    public void setSFXTLR(String SFXTLR) {
        this.SFXTLR = SFXTLR;
    }
    public String getSB_FZBHSB_ID() {
        return this.SB_FZBHSB_ID;
    }
    public void setSB_FZBHSB_ID(String SB_FZBHSB_ID) {
        this.SB_FZBHSB_ID = SB_FZBHSB_ID;
    }
}
