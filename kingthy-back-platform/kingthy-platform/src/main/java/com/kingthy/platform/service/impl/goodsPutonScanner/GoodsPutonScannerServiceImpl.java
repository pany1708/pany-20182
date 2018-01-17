package com.kingthy.platform.service.impl.goodsPutonScanner;

import com.kingthy.platform.entity.goodsPutonScanner.GoodsPutonScanner;
import com.kingthy.platform.mapper.goods.GoodsMapper;
import com.kingthy.platform.mapper.goodsPutonScanner.GoodsPutonScannerMapper;
import com.kingthy.platform.service.goodsPutonScanner.GoodsPutonScannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * GoodsPutonScannerServiceImpl(商品扫描实现类)
 * 
 * 陈钊 2017年4月7日 上午11:26:12
 * 
 * @version 1.0.0
 *
 */
@Service(value = "goodsPutonScannerService")
public class GoodsPutonScannerServiceImpl implements GoodsPutonScannerService
{
    private static final Logger LOG = LoggerFactory.getLogger(GoodsPutonScannerServiceImpl.class);
    
    /**
     * 已上架
     */
    private static final Integer on = 1;
    
    private static final String modifier = "system";
    
    @Autowired
    private GoodsPutonScannerMapper goodsPutonScannerMapper;
    
    @Autowired
    private GoodsMapper goodsMapper;
    
    @Transactional
    @Override
    public void toUpOnGoods()
    {
        // 查询出所有未上架的，并且时间在当前时间之前的任务记录
        List<GoodsPutonScanner> goodsPutonScannerList = goodsPutonScannerMapper.findRecordsNeedHandle();
        
        if (goodsPutonScannerList != null && goodsPutonScannerList.size() > 0)
        {
            Date currentDate = new Date();
            Map<String, Object> goodsPutOnParam = new HashMap<String, Object>();
            goodsPutOnParam.put("goodsPutonScannerList", goodsPutonScannerList);
            goodsPutOnParam.put("status", on);
            goodsPutOnParam.put("modifyDate", currentDate);
            goodsPutOnParam.put("updater", modifier);
            int goodsResult = goodsMapper.updateStatusBatch(goodsPutOnParam);
            Map<String, Object> goodsPutonScannerParam = new HashMap<String, Object>();
            goodsPutonScannerParam.put("goodsPutonScannerList", goodsPutonScannerList);
            goodsPutonScannerParam.put("status", true);
            goodsPutonScannerParam.put("modifyDate", currentDate);
            goodsPutonScannerParam.put("modifier", modifier);
            int goodsPutonScannerResult = goodsPutonScannerMapper.updateStatusBatch(goodsPutonScannerParam);
            // 更新记录数量不一致则进行记录，以便后面处理
            if (goodsResult != goodsPutonScannerResult)
            {
                LOG.info("扫描表更新：" + goodsPutonScannerResult + "条记录" + ",商品表更新：" + goodsResult + "条记录" + "本次扫描更新商品信息："
                    + goodsPutonScannerList);
            }
        }
    }
    
}
