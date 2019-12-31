package cn.com.sgcc.dev.dbUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.identityscope.IdentityScopeType;

import java.util.Locale;

import cn.com.sgcc.dev.dao2.DaoMaster;
import cn.com.sgcc.dev.dao2.DaoSession;


/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/21
 */

public class DBManagerOut {
    //    private final String outName = Constants.OUTPUT_PATH + "rlst_pad.db";
    private static DBManagerOut mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DBManagerOut(Context context, String outName) {
        if (mInstance == null) {
            DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, outName, null);
//            String sbxx = PreferenceUtils.getPrefString(context, "sbxx", null);
//            Database db = openHelper.getEncryptedWritableDb(sbxx);
            SQLiteDatabase db = openHelper.getWritableDatabase();
//            db.enableWriteAheadLogging();
            db.disableWriteAheadLogging();

            db.setLocale(Locale.CHINESE);
            mDaoMaster = new DaoMaster(db);
            mDaoSession = mDaoMaster.newSession(IdentityScopeType.None);
        }
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManagerOut getmInstance(Context context, String outName) {
        mInstance = null;
        if (mInstance == null) {
            synchronized (DBManagerOut.class) {
                if (mInstance == null) {
                    mInstance = new DBManagerOut(context, outName);
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
