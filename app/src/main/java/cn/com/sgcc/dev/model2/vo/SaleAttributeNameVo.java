package cn.com.sgcc.dev.model2.vo;

import java.io.Serializable;
import java.util.List;

public class SaleAttributeNameVo implements Serializable {
	private String name; //分类信息名称
	private String nameId;
	private List<SaleAttributeVo> saleVo; // 属性值集合
	private String saleAttr;
	private boolean nameChecked; //false  是否展开全部

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameId() {
		return nameId;
	}

	public void setNameId(String nameId) {
		this.nameId = nameId;
	}

	public List<SaleAttributeVo> getSaleVo() {
		return saleVo;
	}

	public void setSaleVo(List<SaleAttributeVo> saleVo) {
		this.saleVo = saleVo;
	}

	public String getSaleAttr() {
		return saleAttr;
	}

	public void setSaleAttr(String saleAttr) {
		this.saleAttr = saleAttr;
	}

	public boolean isNameChecked() {
		return nameChecked;
	}

	public void setNameChecked(boolean nameChecked) {
		this.nameChecked = nameChecked;
	}

	@Override
	public String toString() {
		return "SaleAttributeNameVo{" +
				"name='" + name + '\'' +
				", nameId='" + nameId + '\'' +
				", saleVo=" + saleVo +
				", saleAttr='" + saleAttr + '\'' +
				", nameChecked=" + nameChecked +
				'}';
	}
}
