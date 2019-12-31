package cn.com.sgcc.dev.model2;

import java.util.List;

import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;

/**
 * <p>@description:首页搜索条件实体类</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/12
 */

public class SearchEntity {
    private String ZZType = ""; //装置类型
    private String ZZName = ""; //装置名称
    private String pxOne = ""; //排序名称
    private String pxTwo = ""; //排序方式
    private String bhlb = "";  //保护类别
    private String clzt = "";   //处理状态
    private List<SaleAttributeNameVo> nameVos;//筛选属性对象集合
    private List<SaleAttributeNameVo> nameVoJYX;//所有校验项
    private List<SaleAttributeNameVo> nameVoJYXOne;//一级校验项

    public String getZZType() {
        return ZZType;
    }

    public void setZZType(String ZZType) {
        this.ZZType = ZZType;
    }

    public String getZZName() {
        return ZZName;
    }

    public void setZZName(String ZZName) {
        this.ZZName = ZZName;
    }

    public String getPxOne() {
        return pxOne;
    }

    public void setPxOne(String pxOne) {
        this.pxOne = pxOne;
    }

    public String getPxTwo() {
        return pxTwo;
    }

    public void setPxTwo(String pxTwo) {
        this.pxTwo = pxTwo;
    }

    public String getBhlb() {
        return bhlb;
    }

    public void setBhlb(String bhlb) {
        this.bhlb = bhlb;
    }

    public String getClzt() {
        return clzt;
    }

    public void setClzt(String clzt) {
        this.clzt = clzt;
    }

    public List<SaleAttributeNameVo> getNameVos() {
        return nameVos;
    }

    public void  setNameVos(List<SaleAttributeNameVo> nameVos) {
        this.nameVos = nameVos;
    }

    public List<SaleAttributeNameVo> getNameVoJYX() {
        return nameVoJYX;
    }

    public void setNameVoJYX(List<SaleAttributeNameVo> nameVoJYX) {
        this.nameVoJYX = nameVoJYX;
    }

    public List<SaleAttributeNameVo> getNameVoJYXOne() {
        return nameVoJYXOne;
    }

    public void setNameVoJYXOne(List<SaleAttributeNameVo> nameVoJYXOne) {
        this.nameVoJYXOne = nameVoJYXOne;
    }

    @Override
    public String toString() {
        return "SearchEntity{" +
                "ZZType='" + ZZType + '\'' +
                ", ZZName='" + ZZName + '\'' +
                ", pxOne='" + pxOne + '\'' +
                ", pxTwo='" + pxTwo + '\'' +
                ", bhlb='" + bhlb + '\'' +
                ", clzt='" + clzt + '\'' +
                ", nameVos=" + nameVos +
                ", nameVoJYX=" + nameVoJYX +
                ", nameVoJYXOne=" + nameVoJYXOne +
                '}';
    }
}
