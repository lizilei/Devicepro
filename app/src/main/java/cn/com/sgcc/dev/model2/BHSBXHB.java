package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * <p>
 * 保护设备型号实体
 * </p>
 *
 * @author 申华
 * @version 1.0
 * @String 2013-7-4 上午10:07:41
 * @see
 */
@Entity(nameInDb = "BHSBXHB", createInDb = false)
public class BHSBXHB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Property(nameInDb = "ID")
    private Long id;   // Id唯一标识
    @Property(nameInDb = "SBXH")
    private String sbxh; // 设备型号
    @Property(nameInDb = "ZZCJ")
    private String zzcj; // 制造厂家
    @Property(nameInDb = "BBJYM")
    private String bbjym; // 制造厂家
    @Property(nameInDb = "BHLB")
    private String bhlb; // 设备类别
    @Property(nameInDb = "BHLX")
    private String bhlx; // 设备类型
    @Property(nameInDb = "BHFL")
    private String bhfl; // 设备分类
    @Property(nameInDb = "BHGN")
    private String bhgn; //
    @Property(nameInDb = "bj")
    private String bj; //
    @Property(nameInDb = "WDID")
    private long wdid; //
    @Property(nameInDb = "TB")
    private String tb; //
    @Property(nameInDb = "RJXD")
    private String rjxd; //
    @Property(nameInDb = "SH")
    private String sh;   // 审核状态
    @Property(nameInDb = "SHR")
    private String shr;  // 审核人
    @Property(nameInDb = "DWTJR")
    private String dwtjr;  //
    @Property(nameInDb = "TJR")
    private String tjr;  //
    @Property(nameInDb = "CZR")
    private String czr;  // 操作人
    @Property(nameInDb = "SHYY")
    private String shyy; // 审核不通过原因
    @Property(nameInDb = "YZRQ")
    private String yzrq; //
    @Property(nameInDb = "BZ")
    private String bz;   // 设备描述
    @Property(nameInDb = "SFQY")
    private String sfqy; // 是否启用
    @Property(nameInDb = "CODE")
    private String code; // 设备编码
    @Property(nameInDb = "BBLX")
    private String bblx; // 版本类型
    @Property(nameInDb = "BBXH")
    private int bbxh;
    @Property(nameInDb = "SFBDSJ")
    private String sfbdsj; //
    @Property(nameInDb = "BHSBXHXX")
    private String bhsbxhxx; //
    @Property(nameInDb = "STATE")
    private String state;// 标记状态
    @Property(nameInDb = "XHXX")
    private String xhxx; //
    @Property(nameInDb = "CZ")
    private String cz; //
    @Property(nameInDb = "FG")
    private String fg; //
    @Property(nameInDb = "FNAME")
    private String fname; //
    @Property(nameInDb = "LS_BHSBXHXX")
    private String ls_bhsbxhxx; //
    @Property(nameInDb = "LS_CODE")
    private String ls_code; //
    @Property(nameInDb = "T_XX")
    private String t_xx; //
    @Property(nameInDb = "SFXTLR")
    private String sfxtlr; //
    @Property(nameInDb = "ED_TAG")
    private String ED_TAG; //
    @Generated(hash = 118438317)
    public BHSBXHB(Long id, String sbxh, String zzcj, String bbjym, String bhlb,
            String bhlx, String bhfl, String bhgn, String bj, long wdid, String tb,
            String rjxd, String sh, String shr, String dwtjr, String tjr,
            String czr, String shyy, String yzrq, String bz, String sfqy,
            String code, String bblx, int bbxh, String sfbdsj, String bhsbxhxx,
            String state, String xhxx, String cz, String fg, String fname,
            String ls_bhsbxhxx, String ls_code, String t_xx, String sfxtlr,
            String ED_TAG) {
        this.id = id;
        this.sbxh = sbxh;
        this.zzcj = zzcj;
        this.bbjym = bbjym;
        this.bhlb = bhlb;
        this.bhlx = bhlx;
        this.bhfl = bhfl;
        this.bhgn = bhgn;
        this.bj = bj;
        this.wdid = wdid;
        this.tb = tb;
        this.rjxd = rjxd;
        this.sh = sh;
        this.shr = shr;
        this.dwtjr = dwtjr;
        this.tjr = tjr;
        this.czr = czr;
        this.shyy = shyy;
        this.yzrq = yzrq;
        this.bz = bz;
        this.sfqy = sfqy;
        this.code = code;
        this.bblx = bblx;
        this.bbxh = bbxh;
        this.sfbdsj = sfbdsj;
        this.bhsbxhxx = bhsbxhxx;
        this.state = state;
        this.xhxx = xhxx;
        this.cz = cz;
        this.fg = fg;
        this.fname = fname;
        this.ls_bhsbxhxx = ls_bhsbxhxx;
        this.ls_code = ls_code;
        this.t_xx = t_xx;
        this.sfxtlr = sfxtlr;
        this.ED_TAG = ED_TAG;
    }
    @Generated(hash = 1276788725)
    public BHSBXHB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSbxh() {
        return this.sbxh;
    }
    public void setSbxh(String sbxh) {
        this.sbxh = sbxh;
    }
    public String getZzcj() {
        return this.zzcj;
    }
    public void setZzcj(String zzcj) {
        this.zzcj = zzcj;
    }
    public String getBbjym() {
        return this.bbjym;
    }
    public void setBbjym(String bbjym) {
        this.bbjym = bbjym;
    }
    public String getBhlb() {
        return this.bhlb;
    }
    public void setBhlb(String bhlb) {
        this.bhlb = bhlb;
    }
    public String getBhlx() {
        return this.bhlx;
    }
    public void setBhlx(String bhlx) {
        this.bhlx = bhlx;
    }
    public String getBhfl() {
        return this.bhfl;
    }
    public void setBhfl(String bhfl) {
        this.bhfl = bhfl;
    }
    public String getBhgn() {
        return this.bhgn;
    }
    public void setBhgn(String bhgn) {
        this.bhgn = bhgn;
    }
    public String getBj() {
        return this.bj;
    }
    public void setBj(String bj) {
        this.bj = bj;
    }
    public long getWdid() {
        return this.wdid;
    }
    public void setWdid(long wdid) {
        this.wdid = wdid;
    }
    public String getTb() {
        return this.tb;
    }
    public void setTb(String tb) {
        this.tb = tb;
    }
    public String getRjxd() {
        return this.rjxd;
    }
    public void setRjxd(String rjxd) {
        this.rjxd = rjxd;
    }
    public String getSh() {
        return this.sh;
    }
    public void setSh(String sh) {
        this.sh = sh;
    }
    public String getShr() {
        return this.shr;
    }
    public void setShr(String shr) {
        this.shr = shr;
    }
    public String getDwtjr() {
        return this.dwtjr;
    }
    public void setDwtjr(String dwtjr) {
        this.dwtjr = dwtjr;
    }
    public String getTjr() {
        return this.tjr;
    }
    public void setTjr(String tjr) {
        this.tjr = tjr;
    }
    public String getCzr() {
        return this.czr;
    }
    public void setCzr(String czr) {
        this.czr = czr;
    }
    public String getShyy() {
        return this.shyy;
    }
    public void setShyy(String shyy) {
        this.shyy = shyy;
    }
    public String getYzrq() {
        return this.yzrq;
    }
    public void setYzrq(String yzrq) {
        this.yzrq = yzrq;
    }
    public String getBz() {
        return this.bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
    public String getSfqy() {
        return this.sfqy;
    }
    public void setSfqy(String sfqy) {
        this.sfqy = sfqy;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getBblx() {
        return this.bblx;
    }
    public void setBblx(String bblx) {
        this.bblx = bblx;
    }
    public int getBbxh() {
        return this.bbxh;
    }
    public void setBbxh(int bbxh) {
        this.bbxh = bbxh;
    }
    public String getSfbdsj() {
        return this.sfbdsj;
    }
    public void setSfbdsj(String sfbdsj) {
        this.sfbdsj = sfbdsj;
    }
    public String getBhsbxhxx() {
        return this.bhsbxhxx;
    }
    public void setBhsbxhxx(String bhsbxhxx) {
        this.bhsbxhxx = bhsbxhxx;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getXhxx() {
        return this.xhxx;
    }
    public void setXhxx(String xhxx) {
        this.xhxx = xhxx;
    }
    public String getCz() {
        return this.cz;
    }
    public void setCz(String cz) {
        this.cz = cz;
    }
    public String getFg() {
        return this.fg;
    }
    public void setFg(String fg) {
        this.fg = fg;
    }
    public String getFname() {
        return this.fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLs_bhsbxhxx() {
        return this.ls_bhsbxhxx;
    }
    public void setLs_bhsbxhxx(String ls_bhsbxhxx) {
        this.ls_bhsbxhxx = ls_bhsbxhxx;
    }
    public String getLs_code() {
        return this.ls_code;
    }
    public void setLs_code(String ls_code) {
        this.ls_code = ls_code;
    }
    public String getT_xx() {
        return this.t_xx;
    }
    public void setT_xx(String t_xx) {
        this.t_xx = t_xx;
    }
    public String getSfxtlr() {
        return this.sfxtlr;
    }
    public void setSfxtlr(String sfxtlr) {
        this.sfxtlr = sfxtlr;
    }
    public String getED_TAG() {
        return this.ED_TAG;
    }
    public void setED_TAG(String ED_TAG) {
        this.ED_TAG = ED_TAG;
    }
}
