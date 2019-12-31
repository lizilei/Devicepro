package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 板卡信息实体类
 */
@Entity(nameInDb = "BKXX", createInDb = false)
public class BKXX implements Serializable{
    private static final long serialVersionUID = 1L;
    // @ExcelField(name = "标识", next = "zzcj")
    // @ZdhzColumn(columnName = "ID")
    @Id
    @Property(nameInDb = "ID")
    private Long id;
    // @ExcelField(name = "制造厂家", next = "bkxh")
    // @ZdhzColumn(columnName = "ZZCJ")
    @Property(nameInDb = "ZZCJ")
    private String zzcj;
    // @ExcelField(name = "板卡型号", next = "bklb")
    // @ZdhzColumn(columnName = "BKXH")
    @Property(nameInDb = "BKXH")
    private String bkxh;
    // @ExcelField(name = "板卡类别", next = "rjbb")
    // @ZdhzColumn(columnName = "BKLB")
    @Property(nameInDb = "BKLB")
    private String bklb;
    // @ExcelField(name = "板卡软件版本", next = "bkbh")
    // @ZdhzColumn(columnName = "RJBB")
    @Property(nameInDb = "RJBB")
    private String rjbb;
    // @ExcelField(name = "板卡编号", next = "bkscrq")
    // @ZdhzColumn(columnName = "BKBH")
    @Property(nameInDb = "BKBH")
    private String bkbh;
    // @ExcelField(name = "板卡生成日期", next = "bkbgyy")
    // @ZdhzColumn(columnName = "BKSCRQ")
    @Property(nameInDb = "BKSCRQ")
    private String bkscrq;
    // @ExcelField(name = "板卡变更原因", next = "bgsj")
    // @ZdhzColumn(columnName = "BKBGYY")
    @Property(nameInDb = "BKBGYY")
    private String bkbgyy;
    // @ExcelField(name = "变更时间", next = "zsjid")
    // @ZdhzColumn(columnName = "BGSJ")
    @Property(nameInDb = "BGSJ")
    private String bgsj;
    // @ExcelField(name = "主数据标识", next = "zsjtype")
    // @ZdhzColumn(columnName = "ZSJID")
    @Property(nameInDb = "ZSJID")
    private long zsjid;
    // @ExcelField(name = "主数据类型", next = "lsbkbh")
    // @ZdhzColumn(columnName = "ZSJTYPE")
    @Property(nameInDb = "ZSJTYPE")
    private String zsjtype;
    // @ExcelField(name = "历史板卡编号", next = "ls_zsj_id")
    // @ZdhzColumn(columnName = "LSBKBH")
    @Property(nameInDb = "LSBKBH")
    private String lsbkbh;
    // @ExcelField(name = "历史主数据标识", next = "")
    // @ZdhzColumn(columnName = "LS_ZSJ_ID")
    @Property(nameInDb = "LS_ZSJ_ID")
    private String lsbkbhstr;
    @Property(nameInDb = "SB_LS_ID")
    private String sb_ls_id;
    @Property(nameInDb = "SB")
    private String sb;
    @Property(nameInDb = "SBSJ")
    private String sbsj;
    @Property(nameInDb = "HZSJ")
    private String hzsj;
    @Property(nameInDb = "SBDW")
    private String sbdw;
    @Property(nameInDb = "SBCZLX")
    private String sbczlx;
    @Property(nameInDb = "WJ_DW")
    private String wj_dw;
    @Property(nameInDb = "WJ_LS_ID")
    private String wj_ls_id;
    @Property(nameInDb = "SFXTLR")
    private String sfxtlr;
    @Generated(hash = 1965809941)
    public BKXX(Long id, String zzcj, String bkxh, String bklb, String rjbb,
            String bkbh, String bkscrq, String bkbgyy, String bgsj, long zsjid,
            String zsjtype, String lsbkbh, String lsbkbhstr, String sb_ls_id,
            String sb, String sbsj, String hzsj, String sbdw, String sbczlx,
            String wj_dw, String wj_ls_id, String sfxtlr) {
        this.id = id;
        this.zzcj = zzcj;
        this.bkxh = bkxh;
        this.bklb = bklb;
        this.rjbb = rjbb;
        this.bkbh = bkbh;
        this.bkscrq = bkscrq;
        this.bkbgyy = bkbgyy;
        this.bgsj = bgsj;
        this.zsjid = zsjid;
        this.zsjtype = zsjtype;
        this.lsbkbh = lsbkbh;
        this.lsbkbhstr = lsbkbhstr;
        this.sb_ls_id = sb_ls_id;
        this.sb = sb;
        this.sbsj = sbsj;
        this.hzsj = hzsj;
        this.sbdw = sbdw;
        this.sbczlx = sbczlx;
        this.wj_dw = wj_dw;
        this.wj_ls_id = wj_ls_id;
        this.sfxtlr = sfxtlr;
    }
    @Generated(hash = 797900419)
    public BKXX() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getZzcj() {
        return this.zzcj;
    }
    public void setZzcj(String zzcj) {
        this.zzcj = zzcj;
    }
    public String getBkxh() {
        return this.bkxh;
    }
    public void setBkxh(String bkxh) {
        this.bkxh = bkxh;
    }
    public String getBklb() {
        return this.bklb;
    }
    public void setBklb(String bklb) {
        this.bklb = bklb;
    }
    public String getRjbb() {
        return this.rjbb;
    }
    public void setRjbb(String rjbb) {
        this.rjbb = rjbb;
    }
    public String getBkbh() {
        return this.bkbh;
    }
    public void setBkbh(String bkbh) {
        this.bkbh = bkbh;
    }
    public String getBkscrq() {
        return this.bkscrq;
    }
    public void setBkscrq(String bkscrq) {
        this.bkscrq = bkscrq;
    }
    public String getBkbgyy() {
        return this.bkbgyy;
    }
    public void setBkbgyy(String bkbgyy) {
        this.bkbgyy = bkbgyy;
    }
    public String getBgsj() {
        return this.bgsj;
    }
    public void setBgsj(String bgsj) {
        this.bgsj = bgsj;
    }
    public long getZsjid() {
        return this.zsjid;
    }
    public void setZsjid(long zsjid) {
        this.zsjid = zsjid;
    }
    public String getZsjtype() {
        return this.zsjtype;
    }
    public void setZsjtype(String zsjtype) {
        this.zsjtype = zsjtype;
    }
    public String getLsbkbh() {
        return this.lsbkbh;
    }
    public void setLsbkbh(String lsbkbh) {
        this.lsbkbh = lsbkbh;
    }
    public String getLsbkbhstr() {
        return this.lsbkbhstr;
    }
    public void setLsbkbhstr(String lsbkbhstr) {
        this.lsbkbhstr = lsbkbhstr;
    }
    public String getSb_ls_id() {
        return this.sb_ls_id;
    }
    public void setSb_ls_id(String sb_ls_id) {
        this.sb_ls_id = sb_ls_id;
    }
    public String getSb() {
        return this.sb;
    }
    public void setSb(String sb) {
        this.sb = sb;
    }
    public String getSbsj() {
        return this.sbsj;
    }
    public void setSbsj(String sbsj) {
        this.sbsj = sbsj;
    }
    public String getHzsj() {
        return this.hzsj;
    }
    public void setHzsj(String hzsj) {
        this.hzsj = hzsj;
    }
    public String getSbdw() {
        return this.sbdw;
    }
    public void setSbdw(String sbdw) {
        this.sbdw = sbdw;
    }
    public String getSbczlx() {
        return this.sbczlx;
    }
    public void setSbczlx(String sbczlx) {
        this.sbczlx = sbczlx;
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
    public String getSfxtlr() {
        return this.sfxtlr;
    }
    public void setSfxtlr(String sfxtlr) {
        this.sfxtlr = sfxtlr;
    }
}
