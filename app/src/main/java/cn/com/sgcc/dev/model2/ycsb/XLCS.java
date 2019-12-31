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
 * 线路参数信息封装类
 */
@Entity(nameInDb = "XLCS", createInDb = false)
public class XLCS {
    // @ZdhzColumn(columnName = "XLBH")
    // @ExcelField(name = "线路编号", next = "")
    @Property(nameInDb = "XLBH")
    private Long xlbh;
    // @ZdhzColumn(columnName = "ID")
    // @ExcelField(name = "标识", next = "BH")
    @Id
    @Property(nameInDb = "ID")
    private int id;      // 线路ID
    // @ZdhzColumn(columnName = "BH")
    // @ExcelField(name = "编号", next = "GLDW")
    @Property(nameInDb = "BH")
    private String BH;      // 编号
    @Property(nameInDb = "VF")
    private int vf;      // 原始数据导入标识

    // @ZdhzColumn(columnName = "XLMC")
    // @ExcelField(name = "线路名称", next = "XLCD")
    @Property(nameInDb = "XLMC")
    private String XLMC;    // 线路名称
    // @ZdhzColumn(columnName = "XLCD")
    // @ExcelField(name = "线路长度", next = "KDQLLX")
    @Property(nameInDb = "XLCD")
    private double XLCD;    // 线路长度

    // @ZdhzColumn(columnName = "DYDJ")
    // @ExcelField(name = "电压等级", next = "KGBH1")
    @Property(nameInDb = "DYDJ")
    private int DYDJ;    // 电压等级

    // @ZdhzColumn(columnName = "KGBH1")
    // @ExcelField(name = "开关编号一", next = "KGBH2")
    @Property(nameInDb = "KGBH1")
    private String KGBH1;   // 开关编号
    // @ZdhzColumn(columnName = "KGBH2")
    // @ExcelField(name = "开关编号二", next = "KGBH3")
    @Property(nameInDb = "KGBH2")
    private String KGBH2;   // 开关编号
    // @ZdhzColumn(columnName = "KGBH3")
    // @ExcelField(name = "开关编号三", next = "SFTCYX")
    @Property(nameInDb = "KGBH3")
    private String KGBH3;   // 开关编号

    // @ZdhzColumn(columnName = "CZ1")
    // @ExcelField(name = "厂站一", next = "CZ2")
    @Property(nameInDb = "CZ1")
    private String CZ1;     // 厂站名称
    // @ZdhzColumn(columnName = "CZ2")
    // @ExcelField(name = "CZ2")", next = "CZ3")
    @Property(nameInDb = "XLBH")
    private String CZ2;     // 厂站名称
    // @ZdhzColumn(columnName = "CZ3")
    // @ExcelField(name = "厂站三", next = "XLMC")
    @Property(nameInDb = "CZ3")
    private String CZ3;     // 厂站名称
    @Property(nameInDb = "JGLX1")
    private String JGLX1;   // 间隔类型
    @Property(nameInDb = "JGLX2")
    private String JGLX2;   // 间隔类型
    @Property(nameInDb = "JGLX3")
    private String JGLX3;   // 间隔类型
    @Property(nameInDb = "JGMC1")
    private String JGMC1;   // 间隔类型
    @Property(nameInDb = "JGMC2")
    private String JGMC2;   // 间隔类型
    @Property(nameInDb = "JGMC3")
    private String JGMC3;   // 间隔类型

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
    // @ExcelField(name = "厂站管理单位", next = "GLDW2")
    @Property(nameInDb = "GLDW")
    private String GLDW;    // 管理单位
    // @ZdhzColumn(columnName = "GLDW2")
    // @ExcelField(name = "厂站管理单位二", next = "GLDW3")
    @Property(nameInDb = "GLDW2")
    private String GLDW2;   // 管理单位
    // @ZdhzColumn(columnName = "GLDW3")
    // @ExcelField(name = "厂站管理单位三", next = "DDDW")
    @Property(nameInDb = "GLDW3")
    private String GLDW3;   // 管理单位

    // @ZdhzColumn(columnName = "KDQLLX")
    // @ExcelField(name = "跨地区联络线", next = "DTXL")
    @Property(nameInDb = "KDQLLX")
    private String KDQLLX;  // 是否为跨地区联络线

    // @ZdhzColumn(columnName = "DTXL")
    // @ExcelField(name = "电铁线", next = "ZDX")
    @Property(nameInDb = "DTXL")
    private String DTXL;

    // @ZdhzColumn(columnName = "ZDX")
    // @ExcelField(name = "终端线", next = "DYDJ")
    @Property(nameInDb = "ZDX")
    private String ZDX;
    @Property(nameInDb = "WDID")
    private int WDID;

