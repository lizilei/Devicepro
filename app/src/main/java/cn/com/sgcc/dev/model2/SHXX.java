//package cn.com.sgcc.dev.model2;
//
//import org.greenrobot.greendao.annotation.Id;
//import org.greenrobot.greendao.annotation.Property;
//
//import java.util.Date;
//
///**
// * 审核信息基本信息封装类
// *
// * @author
// * @version 1.0
// * @Date Oct 15, 2009 9:35:38 AM
// * @see
// */
////@Entity(nameInDb = "SHXX", createInDb = false)
//public class SHXX {
//    // @ZdhzColumn(columnName = "ID")
//    // @ExcelField(name = "标识", next = "shdw")
//    @Id
//    @Property(nameInDb = "ID")
//    private long id;
//    // @ZdhzColumn(columnName = "BZ")
//    // @ExcelField(name = "备注", next = "")
//    @Property(nameInDb = "BZ")
//    private String BZ;         // 备注
//    // @ZdhzColumn(columnName = "DWMC")
//    // @ExcelField(name = "操作单位", next = "shry")
//    @Property(nameInDb = "DWMC")
//    private String shdw;       // 审核单位
//    // @ZdhzColumn(columnName = "CZR")
//    // @ExcelField(name = "操作人", next = "cz")
//    @Property(nameInDb = "CZR")
//    private String shry;       // 审核人
//    // @ZdhzColumn(columnName = "CZLX")
//    // @ExcelField(name = "操作", next = "czsj")
//    @Property(nameInDb = "CZLX")
//    private String cz;         // 操作
//
//    private String sj;
//
//    private String shyy;
//    // @ZdhzColumn(columnName = "BBS")
//    // @ExcelField(name = "表标识", next = "BZ")
//    @Property(nameInDb = "BBS")
//    private String tableinfo;
//    // @ZdhzColumn(columnName = "DXZJ")
//    // @ExcelField(name = "对象id", next = "tableinfo")
//    @Property(nameInDb = "DXZJ")
//    private long objectid;
//    private long objectidnew;
//    private String flagdydz;
//    // @ZdhzColumn(columnName = "CZSJ")
//    // @ExcelField(name = "操作时间", next = "objectid")
//    @Property(nameInDb = "CZSJ")
//    private Date czsj;
//    // //@ExcelField(name = "事件编号", next = "")
//    @Property(nameInDb = "SJBN")
//    private String sjbh;
//    // @ZdhzColumn(columnName = "BBS")
//    @Property(nameInDb = "BBS")
//    private String bbs;
//}
