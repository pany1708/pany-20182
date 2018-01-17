package com.kingthy.transaction;

import com.kingthy.entity.ReportGoodsData;
import com.kingthy.mapper.ReportGoodsDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportGoodsDataTransaction
 * @description 商品数据统计结果事务处理层
 * @create 2017/8/28
 */
@Component
public class ReportGoodsDataTransaction {
    @Autowired
    private ReportGoodsDataMapper reportGoodsDataMapper;

    @Transactional
    public void add(ReportGoodsData reportGoodsData) {
        reportGoodsDataMapper.deleteByDataTimeType(reportGoodsData);
        reportGoodsDataMapper.insert(reportGoodsData);
    }
}
