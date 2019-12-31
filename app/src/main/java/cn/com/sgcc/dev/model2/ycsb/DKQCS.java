package cn.com.sgcc.dev.model2.ycsb;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 类说明 声明电抗器参数所用到的变量
 */

@Entity(nameInDb = "DKQCS", createInDb = false)
public class DKQCS {
    @Property(nameInDb = "VF")
    private int vf;      // 原始数据导入标识

    // @ZdhzColumn(columnName = "ID")
    // @ExcelField(name = "标识", next = "bh")
    @Id
    @Property(nameInDb = "ID")
    private Long id;

    // @ZdhzColumn(columnName = "BH")
    // @ExcelField(name = "编号", next = "gldw")
    @Property(nameInDb = "BH")
    private String bh;

    // @ZdhzColumn(columnName = "TYPE")
    // @ExcelField(name = "电抗器类型", next = "dkqmc")
    @Property(nameInDb = "TYPE")
    private String dkqlx;

    // @ZdhzColumn(columnName = "DKQMC")
    // @ExcelField(name = "电抗器名称", next = "dydj")
    @Property(nameInDb = "DKQMC")
    private String dkqmc;

    // @ZdhzColumn(columnName = "CZMC")
    // @ExcelField(name = "厂站名称", next = "dkqlx")
    @Property(nameInDb = "CZMC")
    private String czmc;

    // @ZdhzColumn(columnName = "BZ")
    // @ExcelField(name = "备注", next = "sh")
    @Property(nameInDb = "BZ")
    private String bz;      // 备注
    // @ZdhzColumn(columnName = "SH")
    // @ExcelField(name = "审核", next = "shr2")
    @Property(nameInDb = "SH")
    private String sh;
    // @ZdhzColumn(columnName = "SHR")
    // @ExcelField(name = "审核人", next = "shyy")
    @Property(nameInDb = "SHR")
    private String shr2;
    // @ZdhzColumn(columnName = "SHYY")
    // @ExcelField(name = "审核原因", next = "czr")
    @Property(nameInDb = "SHYY")
    private String shyy;
    // @ZdhzColumn(columnName = "DYDJ")
    // @ExcelField(name = "电压等级", next = "KGBH")
    @Property(nameInDb = "DYDJ")
    private int dydj;
    // @ZdhzColumn(columnName = "KGBH")
    // @ExcelField(name = "开关编号", next = "sftcyx")
    @Property(nameInDb = "KGBH")
    private String KGBH;    // 开关编号

    // @ZdhzColumn(columnName = "SFTCYX")
    // @ExcelField(name = "是否退出运行", next = "bz")
    @Property(nameInDb = "SFTCYX")
    private String sftcyx;

    // @ZdhzColumn(columnName = "GLDW")
    // @ExcelField(name = "厂站管理单位", next = "dddw")
    @Property(nameInDb = "GLDW")
    private String gldw;
    @Property(nameInDb = "WDID")
    private int WDID;
    // @ZdhzColumn(columnName = "CZR")
    // @ExcelField(name = "操作人", next = "")
    @Property(nameInDb = "CZR")
    private String czr;
    @Property(nameInDb = "TJR")
    private String tjr;
    @Property(nameInDb = "DWTJR")
    private String dwtjr;

    // @ZdhzColumn(columnName = "DDDW")
    // @ExcelField(name = "调度单位", next = "czmc")
    @Property(nameInDb = "DDDW")
    private String dddw;
    @Property(nameInDb = "JGLX")
    private String jglx;
    @Property(nameInDb = "JGMC")
    private String jgmc;

