package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

@Entity(nameInDb = "FZBHSB", createInDb = false)
public class FZBHSB implements Serializable {
    private static final long serialVersionUID = 1L;
    // @ExcelField(name = "上报保护配置标识", next = "sbxh")
    // @ZdhzColumn(columnName = "SB_BHPZ_ID")
    @Property(nameInDb = "SB_BHPZ_ID")
    private String sb_bhpz_id;
    // @ExcelField(name = "标识", next = "shsj")
    // @ZdhzColumn(columnName = "ID")
    @Id
    @Unique
    @Property(nameInDb = "ID")
    private Long id;
    // @ZdhzColumn(columnName = "SHSJ")
    // @ExcelField(name = "首次审核通过时间", next = "czr")
    @Property(nameInDb = "SHSJ")
    private String shsj;             //
    // @ZdhzColumn(columnName = "CJ")
    // @ExcelField(name = "制造厂家", next = "tyrq")
    @Property(nameInDb = "CJ")
    private String cj;
    // @ZdhzColumn(columnName = "CZMC")
    // @ExcelField(name = "厂站名称", next = "bdzlx")
    @Property(nameInDb = "CZMC")
    private String czmc;
    // @ZdhzColumn(columnName = "YCSBMC")
    // @ExcelField(name = "一次设备名称", next = "dydj")
    @Property(nameInDb = "YCSBMC")
    private String ycsbmc;
    // @ZdhzColumn(columnName = "DYDJ")
    // @ExcelField(name = "电压等级", next = "dddw")
    @Property(nameInDb = "DYDJ")
    private int dydj;
    // @ExcelField(name = "保护配置唯一标识", next = "sbmc")
    // @ZdhzColumn(columnName = "BHPZID")
    @Property(nameInDb = "BHPZID")
    private long bhpzid;
    // @ZdhzColumn(columnName = "FZSBLX")
    // @ExcelField(name = "辅助设备类型", next = "bhpzid")
    @Property(nameInDb = "FZSBLX")
    private String fzsblx;
    // @ZdhzColumn(columnName = "SBXH")
    // @ExcelField(name = "设备型号", next = "cj")
    @Property(nameInDb = "SBXH")
    private String sbxh;
    // @ZdhzColumn(columnName = "TYRQ", type = ExcelFieldType.TIMESTAMP)
    // @ExcelField(name = "投运日期", next = "type")
    @Property(nameInDb = "TYRQ")
    private String tyrq;
    // @ZdhzColumn(columnName = "SBMC")
    // @ExcelField(name = "设备名称", next = "czmc")
    @Property(nameInDb = "SBMC")
    private String sbmc;
    // @ZdhzColumn(columnName = "DDDW")
    // @ExcelField(name = "调度单位", next = "zbtdjgx")
    @Property(nameInDb = "DDDW")
    private String dddw;
    // @ZdhzColumn(columnName = "ZBTDJGX")
    // @ExcelField(name = "载波通道加工相", next = "tdpl")
    @Property(nameInDb = "ZBTDJGX")
    private String zbtdjgx;
    // @ZdhzColumn(columnName = "TDPL")
    // @ExcelField(name = "通道频率", next = "dqjyzq")
    @Property(nameInDb = "TDPL")
    private int tdpl;
    // @ZdhzColumn(columnName = "CZR")
    // @ExcelField(name = "操作人", next = "czzgdydj")
    @Property(nameInDb = "CZR")
    private String czr;
    @Property(nameInDb = "WDID")
    private Long wdId;   //文档id
    // @ZdhzColumn(columnName = "DQJYZQ")
    // @ExcelField(name = "定期检验周期", next = "sftcyx")
    @Property(nameInDb = "DQJYZQ")
    private float dqjyzq;
    // @ZdhzColumn(columnName = "SFTCYX")
    // @ExcelField(name = "是否退出运行", next = "tcyxsj")
    @Property(nameInDb = "SFTCYX")
    private String sftcyx;           // 是否退出运行
    @Property(nameInDb = "SBDW")
    private String sbdw;           // 上报单位
    // @ZdhzColumn(columnName = "TCYXSJ")
    // @ExcelField(name = "退出运行时间", next = "jgmc")
    @Property(nameInDb = "TCYXSJ")
    private String tcyxsj;           // 退出运行时间 年-月-日

