package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * 出厂信息实体类
 */

@Entity(nameInDb = "CCXX", createInDb = false)
public class CCXX implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Property(nameInDb = "ID")
    private Long id;
    @Property(nameInDb = "STATE")
    private String state;     //
    @Property(nameInDb = "FWSF")
    private String fwsf;
    @Property(nameInDb = "SFSBM")
    private String sfsbm;
    @Property(nameInDb = "ZZCJ")
    private String zzcj;
    @Property(nameInDb = "BHLB")
    private String bhlb;
    @Property(nameInDb = "BHXH")
    private String bhxh;
    @Property(nameInDb = "BHFL")
    private String bhfl;
    @Property(nameInDb = "BHLX")
    private String bhlx;
    @Property(nameInDb = "XP")
    private String xp;
    @Property(nameInDb = "SYDYDJ")
    private String sydydj;
    @Property(nameInDb = "WJBB")
    private String wjbb;
    @Property(nameInDb = "WJMC")
    private String wjmc;
    @Property(nameInDb = "CRC32")
    private String crc32;     // ICD文件CRC32校验码
    @Property(nameInDb = "SCRQ")
    private String scrq;      // 出厂日期
    @Property(nameInDb = "CCRQ")
    private String ccrq;      // 出厂日期
    @Property(nameInDb = "BKSL")
    private int bksl;      // 板卡数量
    @Property(nameInDb = "HGQLX")
    private String hgqlx;     // 互感器类型
    @Property(nameInDb = "SBLBXH")
    private String sblbxh;
    @Property(nameInDb = "SBGNPZ")
    private String sbgnpz;    // 设备功能配置
    @Property(nameInDb = "TYPE")
    private String type;
    @Property(nameInDb = "SFLTYSB")
    private String sfltysb;
    @Property(nameInDb = "LTYBZBB")
    private String ltybzbb;
    @Property(nameInDb = "BBLX")
    private String bblx;
    // 保护装置及故障录波器缺少
    @Property(nameInDb = "SFFBZYJCGG")
    private String sffbzyjcgg;// 是否发布专业检测公告
    @Property(nameInDb = "MD5M")
    private String md5m;      // ICD文件MD5校验码
    @Property(nameInDb = "WJSCRQ")
    private String wjscrq;    // ICD文件生成日期
    @Property(nameInDb = "ECEDDL")
    private String eceddl;    // CT二次额定电流
    @Property(nameInDb = "ZLEDDY")
    private String zleddy;    // 直流额定电压
    @Property(nameInDb = "TDLX")
    private String tdlx;      // 通道类型
    @Property(nameInDb = "SFJDHZZ")
    private String sfjdhzz;   // 是否就地化装置
    @Property(nameInDb = "DZBQZZCJ")
    private String dzbqzzcj;  // 电子标签制造厂家
    @Property(nameInDb = "DZBQXH")
    private String dzbqxh;    // 电子标签型号
    @Property(nameInDb = "LJQSL")
    private String ljqsl;     // 连接器数量
    @Property(nameInDb = "LJQZZCJ")
    private String ljqzzcj;   // 连接器（母头）制造厂家
    @Property(nameInDb = "LJQXH")
    private String ljqxh;     // 连接器（母头）型号

    // 安全自动装置
    @Property(nameInDb = "AKZDLX")
    private String akzdlx;    // 安控站点类型

    // 交换机
    @Property(nameInDb = "JSGXJKMS")
    private String jsgxjkms;  // 接收光纤接口模式
    @Property(nameInDb = "JHJGN")
    private String jhjgn;     // 交换机功能
    @Property(nameInDb = "JHJJLS")
    private int jhjjls;    // 交换机级联数
    @Property(nameInDb = "SFZCRSTPHW")
    private String sfzcrstphw;// 是否支持RSTP环网
    @Property(nameInDb = "SFZCDS")
    private String sfzcds;    // 是否支持1588对时
    @Property(nameInDb = "SFZCDTZBGL")
    private String sfzcdtzbgl;// 是否支持动态组播管理
    @Property(nameInDb = "SFZCCYIEC")
    private String sfzccyiec; // 是否支持采用IEC61850上送交换机信息
    @Generated(hash = 549598923)
    public CCXX(Long id, String state, String fwsf, String sfsbm, String zzcj,
            String bhlb, String bhxh, String bhfl, String bhlx, String xp,
            String sydydj, String wjbb, String wjmc, String crc32, String scrq,
            String ccrq, int bksl, String hgqlx, String sblbxh, String sbgnpz,
            String type, String sfltysb, String ltybzbb, String bblx,
            String sffbzyjcgg, String md5m, String wjscrq, String eceddl,
            String zleddy, String tdlx, String sfjdhzz, String dzbqzzcj,
            String dzbqxh, String ljqsl, String ljqzzcj, String ljqxh,
            String akzdlx, String jsgxjkms, String jhjgn, int jhjjls,
            String sfzcrstphw, String sfzcds, String sfzcdtzbgl, String sfzccyiec) {
        this.id = id;
        this.state = state;
        this.fwsf = fwsf;
        this.sfsbm = sfsbm;
        this.zzcj = zzcj;
        this.bhlb = bhlb;
        this.bhxh = bhxh;
        this.bhfl = bhfl;
        this.bhlx = bhlx;
        this.xp = xp;
        this.sydydj = sydydj;
        this.wjbb = wjbb;
        this.wjmc = wjmc;
        this.crc32 = crc32;
        this.scrq = scrq;
        this.ccrq = ccrq;
        this.bksl = bksl;
        this.hgqlx = hgqlx;
        this.sblbxh = sblbxh;
        this.sbgnpz = sbgnpz;
        this.type = type;
        this.sfltysb = sfltysb;
        this.ltybzbb = ltybzbb;
        this.bblx = bblx;
        this.sffbzyjcgg = sffbzyjcgg;
        this.md5m = md5m;
        this.wjscrq = wjscrq;
        this.eceddl = eceddl;
        this.zleddy = zleddy;
        this.tdlx = tdlx;
        this.sfjdhzz = sfjdhzz;
        this.dzbqzzcj = dzbqzzcj;
        this.dzbqxh = dzbqxh;
        this.ljqsl = ljqsl;
        this.ljqzzcj = ljqzzcj;
        this.ljqxh = ljqxh;
        this.akzdlx = akzdlx;
        this.jsgxjkms = jsgxjkms;
        this.jhjgn = jhjgn;
        this.jhjjls = jhjjls;
        this.sfzcrstphw = sfzcrstphw;
        this.sfzcds = sfzcds;
        this.sfzcdtzbgl = sfzcdtzbgl;
        this.sfzccyiec = sfzccyiec;
    }
    @Generated(hash = 165088846)
    public CCXX() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getFwsf() {
        return this.fwsf;
    }
    public void setFwsf(String fwsf) {
        this.fwsf = fwsf;
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
    public String getXp() {
        return this.xp;
    }
    public void setXp(String xp) {
        this.xp = xp;
    }
    public String getSydydj() {
        return this.sydydj;
    }
    public void setSydydj(String sydydj) {
        this.sydydj = sydydj;
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
    public String getCrc32() {
        return this.crc32;
    }
    public void setCrc32(String crc32) {
        this.crc32 = crc32;
    }
    public String getScrq() {
        return this.scrq;
    }
    public void setScrq(String scrq) {
        this.scrq = scrq;
    }
    public String getCcrq() {
        return this.ccrq;
    }
    public void setCcrq(String ccrq) {
        this.ccrq = ccrq;
    }
    public int getBksl() {
        return this.bksl;
    }
    public void setBksl(int bksl) {
        this.bksl = bksl;
    }
    public String getHgqlx() {
        return this.hgqlx;
    }
    public void setHgqlx(String hgqlx) {
        this.hgqlx = hgqlx;
    }
    public String getSblbxh() {
        return this.sblbxh;
    }
    public void setSblbxh(String sblbxh) {
        this.sblbxh = sblbxh;
    }
    public String getSbgnpz() {
        return this.sbgnpz;
    }
    public void setSbgnpz(String sbgnpz) {
        this.sbgnpz = sbgnpz;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSfltysb() {
        return this.sfltysb;
    }
    public void setSfltysb(String sfltysb) {
        this.sfltysb = sfltysb;
    }
    public String getLtybzbb() {
        return this.ltybzbb;
    }
    public void setLtybzbb(String ltybzbb) {
        this.ltybzbb = ltybzbb;
    }
    public String getBblx() {
        return this.bblx;
    }
    public void setBblx(String bblx) {
        this.bblx = bblx;
    }
    public String getSffbzyjcgg() {
        return this.sffbzyjcgg;
    }
    public void setSffbzyjcgg(String sffbzyjcgg) {
        this.sffbzyjcgg = sffbzyjcgg;
    }
    public String getMd5m() {
        return this.md5m;
    }
    public void setMd5m(String md5m) {
        this.md5m = md5m;
    }
    public String getWjscrq() {
        return this.wjscrq;
    }
    public void setWjscrq(String wjscrq) {
        this.wjscrq = wjscrq;
    }
    public String getEceddl() {
        return this.eceddl;
    }
    public void setEceddl(String eceddl) {
        this.eceddl = eceddl;
    }
    public String getZleddy() {
        return this.zleddy;
    }
    public void setZleddy(String zleddy) {
        this.zleddy = zleddy;
    }
    public String getTdlx() {
        return this.tdlx;
    }
    public void setTdlx(String tdlx) {
        this.tdlx = tdlx;
    }
    public String getSfjdhzz() {
        return this.sfjdhzz;
    }
    public void setSfjdhzz(String sfjdhzz) {
        this.sfjdhzz = sfjdhzz;
    }
    public String getDzbqzzcj() {
        return this.dzbqzzcj;
    }
    public void setDzbqzzcj(String dzbqzzcj) {
        this.dzbqzzcj = dzbqzzcj;
    }
    public String getDzbqxh() {
        return this.dzbqxh;
    }
    public void setDzbqxh(String dzbqxh) {
        this.dzbqxh = dzbqxh;
    }
    public String getLjqsl() {
        return this.ljqsl;
    }
    public void setLjqsl(String ljqsl) {
        this.ljqsl = ljqsl;
    }
    public String getLjqzzcj() {
        return this.ljqzzcj;
    }
    public void setLjqzzcj(String ljqzzcj) {
        this.ljqzzcj = ljqzzcj;
    }
    public String getLjqxh() {
        return this.ljqxh;
    }
    public void setLjqxh(String ljqxh) {
        this.ljqxh = ljqxh;
    }
    public String getAkzdlx() {
        return this.akzdlx;
    }
    public void setAkzdlx(String akzdlx) {
        this.akzdlx = akzdlx;
    }
    public String getJsgxjkms() {
        return this.jsgxjkms;
    }
    public void setJsgxjkms(String jsgxjkms) {
        this.jsgxjkms = jsgxjkms;
    }
    public String getJhjgn() {
        return this.jhjgn;
    }
    public void setJhjgn(String jhjgn) {
        this.jhjgn = jhjgn;
    }
    public int getJhjjls() {
        return this.jhjjls;
    }
    public void setJhjjls(int jhjjls) {
        this.jhjjls = jhjjls;
    }
    public String getSfzcrstphw() {
        return this.sfzcrstphw;
    }
    public void setSfzcrstphw(String sfzcrstphw) {
        this.sfzcrstphw = sfzcrstphw;
    }
    public String getSfzcds() {
        return this.sfzcds;
    }
    public void setSfzcds(String sfzcds) {
        this.sfzcds = sfzcds;
    }
    public String getSfzcdtzbgl() {
        return this.sfzcdtzbgl;
    }
    public void setSfzcdtzbgl(String sfzcdtzbgl) {
        this.sfzcdtzbgl = sfzcdtzbgl;
    }
    public String getSfzccyiec() {
        return this.sfzccyiec;
    }
    public void setSfzccyiec(String sfzccyiec) {
        this.sfzccyiec = sfzccyiec;
    }
}
