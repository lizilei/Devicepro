package cn.com.sgcc.dev.model;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * <p>@description:自定义类型参数</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/2
 */

public class TimestampConverter implements PropertyConverter<Timestamp, String> {

    /**
     * 将String转化成Timestamp
     *
     * @param databaseValue
     * @return
     */
    @Override
    public Timestamp convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return Timestamp.valueOf(df.format(databaseValue));
//        return Timestamp.valueOf(databaseValue);
    }

    /**
     * 将Timestamp转化成String
     *
     * @param entityProperty
     * @return
     */
    @Override
    public String convertToDatabaseValue(Timestamp entityProperty) {
        if (entityProperty == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return df.format(entityProperty);
    }

//    /**
//     * 将String转化成Timestamp
//     *
//     * @param databaseValue
//     * @return
//     */
//    @Override
//    public Timestamp convertToEntityProperty(String databaseValue) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return Timestamp.valueOf(df.format(databaseValue));
//    }
//
//    /**
//     * 将Timestamp转化成String
//     *
//     * @param entityProperty
//     * @return
//     */
//    @Override
//    public String convertToDatabaseValue(Timestamp entityProperty) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return df.format(entityProperty);
//    }
}
