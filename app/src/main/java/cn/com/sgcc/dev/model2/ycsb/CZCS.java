package cn.com.sgcc.dev.model2.ycsb;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

@Entity(nameInDb = "CZCS", createInDb = false)
public class CZCS {
    @Id
    @Property(nameInDb = "ID")
    private Long id;
    @Property(nameInDb = "BH")
    private String bh;
    @Property(nameInDb = "CZMC")
    private String czmc;
    @Property(nameInDb = "CZZGDYDJ")
    private long czzgdydj;
    @Property(nameInDb = "BZ")
    private String bz;
    @Property(nameInDb = "GLDW")
    private String gldw;
    @Property(nameInDb = "VF")
    private int vf;
    @Property(nameInDb = "CZR")
    private String czr;
    @Property(nameInDb = "WDID")
    private int WDID;
    @Property(nameInDb = "CZSZDQ")
    private String czszdq;
    @Property(nameInDb = "TYRQ")
    private Date tyrq;
    @Property(nameInDb = "BDZLX")
    private String bdzlx;
    @Property(nameInDb = "CZSJ")
    private Date czsj;
    @Property(nameInDb = "OBJID")
    private String objid;
    @Property(nameInDb = "SFTCYX")
    private String sftcyx;
    @Property(nameInDb = "TCYXSJ")
    private Date tcyxsj;
    @Property(nameInDb = "SHYY")
    private String shyy;
    @Property(nameInDb = "TJR")
    private String tjr;
    @Property(nameInDb = "SHR")
    private String shr;
    @Property(nameInDb = "SH")
    private String sh;
    @Property(nameInDb = "SFBDSJ")
    private String sfbdsj;
    @Property(nameInDb = "SB")
    private String sb;
    @Property(nameInDb = "SBSJ")
    private Date sbsj;
    @Property(nameInDb = "SB_LS_ID")
    private String sb_ls_id;
    @Property(nameInDb = "SBDW")
    private String sbdw;
    @Property(nameInDb = "CZSX")
    private String czsx;
    @Property(nameInDb = "HZSJ")
    private Date hzsj;
    @Property(nameInDb = "SBCZLX")
    private String sbczlx;
    @Property(nameInDb = "SFXTLR")
    private String sfxtlr;
    @Property(nameInDb = "WJ_DW")
    private String wj_dw;
    @Property(nameInDb = "WJ_LS_ID")
    private String wj_ls_id;
    @Property(nameInDb = "ISFHZ")
    private String isfhz;
    //首字母
    @Transient
    private String first;

    //是否选中，在线登录需要
    @Transient
    private boolean isChecked;

    @Generated(hash = 618854519)
    public CZCS(Long id, String bh, String czmc, long czzgdydj, String bz,
            String gldw, int vf, String czr, int WDID, String czszdq, Date tyrq,
            String bdzlx, Date czsj, String objid, String sftcyx, Date tcyxsj,
            String shyy, String tjr, String shr, String sh, String sfbdsj,
            String sb, Date sbsj, String sb_ls_id, String sbdw, String czsx,
            Date hzsj, String sbczlx, String sfxtlr, String wj_dw, String wj_ls_id,
            String isfhz) {
        this.id = id;
        this.bh = bh;
        this.czmc = czmc;
        this.czzgdydj = czzgdydj;
        this.bz = bz;
        this.gldw = gldw;
        this.vf = vf;
        this.czr = czr;
        this.WDID = WDID;
        this.czszdq = czszdq;
        this.tyrq = tyrq;
        this.bdzlx = bdzlx;
        this.czsj = czsj;
        this.objid = objid;
        this.sftcyx = sftcyx;
        this.tcyxsj = tcyxsj;
        this.shyy = shyy;
        this.tjr = tjr;
        this.shr = shr;
        this.sh = sh;
        this.sfbdsj = sfbdsj;
        this.sb = sb;
        this.sbsj = sbsj;
        this.sb_ls_id = sb_ls_id;
        this.sbdw = sbdw;
        this.czsx = czsx;
        this.hzsj = hzsj;
        this.sbczlx = sbczlx;
        this.sfxtlr = sfxtlr;
        this.wj_dw = wj_dw;
        this.wj_ls_id = wj_ls_id;
        this.isfhz = isfhz;
    }

