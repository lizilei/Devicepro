package cn.com.sgcc.dev.dbUtils;

import org.greenrobot.greendao.Property;

import java.util.List;
import java.util.Map;

import cn.com.sgcc.dev.model2.AKXT;
import cn.com.sgcc.dev.model2.AKXTGX;
import cn.com.sgcc.dev.model2.BHLBXH;
import cn.com.sgcc.dev.model2.BHPZ;
import cn.com.sgcc.dev.model2.BHPZXHBBGX;
import cn.com.sgcc.dev.model2.BHSBXHB;
import cn.com.sgcc.dev.model2.BHXHRJBB;
import cn.com.sgcc.dev.model2.BKXX;
import cn.com.sgcc.dev.model2.BZSJ;
import cn.com.sgcc.dev.model2.DWLX;
import cn.com.sgcc.dev.model2.FZBHSB;
import cn.com.sgcc.dev.model2.GXDW;
import cn.com.sgcc.dev.model2.ICDXX;
import cn.com.sgcc.dev.model2.LJQXX;
import cn.com.sgcc.dev.model2.LTYSBXH;
import cn.com.sgcc.dev.model2.PZTDGX;
import cn.com.sgcc.dev.model2.RLST_USER;
import cn.com.sgcc.dev.model2.RZGL;
import cn.com.sgcc.dev.model2.SearchEntity;
import cn.com.sgcc.dev.model2.TDXX;
import cn.com.sgcc.dev.model2.UserInfo;
import cn.com.sgcc.dev.model2.ZZCJ;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.model2.ycsb.JGCS;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/9
 */

public interface IDaoUtil {
    /**
     * 获取标准数据字典值
     *
     * @param bzsjflmc 标准数据分类名称
     * @return
     */
    List<BZSJ> getBZSJ(String bzsjflmc);

    /**
     * 获取管辖单位
     * dwmc 单位名称  默认传null返回所有单位
     *
     * @return
     */
    List<GXDW> getDDDWByDWMC(String dwmc);

    /**
     * 获取单位和厂站
     *
     * @return
     */
    CZCS getCZCSByGLDW();

    /**
     * 获取厂站参数
     *
     * @param ssdw 为空返回所有厂站
     * @return
     */
    List<CZCS> getCZCS(String ssdw,boolean isRegist);

    /**
     * 获取电压等级
     *
     * @param o
     * @param cond 传三个  依次为：czmc、ycsbmc、gldw
     * @return
     */
    Object getCZDYDJ(Object o, String... cond);

    /**
     * 获取设计单位、基建单位、运行单位，维护单位、检修单位
     * 运行单位和维护单位都是（运行/维护单位）
     *
     * @param dwlx
     * @return
     */

    List<DWLX> getDWLX(String dwlx);

    /**
     * 获取跳闸关系
     *
     * @param o
     * @param cond
     * @return
     */
    List<Object> getTZGX(Object o, String... cond);

    /**
     * 获取一次设备名称
     * 线路传值顺序：CZ1,GLDW,CZ2,GLDW2,CZ3,GLDW3
     * 其他：CZMC,GLDW
     *
     * @param o
     * @param cond
     * @return
     */
    List<Object> getYCSBMC(Object o, String... cond);

    /**
     * 从保护或辅助拿一次设备名称
     *
     * @param o    BHPZ.class or FZBHSB.class
     * @param cond DWMC、CZMC、YCSBLX
     * @return
     */
    List<Object> getYCSBMCFromBHOrFZ(Object o, String... cond);

    /**
     * 一次设备类型和一次设备名称
     * '变压器','#2主变'
     * 电压等级(选择一次自动填充电压等级)
     * 参数顺序：一次设备名称、厂站名称、管理单位
     * 线路若有多个厂站和管理单位，则传多个
     *
     * @param o
     * @param cond
     * @return
     */
    List<Object> getYCSBDYDJ(Object o, String... cond);

    /**
     * 保护类别细化
     * 保护类别（标准数据）--选择“线路保护”，查询bhsbxhb条件如下bhlx为当前条件
     *
     * @param bhlb
     * @return
     */
    List<BHLBXH> getBHLBXH(String bhlb);

    /**
     * 制造厂家
     *
     * @return
     */
    List<ZZCJ> getZZCJ(String mc);

    /**
     * 保护型号
     * 需要判断是否为六统一设备，是查询查询 LTYSBXH 表，否 查询 BHSBXHB 表
     *
     * @param isSixSb 是否六统一设备
     * @param cond
     * @return
     */
    List<Object> getBHXH(boolean isSixSb, boolean is2013, String... cond);

    /**
     * 选择型号后带出 保护分类BHFL/保护类型BHLX
     *
     * @param queryType 传BHLX、BHFL、RJXD或BBJYM
     * @param cond
     * @return
     */
    List<BHSBXHB> getBHFLOrBHLX(String queryType, String... cond);

    /**
     * 六统一设备并且是2013版本选择型号后带出 保护分类BHFL/保护类型BHLX
     *
     * @param queryType 传BHLX、BHFL、RJXD或BBJYM
     * @param cond
     * @return
     */
    List<LTYSBXH> getLTYSBXHBHFLOrBHLX(String queryType, String... cond);

