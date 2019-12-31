package cn.com.sgcc.dev.dao2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.identityscope.IdentityScopeType;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * Master of DAO (schema version 1): knows all DAOs.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        UserInfoDao.createTable(db, ifNotExists);
        CODEDao.createTable(db, ifNotExists);
    }

    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        UserInfoDao.dropTable(db, ifExists);
        CODEDao.dropTable(db, ifExists);
    }

    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * Convenience method using a {@link DevOpenHelper}.
     */
    public static DaoSession newDevSession(Context context, String name) {
        Database db = new DevOpenHelper(context, name).getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(DWLXDao.class);
        registerDaoClass(JZXHDao.class);
        registerDaoClass(AKXTDao.class);
        registerDaoClass(ZZCJDao.class);
        registerDaoClass(BZSJFLDao.class);
        registerDaoClass(CCXXDao.class);
        registerDaoClass(AKXTGXDao.class);
        registerDaoClass(SBXXDao.class);
        registerDaoClass(FZBHSBDao.class);
        registerDaoClass(CCXXBKDao.class);
        registerDaoClass(RZGLDao.class);
        registerDaoClass(CCXXRJBBDao.class);
        registerDaoClass(BKXXDao.class);
        registerDaoClass(SBZCXXDao.class);
        registerDaoClass(BZSJDao.class);
        registerDaoClass(LJQXXDao.class);
        registerDaoClass(JGCSDao.class);
        registerDaoClass(MXCSDao.class);
        registerDaoClass(DKQCSDao.class);
        registerDaoClass(XLCSDao.class);
        registerDaoClass(DDJCSDao.class);
        registerDaoClass(DRQCSDao.class);
        registerDaoClass(BYQCSDao.class);
        registerDaoClass(FDJCSDao.class);
        registerDaoClass(CZCSDao.class);
        registerDaoClass(DLQCSDao.class);
        registerDaoClass(UserInfoDao.class);
        registerDaoClass(TDXXDao.class);
        registerDaoClass(PZTDGXDao.class);
        registerDaoClass(BHLBXHDao.class);
        registerDaoClass(LTYSBXHDao.class);
        registerDaoClass(BDWDao.class);
        registerDaoClass(CODEDao.class);
        registerDaoClass(BHSBXHBDao.class);
        registerDaoClass(BHPZDao.class);
        registerDaoClass(WDGLDao.class);
        registerDaoClass(BHPZXHBBGXDao.class);
        registerDaoClass(DLQKDao.class);
        registerDaoClass(GXDWDao.class);
        registerDaoClass(FZBHSBXHBBGXDao.class);
        registerDaoClass(RLST_USERDao.class);
        registerDaoClass(ICDXXDao.class);
        registerDaoClass(DWJCDao.class);
        registerDaoClass(BHXHRJBBDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }

    /**
     * Calls {@link #createAllTables(Database, boolean)} in {@link #onCreate(Database)} -
     */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

}