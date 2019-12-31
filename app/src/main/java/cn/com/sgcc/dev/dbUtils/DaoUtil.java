package cn.com.sgcc.dev.dbUtils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.os.IResultReceiver;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.sgcc.dev.BaseApplication;
import cn.com.sgcc.dev.application.App;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.dao2.AKXTDao;
import cn.com.sgcc.dev.dao2.AKXTGXDao;
import cn.com.sgcc.dev.dao2.BHLBXHDao;
import cn.com.sgcc.dev.dao2.BHPZDao;
import cn.com.sgcc.dev.dao2.BHPZXHBBGXDao;
import cn.com.sgcc.dev.dao2.BHSBXHBDao;
import cn.com.sgcc.dev.dao2.BHXHRJBBDao;
import cn.com.sgcc.dev.dao2.BKXXDao;
import cn.com.sgcc.dev.dao2.BYQCSDao;
import cn.com.sgcc.dev.dao2.BZSJDao;
import cn.com.sgcc.dev.dao2.BZSJFLDao;
import cn.com.sgcc.dev.dao2.CCXXBKDao;
import cn.com.sgcc.dev.dao2.CCXXDao;
import cn.com.sgcc.dev.dao2.CCXXRJBBDao;
import cn.com.sgcc.dev.dao2.CODEDao;
import cn.com.sgcc.dev.dao2.CZCSDao;
import cn.com.sgcc.dev.dao2.DDJCSDao;
import cn.com.sgcc.dev.dao2.DKQCSDao;
import cn.com.sgcc.dev.dao2.DLQCSDao;
import cn.com.sgcc.dev.dao2.DRQCSDao;
import cn.com.sgcc.dev.dao2.DWLXDao;
import cn.com.sgcc.dev.dao2.DaoSession;
import cn.com.sgcc.dev.dao2.FDJCSDao;
import cn.com.sgcc.dev.dao2.FZBHSBDao;
import cn.com.sgcc.dev.dao2.FZBHSBXHBBGXDao;
import cn.com.sgcc.dev.dao2.GXDWDao;
import cn.com.sgcc.dev.dao2.ICDXXDao;
import cn.com.sgcc.dev.dao2.JGCSDao;
import cn.com.sgcc.dev.dao2.LJQXXDao;
import cn.com.sgcc.dev.dao2.LTYSBXHDao;
import cn.com.sgcc.dev.dao2.MXCSDao;
import cn.com.sgcc.dev.dao2.PZTDGXDao;
import cn.com.sgcc.dev.dao2.RLST_USERDao;
import cn.com.sgcc.dev.dao2.RZGLDao;
import cn.com.sgcc.dev.dao2.TDXXDao;
import cn.com.sgcc.dev.dao2.UserInfoDao;
import cn.com.sgcc.dev.dao2.WDGLDao;
import cn.com.sgcc.dev.dao2.XLCSDao;
import cn.com.sgcc.dev.dao2.ZZCJDao;
import cn.com.sgcc.dev.model2.AKXT;
import cn.com.sgcc.dev.model2.AKXTGX;
import cn.com.sgcc.dev.model2.BDW;
import cn.com.sgcc.dev.model2.BHLBXH;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.BHPZXHBBGX;
import cn.com.sgcc.dev.model2.BHSBXHB;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.model2.BKXX;
import cn.com.sgcc.dev.model2.BZSJ;
import cn.com.sgcc.dev.model2.BZSJFL;
import cn.com.sgcc.dev.model2.CCXX;
import cn.com.sgcc.dev.model2.CCXXBK;
import cn.com.sgcc.dev.model2.CCXXRJBB;
import cn.com.sgcc.dev.model2.CODE;
import cn.com.sgcc.dev.model2.DWLX;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.FZBHSBXHBBGX;
import cn.com.sgcc.dev.model2.GXDW;
import cn.com.sgcc.dev.model2.ICDXX;
import cn.com.sgcc.dev.model2.LJQXX;
import cn.com.sgcc.dev.model2.LTYSBXH;
import cn.com.sgcc.dev.model2.PZTDGX;
import cn.com.sgcc.dev.model2.RLST_USER;
import cn.com.sgcc.dev.model2.RZGL;
import cn.com.sgcc.dev.model2.SBXX;
import cn.com.sgcc.dev.model2.SBZCXX;
import cn.com.sgcc.dev.model2.SearchEntity;
import cn.com.sgcc.dev.model2.TDXX;
import cn.com.sgcc.dev.model2.UserInfo;
import cn.com.sgcc.dev.model2.WDGL;
import cn.com.sgcc.dev.model2.ZZCJ;
import cn.com.sgcc.dev.model2.regist.DecryptKey;
import cn.com.sgcc.dev.model2.vo.SaleAttributeNameVo;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.model2.ycsb.BYQCS;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.model2.ycsb.DDJCS;
import cn.com.sgcc.dev.model2.ycsb.DKQCS;
import cn.com.sgcc.dev.model2.ycsb.DLQCS;
import cn.com.sgcc.dev.model2.ycsb.DRQCS;
import cn.com.sgcc.dev.model2.ycsb.FDJCS;
import cn.com.sgcc.dev.model2.ycsb.JGCS;
import cn.com.sgcc.dev.model2.ycsb.MXCS;
import cn.com.sgcc.dev.model2.ycsb.XLCS;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.view.activity.DemoActivity;
import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/7
 */

public class DaoUtil implements IDaoUtil {
    private DaoSession daoSession;
    private DaoSession outputDaoSession;
    private Context context;

    public DaoUtil(Context context) {
        this.context = context;
        daoSession = DBManager.getmInstance(context).getSession();
        daoSession.clear();
    }

    public DaoUtil(Context context, String ss) {
        this.context = context;
        daoSession = DBManager.getmInstance(context).getNewSession();
        daoSession.clear();
    }

    public DaoUtil(Context context, boolean isOutput, String outName) {
        this.context = context;
        if (isOutput) {
            daoSession = DBManager.getmInstance(context).getSession();
//            if (outputDaoSession != null)
//                outputDaoSession = null;
            outputDaoSession = DBManagerOut.getmInstance(context, outName).getSession();
        }
    }

    @Override
    public List<BZSJ> getBZSJ(String bzsjmc) {
        BZSJFLDao bzsjflDao = daoSession.getBZSJFLDao();
        List<BZSJFL> bzsjfl = bzsjflDao.queryBuilder().distinct().
                where(BZSJFLDao.Properties.Bzsjflmc
                        .eq(bzsjmc)).build().list();
        if (bzsjfl == null || bzsjfl.isEmpty()) {
            return null;
        }

        BZSJDao bzsjDao = daoSession.getBZSJDao();
        if (bzsjfl != null) {
            List<BZSJ> bzsjList = bzsjDao.queryBuilder().distinct().
                    where(BZSJDao.Properties.BzsjflId.eq(bzsjfl.get(0).getId()))
                    .stringOrderCollation("COLLATE LOCALIZED")
                    .orderAsc(BZSJDao.Properties.BzsjIndex)
                    .build().list();
            return bzsjList;
        }
        return null;
    }

    @Override
    public List<GXDW> getDDDWByDWMC(String dwmc) {
        GXDWDao gxdwDao = daoSession.getGXDWDao();

        List<GXDW> gxdwList;
        if (dwmc == null) {
            gxdwList = gxdwDao.queryBuilder().distinct().build().list();
        } else {
            gxdwList = gxdwDao.queryBuilder().where(GXDWDao.Properties.DWMC.eq(dwmc)).build().list();
        }
        return gxdwList;
    }

    @Override
    public CZCS getCZCSByGLDW() {
        QueryBuilder<CZCS> builder = daoSession.getCZCSDao().queryBuilder();
        String czmcID = PreferenceUtils.getPrefString(context, "czmcID", "");
        return builder.where(CZCSDao.Properties.Id.eq(czmcID)).build().unique();
    }

    @Override
    public CZCS getCZCSONE(String czmc, String gldw, Long czzgdydj) {
        QueryBuilder<CZCS> builder = daoSession.getCZCSDao().queryBuilder();
        return builder.where(CZCSDao.Properties.Czmc.eq(czmc), CZCSDao.Properties.Gldw.eq(gldw),
                CZCSDao.Properties.Czzgdydj.eq(czzgdydj)
        ).build().unique();
    }

    @Override
    public List<CZCS> getCZCS(String ssdw, boolean isRegist) {
        QueryBuilder<CZCS> builder = daoSession.getCZCSDao().queryBuilder();
        if (ssdw != null) {
            if (isRegist) {
                if (BaseApplication.getInstance().getDecryptKey() == null)
                    BaseApplication.getInstance().initDecryptKey();
                DecryptKey dy = BaseApplication.getInstance().getDecryptKey();

                List<CZCS> czcs = builder.where(CZCSDao.Properties.Gldw.eq(ssdw)).stringOrderCollation("COLLATE LOCALIZED").orderAsc(CZCSDao.Properties.Czmc).build().list();
                List<CZCS> czcsList = new ArrayList<>();
                czcsList.addAll(czcs);

                if (dy != null) {
                    for (CZCS czc : czcsList) {
                        boolean hasCz = false;
                        for (DecryptKey.CzInfo czInfo : dy.getList()) {
                            if (czc.getCzmc().equals(czInfo.getCzmc().trim()) && czc.getGldw().equals(czInfo.getCzgldw().trim())) {
                                hasCz = true;
                                break;
                            }
                        }
                        if (!hasCz) {
                            czcs.remove(czc);
                        }
                    }
                } else {
                    czcs.clear();
                    czcsList.clear();
                }

                return czcs;
            } else {
                builder.where(CZCSDao.Properties.Gldw.eq(ssdw)).stringOrderCollation("COLLATE LOCALIZED")
                        .orderAsc(CZCSDao.Properties.Czmc);
                builder.stringOrderCollation("COLLATE LOCALIZED").orderAsc(CZCSDao.Properties.Czmc);
                return builder.build().list();
            }
        } else {
            if (isRegist) {
                if (BaseApplication.getInstance().getDecryptKey() == null)
                    BaseApplication.getInstance().initDecryptKey();
                DecryptKey dy = BaseApplication.getInstance().getDecryptKey();

                List<CZCS> czcs = builder.stringOrderCollation("COLLATE LOCALIZED").orderAsc(CZCSDao.Properties.Czmc).build().list();
                List<CZCS> czcsList = new ArrayList<>();
                czcsList.addAll(czcs);

                if (dy != null) {
                    for (CZCS czc : czcsList) {
                        boolean hasCz = false;
                        for (DecryptKey.CzInfo czInfo : dy.getList()) {
                            if (czc.getCzmc().equals(czInfo.getCzmc().trim()) && czc.getGldw().equals(czInfo.getCzgldw().trim())) {
                                hasCz = true;
                                break;
                            }
                        }
                        if (!hasCz) {
                            czcs.remove(czc);
                        }
                    }
                } else {
                    czcs.clear();
                    czcsList.clear();
                }

                return czcs;
            } else {
                builder.stringOrderCollation("COLLATE LOCALIZED").orderAsc(CZCSDao.Properties.Czmc);
                return builder.build().list();
            }
        }
    }

