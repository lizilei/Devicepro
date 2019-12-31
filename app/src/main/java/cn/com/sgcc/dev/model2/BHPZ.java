package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

@Entity(nameInDb = "BHPZ", createInDb = false)
public class BHPZ implements Serializable {
    private static final long serialVersionUID = 1L;

    // @ExcelField(name = "ID", next = "shsj")
    // @ZdhzColumn(columnName = "ID")
    @Id
    @Unique
    @Property(nameInDb = "ID")
    private Long id;         // 保护配置标识
    // @ZdhzColumn(columnName = "BH")
    // @ExcelField(name = "编号", next = "dddw")
    @Property(nameInDb = "BH")
    private String bh;         // 编号
    // @ZdhzColumn(columnName = "SBDW")
    @Property(nameInDb = "SBDW")
    private String sbdw;
    // @ZdhzColumn(columnName = "DDDW")
    // @ExcelField(name = "调度单位", next = "ycsblx")
    @Property(nameInDb = "DDDW")
    private String dddw;       // 调度单位
    // @ZdhzColumn(columnName = "YCSBLX")
    // @ExcelField(name = "一次设备类型", next = "czmc")
    @Property(nameInDb = "YCSBLX")
    private String ycsblx;     // 一次设备类型
    // @ZdhzColumn(columnName = "CZMC")
    // @ExcelField(name = "厂站名称", next = "czsx")
    @Property(nameInDb = "CZMC")
    private String czmc;       // 厂站名称
    // @ZdhzColumn(columnName = "YCSBMC")
    // @ExcelField(name = "一次设备名称", next = "dydj")
    @Property(nameInDb = "YCSBMC")
    private String ycsbmc;     // 一次设备名称
    // @ZdhzColumn(columnName = "DYDJ")
    // @ExcelField(name = "一次设备电压等级", next = "zzcj")
    @Property(nameInDb = "DYDJ")
    private long dydj;       // 一次设备电压等级
    // @ZdhzColumn(columnName = "ZZCJ")
    // @ExcelField(name = "制造厂家", next = "bhlb")
    @Property(nameInDb = "ZZCJ")
    private String zzcj;       // 制造厂家
    @Property(nameInDb = "ZZCJ2")
    private String zzcj2;       // 制造厂家2
    // @ZdhzColumn(columnName = "BHLB")
    // @ExcelField(name = "保护类别", next = "bhxh")
    @Property(nameInDb = "BHLB")
    private String bhlb;       // 保护类别
    // @ZdhzColumn(columnName = "BHXH")
    // @ExcelField(name = "保护型号", next = "bhlx")
    @Property(nameInDb = "BHXH")
    private String bhxh;       // 保护型号
    @Property(nameInDb = "BHXH2")
    private String bhxh2;       // 保护型号2
    // @ZdhzColumn(columnName = "BHLX")
    // @ExcelField(name = "保护类型", next = "bhfl")
    @Property(nameInDb = "BHLX")
    private String bhlx;       // 保护类型
    // //@ExcelField(name = "保护分类", next = "tdxxStr")
    // @ZdhzColumn(columnName = "BHFL")
    // @ExcelField(name = "保护分类", next = "scdqjylx")
    @Property(nameInDb = "BHFL")
    private String bhfl;       // 保护分类
    // @ZdhzColumn(columnName = "SCDQJYLX")
    // @ExcelField(name = "上次检验类型", next = "bz")
    @Property(nameInDb = "SCDQJYLX")
    private String scdqjylx;          // 上次检验类型
    @Property(nameInDb = "TDLX")
    private String tdlx;          // 通道类型
    // @ZdhzColumn(columnName = "BZ")
    // @ExcelField(name = "备注", next = "rjbb")
    @Property(nameInDb = "BZ")
    private String bz;         // 备注
    // @ZdhzColumn(columnName = "RJBB")
    // @ExcelField(name = "软件版本", next = "usegddata")
    @Property(nameInDb = "RJBB")
    private String rjbb;       // 软件版本及校验码
    // @ZdhzColumn(columnName = "TYRQ")
    // @ExcelField(name = "投运日期", next = "dqjyzq")
    @Property(nameInDb = "TYRQ")
    private String tyrq;              // 投运日期 年-月-日
    // @ZdhzColumn(columnName = "DQJYZQ")
    // @ExcelField(name = "定期检验周期", next = "yxdw")
    @Property(nameInDb = "DQJYZQ")
    private float dqjyzq;            // 定期检验周期
    // @ZdhzColumn(columnName = "YXDW")
    // @ExcelField(name = "运行单位", next = "whdw")
    @Property(nameInDb = "YXDW")
    private String yxdw;              // 运行单位
    // @ZdhzColumn(columnName = "WHDW")
    // @ExcelField(name = "维护单位", next = "sjdw")
    @Property(nameInDb = "WHDW")
    private String whdw;              // 维护单位
    // @ZdhzColumn(columnName = "SJDW")
    // @ExcelField(name = "设计单位", next = "jjdw")
    @Property(nameInDb = "SJDW")
    private String sjdw;              // 设计单位
    // @ZdhzColumn(columnName = "JJDW")
    // @ExcelField(name = "基建单位", next = "xlcd")
    @Property(nameInDb = "JJDW")
    private String jjdw;              // 基建单位
    // @ZdhzColumn(columnName = "XLCD")
    // @ExcelField(name = "线路长度", next = "mxts")
    @Property(nameInDb = "XLCD")
    private float xlcd;              // 线路长度
    // @ZdhzColumn(columnName = "MXTS")
    // @ExcelField(name = "母线条数", next = "sftcyx")
    @Property(nameInDb = "MXTS")
    private long mxts;              // 母线条数
    // @ZdhzColumn(columnName = "SFTCYX")
    // @ExcelField(name = "是否退出运行", next = "tcyxsj")
    @Property(nameInDb = "SFTCYX")
    private String sftcyx;            // 是否退出运行
    // @ZdhzColumn(columnName = "TCYXSJ")
    // @ExcelField(name = "退出运行时间", next = "tcyxid")
    @Property(nameInDb = "TCYXSJ")
    private String tcyxsj;            // 退出运行时间 年-月-日
    // @ZdhzColumn(columnName = "CZGLDW")
    // @ExcelField(name = "厂站管理单位", next = "czzgdydj")
    @Property(nameInDb = "CZGLDW")
    private String czgldw;            // 厂站运行单位
    // @ZdhzColumn(columnName = "CZZGDYDJ")
    // @ExcelField(name = "厂站最高电压等级", next = "bhmc")
    @Property(nameInDb = "CZZGDYDJ")
    private long czzgdydj;          // 厂站最高电压等级
    // @ZdhzColumn(columnName = "BHMC")
    // @ExcelField(name = "保护名称", next = "ccrq")
    @Property(nameInDb = "BHMC")
    private String bhmc;       // 保护名称
    // @ZdhzColumn(columnName = "CCRQ")
    // @ExcelField(name = "出厂日期", next = "xcdqjylx")
    @Property(nameInDb = "CCRQ")
    private String ccrq;       // 出厂日期 年-月-日
    // @ZdhzColumn(columnName = "XCDQJYLX")
    // @ExcelField(name = "下次检验类型", next = "xcdqjysj")
    @Property(nameInDb = "XCDQJYLX")
    private String xcdqjylx;          // 下次检验类型
    @Property(nameInDb = "BCDQJYRQ")
    private String bcdqjyrq;          //
    @Property(nameInDb = "XCDQJYRQ")
    private String xcdqjyrq;          //
    @Property(nameInDb = "VF")
    private int vf;         // 原始数据导入标识
    // @ZdhzColumn(columnName = "SCDQJYSJ", type = ExcelFieldType.TIMESTAMP)
    // @ExcelField(name = "上次检验时间", next = "scdqjydw")
    @Property(nameInDb = "SCDQJYSJ")
    private String scdqjysj;          // 上次检验时间
    @Property(nameInDb = "SB")
    private String sb;
    @Property(nameInDb = "DDMM")
    private String ddmm;
    // @ZdhzColumn(columnName = "SCDQJYDW")
    // @ExcelField(name = "上次检验单位", next = "czr")
    @Property(nameInDb = "SCDQJYDW")
    private String scdqjydw;          // 下次检验单位
    // @ZdhzColumn(columnName = "CZR")
    // @ExcelField(name = "操作人", next = "bhsx", must = false)
    @Property(nameInDb = "CZR")
    private String czr;               // 操作人
    @Property(nameInDb = "WDID")
    private Long WDID;
    @Property(nameInDb = "CZ")
    private String cz;  //操作
    // @ZdhzColumn(columnName = "BHSX")
    // @ExcelField(name = "设备属性", next = "sh")
    @Property(nameInDb = "BHSX")
    private String bhsx;              // 保护属性
    @Property(nameInDb = "SH")
    private String sh;              //审核
    @Property(nameInDb = "SHR")
    private String shr;              //审核人
    @Property(nameInDb = "SHYY")
    private String shyy;              //审核原因
    // @ZdhzColumn(columnName = "TB")
    // @ExcelField(name = "保护套别", next = "bhmcsx")
    @Property(nameInDb = "TB")
    private String tb;                // 套别
    // @ZdhzColumn(columnName = "BHMCSX")
    // @ExcelField(name = "名称属性", next = "sftjyxsj")
    @Property(nameInDb = "BHMCSX")
    private String bhmcsx;            // 保护名称属性
    // @ZdhzColumn(columnName = "SFTJYXSJ")
    // @ExcelField(name = "是否统计运行时间", next = "zleddy")
    @Property(nameInDb = "SFTJYXSJ")
    private String sftjyxsj;          // 是否统计运行时间
    // @ZdhzColumn(columnName = "TCYXID")
    // @ExcelField(name = "关联的退出运行设备标识", next = "czgldw")
    @Property(nameInDb = "TCYXID")
    private String tcyxid;    //关联的退出运行设备标识
    // @ZdhzColumn(columnName = "ZLEDDY")
    // @ExcelField(name = "直流额定电压", next = "zcxz")
    @Property(nameInDb = "ZLEDDY")
    private long zleddy;     // 一次设备电压等级
    @Property(nameInDb = "TJR")
    private String tjr;     //
    @Property(nameInDb = "DWTJR")
    private String dwtjr;     //
    // @ZdhzColumn(columnName = "ZCXZ")
    // @ExcelField(name = "资产性质", next = "kgbh")
    @Property(nameInDb = "ZCXZ")
    private String zcxz;              // 资产性质
    // //@ExcelField(name = "跳闸关系", next = "")
    // @ZdhzColumn(columnName = "KGBH")
    // @ExcelField(name = "跳闸关系", next = "dycjghrq")
    @Property(nameInDb = "KGBH")
    private String kgbh;              // 开关编号
    // @ZdhzColumn(columnName = "DYCJGHRQ")
    // @ExcelField(name = "电源插件更换日期", next = "dycjxh", must = false)
    @Property(nameInDb = "DYCJGHRQ")
    private String dycjghrq;          // 出厂日期 年-月-日
    @Property(nameInDb = "RJXD")
    private String rjxd;              // 开关编号
    // @ZdhzColumn(columnName = "DYCJXH")
    // @ExcelField(name = "电源插件型号", next = "tyrq2")
    @Property(nameInDb = "DYCJXH")
    private String dycjxh;
    // @ZdhzColumn(columnName = "TYRQ2")
    // @ExcelField(name = "新投运日期", next = "bhlbxh")
    @Property(nameInDb = "TYRQ2")
    private String tyrq2;             // 投运日期 年-月-日
    // @ZdhzColumn(columnName = "BHLBXH")
    // @ExcelField(name = "保护类别细化", next = "jgmc")
    @Property(nameInDb = "BHLBXH")
    private String bhlbxh;
    @Property(nameInDb = "TDLXXH")
    private String tdlxxh;
    // @ZdhzColumn(columnName = "JGMC")
    // @ExcelField(name = "间隔名称", next = "jglx")
    @Property(nameInDb = "JGMC")
    private String jgmc;       // 间隔名称
    // @ZdhzColumn(columnName = "JGLX")
    // @ExcelField(name = "间隔类型", next = "szpg")
    @Property(nameInDb = "JGLX")
    private String jglx;       // 间隔类型
    // @ZdhzColumn(columnName = "XCDQJYSJ", type = ExcelFieldType.TIMESTAMP)
    // @ExcelField(name = "下次检验时间", next = "scdqjysj")
    @Property(nameInDb = "XCDQJYSJ")
    private String xcdqjysj;          // 下次检验时间
    // @ZdhzColumn(columnName = "SZPG")
    // @ExcelField(name = "所在屏柜", next = "zcdw")
    @Property(nameInDb = "SZPG")
    private String szpg;              // 所在屏柜
    // @ZdhzColumn(columnName = "ZCDW")
    // @ExcelField(name = "资产单位", next = "sjbb")
    @Property(nameInDb = "ZCDW")
    private String zcdw;              // 资产单位
    @Property(nameInDb = "JGID")
    private String jgid;              //
    @Property(nameInDb = "BDZID")
    private String bdzid;              //
    // @ZdhzColumn(columnName = "SJBB")
    // @ExcelField(name = "实际变比", next = "edbb")
    @Property(nameInDb = "SJBB")
    private String sjbb;
    // @ZdhzColumn(columnName = "EDBB")
    // @ExcelField(name = "额定变比", next = "zqj")
    @Property(nameInDb = "EDBB")
    private String edbb;
    // @ZdhzColumn(columnName = "ZQJ")
    // @ExcelField(name = "准确级", next = "cteceddl")
    @Property(nameInDb = "ZQJ")
    private String zqj;               //
    @Property(nameInDb = "CZSJ")
    private String czsj;               //
    @Property(nameInDb = "CZPMSID")
    private String czpmsid;               //
    @Property(nameInDb = "JGPMSID")
    private String jgpmsid;               //
    // @ZdhzColumn(columnName = "SB_BHPZ_ID")
    @Property(nameInDb = "SB_BHPZ_ID")
    private String sb_bhpz_id;
    // @ZdhzColumn(columnName = "CTECEDDL")
    // @ExcelField(name = "CT二次额定电流", next = "yxzt")
    @Property(nameInDb = "CTECEDDL")
    private String cteceddl;
    // @ZdhzColumn(columnName = "YXZT")
    // @ExcelField(name = "运行状态", next = "zcbh")
    @Property(nameInDb = "YXZT")
    private String yxzt;
    // @ZdhzColumn(columnName = "ZCBH")
    // @ExcelField(name = "资产编号", next = "ccbh")
    @Property(nameInDb = "ZCBH")
    private String zcbh;
    // @ZdhzColumn(columnName = "CCBH")
    // @ExcelField(name = "出厂编号", next = "mntds")
    @Property(nameInDb = "CCBH")
    private String ccbh;
    // @ZdhzColumn(columnName = "MNTDS")
    // @ExcelField(name = "模拟通道数", next = "sztds")
    @Property(nameInDb = "MNTDS")
    private String mntds;
    // @ZdhzColumn(columnName = "SZTDS")
    // @ExcelField(name = "数字通道数", next = "sbgnpz")
    @Property(nameInDb = "SZTDS")
    private String sztds;
    // @ZdhzColumn(columnName = "SBGNPZ")
    // @ExcelField(name = "设备功能配置", next = "akzdm")
    @Property(nameInDb = "SBGNPZ")
    private String sbgnpz;
    // @ZdhzColumn(columnName = "AKZDM")
    // @ExcelField(name = "安控站点名", next = "akzdlx")
    @Property(nameInDb = "AKZDM")
    private String akzdm;
    // @ZdhzColumn(columnName = "AKZDLX")
    // @ExcelField(name = "安控站点类型", next = "ssakxtddmm")
    @Property(nameInDb = "AKZDLX")
    private String akzdlx;
    // @ZdhzColumn(columnName = "SSAKXTDDMM")
    // @ExcelField(name = "所属安控系统调度命名", next = "bdzlx")
    @Property(nameInDb = "SSAKXTDDMM")
    private String ssakxtddmm;
    // @ZdhzColumn(columnName = "BDZLX")
    // @ExcelField(name = "厂站类型", next = "cjxx")
    @Property(nameInDb = "BDZLX")
    private String bdzlx;             // 厂站类型
    // @ZdhzColumn(columnName = "CJXX")
    // @ExcelField(name = "测距形式", next = "gzlbqlx")
    @Property(nameInDb = "CJXX")
    private String cjxx;              // 测距形式
    // @ZdhzColumn(columnName = "GZLBQLX")
    // @ExcelField(name = "故障录波器类型", next = "sfjrzz")
    @Property(nameInDb = "GZLBQLX")
    private String gzlbqlx;           // 故障录波器类型
    // @ZdhzColumn(columnName = "SFWCXXJR")
    // @ExcelField(name = "信息是否接入调度主站", next = "ckfs")
    @Property(nameInDb = "SFWCXXJR")
    private String sfjrzz;            // 信息是否接入调度主站
    // @ZdhzColumn(columnName = "CKFS")
    // @ExcelField(name = "出口方式", next = "sjcjfs")
    @Property(nameInDb = "CKFS")
    private String ckfs;              // 出口方式
    // @ZdhzColumn(columnName = "SJCJFS")
    // @ExcelField(name = "数据采集方式", next = "sbxs")
    @Property(nameInDb = "SJCJFS")
    private String sjcjfs;            // 数据采集方式
    // @ZdhzColumn(columnName = "SBXS")
    // @ExcelField(name = "设备型式", next = "sfltysb", must = false)
    @Property(nameInDb = "SBXS")
    private String sbxs;              // 设备型式
    // @ZdhzColumn(columnName = "SFLTYSB")
    // @ExcelField(name = "是否六统一设备", next = "ltybzbb")
    @Property(nameInDb = "SFLTYSB")
    private String sfltysb;           // 是否六统一设备
    // @ZdhzColumn(columnName = "LTYBZBB")
    // @ExcelField(name = "六统一标准版本", next = "sfsbm")
    @Property(nameInDb = "LTYBZBB")
    private String ltybzbb;           // 六统一标准版本
    // @ZdhzColumn(columnName = "SFSBM")
    @Property(nameInDb = "SFSBM")
    // @ExcelField(name = "身份识别码", next = "validateformat")
    private String sfsbm;             // 身份识别码
    @Property(nameInDb = "SBSJ")
    private String sbsj;           //
    // @ZdhzColumn(columnName = "USEGDDATA")
    // @ExcelField(name = "是否使用国调下发型号", next = "xp", must = false)
    @Property(nameInDb = "USEGDDATA")
    private String usegddata = "true";// 是否使用国调标准型号
    @Property(nameInDb = "SFBDSJ")
    private String sfbdsj;//
    @Property(nameInDb = "BHSBXHXX")
    private String bhsbxhxx;//
    @Property(nameInDb = "STATE")
    private String state;//
    @Property(nameInDb = "SB_LS_ID")
    private String sb_lx_id;//
    // @ZdhzColumn(columnName = "CZSX")
    // @ExcelField(name = "厂站属性", next = "ycsbmc", must = false)
    @Property(nameInDb = "CZSX")
    private String czsx;
    // @ZdhzColumn(columnName = "CASEVALIDATE")
    @Property(nameInDb = "CASEVALIDATE")
    private String caseValidate;
    @Property(nameInDb = "HZSJ")
    private String hzsj;
    // @ExcelField(name = "历史核查结果", next = "tdxxStr", must = false)
    // @ZdhzColumn(columnName = "LS_VALIDATEFORMAT")
    @Property(nameInDb = "LS_VALIDATEFORMAT")
    private String ls_validateformat;
    @Property(nameInDb = "SBCZLX")
    private String sbczlx;
    // @ZdhzColumn(columnName = "SFXTLR")
    @Property(nameInDb = "SFXTLR")
    private String sfxtlr;
    @Property(nameInDb = "SHXTLR")
    private String shxtlr;
    // @ExcelField(name = "核查结果", next = "ls_validateformat", must = false)
    // @ZdhzColumn(columnName = "VALIDATEFORMAT")
    @Property(nameInDb = "VALIDATEFORMAT")
    private String validateformat;
    @Property(nameInDb = "WJ_DW")
    private String wj_dw;
    @Property(nameInDb = "WJ_LS_ID")
    private String wj_ls_id;
    @Property(nameInDb = "XP")
    private String xp;
    // @ExcelField(name = "家族型号", must = false, next = "bksl")
    // @ZdhzColumn(columnName = "JZXH")
    @Property(nameInDb = "JZXH")
    private String jzxh;              // 家族型号
    // @ExcelField(name = "板卡数量", must = false, next = "icdwjmc")
    // @ZdhzColumn(columnName = "BKSL")
    @Property(nameInDb = "BKSL")
    private int bksl;              // 板卡数量
    // @ExcelField(name = "ICD文件名称", must = false, next = "tyrq")
    // @ZdhzColumn(columnName = "ICDWJMC")
    @Property(nameInDb = "ICDWJMC")
    private String icdwjmc;
    // @ZdhzColumn(columnName = "CZGLDW_GD")
    @Property(nameInDb = "CZGLDW_GD")
    private String czgldw_gd;
    // @ZdhzColumn(columnName = "DDDW_GD")
    @Property(nameInDb = "DDDW_GD")
    private String dddw_gd;
    // @ZdhzColumn(columnName = "SBDW_GD")
    @Property(nameInDb = "SBDW_GD")
    private String sbdw_gd;
    @Property(nameInDb = "SHSJ")
    private String shsj;
    @Property(nameInDb = "SFSCH")
    private String sfsch;
    @Property(nameInDb = "ED_TAG")
    private String ed_tag;  //装置处理状态
    @Property(nameInDb = "LJQSL")
    private String ljqsl;  //连接器数量
    @Property(nameInDb = "LJQZZCJ")
    private String ljqzzcj;  //连接器装置厂家
    @Property(nameInDb = "LJQXH")
    private String ljqxh;  //连接器型号
    @Property(nameInDb = "SFJDHZZ")
    private String sfjdhzz;  //是否就地化装置
    @Property(nameInDb = "SWID")
    private String sw_id;  //实物id
    @Property(nameInDb = "JY")
    private String jy;  //装置校验标识
    @Generated(hash = 1354248545)
    public BHPZ(Long id, String bh, String sbdw, String dddw, String ycsblx,
            String czmc, String ycsbmc, long dydj, String zzcj, String zzcj2,
            String bhlb, String bhxh, String bhxh2, String bhlx, String bhfl,
            String scdqjylx, String tdlx, String bz, String rjbb, String tyrq,
            float dqjyzq, String yxdw, String whdw, String sjdw, String jjdw,
            float xlcd, long mxts, String sftcyx, String tcyxsj, String czgldw,
            long czzgdydj, String bhmc, String ccrq, String xcdqjylx,
            String bcdqjyrq, String xcdqjyrq, int vf, String scdqjysj, String sb,
            String ddmm, String scdqjydw, String czr, Long WDID, String cz,
            String bhsx, String sh, String shr, String shyy, String tb,
            String bhmcsx, String sftjyxsj, String tcyxid, long zleddy, String tjr,
            String dwtjr, String zcxz, String kgbh, String dycjghrq, String rjxd,
            String dycjxh, String tyrq2, String bhlbxh, String tdlxxh, String jgmc,
            String jglx, String xcdqjysj, String szpg, String zcdw, String jgid,
            String bdzid, String sjbb, String edbb, String zqj, String czsj,
            String czpmsid, String jgpmsid, String sb_bhpz_id, String cteceddl,
            String yxzt, String zcbh, String ccbh, String mntds, String sztds,
            String sbgnpz, String akzdm, String akzdlx, String ssakxtddmm,
            String bdzlx, String cjxx, String gzlbqlx, String sfjrzz, String ckfs,
            String sjcjfs, String sbxs, String sfltysb, String ltybzbb,
            String sfsbm, String sbsj, String usegddata, String sfbdsj,
            String bhsbxhxx, String state, String sb_lx_id, String czsx,
            String caseValidate, String hzsj, String ls_validateformat,
            String sbczlx, String sfxtlr, String shxtlr, String validateformat,
            String wj_dw, String wj_ls_id, String xp, String jzxh, int bksl,
            String icdwjmc, String czgldw_gd, String dddw_gd, String sbdw_gd,
            String shsj, String sfsch, String ed_tag, String ljqsl, String ljqzzcj,
            String ljqxh, String sfjdhzz, String sw_id, String jy) {
        this.id = id;
        this.bh = bh;
        this.sbdw = sbdw;
        this.dddw = dddw;
        this.ycsblx = ycsblx;
        this.czmc = czmc;
        this.ycsbmc = ycsbmc;
        this.dydj = dydj;
        this.zzcj = zzcj;
        this.zzcj2 = zzcj2;
        this.bhlb = bhlb;
        this.bhxh = bhxh;
        this.bhxh2 = bhxh2;
        this.bhlx = bhlx;
        this.bhfl = bhfl;
        this.scdqjylx = scdqjylx;
        this.tdlx = tdlx;
        this.bz = bz;
        this.rjbb = rjbb;
        this.tyrq = tyrq;
        this.dqjyzq = dqjyzq;
        this.yxdw = yxdw;
        this.whdw = whdw;
        this.sjdw = sjdw;
        this.jjdw = jjdw;
        this.xlcd = xlcd;
        this.mxts = mxts;
        this.sftcyx = sftcyx;
        this.tcyxsj = tcyxsj;
        this.czgldw = czgldw;
        this.czzgdydj = czzgdydj;
        this.bhmc = bhmc;
        this.ccrq = ccrq;
        this.xcdqjylx = xcdqjylx;
        this.bcdqjyrq = bcdqjyrq;
        this.xcdqjyrq = xcdqjyrq;
        this.vf = vf;
        this.scdqjysj = scdqjysj;
        this.sb = sb;
        this.ddmm = ddmm;
        this.scdqjydw = scdqjydw;
        this.czr = czr;
        this.WDID = WDID;
        this.cz = cz;
        this.bhsx = bhsx;
        this.sh = sh;
        this.shr = shr;
        this.shyy = shyy;
        this.tb = tb;
        this.bhmcsx = bhmcsx;
        this.sftjyxsj = sftjyxsj;
        this.tcyxid = tcyxid;
        this.zleddy = zleddy;
        this.tjr = tjr;
        this.dwtjr = dwtjr;
        this.zcxz = zcxz;
        this.kgbh = kgbh;
        this.dycjghrq = dycjghrq;
        this.rjxd = rjxd;
        this.dycjxh = dycjxh;
        this.tyrq2 = tyrq2;
        this.bhlbxh = bhlbxh;
        this.tdlxxh = tdlxxh;
        this.jgmc = jgmc;
        this.jglx = jglx;
        this.xcdqjysj = xcdqjysj;
        this.szpg = szpg;
        this.zcdw = zcdw;
        this.jgid = jgid;
        this.bdzid = bdzid;
        this.sjbb = sjbb;
        this.edbb = edbb;
        this.zqj = zqj;
        this.czsj = czsj;
        this.czpmsid = czpmsid;
        this.jgpmsid = jgpmsid;
        this.sb_bhpz_id = sb_bhpz_id;
        this.cteceddl = cteceddl;
        this.yxzt = yxzt;
        this.zcbh = zcbh;
        this.ccbh = ccbh;
        this.mntds = mntds;
        this.sztds = sztds;
        this.sbgnpz = sbgnpz;
        this.akzdm = akzdm;
        this.akzdlx = akzdlx;
        this.ssakxtddmm = ssakxtddmm;
        this.bdzlx = bdzlx;
        this.cjxx = cjxx;
        this.gzlbqlx = gzlbqlx;
        this.sfjrzz = sfjrzz;
        this.ckfs = ckfs;
        this.sjcjfs = sjcjfs;
        this.sbxs = sbxs;
        this.sfltysb = sfltysb;
        this.ltybzbb = ltybzbb;
        this.sfsbm = sfsbm;
        this.sbsj = sbsj;
        this.usegddata = usegddata;
        this.sfbdsj = sfbdsj;
        this.bhsbxhxx = bhsbxhxx;
        this.state = state;
        this.sb_lx_id = sb_lx_id;
        this.czsx = czsx;
        this.caseValidate = caseValidate;
        this.hzsj = hzsj;
        this.ls_validateformat = ls_validateformat;
        this.sbczlx = sbczlx;
        this.sfxtlr = sfxtlr;
        this.shxtlr = shxtlr;
        this.validateformat = validateformat;
        this.wj_dw = wj_dw;
        this.wj_ls_id = wj_ls_id;
        this.xp = xp;
        this.jzxh = jzxh;
        this.bksl = bksl;
        this.icdwjmc = icdwjmc;
        this.czgldw_gd = czgldw_gd;
        this.dddw_gd = dddw_gd;
        this.sbdw_gd = sbdw_gd;
        this.shsj = shsj;
        this.sfsch = sfsch;
        this.ed_tag = ed_tag;
        this.ljqsl = ljqsl;
        this.ljqzzcj = ljqzzcj;
        this.ljqxh = ljqxh;
        this.sfjdhzz = sfjdhzz;
        this.sw_id = sw_id;
        this.jy = jy;
    }
    @Generated(hash = 1232540019)
    public BHPZ() {
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
    public String getSbdw() {
        return this.sbdw;
    }
    public void setSbdw(String sbdw) {
        this.sbdw = sbdw;
    }
    public String getDddw() {
        return this.dddw;
    }
    public void setDddw(String dddw) {
        this.dddw = dddw;
    }
    public String getYcsblx() {
        return this.ycsblx;
    }
    public void setYcsblx(String ycsblx) {
        this.ycsblx = ycsblx;
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
    public long getDydj() {
        return this.dydj;
    }
    public void setDydj(long dydj) {
        this.dydj = dydj;
    }
    public String getZzcj() {
        return this.zzcj;
    }
    public void setZzcj(String zzcj) {
        this.zzcj = zzcj;
    }
    public String getZzcj2() {
        return this.zzcj2;
    }
    public void setZzcj2(String zzcj2) {
        this.zzcj2 = zzcj2;
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
    public String getBhxh2() {
        return this.bhxh2;
    }
    public void setBhxh2(String bhxh2) {
        this.bhxh2 = bhxh2;
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
    public String getScdqjylx() {
        return this.scdqjylx;
    }
    public void setScdqjylx(String scdqjylx) {
        this.scdqjylx = scdqjylx;
    }
    public String getTdlx() {
        return this.tdlx;
    }
    public void setTdlx(String tdlx) {
        this.tdlx = tdlx;
    }
    public String getBz() {
        return this.bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
    public String getRjbb() {
        return this.rjbb;
    }
    public void setRjbb(String rjbb) {
        this.rjbb = rjbb;
    }
    public String getTyrq() {
        return this.tyrq;
    }
    public void setTyrq(String tyrq) {
        this.tyrq = tyrq;
    }
    public float getDqjyzq() {
        return this.dqjyzq;
    }
    public void setDqjyzq(float dqjyzq) {
        this.dqjyzq = dqjyzq;
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
    public float getXlcd() {
        return this.xlcd;
    }
    public void setXlcd(float xlcd) {
        this.xlcd = xlcd;
    }
    public long getMxts() {
        return this.mxts;
    }
    public void setMxts(long mxts) {
        this.mxts = mxts;
    }
    public String getSftcyx() {
        return this.sftcyx;
    }
    public void setSftcyx(String sftcyx) {
        this.sftcyx = sftcyx;
    }
    public String getTcyxsj() {
        return this.tcyxsj;
    }
    public void setTcyxsj(String tcyxsj) {
        this.tcyxsj = tcyxsj;
    }
    public String getCzgldw() {
        return this.czgldw;
    }
    public void setCzgldw(String czgldw) {
        this.czgldw = czgldw;
    }
    public long getCzzgdydj() {
        return this.czzgdydj;
    }
    public void setCzzgdydj(long czzgdydj) {
        this.czzgdydj = czzgdydj;
    }
    public String getBhmc() {
        return this.bhmc;
    }
    public void setBhmc(String bhmc) {
        this.bhmc = bhmc;
    }
    public String getCcrq() {
        return this.ccrq;
    }
    public void setCcrq(String ccrq) {
        this.ccrq = ccrq;
    }
    public String getXcdqjylx() {
        return this.xcdqjylx;
    }
    public void setXcdqjylx(String xcdqjylx) {
        this.xcdqjylx = xcdqjylx;
    }
    public String getBcdqjyrq() {
        return this.bcdqjyrq;
    }
    public void setBcdqjyrq(String bcdqjyrq) {
        this.bcdqjyrq = bcdqjyrq;
    }
    public String getXcdqjyrq() {
        return this.xcdqjyrq;
    }
    public void setXcdqjyrq(String xcdqjyrq) {
        this.xcdqjyrq = xcdqjyrq;
    }
    public int getVf() {
        return this.vf;
    }
    public void setVf(int vf) {
        this.vf = vf;
    }
    public String getScdqjysj() {
        return this.scdqjysj;
    }
    public void setScdqjysj(String scdqjysj) {
        this.scdqjysj = scdqjysj;
    }
    public String getSb() {
        return this.sb;
    }
    public void setSb(String sb) {
        this.sb = sb;
    }
    public String getDdmm() {
        return this.ddmm;
    }
    public void setDdmm(String ddmm) {
        this.ddmm = ddmm;
    }
    public String getScdqjydw() {
        return this.scdqjydw;
    }
    public void setScdqjydw(String scdqjydw) {
        this.scdqjydw = scdqjydw;
    }
    public String getCzr() {
        return this.czr;
    }
    public void setCzr(String czr) {
        this.czr = czr;
    }
    public Long getWDID() {
        return this.WDID;
    }
    public void setWDID(Long WDID) {
        this.WDID = WDID;
    }
    public String getCz() {
        return this.cz;
    }
    public void setCz(String cz) {
        this.cz = cz;
    }
    public String getBhsx() {
        return this.bhsx;
    }
    public void setBhsx(String bhsx) {
        this.bhsx = bhsx;
    }
    public String getSh() {
        return this.sh;
    }
    public void setSh(String sh) {
        this.sh = sh;
    }
    public String getShr() {
        return this.shr;
    }
    public void setShr(String shr) {
        this.shr = shr;
    }
    public String getShyy() {
        return this.shyy;
    }
    public void setShyy(String shyy) {
        this.shyy = shyy;
    }
    public String getTb() {
        return this.tb;
    }
    public void setTb(String tb) {
        this.tb = tb;
    }
    public String getBhmcsx() {
        return this.bhmcsx;
    }
    public void setBhmcsx(String bhmcsx) {
        this.bhmcsx = bhmcsx;
    }
    public String getSftjyxsj() {
        return this.sftjyxsj;
    }
    public void setSftjyxsj(String sftjyxsj) {
        this.sftjyxsj = sftjyxsj;
    }
    public String getTcyxid() {
        return this.tcyxid;
    }
    public void setTcyxid(String tcyxid) {
        this.tcyxid = tcyxid;
    }
    public long getZleddy() {
        return this.zleddy;
    }
    public void setZleddy(long zleddy) {
        this.zleddy = zleddy;
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
    public String getZcxz() {
        return this.zcxz;
    }
    public void setZcxz(String zcxz) {
        this.zcxz = zcxz;
    }
    public String getKgbh() {
        return this.kgbh;
    }
    public void setKgbh(String kgbh) {
        this.kgbh = kgbh;
    }
    public String getDycjghrq() {
        return this.dycjghrq;
    }
    public void setDycjghrq(String dycjghrq) {
        this.dycjghrq = dycjghrq;
    }
    public String getRjxd() {
        return this.rjxd;
    }
    public void setRjxd(String rjxd) {
        this.rjxd = rjxd;
    }
    public String getDycjxh() {
        return this.dycjxh;
    }
    public void setDycjxh(String dycjxh) {
        this.dycjxh = dycjxh;
    }
    public String getTyrq2() {
        return this.tyrq2;
    }
    public void setTyrq2(String tyrq2) {
        this.tyrq2 = tyrq2;
    }
    public String getBhlbxh() {
        return this.bhlbxh;
    }
    public void setBhlbxh(String bhlbxh) {
        this.bhlbxh = bhlbxh;
    }
    public String getTdlxxh() {
        return this.tdlxxh;
    }
    public void setTdlxxh(String tdlxxh) {
        this.tdlxxh = tdlxxh;
    }
    public String getJgmc() {
        return this.jgmc;
    }
    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }
    public String getJglx() {
        return this.jglx;
    }
    public void setJglx(String jglx) {
        this.jglx = jglx;
    }
    public String getXcdqjysj() {
        return this.xcdqjysj;
    }
    public void setXcdqjysj(String xcdqjysj) {
        this.xcdqjysj = xcdqjysj;
    }
    public String getSzpg() {
        return this.szpg;
    }
    public void setSzpg(String szpg) {
        this.szpg = szpg;
    }
    public String getZcdw() {
        return this.zcdw;
    }
    public void setZcdw(String zcdw) {
        this.zcdw = zcdw;
    }
    public String getJgid() {
        return this.jgid;
    }
    public void setJgid(String jgid) {
        this.jgid = jgid;
    }
    public String getBdzid() {
        return this.bdzid;
    }
    public void setBdzid(String bdzid) {
        this.bdzid = bdzid;
    }
    public String getSjbb() {
        return this.sjbb;
    }
    public void setSjbb(String sjbb) {
        this.sjbb = sjbb;
    }
    public String getEdbb() {
        return this.edbb;
    }
    public void setEdbb(String edbb) {
        this.edbb = edbb;
    }
    public String getZqj() {
        return this.zqj;
    }
    public void setZqj(String zqj) {
        this.zqj = zqj;
    }
    public String getCzsj() {
        return this.czsj;
    }
    public void setCzsj(String czsj) {
        this.czsj = czsj;
    }
    public String getCzpmsid() {
        return this.czpmsid;
    }
    public void setCzpmsid(String czpmsid) {
        this.czpmsid = czpmsid;
    }
    public String getJgpmsid() {
        return this.jgpmsid;
    }
    public void setJgpmsid(String jgpmsid) {
        this.jgpmsid = jgpmsid;
    }
    public String getSb_bhpz_id() {
        return this.sb_bhpz_id;
    }
    public void setSb_bhpz_id(String sb_bhpz_id) {
        this.sb_bhpz_id = sb_bhpz_id;
    }
    public String getCteceddl() {
        return this.cteceddl;
    }
    public void setCteceddl(String cteceddl) {
        this.cteceddl = cteceddl;
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
    public String getCcbh() {
        return this.ccbh;
    }
    public void setCcbh(String ccbh) {
        this.ccbh = ccbh;
    }
    public String getMntds() {
        return this.mntds;
    }
    public void setMntds(String mntds) {
        this.mntds = mntds;
    }
    public String getSztds() {
        return this.sztds;
    }
    public void setSztds(String sztds) {
        this.sztds = sztds;
    }
    public String getSbgnpz() {
        return this.sbgnpz;
    }
    public void setSbgnpz(String sbgnpz) {
        this.sbgnpz = sbgnpz;
    }
    public String getAkzdm() {
        return this.akzdm;
    }
    public void setAkzdm(String akzdm) {
        this.akzdm = akzdm;
    }
    public String getAkzdlx() {
        return this.akzdlx;
    }
    public void setAkzdlx(String akzdlx) {
        this.akzdlx = akzdlx;
    }
    public String getSsakxtddmm() {
        return this.ssakxtddmm;
    }
    public void setSsakxtddmm(String ssakxtddmm) {
        this.ssakxtddmm = ssakxtddmm;
    }
    public String getBdzlx() {
        return this.bdzlx;
    }
    public void setBdzlx(String bdzlx) {
        this.bdzlx = bdzlx;
    }
    public String getCjxx() {
        return this.cjxx;
    }
    public void setCjxx(String cjxx) {
        this.cjxx = cjxx;
    }
    public String getGzlbqlx() {
        return this.gzlbqlx;
    }
    public void setGzlbqlx(String gzlbqlx) {
        this.gzlbqlx = gzlbqlx;
    }
    public String getSfjrzz() {
        return this.sfjrzz;
    }
    public void setSfjrzz(String sfjrzz) {
        this.sfjrzz = sfjrzz;
    }
    public String getCkfs() {
        return this.ckfs;
    }
    public void setCkfs(String ckfs) {
        this.ckfs = ckfs;
    }
    public String getSjcjfs() {
        return this.sjcjfs;
    }
    public void setSjcjfs(String sjcjfs) {
        this.sjcjfs = sjcjfs;
    }
    public String getSbxs() {
        return this.sbxs;
    }
    public void setSbxs(String sbxs) {
        this.sbxs = sbxs;
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
    public String getSbsj() {
        return this.sbsj;
    }
    public void setSbsj(String sbsj) {
        this.sbsj = sbsj;
    }
    public String getUsegddata() {
        return this.usegddata;
    }
    public void setUsegddata(String usegddata) {
        this.usegddata = usegddata;
    }
    public String getSfbdsj() {
        return this.sfbdsj;
    }
    public void setSfbdsj(String sfbdsj) {
        this.sfbdsj = sfbdsj;
    }
    public String getBhsbxhxx() {
        return this.bhsbxhxx;
    }
    public void setBhsbxhxx(String bhsbxhxx) {
        this.bhsbxhxx = bhsbxhxx;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getSb_lx_id() {
        return this.sb_lx_id;
    }
    public void setSb_lx_id(String sb_lx_id) {
        this.sb_lx_id = sb_lx_id;
    }
    public String getCzsx() {
        return this.czsx;
    }
    public void setCzsx(String czsx) {
        this.czsx = czsx;
    }
    public String getCaseValidate() {
        return this.caseValidate;
    }
    public void setCaseValidate(String caseValidate) {
        this.caseValidate = caseValidate;
    }
    public String getHzsj() {
        return this.hzsj;
    }
    public void setHzsj(String hzsj) {
        this.hzsj = hzsj;
    }
    public String getLs_validateformat() {
        return this.ls_validateformat;
    }
    public void setLs_validateformat(String ls_validateformat) {
        this.ls_validateformat = ls_validateformat;
    }
    public String getSbczlx() {
        return this.sbczlx;
    }
    public void setSbczlx(String sbczlx) {
        this.sbczlx = sbczlx;
    }
    public String getSfxtlr() {
        return this.sfxtlr;
    }
    public void setSfxtlr(String sfxtlr) {
        this.sfxtlr = sfxtlr;
    }
    public String getShxtlr() {
        return this.shxtlr;
    }
    public void setShxtlr(String shxtlr) {
        this.shxtlr = shxtlr;
    }
    public String getValidateformat() {
        return this.validateformat;
    }
    public void setValidateformat(String validateformat) {
        this.validateformat = validateformat;
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
    public String getXp() {
        return this.xp;
    }
    public void setXp(String xp) {
        this.xp = xp;
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
    public String getIcdwjmc() {
        return this.icdwjmc;
    }
    public void setIcdwjmc(String icdwjmc) {
        this.icdwjmc = icdwjmc;
    }
    public String getCzgldw_gd() {
        return this.czgldw_gd;
    }
    public void setCzgldw_gd(String czgldw_gd) {
        this.czgldw_gd = czgldw_gd;
    }
    public String getDddw_gd() {
        return this.dddw_gd;
    }
    public void setDddw_gd(String dddw_gd) {
        this.dddw_gd = dddw_gd;
    }
    public String getSbdw_gd() {
        return this.sbdw_gd;
    }
    public void setSbdw_gd(String sbdw_gd) {
        this.sbdw_gd = sbdw_gd;
    }
    public String getShsj() {
        return this.shsj;
    }
    public void setShsj(String shsj) {
        this.shsj = shsj;
    }
    public String getSfsch() {
        return this.sfsch;
    }
    public void setSfsch(String sfsch) {
        this.sfsch = sfsch;
    }
    public String getEd_tag() {
        return this.ed_tag;
    }
    public void setEd_tag(String ed_tag) {
        this.ed_tag = ed_tag;
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
    public String getSfjdhzz() {
        return this.sfjdhzz;
    }
    public void setSfjdhzz(String sfjdhzz) {
        this.sfjdhzz = sfjdhzz;
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
