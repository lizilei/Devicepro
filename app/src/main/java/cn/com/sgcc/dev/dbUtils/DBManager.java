package cn.com.sgcc.dev.dbUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.identityscope.IdentityScopeType;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;

import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.dao2.DaoMaster;
import cn.com.sgcc.dev.dao2.DaoSession;
import cn.com.sgcc.dev.utils.FileUtils;


/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/21
 */

public class DBManager {
    private static DBManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DBManager(Context context) {
        if (mInstance == null) {
            if (Constants.DB_NAME == null || Constants.DB_NAME.equals("")) {
                Constants.DB_NAME = getDBName();
            }

            if (Constants.DB_NAME != null && !Constants.DB_NAME.equals("")) {
                DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, Constants.INPUT_PATH + Constants.DB_NAME, null);
//            String sbxx = PreferenceUtils.getPrefString(context, "sbxx", null);
//            String sbxx = context.getPackageName();
//            Database db = openHelper.getEncryptedWritableDb(sbxx);
                SQLiteDatabase db = openHelper.getWritableDatabase();

                db.disableWriteAheadLogging();
//                db.enableWriteAheadLogging();

                db.setLocale(Locale.CHINESE);
                mDaoMaster = new DaoMaster(db);
                mDaoSession = mDaoMaster.newSession(IdentityScopeType.None);
            }
        }
    }

    /**
     * 获取db文件名
     *
     * @return
     */
    public String getDBName() {
        File[] allFiles = new File(Constants.INPUT_PATH).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return FileUtils.getExtensionName(name).equals("db");
            }
        });

        if (allFiles.length > 0) {
            return allFiles[0].getName();
        }
        return "";
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getmInstance(Context context) {
        //if (mInstance == null) {
        synchronized (DBManager.class) {
            if (mInstance != null) {
                mInstance = null;
            }
            if (mInstance == null) {
                mInstance = new DBManager(context);
                return mInstance;
            }
        }
        // }

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
