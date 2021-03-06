package cn.com.sgcc.dev.dao2;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.com.sgcc.dev.model2.ycsb.MXCS;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MXCS".
*/
public class MXCSDao extends AbstractDao<MXCS, Integer> {

    public static final String TABLENAME = "MXCS";

    /**
     * Properties of entity MXCS.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, int.class, "id", true, "ID");
        public final static Property BH = new Property(1, String.class, "BH", false, "BH");
        public final static Property MXMC = new Property(2, String.class, "MXMC", false, "MXMC");
        public final static Property CZMC = new Property(3, String.class, "CZMC", false, "CZMC");
        public final static Property DYDJ = new Property(4, int.class, "DYDJ", false, "DYDJ");
        public final static Property KGBH = new Property(5, String.class, "KGBH", false, "KGBH");
        public final static Property MXLJFS = new Property(6, String.class, "MXLJFS", false, "MXLJFS");
        public final static Property MXTS = new Property(7, int.class, "MXTS", false, "MXTS");
        public final static Property SFTCYX = new Property(8, String.class, "SFTCYX", false, "SFTCYX");
        public final static Property BZ = new Property(9, String.class, "BZ", false, "BZ");
        public final static Property Sh = new Property(10, String.class, "sh", false, "SH");
        public final static Property Shr2 = new Property(11, String.class, "shr2", false, "SHR");
        public final static Property Shyy = new Property(12, String.class, "shyy", false, "SHYY");
        public final static Property GLDW = new Property(13, String.class, "GLDW", false, "GLDW");
        public final static Property Vf = new Property(14, int.class, "vf", false, "VF");
        public final static Property WDID = new Property(15, int.class, "WDID", false, "WDID");
        public final static Property Dddw = new Property(16, String.class, "dddw", false, "DDDW");
        public final static Property Czr = new Property(17, String.class, "czr", false, "CZR");
        public final static Property Tjr = new Property(18, String.class, "tjr", false, "TJR");
        public final static Property Dwtjr = new Property(19, String.class, "dwtjr", false, "DWTJR");
        public final static Property Jglx = new Property(20, String.class, "jglx", false, "JGLX");
        public final static Property Jgmc = new Property(21, String.class, "jgmc", false, "JGMC");
        public final static Property CZSJ = new Property(22, java.util.Date.class, "CZSJ", false, "CZSJ");
        public final static Property SFBDSJ = new Property(23, String.class, "SFBDSJ", false, "SFBDSJ");
        public final static Property SB = new Property(24, String.class, "SB", false, "SB");
        public final static Property SBSJ = new Property(25, java.util.Date.class, "SBSJ", false, "SBSJ");
        public final static Property SB_LS_ID = new Property(26, String.class, "SB_LS_ID", false, "SB_LS_ID");
        public final static Property SBDW = new Property(27, String.class, "SBDW", false, "SBDW");
        public final static Property HZSJ = new Property(28, java.util.Date.class, "HZSJ", false, "HZSJ");
        public final static Property SBCZLX = new Property(29, String.class, "SBCZLX", false, "SBCZLX");
        public final static Property SFXTLR = new Property(30, String.class, "SFXTLR", false, "SFXTLR");
        public final static Property WJ_DW = new Property(31, String.class, "WJ_DW", false, "WJ_DW");
        public final static Property WJ_LS_ID = new Property(32, String.class, "WJ_LS_ID", false, "WJ_LS_ID");
    }


    public MXCSDao(DaoConfig config) {
        super(config);
    }
    
    public MXCSDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MXCS entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String BH = entity.getBH();
        if (BH != null) {
            stmt.bindString(2, BH);
        }
 
        String MXMC = entity.getMXMC();
        if (MXMC != null) {
            stmt.bindString(3, MXMC);
        }
 
        String CZMC = entity.getCZMC();
        if (CZMC != null) {
            stmt.bindString(4, CZMC);
        }
        stmt.bindLong(5, entity.getDYDJ());
 
        String KGBH = entity.getKGBH();
        if (KGBH != null) {
            stmt.bindString(6, KGBH);
        }
 
        String MXLJFS = entity.getMXLJFS();
        if (MXLJFS != null) {
            stmt.bindString(7, MXLJFS);
        }
        stmt.bindLong(8, entity.getMXTS());
 
        String SFTCYX = entity.getSFTCYX();
        if (SFTCYX != null) {
            stmt.bindString(9, SFTCYX);
        }
 
        String BZ = entity.getBZ();
        if (BZ != null) {
            stmt.bindString(10, BZ);
        }
 
        String sh = entity.getSh();
        if (sh != null) {
            stmt.bindString(11, sh);
        }
 
        String shr2 = entity.getShr2();
        if (shr2 != null) {
            stmt.bindString(12, shr2);
        }
 
        String shyy = entity.getShyy();
        if (shyy != null) {
            stmt.bindString(13, shyy);
        }
 
        String GLDW = entity.getGLDW();
        if (GLDW != null) {
            stmt.bindString(14, GLDW);
        }
        stmt.bindLong(15, entity.getVf());
        stmt.bindLong(16, entity.getWDID());
 
        String dddw = entity.getDddw();
        if (dddw != null) {
            stmt.bindString(17, dddw);
        }
 
        String czr = entity.getCzr();
        if (czr != null) {
            stmt.bindString(18, czr);
        }
 
        String tjr = entity.getTjr();
        if (tjr != null) {
            stmt.bindString(19, tjr);
        }
 
        String dwtjr = entity.getDwtjr();
        if (dwtjr != null) {
            stmt.bindString(20, dwtjr);
        }
 
        String jglx = entity.getJglx();
        if (jglx != null) {
            stmt.bindString(21, jglx);
        }
 
        String jgmc = entity.getJgmc();
        if (jgmc != null) {
            stmt.bindString(22, jgmc);
        }
 
        java.util.Date CZSJ = entity.getCZSJ();
        if (CZSJ != null) {
            stmt.bindLong(23, CZSJ.getTime());
        }
 
        String SFBDSJ = entity.getSFBDSJ();
        if (SFBDSJ != null) {
            stmt.bindString(24, SFBDSJ);
        }
 
        String SB = entity.getSB();
        if (SB != null) {
            stmt.bindString(25, SB);
        }
 
        java.util.Date SBSJ = entity.getSBSJ();
        if (SBSJ != null) {
            stmt.bindLong(26, SBSJ.getTime());
        }
 
        String SB_LS_ID = entity.getSB_LS_ID();
        if (SB_LS_ID != null) {
            stmt.bindString(27, SB_LS_ID);
        }
 
        String SBDW = entity.getSBDW();
        if (SBDW != null) {
            stmt.bindString(28, SBDW);
        }
 
        java.util.Date HZSJ = entity.getHZSJ();
        if (HZSJ != null) {
            stmt.bindLong(29, HZSJ.getTime());
        }
 
        String SBCZLX = entity.getSBCZLX();
        if (SBCZLX != null) {
            stmt.bindString(30, SBCZLX);
        }
 
        String SFXTLR = entity.getSFXTLR();
        if (SFXTLR != null) {
            stmt.bindString(31, SFXTLR);
        }
 
        String WJ_DW = entity.getWJ_DW();
        if (WJ_DW != null) {
            stmt.bindString(32, WJ_DW);
        }
 
        String WJ_LS_ID = entity.getWJ_LS_ID();
        if (WJ_LS_ID != null) {
            stmt.bindString(33, WJ_LS_ID);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MXCS entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String BH = entity.getBH();
        if (BH != null) {
            stmt.bindString(2, BH);
        }
 
        String MXMC = entity.getMXMC();
        if (MXMC != null) {
            stmt.bindString(3, MXMC);
        }
 
        String CZMC = entity.getCZMC();
        if (CZMC != null) {
            stmt.bindString(4, CZMC);
        }
        stmt.bindLong(5, entity.getDYDJ());
 
        String KGBH = entity.getKGBH();
        if (KGBH != null) {
            stmt.bindString(6, KGBH);
        }
 
        String MXLJFS = entity.getMXLJFS();
        if (MXLJFS != null) {
            stmt.bindString(7, MXLJFS);
        }
        stmt.bindLong(8, entity.getMXTS());
 
        String SFTCYX = entity.getSFTCYX();
        if (SFTCYX != null) {
            stmt.bindString(9, SFTCYX);
        }
 
        String BZ = entity.getBZ();
        if (BZ != null) {
            stmt.bindString(10, BZ);
        }
 
        String sh = entity.getSh();
        if (sh != null) {
            stmt.bindString(11, sh);
        }
 
        String shr2 = entity.getShr2();
        if (shr2 != null) {
            stmt.bindString(12, shr2);
        }
 
        String shyy = entity.getShyy();
        if (shyy != null) {
            stmt.bindString(13, shyy);
        }
 
        String GLDW = entity.getGLDW();
        if (GLDW != null) {
            stmt.bindString(14, GLDW);
        }
        stmt.bindLong(15, entity.getVf());
        stmt.bindLong(16, entity.getWDID());
 
        String dddw = entity.getDddw();
        if (dddw != null) {
            stmt.bindString(17, dddw);
        }
 
        String czr = entity.getCzr();
        if (czr != null) {
            stmt.bindString(18, czr);
        }
 
        String tjr = entity.getTjr();
        if (tjr != null) {
            stmt.bindString(19, tjr);
        }
 
        String dwtjr = entity.getDwtjr();
        if (dwtjr != null) {
            stmt.bindString(20, dwtjr);
        }
 
        String jglx = entity.getJglx();
        if (jglx != null) {
            stmt.bindString(21, jglx);
        }
 
        String jgmc = entity.getJgmc();
        if (jgmc != null) {
            stmt.bindString(22, jgmc);
        }
 
        java.util.Date CZSJ = entity.getCZSJ();
        if (CZSJ != null) {
            stmt.bindLong(23, CZSJ.getTime());
        }
 
        String SFBDSJ = entity.getSFBDSJ();
        if (SFBDSJ != null) {
            stmt.bindString(24, SFBDSJ);
        }
 
        String SB = entity.getSB();
        if (SB != null) {
            stmt.bindString(25, SB);
        }
 
        java.util.Date SBSJ = entity.getSBSJ();
        if (SBSJ != null) {
            stmt.bindLong(26, SBSJ.getTime());
        }
 
        String SB_LS_ID = entity.getSB_LS_ID();
        if (SB_LS_ID != null) {
            stmt.bindString(27, SB_LS_ID);
        }
 
        String SBDW = entity.getSBDW();
        if (SBDW != null) {
            stmt.bindString(28, SBDW);
        }
 
        java.util.Date HZSJ = entity.getHZSJ();
        if (HZSJ != null) {
            stmt.bindLong(29, HZSJ.getTime());
        }
 
        String SBCZLX = entity.getSBCZLX();
        if (SBCZLX != null) {
            stmt.bindString(30, SBCZLX);
        }
 
        String SFXTLR = entity.getSFXTLR();
        if (SFXTLR != null) {
            stmt.bindString(31, SFXTLR);
        }
 
        String WJ_DW = entity.getWJ_DW();
        if (WJ_DW != null) {
            stmt.bindString(32, WJ_DW);
        }
 
        String WJ_LS_ID = entity.getWJ_LS_ID();
        if (WJ_LS_ID != null) {
            stmt.bindString(33, WJ_LS_ID);
        }
    }

    @Override
    public Integer readKey(Cursor cursor, int offset) {
        return cursor.getInt(offset + 0);
    }    

    @Override
    public MXCS readEntity(Cursor cursor, int offset) {
        MXCS entity = new MXCS( //
            cursor.getInt(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // BH
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // MXMC
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // CZMC
            cursor.getInt(offset + 4), // DYDJ
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // KGBH
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // MXLJFS
            cursor.getInt(offset + 7), // MXTS
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // SFTCYX
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // BZ
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // sh
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // shr2
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // shyy
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // GLDW
            cursor.getInt(offset + 14), // vf
            cursor.getInt(offset + 15), // WDID
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // dddw
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // czr
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // tjr
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // dwtjr
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // jglx
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // jgmc
            cursor.isNull(offset + 22) ? null : new java.util.Date(cursor.getLong(offset + 22)), // CZSJ
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // SFBDSJ
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // SB
            cursor.isNull(offset + 25) ? null : new java.util.Date(cursor.getLong(offset + 25)), // SBSJ
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // SB_LS_ID
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // SBDW
            cursor.isNull(offset + 28) ? null : new java.util.Date(cursor.getLong(offset + 28)), // HZSJ
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // SBCZLX
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // SFXTLR
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // WJ_DW
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32) // WJ_LS_ID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MXCS entity, int offset) {
        entity.setId(cursor.getInt(offset + 0));
        entity.setBH(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMXMC(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCZMC(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDYDJ(cursor.getInt(offset + 4));
        entity.setKGBH(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMXLJFS(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMXTS(cursor.getInt(offset + 7));
        entity.setSFTCYX(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setBZ(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setSh(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setShr2(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setShyy(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setGLDW(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setVf(cursor.getInt(offset + 14));
        entity.setWDID(cursor.getInt(offset + 15));
        entity.setDddw(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setCzr(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setTjr(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setDwtjr(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setJglx(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setJgmc(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setCZSJ(cursor.isNull(offset + 22) ? null : new java.util.Date(cursor.getLong(offset + 22)));
        entity.setSFBDSJ(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setSB(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setSBSJ(cursor.isNull(offset + 25) ? null : new java.util.Date(cursor.getLong(offset + 25)));
        entity.setSB_LS_ID(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setSBDW(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setHZSJ(cursor.isNull(offset + 28) ? null : new java.util.Date(cursor.getLong(offset + 28)));
        entity.setSBCZLX(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setSFXTLR(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setWJ_DW(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setWJ_LS_ID(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
     }
    
    @Override
    protected final Integer updateKeyAfterInsert(MXCS entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public Integer getKey(MXCS entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MXCS entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
