package cn.com.sgcc.dev.dao2;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.com.sgcc.dev.model2.CCXX;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CCXX".
*/
public class CCXXDao extends AbstractDao<CCXX, Long> {

    public static final String TABLENAME = "CCXX";

    /**
     * Properties of entity CCXX.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID");
        public final static Property State = new Property(1, String.class, "state", false, "STATE");
        public final static Property Fwsf = new Property(2, String.class, "fwsf", false, "FWSF");
        public final static Property Sfsbm = new Property(3, String.class, "sfsbm", false, "SFSBM");
        public final static Property Zzcj = new Property(4, String.class, "zzcj", false, "ZZCJ");
        public final static Property Bhlb = new Property(5, String.class, "bhlb", false, "BHLB");
        public final static Property Bhxh = new Property(6, String.class, "bhxh", false, "BHXH");
        public final static Property Bhfl = new Property(7, String.class, "bhfl", false, "BHFL");
        public final static Property Bhlx = new Property(8, String.class, "bhlx", false, "BHLX");
        public final static Property Xp = new Property(9, String.class, "xp", false, "XP");
        public final static Property Sydydj = new Property(10, String.class, "sydydj", false, "SYDYDJ");
        public final static Property Wjbb = new Property(11, String.class, "wjbb", false, "WJBB");
        public final static Property Wjmc = new Property(12, String.class, "wjmc", false, "WJMC");
        public final static Property Crc32 = new Property(13, String.class, "crc32", false, "CRC32");
        public final static Property Scrq = new Property(14, String.class, "scrq", false, "SCRQ");
        public final static Property Ccrq = new Property(15, String.class, "ccrq", false, "CCRQ");
        public final static Property Bksl = new Property(16, int.class, "bksl", false, "BKSL");
        public final static Property Hgqlx = new Property(17, String.class, "hgqlx", false, "HGQLX");
        public final static Property Sblbxh = new Property(18, String.class, "sblbxh", false, "SBLBXH");
        public final static Property Sbgnpz = new Property(19, String.class, "sbgnpz", false, "SBGNPZ");
        public final static Property Type = new Property(20, String.class, "type", false, "TYPE");
        public final static Property Sfltysb = new Property(21, String.class, "sfltysb", false, "SFLTYSB");
        public final static Property Ltybzbb = new Property(22, String.class, "ltybzbb", false, "LTYBZBB");
        public final static Property Bblx = new Property(23, String.class, "bblx", false, "BBLX");
        public final static Property Sffbzyjcgg = new Property(24, String.class, "sffbzyjcgg", false, "SFFBZYJCGG");
        public final static Property Md5m = new Property(25, String.class, "md5m", false, "MD5M");
        public final static Property Wjscrq = new Property(26, String.class, "wjscrq", false, "WJSCRQ");
        public final static Property Eceddl = new Property(27, String.class, "eceddl", false, "ECEDDL");
        public final static Property Zleddy = new Property(28, String.class, "zleddy", false, "ZLEDDY");
        public final static Property Tdlx = new Property(29, String.class, "tdlx", false, "TDLX");
        public final static Property Sfjdhzz = new Property(30, String.class, "sfjdhzz", false, "SFJDHZZ");
        public final static Property Dzbqzzcj = new Property(31, String.class, "dzbqzzcj", false, "DZBQZZCJ");
        public final static Property Dzbqxh = new Property(32, String.class, "dzbqxh", false, "DZBQXH");
        public final static Property Ljqsl = new Property(33, String.class, "ljqsl", false, "LJQSL");
        public final static Property Ljqzzcj = new Property(34, String.class, "ljqzzcj", false, "LJQZZCJ");
        public final static Property Ljqxh = new Property(35, String.class, "ljqxh", false, "LJQXH");
        public final static Property Akzdlx = new Property(36, String.class, "akzdlx", false, "AKZDLX");
        public final static Property Jsgxjkms = new Property(37, String.class, "jsgxjkms", false, "JSGXJKMS");
        public final static Property Jhjgn = new Property(38, String.class, "jhjgn", false, "JHJGN");
        public final static Property Jhjjls = new Property(39, int.class, "jhjjls", false, "JHJJLS");
        public final static Property Sfzcrstphw = new Property(40, String.class, "sfzcrstphw", false, "SFZCRSTPHW");
        public final static Property Sfzcds = new Property(41, String.class, "sfzcds", false, "SFZCDS");
        public final static Property Sfzcdtzbgl = new Property(42, String.class, "sfzcdtzbgl", false, "SFZCDTZBGL");
        public final static Property Sfzccyiec = new Property(43, String.class, "sfzccyiec", false, "SFZCCYIEC");
    }


    public CCXXDao(DaoConfig config) {
        super(config);
    }
    
    public CCXXDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CCXX entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String state = entity.getState();
        if (state != null) {
            stmt.bindString(2, state);
        }
 
        String fwsf = entity.getFwsf();
        if (fwsf != null) {
            stmt.bindString(3, fwsf);
        }
 
        String sfsbm = entity.getSfsbm();
        if (sfsbm != null) {
            stmt.bindString(4, sfsbm);
        }
 
        String zzcj = entity.getZzcj();
        if (zzcj != null) {
            stmt.bindString(5, zzcj);
        }
 
        String bhlb = entity.getBhlb();
        if (bhlb != null) {
            stmt.bindString(6, bhlb);
        }
 
        String bhxh = entity.getBhxh();
        if (bhxh != null) {
            stmt.bindString(7, bhxh);
        }
 
        String bhfl = entity.getBhfl();
        if (bhfl != null) {
            stmt.bindString(8, bhfl);
        }
 
        String bhlx = entity.getBhlx();
        if (bhlx != null) {
            stmt.bindString(9, bhlx);
        }
 
        String xp = entity.getXp();
        if (xp != null) {
            stmt.bindString(10, xp);
        }
 
        String sydydj = entity.getSydydj();
        if (sydydj != null) {
            stmt.bindString(11, sydydj);
        }
 
        String wjbb = entity.getWjbb();
        if (wjbb != null) {
            stmt.bindString(12, wjbb);
        }
 
        String wjmc = entity.getWjmc();
        if (wjmc != null) {
            stmt.bindString(13, wjmc);
        }
 
        String crc32 = entity.getCrc32();
        if (crc32 != null) {
            stmt.bindString(14, crc32);
        }
 
        String scrq = entity.getScrq();
        if (scrq != null) {
            stmt.bindString(15, scrq);
        }
 
        String ccrq = entity.getCcrq();
        if (ccrq != null) {
            stmt.bindString(16, ccrq);
        }
        stmt.bindLong(17, entity.getBksl());
 
        String hgqlx = entity.getHgqlx();
        if (hgqlx != null) {
            stmt.bindString(18, hgqlx);
        }
 
        String sblbxh = entity.getSblbxh();
        if (sblbxh != null) {
            stmt.bindString(19, sblbxh);
        }
 
        String sbgnpz = entity.getSbgnpz();
        if (sbgnpz != null) {
            stmt.bindString(20, sbgnpz);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(21, type);
        }
 
        String sfltysb = entity.getSfltysb();
        if (sfltysb != null) {
            stmt.bindString(22, sfltysb);
        }
 
        String ltybzbb = entity.getLtybzbb();
        if (ltybzbb != null) {
            stmt.bindString(23, ltybzbb);
        }
 
        String bblx = entity.getBblx();
        if (bblx != null) {
            stmt.bindString(24, bblx);
        }
 
        String sffbzyjcgg = entity.getSffbzyjcgg();
        if (sffbzyjcgg != null) {
            stmt.bindString(25, sffbzyjcgg);
        }
 
        String md5m = entity.getMd5m();
        if (md5m != null) {
            stmt.bindString(26, md5m);
        }
 
        String wjscrq = entity.getWjscrq();
        if (wjscrq != null) {
            stmt.bindString(27, wjscrq);
        }
 
        String eceddl = entity.getEceddl();
        if (eceddl != null) {
            stmt.bindString(28, eceddl);
        }
 
        String zleddy = entity.getZleddy();
        if (zleddy != null) {
            stmt.bindString(29, zleddy);
        }
 
        String tdlx = entity.getTdlx();
        if (tdlx != null) {
            stmt.bindString(30, tdlx);
        }
 
        String sfjdhzz = entity.getSfjdhzz();
        if (sfjdhzz != null) {
            stmt.bindString(31, sfjdhzz);
        }
 
        String dzbqzzcj = entity.getDzbqzzcj();
        if (dzbqzzcj != null) {
            stmt.bindString(32, dzbqzzcj);
        }
 
        String dzbqxh = entity.getDzbqxh();
        if (dzbqxh != null) {
            stmt.bindString(33, dzbqxh);
        }
 
        String ljqsl = entity.getLjqsl();
        if (ljqsl != null) {
            stmt.bindString(34, ljqsl);
        }
 
        String ljqzzcj = entity.getLjqzzcj();
        if (ljqzzcj != null) {
            stmt.bindString(35, ljqzzcj);
        }
 
        String ljqxh = entity.getLjqxh();
        if (ljqxh != null) {
            stmt.bindString(36, ljqxh);
        }
 
        String akzdlx = entity.getAkzdlx();
        if (akzdlx != null) {
            stmt.bindString(37, akzdlx);
        }
 
        String jsgxjkms = entity.getJsgxjkms();
        if (jsgxjkms != null) {
            stmt.bindString(38, jsgxjkms);
        }
 
        String jhjgn = entity.getJhjgn();
        if (jhjgn != null) {
            stmt.bindString(39, jhjgn);
        }
        stmt.bindLong(40, entity.getJhjjls());
 
        String sfzcrstphw = entity.getSfzcrstphw();
        if (sfzcrstphw != null) {
            stmt.bindString(41, sfzcrstphw);
        }
 
        String sfzcds = entity.getSfzcds();
        if (sfzcds != null) {
            stmt.bindString(42, sfzcds);
        }
 
        String sfzcdtzbgl = entity.getSfzcdtzbgl();
        if (sfzcdtzbgl != null) {
            stmt.bindString(43, sfzcdtzbgl);
        }
 
        String sfzccyiec = entity.getSfzccyiec();
        if (sfzccyiec != null) {
            stmt.bindString(44, sfzccyiec);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CCXX entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String state = entity.getState();
        if (state != null) {
            stmt.bindString(2, state);
        }
 
        String fwsf = entity.getFwsf();
        if (fwsf != null) {
            stmt.bindString(3, fwsf);
        }
 
        String sfsbm = entity.getSfsbm();
        if (sfsbm != null) {
            stmt.bindString(4, sfsbm);
        }
 
        String zzcj = entity.getZzcj();
        if (zzcj != null) {
            stmt.bindString(5, zzcj);
        }
 
        String bhlb = entity.getBhlb();
        if (bhlb != null) {
            stmt.bindString(6, bhlb);
        }
 
        String bhxh = entity.getBhxh();
        if (bhxh != null) {
            stmt.bindString(7, bhxh);
        }
 
        String bhfl = entity.getBhfl();
        if (bhfl != null) {
            stmt.bindString(8, bhfl);
        }
 
        String bhlx = entity.getBhlx();
        if (bhlx != null) {
            stmt.bindString(9, bhlx);
        }
 
        String xp = entity.getXp();
        if (xp != null) {
            stmt.bindString(10, xp);
        }
 
        String sydydj = entity.getSydydj();
        if (sydydj != null) {
            stmt.bindString(11, sydydj);
        }
 
        String wjbb = entity.getWjbb();
        if (wjbb != null) {
            stmt.bindString(12, wjbb);
        }
 
        String wjmc = entity.getWjmc();
        if (wjmc != null) {
            stmt.bindString(13, wjmc);
        }
 
        String crc32 = entity.getCrc32();
        if (crc32 != null) {
            stmt.bindString(14, crc32);
        }
 
        String scrq = entity.getScrq();
        if (scrq != null) {
            stmt.bindString(15, scrq);
        }
 
        String ccrq = entity.getCcrq();
        if (ccrq != null) {
            stmt.bindString(16, ccrq);
        }
        stmt.bindLong(17, entity.getBksl());
 
        String hgqlx = entity.getHgqlx();
        if (hgqlx != null) {
            stmt.bindString(18, hgqlx);
        }
 
        String sblbxh = entity.getSblbxh();
        if (sblbxh != null) {
            stmt.bindString(19, sblbxh);
        }
 
        String sbgnpz = entity.getSbgnpz();
        if (sbgnpz != null) {
            stmt.bindString(20, sbgnpz);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(21, type);
        }
 
        String sfltysb = entity.getSfltysb();
        if (sfltysb != null) {
            stmt.bindString(22, sfltysb);
        }
 
        String ltybzbb = entity.getLtybzbb();
        if (ltybzbb != null) {
            stmt.bindString(23, ltybzbb);
        }
 
        String bblx = entity.getBblx();
        if (bblx != null) {
            stmt.bindString(24, bblx);
        }
 
        String sffbzyjcgg = entity.getSffbzyjcgg();
        if (sffbzyjcgg != null) {
            stmt.bindString(25, sffbzyjcgg);
        }
 
        String md5m = entity.getMd5m();
        if (md5m != null) {
            stmt.bindString(26, md5m);
        }
 
        String wjscrq = entity.getWjscrq();
        if (wjscrq != null) {
            stmt.bindString(27, wjscrq);
        }
 
        String eceddl = entity.getEceddl();
        if (eceddl != null) {
            stmt.bindString(28, eceddl);
        }
 
        String zleddy = entity.getZleddy();
        if (zleddy != null) {
            stmt.bindString(29, zleddy);
        }
 
        String tdlx = entity.getTdlx();
        if (tdlx != null) {
            stmt.bindString(30, tdlx);
        }
 
        String sfjdhzz = entity.getSfjdhzz();
        if (sfjdhzz != null) {
            stmt.bindString(31, sfjdhzz);
        }
 
        String dzbqzzcj = entity.getDzbqzzcj();
        if (dzbqzzcj != null) {
            stmt.bindString(32, dzbqzzcj);
        }
 
        String dzbqxh = entity.getDzbqxh();
        if (dzbqxh != null) {
            stmt.bindString(33, dzbqxh);
        }
 
        String ljqsl = entity.getLjqsl();
        if (ljqsl != null) {
            stmt.bindString(34, ljqsl);
        }
 
        String ljqzzcj = entity.getLjqzzcj();
        if (ljqzzcj != null) {
            stmt.bindString(35, ljqzzcj);
        }
 
        String ljqxh = entity.getLjqxh();
        if (ljqxh != null) {
            stmt.bindString(36, ljqxh);
        }
 
        String akzdlx = entity.getAkzdlx();
        if (akzdlx != null) {
            stmt.bindString(37, akzdlx);
        }
 
        String jsgxjkms = entity.getJsgxjkms();
        if (jsgxjkms != null) {
            stmt.bindString(38, jsgxjkms);
        }
 
        String jhjgn = entity.getJhjgn();
        if (jhjgn != null) {
            stmt.bindString(39, jhjgn);
        }
        stmt.bindLong(40, entity.getJhjjls());
 
        String sfzcrstphw = entity.getSfzcrstphw();
        if (sfzcrstphw != null) {
            stmt.bindString(41, sfzcrstphw);
        }
 
        String sfzcds = entity.getSfzcds();
        if (sfzcds != null) {
            stmt.bindString(42, sfzcds);
        }
 
        String sfzcdtzbgl = entity.getSfzcdtzbgl();
        if (sfzcdtzbgl != null) {
            stmt.bindString(43, sfzcdtzbgl);
        }
 
        String sfzccyiec = entity.getSfzccyiec();
        if (sfzccyiec != null) {
            stmt.bindString(44, sfzccyiec);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CCXX readEntity(Cursor cursor, int offset) {
        CCXX entity = new CCXX( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // state
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // fwsf
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // sfsbm
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // zzcj
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // bhlb
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // bhxh
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // bhfl
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // bhlx
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // xp
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // sydydj
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // wjbb
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // wjmc
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // crc32
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // scrq
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // ccrq
            cursor.getInt(offset + 16), // bksl
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // hgqlx
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // sblbxh
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // sbgnpz
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // type
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // sfltysb
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // ltybzbb
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // bblx
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // sffbzyjcgg
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // md5m
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // wjscrq
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // eceddl
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // zleddy
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // tdlx
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // sfjdhzz
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // dzbqzzcj
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // dzbqxh
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // ljqsl
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34), // ljqzzcj
            cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35), // ljqxh
            cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36), // akzdlx
            cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37), // jsgxjkms
            cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38), // jhjgn
            cursor.getInt(offset + 39), // jhjjls
            cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40), // sfzcrstphw
            cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41), // sfzcds
            cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42), // sfzcdtzbgl
            cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43) // sfzccyiec
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CCXX entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setState(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFwsf(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSfsbm(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setZzcj(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setBhlb(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setBhxh(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setBhfl(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setBhlx(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setXp(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setSydydj(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setWjbb(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setWjmc(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setCrc32(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setScrq(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setCcrq(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setBksl(cursor.getInt(offset + 16));
        entity.setHgqlx(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setSblbxh(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setSbgnpz(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setType(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setSfltysb(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setLtybzbb(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setBblx(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setSffbzyjcgg(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setMd5m(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setWjscrq(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setEceddl(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setZleddy(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setTdlx(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setSfjdhzz(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setDzbqzzcj(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setDzbqxh(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setLjqsl(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setLjqzzcj(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
        entity.setLjqxh(cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35));
        entity.setAkzdlx(cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36));
        entity.setJsgxjkms(cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37));
        entity.setJhjgn(cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38));
        entity.setJhjjls(cursor.getInt(offset + 39));
        entity.setSfzcrstphw(cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40));
        entity.setSfzcds(cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41));
        entity.setSfzcdtzbgl(cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42));
        entity.setSfzccyiec(cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CCXX entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CCXX entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CCXX entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
