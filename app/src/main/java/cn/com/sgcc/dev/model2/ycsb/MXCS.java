/**
 * Copyright (c) 2009, Chinsoft All Rights Reserved.
 */
package cn.com.sgcc.dev.model2.ycsb;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;


/**
 * 母线参数信息封装类
 *
 * @author 王刚
 * @version 1.0
 * @Date Oct 15, 2009 5:31:03 PM
 * @see
 */
@Entity(nameInDb = "MXCS", createInDb = false)
public class MXCS {

    // @ZdhzColumn(columnName = "ID")
    // @ExcelField(name = "标识", next = "BH")
    @Id
    @Property(nameInDb = "ID")
    private int id;      // 母线ID

    // @ZdhzColumn(columnName = "BH")
    // @ExcelField(name = "编号", next = "GLDW")
    @Property(nameInDb = "BH")
    private String BH;      // 编号

    // @ZdhzColumn(columnName = "MXMC")
    // @ExcelField(name = "母线名称", next = "DYDJ")
    @Property(nameInDb = "MXMC")
    private String MXMC;    // 母线名称

    // @ZdhzColumn(columnName = "CZMC")
    // @ExcelField(name = "厂站名称", next = "MXMC")
    @Property(nameInDb = "CZMC")
    private String CZMC;    // 厂站名称

    // @ZdhzColumn(columnName = "DYDJ")
    // @ExcelField(name = "电压等级", next = "KGBH")
    @Property(nameInDb = "DYDJ")
    private int DYDJ;    // 电压等级

    // @ZdhzColumn(columnName = "KGBH")
    // @ExcelField(name = "开关编号", next = "MXLJFS")
    @Property(nameInDb = "KGBH")
    private String KGBH;    // 开关编号

    // @ZdhzColumn(columnName = "MXLJFS")
    // @ExcelField(name = "母线连接方式", next = "MXTS")
    @Property(nameInDb = "MXLJFS")
    private String MXLJFS;  // 母线连接方式

    // @ZdhzColumn(columnName = "MXTS")
    // @ExcelField(name = "母线条数", next = "SFTCYX")
    @Property(nameInDb = "MXTS")
    private int MXTS;    // 母线条数

    // @ZdhzColumn(columnName = "SFTCYX")
    // @ExcelField(name = "是否退出运行", next = "BZ")
    @Property(nameInDb = "SFTCYX")
    private String SFTCYX;  // 是否退出运行
    // @ZdhzColumn(columnName = "BZ")
    // @ExcelField(name = "备注", next = "sh")
    @Property(nameInDb = "BZ")
    private String BZ;      // 备注
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
    // @ZdhzColumn(columnName = "GLDW")
    // @ExcelField(name = "厂站管理单位", next = "dddw")
    @Property(nameInDb = "GLDW")
    private String GLDW;    // 管理单位
    @Property(nameInDb = "VF")
    private int vf;
    @Property(nameInDb = "WDID")
    private int WDID;

    // @ZdhzColumn(columnName = "DDDW")
    // @ExcelField(name = "调度单位", next = "CZMC")
    @Property(nameInDb = "DDDW")
    private String dddw;
    // @ZdhzColumn(columnName = "CZR")
    // @ExcelField(name = "操作人", next = "")
    @Property(nameInDb = "CZR")
    private String czr;
    @Property(nameInDb = "TJR")
    private String tjr;
    @Property(nameInDb = "DWTJR")
    private String dwtjr;
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

    @Generated(hash = 695492547)
    public MXCS(int id, String BH, String MXMC, String CZMC, int DYDJ, String KGBH,
            String MXLJFS, int MXTS, String SFTCYX, String BZ, String sh, String shr2,
            String shyy, String GLDW, int vf, int WDID, String dddw, String czr, String tjr,
            String dwtjr, String jglx, String jgmc, Date CZSJ, String SFBDSJ, String SB,
            Date SBSJ, String SB_LS_ID, String SBDW, Date HZSJ, String SBCZLX, String SFXTLR,
            String WJ_DW, String WJ_LS_ID) {
        this.id = id;
        this.BH = BH;
        this.MXMC = MXMC;
        this.CZMC = CZMC;
        this.DYDJ = DYDJ;
        this.KGBH = KGBH;
        this.MXLJFS = MXLJFS;
        this.MXTS = MXTS;
        this.SFTCYX = SFTCYX;
        this.BZ = BZ;
        this.sh = sh;
        this.shr2 = shr2;
        this.shyy = shyy;
        this.GLDW = GLDW;
        this.vf = vf;
        this.WDID = WDID;
        this.dddw = dddw;
        this.czr = czr;
        this.tjr = tjr;
        this.dwtjr = dwtjr;
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

    @Generated(hash = 1627633266)
    public MXCS() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBH() {
        return this.BH;
    }

    public void setBH(String BH) {
        this.BH = BH;
    }

    public String getMXMC() {
        return this.MXMC;
    }

    public void setMXMC(String MXMC) {
        this.MXMC = MXMC;
    }

    public String getCZMC() {
        return this.CZMC;
    }

    public void setCZMC(String CZMC) {
        this.CZMC = CZMC;
    }

    public int getDYDJ() {
        return this.DYDJ;
    }

    public void setDYDJ(int DYDJ) {
        this.DYDJ = DYDJ;
    }

    public String getKGBH() {
        return this.KGBH;
    }

    public void setKGBH(String KGBH) {
        this.KGBH = KGBH;
    }

    public String getMXLJFS() {
        return this.MXLJFS;
    }

    public void setMXLJFS(String MXLJFS) {
        this.MXLJFS = MXLJFS;
    }

    public int getMXTS() {
        return this.MXTS;
    }

    public void setMXTS(int MXTS) {
        this.MXTS = MXTS;
    }

    public String getSFTCYX() {
        return this.SFTCYX;
    }

    public void setSFTCYX(String SFTCYX) {
        this.SFTCYX = SFTCYX;
    }

    public String getBZ() {
        return this.BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
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

    public String getGLDW() {
        return this.GLDW;
    }

    public void setGLDW(String GLDW) {
        this.GLDW = GLDW;
    }

    public int getVf() {
        return this.vf;
    }

    public void setVf(int vf) {
        this.vf = vf;
    }

    public int getWDID() {
        return this.WDID;
    }

    public void setWDID(int WDID) {
        this.WDID = WDID;
    }

    public String getDddw() {
        return this.dddw;
    }

    public void setDddw(String dddw) {
        this.dddw = dddw;
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
