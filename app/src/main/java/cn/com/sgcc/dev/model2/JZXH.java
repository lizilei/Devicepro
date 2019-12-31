package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * <p>@description:家族型号</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/8
 */
@Entity(nameInDb = "JZXH", createInDb = false)
public class JZXH {

    @Id
    @Property(nameInDb = "ID")
    private Long ID;
    @Property(nameInDb = "ZZCJ")
    private String ZZCJ;
    @Property(nameInDb = "JZXH")
    private String JZXH;
    @Property(nameInDb = "SFZXWH")
    private String SFZXWH;
    @Property(nameInDb = "STATE")
    private String STATE;
    @Property(nameInDb = "VERSION")
    private String VERSION;
    @Property(nameInDb = "TIME")
    private String TIME;
    @Generated(hash = 240875849)
    public JZXH(Long ID, String ZZCJ, String JZXH, String SFZXWH, String STATE,
            String VERSION, String TIME) {
        this.ID = ID;
        this.ZZCJ = ZZCJ;
        this.JZXH = JZXH;
        this.SFZXWH = SFZXWH;
        this.STATE = STATE;
        this.VERSION = VERSION;
        this.TIME = TIME;
    }
    @Generated(hash = 1927351704)
    public JZXH() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getZZCJ() {
        return this.ZZCJ;
    }
    public void setZZCJ(String ZZCJ) {
        this.ZZCJ = ZZCJ;
    }
    public String getJZXH() {
        return this.JZXH;
    }
    public void setJZXH(String JZXH) {
        this.JZXH = JZXH;
    }
    public String getSFZXWH() {
        return this.SFZXWH;
    }
    public void setSFZXWH(String SFZXWH) {
        this.SFZXWH = SFZXWH;
    }
    public String getSTATE() {
        return this.STATE;
    }
    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }
    public String getVERSION() {
        return this.VERSION;
    }
    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }
    public String getTIME() {
        return this.TIME;
    }
    public void setTIME(String TIME) {
        this.TIME = TIME;
    }
}
