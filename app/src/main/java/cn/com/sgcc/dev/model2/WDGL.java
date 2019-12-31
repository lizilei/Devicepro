package cn.com.sgcc.dev.model2;

import android.graphics.Bitmap;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * <p>@description:文档管理树形封装类</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/1/22
 */

@Entity(nameInDb = "WDGL", createInDb = false)
public class WDGL {
    @Id
    @Property(nameInDb = "ID")
    private Long id;
    @Property(nameInDb = "TYPE")
    private String type;      ///文档类型
    @Property(nameInDb = "KEYWORD")
    private String keyWord;      //关键字
    @Property(nameInDb = "MSLX")
    private String mslx;       //模式类型
    @Property(nameInDb = "NAME")
    private String name;
    @Property(nameInDb = "CSLX")
    private String cslx;      //参数类型
    @Property(nameInDb = "CSID")
    private long csId;      //关联标识
    @Property(nameInDb = "CZR")
    private String czr;    //操作人
    @Property(nameInDb = "WDID")
    private String wdId;    //文档标识
    @Property(nameInDb = "SBSJ")
    private String sbsj;
    @Property(nameInDb = "SSDW")
    private String ssdw;    //所属单位
    @Property(nameInDb = "CSTABLE")
    private String cstable;  //关联表名
    @Property(nameInDb = "REALNAME")
    private String realName;     //真实文件名
    @Property(nameInDb = "SB")
    private String sb;      //上报状态
    @Property(nameInDb = "SB_LS_ID")
    private String sb_ls_id;
    @Property(nameInDb = "SFBDSJ")
    private String sfbdsj;
    @Property(nameInDb = "SBDW")
    private String sbdw;
    @Property(nameInDb = "SH")
    private String sh;
    @Property(nameInDb = "HZSJ")
    private String hzsj;
    @Property(nameInDb = "SBCZLX")
    private String sbczlx;
    @Property(nameInDb = "WJ_DW")
    private String wj_dw;
    @Property(nameInDb = "WJ_LS_ID")
    private String wj_ls_id;
    @Property(nameInDb = "SFXTLR")
    private String sfxtlr;
    @Property(nameInDb = "ED_TAG")
    private String ed_tag;
    @Transient
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Generated(hash = 2075790429)
    public WDGL(Long id, String type, String keyWord, String mslx, String name,
            String cslx, long csId, String czr, String wdId, String sbsj,
            String ssdw, String cstable, String realName, String sb,
            String sb_ls_id, String sfbdsj, String sbdw, String sh, String hzsj,
            String sbczlx, String wj_dw, String wj_ls_id, String sfxtlr,
            String ed_tag) {
        this.id = id;
        this.type = type;
        this.keyWord = keyWord;
        this.mslx = mslx;
        this.name = name;
        this.cslx = cslx;
        this.csId = csId;
        this.czr = czr;
        this.wdId = wdId;
        this.sbsj = sbsj;
        this.ssdw = ssdw;
        this.cstable = cstable;
        this.realName = realName;
        this.sb = sb;
        this.sb_ls_id = sb_ls_id;
        this.sfbdsj = sfbdsj;
        this.sbdw = sbdw;
        this.sh = sh;
        this.hzsj = hzsj;
        this.sbczlx = sbczlx;
        this.wj_dw = wj_dw;
        this.wj_ls_id = wj_ls_id;
        this.sfxtlr = sfxtlr;
        this.ed_tag = ed_tag;
    }
    @Generated(hash = 1719516472)
    public WDGL() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getKeyWord() {
        return this.keyWord;
    }
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
    public String getMslx() {
        return this.mslx;
    }
    public void setMslx(String mslx) {
        this.mslx = mslx;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCslx() {
        return this.cslx;
    }
    public void setCslx(String cslx) {
        this.cslx = cslx;
    }
    public long getCsId() {
        return this.csId;
    }
    public void setCsId(long csId) {
        this.csId = csId;
    }
    public String getCzr() {
        return this.czr;
    }
    public void setCzr(String czr) {
        this.czr = czr;
    }
    public String getWdId() {
        return this.wdId;
    }
    public void setWdId(String wdId) {
        this.wdId = wdId;
    }
    public String getSbsj() {
        return this.sbsj;
    }
    public void setSbsj(String sbsj) {
        this.sbsj = sbsj;
    }
    public String getSsdw() {
        return this.ssdw;
    }
    public void setSsdw(String ssdw) {
        this.ssdw = ssdw;
    }
    public String getCstable() {
        return this.cstable;
    }
    public void setCstable(String cstable) {
        this.cstable = cstable;
    }
    public String getRealName() {
        return this.realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getSb() {
        return this.sb;
    }
    public void setSb(String sb) {
        this.sb = sb;
    }
    public String getSb_ls_id() {
        return this.sb_ls_id;
    }
    public void setSb_ls_id(String sb_ls_id) {
        this.sb_ls_id = sb_ls_id;
    }
    public String getSfbdsj() {
        return this.sfbdsj;
    }
    public void setSfbdsj(String sfbdsj) {
        this.sfbdsj = sfbdsj;
    }
    public String getSbdw() {
        return this.sbdw;
    }
    public void setSbdw(String sbdw) {
        this.sbdw = sbdw;
    }
    public String getSh() {
        return this.sh;
    }
    public void setSh(String sh) {
        this.sh = sh;
    }
    public String getHzsj() {
        return this.hzsj;
    }
    public void setHzsj(String hzsj) {
        this.hzsj = hzsj;
    }
    public String getSbczlx() {
        return this.sbczlx;
    }
    public void setSbczlx(String sbczlx) {
        this.sbczlx = sbczlx;
    }
    public String getWj_dw() {
        return this.wj_dw;
    }
    public void setWj_dw(String wj_dw) {
        this.wj_dw = wj_dw;
    }
    public String getWj_ls_id() {
        return this.wj_ls_id;
    }
    public void setWj_ls_id(String wj_ls_id) {
        this.wj_ls_id = wj_ls_id;
    }
    public String getSfxtlr() {
        return this.sfxtlr;
    }
    public void setSfxtlr(String sfxtlr) {
        this.sfxtlr = sfxtlr;
    }
    public String getEd_tag() {
        return this.ed_tag;
    }
    public void setEd_tag(String ed_tag) {
        this.ed_tag = ed_tag;
    }
}
