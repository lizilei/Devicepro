//package cn.com.sgcc.dev.model2;
//
//import org.greenrobot.greendao.annotation.Id;
//import org.greenrobot.greendao.annotation.Property;
//import org.greenrobot.greendao.annotation.ToMany;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * <p>
// * 保护设备型号实体
// * </p>
// *
// * @author 申华
// * @version 1.0
// * @Date 2013-7-4 上午10:07:41
// * @see
// */
////@Entity(nameInDb = "BHSBXHSB",createInDb = false)
//public class BHSBXHSB implements Serializable {
//    /**
//     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
//     */
//    private static final long serialVersionUID = 1L;
//    @Id
//    @Property(nameInDb = "ID")
//    private long              id;                                        // Id唯一标识
//    @Property(nameInDb = "SBXH")
//    private String            sbxh;                                      // 设备型号
//    @Property(nameInDb = "ZZCJ")
//    private String            zzcj;                                      // 制造厂家
//    @Property(nameInDb = "BHLB")
//    private String            bhlb;                                      // 设备类别
//    @Property(nameInDb = "BHLX")
//    private String            bhlx;                                      // 设备类型
//    @Property(nameInDb = "BHFL")
//    private String            bhfl;                                      // 设备分类
//    @Property(nameInDb = "UCODE")
//    private String            ucode;                                     // 要更新的编码
//    @Property(nameInDb = "RJBBXX")
//    private String            rjbbxx;                                    // 软件版本信息
//    @Property(nameInDb = "SBSJ")
//    private String            sbsj;
//    @Property(nameInDb = "MKRJBB")
//    private String            mkrjbb;
//    List<String>              sblblist         = new ArrayList<String>();
//    List<String>              sblxlist         = new ArrayList<String>();
//    List<String>              sbfllist         = new ArrayList<String>();
//    List<String>              bblxlist         = new ArrayList<String>();
//    List<String>              sbxhlist         = new ArrayList<String>();
//    List<String>              mkmclist         = new ArrayList<String>();
//    List<String>              rjbblist         = new ArrayList<String>();
//    List<String>              bbjymlist        = new ArrayList<String>();
//    List<String>              bbcodelist       = new ArrayList<String>();
//    @Property(nameInDb = "IDS")
//    private String            ids;
//    @Property(nameInDb = "BZ")
//    private String           bz;   // 设备描述
//    @ToMany(referencedJoinProperty = "ID")
//    private List<BHXHRJBB> rjbbs;// 软件版本
//    @Property(nameInDb = "BBLX")
//    private String           bblx; // 版本类型
//    @Property(nameInDb = "STATE")
//    private String           state;// 标记状态
//    @Property(nameInDb = "SH")
//    private String           sh;   // 审核状态
//    @Property(nameInDb = "SHR")
//    private String           shr;  // 审核人
//    @Property(nameInDb = "CZR")
//    private String           czr;  // 操作人
//    @Property(nameInDb = "SHYY")
//    private String           shyy; // 审核不通过原因
//    @ToMany(referencedJoinProperty = "id")
//    private List<SHXX>       logs; // 日志信息
//    @Property(nameInDb = "BBXH")
//    private int              bbxh;
//}
