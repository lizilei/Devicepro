package cn.com.sgcc.dev.model2.vo;

import java.io.Serializable;

public class SaleAttributeVo implements Serializable {

	private String goods;
	private String value;   //属性名称(汉字)
	private String lieName;  //列名(英文)
	private String goodsAndValId;
	private boolean checked; //false  是否被选中


	public String getGoodsAndValId() {
		return goodsAndValId;
	}

	public void setGoodsAndValId(String goodsAndValId) {
		this.goodsAndValId = goodsAndValId;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

	public String getLieName() {
		return lieName;
	}

	public void setLieName(String lieName) {
		this.lieName = lieName;
	}

	@Override
	public String toString() {
		return "SaleAttributeVo{" +
				"goods='" + goods + '\'' +
				", value='" + value + '\'' +
				", lieName='" + lieName + '\'' +
				", goodsAndValId='" + goodsAndValId + '\'' +
				", checked=" + checked +
				'}';
	}
}
