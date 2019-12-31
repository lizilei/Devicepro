package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * 六统一设备型号实体类
 */
@Entity(nameInDb = "LTYSBXH", createInDb = false)
public class LTYSBXH implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Property(nameInDb = "ID")
    private Long id;    // id
    @Property(nameInDb = "ZZCJ")
    private String zzcj;  // 制造厂家
    @Property(nameInDb = "BHLB")
    private String bhlb;  // 保护类别
    @Property(nameInDb = "BHXH")
    private String bhxh;  // 保护型号
    @Property(nameInDb = "BHFL")
    private String bhfl;  // 保护分类
    @Property(nameInDb = "BHLX")
    private String bhlx;  // 保护类型
    @Property(nameInDb = "BBLX")
    private String bblx;  // 版本类型
    @Property(nameInDb = "STATE")
    private String state; //
    @Property(nameInDb = "XP")
    private String xp;      // 选配
    @Property(nameInDb = "RJBB")
    private String rjbb;    //装置软件版本
    @Property(nameInDb = "SYDYDJ")
    private String sydydj;  // 适用电压等级
    @Property(nameInDb = "SFQY")
    private String sfqy;    // 是否启用
    @Property(nameInDb = "CODE")
    private String code;    // 编码
    @Property(nameInDb = "WJBB")
    private String wjbb;    // 文件版本
    @Property(nameInDb = "WJMC")
    private String wjmc;    // 文件名称
    @Property(nameInDb = "WJDX")
    private String wjdx;    // 文件大小
    @Property(nameInDb = "ZZXGSJ")
    private String zzxgsj;  // 最终修改时间
    @Property(nameInDb = "CRC32")
    private String crc32;   // CRC32验证码
    @Property(nameInDb = "MD5")
    private String md5;     // MD5验证码
    @Property(nameInDb = "JYMSCSJ")
    private String jymscsj; //校验码生成时间
    @Property(nameInDb = "XXSCSJ")
    private String xxscsj;  // 信息生成时间
    @Property(nameInDb = "ZYJCPC")
    private String zyjcpc;  //
    @Property(nameInDb = "SYSBLX")// 专业检测批次
    private String sysblx;  // 适用设备类型
    @Property(nameInDb = "ED_TAG")
    private String ED_TAG;  //
    @Transient
    private String SCSJ;  //
    @Generated(hash = 642542082)
    public LTYSBXH(Long id, String zzcj, String bhlb, String bhxh, String bhfl,
            String bhlx, String bblx, String state, String xp, String rjbb,
            String sydydj, String sfqy, String code, String wjbb, String wjmc,
            String wjdx, String zzxgsj, String crc32, String md5, String jymscsj,
            String xxscsj, String zyjcpc, String sysblx, String ED_TAG) {
        this.id = id;
        this.zzcj = zzcj;
        this.bhlb = bhlb;
        this.bhxh = bhxh;
        this.bhfl = bhfl;
        this.bhlx = bhlx;
        this.bblx = bblx;
        this.state = state;
        this.xp = xp;
        this.rjbb = rjbb;
        this.sydydj = sydydj;
        this.sfqy = sfqy;
        this.code = code;
        this.wjbb = wjbb;
        this.wjmc = wjmc;
        this.wjdx = wjdx;
        this.zzxgsj = zzxgsj;
        this.crc32 = crc32;
        this.md5 = md5;
        this.jymscsj = jymscsj;
        this.xxscsj = xxscsj;
        this.zyjcpc = zyjcpc;
        this.sysblx = sysblx;
        this.ED_TAG = ED_TAG;
    }
    @Generated(hash = 739106790)
    public LTYSBXH() {
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
    public String getBhlb() {
        return this.bhlb;
    }
    public void setBhlb(String bhlb) {
        this.bhlb = bhlb;
    }
    public String getBhxh() {
        return this.bhxh;
    }
    public void setBhxh(String bhxh) {
        this.bhxh = bhxh;
    }
    public String getBhfl() {
        return this.bhfl;
    }
    public void setBhfl(String bhfl) {
        this.bhfl = bhfl;
    }
    public String getBhlx() {
        return this.bhlx;
    }
    public void setBhlx(String bhlx) {
        this.bhlx = bhlx;
    }
    public String getBblx() {
        return this.bblx;
    }
    public void setBblx(String bblx) {
        this.bblx = bblx;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getXp() {
        return this.xp;
    }
    public void setXp(String xp) {
        this.xp = xp;
    }
    public String getRjbb() {
        return this.rjbb;
    }
    public void setRjbb(String rjbb) {
        this.rjbb = rjbb;
    }
    public String getSydydj() {
        return this.sydydj;
    }
    public void setSydydj(String sydydj) {
        this.sydydj = sydydj;
    }
    public String getSfqy() {
        return this.sfqy;
    }
    public void setSfqy(String sfqy) {
        this.sfqy = sfqy;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getWjbb() {
        return this.wjbb;
    }
    public void setWjbb(String wjbb) {
        this.wjbb = wjbb;
    }
    public String getWjmc() {
        return this.wjmc;
    }
    public void setWjmc(String wjmc) {
        this.wjmc = wjmc;
    }
    public String getWjdx() {
        return this.wjdx;
    }
    public void setWjdx(String wjdx) {
        this.wjdx = wjdx;
    }
    public String getZzxgsj() {
        return this.zzxgsj;
    }
    public void setZzxgsj(String zzxgsj) {
        this.zzxgsj = zzxgsj;
    }
    public String getCrc32() {
        return this.crc32;
    }
    public void setCrc32(String crc32) {
        this.crc32 = crc32;
    }
    public String getMd5() {
        return this.md5;
    }
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    public String getJymscsj() {
        return this.jymscsj;
    }
    public void setJymscsj(String jymscsj) {
        this.jymscsj = jymscsj;
    }
    public String getXxscsj() {
        return this.xxscsj;
    }
    public void setXxscsj(String xxscsj) {
        this.xxscsj = xxscsj;
    }
    public String getZyjcpc() {
        return this.zyjcpc;
    }
    public void setZyjcpc(String zyjcpc) {
        this.zyjcpc = zyjcpc;
    }
    public String getSysblx() {
        return this.sysblx;
    }
    public void setSysblx(String sysblx) {
        this.sysblx = sysblx;
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
