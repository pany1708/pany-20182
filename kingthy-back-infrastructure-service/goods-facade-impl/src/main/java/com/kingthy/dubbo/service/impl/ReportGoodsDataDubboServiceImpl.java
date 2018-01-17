package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dubbo.goods.service.ReportGoodsDataDubboService;
import com.kingthy.entity.ReportGoodsData;
import com.kingthy.mapper.ReportGoodsDataMapper;
import com.kingthy.transaction.ReportGoodsDataTransaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportGoodsDataDubboServiceImpl
 * @description 商品数据统计结果接口实现类
 * @create 2017/8/28
 */
@Service(version = "1.0.0",timeout = 10000)
public class ReportGoodsDataDubboServiceImpl implements ReportGoodsDataDubboService {

    @Autowired
    private ReportGoodsDataMapper reportGoodsDataMapper;

    @Autowired
    private ReportGoodsDataTransaction reportGoodsDataTransaction;

    @Override
    public void add(ReportGoodsData reportGoodsData) {
        reportGoodsDataTransaction.add(reportGoodsData);
    }

    @Override
    public List<ReportGoodsData> findByTimeType(int timeType) {
        return reportGoodsDataMapper.findByTimeType(timeType);
    }
}
