package com.kingthy.platform.service.impl.report;

import com.kingthy.platform.dto.report.GoodsPriceRangeNumDto;
import com.kingthy.platform.entity.report.ReportOnsaleGoods;
import com.kingthy.platform.mapper.report.ReportOnsaleGoodsMapper;
import com.kingthy.platform.service.report.ReportOnsaleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportOnsaleGoodsServiceImpl
 * @description 上架商品价格分布实现类
 * @create 2017/7/31
 */
@Service(value = "reportOnsaleGoodsService")
public class ReportOnsaleGoodsServiceImpl implements ReportOnsaleGoodsService {

    @Autowired
    private ReportOnsaleGoodsMapper reportOnsaleGoodsMapper;

    @Override
    @Transactional
    public void add(List<GoodsPriceRangeNumDto> list) {
        reportOnsaleGoodsMapper.deleteAll();
        Date date = new Date();
        for (GoodsPriceRangeNumDto goodsPriceRangeNumDto:list){
            ReportOnsaleGoods reportOnsaleGoods = new ReportOnsaleGoods();
            reportOnsaleGoods.setNum(goodsPriceRangeNumDto.getNum());
            reportOnsaleGoods.setPricerange(goodsPriceRangeNumDto.getPriceRange());
            reportOnsaleGoods.setCreateDate(date);
            reportOnsaleGoods.setVersion(1);
            reportOnsaleGoods.setDelFlag(false);
            reportOnsaleGoodsMapper.insert(reportOnsaleGoods);
        }
    }

    @Override
    public List<ReportOnsaleGoods> findAll() {
        return reportOnsaleGoodsMapper.selectAll();
    }
}
