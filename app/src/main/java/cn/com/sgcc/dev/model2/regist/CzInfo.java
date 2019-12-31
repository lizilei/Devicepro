package cn.com.sgcc.dev.model2.regist;

import java.util.List;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version V1.0
 * @Email lizilei_warms@163.com
 * @since 2019/6/28 0028 下午 1:59
 */

public class CzInfo {
    private List<Czxq> tfczList;

    public List<Czxq> getTfczList() {
        return tfczList;
    }

    public void setTfczList(List<Czxq> tfczList) {
        this.tfczList = tfczList;
    }

   public class Czxq{
        private String stationId;
        private String stationName;
        private String orgName;
        private boolean isChecked;

       public boolean isChecked() {
           return isChecked;
       }

       public void setChecked(boolean checked) {
           isChecked = checked;
       }

       public String getStationId() {
            return stationId;
        }

        public void setStationId(String stationId) {
            this.stationId = stationId;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

       @Override
       public String toString() {
           return "Czxq{" +
                   "stationId='" + stationId + '\'' +
                   ", stationName='" + stationName + '\'' +
                   ", orgName='" + orgName + '\'' +
                   ", isChecked=" + isChecked +
                   '}';
       }
   }
}
