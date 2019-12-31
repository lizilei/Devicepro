package cn.com.sgcc.dev.model2.regist;

import java.util.List;

/**
 * <p>@description:解密后的key文件内容实体类</p>
 *
 * @author lizilei
 * @version V1.0
 * @Email lizilei_warms@163.com
 * @since 2019/6/25 0025 下午 4:16
 */

public class DecryptKey {

    /**
     * ZCM : zcm10000000002
     * SERIALNUMBER : 10000000002
     * IMEI : 10000000002
     * ANDROID_ID : 10000000002
     * list : [{"czgldw":"国网巴中供电公司","czmc":"观井变"}]
     */

    private String ZCM;
    private String SERIALNUMBER;
    private String IMEI;
    private String ANDROID_ID;
    private List<CzInfo> list;

    public String getZCM() {
        return ZCM;
    }

    public void setZCM(String ZCM) {
        this.ZCM = ZCM;
    }

    public String getSERIALNUMBER() {
        return SERIALNUMBER;
    }

    public void setSERIALNUMBER(String SERIALNUMBER) {
        this.SERIALNUMBER = SERIALNUMBER;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getANDROID_ID() {
        return ANDROID_ID;
    }

    public void setANDROID_ID(String ANDROID_ID) {
        this.ANDROID_ID = ANDROID_ID;
    }

    public List<CzInfo> getList() {
        return list;
    }

    public void setList(List<CzInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "DecryptKey{" +
                "ZCM='" + ZCM + '\'' +
                ", SERIALNUMBER='" + SERIALNUMBER + '\'' +
                ", IMEI='" + IMEI + '\'' +
                ", ANDROID_ID='" + ANDROID_ID + '\'' +
                ", list=" + list +
                '}';
    }

    public static class CzInfo {
        /**
         * czgldw : 国网巴中供电公司
         * czmc : 观井变
         */

        private String czgldw;
        private String czmc;

        public String getCzgldw() {
            return czgldw;
        }

        public void setCzgldw(String czgldw) {
            this.czgldw = czgldw;
        }

        public String getCzmc() {
            return czmc;
        }

        public void setCzmc(String czmc) {
            this.czmc = czmc;
        }


        @Override
        public String toString() {
            return "CzInfo{" +
                    "czgldw='" + czgldw + '\'' +
                    ", czmc='" + czmc + '\'' +
                    '}';
        }
    }
}
