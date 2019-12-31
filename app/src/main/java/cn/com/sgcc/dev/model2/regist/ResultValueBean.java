package cn.com.sgcc.dev.model2.regist;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version V1.0
 * @Email lizilei_warms@163.com
 * @since 2019/6/28 0028 上午 9:36
 */

public class ResultValueBean {
    /**
     * name : banzhang
     * id : 4028f581653b186401653b9e2b42011d
     */

    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ResultValueBean{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
