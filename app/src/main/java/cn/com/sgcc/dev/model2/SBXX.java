package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * <p>@description:设备信息实体类</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/10/12
 */
@Entity(nameInDb = "SBXX", createInDb = false)
public class SBXX {
    @Id
    @Property(nameInDb = "ID")
    private Long id;
    @Property(nameInDb = "IMEI")
    private String imei;
    @Property(nameInDb = "ANDROID_ID")
    private String ANDROID_ID;
    @Property(nameInDb = "SERIALNUMBER")
    private String SerialNumber;
    @Property(nameInDb = "SBWYBDSBM")
    private String sbwybdsbm;
    @Property(nameInDb = "SJWJSJ")
    private String SJWJSJ;
    @Property(nameInDb = "SFJG")
    private String SFJG;

    @Generated(hash = 1145201994)
    public SBXX(Long id, String imei, String ANDROID_ID, String SerialNumber,
            String sbwybdsbm, String SJWJSJ, String SFJG) {
        this.id = id;
        this.imei = imei;
        this.ANDROID_ID = ANDROID_ID;
        this.SerialNumber = SerialNumber;
        this.sbwybdsbm = sbwybdsbm;
        this.SJWJSJ = SJWJSJ;
        this.SFJG = SFJG;
    }

    @Generated(hash = 1910743096)
    public SBXX() {
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

    public String getANDROID_ID() {
        return this.ANDROID_ID;
    }

    public void setANDROID_ID(String ANDROID_ID) {
        this.ANDROID_ID = ANDROID_ID;
    }

    public String getSerialNumber() {
        return this.SerialNumber;
    }

    public void setSerialNumber(String SerialNumber) {
        this.SerialNumber = SerialNumber;
    }

    public String getSbwybdsbm() {
        return this.sbwybdsbm;
    }

    public void setSbwybdsbm(String sbwybdsbm) {
        this.sbwybdsbm = sbwybdsbm;
    }

    public String getSJWJSJ() {
        return this.SJWJSJ;
    }

    public void setSJWJSJ(String SJWJSJ) {
        this.SJWJSJ = SJWJSJ;
    }

    @Override
    public String toString() {
        return "SBXX{" +
                "id=" + id +
                ", imei='" + imei + '\'' +
                ", ANDROID_ID='" + ANDROID_ID + '\'' +
                ", SerialNumber='" + SerialNumber + '\'' +
                ", sbwybdsbm='" + sbwybdsbm + '\'' +
                ", SJWJSJ='" + SJWJSJ + '\'' +
                '}';
    }

    public String getSFJG() {
        return this.SFJG;
    }

    public void setSFJG(String SFJG) {
        this.SFJG = SFJG;
    }
}
