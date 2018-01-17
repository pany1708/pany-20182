package com.kingthy.platform.service.impl.report;

import com.kingthy.platform.entity.report.ReportGoodsData;
import com.kingthy.platform.mapper.report.ReportGoodsDataMapper;
import com.kingthy.platform.service.report.ReportGoodsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportGoodsDataServiceImpl
 * @description 商品数据统计结果实现类
 * @create 2017/7/28
 */
@Service(value = "reportGoodsDataService")
public class ReportGoodsDataServiceImpl implements ReportGoodsDataService {

    @Autowired
    private ReportGoodsDataMapper reportGoodsDataMapper;

    @Override
    @Transactional
    public void add(ReportGoodsData reportGoodsData) {
        reportGoodsDataMapper.deleteByDataTimeType(reportGoodsData);
        reportGoodsDataMapper.insert(reportGoodsData);
    }

    @Override
    public List<ReportGoodsData> findByTimeType(int timeType) {
        return reportGoodsDataMapper.findByTimeType(timeType);
    }
}
