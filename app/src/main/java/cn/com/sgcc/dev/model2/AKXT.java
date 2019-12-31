package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "AKXT", createInDb = false)
public class AKXT {
    // @ExcelField(name = "标识", next = "akxtm")
    // @ZdhzColumn(columnName = "ID")
    @Id
    @Property(nameInDb = "ID")
    private Long id;     //标识
    // @ExcelField(name = "安控系统名", next = "ssddm")
    // @ZdhzColumn(columnName = "AKXTM")
    @Property(nameInDb = "AKXTM")
    private String akxtm;       // 安控系统名
    // @ExcelField(name = "所属调度名", next = "akxtdydj")
    // @ZdhzColumn(columnName = "SSDDM")
    @Property(nameInDb = "SSDDM")
    private String ssddm;       // 所属调度名
    // @ExcelField(name = "安控系统电压等级", next = "czr")
    // @ZdhzColumn(columnName = "AKXTDYDJ")
    @Property(nameInDb = "AKXTDYDJ")
    private long akxtdydj;    // 安控系统电压等级
    // @ExcelField(name = "操作人", must = false, next = "")
    // @ZdhzColumn(columnName = "CZR")
    @Property(nameInDb = "CZR")
    private String czr;         // 操作人
    // @ExcelField(name = "状态", next = "")
//    private String bj;          // 导入状态
    @Property(nameInDb = "SB")
    private String sb;
    @Property(nameInDb = "SBSJ")
    private String sbsj;
    @Property(nameInDb = "SFBDSJ")
    private String sfbdsj;
    @Property(nameInDb = "SB_LS_ID")
    private String sb_ls_id;
    @Property(nameInDb = "SBDW")
    private String sbdw;
    @Property(nameInDb = "HZSJ")
    private String hzsj;
    @Property(nameInDb = "SBCZLX")
    private String sbczlx;
    @Property(nameInDb = "WJ_DW")
    private String wj_dw;
    @Property(nameInDb = "WJ_LS_ID")
    private String wj_ls_id;
    @Property(nameInDb = "SFXTLR")
    private String sfxtlr;
//    //保护配置ID
//    @Property(nameInDb = "BHPZID")
//    private String bhpzid;
//    //安控系统类型
//    @Property(nameInDb = "AKZDLX")
//    private String akzdlx;
    @Generated(hash = 1733042375)
    public AKXT(Long id, String akxtm, String ssddm, long akxtdydj, String czr,
            String sb, String sbsj, String sfbdsj, String sb_ls_id, String sbdw,
            String hzsj, String sbczlx, String wj_dw, String wj_ls_id,
            String sfxtlr) {
        this.id = id;
        this.akxtm = akxtm;
        this.ssddm = ssddm;
        this.akxtdydj = akxtdydj;
        this.czr = czr;
        this.sb = sb;
        this.sbsj = sbsj;
        this.sfbdsj = sfbdsj;
        this.sb_ls_id = sb_ls_id;
        this.sbdw = sbdw;
        this.hzsj = hzsj;
        this.sbczlx = sbczlx;
        this.wj_dw = wj_dw;
        this.wj_ls_id = wj_ls_id;
        this.sfxtlr = sfxtlr;
    }
    @Generated(hash = 1737552167)
    public AKXT() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAkxtm() {
        return this.akxtm;
    }
    public void setAkxtm(String akxtm) {
        this.akxtm = akxtm;
    }
    public String getSsddm() {
        return this.ssddm;
    }
    public void setSsddm(String ssddm) {
        this.ssddm = ssddm;
    }
    public long getAkxtdydj() {
        return this.akxtdydj;
    }
    public void setAkxtdydj(long akxtdydj) {
        this.akxtdydj = akxtdydj;
    }
    public String getCzr() {
        return this.czr;
    }
    public void setCzr(String czr) {
        this.czr = czr;
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
    public String getSfbdsj() {
        return this.sfbdsj;
    }
    public void setSfbdsj(String sfbdsj) {
        this.sfbdsj = sfbdsj;
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
    public String getHzsj() {
        return this.hzsj;
    }
    public void setHzsj(String hzsj) {
        this.hzsj = hzsj;
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