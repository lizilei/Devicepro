package cn.com.sgcc.dev.model;

import java.io.Serializable;

/**
 * <p>@description:2.1.1.12设备详情界面子条目信息</p>
 * 表名：SG_PRO_PUBDEV_VER
 *
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/9/2
 */
public class DeviceDetailsNameItem implements Serializable {
    private String name_one;                  //第一条录入信息名称
    private String content_one;              //第一条录入信息内容
    private String name_two;                 //第二条录入信息名称
    private String content_two;              //第二条录入信息内容
    private String name_three;                  //第三条录入信息名称
    private String content_three;              //第三条录入信息内容
    private String name_four;                  //第四条录入信息名称
    private String content_four;              //第四条录入信息内容
    private int num;              //有效内容条目，最大值为3，最小为1，特殊值大于3
    private boolean isHaveAdd = false;   //标志是否需要显示增加按钮

    public boolean isHaveAdd() {
        return isHaveAdd;
    }

    public void setHaveAdd(boolean haveAdd) {
        isHaveAdd = haveAdd;
    }

    public String getName_one() {
        return name_one;
    }

    public void setName_one(String name_one) {
        this.name_one = name_one;
    }

    public String getContent_one() {
        return content_one;
    }

    public void setContent_one(String content_one) {
        this.content_one = content_one;
    }

    public String getName_two() {
        return name_two;
    }

    public void setName_two(String name_two) {
        this.name_two = name_two;
    }

    public String getContent_two() {
        return content_two;
    }

    public void setContent_two(String content_two) {
        this.content_two = content_two;
    }

    public String getName_three() {
        return name_three;
    }

    public void setName_three(String name_three) {
        this.name_three = name_three;
    }

    public String getContent_three() {
        return content_three;
    }

    public void setContent_three(String content_three) {
        this.content_three = content_three;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName_four() {
        return name_four;
    }

    public void setName_four(String name_four) {
        this.name_four = name_four;
    }

    public String getContent_four() {
        return content_four;
    }

    public void setContent_four(String content_four) {
        this.content_four = content_four;
    }
}
