package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <p>@description:配置通道关系</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/8
 */
@Entity(nameInDb = "PZTDGX", createInDb = false)
public class PZTDGX {
    @Id
    @Property(nameInDb = "ID")
    private Long ID;
    @Property(nameInDb = "BHPZID")
    private long BHPZID;
    @Property(nameInDb = "TDXXID")
    private long TDXXID;
    @Property(nameInDb = "SFXTLR")
    private String SFXTLR;
    @Generated(hash = 2093203933)
    public PZTDGX(Long ID, long BHPZID, long TDXXID, String SFXTLR) {
        this.ID = ID;
        this.BHPZID = BHPZID;
        this.TDXXID = TDXXID;
        this.SFXTLR = SFXTLR;
    }
    @Generated(hash = 94692678)
    public PZTDGX() {
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
    public long getTDXXID() {
        return this.TDXXID;
    }
    public void setTDXXID(long TDXXID) {
        this.TDXXID = TDXXID;
    }
    public String getSFXTLR() {
        return this.SFXTLR;
    }
    public void setSFXTLR(String SFXTLR) {
        this.SFXTLR = SFXTLR;
    }
}
