package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "AKXTGX", createInDb = false)
public class AKXTGX {

    @Id
    @Property(nameInDb = "ID")
    private Long id;     //标识
    @Property(nameInDb = "BHPZID")
    private String bhpzid;       // 保护配置ID
    @Property(nameInDb = "AKXTID")
    private String akxtid;       // 安控系统ID
    @Property(nameInDb = "AKZDLX")
    private String akzdlx;    // 安控站点类型
    @Generated(hash = 1987497173)
    public AKXTGX(Long id, String bhpzid, String akxtid, String akzdlx) {
        this.id = id;
        this.bhpzid = bhpzid;
        this.akxtid = akxtid;
        this.akzdlx = akzdlx;
    }
    @Generated(hash = 1388623883)
    public AKXTGX() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBhpzid() {
        return this.bhpzid;
    }
    public void setBhpzid(String bhpzid) {
        this.bhpzid = bhpzid;
    }
    public String getAkxtid() {
        return this.akxtid;
    }
    public void setAkxtid(String akxtid) {
        this.akxtid = akxtid;
    }
    public String getAkzdlx() {
        return this.akzdlx;
    }
    public void setAkzdlx(String akzdlx) {
        this.akzdlx = akzdlx;
    }


}