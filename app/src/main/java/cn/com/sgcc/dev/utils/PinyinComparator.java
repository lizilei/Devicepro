package cn.com.sgcc.dev.utils;

/**
 * <p>@description:2.1.1.拼音首字排序</p>
 * @author tanqiu
 * @version 1.0.0
 * @Email
 * @since 2017/9/9
 */
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import cn.com.sgcc.dev.model2.ycsb.CZCS;

public class PinyinComparator implements Comparator {

	@Override
	public int compare(Object arg0, Object arg1) {
// 按照名字排序
		CZCS user0 = (CZCS) arg0;
		CZCS user1 = (CZCS) arg1;
		String catalog0 = "";
		String catalog1 = "";
		int type = 0;

		if (user0 != null && user0.getCzmc() != null
				&& user0.getCzmc().length() >= 1){
			catalog0 = TextPinyinUtil.getInstance().getPinyin(user0.getCzmc() )
					.substring(0, 1) + "";
		}

		if (user1 != null && user1.getCzmc()  != null
				&& user1.getCzmc().length() >= 1){
			catalog1 = TextPinyinUtil.getInstance().getPinyin(user1.getCzmc())
					.substring(0, 1) + "";
		}

		int flag = catalog0.toLowerCase().compareTo(catalog1.toLowerCase());
		if (flag == 0) {
			type = 0;
		} else if (flag > 0) {
			type = 1;
		} else {
			type = -1;
		}
		return type;

	}
}
