package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * @author 赵丹
 * @version 1.0
 * @类 BZSJ.java
 * <p>
 * 标准数据类
 * </p>
 * @Date May 11, 2012 1:57:25 PM
 * @see
 */
@Entity(nameInDb = "BZSJ", createInDb = false)
public class BZSJ {
    @Id
    @Property(nameInDb = "ID")
    private Long id;
    @Property(nameInDb = "BZSJSXMC")
    private String bzsjSxmc;
    @Property(nameInDb = "BZ")
    private String bz;
    @Property(nameInDb = "BZSJFLID")
    private int bzsjflId;
    @Property(nameInDb = "BZSJINDEX")
    private int bzsjIndex;
    @Generated(hash = 709162158)
    public BZSJ(Long id, String bzsjSxmc, String bz, int bzsjflId, int bzsjIndex) {
        this.id = id;
        this.bzsjSxmc = bzsjSxmc;
        this.bz = bz;
        this.bzsjflId = bzsjflId;
        this.bzsjIndex = bzsjIndex;
    }
    @Generated(hash = 185917689)
    public BZSJ() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBzsjSxmc() {
        return this.bzsjSxmc;
    }
    public void setBzsjSxmc(String bzsjSxmc) {
        this.bzsjSxmc = bzsjSxmc;
    }
    public String getBz() {
        return this.bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
    public int getBzsjflId() {
        return this.bzsjflId;
    }
    public void setBzsjflId(int bzsjflId) {
        this.bzsjflId = bzsjflId;
    }
    public int getBzsjIndex() {
        return this.bzsjIndex;
    }
    public void setBzsjIndex(int bzsjIndex) {
        this.bzsjIndex = bzsjIndex;
    }
}
