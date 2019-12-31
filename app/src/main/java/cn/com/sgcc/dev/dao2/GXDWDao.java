package cn.com.sgcc.dev.dao2;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.com.sgcc.dev.model2.GXDW;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GXDW".
*/
public class GXDWDao extends AbstractDao<GXDW, Long> {

    public static final String TABLENAME = "GXDW";

    /**
     * Properties of entity GXDW.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "ID", true, "ID");
        public final static Property BH = new Property(1, String.class, "BH", false, "BH");
        public final static Property DWMC = new Property(2, String.class, "DWMC", false, "DWMC");
        public final static Property JSSJY = new Property(3, String.class, "JSSJY", false, "JSSJY");
        public final static Property BZ = new Property(4, String.class, "BZ", false, "BZ");
        public final static Property DWLB = new Property(5, String.class, "DWLB", false, "DWLB");
        public final static Property VF = new Property(6, int.class, "VF", false, "VF");
        public final static Property GLDW = new Property(7, String.class, "GLDW", false, "GLDW");
        public final static Property FL = new Property(8, String.class, "FL", false, "FL");
        public final static Property CZR = new Property(9, String.class, "CZR", false, "CZR");
        public final static Property WDID = new Property(10, int.class, "WDID", false, "WDID");
        public final static Property WZSDWLB = new Property(11, String.class, "WZSDWLB", false, "WZSDWLB");
        public final static Property DDDW = new Property(12, String.class, "DDDW", false, "DDDW");
        public final static Property SFXTLR = new Property(13, String.class, "SFXTLR", false, "SFXTLR");
    }


    public GXDWDao(DaoConfig config) {
        super(config);
    }
    
    public GXDWDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GXDW entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        String BH = entity.getBH();
        if (BH != null) {
            stmt.bindString(2, BH);
        }
 
        String DWMC = entity.getDWMC();
        if (DWMC != null) {
            stmt.bindString(3, DWMC);
        }
 
        String JSSJY = entity.getJSSJY();
        if (JSSJY != null) {
            stmt.bindString(4, JSSJY);
        }
 
        String BZ = entity.getBZ();
        if (BZ != null) {
            stmt.bindString(5, BZ);
        }
 
        String DWLB = entity.getDWLB();
        if (DWLB != null) {
            stmt.bindString(6, DWLB);
        }
        stmt.bindLong(7, entity.getVF());
 
        String GLDW = entity.getGLDW();
        if (GLDW != null) {
            stmt.bindString(8, GLDW);
        }
 
        String FL = entity.getFL();
        if (FL != null) {
            stmt.bindString(9, FL);
        }
 
        String CZR = entity.getCZR();
        if (CZR != null) {
            stmt.bindString(10, CZR);
        }
        stmt.bindLong(11, entity.getWDID());
 
        String WZSDWLB = entity.getWZSDWLB();
        if (WZSDWLB != null) {
            stmt.bindString(12, WZSDWLB);
        }
 
        String DDDW = entity.getDDDW();
        if (DDDW != null) {
            stmt.bindString(13, DDDW);
        }
 
        String SFXTLR = entity.getSFXTLR();
        if (SFXTLR != null) {
            stmt.bindString(14, SFXTLR);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GXDW entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        String BH = entity.getBH();
        if (BH != null) {
            stmt.bindString(2, BH);
        }
 
        String DWMC = entity.getDWMC();
        if (DWMC != null) {
            stmt.bindString(3, DWMC);
        }
 
        String JSSJY = entity.getJSSJY();
        if (JSSJY != null) {
            stmt.bindString(4, JSSJY);
        }
 
        String BZ = entity.getBZ();
        if (BZ != null) {
            stmt.bindString(5, BZ);
        }
 
        String DWLB = entity.getDWLB();
        if (DWLB != null) {
            stmt.bindString(6, DWLB);
        }
        stmt.bindLong(7, entity.getVF());
 
        String GLDW = entity.getGLDW();
        if (GLDW != null) {
            stmt.bindString(8, GLDW);
        }
 
        String FL = entity.getFL();
        if (FL != null) {
            stmt.bindString(9, FL);
        }
 
        String CZR = entity.getCZR();
        if (CZR != null) {
            stmt.bindString(10, CZR);
        }
        stmt.bindLong(11, entity.getWDID());
 
        String WZSDWLB = entity.getWZSDWLB();
        if (WZSDWLB != null) {
            stmt.bindString(12, WZSDWLB);
        }
 
        String DDDW = entity.getDDDW();
        if (DDDW != null) {
            stmt.bindString(13, DDDW);
        }
 
        String SFXTLR = entity.getSFXTLR();
        if (SFXTLR != null) {
            stmt.bindString(14, SFXTLR);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public GXDW readEntity(Cursor cursor, int offset) {
        GXDW entity = new GXDW( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // BH
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // DWMC
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // JSSJY
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // BZ
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // DWLB
            cursor.getInt(offset + 6), // VF
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // GLDW
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // FL
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // CZR
            cursor.getInt(offset + 10), // WDID
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // WZSDWLB
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // DDDW
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13) // SFXTLR
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GXDW entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBH(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDWMC(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setJSSJY(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setBZ(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDWLB(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setVF(cursor.getInt(offset + 6));
        entity.setGLDW(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setFL(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCZR(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setWDID(cursor.getInt(offset + 10));
        entity.setWZSDWLB(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setDDDW(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setSFXTLR(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(GXDW entity, long rowId) {
        entity.setID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(GXDW entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GXDW entity) {
        return entity.getID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}