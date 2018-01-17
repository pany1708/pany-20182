package com.kingthy.platform.mapper.goodsPutonScanner;

import com.kingthy.platform.entity.goodsPutonScanner.GoodsPutonScanner;
import com.kingthy.platform.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * GoodsPutonScannerMapper(商品扫描映射接口)
 * 
 * 陈钊 2017年4月11日 下午7:46:15
 * 
 * @version 1.0.0
 *
 */
public interface GoodsPutonScannerMapper extends MyMapper<GoodsPutonScanner>
{
    /**
     * 
     * findRecordsNeedHandle(查找需要处理的记录)
     * 
     * @return <b>创建人：</b>陈钊<br/>
     *         List<GoodsPutonScanner>
     * @exception @since 1.0.0
     */
    List<GoodsPutonScanner> findRecordsNeedHandle();
    
    /**
     * 
     * updateStatusBatch(批量更新上架状态)
     * 
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int updateStatusBatch(Map<String, Object> paramMap);
    
    /**
     * 
     * deleteBatch(批量删除)
     * 
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int deleteBatch(Map<String, Object> paramMap);
    
    /**
     * 
     * changeDelFlagBatch(批量更改delFlag)
     * 
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int changeDelFlagBatch(Map<String, Object> paramMap);
    
    /**
     * 
     * deleteByGoodsUuid(根据商品uuid删除扫描表中对应记录)
     * 
     * @param goodsUuid
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int deleteByGoodsUuid(String goodsUuid);
}