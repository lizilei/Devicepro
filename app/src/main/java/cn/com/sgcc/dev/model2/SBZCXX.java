package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version V1.0
 * @Email lizilei_warms@163.com
 * @since 2019/6/26 0026 下午 4:45
 */

@Entity(nameInDb = "SBZCXX", createInDb = false)
public class SBZCXX {
    private static final long serialVersionUID = 1L;
    @Id
    @Property(nameInDb = "ID")
    private Long              id;
    @Property(nameInDb = "IMEI")
    private String            imei;
    @Property(nameInDb = "ANDROID_ID")
    private String            android_id;
    @Property(nameInDb = "SERIALNUMBER")
    private String            serialnumber;
    @Property(nameInDb = "SQSJ")
    private String            sqsj;                 // 申请时间
    @Property(nameInDb = "SQR")
    private String            sqr;                  // 申请人
    @Property(nameInDb = "SQGS")
    private String            sqgs;                 // 申请公司
    @Property(nameInDb = "SSDW")
    private String            ssdw;                 // 所属单位
    @Property(nameInDb = "CZMC")
    private String            czmc;                 // 厂站名称
    @Property(nameInDb = "CZGLDW")
    private String            czgldw;               // 厂站管理单位
    @Property(nameInDb = "ZCM")
    private String            zcm;                  // 注册码
    @Property(nameInDb = "BZ")
    private String            bz;                   // 备注
    @Generated(hash = 1546435524)
    public SBZCXX(Long id, String imei, String android_id, String serialnumber,
            String sqsj, String sqr, String sqgs, String ssdw, String czmc,
            String czgldw, String zcm, String bz) {
        this.id = id;
        this.imei = imei;
        this.android_id = android_id;
        this.serialnumber = serialnumber;
        this.sqsj = sqsj;
        this.sqr = sqr;
        this.sqgs = sqgs;
        this.ssdw = ssdw;
        this.czmc = czmc;
        this.czgldw = czgldw;
        this.zcm = zcm;
        this.bz = bz;
    }
    @Generated(hash = 1167185852)
    public SBZCXX() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImei() {
        return this.imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }
    public String getAndroid_id() {
        return this.android_id;
    }
    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }
    public String getSerialnumber() {
        return this.serialnumber;
    }
    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }
    public String getSqsj() {
        return this.sqsj;
    }
    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }
    public String getSqr() {
        return this.sqr;
    }
    public void setSqr(String sqr) {
        this.sqr = sqr;
    }
    public String getSqgs() {
        return this.sqgs;
    }
    public void setSqgs(String sqgs) {
        this.sqgs = sqgs;
    }
    public String getSsdw() {
        return this.ssdw;
    }
    public void setSsdw(String ssdw) {
        this.ssdw = ssdw;
    }
    public String getCzmc() {
        return this.czmc;
    }
    public void setCzmc(String czmc) {
        this.czmc = czmc;
    }
    public String getCzgldw() {
        return this.czgldw;
    }
    public void setCzgldw(String czgldw) {
        this.czgldw = czgldw;
    }
    public String getZcm() {
        return this.zcm;
    }
    public void setZcm(String zcm) {
        this.zcm = zcm;
    }
    public String getBz() {
        return this.bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
}
