package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 通道信息
 */
@Entity(nameInDb = "TDXX", createInDb = false)
public class TDXX {
    @Id
    @Property(nameInDb = "ID")
    private Long id;
    @Property(nameInDb = "TDLX")
    private String tdlx;
    @Property(nameInDb = "SFFY")
    private String sffy;
    @Property(nameInDb = "TDZZXH")
    private String tdzzxh;
    @Property(nameInDb = "SFXTLR")
    private String sfxtlr;
    @Generated(hash = 314384455)
    public TDXX(Long id, String tdlx, String sffy, String tdzzxh, String sfxtlr) {
        this.id = id;
        this.tdlx = tdlx;
        this.sffy = sffy;
        this.tdzzxh = tdzzxh;
        this.sfxtlr = sfxtlr;
    }
    @Generated(hash = 1896710942)
    public TDXX() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTdlx() {
        return this.tdlx;
    }
    public void setTdlx(String tdlx) {
        this.tdlx = tdlx;
    }
    public String getSffy() {
        return this.sffy;
    }
    public void setSffy(String sffy) {
        this.sffy = sffy;
    }
    public String getTdzzxh() {
        return this.tdzzxh;
    }
    public void setTdzzxh(String tdzzxh) {
        this.tdzzxh = tdzzxh;
    }
    public String getSfxtlr() {
        return this.sfxtlr;
    }
    public void setSfxtlr(String sfxtlr) {
        this.sfxtlr = sfxtlr;
    }
}
