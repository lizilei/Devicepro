package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * 连接器信息实体类
 */
@Entity(nameInDb = "LJQXX", createInDb = false)
public class LJQXX implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private long id;
    @Property(nameInDb = "BHPZID")
    private long bhpzid;
    @Property(nameInDb = "CTZJBH")
    private String ctzjbh;  // 插头组件编号
    @Property(nameInDb = "CTZJZZCJ")
    private String ctzjzzcj;// 插头组件制造厂家
    @Property(nameInDb = "CTZJXH")
    private String ctzjxh;  // 插头组件型号
    @Property(nameInDb = "JKLX")
    private String jklx;    // 接口类型
    @Property(nameInDb = "JKYT")
    private String jkyt;    // 接口用途

    @Property(nameInDb = "JTZJQFRQ")
    private String   jtzjqfrq;// 插头组件铅封日期

    @Property(nameInDb = "GHSJ")
    private String ghsj;    // 更换时间
    @Property(nameInDb = "GHYY")
    private String ghyy;    // 更换原因
    @Property(nameInDb = "LSCTID")
    private long lsctid;  // 历史插头ID
    @Property(nameInDb = "SJBS")
    private String sjbs;    // 数据标识
    @Property(nameInDb = "SH")
    private String sh;      //
    @Property(nameInDb = "SBDW")
    private String sbdw;
    @Property(nameInDb = "SB")
    private String sb;
    @Property(nameInDb = "SB_LS_ID")
    private String sb_ls_id;
    //    @Property(nameInDb = "LS_ZSJ_ID")
    @Transient
    private String ls_zsj_id;
    @Property(nameInDb = "SFXTLR")
    private String sfxtlr;
    @Property(nameInDb = "EWMXX")
    private String ewmxx;  //二维码信息
    @Property(nameInDb = "ED_TAG")
    private String ED_TAG;
    @Property(nameInDb = "DZPXX")
    private String dzpxx;
    @Generated(hash = 1366470732)
    public LJQXX(long id, long bhpzid, String ctzjbh, String ctzjzzcj,
            String ctzjxh, String jklx, String jkyt, String jtzjqfrq, String ghsj,
            String ghyy, long lsctid, String sjbs, String sh, String sbdw,
            String sb, String sb_ls_id, String sfxtlr, String ewmxx, String ED_TAG,
            String dzpxx) {
        this.id = id;
        this.bhpzid = bhpzid;
        this.ctzjbh = ctzjbh;
        this.ctzjzzcj = ctzjzzcj;
        this.ctzjxh = ctzjxh;
        this.jklx = jklx;
        this.jkyt = jkyt;
        this.jtzjqfrq = jtzjqfrq;
        this.ghsj = ghsj;
        this.ghyy = ghyy;
        this.lsctid = lsctid;
        this.sjbs = sjbs;
        this.sh = sh;
        this.sbdw = sbdw;
        this.sb = sb;
        this.sb_ls_id = sb_ls_id;
        this.sfxtlr = sfxtlr;
        this.ewmxx = ewmxx;
        this.ED_TAG = ED_TAG;
        this.dzpxx = dzpxx;
    }
    @Generated(hash = 1413132654)
    public LJQXX() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getBhpzid() {
        return this.bhpzid;
    }
    public void setBhpzid(long bhpzid) {
        this.bhpzid = bhpzid;
    }
    public String getCtzjbh() {
        return this.ctzjbh;
    }
    public void setCtzjbh(String ctzjbh) {
        this.ctzjbh = ctzjbh;
    }
    public String getCtzjzzcj() {
        return this.ctzjzzcj;
    }
    public void setCtzjzzcj(String ctzjzzcj) {
        this.ctzjzzcj = ctzjzzcj;
    }
    public String getCtzjxh() {
        return this.ctzjxh;
    }
    public void setCtzjxh(String ctzjxh) {
        this.ctzjxh = ctzjxh;
    }
    public String getJklx() {
        return this.jklx;
    }
    public void setJklx(String jklx) {
        this.jklx = jklx;
    }
    public String getJkyt() {
        return this.jkyt;
    }
    public void setJkyt(String jkyt) {
        this.jkyt = jkyt;
    }
    public String getJtzjqfrq() {
        return this.jtzjqfrq;
    }
    public void setJtzjqfrq(String jtzjqfrq) {
        this.jtzjqfrq = jtzjqfrq;
    }
    public String getGhsj() {
        return this.ghsj;
    }
    public void setGhsj(String ghsj) {
        this.ghsj = ghsj;
    }
    public String getGhyy() {
        return this.ghyy;
    }
    public void setGhyy(String ghyy) {
        this.ghyy = ghyy;
    }
    public long getLsctid() {
        return this.lsctid;
    }
    public void setLsctid(long lsctid) {
        this.lsctid = lsctid;
    }
    public String getSjbs() {
        return this.sjbs;
    }
    public void setSjbs(String sjbs) {
        this.sjbs = sjbs;
    }
    public String getSh() {
        return this.sh;
    }
    public void setSh(String sh) {
        this.sh = sh;
    }
    public String getSbdw() {
        return this.sbdw;
    }
    public void setSbdw(String sbdw) {
        this.sbdw = sbdw;
    }
    public String getSb() {
        return this.sb;
    }
    public void setSb(String sb) {
        this.sb = sb;
    }
    public String getSb_ls_id() {
        return this.sb_ls_id;
    }
    public void setSb_ls_id(String sb_ls_id) {
        this.sb_ls_id = sb_ls_id;
    }
    public String getSfxtlr() {
        return this.sfxtlr;
    }
    public void setSfxtlr(String sfxtlr) {
        this.sfxtlr = sfxtlr;
    }
    public String getEwmxx() {
        return this.ewmxx;
    }
    public void setEwmxx(String ewmxx) {
        this.ewmxx = ewmxx;
    }
    public String getED_TAG() {
        return this.ED_TAG;
    }
    public void setED_TAG(String ED_TAG) {
        this.ED_TAG = ED_TAG;
    }
    public String getDzpxx() {
        return this.dzpxx;
    }
    public void setDzpxx(String dzpxx) {
        this.dzpxx = dzpxx;
    }

}
