package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "CCXXBK", createInDb = false)
public class CCXXBK {
    // @ExcelField(name = "ID", next = "xh")
    // @ZdhzColumn(columnName = "ID")
    @Id
    @Property(nameInDb = "ID")
    private Long id;
    // @ZdhzColumn(columnName = "XH")
    // @ExcelField(name = "板卡序号", next = "sfsbm")
    @Property(nameInDb = "XH")
    private long xh;
    // @ZdhzColumn(columnName = "SFSBM")
    // @ExcelField(name = "身份识别码", next = "zzcj")
    @Property(nameInDb = "SFSBM")
    private String sfsbm;  // 身份识别码
    // @ZdhzColumn(columnName = "ZZCJ")
    // @ExcelField(name = "制造厂家", next = "bkxh")
    @Property(nameInDb = "ZZCJ")
    private String zzcj;   // 制造厂家
    // @ZdhzColumn(columnName = "BKXH")
    // @ExcelField(name = "板卡型号", next = "bklb")
    @Property(nameInDb = "BKXH")
    private String bkxh;   // 板卡型号
    // @ZdhzColumn(columnName = "BKLB")
    // @ExcelField(name = "板卡类别/用途", next = "rjbb")
    @Property(nameInDb = "BKLB")
    private String bklb;   // 板卡类别/用途
    // @ZdhzColumn(columnName = "RJBB")
    // @ExcelField(name = "版本软件版本", next = "bkbh")
    @Property(nameInDb = "RJBB")
    private String rjbb;   // 版本软件版本
    // @ZdhzColumn(columnName = "BKBH")
    // @ExcelField(name = "板卡编号", next = "bkscrq")
    @Property(nameInDb = "BKBH")
    private String bkbh;   // 板卡编号
    // @ZdhzColumn(columnName = "BKSCRQ")
    // @ExcelField(name = "板卡生产日期", next = "")
    @Property(nameInDb = "BKSCRQ")
    private String bkscrq; // 板卡生产日期
    @Generated(hash = 585236078)
    public CCXXBK(Long id, long xh, String sfsbm, String zzcj, String bkxh,
            String bklb, String rjbb, String bkbh, String bkscrq) {
        this.id = id;
        this.xh = xh;
        this.sfsbm = sfsbm;
        this.zzcj = zzcj;
        this.bkxh = bkxh;
        this.bklb = bklb;
        this.rjbb = rjbb;
        this.bkbh = bkbh;
        this.bkscrq = bkscrq;
    }
    @Generated(hash = 318972541)
    public CCXXBK() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getXh() {
        return this.xh;
    }
    public void setXh(long xh) {
        this.xh = xh;
    }
    public String getSfsbm() {
        return this.sfsbm;
    }
    public void setSfsbm(String sfsbm) {
        this.sfsbm = sfsbm;
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
}
