package cn.com.sgcc.dev.model2.print;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <p>@description:公共打印参数实体类</p>
 *
 * @author lizilei
 * @Version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/4/2
 */

public class Print implements Parcelable {
    private String form_type;
    private String qrcode;
    private String czmc;
    private String bh_sb_mc;
    private String bhlb;
    private String zzcj;
    private String bh_sb_xh;
    private String ycsbmc;
    private String ycsblx;
    private String yxdw;
    private String whdw;
    private String dddw;
    private String gldwmc;
    private String tyrq;
    private String ccrq;

    public Print() {
    }

    public Print(String form_type, String qrcode, String czmc, String bh_sb_mc, String bhlb, String zzcj, String bh_sb_xh, String ycsbmc, String ycsblx, String yxdw, String whdw, String dddw, String gldwmc, String tyrq, String ccrq) {
        this.form_type = form_type;
        this.qrcode = qrcode;
        this.czmc = czmc;
        this.bh_sb_mc = bh_sb_mc;
        this.bhlb = bhlb;
        this.zzcj = zzcj;
        this.bh_sb_xh = bh_sb_xh;
        this.ycsbmc = ycsbmc;
        this.ycsblx = ycsblx;
        this.yxdw = yxdw;
        this.whdw = whdw;
        this.dddw = dddw;
        this.gldwmc = gldwmc;
        this.tyrq = tyrq;
        this.ccrq = ccrq;
    }

    public String getForm_type() {
        return form_type;
    }

    public void setForm_type(String form_type) {
        this.form_type = form_type;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getCzmc() {
        return czmc;
    }

    public void setCzmc(String czmc) {
        this.czmc = czmc;
    }

    public String getBh_sb_mc() {
        return bh_sb_mc;
    }

    public void setBh_sb_mc(String bh_sb_mc) {
        this.bh_sb_mc = bh_sb_mc;
    }

    public String getBhlb() {
        return bhlb;
    }

    public void setBhlb(String bhlb) {
        this.bhlb = bhlb;
    }

    public String getZzcj() {
        return zzcj;
    }

    public void setZzcj(String zzcj) {
        this.zzcj = zzcj;
    }

    public String getBh_sb_xh() {
        return bh_sb_xh;
    }

    public void setBh_sb_xh(String bh_sb_xh) {
        this.bh_sb_xh = bh_sb_xh;
    }

    public String getYcsbmc() {
        return ycsbmc;
    }

    public void setYcsbmc(String ycsbmc) {
        this.ycsbmc = ycsbmc;
    }

    public String getYcsblx() {
        return ycsblx;
    }

    public void setYcsblx(String ycsblx) {
        this.ycsblx = ycsblx;
    }

    public String getYxdw() {
        return yxdw;
    }

    public void setYxdw(String yxdw) {
        this.yxdw = yxdw;
    }

    public String getWhdw() {
        return whdw;
    }

    public void setWhdw(String whdw) {
        this.whdw = whdw;
    }

    public String getDddw() {
        return dddw;
    }

    public void setDddw(String dddw) {
        this.dddw = dddw;
    }

    public String getGldwmc() {
        return gldwmc;
    }

    public void setGldwmc(String gldwmc) {
        this.gldwmc = gldwmc;
    }

    public String getTyrq() {
        return tyrq;
    }

    public void setTyrq(String tyrq) {
        this.tyrq = tyrq;
    }

    public String getCcrq() {
        return ccrq;
    }

    public void setCcrq(String ccrq) {
        this.ccrq = ccrq;
    }

    @Override
    public String toString() {
        return "Print{" +
                "form_type='" + form_type + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", czmc='" + czmc + '\'' +
                ", bh_sb_mc='" + bh_sb_mc + '\'' +
                ", bhlb='" + bhlb + '\'' +
                ", zzcj='" + zzcj + '\'' +
                ", bh_sb_xh='" + bh_sb_xh + '\'' +
                ", ycsbmc='" + ycsbmc + '\'' +
                ", ycsblx='" + ycsblx + '\'' +
                ", yxdw='" + yxdw + '\'' +
                ", whdw='" + whdw + '\'' +
                ", dddw='" + dddw + '\'' +
                ", gldwmc='" + gldwmc + '\'' +
                ", tyrq='" + tyrq + '\'' +
                ", ccrq='" + ccrq + '\'' +
                '}';
    }

    protected Print(Parcel in) {
        this.form_type = in.readString();
        this.qrcode = in.readString();
        this.czmc = in.readString();
        this.bh_sb_mc = in.readString();
        this.bhlb = in.readString();
        this.zzcj = in.readString();
        this.bh_sb_xh = in.readString();
        this.ycsbmc = in.readString();
        this.ycsblx = in.readString();
        this.yxdw = in.readString();
        this.whdw = in.readString();
        this.dddw = in.readString();
        this.gldwmc = in.readString();
        this.tyrq = in.readString();
        this.ccrq = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.form_type);
        dest.writeString(this.qrcode);
        dest.writeString(this.czmc);
        dest.writeString(this.bh_sb_mc);
        dest.writeString(this.bhlb);
        dest.writeString(this.zzcj);
        dest.writeString(this.bh_sb_xh);
        dest.writeString(this.ycsbmc);
        dest.writeString(this.ycsblx);
        dest.writeString(this.yxdw);
        dest.writeString(this.whdw);
        dest.writeString(this.dddw);
        dest.writeString(this.gldwmc);
        dest.writeString(this.tyrq);
        dest.writeString(this.ccrq);
    }

    public static final Creator<Print> CREATOR = new Creator<Print>() {
        @Override
        public Print createFromParcel(Parcel source) {
            return new Print(source);
        }

        @Override
        public Print[] newArray(int size) {
            return new Print[size];
        }
    };
}
