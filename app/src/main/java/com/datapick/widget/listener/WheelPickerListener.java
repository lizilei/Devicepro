package com.datapick.widget.listener;


import com.datapick.widget.bean.DateBean;
import com.datapick.widget.bean.DateType;

/**
 * Created by codbking on 2016/9/22.
 */

public interface WheelPickerListener {
     void onSelect(DateType type, DateBean bean);
}