    /**
     * 设备类型
     * <p>
     * 传：ZZCJ、BHLB
     *
     * @param cond
     * @return
     */
    List<BHSBXHB> getSBXH(String... cond);

    /**
     * 选择型号、保护类别、保护类型后带出软件版本
     * 参数传值顺序 ：ZZCJ、SBXH、BHLB、BHLX
     *
     * @param cond
     * @return
     */
    List<BHXHRJBB> getBHSBXHRJBB(String... cond);

    /**
     * 获取软件版本信息，code拿
     *
     * @param code
     * @return
     */
    List<BHXHRJBB> getBHSBXHRJBBbyCode(String code);

    /**
     * 获取软件版本信息，code拿
     *
     * @param code
     * @return
     */
    List<LTYSBXH> getLTYSBXHbyCode(String code);

    /**
     * 选择型号、保护类别、保护类型后带出软件版本
     * 参数传值顺序 ：ZZCJ、SBXH、BHLB、BHLX
     *
     * @param cond
     * @return
     */
    List<Object> getFZBHSBXHRJBB(boolean isLty, boolean is2013, String... cond);

    /**
     * 选择型号、保护类别、保护类型后带出软件版本（不分模块）
     * 参数传值顺序 ：ZZCJ、SBXH、BHLB、BHLX
     *
     * @param cond
     * @return
     */

    /**
     * 根据制造厂家和保护型号带出选配功能、ICD文件名、软件版本
     * 传值顺序：ZZCJ、BHXH
     *
     * @param cond
     * @return
     */
    List<LTYSBXH> getLTYSBXHBYBHXH(String... cond);

    /**
     * 获取保护设备
     *
     * @param shEntity
     * @return
     */
    List<BHPZ> getBHPZ(int offset, SearchEntity shEntity);

    /**
     * 获取辅助设备
     *
     * @param shEntity
     * @return
     */
    List<FZBHSB> getFZBHSB(int offset, SearchEntity shEntity);

    /**
     * 获取唯一的变电站
     *
     * @param
     * @return
     */
    CZCS getCZCSONE(String czmc, String gldw, Long czzgdydj);


    long getTotalSb(Object o, SearchEntity shEntity);

    /**
     * 操作保护配置
     *
     * @param type C:新增 M：修改  D:删除
     * @param bhpz
     */
    void coreBHPZ(String type, BHPZ bhpz);

    /**
     * 操作辅助保护设备
     *
     * @param type C:新增 M：修改  D:删除
     * @param bhpz
     */
    void coreFZHBSB(String type, FZBHSB bhpz);

    /**
     * 获取ICD、板卡信息、连接器信息
     *
     * @param o     例  IC：ICDXX.class
     * @param zsjId 主数据ID,即保护/辅助装置ID 保护：BHPZ 辅助：FZBHSB
     * @return
     */
    List<Object> getICDOrBKXX(Object o, String... zsjId);

    /**
     * 获取通道信息
     *
     * @param bhpzId 保护配置Id
     * @return
     */
    List<TDXX> getTDXX(String bhpzId);

    /**
     * 获取通道信息
     *
     * @param cond 保护配置Id
     * @return
     */
    String checkTDXX(String... cond);

    /**
     * 删除通道信息
     *
     * @param bhpzId 保护配置Id
     * @return
     */
    void deleteTDXX(String bhpzId);

    /**
     * 操作连接器方法
     *
     * @param type C:新增 D:删除
     * @param bhpz
     */
    void coreLJQ(String type, LJQXX bhpz);

    /**
     * 操作ICD方法
     *
     * @param type C:新增 D:删除
     * @param bhpz
     */
    void coreICD(String type, ICDXX bhpz);

    /**
     * 需要插入表的类型
     *
     * @param tag 标识哪张表
     * @return
     */
    Long getInsertId(String tag);

    /**
     * 型号code
     *
     * @return
     */
    String getInsertId();


    /**
     * 设备识别代码拿保护配置
     *
     * @param sbsbdm
     * @return
     */
    BHPZ getBHPZBySbsbdm(String sbsbdm);

    /**
     * 实物ID拿保护配置
     *
     * @param swid
     * @return
     */
    BHPZ getBHPZBySwid(String swid);

    /**
     * 连接器二维码拿保护配置
     *
     * @param bianhao
     * @param xinghao
     * @return
     */
    BHPZ getBHPZByLjqSbsbdm(String bianhao, String xinghao);

    /**
     * 设备识别代码找装置
     *
     * @param sbsbdm
     * @return
     */
    Object getFZSBBySbsbdm(String... sbsbdm);

    /**
     * 从出厂信息拿关联台账信息
     *
     * @param sbsbdm
     * @return
     */
    Object getObjectFromCCXX(String sbsbdm);


    /**
     * 获取安控系统调度命名
     *
     * @param
     * @return
     */
    List<AKXT> getAKXTALL(String type);

    /**
     * 获取安控系统调度命名单个
     *
     * @param
     * @return
     */
    List<AKXT> getAKXT(String type);

