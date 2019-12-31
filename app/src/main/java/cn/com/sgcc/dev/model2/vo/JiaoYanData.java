package cn.com.sgcc.dev.model2.vo;

import java.io.Serializable;
import java.util.List;

public class JiaoYanData implements Serializable {

	private List<SaleAttributeNameVo> bhData; //保护校验原始数据
	private List<SaleAttributeNameVo> fzData;//辅助校验原始数据

	public List<SaleAttributeNameVo> getBhData() {
		return bhData;
	}

	public void setBhData(List<SaleAttributeNameVo> bhData) {
		this.bhData = bhData;
	}

	public List<SaleAttributeNameVo> getFzData() {
		return fzData;
	}

	public void setFzData(List<SaleAttributeNameVo> fzData) {
		this.fzData = fzData;
	}

	@Override
	public String toString() {
		return "JiaoYanData{" +
				"bhData=" + bhData +
				", fzData=" + fzData +
				'}';
	}
}