    // @ZdhzColumn(columnName = "SH")
    // @ExcelField(name = "审核状态", next = "shr2")
    @Property(nameInDb = "SH")
    private String sh;               // 审核
    // @ZdhzColumn(columnName = "SHR")
    // @ExcelField(name = "审核人", next = "shyy")
    @Property(nameInDb = "SHR")
    private String shr2;             // 审核人
    // @ZdhzColumn(columnName = "SHYY")
    // @ExcelField(name = "审核原因", next = "")
    @Property(nameInDb = "SHYY")
    private String shyy;             // 审核原因
    @Property(nameInDb = "DWTJR")
    private String dwtjr;

    @Property(nameInDb = "TJR")
    private String tjr;
    // @ZdhzColumn(columnName = "JGLX")
    // @ExcelField(name = "间隔类型", next = "czgldw")
    @Property(nameInDb = "JGLX")
    private String jglx;
    // @ZdhzColumn(columnName = "JGMC")
    // @ExcelField(name = "间隔名称", next = "jglx")
    @Property(nameInDb = "JGMC")
    private String jgmc;
    // @ZdhzColumn(columnName = "GLDW")
    // @ExcelField(name = "厂站管理单位", next = "yxdw")
    @Property(nameInDb = "GLDW")
    private String czgldw;

    // @ZdhzColumn(columnName = "RJBB")
    // @ExcelField(name = "软件版本", next = "usegddata")
    @Property(nameInDb = "RJBB")
    private String rjbb;             // 软件版本
    // @ZdhzColumn(columnName = "YXDW")
    // @ExcelField(name = "运行单位", next = "whdw")
    @Property(nameInDb = "YXDW")
    private String yxdw;             // 运行单位
    // @ZdhzColumn(columnName = "WHDW")
    // @ExcelField(name = "维护单位", next = "sjdw")
    @Property(nameInDb = "WHDW")
    private String whdw;             // 维护单位
    // @ZdhzColumn(columnName = "SJDW")
    // @ExcelField(name = "设计单位", next = "jjdw")
    @Property(nameInDb = "SJDW")
    private String sjdw;             // 设计单位
    // @ZdhzColumn(columnName = "JJDW")
    // @ExcelField(name = "基建单位", next = "xcdqjylx")
    @Property(nameInDb = "JJDW")
    private String jjdw;             // 基建单位
    // @ZdhzColumn(columnName = "SCDQJYLX")
    // @ExcelField(name = "本次定期检验类型", next = "scdqjydw")
    @Property(nameInDb = "SCDQJYLX")
    private String scdqjylx;         // 上次定期检验类型
    // @ZdhzColumn(columnName = "SCDQJYSJ", type = ExcelFieldType.TIMESTAMP)
    // @ExcelField(name = "本次定期检验时间", next = "rjbb")
    @Property(nameInDb = "SCDQJYSJ")
    private String scdqjysj;         // 上次定期检验时间 /上次检修时间
    // @ZdhzColumn(columnName = "SCDQJYDW")
    // @ExcelField(name = "本次定期检验单位", next = "scdqjysj")
    @Property(nameInDb = "SCDQJYDW")
    private String scdqjydw;         // 上次定期检验单位
    // @ZdhzColumn(columnName = "XCDQJYLX")
    // @ExcelField(name = "下次定期检验类型", next = "xcdqjysj")
    @Property(nameInDb = "XCDQJYLX")
    private String xcdqjylx;         // 下次定期检验类型
    // @ZdhzColumn(columnName = "XCDQJYSJ", type = ExcelFieldType.TIMESTAMP)
    // @ExcelField(name = "下次定期检验时间", next = "scdqjylx")
    @Property(nameInDb = "XCDQJYSJ")
    private String xcdqjysj;         // 下次定期检验时间
    // @ZdhzColumn(columnName = "SBBM")
    // @ExcelField(name = "设备编码", next = "sbxs")
    @Property(nameInDb = "SBBM")
    private String sbbm;             // 设备编码（SBBM）
    // @ZdhzColumn(columnName = "ZCXZ")
    // @ExcelField(name = "资产性质", next = "ccrq")
    @Property(nameInDb = "ZCXZ")
    private String zcxz;             // 资产性质(ZCXZ)
    // @ZdhzColumn(columnName = "ZCDW")
    // @ExcelField(name = "资产单位", next = "zcxz")
    @Property(nameInDb = "ZCDW")
    private String zcdw;             // 资产单位(ZCDW)
    // @ZdhzColumn(columnName = "SBXS")
    // @ExcelField(name = "设备型式", next = "validateformat")
    @Property(nameInDb = "SBXS")
    private String sbxs;             // 设备型式(SBXS)

