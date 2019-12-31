package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

@Entity(nameInDb = "BHXHRJBB", createInDb = false)
public class BHXHRJBB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Property(nameInDb = "ID")
    private Long id;      // ID
    @Property(nameInDb = "CODE")
    private String code;    // 版本编码
    @Property(nameInDb = "MKMC")
    private String mkmc;    // 模块名称
    @Property(nameInDb = "BB")
    private String bb;      // 版本
    @Property(nameInDb = "JYM")
    private String jym;     // 校验码
    @Property(nameInDb = "SFQY")
    private String sfqy;    // 是否启用
    @Property(nameInDb = "BHXHCODE")
    private String bhxhcode;// 保护设备型号编码
    @Property(nameInDb = "STATE")
    private String state;   // 状态
    @Property(nameInDb = "MS")
    private String ms;      // 描述
    @Property(nameInDb = "BBLX")
    private String bblx;    // 版本类型
    @Property(nameInDb = "TBDW")
    private String tbdw;    // 填报单位
    @Property(nameInDb = "TBR")
    private String tbr;     // 填报人
    @Property(nameInDb = "TBSJ")
    private String tbsj;    // 填报时间
    @Property(nameInDb = "BBXX")
    private String bbxx;     //
    @Property(nameInDb = "BBXX2")
    private String bbxx2;     //
    @Property(nameInDb = "CZ")
    private String cz;     //
    @Property(nameInDb = "FG")
    private String fg;     //
    @Property(nameInDb = "FNAME")
    private String fname;     //
    @Property(nameInDb = "LS_CODE")
    private String ls_code;     //
    @Property(nameInDb = "LS_RJBBXX")
    private String ls_rjbbxx;     //
    @Property(nameInDb = "RJBBXX")
    private String rjbbxx;     //
    @Property(nameInDb = "XX")
    private String xx;     //
    @Property(nameInDb = "ED_TAG")
    private String ED_TAG; //
    @Transient
    private String SCSJ; //

    @Generated(hash = 1436088982)
    public BHXHRJBB(Long id, String code, String mkmc, String bb, String jym,
            String sfqy, String bhxhcode, String state, String ms, String bblx,
            String tbdw, String tbr, String tbsj, String bbxx, String bbxx2,
            String cz, String fg, String fname, String ls_code, String ls_rjbbxx,
            String rjbbxx, String xx, String ED_TAG) {
        this.id = id;
        this.code = code;
        this.mkmc = mkmc;
        this.bb = bb;
        this.jym = jym;
        this.sfqy = sfqy;
        this.bhxhcode = bhxhcode;
        this.state = state;
        this.ms = ms;
        this.bblx = bblx;
        this.tbdw = tbdw;
        this.tbr = tbr;
        this.tbsj = tbsj;
        this.bbxx = bbxx;
        this.bbxx2 = bbxx2;
        this.cz = cz;
        this.fg = fg;
        this.fname = fname;
        this.ls_code = ls_code;
        this.ls_rjbbxx = ls_rjbbxx;
        this.rjbbxx = rjbbxx;
        this.xx = xx;
        this.ED_TAG = ED_TAG;
    }
    @Generated(hash = 1291196075)
    public BHXHRJBB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMkmc() {
        return this.mkmc;
    }
    public void setMkmc(String mkmc) {
        this.mkmc = mkmc;
    }
    public String getBb() {
        return this.bb;
    }
    public void setBb(String bb) {
        this.bb = bb;
    }
    public String getJym() {
        return this.jym;
    }
    public void setJym(String jym) {
        this.jym = jym;
    }
    public String getSfqy() {
        return this.sfqy;
    }
    public void setSfqy(String sfqy) {
        this.sfqy = sfqy;
    }
    public String getBhxhcode() {
        return this.bhxhcode;
    }
    public void setBhxhcode(String bhxhcode) {
        this.bhxhcode = bhxhcode;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getMs() {
        return this.ms;
    }
    public void setMs(String ms) {
        this.ms = ms;
    }
    public String getBblx() {
        return this.bblx;
    }
    public void setBblx(String bblx) {
        this.bblx = bblx;
    }
    public String getTbdw() {
        return this.tbdw;
    }
    public void setTbdw(String tbdw) {
        this.tbdw = tbdw;
    }
    public String getTbr() {
        return this.tbr;
    }
    public void setTbr(String tbr) {
        this.tbr = tbr;
    }
    public String getTbsj() {
        return this.tbsj;
    }
    public void setTbsj(String tbsj) {
        this.tbsj = tbsj;
    }
    public String getBbxx() {
        return this.bbxx;
    }
    public void setBbxx(String bbxx) {
        this.bbxx = bbxx;
    }
    public String getBbxx2() {
        return this.bbxx2;
    }
    public void setBbxx2(String bbxx2) {
        this.bbxx2 = bbxx2;
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
    public String getLs_code() {
        return this.ls_code;
    }
    public void setLs_code(String ls_code) {
        this.ls_code = ls_code;
    }
    public String getLs_rjbbxx() {
        return this.ls_rjbbxx;
    }
    public void setLs_rjbbxx(String ls_rjbbxx) {
        this.ls_rjbbxx = ls_rjbbxx;
    }
    public String getRjbbxx() {
        return this.rjbbxx;
    }
    public void setRjbbxx(String rjbbxx) {
        this.rjbbxx = rjbbxx;
    }
    public String getXx() {
        return this.xx;
    }
    public void setXx(String xx) {
        this.xx = xx;
    }
    public String getED_TAG() {
        return this.ED_TAG;
    }
    public void setED_TAG(String ED_TAG) {
        this.ED_TAG = ED_TAG;
    }

    public String getSCSJ() {
        return SCSJ;
    }

    public void setSCSJ(String SCSJ) {
        this.SCSJ = SCSJ;
    }
}
