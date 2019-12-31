package cn.com.sgcc.dev.model2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/18
 */

@Entity
public class CODE {
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;

    @Generated(hash = 2013573085)
    public CODE(Long id) {
        this.id = id;
    }

    @Generated(hash = 848511480)
    public CODE() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