    // @ExcelField(name = "核查结果", next = "ls_validateformat")
    // @ZdhzColumn(columnName = "VALIDATEFORMAT")
    @Property(nameInDb = "VALIDATEFORMAT")
    private String validateformat;
    // @ExcelField(name = "历史核查结果", next = "ycsblx")
    // @ZdhzColumn(columnName = "LS_VALIDATEFORMAT")
    @Property(nameInDb = "LS_VALIDATEFORMAT")
    private String ls_validateformat;
    // @ZdhzColumn(columnName = "CASEVALIDATE")
    @Property(nameInDb = "CASEVALIDATE")
    private String caseValidate;
    // @ZdhzColumn(columnName = "SFXTLR")
    @Property(nameInDb = "SFXTLR")
    private String sfxtlr;

    // @ZdhzColumn(columnName = "BDZLX")
    // @ExcelField(name = "变电站类型", next = "ycsbmc")
    @Property(nameInDb = "BDZLX")
    private String bdzlx;

    // @ZdhzColumn(columnName = "CZZGDYDJ")
    // @ExcelField(name = "厂站最高电压等级", next = "sb_bhpz_id")
    @Property(nameInDb = "CZZGDYDJ")
    private long czzgdydj;

    // @ExcelField(name = "是否六统一设备", must = false, next = "ltybzbb")
    // @ZdhzColumn(columnName = "SFLTYSB")
    @Property(nameInDb = "SFLTYSB")
    private String sfltysb;
    // @ExcelField(name = "六统一标准版本", must = false, next = "sfsbm")
    // @ZdhzColumn(columnName = "LTYBZBB")
    @Property(nameInDb = "LTYBZBB")
    private String ltybzbb;
    // @ExcelField(name = "身份识别码", must = false, next = "icdwjmc")
    // @ZdhzColumn(columnName = "SFSBM")
    @Property(nameInDb = "SFSBM")
    private String sfsbm;
    // @ExcelField(name = "IDC文件名称", must = false, next = "jzxh")
    // @ZdhzColumn(columnName = "icdwjmc")
    @Property(nameInDb = "ICDWJMC")
    private String icdwjmc;
    // @ExcelField(name = "家族型号", must = false, next = "sbsx")
    // @ZdhzColumn(columnName = "jzxh")
    @Property(nameInDb = "JZXH")
    private String jzxh;
    // @ExcelField(name = "板卡数量", must = false, next = "yxzt")
    // @ZdhzColumn(columnName = "bksl")
    @Property(nameInDb = "BKSL")
    private int bksl;

    // @ZdhzColumn(columnName = "CCBH")
    // @ExcelField(name = "出厂编号", next = "zcdw")
    @Property(nameInDb = "CCBH")
    private String ccbh;           // 出厂编号(CCBH)
    // @ZdhzColumn(columnName = "CCRQ", type = ExcelFieldType.TIMESTAMP)
    // @ExcelField(name = "出厂日期", next = "tb")
    @Property(nameInDb = "CCRQ")
    private String ccrq;           // 出厂日期(CCRQ)
    // @ZdhzColumn(columnName = "YXZT")
    // @ExcelField(name = "运行状态", next = "zcbh")
    @Property(nameInDb = "YXZT")
    private String yxzt;           // 运行状态(YXZT)
    // @ZdhzColumn(columnName = "ZCBH")
    // @ExcelField(name = "资产编号", next = "ccbh")
    @Property(nameInDb = "ZCBH")
    private String zcbh;           // 资产编号(ZCBH)
    // @ZdhzColumn(columnName = "TB")
    // @ExcelField(name = "套别", next = "sbbm")
    @Property(nameInDb = "TB")
    private String tb;             // 套别(TB)
    // @ZdhzColumn(columnName = "SBSX")
    // @ExcelField(name = "设备属性", next = "bksl")
    @Property(nameInDb = "SBSX")
    private String sbsx;           // 设备属性(SBSX)
    // @ZdhzColumn(columnName = "YCSBLX")
    // @ExcelField(name = "一次设备类型", next = "dsfs")
    @Property(nameInDb = "YCSBLX")
    private String ycsblx;         // 一次设备类型(YCSBLX)
    // @ZdhzColumn(columnName = "DSFS")
    // @ExcelField(name = "对时方式", next = "gddy")
    @Property(nameInDb = "DSFS")
    private String dsfs;           // 对时方式(DSFS)
    // @ZdhzColumn(columnName = "GDDY")
    // @ExcelField(name = "供电电源", next = "zzsx")
    @Property(nameInDb = "GDDY")
    private String gddy;           // 供电电源(GDDY)
    // @ZdhzColumn(columnName = "ZZSX")
    // @ExcelField(name = "资产属性", next = "fsgxsl")
    @Property(nameInDb = "ZZSX")
    private String zzsx;           // 装置属性(ZZSX)