    @Override
    public Object getCZDYDJ(Object o, String... cond) {
        if (o == XLCS.class) {
            XLCSDao xlcsDao = daoSession.getXLCSDao();
            QueryBuilder builder = xlcsDao.queryBuilder();
            builder.distinct().
                    where(XLCSDao.Properties.CZ1.eq(cond[0])
                            , XLCSDao.Properties.XLMC.eq(cond[1])
                            , XLCSDao.Properties.GLDW.eq(cond[2])).
                    whereOr(XLCSDao.Properties.CZ2.eq(cond[0])
                            , XLCSDao.Properties.XLMC.eq(cond[1])
                            , XLCSDao.Properties.GLDW2.eq(cond[2])).
                    whereOr(XLCSDao.Properties.CZ3.eq(cond[0])
                            , XLCSDao.Properties.XLMC.eq(cond[1])
                            , XLCSDao.Properties.GLDW3.eq(cond[2]));
            List<XLCS> list = builder.limit(1).build().list();
            if (list.size() > 0) {
                return list.get(0);
            }
        } else if (o == MXCS.class) {
            MXCSDao mxcsDao = daoSession.getMXCSDao();
            List<MXCS> list = mxcsDao.queryBuilder().distinct().
                    where(MXCSDao.Properties.CZMC.eq(cond[0])
                            , MXCSDao.Properties.MXMC.eq(cond[1])
                            , MXCSDao.Properties.GLDW.eq(cond[2])).limit(1).build().list();
            if (list.size() > 0) {
                return list.get(0);
            }
        } else if (o == BYQCS.class) {
            BYQCSDao byqcsDao = daoSession.getBYQCSDao();
            List<BYQCS> list = byqcsDao.queryBuilder().distinct().
                    where(BYQCSDao.Properties.CZMC.eq(cond[0])
                            , BYQCSDao.Properties.BYQMC.eq(cond[1])
                            , BYQCSDao.Properties.GLDW.eq(cond[2])).limit(1).build().list();
            if (list.size() > 0) {
                return list.get(0);
            }
        } else if (o == DLQCS.class) {
            DLQCSDao dlqcsDao = daoSession.getDLQCSDao();
            List<DLQCS> list = dlqcsDao.queryBuilder().distinct().
                    where(DLQCSDao.Properties.CZMC.eq(cond[0])
                            , DLQCSDao.Properties.DLQMC.eq(cond[1])
                            , DLQCSDao.Properties.GLDW.eq(cond[2])).limit(1).build().list();
            if (list.size() > 0) {
                return list.get(0);
            }
        } else if (o == FDJCS.class) {
            FDJCSDao fdjcsDao = daoSession.getFDJCSDao();
            List<FDJCS> list = fdjcsDao.queryBuilder().distinct().
                    where(FDJCSDao.Properties.CZMC.eq(cond[0])
                            , FDJCSDao.Properties.FDJMC.eq(cond[1])
                            , FDJCSDao.Properties.GLDW.eq(cond[2])).limit(1).build().list();
            if (list.size() > 0) {
                return list.get(0);
            }
        } else if (o == DKQCS.class) {
            DKQCSDao dkqcsDao = daoSession.getDKQCSDao();
            List<DKQCS> list = dkqcsDao.queryBuilder().distinct().
                    where(DKQCSDao.Properties.Czmc.like(cond[0])
                            , DKQCSDao.Properties.Dkqmc.like(cond[1])
                            , DKQCSDao.Properties.Gldw.eq(cond[2])).limit(1).build().list();
            if (list.size() > 0) {
                return list.get(0);
            }
        } else if (o == DRQCS.class) {
            DRQCSDao drqcsDao = daoSession.getDRQCSDao();
            List<DRQCS> list = drqcsDao.queryBuilder().distinct().
                    where(DRQCSDao.Properties.CZMC.eq(cond[0])
                            , DRQCSDao.Properties.DRQMC.eq(cond[1])
                            , DRQCSDao.Properties.GLDW.eq(cond[2])).limit(1).build().list();
            if (list.size() > 0) {
                return list.get(0);
            }
        } else if (o == DDJCS.class) {
            DDJCSDao ddjcsDao = daoSession.getDDJCSDao();
            List<DDJCS> list = ddjcsDao.queryBuilder().distinct().
                    where(DDJCSDao.Properties.CZMC.eq(cond[0])
                            , DDJCSDao.Properties.DDJMC.eq(cond[1])
                            , DDJCSDao.Properties.GLDW.eq(cond[2])).limit(1).build().list();
            if (list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public List<DWLX> getDWLX(String dwlx) {
        DWLXDao dwlxDao = daoSession.getDWLXDao();
        List<DWLX> dwlxList = dwlxDao.queryBuilder().distinct().where(DWLXDao.Properties.DWLX
                .eq(dwlx)).stringOrderCollation("COLLATE LOCALIZED").orderAsc(DWLXDao.Properties.DWMC).build().list();
        return dwlxList;
    }


    @Override
    public List<Object> getTZGX(Object o, String... cond) {
        List<Object> objList = new ArrayList<>();
        if (o == DLQCS.class) {
            DLQCSDao dlqcsDao = daoSession.getDLQCSDao();
            List<DLQCS> dlqcsList = dlqcsDao.queryBuilder().distinct().
                    where(DLQCSDao.Properties.CZMC.eq(cond[0])
                            , DLQCSDao.Properties.DYDJ.eq(cond[1])
                            , DLQCSDao.Properties.GLDW.eq(cond[2])
                            , DLQCSDao.Properties.SFTCYX.eq("否")
                            , DLQCSDao.Properties.KGBH.isNotNull()).
                    build().list();
            objList.addAll(dlqcsList);
        } else if (o == BYQCS.class) {
            DLQCSDao dlqcsDao = daoSession.getDLQCSDao();
            List<DLQCS> dlqcsList = dlqcsDao.queryBuilder().distinct().
                    where(DLQCSDao.Properties.CZMC.eq(cond[0])
                            , DLQCSDao.Properties.GLDW.eq(cond[1])
                            , DLQCSDao.Properties.SFTCYX.eq("否")
                            , DLQCSDao.Properties.KGBH.isNotNull()).
                    build().list();
            objList.addAll(dlqcsList);
        }
        if (o == BHPZ.class) {
            QueryBuilder<BHPZ> builder = daoSession.getBHPZDao().queryBuilder();
            builder.where(BHPZDao.Properties.Czmc.eq(cond[0])
                    , BHPZDao.Properties.Dydj.eq(cond[1])
                    , BHPZDao.Properties.Czgldw.eq(cond[2])
                    , BHPZDao.Properties.Sftcyx.eq("否")
                    , BHPZDao.Properties.Kgbh.isNotNull());
            List<BHPZ> bhpzList = builder.build().list();
            objList.addAll(bhpzList);
        }
        return objList;
    }

    @Override
    public List<Object> getYCSBMCFromBHOrFZ(Object o, String... cond) {
        List<Object> list = new ArrayList<>();
        if (o == BHPZ.class) {
            QueryBuilder<BHPZ> builder = daoSession.getBHPZDao().queryBuilder();
            builder.where(BHPZDao.Properties.Czgldw.eq(cond[0]),
                    BHPZDao.Properties.Czmc.eq(cond[1]));
            if (!cond[2].equals("间隔参数")) {
                builder.where(BHPZDao.Properties.Ycsblx.eq(cond[2]));
            }
            if (cond[3] != null && !cond[3].equals("")) {
                builder.where(BHPZDao.Properties.Jglx.eq(cond[3]));
            }
            if (cond[4] != null && !cond[4].equals("")) {
                builder.where(BHPZDao.Properties.Jgmc.eq(cond[4]));
            }
            List<BHPZ> bhpzList = builder.build().list();
            list.addAll(bhpzList);
        } else if (o == FZBHSB.class) {
            QueryBuilder<FZBHSB> builder = daoSession.getFZBHSBDao().queryBuilder();
            builder.where(FZBHSBDao.Properties.Czgldw.eq(cond[0]),
                    FZBHSBDao.Properties.Czmc.eq(cond[1]));
            if (!cond[2].equals("间隔参数")) {
                builder.where(FZBHSBDao.Properties.Ycsblx.eq(cond[2]));
            }
            if (cond[3] != null && !cond[3].equals("")) {
                builder.where(FZBHSBDao.Properties.Jglx.eq(cond[3]));
            }
            if (cond[4] != null && !cond[4].equals("")) {
                builder.where(FZBHSBDao.Properties.Jgmc.eq(cond[4]));
            }
            List<FZBHSB> bhpzList = builder.build().list();
            list.addAll(bhpzList);
        }
        return list;
    }

    @Override
    public List<Object> getYCSBMC(Object o, String... cond) {
        List<Object> objList = new ArrayList<>();
        if (o == XLCS.class) {
            XLCSDao xlcsDao = daoSession.getXLCSDao();
            QueryBuilder builder = xlcsDao.queryBuilder();
//            builder.distinct().where(XLCSDao.Properties.Sh.notEq("已删除")).
//                    whereOr(XLCSDao.Properties.SFTCYX.notEq("是")
//                            , XLCSDao.Properties.SFTCYX.isNull()).
//                    whereOr(XLCSDao.Properties.CZ1.like("%" + cond[0] + "%")
//                            , XLCSDao.Properties.GLDW.eq(cond[1])).
//                    whereOr(XLCSDao.Properties.CZ2.like("%" + cond[0] + "%")
//                            , XLCSDao.Properties.GLDW2.eq(cond[1])).
//                    whereOr(XLCSDao.Properties.CZ3.like("%" + cond[0] + "%")
//                            , XLCSDao.Properties.GLDW3.eq(cond[1]));
            String conditions = "sh!='已删除' " + "and(sftcyx!='是' or sftcyx is null) and((CZ1='" + cond[0] + "' and GLDW='" + cond[1] + "')" +
                    "or(CZ2='" + cond[0] + "' and GLDW2='" + cond[1] + "')or(CZ3='" + cond[0] + "' and GLDW3='" + cond[1] + "'))";
            if (cond[2] != null && !cond[2].equals("")
                    && cond[3] != null && !cond[3].equals("")) {
                conditions = "sh!='已删除' " + "and(sftcyx!='是' or sftcyx is null) and((CZ1='" + cond[0] + "' and GLDW='" + cond[1] +
                        "' and JGLX1='" + cond[2] + "' and JGMC1='" + cond[3] + "')" + "or(CZ2='" + cond[0] + "' and GLDW2='" + cond[1] +
                        "' and JGLX2='" + cond[2] + "' and JGMC2='" + cond[3] + "')or(CZ3='" + cond[0] + "' and GLDW3='" + cond[1] +
                        "' and JGLX3='" + cond[2] + "' and JGMC3='" + cond[3] + "'))";
            } else {
                if (cond[2] != null && !cond[2].equals("")) {
                    conditions = "sh!='已删除' " + "and(sftcyx!='是' or sftcyx is null) and((CZ1='" + cond[0] + "' and GLDW='" + cond[1] +
                            "' and JGLX1='" + cond[2] + "')" + "or(CZ2='" + cond[0] + "' and GLDW2='" + cond[1] +
                            "' and JGLX2='" + cond[2] + "')or(CZ3='" + cond[0] + "' and GLDW3='" + cond[1] +
                            "' and JGLX3='" + cond[2] + "'))";
                } else if (cond[3] != null && !cond[3].equals("")) {
                    conditions = "sh!='已删除' " + "and(sftcyx!='是' or sftcyx is null) and((CZ1='" + cond[0] + "' and GLDW='" + cond[1] +
                            "' and JGMC1='" + cond[3] + "')" + "or(CZ2='" + cond[0] + "' and GLDW2='" + cond[1] +
                            "' and JGMC2='" + cond[3] + "')or(CZ3='" + cond[0] + "' and GLDW3='" + cond[1] +
                            "' and JGMC3='" + cond[3] + "'))";
                }
            }
            builder.distinct().where(new WhereCondition.StringCondition(conditions));

            List<XLCS> xlcsList = builder.stringOrderCollation("COLLATE LOCALIZED").orderAsc(XLCSDao.Properties.XLMC).build().list();
            objList.addAll(xlcsList);
        } else if (o == MXCS.class) {
            QueryBuilder<MXCS> builder = daoSession.getMXCSDao().queryBuilder();
            builder.where(MXCSDao.Properties.Sh.notEq("已删除")
                    , MXCSDao.Properties.CZMC.like("%" + cond[0] + "%")
                    , MXCSDao.Properties.GLDW.eq(cond[1])).
                    whereOr(MXCSDao.Properties.SFTCYX.notEq("是")
                            , MXCSDao.Properties.SFTCYX.isNull());
            if (cond[2] != null && !cond[2].equals("")) {
                builder.where(MXCSDao.Properties.Jglx.eq(cond[2]));
            }
            if (cond[3] != null && !cond[3].equals("")) {
                builder.where(MXCSDao.Properties.Jgmc.eq(cond[3]));
            }

            List<MXCS> mxcsList = builder.stringOrderCollation("COLLATE LOCALIZED").orderAsc(MXCSDao.Properties.MXMC).build().list();
            objList.addAll(mxcsList);
        } else if (o == BYQCS.class) {
            QueryBuilder<BYQCS> builder = daoSession.getBYQCSDao().queryBuilder();

            builder.where(BYQCSDao.Properties.Sh.notEq("已删除")
                    , BYQCSDao.Properties.CZMC.like("%" + cond[0] + "%")
                    , BYQCSDao.Properties.GLDW.eq(cond[1])).
                    whereOr(BYQCSDao.Properties.SFTCYX.notEq("是")
                            , BYQCSDao.Properties.SFTCYX.isNull());
            if (cond[2] != null && !cond[2].equals("")) {
                builder.where(BYQCSDao.Properties.Jglx.eq(cond[2]));
            }
            if (cond[3] != null && !cond[3].equals("")) {
                builder.where(BYQCSDao.Properties.Jgmc.eq(cond[3]));
            }
            List<BYQCS> byqcsList = builder.stringOrderCollation("COLLATE LOCALIZED").orderAsc(BYQCSDao.Properties.BYQMC).build().list();
            objList.addAll(byqcsList);
        } else if (o == DLQCS.class) {
            QueryBuilder<DLQCS> builder = daoSession.getDLQCSDao().queryBuilder();

            builder.where(DLQCSDao.Properties.Sh.notEq("已删除")
                    , DLQCSDao.Properties.CZMC.like("%" + cond[0] + "%")
                    , DLQCSDao.Properties.GLDW.eq(cond[1])).
                    whereOr(DLQCSDao.Properties.SFTCYX.notEq("是")
                            , DLQCSDao.Properties.SFTCYX.isNull());
            if (cond[2] != null && !cond[2].equals("")) {
                builder.where(DLQCSDao.Properties.Jglx.eq(cond[2]));
            }
            if (cond[3] != null && !cond[3].equals("")) {
                builder.where(DLQCSDao.Properties.Jgmc.eq(cond[3]));
            }
            List<DLQCS> dlqcsList = builder.stringOrderCollation("COLLATE LOCALIZED").orderAsc(DLQCSDao.Properties.DLQMC).build().list();
            objList.addAll(dlqcsList);
        } else if (o == FDJCS.class) {
            QueryBuilder<FDJCS> builder = daoSession.getFDJCSDao().queryBuilder();
            builder.where(FDJCSDao.Properties.Sh.notEq("已删除")
                    , FDJCSDao.Properties.CZMC.like("%" + cond[0] + "%")
                    , FDJCSDao.Properties.GLDW.eq(cond[1])).
                    whereOr(FDJCSDao.Properties.SFTCYX.notEq("是")
                            , FDJCSDao.Properties.SFTCYX.isNull());
            if (cond[2] != null && !cond[2].equals("")) {
                builder.where(FDJCSDao.Properties.Jglx.eq(cond[2]));
            }
            if (cond[3] != null && !cond[3].equals("")) {
                builder.where(FDJCSDao.Properties.Jgmc.eq(cond[3]));
            }
            List<FDJCS> fdjcsList = builder.stringOrderCollation("COLLATE LOCALIZED").orderAsc(FDJCSDao.Properties.FDJMC).build().list();
            objList.addAll(fdjcsList);
        } else if (o == DKQCS.class) {
            QueryBuilder<DKQCS> builder = daoSession.getDKQCSDao().queryBuilder();

            builder.where(DKQCSDao.Properties.Sh.notEq("已删除")
                    , DKQCSDao.Properties.Czmc.like("%" + cond[0] + "%")
                    , DKQCSDao.Properties.Gldw.eq(cond[1])).
                    whereOr(DKQCSDao.Properties.Sftcyx.notEq("是")
                            , DKQCSDao.Properties.Sftcyx.isNull());
            if (cond[2] != null && !cond[2].equals("")) {
                builder.where(DKQCSDao.Properties.Jglx.eq(cond[2]));
            }
            if (cond[3] != null && !cond[3].equals("")) {
                builder.where(DKQCSDao.Properties.Jgmc.eq(cond[3]));
            }
            List<DKQCS> dkqcsList = builder.stringOrderCollation("COLLATE LOCALIZED").orderAsc(DKQCSDao.Properties.Dkqmc).build().list();

            objList.addAll(dkqcsList);
        } else if (o == DRQCS.class) {
            QueryBuilder<DRQCS> builder = daoSession.getDRQCSDao().queryBuilder();

            builder.where(DRQCSDao.Properties.Sh.notEq("已删除")
                    , DRQCSDao.Properties.CZMC.like("%" + cond[0] + "%")
                    , DRQCSDao.Properties.GLDW.eq(cond[1])).
                    whereOr(DRQCSDao.Properties.SFTCYX.notEq("是")
                            , DRQCSDao.Properties.SFTCYX.isNull());
            if (cond[2] != null && !cond[2].equals("")) {
                builder.where(DRQCSDao.Properties.Jglx.eq(cond[2]));
            }
            if (cond[3] != null && !cond[3].equals("")) {
                builder.where(DRQCSDao.Properties.Jgmc.eq(cond[3]));
            }
            List<DRQCS> drqcsList = builder.stringOrderCollation("COLLATE LOCALIZED").orderAsc(DRQCSDao.Properties.DRQMC).build().list();
            objList.addAll(drqcsList);
        } else if (o == DDJCS.class) {
            QueryBuilder<DDJCS> builder = daoSession.getDDJCSDao().queryBuilder();

            builder.where(DDJCSDao.Properties.Sh.notEq("已删除")
                    , DDJCSDao.Properties.CZMC.like("%" + cond[0] + "%")
                    , DDJCSDao.Properties.GLDW.eq(cond[1])).
                    whereOr(DDJCSDao.Properties.SFTCYX.notEq("是")
                            , DDJCSDao.Properties.SFTCYX.isNull());
            if (cond[2] != null && !cond[2].equals("")) {
                builder.where(DDJCSDao.Properties.Jglx.eq(cond[2]));
            }
            if (cond[3] != null && !cond[3].equals("")) {
                builder.where(DDJCSDao.Properties.Jgmc.eq(cond[3]));
            }
            List<DDJCS> ddjcsList = builder.stringOrderCollation("COLLATE LOCALIZED").orderAsc(DDJCSDao.Properties.DDJMC).build().list();
            objList.addAll(ddjcsList);
        } else if (o == JGCS.class) {
            QueryBuilder<JGCS> builder = daoSession.getJGCSDao().queryBuilder();
            builder.where(JGCSDao.Properties.Sh.notEq("已删除")
                    , JGCSDao.Properties.Czmc.like("%" + cond[0] + "%")
                    , JGCSDao.Properties.Gldw.eq(cond[1]))
                    .stringOrderCollation("COLLATE LOCALIZED")
                    .orderAsc(JGCSDao.Properties.Jgmc);
            if (cond.length == 3 && cond[2] != null && !cond[2].equals("")) {
                builder.where(JGCSDao.Properties.Jglx.eq(cond[2]));
            }

            List<JGCS> jgcsList = builder.build().list();
            if (jgcsList != null && jgcsList.size() > 0) {
                objList.addAll(jgcsList);
            }
        }
        return objList;
    }

    @Override
    public List<Object> getYCSBDYDJ(Object o, String... cond) {
        List<Object> objList = new ArrayList<>();
        if (o == XLCS.class) {
            XLCSDao xlcsDao = daoSession.getXLCSDao();
            List<XLCS> xlcsList = xlcsDao.queryBuilder().distinct().
                    where(XLCSDao.Properties.XLMC.eq(cond[0])).
                    whereOr(XLCSDao.Properties.CZ1.like("%" + cond[1] + "%")
                            , XLCSDao.Properties.GLDW.eq(cond[2])).
                    whereOr(XLCSDao.Properties.CZ2.like("%" + cond[1] + "%")
                            , XLCSDao.Properties.GLDW2.eq(cond[2])).
                    whereOr(XLCSDao.Properties.CZ3.like("%" + cond[1] + "%")
                            , XLCSDao.Properties.GLDW3.eq(cond[2])).
                    build().list();
            objList.addAll(xlcsList);
        } else if (o == MXCS.class) {
            MXCSDao mxcsDao = daoSession.getMXCSDao();
            List<MXCS> mxcsList = mxcsDao.queryBuilder().distinct().
                    where(MXCSDao.Properties.MXMC.eq(cond[0])
                            , MXCSDao.Properties.CZMC.like("%" + cond[1] + "%")
                            , MXCSDao.Properties.GLDW.eq(cond[2])).
                    build().list();
            objList.addAll(mxcsList);
        } else if (o == BYQCS.class) {
            BYQCSDao byqcsDao = daoSession.getBYQCSDao();
            List<BYQCS> byqcsList = byqcsDao.queryBuilder().distinct().
                    where(BYQCSDao.Properties.BYQMC.eq(cond[0])
                            , BYQCSDao.Properties.CZMC.like("%" + cond[1] + "%")
                            , BYQCSDao.Properties.GLDW.eq(cond[2])).
                    build().list();
            objList.addAll(byqcsList);
        } else if (o == DLQCS.class) {
            DLQCSDao dlqcsDao = daoSession.getDLQCSDao();
            List<DLQCS> dlqcsList = dlqcsDao.queryBuilder().distinct().
                    where(DLQCSDao.Properties.DLQMC.eq(cond[0])
                            , DLQCSDao.Properties.CZMC.like("%" + cond[1] + "%")
                            , DLQCSDao.Properties.GLDW.eq(cond[2])).
                    build().list();
            objList.addAll(dlqcsList);
        } else if (o == FDJCS.class) {
            FDJCSDao fdjcsDao = daoSession.getFDJCSDao();
            List<FDJCS> fdjcsList = fdjcsDao.queryBuilder().distinct().
                    where(FDJCSDao.Properties.FDJMC.eq(cond[0])
                            , FDJCSDao.Properties.CZMC.like("%" + cond[1] + "%")
                            , FDJCSDao.Properties.GLDW.eq(cond[2])).
                    build().list();
            objList.addAll(fdjcsList);
        } else if (o == DKQCSDao.class) {
            DKQCSDao dkqcsDao = daoSession.getDKQCSDao();
            List<DKQCS> dkqcsList = dkqcsDao.queryBuilder().distinct().
                    where(DKQCSDao.Properties.Dkqmc.eq(cond[0])
                            , DKQCSDao.Properties.Czmc.like("%" + cond[1] + "%")
                            , DKQCSDao.Properties.Gldw.eq(cond[2])).
                    build().list();
            objList.addAll(dkqcsList);
        } else if (o == DRQCS.class) {
            DRQCSDao drqcsDao = daoSession.getDRQCSDao();
            List<DRQCS> drqcsList = drqcsDao.queryBuilder().distinct().
                    where(DRQCSDao.Properties.DRQMC.eq(cond[0])
                            , DRQCSDao.Properties.CZMC.like("%" + cond[1] + "%")
                            , DRQCSDao.Properties.GLDW.eq(cond[2])).
                    build().list();
            objList.addAll(drqcsList);
        } else if (o == DDJCS.class) {
            DDJCSDao ddjcsDao = daoSession.getDDJCSDao();
            List<DDJCS> ddjcsList = ddjcsDao.queryBuilder().distinct().
                    where(DDJCSDao.Properties.DDJMC.eq(cond[0])
                            , DDJCSDao.Properties.CZMC.like("%" + cond[1] + "%")
                            , DDJCSDao.Properties.GLDW.eq(cond[2])).
                    build().list();
            objList.addAll(ddjcsList);
        } else if (o == JGCS.class) {
            JGCSDao jgcsDao = daoSession.getJGCSDao();
            List<JGCS> jgcsList = jgcsDao.queryBuilder().distinct().
                    where(JGCSDao.Properties.Jgmc.eq(cond[0])
                            , JGCSDao.Properties.Czmc.like("%" + cond[1] + "%")
                            , JGCSDao.Properties.Gldw.eq(cond[2])).
                    build().list();
            objList.addAll(jgcsList);
        }
        return objList;
    }

    @Override
    public List<BHLBXH> getBHLBXH(String bhlb) {
        BHLBXHDao bhlbxhDao = daoSession.getBHLBXHDao();
        List<BHLBXH> data = bhlbxhDao.queryBuilder().distinct().
                where(BHLBXHDao.Properties.Bhlb.eq(bhlb)).
                build().list();
        return data;
    }

    @Override
    public List<ZZCJ> getZZCJ(String mc) {
        if (mc != null && !mc.equals("")) {
            return daoSession.getZZCJDao().queryBuilder().where(ZZCJDao.Properties.MC.eq(mc))
                    .stringOrderCollation("COLLATE LOCALIZED").orderAsc(ZZCJDao.Properties.MC).build().list();
        } else {
            return daoSession.getZZCJDao().queryBuilder().stringOrderCollation("COLLATE LOCALIZED")
                    .orderAsc(ZZCJDao.Properties.MC).build().list();
        }
    }

    @Override
    public List<Object> getBHXH(boolean isSixSb, boolean is2013, String... cond) {
        List<Object> objList = new ArrayList<>();
        String czlx = cond[2];
        CZCS czcs = getCZCSByGLDW();
        int dydj = 0;
        if (cond[3] != null && !cond[3].equals("") && !cond[3].equals("必填项") && !cond[3].equals("null")) {
            dydj = Integer.parseInt(cond[3]);
        }

        if (isSixSb && is2013) {//六统一
            QueryBuilder<LTYSBXH> builder = daoSession.getLTYSBXHDao().queryBuilder().distinct();
            builder.where(LTYSBXHDao.Properties.Zzcj.eq(cond[0])
                    , LTYSBXHDao.Properties.Bhlb.eq(cond[1])
                    , LTYSBXHDao.Properties.Sfqy.isNotNull()).
                    orderAsc(LTYSBXHDao.Properties.Bhxh);
            if (cond.length == 6) {
                builder.where(LTYSBXHDao.Properties.Bhxh.eq(cond[5]));
            }
            objList.addAll(builder.build().list());
        } else {//非六统一
            QueryBuilder<BHSBXHB> builder = daoSession.getBHSBXHBDao().queryBuilder().distinct();
            if (dydj >= 220 || czlx.equals("智能站") && czcs.getCzzgdydj() >= 110) {
                builder.where(BHSBXHBDao.Properties.Zzcj.eq(cond[0])
                        , BHSBXHBDao.Properties.Bhlb.eq(cond[1])).
                        orderAsc(BHSBXHBDao.Properties.Sbxh);
                builder.whereOr(BHSBXHBDao.Properties.Sfqy.eq("Y"),
                        BHSBXHBDao.Properties.Sfqy.eq("N"));
            } else {
                if (cond[4].equals("是")) {
                    builder.where(BHSBXHBDao.Properties.Zzcj.eq(cond[0])
                            , BHSBXHBDao.Properties.Bhlb.eq(cond[1])).
                            orderAsc(BHSBXHBDao.Properties.Sbxh);
                    builder.whereOr(BHSBXHBDao.Properties.Sfqy.eq("Y"),
                            BHSBXHBDao.Properties.Sfqy.eq("N"));
                } else {
                    builder.where(BHSBXHBDao.Properties.Zzcj.eq(cond[0])
                            , BHSBXHBDao.Properties.Bhlb.eq(cond[1])).
                            orderAsc(BHSBXHBDao.Properties.Sbxh);
                }
            }
            if (cond.length == 6) {
                builder.where(BHSBXHBDao.Properties.Sbxh.eq(cond[5]));
            }
            builder.where(BHSBXHBDao.Properties.Code.notEq("x"),
                    BHSBXHBDao.Properties.Code.notEq("X"));
            objList.addAll(builder.build().list());
        }
        return objList;
    }


    @Override
    public List<BHSBXHB> getBHFLOrBHLX(String queryType, String... cond) {
        List<BHSBXHB> list = null;
        QueryBuilder<BHSBXHB> builder = daoSession.getBHSBXHBDao().queryBuilder();
        if (queryType.equals("BHLX")) {
            list = builder.distinct().where(
                    BHSBXHBDao.Properties.Zzcj.eq(cond[0])
                    , BHSBXHBDao.Properties.Bhlb.eq(cond[1])
                    , BHSBXHBDao.Properties.Sbxh.eq(cond[2])
            ).orderAsc(BHSBXHBDao.Properties.Bhlx).build().list();
        } else if (queryType.equals("BHFL")) {
            list = builder.distinct().where(
                    BHSBXHBDao.Properties.Zzcj.eq(cond[0])
                    , BHSBXHBDao.Properties.Bhlb.eq(cond[1])
                    , BHSBXHBDao.Properties.Sbxh.eq(cond[2])
            ).orderAsc(BHSBXHBDao.Properties.Bhfl).build().list();
        } else if (queryType.equals("RJXD")) {
            list = builder.distinct().where(
                    BHSBXHBDao.Properties.Zzcj.eq(cond[0])
                    , BHSBXHBDao.Properties.Bhlb.eq(cond[1])
                    , BHSBXHBDao.Properties.Sbxh.eq(cond[2])
            ).build().list();
        } else if (queryType.equals("BBJYM")) {
            list = builder.where(
                    BHSBXHBDao.Properties.Zzcj.eq(cond[0])
                    , BHSBXHBDao.Properties.Bhlb.eq(cond[1])
                    , BHSBXHBDao.Properties.Sbxh.eq(cond[2])
            ).orderAsc(BHSBXHBDao.Properties.Sbxh).build().list();
        } else if (queryType.equals("ALL")) {
            list = builder.distinct().where(
                    BHSBXHBDao.Properties.Zzcj.eq(cond[0])
                    , BHSBXHBDao.Properties.Bhlb.eq(cond[1])
                    , BHSBXHBDao.Properties.Sbxh.eq(cond[2])
            ).build().list();
        }
        return list;
    }

    @Override
    public List<LTYSBXH> getLTYSBXHBHFLOrBHLX(String queryType, String... cond) {
        List<LTYSBXH> list = null;
        QueryBuilder<LTYSBXH> builder = daoSession.getLTYSBXHDao().queryBuilder();
        if (queryType.equals("BHLX")) {
            list = builder.distinct().where(
                    LTYSBXHDao.Properties.Zzcj.eq(cond[0])
                    , LTYSBXHDao.Properties.Bhlb.eq(cond[1])
                    , LTYSBXHDao.Properties.Bhxh.eq(cond[2])
            ).orderAsc(LTYSBXHDao.Properties.Bhlx).build().list();
        } else if (queryType.equals("BHFL")) {
            list = builder.distinct().where(
                    LTYSBXHDao.Properties.Zzcj.eq(cond[0])
                    , LTYSBXHDao.Properties.Bhlb.eq(cond[1])
                    , LTYSBXHDao.Properties.Bhxh.eq(cond[2])
            ).orderAsc(LTYSBXHDao.Properties.Bhfl).build().list();
        } else if (queryType.equals("ALL")) {
            list = builder.distinct().where(
                    LTYSBXHDao.Properties.Zzcj.eq(cond[0])
                    , LTYSBXHDao.Properties.Bhlb.eq(cond[1])
                    , LTYSBXHDao.Properties.Bhxh.eq(cond[2])
            ).build().list();
        }
        return list;
    }

    @Override
    public List<BHSBXHB> getSBXH(String... cond) {
        BHSBXHBDao bhsbxhbDao = daoSession.getBHSBXHBDao();
        List<BHSBXHB> list = bhsbxhbDao.queryBuilder().distinct().
                where(BHSBXHBDao.Properties.Id.isNotNull()
                        , BHSBXHBDao.Properties.Zzcj.eq(cond[0])
                        , BHSBXHBDao.Properties.Bhlb.eq(cond[1])
                        , BHSBXHBDao.Properties.Sfqy.eq("Y")).
                orderAsc(BHSBXHBDao.Properties.Sbxh).build().list();
        return list;
    }

    @Override
    public List<BHXHRJBB> getBHSBXHRJBB(String... cond) {
        List<BHXHRJBB> list;
        BHXHRJBBDao bhsbxhrjbbDao = daoSession.getBHXHRJBBDao();
        QueryBuilder<BHXHRJBB> builder = bhsbxhrjbbDao.queryBuilder();
        if (cond.length == 1) {
            list = builder.where(BHXHRJBBDao.Properties.Bhxhcode.eq(cond[0])).build().list();
        } else {
            list = builder.where(BHXHRJBBDao.Properties.Id.isNotNull()).
                    where(new WhereCondition.StringCondition("BHXHCODE IN " +
                            "(SELECT CODE FROM BHSBXHB WHERE 1=1 AND ZZCJ=" + "'" + cond[0] + "'" + " AND SBXH=" + "'" + cond[1] + "'" +
                            " AND SFQY='Y' AND BHLB=" + "'" + cond[2] + "'" + " AND BHLX=" + "'" + cond[3] + "'" + ")")).
                    stringOrderCollation("COLLATE LOCALIZED").
                    orderAsc(BHXHRJBBDao.Properties.Mkmc, BHXHRJBBDao.Properties.Bb).build().list();
        }
        return list;
    }

    @Override
    public List<BHXHRJBB> getBHSBXHRJBBbyCode(String code) {
        List<BHXHRJBB> list = new ArrayList<>();
        list = daoSession.getBHXHRJBBDao().queryBuilder()
                .where(BHXHRJBBDao.Properties.Code.eq(code)).build().list();
        return list;
    }

    @Override
    public List<LTYSBXH> getLTYSBXHbyCode(String code) {
        List<LTYSBXH> list;
        list = daoSession.getLTYSBXHDao().queryBuilder()
                .where(LTYSBXHDao.Properties.Code.eq(code)).build().list();
        return list;
    }

    @Override
    public List<Object> getFZBHSBXHRJBB(boolean isLty, boolean is2013, String... cond) {
        List<Object> list = new ArrayList<>();
        if (isLty && is2013) {
            QueryBuilder<LTYSBXH> builder = daoSession.getLTYSBXHDao().queryBuilder();
            builder.where(LTYSBXHDao.Properties.Bhxh.eq(cond[0] + "")
                    , LTYSBXHDao.Properties.Zzcj.eq(cond[1] + "")
                    , LTYSBXHDao.Properties.Bhlb.eq(cond[2] + ""));
            if (cond.length == 4 && cond[1] != null && !cond[1].equals("")) {
                builder.where(LTYSBXHDao.Properties.Rjbb.eq(cond[3] + ""));
            }
            if (cond.length == 5 && cond[2] != null && !cond[2].equals("")) {
                if (cond[3] != null) {
                    builder.where(LTYSBXHDao.Properties.Rjbb.eq(cond[3] + ""));
                }
                builder.where(LTYSBXHDao.Properties.Xp.eq(cond[4] + ""));
            }

            if (cond.length == 6 && cond[3] != null && !cond[3].equals("")) {
                if (cond[3] != null) {
                    builder.where(LTYSBXHDao.Properties.Rjbb.eq(cond[3] + ""));
                }
                if (cond[4] != null) {
                    builder.where(LTYSBXHDao.Properties.Xp.eq(cond[4] + ""));
                }
                builder.where(LTYSBXHDao.Properties.Wjmc.eq(cond[5] + ""));
            }
            list.addAll(builder.list());
        } else {
            QueryBuilder<BHXHRJBB> builder = daoSession.getBHXHRJBBDao().queryBuilder();
            builder.where(BHXHRJBBDao.Properties.Bhxhcode.eq(cond[0] + ""));
            if (cond.length == 2 && cond[1] != null && !cond[1].equals("")) {
                builder.where(BHXHRJBBDao.Properties.Mkmc.eq(cond[1] + ""));
            }
            if (cond.length == 3 && cond[2] != null && !cond[2].equals("")) {
                if (cond[1] != null && !cond[1].equals("")) {
                    builder.where(BHXHRJBBDao.Properties.Mkmc.eq(cond[1] + ""));
                }
                builder.where(BHXHRJBBDao.Properties.Bb.eq(cond[2] + ""));
            }
            list.addAll(builder.list());
        }
        return list;
    }

    @Override
    public List<LTYSBXH> getLtyXX(Map<Property, String> map) {
        QueryBuilder<LTYSBXH> builder = daoSession.getLTYSBXHDao().queryBuilder();

        for (Map.Entry<Property, String> entry : map.entrySet()) {
            builder.where(entry.getKey().eq(entry.getValue() + ""));
        }

        return builder.list();
    }

    @Override
    public List<LTYSBXH> getLTYSBXHBYBHXH(String... cond) {
        LTYSBXHDao ltysbxhDao = daoSession.getLTYSBXHDao();
        List<LTYSBXH> ltysbxh = ltysbxhDao.queryBuilder().where(LTYSBXHDao.Properties.Zzcj.eq(cond[0])
                , LTYSBXHDao.Properties.Bhxh.eq(cond[1])
                , LTYSBXHDao.Properties.Sfqy.eq("Y")).build().list();
        return ltysbxh;
    }

    //保护装置列表无码的排序
    @Override
    public List<BHPZ> getBHPZ(int offset, SearchEntity shEntity) {
        daoSession.clear();
        QueryBuilder<BHPZ> builder = daoSession.getBHPZDao().queryBuilder();
        CZCS czcs = getCZCSByGLDW();
        builder.where(BHPZDao.Properties.Czmc.eq(czcs.getCzmc()));
        builder.where(BHPZDao.Properties.Czgldw.eq(czcs.getGldw()));

        if (!shEntity.getZZName().equals("")) {
            builder.where(BHPZDao.Properties.Bhmc.like("%" + shEntity.getZZName() + "%"));
        }

        List<SaleAttributeNameVo> nameVos = shEntity.getNameVos();
        boolean hasD = false; //是否选中了"已删除",默认没有
        String zzJY = "";
        if (nameVos != null && nameVos.size() > 0) {
            for (SaleAttributeNameVo nameVo : nameVos) {
                String searchName = nameVo.getName();
                List<SaleAttributeVo> vo = nameVo.getSaleVo();
                if (searchName.equals("一次设备名称")) {
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        builder.where(BHPZDao.Properties.Ycsbmc.like("%" + saleAttributeVo.getValue() + "%"));
                    }
                } else if (searchName.equals("制造厂家")) {
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        builder.where(BHPZDao.Properties.Zzcj.like("%" + saleAttributeVo.getValue() + "%"));
                    }
                } else if (searchName.equals("型号")) {
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        builder.where(BHPZDao.Properties.Bhxh.like("%" + saleAttributeVo.getValue() + "%"));
                    }
                } else if (searchName.equals("数据状态")) {
                    List<String> stateList = new ArrayList<>();
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        if (saleAttributeVo.getValue().equals("未操作")) {
                            stateList.add("N");
                        } else if (saleAttributeVo.getValue().equals("已操作")) {
                            stateList.add("M");
                            stateList.add("H");
                            stateList.add("S");
                            stateList.add("C");
                            stateList.add("MH");
                            stateList.add("SH");
                            stateList.add("CH");
                        } else if (saleAttributeVo.getValue().equals("新增")) {
                            stateList.add("C");
                            stateList.add("CH");
                        } else if (saleAttributeVo.getValue().equals("已删除")) {
                            stateList.add("D");
                            hasD = true;
                        } else if (saleAttributeVo.getValue().equals("已导出")) {
                            stateList.add("F");
                        } else if (saleAttributeVo.getValue().equals("未核对")) {
                            stateList.add("M");
                            stateList.add("S");
                            stateList.add("C");
                            stateList.add("N");
                        } else if (saleAttributeVo.getValue().equals("已核对")) {
                            stateList.add("H");
                            stateList.add("MH");
                            stateList.add("SH");
                            stateList.add("CH");
                        }
                    }
                    String conditions = "(1!=1";
                    for (String s : stateList) {
                        if (s.equals("N")) {
                            conditions += " or (ed_tag is null)";
                            continue;
                        }
                        if (s.equals("F")) {
                            conditions += " or sb =='" + s + "'";
                            continue;
                        }
                        conditions += " or ed_tag =='" + s + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("电压等级")) {
                    String conditions = "(1 !=1";
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        conditions += " or dydj =='" + saleAttributeVo.getValue() + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("装置类别")) { //bhlb !=null
                    String conditions = "(1 !=1";
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        conditions += " or bhlb =='" + saleAttributeVo.getValue() + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("调度单位")) {
                    String conditions = "(1 !=1";
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        conditions += " or dddw =='" + saleAttributeVo.getValue() + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("装置校验")) {
                    if (vo.get(0).getValue().equals("通过校验")) {
                        builder.where(BHPZDao.Properties.Jy.eq("TG"));
                    } else if (vo.get(0).getValue().equals("未通过校验")) {
                        //conditions="ed_tag is null or ed_tag='WTG'";
                        builder.whereOr(BHPZDao.Properties.Jy.isNull(),
                                BHPZDao.Properties.Jy.eq(""),
                                BHPZDao.Properties.Jy.notEq("TG"));
                    }

                    //这样写有问题
//                    String conditions="";
//                    if (vo.get(0).getValue().equals("通过校验")) {
//                        conditions="ed_tag = 'TG'";
//                    } else if (vo.get(0).getValue().equals("未通过校验")) {
//                        conditions="(ed_tag is null)or ed_tag='WTG'";
//                    }
//                    builder.where(new WhereCondition.StringCondition(conditions));
                }
            }
        }

        if (!hasD) {
            builder.whereOr(BHPZDao.Properties.Ed_tag.isNull(),
                    BHPZDao.Properties.Ed_tag.notEq("D"));
        }

        //--------------排序--------------------------------------
        List<BHPZ> listBhpz = null;
        if (nameVos != null && nameVos.size() > 0) {
            //排序
            for (SaleAttributeNameVo nameVo : nameVos) {
                String searchName = nameVo.getName();
                List<SaleAttributeVo> vo = nameVo.getSaleVo();
                if (searchName.equals("排序")) {
                    if (!vo.get(0).getValue().equals("默认排序先无码后有码")) {
                        if (vo.get(0).getValue().equals("电压等级从高到低")) {
                            listBhpz = builder.where(BHPZDao.Properties.Dydj.isNotNull()).stringOrderCollation("COLLATE LOCALIZED").
                                    orderDesc(BHPZDao.Properties.Dydj).build().list();
                        } else if (vo.get(0).getValue().equals("保护类别")) {
                            listBhpz = builder.where(BHPZDao.Properties.Bhlb.isNotNull()).stringOrderCollation("COLLATE LOCALIZED").
                                    orderAsc(BHPZDao.Properties.Bhlb).build().list();
                        } else if (vo.get(0).getValue().equals("制造厂家")) {
                            listBhpz = builder.where(BHPZDao.Properties.Zzcj.isNotNull()).stringOrderCollation("COLLATE LOCALIZED").
                                    orderAsc(BHPZDao.Properties.Zzcj).build().list();
                        }
                    } else {//默认排序
                        List<BHPZ> list_youma = null;
                        list_youma = getBHPZs(shEntity);
                        listBhpz = builder.where(BHPZDao.Properties.Sw_id.eq("")).stringOrderCollation("COLLATE LOCALIZED").
                                orderAsc(BHPZDao.Properties.Ycsbmc).orderAsc(BHPZDao.Properties.Bhmc).build().list();//无码
                        listBhpz.addAll(list_youma);
                    }
                    break;
                } else if (nameVos.size() == 1) {
                    List<BHPZ> list_youma = null;
                    list_youma = getBHPZs(shEntity);
                    listBhpz = builder.where(BHPZDao.Properties.Sw_id.eq("")).stringOrderCollation("COLLATE LOCALIZED").
                            orderAsc(BHPZDao.Properties.Ycsbmc).orderAsc(BHPZDao.Properties.Bhmc).build().list();//无码
                    listBhpz.addAll(list_youma);
                    break;
                }
            }
        } else {
            //--------------排序-------------------
            List<BHPZ> list_youma = null;
            list_youma = getBHPZs(shEntity);
            listBhpz = builder.where(BHPZDao.Properties.Sw_id.eq("")).stringOrderCollation("COLLATE LOCALIZED").
                    orderAsc(BHPZDao.Properties.Ycsbmc).orderAsc(BHPZDao.Properties.Bhmc).build().list();
            listBhpz.addAll(list_youma);
        }

        //--------------------以下代码暂时作废------------------------------------
        //校验一级项
//        if (zzJY!=null&&zzJY!="") {
//            if (zzJY.equals("未通过校验")) {
//                List<SaleAttributeNameVo> nameVoJYXOne = shEntity.getNameVoJYXOne();
//                if (nameVoJYXOne != null && nameVoJYXOne.size() > 0) {
//                    SaleAttributeNameVo nameVojy = (SaleAttributeNameVo) nameVoJYXOne.get(0);
//                    List<SaleAttributeVo> vo2 = nameVojy.getSaleVo();
//                    String condition2 = "(1!=1";
//                    for (SaleAttributeVo saleAttributeVo : vo2) {
//                        //condition2 += " or (swid is null) or trim(swid)=''";
//                        condition2 += " or (" + saleAttributeVo.getLieName() + " is null) or trim(" + saleAttributeVo.getLieName() + ")=''";
//                    }
//                    builder.where(new WhereCondition.StringCondition(condition2 + ")"));
//                }
//            } else if (zzJY.equals("通过校验")) {
//                List<SaleAttributeNameVo> nameVoJYXOne = shEntity.getNameVoJYXOne();
//                if (nameVoJYXOne != null && nameVoJYXOne.size() > 0) {
//                    SaleAttributeNameVo nameVojy = (SaleAttributeNameVo) nameVoJYXOne.get(0);
//                    List<SaleAttributeVo> vo2 = nameVojy.getSaleVo();
//                    String condition2 = "(1==1";
//                    for (SaleAttributeVo saleAttributeVo : vo2) {
//                        //condition2 += " or (swid is not null) or trim(swid)!=''";
//                        //condition2 += " or (" + saleAttributeVo.getLieName() + " is not null) or trim(" + saleAttributeVo.getLieName() + ")!=''";
//                        //condition2 += " and (swid is not null) and trim(swid)!=''";
//                        //condition2 += " and (" + saleAttributeVo.getLieName() + " is not null) and trim(" + saleAttributeVo.getLieName() + ")!=''";
//                        condition2 += " and (" + saleAttributeVo.getLieName() + " is not null) and " + saleAttributeVo.getLieName() + "!=''";
//                    }
//                    builder.where(new WhereCondition.StringCondition(condition2 + ")"));
//                }
//            }
//        }

        //保护校验二级项
        List<BHPZ> bhpzJY = new ArrayList<>(); //未通过校验
        List<BHPZ> bhpzJYTG = new ArrayList<>(); //通过校验
        if (zzJY != null && !zzJY.equals("")) {
            List<SaleAttributeNameVo> nameVoJYX = shEntity.getNameVoJYX();
            if (nameVoJYX != null && nameVoJYX.size() > 0) {
                SaleAttributeNameVo nameVojy = (SaleAttributeNameVo) nameVoJYX.get(0);
                List<SaleAttributeVo> vo2 = nameVojy.getSaleVo();
                for (int w = 0; w < listBhpz.size(); w++) {
                    for (SaleAttributeVo saleAttributeVo : vo2) {
                        if (saleAttributeVo.getValue().equals("设备识别代码")) {
                            if (listBhpz.get(w).getSfsbm() == null || (listBhpz.get(w).getSfsbm() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("实物ID")) {
                            if (listBhpz.get(w).getSw_id() == null || (listBhpz.get(w).getSw_id() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("装置名称")) {
                            if (listBhpz.get(w).getBhmc() == null || (listBhpz.get(w).getBhmc() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("是否六统一")) {
                            if (listBhpz.get(w).getSfltysb() == null || (listBhpz.get(w).getSfltysb() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("制造厂家")) {
                            if (listBhpz.get(w).getZzcj() == null || (listBhpz.get(w).getZzcj() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("装置类别")) {
                            if (listBhpz.get(w).getBhlb() == null || (listBhpz.get(w).getBhlb() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("装置型号")) {
                            if (listBhpz.get(w).getBhxh() == null || (listBhpz.get(w).getBhxh() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("出厂日期")) {
                            if (listBhpz.get(w).getCcrq() == null || (listBhpz.get(w).getCcrq() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("是否就地化设备")) {
                            if (listBhpz.get(w).getSfjdhzz() == null || (listBhpz.get(w).getSfjdhzz() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("板卡数量")) {
                            if ((listBhpz.get(w).getBksl() + "") == null || (listBhpz.get(w).getBksl() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("保护分类")) {
                            if (listBhpz.get(w).getBhfl() == null || (listBhpz.get(w).getBhfl() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("保护类型")) {
                            if (listBhpz.get(w).getBhlx() == null || (listBhpz.get(w).getBhlx() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("一次设备类型")) {
                            if (listBhpz.get(w).getYcsblx() == null || (listBhpz.get(w).getYcsblx() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("一次设备名称")) {
                            if (listBhpz.get(w).getYcsbmc() == null || (listBhpz.get(w).getYcsbmc() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("电压等级")) {
                            if ((listBhpz.get(w).getDydj() + "") == null || (listBhpz.get(w).getDydj() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("单位名称")) {
                            if (listBhpz.get(w).getCzgldw() == null || (listBhpz.get(w).getCzgldw() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("调度单位")) {
                            if (listBhpz.get(w).getDddw() == null || (listBhpz.get(w).getDddw() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("所属屏柜")) {
                            if (listBhpz.get(w).getSzpg() == null || (listBhpz.get(w).getSzpg() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("投运日期")) {
                            if (listBhpz.get(w).getTyrq() == null || (listBhpz.get(w).getTyrq() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("设计单位")) {
                            if (listBhpz.get(w).getSjdw() == null || (listBhpz.get(w).getSjdw() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("基建单位")) {
                            if (listBhpz.get(w).getJjdw() == null || (listBhpz.get(w).getJjdw() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("运行单位")) {
                            if (listBhpz.get(w).getYxdw() == null || (listBhpz.get(w).getYxdw() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("维护单位")) {
                            if (listBhpz.get(w).getWhdw() == null || (listBhpz.get(w).getWhdw() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("设备属性")) {
                            if (listBhpz.get(w).getBhsx() == null || (listBhpz.get(w).getBhsx() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("套别")) {
                            if (listBhpz.get(w).getTb() == null || (listBhpz.get(w).getTb() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("设备状态")) {
                            if (listBhpz.get(w).getYxzt() == null || (listBhpz.get(w).getYxzt() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("数据采集方式")) {
                            if (listBhpz.get(w).getSjcjfs() == null || (listBhpz.get(w).getSjcjfs() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("出口方式")) {
                            if (listBhpz.get(w).getCkfs() == null || (listBhpz.get(w).getCkfs() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("定期检验周期")) {
                            if ((listBhpz.get(w).getDqjyzq() + "") == null || (listBhpz.get(w).getDqjyzq() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("跳闸关系")) {
                            if (listBhpz.get(w).getKgbh() == null || (listBhpz.get(w).getKgbh() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("名称属性")) {
                            if (listBhpz.get(w).getBhmcsx() == null || (listBhpz.get(w).getBhmcsx() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("统计运行时间")) {
                            if (listBhpz.get(w).getSftjyxsj() == null || (listBhpz.get(w).getSftjyxsj() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("上次检修时间")) {
                            if (listBhpz.get(w).getScdqjysj() == null || (listBhpz.get(w).getScdqjysj() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("电源插件型号")) {
                            if (listBhpz.get(w).getDycjxh() == null || (listBhpz.get(w).getDycjxh() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("电源插件更换日期")) {
                            if (listBhpz.get(w).getDycjghrq() == null || (listBhpz.get(w).getDycjghrq() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("数字通道数")) {
                            if (listBhpz.get(w).getSztds() == null || (listBhpz.get(w).getSztds() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("模拟通道数")) {
                            if (listBhpz.get(w).getMntds() == null || (listBhpz.get(w).getMntds() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("额定变比")) {
                            if (listBhpz.get(w).getEdbb() == null || (listBhpz.get(w).getEdbb() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("实际变比")) {
                            if (listBhpz.get(w).getSjbb() == null || (listBhpz.get(w).getSjbb() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("准确级")) {
                            if (listBhpz.get(w).getZqj() == null || (listBhpz.get(w).getZqj() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("CT二次额定电流")) {
                            if (listBhpz.get(w).getCteceddl() == null || (listBhpz.get(w).getCteceddl() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("资产编号")) {
                            if (listBhpz.get(w).getZcbh() == null || (listBhpz.get(w).getZcbh() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("资产性质")) {
                            if (listBhpz.get(w).getZcxz() == null || (listBhpz.get(w).getZcxz() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("资产单位")) {
                            if (listBhpz.get(w).getZcdw() == null || (listBhpz.get(w).getZcdw() + "").trim().equals("")) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } //以上保护校验一级项

                        else if (saleAttributeVo.getValue().equals("六统一标准版本")) {
                            if (listBhpz.get(w).getSfltysb().equals("是") && (listBhpz.get(w).getLtybzbb() == null || (listBhpz.get(w).getLtybzbb() + "").trim().equals(""))) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("故障录波器类型")) {
                            if (listBhpz.get(w).getBhlb().equals("故障录波器") && (listBhpz.get(w).getGzlbqlx() == null || (listBhpz.get(w).getGzlbqlx() + "").trim().equals(""))) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("装置类别细化")) {
                            if (!listBhpz.get(w).getBhlb().equals("安全自动装置") && (listBhpz.get(w).getBhlbxh() == null || (listBhpz.get(w).getBhlbxh() + "").trim().equals(""))) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("设备类型")) {
                            if (listBhpz.get(w).getBhlb().equals("安全自动装置") && (listBhpz.get(w).getBhlbxh() == null || (listBhpz.get(w).getBhlbxh() + "").trim().equals(""))) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("设备功能配置")) {
                            if (listBhpz.get(w).getBhlb().equals("安全自动装置") && listBhpz.get(w).getBhlbxh().equals("频率电压紧急控制装置") && (listBhpz.get(w).getSbgnpz() == null || (listBhpz.get(w).getSbgnpz() + "").trim().equals(""))) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("测距形式")) {
                            if (listBhpz.get(w).getBhlb().equals("故障测距装置") && (listBhpz.get(w).getCjxx() == null || (listBhpz.get(w).getCjxx() + "").trim().equals(""))) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("是否接入调度主站")) {
                            if (listBhpz.get(w).getBhlb().equals("保护故障信息系统子站") && (listBhpz.get(w).getSfjrzz() == null || (listBhpz.get(w).getSfjrzz() + "").trim().equals(""))) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("退运日期")) {
                            if (listBhpz.get(w).getYxzt().equals("退运") && (listBhpz.get(w).getTcyxsj() == null || (listBhpz.get(w).getTcyxsj() + "").trim().equals(""))) {
                                bhpzJY.add(listBhpz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("通道类型") || saleAttributeVo.getValue().equals("是否复用") || saleAttributeVo.getValue().equals("通道装置型号")) {
                            //先获取通道
                            if ((listBhpz.get(w).getBhlb().equals("线路保护") || listBhpz.get(w).getBhlb().equals("过电压及远方跳闸保护"))) {
                                List<TDXX> load_data = new ArrayList<>();
                                load_data = getTDXX(listBhpz.get(w).getId() + "");
                                if (!load_data.isEmpty() && load_data.size() > 0) {
                                    if (saleAttributeVo.getValue().equals("通道类型")) {
                                        for (int i = 0; i < load_data.size(); i++) {
                                            if (load_data.get(i).getTdlx() == null || (load_data.get(i).getTdlx() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;

                                    } else if (saleAttributeVo.getValue().equals("是否复用")) {
                                        for (int i = 0; i < load_data.size(); i++) {
                                            if (load_data.get(i).getSffy() == null || (load_data.get(i).getSffy() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;

                                    } else if (saleAttributeVo.getValue().equals("通道装置型号")) {
                                        for (int i = 0; i < load_data.size(); i++) {
                                            if (load_data.get(i).getTdzzxh() == null || (load_data.get(i).getTdzzxh() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    }
                                } else {
                                    bhpzJY.add(listBhpz.get(w));
                                    break;
                                }
                            }
                            continue;

                        } else if (saleAttributeVo.getValue().equals("所属安控系统调度命名") || saleAttributeVo.getValue().equals("安控站点类型")) {
                            //先获取安控
                            if ((listBhpz.get(w).getBhlb().equals("安全自动装置") && listBhpz.get(w).getBhlbxh().equals("安全稳定控制装置"))) {
                                List<AKXTGX> akxtgx_data = new ArrayList<>();
                                akxtgx_data = getAKXTGX(listBhpz.get(w).getId() + "");
                                if (!akxtgx_data.isEmpty() && akxtgx_data.size() > 0) {
                                    if (saleAttributeVo.getValue().equals("所属安控系统调度命名")) {
                                        for (int i = 0; i < akxtgx_data.size(); i++) {
                                            String akxtm = "";
                                            if (getAKXT(akxtgx_data.get(i).getAkxtid()).size() > 0) {
                                                akxtm = getAKXT(akxtgx_data.get(i).getAkxtid()).get(0).getAkxtm() + "";
                                                if (akxtm == null || akxtm.trim().equals("")) {
                                                    bhpzJY.add(listBhpz.get(w));
                                                    break;
                                                }
                                                continue;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("安控站点类型")) {
                                        for (int i = 0; i < akxtgx_data.size(); i++) {
                                            if (akxtgx_data.get(i).getAkzdlx() == null || (akxtgx_data.get(i).getAkzdlx() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    }

                                } else {
                                    bhpzJY.add(listBhpz.get(w));
                                    break;
                                }
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("连接器组件数量")
                                || saleAttributeVo.getValue().equals("连接器插座组件制造厂家")
                                || saleAttributeVo.getValue().equals("连接器插座组件型号")) {
                            if (listBhpz.get(w).getSfjdhzz().equals("是")) {
                                if (saleAttributeVo.getValue().equals("连接器组件数量")) {
                                    if (listBhpz.get(w).getLjqsl() == null || (listBhpz.get(w).getLjqsl() + "").trim().equals("")) {
                                        bhpzJY.add(listBhpz.get(w));
                                        break;
                                    }
                                    continue;
                                } else if (saleAttributeVo.getValue().equals("连接器插座组件制造厂家")) {
                                    if (listBhpz.get(w).getLjqzzcj() == null || (listBhpz.get(w).getLjqzzcj() + "").trim().equals("")) {
                                        bhpzJY.add(listBhpz.get(w));
                                        break;
                                    }
                                    continue;
                                } else if (saleAttributeVo.getValue().equals("连接器插座组件型号")) {
                                    if (listBhpz.get(w).getLjqxh() == null || (listBhpz.get(w).getLjqxh() + "").trim().equals("")) {
                                        bhpzJY.add(listBhpz.get(w));
                                        break;
                                    }
                                    continue;
                                }
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("插头组件型号") || saleAttributeVo.getValue().equals("插头组件编号")
                                || saleAttributeVo.getValue().equals("插头组件制造厂家") || saleAttributeVo.getValue().equals("插头组件铅封日期")
                                || saleAttributeVo.getValue().equals("插头组件接口类型") || saleAttributeVo.getValue().equals("插头组件接口用途")
                                || saleAttributeVo.getValue().equals("端子箱")) {
                            if (listBhpz.get(w).getSfjdhzz().equals("是")) {
                                List<Object> ljqList = getICDOrBKXX(LJQXX.class, listBhpz.get(w).getId() + "");
                                if (ljqList != null && ljqList.size() > 0) {
                                    //去掉标记删除的连接器
                                    for (int i = 0; i < ljqList.size(); i++) {
                                        LJQXX ljqxx1 = (LJQXX) ljqList.get(i);
                                        if (!isAdd(ljqxx1)) {
                                            ljqList.remove(i);
                                        }
                                    }

                                    if (saleAttributeVo.getValue().equals("插头组件型号")) {
                                        for (int i = 0; i < ljqList.size(); i++) {
                                            if (((LJQXX) ljqList.get(i)).getCtzjxh() == null || (((LJQXX) ljqList.get(i)).getCtzjxh() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("插头组件编号")) {
                                        for (int i = 0; i < ljqList.size(); i++) {
                                            if (((LJQXX) ljqList.get(i)).getCtzjbh() == null || (((LJQXX) ljqList.get(i)).getCtzjbh() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("插头组件制造厂家")) {
                                        for (int i = 0; i < ljqList.size(); i++) {
                                            if (((LJQXX) ljqList.get(i)).getCtzjzzcj() == null || (((LJQXX) ljqList.get(i)).getCtzjzzcj() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("插头组件铅封日期")) {
                                        for (int i = 0; i < ljqList.size(); i++) {
                                            if (((LJQXX) ljqList.get(i)).getJtzjqfrq() == null || (((LJQXX) ljqList.get(i)).getJtzjqfrq() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("插头组件接口类型")) {
                                        for (int i = 0; i < ljqList.size(); i++) {
                                            if (((LJQXX) ljqList.get(i)).getJklx() == null || (((LJQXX) ljqList.get(i)).getJklx() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("插头组件接口用途")) {
                                        for (int i = 0; i < ljqList.size(); i++) {
                                            if (((LJQXX) ljqList.get(i)).getJkyt() == null || (((LJQXX) ljqList.get(i)).getJkyt() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("端子箱")) {
                                        for (int i = 0; i < ljqList.size(); i++) {
                                            if (((LJQXX) ljqList.get(i)).getDzpxx() == null || (((LJQXX) ljqList.get(i)).getDzpxx() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    }
                                }
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("ICD文件名称") || saleAttributeVo.getValue().equals("ICD文件版本")
                                || saleAttributeVo.getValue().equals("CRC32验证码") || saleAttributeVo.getValue().equals("MD5校验码")
                                || saleAttributeVo.getValue().equals("ICD文件最终修改时间") || saleAttributeVo.getValue().equals("选配功能")
                                || saleAttributeVo.getValue().equals("专业检测批次")) {
                            if (listBhpz.get(w).getSfltysb().equals("是") && listBhpz.get(w).getLtybzbb().equals("2013版")) {
                                List<Object> ltysbxhList = getBHRJBBByCode(true, true, listBhpz.get(w).getId() + "");
                                if (ltysbxhList != null && ltysbxhList.size() > 0) {
                                    if (saleAttributeVo.getValue().equals("ICD文件名称")) {
                                        for (int i = 0; i < ltysbxhList.size(); i++) {
                                            if (((LTYSBXH) ltysbxhList.get(i)).getWjmc() == null || (((LTYSBXH) ltysbxhList.get(i)).getWjmc() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("ICD文件版本")) {
                                        for (int i = 0; i < ltysbxhList.size(); i++) {
                                            if (((LTYSBXH) ltysbxhList.get(i)).getWjbb() == null || (((LTYSBXH) ltysbxhList.get(i)).getWjbb() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("CRC32验证码")) {
                                        for (int i = 0; i < ltysbxhList.size(); i++) {
                                            if (((LTYSBXH) ltysbxhList.get(i)).getCrc32() == null || (((LTYSBXH) ltysbxhList.get(i)).getCrc32() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("MD5校验码")) {
                                        for (int i = 0; i < ltysbxhList.size(); i++) {
                                            if (((LTYSBXH) ltysbxhList.get(i)).getMd5() == null || (((LTYSBXH) ltysbxhList.get(i)).getMd5() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("ICD文件最终修改时间")) {
                                        for (int i = 0; i < ltysbxhList.size(); i++) {
                                            if (((LTYSBXH) ltysbxhList.get(i)).getZzxgsj() == null || (((LTYSBXH) ltysbxhList.get(i)).getZzxgsj() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("选配功能")) {
                                        for (int i = 0; i < ltysbxhList.size(); i++) {
                                            if (((LTYSBXH) ltysbxhList.get(i)).getXp() == null || (((LTYSBXH) ltysbxhList.get(i)).getXp() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("专业检测批次")) {
                                        for (int i = 0; i < ltysbxhList.size(); i++) {
                                            if (((LTYSBXH) ltysbxhList.get(i)).getZyjcpc() == null || (((LTYSBXH) ltysbxhList.get(i)).getZyjcpc() + "").trim().equals("")) {
                                                bhpzJY.add(listBhpz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    }

                                } else {
                                    bhpzJY.add(listBhpz.get(w));
                                    break;
                                }
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("模块名称") || saleAttributeVo.getValue().equals("软件版本")
                                || saleAttributeVo.getValue().equals("校验码") || saleAttributeVo.getValue().equals("生成时间")) {
                            if (listBhpz.get(w).getSfltysb().equals("是") && listBhpz.get(w).getLtybzbb().equals("2013版")) {
                                List<Object> list = getBHRJBBByCode(true, true, listBhpz.get(w).getId() + "");
                                if (list != null && list.size() > 0) {
                                    LTYSBXH ltysbxh = (LTYSBXH) list.get(0);
                                    if (saleAttributeVo.getValue().equals("软件版本")) {
                                        if (ltysbxh.getRjbb() == null || (ltysbxh.getRjbb() + "").trim().equals("")) {
                                            bhpzJY.add(listBhpz.get(w));
                                        }
                                        continue;
                                    }
                                    continue;
                                } else {
                                    bhpzJY.add(listBhpz.get(w));
                                    break;
                                }
                            } else {//分模块和不分模块
                                List<Object> list = getBHRJBBByCode(false, false, listBhpz.get(w).getId() + "");
                                if (list != null && list.size() > 0) {
                                    List<BHXHRJBB> rjbbList = new ArrayList<>();
                                    for (Object o : list) {
                                        rjbbList.add((BHXHRJBB) o);
                                    }

                                    //非六统一，不分模块
                                    if (rjbbList.size() == 1 && rjbbList.get(0) != null && rjbbList.get(0).getBblx() == null) {
                                        if (saleAttributeVo.getValue().equals("软件版本") && (rjbbList.get(0).getBb() == null || (rjbbList.get(0).getBb() + "").trim().equals(""))) {
                                            bhpzJY.add(listBhpz.get(w));
                                            break;
                                        } else if (saleAttributeVo.getValue().equals("校验码") && (rjbbList.get(0).getJym() == null || (rjbbList.get(0).getJym() + "").trim().equals(""))) {
                                            bhpzJY.add(listBhpz.get(w));
                                            break;
                                        } else if (saleAttributeVo.getValue().equals("生成时间") && (rjbbList.get(0).getSCSJ() == null || (rjbbList.get(0).getSCSJ() + "").trim().equals(""))) {
                                            bhpzJY.add(listBhpz.get(w));
                                            break;
                                        }
                                        continue;
                                    } else if (rjbbList.size() > 1 || rjbbList.size() == 1 && rjbbList.get(0) != null
                                            && rjbbList.get(0).getBblx() != null && rjbbList.get(0).getBblx().equals("非六统一，分模块")) {//非六统一,分模块
                                        if (saleAttributeVo.getValue().equals("模块名称")) {
                                            for (int i = 0; i < rjbbList.size(); i++) {
                                                if (rjbbList.get(i).getMkmc() == null || (rjbbList.get(i).getMkmc() + "").trim().equals("")) {
                                                    bhpzJY.add(listBhpz.get(w));
                                                    break;
                                                }
                                                continue;
                                            }
                                            continue;

                                        } else if (saleAttributeVo.getValue().equals("软件版本")) {
                                            for (int i = 0; i < rjbbList.size(); i++) {
                                                if (rjbbList.get(i).getBb() == null || (rjbbList.get(i).getBb() + "").trim().equals("")) {
                                                    bhpzJY.add(listBhpz.get(w));
                                                    break;
                                                }
                                                continue;
                                            }
                                            continue;
                                        } else if (saleAttributeVo.getValue().equals("软件校验码版本")) {
                                            for (int i = 0; i < rjbbList.size(); i++) {
                                                if (rjbbList.get(i).getJym() == null || (rjbbList.get(i).getJym() + "").trim().equals("")) {
                                                    bhpzJY.add(listBhpz.get(w));
                                                    break;
                                                }
                                                continue;
                                            }
                                            continue;
                                        } else if (saleAttributeVo.getValue().equals("生成时间")) {
                                            for (int i = 0; i < rjbbList.size(); i++) {
                                                if (rjbbList.get(i).getSCSJ() == null || (rjbbList.get(i).getSCSJ() + "").trim().equals("")) {
                                                    bhpzJY.add(listBhpz.get(w));
                                                    break;
                                                }
                                                continue;
                                            }
                                            continue;
                                        }
                                    }
                                } else {
                                    bhpzJY.add(listBhpz.get(w));
                                    break;
                                }
                            }
                        }

                    }
                }
            }
        }

        //保护校验二级项--------------------------------------------------------
        if (zzJY != null && !zzJY.equals("")) {
            //获取通过校验的装置
            boolean noTGJY; //未通过校验
            for (int i = 0; i < listBhpz.size(); i++) {
                noTGJY = false;
                for (int j = 0; j < bhpzJY.size(); j++) {
                    if (listBhpz.get(i).getId() == bhpzJY.get(j).getId()) {
                        noTGJY = true;
                        break;
                    } else {
                        continue;
                    }
                }
                if (!noTGJY) {
                    bhpzJYTG.add(listBhpz.get(i));
                }
            }
            if (zzJY.equals("未通过校验")) {
                return groupList(bhpzJY, offset);
            } else if (zzJY.equals("通过校验")) {
                return groupList(bhpzJYTG, offset);
            }
        }
        return groupList(listBhpz, offset);
//      return builder.offset(offset * 100).limit(100).build().list();
    }

    //保护装置列表有码的排序
    public List<BHPZ> getBHPZs(SearchEntity shEntity) {
        daoSession.clear();
        QueryBuilder<BHPZ> builder = daoSession.getBHPZDao().queryBuilder();

        CZCS czcs = getCZCSByGLDW();
        builder.where(BHPZDao.Properties.Czmc.eq(czcs.getCzmc()));
        builder.where(BHPZDao.Properties.Czgldw.eq(czcs.getGldw()));

        if (!shEntity.getZZName().equals("")) {
            builder.where(BHPZDao.Properties.Bhmc.like("%" + shEntity.getZZName() + "%"));
        }

        boolean hasD = false;
        String zzJY = "";
        List<SaleAttributeNameVo> nameVos = shEntity.getNameVos();
        if (nameVos != null && nameVos.size() > 0) {
            for (SaleAttributeNameVo nameVo : nameVos) {
                String searchName = nameVo.getName();
                List<SaleAttributeVo> vo = nameVo.getSaleVo();
                if (searchName.equals("一次设备名称")) {
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        builder.where(BHPZDao.Properties.Ycsbmc.like("%" + saleAttributeVo.getValue() + "%"));
                    }
                } else if (searchName.equals("制造厂家")) {
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        builder.where(BHPZDao.Properties.Zzcj.like("%" + saleAttributeVo.getValue() + "%"));
                    }
                } else if (searchName.equals("型号")) {
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        builder.where(BHPZDao.Properties.Bhxh.like("%" + saleAttributeVo.getValue() + "%"));
                    }
                } else if (searchName.equals("数据状态")) {
                    List<String> stateList = new ArrayList<>();
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        if (saleAttributeVo.getValue().equals("未操作")) {
                            stateList.add("N");
                        } else if (saleAttributeVo.getValue().equals("已操作")) {
                            stateList.add("M");
                            stateList.add("H");
                            stateList.add("S");
                            stateList.add("C");
                            stateList.add("MH");
                            stateList.add("SH");
                            stateList.add("CH");
                        } else if (saleAttributeVo.getValue().equals("新增")) {
                            stateList.add("C");
                            stateList.add("CH");
                        } else if (saleAttributeVo.getValue().equals("已删除")) {
                            stateList.add("D");
                            hasD = true;
                        } else if (saleAttributeVo.getValue().equals("已导出")) {
                            stateList.add("F");
                        } else if (saleAttributeVo.getValue().equals("未核对")) {
                            stateList.add("M");
                            stateList.add("S");
                            stateList.add("C");
                            stateList.add("N");
                        } else if (saleAttributeVo.getValue().equals("已核对")) {
                            stateList.add("H");
                            stateList.add("MH");
                            stateList.add("SH");
                            stateList.add("CH");
                        }
                    }
                    String conditions = "(1!=1";
                    for (String s : stateList) {
                        if (s.equals("N")) {
                            conditions += " or (ed_tag is null)";
                            continue;
                        }
                        if (s.equals("F")) {
                            conditions += " or sb =='" + s + "'";
                            continue;
                        }
                        conditions += " or ed_tag =='" + s + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("电压等级")) {
                    String conditions = "(dydj !=null";
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        conditions += " or dydj =='" + saleAttributeVo.getValue() + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("装置类别")) {
                    String conditions = "(bhlb !=null";
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        conditions += " or bhlb =='" + saleAttributeVo.getValue() + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("调度单位")) {
                    String conditions = "(dddw !=null";
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        conditions += " or dddw =='" + saleAttributeVo.getValue() + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("装置校验")) {
                    if (vo.get(0).getValue().equals("通过校验")) {
                        builder.where(BHPZDao.Properties.Jy.eq("TG"));
                    } else if (vo.get(0).getValue().equals("未通过校验")) {
                        //conditions="ed_tag is null or ed_tag='WTG'";
                        builder.whereOr(BHPZDao.Properties.Jy.isNull(),
                                BHPZDao.Properties.Jy.eq(""),
                                BHPZDao.Properties.Jy.notEq("TG"));
                    }
                }
            }
        }

        if (!hasD) {
            builder.whereOr(BHPZDao.Properties.Ed_tag.isNull(),
                    BHPZDao.Properties.Ed_tag.notEq("D"));
        }

        //--------------排序------------------------
        List<BHPZ> list_youma = null;
        list_youma = builder.where(BHPZDao.Properties.Sw_id.notEq("")).stringOrderCollation("COLLATE LOCALIZED").
                orderAsc(BHPZDao.Properties.Ycsbmc).orderAsc(BHPZDao.Properties.Bhmc).build().list();

        return list_youma;
    }

    //过滤要显示的连接器
    public Boolean isAdd(LJQXX ljqxx1) {
        Boolean yy = false; //原因
        if (ljqxx1.getGhyy() == null || "".equals(ljqxx1.getGhyy())) {
            yy = true;
        } else {
            yy = false;
        }
        Boolean sj = false; //原因
        if (ljqxx1.getGhsj() == null || "".equals(ljqxx1.getGhsj())) {
            sj = true;
        } else {
            sj = false;
        }
        Boolean edTag = false; //标记删除
        if (ljqxx1.getED_TAG() != null && ljqxx1.getED_TAG().equals("D")) {
            edTag = false;
        } else {
            edTag = true;
        }


        //sb是非D或者变更时间和变更原因都为null时,返回true, 加载
        if (ljqxx1.getSb() != null) {
            if (!ljqxx1.getSb().equals("D") && yy && sj && edTag) {
                return true;
            } else {
                return false;
            }
        } else if (ljqxx1.getSb() == null && yy && sj && edTag) {
            return true;
        } else {
            return false;
        }
    }

    //分页取保护装置列表数据
    public List<BHPZ> groupList(List<BHPZ> list, int offset) {
        List<BHPZ> result_list = new ArrayList<>();
        if (list != null && list.size() > 0) {
            int listSize = list.size();
            int toIndex = 100; //单页个数
            int i2 = offset * toIndex;
            if (i2 < listSize) {
                if ((i2 + toIndex) > listSize) {        //最后没有100条数据则剩余几条就装几条
                    toIndex = listSize - i2;
                }
                result_list = list.subList(i2, i2 + toIndex);
            }
        }
        return result_list;
    }

    //辅助装置列表无码的排序
    @Override
    public List<FZBHSB> getFZBHSB(int offset, SearchEntity shEntity) {
        QueryBuilder<FZBHSB> builder = daoSession.getFZBHSBDao().queryBuilder();
        CZCS czcs = getCZCSByGLDW();
        builder.where(FZBHSBDao.Properties.Czmc.eq(czcs.getCzmc()));
        builder.where(FZBHSBDao.Properties.Czgldw.eq(czcs.getGldw()));

        if (!shEntity.getZZName().equals("")) {
            builder.where(FZBHSBDao.Properties.Sbmc.like("%" + shEntity.getZZName() + "%"));
        }

        List<SaleAttributeNameVo> nameVos = shEntity.getNameVos();
        boolean hasD = false;
        String zzJY = "";
        if (nameVos != null && nameVos.size() > 0) {
            for (SaleAttributeNameVo nameVo : nameVos) {
                String searchName = nameVo.getName();
                List<SaleAttributeVo> vo = nameVo.getSaleVo();
                if (searchName.equals("一次设备名称")) {
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        builder.where(FZBHSBDao.Properties.Ycsbmc.like("%" + saleAttributeVo.getValue() + "%"));
                    }
                } else if (searchName.equals("制造厂家")) {
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        builder.where(FZBHSBDao.Properties.Cj.like("%" + saleAttributeVo.getValue() + "%"));
                    }
                } else if (searchName.equals("型号")) {
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        builder.where(FZBHSBDao.Properties.Sbxh.like("%" + saleAttributeVo.getValue() + "%"));
                    }
                } else if (searchName.equals("数据状态")) {
                    List<String> stateList = new ArrayList<>();
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        if (saleAttributeVo.getValue().equals("未操作")) {
                            stateList.add("N");
                        } else if (saleAttributeVo.getValue().equals("已操作")) {
                            stateList.add("M");
                            stateList.add("H");
                            stateList.add("S");
                            stateList.add("C");
                            stateList.add("MH");
                            stateList.add("SH");
                            stateList.add("CH");
                        } else if (saleAttributeVo.getValue().equals("新增")) {
                            stateList.add("C");
                            stateList.add("CH");
                        } else if (saleAttributeVo.getValue().equals("已删除")) {
                            stateList.add("D");
                            hasD = true;
                        } else if (saleAttributeVo.getValue().equals("已导出")) {
                            stateList.add("F");
                        } else if (saleAttributeVo.getValue().equals("未核对")) {
                            stateList.add("M");
                            stateList.add("S");
                            stateList.add("C");
                            stateList.add("N");
                        } else if (saleAttributeVo.getValue().equals("已核对")) {
                            stateList.add("H");
                            stateList.add("MH");
                            stateList.add("SH");
                            stateList.add("CH");
                        }
                    }
                    String conditions = "(1!=1";
                    for (String s : stateList) {
                        if (s.equals("N")) {
                            conditions += " or (ed_tag is null)";
                            continue;
                        }
                        if (s.equals("F")) {
                            conditions += " or sb =='" + s + "'";
                            continue;
                        }
                        conditions += " or ed_tag =='" + s + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("电压等级")) {
                    String conditions = "(dydj !=null";
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        conditions += " or dydj =='" + saleAttributeVo.getValue() + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("装置类别")) {
                    String conditions = "(fzsblx !=null";
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        conditions += " or fzsblx =='" + saleAttributeVo.getValue() + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("调度单位")) {
                    String conditions = "(dddw !=null";
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        conditions += " or dddw =='" + saleAttributeVo.getValue() + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("装置校验")) {
                    if (vo.get(0).getValue().equals("通过校验")) {
                        builder.where(FZBHSBDao.Properties.Jy.eq("TG"));
                    } else if (vo.get(0).getValue().equals("未通过校验")) {
                        //conditions="ed_tag is null or ed_tag='WTG'";
                        builder.whereOr(FZBHSBDao.Properties.Jy.isNull(),
                                FZBHSBDao.Properties.Jy.eq(""),
                                FZBHSBDao.Properties.Jy.notEq("TG"));
                    }
                }
            }
        }

        if (!hasD) {
            builder.whereOr(FZBHSBDao.Properties.Ed_tag.isNull(),
                    FZBHSBDao.Properties.Ed_tag.notEq("D"));
        }

        //--------------排序------------------------
        List<FZBHSB> listFz = null;  //全部装置
        if (nameVos != null && nameVos.size() > 0) {
            //排序
            for (SaleAttributeNameVo nameVo : nameVos) {
                String searchName = nameVo.getName();
                List<SaleAttributeVo> vo = nameVo.getSaleVo();
                if (searchName.equals("排序")) {
                    if (!vo.get(0).getValue().equals("默认排序先无码后有码")) {
                        if (vo.get(0).getValue().equals("电压等级从高到低")) {
                            listFz = builder.where(FZBHSBDao.Properties.Dydj.isNotNull()).stringOrderCollation("COLLATE LOCALIZED").
                                    orderDesc(FZBHSBDao.Properties.Dydj).build().list();
                        } else if (vo.get(0).getValue().equals("保护类别")) {
                            listFz = builder.where(FZBHSBDao.Properties.Fzsblx.isNotNull()).stringOrderCollation("COLLATE LOCALIZED").
                                    orderAsc(FZBHSBDao.Properties.Fzsblx).build().list();
                        } else if (vo.get(0).getValue().equals("制造厂家")) {
                            listFz = builder.where(FZBHSBDao.Properties.Cj.isNotNull()).stringOrderCollation("COLLATE LOCALIZED").
                                    orderAsc(FZBHSBDao.Properties.Cj).build().list();
                        }
                    } else {//默认排序
                        List<FZBHSB> list_youma;
                        list_youma = getFZBHSBs(shEntity);
                        listFz = builder.where(FZBHSBDao.Properties.Sw_id.eq("")).stringOrderCollation("COLLATE LOCALIZED").
                                orderAsc(FZBHSBDao.Properties.Ycsbmc).orderAsc(FZBHSBDao.Properties.Sbmc).build().list();//无码
                        listFz.addAll(list_youma);
                        break;
                    }
                } else if (nameVos.size() == 1) {
                    List<FZBHSB> list_youma;
                    list_youma = getFZBHSBs(shEntity);
                    listFz = builder.where(FZBHSBDao.Properties.Sw_id.eq("")).stringOrderCollation("COLLATE LOCALIZED").
                            orderAsc(FZBHSBDao.Properties.Ycsbmc).orderAsc(FZBHSBDao.Properties.Sbmc).build().list();
                    listFz.addAll(list_youma);
                    break;
                }
            }
        } else {
            //--------------排序------------------------
            List<FZBHSB> list_youma;
            list_youma = getFZBHSBs(shEntity);
            listFz = builder.where(FZBHSBDao.Properties.Sw_id.eq("")).stringOrderCollation("COLLATE LOCALIZED").
                    orderAsc(FZBHSBDao.Properties.Ycsbmc).orderAsc(FZBHSBDao.Properties.Sbmc).build().list();
            listFz.addAll(list_youma);
        }


        //---------------------以下代码暂时作废--------------
        //校验一级项
//        List<SaleAttributeNameVo> nameVoJYXOne = shEntity.getNameVoJYXOne();
//        if (nameVoJYXOne != null && nameVoJYXOne.size() > 0) {
//            SaleAttributeNameVo nameVojy=(SaleAttributeNameVo)nameVoJYXOne.get(1);
//            List<SaleAttributeVo> vo2= nameVojy.getSaleVo();
//            String condition2 = "(1!=1";
//            for (SaleAttributeVo saleAttributeVo : vo2) {
//                //condition2 += " or (swid is null) or trim(swid)=''";
//                condition2 += " or ("+saleAttributeVo.getLieName()+" is null) or trim("+saleAttributeVo.getLieName()+")=''";
//            }
//
//            builder.where(new WhereCondition.StringCondition(condition2 + ")"));
//        }

        //辅助校验二级项--------------------------------------------------------
        List<FZBHSB> fzJY = new ArrayList<>(); //未通过校验
        List<FZBHSB> fzJYTG = new ArrayList<>(); //通过校验

        if (zzJY != null && !zzJY.equals("")) {
            List<SaleAttributeNameVo> nameVoJYX = shEntity.getNameVoJYX();
            if (nameVoJYX != null && nameVoJYX.size() > 0) {
                SaleAttributeNameVo nameVojy = (SaleAttributeNameVo) nameVoJYX.get(1);
                List<SaleAttributeVo> vo2 = nameVojy.getSaleVo();
                for (int w = 0; w < listFz.size(); w++) {
                    for (SaleAttributeVo saleAttributeVo : vo2) {
                        if (saleAttributeVo.getValue().equals("设备识别代码")) {
                            if (listFz.get(w).getSfsbm() == null || (listFz.get(w).getSfsbm() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;

                        } else if (saleAttributeVo.getValue().equals("实物ID")) {
                            if (listFz.get(w).getSw_id() == null || (listFz.get(w).getSw_id() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("装置名称")) {
                            if (listFz.get(w).getSbmc() == null || (listFz.get(w).getSbmc() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("是否六统一")) {
                            if (listFz.get(w).getSfltysb() == null || (listFz.get(w).getSfltysb() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("制造厂家")) {
                            if (listFz.get(w).getCj() == null || (listFz.get(w).getCj() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("装置类别")) {
                            if (listFz.get(w).getFzsblx() == null || (listFz.get(w).getFzsblx() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("装置型号")) {
                            if (listFz.get(w).getSbxh() == null || (listFz.get(w).getSbxh() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("出厂日期")) {
                            if (listFz.get(w).getCcrq() == null || (listFz.get(w).getCcrq() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("板卡数量")) {
                            if ((listFz.get(w).getBksl() + "") == null || (listFz.get(w).getBksl() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("保护分类")) {
                            if (listFz.get(w).getBhfl() == null || (listFz.get(w).getBhfl() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("保护类型")) {
                            if (listFz.get(w).getBhlx() == null || (listFz.get(w).getBhlx() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("一次设备类型")) {
                            if (listFz.get(w).getYcsblx() == null || (listFz.get(w).getYcsblx() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("一次设备名称")) {
                            if (listFz.get(w).getYcsbmc() == null || (listFz.get(w).getYcsbmc() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("电压等级")) {
                            if ((listFz.get(w).getDydj() + "") == null || (listFz.get(w).getDydj() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("单位名称")) {
                            if (listFz.get(w).getCzgldw() == null || (listFz.get(w).getCzgldw() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("调度单位")) {
                            if (listFz.get(w).getDddw() == null || (listFz.get(w).getDddw() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("投运日期")) {
                            if (listFz.get(w).getTyrq() == null || (listFz.get(w).getTyrq() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("设计单位")) {
                            if (listFz.get(w).getSjdw() == null || (listFz.get(w).getSjdw() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("基建单位")) {
                            if (listFz.get(w).getJjdw() == null || (listFz.get(w).getJjdw() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("运行单位")) {
                            if (listFz.get(w).getYxdw() == null || (listFz.get(w).getYxdw() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("维护单位")) {
                            if (listFz.get(w).getWhdw() == null || (listFz.get(w).getWhdw() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("设备属性")) {
                            if (listFz.get(w).getSbsx() == null || (listFz.get(w).getSbsx() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("套别")) {
                            if (listFz.get(w).getTb() == null || (listFz.get(w).getTb() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("设备状态")) {
                            if (listFz.get(w).getYxzt() == null || (listFz.get(w).getYxzt() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("定期检验周期")) {
                            if ((listFz.get(w).getDqjyzq() + "") == null || (listFz.get(w).getDqjyzq() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("上次检修时间")) {
                            if (listFz.get(w).getScdqjysj() == null || (listFz.get(w).getScdqjysj() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("资产编号")) {
                            if (listFz.get(w).getZcbh() == null || (listFz.get(w).getZcbh() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("资产性质")) {
                            if (listFz.get(w).getZcxz() == null || (listFz.get(w).getZcxz() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("资产单位")) {
                            if (listFz.get(w).getZcdw() == null || (listFz.get(w).getZcdw() + "").trim().equals("")) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } //以上辅助一级校验项
                        else if (saleAttributeVo.getValue().equals("六统一标准版本")) {
                            if (listFz.get(w).getSfltysb().equals("是") && (listFz.get(w).getLtybzbb() == null || (listFz.get(w).getLtybzbb() + "").trim().equals(""))) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("退运日期")) {
                            if (listFz.get(w).getYxzt().equals("退运") && (listFz.get(w).getTcyxsj() == null || (listFz.get(w).getTcyxsj() + "").trim().equals(""))) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("通道频率")) {
                            if (listFz.get(w).getFzsblx().equals("收发信机") && ((listFz.get(w).getTdpl() + "").trim().equals("") || (listFz.get(w).getTdpl() + "") == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("载波通道加工相")) {
                            if (listFz.get(w).getFzsblx().equals("收发信机") && ((listFz.get(w).getZbtdjgx() + "").trim().equals("") || listFz.get(w).getZbtdjgx() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("对时方式")) {
                            if (listFz.get(w).getFzsblx().equals("合并单元智能终端集成") && ((listFz.get(w).getDsfs() + "").trim().equals("") || listFz.get(w).getDsfs() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("发送光纤口数量")) {
                            if (listFz.get(w).getFzsblx().equals("合并单元智能终端集成") && ((listFz.get(w).getFsgxsl() + "").trim().equals("") || listFz.get(w).getFsgxsl() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("发送光纤口数量智能")) {
                            if (listFz.get(w).getFzsblx().equals("智能终端") && ((listFz.get(w).getFsgxsl() + "").trim().equals("") || listFz.get(w).getFsgxsl() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("接收光纤口数量")) {
                            if (listFz.get(w).getFzsblx().equals("合并单元智能终端集成") && ((listFz.get(w).getFsgxsl() + "").trim().equals("") || listFz.get(w).getFsgxsl() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("接收光纤口数量智能")) {
                            if (listFz.get(w).getFzsblx().equals("智能终端") && ((listFz.get(w).getFsgxsl() + "").trim().equals("") || listFz.get(w).getFsgxsl() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("光纤接口模式")) {
                            if (listFz.get(w).getFzsblx().equals("合并单元智能终端集成") && ((listFz.get(w).getGxjkms() + "").trim().equals("") || listFz.get(w).getGxjkms() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("光纤接口模式智能")) {
                            if (listFz.get(w).getFzsblx().equals("智能终端") && ((listFz.get(w).getGxjkms() + "").trim().equals("") || listFz.get(w).getGxjkms() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("光纤接口模式交换机")) {
                            if (listFz.get(w).getFzsblx().equals("交换机") && ((listFz.get(w).getGxjkms() + "").trim().equals("") || listFz.get(w).getGxjkms() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("合并单元功能")) {
                            if (listFz.get(w).getFzsblx().equals("合并单元智能终端集成") && ((listFz.get(w).getHbdygn() + "").trim().equals("") || listFz.get(w).getHbdygn() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("互感器类型")) {
                            if (listFz.get(w).getFzsblx().equals("合并单元智能终端集成") && ((listFz.get(w).getHgqlx() + "").trim().equals("") || listFz.get(w).getHgqlx() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("供电电源")) {
                            if (listFz.get(w).getFzsblx().equals("合并单元智能终端集成") && ((listFz.get(w).getGddy() + "").trim().equals("") || listFz.get(w).getGddy() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("装置属性")) {
                            if (listFz.get(w).getFzsblx().equals("合并单元智能终端集成") && ((listFz.get(w).getZzsx() + "").trim().equals("") || listFz.get(w).getZzsx() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("智能终端功能")) {
                            if (listFz.get(w).getFzsblx().equals("合并单元智能终端集成") && ((listFz.get(w).getZnzdgn() + "").trim().equals("") || listFz.get(w).getZnzdgn() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("智能终端功能智能")) {
                            if (listFz.get(w).getFzsblx().equals("智能终端") && ((listFz.get(w).getZnzdgn() + "").trim().equals("") || listFz.get(w).getZnzdgn() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("交换机功能")) {
                            if (listFz.get(w).getFzsblx().equals("交换机") && ((listFz.get(w).getJhjgn() + "").trim().equals("") || listFz.get(w).getJhjgn() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("交换机级联数")) {
                            if (listFz.get(w).getFzsblx().equals("交换机") && ((listFz.get(w).getJhjjls() + "").trim().equals("") || (listFz.get(w).getJhjjls() + "") == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("是否支持RSTP环网")) {
                            if (listFz.get(w).getFzsblx().equals("交换机") && ((listFz.get(w).getSfrstp() + "").trim().equals("") || listFz.get(w).getSfrstp() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("是否支持1588对时")) {
                            if (listFz.get(w).getFzsblx().equals("交换机") && ((listFz.get(w).getSfds() + "").trim().equals("") || listFz.get(w).getSfds() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("是否支持动态组播管理")) {
                            if (listFz.get(w).getFzsblx().equals("交换机") && ((listFz.get(w).getSfzb() + "").trim().equals("") || listFz.get(w).getSfzb() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("是否支持SNMP网络管理")) {
                            if (listFz.get(w).getFzsblx().equals("交换机") && ((listFz.get(w).getSfsnmp() + "").trim().equals("") || listFz.get(w).getSfsnmp() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("是否支持采用IEC61850上送交换机信息")) {
                            if (listFz.get(w).getFzsblx().equals("交换机") && ((listFz.get(w).getSfiec() + "").trim().equals("") || listFz.get(w).getSfiec() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("交换机类型")) {
                            if (listFz.get(w).getFzsblx().equals("交换机") && ((listFz.get(w).getJhjlx() + "").trim().equals("") || listFz.get(w).getJhjlx() == null)) {
                                fzJY.add(listFz.get(w));
                                break;
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("ICD文件名称") || saleAttributeVo.getValue().equals("ICD文件版本")
                                || saleAttributeVo.getValue().equals("CRC32验证码") || saleAttributeVo.getValue().equals("MD5校验码")
                                || saleAttributeVo.getValue().equals("ICD文件最终修改时间") || saleAttributeVo.getValue().equals("专业检测批次")) {
                            if (listFz.get(w).getSfltysb().equals("是") && listFz.get(w).getLtybzbb().equals("2013版")) {
                                List<Object> ltysbxhList = getBHRJBBByCode(true, true, listFz.get(w).getId() + "");
                                if (ltysbxhList != null && ltysbxhList.size() > 0) {
                                    if (saleAttributeVo.getValue().equals("ICD文件名称")) {
                                        for (int i = 0; i < ltysbxhList.size(); i++) {
                                            if (((LTYSBXH) ltysbxhList.get(i)).getWjmc() == null || (((LTYSBXH) ltysbxhList.get(i)).getWjmc() + "").trim().equals("")) {
                                                fzJY.add(listFz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("ICD文件版本")) {
                                        for (int i = 0; i < ltysbxhList.size(); i++) {
                                            if (((LTYSBXH) ltysbxhList.get(i)).getWjbb() == null || (((LTYSBXH) ltysbxhList.get(i)).getWjbb() + "").trim().equals("")) {
                                                fzJY.add(listFz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("CRC32验证码")) {
                                        for (int i = 0; i < ltysbxhList.size(); i++) {
                                            if (((LTYSBXH) ltysbxhList.get(i)).getCrc32() == null || (((LTYSBXH) ltysbxhList.get(i)).getCrc32() + "").trim().equals("")) {
                                                fzJY.add(listFz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("MD5校验码")) {
                                        for (int i = 0; i < ltysbxhList.size(); i++) {
                                            if (((LTYSBXH) ltysbxhList.get(i)).getMd5() == null || (((LTYSBXH) ltysbxhList.get(i)).getMd5() + "").trim().equals("")) {
                                                fzJY.add(listFz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("ICD文件最终修改时间")) {
                                        for (int i = 0; i < ltysbxhList.size(); i++) {
                                            if (((LTYSBXH) ltysbxhList.get(i)).getZzxgsj() == null || (((LTYSBXH) ltysbxhList.get(i)).getZzxgsj() + "").trim().equals("")) {
                                                fzJY.add(listFz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    } else if (saleAttributeVo.getValue().equals("专业检测批次")) {
                                        for (int i = 0; i < ltysbxhList.size(); i++) {
                                            if (((LTYSBXH) ltysbxhList.get(i)).getZyjcpc() == null || (((LTYSBXH) ltysbxhList.get(i)).getZyjcpc() + "").trim().equals("")) {
                                                fzJY.add(listFz.get(w));
                                                break;
                                            }
                                            continue;
                                        }
                                        continue;
                                    }

                                } else {
                                    fzJY.add(listFz.get(w));
                                    break;
                                }
                            }
                            continue;
                        } else if (saleAttributeVo.getValue().equals("模块名称") || saleAttributeVo.getValue().equals("软件版本")
                                || saleAttributeVo.getValue().equals("校验码") || saleAttributeVo.getValue().equals("生成时间")) {
                            if (listFz.get(w).getSfltysb().equals("是") && listFz.get(w).getLtybzbb().equals("2013版")) {
                                List<Object> list = getBHRJBBByCode(true, true, listFz.get(w).getId() + "");
                                if (list != null && list.size() > 0) {
                                    LTYSBXH ltysbxh = (LTYSBXH) list.get(0);
                                    if (saleAttributeVo.getValue().equals("软件版本")) {
                                        if (ltysbxh.getRjbb() == null || (ltysbxh.getRjbb() + "").trim().equals("")) {
                                            fzJY.add(listFz.get(w));
                                        }
                                        continue;
                                    }
                                    continue;
                                } else {
                                    fzJY.add(listFz.get(w));
                                    break;
                                }
                            } else {//分模块和不分模块
                                List<Object> list = getBHRJBBByCode(false, false, listFz.get(w).getId() + "");
                                if (list != null && list.size() > 0) {
                                    List<BHXHRJBB> rjbbList = new ArrayList<>();
                                    for (Object o : list) {
                                        rjbbList.add((BHXHRJBB) o);
                                    }

                                    //非六统一，不分模块
                                    if (rjbbList.size() == 1 && rjbbList.get(0) != null && rjbbList.get(0).getBblx() == null) {
                                        if (saleAttributeVo.getValue().equals("软件版本") && (rjbbList.get(0).getBb() == null || (rjbbList.get(0).getBb() + "").trim().equals(""))) {
                                            fzJY.add(listFz.get(w));
                                            break;
                                        } else if (saleAttributeVo.getValue().equals("校验码") && (rjbbList.get(0).getJym() == null || (rjbbList.get(0).getJym() + "").trim().equals(""))) {
                                            fzJY.add(listFz.get(w));
                                            break;
                                        } else if (saleAttributeVo.getValue().equals("生成时间") && (rjbbList.get(0).getSCSJ() == null || (rjbbList.get(0).getSCSJ() + "").trim().equals(""))) {
                                            fzJY.add(listFz.get(w));
                                            break;
                                        }
                                        continue;
                                    } else if (rjbbList.size() > 1 || rjbbList.size() == 1 && rjbbList.get(0) != null
                                            && rjbbList.get(0).getBblx() != null && rjbbList.get(0).getBblx().equals("非六统一，分模块")) {//非六统一,分模块
                                        if (saleAttributeVo.getValue().equals("模块名称")) {
                                            for (int i = 0; i < rjbbList.size(); i++) {
                                                if (rjbbList.get(i).getMkmc() == null || (rjbbList.get(i).getMkmc() + "").trim().equals("")) {
                                                    fzJY.add(listFz.get(w));
                                                    break;
                                                }
                                                continue;
                                            }
                                            continue;

                                        } else if (saleAttributeVo.getValue().equals("软件版本")) {
                                            for (int i = 0; i < rjbbList.size(); i++) {
                                                if (rjbbList.get(i).getBb() == null || (rjbbList.get(i).getBb() + "").trim().equals("")) {
                                                    fzJY.add(listFz.get(w));
                                                    break;
                                                }
                                                continue;
                                            }
                                            continue;
                                        } else if (saleAttributeVo.getValue().equals("软件校验码版本")) {
                                            for (int i = 0; i < rjbbList.size(); i++) {
                                                if (rjbbList.get(i).getJym() == null || (rjbbList.get(i).getJym() + "").trim().equals("")) {
                                                    fzJY.add(listFz.get(w));
                                                    break;
                                                }
                                                continue;
                                            }
                                            continue;
                                        } else if (saleAttributeVo.getValue().equals("生成时间")) {
                                            for (int i = 0; i < rjbbList.size(); i++) {
                                                if (rjbbList.get(i).getSCSJ() == null || (rjbbList.get(i).getSCSJ() + "").trim().equals("")) {
                                                    fzJY.add(listFz.get(w));
                                                    break;
                                                }
                                                continue;
                                            }
                                            continue;
                                        }
                                    }
                                } else {
                                    fzJY.add(listFz.get(w));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }


        //辅助校验二级项-----------------------------------
        if (zzJY != null && !zzJY.equals("")) {
            //获取通过校验的装置
            boolean noTGJY; //未通过校验
            for (int i = 0; i < listFz.size(); i++) {
                noTGJY = false;
                for (int j = 0; j < fzJY.size(); j++) {
                    if (listFz.get(i).getId() == fzJY.get(j).getId()) {
                        noTGJY = true;
                        break;
                    } else {
                        continue;
                    }
                }
                if (!noTGJY) {
                    fzJYTG.add(listFz.get(i));
                }
            }

            if (zzJY.equals("未通过校验")) {
                return groupLists(fzJY, offset);
            } else if (zzJY.equals("通过校验")) {
                return groupLists(fzJYTG, offset);
            }
        }
        return groupLists(listFz, offset);

    }

    //辅助装置列表有码的排序
    public List<FZBHSB> getFZBHSBs(SearchEntity shEntity) {
        QueryBuilder<FZBHSB> builder = daoSession.getFZBHSBDao().queryBuilder();
        CZCS czcs = getCZCSByGLDW();
        builder.where(FZBHSBDao.Properties.Czmc.eq(czcs.getCzmc()));
        builder.where(FZBHSBDao.Properties.Czgldw.eq(czcs.getGldw()));

        if (!shEntity.getZZName().equals("")) {
            builder.where(FZBHSBDao.Properties.Sbmc.like("%" + shEntity.getZZName() + "%"));
        }

        List<SaleAttributeNameVo> nameVos = shEntity.getNameVos();
        boolean hasD = false;
        if (nameVos != null && nameVos.size() > 0) {
            for (SaleAttributeNameVo nameVo : nameVos) {
                String searchName = nameVo.getName();
                List<SaleAttributeVo> vo = nameVo.getSaleVo();
                if (searchName.equals("一次设备名称")) {
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        builder.where(FZBHSBDao.Properties.Ycsbmc.like("%" + saleAttributeVo.getValue() + "%"));
                    }
                } else if (searchName.equals("制造厂家")) {
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        builder.where(FZBHSBDao.Properties.Cj.like("%" + saleAttributeVo.getValue() + "%"));
                    }
                } else if (searchName.equals("型号")) {
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        builder.where(FZBHSBDao.Properties.Sbxh.like("%" + saleAttributeVo.getValue() + "%"));
                    }
                } else if (searchName.equals("数据状态")) {
                    List<String> stateList = new ArrayList<>();
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        if (saleAttributeVo.getValue().equals("未操作")) {
                            stateList.add("N");
                        } else if (saleAttributeVo.getValue().equals("已操作")) {
                            stateList.add("M");
                            stateList.add("H");
                            stateList.add("S");
                            stateList.add("C");
                            stateList.add("MH");
                            stateList.add("SH");
                            stateList.add("CH");
                        } else if (saleAttributeVo.getValue().equals("新增")) {
                            stateList.add("C");
                            stateList.add("CH");
                        } else if (saleAttributeVo.getValue().equals("已删除")) {
                            stateList.add("D");
                            hasD = true;
                        } else if (saleAttributeVo.getValue().equals("已导出")) {
                            stateList.add("F");
                        } else if (saleAttributeVo.getValue().equals("未核对")) {
                            stateList.add("M");
                            stateList.add("S");
                            stateList.add("C");
                            stateList.add("N");
                        } else if (saleAttributeVo.getValue().equals("已核对")) {
                            stateList.add("H");
                            stateList.add("MH");
                            stateList.add("SH");
                            stateList.add("CH");
                        }
                    }
                    String conditions = "(1!=1";
                    for (String s : stateList) {
                        if (s.equals("N")) {
                            conditions += " or (ed_tag is null)";
                            continue;
                        }
                        if (s.equals("F")) {
                            conditions += " or sb =='" + s + "'";
                            continue;
                        }
                        conditions += " or ed_tag =='" + s + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("电压等级")) {
                    String conditions = "(dydj !=null";
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        conditions += " or dydj =='" + saleAttributeVo.getValue() + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("装置类别")) {
                    String conditions = "(fzsblx !=null";
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        conditions += " or fzsblx =='" + saleAttributeVo.getValue() + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("调度单位")) {
                    String conditions = "(dddw !=null";
                    for (SaleAttributeVo saleAttributeVo : vo) {
                        conditions += " or dddw =='" + saleAttributeVo.getValue() + "'";
                    }
                    builder.where(new WhereCondition.StringCondition(conditions + ")"));
                } else if (searchName.equals("装置校验")) {
                    if (vo.get(0).getValue().equals("通过校验")) {
                        builder.where(FZBHSBDao.Properties.Jy.eq("TG"));
                    } else if (vo.get(0).getValue().equals("未通过校验")) {
                        //conditions="ed_tag is null or ed_tag='WTG'";
                        builder.whereOr(FZBHSBDao.Properties.Jy.isNull(),
                                FZBHSBDao.Properties.Jy.eq(""),
                                FZBHSBDao.Properties.Jy.notEq("TG"));
                    }
                }
            }
        }

        if (!hasD) {
            builder.whereOr(FZBHSBDao.Properties.Ed_tag.isNull(),
                    FZBHSBDao.Properties.Ed_tag.notEq("D"));
        }

        //--------------排序------------------------
        List<FZBHSB> list_youma;
        list_youma = builder.where(FZBHSBDao.Properties.Sw_id.notEq("")).stringOrderCollation("COLLATE LOCALIZED").
                orderAsc(FZBHSBDao.Properties.Ycsbmc).orderAsc(FZBHSBDao.Properties.Sbmc).build().list();

        return list_youma;
    }

    //分页取辅助装置列表数据
    public List<FZBHSB> groupLists(List<FZBHSB> list, int offset) {
        List<FZBHSB> result_list = new ArrayList<>();
        if (list != null && list.size() > 0) {
            int listSize = list.size();
            int toIndex = 100; //单页个数
            int i2 = offset * toIndex;
            if (i2 < listSize) {
                if ((i2 + toIndex) > listSize) {        //最后没有100条数据则剩余几条就装几条
                    toIndex = listSize - i2;
                }
                result_list = list.subList(i2, i2 + toIndex);
            }
        }
        return result_list;
    }

    @Override
    public long getTotalSb(Object o, SearchEntity shEntity) {
        long count = 0;
        if (o == BHPZ.class) {
            QueryBuilder<BHPZ> builder = daoSession.getBHPZDao().queryBuilder();
            CZCS czcs = getCZCSByGLDW();
            builder.where(BHPZDao.Properties.Czmc.eq(czcs.getCzmc()));
            builder.where(BHPZDao.Properties.Czgldw.eq(czcs.getGldw()));

            if (!shEntity.getZZName().equals("")) {
                builder.where(BHPZDao.Properties.Bhmc.like("%" + shEntity.getZZName() + "%"));
            }

            //筛选
            List<SaleAttributeNameVo> nameVos = shEntity.getNameVos();
            if (nameVos != null && nameVos.size() > 0) {
                boolean hasD = false;
                for (SaleAttributeNameVo nameVo : nameVos) {
                    String searchName = nameVo.getName();
                    List<SaleAttributeVo> vo = nameVo.getSaleVo();
                    if (searchName.equals("一次设备名称")) {
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            builder.where(BHPZDao.Properties.Ycsbmc.like("%" + saleAttributeVo.getValue() + "%"));
                        }
                    } else if (searchName.equals("制造厂家")) {
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            builder.where(BHPZDao.Properties.Zzcj.like("%" + saleAttributeVo.getValue() + "%"));
                        }
                    } else if (searchName.equals("型号")) {
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            builder.where(BHPZDao.Properties.Bhxh.like("%" + saleAttributeVo.getValue() + "%"));
                        }
                    } else if (searchName.equals("数据状态")) {
                        List<String> stateList = new ArrayList<>();
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            if (saleAttributeVo.getValue().equals("未操作")) {
                                stateList.add("N");
                            } else if (saleAttributeVo.getValue().equals("已操作")) {
                                stateList.add("M");
                                stateList.add("H");
                                stateList.add("S");
                                stateList.add("C");
                                stateList.add("MH");
                                stateList.add("SH");
                                stateList.add("CH");
                            } else if (saleAttributeVo.getValue().equals("新增")) {
                                stateList.add("C");
                                stateList.add("CH");
                            } else if (saleAttributeVo.getValue().equals("已删除")) {
                                stateList.add("D");
                                hasD = true;
                            } else if (saleAttributeVo.getValue().equals("已导出")) {
                                stateList.add("F");
                            } else if (saleAttributeVo.getValue().equals("未核对")) {
                                stateList.add("M");
                                stateList.add("S");
                                stateList.add("C");
                                stateList.add("N");
                            } else if (saleAttributeVo.getValue().equals("已核对")) {
                                stateList.add("H");
                                stateList.add("MH");
                                stateList.add("SH");
                                stateList.add("CH");
                            }
                        }
                        String conditions = "(1!=1";
                        for (String s : stateList) {
                            if (s.equals("N")) {
                                conditions += " or (ed_tag is null)";
                                continue;
                            }
                            if (s.equals("F")) {
                                conditions += " or sb =='" + s + "'";
                                continue;
                            }
                            conditions += " or ed_tag =='" + s + "'";
                        }
                        builder.where(new WhereCondition.StringCondition(conditions + ")"));
                    } else if (searchName.equals("电压等级")) {
                        String conditions = "(dydj !=null";
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            conditions += " or dydj =='" + saleAttributeVo.getValue() + "'";
                        }
                        builder.where(new WhereCondition.StringCondition(conditions + ")"));
                    } else if (searchName.equals("装置类别")) {
                        String conditions = "(bhlb !=null";
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            conditions += " or bhlb =='" + saleAttributeVo.getValue() + "'";
                        }
                        builder.where(new WhereCondition.StringCondition(conditions + ")"));
                    } else if (searchName.equals("调度单位")) {
                        String conditions = "(dddw !=null";
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            conditions += " or dddw =='" + saleAttributeVo.getValue() + "'";
                        }
                        builder.where(new WhereCondition.StringCondition(conditions + ")"));
                    } else if (searchName.equals("装置校验")) {
                        if (vo.get(0).getValue().equals("通过校验")) {
                            builder.where(BHPZDao.Properties.Jy.eq("TG"));
                        } else if (vo.get(0).getValue().equals("未通过校验")) {
                            //conditions="ed_tag is null or ed_tag='WTG'";
                            builder.whereOr(BHPZDao.Properties.Jy.isNull(),
                                    BHPZDao.Properties.Jy.eq(""),
                                    BHPZDao.Properties.Jy.notEq("TG"));
                        }
                    }
                }
                if (!hasD) {
                    builder.whereOr(BHPZDao.Properties.Ed_tag.isNull(),
                            BHPZDao.Properties.Ed_tag.notEq("D"));
                }
            } else {
                builder.whereOr(BHPZDao.Properties.Ed_tag.isNull(),
                        BHPZDao.Properties.Ed_tag.notEq("D"));
            }
            count = builder.count();
        } else if (o == FZBHSB.class) {
            QueryBuilder<FZBHSB> builder = daoSession.getFZBHSBDao().queryBuilder();
            CZCS czcs = getCZCSByGLDW();
            builder.where(FZBHSBDao.Properties.Czmc.eq(czcs.getCzmc()));
            builder.where(FZBHSBDao.Properties.Czgldw.eq(czcs.getGldw()));

            if (!shEntity.getZZName().equals("")) {
                builder.where(FZBHSBDao.Properties.Sbmc.like("%" + shEntity.getZZName() + "%"));
            }

            //筛选
            List<SaleAttributeNameVo> nameVos = shEntity.getNameVos();
            if (nameVos != null && nameVos.size() > 0) {
                boolean hasD = false;
                for (SaleAttributeNameVo nameVo : nameVos) {
                    String searchName = nameVo.getName();
                    List<SaleAttributeVo> vo = nameVo.getSaleVo();
                    if (searchName.equals("一次设备名称")) {
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            builder.where(FZBHSBDao.Properties.Ycsbmc.like("%" + saleAttributeVo.getValue() + "%"));
                        }
                    } else if (searchName.equals("制造厂家")) {
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            builder.where(FZBHSBDao.Properties.Cj.like("%" + saleAttributeVo.getValue() + "%"));
                        }
                    } else if (searchName.equals("型号")) {
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            builder.where(FZBHSBDao.Properties.Sbxh.like("%" + saleAttributeVo.getValue() + "%"));
                        }
                    } else if (searchName.equals("数据状态")) {
                        List<String> stateList = new ArrayList<>();
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            if (saleAttributeVo.getValue().equals("未操作")) {
                                stateList.add("N");
                            } else if (saleAttributeVo.getValue().equals("已操作")) {
                                stateList.add("M");
                                stateList.add("H");
                                stateList.add("S");
                                stateList.add("C");
                                stateList.add("MH");
                                stateList.add("SH");
                                stateList.add("CH");
                            } else if (saleAttributeVo.getValue().equals("新增")) {
                                stateList.add("C");
                                stateList.add("CH");
                            } else if (saleAttributeVo.getValue().equals("已删除")) {
                                stateList.add("D");
                                hasD = true;
                            } else if (saleAttributeVo.getValue().equals("已导出")) {
                                stateList.add("F");
                            } else if (saleAttributeVo.getValue().equals("未核对")) {
                                stateList.add("M");
                                stateList.add("S");
                                stateList.add("C");
                                stateList.add("N");
                            } else if (saleAttributeVo.getValue().equals("已核对")) {
                                stateList.add("H");
                                stateList.add("MH");
                                stateList.add("SH");
                                stateList.add("CH");
                            }
                        }
                        String conditions = "(1!=1";
                        for (String s : stateList) {
                            if (s.equals("N")) {
                                conditions += " or (ed_tag is null)";
                                continue;
                            }
                            if (s.equals("F")) {
                                conditions += " or sb =='" + s + "'";
                                continue;
                            }
                            conditions += " or ed_tag =='" + s + "'";
                        }
                        builder.where(new WhereCondition.StringCondition(conditions + ")"));
                    } else if (searchName.equals("电压等级")) {
                        String conditions = "(dydj !=null";
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            conditions += " or dydj =='" + saleAttributeVo.getValue() + "'";
                        }
                        builder.where(new WhereCondition.StringCondition(conditions + ")"));
                    } else if (searchName.equals("装置类别")) {
                        String conditions = "(fzsblx !=null";
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            conditions += " or fzsblx =='" + saleAttributeVo.getValue() + "'";
                        }
                        builder.where(new WhereCondition.StringCondition(conditions + ")"));
                    } else if (searchName.equals("调度单位")) {
                        String conditions = "(dddw !=null";
                        for (SaleAttributeVo saleAttributeVo : vo) {
                            conditions += " or dddw =='" + saleAttributeVo.getValue() + "'";
                        }
                        builder.where(new WhereCondition.StringCondition(conditions + ")"));
                    } else if (searchName.equals("装置校验")) {
                        if (vo.get(0).getValue().equals("通过校验")) {
                            builder.where(FZBHSBDao.Properties.Jy.eq("TG"));
                        } else if (vo.get(0).getValue().equals("未通过校验")) {
                            //conditions="ed_tag is null or ed_tag='WTG'";
                            builder.whereOr(FZBHSBDao.Properties.Jy.isNull(),
                                    FZBHSBDao.Properties.Jy.eq(""),
                                    FZBHSBDao.Properties.Jy.notEq("TG"));
                        }
                    }
                }
                if (!hasD) {
                    builder.whereOr(FZBHSBDao.Properties.Ed_tag.isNull(),
                            FZBHSBDao.Properties.Ed_tag.notEq("D"));
                }
            } else {
                builder.whereOr(FZBHSBDao.Properties.Ed_tag.isNull(),
                        FZBHSBDao.Properties.Ed_tag.notEq("D"));
            }

            count = builder.count();
        }
        return count;
    }

    @Override
    public void coreBHPZ(String type, BHPZ bhpz) {
        BHPZDao bhpzDao = daoSession.getBHPZDao();
        if (type.equals("C")) {//新增
            bhpzDao.insertOrReplace(bhpz);
        } else if (type.equals("M")) {//修改
            bhpzDao.update(bhpz);
        } else if (type.equals("D")) {//删除
            //删除ICD
            List<Object> icdList = getICDOrBKXX(ICDXX.class, bhpz.getId() + "", "BHPZ");
            if (icdList != null && icdList.size() > 0) {
                for (Object o : icdList) {
                    daoSession.getICDXXDao().delete((ICDXX) o);
                }
            }
            //删除板卡
            List<Object> bkList = getICDOrBKXX(BKXX.class, bhpz.getId() + "", "BHPZ");
            if (bkList != null && bkList.size() > 0) {
                for (Object o : bkList) {
                    daoSession.getBKXXDao().delete((BKXX) o);
                }
            }
            //删除版本关系
            deleteBHPZXHBBGX(bhpz.getId() + "");
            //删除连接器
            List<Object> ljqList = getICDOrBKXX(LJQXX.class, bhpz.getId() + "");
            if (ljqList != null && ljqList.size() > 0) {
                for (Object o : ljqList) {
                    daoSession.getLJQXXDao().delete((LJQXX) o);
                }
            }
            //删除通道关系
            deleteTDXX(bhpz.getId() + "");
            //删除安控关系
            deleteAKXTGX(bhpz.getId() + "");
            bhpzDao.delete(bhpz);

            //删除日志信息
            List<RZGL> rzgls = daoSession.getRZGLDao().queryBuilder().where(RZGLDao.Properties.DXZJ.eq(bhpz.getId())
                    , RZGLDao.Properties.BBS.eq("BHPZ")).build().list();
            if (rzgls != null && rzgls.size() > 0) {
                daoSession.getRZGLDao().deleteInTx(rzgls);
            }
            //删除相关附件信息
            List<Object> wdgls = getICDOrBKXX(WDGL.class, bhpz.getWDID() + "");
            if (wdgls != null && wdgls.size() > 0) {
                for (Object wdgl : wdgls) {
                    WDGL wd = (WDGL) wdgl;
                    daoSession.getWDGLDao().delete(wd);
                }
            }
            daoSession.clear();

            //删除无用的型号和版本
            deleteXhAndRjbb();
        }
    }

    @Override
    public void coreFZHBSB(String type, FZBHSB fzbhsb) {
        FZBHSBDao fzbhsbDao = daoSession.getFZBHSBDao();
        if (type.equals("C")) {//新增
            fzbhsbDao.insertOrReplace(fzbhsb);
        } else if (type.equals("M")) {//修改
            fzbhsbDao.update(fzbhsb);
        } else if (type.equals("D")) {//删除
            //删除ICD
            List<Object> icdList = getICDOrBKXX(ICDXX.class, fzbhsb.getId() + "", "FZBHSB");
            if (icdList != null && icdList.size() > 0) {
                for (Object o : icdList) {
                    daoSession.getICDXXDao().delete((ICDXX) o);
                }
            }
            //删除板卡
            List<Object> bkList = getICDOrBKXX(BKXX.class, fzbhsb.getId() + "", "FZBHSB");
            if (bkList != null && bkList.size() > 0) {
                for (Object o : bkList) {
                    daoSession.getBKXXDao().delete((BKXX) o);
                }
            }
            //删除版本关系
            coreDelte(fzbhsb.getId() + "");

            //删除日志信息
            List<RZGL> rzgls = daoSession.getRZGLDao().queryBuilder().where(RZGLDao.Properties.DXZJ.eq(fzbhsb.getId())
                    , RZGLDao.Properties.BBS.eq("FZBHSB")).build().list();
            if (rzgls != null && rzgls.size() > 0) {
                daoSession.getRZGLDao().deleteInTx(rzgls);
            }
            //删除相关附件信息
            List<Object> wdgls = getICDOrBKXX(WDGL.class, fzbhsb.getWdId() + "");
            if (wdgls != null && wdgls.size() > 0) {
                for (Object wdgl : wdgls) {
                    WDGL wd = (WDGL) wdgl;
                    daoSession.getWDGLDao().delete(wd);
                }
            }
            fzbhsbDao.delete(fzbhsb);

            //删除无用的型号和版本
            deleteXhAndRjbb();
        }
    }

    //还可以获取六统一设备型号实体
    @Override
    public List<Object> getICDOrBKXX(Object o, String... zsjId) {
        List<Object> list = new ArrayList<>();
        daoSession.clear();
        if (o == ICDXX.class) {
            List<ICDXX> icdxxList = daoSession.getICDXXDao().queryBuilder().
                    where(ICDXXDao.Properties.ZSJID.eq(zsjId[0])
                            , ICDXXDao.Properties.ZSJTYPE.eq(zsjId[1])).
                    build().list();
            list.addAll(icdxxList);
        } else if (o == BKXX.class) {
            List<BKXX> bkxxList = daoSession.getBKXXDao().queryBuilder().
                    where(BKXXDao.Properties.Zsjid.eq(zsjId[0])
                            , BKXXDao.Properties.Zsjtype.eq(zsjId[1])).
                    build().list();
            list.addAll(bkxxList);
        } else if (o == LJQXX.class) {
            List<LJQXX> ljqxxList = daoSession.getLJQXXDao().queryBuilder().
                    where(LJQXXDao.Properties.Bhpzid.eq(zsjId[0])).
                    build().list();
            list.addAll(ljqxxList);
        } else if (o == LTYSBXH.class) {
            List<LTYSBXH> ltysbxhList = daoSession.getLTYSBXHDao().queryBuilder().
                    where(LTYSBXHDao.Properties.Code.eq(zsjId[0])).
                    build().list();
            list.addAll(ltysbxhList);
        } else if (o == WDGL.class) {
            List<WDGL> wdgls = daoSession.getWDGLDao().queryBuilder().where(
                    WDGLDao.Properties.WdId.eq(zsjId[0])).
                    whereOr(WDGLDao.Properties.Ed_tag.notEq("D"),
                            WDGLDao.Properties.Ed_tag.isNull(),
                            WDGLDao.Properties.Ed_tag.eq("")).
                    build().list();
            list.addAll(wdgls);
        } else {
            throw new IllegalStateException("不合法的参数类型");
        }
        return list;
    }

    @Override
    public List<TDXX> getTDXX(String bhpzId) {
        List<TDXX> tdxxList = new ArrayList<>();
        List<PZTDGX> pztdgxList = daoSession.getPZTDGXDao().queryBuilder().
                where(PZTDGXDao.Properties.BHPZID.eq(bhpzId)).
                build().list();

        for (PZTDGX pztdgx : pztdgxList) {
            List<TDXX> tdxxLists = new ArrayList<>();
            tdxxLists = daoSession.getTDXXDao().queryBuilder().where(TDXXDao.Properties.Id.eq(pztdgx.getTDXXID()))
                    .build().list();
            if (tdxxLists.size() > 0) {
                tdxxList.add(tdxxLists.get(0));
            }
        }

        return tdxxList;
    }

    @Override
    public void deleteTDXX(String bhpzId) {
        List<PZTDGX> tdxxList = new ArrayList<>();
//        List<PZTDGX> pztdgxList = daoSession.getPZTDGXDao().queryBuilder().
//                where(PZTDGXDao.Properties.BHPZID.eq(bhpzId)).
//                build().list();
        //只删除关联表
//        if (pztdgxList.size()>0){
//            tdxxList = daoSession.getTDXXDao().queryBuilder().where(TDXXDao.Properties.Id.eq(pztdgxList.get(0).getTDXXID()))
//                    .build().list();
//        }
//        for (TDXX tdxx : tdxxList) {
//            daoSession.getTDXXDao().delete(tdxx);
//        }
        tdxxList = daoSession.getPZTDGXDao().queryBuilder().
                where(PZTDGXDao.Properties.BHPZID.eq(bhpzId)).
                build().list();
        for (PZTDGX pztdgx : tdxxList) {
            daoSession.getPZTDGXDao().delete(pztdgx);
        }
    }

    @Override
    public void coreLJQ(String type, LJQXX ljqxx) {
        if (type.equals("C")) {
            daoSession.getLJQXXDao().insert(ljqxx);
        } else if (type.equals("D")) {
            daoSession.getLJQXXDao().delete(ljqxx);
        } else if (type.equals("M")) {
            daoSession.getLJQXXDao().update(ljqxx);
        }
        daoSession.clear();
    }

    @Override
    public void coreICD(String type, ICDXX icdxx) {
        if (type.equals("C")) {
            daoSession.getICDXXDao().insert(icdxx);
        } else if (type.equals("D")) {
            daoSession.getICDXXDao().delete(icdxx);
        } else if (type.equals("M")) {
            daoSession.getICDXXDao().update(icdxx);
        }
        daoSession.clear();
    }

    @Override
    public Long getInsertId(String tag) {
        Long id = null;
        if (tag.equals("LJQXX")) {
            LJQXX ljqxx = daoSession.getLJQXXDao().queryBuilder().limit(1).
                    orderDesc(LJQXXDao.Properties.Id).build().unique();
            id = ljqxx == null ? 0 : ljqxx.getId() + 1;
//            id = System.currentTimeMillis();
        } else if (tag.equals("FZBHSB")) {
//            long total = daoSession.getFZBHSBDao().count();
//            FZBHSB fzbhsb = daoSession.getFZBHSBDao().loadByRowId(total);
//            id = fzbhsb == null ? 0 : fzbhsb.getId() + 1;
//            FZBHSB fzbhsb = daoSession.getFZBHSBDao().queryBuilder().limit(1).
//                    orderDesc(FZBHSBDao.Properties.Id).build().unique();
//            id = fzbhsb == null ? 0 : fzbhsb.getId() + 1;
            id = System.currentTimeMillis();
        } else if (tag.equals("BHPZ")) {
//            BHPZ bhpz = daoSession.getBHPZDao().queryBuilder().where(new WhereCondition.StringCondition(
//                    "select max(id) from bhpz"
//            )).unique();

//            BHPZ bhpz = daoSession.getBHPZDao().queryBuilder().limit(1).
//                    orderDesc(BHPZDao.Properties.Id).build().unique();
//            id = bhpz == null ? 0 : bhpz.getId() + 1;
            id = System.currentTimeMillis();
        } else if (tag.equals("TDXX")) {
            TDXX tdxx = daoSession.getTDXXDao().queryBuilder().limit(1).
                    orderDesc(TDXXDao.Properties.Id).build().unique();
            id = tdxx == null ? 0 : tdxx.getId() + 1;
//            id = System.currentTimeMillis();
        } else if (tag.equals("PZTDGX")) {
            PZTDGX pztdgx = daoSession.getPZTDGXDao().queryBuilder().limit(1).
                    orderDesc(PZTDGXDao.Properties.ID).build().unique();
            id = pztdgx == null ? 0 : pztdgx.getID() + 1;
//            id = System.currentTimeMillis();
        } else if (tag.equals("AKXTGX")) {
            AKXTGX akxtgx = daoSession.getAKXTGXDao().queryBuilder().limit(1).
                    orderDesc(AKXTGXDao.Properties.Id).build().unique();
            id = akxtgx == null ? 0 : akxtgx.getId() + 1;
//            id = System.currentTimeMillis();
        } else if (tag.equals("BHXHRJBB")) {
            BHXHRJBB bhxhrjbb = daoSession.getBHXHRJBBDao().queryBuilder().limit(1).
                    orderDesc(BHXHRJBBDao.Properties.Id).build().unique();
            id = bhxhrjbb == null ? 0 : bhxhrjbb.getId() + 1;
//            id = System.currentTimeMillis();
        } else if (tag.equals("LTYSBXH")) {
//            LTYSBXH ltysbxh = daoSession.getLTYSBXHDao().queryBuilder().limit(1).
//                    orderDesc(LTYSBXHDao.Properties.Id).build().unique();
//            id = ltysbxh == null ? 0 : ltysbxh.getId() + 1;
            id = System.currentTimeMillis();
        } else if (tag.equals("FZBHSBXHBBGX")) {
            FZBHSBXHBBGX bbgx = daoSession.getFZBHSBXHBBGXDao().queryBuilder().limit(1).
                    orderDesc(FZBHSBXHBBGXDao.Properties.ID).build().unique();
            id = bbgx == null ? 0 : bbgx.getID() + 1;
//            id = System.currentTimeMillis();
        } else if (tag.equals("BHPZXHBBGX")) {
            BHPZXHBBGX bbgx = daoSession.getBHPZXHBBGXDao().queryBuilder().limit(1).
                    orderDesc(BHPZXHBBGXDao.Properties.ID).build().unique();
            id = bbgx == null ? 0 : bbgx.getID() + 1;
//            id = System.currentTimeMillis();
        } else if (tag.equals("CODE")) {
            CODE code = daoSession.getCODEDao().queryBuilder().limit(1).
                    orderDesc(CODEDao.Properties.Id).build().unique();
            id = code == null ? 0 : code.getId() + 1;
            daoSession.getCODEDao().insert(new CODE(id));
        } else if (tag.equals("ICDXX")) {
//            ICDXX icdxx = daoSession.getICDXXDao().queryBuilder().limit(1).
//                    orderDesc(ICDXXDao.Properties.ID).build().unique();
//            id = icdxx == null ? 0 : icdxx.getID() + 1;
            id = System.currentTimeMillis();
        } else if (tag.equals("BKXX")) {
            BKXX bkxx = daoSession.getBKXXDao().queryBuilder().limit(1).
                    orderDesc(BKXXDao.Properties.Id).build().unique();
            id = bkxx == null ? 0 : bkxx.getId() + 1;
        } else if (tag.equals("RZGL")) {
//            RZGL rzgl = daoSession.getRZGLDao().queryBuilder().limit(1).
//                    orderDesc(RZGLDao.Properties.ID).build().unique();
//            id = rzgl == null ? 0 : rzgl.getID() + 1;
            id = System.currentTimeMillis();

        } else if (tag.equals("ZZCJ")) {
//            ZZCJ zzcj = daoSession.getZZCJDao().queryBuilder().limit(1).
//                    orderDesc(ZZCJDao.Properties.ID).build().unique();
//            id = zzcj == null ? 0 : zzcj.getID() + 1;
            id = System.currentTimeMillis();

        } else if (tag.equals("BHSBXHB")) {
//            BHSBXHB bhsbxhb = daoSession.getBHSBXHBDao().queryBuilder().limit(1).
//                    orderDesc(BHSBXHBDao.Properties.Id).build().unique();
//            id = bhsbxhb == null ? 0 : bhsbxhb.getId() + 1;
            id = System.currentTimeMillis();

        } else if (tag.equals("WDGL")) {
            WDGL wdgl = daoSession.getWDGLDao().queryBuilder().limit(1).
                    orderDesc(WDGLDao.Properties.Id).build().unique();
            id = wdgl == null ? 0 : wdgl.getId() + 1;
        }
        return id;
    }

    @Override
    public String getInsertId() {
        String id;
        CODE code = daoSession.getCODEDao().queryBuilder().limit(1).
                orderDesc(CODEDao.Properties.Id).build().unique();
        id = code == null ? "0" : String.valueOf(code.getId() + 1);
        daoSession.getCODEDao().insert(new CODE(Long.parseLong(id)));
        //pc端不识别逗号
        id += "" + BaseApplication.getPad_id();

        return id;
    }

    @Override
    public BHPZ getBHPZBySbsbdm(String sbsbdm) {
        return daoSession.getBHPZDao().queryBuilder().
                where(BHPZDao.Properties.Sfsbm.eq(sbsbdm)).limit(1).build().unique();
    }

    @Override
    public BHPZ getBHPZBySwid(String swid) {
        return daoSession.getBHPZDao().queryBuilder().
                where(BHPZDao.Properties.Sw_id.eq(swid)).limit(1).build().unique();
    }

    @Override
    public BHPZ getBHPZByLjqSbsbdm(String bianhao, String xinghao) {
        List<LJQXX> ljqxxList = daoSession.getLJQXXDao().queryBuilder().
                where(LJQXXDao.Properties.Ctzjbh.eq(bianhao), LJQXXDao.Properties.Ctzjxh.eq(xinghao)).
                build().list();
        if (ljqxxList != null && ljqxxList.size() > 0) {
            return daoSession.getBHPZDao().queryBuilder().
                    where(BHPZDao.Properties.Id.eq(ljqxxList.get(0).getBhpzid())).build().unique();
        } else {
            return null;
        }
    }

    @Override
    public Object getFZSBBySbsbdm(String... cond) {
        Object obj = null;

        if (cond.length == 1) {//设备识别代码
            obj = daoSession.getFZBHSBDao().queryBuilder().
                    where(FZBHSBDao.Properties.Sw_id.eq(cond[0])).limit(1).build().unique();
            if (obj == null) {
                obj = daoSession.getBHPZDao().queryBuilder().
                        where(BHPZDao.Properties.Sw_id.eq(cond[0])).limit(1).build().unique();
            }
        } else if (cond.length == 2) {//设备名称
            List<FZBHSB> fzbhsbList = daoSession.getFZBHSBDao().queryBuilder().
                    where(FZBHSBDao.Properties.Sbmc.eq(cond[0])
                            , FZBHSBDao.Properties.Yxzt.notEq("退运")).
                    whereOr(FZBHSBDao.Properties.Ed_tag.isNull()
                            , FZBHSBDao.Properties.Ed_tag.notEq("D")).
                    build().list();
            if (fzbhsbList != null && fzbhsbList.size() > 0) {
                obj = fzbhsbList.get(0);
            } else {
                List<BHPZ> bhpzList = daoSession.getBHPZDao().queryBuilder().
                        where(BHPZDao.Properties.Bhmc.eq(cond[0])
                                , BHPZDao.Properties.Yxzt.notEq("退运")).
                        whereOr(BHPZDao.Properties.Ed_tag.isNull()
                                , BHPZDao.Properties.Ed_tag.notEq("D")).
                        build().list();
                if (bhpzList != null && bhpzList.size() > 0) {
                    obj = bhpzList.get(0);
                }
            }
        } else if (cond.length == 3) {//实物ID
            obj = daoSession.getFZBHSBDao().queryBuilder().
                    where(FZBHSBDao.Properties.Sw_id.eq(cond[0])).limit(1).build().unique();
        } else {
            obj = daoSession.getFZBHSBDao().queryBuilder().
                    where(FZBHSBDao.Properties.Sfsbm.eq(cond[0])).limit(1).build().unique();
        }
        return obj;
    }

    @Override
    public Object getObjectFromCCXX(String sbsbdm) {
        List<CCXX> ccxxList = daoSession.getCCXXDao().queryBuilder().
                where(CCXXDao.Properties.Sfsbm.eq(sbsbdm)).build().list();
        if (ccxxList == null || ccxxList.size() == 0) {
            return null;
        }
        List<BZSJ> bzsjList = getBZSJ("辅助保护类别");
        boolean isFZ = false;

        CCXX ccxx = ccxxList.get(0);
        for (BZSJ bzsj : bzsjList) {
            if (bzsj.getBzsjSxmc().equals(ccxx.getBhlb())) {
                isFZ = true;
                break;
            } else {
                isFZ = false;
            }
        }

        if (isFZ) {
            FZBHSB fzbhsb = new FZBHSB();
            fzbhsb.setSfsbm(sbsbdm);
            fzbhsb.setCj(ccxx.getZzcj());
            fzbhsb.setFzsblx(ccxx.getBhlb());
            fzbhsb.setSbxh(ccxx.getBhxh());
            fzbhsb.setBhfl(ccxx.getBhfl());
            fzbhsb.setBhlx(ccxx.getBhlx());
            fzbhsb.setBksl(ccxx.getBksl());
            fzbhsb.setSfltysb(ccxx.getSfltysb());
            fzbhsb.setLtybzbb(ccxx.getLtybzbb());
            fzbhsb.setCcrq(ccxx.getCcrq());
//            fzbhsb.setCteceddl(ccxx.getEceddl());
            return fzbhsb;
        } else {
            BHPZ bhpz = new BHPZ();
            bhpz.setSfsbm(sbsbdm);
            bhpz.setZzcj(ccxx.getZzcj());
            bhpz.setBhlb(ccxx.getBhlb());
            bhpz.setBhxh(ccxx.getBhxh());
            bhpz.setBhfl(ccxx.getBhfl());
            bhpz.setBhlx(ccxx.getBhlx());
            bhpz.setBksl(ccxx.getBksl());
            bhpz.setSfltysb(ccxx.getSfltysb());
            bhpz.setLtybzbb(ccxx.getLtybzbb());
            bhpz.setSfjdhzz(ccxx.getSfjdhzz());
            bhpz.setLjqzzcj(ccxx.getLjqzzcj());
            bhpz.setLjqsl(ccxx.getLjqsl());
            bhpz.setLjqxh(ccxx.getLjqxh());
            bhpz.setXp(ccxx.getXp());
            bhpz.setIcdwjmc(ccxx.getWjmc());
            bhpz.setState(ccxx.getState());
            bhpz.setCcrq(ccxx.getCcrq());
            bhpz.setCteceddl(ccxx.getEceddl());
            return bhpz;
        }
    }

    @Override
    public List<AKXT> getAKXT(String id) {
        //type预留查询条件，先获取全部
        List<AKXT> list = new ArrayList<>();
        list = daoSession.getAKXTDao().queryBuilder().
                where(AKXTDao.Properties.Id.eq(id)).build().list();
        return list;
    }

    @Override
    public List<AKXT> getAKXTALL(String type) {
        //type预留查询条件，先获取全部
        List<AKXT> list = new ArrayList<>();
        list = daoSession.getAKXTDao().loadAll();
        return list;
    }

    @Override
    public List<AKXTGX> getAKXTGX(String id) {
        List<AKXTGX> list = new ArrayList<>();
        daoSession.clear();
        list = daoSession.getAKXTGXDao().queryBuilder().
                where(AKXTGXDao.Properties.Bhpzid.eq(id)).build().list();
        return list;
    }

    @Override
    public void getBHPZorFZSBbyxh(String XH, String type) {
        List<BHPZ> list = new ArrayList<>();
        List<FZBHSB> listfz = new ArrayList<>();
        daoSession.clear();
        if (type.equals("BHPZ")) {
            list = daoSession.getBHPZDao().queryBuilder().
                    where(BHPZDao.Properties.Bhxh.eq(XH)).build().list();
            for (BHPZ bhpz : list) {
                bhpz.setUsegddata(true + "");
                daoSession.getBHPZDao().update(bhpz);
            }
        } else {
            listfz = daoSession.getFZBHSBDao().queryBuilder().
                    where(FZBHSBDao.Properties.Sbxh.eq(XH)).build().list();
            for (FZBHSB fzbhsb : listfz) {
                fzbhsb.setUsegddata(true + "");
                daoSession.getFZBHSBDao().update(fzbhsb);
            }
        }
        daoSession.clear();
    }

    @Override
    public void saveTDXX(TDXX tdxx) {
        daoSession.getTDXXDao().insert(tdxx);
        daoSession.clear();
    }

    @Override
    public void saveTDXXPZ(PZTDGX pztdgx) {
        daoSession.getPZTDGXDao().insert(pztdgx);
        daoSession.clear();
    }

    @Override
    public void saveAKXTGX(AKXTGX akxtgx) {
        daoSession.getAKXTGXDao().insert(akxtgx);
        daoSession.clear();
    }

    @Override
    public void deleteAKXTGX(String bhpzid) {
        List<AKXTGX> list = new ArrayList<>();
        list = daoSession.getAKXTGXDao().queryBuilder().
                where(AKXTGXDao.Properties.Bhpzid.eq(bhpzid)).build().list();
        for (AKXTGX akxtgx : list) {
            daoSession.getAKXTGXDao().delete(akxtgx);
        }
    }

    @Override
    public void deleteBHPZXHBBGX(String bhpzid) {
        List<BHPZXHBBGX> list = new ArrayList<>();
        list = daoSession.getBHPZXHBBGXDao().queryBuilder().
                where(BHPZXHBBGXDao.Properties.BHPZID.eq(bhpzid)).build().list();
        for (BHPZXHBBGX bhpzxhbbgx : list) {
            daoSession.getBHPZXHBBGXDao().delete(bhpzxhbbgx);
        }
    }


    @Override
    public List<Object> getRJBBByCode(boolean isLty, boolean is2013, String fzbhsbId) {
        List<Object> list = new ArrayList<>();
        List<FZBHSBXHBBGX> gxList = daoSession.getFZBHSBXHBBGXDao().queryBuilder().
                where(FZBHSBXHBBGXDao.Properties.FZBHSBID.eq(fzbhsbId)).build().list();
        //去除软件版本相同的数据
        for (int i = 0; i < gxList.size(); i++) {
            for (int j = gxList.size() - 1; j > i; j--) {
                if (gxList.get(j).getRJBBCODE().equals(gxList.get(i).getRJBBCODE())) {
                    gxList.remove(j);
                }
            }
        }
        if (isLty && is2013) {
            if (gxList != null && gxList.size() > 0) {
                for (FZBHSBXHBBGX fzbhsbxhbbgx : gxList) {
                    if (fzbhsbxhbbgx.getRJBBCODE() != null) {
                        List<LTYSBXH> ltysbxh = daoSession.getLTYSBXHDao().queryBuilder().where(LTYSBXHDao.Properties.Code
                                .eq(fzbhsbxhbbgx.getRJBBCODE())).build().list();
                        if (ltysbxh != null && ltysbxh.size() > 0) {
                            ltysbxh.get(0).setSCSJ(fzbhsbxhbbgx.getSCSJ());
                            list.add(ltysbxh.get(0));
                        }
                    }
                }
            }
        } else {
            if (gxList != null && gxList.size() > 0) {
                for (FZBHSBXHBBGX fzbhsbxhbbgx : gxList) {
                    if (fzbhsbxhbbgx.getRJBBCODE() != null) {
                        List<BHXHRJBB> bhxhrjbb = daoSession.getBHXHRJBBDao().queryBuilder().where(BHXHRJBBDao.Properties.Code
                                .eq(fzbhsbxhbbgx.getRJBBCODE())).build().list();
                        if (bhxhrjbb != null && bhxhrjbb.size() > 0) {
                            bhxhrjbb.get(0).setSCSJ(fzbhsbxhbbgx.getSCSJ());
                            list.add(bhxhrjbb.get(0));
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<Object> getBHRJBBByCode(boolean isLty, boolean is2013, String fzbhsbId) {
        List<Object> list = new ArrayList<>();
        List<BHPZXHBBGX> gxList = daoSession.getBHPZXHBBGXDao().queryBuilder().
                where(BHPZXHBBGXDao.Properties.BHPZID.eq(fzbhsbId)).build().list();
        if (isLty && is2013) {
            for (BHPZXHBBGX fzbhsbxhbbgx : gxList) {
                if (fzbhsbxhbbgx.getRJBBCODE() != null) {
                    List<LTYSBXH> ltysbxh = daoSession.getLTYSBXHDao().queryBuilder().where(LTYSBXHDao.Properties.Code
                            .eq(fzbhsbxhbbgx.getRJBBCODE())).build().list();
                    if (ltysbxh != null && ltysbxh.size() > 0) {
                        ltysbxh.get(0).setSCSJ(fzbhsbxhbbgx.getSCSJ());
                        list.add(ltysbxh.get(0));
                    }
                }
            }
        } else {
            for (BHPZXHBBGX fzbhsbxhbbgx : gxList) {
                if (fzbhsbxhbbgx.getRJBBCODE() != null) {
                    List<BHXHRJBB> bhxhrjbb = daoSession.getBHXHRJBBDao().queryBuilder().where(BHXHRJBBDao.Properties.Code
                            .eq(fzbhsbxhbbgx.getRJBBCODE())).build().list();
                    if (bhxhrjbb != null && bhxhrjbb.size() > 0) {
                        bhxhrjbb.get(0).setSCSJ(fzbhsbxhbbgx.getSCSJ());
                        list.add(bhxhrjbb.get(0));
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void deleteXhAndRjbb() {
        //删除无用的版本
        BHXHRJBBDao dao = daoSession.getBHXHRJBBDao();
        List<BHXHRJBB> bhxhrjbbList = dao.queryBuilder().where(
                BHXHRJBBDao.Properties.ED_TAG.isNotNull()).build().list();
        for (BHXHRJBB rjbb : bhxhrjbbList) {
            List<BHPZXHBBGX> bhgx = daoSession.getBHPZXHBBGXDao().queryBuilder().
                    where(BHPZXHBBGXDao.Properties.RJBBCODE.eq(rjbb.getCode())).build().list();
            List<FZBHSBXHBBGX> fzgx = daoSession.getFZBHSBXHBBGXDao().queryBuilder().
                    where(FZBHSBXHBBGXDao.Properties.RJBBCODE.eq(rjbb.getCode())).build().list();
            if (bhgx.isEmpty() && fzgx.isEmpty()) {
                dao.delete(rjbb);
            }
        }


        //删除无用的型号-六统一
        LTYSBXHDao ltysbxhDao = daoSession.getLTYSBXHDao();
        List<LTYSBXH> list = ltysbxhDao.queryBuilder().where(LTYSBXHDao.Properties.ED_TAG.isNotNull()).build().list();
        for (LTYSBXH ltysbxh1 : list) {
            List<BHPZXHBBGX> bhgx = daoSession.getBHPZXHBBGXDao().queryBuilder().
                    where(BHPZXHBBGXDao.Properties.RJBBCODE.eq(ltysbxh1.getCode())).build().list();
            List<FZBHSBXHBBGX> fzgx = daoSession.getFZBHSBXHBBGXDao().queryBuilder().
                    where(FZBHSBXHBBGXDao.Properties.RJBBCODE.eq(ltysbxh1.getCode())).build().list();
            List<BHPZ> bhpz = daoSession.getBHPZDao().queryBuilder().
                    where(BHPZDao.Properties.Bhxh.eq(ltysbxh1.getBhxh())).build().list();
            List<FZBHSB> fzbhsb = daoSession.getFZBHSBDao().queryBuilder().
                    where(FZBHSBDao.Properties.Sbxh.eq(ltysbxh1.getBhxh())).build().list();
            if (bhgx.isEmpty() && fzgx.isEmpty() && bhpz.isEmpty() && fzbhsb.isEmpty()) {
                ltysbxhDao.delete(ltysbxh1);
            }
        }
        //删除无用的型号-非六统一
        BHSBXHBDao bhsbxhbDao = daoSession.getBHSBXHBDao();
        List<BHSBXHB> bhsbxhbList = bhsbxhbDao.queryBuilder().where(BHSBXHBDao.Properties.ED_TAG.isNotNull()).build().list();
        for (BHSBXHB bhsbxhb : bhsbxhbList) {
            List<BHPZ> bhpz = daoSession.getBHPZDao().queryBuilder().
                    where(BHPZDao.Properties.Bhxh.eq(bhsbxhb.getSbxh())).build().list();
            List<FZBHSB> fzbhsb = daoSession.getFZBHSBDao().queryBuilder().
                    where(FZBHSBDao.Properties.Sbxh.eq(bhsbxhb.getSbxh())).build().list();
            if (bhpz.isEmpty() && fzbhsb.isEmpty()) {
                bhsbxhbDao.delete(bhsbxhb);
            }
        }
    }

    @Override
    public void coreSave(Object o) {
        if (o instanceof BHXHRJBB) {
            BHXHRJBBDao dao = daoSession.getBHXHRJBBDao();
            dao.insert((BHXHRJBB) o);
        } else if (o instanceof FZBHSBXHBBGX) {
            daoSession.getFZBHSBXHBBGXDao().insert((FZBHSBXHBBGX) o);
        } else if (o instanceof LTYSBXH) {
            LTYSBXH ltysbxh = (LTYSBXH) o;
            LTYSBXHDao dao = daoSession.getLTYSBXHDao();
            dao.insert(ltysbxh);
        } else if (o instanceof ICDXX) {
            daoSession.getICDXXDao().insert((ICDXX) o);
        } else if (o instanceof BKXX) {
            daoSession.getBKXXDao().insert((BKXX) o);
        } else if (o instanceof BHPZXHBBGX) {
            daoSession.getBHPZXHBBGXDao().insert((BHPZXHBBGX) o);
        } else if (o instanceof BHSBXHB) {
            BHSBXHB bhsbxhb = (BHSBXHB) o;
            BHSBXHBDao dao = daoSession.getBHSBXHBDao();
            if (bhsbxhb.getSh() != null && bhsbxhb.getSh().equals("M")) {
                bhsbxhb.setSh(null);
                dao.update(bhsbxhb);
            } else {
                dao.insert(bhsbxhb);
            }
        } else if (o instanceof ZZCJ) {
            daoSession.getZZCJDao().insert((ZZCJ) o);
        } else if (o instanceof SBXX) {
            daoSession.getSBXXDao().insertOrReplace((SBXX) o);
        } else if (o instanceof WDGL) {
            WDGL wdgl = (WDGL) o;
            if (wdgl.getEd_tag() == null || wdgl.getEd_tag().equals(" ") || wdgl.getEd_tag().equals("C")) {
                daoSession.getWDGLDao().insert(wdgl);
            } else if (wdgl.getEd_tag().equals("D")) {
                daoSession.getWDGLDao().update(wdgl);
            } else if (wdgl.getEd_tag().equals("DEL")) {
                daoSession.getWDGLDao().delete(wdgl);
            }
        } else if (o instanceof UserInfo) {
            UserInfo info = (UserInfo) o;
            UserInfo infos = daoSession.getUserInfoDao().queryBuilder().where(UserInfoDao.Properties.Username.eq(info.getUsername())
                    , UserInfoDao.Properties.Password.eq(info.getPassword())).build().unique();
            if (infos == null) {
                daoSession.getUserInfoDao().insert(info);
            }
        }
        //保存同时清缓存
        daoSession.clear();
    }

    @Override
    public void coreDelte(String fzsbid) {
        FZBHSBXHBBGXDao dao = daoSession.getFZBHSBXHBBGXDao();
        List<FZBHSBXHBBGX> bbgx = dao.queryBuilder().where(FZBHSBXHBBGXDao.Properties.FZBHSBID.eq(fzsbid)).build().list();
        dao.deleteInTx(bbgx);
    }

    @Override
    public List<BKXX> getCCXXBK(String sbsbdm) {
        CCXXBKDao dao = daoSession.getCCXXBKDao();
        List<CCXXBK> bkList = dao.queryBuilder().where(CCXXBKDao.Properties.Sfsbm.eq(sbsbdm)).build().list();
        if (bkList.size() > 0) {
            List<BKXX> list = new ArrayList<>();
            for (CCXXBK ccxxbk : bkList) {
                BKXX bx = new BKXX();
                bx.setZzcj(ccxxbk.getZzcj());
                bx.setBkxh(ccxxbk.getBkxh());
                bx.setBklb(ccxxbk.getBklb());
                bx.setRjbb(ccxxbk.getRjbb());
                bx.setBkbh(ccxxbk.getBkbh());
                bx.setBkscrq(ccxxbk.getBkscrq());
                list.add(bx);
            }
            return list;
        }
        return null;
    }

    @Override
    public List<Object> getCCXXRJBB(boolean isSix, boolean is2013, String sbsbdm) {
        List<CCXX> ccxxList = daoSession.getCCXXDao().queryBuilder().
                where(CCXXDao.Properties.Sfsbm.eq(sbsbdm)).build().list();

        CCXXRJBBDao dao = daoSession.getCCXXRJBBDao();
        List<CCXXRJBB> rjbbList = dao.queryBuilder().where(CCXXRJBBDao.Properties.Sfsbm.eq(sbsbdm)).build().list();
        if (rjbbList.size() > 0) {
            CCXX ccxx = ccxxList.get(0);
            List<Object> list = new ArrayList<>();
            if (isSix && is2013) {
                for (CCXXRJBB rjbb : rjbbList) {
                    LTYSBXH ltysbxh = new LTYSBXH();
                    ltysbxh.setRjbb(rjbb.getRjbb());
                    ltysbxh.setSCSJ(rjbb.getScrq());
                    ltysbxh.setCode(rjbb.getCode());
                    ltysbxh.setZzcj(ccxx.getZzcj());
                    ltysbxh.setBhlb(ccxx.getBhlb());
                    ltysbxh.setBhxh(ccxx.getBhxh());
                    ltysbxh.setBhfl(ccxx.getBhfl());
                    ltysbxh.setBhlx(ccxx.getBhlx());
                    ltysbxh.setBblx(ccxx.getBblx());
                    ltysbxh.setCrc32(ccxx.getCrc32());
                    ltysbxh.setMd5(ccxx.getMd5m());
                    ltysbxh.setWjmc(ccxx.getWjmc());
                    ltysbxh.setWjbb(ccxx.getWjbb());
                    ltysbxh.setXp(ccxx.getXp());
                    list.add(ltysbxh);
                }
            } else {
                for (CCXXRJBB rjbb : rjbbList) {
                    BHXHRJBB bj = new BHXHRJBB();
                    bj.setMkmc(rjbb.getMkmc());
                    bj.setBb(rjbb.getRjbb());
                    bj.setJym(rjbb.getJym());
                    bj.setBblx(ccxx.getBblx());
                    bj.setSCSJ(rjbb.getScrq());
                    bj.setCode(rjbb.getCode());
                    list.add(bj);
                }
            }
            return list;
        }
        return null;
    }

    @Override
    public List<BHPZ> getALLBHPZ() {
        List<BHPZ> list = daoSession.getBHPZDao().loadAll();
        return list;
    }

    @Override
    public BHXHRJBB getBHXHRJBBByCode(String code) {
        return daoSession.getBHXHRJBBDao().queryBuilder()
                .where(BHXHRJBBDao.Properties.Code.eq(code)).build().unique();
    }

    @Override
    public Object getBHXHByCode(String code, boolean isSix, boolean is2013) {
        if (isSix && is2013) {
            if (code == null || code.equals("")) {
                return null;
            }
            List<LTYSBXH> ltysbxhs = daoSession.getLTYSBXHDao().queryBuilder()
                    .where(LTYSBXHDao.Properties.Code.eq(code)).build().list();
            if (ltysbxhs != null && ltysbxhs.size() > 0) {
                return ltysbxhs.get(0);
            } else {
                return null;
            }
        } else {
            if (code == null || code.equals("")) {
                return null;
            }
            List<BHSBXHB> list = daoSession.getBHSBXHBDao().queryBuilder()
                    .where(BHSBXHBDao.Properties.Code.eq(code + "")).build().list();
            if (list != null && list.size() > 0) {
                return list.get(0);
            } else {
                return null;
            }
        }
    }

    @Override
    public List<BHPZXHBBGX> getBHPZXHBBGX(String bhpzid) {
        List<BHPZXHBBGX> list = new ArrayList<>();
        list = daoSession.getBHPZXHBBGXDao().queryBuilder()
                .where(BHPZXHBBGXDao.Properties.BHPZID.eq(bhpzid)).build().list();
        return list;
    }

    @Override
    public ICDXX getICDXXFromCCXX(String sbsbdm) {
        List<CCXX> ccxxList = daoSession.getCCXXDao().queryBuilder().
                where(CCXXDao.Properties.Sfsbm.eq(sbsbdm)).build().list();
        if (ccxxList == null || ccxxList.size() == 0) {
            return null;
        }

        CCXX ccxx = ccxxList.get(0);
        if (ccxx.getSfltysb().equals("是")) {
            ICDXX icdxx = new ICDXX();
            icdxx.setWJMC(ccxx.getWjmc());
            icdxx.setWJBB(ccxx.getWjbb());
            icdxx.setCRC32(ccxx.getCrc32());
            icdxx.setMD5(ccxx.getMd5m());
            return icdxx;
        }
        return null;
    }

    @Override
    public void coreSavaRZXX(Object o) {
        RZGL rzgl = new RZGL();
        rzgl.setID(getInsertId("RZGL"));
        rzgl.setSFXTLR("Y");
        rzgl.setCZSJ(TimeUtil.getCurrentTime());
        if (o instanceof BHPZ) {
            BHPZ bhpz = (BHPZ) o;
            rzgl.setDWMC(bhpz.getCzgldw());
            rzgl.setCZR(PreferenceUtils.getPrefString(context, "userInfo", null).split("-")[0]);
            rzgl.setDXZJ(bhpz.getId());
            if (bhpz.getEd_tag().equals("C")) {
                rzgl.setCZLX("添加");
            } else if (bhpz.getEd_tag().equals("M")) {
                rzgl.setCZLX("编辑");
            } else if (bhpz.getEd_tag().equals("D")) {
                rzgl.setCZLX("标记删除");
            } else if (bhpz.getSb().equals("F")) {
                rzgl.setCZLX("已导出");
            } else if (bhpz.getEd_tag().contains("H")) {
                rzgl.setCZLX("已核对");
            }
            rzgl.setBBS("BHPZ");
            rzgl.setSB(bhpz.getSb());

        } else if (o instanceof FZBHSB) {
            FZBHSB fzbhsb = (FZBHSB) o;
            rzgl.setDWMC(fzbhsb.getCzgldw());
            rzgl.setCZR(PreferenceUtils.getPrefString(context, "userInfo", null).split("-")[0]);
            rzgl.setDXZJ(fzbhsb.getId());
            if (fzbhsb.getEd_tag().equals("C")) {
                rzgl.setCZLX("添加");
            } else if (fzbhsb.getEd_tag().equals("M")) {
                rzgl.setCZLX("编辑");
            } else if (fzbhsb.getEd_tag().equals("D")) {
                rzgl.setCZLX("标记删除");
            } else if (fzbhsb.getSb().equals("F")) {
                rzgl.setCZLX("已导出");
            } else if (fzbhsb.getEd_tag().contains("H")) {
                rzgl.setCZLX("已核对");
            }
            rzgl.setBBS("FZSB");
            rzgl.setSB(fzbhsb.getSb());
        }
        daoSession.getRZGLDao().insert(rzgl);
    }

    /**
     * 数据导出
     */
    @Override
    public void outputDB(List<String> czmcList, boolean isOutPut) {
        //导出保护设备
        QueryBuilder<BHPZ> builder = daoSession.getBHPZDao().queryBuilder();

        builder.where(BHPZDao.Properties.Czmc.in(czmcList));
        if (!isOutPut) {
            builder.where(BHPZDao.Properties.Sb.notEq("F")).and(
                    BHPZDao.Properties.Ed_tag.eq("C")
                    , BHPZDao.Properties.Ed_tag.eq("CH")
                    , BHPZDao.Properties.Ed_tag.eq("M")
                    , BHPZDao.Properties.Ed_tag.eq("MH")
                    , BHPZDao.Properties.Ed_tag.eq("D"));
        } else {
            builder.whereOr(BHPZDao.Properties.Ed_tag.eq("C")
                    , BHPZDao.Properties.Ed_tag.eq("CH")
                    , BHPZDao.Properties.Ed_tag.eq("M")
                    , BHPZDao.Properties.Ed_tag.eq("MH")
                    , BHPZDao.Properties.Ed_tag.eq("D"));
        }

        List<BHPZ> bhpzList = builder.build().list();
        for (BHPZ bhpz : bhpzList) {
            //导出板卡信息
            List<BKXX> bkxxList = daoSession.getBKXXDao().queryBuilder().
                    where(BKXXDao.Properties.Zsjid.eq(bhpz.getId())
                            , BKXXDao.Properties.Zsjtype.eq("BHPZ")).
                    build().list();
            if (bkxxList.size() > 0) {
                outputDaoSession.getBKXXDao().insertOrReplaceInTx(bkxxList);
            }
            //导出ICD信息
            List<ICDXX> icdxxList = daoSession.getICDXXDao().queryBuilder().
                    where(ICDXXDao.Properties.ZSJID.eq(bhpz.getId())
                            , ICDXXDao.Properties.ZSJTYPE.eq("BHPZ")).
                    build().list();
            if (icdxxList.size() > 0) {
                outputDaoSession.getICDXXDao().insertOrReplaceInTx(icdxxList);
            }
            List<LJQXX> ljqxxList = daoSession.getLJQXXDao().queryBuilder().
                    where(LJQXXDao.Properties.Bhpzid.eq(bhpz.getId())).
                    build().list();
            if (ljqxxList.size() > 0) {
                outputDaoSession.getLJQXXDao().insertOrReplaceInTx(ljqxxList);
            }

            List<BHPZXHBBGX> bhgxList = daoSession.getBHPZXHBBGXDao().queryBuilder().
                    where(BHPZXHBBGXDao.Properties.BHPZID.eq(bhpz.getId())).
                    build().list();
            if (bhgxList.size() > 0) {
                for (BHPZXHBBGX bhpzxhbbgx : bhgxList) {
                    outputDaoSession.getBHPZXHBBGXDao().insertOrReplace(bhpzxhbbgx);
                    List<BHXHRJBB> bhrjList = daoSession.getBHXHRJBBDao().queryBuilder().distinct().
                            where(BHXHRJBBDao.Properties.Code.eq(bhpzxhbbgx.getRJBBCODE())).
                            build().list();
                    if (bhrjList.size() > 0) {
                        for (BHXHRJBB bhxhrjbb : bhrjList) {
                            BHXHRJBB rjbb = outputDaoSession.getBHXHRJBBDao().load(bhxhrjbb.getId());
                            if (rjbb == null) {
                                outputDaoSession.getBHXHRJBBDao().insert(bhxhrjbb);
                            }
                        }
                    }
                    List<LTYSBXH> ltyxhList = daoSession.getLTYSBXHDao().queryBuilder().distinct().
                            where(LTYSBXHDao.Properties.Code.eq(bhpzxhbbgx.getRJBBCODE())).
                            build().list();
                    if (ltyxhList.size() > 0) {
                        for (LTYSBXH ltysbxh : ltyxhList) {
                            LTYSBXH lty = outputDaoSession.getLTYSBXHDao().load(ltysbxh.getId());
                            if (lty == null) {
                                outputDaoSession.getLTYSBXHDao().insert(ltysbxh);
                            }
                        }
                    }
                }
            }

            List<AKXTGX> akxtgxList = daoSession.getAKXTGXDao().queryBuilder().
                    where(AKXTGXDao.Properties.Bhpzid.eq(bhpz.getId())).build().list();
            if (akxtgxList.size() > 0) {
                outputDaoSession.getAKXTGXDao().insertOrReplaceInTx(akxtgxList);
            }
            List<PZTDGX> tdgxList = daoSession.getPZTDGXDao().queryBuilder().
                    where(PZTDGXDao.Properties.BHPZID.eq(bhpz.getId())).build().list();
            if (tdgxList.size() > 0) {
                for (PZTDGX pztdgx : tdgxList) {
                    outputDaoSession.getPZTDGXDao().insert(pztdgx);
                    List<TDXX> tdxxList = daoSession.getTDXXDao().queryBuilder().
                            where(TDXXDao.Properties.Id.eq(pztdgx.getTDXXID())).build().list();
                    if (tdxxList.size() > 0) {
                        outputDaoSession.getTDXXDao().insertOrReplaceInTx(tdxxList);
                    }
                }
            }
            //导出附件信息
            List<WDGL> wdglList = daoSession.getWDGLDao().queryBuilder().where(WDGLDao.Properties.WdId.eq(bhpz.getId() + "")
                    , WDGLDao.Properties.Cstable.eq("BHPZ"), WDGLDao.Properties.Ed_tag.isNotNull()).build().list();
            if (wdglList != null && wdglList.size() > 0) {
                outputDaoSession.getWDGLDao().insertInTx(wdglList);
            }
            //导出日志信息
            List<RZGL> rzList = daoSession.getRZGLDao().queryBuilder().
                    where(RZGLDao.Properties.DXZJ.eq(bhpz.getId())
                            , RZGLDao.Properties.BBS.eq("BHPZ")).build().list();
            if (rzList.size() > 0) {
                outputDaoSession.getRZGLDao().insertInTx(rzList);
            }
            if (bhpz.getSb() != null && bhpz.getSb().equals("FF"))
                bhpz.setSb("F");

            outputDaoSession.getBHPZDao().insert(bhpz);
        }

        List<BHPZ> bhpzList2 = builder.build().list();

        for (BHPZ bhpz : bhpzList2) {
            if (bhpz.getSb() != null && bhpz.getSb().equals("F"))
                continue;
            bhpz.setSb("F");
            daoSession.getBHPZDao().update(bhpz);
        }

        //导出辅助设备
        QueryBuilder<FZBHSB> builder1 = daoSession.getFZBHSBDao().queryBuilder();
        builder1.where(FZBHSBDao.Properties.Czmc.in(czmcList));
        if (!isOutPut) {
            builder1.where(FZBHSBDao.Properties.Sb.notEq("F")).and(
                    FZBHSBDao.Properties.Ed_tag.eq("C")
                    , FZBHSBDao.Properties.Ed_tag.eq("CH")
                    , FZBHSBDao.Properties.Ed_tag.eq("M")
                    , FZBHSBDao.Properties.Ed_tag.eq("MH")
                    , FZBHSBDao.Properties.Ed_tag.eq("D"));
        } else {
            builder1.whereOr(FZBHSBDao.Properties.Ed_tag.eq("C")
                    , FZBHSBDao.Properties.Ed_tag.eq("CH")
                    , FZBHSBDao.Properties.Ed_tag.eq("M")
                    , FZBHSBDao.Properties.Ed_tag.eq("MH")
                    , FZBHSBDao.Properties.Ed_tag.eq("D"));
        }

        List<FZBHSB> fzbhsbList = builder1.build().list();

        for (FZBHSB fzbhsb : fzbhsbList) {
            List<BKXX> bkxxList = daoSession.getBKXXDao().queryBuilder().
                    where(BKXXDao.Properties.Zsjid.eq(fzbhsb.getId())
                            , BKXXDao.Properties.Zsjtype.eq("FZBHSB")).
                    build().list();
            if (bkxxList.size() > 0) {
                outputDaoSession.getBKXXDao().insertOrReplaceInTx(bkxxList);
            }
            List<ICDXX> icdxxList = daoSession.getICDXXDao().queryBuilder().
                    where(ICDXXDao.Properties.ZSJID.eq(fzbhsb.getId())
                            , ICDXXDao.Properties.ZSJTYPE.eq("FZBHSB")).
                    build().list();
            if (icdxxList.size() > 0) {
                outputDaoSession.getICDXXDao().insertOrReplaceInTx(icdxxList);
            }
            List<FZBHSBXHBBGX> fzgxList = daoSession.getFZBHSBXHBBGXDao().queryBuilder().
                    where(FZBHSBXHBBGXDao.Properties.FZBHSBID.eq(fzbhsb.getId())).
                    build().list();
            if (fzgxList.size() > 0) {
                for (FZBHSBXHBBGX fzbhsbxhbbgx : fzgxList) {
                    outputDaoSession.getFZBHSBXHBBGXDao().insert(fzbhsbxhbbgx);
                    List<BHXHRJBB> bhrjList = daoSession.getBHXHRJBBDao().queryBuilder().distinct().
                            where(BHXHRJBBDao.Properties.Code.eq(fzbhsbxhbbgx.getRJBBCODE())).
                            build().list();
                    if (bhrjList.size() > 0) {
                        for (BHXHRJBB bhxhrjbb : bhrjList) {
                            BHXHRJBB rjbb = outputDaoSession.getBHXHRJBBDao().load(bhxhrjbb.getId());
                            if (rjbb == null) {
                                outputDaoSession.getBHXHRJBBDao().insert(bhxhrjbb);
                            }
                        }
                    }
                    List<LTYSBXH> ltyxhList = daoSession.getLTYSBXHDao().queryBuilder().distinct().
                            where(LTYSBXHDao.Properties.Code.eq(fzbhsbxhbbgx.getRJBBCODE())).
                            build().list();
                    if (ltyxhList.size() > 0) {
                        for (LTYSBXH ltysbxh : ltyxhList) {
                            LTYSBXH lty = outputDaoSession.getLTYSBXHDao().load(ltysbxh.getId());
                            if (lty == null) {
                                outputDaoSession.getLTYSBXHDao().insert(ltysbxh);
                            }
                        }
                    }
                }
            }
            //导出附件信息
            List<WDGL> wdglList = daoSession.getWDGLDao().queryBuilder().where(WDGLDao.Properties.WdId.eq(fzbhsb.getId() + "")
                    , WDGLDao.Properties.Cstable.eq("FZBHSB"), WDGLDao.Properties.Ed_tag.isNotNull()).build().list();
            if (wdglList != null && wdglList.size() > 0) {
                outputDaoSession.getWDGLDao().insertInTx(wdglList);
            }

            //导出日志信息
            List<RZGL> rzList = daoSession.getRZGLDao().queryBuilder().
                    where(RZGLDao.Properties.DXZJ.eq(fzbhsb.getId())
                            , RZGLDao.Properties.BBS.eq("FZBHSB")).build().list();
            if (rzList.size() > 0) {
                outputDaoSession.getRZGLDao().insertInTx(rzList);
            }
            if (fzbhsb.getSb() != null && fzbhsb.getSb().equals("FF"))
                fzbhsb.setSb("F");
            outputDaoSession.getFZBHSBDao().insert(fzbhsb);
        }

        List<FZBHSB> fzbhsbList2 = builder1.build().list();
        for (FZBHSB fzbhsb : fzbhsbList2) {
            if (fzbhsb.getSb() != null && fzbhsb.getSb().equals("F"))
                continue;
            fzbhsb.setSb("F");
            daoSession.update(fzbhsb);
        }

        List<BHSBXHB> xhList = daoSession.getBHSBXHBDao().queryBuilder().
                where(BHSBXHBDao.Properties.ED_TAG.eq("C")).build().list();
        if (xhList.size() > 0) {
            outputDaoSession.getBHSBXHBDao().insertInTx(xhList);
        }

        //导出BDW
        List<BDW> bdwList = daoSession.getBDWDao().queryBuilder().build().list();
        if (bdwList.size() > 0) {
            outputDaoSession.getBDWDao().insertInTx(bdwList);
        }

        List<BDW> bdw = outputDaoSession.getBDWDao().queryBuilder().build().list();

        //导出制造厂家厂家
        List<ZZCJ> zzcjList = daoSession.getZZCJDao().queryBuilder()
                .where(ZZCJDao.Properties.ED_TAG.eq("C")).build().list();
        if (zzcjList.size() > 0) {
            outputDaoSession.getZZCJDao().insertInTx(zzcjList);
        }

        //导出设备信息
        String sbxx;
        SBXX sbxx1 = new SBXX();
        if (false) {
            SBXX sx = daoSession.getSBXXDao().queryBuilder().unique();

            String[] ss = sbxx.split("-");
            sbxx1.setId(Long.parseLong(ss[0]));
            sbxx1.setImei(ss[1]);
            sbxx1.setANDROID_ID(ss[2]);
            sbxx1.setSerialNumber(ss[3]);
            sbxx1.setSbwybdsbm(ss[4]);
            if (sx != null && sx.getSJWJSJ() != null) {
                sbxx1.setSJWJSJ(sx.getSJWJSJ());
            }
            outputDaoSession.getSBXXDao().insert(sbxx1);
        } else {
            Map<String, String> keyMaps = new HashMap<>();
            Map<String, String> txtMaps = AppUtils.setTxt(keyMaps, "", false);
            sbxx = txtMaps.get("sbxx") + "";
            if (sbxx != null && !sbxx.equals("") && !sbxx.equalsIgnoreCase("null")) {
                SBXX sx = daoSession.getSBXXDao().queryBuilder().unique();
                String[] ss = sbxx.split("-");
                sbxx1.setId(Long.parseLong(ss[0]));
                sbxx1.setImei(ss[1]);
                sbxx1.setANDROID_ID(ss[2]);
                sbxx1.setSerialNumber(ss[3]);
                sbxx1.setSbwybdsbm(ss[4]);
                String jgms = txtMaps.get("jgms") + "";
                sbxx1.setSFJG(jgms.equals("是") ? "true" : "false");
                if (sx != null && sx.getSJWJSJ() != null) {
                    sbxx1.setSJWJSJ(sx.getSJWJSJ());
                }
                outputDaoSession.getSBXXDao().insert(sbxx1);
            }
        }


        if (AppUtils.isRegist() && BaseApplication.getInstance().getDecryptKey() == null)
            BaseApplication.getInstance().initDecryptKey();
        DecryptKey dk = BaseApplication.getInstance().getDecryptKey();

        //导出设备注册信息
        List<DecryptKey.CzInfo> czInfos = dk.getList();
        if (czInfos != null && czInfos.size() > 0) {
            List<SBZCXX> sbzcxxes = new ArrayList<>();
            for (int i = 0; i < czInfos.size(); i++) {
                SBZCXX sc = new SBZCXX();
                sc.setId(Long.valueOf(TimeUtil.getCurrentTime2() + i));
                sc.setAndroid_id(sbxx1.getANDROID_ID());
                sc.setImei(sbxx1.getImei());
                sc.setSerialnumber(sbxx1.getSerialNumber());
                sc.setZcm(sbxx1.getSbwybdsbm());
                sc.setCzmc(czInfos.get(i).getCzmc());
                sc.setCzgldw(czInfos.get(i).getCzgldw());
                sbzcxxes.add(sc);
            }
            outputDaoSession.getSBZCXXDao().insertInTx(sbzcxxes);
        }

        daoSession.clear();
        outputDaoSession.clear();
    }

    /**
     * 是否通过校验
     *
     * @return
     */
    @Override
    public boolean isJY(List<String> czmcList) {
        List<BHPZ> bhpzList = daoSession.getBHPZDao().queryBuilder().
                where(BHPZDao.Properties.Czmc.in(czmcList), BHPZDao.Properties.Jy.eq("WTG")
                        , BHPZDao.Properties.Ed_tag.isNotNull(), BHPZDao.Properties.Ed_tag.notEq("D")).
                build().list();
        if (bhpzList != null && bhpzList.size() > 0)
            return true;
        List<FZBHSB> fzbhsbList = daoSession.getFZBHSBDao().queryBuilder().
                where(FZBHSBDao.Properties.Czmc.in(czmcList), FZBHSBDao.Properties.Jy.eq("WTG")
                        , FZBHSBDao.Properties.Ed_tag.isNotNull(), FZBHSBDao.Properties.Ed_tag.notEq("D")).
                build().list();
        if (fzbhsbList != null && fzbhsbList.size() > 0)
            return true;
        return false;
    }

    @Override
    public RLST_USER getUserByName(String userName) {
        return daoSession.getRLST_USERDao().queryBuilder().
                where(RLST_USERDao.Properties.UserName.eq(userName)).
                build().unique();
    }

    @Override
    public BHPZ getBHPZMC(String BHMC) {
        return daoSession.getBHPZDao().queryBuilder().
                where(BHPZDao.Properties.Bhmc.eq(BHMC)).
                build().unique();
    }

    @Override
    public String getCodeByBhxhRjbb(Object o) {
        if (o instanceof BHXHRJBB) {
            BHXHRJBB rjbb = (BHXHRJBB) o;
            QueryBuilder<BHXHRJBB> builder = daoSession.getBHXHRJBBDao().queryBuilder();
            builder.where(BHXHRJBBDao.Properties.Bhxhcode.eq(rjbb.getBhxhcode()));

            if (rjbb.getBb() != null && !rjbb.getBb().equals("")) {
                builder.where(BHXHRJBBDao.Properties.Bb.eq(rjbb.getBb()));
            } else {
                builder.whereOr(BHXHRJBBDao.Properties.Bb.isNull()
                        , BHXHRJBBDao.Properties.Bb.eq(""));
            }
            if (rjbb.getJym() != null && !rjbb.getJym().equals("")
                    && !rjbb.getJym().equals("null")) {
                builder.where(BHXHRJBBDao.Properties.Jym.eq(rjbb.getJym()));
            } else {
                builder.whereOr(BHXHRJBBDao.Properties.Jym.isNull()
                        , BHXHRJBBDao.Properties.Jym.eq(""));
            }

            if (rjbb.getMkmc() != null && !rjbb.getMkmc().equals("")) {
                builder.where(BHXHRJBBDao.Properties.Mkmc.eq(rjbb.getMkmc()));
            } else {
                builder.whereOr(BHXHRJBBDao.Properties.Mkmc.isNull()
                        , BHXHRJBBDao.Properties.Mkmc.eq(""));
            }
            List<BHXHRJBB> bhxhrjbb = builder.list();
            if (bhxhrjbb != null && bhxhrjbb.size() > 0) {
                return bhxhrjbb.get(0).getCode();
            }
        } else if (o instanceof LTYSBXH) {
            LTYSBXH ltysbxh = (LTYSBXH) o;
            QueryBuilder<LTYSBXH> builder = daoSession.getLTYSBXHDao().queryBuilder();
            builder.where(LTYSBXHDao.Properties.Zzcj.eq(ltysbxh.getZzcj())
                    , LTYSBXHDao.Properties.Bhxh.eq(ltysbxh.getBhxh())
                    , LTYSBXHDao.Properties.Bhfl.eq(ltysbxh.getBhfl())
                    , LTYSBXHDao.Properties.Bhlx.eq(ltysbxh.getBhlx())
                    , LTYSBXHDao.Properties.Wjmc.eq(ltysbxh.getWjmc())
                    , LTYSBXHDao.Properties.Rjbb.eq(ltysbxh.getRjbb()));
//            if (ltysbxh.getXp() != null && !ltysbxh.getXp().equals(""))
            if (ltysbxh.getXp() != null) {
                builder.where(LTYSBXHDao.Properties.Xp.eq(ltysbxh.getXp()));
            }
            if (ltysbxh.getWjbb() != null) {
                builder.where(LTYSBXHDao.Properties.Wjbb.eq(ltysbxh.getWjbb()));
            }
            if (ltysbxh.getCrc32() != null) {
                builder.where(LTYSBXHDao.Properties.Crc32.eq(ltysbxh.getCrc32()));
            }
            if (ltysbxh.getMd5() != null) {
                builder.where(LTYSBXHDao.Properties.Md5.eq(ltysbxh.getMd5()));
            }
            if (ltysbxh.getZzxgsj() != null) {
                builder.where(LTYSBXHDao.Properties.Zzxgsj.eq(ltysbxh.getZzxgsj()));
            }
            List<LTYSBXH> ltysb = builder.limit(1).list();
            if (ltysb != null && ltysb.size() > 0) {
                return ltysb.get(0).getCode();
            }
        }
        return null;
    }

    @Override
    public String getbhxhCode(String code) {
        String bhxhcode = "";
        BHXHRJBB bhsbxhb = daoSession.getBHXHRJBBDao().queryBuilder().
                where(BHXHRJBBDao.Properties.Code.eq(code)).limit(1).
                build().unique();
        bhxhcode = bhsbxhb.getBhxhcode();
        return bhxhcode;
    }


    @Override
    public String checkTDXX(String... cond) {
        String tdxxcode = "";
        List<TDXX> list = new ArrayList<>();
        QueryBuilder<TDXX> builder = daoSession.getTDXXDao().queryBuilder();
        builder.where(TDXXDao.Properties.Tdlx.eq(cond[0]));
        if (cond[1] != null && !cond[1].equals("")) {
            builder.where(TDXXDao.Properties.Sffy.eq(cond[1]));
        }
        if (cond[2] != null) {
            builder.where(TDXXDao.Properties.Tdzzxh.eq(cond[2]));
        }
        list.addAll(builder.list());
        if (list != null && list.size() > 0) {
            tdxxcode = list.get(0).getId() + "";
        }
        return tdxxcode;
    }

    @Override
    public RZGL getRzxx(Long dxzj, String type) {
        List<RZGL> rzgl = daoSession.getRZGLDao().queryBuilder().where(RZGLDao.Properties.DXZJ.eq(dxzj + "")
                , RZGLDao.Properties.BBS.eq(type)).orderDesc(RZGLDao.Properties.CZSJ).build().list();
        if (rzgl != null && rzgl.size() > 0) {
            return rzgl.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String getSameBhxhCode(String code, String bblx) {
        List<BHSBXHB> list = daoSession.getBHSBXHBDao().queryBuilder().where(
                BHSBXHBDao.Properties.Code.eq(code)
                , BHSBXHBDao.Properties.Bblx.eq(bblx)).build().list();
        BHSBXHB xhb1 = null;
        if (list != null && list.size() > 0) {
            xhb1 = list.get(0);
        }

        if (xhb1 != null) {
            List<BHSBXHB> list2 = daoSession.getBHSBXHBDao().queryBuilder().where(
                    BHSBXHBDao.Properties.Zzcj.eq(xhb1.getZzcj())
                    , BHSBXHBDao.Properties.Bhlb.eq(xhb1.getBhlb())
                    , BHSBXHBDao.Properties.Sbxh.eq(xhb1.getSbxh())
                    , BHSBXHBDao.Properties.Bblx.eq(xhb1.getBblx())
                    , BHSBXHBDao.Properties.Code.notEq(code)
            ).build().list();
            BHSBXHB xhb2 = null;
            if (list2 != null && list2.size() > 0) {
                for (BHSBXHB bhsbxhb : list2) {
                    if (bhsbxhb.getCode() != null && !bhsbxhb.getCode().equals("x")) {
                        if (bhsbxhb.getSfqy() == null || bhsbxhb.getSfqy() != null && !bhsbxhb.getSfqy().equals("Y")) {
                            xhb2 = bhsbxhb;
                            break;
                        }
                    }
                }
            }
            if (xhb2 != null) {
                return xhb2.getCode();
            }
        }
        return null;
    }

    @Override
    public boolean hasBhpzOrFzbhsb() {


        List<BHPZ> bhpzList = daoSession.getBHPZDao().queryBuilder().
                whereOr(BHPZDao.Properties.Ed_tag.eq("C")
                        , BHPZDao.Properties.Ed_tag.eq("M")
                        , BHPZDao.Properties.Ed_tag.eq("D")
                        , BHPZDao.Properties.Ed_tag.eq("CH")
                        , BHPZDao.Properties.Ed_tag.eq("MH")).
                whereOr(BHPZDao.Properties.Sb.isNull()
                        , BHPZDao.Properties.Sb.notEq("F")).
                build().list();
        if (bhpzList != null && bhpzList.size() > 0) {
            return true;
        }
        daoSession.clear();
        List<FZBHSB> fzbhsbList = daoSession.getFZBHSBDao().queryBuilder().
                whereOr(FZBHSBDao.Properties.Ed_tag.eq("C")
                        , FZBHSBDao.Properties.Ed_tag.eq("M")
                        , FZBHSBDao.Properties.Ed_tag.eq("D")
                        , FZBHSBDao.Properties.Ed_tag.eq("CH")
                        , FZBHSBDao.Properties.Ed_tag.eq("MH")).
                whereOr(FZBHSBDao.Properties.Sb.isNull()
                        , FZBHSBDao.Properties.Sb.notEq("F")).
                build().list();
        return fzbhsbList != null && fzbhsbList.size() > 0;

    }


    @Override
    public List<JGCS> getJgmc(String czmc, String dwmc, String jglx) {
        List<JGCS> jgcs = daoSession.getJGCSDao().queryBuilder().where(
                JGCSDao.Properties.Sh.notEq("已删除")
                , JGCSDao.Properties.Czmc.eq(czmc + "")
                , JGCSDao.Properties.Dwmc.eq(dwmc + "")
                , JGCSDao.Properties.Jglx.eq(jglx + "")
                , JGCSDao.Properties.Sftcyx.eq("否")
        ).build().list();
        return jgcs;
    }

    @Override
    public boolean isSWIDUsed(String swid, String zsjid) {
        List<BHPZ> bhpzList = daoSession.getBHPZDao().queryBuilder()
                .where(BHPZDao.Properties.Sfsbm.eq(swid + "")).build().list();
        if (bhpzList != null && bhpzList.size() > 0) {
            return zsjid == null || zsjid.equals("") || !zsjid.equals(bhpzList.get(0).getId() + "");
        }

        List<FZBHSB> fzbhsbList = daoSession.getFZBHSBDao().queryBuilder()
                .where(FZBHSBDao.Properties.Sfsbm.eq(swid + "")).build().list();
        if (fzbhsbList != null && fzbhsbList.size() > 0) {
            return zsjid == null || zsjid.equals("") || !zsjid.equals(fzbhsbList.get(0).getId() + "");
        }
        return false;
    }

    @Override
    public UserInfo getUserInfo(String userName, String pwd) {
        if (tablesExist(daoSession.getDatabase(), "USER_INFO")) {
            UserInfo info = daoSession.getUserInfoDao().queryBuilder().where(UserInfoDao.Properties.Username.eq(userName)
                    , UserInfoDao.Properties.Password.eq(pwd)).build().unique();
            return info;
        }

        return null;
    }


    /**
     * 判断是否存在表
     *
     * @param db
     * @param tableName
     * @return
     */
    public boolean tablesExist(Database db, String tableName) {
        boolean result = false;
        if (tableName == null)
            return false;
        Cursor cursor = null;
        try {
            String sql = "select count(*) as c from sqlite_master where type='table' and name='" + tableName.trim() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {

        }
        return result;
    }
}
