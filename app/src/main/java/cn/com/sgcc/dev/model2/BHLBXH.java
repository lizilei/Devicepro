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
 * @since 2017/9/7
 */

@Entity(nameInDb = "BHLBXH", createInDb = false)
public class BHLBXH {
    @Id
    @Property(nameInDb = "ID")
    private Long id;
    @Property(nameInDb = "BHLB")
    private String bhlb;
    @Property(nameInDb = "BHLBXH")
    private String bhlbxh;
    @Property(nameInDb = "BZ")
    private String bz;
    @Generated(hash = 79198212)
    public BHLBXH(Long id, String bhlb, String bhlbxh, String bz) {
        this.id = id;
        this.bhlb = bhlb;
        this.bhlbxh = bhlbxh;
        this.bz = bz;
    }
    @Generated(hash = 45765798)
    public BHLBXH() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBhlb() {
        return this.bhlb;
    }
    public void setBhlb(String bhlb) {
        this.bhlb = bhlb;
    }
    public String getBhlbxh() {
        return this.bhlbxh;
    }
    public void setBhlbxh(String bhlbxh) {
        this.bhlbxh = bhlbxh;
    }
    public String getBz() {
        return this.bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
}