    // @ZdhzColumn(columnName = "FSGXSL")
    // @ExcelField(name = "发送光纤口数量", next = "gxjkms")
    @Property(nameInDb = "FSGXSL")
    private String fsgxsl = "0";// 发送光纤口数量(FSGXSL)
    // @ZdhzColumn(columnName = "GXJKMS")
    // @ExcelField(name = "光纤接口模式", next = "hbdygn")
    @Property(nameInDb = "GXJKMS")
    private String gxjkms;         // 光纤接口模式(GXJKMS)
    // @ZdhzColumn(columnName = "HBDYGN")
    // @ExcelField(name = "合并单元功能", next = "hgqlx")
    @Property(nameInDb = "HBDYGN")
    private String hbdygn;         // 合并单元功能(HBDYGN)
    // @ZdhzColumn(columnName = "HGQLX")
    // @ExcelField(name = "互感器类型", next = "znzdgn")
    @Property(nameInDb = "HGQLX")
    private String hgqlx;          // 互感器类型(HGQLX)
    // @ZdhzColumn(columnName = "ZNZDGN")
    // @ExcelField(name = "智能终端功能", next = "jhjgn")
    @Property(nameInDb = "ZNZDGN")
    private String znzdgn;         // 智能终端功能(ZNZDGN)
    // @ZdhzColumn(columnName = "JHJGN")
    // @ExcelField(name = "交换机功能", next = "jhjjls")
    @Property(nameInDb = "JHJGN")
    private String jhjgn;          // 交换机功能(JHJGN)
    // @ZdhzColumn(columnName = "JHJJLS")
    // @ExcelField(name = "交换机级联数", next = "sfrstp")
    @Property(nameInDb = "JHJJLS")
    private int jhjjls;         // 交换机级联数(JHJJLS)
    // @ZdhzColumn(columnName = "SFRSTP")
    // @ExcelField(name = "是否支持RSTP环网", next = "sfds")
    @Property(nameInDb = "SFRSTP")
    private String sfrstp;         // 是否支持RSTP环网(SFRSTP)
    // @ZdhzColumn(columnName = "SFDS")
    // @ExcelField(name = "是否支持1588对时", next = "sfzb")
    @Property(nameInDb = "SFDS")
    private String sfds;           // 是否支持1588对时(SFDS)
    // @ZdhzColumn(columnName = "SFZB")
    // @ExcelField(name = "是否支持动态组播管理", next = "sfsnmp")
    @Property(nameInDb = "SFZB")
    private String sfzb;           // 是否支持动态组播管理(SFZB)
    // @ZdhzColumn(columnName = "SFSNMP")
    // @ExcelField(name = "是否支持SNMP网络管理", next = "sfiec")
    @Property(nameInDb = "SFSNMP")
    private String sfsnmp;         // 是否支持SNMP网络管理(SFSNMP)
    // @ZdhzColumn(columnName = "SFIEC")
    // @ExcelField(name = "是否支持采用IEC61850上送交换机信息", next = "jhjlx")
    @Property(nameInDb = "SFIEC")
    private String sfiec;          // 是否支持采用IEC61850上送交换机信息(SFIEC)
    // @ZdhzColumn(columnName = "JHJLX")
    // @ExcelField(name = "交换机类型", next = "bhlx")
    @Property(nameInDb = "JHJLX")
    private String jhjlx;          // 交换机类型(JHJLX)
    // @ZdhzColumn(columnName = "BHLX")
    // @ExcelField(name = "保护类型", next = "bhfl")
    @Property(nameInDb = "BHLX")
    private String bhlx;
    // @ZdhzColumn(columnName = "BHFL")
    // @ExcelField(name = "保护分类", next = "czsx")
    @Property(nameInDb = "BHFL")
    private String bhfl;
    // @ZdhzColumn(columnName = "CZSX")
    // @ExcelField(name = "厂站属性", next = "sh")
    @Property(nameInDb = "CZSX")
    private String czsx;           // 厂站属性包含（电厂，变电站）
//    private String sfsbmsfyx = ""; // 默认身份识别码无效

