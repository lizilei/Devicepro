package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * @类 BZSJFL.java
 *    <p>
 *    标准数据分类表
 *    </p>
 * @author 赵丹
 * @version 1.0
 * @Date May 11, 2012 2:07:25 PM
 * @see
 */
@Entity(nameInDb = "BZSJFL",createInDb = false)
public class BZSJFL {
    @Id
    @Property(nameInDb = "ID")
    private int        id;
    @Property(nameInDb = "BZSJFLMC")
    private String     bzsjflmc;
    @Property(nameInDb = "BZ")
    private String     bz;
    @Property(nameInDb = "SFKBJ")
    private String     sfkbj;
    @Generated(hash = 2082837556)
    public BZSJFL(int id, String bzsjflmc, String bz, String sfkbj) {
        this.id = id;
        this.bzsjflmc = bzsjflmc;
        this.bz = bz;
        this.sfkbj = sfkbj;
    }
    @Generated(hash = 1670944254)
    public BZSJFL() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBzsjflmc() {
        return this.bzsjflmc;
    }
    public void setBzsjflmc(String bzsjflmc) {
        this.bzsjflmc = bzsjflmc;
    }
    public String getBz() {
        return this.bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
    public String getSfkbj() {
        return this.sfkbj;
    }
    public void setSfkbj(String sfkbj) {
        this.sfkbj = sfkbj;
    }
}
