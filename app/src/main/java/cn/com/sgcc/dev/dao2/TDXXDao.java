package cn.com.sgcc.dev.dao2;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.com.sgcc.dev.model2.TDXX;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TDXX".
*/
public class TDXXDao extends AbstractDao<TDXX, Long> {

    public static final String TABLENAME = "TDXX";

    /**
     * Properties of entity TDXX.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID");
        public final static Property Tdlx = new Property(1, String.class, "tdlx", false, "TDLX");
        public final static Property Sffy = new Property(2, String.class, "sffy", false, "SFFY");
        public final static Property Tdzzxh = new Property(3, String.class, "tdzzxh", false, "TDZZXH");
        public final static Property Sfxtlr = new Property(4, String.class, "sfxtlr", false, "SFXTLR");
    }


    public TDXXDao(DaoConfig config) {
        super(config);
    }
    
    public TDXXDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TDXX entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String tdlx = entity.getTdlx();
        if (tdlx != null) {
            stmt.bindString(2, tdlx);
        }
 
        String sffy = entity.getSffy();
        if (sffy != null) {
            stmt.bindString(3, sffy);
        }
 
        String tdzzxh = entity.getTdzzxh();
        if (tdzzxh != null) {
            stmt.bindString(4, tdzzxh);
        }
 
        String sfxtlr = entity.getSfxtlr();
        if (sfxtlr != null) {
            stmt.bindString(5, sfxtlr);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TDXX entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String tdlx = entity.getTdlx();
        if (tdlx != null) {
            stmt.bindString(2, tdlx);
        }
 
        String sffy = entity.getSffy();
        if (sffy != null) {
            stmt.bindString(3, sffy);
        }
 
        String tdzzxh = entity.getTdzzxh();
        if (tdzzxh != null) {
            stmt.bindString(4, tdzzxh);
        }
 
        String sfxtlr = entity.getSfxtlr();
        if (sfxtlr != null) {
            stmt.bindString(5, sfxtlr);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TDXX readEntity(Cursor cursor, int offset) {
        TDXX entity = new TDXX( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // tdlx
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // sffy
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // tdzzxh
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // sfxtlr
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TDXX entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTdlx(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSffy(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTdzzxh(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSfxtlr(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TDXX entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TDXX entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TDXX entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
