package cn.com.sgcc.dev.net;

import java.util.List;

/**
 * Created by lilizilei on 2018/3/21.
 */

public class BaseCallModel<T> {
    public int code;
    public String msg;
    public T data;
    public List<T> list;

//    {
//        "successful":true,//请求是否成功
//            "resultValue": {//请求结果
//        "name":"banzhang",//当前登录账号（运维系统账号）
//                "id":"4028f581653b186401653b9e2b42011d"//当前登录账号的ID
//    },
//        "resultHint":"",//结果提示
//            "errorPage":"",
//            "type":""
//    }

}