    // @ZdhzColumn(columnName = "DDDW")
    // @ExcelField(name = "调度单位", next = "CZ1")
    @Property(nameInDb = "DDDW")
    private String DDDW;
    // @ZdhzColumn(columnName = "CZR")
    // @ExcelField(name = "操作人", next = "xlbh")
    @Property(nameInDb = "CZR")
    private String czr;
    @Property(nameInDb = "TJR")
    private String tjr;
    @Property(nameInDb = "DWTJR")
    private String dwtjr;

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
    @Generated(hash = 154097242)
    public XLCS(Long xlbh, int id, String BH, int vf, String XLMC, double XLCD,
            int DYDJ, String KGBH1, String KGBH2, String KGBH3, String CZ1,
            String CZ2, String CZ3, String JGLX1, String JGLX2, String JGLX3,
            String JGMC1, String JGMC2, String JGMC3, String SFTCYX, String BZ,
            String sh, String shr2, String shyy, String GLDW, String GLDW2,
            String GLDW3, String KDQLLX, String DTXL, String ZDX, int WDID,
            String DDDW, String czr, String tjr, String dwtjr, Date CZSJ,
            String SFBDSJ, String SB, Date SBSJ, String SB_LS_ID, String SBDW,
            Date HZSJ, String SBCZLX, String SFXTLR, String WJ_DW,
            String WJ_LS_ID) {
        this.xlbh = xlbh;
        this.id = id;
        this.BH = BH;
        this.vf = vf;
        this.XLMC = XLMC;
        this.XLCD = XLCD;
        this.DYDJ = DYDJ;
        this.KGBH1 = KGBH1;
        this.KGBH2 = KGBH2;
        this.KGBH3 = KGBH3;
        this.CZ1 = CZ1;
        this.CZ2 = CZ2;
        this.CZ3 = CZ3;
        this.JGLX1 = JGLX1;
        this.JGLX2 = JGLX2;
        this.JGLX3 = JGLX3;
        this.JGMC1 = JGMC1;
        this.JGMC2 = JGMC2;
        this.JGMC3 = JGMC3;
        this.SFTCYX = SFTCYX;
        this.BZ = BZ;
        this.sh = sh;
        this.shr2 = shr2;
        this.shyy = shyy;
        this.GLDW = GLDW;
        this.GLDW2 = GLDW2;
        this.GLDW3 = GLDW3;
        this.KDQLLX = KDQLLX;
        this.DTXL = DTXL;
        this.ZDX = ZDX;
        this.WDID = WDID;
        this.DDDW = DDDW;
        this.czr = czr;
        this.tjr = tjr;
        this.dwtjr = dwtjr;
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
    @Generated(hash = 711081209)
    public XLCS() {
    }
    public Long getXlbh() {
        return this.xlbh;
    }
    public void setXlbh(Long xlbh) {
        this.xlbh = xlbh;
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
    public int getVf() {
        return this.vf;
    }
    public void setVf(int vf) {
        this.vf = vf;
    }
    public String getXLMC() {
        return this.XLMC;
    }
    public void setXLMC(String XLMC) {
        this.XLMC = XLMC;
    }
    public double getXLCD() {
        return this.XLCD;
    }
    public void setXLCD(double XLCD) {
        this.XLCD = XLCD;
    }
    public int getDYDJ() {
        return this.DYDJ;
    }
    public void setDYDJ(int DYDJ) {
        this.DYDJ = DYDJ;
    }
    public String getKGBH1() {
        return this.KGBH1;
    }
    public void setKGBH1(String KGBH1) {
        this.KGBH1 = KGBH1;
    }
    public String getKGBH2() {
        return this.KGBH2;
    }
    public void setKGBH2(String KGBH2) {
        this.KGBH2 = KGBH2;
    }
    public String getKGBH3() {
        return this.KGBH3;
    }
    public void setKGBH3(String KGBH3) {
        this.KGBH3 = KGBH3;
    }
    public String getCZ1() {
        return this.CZ1;
    }
    public void setCZ1(String CZ1) {
        this.CZ1 = CZ1;
    }
    public String getCZ2() {
        return this.CZ2;
    }
    public void setCZ2(String CZ2) {
        this.CZ2 = CZ2;
    }
    public String getCZ3() {
        return this.CZ3;
    }
    public void setCZ3(String CZ3) {
        this.CZ3 = CZ3;
    }
    public String getJGLX1() {
        return this.JGLX1;
    }
    public void setJGLX1(String JGLX1) {
        this.JGLX1 = JGLX1;
    }
    public String getJGLX2() {
        return this.JGLX2;
    }
    public void setJGLX2(String JGLX2) {
        this.JGLX2 = JGLX2;
    }
    public String getJGLX3() {
        return this.JGLX3;
    }
    public void setJGLX3(String JGLX3) {
        this.JGLX3 = JGLX3;
    }
    public String getJGMC1() {
        return this.JGMC1;
    }
    public void setJGMC1(String JGMC1) {
        this.JGMC1 = JGMC1;
    }
    public String getJGMC2() {
        return this.JGMC2;
    }
    public void setJGMC2(String JGMC2) {
        this.JGMC2 = JGMC2;
    }
    public String getJGMC3() {
        return this.JGMC3;
    }
    public void setJGMC3(String JGMC3) {
        this.JGMC3 = JGMC3;
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
    public String getGLDW2() {
        return this.GLDW2;
    }
    public void setGLDW2(String GLDW2) {
        this.GLDW2 = GLDW2;
    }
    public String getGLDW3() {
        return this.GLDW3;
    }
    public void setGLDW3(String GLDW3) {
        this.GLDW3 = GLDW3;
    }
    public String getKDQLLX() {
        return this.KDQLLX;
    }
    public void setKDQLLX(String KDQLLX) {
        this.KDQLLX = KDQLLX;
    }
    public String getDTXL() {
        return this.DTXL;
    }
    public void setDTXL(String DTXL) {
        this.DTXL = DTXL;
    }
    public String getZDX() {
        return this.ZDX;
    }
    public void setZDX(String ZDX) {
        this.ZDX = ZDX;
    }
    public int getWDID() {
        return this.WDID;
    }
    public void setWDID(int WDID) {
        this.WDID = WDID;
    }
    public String getDDDW() {
        return this.DDDW;
    }
    public void setDDDW(String DDDW) {
        this.DDDW = DDDW;
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
