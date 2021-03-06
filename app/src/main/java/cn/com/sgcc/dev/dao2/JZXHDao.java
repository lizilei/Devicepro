package cn.com.sgcc.dev.dao2;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.com.sgcc.dev.model2.JZXH;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "JZXH".
*/
public class JZXHDao extends AbstractDao<JZXH, Long> {

    public static final String TABLENAME = "JZXH";

    /**
     * Properties of entity JZXH.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "ID", true, "ID");
        public final static Property ZZCJ = new Property(1, String.class, "ZZCJ", false, "ZZCJ");
        public final static Property JZXH = new Property(2, String.class, "JZXH", false, "JZXH");
        public final static Property SFZXWH = new Property(3, String.class, "SFZXWH", false, "SFZXWH");
        public final static Property STATE = new Property(4, String.class, "STATE", false, "STATE");
        public final static Property VERSION = new Property(5, String.class, "VERSION", false, "VERSION");
        public final static Property TIME = new Property(6, String.class, "TIME", false, "TIME");
    }


    public JZXHDao(DaoConfig config) {
        super(config);
    }
    
    public JZXHDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, JZXH entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        String ZZCJ = entity.getZZCJ();
        if (ZZCJ != null) {
            stmt.bindString(2, ZZCJ);
        }
 
        String JZXH = entity.getJZXH();
        if (JZXH != null) {
            stmt.bindString(3, JZXH);
        }
 
        String SFZXWH = entity.getSFZXWH();
        if (SFZXWH != null) {
            stmt.bindString(4, SFZXWH);
        }
 
        String STATE = entity.getSTATE();
        if (STATE != null) {
            stmt.bindString(5, STATE);
        }
 
        String VERSION = entity.getVERSION();
        if (VERSION != null) {
            stmt.bindString(6, VERSION);
        }
 
        String TIME = entity.getTIME();
        if (TIME != null) {
            stmt.bindString(7, TIME);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, JZXH entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        String ZZCJ = entity.getZZCJ();
        if (ZZCJ != null) {
            stmt.bindString(2, ZZCJ);
        }
 
        String JZXH = entity.getJZXH();
        if (JZXH != null) {
            stmt.bindString(3, JZXH);
        }
 
        String SFZXWH = entity.getSFZXWH();
        if (SFZXWH != null) {
            stmt.bindString(4, SFZXWH);
        }
 
        String STATE = entity.getSTATE();
        if (STATE != null) {
            stmt.bindString(5, STATE);
        }
 
        String VERSION = entity.getVERSION();
        if (VERSION != null) {
            stmt.bindString(6, VERSION);
        }
 
        String TIME = entity.getTIME();
        if (TIME != null) {
            stmt.bindString(7, TIME);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public JZXH readEntity(Cursor cursor, int offset) {
        JZXH entity = new JZXH( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // ZZCJ
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // JZXH
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // SFZXWH
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // STATE
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // VERSION
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // TIME
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, JZXH entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setZZCJ(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setJZXH(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSFZXWH(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSTATE(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setVERSION(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTIME(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(JZXH entity, long rowId) {
        entity.setID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(JZXH entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(JZXH entity) {
        return entity.getID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
