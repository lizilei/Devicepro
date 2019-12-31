//package cn.com.sgcc.dev.model;
//
//import org.greenrobot.greendao.annotation.Entity;
//import org.greenrobot.greendao.annotation.Id;
//import org.greenrobot.greendao.annotation.Property;
//
//import java.io.Serializable;
//import org.greenrobot.greendao.annotation.Generated;
//
///**
// * <p>@description:保护小室</p>
// * 表名：SG_CON_PROCUB
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/25
// */
//
//@Entity(nameInDb = "SG_CON_PROCUB")
//public class BHXS_SG_CON_PROCUB implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String ID;           // 保护小室ID
//    @Property(nameInDb = "NAME")
//    private String NAME;         // 保护小室名称
//    @Property(nameInDb = "SUBSTATION_ID")
//    private String SUBSTATION_ID;// 保护小室所属厂站
//    @Generated(hash = 1858520136)
//    public BHXS_SG_CON_PROCUB(String ID, String NAME, String SUBSTATION_ID) {
//        this.ID = ID;
//        this.NAME = NAME;
//        this.SUBSTATION_ID = SUBSTATION_ID;
//    }
//    @Generated(hash = 1823077961)
//    public BHXS_SG_CON_PROCUB() {
//    }
//    public String getID() {
//        return this.ID;
//    }
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//    public String getNAME() {
//        return this.NAME;
//    }
//    public void setNAME(String NAME) {
//        this.NAME = NAME;
//    }
//    public String getSUBSTATION_ID() {
//        return this.SUBSTATION_ID;
//    }
//    public void setSUBSTATION_ID(String SUBSTATION_ID) {
//        this.SUBSTATION_ID = SUBSTATION_ID;
//    }
//}