    /**
     * 获取安控系统关系表
     *
     * @param
     * @return
     */
    List<AKXTGX> getAKXTGX(String id);

    /**
     * 保存通道信息
     *
     * @param
     * @return
     */
    void saveTDXX(TDXX tdxx);

    /**
     * 保存软件版本信息
     *
     * @param
     * @return
     */
    void coreSave(Object o);

    /**
     * 删除操作
     *
     * @param
     */
    void coreDelte(String szsbid);

    /**
     * 保存并关联通道信息和保护配置
     *
     * @param
     * @return
     */
    void saveTDXXPZ(PZTDGX pztdgx);

    /**
     * 保护查重
     *
     * @param
     * @return
     */
    BHPZ getBHPZMC(String BHMC);

    /**
     * 保护和辅佐型号变更修改国调
     *
     * @param
     * @return
     */
    void getBHPZorFZSBbyxh(String XH, String type);

    /**
     * 保存并关联安控系统
     *
     * @param
     * @return
     */
    void saveAKXTGX(AKXTGX akxt);

    /**
     * 更新删除关联安控系统
     *
     * @param
     * @return
     */
    void deleteAKXTGX(String bhpzid);

    /**
     * 获取软件版本关系表
     *
     * @param
     * @return
     */
    List<BHPZXHBBGX> getBHPZXHBBGX(String bhpzid);

    /**
     * 更新删除关联软件版本
     *
     * @param
     * @return
     */
    void deleteBHPZXHBBGX(String bhpzid);

    /**
     * 根据辅助保护设备Id取出模块、版本、生成日期等
     *
     * @param isLty
     * @param is2013
     * @param fzbhsbId
     * @return
     */
    List<Object> getRJBBByCode(boolean isLty, boolean is2013, String fzbhsbId);

    /**
     * 根据保护设备Id取出模块、版本、生成日期等
     *
     * @param isLty
     * @param is2013
     * @param fzbhsbId
     * @return
     */
    List<Object> getBHRJBBByCode(boolean isLty, boolean is2013, String fzbhsbId);

    /**
     * 从出厂信息板卡带出板卡信息
     *
     * @param sbsbdm
     * @return
     */
    List<BKXX> getCCXXBK(String sbsbdm);

    /**
     * 从出厂信息软件版本带出软件版本信息
     *
     * @param sbsbdm
     * @return
     */
    List<Object> getCCXXRJBB(boolean isSix, boolean is2013, String sbsbdm);

    /**
     * 获取所有保护配置内容
     *
     * @return
     */
    List<BHPZ> getALLBHPZ();

    /**
     * 通过code获取软件版本
     *
     * @param code
     * @return
     */
    BHXHRJBB getBHXHRJBBByCode(String code);

    /**
     * 通过code获取保护设备型号
     *
     * @param code
     * @param isSix
     * @param is2013
     * @return
     */
    Object getBHXHByCode(String code, boolean isSix, boolean is2013);


    ICDXX getICDXXFromCCXX(String sbsbdm);

    /**
     * 保存日志信息接口
     *
     * @param o
     */
    void coreSavaRZXX(Object o);

    /**
     * 导出操作的db及关系
     */
    void outputDB(List<String> czmcList, boolean isOutPut);

    /**
     * 是否通过校验
     */
    boolean isJY(List<String> czmcList);

    /**
     * 根据用户名拿用户信息
     *
     * @param userName
     * @return
     */
    RLST_USER getUserByName(String userName);

    /**
     * 根据BHXHRJBB 去查找该软件版本是否存在
     * 存在返回code,不存在返回null
     * 软件版本和六统一设备型号共用此接口
     *
     * @param o
     * @return
     */
    String getCodeByBhxhRjbb(Object o);

    /**
     * 根据BHXHRJBB code去查找该软件版本bhxhcode
     * 存在返回code,不存在返回null
     */
    String getbhxhCode(String code);

    /**
     * 获取日志信息
     *
     * @param dxzj
     * @param type 辅助-FZSB 保护-BHPZ
     * @return
     */
    RZGL getRzxx(Long dxzj, String type);

    /**
     * 根据bhxhcode反查bhsbxhb，返回是否存在相同的型号
     *
     * @param code
     * @param bblx
     * @return
     */
    String getSameBhxhCode(String code, String bblx);

    boolean hasBhpzOrFzbhsb();

    /**
     * 间隔名称
     *
     * @return
     */
    List<JGCS> getJgmc(String czmc, String dwmc, String jglx);

    /**
     * 根据实物id判断是否id被使用
     *
     * @param swid
     * @param zsjid
     * @return
     */
    boolean isSWIDUsed(String swid, String zsjid);

    /**
     * 获取六统一相关信息
     *
     * @param map
     * @return
     */
    List<LTYSBXH> getLtyXX(Map<Property, String> map);

    /**
     * 删除无用的型号的版本
     */
    void deleteXhAndRjbb();

    /**
     * 获取运维在线登录账号的用户信息
     * @return
     */
    UserInfo getUserInfo(String userName,String pwd);
}
