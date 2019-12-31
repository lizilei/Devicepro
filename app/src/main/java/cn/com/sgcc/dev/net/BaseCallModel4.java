package cn.com.sgcc.dev.net;

import java.util.List;

/**
 * Created by lilizilei on 2018/3/21.
 */

public class BaseCallModel4<T> {


    /**
     * successful : true
     * resultValue : {"name":"banzhang","id":"4028f581653b186401653b9e2b42011d"}
     * resultHint :
     * errorPage :
     * type :
     */

    public boolean successful;
    public List<T> resultValue;
    public String resultHint;
    public String errorPage;
    public String type;



}