    // @ZdhzColumn(columnName = "USEGDDATA")
    // @ExcelField(name = "是否使用国调下发型号", next = "sfltysb")
    @Property(nameInDb = "SB")
    private String sb;// 上报

    @Property(nameInDb = "CZSJ")
    private String czsj;// 操作时间

    @Property(nameInDb = "USEGDDATA")
    private String usegddata = "true";// 是否使用国调标准型号

    // @ZdhzColumn(columnName = "GLDW_GD"
    @Property(nameInDb = "GLDW_GD")
    private String gldw_gd;
    // @ZdhzColumn(columnName = "DDDW_GD")
    @Property(nameInDb = "DDDW_GD")
    private String dddw_gd;
    @Property(nameInDb = "ED_TAG")
    private String ed_tag; //装置处理状态
    @Property(nameInDb = "SWID")
    private String sw_id;  //实物id
    @Property(nameInDb = "JY")
    private String jy;  //装置校验标识
    @Generated(hash = 1616356957)
    public FZBHSB(String sb_bhpz_id, Long id, String shsj, String cj, String czmc,
            String ycsbmc, int dydj, long bhpzid, String fzsblx, String sbxh,
            String tyrq, String sbmc, String dddw, String zbtdjgx, int tdpl,
            String czr, Long wdId, float dqjyzq, String sftcyx, String sbdw,
            String tcyxsj, String sh, String shr2, String shyy, String dwtjr,
            String tjr, String jglx, String jgmc, String czgldw, String rjbb,
            String yxdw, String whdw, String sjdw, String jjdw, String scdqjylx,
            String scdqjysj, String scdqjydw, String xcdqjylx, String xcdqjysj,
            String sbbm, String zcxz, String zcdw, String sbxs,
            String validateformat, String ls_validateformat, String caseValidate,
            String sfxtlr, String bdzlx, long czzgdydj, String sfltysb,
            String ltybzbb, String sfsbm, String icdwjmc, String jzxh, int bksl,
            String ccbh, String ccrq, String yxzt, String zcbh, String tb,
            String sbsx, String ycsblx, String dsfs, String gddy, String zzsx,
            String fsgxsl, String gxjkms, String hbdygn, String hgqlx,
            String znzdgn, String jhjgn, int jhjjls, String sfrstp, String sfds,
            String sfzb, String sfsnmp, String sfiec, String jhjlx, String bhlx,
            String bhfl, String czsx, String sb, String czsj, String usegddata,
            String gldw_gd, String dddw_gd, String ed_tag, String sw_id,
            String jy) {
        this.sb_bhpz_id = sb_bhpz_id;
        this.id = id;
        this.shsj = shsj;
        this.cj = cj;
        this.czmc = czmc;
        this.ycsbmc = ycsbmc;
        this.dydj = dydj;
        this.bhpzid = bhpzid;
        this.fzsblx = fzsblx;
        this.sbxh = sbxh;
        this.tyrq = tyrq;
        this.sbmc = sbmc;
        this.dddw = dddw;
        this.zbtdjgx = zbtdjgx;
        this.tdpl = tdpl;
        this.czr = czr;
        this.wdId = wdId;
        this.dqjyzq = dqjyzq;
        this.sftcyx = sftcyx;
        this.sbdw = sbdw;
        this.tcyxsj = tcyxsj;
        this.sh = sh;
        this.shr2 = shr2;
        this.shyy = shyy;
        this.dwtjr = dwtjr;
        this.tjr = tjr;
        this.jglx = jglx;
        this.jgmc = jgmc;
        this.czgldw = czgldw;
        this.rjbb = rjbb;
        this.yxdw = yxdw;
        this.whdw = whdw;
        this.sjdw = sjdw;
        this.jjdw = jjdw;
        this.scdqjylx = scdqjylx;
        this.scdqjysj = scdqjysj;
        this.scdqjydw = scdqjydw;
        this.xcdqjylx = xcdqjylx;
        this.xcdqjysj = xcdqjysj;
        this.sbbm = sbbm;
        this.zcxz = zcxz;
        this.zcdw = zcdw;
        this.sbxs = sbxs;
        this.validateformat = validateformat;
        this.ls_validateformat = ls_validateformat;
        this.caseValidate = caseValidate;
        this.sfxtlr = sfxtlr;
        this.bdzlx = bdzlx;
        this.czzgdydj = czzgdydj;
        this.sfltysb = sfltysb;
        this.ltybzbb = ltybzbb;
        this.sfsbm = sfsbm;
        this.icdwjmc = icdwjmc;
        this.jzxh = jzxh;
        this.bksl = bksl;
        this.ccbh = ccbh;
        this.ccrq = ccrq;
        this.yxzt = yxzt;
        this.zcbh = zcbh;
        this.tb = tb;
        this.sbsx = sbsx;
        this.ycsblx = ycsblx;
        this.dsfs = dsfs;
        this.gddy = gddy;
        this.zzsx = zzsx;
        this.fsgxsl = fsgxsl;
        this.gxjkms = gxjkms;
        this.hbdygn = hbdygn;
        this.hgqlx = hgqlx;
        this.znzdgn = znzdgn;
        this.jhjgn = jhjgn;
        this.jhjjls = jhjjls;
        this.sfrstp = sfrstp;
        this.sfds = sfds;
        this.sfzb = sfzb;
        this.sfsnmp = sfsnmp;
        this.sfiec = sfiec;
        this.jhjlx = jhjlx;
        this.bhlx = bhlx;
        this.bhfl = bhfl;
        this.czsx = czsx;
        this.sb = sb;
        this.czsj = czsj;
        this.usegddata = usegddata;
        this.gldw_gd = gldw_gd;
        this.dddw_gd = dddw_gd;
        this.ed_tag = ed_tag;
        this.sw_id = sw_id;
        this.jy = jy;
    }
    @Generated(hash = 1879218582)
    public FZBHSB() {
    }
    public String getSb_bhpz_id() {
        return this.sb_bhpz_id;
    }
    public void setSb_bhpz_id(String sb_bhpz_id) {
        this.sb_bhpz_id = sb_bhpz_id;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getShsj() {
        return this.shsj;
    }
    public void setShsj(String shsj) {
        this.shsj = shsj;
    }
    public String getCj() {
        return this.cj;
    }
    public void setCj(String cj) {
        this.cj = cj;
    }
    public String getCzmc() {
        return this.czmc;
    }
    public void setCzmc(String czmc) {
        this.czmc = czmc;
    }
    public String getYcsbmc() {
        return this.ycsbmc;
    }
    public void setYcsbmc(String ycsbmc) {
        this.ycsbmc = ycsbmc;
    }
    public int getDydj() {
        return this.dydj;
    }
    public void setDydj(int dydj) {
        this.dydj = dydj;
    }
    public long getBhpzid() {
        return this.bhpzid;
    }
    public void setBhpzid(long bhpzid) {
        this.bhpzid = bhpzid;
    }
    public String getFzsblx() {
        return this.fzsblx;
    }
    public void setFzsblx(String fzsblx) {
        this.fzsblx = fzsblx;
    }
    public String getSbxh() {
        return this.sbxh;
    }
    public void setSbxh(String sbxh) {
        this.sbxh = sbxh;
    }
    public String getTyrq() {
        return this.tyrq;
    }
    public void setTyrq(String tyrq) {
        this.tyrq = tyrq;
    }
    public String getSbmc() {
        return this.sbmc;
    }
    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }
    public String getDddw() {
        return this.dddw;
    }
    public void setDddw(String dddw) {
        this.dddw = dddw;
    }
    public String getZbtdjgx() {
        return this.zbtdjgx;
    }
    public void setZbtdjgx(String zbtdjgx) {
        this.zbtdjgx = zbtdjgx;
    }
    public int getTdpl() {
        return this.tdpl;
    }
    public void setTdpl(int tdpl) {
        this.tdpl = tdpl;
    }
    public String getCzr() {
        return this.czr;
    }
    public void setCzr(String czr) {
        this.czr = czr;
    }
    public Long getWdId() {
        return this.wdId;
    }
    public void setWdId(Long wdId) {
        this.wdId = wdId;
    }
    public float getDqjyzq() {
        return this.dqjyzq;
    }
    public void setDqjyzq(float dqjyzq) {
        this.dqjyzq = dqjyzq;
    }
    public String getSftcyx() {
        return this.sftcyx;
    }
    public void setSftcyx(String sftcyx) {
        this.sftcyx = sftcyx;
    }
    public String getSbdw() {
        return this.sbdw;
    }
    public void setSbdw(String sbdw) {
        this.sbdw = sbdw;
    }
    public String getTcyxsj() {
        return this.tcyxsj;
    }
    public void setTcyxsj(String tcyxsj) {
        this.tcyxsj = tcyxsj;
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
    public String getDwtjr() {
        return this.dwtjr;
    }
    public void setDwtjr(String dwtjr) {
        this.dwtjr = dwtjr;
    }
    public String getTjr() {
        return this.tjr;
    }
    public void setTjr(String tjr) {
        this.tjr = tjr;
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
    public String getCzgldw() {
        return this.czgldw;
    }
    public void setCzgldw(String czgldw) {
        this.czgldw = czgldw;
    }
    public String getRjbb() {
        return this.rjbb;
    }
    public void setRjbb(String rjbb) {
        this.rjbb = rjbb;
    }
    public String getYxdw() {
        return this.yxdw;
    }
    public void setYxdw(String yxdw) {
        this.yxdw = yxdw;
    }
    public String getWhdw() {
        return this.whdw;
    }
    public void setWhdw(String whdw) {
        this.whdw = whdw;
    }
    public String getSjdw() {
        return this.sjdw;
    }
    public void setSjdw(String sjdw) {
        this.sjdw = sjdw;
    }
    public String getJjdw() {
        return this.jjdw;
    }
    public void setJjdw(String jjdw) {
        this.jjdw = jjdw;
    }
    public String getScdqjylx() {
        return this.scdqjylx;
    }
    public void setScdqjylx(String scdqjylx) {
        this.scdqjylx = scdqjylx;
    }
    public String getScdqjysj() {
        return this.scdqjysj;
    }
    public void setScdqjysj(String scdqjysj) {
        this.scdqjysj = scdqjysj;
    }
    public String getScdqjydw() {
        return this.scdqjydw;
    }
    public void setScdqjydw(String scdqjydw) {
        this.scdqjydw = scdqjydw;
    }
    public String getXcdqjylx() {
        return this.xcdqjylx;
    }
    public void setXcdqjylx(String xcdqjylx) {
        this.xcdqjylx = xcdqjylx;
    }
    public String getXcdqjysj() {
        return this.xcdqjysj;
    }
    public void setXcdqjysj(String xcdqjysj) {
        this.xcdqjysj = xcdqjysj;
    }
    public String getSbbm() {
        return this.sbbm;
    }
    public void setSbbm(String sbbm) {
        this.sbbm = sbbm;
    }
    public String getZcxz() {
        return this.zcxz;
    }
    public void setZcxz(String zcxz) {
        this.zcxz = zcxz;
    }
    public String getZcdw() {
        return this.zcdw;
    }
    public void setZcdw(String zcdw) {
        this.zcdw = zcdw;
    }
    public String getSbxs() {
        return this.sbxs;
    }
    public void setSbxs(String sbxs) {
        this.sbxs = sbxs;
    }
    public String getValidateformat() {
        return this.validateformat;
    }
    public void setValidateformat(String validateformat) {
        this.validateformat = validateformat;
    }
    public String getLs_validateformat() {
        return this.ls_validateformat;
    }
    public void setLs_validateformat(String ls_validateformat) {
        this.ls_validateformat = ls_validateformat;
    }
    public String getCaseValidate() {
        return this.caseValidate;
    }
    public void setCaseValidate(String caseValidate) {
        this.caseValidate = caseValidate;
    }
    public String getSfxtlr() {
        return this.sfxtlr;
    }
    public void setSfxtlr(String sfxtlr) {
        this.sfxtlr = sfxtlr;
    }
    public String getBdzlx() {
        return this.bdzlx;
    }
    public void setBdzlx(String bdzlx) {
        this.bdzlx = bdzlx;
    }
    public long getCzzgdydj() {
        return this.czzgdydj;
    }
    public void setCzzgdydj(long czzgdydj) {
        this.czzgdydj = czzgdydj;
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
    public String getSfsbm() {
        return this.sfsbm;
    }
    public void setSfsbm(String sfsbm) {
        this.sfsbm = sfsbm;
    }
    public String getIcdwjmc() {
        return this.icdwjmc;
    }
    public void setIcdwjmc(String icdwjmc) {
        this.icdwjmc = icdwjmc;
    }
    public String getJzxh() {
        return this.jzxh;
    }
    public void setJzxh(String jzxh) {
        this.jzxh = jzxh;
    }
    public int getBksl() {
        return this.bksl;
    }
    public void setBksl(int bksl) {
        this.bksl = bksl;
    }
    public String getCcbh() {
        return this.ccbh;
    }
    public void setCcbh(String ccbh) {
        this.ccbh = ccbh;
    }
    public String getCcrq() {
        return this.ccrq;
    }
    public void setCcrq(String ccrq) {
        this.ccrq = ccrq;
    }
    public String getYxzt() {
        return this.yxzt;
    }
    public void setYxzt(String yxzt) {
        this.yxzt = yxzt;
    }
    public String getZcbh() {
        return this.zcbh;
    }
    public void setZcbh(String zcbh) {
        this.zcbh = zcbh;
    }
    public String getTb() {
        return this.tb;
    }
    public void setTb(String tb) {
        this.tb = tb;
    }
    public String getSbsx() {
        return this.sbsx;
    }
    public void setSbsx(String sbsx) {
        this.sbsx = sbsx;
    }
    public String getYcsblx() {
        return this.ycsblx;
    }
    public void setYcsblx(String ycsblx) {
        this.ycsblx = ycsblx;
    }
    public String getDsfs() {
        return this.dsfs;
    }
    public void setDsfs(String dsfs) {
        this.dsfs = dsfs;
    }
    public String getGddy() {
        return this.gddy;
    }
    public void setGddy(String gddy) {
        this.gddy = gddy;
    }
    public String getZzsx() {
        return this.zzsx;
    }
    public void setZzsx(String zzsx) {
        this.zzsx = zzsx;
    }
    public String getFsgxsl() {
        return this.fsgxsl;
    }
    public void setFsgxsl(String fsgxsl) {
        this.fsgxsl = fsgxsl;
    }
    public String getGxjkms() {
        return this.gxjkms;
    }
    public void setGxjkms(String gxjkms) {
        this.gxjkms = gxjkms;
    }
    public String getHbdygn() {
        return this.hbdygn;
    }
    public void setHbdygn(String hbdygn) {
        this.hbdygn = hbdygn;
    }
    public String getHgqlx() {
        return this.hgqlx;
    }
    public void setHgqlx(String hgqlx) {
        this.hgqlx = hgqlx;
    }
    public String getZnzdgn() {
        return this.znzdgn;
    }
    public void setZnzdgn(String znzdgn) {
        this.znzdgn = znzdgn;
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
    public String getSfrstp() {
        return this.sfrstp;
    }
    public void setSfrstp(String sfrstp) {
        this.sfrstp = sfrstp;
    }
    public String getSfds() {
        return this.sfds;
    }
    public void setSfds(String sfds) {
        this.sfds = sfds;
    }
    public String getSfzb() {
        return this.sfzb;
    }
    public void setSfzb(String sfzb) {
        this.sfzb = sfzb;
    }
    public String getSfsnmp() {
        return this.sfsnmp;
    }
    public void setSfsnmp(String sfsnmp) {
        this.sfsnmp = sfsnmp;
    }
    public String getSfiec() {
        return this.sfiec;
    }
    public void setSfiec(String sfiec) {
        this.sfiec = sfiec;
    }
    public String getJhjlx() {
        return this.jhjlx;
    }
    public void setJhjlx(String jhjlx) {
        this.jhjlx = jhjlx;
    }
    public String getBhlx() {
        return this.bhlx;
    }
    public void setBhlx(String bhlx) {
        this.bhlx = bhlx;
    }
    public String getBhfl() {
        return this.bhfl;
    }
    public void setBhfl(String bhfl) {
        this.bhfl = bhfl;
    }
    public String getCzsx() {
        return this.czsx;
    }
    public void setCzsx(String czsx) {
        this.czsx = czsx;
    }
    public String getSb() {
        return this.sb;
    }
    public void setSb(String sb) {
        this.sb = sb;
    }
    public String getCzsj() {
        return this.czsj;
    }
    public void setCzsj(String czsj) {
        this.czsj = czsj;
    }
    public String getUsegddata() {
        return this.usegddata;
    }
    public void setUsegddata(String usegddata) {
        this.usegddata = usegddata;
    }
    public String getGldw_gd() {
        return this.gldw_gd;
    }
    public void setGldw_gd(String gldw_gd) {
        this.gldw_gd = gldw_gd;
    }
    public String getDddw_gd() {
        return this.dddw_gd;
    }
    public void setDddw_gd(String dddw_gd) {
        this.dddw_gd = dddw_gd;
    }
    public String getEd_tag() {
        return this.ed_tag;
    }
    public void setEd_tag(String ed_tag) {
        this.ed_tag = ed_tag;
    }
    public String getSw_id() {
        return this.sw_id;
    }
    public void setSw_id(String sw_id) {
        this.sw_id = sw_id;
    }
    public String getJy() {
        return this.jy;
    }
    public void setJy(String jy) {
        this.jy = jy;
    }
}
