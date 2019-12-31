package cn.com.sgcc.dev.model2.ycsb;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;

/**
 * 类说明
 * <p>
 * 间隔参数的实体类
 * </p>
 */
@Entity(nameInDb = "JGCS", createInDb = false)
public class JGCS {
    @Id
    @Property(nameInDb = "ID")
    private int id;
    @Property(nameInDb = "DWMC")
    private String dwmc;
    @Property(nameInDb = "CZMC")
    private String czmc;
    @Property(nameInDb = "JGMC")
    private String jgmc;
    @Property(nameInDb = "JGLX")
    private String jglx;
    @Property(nameInDb = "DYDJ")
    private int dydj;
    @Property(nameInDb = "BZ")
    private String bz;
    @Property(nameInDb = "SH")
    private String sh;
    @Property(nameInDb = "SHYY")
    private String shyy;
    @Property(nameInDb = "TJR")
    private String tjr;
    @Property(nameInDb = "DWTJR")
    private String dwtjr;
    @Property(nameInDb = "CZR")
    private String czr;
    @Property(nameInDb = "SHR")
    private String shr;
    @Property(nameInDb = "SFTCYX")
    private String sftcyx;
    @Property(nameInDb = "WDID")
    private int wdid;
    @Property(nameInDb = "GLDW")
    private String gldw;
    @Property(nameInDb = "DDDW")
    private String dddw;
    @Property(nameInDb = "TCYXSJ")
    private Date tcyxsj;
    @Property(nameInDb = "SFBDSJ")
    private String sfbdsj;
    @Property(nameInDb = "OBJID")
    private String objid;
    @Generated(hash = 1193316421)
    public JGCS(int id, String dwmc, String czmc, String jgmc, String jglx,
            int dydj, String bz, String sh, String shyy, String tjr, String dwtjr,
            String czr, String shr, String sftcyx, int wdid, String gldw,
            String dddw, Date tcyxsj, String sfbdsj, String objid) {
        this.id = id;
        this.dwmc = dwmc;
        this.czmc = czmc;
        this.jgmc = jgmc;
        this.jglx = jglx;
        this.dydj = dydj;
        this.bz = bz;
        this.sh = sh;
        this.shyy = shyy;
        this.tjr = tjr;
        this.dwtjr = dwtjr;
        this.czr = czr;
        this.shr = shr;
        this.sftcyx = sftcyx;
        this.wdid = wdid;
        this.gldw = gldw;
        this.dddw = dddw;
        this.tcyxsj = tcyxsj;
        this.sfbdsj = sfbdsj;
        this.objid = objid;
    }
    @Generated(hash = 1923644428)
    public JGCS() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDwmc() {
        return this.dwmc;
    }
    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }
    public String getCzmc() {
        return this.czmc;
    }
    public void setCzmc(String czmc) {
        this.czmc = czmc;
    }
    public String getJgmc() {
        return this.jgmc;
    }
    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }
    public String getJglx() {
        return this.jglx;
    }
    public void setJglx(String jglx) {
        this.jglx = jglx;
    }
    public int getDydj() {
        return this.dydj;
    }
    public void setDydj(int dydj) {
        this.dydj = dydj;
    }
    public String getBz() {
        return this.bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
    public String getSh() {
        return this.sh;
    }
    public void setSh(String sh) {
        this.sh = sh;
    }
    public String getShyy() {
        return this.shyy;
    }
    public void setShyy(String shyy) {
        this.shyy = shyy;
    }
    public String getTjr() {
        return this.tjr;
    }
    public void setTjr(String tjr) {
        this.tjr = tjr;
    }
    public String getDwtjr() {
        return this.dwtjr;
    }
    public void setDwtjr(String dwtjr) {
        this.dwtjr = dwtjr;
    }
    public String getCzr() {
        return this.czr;
    }
    public void setCzr(String czr) {
        this.czr = czr;
    }
    public String getShr() {
        return this.shr;
    }
    public void setShr(String shr) {
        this.shr = shr;
    }
    public String getSftcyx() {
        return this.sftcyx;
    }
    public void setSftcyx(String sftcyx) {
        this.sftcyx = sftcyx;
    }
    public int getWdid() {
        return this.wdid;
    }
    public void setWdid(int wdid) {
        this.wdid = wdid;
    }
    public String getGldw() {
        return this.gldw;
    }
    public void setGldw(String gldw) {
        this.gldw = gldw;
    }
    public String getDddw() {
        return this.dddw;
    }
    public void setDddw(String dddw) {
        this.dddw = dddw;
    }
    public Date getTcyxsj() {
        return this.tcyxsj;
    }
    public void setTcyxsj(Date tcyxsj) {
        this.tcyxsj = tcyxsj;
    }
    public String getSfbdsj() {
        return this.sfbdsj;
    }
    public void setSfbdsj(String sfbdsj) {
        this.sfbdsj = sfbdsj;
    }
    public String getObjid() {
        return this.objid;
    }
    public void setObjid(String objid) {
        this.objid = objid;
    }}