    @Generated(hash = 1276010123)
    public CZCS() {
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBh() {
        return this.bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getCzmc() {
        return this.czmc;
    }

    public void setCzmc(String czmc) {
        this.czmc = czmc;
    }

    public long getCzzgdydj() {
        return this.czzgdydj;
    }

    public void setCzzgdydj(long czzgdydj) {
        this.czzgdydj = czzgdydj;
    }

    public String getBz() {
        return this.bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getGldw() {
        return this.gldw;
    }

    public void setGldw(String gldw) {
        this.gldw = gldw;
    }

    public int getVf() {
        return this.vf;
    }

    public void setVf(int vf) {
        this.vf = vf;
    }

    public String getCzr() {
        return this.czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }

    public int getWDID() {
        return this.WDID;
    }

    public void setWDID(int WDID) {
        this.WDID = WDID;
    }

    public String getCzszdq() {
        return this.czszdq;
    }

    public void setCzszdq(String czszdq) {
        this.czszdq = czszdq;
    }

    public Date getTyrq() {
        return this.tyrq;
    }

    public void setTyrq(Date tyrq) {
        this.tyrq = tyrq;
    }

    public String getBdzlx() {
        return this.bdzlx;
    }

    public void setBdzlx(String bdzlx) {
        this.bdzlx = bdzlx;
    }

    public Date getCzsj() {
        return this.czsj;
    }

    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }

    public String getObjid() {
        return this.objid;
    }

    public void setObjid(String objid) {
        this.objid = objid;
    }

    public String getSftcyx() {
        return this.sftcyx;
    }

    public void setSftcyx(String sftcyx) {
        this.sftcyx = sftcyx;
    }

    public Date getTcyxsj() {
        return this.tcyxsj;
    }

    public void setTcyxsj(Date tcyxsj) {
        this.tcyxsj = tcyxsj;
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

    public String getShr() {
        return this.shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public String getSh() {
        return this.sh;
    }

    public void setSh(String sh) {
        this.sh = sh;
    }

    public String getSfbdsj() {
        return this.sfbdsj;
    }

    public void setSfbdsj(String sfbdsj) {
        this.sfbdsj = sfbdsj;
    }

    public String getSb() {
        return this.sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public Date getSbsj() {
        return this.sbsj;
    }

    public void setSbsj(Date sbsj) {
        this.sbsj = sbsj;
    }

    public String getSb_ls_id() {
        return this.sb_ls_id;
    }

    public void setSb_ls_id(String sb_ls_id) {
        this.sb_ls_id = sb_ls_id;
    }

    public String getSbdw() {
        return this.sbdw;
    }

    public void setSbdw(String sbdw) {
        this.sbdw = sbdw;
    }

    public String getCzsx() {
        return this.czsx;
    }

    public void setCzsx(String czsx) {
        this.czsx = czsx;
    }

    public Date getHzsj() {
        return this.hzsj;
    }

    public void setHzsj(Date hzsj) {
        this.hzsj = hzsj;
    }

    public String getSbczlx() {
        return this.sbczlx;
    }

    public void setSbczlx(String sbczlx) {
        this.sbczlx = sbczlx;
    }

    public String getSfxtlr() {
        return this.sfxtlr;
    }

    public void setSfxtlr(String sfxtlr) {
        this.sfxtlr = sfxtlr;
    }

    public String getWj_dw() {
        return this.wj_dw;
    }

    public void setWj_dw(String wj_dw) {
        this.wj_dw = wj_dw;
    }

    public String getWj_ls_id() {
        return this.wj_ls_id;
    }

    public void setWj_ls_id(String wj_ls_id) {
        this.wj_ls_id = wj_ls_id;
    }

    public String getIsfhz() {
        return this.isfhz;
    }

    public void setIsfhz(String isfhz) {
        this.isfhz = isfhz;
    }


    @Override
    public String toString() {
        return "CZCS{" +
                "czmc='" + czmc + '\'' +
                ", gldw='" + gldw + '\'' +
                '}';
    }
}