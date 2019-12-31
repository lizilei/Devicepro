package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "CCXXRJBB", createInDb = false)
public class CCXXRJBB {

    // @ExcelField(name = "ID", next = "sfsbm")
    // @ZdhzColumn(columnName = "ID")
    @Id
    @Property(nameInDb = "ID")
    private long id;
    // @ZdhzColumn(columnName = "SFSBM")
    // @ExcelField(name = "身份识别码", next = "mkmc")
    @Property(nameInDb = "SFSBM")
    private String sfsbm;// 身份识别码
    // @ZdhzColumn(columnName = "MKMC")
    // @ExcelField(name = "模块名称", next = "rjbb")
    @Property(nameInDb = "MKMC")
    private String mkmc; // 模块名称
    // @ZdhzColumn(columnName = "RJBB")
    // @ExcelField(name = "软件版本", next = "jym")
    @Property(nameInDb = "RJBB")
    private String rjbb; // 软件版本
    // @ZdhzColumn(columnName = "JYM")
    // @ExcelField(name = "校验码", next = "scrq")
    @Property(nameInDb = "JYM")
    private String jym;  // 校验码
    // @ZdhzColumn(columnName = "SCRQ")
    // @ExcelField(name = "生成日期", next = "code")
    @Property(nameInDb = "SCRQ")
    private String scrq; // 生成日期
    // @ZdhzColumn(columnName = "CODE")
    // @ExcelField(name = "版本编码", next = "")
    @Property(nameInDb = "CODE")
    private String code; // 版本编码
    @Generated(hash = 1830422133)
    public CCXXRJBB(long id, String sfsbm, String mkmc, String rjbb, String jym,
            String scrq, String code) {
        this.id = id;
        this.sfsbm = sfsbm;
        this.mkmc = mkmc;
        this.rjbb = rjbb;
        this.jym = jym;
        this.scrq = scrq;
        this.code = code;
    }
    @Generated(hash = 217873323)
    public CCXXRJBB() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getSfsbm() {
        return this.sfsbm;
    }
    public void setSfsbm(String sfsbm) {
        this.sfsbm = sfsbm;
    }
    public String getMkmc() {
        return this.mkmc;
    }
    public void setMkmc(String mkmc) {
        this.mkmc = mkmc;
    }
    public String getRjbb() {
        return this.rjbb;
    }
    public void setRjbb(String rjbb) {
        this.rjbb = rjbb;
    }
    public String getJym() {
        return this.jym;
    }
    public void setJym(String jym) {
        this.jym = jym;
    }
    public String getScrq() {
        return this.scrq;
    }
    public void setScrq(String scrq) {
        this.scrq = scrq;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