    @Property(nameInDb = "CZSJ")
    private Date CZSJ;
    @Property(nameInDb = "SFBDSJ")
    private String SFBDSJ;
    @Property(nameInDb = "SB")
    private String SB;
    @Property(nameInDb = "SBSJ")
    private Date SBSJ;
    @Property(nameInDb = "SB_LS_ID")
    private String SB_LS_ID;
    @Property(nameInDb = "SBDW")
    private String SBDW;
    @Property(nameInDb = "HZSJ")
    private Date HZSJ;
    @Property(nameInDb = "SBCZLX")
    private String SBCZLX;
    @Property(nameInDb = "SFXTLR")
    private String SFXTLR;
    @Property(nameInDb = "WJ_DW")
    private String WJ_DW;
    @Property(nameInDb = "WJ_LS_ID")
    private String WJ_LS_ID;
    @Generated(hash = 1819535986)
    public DKQCS(int vf, Long id, String bh, String dkqlx, String dkqmc,
            String czmc, String bz, String sh, String shr2, String shyy, int dydj,
            String KGBH, String sftcyx, String gldw, int WDID, String czr,
            String tjr, String dwtjr, String dddw, String jglx, String jgmc,
            Date CZSJ, String SFBDSJ, String SB, Date SBSJ, String SB_LS_ID,
            String SBDW, Date HZSJ, String SBCZLX, String SFXTLR, String WJ_DW,
            String WJ_LS_ID) {
        this.vf = vf;
        this.id = id;
        this.bh = bh;
        this.dkqlx = dkqlx;
        this.dkqmc = dkqmc;
        this.czmc = czmc;
        this.bz = bz;
        this.sh = sh;
        this.shr2 = shr2;
        this.shyy = shyy;
        this.dydj = dydj;
        this.KGBH = KGBH;
        this.sftcyx = sftcyx;
        this.gldw = gldw;
        this.WDID = WDID;
        this.czr = czr;
        this.tjr = tjr;
        this.dwtjr = dwtjr;
        this.dddw = dddw;
        this.jglx = jglx;
        this.jgmc = jgmc;
        this.CZSJ = CZSJ;
        this.SFBDSJ = SFBDSJ;
        this.SB = SB;
        this.SBSJ = SBSJ;
        this.SB_LS_ID = SB_LS_ID;
        this.SBDW = SBDW;
        this.HZSJ = HZSJ;
        this.SBCZLX = SBCZLX;
        this.SFXTLR = SFXTLR;
        this.WJ_DW = WJ_DW;
        this.WJ_LS_ID = WJ_LS_ID;
    }
    @Generated(hash = 951493607)
    public DKQCS() {
    }
    public int getVf() {
        return this.vf;
    }
    public void setVf(int vf) {
        this.vf = vf;
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
    public String getDkqlx() {
        return this.dkqlx;
    }
    public void setDkqlx(String dkqlx) {
        this.dkqlx = dkqlx;
    }
    public String getDkqmc() {
        return this.dkqmc;
    }
    public void setDkqmc(String dkqmc) {
        this.dkqmc = dkqmc;
    }
    public String getCzmc() {
        return this.czmc;
    }
    public void setCzmc(String czmc) {
        this.czmc = czmc;
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
    public String getShr2() {
        return this.shr2;
    }
    public void setShr2(String shr2) {
        this.shr2 = shr2;
    }
    public String getShyy() {
        return this.shyy;
    }
    public void setShyy(String shyy) {
        this.shyy = shyy;
    }
    public int getDydj() {
        return this.dydj;
    }
    public void setDydj(int dydj) {
        this.dydj = dydj;
    }
    public String getKGBH() {
        return this.KGBH;
    }
    public void setKGBH(String KGBH) {
        this.KGBH = KGBH;
    }
    public String getSftcyx() {
        return this.sftcyx;
    }
    public void setSftcyx(String sftcyx) {
        this.sftcyx = sftcyx;
    }
    public String getGldw() {
        return this.gldw;
    }
    public void setGldw(String gldw) {
        this.gldw = gldw;
    }
    public int getWDID() {
        return this.WDID;
    }
    public void setWDID(int WDID) {
        this.WDID = WDID;
    }
    public String getCzr() {
        return this.czr;
    }
    public void setCzr(String czr) {
        this.czr = czr;
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
    public String getDddw() {
        return this.dddw;
    }
    public void setDddw(String dddw) {
        this.dddw = dddw;
    }
    public String getJglx() {
        return this.jglx;
    }
    public void setJglx(String jglx) {
        this.jglx = jglx;
    }
    public String getJgmc() {
        return this.jgmc;
    }
    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }
    public Date getCZSJ() {
        return this.CZSJ;
    }
    public void setCZSJ(Date CZSJ) {
        this.CZSJ = CZSJ;
    }
    public String getSFBDSJ() {
        return this.SFBDSJ;
    }
    public void setSFBDSJ(String SFBDSJ) {
        this.SFBDSJ = SFBDSJ;
    }
    public String getSB() {
        return this.SB;
    }
    public void setSB(String SB) {
        this.SB = SB;
    }
    public Date getSBSJ() {
        return this.SBSJ;
    }
    public void setSBSJ(Date SBSJ) {
        this.SBSJ = SBSJ;
    }
    public String getSB_LS_ID() {
        return this.SB_LS_ID;
    }
    public void setSB_LS_ID(String SB_LS_ID) {
        this.SB_LS_ID = SB_LS_ID;
    }
    public String getSBDW() {
        return this.SBDW;
    }
    public void setSBDW(String SBDW) {
        this.SBDW = SBDW;
    }
    public Date getHZSJ() {
        return this.HZSJ;
    }
    public void setHZSJ(Date HZSJ) {
        this.HZSJ = HZSJ;
    }
    public String getSBCZLX() {
        return this.SBCZLX;
    }
    public void setSBCZLX(String SBCZLX) {
        this.SBCZLX = SBCZLX;
    }
    public String getSFXTLR() {
        return this.SFXTLR;
    }
    public void setSFXTLR(String SFXTLR) {
        this.SFXTLR = SFXTLR;
    }
    public String getWJ_DW() {
        return this.WJ_DW;
    }
    public void setWJ_DW(String WJ_DW) {
        this.WJ_DW = WJ_DW;
    }
    public String getWJ_LS_ID() {
        return this.WJ_LS_ID;
    }
    public void setWJ_LS_ID(String WJ_LS_ID) {
        this.WJ_LS_ID = WJ_LS_ID;
    }

}
