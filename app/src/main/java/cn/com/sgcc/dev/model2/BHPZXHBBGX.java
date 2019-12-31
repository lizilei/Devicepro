package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import org.greenrobot.greendao.annotation.Generated;

/**
 * <p>@description:保护配置软件版本关系表</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/19
 */
@Entity(nameInDb = "BHPZXHBBGX", createInDb = false)
public class BHPZXHBBGX {
    @Id
    @Property(nameInDb = "ID")
    private Long ID;
    @Property(nameInDb = "BHPZID")
    private long BHPZID;
    @Property(nameInDb = "RJBBCODE")
    private String RJBBCODE;
    @Property(nameInDb = "SCSJ")
    private String SCSJ;
    @Property(nameInDb = "SFXTLR")
    private String SFXTLR;
    @Generated(hash = 483681781)
    public BHPZXHBBGX(Long ID, long BHPZID, String RJBBCODE, String SCSJ,
            String SFXTLR) {
        this.ID = ID;
        this.BHPZID = BHPZID;
        this.RJBBCODE = RJBBCODE;
        this.SCSJ = SCSJ;
        this.SFXTLR = SFXTLR;
    }
    @Generated(hash = 1562264475)
    public BHPZXHBBGX() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public long getBHPZID() {
        return this.BHPZID;
    }
    public void setBHPZID(long BHPZID) {
        this.BHPZID = BHPZID;
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
}
